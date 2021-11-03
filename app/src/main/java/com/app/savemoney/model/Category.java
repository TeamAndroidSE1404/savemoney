package com.app.savemoney.model;

//import com.google.firebase.database.Exclude;
//import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

//@IgnoreExtraProperties
public class Category {

    private String uid;

    private String categoryName;

    private String icon;

    private String classify;

    public Category() {

    }

    public Category(String uid, String categoryName, String icon, String classify) {
        this.uid = uid;
        this.categoryName = categoryName;
        this.icon = icon;
        this.classify = classify;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

//    @Exclude
//    public Map<String, Object> toMap() {
//        Map<String, Object> result = new HashMap<>();
//
//        result.put("uid", uid);
//        result.put("categoryName", categoryName);
//        result.put("icon", icon);
//        result.put("classify", classify);
//
//        return result;
//    }
}
