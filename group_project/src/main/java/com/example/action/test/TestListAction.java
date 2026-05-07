package com.example.action.test;
import com.example.model.Teacher;
import com.example.model.TestListStudent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import com.example.model.ClassNum;
import com.example.model.School;
import com.example.model.Subject;
import com.example.config.dao.ClassNumDao;
import com.example.config.dao.SubjectDao;
import com.example.config.dao.TestListStudentDao;
import com.example.action.Action;

public class TestListAction implements Action{
    public void execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");
        if (teacher == null) {
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            return;
        }
        School school = teacher.getSchool();

        String subjectCd = request.getParameter("subjectCd");
        String classNum  = request.getParameter("classNum");

        request.getRequestDispatcher("/WEB-INF/views/test/test_list.jsp").forward(request, response);
    }
}
