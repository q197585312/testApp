package gaming178.com.casinogame.Util;


import android.util.Log;

import gaming178.com.mylibrary.allinone.util.TimeUtils;

/**
 * Created by ASUS on 2019/7/26.
 */

public class LogIntervalUtils {
    static long currentTime = 0;

    public static void logTime(String content) {
        logCustomTime(currentTime,content);
    }

    public static void logCustomTime(long ctime, String content) {
        String s = TimeUtils.dateFormat("");
        Log.d("LogIntervalUtils", content + ",当前系统时间：" + s);
    }
}
