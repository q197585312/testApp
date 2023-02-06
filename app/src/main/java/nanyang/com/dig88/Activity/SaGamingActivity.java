package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.text.TextUtils;

import nanyang.com.dig88.R;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2018/5/9.
 */

public class SaGamingActivity extends BaseWebGameActivity {

    @Override
    public void initGameData() {
        super.initGameData();
        String url = "https://sagming.khmergaming.com/api/login.php?token=" + getUserInfoBean().getSession_id() +
                "&language=" + getLanguage() + "&loginFrom=1";
        showBlockDialog();
        webView.loadUrl(url);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setTitle(getString(R.string.sa_live_entertainment));
    }

    private String getLanguage() {
        String lg = AppTool.getAppLanguage(mContext);
        if (TextUtils.isEmpty(lg)) {
            return "en";
        } else {
            switch (lg) {
                case "en":
                    return "en";
                case "th":
                    return "th";
                case "vn":
                    return "vn";
                case "zh":
                    return "cn";
                case "in":
                    return "id";
                case "kr":
                    return "kr";
                case "my":
                    return "ma";
                case "kh":
                    return "ca";
            }
        }
        return "en";
    }

    @Override
    public String getType2() {
        return "188";
    }
}
