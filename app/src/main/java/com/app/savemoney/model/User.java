package com.app.savemoney.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class User {

    private String userName;
    private String email;
    private String password;
    private String fullName;

    public User() {

    }

    public User(String userName, String email, String password, String fullName) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Exclude
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();

        result.put("username", userName);
        result.put("email", email);
        result.put("password", password);
        result.put("fullname", fullName);

        return result;

    }
}
