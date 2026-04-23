package com.example.action.subject;

import com.example.dao.SubjectDao;
import com.example.model.Teacher;
import com.example.model.Subject;
import com.example.action.Action;
import java.util.List;
import jakarta.servlet.http.*;

public class SubjectListAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        Teacher user = (Teacher)session.getAttribute("user");

        SubjectDao dao = new SubjectDao();
        List<Subject> list = dao.filter(user.getSchoolCd());

        session.setAttribute("list", list);

        req.getRequestDispatcher("/WEB-INF/views/subject/subject_list.jsp").forward(req, res);
    }
}