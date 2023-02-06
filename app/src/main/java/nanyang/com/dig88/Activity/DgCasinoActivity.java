package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.google.gson.Gson;

import java.io.IOException;

import nanyang.com.dig88.Entity.Dg99Bean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class DgCasinoActivity extends BaseWebGameActivity {
    HttpClient httpClient;
    String dg99LoadUrl = "http://gd99api.dig88api.com/api/login.php?";
    LoginInfoBean s;
    private Thread CheckOrCreateAccThread = null;
    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            if (!isAttached)
                return;
            super.dispatchMessage(msg);
            if (msg.what == 1) {
                webView.loadUrl((String) msg.obj);
            }
        }
    };
    private boolean isDestroy = false;

    //语言的值 ca en cn kr id ma th vn
    private String getLangurge() {
        String lg = AppTool.getAppLanguage(mContext);
        if (TextUtils.isEmpty(lg)) {
            return "en";
        } else {
            switch (lg) {
                case "vn":
                    return "vn";
                case "zh":
                    return "cn";
                case "kr":
                    return "kr";
                case "in":
                    return "id";
                case "my":
                    return "ma";
                case "th":
                    return "th";
                case "kh":
                    return "en";
                case "zh_TW":
                    return "tw";
                default:
                    return "en";
            }
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        setTitle(getString(R.string.dg_live_entertainment));
    }

    @Override
    public void initGameData() {
        super.initGameData();
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        showBlockDialog();
        CheckOrCreateAcc getSportToken = new CheckOrCreateAcc();
        CheckOrCreateAccThread = new Thread(getSportToken);
        CheckOrCreateAccThread.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            webView.destroy();
            isDestroy = true;
            handler = null;
            finish();
            return true;
        }
        return false;
    }

    @Override
    public String getType2() {
        return "136";
    }

    public class CheckOrCreateAcc implements Runnable {
        @Override
        public void run() {
            try {
                String params = "web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername() +
                        "&sess_id=" + getUserInfoBean().getSession_id() + "&deviceType=2" +
                        "&language=" + getLangurge();
                String result = httpClient.sendPost(dg99LoadUrl + params, "");
                Gson g = new Gson();
                Dg99Bean b = g.fromJson(result, Dg99Bean.class);
                if (!isDestroy) {
                    handler.sendMessage(handler.obtainMessage(1, b.getLink()));
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}

