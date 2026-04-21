//生徒一覧を表示するためにDAOから生徒情報を取得したレコードを格納するBeanです。
package com.example.model;

import java.io.Serializable;

//Serializableを実装することでログイン認証がログアウトで解除されるまでBeanをセッションに保持できるようにしています。
public class Student implements Serializable {
    private String schoolCd;
    private String no;
    private String name;
    private Integer entYear;
    private String classNum;
    private Boolean isAttend;

    public Student() {
    }

    public Student(String schoolCd, String no, String name, Integer entYear, String classNum, Boolean isAttend) {
        this.schoolCd = schoolCd;
        this.no = no;
        this.name = name;
        this.entYear = entYear;
        this.classNum = classNum;
        this.isAttend = isAttend;
    }

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEntYear() {
        return entYear;
    }

    public void setEntYear(Integer entYear) {
        this.entYear = entYear;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Boolean getIsAttend() {
        return isAttend;
    }

    public void setIsAttend(Boolean isAttend) {
        this.isAttend = isAttend;
    }
}
