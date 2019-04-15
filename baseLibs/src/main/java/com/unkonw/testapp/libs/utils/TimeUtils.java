package com.unkonw.testapp.libs.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/1/9.
 */
public class TimeUtils {
    /**
     * 获取增加多少月的时间
     *
     * @return addMonth - 增加多少月
     */
    public static Date getAddMonthDate(Date date,int addMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, addMonth);
        return calendar.getTime();
    }

    /**
     * 获取增加多少天的时间
     *
     * @return addDay - 增加多少天
     */
    public static Date getAddDayDate(Date date,int addDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, addDay);
        return calendar.getTime();
    }

    /**
     * 获取增加多少小时的时间
     *
     * @return addDay - 增加多少小时
     */
    public static Date getAddHourDate(Date date,int addHour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, addHour);
        return calendar.getTime();
    }

    /**
     * 显示时间格式为 hh:mm
     *
     * @param when
     * @return String
     */
    public static String formatLongTime(long when, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String temp = sdf.format(when);
//        if (temp != null && temp.length() == 5 && temp.substring(0, 1).equals("0")) {
//            temp = temp.substring(1);
//        }
        return temp;
    }



    /**
     * 将长时间格式字符串转换为字符串,默认为yyyy-MM-dd HH:mm:ss
     *
     * @param milliseconds long型时间,支持毫秒和秒
     * @param dataFormat   需要返回的时间格式，例如： yyyy-MM-dd， yyyy-MM-dd HH:mm:ss
     * @return dataFormat格式的时间结果字符串
     */
    public static String dateFormat(long milliseconds, String dataFormat) {
        long tempTimestamp = milliseconds > 9999999999L ? milliseconds : milliseconds * 1000;
        if (TextUtils.isEmpty(dataFormat)) {
            dataFormat = "yyyy-MM-dd HH:mm:ss";
        }
        Date date = new Date(tempTimestamp * 1l);
        SimpleDateFormat formatter = new SimpleDateFormat(dataFormat);
        return formatter.format(date);
    }

    /**
     * @param when
     * @return String
     */
    public static String dateFormat(Object when, String dataFormat) {
        if (TextUtils.isEmpty(dataFormat)) {
            dataFormat = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
        String temp = sdf.format(when);
        return temp;
    }

    public static String dateFormatChange(String time, String formFormat, String toFormat, Locale locale) {

        if (formFormat.isEmpty() || toFormat.isEmpty())
            return time;

        DateFormat formatter = new SimpleDateFormat(formFormat, locale);

        DateFormat dt = new SimpleDateFormat(toFormat);

        try {
            return dt.format(formatter.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }

    /**
     *
     * @param now
     * @param other
     * @param format
     * @return now-other
     */
    public static long diffTime(String now,String other,String format) {
        if (TextUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateFormat df = new SimpleDateFormat(format);

        Date d1 = null;Date d2=null;
        long diff=0;
        try {
            d1 = df.parse(now);
            d2=df.parse(other);
            diff = d1.getTime() - d2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }
    public static long diffTime(String s1,String format1,String s2,String format2) {
        //设定时间的模板

        SimpleDateFormat sdf1 = new SimpleDateFormat(format1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(format2);
        //得到指定模范的时间

        //比较
        long diff=0;
        try {
            Date d1 = sdf1.parse(s1);
            Date d2 = sdf2.parse(s2);
            diff = d1.getTime() - d2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }
    /**
     * 是否同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(long date1, long date2) {
        long days1 = date1 / (1000 * 60 * 60 * 24);
        long days2 = date2 / (1000 * 60 * 60 * 24);
        return days1 == days2;
    }
    public static  Date format2Date(String obj,String format){
        SimpleDateFormat f = new SimpleDateFormat(format);
        try {
            return f.parse(obj);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

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
