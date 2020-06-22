package com.zhy.autolayout.config;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;

import com.zhy.autolayout.utils.L;
import com.zhy.autolayout.utils.ScreenUtils;

/**
 * Created by zhy on 15/11/18.
 */
public class AutoLayoutConifg {

    private static AutoLayoutConifg sIntance = new AutoLayoutConifg();


    private static final String KEY_DESIGN_WIDTH = "design_width";
    private static final String KEY_DESIGN_HEIGHT = "design_height";

    private int initDesignWidth;
    private int initDesignHeight;


    private double mScreenWidth;
    private double mScreenHeight;
    private int orientationAuto = -11;


    public int getmDesignWidth() {
        return mDesignWidth;
    }

    public void setmDesignWidth(int mDesignWidth) {
        this.mDesignWidth = mDesignWidth;
    }

    public int getmDesignHeight() {
        return mDesignHeight;
    }

    public void setmDesignHeight(int mDesignHeight) {
        this.mDesignHeight = mDesignHeight;
    }

    private int mDesignWidth;
    private int mDesignHeight;

    private AutoLayoutConifg() {
    }

    public void checkParams() {
        if (mDesignHeight <= 0 || mDesignWidth <= 0) {
            throw new RuntimeException(
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.");
        }
    }

    public AutoLayoutConifg useDeviceSize() {
        return this;
    }


    public static AutoLayoutConifg getInstance() {
        return sIntance;
    }


    public double getScreenWidth() {
        return mScreenWidth;
    }

    public double getScreenHeight() {
        return mScreenHeight;
    }

    public int getDesignWidth() {
        return mDesignWidth;
    }

    public int getDesignHeight() {
        return mDesignHeight;
    }


    public void init(Context context) {
        getMetaData(context);
/*        double[] screenSize = ScreenUtils.getScreenSize(context, isFullScreen);
        initScreenWidth = screenSize[0];
        initScreenHeight = screenSize[1];
        mScreenWidth = initScreenWidth;
        mScreenHeight = initScreenHeight;*/
        setSize(context);
    }

    public void setSize(Context context) {

        boolean isLand = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (isLand) {
            mDesignWidth = initDesignHeight;
            mDesignHeight = initDesignWidth;
        } else {
            mDesignWidth = initDesignWidth;
            mDesignHeight = initDesignHeight;
        }
        setScreenVisibleSize(context);
    }

    public void setScreenVisibleSize(Context context) {
   /*     if (checkHasInit(context))
            return;*/
        Rect outRect1 = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
        mScreenHeight = outRect1.height();
        mScreenWidth = outRect1.width();
        this.orientationAuto = context.getResources().getConfiguration().orientation;
        double[] screenSize = ScreenUtils.getAutoScreenSize(context);
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mScreenHeight = screenSize[1];
        } else {
            mScreenWidth = screenSize[0];
        }
        L.e(",context:" + ((Activity) context).getClass().getSimpleName() +

                ",mScreenWidth:" + mScreenWidth
                + ",mScreenHeight:" + mScreenHeight
                + ",mDesignWidth:" + mDesignWidth
                + ",mDesignHeight:" + mDesignHeight
                + ",orientationAuto:" + orientationAuto);
    }

    private boolean checkHasInit(Context context) {
        if (mScreenHeight > 0 && mScreenWidth > 0 && orientationAuto == context.getResources().getConfiguration().orientation) {
            return true;
        }
        return false;
    }

    private void getMetaData(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            ActivityInfo info = packageManager
                    .getActivityInfo(((Activity) context).getComponentName(), PackageManager.GET_META_DATA);
            if (info != null && info.metaData != null) {
                initDesignWidth = (int) info.metaData.get(KEY_DESIGN_WIDTH);
                initDesignHeight = (int) info.metaData.get(KEY_DESIGN_HEIGHT);
            }
            if (initDesignWidth < 1 || initDesignHeight < 1) {
                applicationInfo = packageManager.getApplicationInfo(context
                        .getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    initDesignWidth = (int) applicationInfo.metaData.get(KEY_DESIGN_WIDTH);
                    initDesignHeight = (int) applicationInfo.metaData.get(KEY_DESIGN_HEIGHT);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.", e);
        }

        L.e(" designWidth =" + mDesignWidth + " , designHeight = " + mDesignHeight);
    }


}
