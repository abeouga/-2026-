package com.example.action.test;


import java.util.List;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;

public class TestListSubjectExecuteAction extends Action{
    public String execute(HttpServletRequest request,HttpServletResponse responce)throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        
        if (teacher == null) {
            return "login-in.jsp";
        }

        School school = teacher.getSchool();
        SubjectDao sdao = new SubjectDao();
        ClassNumDao cdao = new ClassNumDao();
        Subject subject = null;
        int entYear = 0;
        List<Subject> list = sdao.filter(school);
        request.setAttribute("subjectList",list);
        List<String> list2 = cdao.filter(school);
        request.setAttribute("classList",list2);
        
        
        String entYearStr = request.getParameter("entYear");
        String subjectCd = request.getParameter("subjectCd");
        String classNum = request.getParameter("classNum");
        if (subjectCd != null) {
            subjectCd = subjectCd.trim();
        }
        if (classNum != null) {
            classNum = classNum.trim();
        }

        if (
            subjectCd == null || subjectCd.isEmpty() ||
            classNum == null  || classNum.isEmpty() ||
            entYearStr == null || entYearStr.isEmpty()
        ) {
            return "test_list_subject.jsp"; // 初期表示
        }

        if(entYearStr != null && !entYearStr.isEmpty()){
            entYear = Integer.parseInt(entYearStr);
        }

        List<TestListSubject> testList = null;

        // ▼ 検索処理
        if (subjectCd != null && !subjectCd.isEmpty()
         && classNum != null && !classNum.isEmpty()) {

            subject = sdao.get(subjectCd,school);

            if (subject != null) {
                TestListSubjectDao dao = new TestListSubjectDao();
                testList = dao.filter(entYear,classNum, subject, school);
            }
        }
        System.out.println("subjectCd=[" + subjectCd + "]");
        System.out.println("subject.getCd()=[" + subject.getCd() + "]");
        request.setAttribute("testList",testList);
        //request.getRequestDispatcher("test_list.jsp").forward(request, response);
        return "test_list_subject.jsp";

    }
}
