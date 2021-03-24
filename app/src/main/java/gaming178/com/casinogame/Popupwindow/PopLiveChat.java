package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.net.http.SslError;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import gaming178.com.baccaratgame.BuildConfig;

/**
 * Created by Administrator on 2020/5/21.
 */

public class PopLiveChat extends BasePopupWindow {
    private WebView webView;
    private String url = "";
    BaseActivity activity;
    private ImageView img_exit;
    private TextView gd_tv_pop_title;

    public PopLiveChat(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_report;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        img_exit = view.findViewById(R.id.gd__img_exit);
        gd_tv_pop_title = view.findViewById(R.id.gd_tv_pop_title);
        gd_tv_pop_title.setText(context.getString(R.string.Live_Chat));
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        activity = (BaseActivity) context;
        if (BuildConfig.FLAVOR.equals("mainkasino")) {
            url = "https://secure.livechatinc.com/licence/11168507/v2/open_chat.cgi?license=11168507";
        } else if (BuildConfig.FLAVOR.equals("ratucasino88")) {
            url = "https://direct.lc.chat/11977407/1";
        } else if (BuildConfig.FLAVOR.equals("depocasino")) {
            url = "https://tawk.to/chat/5a9936514b401e45400d5958/default";
        } else if (BuildConfig.FLAVOR.equals("ularnaga")) {
            url = "https://tawk.to/chat/5a9900284b401e45400d57fa/default";
        } else if (BuildConfig.FLAVOR.equals("oricasino")) {
            url = "https://tawk.to/chat/5f5216ebf0e7167d000d68e1/default";
        } else if (BuildConfig.FLAVOR.equals("wargacasino")) {
            url = "https://direct.lc.chat/11150507/3";
        } else if (BuildConfig.FLAVOR.equals("rajabakarat")) {
            url = "https://direct.lc.chat/8846269/";
        } else if (BuildConfig.FLAVOR.equals("idrkasino")) {
            url = "https://direct.lc.chat/8863379/";
        } else if (BuildConfig.FLAVOR.equals("rajacasino")) {
            url = "https://direct.lc.chat/8843331/";
        } else if (BuildConfig.FLAVOR.equals("memoricasino")) {
            url = "https://direct.lc.chat/11077682/";
        } else if (BuildConfig.FLAVOR.equals("indkasino")) {
            url = "https://direct.lc.chat/8837176/";
        } else if (BuildConfig.FLAVOR.equals("rolet303")) {
            url = "https://direct.lc.chat/12113850/";
        } else if (BuildConfig.FLAVOR.equals("casino288")) {
            url = "https://direct.lc.chat/12114489/";
        } else if (BuildConfig.FLAVOR.equals("casino188")) {
            url = "https://direct.lc.chat/10472107/";
        } else if (BuildConfig.FLAVOR.equals("ezykasino")) {
            url = "https://direct.lc.chat/10963232/";
        } else if (BuildConfig.FLAVOR.equals("ahlicasino")) {
            url = "https://direct.lc.chat/8667886/";
        } else if (BuildConfig.FLAVOR.equals("cahaya")) {
            url = "https://direct.lc.chat/8666201/";
        } else if (BuildConfig.FLAVOR.equals("palapacasino")) {
            url = "https://static.zdassets.com/web_widget/latest/liveChat.html?v=10#key=palapacasino.zendesk.com&settings=JTdCJTIyd2ViV2lkZ2V0JTIyJTNBJTdCJTIyY2hhdCUyMiUzQSU3QiUyMnRpdGxlJTIyJTNBbnVsbCUyQyUyMm1lbnVPcHRpb25zJTIyJTNBJTdCJTIyZW1haWxUcmFuc2NyaXB0JTIyJTNBdHJ1ZSU3RCUyQyUyMmRlcGFydG1lbnRzJTIyJTNBJTdCJTdEJTJDJTIycHJlY2hhdEZvcm0lMjIlM0ElN0IlMjJkZXBhcnRtZW50TGFiZWwlMjIlM0FudWxsJTJDJTIyZ3JlZXRpbmclMjIlM0FudWxsJTdEJTJDJTIyb2ZmbGluZUZvcm0lMjIlM0ElN0IlMjJncmVldGluZyUyMiUzQW51bGwlN0QlMkMlMjJjb25jaWVyZ2UlMjIlM0ElN0IlMjJhdmF0YXJQYXRoJTIyJTNBbnVsbCUyQyUyMm5hbWUlMjIlM0FudWxsJTJDJTIydGl0bGUlMjIlM0FudWxsJTdEJTdEJTJDJTIyY29sb3IlMjIlM0ElN0IlMjJhcnRpY2xlTGlua3MlMjIlM0ElMjIlMjIlMkMlMjJidXR0b24lMjIlM0ElMjIlMjIlMkMlMjJoZWFkZXIlMjIlM0ElMjIlMjIlMkMlMjJsYXVuY2hlciUyMiUzQSUyMiUyMiUyQyUyMmxhdW5jaGVyVGV4dCUyMiUzQSUyMiUyMiUyQyUyMnJlc3VsdExpc3RzJTIyJTNBJTIyJTIyJTJDJTIydGhlbWUlMjIlM0FudWxsJTdEJTdEJTdE&&locale=en-US&title=Web%20Widget%20Live%20Chat";
        } else if (BuildConfig.FLAVOR.equals("juragan")) {
            url = "https://v2.zopim.com/widget/livechat.html?api_calls=%5B%5D&hostname=juragancasino.com&key=4oMgYnkcPZzXN2cPvN0HIsEcH2GHoW5q&lang=ms&";
        } else if (BuildConfig.FLAVOR.equals("pelangi")) {
            url = "https://v2.zopim.com/widget/livechat.html?api_calls=%5B%5D&hostname=pelangicasino.com&key=4oMf9O1QEnhbeGTrma2XYssqj68yXRoA&lang=id&";
        } else if (BuildConfig.FLAVOR.equals("hokicasino88")) {
            url = "https://tawk.to/chat/5d7f4025c22bdd393bb602c1/default";
        } else if (BuildConfig.FLAVOR.equals("doacasino")) {
            url = "https://tawk.to/chat/5cefa859267b2e5785302124/default";
        } else if (BuildConfig.FLAVOR.equals("mandiricasino")) {
            url = "https://direct.lc.chat/10687487/";
        } else if (BuildConfig.FLAVOR.equals("nagacasino")) {
            url = "https://direct.lc.chat/10960092/";
        } else if (BuildConfig.FLAVOR.equals("pemain")) {
            url = "https://direct.lc.chat/8668396/";
        } else if (BuildConfig.FLAVOR.equals("serbacasino")) {
            url = "https://direct.lc.chat/8951849/";
        } else if (BuildConfig.FLAVOR.equals("dkicasino")) {
            url = "https://direct.lc.chat/10861152/";
        } else if (BuildConfig.FLAVOR.equals("w99casino")) {
            url = "https://direct.lc.chat/9637025/";
        } else if (BuildConfig.FLAVOR.equals("hobi")) {
            url = "https://direct.lc.chat/8668406/";
        } else if (BuildConfig.FLAVOR.equals("istanacasino")) {
            url = "https://direct.lc.chat/12444219/";
        } else if (BuildConfig.FLAVOR.equals("livecasino338")) {
            url = "http://bandarq.cc/livecasino338/";
        } else if (BuildConfig.FLAVOR.equals("casino388")) {
            url = "https://direct.lc.chat/8655841/";
        } else if (BuildConfig.FLAVOR.equals("rentalbaccarat")) {
            url = "https://tawk.to/chat/5e3bee1da89cda5a188478c8/default";
        } else if (BuildConfig.FLAVOR.equals("marina118")) {
            url = "https://direct.lc.chat/8631744/";
        } else if (BuildConfig.FLAVOR.equals("winnicasino")) {
            url = "https://direct.lc.chat/11224587/";
        } else if (BuildConfig.FLAVOR.equals("idolcasino")) {
            url = "https://direct.lc.chat/8675176/1";
        }

        webView = (WebView) view.findViewById(R.id.gd__wv_report);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
        webView.getSettings().setDomStorageEnabled(true);//DOM Storage
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed();//接受证书
                //handleMessage(Message msg); 其他处理
            }
        });
        webView.loadUrl(url);
    }
}
