package com.app.savemoney.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class User {

    private String email;
    private String password;
    private String fullName;

    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String FULL_NAME = "fullName";


    public User() {

    }

    public User(String userName, String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
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

        result.put(EMAIL, email);
        result.put(PASSWORD, password);
        result.put(FULL_NAME, fullName);

        return result;

    }
}
