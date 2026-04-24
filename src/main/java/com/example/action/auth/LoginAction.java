package com.example.action.auth;
import com.example.bean.Teacher;
import com.example.dao.TeacherDAO;
import com.example.tool.Action;
import jakarta.servlet.http.*;

public class LoginAction extends Action{
    public String execute(
        HttpServletRequest request,HttpServletResponse response
    )throws Exception{
        HttpSession session=request.getSession();

        String login=request.getParameter("login");
        String password=request.getParameter("password");
        TeacherDAO dao=new TeacherDAO();
        Teacher teacher=dao.search(login,password);

        if(teacher!=null){
            session.setAttribute("teacher",teacher);
            return "/WEB-INF/views/auth/menu.jsp";
        }
        return "/WEB-INF/views/auth/login-error.jsp";
    }
}