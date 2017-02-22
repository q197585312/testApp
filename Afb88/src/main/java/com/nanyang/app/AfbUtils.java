package com.nanyang.app;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/2/22.
 */

public class AfbUtils {

    public static String changeValueS(String v) {
        if (v == null || v.equals(""))
            return "";
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = "";
        try {
            if (Float.valueOf(v) == 0) {
                return "";
            }
            p = decimalFormat.format(Float.valueOf(v) / 10);//format 返回的是字符串

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return p;
    }
}
