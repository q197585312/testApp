package com.nanyang.app.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.nanyang.app.AfbApplication;

/**
 * Created by Administrator on 2019/4/26.
 */

public class MyGoHomeBroadcastReceiver extends BroadcastReceiver {
    String SYSTEM_REASON = "reason";
    String SYSTEM_HOME_KEY = "homekey";
    String SYSTEM_HOME_KEY_LONG = "recentapps";
    AfbApplication afbApplication;

    public MyGoHomeBroadcastReceiver(AfbApplication afbApplication) {
        this.afbApplication = afbApplication;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            String reason = intent.getStringExtra(SYSTEM_REASON);
            if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
//表示按了home键,程序到了后台
                afbApplication.setGoHome(true);
                Log.d("shangpeisheng", "isGoHome: " + afbApplication.isGoHome());
            } else if (TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)) {
//表示长按home键,显示最近使用的程序列表
            }
        }
    }

}
