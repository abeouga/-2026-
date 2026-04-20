package com.example.dao;

import com.example.model.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestDao extends DaoBase {

    public List<Test> filter(String schoolCd, Integer entYear, String classNum, String subjectCd, Integer no) throws Exception {
        // 科目ごと、クラスごとの成績一覧取得（test_list_subject.jsp向け）
        List<Test> list = new ArrayList<>();
        String sql = "SELECT t.*, s.name as student_name FROM test t JOIN student s ON t.student_no = s.no AND t.school_cd = s.school_cd " +
                     "WHERE t.school_cd = ? AND s.ent_year = ? AND s.class_num = ? AND t.subject_cd = ? AND t.no = ?";
                     
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
             
            st.setString(1, schoolCd);
            st.setInt(2, entYear);
            st.setString(3, classNum);
            st.setString(4, subjectCd);
            st.setInt(5, no);
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test(
                        rs.getString("school_cd"),
                        rs.getString("student_no"),
                        rs.getString("subject_cd"),
                        rs.getInt("no"),
                        rs.getString("class_num"),
                        rs.getInt("point")
                    );
                    test.setStudentName(rs.getString("student_name"));
                    list.add(test);
                }
            }
        }
        return list;
    }

    public List<Test> filterByStudent(String schoolCd, String studentNo) throws Exception {
        // 学生ごとの成績一覧取得（test_list_student.jsp向け）
        List<Test> list = new ArrayList<>();
        String sql = "SELECT t.*, sub.name as subject_name FROM test t JOIN subject sub ON t.subject_cd = sub.cd AND t.school_cd = sub.school_cd " +
                     "WHERE t.school_cd = ? AND t.student_no = ?";
                     
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
             
            st.setString(1, schoolCd);
            st.setString(2, studentNo);
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test(
                        rs.getString("school_cd"),
                        rs.getString("student_no"),
                        rs.getString("subject_cd"),
                        rs.getInt("no"),
                        rs.getString("class_num"),
                        rs.getInt("point")
                    );
                    test.setSubjectName(rs.getString("subject_name"));
                    list.add(test);
                }
            }
        }
        return list;
    }

    public boolean save(Test test) throws Exception {
        String sqlSelect = "SELECT * FROM test WHERE school_cd = ? AND student_no = ? AND subject_cd = ? AND no = ?";
        try (Connection con = getConnection()) {
            boolean exists = false;
            try (PreparedStatement stSelect = con.prepareStatement(sqlSelect)) {
                stSelect.setString(1, test.getSchoolCd());
                stSelect.setString(2, test.getStudentNo());
                stSelect.setString(3, test.getSubjectCd());
                stSelect.setInt(4, test.getNo());
                try (ResultSet rs = stSelect.executeQuery()) {
                    if (rs.next()) {
                        exists = true;
                    }
                }
            }

            if (exists) {
                String sqlUpdate = "UPDATE test SET class_num = ?, point = ? WHERE school_cd = ? AND student_no = ? AND subject_cd = ? AND no = ?";
                try (PreparedStatement stUpdate = con.prepareStatement(sqlUpdate)) {
                    stUpdate.setString(1, test.getClassNum());
                    stUpdate.setInt(2, test.getPoint());
                    stUpdate.setString(3, test.getSchoolCd());
                    stUpdate.setString(4, test.getStudentNo());
                    stUpdate.setString(5, test.getSubjectCd());
                    stUpdate.setInt(6, test.getNo());
                    return stUpdate.executeUpdate() > 0;
                }
            } else {
                String sqlInsert = "INSERT INTO test (school_cd, student_no, subject_cd, no, class_num, point) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stInsert = con.prepareStatement(sqlInsert)) {
                    stInsert.setString(1, test.getSchoolCd());
                    stInsert.setString(2, test.getStudentNo());
                    stInsert.setString(3, test.getSubjectCd());
                    stInsert.setInt(4, test.getNo());
                    stInsert.setString(5, test.getClassNum());
                    stInsert.setInt(6, test.getPoint());
                    return stInsert.executeUpdate() > 0;
                }
            }
        }
    }
}
