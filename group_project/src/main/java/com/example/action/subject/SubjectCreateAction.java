package com.example.action.subject;

import jakarta.servlet.http.*;
import com.example.action.Action;
import com.example.model.Teacher;

public class SubjectCreateAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

                HttpSession session = req.getSession();
        Teacher user = (Teacher) session.getAttribute("user");
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/views/subject/subject_create.jsp").forward(req, res);

    }
}