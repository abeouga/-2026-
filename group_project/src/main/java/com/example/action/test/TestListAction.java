package com.example.action.test;
import com.example.bean.Teacher;
import com.example.bean.TestListStudent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import com.example.bean.ClassNum;
import com.example.bean.School;
import com.example.bean.Subject;
import com.example.dao.ClassNumDao;
import com.example.dao.SubjectDao;
import com.example.dao.TestListStudentDao;
import com.example.tool.Action;

public class TestListAction extends Action{
    public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        School school = teacher.getSchool();

        String subjectCd = request.getParameter("subjectCd");
        String classNum  = request.getParameter("classNum");

        SubjectDao sdao = new SubjectDao();
        Subject subject = null;

        if (subjectCd != null && !subjectCd.isEmpty()) {
            subject = sdao.get(subjectCd, school);
        }
        TestListStudentDao dao = new TestListStudentDao();
        List<TestListStudent> list = null;
        if (subject != null && classNum != null && !classNum.isEmpty()) {
            list = dao.filter(classNum, subject, school);
        }

        request.setAttribute("testList",list);
        

        //request.getRequestDispatcher("test_list.jsp").forward(request, response);
        return "test_list.jsp";
        
    }
}
