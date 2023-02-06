package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.text.TextUtils;

import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.N2CasinoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2018/5/9.
 */

public class N2CasinoActivity extends BaseWebGameActivity {

    @Override
    public void initGameData() {
        super.initGameData();
        LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        String url = "http://n2live.k-api.com/api/login.php?web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername() +
                "&token=" + getUserInfoBean().getSession_id() + "&language=" + getLanguage() + "&platfrom=Mobile";
        showBlockDialog();
        HttpUtils.httpGet(url, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                if (s.contains("No Error")) {
                    N2CasinoBean n2CasinoBean = gson.fromJson(s, N2CasinoBean.class);
                    webView.loadUrl(n2CasinoBean.getLoginURL());
                } else {
                    dismissBlockDialog();
                }
            }

            @Override
            public void onRequestFailed(String s) {
                dismissBlockDialog();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setTitle(getString(R.string.n2_live_entertainment));
    }

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
                return "kh";
            default:
                return "en";
        }
    }

    @Override
    public String getType2() {
        return "345";
    }
}
