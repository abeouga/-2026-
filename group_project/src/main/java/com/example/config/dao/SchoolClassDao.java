package com.example.config.dao;

import com.example.model.SchoolClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SchoolClassDao extends DaoBase{
    public List<SchoolClass> filter(String schoolCd) throws Exception {
        List<SchoolClass> list = new ArrayList<>();
        String sql = "SELECT class_num FROM class_num WHERE school_cd = ? ORDER BY class_num";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            
            st.setString(1, schoolCd);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    SchoolClass sc = new SchoolClass();
                    sc.setClassNum(rs.getString("class_num"));
                    list.add(sc);
                }
            }
        }
        return list;
    }
        public SchoolClass get(String schoolCd, String classNum) throws Exception {
        SchoolClass schoolClass = null;
        String sql = "SELECT * FROM class_num WHERE school_cd = ? AND class_Num = ?";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
             
            st.setString(1, schoolCd);
            st.setString(2, classNum);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    schoolClass = new SchoolClass(
                        rs.getString("school_cd"),
                        rs.getString("class_num")
                    );
                }
            }
        }
        return schoolClass;
    }

    public boolean save(SchoolClass schoolClass) throws Exception {
        try (Connection con = getConnection()) {
            // Insert
            String sqlInsert = "INSERT INTO class_num (school_cd, class_num) VALUES (?, ?)";
            try (PreparedStatement stInsert = con.prepareStatement(sqlInsert)) {
                stInsert.setString(1, schoolClass.getSchoolCd());
                stInsert.setString(2, schoolClass.getClassNum());
                return stInsert.executeUpdate() > 0;
            }
        }
    }
    public boolean delete(SchoolClass schoolClass) throws Exception {
        String sql = "DELETE FROM class_num WHERE school_cd = ? AND class_num = ?";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, schoolClass.getSchoolCd());
            st.setString(2, schoolClass.getClassNum());
            return st.executeUpdate() > 0;
        }
    }
}
