package gaming178.com.casinogame.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import gaming178.com.casinogame.Util.WebSiteUrl;

/**
 * Created by Administrator on 2018/9/28.
 */

public class DsvCasinoActivity extends SlotsWebActivity {
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
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        img_exit.setVisibility(View.GONE);
        new Thread() {
            @Override
            public void run() {
                String dataUrl = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "dnapath.jsp";
                if (TextUtils.isEmpty(dataUrl) || mAppViewModel.getHttpClient() == null) {
                    return;
                }
                String result = mAppViewModel.getHttpClient().sendPost(dataUrl, "gameplat=mobile");
                //Results=OK#http://dsv365.com/mobile/login.jsp?token=lhzTYkOVrY0Zi9Jz6GQozYLUhb5hdVq6#
                if (result.startsWith("Results=OK") || result.startsWith("Results=ok")) {
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
