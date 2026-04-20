package com.example.model;

import java.io.Serializable;

public class Test implements Serializable {
    private String schoolCd;
    private String studentNo;
    private String subjectCd;
    private Integer no;
    private String classNum;
    private Integer point;

    // join等で表示用に各種名称を持たせるプロパティ(画面表示用)
    private String studentName;
    private String subjectName;
    
    public Test() {
    }

    public Test(String schoolCd, String studentNo, String subjectCd, Integer no, String classNum, Integer point) {
        this.schoolCd = schoolCd;
        this.studentNo = studentNo;
        this.subjectCd = subjectCd;
        this.no = no;
        this.classNum = classNum;
        this.point = point;
    }

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
