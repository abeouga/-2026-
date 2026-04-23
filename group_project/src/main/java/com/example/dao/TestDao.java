package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.Test;
import bean.School;
import bean.Student;
import bean.Subject;

public class TestDao extends DAO{
    private String basesql = "select * from test where class_num = ? and subject_cd = ?";
    public Test get(Student student,Subject subject,School school,int no)throws Exception{
        Connection connection = getConnection();
        PreparedStatement statement = null;
        Test test = new Test();
        try{
            
            statement = connection.prepareStatement(basesql);
            ResultSet rSet = statement.executeQuery();
            if(rSet.next()){
                //リザルトセットが存在する場合
                //学校インスタンスに学校コードと学校名をセット
                test.setClassNum(rSet.getString("classNum"));
                subject.setCd(rSet.getString("subject_cd"));
                test.setSubject(subject);
            }else{
                //リザルトセットが存在しない場合
                //学校インスタンスにnullをセット
                subject = null;
            }
        }catch(Exception e){
            throw e;
        }finally{
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
        return test;
    }
}
