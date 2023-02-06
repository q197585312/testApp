package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;

import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BlockDialog;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.SharePreferenceUtil;

/**
 * Created by Administrator on 2016/10/12.
 */
public class EvoCasinoActivity extends BaseWebGameActivity {
    HttpClient httpClient;
    private String ip;

    private String getLanguage() {
        String lg = AppTool.getAppLanguage(mContext);
        if (TextUtils.isEmpty(lg)) {
            return "en";
        }
        switch (lg) {
            case "en":
                return "en";
            case "zh":
                return "cn";
            case "kr":
                return "kr";
            case "in":
                return "id";
            case "th":
                return "th";
            case "vn":
                return "vn";
            case "ms":
                return "ma";
            case "kh":
                return "ca";
            default:
                return "en";
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
        setTitle(getString(R.string.ag_live_entertainment));
        ip = SharePreferenceUtil.getString(mContext, "IP");
    }

    @Override
    public String getType2() {
        return "263";
    }

    @Override
    public void initGameData() {
        super.initGameData();
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        showBlockDialog();
        LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        String name = s.getUsername();
        String loginUrl = "https://evogaming.k-api.com/api/login.php?" + "&web_id=" + WebSiteUrl.WebId +
                "&language=" + getLanguage() + "&username=" + name + "&token=" + getUserInfoBean().getSession_id() + "&platfrom=mobile" + "&ip=" + ip;
        webView.loadUrl(loginUrl);
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
}
