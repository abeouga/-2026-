package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable{
    private int entYear;
    private String studentNo;
    private String studentName;
    private String classNum;
    private Map<Integer,Integer> points;

    public int getEntYear(){
        return entYear;
    }
    public void setEntYear(int entYear){
        this.entYear=entYear;
    }
    public String getstudentNo(){
        return studentNo;
    }
    public void setStudentNo(String studentNo){
        this.studentNo=studentNo;
    }
    public String getstudentName(){
        return studentName;
    }
    public void setStudentName(String studentName){
        this.studentName=studentName;
    }
    public String getClassNum(){
        return classNum;
    }
    public void setClassNum(String classNum){
        this.classNum=classNum;
    }
    public Map<Integer,Integer> getPoints(){
        return points;
    }
    public void setPoints(Map<Integer,Integer> points){
        this.points=points;
    }
    public String getPoint(int key){
        String stkey = String.valueOf(key);
        return stkey;
    }
    public void putPoint(int key,int value){
        points.put(key, value);
    }
    
}
