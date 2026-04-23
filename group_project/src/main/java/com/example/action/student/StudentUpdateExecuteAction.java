package com.example.action.student;

import com.example.action.Action;

import com.example.dao.StudentDao;
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
        String name = req.getParameter("name");
        String classNum = req.getParameter("classNum");
        String isAttendStr = req.getParameter("isAttend");

        if (name == null || name.isEmpty()) {
            Student student = new Student(teacher.getSchoolCd(), no, name, Integer.parseInt(entYearStr), classNum, isAttendStr != null);
            req.setAttribute("student", student);
            req.setAttribute("nameError", "このフィールドを入力してください");
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

        StudentDao dao = new StudentDao();
        dao.save(student);

        req.setAttribute("student", student);
        req.getRequestDispatcher("/WEB-INF/views/student/student_update_done.jsp").forward(req, res);
    }
}

