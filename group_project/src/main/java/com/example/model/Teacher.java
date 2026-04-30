package com.example.model;

import java.io.Serializable;

public class Teacher implements Serializable {
    private String schoolCd;
    private String id;
    private String password;
    private String name;
    private String role;

    public Teacher() {
    }

    public Teacher(String schoolCd, String id, String password, String name, String role) {
        this.schoolCd = schoolCd;
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public Teacher(String schoolCd, String id, String password, String name) {
        this(schoolCd, id, password, name, "viewer");
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public School getSchool() {
        return new School(this.schoolCd, null);
    }
}
