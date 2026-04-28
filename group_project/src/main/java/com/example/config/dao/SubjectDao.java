package com.example.config.dao;

import com.example.model.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao extends DaoBase {

    public List<Subject> filter(String schoolCd) throws Exception {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject WHERE school_cd = ? ORDER BY cd";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
             
            st.setString(1, schoolCd);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(new Subject(
                        rs.getString("school_cd"),
                        rs.getString("cd"),
                        rs.getString("name")
                    ));
                }
            }
        }
        return list;
    }

    public Subject get(String schoolCd, String cd) throws Exception {
        Subject subject = null;
        String sql = "SELECT * FROM subject WHERE school_cd = ? AND cd = ?";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
             
            st.setString(1, schoolCd);
            st.setString(2, cd);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject(
                        rs.getString("school_cd"),
                        rs.getString("cd"),
                        rs.getString("name")
                    );
                }
            }
        }
        return subject;
    }

    public boolean save(Subject subject) throws Exception {
        String sqlSelect = "SELECT * FROM subject WHERE school_cd = ? AND cd = ?";
        try (Connection con = getConnection()) {
            boolean exists = false;
            try (PreparedStatement stSelect = con.prepareStatement(sqlSelect)) {
                stSelect.setString(1, subject.getSchoolCd());
                stSelect.setString(2, subject.getCd());
                try (ResultSet rs = stSelect.executeQuery()) {
                    if (rs.next()) {
                        exists = true;
                    }
                }
            }

            if (exists) {
                // Update
                String sqlUpdate = "UPDATE subject SET name = ? WHERE school_cd = ? AND cd = ?";
                try (PreparedStatement stUpdate = con.prepareStatement(sqlUpdate)) {
                    stUpdate.setString(1, subject.getName());
                    stUpdate.setString(2, subject.getSchoolCd());
                    stUpdate.setString(3, subject.getCd());
                    return stUpdate.executeUpdate() > 0;
                }
            } else {
                // Insert
                String sqlInsert = "INSERT INTO subject (school_cd, cd, name) VALUES (?, ?, ?)";
                try (PreparedStatement stInsert = con.prepareStatement(sqlInsert)) {
                    stInsert.setString(1, subject.getSchoolCd());
                    stInsert.setString(2, subject.getCd());
                    stInsert.setString(3, subject.getName());
                    return stInsert.executeUpdate() > 0;
                }
            }
        }
    }

    public boolean delete(Subject subject) throws Exception {
        String sql = "DELETE FROM subject WHERE school_cd = ? AND cd = ?";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, subject.getSchoolCd());
            st.setString(2, subject.getCd());
            return st.executeUpdate() > 0;
        }
    }
}
