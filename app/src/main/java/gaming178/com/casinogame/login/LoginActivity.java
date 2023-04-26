package gaming178.com.casinogame.login;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codersun.fingerprintcompat.AonFingerChangeCallback;
import com.codersun.fingerprintcompat.FingerManager;
import com.codersun.fingerprintcompat.SimpleFingerCheckCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorGravity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.LobbyActivity;
import gaming178.com.casinogame.Activity.RegisterActivity;
import gaming178.com.casinogame.Bean.GameMenuItem;
import gaming178.com.casinogame.Bean.Liga365AgentBean;
import gaming178.com.casinogame.Bean.NamePicBean;
import gaming178.com.casinogame.Bean.UserBean;
import gaming178.com.casinogame.Bean.UserLoginBean;
import gaming178.com.casinogame.Bean.UserResponseBean;
import gaming178.com.casinogame.Bean.WABean;
import gaming178.com.casinogame.Control.AutoScrollTextView;
import gaming178.com.casinogame.Popupwindow.PopImg;
import gaming178.com.casinogame.Popupwindow.PopImgTitleHint;
import gaming178.com.casinogame.Popupwindow.PopLanguage;
import gaming178.com.casinogame.Popupwindow.PopShowGame;
import gaming178.com.casinogame.Util.AllCapTransformationMethod;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.CircleAnimation;
import gaming178.com.casinogame.Util.ErrorCode;
import gaming178.com.casinogame.Util.Gd88Utils;
import gaming178.com.casinogame.Util.HttpClient;
import gaming178.com.casinogame.Util.PopMenu;
import gaming178.com.casinogame.Util.PopWebView;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyBannerAdapter;
import gaming178.com.casinogame.adapter.MyNetWorkBannerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.casinogame.entity.BannerBean;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.BlockDialog;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.allinone.util.GdToastUtils;
import gaming178.com.mylibrary.allinone.util.WidgetUtil;
import gaming178.com.mylibrary.lib.util.LogUtil;

/**
 * Created by Administrator on 2016/3/22.
 */
