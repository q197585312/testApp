package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;

import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.SharePreferenceUtil;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class SexyCasinoActivity extends BaseWebGameActivity {
    HttpClient httpClient;
    String loadUrl = "https://newsexy.k-api.com/api/login.php?";
    LoginInfoBean s;

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
                    return "ca";
                case "zh_TW":
                    return "tw";
                default:
                    return "en";
            }
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        setTitle(getString(R.string.sexy_live_entertainment));
    }

    @Override
    public void initGameData() {
        super.initGameData();
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        showBlockDialog();
        //https://newsexy.k-api.com/api/login.php?token=&platform=mobile&language=&web_id=&username=&ip=&gametype=0
        String ip = SharePreferenceUtil.getString(mContext, "IP");
        String params = "token=" + getUserInfoBean().getSession_id() + "&language=" + getLangurge() + "&platform=mobile" +
                "&web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername() + "&gametype=0" + "&ip=" + ip;
        webView.loadUrl(loadUrl + params);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            webView.destroy();
            handler = null;
            finish();
            return true;
        }
        return false;
    }

    @Override
    public String getType2() {
        return "208";
    }
}

