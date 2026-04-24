package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends DAO{
    private String baseSql = "select * from subject where school_cd=?";
    public Subject get(String cd,School school)throws Exception{
        String exsql = " and cd = ?";
        Subject subject = new Subject();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(baseSql+exsql);
            statement.setString(1, school.getCd());
            statement.setString(2, cd);
            ResultSet rSet = statement.executeQuery();
            if(rSet.next()){
                //リザルトセットが存在する場合
                //学校インスタンスに学校コードと学校名をセット
                subject.setCd(rSet.getString("cd").trim());
                subject.setName(rSet.getString("NAME"));
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
        return subject;
    }
    public List<Subject> filter(School school)throws Exception{
         
        //リストを初期化
        List<Subject> list = new ArrayList<>();
        //データベースへのコネクションを確立
        Connection connection = getConnection();
        //プリペアードステートメント
        PreparedStatement statement = null;
        try{
            //プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql);
            //プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            //プリペアードステートメントを実行
            ResultSet rSet = statement.executeQuery();

            //リザルトセットを全権走査
            while(rSet.next()){
                Subject subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                list.add(subject);

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
        return list;
    }
    public boolean save(Subject subject)throws Exception{
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("insert into subject(school_cd,cd,name) values(?,?,?)");
            statement.setString(1, subject.getSchool().getCd());
            statement.setString(2, subject.getCd());
            statement.setString(3, subject.getName());

            int result = statement.executeUpdate();
            return result > 0; 

        }catch(Exception e){
            throw e;
        }finally{
            if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }

        }
 
    }
    public boolean delete(Subject subject)throws Exception{
         Connection connection = getConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("delete from subject where cd = ?");
            statement.setString(1, subject.getCd());

            int result = statement.executeUpdate();
            return result > 0; 

        }catch(Exception e){
            throw e;
        }finally{
            if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }

        }
    }
}
