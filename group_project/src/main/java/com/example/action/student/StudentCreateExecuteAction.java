package com.example.action.student;

import com.example.action.Action;

import com.example.config.dao.ClassNumDao;
import com.example.config.dao.StudentDao;
import com.example.model.Student;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class StudentCreateExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        String entYearStr = req.getParameter("entYear");
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String classNum = req.getParameter("classNum");


        // 入学年度リストを生成
        java.util.List<Integer> entYearSet = new java.util.ArrayList<>();
        int year = java.time.LocalDate.now().getYear();
        for (int i = year - 10; i <= year + 1; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);

        // クラス番号リストを取得
        com.example.config.dao.ClassNumDao classNumDao = new com.example.config.dao.ClassNumDao();
        java.util.List<String> classNumSet = classNumDao.filter(teacher.getSchoolCd());
        req.setAttribute("class_num_set", classNumSet);

        boolean error = false;

        if (no == null || no.trim().isEmpty()) {
            req.setAttribute("noError", "学生番号を入力してください");
            error = true;
        }

        if (entYearStr == null || entYearStr.isEmpty() || entYearStr.equals("0")) {
            req.setAttribute("ent_year_error", true);
            error = true;
        }

        if (name == null || name.isEmpty()) {
            req.setAttribute("nameError", "このフィールドを入力してください");
            error = true;
        }

        // クラス番号の妥当性チェック
        if (classNum != null) {
            classNum = classNum.trim();
        }
        if (classNum == null || !classNumSet.contains(classNum)) {
            req.setAttribute("classNumError", "有効なクラスを選択してください");
            error = true;
        } else if (classNum.length() > 3) {
            req.setAttribute("classNumError", "クラス番号は3文字以内で入力してください");
            error = true;
        }

        if (error) {
            req.setAttribute("entYear", entYearStr);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("classNum", classNum);
            req.getRequestDispatcher("/WEB-INF/views/student/student_create.jsp").forward(req, res);
            return;
        }

        // 学生番号の重複チェック
        StudentDao dao = new StudentDao();
        if (no != null) no = no.trim();
        Student existing = dao.get(teacher.getSchoolCd(), no);
        if (existing != null) {
            req.setAttribute("entYear", entYearStr);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("classNum", classNum);
            req.setAttribute("no_duplicate_error", true);
            req.getRequestDispatcher("/WEB-INF/views/student/student_create.jsp").forward(req, res);
            return;
        }

        int entYear = Integer.parseInt(entYearStr);

        Student student = new Student();
        student.setSchoolCd(teacher.getSchoolCd());
        student.setEntYear(entYear);
        // 入力された学生番号をそのままセットして保存
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setIsAttend(true);

        dao.save(student);

        req.setAttribute("student", student);
        req.getRequestDispatcher("/WEB-INF/views/student/studentCreateComplete.jsp").forward(req, res);
    }
}

