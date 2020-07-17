package gaming178.com.casinogame.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.entity.AppVersionBean;
import gaming178.com.casinogame.Activity.entity.BalanceBean;
import gaming178.com.casinogame.Bean.GdOrGrAgentBean;
import gaming178.com.casinogame.Util.ActivityPageManager;
import gaming178.com.casinogame.Util.ErrorCode;
import gaming178.com.casinogame.Util.Gd88Utils;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.HttpClient;
import gaming178.com.casinogame.Util.LogIntervalUtils;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.casinogame.login.LoginActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.DecimalUtils;
import gaming178.com.mylibrary.allinone.util.MD5;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.allinone.util.ToastUtils;
import gaming178.com.mylibrary.allinone.util.UpdateManager;
import gaming178.com.mylibrary.base.RequestBean;
import gaming178.com.mylibrary.base.quick.QuickCookieThreadHandler;
import gaming178.com.mylibrary.base.quick.QuickRequestBean;

/**
 * Created by xToney on 2015/12/24.
 */
public class WelcomeActivity extends BaseActivity {
    private static final int LOGIN_ERROR_MSG = -13;


    FrameLayout flWelcome;
    LinearLayout llLogin;
    ImageView bgImg;

    long currentTime;
    private String updateUrl;
    private File loadFile;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        String firstLg;
        if (!BuildConfig.FLAVOR.isEmpty()&&!BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            firstLg = "my";
        } else {
            firstLg = "en";
        }
        if (Gd88Utils.isFirstIn(mContext)) {
            AppTool.setAppLanguage(mContext, firstLg);
        }
        LogIntervalUtils.logCustomTime(currentTime, "onCreate");
        ActivityPageManager.getInstance().addActivity(this);
        flWelcome = (FrameLayout) findViewById(R.id.fl_welcome);
        llLogin = (LinearLayout) findViewById(R.id.ll_login);
        bgImg = (ImageView) findViewById(R.id.welcome_img);
        if (BuildConfig.FLAVOR.isEmpty()||BuildConfig.FLAVOR.equals("gd88") || BuildConfig.FLAVOR.equals("liga365")) {
            bgImg.setImageResource(R.mipmap.gd88_welcome_logo);
        }else {
            bgImg.setImageResource(R.mipmap.title_logo);
        }
        flWelcome.setVisibility(View.VISIBLE);
        llLogin.setVisibility(View.GONE);
        mContext = this;
        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        if (it != null && bundle != null && !TextUtils.isEmpty(bundle.getString("web_id"))) {
            if (bundle.getString("web_id").equals("54")) {
                AppTool.setAppLanguage(WelcomeActivity.this, "zh");
            }
            bgImg.setVisibility(View.GONE);
        } else {
            bgImg.setVisibility(View.VISIBLE);
        }
        initWebsiteUrl();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_welcome_gd;
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogIntervalUtils.logCustomTime(currentTime, "onResume");
    }

    @Override
    public void startUpdateStatus() {
    }

    private void initWebsiteUrl() {
      /*  final String url = "http://www.grjl25.com/getDomainInform.jsp";
        final String param = "labelid=" + WebSiteUrl.Labelid;
        QuickCookieThreadHandler handler = new QuickCookieThreadHandler(this) {
            @Override
            protected QuickRequestBean getRequestBean() {
                return new QuickRequestBean(RequestBean.Method.GET, url + "?" + param, new HashMap(), new TypeToken<String>() {
                }.getType());
            }

            @Override
            public void successEnd(String result) {
                super.successEnd(result);
                Log.d("AppData", result);
                WebSiteUrl.setNormal(result);
//                WebSiteUrl.setNormal("http://113.130.125.213/");
                hasNewVersion();
            }
        };
        handler.startThread(null);*/
        httpClient = new HttpClient("");
        hasWebsiteChecked = false;
        handler.sendEmptyMessage(ErrorCode.GET_WEBSITE);

        hasNewVersion();

    }

    String userName;
    String password;

    HttpClient httpClient;
    String balance1;
    String lg;
    String createAccountUrl = "http://113.130.125.201/GDWebService?wsdl";
    String appKey = "uYGg6iOz1vfA8LXAizpYPoQ93nr6Ot";
    String urlHead = "mawar4d";

    private void initNewIntent(final String urlHost) {


        new Thread() {
            @Override
            public void run() {
                StringBuffer creatSb = new StringBuffer();
                creatSb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:maw=\"http://" + urlHead + "/\"><soapenv:Header/><soapenv:Body><maw:CreateAccount><!--Optional:--><strUsername>" + userName + "</strUsername><strPassword>" + password + "</strPassword><Api_key>" + appKey + "</Api_key></maw:CreateAccount></soapenv:Body></soapenv:Envelope>");
                String s = httpClient.sendPostSoap(createAccountUrl, creatSb.toString());
                if (s.contains("Success") || s.contains("Username already exist")) {
                    if (!TextUtils.isEmpty(balance1)) {
                        StringBuffer depositSb = new StringBuffer();
                        SimpleDateFormat dff = new SimpleDateFormat("MMddHHmmss");
                        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                        String dateStr = dff.format(new Date());
                        depositSb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:maw=\"http://" + urlHead + "/\"><soapenv:Header/><soapenv:Body><maw:memDeposit><!--Optional:--><strUsername>" + userName + "</strUsername><Api_key>" + appKey + "</Api_key><strPwd>" + password + "</strPwd><addCredit>" + balance1 + "</addCredit><sIP>" + AppTool.getLocalIP() + "</sIP><Serial>" + dateStr + "</Serial></maw:memDeposit></soapenv:Body></soapenv:Envelope>");
                        String depositStr = httpClient.sendPostSoap(createAccountUrl, depositSb.toString());
                    }
                    StringBuffer balanceSb = new StringBuffer();
                    balanceSb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:maw=\"http://" + urlHead + "/\"><soapenv:Header/><soapenv:Body><maw:getMemberBalance><!--Optional:--><strUsername>" + userName + "</strUsername><Api_key>" + appKey + "</Api_key></maw:getMemberBalance></soapenv:Body></soapenv:Envelope>");
                    String moneyStr = httpClient.sendPostSoap(createAccountUrl, balanceSb.toString()).replace("&quot;", "\"");
                    if (TextUtils.isEmpty(balance1)) {
                        int f1 = moneyStr.indexOf("<return>");
                        int e1 = moneyStr.indexOf("</return>");
                        if (e1 > f1) {
                            String str = moneyStr.substring(f1 + "<return>".length(), e1);
                            Gson g = new Gson();
                            BalanceBean balanceBean = g.fromJson(str, BalanceBean.class);
                            balance1 = balanceBean.getBalance() + "";
                        }
                    }
                    StringBuffer keySb = new StringBuffer();
                    keySb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:maw=\"http://" + urlHead + "/\"><soapenv:Header/><soapenv:Body><maw:getRandCode><!--Optional:--><Api_key>" + appKey + "</Api_key></maw:getRandCode></soapenv:Body></soapenv:Envelope>");
                    String keyData = httpClient.sendPostSoap(createAccountUrl, keySb.toString());
                    int f = keyData.indexOf("<return>");
                    int e = keyData.indexOf("</return>");
                    if (e > f) {
                        String key = keyData.substring(f + "<return>".length(), e);

                        String webUrL = "http://" + urlHost + "/cklogin.jsp";
                        String params = "txtAcctid=" + userName + "&txtPwd=" + password + "&txtLang=" + (lg.equals("zh") ? "0" : "1") + "&txtRandCode=" + key;

                        String resReturn = httpClient.sendPost(webUrL, params);
                        if (!resReturn.equals("netError")) {
                            WebSiteUrl.setDomain(1, urlHost);
                            String strRes = "";
                            switch (lg) {
                                case "zh":
                                    AppTool.setAppLanguage(WelcomeActivity.this, "zh");
                                    break;
                                case "zh_TW":
                                    AppTool.setAppLanguage(WelcomeActivity.this, "zh_TW");
                                    break;
                                default:
                                    AppTool.setAppLanguage(WelcomeActivity.this, "en");
                                    break;
                            }
                            mAppViewModel.getUser().setName(userName);
                            if (mAppViewModel.getCookie() == null || mAppViewModel.getCookie().equals("")) {
                                mAppViewModel.setHttpClient(new HttpClient(WebSiteUrl.INDEX, mAppViewModel.getCookie()));

                                if (mAppViewModel.getHttpClient().connect("POST") == false) {
                                    Log.i(WebSiteUrl.Tag, "HttpClient conn error");
                                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                                    return;
                                }
                                try {
                                    mAppViewModel.getHttpClient().getBodyString("UTF-8");
                                } catch (IOException e11) {
                                    e11.printStackTrace();
                                }
                                mAppViewModel.setCookie(mAppViewModel.getHttpClient().getSessionId());
                                //      Log.e(WebSiteUrl.Tag,"sessionid="+mAppViewModel.getHttpClient().getSessionId());
                            }

//
                            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LOGOUT_URL/*"http://96.9.71.29/DIGKorean/main.jsp"*/, "membername=" + mAppViewModel.getUser().getName() + "&lang=0");
                            //语言设定
                            loginInit(0, "", balance1);
                        } else {
                            handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
                        }
                    } else {
                        handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
                    }
                } else {
                    handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
                }
            }
        }.start();
    }

    public class ThreadLogin extends Thread {
        String us;
        String k;

        public ThreadLogin(String us, String k) {
            this.us = us;
            this.k = k;
        }

        @Override
        public void run() {

            HttpClient httpClient = new HttpClient("http://lapigd.afb333.com/Validate.aspx", "");
            //System.out.println(soapRequestData);
            String strPost = httpClient.sendPost("http://lapigd.afb333.com/Validate.aspx", "us=" + us + "&" + k);
            LogIntervalUtils.logCustomTime(currentTime, "验证完成开始解析");
//                <a href="http://23api.gd88.org/OLTGames/checklogin.jsp?txtAcctid=demoafbai5&amp;txtPwd=123456&amp;txtLang=0&amp;txtSessID=3udtldgt4tec45lpnpkwg1mf|a8c20c5281884defad37190a58eb1939">
            if (strPost.equals("netError")) {
                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                return;
            }

            if (strPost.contains("Negative balance or Balance = 0")) {
                handler.obtainMessage(LOGIN_ERROR_MSG, 0, 0, "Negative balance or Balance = 0 ! Please do transfer!").sendToTarget();
                return;
            }
            int i = strPost.indexOf("http://");
            int end = strPost.indexOf("\">here");

            String str1 = strPost.substring(i, end);


/*            if (mAppViewModel.getCookie() == null || mAppViewModel.getCookie().equals("")) {
                mAppViewModel.setHttpClient(new HttpClient(str1, mAppViewModel.getCookie()));

                if (mAppViewModel.getHttpClient().connect("POST") == false) {
                    android.util.Log.i(WebSiteUrl.Tag, "HttpClient conn error");
                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                    return;
                }
                mAppViewModel.getHttpClient().getBodyString("UTF-8");
                mAppViewModel.setCookie(mAppViewModel.getHttpClient().getSessionId());
                String userName=params.substring(params.indexOf("txtAcctid=")+10,params.indexOf("&txtPwd"));
                mAppViewModel.getUser().setName(userName);
                //      Log.e(WebSiteUrl.Tag,"sessionid="+mAppViewModel.getHttpClient().getSessionId());
            }

//
            strRess = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LOGOUT_URL, "");*/
            String url = str1.replaceAll("&amp;", "&");
            String loginUrl = url.substring(0, url.indexOf("?"));
            String params = url.substring(url.indexOf("?") + 1);

            WebSiteUrl.setOther("http://23api.gd88.org/", "OLTGames/");
            String strRes;
            mAppViewModel.setCookie("");
            mAppViewModel.setHttpClient(new HttpClient(WebSiteUrl.INDEX, mAppViewModel.getCookie()));

            if (mAppViewModel.getHttpClient().connect("POST") == false) {
                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                return;
            }
            try {
                strRes = mAppViewModel.getHttpClient().getBodyString("UTF-8");
                mAppViewModel.setCookie(mAppViewModel.getHttpClient().getSessionId());
            } catch (Exception e) {
                e.printStackTrace();

            }

            String loginParams = params;
            WebSiteUrl.LOGIN_URL = loginUrl;
            LogIntervalUtils.logCustomTime(currentTime, "解析完成开始登录" + WebSiteUrl.LOGIN_URL);
            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LOGIN_URL, loginParams);
            LogIntervalUtils.logCustomTime(currentTime, WebSiteUrl.LOGIN_URL + "登录完成开始解析游戏数据");
            mAppViewModel.getUser().setName(us);
/*            if(strRes.startsWith("Results=error"))//登录失败，没有失败原因
            {
                //	Log.e(WebSiteUrl.Tag,strRes);
                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_USERNAME);
                mAppViewModel.setCookie("");
                return ;
            }
            else if(!strRes.startsWith("Results=ok")){
                //	Log.e(WebSiteUrl.Tag,strRes);
                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                return ;
            }else if(strRes.startsWith("Results=ok")){
                String resultInfo[] = strRes.split("\\#");
                mAppViewModel.getUser().setCurrency(resultInfo[1]);

            }*/
            String language = "0";
            String annoucementParams = "lng=" + language + "&Usid=" + mAppViewModel.getUser().getName();
            LogIntervalUtils.logCustomTime(currentTime, "开始" + WebSiteUrl.ANNOUNCEMENT_URL);
            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.ANNOUNCEMENT_URL, annoucementParams);
            LogIntervalUtils.logCustomTime(currentTime, WebSiteUrl.ANNOUNCEMENT_URL + "完成");
            if (strRes.equals("netError")) {

                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                return;
            }
            String ann[] = strRes.split("Results=ok\\|");
            if (ann.length > 1)
                mAppViewModel.setAnnouncement(ann[1]);
            //	Log.i(WebSiteUrl.Tag,appData.getAnnouncement());
            LogIntervalUtils.logCustomTime(currentTime, "开始请求游戏Table数据" + WebSiteUrl.TABLEINFO_URL_A);
            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.TABLEINFO_URL_A, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
            LogIntervalUtils.logCustomTime(currentTime, WebSiteUrl.TABLEINFO_URL_A + "游戏Table数据完成，开始解析");
            if (strRes.equals("netError")) {

                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                return;
            }

            String tableInfo[] = strRes.split("\\^");
            if (tableInfo.length < 12) {
                handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
                return;
            }
            mAppViewModel.splitTableInfo(strRes, mAppViewModel.getHallId());
            LogIntervalUtils.logCustomTime(currentTime, "Table解析完成，开始请求" + WebSiteUrl.COUNTDOWN_URL_A);
            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.COUNTDOWN_URL_A, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
            LogIntervalUtils.logCustomTime(currentTime, WebSiteUrl.COUNTDOWN_URL_A + "完成，开始进入游戏");
            if (strRes.equals("netError")) {
                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                return;
            }
            mAppViewModel.splitTimer(strRes);
            mAppViewModel.setbLogin(true);
            mAppViewModel.setbLobby(true);
            WebSiteUrl.isDomain = true;
            handler.sendEmptyMessage(ErrorCode.LOGIN_SECCESS);
        }

    }

    private void hasAppIntent() {
        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        Log.d("AppData", it.toString());
        final int gameType = bundle.getInt("gameType", 0);
        LogIntervalUtils.logCustomTime(currentTime, "进入欢迎页开始解析数据");
        if (gameType == 3) {
            int homeColor = bundle.getInt("homeColor", 0xff0d5924);
            String language = bundle.getString("language");
            mAppViewModel.setHomeColor(homeColor);
            if (!StringUtils.isNull(language)) {
                switch (language) {
                    case "zh":
                        AppTool.setAppLanguage(WelcomeActivity.this, "zh");
                        break;
                    default:
                        AppTool.setAppLanguage(WelcomeActivity.this, "en");
                        break;
                }
            }
            final String webUrl = bundle.getString("webUrl");
            WebSiteUrl.GameType = gameType;
            if (StringUtils.isNull(webUrl)) {

                final String k = bundle.getString("k");
                final String us = bundle.getString("us");
//            WebSiteUrl.isDomain = false;
                ThreadLogin login = new ThreadLogin(us.trim(), k);
                login.start();
            } else {
                fromAfb1188(gameType);
            }

        } else if (gameType == 4) {
            Intent i = getIntent();
            userName = i.getStringExtra("userName").toUpperCase();
            password = i.getStringExtra("password");
            balance1 = i.getStringExtra("balance");
            lg = i.getStringExtra("language");
            String urlHost;
            if (TextUtils.isEmpty(balance1)) {
                appKey = "m9EgRehdQIVL1NsnRexI3aaWdxMOKc";
                urlHead = "zle999";
                urlHost = "202.178.114.15";
            } else {
                urlHost = "113.130.125.201";
                appKey = "uYGg6iOz1vfA8LXAizpYPoQ93nr6Ot";
                urlHead = "mawar4d";
            }
            createAccountUrl = "http://" + urlHost + "/GDWebService?wsdl";
            initNewIntent(urlHost);
        } else if (gameType == 5) {
            fromAfb1188(gameType);
        } else if (gameType == 6) {
            Intent i = getIntent();
            userName = i.getStringExtra("userName").toUpperCase();
            password = i.getStringExtra("password");
            balance1 = i.getStringExtra("balance");
            lg = i.getStringExtra("language");
            createAccountUrl = "http://113.130.125.182/GDWebService?wsdl";
            appKey = "YGzCaiFEYqhioSVz1FdDWTHUJX71DU";
            urlHead = "shiokambing3";
            initNewIntent("113.130.125.182");
        } else {
            Log.i(WebSiteUrl.Tag, "web_id=" + bundle.getString("web_id"));
            Log.i("AppData", "web_id=" + bundle.getString("web_id"));
            final int web_id = Integer.parseInt(bundle.getString("web_id"));
            final String currency = bundle.getString("currency");
            fromDig88(web_id, currency, gameType);
        }


    }

    private void fromAfb1188(int type) {
        //http://112-alias-api.gd88.org/cklogin.jsp?txtAcctid=TestSH20&txtPwd=12345678&txtLang=0&txtRandCode=qwtpbS8vRnYZedYdD1kWvnPwcAC1jijhVB90dQw1DpEJSG2Kvln
        //http://112-alias-api.gd88.org/select_tb_infoa.jsp
        //http://112-alias-api.gd88.org/GDWebService?wsdl
        WebSiteUrl.isDomain = true;
        LogIntervalUtils.logCustomTime(currentTime, "开始启动登录线程");
        if (type == 5)
            WebSiteUrl.setOther("http://112api.gd09.info/", "");
        else {
            WebSiteUrl.setOther("http://afb88.bpt88.net/", "OLTGames/");
        }
        new Thread() {
            @Override
            public void run() {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username").toUpperCase();//转成大写;
                String password = intent.getStringExtra("password");
                String language = intent.getStringExtra("language");
                String balance = intent.getStringExtra("balance");
                String webUrl = intent.getStringExtra("webUrl");
                String resReturn = httpClient.sendPost(webUrl, "");
                String strRes;
                mAppViewModel.setCookie("");
                LogIntervalUtils.logCustomTime(currentTime, "初始化cookie" + WebSiteUrl.INDEX);
                mAppViewModel.setHttpClient(new HttpClient(WebSiteUrl.INDEX, mAppViewModel.getCookie()));
                if (mAppViewModel.getHttpClient().connect("POST") == false) {
                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                    return;
                }
                try {
                    strRes = mAppViewModel.getHttpClient().getBodyString("UTF-8");
                    mAppViewModel.setCookie(mAppViewModel.getHttpClient().getSessionId());
                    LogIntervalUtils.logCustomTime(currentTime, "初始化cookie完成");
                } catch (Exception e) {
                    e.printStackTrace();

                }
                mAppViewModel.getUser().setName(username);
                String language1 = "0";
                String annoucementParams = "lng=" + language1 + "&Usid=" + mAppViewModel.getUser().getName();
                LogIntervalUtils.logCustomTime(currentTime, "开始" + WebSiteUrl.ANNOUNCEMENT_URL);
                strRes = httpClient.sendPost(WebSiteUrl.ANNOUNCEMENT_URL, annoucementParams);
                if (strRes.equals("netError")) {

                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                    return;
                }
                LogIntervalUtils.logCustomTime(currentTime, "" + WebSiteUrl.ANNOUNCEMENT_URL + "完成");
                String ann[] = strRes.split("Results=ok\\|");
                if (ann.length > 1)
                    mAppViewModel.setAnnouncement(ann[1]);
                //	Log.i(WebSiteUrl.Tag,appData.getAnnouncement());
                LogIntervalUtils.logCustomTime(currentTime, "开始Table数据" + WebSiteUrl.TABLEINFO_URL_A);
                strRes = httpClient.sendPost(WebSiteUrl.TABLEINFO_URL_A, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
                LogIntervalUtils.logCustomTime(currentTime, "" + WebSiteUrl.TABLEINFO_URL_A + "完成");
                if (strRes.equals("netError")) {
                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                    return;
                }
                String tableInfo[] = strRes.split("\\^");
                if (tableInfo.length < 12) {
                    handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
                    return;
                }
                mAppViewModel.splitTableInfo(strRes, mAppViewModel.getHallId());
                LogIntervalUtils.logCustomTime(currentTime, "开始" + WebSiteUrl.COUNTDOWN_URL_A);
                strRes = httpClient.sendPost(WebSiteUrl.COUNTDOWN_URL_A, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
                if (strRes.equals("netError")) {
                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                    return;
                }
                LogIntervalUtils.logCustomTime(currentTime, "" + WebSiteUrl.COUNTDOWN_URL_A + "完成");
                mAppViewModel.splitTimer(strRes);
                mAppViewModel.setbLogin(true);
                mAppViewModel.setbLobby(true);
                SimpleDateFormat dff = new SimpleDateFormat("MMddHHmmss");
                dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                String dateStr = dff.format(new Date());
                String sbUrl = "http://112-alias-api.gd88.org/player/afb1188/GD88WebService?wsdl";
                String sbParam = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:maw=\"http://gd88/\"><soapenv:Header/><soapenv:Body><maw:memDeposit><!--Optional:--><strUsername>" + username + "</strUsername><Api_key>" + "j0h93zNB7VDGn4TJEMbnm8WfpOuLMDwl" + "</Api_key><strPwd>" + password + "</strPwd><addCredit>" + balance + "</addCredit><sIP>" + AppTool.getLocalIP() + "</sIP><Serial>" + dateStr + "</Serial></maw:memDeposit></soapenv:Body></soapenv:Envelope>";
                LogIntervalUtils.logCustomTime(currentTime, "开始" + sbUrl);
                String depositStr = httpClient.sendPostSoap(sbUrl, sbParam);
                handler.sendEmptyMessage(ErrorCode.LOGIN_SECCESS);
                LogIntervalUtils.logCustomTime(currentTime, "" + sbUrl + "完成进入游戏------");
            }
        }.start();
    }

    private void fromDig88(int web_id, String currency, int gameType) {
        try {
            WebSiteUrl.setDomain(gameType, "113.130.125.201");
            getAgentName(gameType, currency, web_id);
        } catch (Exception e) {
            Log.d("AppData", e.getMessage());
            e.printStackTrace();
            Toast.makeText(WelcomeActivity.this, getResources().getString(R.string.login_data_error), Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
        }
    }

    private void initAgentName(Intent it) {
        Bundle bundle = it.getExtras();
        if (Intent.ACTION_VIEW.equals(it.getAction())) {
            Uri uri = it.getData();
            if (uri != null) {
                String host = uri.getHost();

                String language = uri.getQueryParameter("language");
                String balance = uri.getQueryParameter("balance");
                String username = uri.getQueryParameter("username");
                String web_id = uri.getQueryParameter("web_id");
                String game_type = uri.getQueryParameter("game_type");
                String currency = uri.getQueryParameter("currency");
                loginFromDig88(Integer.valueOf(game_type), Integer.valueOf(web_id), currency, username, balance, language);

            }
        } else {
            final int gameType = bundle.getInt("gameType", 0);
            final int web_id = Integer.parseInt(bundle.getString("web_id"));
            final String currency = bundle.getString("currency");
            final String username = bundle.getString("username");
            final String balance = bundle.getString("balance");
            final String language = bundle.getString("language");
            loginFromDig88(gameType, web_id, currency, username, balance, language);
        }

    }

    private void loginFromDig88(final int gameType, final int web_id, final String currency, final String username, final String balance, final String language) {
        try {
            if (agentName != null) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Map<String, String> patameterMap = new HashMap<String, String>();
                            Map<String, String> moneyMap = new HashMap<String, String>();

                            String userName = web_id + "t" + username;
                            String password = null;
                            patameterMap.put("strUsername", userName);
                            moneyMap.put("strUsername", userName);
                            moneyMap.put("addCredit", balance);
                            moneyMap.put("sIP", AppTool.getLocalIP());
                            SimpleDateFormat dff = new SimpleDateFormat("MMddHHmmss");
                            dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                            String dateStr = dff.format(new Date());
                            moneyMap.put("Serial", dateStr);

                            patameterMap.put("strAGName", agentName);
                            password = "DIG88" + username;
                            patameterMap.put("OperatorName", "dig88");
                            patameterMap.put("OperatorPwd", "GDC=>HL111888");
                            moneyMap.put("OperatorName", "dig88");
                            moneyMap.put("OperatorPwd", "GDC=>HL111888");
                            /*
                            if (gameType == 0) {

                            } else {
                                password = "DIGGD" + username;
                                patameterMap.put("Api_key", "WsCDEWQnKjzCYGpuWtFCMaEY");
                                moneyMap.put("Api_key", "WsCDEWQnKjzCYGpuWtFCMaEY");
                               if (web_id == 420) {
                                    password = "DIGKR" + username;
                                    patameterMap.put("OperatorName", "digk168");
                                    patameterMap.put("OperatorPwd", "y254tHnngIxyXtvA2u54");
                                } else {
                                }
                            }*/
                            password = MD5.md5(password);
                            patameterMap.put("strPassword", password);
                            moneyMap.put("strPwd", password);

                            String appWebServiceUrl = WebSiteUrl.AppWebServiceUrl;
                            String nameSpace = WebSiteUrl.AppWebServiceNameSpace;
                            HttpClient httpClient = new HttpClient(appWebServiceUrl, "");

                            String soapRequestData = httpClient.buildRequestData(patameterMap, nameSpace, "CreateMember");
                            String strPost = httpClient.sendPostSoap(WebSiteUrl.AppWebServiceUrl, soapRequestData);

                            String moneyRequestData = httpClient.buildRequestData(moneyMap, nameSpace, "memDeposit");
                            String strMoney = httpClient.sendPostSoap(WebSiteUrl.AppWebServiceUrl, moneyRequestData);
                            if (strPost != null && !"netError".equals(strPost)) {
                                StringReader sr = new StringReader(strPost);
                                InputSource is = new InputSource(sr);
                                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                Document doc = builder.parse(is);
                                if (doc.getElementsByTagName("return") != null) {
                                    String return_value = doc.getElementsByTagName("return").item(0).getFirstChild().getNodeValue();
                                    if ("0".equals(return_value) || "2".equals(return_value)) {
                                        String params = "txtAcctid=" + userName + "&txtPwd=" + password + "&txtLang=" + (language.equals("zh") ? "0" : "1") + "&txtRandCode=jBmkBignwbhcMiVEvneJNRPlNhLGKEgnsiToyrIjMbHZBbcLAm";

                                        String resReturn = httpClient.sendPost(WebSiteUrl.AppDig88LoginUrl, params);
                                        if ((resReturn != null && resReturn.contains("Results=ok#")) || (WebSiteUrl.GameType == 1 && !resReturn.equals("netError")))//登录成功
                                        {
                                            String strRes = "";
                                            switch (language) {
                                                case "zh":
                                                    AppTool.setAppLanguage(WelcomeActivity.this, "zh");
                                                    break;
                                                case "zh_TW":
                                                    AppTool.setAppLanguage(WelcomeActivity.this, "zh_TW");
                                                    break;
                                                case "th":
                                                    AppTool.setAppLanguage(WelcomeActivity.this, "th");
                                                    break;
                                                default:
                                                    AppTool.setAppLanguage(WelcomeActivity.this, "en");
                                                    break;
                                            }
                                            mAppViewModel.getUser().setName(userName);
                                            if (mAppViewModel.getCookie() == null || mAppViewModel.getCookie().equals("")) {
                                                mAppViewModel.setHttpClient(new HttpClient(WebSiteUrl.INDEX, mAppViewModel.getCookie()));

                                                if (mAppViewModel.getHttpClient().connect("POST") == false) {
                                                    Log.i(WebSiteUrl.Tag, "HttpClient conn error");
                                                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                                                    return;
                                                }
                                                mAppViewModel.getHttpClient().getBodyString("UTF-8");
                                                mAppViewModel.setCookie(mAppViewModel.getHttpClient().getSessionId());
                                                //      Log.e(WebSiteUrl.Tag,"sessionid="+mAppViewModel.getHttpClient().getSessionId());
                                            }

//
                                            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LOGOUT_URL/*"http://96.9.71.29/DIGKorean/main.jsp"*/, "membername=" + mAppViewModel.getUser().getName() + "&lang=0");
                                            //语言设定
                                            loginInit(gameType, currency, balance);
                                            return;

                                        }
                                    }
                                }
                                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                            } else {
                                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                            }
                        } catch (Exception e) {
                            handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
                            if (e != null) {
                                Log.d("AppData", e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    }
                }
                );
                thread.start();
            } else {
                finish();
            }

        } catch (Exception e) {
            Log.d("AppData", e.getMessage());
            e.printStackTrace();
            Toast.makeText(WelcomeActivity.this, getResources().getString(R.string.login_data_error), Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
        }
    }

    private void loginInit(int gameType, String currency, String balance) {
        String strRes;
        String annoucementParams = "lng=" + 0 + "&Usid=" + mAppViewModel.getUser().getName();
        strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.ANNOUNCEMENT_URL, annoucementParams);
        if (strRes.equals("netError")) {
            //	Log.e(WebSiteUrl.Tag,strRes);
            handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
            return;
        }

        String ann[] = strRes.split("Results=ok\\|");
        if (ann.length > 1)
            mAppViewModel.setAnnouncement(ann[1]);

        //	Log.i(WebSiteUrl.Tag,appData.getAnnouncement());
        strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.TABLEINFO_URL_A/*"http://96.9.71.29/DIGKorean/select_tb_infoa.jsp"*/, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
        if (strRes.equals("netError")) {
            //	Log.e(WebSiteUrl.Tag,strRes);
            handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
            return;
        }
        //	Log.i(WebSiteUrl.Tag,strRes);
        String tableInfo[] = strRes.split("\\^");
        if (tableInfo.length < 8) {
            handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
            return;
        }
        mAppViewModel.setHallId(gameType + 1);
        mAppViewModel.setbInitLimit(false);
        mAppViewModel.splitTableInfo(strRes, mAppViewModel.getHallId());
        mAppViewModel.getUser().setCurrency(currency);
        mAppViewModel.getUser().setBalance(DecimalUtils.round(Double.valueOf(balance), 2));
        strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.COUNTDOWN_URL, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
        if (strRes.equals("netError")) {
            handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
            return;
        }
        //	Log.i(WebSiteUrl.Tag,strRes);
        mAppViewModel.splitTimer(strRes);
        mAppViewModel.setbLogin(true);
        mAppViewModel.setbLobby(true);
        handler.sendEmptyMessage(ErrorCode.LOGIN_AREADY);
        WebSiteUrl.isDomain = true;
        return;
    }

    private boolean hasVersionChecked;
    private boolean hasWebsiteChecked;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {


            switch (msg.what) {
                case ErrorCode.LOGIN_ERROR_NETWORK:
                    Toast.makeText(mContext, R.string.login_network_error, Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case ErrorCode.DATA_ERROR_LENGTH:
                    Toast.makeText(mContext, R.string.login_data_error, Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case ErrorCode.LOGIN_AREADY:
                    goLobby();
                    break;
                case HandlerCode.SHOW_BACCARACT:
                    dismissBlockDialog();

                    AppTool.activiyJump(mContext, LobbyBaccaratActivity.class);
                    AppTool.setAppLanguage(mContext,AppTool.getAppLanguage(mContext));
                    finish();
                    break;
                case HandlerCode.SHOW_BACCARACT_FAIL:
                    dismissBlockDialog();
                    Toast.makeText(mContext, R.string.server_network_error, Toast.LENGTH_LONG).show();
                    break;
                case ErrorCode.LOGIN_SECCESS:
                    dismissBlockDialog();
                    AppTool.activiyJump(mContext, LobbyActivity.class);
                    if (WebSiteUrl.GameType != 3) {
                        finish();
                    }
                    break;

                case ErrorCode.LOGIN_ERROR_USERNAME:
                    dismissBlockDialog();
                    Toast.makeText(mContext, R.string.login_username_error, Toast.LENGTH_LONG).show();
                    break;
                case LOGIN_ERROR_MSG:
                    dismissBlockDialog();
                    Toast.makeText(mContext, (String) msg.obj, Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case ErrorCode.REQUEST_SECCESS:
                    initAgentName(getIntent());
                    break;
                case ErrorCode.CHECHED_VERSION:
                    hasVersionChecked = true;
                    CheckNext();
                    break;
                case ErrorCode.GET_WEBSITE:
                    hasWebsiteChecked = true;
                    CheckNext();
                    break;

            }
            //


        }
    };

    private void CheckNext() {
        if (hasVersionChecked && hasWebsiteChecked) {
            if (!StringUtils.isNull(updateUrl)) {
                updateApp(updateUrl);
            } else {
                goMain();
            }
        }

    }

    @Override
    protected void initToolBar() {
    }

    private void updateApp(String updateUrl) {
        this.updateUrl = updateUrl;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE

        };

        if (requestPermission(PERMISSIONS_STORAGE, READ_CODE)) {
            showDownload(updateUrl);
        }

    }

    private void showDownload(String updateUrl) {
        UpdateManager updateManager = new UpdateManager(mContext);
        updateManager.setCancel("");
        updateManager.setTitle(getString(R.string.version_update));
        updateManager.setUpdate(getString(R.string.update));
        updateManager.setUpdateMsg(getString(R.string.update_msg));
        updateManager.setLoadTitle(getString(R.string.app_update));
        updateManager.checkUpdate(updateUrl);
        updateManager.setOnLoadEnd(new UpdateManager.ILoad() {
            @Override
            public void onLoadEnd(File file) {
                WelcomeActivity.this.loadFile = file;
                checkIsAndroidO();
            }
        });
    }

    public void checkIsAndroidO() {
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = getPackageManager().canRequestPackageInstalls();
            if (b) {
                AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
            } else {
                //请求安装未知应用来源的权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, INSTALL_CODE);
            }
        } else {
            AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
        }
    }

    final int READ_CODE = 101;
    final int INSTALL_CODE = 102;
    final int INSTALL_AFB_CODE = 109;

    private int version = Build.VERSION.SDK_INT;
    private String appVersionUrl = "http://www.appgd88.com/api/gd88AndroidVersion.php?Labelid=";
    private Gson gson = new Gson();

    private void hasNewVersion() {
        if(BuildConfig.FLAVOR.isEmpty())
            return;
        updateUrl = null;
        hasVersionChecked = false;
        QuickCookieThreadHandler threadHandler = new QuickCookieThreadHandler(this) {
            @Override
            protected QuickRequestBean getRequestBean() {
                return new QuickRequestBean(RequestBean.Method.GET, appVersionUrl + BuildConfig.Labelid, new HashMap(), new TypeToken<String>() {
                }.getType());
            }

            @Override
            public void successEnd(String data) {
                if (data == null)
                    return;
                AppVersionBean appVersionBean = gson.fromJson(data, AppVersionBean.class);
                PackageInfo packageInfo = AppTool.getApkInfo(mContext);
                float localVersion = Float.parseFloat(packageInfo.versionName);
                float serverVersion = Float.parseFloat(appVersionBean.getData().getVersion().trim());
                Log.d("AppData", serverVersion + ":server");
                Log.d("AppData", localVersion + ":local");
                if (version >= 23) {
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                    int REQUEST_EXTERNAL_STORAGE = 1;
                    String[] PERMISSIONS_STORAGE = {
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    };
                    int permission = ActivityCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(
                                WelcomeActivity.this,
                                PERMISSIONS_STORAGE,
                                REQUEST_EXTERNAL_STORAGE
                        );
                    }
                }

                if (AppTool.getApkInfo(mContext) != null && serverVersion > localVersion) {
                    updateUrl = appVersionBean.getData().getUrl();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            dismissBlockDialog();
                        }
                    });

