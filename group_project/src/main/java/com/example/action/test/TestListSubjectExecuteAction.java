package com.example.action.test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.action.Action;
import com.example.model.School;
import com.example.model.Student;
import com.example.model.Subject;
import com.example.model.Teacher;
import com.example.model.TestListStudent;
import com.example.model.TestListSubject;
import com.example.config.dao.ClassNumDao;
import com.example.config.dao.StudentDao;
import com.example.config.dao.SubjectDao;
import com.example.config.dao.TestListStudentDao;
import com.example.config.dao.TestListSubjectDao;

public class TestListSubjectExecuteAction implements Action{
    public void execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");
        
        if (teacher == null) {
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            return;
        }

        School school = teacher.getSchool();
        SubjectDao sdao = new SubjectDao();
        ClassNumDao cdao = new ClassNumDao();
        Subject subject = null;
        int entYear = 0;
        List<Integer> entYearList = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear - 10; i <= currentYear + 1; i++) {
            entYearList.add(i);
        }
        request.setAttribute("entYearList", entYearList);
        List<Subject> list = sdao.filter(school.getCd());
        request.setAttribute("subjectList",list);
        List<String> list2 = cdao.filter(school.getCd());
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
            request.getRequestDispatcher("/WEB-INF/views/test/test_list_subject.jsp").forward(request, response);
            return;
        }

        if(entYearStr != null && !entYearStr.isEmpty()){
            entYear = Integer.parseInt(entYearStr);
        }

        List<TestListSubject> testList = null;

        // ▼ 検索処理
        if (subjectCd != null && !subjectCd.isEmpty()
         && classNum != null && !classNum.isEmpty()) {

            subject = sdao.get(school.getCd(), subjectCd);

            if (subject != null) {
                TestListSubjectDao dao = new TestListSubjectDao();
                testList = dao.filter(entYear,classNum, subject, school);
            }
        }
        System.out.println("subjectCd=[" + subjectCd + "]");
        if (subject != null) {
            System.out.println("subject.getCd()=[" + subject.getCd() + "]");
        }
        request.setAttribute("testListsub",testList);
        request.getRequestDispatcher("/WEB-INF/views/test/test_list.jsp").forward(request, response);
    }
}
