package com.nanyang.app.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {
    public static String getUrlString(String str) {
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 把string数组装换成get请求的？/& 拼接的字符串
     * @param strings
     * @return
     */
    public static String getParameterStr(String... strings) {
        String result = "";
        if (strings != null && strings.length % 2 == 0) {
            for (int i = 0; i < strings.length - 1; i += 2) {
                String key = strings[i];
                String value = strings[i + 1];
                if (key != null && !"".equals(key) && value != null
                        && !"".equals(value)) {
                    if (i != 0 && !"".equals(result)) {
                        result += "&";
                    }
                    result += strings[i] + "=" + strings[i + 1];
                }
            }
            if (!"".equals(result)) {
                result = "?" + result;
            }
        }
        return result;
    }


    public static boolean isNull(Context context, String s, String prompt) {
        if (s == null || s.equals("")) {
            if (prompt != null) {
                Toast.makeText(context, prompt, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
    }
    /**
     * 判断字符串是否为null或""
     *
     * @param string
     * @return 为空或null返回false，否则返回true
     */
    @SuppressLint("NewApi")
    public static boolean isNull(String string) {
        return !(string == null && "".equals(string));
    }

    public static String join(String[] array, String sep) {
        if (array == null) {
            return null;
        }

        int arraySize = array.length;
        int sepSize = 0;
        if (sep != null && !sep.equals("")) {
            sepSize = sep.length();
        }

        int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0]
                .length()) + sepSize) * arraySize);
        StringBuilder buf = new StringBuilder(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(sep);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    // no null in array
    public static String jsonJoin(String[] array) {
        int arraySize = array.length;
        int bufSize = arraySize * (array[0].length() + 3);
        StringBuilder buf = new StringBuilder(bufSize);
        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(',');
            }

            buf.append('"');
            buf.append(array[i]);
            buf.append('"');
        }
        return buf.toString();
    }

    /**
     * 将长字符从截取剩下的用...代替
     *
     * @param input
     * @param count
     * @return
     */
    public static String cutString(String input, int count) {
        return cutString(input, count, null);
    }

    /**
     * 将长字符从截取剩下的用more代替,more为空则用省略号代替
     *
     * @param input
     * @param count
     * @param more
     * @return
     */
    public static String cutString(String input, int count, String more) {
        String resultString = "";
        if (input != null) {
            if (more == null) {
                more = "...";
            }
            if (input.length() > count) {
                resultString = input.substring(0, count) + more;
            } else {
                resultString = input;
            }
        }
        return resultString;
    }

    /**
     * 获得指定中文长度对应的字符串长度，用于截取显示文字，一个中文等于两个英文
     *
     * @param chineseLengthForDisplay
     * @param string
     * @return
     */
    public static int chineseWidth2StringLenth(int chineseLengthForDisplay, String string) {
        int result = 0;
        int displayWidth = chineseLengthForDisplay * 2;
        if (string != null) {
            for (char chr : string.toCharArray()) {
                // 中文
                if (chr >= 0x4e00 && chr <= 0x9fbb) {
                    displayWidth -= 2;
                } else {
                    // 英文
                    displayWidth -= 1;
                }
                if (displayWidth <= 0) {
                    break;
                }
                result++;
            }
        }
        return result;
    }


    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(float s){
        String ss=s+"";
        if(ss.indexOf(".") > 0){
            ss = ss.replaceAll("0+?$", "");//去掉多余的0
            ss = ss.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return ss;
    }
    public static String  URLEncode(String url)  {
        try {
            return URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }


    public static  Map<String,Integer> countDifferentCharMap(String str) {
        Map<String,Integer> countMap=new LinkedHashMap<>();
        for (char ch : str.toCharArray()) {
            String ss = String.valueOf(ch);
            Integer count = countMap.get(ss);
            if (count == null)
                count = 0;
            count++;
            countMap.put(ss, count);

        }
        return countMap;
    }
    public static String findGroup0(String str1, String fromat) {
        Pattern pattern2 = Pattern.compile(fromat);
        Matcher matcher2 = pattern2.matcher(str1);
        while (matcher2.find()) {
           return matcher2.group(0);
        }
        return "";
    }
    public static boolean matches(String str1,  String fromat){
        return str1.matches(fromat);
    }

}
