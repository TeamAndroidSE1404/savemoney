package com.app.savemoney.model;

import java.util.Date;

public class Expense {

    private int id;

    private String description;

    private Date date;

    private Category cate;

    private double price;

    public Expense() {
    }

    public Expense(int id, String description, Date date, Category cate, double price) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.cate = cate;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCate() {
        return cate;
    }

    public void setCate(Category cate) {
        this.cate = cate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