public class LoginActivity extends BaseActivity {
    double count = 38931592;
    int format = 0;
    HttpClient httpClient = new HttpClient("");
    private EditText tv_name;
    private EditText tv_password;
    private BlockDialog dialog;
    private Button btn_login;
    private String language;
    private String version;
    private View ll_language;
    private ImageView iv_language_flag;
    private TextView tv_register, tvWhatsApp, tvPromo, tvLiveChat;
    private View llContainer;
    private int[] sc;
    private View llBottomBtn;
    private int scrollHeight = 0;
    private HashMap<String, String> siteMap;
    private ImageView imgOpen;
    private ImageView img_gif;
    private ImageView img_login_title, img_login_title_main, gd_img_login_title_main_sbocasino77;
    private LinearLayout ll_liga365;
    private LinearLayout ll_remember_me;
    private CheckBox cb_remember_me;
    private WebView webView;
    private ImageView img_exit;
    private RelativeLayout rl_webview;
    private LinearLayout ll_lang, ll_lg_and_remember, ll_sbocasino77;
    private ImageView img_in;
    private ImageView img_en;
    private TextView tv_user_name, tv_password_name;
    private ImageView imgGif1, imgGif2;
    private boolean isNeedCount = true;
    private BannerViewPager bannerView;
    private LinearLayout ll_register_bg;
    private ImageView img_ula_enter_bg;
    private FrameLayout fl_whatsapp;
    private AutoScrollTextView hallGameBottomPromptTv;
    private RecyclerView rajaRc, rajaRc2;
    private TextView tvCount;
    private WABean currentWABean;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ErrorCode.LOGIN_SECCESS:
                    dismissLoginBlockDialog();
                    UserLoginBean dataBean = new UserLoginBean();
                    UserLoginBean userLoginBean = (UserLoginBean) AppTool.getObjectData(mContext, WebSiteUrl.Tag);
                    String usName = tv_name.getText().toString().trim();
                    if (userLoginBean == null || !TextUtils.isEmpty(usName)) {
                        String password = tv_password.getText().toString().trim();
                        EditText siteEdt = findViewById(R.id.gd__login_site_edt);
                        String site = siteEdt.getText().toString().trim();
                        dataBean.setSite(site);
                        dataBean.setUsername(usName);
                        dataBean.setPassword(password);
                    } else {
                        dataBean.setSite(userLoginBean.getSite());
                        dataBean.setUsername(userLoginBean.getUsername());
                        dataBean.setPassword(userLoginBean.getPassword());
                    }
                    boolean rememberMe = cb_remember_me.isChecked();
                    dataBean.setRememberMe(rememberMe);
                    AppTool.saveObjectData(mContext, WebSiteUrl.Tag, dataBean);
                    skipAct(LobbyActivity.class);
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
            rl_webview.setVisibility(View.GONE);
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
        tvWhatsApp = findViewById(R.id.gd__tv_whatsapp);
        tvPromo = findViewById(R.id.gd__tv_promo);
        if (BuildConfig.FLAVOR.equals("ratucasino88")) {
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.kilat).into(img_login_title);
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(1);
                }
            });
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(2);
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("pemain")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://rebrand.ly/pemainkasino");
                }
            });
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "https://direct.lc.chat/8668396/";
                        }

                        @Override
                        public String getTitle() {
                            return "LiveChat";
                        }
                    };
                    popWebView.showPopupCenterWindow();
                }
            });
            WidgetUtil.startScaleAnimation(tvWhatsApp);
            WidgetUtil.startScaleAnimation(tvPromo);
            ll_register_bg = findViewById(R.id.ll_register_bg);
            ObjectAnimator objectAnimator = WidgetUtil.startAlphaAnimation(ll_register_bg, 800);
            objectAnimator.start();
        }

        if (BuildConfig.FLAVOR.equals("menangcasino")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://rebrand.ly/menangcas");
                }
            });
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "https://direct.lc.chat/11428453/";
                        }

                        @Override
                        public String getTitle() {
                            return "LiveChat";
                        }
                    };
                    popWebView.showPopupCenterWindow();
                }
            });
            WidgetUtil.startScaleAnimation(tvWhatsApp);
            WidgetUtil.startScaleAnimation(tvPromo);
            ll_register_bg = findViewById(R.id.ll_register_bg);
            ObjectAnimator objectAnimator = WidgetUtil.startAlphaAnimation(ll_register_bg, 800);
            objectAnimator.start();
        }

        if (BuildConfig.FLAVOR.equals("serbacasino")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://wa.me/855977595518");
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("mastercasino88")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(1);
                }
            });
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(2);
                }
            });
            tvLiveChat = findViewById(R.id.gd__tv_live_chat);
            tvLiveChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(3);
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("rentalbaccarat")) {
            img_login_title.setVisibility(View.GONE);
        }

        if (BuildConfig.FLAVOR.equals("hobi")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://wa.me/855975299341");
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("cahaya")) {
            hallGameBottomPromptTv = findViewById(R.id.gd__hall_game_bottom_prompt_tv);
            hallGameBottomPromptTv.setSelected(true);
            hallGameBottomPromptTv.stopScroll();
            hallGameBottomPromptTv.setTextColor(Color.WHITE);
            hallGameBottomPromptTv.setSpeed(0.8f);
            bannerView = findViewById(R.id.banner_view);
            bannerView.post(new Runnable() {
                @Override
                public void run() {
                    int width = bannerView.getWidth();
                    ViewGroup.LayoutParams layoutParams = bannerView.getLayoutParams();
                    layoutParams.height = (int) (width / 3);
                    bannerView.setLayoutParams(layoutParams);
                }
            });
            new Thread() {
                @Override
                public void run() {
                    String url = "http://www.grjl25.com/getDomainInform.jsp?";
                    String param = "labelid=" + BuildConfig.Labelid;
                    String result = httpClient.getHttpClient(url + param, null);
                    WebSiteUrl.setNormal(result);
                    String annoucementParams = "lng=" + 0 + "&Usid=" + mAppViewModel.getUser().getName();
                    String annoucement = httpClient.sendPost(WebSiteUrl.GAME_GG_URL, annoucementParams);
                    url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "getSliderImg.jsp";
                    String bannerResult = httpClient.sendPost(url, "");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (annoucement.startsWith("Results=ok")) {
                                String[] split = annoucement.split("\\|");
                                hallGameBottomPromptTv.setText(split[1]);
                            } else {
                                hallGameBottomPromptTv.setText("Situs Bandar Casino Online Terkemuka Dengan Bonus Rollingan Dan Cashback Terbesar Di Indonesia Saat ini, Kami Menerima Semua Jenis Deposit Bank Dan E-Wallet.");
                            }
                            hallGameBottomPromptTv.init(hallGameBottomPromptTv.getWidth());
                            hallGameBottomPromptTv.startScroll();
                            List<String> imgList = new ArrayList<>();
                            if (bannerResult.contains("Success")) {
                                BannerBean bannerBean = new Gson().fromJson(bannerResult, BannerBean.class);
                                List<BannerBean.DataBean> data = bannerBean.getData();
                                if (data != null && data.size() > 0) {
                                    for (int i = 0; i < data.size(); i++) {
                                        BannerBean.DataBean dataBean = data.get(i);
                                        imgList.add(dataBean.getPath());
                                    }
                                } else {
                                    imgList.add("https://rejekibersaudara.com/assets/images/slider-selamat-datang.jpg");
                                    imgList.add("https://rejekibersaudara.com/assets/images/slider-20.jpg");
                                    imgList.add("https://rejekibersaudara.com/assets/images/slider-game.jpg");
                                    imgList.add("https://rejekibersaudara.com/assets/images/slider-roll.jpg");
                                    imgList.add("https://rejekibersaudara.com/assets/images/Slider-CK.jpg");
                                    imgList.add("https://rejekibersaudara.com/assets/images/slide11.jpg");
                                }
                            } else {
                                imgList.add("https://rejekibersaudara.com/assets/images/slider-selamat-datang.jpg");
                                imgList.add("https://rejekibersaudara.com/assets/images/slider-20.jpg");
                                imgList.add("https://rejekibersaudara.com/assets/images/slider-game.jpg");
                                imgList.add("https://rejekibersaudara.com/assets/images/slider-roll.jpg");
                                imgList.add("https://rejekibersaudara.com/assets/images/Slider-CK.jpg");
                                imgList.add("https://rejekibersaudara.com/assets/images/slide11.jpg");
                            }
                            bannerView.setLifecycleRegistry(getLifecycle()).
                                    setAdapter(new MyNetWorkBannerAdapter()).
                                    setScrollDuration(500).
                                    setIndicatorSliderColor(Color.WHITE,
                                            Color.parseColor("#FAE58C")).
                                    setIndicatorGravity(IndicatorGravity.CENTER).
                                    create(imgList);
                        }
                    });
                }
            }.start();
            FrameLayout flSlideLeft = findViewById(R.id.fl_slide_left);
            rajaRc = findViewById(R.id.rc_raja);
            rajaRc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            List<NamePicBean> list = new ArrayList<>();
            list.add(new NamePicBean("SLOTS", R.mipmap.cayaha_slots));
            list.add(new NamePicBean("LIVE CASINO", R.mipmap.cayaha_casino));
            list.add(new NamePicBean("SPORTS", R.mipmap.cayaha_sports));
            list.add(new NamePicBean("COCK FIGHT", R.mipmap.cayaha_hot_games));
            list.add(new NamePicBean("POKER", R.mipmap.cayaha_poker));
            BaseRecyclerAdapter<NamePicBean> adapter = new BaseRecyclerAdapter<NamePicBean>(mContext, list, R.layout.item_raja_show) {
                @Override
                public void convert(MyRecyclerViewHolder holder, int position, NamePicBean item) {
                    LinearLayout ll_parent = holder.getLinearLayout(R.id.ll_parent);
                    ll_parent.post(new Runnable() {
                        @Override
                        public void run() {
                            int height = flSlideLeft.getHeight();
                            ViewGroup.LayoutParams layoutParams = ll_parent.getLayoutParams();
                            layoutParams.height = height;
                            layoutParams.width = (bannerView.getWidth() - flSlideLeft.getWidth() * 2) / 5;
                            ll_parent.setLayoutParams(layoutParams);
                        }
                    });
                    ImageView imageView = holder.getImageView(R.id.img);
                    TextView textView = holder.getTextView(R.id.tv);
                    textView.setText(item.getName());
                    imageView.setImageResource(item.getPic());
                }
            };
            rajaRc.setAdapter(adapter);
            gd_img_login_title_main_sbocasino77 = findViewById(R.id.gd_img_login_title_main_sbocasino77);
            gd_img_login_title_main_sbocasino77.post(new Runnable() {
                @Override
                public void run() {
                    int width = gd_img_login_title_main_sbocasino77.getWidth();
                    ViewGroup.LayoutParams layoutParams = gd_img_login_title_main_sbocasino77.getLayoutParams();
                    layoutParams.width = width;
                    layoutParams.height = (int) (width / 3.5);
                    gd_img_login_title_main_sbocasino77.setLayoutParams(layoutParams);
                    Glide.with(LoginActivity.this).asGif().load(R.mipmap.cayaha_login_animation).into(gd_img_login_title_main_sbocasino77);
                }
            });
            tvCount = findViewById(R.id.gd_tv_count);
            count = 38864610;
            new Thread() {
                @Override
                public void run() {
                    while (isNeedCount) {
                        try {
                            Thread.sleep(10);
                            format++;
                            if (format == 100) {
                                format = 0;
                                count++;
                            }
                            getHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    String a = Gd88Utils.formatTosepara(count);
                                    tvCount.setText("IDR " + a + "." + (format < 10 ? ("0" + format) : format));
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        if (BuildConfig.FLAVOR.equals("wargacasino")) {
            img_login_title_main = findViewById(R.id.gd_img_login_title_main);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.war_gif).into(img_login_title_main);
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://jfa.one/rtp-wargacasino");
                }
            });
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://bit.ly/WA-Wargacas1");
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("oricasino")) {
            img_login_title_main = findViewById(R.id.gd_img_login_title_main);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.form_bg_ori).into(img_login_title_main);
            img_ula_enter_bg = findViewById(R.id.img_ula_enter_bg);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.login_ori_bg).into(img_ula_enter_bg);
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(1);
                }
            });
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(2);
                }
            });

            RecyclerView recyclerView = findViewById(R.id.rc_liveCasino);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            List<GameMenuItem> list = new ArrayList();
            list.add(new GameMenuItem(R.mipmap.ori_poker, "", ""));
            list.add(new GameMenuItem(R.mipmap.ori_slot, "", ""));
            list.add(new GameMenuItem(R.mipmap.ori_casino, "", ""));
            list.add(new GameMenuItem(R.mipmap.ori_sports, "", ""));
            list.add(new GameMenuItem(R.mipmap.ori_sabung_ayam, "", ""));
            BaseRecyclerAdapter<GameMenuItem> adapter = new BaseRecyclerAdapter<GameMenuItem>(mContext, list, R.layout.ori_game_item) {
                @Override
                public void convert(MyRecyclerViewHolder holder, int position, GameMenuItem item) {
                    ImageView imageView = holder.getImageView(R.id.img);
                    imageView.setImageResource(item.getDrawableRes());
                    View view = holder.getView(R.id.view);
                    if (position == list.size() - 1) {
                        view.setVisibility(View.GONE);
                    } else {
                        view.setVisibility(View.VISIBLE);
                    }
                }
            };
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<GameMenuItem>() {
                @Override
                public void onItemClick(View view, GameMenuItem item, int position) {
                    PopShowGame popShowGame = new PopShowGame(mContext, recyclerView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    popShowGame.showContent(position);
                    popShowGame.showPopupDownWindow(R.style.popWindow_animation_Top_Center);
                }
            });
            recyclerView.setAdapter(adapter);

            tvCount = findViewById(R.id.gd_tv_count);
            count = 829466500;
            new Thread() {
                @Override
                public void run() {
                    while (isNeedCount) {
                        try {
                            Thread.sleep(2);
                            format++;
                            if (format == 3) {
                                format = 0;
                                count++;
                            }
                            getHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    String a = Gd88Utils.formatTosepara(count);
                                    tvCount.setText(a);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        if (BuildConfig.FLAVOR.equals("hitamslot")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(-1);
                }
            });
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "https://direct.lc.chat/13218531/";
                        }

                        @Override
                        public String getTitle() {
                            return "LIVECHAT";
                        }
                    };
                    popWebView.showPopupCenterWindow();
                }
            });
            tv_name = (EditText) this.findViewById(R.id.gd__login_username_edt);
            tv_name.setTransformationMethod(new AllCapTransformationMethod());
        }

        if (BuildConfig.FLAVOR.equals("casino388")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://api.whatsapp.com/send/?phone=6282298087754&text&app_absent=0");
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("kasino365")) {
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.gif_365_logo).into(img_login_title);
            WebView wb_notice = findViewById(R.id.wb_notice);
            WebView wb_banner = findViewById(R.id.wb_banner);
            WebView wb_game = findViewById(R.id.wb_game);
            loadWebView(wb_notice, "https://adminiframe.xyz/website/running_text");
            loadWebView(wb_banner, "https://www.adminiframe.xyz/website/sliders");
            loadWebView(wb_game, "https://www.adminiframe.xyz/website/slot_updates");
            goNetWA(4);

            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(1);
                }
            });
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(2);
                }
            });
            img_exit = findViewById(R.id.gd_img_exit);
            Glide.with(LoginActivity.this).asGif().load("https://i0.wp.com/luckydragon.quest/assets/images/website/365luckywheel.gif").into(img_exit);
            img_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(3);
                }
            });

            gd_img_login_title_main_sbocasino77 = findViewById(R.id.gd_img_login_title_main_sbocasino77);
            Glide.with(mContext).asGif()
                    .load("https://misterhoki08.github.io/master365/assets/telegram.gif")
                    .apply(new RequestOptions()
                            .circleCropTransform())
                    .into(gd_img_login_title_main_sbocasino77);
            gd_img_login_title_main_sbocasino77.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://t.me/infoslotharian88");
                }
            });
            initRightLang();
        }

        if (BuildConfig.FLAVOR.equals("hokicasino88")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://api.whatsapp.com/send/?phone=6282187616272&text&app_absent=0");
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("doacasino")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://api.whatsapp.com/send/?phone=85586490002");
                }
            });
            gd_img_login_title_main_sbocasino77 = findViewById(R.id.gd_img_login_title_main_sbocasino77);
            goNetWA(1);
            gd_img_login_title_main_sbocasino77.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(2);
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("gd88") || BuildConfig.FLAVOR.equals("liga365")) {
            img_login_title.setImageResource(R.mipmap.gd_app_logo);
        } else {
            tv_register.setVisibility(View.VISIBLE);
            if (!BuildConfig.FLAVOR.equals("ratucasino88") &&
                    !BuildConfig.FLAVOR.equals("kasino365") && !BuildConfig.FLAVOR.equals("mainkasino") &&
                    !BuildConfig.FLAVOR.equals("memoricasino")) {
                img_login_title.setImageResource(R.mipmap.gd_title_logo);
            }
        }
        img_gif = findViewById(R.id.gd_img_gif);
        imgOpen = findViewById(R.id.gd_img_open);
        btn_login = (Button) findViewById(R.id.gd__login_login_btn);
        if (BuildConfig.FLAVOR.equals("mainkasino")) {
            gd_img_login_title_main_sbocasino77 = findViewById(R.id.gd_img_login_title_main_sbocasino77);
            gd_img_login_title_main_sbocasino77.post(new Runnable() {
                @Override
                public void run() {
                    int width = gd_img_login_title_main_sbocasino77.getWidth();
                    ViewGroup.LayoutParams layoutParams = gd_img_login_title_main_sbocasino77.getLayoutParams();
                    int height = (int) (width * 1.05);
                    layoutParams.height = height;
                    gd_img_login_title_main_sbocasino77.setLayoutParams(layoutParams);
                }
            });
            ImageView imgWhatsApp = findViewById(R.id.img_whatsapp);
            ImageView imgFB = findViewById(R.id.img_fb);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.layanan_vip).into(imgWhatsApp);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.logo_mainkasino).into(img_login_title);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.fb_messager).into(imgFB);
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
                            PopImg popImg = new PopImg(LoginActivity.this, btn_login, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            popImg.setLoadUrl(result + "images/popup.gif");
                            popImg.showPopupCenterWindow();
                        }
                    });
                }
            }.start();
            ObjectAnimator objectScaleX = ObjectAnimator.ofFloat(tvPromo, "scaleX", 1, (float) 0.85, 1);
            objectScaleX.setDuration(800);
            objectScaleX.setRepeatCount(Animation.INFINITE);
            ObjectAnimator objectScaleY = ObjectAnimator.ofFloat(tvPromo, "scaleY", 1, (float) 0.85, 1);
            objectScaleY.setDuration(800);
            objectScaleY.setRepeatCount(Animation.INFINITE);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectScaleX, objectScaleY);
            animatorSet.start();
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(1);
                }
            });
            imgWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(2);
                }
            });
            imgFB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(3);
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("rajacasino")) {
            img_login_title_main = findViewById(R.id.gd_img_login_title_main);
            img_login_title_main.post(new Runnable() {
                @Override
                public void run() {
                    int width = img_login_title_main.getWidth();
                    ViewGroup.LayoutParams layoutParams = img_login_title_main.getLayoutParams();
                    layoutParams.height = (int) (width * 0.95);
                    img_login_title_main.setLayoutParams(layoutParams);
                }
            });
            RelativeLayout rl_whatsapp = findViewById(R.id.rl_whatsapp);
            RelativeLayout rl_live_chat = findViewById(R.id.rl_live_chat);
            rl_whatsapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://wa.me/6287829675973");
                }
            });
            rl_live_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "https://direct.lc.chat/8843331/";
                        }

                        @Override
                        public String getTitle() {
                            return "LIVECHAT";
                        }
                    };
                    popWebView.showPopupCenterWindow();
                }
            });
            initRightLang();
        }

        if (BuildConfig.FLAVOR.equals("rajabakarat") || BuildConfig.FLAVOR.equals("idrkasino")) {
            hallGameBottomPromptTv = findViewById(R.id.gd__hall_game_bottom_prompt_tv);
            hallGameBottomPromptTv.setSelected(true);
            hallGameBottomPromptTv.stopScroll();
            if (BuildConfig.FLAVOR.equals("idrkasino")) {
                hallGameBottomPromptTv.setText("Welcome to Idrkasino Situs Judi Bola Sbobet dan Slot Online.   Pemeliharaan Terjadwal: Playtech pada 2021-11-15 dari 8:00 PM sampai 2021-11-30 8:00 PM (GMT + 7). Selama waktu ini, Playtech permainan tidak akan tersedia. Kami memohon maaf atas ketidaknyamanan yang mungkin ditimbulkan.");
                hallGameBottomPromptTv.setTextColor(ContextCompat.getColor(mContext, R.color.login_color));
            } else {
                hallGameBottomPromptTv.setText("Welcome to Rajabaccarat Situs Judi Casino Slot Online Terpercaya Indonesia.   Pemeliharaan Terjadwal: Playtech pada 2021-11-15 dari 8:00 PM sampai 2021-11-30 8:00 PM (GMT + 7). Selama waktu ini, Playtech permainan tidak akan tersedia. Kami memohon maaf atas ketidaknyamanan yang mungkin ditimbulkan.");
                hallGameBottomPromptTv.setTextColor(Color.parseColor("#DC9A04"));
            }
            hallGameBottomPromptTv.setSpeed(0.8f);
            hallGameBottomPromptTv.init(hallGameBottomPromptTv.getWidth());
            hallGameBottomPromptTv.startScroll();
            bannerView = findViewById(R.id.banner_view);
            bannerView.post(new Runnable() {
                @Override
                public void run() {
                    int width = bannerView.getWidth();
                    ViewGroup.LayoutParams layoutParams = bannerView.getLayoutParams();
                    List<Integer> imgList = new ArrayList<>();
                    int indicatorSliderColor;
                    if (BuildConfig.FLAVOR.equals("idrkasino")) {
                        layoutParams.height = (int) (width / 3.13);
                        imgList.add(R.mipmap.idr_s1);
                        imgList.add(R.mipmap.idr_s2);
                        imgList.add(R.mipmap.idr_s3);
                        indicatorSliderColor = ContextCompat.getColor(mContext, R.color.login_color);
                    } else {
                        layoutParams.height = (int) (width / 2.14);
                        imgList.add(R.mipmap.raja_m1);
                        imgList.add(R.mipmap.raja_m2);
                        imgList.add(R.mipmap.raja_m3);
                        imgList.add(R.mipmap.raja_m4);
                        indicatorSliderColor = ContextCompat.getColor(mContext, R.color.yellow_gold2);
                    }
                    bannerView.setLayoutParams(layoutParams);
                    bannerView.setLifecycleRegistry(getLifecycle()).
                            setAdapter(new MyBannerAdapter()).
                            setScrollDuration(500).
                            setIndicatorSliderColor(getResources().getColor(R.color.white),
                                    indicatorSliderColor).
                            setIndicatorGravity(IndicatorGravity.CENTER).
                            create(imgList);
                }
            });

            rajaRc = findViewById(R.id.rc_raja);
            rajaRc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            List<NamePicBean> list = new ArrayList<>();
            list.add(new NamePicBean("Hot Games", R.mipmap.raja_hot_games));
            list.add(new NamePicBean("Slots", R.mipmap.raja_slots));
            list.add(new NamePicBean("Live Casino", R.mipmap.raja_casino));
            list.add(new NamePicBean("Sports", R.mipmap.raja_sports));
            list.add(new NamePicBean("Arcade", R.mipmap.raja_arcade));
            list.add(new NamePicBean("Poker", R.mipmap.raja_poker));
            list.add(new NamePicBean("Togel", R.mipmap.raja_others));
            list.add(new NamePicBean("Live Tv", R.mipmap.raja_live_tv));
            BaseRecyclerAdapter<NamePicBean> adapter = new BaseRecyclerAdapter<NamePicBean>(mContext, list, R.layout.item_raja_show) {
                @Override
                public void convert(MyRecyclerViewHolder holder, int position, NamePicBean item) {
                    ImageView imageView = holder.getImageView(R.id.img);
                    TextView textView = holder.getTextView(R.id.tv);
                    textView.setText(item.getName());
                    imageView.setImageResource(item.getPic());
                }
            };
            rajaRc.setAdapter(adapter);
            FrameLayout flSlideLeft = findViewById(R.id.fl_slide_left);
            FrameLayout flSlideRight = findViewById(R.id.fl_slide_right);
            flSlideLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rajaRc.scrollBy(-100, 0);
                }
            });
            flSlideRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rajaRc.scrollBy(100, 0);
                }
            });
            ImageView imgSlide1 = findViewById(R.id.img_slide1);
            ImageView imgSlide2 = findViewById(R.id.img_slide2);
            if (BuildConfig.FLAVOR.equals("idrkasino")) {
                imgSlide1.setVisibility(View.GONE);
                imgSlide2.setVisibility(View.GONE);
            } else {
                imgSlide1.post(new Runnable() {
                    @Override
                    public void run() {
                        int width1 = imgSlide1.getWidth();
                        int width2 = imgSlide2.getWidth();
                        int leftMargin = width1 - width2;
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imgSlide2.getLayoutParams();
                        layoutParams.setMargins(leftMargin, layoutParams.topMargin, 0, 0);
                        imgSlide2.setLayoutParams(layoutParams);
                        imgSlide2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int left = imgSlide2.getLeft();
                                if (left == leftMargin) {
                                    WidgetUtil.leftMarginsAnimation(imgSlide2, leftMargin, 0);
                                } else {
                                    WidgetUtil.leftMarginsAnimation(imgSlide2, 0, leftMargin);
                                }
                            }
                        });

                    }
                });
            }
            FrameLayout flJackpot = findViewById(R.id.fl_jackpot);
            flJackpot.post(new Runnable() {
                @Override
                public void run() {
                    int width = flJackpot.getWidth();
                    ViewGroup.LayoutParams layoutParams = flJackpot.getLayoutParams();
                    layoutParams.height = (int) (width / 5.3);
                    flJackpot.setLayoutParams(layoutParams);
                }
            });
            tvCount = findViewById(R.id.gd_tv_count);
            new Thread() {
                @Override
                public void run() {
                    while (isNeedCount) {
                        try {
                            Thread.sleep(10);
                            format++;
                            if (format == 100) {
                                format = 0;
                                count++;
                            }
                            getHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    String a = Gd88Utils.formatTosepara(count);
                                    tvCount.setText("IDR " + a + "." + (format < 10 ? ("0" + format) : format));
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            rajaRc2 = findViewById(R.id.rc_raja2);
            rajaRc2.setNestedScrollingEnabled(false);
            rajaRc2.setLayoutManager(new GridLayoutManager(this, 3));
            List<NamePicBean> listSlot = new ArrayList<>();
            listSlot.add(new NamePicBean("Gates Of Olympus", R.mipmap.raja_slot_1));
            listSlot.add(new NamePicBean("Roma", R.mipmap.raja_slot_2));
            listSlot.add(new NamePicBean("Koi Gate", R.mipmap.raja_slot_3));
            listSlot.add(new NamePicBean("Wild West Gold", R.mipmap.raja_slot_4));
            listSlot.add(new NamePicBean("Golden Dragon", R.mipmap.raja_slot_5));
            listSlot.add(new NamePicBean("Sweet Bonanza", R.mipmap.raja_slot_6));
            BaseRecyclerAdapter<NamePicBean> adapterSlot = new BaseRecyclerAdapter<NamePicBean>(mContext, listSlot, R.layout.item_raja_show_slot) {
                @Override
                public void convert(MyRecyclerViewHolder holder, int position, NamePicBean item) {
                    ImageView imageView = holder.getImageView(R.id.img);
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            int width = imageView.getWidth();
                            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                            layoutParams.height = width;
                            imageView.setLayoutParams(layoutParams);
                            imageView.setImageResource(item.getPic());
                        }
                    });
                    TextView textView = holder.getTextView(R.id.tv);
                    textView.setText(item.getName());
                }
            };
            rajaRc2.setAdapter(adapterSlot);
            initRightLang();
        }

        if (BuildConfig.FLAVOR.equals("indkasino")) {
            img_login_title_main = findViewById(R.id.gd_img_login_title_main);
            CircleAnimation circleAnimation = new CircleAnimation(40);
            circleAnimation.setDuration(8000);
            circleAnimation.setRepeatCount(-1);
            circleAnimation.setInterpolator(new LinearInterpolator());
            img_login_title_main.startAnimation(circleAnimation);
            gd_img_login_title_main_sbocasino77 = findViewById(R.id.gd_img_login_title_main_sbocasino77);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(gd_img_login_title_main_sbocasino77, "translationY", 0, -30, 0);
            objectAnimatorY.setDuration(5000);
            objectAnimatorY.setRepeatCount(Animation.INFINITE);
            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(gd_img_login_title_main_sbocasino77, "translationX", 0, 30, 0);
            objectAnimatorX.setDuration(5000);
            objectAnimatorX.setRepeatCount(Animation.INFINITE);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimatorX, objectAnimatorY);
            animatorSet.start();

            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://bit.ly/whatsappindkasino");
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("memoricasino")) {
            imgGif1 = findViewById(R.id.img_gif_1);
            imgGif1.post(new Runnable() {
                @Override
                public void run() {
                    ViewGroup.LayoutParams layoutParams = imgGif1.getLayoutParams();
                    layoutParams.height = (int) (imgGif1.getWidth() / 1.4);
                    imgGif1.setLayoutParams(layoutParams);
                }
            });

            img_login_title_main = findViewById(R.id.gd_img_login_title_main);
            CircleAnimation circleAnimation = new CircleAnimation(40);
            circleAnimation.setDuration(8000);
            circleAnimation.setRepeatCount(-1);
            circleAnimation.setInterpolator(new LinearInterpolator());
            img_login_title_main.startAnimation(circleAnimation);
            LinearLayout llAnimation = findViewById(R.id.ll_animation);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(llAnimation, "translationY", 0, -60, 0);
            objectAnimatorY.setDuration(7000);
            objectAnimatorY.setRepeatCount(Animation.INFINITE);
            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(llAnimation, "translationX", 0, 60, 0);
            objectAnimatorX.setDuration(7000);
            objectAnimatorX.setRepeatCount(Animation.INFINITE);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimatorX, objectAnimatorY);
            animatorSet.start();

            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://bit.ly/2wD1ha7");
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("wingsbet88")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(-2);
                }
            });
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNetWA(-3);
                }
            });
            goNetWA(-1);
        }
        if (BuildConfig.FLAVOR.equals("rascasino")) {
            bannerView = findViewById(R.id.banner_view);
            bannerView.post(new Runnable() {
                @Override
                public void run() {
                    int width = bannerView.getWidth();
                    ViewGroup.LayoutParams layoutParams = bannerView.getLayoutParams();
                    layoutParams.height = (int) (width / 3.5);
                    bannerView.setLayoutParams(layoutParams);
                }
            });
            hallGameBottomPromptTv = findViewById(R.id.gd__hall_game_bottom_prompt_tv);
            hallGameBottomPromptTv.setSelected(true);
            hallGameBottomPromptTv.stopScroll();
            hallGameBottomPromptTv.setTextColor(Color.WHITE);
            hallGameBottomPromptTv.setSpeed(0.8f);
            new Thread() {
                @Override
                public void run() {
                    String url = "http://www.grjl25.com/getDomainInform.jsp?";
                    String param = "labelid=" + BuildConfig.Labelid;
                    String result = httpClient.getHttpClient(url + param, null);
                    WebSiteUrl.setNormal(result);
                    String annoucementParams = "lng=" + 0 + "&Usid=" + mAppViewModel.getUser().getName();
                    String annoucement = httpClient.sendPost(WebSiteUrl.GAME_GG_URL, annoucementParams);
                    url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "getSliderImg.jsp";
                    String bannerResult = httpClient.sendPost(url, "");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (annoucement.startsWith("Results=ok")) {
                                String[] split = annoucement.split("\\|");
                                hallGameBottomPromptTv.setText(split[1]);
                            } else {
                                hallGameBottomPromptTv.setText("Deposit Pulsa tanpa ada potongan disini....");
                            }
                            hallGameBottomPromptTv.init(hallGameBottomPromptTv.getWidth());
                            hallGameBottomPromptTv.startScroll();
                            if (bannerResult.contains("Success")) {
                                BannerBean bannerBean = new Gson().fromJson(bannerResult, BannerBean.class);
                                List<BannerBean.DataBean> data = bannerBean.getData();
                                if (data != null && data.size() > 0) {
                                    List<String> lists = new ArrayList<>();
                                    for (int i = 0; i < data.size(); i++) {
                                        BannerBean.DataBean dataBean = data.get(i);
                                        lists.add(dataBean.getPath());
                                    }
                                    bannerView.setLifecycleRegistry(getLifecycle()).
                                            setAdapter(new MyNetWorkBannerAdapter()).
                                            setScrollDuration(500).
                                            setIndicatorSliderColor(Color.parseColor("#00000000"),
                                                    Color.parseColor("#00000000")).
                                            setIndicatorGravity(IndicatorGravity.CENTER).
                                            create(lists);
                                }
                            }
                        }
                    });
                }
            }.start();
            List<NamePicBean> listPra = Arrays.asList(new NamePicBean("", R.mipmap.gd_pra_1), new NamePicBean("", R.mipmap.gd_pra_2),
                    new NamePicBean("", R.mipmap.gd_pra_3), new NamePicBean("", R.mipmap.gd_pra_4), new NamePicBean("", R.mipmap.gd_pra_5),
                    new NamePicBean("", R.mipmap.gd_pra_6), new NamePicBean("", R.mipmap.gd_pra_7), new NamePicBean("", R.mipmap.gd_pra_8),
                    new NamePicBean("", R.mipmap.gd_pra_9), new NamePicBean("", R.mipmap.gd_pra_10), new NamePicBean("", R.mipmap.gd_pra_11),
                    new NamePicBean("", R.mipmap.gd_pra_12), new NamePicBean("", R.mipmap.gd_pra_13), new NamePicBean("", R.mipmap.gd_pra_14),
                    new NamePicBean("", R.mipmap.gd_pra_15));
            List<NamePicBean> listPg = Arrays.asList(new NamePicBean("", R.mipmap.gd_pg_s1), new NamePicBean("", R.mipmap.gd_pg_s2),
                    new NamePicBean("", R.mipmap.gd_pg_s3), new NamePicBean("", R.mipmap.gd_pg_s4), new NamePicBean("", R.mipmap.gd_pg_s5),
                    new NamePicBean("", R.mipmap.gd_pg_s6), new NamePicBean("", R.mipmap.gd_pg_s7), new NamePicBean("", R.mipmap.gd_pg_s8),
                    new NamePicBean("", R.mipmap.gd_pg_s9), new NamePicBean("", R.mipmap.gd_pg_s10), new NamePicBean("", R.mipmap.gd_pg_s11),
                    new NamePicBean("", R.mipmap.gd_pg_s12));
            List<NamePicBean> listCQ9 = Arrays.asList(new NamePicBean("", R.mipmap.gd_ca9_a1), new NamePicBean("", R.mipmap.gd_ca9_a2),
                    new NamePicBean("", R.mipmap.gd_ca9_a3), new NamePicBean("", R.mipmap.gd_ca9_a4), new NamePicBean("", R.mipmap.gd_ca9_a5),
                    new NamePicBean("", R.mipmap.gd_ca9_a6), new NamePicBean("", R.mipmap.gd_ca9_a7), new NamePicBean("", R.mipmap.gd_ca9_a8));
            List<NamePicBean> listHaba = Arrays.asList(new NamePicBean("", R.mipmap.gd_haba_h1), new NamePicBean("", R.mipmap.gd_haba_h2),
                    new NamePicBean("", R.mipmap.gd_haba_h3), new NamePicBean("", R.mipmap.gd_haba_h4), new NamePicBean("", R.mipmap.gd_haba_h5),
                    new NamePicBean("", R.mipmap.gd_haba_h6), new NamePicBean("", R.mipmap.gd_haba_h7), new NamePicBean("", R.mipmap.gd_haba_h8),
                    new NamePicBean("", R.mipmap.gd_haba_h9));
            List<NamePicBean> listSport = new ArrayList<>();
            listSport.add(new NamePicBean("", R.mipmap.ras_sports));
            List<NamePicBean> listAYAM = new ArrayList<>();
            listAYAM.add(new NamePicBean("", R.mipmap.ras_ayan));
            List<NamePicBean> listCasino = Arrays.asList(new NamePicBean("", R.mipmap.gd_casino_1), new NamePicBean("", R.mipmap.gd_casino_2),
                    new NamePicBean("", R.mipmap.gd_casino_3), new NamePicBean("", R.mipmap.gd_casino_4), new NamePicBean("", R.mipmap.gd_casino_5));
            rajaRc2 = findViewById(R.id.rc_raja2);
            rajaRc2.setNestedScrollingEnabled(false);
            rajaRc2.setLayoutManager(new GridLayoutManager(this, 4));
            List<NamePicBean> currentList = new ArrayList<>();
            currentList.addAll(listPra);
            BaseRecyclerAdapter<NamePicBean> adapterSlot = new BaseRecyclerAdapter<NamePicBean>(mContext, currentList, R.layout.item_raja_show_slot) {
                @Override
                public void convert(MyRecyclerViewHolder holder, int position, NamePicBean item) {
                    ImageView imageView = holder.getImageView(R.id.img);
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            int width = imageView.getWidth();
                            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                            layoutParams.height = (int) (width * 1.05);
                            imageView.setLayoutParams(layoutParams);
                            imageView.setBackgroundResource(item.getPic());
                        }
                    });
                }
            };
            adapterSlot.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<NamePicBean>() {
                @Override
                public void onItemClick(View view, NamePicBean item, int position) {
                    PopImgTitleHint popImgTitleHint = new PopImgTitleHint(mContext, logoutTv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    popImgTitleHint.showPopupCenterWindow();
                }
            });
            rajaRc2.setAdapter(adapterSlot);
            rajaRc = findViewById(R.id.rc_raja);
            rajaRc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            List<NamePicBean> list = new ArrayList<>();
            list.add(new NamePicBean("PRAGMATIC", R.mipmap.ras_pplay));
            list.add(new NamePicBean("PG SOFT", R.mipmap.ras_pg));
            list.add(new NamePicBean("CQ9", R.mipmap.ras_cq9));
            list.add(new NamePicBean("HABANERO", R.mipmap.ras_hb));
            list.add(new NamePicBean("SPORTBOOK", R.mipmap.ras_sport));
            list.add(new NamePicBean("SABUNG AYAM", R.mipmap.ras_sabungayam));
            list.add(new NamePicBean("CASINO", R.mipmap.ras_casino));
            BaseRecyclerAdapter<NamePicBean> adapter = new BaseRecyclerAdapter<NamePicBean>(mContext, list, R.layout.item_raja_show) {
                @Override
                public void convert(MyRecyclerViewHolder holder, int position, NamePicBean item) {
                    ImageView imageView = holder.getImageView(R.id.img);
                    TextView textView = holder.getTextView(R.id.tv);
                    textView.setText(item.getName());
                    imageView.setBackgroundResource(item.getPic());
                }
            };
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<NamePicBean>() {
                @Override
                public void onItemClick(View view, NamePicBean item, int position) {
                    switch (position) {
                        case 0:
                            adapterSlot.addAllAndClear(listPra);
                            break;
                        case 1:
                            adapterSlot.addAllAndClear(listPg);
                            break;
                        case 2:
                            adapterSlot.addAllAndClear(listCQ9);
                            break;
                        case 3:
                            adapterSlot.addAllAndClear(listHaba);
                            break;
                        case 4:
                            adapterSlot.addAllAndClear(listSport);
                            break;
                        case 5:
                            adapterSlot.addAllAndClear(listAYAM);
                            break;
                        case 6:
                            adapterSlot.addAllAndClear(listCasino);
                            break;
                    }
                }
            });
            rajaRc.setAdapter(adapter);
            View viewPro = findViewById(R.id.ll_pro);
            View viewLc = findViewById(R.id.ll_lc);
            viewPro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getNetPromo();
                }
            });
            viewLc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, tvWhatsApp, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "https://direct.lc.chat/13861980/";
                        }

                        @Override
                        public String getTitle() {
                            return "Live Chat";
                        }
                    };
                    popWebView.showPopupCenterWindow();
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("ahlicasino")) {
            Glide.with(this).asGif().load(R.mipmap.ahlicasino_logo_gif).into(img_gif);
            LinearLayout ll_login_prent = findViewById(R.id.ll_login_prent);
            LinearLayout ll_ahl_login = findViewById(R.id.ll_ahl_login);
            ll_login_prent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            ll_ahl_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_login_prent.setVisibility(View.VISIBLE);
                }
            });
            LinearLayout ll_login_prent_exit = findViewById(R.id.ll_login_prent_exit);
            ll_login_prent_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_login_prent.setVisibility(View.GONE);
                }
            });
            LinearLayout gd__ll_container = findViewById(R.id.gd__ll_container);
            gd__ll_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_login_prent.setVisibility(View.GONE);
                }
            });
            FrameLayout fl_name_out_bg = findViewById(R.id.fl_name_out_bg);
            EditText tvName = findViewById(R.id.gd__login_username_edt);
            tvName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        fl_name_out_bg.setVisibility(View.VISIBLE);
                    } else {
                        fl_name_out_bg.setVisibility(View.GONE);
                    }
                }
            });
            FrameLayout fl_password_out_bg = findViewById(R.id.fl_password_out_bg);
            EditText tvPassword = findViewById(R.id.gd__login_password_edt);
            tvPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        fl_password_out_bg.setVisibility(View.VISIBLE);
                    } else {
                        fl_password_out_bg.setVisibility(View.GONE);
                    }
                }
            });

            bannerView = findViewById(R.id.banner_view);
            bannerView.post(new Runnable() {
                @Override
                public void run() {
                    int width = bannerView.getWidth();
                    ViewGroup.LayoutParams layoutParams = bannerView.getLayoutParams();
                    layoutParams.height = (int) (width / 2.8);
                    bannerView.setLayoutParams(layoutParams);
                }
            });
            hallGameBottomPromptTv = findViewById(R.id.gd__hall_game_bottom_prompt_tv);
            hallGameBottomPromptTv.setSelected(true);
            hallGameBottomPromptTv.stopScroll();
            hallGameBottomPromptTv.setTextColor(Color.WHITE);
            hallGameBottomPromptTv.setSpeed(0.8f);
            List<Integer> lists = new ArrayList<>();
            lists.add(R.mipmap.ahl_slide1);
            lists.add(R.mipmap.ahl_slide2);
            lists.add(R.mipmap.ahl_slide3);
            bannerView.setLifecycleRegistry(getLifecycle()).
                    setAdapter(new MyBannerAdapter()).
                    setScrollDuration(500).
                    setIndicatorSliderColor(Color.parseColor("#00000000"),
                            Color.parseColor("#00000000")).
                    setIndicatorGravity(IndicatorGravity.CENTER).
                    create(lists);
            new Thread() {
                @Override
                public void run() {
                    String url = "http://www.grjl25.com/getDomainInform.jsp?";
                    String param = "labelid=" + BuildConfig.Labelid;
                    String result = httpClient.getHttpClient(url + param, null);
                    WebSiteUrl.setNormal(result);
                    String annoucementParams = "lng=" + 0 + "&Usid=" + mAppViewModel.getUser().getName();
                    String annoucement = httpClient.sendPost(WebSiteUrl.GAME_GG_URL, annoucementParams);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (annoucement.startsWith("Results=ok")) {
                                String[] split = annoucement.split("\\|");
                                hallGameBottomPromptTv.setText(split[1]);
                            } else {
                                hallGameBottomPromptTv.setText("Selamat Datang di Ahlicasino Situs Judi Online Terbesar di Indonesia, Menyediakan permainan seperti : BACCARAT, ROULETTE, SICBO, DRAGON-TIGER, SLOT, AFB SPORT, KING KONG, PRAGMATIC, WE1POKER. Yang Terbaru Habanero dan Slot PGSoft. / Link alternatif : http://ahlikasino88.biz/ atau http://113.130.125.234");
                            }
                            hallGameBottomPromptTv.init(hallGameBottomPromptTv.getWidth());
                            hallGameBottomPromptTv.startScroll();
                        }
                    });
                }
            }.start();

            LinearLayout ll_ahl_content = findViewById(R.id.ll_ahl_content);
            ll_ahl_content.post(new Runnable() {
                @Override
                public void run() {
                    ViewGroup.LayoutParams layoutParams = ll_ahl_content.getLayoutParams();
                    layoutParams.height = ll_ahl_content.getWidth() / 2;
                    ll_ahl_content.setLayoutParams(layoutParams);
                }
            });
            LinearLayout ll_wa = findViewById(R.id.ll_wa);
            LinearLayout ll_tg = findViewById(R.id.ll_tg);
            LinearLayout ll_liveChat = findViewById(R.id.ll_liveChat);
            ll_wa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://wa.me/85595306890");
                }
            });
            ll_tg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://t.me/ahlicasino");
                }
            });
            ll_liveChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://secure.livechatinc.com/licence/8667886/v2/open_chat.cgi");
                }
            });

            ImageView img_animation1 = findViewById(R.id.img_animation1);
            ImageView img_animation2 = findViewById(R.id.img_animation2);
            ImageView img_animation3 = findViewById(R.id.img_animation3);
            ImageView img_animation4 = findViewById(R.id.img_animation4);
            ahlTranslationY(img_animation1);
            ahlTranslationY(img_animation2);
            ahlTranslationY(img_animation3);
            ahlTranslationY(img_animation4);
        }

        if (BuildConfig.FLAVOR.equals("royalkasino")) {
            goNetWA(1);
        }

        if (!BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365") &&
                !BuildConfig.FLAVOR.equals("glxcasino") && !BuildConfig.FLAVOR.equals("masterbaccarat") && !BuildConfig.FLAVOR.equals("mejaemas") &&
                !BuildConfig.FLAVOR.equals("rascasino")) {
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
                if (BuildConfig.FLAVOR.equals("gd88") || BuildConfig.FLAVOR.equals("liga365")) {
                    showLanguagePop(ll_language);
                } else {
                    initLanguage(ll_language);
                }
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
            UserLoginBean objectData = (UserLoginBean) objectData1;
            cb_remember_me.setChecked(objectData.getRememberMe());
            if (objectData.getRememberMe() && !BuildConfig.FLAVOR.equals("ahlicasino")) {
                tv_name.setText(objectData.getUsername());
                tv_password.setText(objectData.getPassword());
                EditText viewById = (EditText) findViewById(R.id.gd__login_site_edt);
                if (viewById != null) {
                    viewById.setText(objectData.getSite());
                }
            }
            fingerLogin();
        } else {
            if (!BuildConfig.FLAVOR.equals("rajacasino") && !BuildConfig.FLAVOR.equals("rajabakarat") && !BuildConfig.FLAVOR.equals("kasino365")) {
                cb_remember_me.setChecked(false);
            }
        }

    }

    private void loadWebView(WebView webView, String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
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

    private void ahlTranslationY(View view) {
        ObjectAnimator objectTranslationY = ObjectAnimator.ofFloat(view, "translationY", 0, -40, 0);
        objectTranslationY.setDuration(1500);
        objectTranslationY.setRepeatCount(Animation.INFINITE);
        objectTranslationY.start();
    }

    private void getNetPromo() {
        new Thread() {
            @Override
            public void run() {
                String url = "http://www.grjl25.com/getDomainInform.jsp?";
                String param = "labelid=" + BuildConfig.Labelid;
                String result = httpClient.getHttpClient(url + param, null);
                WebSiteUrl.setNormal(result);
                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        PopWebView popWebView = new PopWebView(mContext, tvWhatsApp, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                            @Override
                            public String getUrl() {
                                String promotionUrl = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "promom.jsp";
                                return promotionUrl;
                            }

                            @Override
                            public String getTitle() {
                                return getString(R.string.PROMOTION);
                            }
                        };
                        popWebView.showPopupCenterWindow();
                    }
                });
            }
        }.start();
    }

    public void goNetWA(int type) {
        new Thread() {
            @Override
            public void run() {
                if (currentWABean == null) {
                    String host = WebSiteUrl.HEADER + WebSiteUrl.PROJECT;
                    if (TextUtils.isEmpty(host)) {
                        String url = "http://www.grjl25.com/getDomainInform.jsp?";
                        String param = "labelid=" + BuildConfig.Labelid;
                        String result = httpClient.getHttpClient(url + param, null);
                        WebSiteUrl.setNormal(result);
                    }
                    String result = httpClient.getHttpClient(WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "getWA.jsp", null);
                    if (result.contains("Success")) {
                        currentWABean = new Gson().fromJson(result, WABean.class);
                    }
                }
                if (currentWABean != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            if (BuildConfig.FLAVOR.equals("hitamslot")) {
                                if (!TextUtils.isEmpty(currentWABean.getWA())) {
                                    Gd88Utils.goBrowser(mContext, currentWABean.getWA());
                                }
                            } else if (BuildConfig.FLAVOR.equals("oricasino")) {
                                if (type == 1) {
                                    if (!TextUtils.isEmpty(currentWABean.getBocoranRtp())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getBocoranRtp());
                                    }
                                } else if (type == 2) {
                                    if (!TextUtils.isEmpty(currentWABean.getLayananVIP())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getLayananVIP());
                                    }
                                } else if (type == 3) {
                                    if (!TextUtils.isEmpty(currentWABean.getPemenang())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getPemenang());
                                    }
                                }
                            } else if (BuildConfig.FLAVOR.equals("mastercasino88")) {
                                if (type == 1) {
                                    if (!TextUtils.isEmpty(currentWABean.getWA())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getWA());
                                    }
                                } else if (type == 2) {
                                    if (!TextUtils.isEmpty(currentWABean.getTelegram())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getTelegram());
                                    }
                                } else if (type == 3) {
                                    if (!TextUtils.isEmpty(currentWABean.getLiveChat())) {
                                        PopWebView popWebView = new PopWebView(mContext, tv_name, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                                            @Override
                                            public String getUrl() {
                                                return currentWABean.getLiveChat();
                                            }

                                            @Override
                                            public String getTitle() {
                                                return "LiveChat";
                                            }
                                        };
                                        popWebView.showPopupCenterWindow();
                                    }
                                }
                            } else if (BuildConfig.FLAVOR.equals("ratucasino88")) {
                                if (type == 1) {
                                    if (!TextUtils.isEmpty(currentWABean.getSuperBonusVIP())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getSuperBonusVIP());
                                    }
                                } else if (type == 2) {
                                    if (!TextUtils.isEmpty(currentWABean.getVIP())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getVIP());
                                    }
                                }
                            } else if (BuildConfig.FLAVOR.equals("wingsbet88")) {
                                if (type == -1) {
                                    if (!TextUtils.isEmpty(currentWABean.getPopup())) {
                                        PopImg popImg = new PopImg(LoginActivity.this, tv_name, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                        popImg.setLoadUrl(currentWABean.getPopup());
                                        if (!TextUtils.isEmpty(currentWABean.getPopupLink())) {
                                            popImg.setGoUrl(currentWABean.getPopupLink());
                                        }
                                        popImg.showPopupCenterWindow();
                                    }
                                } else if (type == -2) {
                                    if (!TextUtils.isEmpty(currentWABean.getBonusSpecial())) {
                                        PopWebView popWebView = new PopWebView(mContext, tv_name, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                                            @Override
                                            public String getUrl() {
                                                return currentWABean.getBonusSpecial();
                                            }

                                            @Override
                                            public String getTitle() {
                                                return "BONUS SPECIAL";
                                            }
                                        };
                                        popWebView.showPopupCenterWindow();
                                    }
                                } else if (type == -3) {
                                    if (!TextUtils.isEmpty(currentWABean.getLayananVIP())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getLayananVIP());
                                    }
                                }
                            } else if (BuildConfig.FLAVOR.equals("doacasino")) {
                                List<WABean.DataBean> data = currentWABean.getData();
                                if (data != null && data.size() > 0) {
                                    WABean.DataBean dataBean = data.get(0);
                                    if (type == 1) {
                                        Glide.with(LoginActivity.this).asGif().load(dataBean.getURL()).into(gd_img_login_title_main_sbocasino77);
                                    } else if (type == 2) {
                                        if (!TextUtils.isEmpty(dataBean.getLink())) {
                                            Gd88Utils.goBrowser(mContext, dataBean.getLink());
                                        }
                                    }
                                }
                            } else if (BuildConfig.FLAVOR.equals("kasino365")) {
                                if (type == 1) {
                                    if (!TextUtils.isEmpty(currentWABean.getBonusSpesial())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getBonusSpesial());
                                    }
                                } else if (type == 2) {
                                    if (!TextUtils.isEmpty(currentWABean.getVIP())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getVIP());
                                    }
                                } else if (type == 3) {
                                    if (!TextUtils.isEmpty(currentWABean.getLuckyDragon())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getLuckyDragon());
                                    }
                                } else if (type == 4) {
                                    if (!TextUtils.isEmpty(currentWABean.get365KasinoPopup())) {
                                        PopImg popImg = new PopImg(LoginActivity.this, tv_name, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                        popImg.setLoadUrl(currentWABean.get365KasinoPopup());
                                        popImg.showPopupCenterWindow();
                                    }
                                }
                            } else if (BuildConfig.FLAVOR.equals("royalkasino")) {
                                if (type == 1) {
                                    if (!TextUtils.isEmpty(currentWABean.get365KasinoPopup())) {
                                        PopImg popImg = new PopImg(LoginActivity.this, tv_name, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                        popImg.setLoadUrl(currentWABean.get365KasinoPopup());
                                        popImg.showPopupCenterWindow();
                                    }
                                }
                            } else if (BuildConfig.FLAVOR.equals("mainkasino")) {
                                if (type == 1) {
                                    if (!TextUtils.isEmpty(currentWABean.getBonusKhusus())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getBonusKhusus());
                                    }
                                } else if (type == 2) {
                                    if (!TextUtils.isEmpty(currentWABean.getMainVIP())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getMainVIP());
                                    }
                                } else if (type == 3) {
                                    if (!TextUtils.isEmpty(currentWABean.getFBMessenger())) {
                                        Gd88Utils.goBrowser(mContext, currentWABean.getFBMessenger());
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }.start();
    }

    private void getNetContact() {
        new Thread() {
            @Override
            public void run() {
                String url = "http://www.grjl25.com/getDomainInform.jsp?";
                String param = "labelid=" + BuildConfig.Labelid;
                String result = httpClient.getHttpClient(url + param, null);
                WebSiteUrl.setNormal(result);
                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        PopWebView popWebView = new PopWebView(mContext, tvWhatsApp, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                            @Override
                            public String getUrl() {
                                String promotionUrl = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "hubungikamim.jsp";
                                return promotionUrl;
                            }

                            @Override
                            public String getTitle() {
                                return getString(R.string.PROMOTION);
                            }
                        };
                        popWebView.showPopupCenterWindow();
                    }
                });
            }
        }.start();
    }

    private void initRightLang() {
        img_in = findViewById(R.id.img_in);
        img_en = findViewById(R.id.img_en);
        img_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppTool.setAppLanguage(mContext, "my");
                recreate();
            }
        });
        img_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppTool.setAppLanguage(mContext, "en");
                recreate();
            }
        });
        cb_remember_me.setChecked(true);
        Object objectData1 = AppTool.getObjectData(mContext, WebSiteUrl.Tag);
        if (objectData1 != null && objectData1 instanceof UserLoginBean) {
            UserLoginBean objectData = (UserLoginBean) objectData1;
            objectData.setRememberMe(true);
            AppTool.saveObjectData(mContext, WebSiteUrl.Tag, objectData);
        }
    }

    private void initSiteMap() {
        siteMap = new HashMap<String, String>();
        mAppViewModel.setHttpClient(new HttpClient());
        new Thread() {
            @Override
            public void run() {
                String agentUrl = "https://www.gd88.app/liga365agengt.php";
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

    public void showLanguagePop(View view) {
        PopLanguage popLanguage = new PopLanguage(mContext, view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popLanguage.showPopupDownWindow();
    }

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
        goLogin(AppConfig.CLICK_LOGIN);
    }

    private void goLogin(String loginType) {
        String usName;
        String usPassword;
        if (loginType.equals(AppConfig.CLICK_LOGIN)) {
            usName = tv_name.getText().toString().trim();
            usPassword = tv_password.getText().toString().trim();
        } else {
            UserLoginBean userLoginBean = (UserLoginBean) AppTool.getObjectData(mContext, WebSiteUrl.Tag);
            usName = userLoginBean.getUsername();
            usPassword = userLoginBean.getPassword();
        }
        mAppViewModel.getUser().setName(usName);
        mAppViewModel.getUser().setRealName(usName);
        mAppViewModel.getUser().setPassword(usPassword);
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
            String site;
            if (loginType.equals(AppConfig.CLICK_LOGIN)) {
                site = edt_site.getText().toString().trim();
            } else {
                UserLoginBean userLoginBean = (UserLoginBean) AppTool.getObjectData(mContext, WebSiteUrl.Tag);
                site = userLoginBean.getSite();
            }
            mAppViewModel.getUser().setSite(site);
            if (site.length() == 0) {
//            showToast("Please input user name");
                return;
            }
            if (siteMap.get(site) == null) {
                Toast.makeText(mContext, R.string.error_site, Toast.LENGTH_LONG).show();
                return;
            }
            UserBean user = new UserBean(usName + siteMap.get(site), usPassword);
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
//        WebSiteUrl.setNormal("http://202.36.58.189/");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isNeedCount = false;
        if (hallGameBottomPromptTv != null) {
            hallGameBottomPromptTv.stopScroll();
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

    private void fingerLogin() {
        boolean isClickBackToLogin = false;
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String s = extras.getString(AppConfig.CLICK_BACK_TO_LOGIN);
                if (!TextUtils.isEmpty(s) && s.equals(AppConfig.CLICK_BACK_TO_LOGIN)) {
                    isClickBackToLogin = true;
                }
            }
        }
        if (!isClickBackToLogin) {
            String finger = (String) AppTool.getObjectData(mContext, AppConfig.ACTION_KEY_FINGER);
            if (!TextUtils.isEmpty(finger)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    fingerVerify();
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void fingerVerify() {
        switch (FingerManager.checkSupport(this)) {
            case DEVICE_UNSUPPORTED:
                ToastUtils.showShort(getString(R.string.not_support));
                break;
            case SUPPORT_WITHOUT_DATA:
                ToastUtils.showShort(getString(R.string.after_verify));
                break;
            case SUPPORT:
                FingerManager.updateFingerData(this);
                FingerManager.build().setApplication(getApplication())
                        .setTitle(getString(R.string.Fingerprint_verification))
                        .setDes(getString(R.string.Please_press_fingerprint))
                        .setNegativeText(getString(R.string.gd_cancel))
//                        .setFingerDialogApi23(new MyFingerDialog())//如果你需要自定义android P 一下系统弹窗就设置,不设置会使用默认弹窗
                        .setFingerCheckCallback(new SimpleFingerCheckCallback() {

                            @Override
                            public void onSucceed() {
                                goLogin(AppConfig.FINGER_LOGIN);
                            }

                            @Override
                            public void onError(String error) {
                                ToastUtils.showShort(getString(R.string.verification_failed));
                            }

                            @Override
                            public void onCancel() {

                            }
                        })
                        .setFingerChangeCallback(new AonFingerChangeCallback() {

                            @Override
                            protected void onFingerDataChange() {

                            }
                        })
                        .create()
                        .startListener(this);
                break;
        }
    }

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
}