//                    updateApp(updateUrl);
                } else {
//                    goMain();
                }

                handler.sendEmptyMessage(ErrorCode.CHECHED_VERSION);

            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
//                goMain();
                handler.sendEmptyMessage(ErrorCode.CHECHED_VERSION);
            }


        };
        threadHandler.startThread(null);
    }

    private void goMain() {
        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        String action = it.getAction();
        if (BuildConfig.FLAVOR.isEmpty()||BuildConfig.FLAVOR.equals("gd88")) {
            if (false)
                testIntent();
            else {

                if (it != null && bundle != null && !TextUtils.isEmpty(bundle.getString("web_id"))) {
//                    showBlockDialog();
                    hasAppIntent();
                } else if (Intent.ACTION_VIEW.equals(action)) {
                    Uri uri = it.getData();
                    String web_id = uri.getQueryParameter("web_id");
                    String game_type = uri.getQueryParameter("game_type");
                    String currency = uri.getQueryParameter("currency");
                    fromDig88(Integer.valueOf(web_id), currency, Integer.valueOf(game_type));


                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AppTool.activiyJump(WelcomeActivity.this, LoginActivity.class);
                            AppTool.setAppLanguage(mContext,AppTool.getAppLanguage(mContext));
                            finish();
                        }
                    }, 2000);
                }
            }
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppTool.activiyJump(WelcomeActivity.this, LoginActivity.class);
                    AppTool.setAppLanguage(mContext,AppTool.getAppLanguage(mContext));
                    finish();
                }
            }, 2000);
        }

    }

    private void testIntent() {
        userName = "saiokambing3003".toUpperCase();
        password = "12345678";
        balance1 = "";
        lg = "en";
        createAccountUrl = "http://113.130.125.182/GDWebService?wsdl";
        appKey = "YGzCaiFEYqhioSVz1FdDWTHUJX71DU";
        urlHead = "shiokambing3";
        initNewIntent("113.130.125.182");
    }

    public void goLobby() {
        if (WebSiteUrl.GameType == 0) {
            AppTool.activiyJump(WelcomeActivity.this, LobbyActivity.class);
            AppTool.setAppLanguage(mContext,AppTool.getAppLanguage(mContext));
            finish();
        } else {
//            showBlockDialog();
            setLoginUi();
            updateTableStatus();
        }


    }

    private void setLoginUi() {
//        llLogin.setVisibility(View.VISIBLE);
//        flWelcome.setVisibility(View.GONE);
    }


    private void updateTableStatus() {

        UpdateGameStatus updateGameStatus = new UpdateGameStatus();

        new Thread(updateGameStatus).start();
    }

    public class UpdateGameStatus implements Runnable {
        int iError = 0;


        public void run() {

            try {
                if (mAppViewModel.isbLogin()) {
                    //   Log.i(WebSiteUrl.Tag, "-------------- UpdateGameStatus 1");
                    String statusUrl = "";
                    statusUrl = WebSiteUrl.TABLEINFO_URL_A;
                    String strRes = mAppViewModel.getHttpClient().sendPost(statusUrl, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
                    String tableInfo[] = strRes.split("\\^");

                    if (strRes.equals("netError") || strRes.equals("Results=no") || tableInfo.length < 9) {//连续5次拿不到数据就退出，返回到登录界面

                        iError++;
                    } else {
                        iError = 0;
                    }

                    if (iError == 0) {
                        mAppViewModel.splitTableInfo(strRes, mAppViewModel.getHallId());
                        //拿公告信息

                    }
                    switch (mAppViewModel.getHallId()) {
                        case 1:
                            statusUrl = WebSiteUrl.COUNTDOWN_URL_A;
                            break;
                        case 2:
                            statusUrl = WebSiteUrl.COUNTDOWN_URL_B;
                            break;

                    }
                    strRes = mAppViewModel.getHttpClient().sendPost(statusUrl, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
                    if (strRes.equals("netError") || strRes.equals("Results=no")) {//连续10次拿不到数据就退出，返回到登录界面

                        iError++;
                    } else
                        iError = 0;


                    //  Log.i(WebSiteUrl.Tag, "++++++++++++++ "+strRes);
                    if (iError == 0) {
                        mAppViewModel.splitTimer(strRes);
                        handler.sendEmptyMessage(HandlerCode.SHOW_BACCARACT);
                    } else
                        handler.sendEmptyMessage(HandlerCode.SHOW_BACCARACT_FAIL);
                }

            } catch (Exception e) {
                e.printStackTrace();
                //   Log.i(WebSiteUrl.Tag, "////////////  update status error");
                handler.sendEmptyMessage(HandlerCode.SHOW_BACCARACT_FAIL);
            }
        }
        //  Log.i(WebSiteUrl.Tag, "-------------- end update status");


    }

    //青龙：D0KDIG899 豪龙：G07DIG812
    //WEBID 23 青龙D0KDIG8A7     豪龙G07DIG840    AFB  i@cq
    String agentName;
    String requestUrl;

    public void getAgentName(int gameType, final String currency, final int web_id) {
        final Gson gson = new Gson();
        if (gameType == 1) {
            requestUrl = WebSiteUrl.GET_GR_CASINO_URL;
        } else {
            requestUrl = WebSiteUrl.GET_GD_CASINO_URL;
        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpClient httpClient = new HttpClient();
                String resultStr = httpClient.sendPost(requestUrl, "web_id=" + web_id + "&currency=" + currency);
                try {
                    GdOrGrAgentBean bean = gson.fromJson(resultStr, GdOrGrAgentBean.class);
                    agentName = bean.getData().getAgent();
                    handler.sendEmptyMessage(ErrorCode.REQUEST_SECCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
                }

            }
        }.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case READ_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showDownload(updateUrl);
                } else {
                    ToastUtils.showToast(this, getString(R.string.no_permission));
                    //  引导用户手动开启安装权限
                }
                break;
            case INSTALL_CODE:
                AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
                } else {
                    ToastUtils.showToast(this, getString(R.string.open_install));
                    //  引导用户手动开启安装权限
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                    startActivityForResult(intent, INSTALL_CODE);
                    Uri packageURI = Uri.parse("package:gaming178.com.baccaratgame");//设置包名，可直接跳转当前软件的设置页面
                    Intent ii = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                    startActivityForResult(ii, INSTALL_AFB_CODE);
                }
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case INSTALL_AFB_CODE:
                AppTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebSiteUrl.GameType = 0;
    }

}
