package com.example.model;

import java.io.Serializable;

public class TestListSubject implements Serializable{
    private String subjectName;
    private String subjectCd;
    private int num;
    private int no;
    private int point;
    private String classNum;
    public String getSubjectName(){
        return subjectName;
    }
    public void setSubjectName(String subjectName){
        this.subjectName=subjectName;
    }
    public String getSubjectCd(){
        return subjectCd;
    }
    public void setSubjectCd(String subjectCd){
        this.subjectCd=subjectCd;
    }
    public int getNum(){
        return num;
    }
    public void setNum(int num){
        this.num=num;
    }
    public int getPoint(){
        return point;
    }
    public void setPoint(int point){
        this.point=point;
    }
    public String getClassNum(){
        return classNum;
    }
    public void setClassNum(String classNum){
        this.classNum=classNum;
    }
    public int getNo(){
        return no;
    }
    public void setNo(int no){
        this.no=no;
    }
}
