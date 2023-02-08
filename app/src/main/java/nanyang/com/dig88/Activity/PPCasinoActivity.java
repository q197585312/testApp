package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.text.TextUtils;

import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2018/5/9.
 */

public class PPCasinoActivity extends BaseWebGameActivity {

    @Override
    public void initGameData() {
        super.initGameData();
        LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        String url = "https://ppslots.k-api.com/api/login.php?web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername() +
                "&token=" + getUserInfoBean().getSession_id() + "&language=" + getLanguage() + "&game_id=101&platfrom=Mobile";
        showBlockDialog();
        webView.loadUrl(url);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setTitle(getString(R.string.pp_live_entertainment));
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
        return "371";
    }
}
