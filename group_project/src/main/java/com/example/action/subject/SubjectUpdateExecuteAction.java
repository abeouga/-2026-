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
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        
        String code = req.getParameter("cd");
        String name = req.getParameter("name");

        if (name.length() > 20) {
            Subject subject = new Subject(user.getSchoolCd(), code, name);
            req.setAttribute("subject", subject);
            req.setAttribute("error", "科目名が長すぎます。20文字に収めてください");
            req.getRequestDispatcher("/WEB-INF/views/subject/subject_update.jsp").forward(req, res);
            return;
        }

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(user.getSchoolCd(), code);

        if (subject == null) {
            req.setAttribute("error", "指定された科目が存在しません");
            req.getRequestDispatcher("/WEB-INF/views/subject/subject_update.jsp").forward(req, res);
            return;
        }

        subject.setName(name);
        boolean line = dao.save(subject);

        session.setAttribute("line", line);

        req.getRequestDispatcher("/WEB-INF/views/subject/subject_update_done.jsp").forward(req, res);
    }
}