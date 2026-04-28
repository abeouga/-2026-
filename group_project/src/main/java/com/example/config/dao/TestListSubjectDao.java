package com.example.config.dao;

import com.example.model.School;
import com.example.model.TestListSubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Subject;

public class TestListSubjectDao extends DaoBase{
    private String baseSql =  "SELECT \n" + //
                "    t.student_no,\n" + //
                "    st.name AS student_name,\n" + //
                "    sub.name AS subject_name,\n" + //
                "    sub.cd AS subject_cd,\n" + //
                "    t.no,\n" + //
                "    t.point,\n" + //
                "    t.class_num\n"+
                "FROM test t\n" + //
                "JOIN student st ON t.student_no = st.no\n" + //
                "JOIN subject sub ON t.subject_cd = sub.cd ";
    private List<TestListSubject> postFilter(ResultSet rSet)throws Exception{
        //リストを初期化
        List<TestListSubject> list = new ArrayList<>();
        try{
            //リザルトセットを全権操作
            while(rSet.next()){
                TestListSubject subject = new TestListSubject();

                subject.setSubjectName(rSet.getString("subject_name"));
                subject.setSubjectCd(rSet.getString("subject_cd")); // ← 追加必要
                subject.setNum(rSet.getInt("no"));    
                subject.setClassNum(rSet.getString("class_num"));              // ← 修正
                subject.setPoint(rSet.getInt("point"));

                list.add(subject);
            }
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return list;
    }
    public List<TestListSubject> filter(int entYear,String classNum,Subject subject,School school)throws Exception{
        //リストを初期化
        List<TestListSubject> list = new ArrayList<>();
        //コネクションを確立
        Connection connection = getConnection();
        //プリペアードステートメント
        PreparedStatement statement = null;
        //リザルトセット
        ResultSet rSet = null;
        //SQL文の条件
        String condition = "WHERE sub.cd = ? " +
                            "AND t.class_num = ? " +
                            "AND st.ent_year = ?" + 
                            "AND st.school_cd = ?";
        //SQL文のソート
        String order = " order by t.student_no asc ";
        
        try{
            //プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            //プリペアードステートメントに学校コードをバインド
            //プリペアードステートメントにクラス番号をバインド
            statement.setString(1, subject.getCd().trim());
            statement.setString(2, classNum);
            statement.setInt(3, entYear);
            statement.setString(4, school.getCd());

            //プリペアードステートメントを実行
            rSet = statement.executeQuery();
            

            //リストへの格納処理を実行
            list = postFilter(rSet);
        }catch(Exception e){
            throw e;
        }finally{
            //プリペアードステートメントを閉じる
            if(statement != null){
                try{
                    statement.close();
                }catch(SQLException sqle){
                    throw sqle;
                }
            }
            //コネクションを閉じる
            if(connection != null){
                try{
                    connection.close();
                }catch(SQLException sqle){
                    throw sqle;
                }
            }
        }
        return list;
    }
}