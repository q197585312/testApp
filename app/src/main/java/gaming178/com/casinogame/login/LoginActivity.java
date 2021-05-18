package gaming178.com.casinogame.login;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.LobbyActivity;
import gaming178.com.casinogame.Activity.RegisterActivity;
import gaming178.com.casinogame.Bean.Liga365AgentBean;
import gaming178.com.casinogame.Bean.UserBean;
import gaming178.com.casinogame.Bean.UserLoginBean;
import gaming178.com.casinogame.Bean.UserResponseBean;
import gaming178.com.casinogame.Util.ErrorCode;
import gaming178.com.casinogame.Util.HttpClient;
import gaming178.com.casinogame.Util.PopMenu;
import gaming178.com.casinogame.Util.PopWebView;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.BlockDialog;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.allinone.util.GdToastUtils;
import gaming178.com.mylibrary.lib.util.LogUtil;

/**
 * Created by Administrator on 2016/3/22.
 */
public class LoginActivity extends BaseActivity {
    private EditText tv_name;
    private EditText tv_password;
    private BlockDialog dialog;

    private Button btn_login;
    private String language;
    private String version;
    private View ll_language;
    private ImageView iv_language_flag;
    private TextView tv_register;
    private View llContainer;
    private int[] sc;
    private View llBottomBtn;
    private int scrollHeight = 0;
    private HashMap<String, String> siteMap;
    private ImageView imgOpen;
    private ImageView img_gif;
    private ImageView img_login_title, img_login_title_main;
    private LinearLayout ll_liga365;
    private LinearLayout ll_remember_me;
    private CheckBox cb_remember_me;
    private WebView webView;
    private ImageView img_exit;
    private RelativeLayout rl_webview;
    private LinearLayout ll_lang, ll_lg_and_remember;
    private ImageView img_in;
    private ImageView img_en;
    private TextView tv_user_name, tv_password_name;

    @Override
    protected void initData(Bundle savedInstanceState) {
        AppTool.setAppLanguage(mContext, AppTool.getAppLanguage(mContext));
        version = AppTool.getApkInfo(mContext).versionName;
        toolbar.setVisibility(View.GONE);
        initControl();
    }

    @Override
    public boolean isLogin() {
        return true;
    }

