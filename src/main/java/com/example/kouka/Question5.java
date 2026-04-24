package kouka;

import tool.Page;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.sql.*;

@WebServlet(urlPatterns={"/kouka/Question5"})
public class Question5 extends HttpServlet{
    public void doGet(
        HttpServletRequest request,HttpServletResponse response
    )throws ServletException,IOException{
        PrintWriter out=response.getWriter();
        try{
            InitialContext ic=new InitialContext();
            DataSource ds=(DataSource)ic.lookup("java:/comp/env/jdbc/kouka");
            java.sql.Connection con=ds.getConnection();


            java.sql.PreparedStatement st=con.prepareStatement("select * from student");
            java.sql.ResultSet rs=st.executeQuery();

            java.sql.PreparedStatement t=con.prepareStatement("select * from TEACHER");
            java.sql.ResultSet ts=st.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<style>");
            out.println("table { border-collapse: collapse; width: 50%; }");
            out.println("th, td { border: 1px solid black; padding: 8px; text-align: center; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table>");
            out.print("<tr>"+"<th>"+"UserId"+"</th>");
            out.println("<th>"+"UserName"+"</th>");
            out.println("<th>"+"MainAddress"+"</th>");
            while(ts.next()) {
                out.println("<tr>");
                out.println("<td>"+ts.getInt("UserId")+"</td>");
                out.println("<td>"+ts.getString("Username")+"</td>");
                out.println("<td>"+ts.getInt("MainAddress")+"</td>");
                out.println("<br>");
            }
            out.print("<tr>"+"<th>"+"studentId"+"</th>");
            out.println("<th>"+"studentName"+"</th>");
            out.println("<th>"+"course"+"</th>");
            while(rs.next()) {
                out.println("<tr>");
                out.println("<td>"+rs.getInt("studentid")+"</td>");
                out.println("<td>"+rs.getString("studentname")+"</td>");
                out.println("<td>"+rs.getInt("course")+"</td>");
                out.println("<br>");
            }
            st.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace(out);
        }
    }
}