package com.app.savemoney.model;

import com.app.savemoney.common.CommonCodeValues;
import com.app.savemoney.common.DateUtils;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Expense {

    private String uid;

    private String description;

    private Date date;

    private Category cate;

    private double price;

    public Expense() {
    }

    public Expense(String uid, String description, Date date, Category cate, double price) {
        this.uid = uid;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("description", description);
        result.put("date", DateUtils.dateToString(date, CommonCodeValues.DATE_DDMMYYYY_HHMM));
        result.put("category", cate.getUid());
        result.put("price", price);
        return result;
    }

    public void toObject(HashMap<String, String> input, Map<String, Category> categories){
        this.uid = input.get("uid");
        this.description = input.get("description");
        this.date = DateUtils.StringToDate(input.get("date"), CommonCodeValues.DATE_DDMMYYYY_HHMM);
        this.cate = categories.get(input.get("category"));
        this.price = Double.valueOf(input.get("price"));

    }


}
