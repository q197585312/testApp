package com.nanyang.app.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2019/4/3.
 */

public class TimeUtils {
    //yyyy-MM-dd-HH:mm
    public static String getTime(String format) {
        SimpleDateFormat myFmt = new SimpleDateFormat(format);
        return myFmt.format(new Date());
    }

    //yyyy-MM-dd-HH:mm
    public static String getTime(String format, Locale locale) {
        SimpleDateFormat myFmt = new SimpleDateFormat(format, locale);
        return myFmt.format(new Date());
    }
}
