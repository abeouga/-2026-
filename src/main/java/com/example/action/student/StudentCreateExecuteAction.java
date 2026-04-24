package com.example.action.student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.dao.SubjectDao;
import com.example.bean.ClassNum;
import com.example.bean.School;
import com.example.bean.Student;
import com.example.bean.Teacher;
import com.example.tool.Action;
import com.example.dao.ClassNumDao;
import com.example.dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class StudentCreateExecuteAction extends Action{
    public String execute(HttpServletRequest request,HttpServletResponse responce)throws Exception{
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        boolean judge = false;
        StudentDao dao = new StudentDao();

        int EntYear;//入力された入学年度
        String ClassNum="";//入力されたクラス番号
        boolean isAttend = false;//在学フラグ
        String name  ="";
        String studentNum = "";
        School schoolcd = teacher.getSchool();
        Student s = new Student();
        
        try{
            EntYear = Integer.parseInt(request.getParameter("f1"));
            ClassNum = request.getParameter("f2");
            isAttend = isAttend = Boolean.parseBoolean(request.getParameter("f3"));
            name = request.getParameter("name");
            studentNum = request.getParameter("studentnum");
            s.setNo(studentNum);
            s.setName(name);
            s.setAttend(isAttend);
            s.setClassNum(ClassNum);;
            s.setEntYear(EntYear);
            s.setSchool(schoolcd);
            judge = dao.save(s);
            return "student_create_done.jsp";
        }catch(Exception e){
            throw e;
        }
        
    }
}
