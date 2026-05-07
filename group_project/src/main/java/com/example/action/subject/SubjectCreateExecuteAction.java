package com.example.action.subject;

import com.example.config.dao.SubjectDao;
import com.example.model.Teacher;
import com.example.model.Subject;
import com.example.action.Action;
import jakarta.servlet.http.*;

public class SubjectCreateExecuteAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        Teacher user = (Teacher) session.getAttribute("user");

        String code = req.getParameter("cd");
        String name = req.getParameter("name");
        // バリデーションね
        if (code == null || name == null || !code.matches("^(10[1-5]|20[1-5])$")) {
            req.setAttribute("cd", code);
            req.setAttribute("name", name);
            req.setAttribute("error", "科目コードは101〜105、または201〜205の形式で入力してください");
            req.getRequestDispatcher("/WEB-INF/views/subject/subject_create.jsp").forward(req, res);
            return;
        }

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(user.getSchoolCd(), code);
        if (subject != null) {
            req.setAttribute("cd", code);
            req.setAttribute("name", name);
            req.setAttribute("error", "科目コードが重複しています");
            req.getRequestDispatcher("/WEB-INF/views/subject/subject_create.jsp").forward(req, res);
            return;
        }
        Subject newSubject = new Subject();
        newSubject.setCd(code);
        newSubject.setName(name);
        newSubject.setSchoolCd(user.getSchoolCd());
        boolean line = dao.save(newSubject);

        session.setAttribute("line", line);

        req.getRequestDispatcher("/WEB-INF/views/subject/subject_create_done.jsp").forward(req, res);
    }
}