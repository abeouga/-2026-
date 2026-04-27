package scoremanager;


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
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;

public class TestListStudentExecuteAction extends Action{
    public String execute(HttpServletRequest request,HttpServletResponse responce)throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        School school = teacher.getSchool();
        SubjectDao sdao = new SubjectDao();
        ClassNumDao cdao = new ClassNumDao();
        StudentDao stdao = new StudentDao();
        Student student = null;
        
        List<Student> list = stdao.filter(school,false);
        request.setAttribute("studentNo",list);
        List<String> list2 = cdao.filter(school);
        request.setAttribute("classList",list2);
        
        String studentNo = request.getParameter("studentNo");

        List<TestListStudent> testList = null;

        // ▼ 検索処理
        if (studentNo != null && !studentNo.isEmpty()) {

            student = stdao.get(studentNo);

            if (student != null) {
                TestListStudentDao dao = new TestListStudentDao();
                testList = dao.filter(studentNo,school);
            }
        }
        request.setAttribute("testList",testList);
        //request.getRequestDispatcher("test_list.jsp").forward(request, response);
        return "test_list_student.jsp";

    }
}
