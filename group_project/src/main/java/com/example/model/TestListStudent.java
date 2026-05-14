package com.example.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListStudent implements Serializable{
    private int entYear;
    private String subjectCd;
    private String studentNo;
    private String studentName;
    private String classNum;
    //private Map<Integer,Integer> points = new HashMap<>();
    private int point;
    private int no;

    public int getEntYear(){
        return entYear;
    }
    public void setEntYear(int entYear){
        this.entYear=entYear;
    }
    public String getStudentNo(){
        return studentNo;
    }
    public void setStudentNo(String studentNo){
        this.studentNo=studentNo;
    }
    public String getStudentName(){
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
    public int getPoint(){
        return point;
    }
    public String getSubjectCd(){
        return subjectCd;
    }
    public void setSubjectCd(String subjectCd){
        this.subjectCd=subjectCd;
    }
    public void setPoint(int point){
        this.point=point;
    }
    public int getNo(){
        return no;
    }
    public void setNo(int no){
        this.no=no;
    }
    
}