    public void initControl() {
        if (BuildConfig.FLAVOR.equals("gd88")) {
            webView = findViewById(R.id.gd_login_webview);
            img_exit = findViewById(R.id.gd_img_exit);
            rl_webview = findViewById(R.id.gd_rl_webview);
            rl_webview.setVisibility(View.VISIBLE);
            img_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rl_webview.setVisibility(View.GONE);
                    webView.destroy();
                    rl_webview.removeAllViews();
                }
            });
            webView.post(new Runnable() {
                @Override
                public void run() {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) webView.getLayoutParams();
                    layoutParams.height = webView.getWidth() / 16 * 9;
                    webView.setLayoutParams(layoutParams);
                }
            });
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
            webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
            webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
            webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
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
            String lg = AppTool.getAppLanguage(mContext);
            String webViewUrl = "https://www.youtube.com/embed/YA5fXr8jrN0";
            if (lg.equals("th")) {
                webViewUrl = "https://www.youtube.com/embed/Rb2oTrsV1XA";
            }
            webView.loadUrl(webViewUrl);
        }
        img_login_title = findViewById(R.id.gd_img_login_title);
        ll_liga365 = findViewById(R.id.ll_liga365);
        ll_remember_me = findViewById(R.id.ll_remember_me);
        cb_remember_me = findViewById(R.id.cb_remember_me);
        tv_register = (TextView) findViewById(R.id.gd__tv_register);
        if (BuildConfig.FLAVOR.equals("gd88") || BuildConfig.FLAVOR.equals("liga365")) {
            img_login_title.setImageResource(R.mipmap.gd_app_logo);
        } else {
            tv_register.setVisibility(View.VISIBLE);
            img_login_title.setImageResource(R.mipmap.gd_title_logo);
        }
        img_gif = findViewById(R.id.gd_img_gif);
        imgOpen = findViewById(R.id.gd_img_open);
        btn_login = (Button) findViewById(R.id.gd__login_login_btn);
        if (BuildConfig.FLAVOR.equals("mainkasino")) {
            tv_user_name = findViewById(R.id.tv_user_name);
            tv_password_name = findViewById(R.id.tv_password_name);
            tv_user_name.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.gd_user_img, 0, 0, 0);
            tv_password_name.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.gd_password_img, 0, 0, 0);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv_register.getLayoutParams();
            layoutParams.topMargin = layoutParams.topMargin / 2;
            tv_register.setLayoutParams(layoutParams);
            cb_remember_me.setChecked(true);
            btn_login.setBackgroundResource(R.drawable.gd_login_button_bg_blue);
            tv_register.setBackgroundResource(R.drawable.gd_login_button_bg_red);
            btn_login.setTextColor(Color.WHITE);
            tv_register.setTextColor(Color.WHITE);
            img_login_title.setVisibility(View.GONE);
            img_login_title_main = findViewById(R.id.gd_img_login_title_main);
            img_login_title_main.setVisibility(View.VISIBLE);
            ll_lg_and_remember = findViewById(R.id.ll_lg_and_remember);
            ll_lg_and_remember.setVisibility(View.GONE);
            ll_lang = findViewById(R.id.ll_lang);
            ll_lang.setVisibility(View.VISIBLE);
            img_in = findViewById(R.id.img_in);
            img_en = findViewById(R.id.img_en);
            img_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppTool.setAppLanguage(LoginActivity.this, "my");
                    recreate();
                }
            });
            img_en.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppTool.setAppLanguage(LoginActivity.this, "en");
                    recreate();
                }
            });

            img_gif.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(LoginActivity.this, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "https://www.pragmaticplay.com/en/games/";
                        }

                        @Override
                        public String getTitle() {
                            return "";
                        }
                    };
                    popWebView.showPopupCenterWindow();
                }
            });
            img_gif.setVisibility(View.VISIBLE);
            new Thread() {
                @Override
                public void run() {
                    String url = "http://www.grjl25.com/getDomainInform.jsp?";
                    String param = "labelid=" + BuildConfig.Labelid;
                    String result = httpClient.getHttpClient(url + param, null);
                    Log.d("AppData", result);
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(LoginActivity.this).asGif().load(result + "images/banner-20200724.gif").diskCacheStrategy(DiskCacheStrategy.NONE).into(img_gif);
                        }
                    });
                }
            }.start();
        }
        if (!BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365") &&
                !BuildConfig.FLAVOR.equals("glxcasino") && !BuildConfig.FLAVOR.equals("masterbaccarat") && !BuildConfig.FLAVOR.equals("mejaemas")) {
            imgOpen.setVisibility(View.VISIBLE);
            imgOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppTool.setAppLanguage(LoginActivity.this, AppTool.getAppLanguage(LoginActivity.this));
                    PopMenu popMenu = new PopMenu(mContext, v, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    popMenu.showPopupDownWindow();
                }
            });
        }
        tv_name = (EditText) this.findViewById(R.id.gd__login_username_edt);
        tv_password = (EditText) this.findViewById(R.id.gd__login_password_edt);
        ll_language = findViewById(R.id.gd__ll_choose_language);
        iv_language_flag = (ImageView) findViewById(R.id.gd__iv_language_flag);
        if (BuildConfig.FLAVOR.equals("liga365")) {
            ll_liga365.setVisibility(View.VISIBLE);
            initSiteMap();
        }


        //    tv_name.setText("T0000000301");
        //   tv_password.setText("111111");
        dialog = new BlockDialog(mContext, getString(R.string.logining));
        tv_password.setOnKeyListener(onKey);
        ll_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLanguage(ll_language);

            }
        });
        ll_remember_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCheck = cb_remember_me.isChecked();
                if (isCheck) {
                    cb_remember_me.setChecked(false);
                } else {
                    cb_remember_me.setChecked(true);
                }
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });
        MenuItemInfo<String> languageItem = new LanguageHelper(mContext).getLanguageItem();
        iv_language_flag.setImageResource(languageItem.getRes());
        Object objectData1 = AppTool.getObjectData(mContext, WebSiteUrl.Tag);
        if (objectData1 != null && objectData1 instanceof UserLoginBean) {
            cb_remember_me.setChecked(true);
            UserLoginBean objectData = (UserLoginBean) objectData1;
            tv_name.setText(objectData.getUsername());
            tv_password.setText(objectData.getPassword());
            EditText viewById = (EditText) findViewById(R.id.gd__login_site_edt);
            if (viewById != null)
                viewById.setText(objectData.getSite());
        } else {
            if (!BuildConfig.FLAVOR.equals("mainkasino")) {
                cb_remember_me.setChecked(false);
            }
        }

    }

    private void initSiteMap() {
        siteMap = new HashMap<String, String>();
        mAppViewModel.setHttpClient(new HttpClient());
        new Thread() {
            @Override
            public void run() {
                String agentUrl = "http://www.appgd88.com/liga365agengt.php";
                String agentResult = mAppViewModel.getHttpClient().sendPost(agentUrl, "");
                Liga365AgentBean liga365AgentBean = null;
                try {
                    liga365AgentBean = new Gson().fromJson(agentResult, Liga365AgentBean.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    tv_password.post(new Runnable() {
                        @Override
                        public void run() {
                            GdToastUtils.showToast(mContext, "no agent !");
                        }
                    });
                }
                if (liga365AgentBean != null && liga365AgentBean.getData().size() > 0) {
                    List<Liga365AgentBean.DataBean> dataList = liga365AgentBean.getData();
                    for (int i = 0; i < dataList.size(); i++) {
                        Liga365AgentBean.DataBean dataBean = dataList.get(i);
                        String web = dataBean.getWeb();
                        String agent = dataBean.getAgent();
                        siteMap.put(web, agent);
                    }
                }
            }
        }.start();

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        inputMove();
    }

    View.OnKeyListener onKey = new View.OnKeyListener() {

        @Override

        public boolean onKey(View v, int keyCode, KeyEvent event) {

// TODO Auto-generated method stub

            if (keyCode == KeyEvent.KEYCODE_ENTER) {

                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                if (imm.isActive()) {

                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

                }
                clickLogin(v);

                return true;

            }

            return false;

        }

    };

    public void initLanguage(View view) {
        showLanguagePop(view, 0.15f);
    }

    private void restart() {
        tv_name.setHint(getString(R.string.input_username));
        tv_password.setHint(getString(R.string.input_password));
        btn_login.setText(getString(R.string.gd_login));
        dialog.setMsg(getString(R.string.logining));
    }


    public void showLoginBlockDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog.show();
        }
    }

    public void dismissLoginBlockDialog() {
        if (dialog != null && dialog.isShowing() && getWindow().isActive()) {
            dialog.dismiss();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.gd_activity_login;
    }

    public void clickLogin(View v) {
        String usName = tv_name.getText().toString().trim();

        mAppViewModel.getUser().setName(usName);
        mAppViewModel.getUser().setRealName(usName);
        mAppViewModel.getUser().setPassword(tv_password.getText().toString().trim());
        if (mAppViewModel.getUser().getName() == null || mAppViewModel.getUser().getName().length() == 0) {
            //     showToast("Please input user name");
            return;
        }
        if (mAppViewModel.getUser().getPassword() == null || mAppViewModel.getUser().getPassword().length() == 0) {
            //   showToast("Please input user password");
            return;
        }
        String language = AppTool.getAppLanguage(mContext);
        switch (language) {
            case "zh":
                this.language = "cn";
                break;
            case "en":
                this.language = "en";
                break;
            default:
                break;
        }
        mAppViewModel.setbInitLimit(false);
        if (BuildConfig.FLAVOR.equals("liga365")) {
            EditText edt_site = (EditText) findViewById(R.id.gd__login_site_edt);
            UserLoginBean userBean = new UserLoginBean(edt_site.getText().toString().trim(), usName, tv_password.getText().toString().trim());
            if (userBean.getSite().length() == 0) {
//            showToast("Please input user name");
                return;
            }
            if (siteMap.get(userBean.getSite()) == null) {
                Toast.makeText(mContext, R.string.error_site, Toast.LENGTH_LONG).show();
                return;
            }
            UserBean user = new UserBean(userBean.getUsername() + siteMap.get(userBean.getSite()), userBean.getPassword());
            goLogin365(user);

        } else {
            Thread loginThread = new ThreadLogin();
            showLoginBlockDialog();
            loginThread.start();
        }

    }

    private void goLogin365(final UserBean user) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    urlHostInit();
                    String urlHost = WebSiteUrl.AppWebServiceUrl;
                    Gson gson = new Gson();
                    UserResponseBean res = null;
                    mAppViewModel.setHttpClient(new HttpClient());
//                  String loginParams = "username=pakmc1"/* +mAppViewModel.getUser().getName()*/+"&password=bb123123"/*+mAppViewModel.getUser().getPassword()*/;
                    String loginParams = gson.toJson(user);
                    String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.PROXY_LOGIN_URL, loginParams);
                    Log.w("Afb88", strRes);
                    if (strRes.equals("netError")) {
                        handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                        return;
                    }
                    res = gson.fromJson(strRes, new TypeToken<UserResponseBean>() {
                    }.getType());
                    if (res == null || res.getErrCode() != 0) {
                        handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                        return;
                    }
                    Map<String, String> patameterMap = new HashMap<String, String>();
                    patameterMap.put("OperatorName", "liga365");
                    patameterMap.put("OperatorPwd", "gdccs$365");
