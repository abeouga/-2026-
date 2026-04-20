package com.example.model;

import java.io.Serializable;

public class School implements Serializable {
    private String cd;
    private String name;

    public School() {
    }

    public School(String cd, String name) {
        this.cd = cd;
        this.name = name;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
