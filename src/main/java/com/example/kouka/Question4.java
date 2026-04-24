package kouka;
import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/kouka/Question4input"})
public class Question4 extends HttpServlet{
    public void doPost(
        HttpServletRequest request,HttpServletResponse response
    )throws ServletException,IOException{
        PrintWriter out=response.getWriter();
        String name=request.getParameter("user");
        System.out.println("お名前:"+name);
        request.setAttribute("name", name);
        request.getRequestDispatcher("Question4output.jsp").forward(request,response);
    }
}