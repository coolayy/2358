package com.androidas.lib.util;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ConnectUtil{
    static URL baiduUrl;
    static HttpURLConnection conn;


    public static void inNet(final Handler handler) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    if (baiduUrl == null) {
                        baiduUrl = new URL("http://www.baidu.com/");
                    }
                    if (conn == null) {
                        conn = (HttpURLConnection) baiduUrl.openConnection();
                    }
                    conn.setConnectTimeout(10000);
                    Looper.prepare();
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                        handler.
//                        Toast.makeText(context, "网络被墙了!", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(context, "网络断掉了!", Toast.LENGTH_SHORT).show();
                    }
                    Looper.loop();
                } catch (MalformedURLException e) {
//                    Toast.makeText(context, "url错误:code:4", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }.start();
    }
}
