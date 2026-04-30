package com.example.action.auth;

import com.example.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TeacherRegisterAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, res);
    }
}
