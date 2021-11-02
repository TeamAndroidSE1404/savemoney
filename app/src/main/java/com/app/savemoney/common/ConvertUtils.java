package com.app.savemoney.common;


import java.text.DecimalFormat;

public class ConvertUtils {

    public static String addComaPrice(double price) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        return decimalFormat.format(price);

    }

}
