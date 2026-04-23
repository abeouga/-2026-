package com.example.action.student;

import com.example.action.Action;

import com.example.dao.StudentDao;
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

        boolean error = false;

        if (entYearStr == null || entYearStr.isEmpty() || entYearStr.equals("0")) {
            req.setAttribute("entYearError", "入学年度を選択してください");
            error = true;
        }
        if (no == null || no.isEmpty()) {
            req.setAttribute("noError", "このフィールドを入力してください");
            error = true;
        }
        if (name == null || name.isEmpty()) {
            req.setAttribute("nameError", "このフィールドを入力してください");
            error = true;
        }

        if (error) {
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("classNum", classNum);
            req.getRequestDispatcher("/WEB-INF/views/student/student_create.jsp").forward(req, res);
            return;
        }

        Student student = new Student();
        student.setSchoolCd(teacher.getSchoolCd());
        student.setEntYear(Integer.parseInt(entYearStr));
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setIsAttend(true);

        StudentDao dao = new StudentDao();
        // 重複チェック
        Student existing = dao.get(teacher.getSchoolCd(), no);
        if (existing != null) {
            req.setAttribute("duplicateError", "学生番号が重複しています");
            req.setAttribute("entYear", entYearStr);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("classNum", classNum);
            req.getRequestDispatcher("/WEB-INF/views/student/student_create.jsp").forward(req, res);
            return;
        }

        dao.save(student);

        req.setAttribute("student", student);
        req.getRequestDispatcher("/WEB-INF/views/student/student_create_done.jsp").forward(req, res);
    }
}

