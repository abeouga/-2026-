package com.example.config.dao;

import com.example.model.Test;
import com.example.model.School;
import com.example.model.Student;
import com.example.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestDao extends DaoBase {

    private String baseSql =
        "SELECT * FROM test WHERE class_num = ? AND subject_cd = ?";

    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        Test test = null;

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(baseSql)) {

            st.setString(1, student.getClassNum());
            st.setString(2, subject.getCd());

            try (ResultSet rs = st.executeQuery()) {

                if (rs.next()) {
                    test = new Test();

                    test.setSchoolCd(rs.getString("school_cd"));
                    test.setStudentNo(rs.getString("student_no"));
                    test.setSubjectCd(rs.getString("subject_cd"));
                    test.setNo(rs.getInt("no"));
                    test.setClassNum(rs.getString("class_num"));
                    test.setPoint(rs.getInt("point"));
                }
            }
        }

        return test;
    }

    public List<Test> filter(int entYear, String classNum, Subject subject, int no, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        String sql = "SELECT st.no AS student_no, st.name AS student_name, " +
    "st.class_num, sub.cd AS subject_cd, sub.name AS subject_name, " +
    "t.point, ? AS no, st.school_cd " +
    "FROM student st " +
    "JOIN subject sub ON sub.cd = ? AND sub.school_cd = st.school_cd " +
    "LEFT JOIN test t ON st.no = t.student_no " +
    "AND t.subject_cd = sub.cd " +
    "AND t.no = ? " +
    "AND t.class_num = st.class_num " +
    "AND t.school_cd = st.school_cd " +
    "WHERE st.school_cd = ? " +
    "AND st.class_num = ? " +
    "AND st.ent_year = ? " +
    "ORDER BY st.no";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, no);
            st.setString(2, subject.getCd());
            st.setInt(3, no);
            st.setString(4, school.getCd());
            st.setString(5, classNum);
            st.setInt(6, entYear);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();
                    test.setSchoolCd(rs.getString("school_cd"));
                    test.setStudentNo(rs.getString("student_no"));
                    test.setSubjectCd(rs.getString("subject_cd"));
                    test.setNo(rs.getInt("no"));
                    test.setClassNum(rs.getString("class_num"));
                    test.setPoint(rs.getInt("point"));
                    test.setStudentName(rs.getString("student_name"));
                    test.setSubjectName(rs.getString("subject_name"));
                    list.add(test);
                }
            }
        }
        return list;
    }

    public boolean save(List<Test> tests) throws Exception {
        String sqlSelect = "SELECT * FROM test WHERE school_cd = ? AND student_no = ? AND subject_cd = ? AND no = ? AND class_num = ?";
        String sqlUpdate = "UPDATE test SET point = ? WHERE school_cd = ? AND student_no = ? AND subject_cd = ? AND no = ? AND class_num = ?";
        String sqlInsert = "INSERT INTO test (school_cd, student_no, subject_cd, no, class_num, point) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection()) {
            for (Test test : tests) {
                boolean exists = false;
                try (PreparedStatement stSelect = con.prepareStatement(sqlSelect)) {
                    stSelect.setString(1, test.getSchoolCd());
                    stSelect.setString(2, test.getStudentNo());
                    stSelect.setString(3, test.getSubjectCd());
                    stSelect.setInt(4, test.getNo());
                    stSelect.setString(5, test.getClassNum());
                    try (ResultSet rs = stSelect.executeQuery()) {
                        if (rs.next()) {
                            exists = true;
                        }
                    }
                }

                if (exists) {
                    try (PreparedStatement stUpdate = con.prepareStatement(sqlUpdate)) {
                        stUpdate.setInt(1, test.getPoint());
                        stUpdate.setString(2, test.getSchoolCd());
                        stUpdate.setString(3, test.getStudentNo());
                        stUpdate.setString(4, test.getSubjectCd());
                        stUpdate.setInt(5, test.getNo());
                        stUpdate.setString(6, test.getClassNum());
                        stUpdate.executeUpdate();
                    }
                } else {
                    try (PreparedStatement stInsert = con.prepareStatement(sqlInsert)) {
                        stInsert.setString(1, test.getSchoolCd());
                        stInsert.setString(2, test.getStudentNo());
                        stInsert.setString(3, test.getSubjectCd());
                        stInsert.setInt(4, test.getNo());
                        stInsert.setString(5, test.getClassNum());
                        stInsert.setInt(6, test.getPoint());
                        stInsert.executeUpdate();
                    }
                }
            }
        }
        return true;
    }
}