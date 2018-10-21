package com.androidas.lib.util;

import android.content.Intent;
import android.net.Uri;


public class IntentUtils {
    private IntentUtils(){}


    public static Intent openWebDefault(String uri){
        Uri content_url = Uri.parse(uri);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(content_url);
        return intent;
    }
}
