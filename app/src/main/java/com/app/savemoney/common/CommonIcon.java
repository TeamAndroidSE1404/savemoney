package com.app.savemoney.common;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class CommonIcon {
    public static Drawable getIcon(Context context, String name) {

        String uri = "@drawable/" + name;
        Drawable res;
        try {
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            res = context.getResources().getDrawable(imageResource);
        } catch (Exception ex) {
            int imageResource = context.getResources().getIdentifier("@drawable/ic_game", null, context.getPackageName());
            res = context.getResources().getDrawable(imageResource);
        }
        return res;

    }

}