//"http://113.130.125.198/OLTGames/GDOnlineWS
                /*    String soapRequestData = mAppViewModel.getHttpClient().buildRequestData(patameterMap, WebSiteUrl.AppWebServiceNameSpace, "GetRandCode");
                    String strPost = mAppViewModel.getHttpClient().sendPostSoap(WebSiteUrl.AppWebServiceUrl, soapRequestData);
*/
//                    String paramsRandCode= "operatorName=liga365&OperatorPwd=gdccs$365&action=getRandCode";
                    String paramsRandCode = "{\"strUsername\":\"" + res.getResult().getNickname() + "\",\"operatorName\":\"liga365\",\"operatorPwd\":\"gdccs$365\",\"action\":\"getRandCode\"}";

                    String return_value = mAppViewModel.getHttpClient().sendPostSoap(urlHost, paramsRandCode);
                    Log.w("Afb88", return_value);
                    if (StringUtils.isNull(return_value) || "netError".equals(return_value)) {
                        handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                        return;
                    }

                  /*  StringReader sr = new StringReader(strPost);
                    InputSource is = new InputSource(sr);
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse(is);
                    if (doc.getElementsByTagName("return") == null) {
                        handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                        return;
                    }
                    String return_value = doc.getElementsByTagName("return").item(0).getFirstChild().getNodeValue();
                    if (return_value == null || "".equals(return_value)) {
                        handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                        return;
                    }*/
                    //"http://113.130.125.198/OLTGames/lglogin.jsp?"
                    String res1 = mAppViewModel.getHttpClient().getHttpClient(WebSiteUrl.LOGIN_URL + "?txtAcctid=" + res.getResult().getNickname() + "&txtLang=&txtPwd=PWDsecw1755x!&txtLang&txtRandCode=" + return_value, "");
                    LogUtil.d("Afb88", res1);
                    String sessionId = mAppViewModel.getHttpClient().getSessionId();
                    /*  mAppViewModel.getHttpClient().setSessionId(sessionId);*/
                    /*         mAppViewModel.setCookie(sessionId);*/

                    String params = "GameType=11&Tbid=0&Usid=" + res.getResult().getNickname();
                    strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.TABLE_INFO_A_URL, params);
                    if (strRes.equals("netError")) {
                        handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                        return;
                    }
                    String tableInfo[] = strRes.split("\\^");
                    if (tableInfo.length < 12) {
                        handler.sendEmptyMessage(ErrorCode.DATA_ERROR_LENGTH);
                        return;
                    }
