package com.nanyang.app.main.home.sport.football;

import android.net.http.SslError;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.api.CookieManger;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerRunningState extends SoccerCommonState {
    public SoccerRunningState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_FOOTBALL_RUNNING + param.getType();
    }

    @Override
    protected String getAllOddsUrl() {
        return AppConstant.HOST + "_view/OddsPageSetting.aspx?ot=r&ov=0&wd=&tf=-1&isPageSingDouble=RMOdds1&m=save";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new SoccerEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new SoccerTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new SoccerOutRightState(getBaseView()));
                break;
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Running), "Running", getBaseView().getContextActivity().getString(R.string.football));
    }


    @Override
    protected SoccerCommonAdapterHelper onSetCommonAdapterHelper() {
        return new SoccerRunningAdapterHelper(getBaseView().getContextActivity());
    }

    @Override
    protected IBetHelper onSetBetHelper() {
        return new SoccerRunningBetHelper(getBaseView());
    }

    protected void clickHallBtn(View v, final SoccerCommonInfo item) {
        BasePopupWindow pop = new BasePopupWindow(getBaseView().getContextActivity(), v, LinearLayout.LayoutParams.MATCH_PARENT, 1300) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_web_layout;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                WebView webView = (WebView) view.findViewById(R.id.web_wv);

//                webView.getSettings().setJavaScriptEnabled(true);
//                webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        /*        webView.getSettings().setAllowFileAccess(true);// 设置允许访问文件数据
                webView.getSettings().setSupportZoom(true);
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                webView.getSettings().setDomStorageEnabled(true);
                webView.getSettings().setDatabaseEnabled(true);

//自适应屏幕
                webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
                webView.getSettings().setLoadWithOverviewMode(true);*/


                //加载需要显示的网页
                //http://main55.afb88.com/_view/LiveCast.aspx?Id=807186&Home=Heidelberg%20United&Away=Hume%20City&L=EN-US
                String lag = AfbUtils.getLanguage(context);
                String l = "eng";
                if (lag.equals("zh")) {
                    l = "eng";
                }else {
                    l="EN-US";
                }

                String gameUrl = AppConstant.URL_RUNNING_MATCH_WEB + "?Id=" + item.getRTSMatchId() + "&Home=" + StringUtils.URLEncode(item.getHome()) + "&Away=" + StringUtils.URLEncode(item.getAway()) + "&L=" + l;
                synCookies(gameUrl);
                webView.loadUrl(gameUrl);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                    }

                    @Override
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                        super.onReceivedError(view, errorCode, description, failingUrl);

                    }

                    @Override
                    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                        handler.proceed();//接受证书

                    }
                });


//                webView.setWebViewClient(new WebViewClient());
            }
        };
        getBaseView().onPopupWindowCreated(pop, Gravity.CENTER);
    }

    public void synCookies(String url) {
        String cookie = "";
        cookie = CookieManger.getCookieStore().get(url).get(0).toString();
        AfbUtils.synCookies(getBaseView().getContextActivity(), url, cookie);
    }
}
