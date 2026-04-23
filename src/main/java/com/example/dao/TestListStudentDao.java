package dao;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class TestListStudentDao extends DAO{
    private String baseSql = "select * from test where school_cd=? ";
    private List<TestListStudent> postFilter(ResultSet rSet)throws Exception{
        //リストを初期化
        List<TestListStudent> list = new ArrayList<>();
        try{
            //リザルトセットを全権操作
            while(rSet.next()){
                //学生インスタンスを初期化
                TestListStudent student = new TestListStudent();
                //学生インスタンスに検索結果をセット
                //student.setSubjectName(rSet.getString("name"));
                student.setSubjectCd(rSet.getString("subject_cd"));
                student.setNum(rSet.getInt("class_num"));
                student.setPoint(rSet.getInt("point"));
                //リストに追加
                list.add(student);
            }
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return list;
    }
    public List<TestListStudent> filter(String classNum,Subject subject,School school)throws Exception{
        //リストを初期化
        List<TestListStudent> list = new ArrayList<>();
        //コネクションを確立
        Connection connection = getConnection();
        //プリペアードステートメント
        PreparedStatement statement = null;
        //リザルトセット
        ResultSet rSet = null;
        //SQL文の条件
        String condition = "and subject_cd = ? and class_num = ?";
        //SQL文のソート
        String order = "order by no asc ";
        
        try{
            //プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            //プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            //プリペアードステートメントにクラス番号をバインド
            statement.setString(2, subject.getCd());
            statement.setString(3, classNum);
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