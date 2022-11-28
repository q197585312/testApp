package gaming178.com.casinogame.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2018/4/18.
 */

public class PragmaticCasinoActivity extends SlotsWebActivity {
    String lang;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (isAttached) {
                if (msg.what == 1) {
                    load();
                } else {
                    Toast.makeText(mContext, "Login error", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    };

    @Override
    public void finish() {
        if (webView != null)
            webView.destroy();
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        img_exit.setVisibility(View.GONE);
        String appLanguage = AppTool.getAppLanguage(mContext);
        if (appLanguage.equals("my")) {
            lang = "id";
        } else {
            lang = "en";
        }
        new Thread() {
            @Override
            public void run() {
                String dataUrl = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "ppcasinopath.jsp";
                if (TextUtils.isEmpty(dataUrl) || mAppViewModel.getHttpClient() == null) {
                    return;
                }
                String result = mAppViewModel.getHttpClient().sendPost(dataUrl, "lang=" + lang);
                if (result.startsWith("Results=ok")) {
                    String[] split = result.split("#");
                    url = split[1];
                    handler.sendEmptyMessage(1);
                } else {
                    handler.sendEmptyMessage(2);
                }
            }
        }.start();
    }

    @Override
    public void goBack() {
        leftClick();
    }

    @Override
    protected void leftClick() {
        skipAct(LobbyActivity.class);
        finish();
    }
}
