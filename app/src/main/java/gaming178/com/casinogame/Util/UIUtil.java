package gaming178.com.casinogame.Util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.List;

public class UIUtil {

    private static int screenWidth;
    private static int screenHeight;
    private static WindowManager wm;

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int wdip2px(Context context, int resDimenId) {
        float dip = context.getResources().getDimension(resDimenId);
        final float scale = context.getResources().getDisplayMetrics().density;
        return dip2px(context, (int) (dip / scale + 0.5f));
    }

    private static void initScreen(Context context) {
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;         // 屏幕宽度（像素）
        screenHeight = dm.heightPixels;       // 屏幕高度（像素）
    }

    public static int getScreenWidth(Context context) {
        if (wm == null) {
            initScreen(context);
        }
        return screenWidth;
    }

    public static int getScreenHeight(Context context) {
        if (wm == null) {
            initScreen(context);
        }
        return screenHeight;
    }
    public static boolean  isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;

    }

}
