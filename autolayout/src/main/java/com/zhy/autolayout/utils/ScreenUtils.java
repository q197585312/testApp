package com.zhy.autolayout.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;

/**
 * Created by zhy on 15/12/4.<br/>
 * form http://stackoverflow.com/questions/1016896/get-screen-dimensions-in-pixels/15699681#15699681
 */
public class ScreenUtils {

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);

        return height;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean checkDeviceHasNavigationBar(Context activity) {

        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity)
                .hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap
                .deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }

    public static double[] getScreenSize(Context context, boolean useDeviceSize) {

        double[] size = new double[2];

        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
// since SDK_INT = 1;
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        if (!useDeviceSize) {
            int bar = getStatusBarHeight(context);
            int nav = 0;
            boolean has;
            if (has = checkDeviceHasNavigationBar(context)) {
                L.e("hasNav:" + has);
                nav = getNavigationBarHeight(context);
            }
            double b = DecimalUtils.div(widthPixels, heightPixels);
            double cac = (bar + nav) * b;
            L.e("w/h:" + b);
            L.e("bar:" + bar);
            L.e("nav:" + nav);
            L.e("Ca:" + cac);
            double ca = 0.0d;
            if (b > 0.6)
                ca = (b * 1070 - 599);

            size[0] = widthPixels - cac - ca;
            size[1] = heightPixels + ca;

            return size;
        }

// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                widthPixels = realSize.x;
                heightPixels = realSize.y;
            } catch (Exception ignored) {
            }
        size[0] = widthPixels;
        size[1] = heightPixels;
        return size;
    }

    public static double[] getAutoScreenSize(Context context) {

        double[] size = new double[2];

        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
// since SDK_INT = 1;
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        size[0] = widthPixels;
        size[1] = heightPixels;
        return size;
    }

    public static double[] getAutoScreenSize(Context context, boolean useDeviceSize, int orientation) {

        double[] size = new double[2];

        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
// since SDK_INT = 1;
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        int bar = 0;
        if (!useDeviceSize) {
            bar = getStatusBarHeight(context);
        }
        int nav = 0;
        boolean has;
        if (has = checkDeviceHasNavigationBar(context)) {
            L.e("hasNav:" + has);
            nav = getNavigationBarHeight(context);
        }
        double cac = bar + nav;
        if (Configuration.ORIENTATION_LANDSCAPE == orientation) {
            size[0] = widthPixels - cac;
            size[1] = heightPixels;
        }
        if (Configuration.ORIENTATION_PORTRAIT == orientation) {
            size[0] = widthPixels;
            size[1] = heightPixels - cac;
        }

        return size;
    }

/*// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try
            {
                widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored)
            {
            }
// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17)
            try
            {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                widthPixels = realSize.x;
                heightPixels = realSize.y;
            } catch (Exception ignored)
            {
            }
        size[0] = widthPixels;
        size[1] = heightPixels;
        return size;
    }*/
}
