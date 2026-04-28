package scoremanager;
import dao.ClassNumDao;
import dao.SubjectDao;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TeacherDAO;
import tool.Action;
import jakarta.servlet.http.*;

public class TestRegistAction extends Action{
    public String execute(
        HttpServletRequest request,HttpServletResponse response
    )throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("teacher");

        if (teacher == null) {
            return "login-in.jsp";
        }

        School school = teacher.getSchool();
        SubjectDao sdao = new SubjectDao();
        ClassNumDao cdao = new ClassNumDao();

        List<String> classList = cdao.filter(school);
        List<Subject> subjectList = sdao.filter(school);

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

            dao.TestDao tdao = new dao.TestDao();

            List<Test> list = tdao.filter(entYear, classNum, subject, no, school);

            request.setAttribute("testList", list);
        }
        return "test_regist.jsp";
    }

}