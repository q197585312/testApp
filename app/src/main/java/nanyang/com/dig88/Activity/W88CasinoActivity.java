package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class W88CasinoActivity extends BaseWebGameActivity {
    HttpClient httpClient;
    LoginInfoBean s;

    //语言的值 ca en cn kr id ma th vn
    private String getLangurge() {
        String lg = AppTool.getAppLanguage(mContext);
        if (TextUtils.isEmpty(lg)) {
            return "en-us";
        } else {
            switch (lg) {
                case "vn":
                    return "vi-vn";
                case "zh":
                    return "zh-cn";
                case "kr":
                    return "ko-kr";
                case "in":
                    return "id-id";
                case "my":
                    return "en-us";
                case "th":
                    return "th-th";
                case "kh":
                    return "km-kh";
                case "zh_TW":
                    return "tw";
                default:
                    return "en-us";
            }
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        setTitle(getString(R.string.w88_live_entertainment));
    }

    @Override
    public void initGameData() {
        super.initGameData();
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        showBlockDialog();
        String loadUrl = "https://casino.globalintgames.com/html5/mobile/?op=DIG88&lang=" + getLangurge() + "&m=normal&token=" + getUserInfoBean().getSession_id() + "&sys=CUSTOM";
        webView.loadUrl(loadUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            goBack();
            return true;
        }
        return false;
    }

    private void goBack() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            webView.destroy();
            ViewGroup view = (ViewGroup) getWindow().getDecorView();
            view.removeAllViews();
            finish();
        }
    }

    @Override
    public String getType2() {
        return "90";
    }
}

