package com.app.savemoney.model;

public class Category {

    private int id;

    private String categoryName;

    private String icon;

    private String classify;

    public Category() {

    }

    public Category(int id, String categoryName, String icon, String classify) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
