package com.app.savemoney.common;

import java.security.MessageDigest;
import java.text.DecimalFormat;


public class ConvertUtils {

    public static String addComaPrice(double price) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        return decimalFormat.format(price);

    }

    public static String encodePassword(String base) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();


        } catch (Exception e) {

            e.printStackTrace();
            return base;
        }


    }

}
