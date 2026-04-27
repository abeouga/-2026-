package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.model.Test;
import com.example.model.School;
import com.example.model.Student;
import com.example.model.Subject;

public class TestDao {

    private String basesql =
        "select * from test where class_num = ? and subject_cd = ?";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/student_management",
            "root",
            "password"
        );
    }

    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        Test test = new Test();

        try {
            statement = connection.prepareStatement(basesql);

            statement.setString(1, student.getClassNum());
            statement.setString(2, subject.getCd());

            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                test.setClassNum(rSet.getString("class_num"));
                test.setSubjectCd(subject.getCd());
            } else {
                test = null;
            }

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return test;
    }
}