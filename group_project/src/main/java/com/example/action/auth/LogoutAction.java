package com.example.action.auth;

import com.example.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }
        res.sendRedirect(req.getContextPath() + "/login.action");
    }
}

