package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;

import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class SsportsActivity extends BaseWebGameActivity {
    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置窗口风格为进度条
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        setTitle(getString(R.string.s_sport_betting));
    }

    @Override
    public void initGameData() {
        super.initGameData();
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        showBlockDialog();
        webView.loadUrl(getGameUrl());
    }

    private String getGameUrl() {
        LoginInfoBean logininfobean = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
//        gametype : 1 - sports, 2 - virtual sports
        String gameType = "1";
        String url = "http://sboapi.khmergaming.com/api/login.php?web_id=" + WebSiteUrl.WebId + "&username=" + logininfobean.getUsername() +
                "&token=" + getUserInfoBean().getSession_id() + "&language=" + Dig88Utils.getLanguage(mContext) + "&platfrom=m&gameType=" + gameType;
        return url;
    }

    private void isGoBack() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            ViewGroup view = (ViewGroup) getWindow().getDecorView();
            view.removeAllViews();
            super.leftClick();
        }

    }

    @Override
    protected void leftClick() {
        isGoBack();
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
    public String getType2() {
        return "100";
    }
}
