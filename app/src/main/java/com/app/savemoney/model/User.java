package com.app.savemoney.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 0L;

    private String userName;
    private String email;
    private String password;
    private String fullName;

    public User() {

    }

    public User(String userName, String password, String fullName) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
