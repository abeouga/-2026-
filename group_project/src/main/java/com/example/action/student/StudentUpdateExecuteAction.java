package com.example.action.student;

import com.example.action.Action;

import com.example.config.dao.StudentDao;
import com.example.model.Student;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class StudentUpdateExecuteAction implements Action {
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
        String oldNo = req.getParameter("oldNo"); // 追加
        String name = req.getParameter("name");
        String classNum = req.getParameter("classNum");
        String isAttendStr = req.getParameter("isAttend");

        // クラス番号リストを取得
        com.example.config.dao.ClassNumDao classNumDao = new com.example.config.dao.ClassNumDao();
        java.util.List<String> classNumSet = classNumDao.filter(teacher.getSchoolCd());

        boolean error = false;
        StudentDao dao = new StudentDao();

        if (name == null || name.isEmpty()) {
            req.setAttribute("nameError", "このフィールドを入力してください");
            error = true;
        }

        // 学生番号の重複チェック（変更された場合のみ）
        if (no != null && !no.equals(oldNo)) {
            Student existing = dao.get(teacher.getSchoolCd(), no);
            if (existing != null) {
                req.setAttribute("noError", "学生番号が重複しています");
                error = true;
            }
        }

        // クラス番号の妥当性チェック
        if (classNum == null || !classNumSet.contains(classNum)) {
            req.setAttribute("classNumError", "有効なクラスを選択してください");
            error = true;
        }

        if (error) {
            Student student = new Student(teacher.getSchoolCd(), no, name, Integer.parseInt(entYearStr), classNum, isAttendStr != null);
            req.setAttribute("student", student);
            req.setAttribute("oldNo", oldNo); // エラー時も保持

            // 入学年度リストを生成
            java.util.List<Integer> entYearSet = new java.util.ArrayList<>();
            int currentYear = java.time.LocalDate.now().getYear();
            for (int i = currentYear - 10; i <= currentYear + 1; i++) {
                entYearSet.add(i);
            }
            req.setAttribute("ent_year_set", entYearSet);
            req.setAttribute("class_num_set", classNumSet);

            req.getRequestDispatcher("/WEB-INF/views/student/student_update.jsp").forward(req, res);
            return;
        }

        Student student = new Student();
        student.setSchoolCd(teacher.getSchoolCd());
        student.setEntYear(Integer.parseInt(entYearStr));
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setIsAttend(isAttendStr != null);

        dao.update(student, oldNo); // save ではなく update を呼ぶ

        req.setAttribute("student", student);
        req.getRequestDispatcher("/WEB-INF/views/student/studentUpdateComplete.jsp").forward(req, res);
    }
}

