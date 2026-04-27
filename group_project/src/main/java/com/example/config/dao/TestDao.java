package com.example.dao;

import com.example.model.Test;
import com.example.model.School;
import com.example.model.Student;
import com.example.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}