package com.example.action.schoolclass;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.action.Action;

public class SchoolClassCreateAction implements Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.getRequestDispatcher("/WEB-INF/views/schoolclass/school_class_create.jsp").forward(req, res);
    }
}
