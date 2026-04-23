package com.example.action.subject;

import jakarta.servlet.http.*;
import com.example.action.Action;

public class SubjectCreateAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.getRequestDispatcher("/WEB-INF/views/subject/subject_create.jsp").forward(req, res);

    }
}