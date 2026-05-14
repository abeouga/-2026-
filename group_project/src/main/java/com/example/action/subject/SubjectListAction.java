package com.example.action.subject;

import com.example.config.dao.SubjectDao;
import com.example.model.Teacher;
import com.example.model.Subject;
import com.example.action.Action;
import java.util.List;
import jakarta.servlet.http.*;

public class SubjectListAction implements Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher user = (Teacher) session.getAttribute("user");
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        SubjectDao dao = new SubjectDao();
        List<Subject> list = dao.filter(user.getSchoolCd());

        java.util.List<Subject> list1 = new java.util.ArrayList<>();
        java.util.List<Subject> list2 = new java.util.ArrayList<>();
        
        for (Subject s : list) {
            if (s.getCd() != null && s.getCd().startsWith("1")) {
                list1.add(s);
            } else if (s.getCd() != null && s.getCd().startsWith("2")) {
                list2.add(s);
            }
        }

        session.setAttribute("list1", list1);
        session.setAttribute("list2", list2);

        req.getRequestDispatcher("/WEB-INF/views/subject/subject_list.jsp").forward(req, res);
    }
}