package com.example.action.student;

import com.example.action.Action;
import com.example.dao.StudentDao;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class StudentDeleteExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        String no = req.getParameter("no");
        if (no != null && !no.isEmpty()) {
            StudentDao dao = new StudentDao();
            dao.delete(teacher.getSchoolCd(), no);
        }

        req.getRequestDispatcher("/WEB-INF/views/student/studentDeleteComplete.jsp").forward(req, res);
    }
}
