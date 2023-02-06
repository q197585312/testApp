package nanyang.com.dig88.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.ImageView;

import butterknife.Bind;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class WMCasinoActivity extends BaseWebGameActivity {
    HttpClient httpClient;
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
                    return "cn";
                default:
                    return "en";
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        toolbar.setVisibility(View.GONE);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        AppTool.setAppLanguage(mContext, "");
    }

    @Override
    public void initGameData() {
        super.initGameData();
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        showBlockDialog();
        String loadUrl = "http://wmapi.k-api.com/api/login.php?";
        String params = "web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername() + "&token=" + getUserInfoBean().getSession_id() + "&language=" + getLangurge();
        webView.loadUrl(loadUrl + params);
    }

    @Override
    public void webViewSetting() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);// 设置允许访问文件数据
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
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
        return "247";
    }
}

