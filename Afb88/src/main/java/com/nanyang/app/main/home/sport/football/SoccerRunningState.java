package com.nanyang.app.main.home.sport.football;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
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
        BasePopupWindow pop = new BasePopupWindow(getBaseView().getContextActivity(), v, LinearLayout.LayoutParams.MATCH_PARENT,800) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_web_layout;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                WebView webView = (WebView) view.findViewById(R.id.web_wv);

                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setSupportZoom(true);          //支持缩放
                webView.getSettings().setBuiltInZoomControls(true);  //启用内置缩放装置
                //加载需要显示的网页
                String lag = AfbUtils.getLanguage(context);
                String l = "eng";
                if (lag.equals("zh")) {
                    l = "ZH-CN";
                }

                String gameUrl = AppConstant.URL_RUNNING_MATCH_WEB + "?Id=" + item.getRTSMatchId() + "&Home=" + StringUtils.URLEncode(item.getHome()) + "&Away=" + StringUtils.URLEncode(item.getAway()) + "&L=" + l;
                synCookies(gameUrl,webView);
                webView.loadUrl(gameUrl);
                Log.d("OkHttp", gameUrl);
                webView.setWebViewClient(new WebViewClient());
            }
        };
        getBaseView().onPopupWindowCreated(pop, Gravity.CENTER);
    }

    public void synCookies(String url,WebView v) {
        CookieSyncManager.createInstance(getBaseView().getContextActivity());
        CookieManager mCookieManager = CookieManager.getInstance();
        mCookieManager.setAcceptCookie(true);
        // 每次移除会有Cookie不一致问题，注释该地方
        //mCookieManager.removeSessionCookie();// 移除
        // Cookie是通过我们Volley活着HttpClient获取的
        //  Log.i(WebSiteUrl.Tag,"cookie="+afbApp.getHttpClient().getCookie());
        String cookie = CookieManger.getCookieStore().getCookies().get(0).value();
        mCookieManager.setCookie(url,cookie);
        CookieSyncManager.getInstance().sync();
        mCookieManager.acceptThirdPartyCookies(v);
    }
}
