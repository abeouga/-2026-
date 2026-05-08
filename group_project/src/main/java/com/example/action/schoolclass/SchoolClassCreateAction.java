package com.example.action.schoolclass;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.action.Action;
import com.example.model.Teacher;

public class SchoolClassCreateAction implements Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/schoolclass/school_class_create.jsp").forward(req, res);
    }
}
