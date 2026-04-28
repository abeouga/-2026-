package com.example.action.test;

import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.action.Action;
import com.example.model.School;
import com.example.model.Student;
import com.example.model.Subject;
import com.example.model.Teacher;
import com.example.model.Test;
import com.example.model.TestListSubject;
import com.example.config.dao.ClassNumDao;
import com.example.config.dao.SubjectDao;
import com.example.config.dao.TestDao;
import com.example.config.dao.TestListSubjectDao;

public class TestRegistExecuteAction implements Action{
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
        TestDao tdao = new TestDao();
        Subject subject = null;
        int entYear = 0;
        List<String> list2 = cdao.filter(school.getCd());
        request.setAttribute("classList",list2);
        List<Subject> list = sdao.filter(school.getCd());
        request.setAttribute("subjectList",list);

        
        
        String entYearStr = request.getParameter("entYear");
        String subjectCd = request.getParameter("subjectCd");
        String classNum = request.getParameter("classNum");
        String noStr = request.getParameter("no");
        int no = 0;
        if (noStr != null && !noStr.isEmpty()) {
            no = Integer.parseInt(noStr);
        }
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
            request.getRequestDispatcher("/WEB-INF/views/test/test_regist.jsp").forward(request, response);
            return;
        }

        if(entYearStr != null && !entYearStr.isEmpty()){
            entYear = Integer.parseInt(entYearStr);
        }

        List<Test> testList = null;

        // ▼ 検索処理
        subject = sdao.get(school.getCd(), subjectCd);
        testList = tdao.filter(entYear, classNum, subject, no, school);
        request.setAttribute("testList",testList);
        System.out.println("list size = " + testList.size());
        // パラメータ取得
        String[] studentNos = request.getParameterValues("studentNoList");
        String[] points = request.getParameterValues("pointList");

        if (studentNos != null && points != null) {
            // List作成
            List<Test> saveList = new ArrayList<>();

            for (int i = 0; i < studentNos.length; i++) {
                Test test = new Test();

                Student student = new Student();
                student.setNo(studentNos[i]);
                student.setClassNum(classNum);

                Subject sub = new Subject();
                sub.setCd(subjectCd);

                test.setStudentNo(student.getNo());
                test.setSubjectCd(sub.getCd());
                test.setSchoolCd(school.getCd());
                test.setNo(no);
                test.setClassNum(classNum);

                if (points[i] != null && !points[i].isEmpty()) {
                    test.setPoint(Integer.parseInt(points[i]));
                }

                saveList.add(test);
            }

            tdao.save(saveList);
            request.getRequestDispatcher("/WEB-INF/views/test/test_regist_done.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/test/test_regist.jsp").forward(request, response);
    }
}
