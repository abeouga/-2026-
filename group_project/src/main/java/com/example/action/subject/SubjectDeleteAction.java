package com.example.action.subject;

import com.example.dao.SubjectDao;
import com.example.model.Teacher;
import com.example.model.Subject;
import com.example.action.Action;
import jakarta.servlet.http.*;

public class SubjectDeleteAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        Teacher user = (Teacher)session.getAttribute("user");

        String code = req.getParameter("code");

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(user.getSchoolCd(), code);

        session.setAttribute("subject", subject);

        req.getRequestDispatcher("/WEB-INF/views/subject/subject_delete.jsp").forward(req, res);
    }
}