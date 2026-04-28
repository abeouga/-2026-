package com.example.action.test;


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
import com.example.config.dao.ClassNumDao;
import com.example.config.dao.StudentDao;
import com.example.config.dao.SubjectDao;
import com.example.config.dao.TestListStudentDao;

public class TestListStudentExecuteAction implements Action{
    public void execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        School school = teacher.getSchool();
        SubjectDao sdao = new SubjectDao();
        ClassNumDao cdao = new ClassNumDao();
        StudentDao stdao = new StudentDao();
        Student student = null;
        
        List<Student> list = stdao.filter(school.getCd(), null, null, false);
        request.setAttribute("studentNo",list);
        List<String> list2 = cdao.filter(school.getCd());
        request.setAttribute("classList",list2);
        
        String studentNo = request.getParameter("studentNo");

        List<TestListStudent> testList = null;

        // ▼ 検索処理
        if (studentNo != null && !studentNo.isEmpty()) {

            student = stdao.get(school.getCd(), studentNo);

            if (student != null) {
                TestListStudentDao dao = new TestListStudentDao();
                testList = dao.filter(studentNo,school);
            }
        }
        request.setAttribute("testList",testList);
        request.getRequestDispatcher("/WEB-INF/views/test/test_list_student.jsp").forward(request, response);
    }
}
