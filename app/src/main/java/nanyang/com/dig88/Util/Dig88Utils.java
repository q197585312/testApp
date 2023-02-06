package nanyang.com.dig88.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import java.util.ArrayList;
import java.util.List;

import nanyang.com.dig88.BuildConfig;
import xs.com.mylibrary.allinone.util.AppTool;


/**
 * Created by Administrator on 2019/8/23.
 */

public class Dig88Utils {
    public static boolean isFirstIn(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("first_pref", Context.MODE_PRIVATE);
        boolean isFirstIn = preferences.getBoolean("isFirstIn", true);
        if (isFirstIn) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
            return true;
        } else {
            return false;
        }
    }

    public static String strFormat(String format, String number) {
        double balance = Double.valueOf(number);
        String strBalance = String.format(format, balance);
        return strBalance;
    }

    public static void setLang(Context mContext) {
        String localLanguage = AppTool.getAppLanguage(mContext);
        String[] split = BuildConfig.Language.split(",");
        List<String> langList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            langList.add(split[i]);
        }
        if (!langList.contains(localLanguage)) {
            localLanguage = split[0];
        }
        if (BuildConfig.FLAVOR.equals("kbet3") || BuildConfig.FLAVOR.equals("ibet567") || BuildConfig.FLAVOR.equals("hjlh6688") ||
                BuildConfig.FLAVOR.equals("dig88") || BuildConfig.FLAVOR.equals("jf58")) {
            if (Dig88Utils.isFirstIn(mContext)) {
                localLanguage = split[1];
                if (BuildConfig.FLAVOR.equals("hjlh6688")) {
                    localLanguage = split[0];
                } else if (BuildConfig.FLAVOR.equals("jf58")) {
                    localLanguage = split[2];
                }
            }
        }
        AppTool.setAppLanguage(mContext, localLanguage);
    }

    public static String upperFirstLatter(String letter) {
        return letter.substring(0, 1).toUpperCase() + letter.substring(1);
    }

    public static SpannableStringBuilder handleStringColor(String content, String splitStr, int firstColor, int nextColor) {
        //xx:(xxx)
        int start = content.indexOf(splitStr);
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        style.setSpan(new ForegroundColorSpan(firstColor), 0, start, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.BLACK), start, start + 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(nextColor), start + 2, content.length() - 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.BLACK), content.length() - 1, content.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return style;
    }

    public static SpannableStringBuilder handleStringColor(String content) {
        //xx:(xxx)
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        style.setSpan(new ForegroundColorSpan(Color.RED), 0, 10, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.BLACK), 11, content.length() - 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return style;
    }

    public static String getLanguage(Context context) {
        String lg = AppTool.getAppLanguage(context);
        if (TextUtils.isEmpty(lg)) {
            return "en";
        } else {
            switch (lg) {
                case "en":
                    return "en";
                case "th":
                    return "th";
                case "vn":
                    return "vn";
                case "zh":
                    return "cn";
                case "in":
                    return "id";
                case "kr":
                    return "kr";
                case "ms":
                    return "ma";
                case "kh":
                    return "ca";
                default:
                    return "en";
            }
        }
    }
}
