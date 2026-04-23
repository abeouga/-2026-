package dao;

import bean.School;
import bean.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDAO extends DAO{
    public Teacher search(String login,String password)
    throws Exception{
        Teacher teacher=null;

        Connection con=getConnection();

        PreparedStatement st;
        st=con.prepareStatement("select * from teacher where ID=? and password=?");
        st.setString(1,login);
        st.setString(2,password);
        ResultSet rs=st.executeQuery();

        while (rs.next()) {
            teacher=new Teacher();
            teacher.setId(rs.getString("id"));
            teacher.setName(rs.getString("name"));
            teacher.setPassword(rs.getString("password"));
            School school = new School();
            school.setCd(rs.getString("SCHOOL_CD"));
            teacher.setSchool(school);


        }
        st.close();
        con.close();
        if (teacher != null) {
            return teacher;  // 成功
        }else{
            return null;
        }

    }
}