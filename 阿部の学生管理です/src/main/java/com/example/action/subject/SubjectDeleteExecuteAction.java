package com.example.action.subject;

import com.example.action.Action;

import com.example.dao.SubjectDao;
import com.example.model.Subject;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SubjectDeleteExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        String cd = req.getParameter("cd");

        Subject subject = new Subject();
        subject.setSchoolCd(teacher.getSchoolCd());
        subject.setCd(cd);

        SubjectDao dao = new SubjectDao();
        dao.delete(subject);

        req.getRequestDispatcher("/WEB-INF/views/subject/subject_delete_done.jsp").forward(req, res);
    }
}

