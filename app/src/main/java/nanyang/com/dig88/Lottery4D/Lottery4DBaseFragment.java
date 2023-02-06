package nanyang.com.dig88.Lottery4D;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.Lottery4D.Bean.PeriodNumberBean;
import nanyang.com.dig88.Lottery4D.Bean.WeekDay;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2018/11/16.
 */

public abstract class Lottery4DBaseFragment extends BaseFragment {
    public String userId;
    public String sessionId;
    public Gson gson;
    public WebView webView;
    public Lottery4DActivity lottery4DActivity;
    public List<WeekDay> currentTypeWeekDayList;
    HttpClient httpClient;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        gson = new Gson();
        httpClient = new HttpClient("");
        userId = getUserInfo().getUser_id();
        sessionId = getUserInfo().getSession_id();
        lottery4DActivity = (Lottery4DActivity) getActivity();
    }

    public void toastMakeText(String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }

    public void initWebView() {
        if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setAllowFileAccess(true);// 设置允许访问文件数据
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setDatabaseEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }

    public String getLang() {
        String lang = "en";
        String localLanguage = getAct().getLocalLanguage();
        if (!TextUtils.isEmpty(localLanguage)) {
            switch (localLanguage) {
                case "zh":
                    lang = "cn";
                    break;
                case "en":
                    lang = "en";
                    break;
                case "kh":
                    lang = "kh";
                    break;
                case "kr":
                    lang = "kr";
                    break;
                case "my":
                    lang = "ma";
                    break;
                case "th":
                    lang = "th";
                    break;
                case "vn":
                    lang = "vn";
                    break;
                case "in":
                    lang = "id";
                    break;
            }
        } else {
            lang = "en";
        }
        return lang;
    }
}
