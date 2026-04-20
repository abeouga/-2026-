package com.example.action.subject;

import com.example.action.Action;

import com.example.dao.SubjectDao;
import com.example.model.Subject;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class SubjectListAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        SubjectDao dao = new SubjectDao();
        List<Subject> subjects = dao.filter(teacher.getSchoolCd());

        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("/WEB-INF/views/subject/subject_list.jsp").forward(req, res);
    }
}

