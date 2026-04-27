package scoremanager;
import bean.Teacher;
import bean.TestListStudent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import bean.ClassNum;
import bean.School;
import bean.Subject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListAction extends Action{
    public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        if (teacher == null) {
            return "login-in.jsp";
        }
        School school = teacher.getSchool();

        String subjectCd = request.getParameter("subjectCd");
        String classNum  = request.getParameter("classNum");

        //request.getRequestDispatcher("test_list.jsp").forward(request, response);
        return "test_list.jsp";
        
    }
}
