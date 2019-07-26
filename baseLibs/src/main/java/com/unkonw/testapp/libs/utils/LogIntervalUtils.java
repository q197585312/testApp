package com.unkonw.testapp.libs.utils;


import android.util.Log;

/**
 * Created by ASUS on 2019/7/26.
 */

public class LogIntervalUtils {
    static long currentTime = 0;

    public static void logTime(String content) {
        long l = System.currentTimeMillis();
        Log.d("LogIntervalUtils", content + ",当前系统时间：" + l);
        if (currentTime == 0)
            Log.d("LogIntervalUtils", content + ",上次间隔时间：" + 0);
        else
            Log.d("LogIntervalUtils", content + ",上次间隔时间：" + (l - currentTime));
        currentTime = l;
    }
}
