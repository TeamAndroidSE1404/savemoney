package com.app.savemoney.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Category {

    private String uid;

    private String categoryName;

    private String icon;

    private String classify;

    private String disable;

    public static final String UID = "uid";
    public static final String CATEGORY_NAME = "categoryName";
    public static final String ICON = "icon";
    public static final String CLASSIFY = "classify";
    public static final String DISABLE = "disable";

    public Category() {

    }

    public Category(String uid){
        this.uid = uid;
    }

    public Category(String uid, String categoryName, String icon, String classify, String disable) {
        this.uid = uid;
        this.categoryName = categoryName;
        this.icon = icon;
        this.classify = classify;
        this.disable = disable;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    @Exclude
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();

        result.put(UID, uid);
        result.put(CATEGORY_NAME, categoryName);
        result.put(ICON, icon);
        result.put(CLASSIFY, classify);
        result.put(DISABLE, disable);

        return result;
    }

    public void toObject(HashMap<String, String> input) {
        this.uid = input.get(UID);
        this.categoryName = input.get(CATEGORY_NAME);
        this.icon = input.get(ICON);
        this.classify = input.get(CLASSIFY);
        this.disable = input.get(DISABLE);

    }
}
