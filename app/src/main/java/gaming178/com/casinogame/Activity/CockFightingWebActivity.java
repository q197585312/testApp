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

public class CockFightingWebActivity extends SlotsWebActivity {
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
                String dataUrl = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "sv338path.jsp";
                if (TextUtils.isEmpty(dataUrl) || mAppViewModel.getHttpClient() == null) {
                    return;
                }
                String result = mAppViewModel.getHttpClient().sendPost(dataUrl, "");
                //Results=ok#https://www.sv33888.com/api/player/gd88/login?cert=Q1ltduaIEwBwkoVn&extension1=g1234567&user=RAJA01&key=fnmKjjnmoCIuPEnzAVRM4gHdxhDWvNnA7gI66aFWy2U%3D&balance=12416.314&language=1
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
}