//            mAppViewModel.setbInitLimit(false);
                    mAppViewModel.splitTableInfo(strRes, 1);
                    String annoucementParams = "GameType=11&lng=" + language + "&Usid=" + res.getResult().getNickname();
                    strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.GAME_GG_URL, annoucementParams);
                    Log.d("Afb88", "url:" + WebSiteUrl.TABLE_INFO_A_URL + ",params:" + annoucementParams);
                    if (strRes.equals("netError")) {
                        //	Log.e(WebSiteUrl.Tag,strRes);
                        handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                        return;
                    }
                    String ann[] = strRes.split("Results=ok\\|");
                    if (ann.length > 1)
                        mAppViewModel.setAnnouncement(ann[1]);
                    strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.COUNTDOWN_URL_A, "GameType=11&Tbid=0&Usid=" + res.getResult().getNickname());
                    Log.d("Afb88", "url:" + WebSiteUrl.COUNTDOWN_URL_A + ",params:" + "GameType=11&Tbid=0&Usid=" + res.getResult().getNickname());
                    if (strRes.equals("netError")) {
                        handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                        return;
                    }
                    mAppViewModel.splitTimer(strRes);
                    mAppViewModel.setbLogin(true);
                    mAppViewModel.setbLobby(true);
                    mAppViewModel.getUser().setName(res.getResult().getNickname());
                    mAppViewModel.getUser().setRealName(user.getUsername());

                    handler.sendEmptyMessage(ErrorCode.LOGIN_SECCESS);

                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                    return;
                }

            }
        }
        );
        showLoginBlockDialog();
        thread.start();
    }

    public void urlHostInit() {
        String url = "http://www.grjl25.com/getDomainInform.jsp?";
        String param = "labelid=" + BuildConfig.Labelid;
        String result = httpClient.getHttpClient(url + param, null);
        Log.d("AppData", result);
//        WebSiteUrl.setNormal("http://113.130.125.211/");
        WebSiteUrl.setNormal(result);
    }

    public void getLiveChat() {
        if (!BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            String liveChatUrl = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "getLiveChat.jsp";
            String liveChatResult = mAppViewModel.getHttpClient().sendPost(liveChatUrl, "");
            if (liveChatResult.startsWith("Results=ok")) {
                String url = liveChatResult.replace("Results=ok#", "");
                mAppViewModel.setLiveChatStr(url);
            } else {
                mAppViewModel.setLiveChatStr("");
            }
        }
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ErrorCode.LOGIN_SECCESS:
                    dismissLoginBlockDialog();
                    if (cb_remember_me.isChecked()) {
                        String usName = tv_name.getText().toString().trim();
                        String password = tv_password.getText().toString().trim();
                        EditText siteEdt = findViewById(R.id.gd__login_site_edt);
                        String site = "";
                        if (siteEdt != null) {
                            site = siteEdt.getText().toString().trim();
                        }
                        UserLoginBean userLoginBean = new UserLoginBean(site, usName, password);

                        AppTool.saveObjectData(mContext, WebSiteUrl.Tag, userLoginBean);
                    } else {
                        AppTool.saveObjectData(mContext, WebSiteUrl.Tag, null);
                    }
                    skipAct(LobbyActivity.class);
                    //    finish();
                    break;
                case ErrorCode.LOGIN_AREADY:
                    dismissLoginBlockDialog();
                    Toast.makeText(mContext, R.string.login_already, Toast.LENGTH_LONG).show();
                    break;
                case ErrorCode.LOGIN_ERROR_USERNAME:
                    dismissLoginBlockDialog();
                    Toast.makeText(mContext, R.string.login_username_error, Toast.LENGTH_LONG).show();
                    break;
                case ErrorCode.LOGIN_ERROR_NETWORK:
                    dismissLoginBlockDialog();
                    Toast.makeText(mContext, R.string.login_network_error, Toast.LENGTH_LONG).show();
                    break;
                case ErrorCode.DATA_ERROR_LENGTH:
                    dismissLoginBlockDialog();
//                    mAppViewModel.setCookie("");
                    Toast.makeText(mContext, R.string.login_data_error, Toast.LENGTH_LONG).show();
                    break;
            }
            //

        }
    };
    HttpClient httpClient = new HttpClient("");

    public class ThreadLogin extends Thread {
        @Override
        public void run() {

            urlHostInit();
            String strRes;
//            mAppViewModel.setCookie("");
            mAppViewModel.setHttpClient(new HttpClient(WebSiteUrl.INDEX, ""));

            if (mAppViewModel.getHttpClient().connect("POST") == false) {
                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                return;
            }

            String loginParams = "txtLang=0&txtAcctid=" + mAppViewModel.getUser().getName() + "&txtPwd=" + mAppViewModel.getUser().getPassword() + "&OsType=Android" + "&OsVersion=" + version + "&txtRandCode=jBmkBignwbhcMiVEvneJNRPlNhLGKEgnsiToyrIjMbHZBbcLAm";
            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LOGIN_URL, loginParams);

            if (strRes.startsWith("Results=error"))//登录失败，没有失败原因
            {
                //	Log.e(WebSiteUrl.Tag,strRes);
                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_USERNAME);
                mAppViewModel.setCookie("");
                return;
            } else if (!strRes.startsWith("Results=ok")) {
                //	Log.e(WebSiteUrl.Tag,strRes);
                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                return;
            } else if (strRes.startsWith("Results=ok")) {
                String resultInfo[] = strRes.split("\\#");
                mAppViewModel.getUser().setCurrency(resultInfo[1]);

            }

            String annoucementParams = "lng=" + language + "&Usid=" + mAppViewModel.getUser().getName();
            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.GAME_GG_URL, annoucementParams);
            if (strRes.equals("netError")) {
                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                return;
            }
            String ann[] = strRes.split("Results=ok\\|");
            if (ann.length > 1)
                mAppViewModel.setAnnouncement(ann[1]);
            //	Log.i(WebSiteUrl.Tag,appData.getAnnouncement());
            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.TABLE_INFO_A_URL, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
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
            strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.COUNTDOWN_URL_A, "GameType=11&Tbid=0&Usid=" + mAppViewModel.getUser().getName());
            if (strRes.equals("netError")) {
                handler.sendEmptyMessage(ErrorCode.LOGIN_ERROR_NETWORK);
                return;
            }
            mAppViewModel.splitTimer(strRes);
            mAppViewModel.setbLogin(true);
            mAppViewModel.setbLobby(true);
            getLiveChat();
            handler.sendEmptyMessage(ErrorCode.LOGIN_SECCESS);
        }

    }


    @Override
    public void startUpdateStatus() {
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void leftClick() {
        android.os.Process.killProcess(android.os.Process.myPid()); //获取PID
        System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出
    }

    public void inputMove() {

        llContainer = findViewById(R.id.gd__ll_container);
        beyondKeyboardLayout();
    }

    private void beyondKeyboardLayout() {
        // 监听根布局的视图变化
        llContainer.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取内容布局在窗体的可视区域
                        llContainer.getRootView().getWindowVisibleDisplayFrame(rect);
                        // 获取内容布局在窗体的不可视区域高度(被其他View遮挡的区域高度)
                        int rootInvisibleHeight = llContainer.getHeight() - rect.bottom;

                        if (rootInvisibleHeight > 120) {
                            int[] location = new int[2];

                            btn_login.getLocationInWindow(location);
                            int screenHeight = llContainer.getRootView().getHeight();
                            int softHeight = screenHeight - rect.bottom;
                            //解决大屏幕会下移问题
                            if ((location[1] + btn_login.getHeight()) > rect.bottom) {
//                                scrollHeight = sc[1] + btn_login.getHeight() - (screenHeight - softHeight);//可以加个5dp的距离这样，按钮不会挨着输入法
//                                int buttonHeight = (location[1] + llContainer.getHeight()) - rect.bottom;
                                int buttonHeight = (location[1] + btn_login.getHeight()) - (screenHeight - softHeight);
                                scrollToPos(0, buttonHeight);
                            } else {
                                scrollToPos(0, 0);
                            }
                        } else {
                            // 键盘隐藏
                            scrollToPos(0, 0);

                        }
                    }
                });
    }

    private void scrollToPos(int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(250);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                llContainer.scrollTo(0, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        animator.start();
    }
}
