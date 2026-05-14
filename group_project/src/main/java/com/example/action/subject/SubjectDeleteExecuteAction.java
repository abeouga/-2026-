package com.example.action.subject;

import com.example.config.dao.SubjectDao;
import com.example.action.Action;
import com.example.model.Teacher;
import com.example.model.Subject;
import jakarta.servlet.http.*;

public class SubjectDeleteExecuteAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher user = (Teacher) session.getAttribute("user");
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }
        
        String code = req.getParameter("cd");

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(user.getSchoolCd(), code);
        boolean line = dao.delete(subject);

        session.setAttribute("line", line);

        req.getRequestDispatcher("/WEB-INF/views/subject/subject_delete_done.jsp").forward(req, res);
    }
}