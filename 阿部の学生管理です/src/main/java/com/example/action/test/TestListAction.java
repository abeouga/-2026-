package com.example.action.test;

import com.example.action.Action;

import com.example.dao.ClassNumDao;
import com.example.dao.SubjectDao;
import com.example.model.ClassNum;
import com.example.model.Subject;
import com.example.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class TestListAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.action");
            return;
        }

        ClassNumDao classNumDao = new ClassNumDao();
        List<ClassNum> classNumList = classNumDao.filter(teacher.getSchoolCd());

        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(teacher.getSchoolCd());

        req.setAttribute("classNumList", classNumList);
        req.setAttribute("subjectList", subjectList);

        req.getRequestDispatcher("/WEB-INF/views/test/test_list.jsp").forward(req, res);
    }
}

