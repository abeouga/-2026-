package com.example.action.student.csv;

import com.example.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentCsvImportAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.getRequestDispatcher("/WEB-INF/views/student/student_csv_import.jsp").forward(req, res);
    }
}
