package com.example.action.subject;

import com.example.action.Action;

import com.example.dao.SubjectDao;
import com.example.model.Subject;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SubjectUpdateExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        if (name == null || name.isEmpty()) {
            Subject subject = new Subject(teacher.getSchoolCd(), cd, name);
            req.setAttribute("subject", subject);
            req.setAttribute("nameError", "このフィールドを入力してください");
            req.getRequestDispatcher("/WEB-INF/views/subject/subject_update.jsp").forward(req, res);
            return;
        }

        Subject subject = new Subject(teacher.getSchoolCd(), cd, name);
        SubjectDao dao = new SubjectDao();
        dao.save(subject);

        req.setAttribute("subject", subject);
        req.getRequestDispatcher("/WEB-INF/views/subject/subject_update_done.jsp").forward(req, res);
    }
}

