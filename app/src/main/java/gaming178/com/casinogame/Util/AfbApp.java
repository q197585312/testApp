package gaming178.com.casinogame.Util;

import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;
import com.unkonw.testapp.libs.base.BaseApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.casinogame.Chat.FaceConversionUtil;
import gaming178.com.mylibrary.allinone.util.ThreadPoolUtils;

/**
 * Created by Administrator on 2016/3/29.
 */
public class AfbApp extends BaseApplication {

    private void initEmjoy(final Context mContext) {
        ThreadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
                FaceConversionUtil.getInstace().getFileText(mContext);
            }
        });
    }

    private void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        handleSSLHandshake();
        closeAndroidPDialog();
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setAppChannel(BuildConfig.FLAVOR);  //设置渠道
        CrashReport.initCrashReport(getApplicationContext(), "01dfe5a2d5", true, strategy);
//        initEmjoy(this);
    }
}
