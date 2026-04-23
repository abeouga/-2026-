package com.example.action.test;


import java.util.List;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.tool.Action;
import com.example.bean.School;
import com.example.bean.Student;
import com.example.bean.Subject;
import com.example.bean.Teacher;
import com.example.bean.TestListStudent;
import com.example.dao.ClassNumDao;
import com.example.dao.StudentDao;
import com.example.dao.SubjectDao;
import com.example.dao.TestListStudentDao;

public class TestListSubjectExecuteAction extends Action{
    public String execute(HttpServletRequest request,HttpServletResponse responce)throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        School school = teacher.getSchool();
        SubjectDao sdao = new SubjectDao();
        ClassNumDao cdao = new ClassNumDao();
        StudentDao stdao = new StudentDao();
        Subject subject = null;
        
        List<Subject> list = sdao.filter(school);
        request.setAttribute("subjectList",list);
        List<String> list2 = cdao.filter(school);
        request.setAttribute("classList",list2);
        
        String entYear = request.getParameter("entYear");
        String subjectCd = request.getParameter("subjectCd");
        String classNum = request.getParameter("classNum");

        List<TestListStudent> testList = null;

        // ▼ 検索処理
        if (subjectCd != null && !subjectCd.isEmpty()
         && classNum != null && !classNum.isEmpty()) {

            subject = sdao.get(subjectCd, school);

            if (subject != null) {
                TestListStudentDao dao = new TestListStudentDao();
                testList = dao.filter(classNum, subject, school);
            }
        }
        System.out.println("subjectCd=" + subjectCd);
        System.out.println("classNum=" + classNum);
        request.setAttribute("testList",testList);
        //request.getRequestDispatcher("test_list.jsp").forward(request, response);
        return "test_list_subject.jsp";

    }
}
