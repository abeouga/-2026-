package com.example.model;

import java.io.Serializable;

public class Teacher implements Serializable {
    private String schoolCd;
    private String id;
    private String password;
    private String name;

    public Teacher() {
    }

    public Teacher(String schoolCd, String id, String password, String name) {
        this.schoolCd = schoolCd;
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return new School(this.schoolCd, null);
    }
}
