package com.example.dao;

import com.example.model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDao extends DaoBase {

    public Teacher get(String id, String password) throws Exception {
        Teacher teacher = null;
        String sql = "SELECT * FROM teacher WHERE id = ? AND password = ?";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
             
            st.setString(1, id);
            st.setString(2, password);
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher(
                        rs.getString("school_cd"),
                        rs.getString("id"),
                        rs.getString("password"),
                        rs.getString("name")
                    );
                }
            }
        }
        return teacher;
    }
}
