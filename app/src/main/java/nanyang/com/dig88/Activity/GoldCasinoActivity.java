package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.Encrypt;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class GoldCasinoActivity extends BaseWebGameActivity {
    @BindView(R.id.web_left_back)
    ImageView exitImgl;
    HttpClient httpClient;
    String requestUrl = "http://gd.digapi.net/main.php?";
    String session_token;
    private Thread CheckOrCreateAccThread = null;
    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 1) {
                webView.loadUrl((String) msg.obj);
            } else if (msg.what == 2) {
                Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };

    private void isGoBack() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.leftClick();
    }

    @Override
    protected void leftClick() {
        isGoBack();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置窗口风格为进度条
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        setTitle(getString(R.string.gold_live_entertainment));
        exitImgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoBack();
            }
        });
    }

    @Override
    public void initGameData() {
        super.initGameData();
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        showBlockDialog();
        CheckOrCreateAcc getSportToken = new CheckOrCreateAcc();
        CheckOrCreateAccThread = new Thread(getSportToken);
        CheckOrCreateAccThread.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            isGoBack();
            return true;
        }
        return false;
    }

    @Override
    public void onLoadFinish() {
        super.onLoadFinish();
        if (exitImgl != null) {
            exitImgl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public String getType2() {
        return "91";
    }

    public class CheckOrCreateAcc implements Runnable {
        @Override
        public void run() {
            String currency = getCurrency();
            String lg = AppTool.getAppLanguage(mContext);
            String lang = "EN";
            if (lg.equals("zh")) {
                lang = "ZH-CN";
            }
            LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
            String name = s.getUsername();
            session_token = getUserInfoBean().getSession_id();
            String playerid = WebSiteUrl.WebId + "s" + name;
            requestUrl += "&OperatorCode=AFB88&PlayerGroup=default&mobile=2&view=N";
            requestUrl += "&lang=" + lang;
            requestUrl += "&playerid=" + playerid;
            requestUrl += "&LoginTokenID=" + session_token;
            requestUrl += "&Currency=" + currency;
            Encrypt encrypt = new Encrypt();
            requestUrl += "&Key=" + encrypt.SHA256("AFB88" + session_token + "b5da5bd5-ab1c-43a0-bc09-b3a1609a70b6" + playerid + currency);
            requestUrl += "&nickname=" + name;
            handler.sendMessage(handler.obtainMessage(1, requestUrl));
        }
    }
}
