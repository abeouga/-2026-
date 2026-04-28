package com.example.action.subject;

import com.example.config.dao.SubjectDao;
import com.example.model.Subject;
import com.example.model.Teacher;
import com.example.action.Action;
import jakarta.servlet.http.*;

public class SubjectUpdateExecuteAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        Teacher user = (Teacher)session.getAttribute("user");
        
        String code = req.getParameter("cd");
        String name = req.getParameter("name");

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(user.getSchoolCd(), code);
        subject.setName(name);
        boolean line = dao.save(subject);

        session.setAttribute("line", line);

        req.getRequestDispatcher("/WEB-INF/views/subject/subject_update_done.jsp").forward(req, res);
    }
}