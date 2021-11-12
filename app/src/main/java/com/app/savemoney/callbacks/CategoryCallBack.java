package com.app.savemoney.callbacks;

import com.app.savemoney.model.Category;

import java.util.Map;

public interface CategoryCallBack {
    public void onCallbackCategory(Map<String, Category> value);

}
