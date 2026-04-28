package com.example.config.dao;

import com.example.model.School;
import com.example.model.Student;
import com.example.model.Teacher;
import com.example.model.TestListStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestListStudentDao extends DAO{
    private String baseSql = "SELECT\n" + //
                "    st.no AS student_no,\n" + //
                "    st.name AS student_name,\n" + //
                "    sub.name AS subject_name,\n" + //
                "    t.point\n" + //
                "FROM test t\n" + //
                "JOIN student st ON t.student_no = st.no\n" + //
                "JOIN subject sub ON t.subject_cd = sub.cd ";
    private List<TestListStudent> postFilter(ResultSet rSet)throws Exception{
        //リストを初期化
        List<TestListStudent> list = new ArrayList<>();
        try{
            //リザルトセットを全権操作
            while(rSet.next()){
                //学生インスタンスを初期化
                TestListStudent student = new TestListStudent();
                //学生インスタンスに検索結果をセット
                student.setStudentName(rSet.getString("name"));
                student.setStudentNo(rSet.getString("student_No"));
                student.setClassNum("class_num");
                student.setPoint(rSet.getInt("point"));
                //リストに追加
                list.add(student);
            }
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return list;
    }
    
    public List<TestListStudent> filter(String studentNo, School school) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        String sql =
            baseSql +
            "WHERE st.no = ? AND st.school_cd = ? " +
            "ORDER BY sub.cd";

        try (
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, studentNo);
            statement.setString(2, school.getCd());

            try (ResultSet rSet = statement.executeQuery()) {
                while (rSet.next()) {
                    TestListStudent student = new TestListStudent();
                    
                    student.setStudentNo(rSet.getString("student_no"));
                    student.setStudentName(rSet.getString("student_name"));
                    student.setSubjectCd(rSet.getString("subject_name"));
                    student.setPoint(rSet.getInt("point"));
                    list.add(student);
                }
            }
        }
        return list;
    }
}
