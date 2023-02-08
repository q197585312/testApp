package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2018/1/10.
 */

public class CockfightActivity extends BaseWebGameActivity {

    private LoginInfoBean s;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        setTitle(getString(R.string.Cockfight));
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void initGameData() {
        super.initGameData();
        showBlockDialog();
        webView.loadUrl(getGameLoadUrl());
    }

    private String getGameLoadUrl() {
        String url = "https://sv388.k-api.com/api/login.php?web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername() +
                "&language=" + getLoginLanguage() + "&platform=Mobile&token=" + getUserInfoBean().getSession_id();
        return url;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    public String getType2() {
        return "138";
    }

    private String getLoginLanguage() {
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
            case "ma":
                return "ma";
            case "kh":
                return "ca";
            default:
                return "en";
        }
    }
}
