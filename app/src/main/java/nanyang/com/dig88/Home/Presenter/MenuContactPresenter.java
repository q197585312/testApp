package nanyang.com.dig88.Home.Presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.BankAccountDetailBean;
import nanyang.com.dig88.Entity.ContactBean;
import nanyang.com.dig88.Home.MenuContactFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.GameMenuItem;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/6/28.
 */

public class MenuContactPresenter extends BaseRetrofitPresenter<MenuContactFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    MenuContactFragment menuContactFragment;

    public MenuContactPresenter(MenuContactFragment iBaseContext) {
        super(iBaseContext);
        menuContactFragment = iBaseContext;
    }

    public void initWebView(String url, WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public void getContactData() {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("lang", getLanguage());
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.ContactUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) {
                List<ContactBean.DataBean> list = new ArrayList<>();
                if (BuildConfig.FLAVOR.equals("ibet567")) {
                    ContactBean.DataBean dataBean = new ContactBean.DataBean();
                    dataBean.setColor("#C13E46");
                    dataBean.setChatType("Affiliate Signup");
                    dataBean.setPiclogo(null);
                    list.add(dataBean);
                }
                if (data.contains("\"msg\":\"1\"")) {
                    ContactBean contactBean = gson.fromJson(data, ContactBean.class);
                    list.addAll(contactBean.getData());
                }
                menuContactFragment.onGetContactData(list);
            }
        });
    }

    public String getWebViewLiveChatUrl() {
        String url = "https://secure.livechatinc.com/licence/9273760/v2/open_chat.cgi?";
        if (BuildConfig.FLAVOR.equals("kbet3")) {
            url = "https://vm.providesupport.com/1kqp3ljshfzaw0j5vutu2dqz3g";
        }
        return url;
    }

    public String getLanguage() {
        return Dig88Utils.getLanguage(menuContactFragment.getContext());
    }
}
