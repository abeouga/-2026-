package com.example.action.student;

import com.example.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentCreateAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.getRequestDispatcher("/WEB-INF/views/student/student_create.jsp").forward(req, res);
    }
}

