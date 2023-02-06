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

/**
 * Created by Administrator on 2018/9/28.
 */

public class LG88WebActivity extends SlotsWebActivity {
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
        new Thread() {
            @Override
            public void run() {
                String dataUrl = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "lg88path.jsp";
                if (TextUtils.isEmpty(dataUrl) || mAppViewModel.getHttpClient() == null) {
                    return;
                }
                String result = mAppViewModel.getHttpClient().sendPost(dataUrl, "deviceType=1");
                //Results=ok#http://54.251.83.21:8090?t=338673310d5ea04a16082d60c3204d831b09&u=lg88_TS1AGD88-RAJA01&l=en
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
