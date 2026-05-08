package com.example.model;
import java.io.Serializable;

public class SchoolClass implements Serializable{

    private String schoolCd;
    private String classNum;

    public SchoolClass() {
    }

    public SchoolClass(String schoolCd, String classNum) {
        this.schoolCd = schoolCd;
        this.classNum = classNum;
    }

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}
