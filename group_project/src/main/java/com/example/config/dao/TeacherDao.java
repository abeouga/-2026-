package com.example.config.dao;

import com.example.model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
                        rs.getString("name"),
                        rs.getString("role")
                    );
                }
            }
        }
        return teacher;
    }

    public Teacher getById(String id) throws Exception {
        Teacher teacher = null;
        String sql = "SELECT * FROM teacher WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
             
            st.setString(1, id);
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher(
                        rs.getString("school_cd"),
                        rs.getString("id"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("role")
                    );
                }
            }
        }
        return teacher;
    }

    public List<Teacher> getAll(String schoolCd) throws Exception {
        List<Teacher> list = new ArrayList<>();
        String sql = "SELECT * FROM teacher WHERE school_cd = ? ORDER BY id";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
             
            st.setString(1, schoolCd);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(new Teacher(
                        rs.getString("school_cd"),
                        rs.getString("id"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("role")
                    ));
                }
            }
        }
        return list;
    }

    public boolean save(Teacher teacher) throws Exception {
        try (Connection con = getConnection()) {
            boolean isNew = (getById(teacher.getId()) == null);
            String sql;
            if (isNew) {
                sql = "INSERT INTO teacher (school_cd, id, password, name, role) VALUES (?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE teacher SET school_cd = ?, password = ?, name = ?, role = ? WHERE id = ?";
            }
            try (PreparedStatement st = con.prepareStatement(sql)) {
                if (isNew) {
                    st.setString(1, teacher.getSchoolCd());
                    st.setString(2, teacher.getId());
                    st.setString(3, teacher.getPassword());
                    st.setString(4, teacher.getName());
                    st.setString(5, teacher.getRole());
                } else {
                    st.setString(1, teacher.getSchoolCd());
                    st.setString(2, teacher.getPassword());
                    st.setString(3, teacher.getName());
                    st.setString(4, teacher.getRole());
                    st.setString(5, teacher.getId());
                }
                int count = st.executeUpdate();
                return count > 0;
            }
        }
    }
}
