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

    public static final String UID = "uid";
    public static final String DESCRIPTION = "description";
    public static final String DATE = "date";
    public static final String CATEGORY = "category";
    public static final String PRICE = "price";



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
        result.put(UID, uid);
        result.put(DESCRIPTION, description);
        result.put(DATE, DateUtils.dateToString(date, CommonCodeValues.DATE_DDMMYYYY_HHMM));
        result.put(CATEGORY, cate.getUid());
        result.put(PRICE, price);
        return result;
    }

    public void toObject(HashMap<String, Object> input, Map<String, Category> categories){
        this.uid = (String) input.get(UID);
        this.description = (String)input.get(DESCRIPTION);
        this.date = DateUtils.StringToDate(((String)input.get(DATE)), CommonCodeValues.DATE_DDMMYYYY_HHMM);

        this.cate = categories.get((String)input.get(CATEGORY));

        this.price = Double.valueOf((Long)input.get(PRICE));

    }

    public void toObject(HashMap<String, Object> input){
        this.uid = (String) input.get(UID);
        this.description = (String)input.get(DESCRIPTION);
        this.date = DateUtils.StringToDate(((String)input.get(DATE)), CommonCodeValues.DATE_DDMMYYYY_HHMM);

        this.cate = null;

        this.price = Double.valueOf((Long)input.get(PRICE));

    }


}
