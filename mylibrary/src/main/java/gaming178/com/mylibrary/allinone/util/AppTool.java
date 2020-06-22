package gaming178.com.mylibrary.allinone.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import gaming178.com.mylibrary.lib.util.LogUtil;

/**
 * Created by Administrator on 2015/9/7.
 */
public class AppTool {
    /**
     * 页面跳转
     *
     * @param context
     * @param cls
     */
    public static void activiyJump(Context context, Class<?> cls, Bundle extras) {
        Intent intent = new Intent(context, cls);
        if (extras != null)
            intent.putExtras(extras);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 页面跳转等待返回
     *
     * @param context
     * @param cls
     */
    public static void activiyJumpForResult(Activity context, Class<?> cls, Bundle extras, int Code) {
        Intent intent = new Intent(context, cls);
        if (extras != null)
            intent.putExtras(extras);
        context.startActivityForResult(intent, Code);
    }

    /**
     * 页面跳转
     *
     * @param context
     * @param cls
     */
    public static void activiyJump(Context context, Class<?> cls) {
        activiyJump(context, cls, null);
    }

    public static void activityJumpForResult(Activity act, Class<?> cls, int requsetCode) {
        Intent intent = new Intent(act, cls);
        act.startActivityForResult(intent, requsetCode);
    }

    /**
     * @param fm
     * @param fg
     * @param fgmentViewId
     * @param addStarck
     */
    public static void setFragment(FragmentManager fm, Fragment fg,
                                   int fgmentViewId, boolean addStarck) {
        FragmentTransaction tx = fm.beginTransaction();

//        if (addStarck) {
//            tx.add(fgmentViewId, fg,fg.getClass().getSimpleName());
//            tx.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//            tx.addToBackStack(fg.getClass().getSimpleName());
//        }
//        else{

//            fm.popBackStackImmediate(fg.getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        tx.replace(fgmentViewId, fg, fg.getClass().getSimpleName());
        if (addStarck)
            tx.addToBackStack(fg.getClass().getSimpleName());
        tx.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//        }
        tx.commit();
    }


    public static void setFragment(FragmentManager fm, Fragment fg,
                                   int fgmentViewId) {
        setFragment(fm, fg, fgmentViewId, false);
    }

    /**

     */
    public static void turnToFragment(FragmentManager fm, int fgmentViewId, Fragment from, Fragment to, Bundle args) {
        FragmentTransaction tx = fm.beginTransaction();
        tx.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        if (args != null && !args.isEmpty())//判断是否有参数
            to.setArguments(args);
        if (from == null) {
            tx.add(fgmentViewId, to).commit();
        } else {
            if (!to.isAdded()) {
                // 先判断是否被add过
                tx.hide(from).add(fgmentViewId, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                tx.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param act
     * @param event
     * @return
     */
    public static boolean isShouldHideInput(Activity act, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = act.getCurrentFocus();
            if (v != null && (v instanceof EditText)) {
                int[] l = {0, 0};
                v.getLocationInWindow(l);
                int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                        + v.getWidth();
                return !(event.getX() > left && event.getX() < right
                        && event.getY() > top && event.getY() < bottom);
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     */
    public static void hideSoftInput(Activity act, MotionEvent ev) {
        // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
        if (isShouldHideInput(act, ev)) {
            View v = act.getCurrentFocus();
            IBinder token = v.getWindowToken();
            if (token != null) {
                InputMethodManager im = (InputMethodManager) act
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(token,
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static void showToast(Activity act, String msg, int gravity) {
        Toast toast = Toast.makeText(act, msg, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    public static void showToast(Activity act, String msg) {
        showToast(act, msg, Gravity.CENTER);
    }

    /**
     * 发送广播
     *
     * @param context
     * @param type
     */
    public static void sendBroadcast(Context context, String type) {
        Intent mIntent = new Intent(type);
        context.sendBroadcast(mIntent);
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 保存对象到文件
     */
    public static boolean saveObjectData(Context context, String name, Object obj) {
        boolean result = false;
        try {
            FileOutputStream stream = context.openFileOutput(name,
                    Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(obj);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从文件中读取对象
     */
    public static Object getObjectData(Context context, String name) {
        Object obj = null;
        try {
            FileInputStream stream = context.openFileInput(name);
            ObjectInputStream ois = new ObjectInputStream(stream);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void setAppLanguage(Context context, String lanAtr) {
        StackTraceElement[] temp = Thread.currentThread().getStackTrace();
        StackTraceElement a = (StackTraceElement) temp[2];
        StackTraceElement b = (StackTraceElement) temp[3];

        if (lanAtr == null || lanAtr.equals("")) {
            lanAtr = getAppLanguage(context);
        } else {
            SharePreferenceUtil.setValue(context, "language", lanAtr);
        }
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (lanAtr == null || lanAtr.equals("")) {
            config.locale = Locale.ENGLISH;

        } else {

            if (lanAtr.equals("zh")) {
                config.locale = Locale.SIMPLIFIED_CHINESE;
            } else if (lanAtr.equals("en")) {
                config.locale = Locale.ENGLISH;
            } else if (lanAtr.equals("th")) {
                config.locale = new Locale("th");
            } else if (lanAtr.equals("zh_TW")) {
                config.locale = Locale.TRADITIONAL_CHINESE;
            } else {
                config.locale = new Locale(lanAtr);
            }
        }
        LogUtil.d("setAppLanguage",lanAtr+ "-setAppLanguage:" + a.getMethodName() + "-" + b.getMethodName());
        Locale.setDefault(Locale.ENGLISH);// 设置选定的语言
        resources.updateConfiguration(config, dm);
    }

    public static String getAppLanguage(Context context) {
        return SharePreferenceUtil.getString(context, "language");
    }

    /**
     * 双击退出函数
     */
    public static boolean isShort = false;

    public static boolean isShort2Click(int millisecond) {
        Timer tExit = null;
        if (!isShort) {
            isShort = true;
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isShort = false; // 取消退出
                }
            }, millisecond); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
            return false;
        } else {
            return true;
        }

    }

    /**
     * 获取当前的应用信息
     *
     * @return
     */
    public static PackageInfo getApkInfo(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            return manager.getPackageInfo(context.getPackageName(), 0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void installApk(String path, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(path)),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static String getLocalIP() {

        Enumeration ifaces;
        try {
            ifaces = NetworkInterface.getNetworkInterfaces();

            while (ifaces.hasMoreElements()) {

                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                Enumeration iaddresses = iface.getInetAddresses();

                while (iaddresses.hasMoreElements()) {
                    InetAddress iaddress = (InetAddress) iaddresses.nextElement();
                    if (!iaddress.isLoopbackAddress() && !iaddress.isLinkLocalAddress() && !iaddress.isSiteLocalAddress()) {
                        return iaddress.getHostAddress() != null ? iaddress.getHostAddress() : iaddress.getHostName();
                    }
                }
            }

            ifaces = NetworkInterface.getNetworkInterfaces();

            while (ifaces.hasMoreElements()) {

                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                Enumeration iaddresses = iface.getInetAddresses();

                while (iaddresses.hasMoreElements()) {
                    InetAddress iaddress = (InetAddress) iaddresses.nextElement();
                    if (!iaddress.isLoopbackAddress() && !iaddress.isLinkLocalAddress()) {
                        return iaddress.getHostAddress() != null ? iaddress.getHostAddress() : iaddress.getHostName();
                    }
                }
            }

            return InetAddress.getLocalHost().getHostAddress() != null ? InetAddress.getLocalHost().getHostAddress() : InetAddress.getLocalHost().getHostName();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "127.0.0.1";
    }

    //判断是手机还是平板
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    public static void installApk(Context context, File file, String appPackage) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
/*        String path = file.getAbsolutePath();
        Uri downloadFileUri = Uri.parse("file://" + path);
        intent.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String fileprovider = appPackage + ".fileprovider";
            Uri apkUri = FileProvider.getUriForFile(context, fileprovider, file);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isAvilible(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }
}
