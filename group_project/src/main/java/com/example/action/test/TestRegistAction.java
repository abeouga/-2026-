package com.example.action.test;
import com.example.config.dao.ClassNumDao;
import com.example.config.dao.SubjectDao;
import com.example.config.dao.TestDao;

import java.util.List;

import com.example.model.School;
import com.example.model.Subject;
import com.example.model.Teacher;
import com.example.model.Test;
import com.example.action.Action;
import jakarta.servlet.http.*;

public class TestRegistAction implements Action{
    public void execute(
        HttpServletRequest request,HttpServletResponse response
    )throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("teacher");

        if (teacher == null) {
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            return;
        }

        School school = teacher.getSchool();
        SubjectDao sdao = new SubjectDao();
        ClassNumDao cdao = new ClassNumDao();

        List<String> classList = cdao.filter(school.getCd());
        List<Subject> subjectList = sdao.filter(school.getCd());

        request.setAttribute("classList", classList);
        request.setAttribute("subjectList", subjectList);

        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");
        String noStr = request.getParameter("no");

        if (entYearStr != null && classNum != null && subjectCd != null && noStr != null
            && !entYearStr.isEmpty() && !classNum.isEmpty()
            && !subjectCd.isEmpty() && !noStr.isEmpty()) {

            int entYear = Integer.parseInt(entYearStr);
            int no = Integer.parseInt(noStr);

            Subject subject = new Subject();
            subject.setCd(subjectCd);

            TestDao tdao = new TestDao();

            List<Test> list = tdao.filter(entYear, classNum, subject, no, school);

            request.setAttribute("testList", list);
        }
        request.getRequestDispatcher("/WEB-INF/views/test/test_regist.jsp").forward(request, response);
    }

}