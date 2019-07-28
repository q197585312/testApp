package com.nanyang.app.Utils;


import android.util.Log;

/**
 * Created by ASUS on 2019/7/26.
 */

public class LogIntervalUtils {
    static long currentTime = 0;

    public static void logTime(String content) {
        logCustomTime(currentTime,content);
    }

    public static void logCustomTime(long ctime, String content) {
        String l = com.unkonw.testapp.libs.utils.TimeUtils.getTime("yyyy-MM-dd HH:mm:ss");
        Log.d("LogIntervalUtils", content + ",当前系统时间：" + l);

    }

}
