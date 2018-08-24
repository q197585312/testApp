package com.unkonw.testapp.libs.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.unkonw.testapp.R;
import com.unkonw.testapp.libs.base.BaseApplication;


/**
 * Toat 工具类
 */

public class ToastUtils {
    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        if (TextUtils.isEmpty(message)||message==null) {
            message = "";
        }
        if (isShow)
            Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(int message) {
        if (isShow)
            Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (TextUtils.isEmpty(message)) {
            message = "";
        }
        if (isShow)
            Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(int message) {
        if (isShow)
            Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        if (TextUtils.isEmpty(message)) {
            message = "";
        }
        if (isShow)
            Toast.makeText(BaseApplication.getInstance(), message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(int message, int duration) {
        if (isShow)
            Toast.makeText(BaseApplication.getInstance(), message, duration).show();
    }

    public static void showMyToast(String msg) {
        Context context = BaseApplication.getInstance();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.def_toast, null);
        TextView chapterNameTV = (TextView) view.findViewById(R.id.chapterName);
        if (TextUtils.isEmpty(msg)) {
            msg = "";
        }
        chapterNameTV.setText(msg);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        if (isShow)
            toast.show();
    }

}
