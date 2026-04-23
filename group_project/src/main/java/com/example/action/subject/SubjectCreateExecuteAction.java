package com.example.action.subject;

import com.example.dao.SubjectDao;
import com.example.model.Teacher;
import com.example.model.Subject;
import com.example.action.Action;
import jakarta.servlet.http.*;

public class SubjectCreateExecuteAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        Teacher user = (Teacher)session.getAttribute("user");
        
        String code = req.getParameter("code");
        String name = req.getParameter("name");

        if (code == null) {
            req.setAttribute("code", code);
            req.setAttribute("name", name);
            req.setAttribute("error", "科目コード、科目名をセットし、科目コードは３文字で入力してください");
            req.getRequestDispatcher("/student-management-v2/subject_list.jsp").forward(req, res);
        }

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(user.getSchoolCd(), code);
        if (subject == null) {
            req.setAttribute("code", code);
            req.setAttribute("name", name);
            req.setAttribute("error", "科目コードが重複しています");
            req.getRequestDispatcher("/subject_list.jsp").forward(req, res);
        }
        boolean line = dao.save(subject);

        session.setAttribute("line", line);

        req.getRequestDispatcher("/WEB-INF/views/subject/subject_create_done.jsp").forward(req, res);
    }
}