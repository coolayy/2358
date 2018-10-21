package com.androidas.lib.util;

import android.content.Context;


public class DisplayUtils {
    private final static String className = DisplayUtils.class.getSimpleName();

    private DisplayUtils(){}

    public static int pxTodip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale+0.5f);
    }

    public static int dipTopx(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue*scale+0.5f);
    }

    public static int pxTosp(Context context, float pxValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue/fontScale+0.5f);
    }

    public static int spTopx(Context context, float spValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue*fontScale+0.5f);
    }

}
