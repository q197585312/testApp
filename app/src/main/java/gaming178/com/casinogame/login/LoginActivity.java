package gaming178.com.casinogame.login;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
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
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codersun.fingerprintcompat.AonFingerChangeCallback;
import com.codersun.fingerprintcompat.FingerManager;
import com.codersun.fingerprintcompat.SimpleFingerCheckCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorGravity;

import java.util.ArrayList;
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
import gaming178.com.casinogame.Popupwindow.PopImg;
import gaming178.com.casinogame.Util.AllCapTransformationMethod;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.CircleAnimation;
import gaming178.com.casinogame.Util.ErrorCode;
import gaming178.com.casinogame.Util.Gd88Utils;
import gaming178.com.casinogame.Util.HttpClient;
import gaming178.com.casinogame.Util.PopMenu;
import gaming178.com.casinogame.Util.PopWebView;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.adapter.MyBannerAdapter;
import gaming178.com.casinogame.base.BaseActivity;
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
    double count = 555650100;
    private BannerViewPager bannerView;
    private LinearLayout ll_register_bg;
    private ImageView img_ula_enter_bg;
    private FrameLayout fl_whatsapp;


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
        tvWhatsApp = findViewById(R.id.gd__tv_whatsapp);
        tvPromo = findViewById(R.id.gd__tv_promo);
        if (BuildConfig.FLAVOR.equals("dewacasino388")) {
            tvWhatsApp.setVisibility(View.VISIBLE);
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://wa.me/855885179735");
                }
            });
            tvPromo.setVisibility(View.VISIBLE);
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getNetPromo();
                }
            });
            List<Integer> imgList = new ArrayList<>();
            imgList.add(R.mipmap.dewa_banner_1);
            imgList.add(R.mipmap.dewa_banner_2);
            imgList.add(R.mipmap.dewa_banner_3);
            imgList.add(R.mipmap.dewa_banner_4);
            bannerView = findViewById(R.id.banner_view);
            bannerView.setLifecycleRegistry(getLifecycle()).
                    setAdapter(new MyBannerAdapter()).
                    setScrollDuration(500).
                    setIndicatorSliderColor(getResources().getColor(R.color.black44_trans),
                            getResources().getColor(R.color.yellow_gold2)).
                    setIndicatorGravity(IndicatorGravity.START).
                    create(imgList);
        }
        if (BuildConfig.FLAVOR.equals("depocasino")) {
            imgGif1 = findViewById(R.id.img_gif_1);
            imgGif2 = findViewById(R.id.img_gif_2);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.depo_gif_1).into(imgGif1);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.depo_gif_2).into(imgGif2);
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "http://45.77.243.206/depocasino/";
                        }

                        @Override
                        public String getTitle() {
                            return context.getString(R.string.PROMOTION);
                        }
                    };
                    popWebView.showPopupCenterWindow();
                }
            });
            ObjectAnimator objectTranslationY = ObjectAnimator.ofFloat(tvWhatsApp, "translationY", 0, -40, 0);
            objectTranslationY.setDuration(700);
            objectTranslationY.setRepeatCount(Animation.INFINITE);
            ObjectAnimator objectScaleY = ObjectAnimator.ofFloat(tvWhatsApp, "scaleY", (float) 0.95, (float) 1.25, (float) 0.95);
            objectScaleY.setDuration(700);
            objectScaleY.setRepeatCount(Animation.INFINITE);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectTranslationY, objectScaleY);
            animatorSet.start();
        }
        if (BuildConfig.FLAVOR.equals("ratucasino88")) {
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.kilat).into(img_login_title);
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "http://139.162.82.229/bonus/";
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

        if (BuildConfig.FLAVOR.equals("livecasino338")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getNetPromo();
                }
            });
            List<Integer> imgList = new ArrayList<>();
            imgList.add(R.mipmap.live_banner_1);
            imgList.add(R.mipmap.live_banner_2);
            imgList.add(R.mipmap.live_banner_3);
            imgList.add(R.mipmap.live_banner_4);
            bannerView = findViewById(R.id.banner_view);
            bannerView.setLifecycleRegistry(getLifecycle()).
                    setAdapter(new MyBannerAdapter()).
                    setScrollDuration(500).
                    setIndicatorSliderColor(getResources().getColor(R.color.black44_trans),
                            getResources().getColor(R.color.yellow_gold2)).
                    setIndicatorGravity(IndicatorGravity.START).
                    create(imgList);
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

        if (BuildConfig.FLAVOR.equals("ularnaga")) {
            img_ula_enter_bg = findViewById(R.id.img_ula_enter_bg);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.form_bg).into(img_ula_enter_bg);
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "http://45.77.243.206/ularnaga/";
                        }

                        @Override
                        public String getTitle() {
                            return getString(R.string.PROMOTION);
                        }
                    };
                    popWebView.showPopupCenterWindow();
                }
            });
            img_login_title_main = findViewById(R.id.gd_img_login_title_main);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.login_dragon).into(img_login_title_main);
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
                    Gd88Utils.goBrowser(mContext, "https://wa.me/6282249576270");
                }
            });
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://kontak-kita.id/T-MasterCasino88");
                }
            });
            tvLiveChat = findViewById(R.id.gd__tv_live_chat);
            tvLiveChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "https://direct.lc.chat/8792026/";
                        }

                        @Override
                        public String getTitle() {
                            return "LiveChat";
                        }
                    };
                    popWebView.showPopupCenterWindow();
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("rentalbaccarat")) {
            img_login_title.setVisibility(View.GONE);
        }
        if (BuildConfig.FLAVOR.equals("kuncicasino")) {
            fl_whatsapp = findViewById(R.id.fl_whatsapp);
            gd_img_login_title_main_sbocasino77 = findViewById(R.id.gd_img_login_title_main_sbocasino77);
            Glide.with(LoginActivity.this).load("https://bit.ly/kcsbvs").diskCacheStrategy(DiskCacheStrategy.NONE).into(gd_img_login_title_main_sbocasino77);
            fl_whatsapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://api.whatsapp.com/send?phone=+6285920574972&text=Hallo%20Boss%20ku%20Kuncicasino");
                }
            });
            new Thread() {
                @Override
                public void run() {
                    while (isNeedCount) {
                        try {
                            Thread.sleep(800);
                            getHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    if (fl_whatsapp.getVisibility() == View.VISIBLE) {
                                        fl_whatsapp.setVisibility(View.INVISIBLE);
                                    } else {
                                        fl_whatsapp.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
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
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://api.whatsapp.com/send?phone=87869413811");
                }
            });
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "https://direct.lc.chat/8666201/";
                        }

                        @Override
                        public String getTitle() {
                            return "LiveChat";
                        }
                    };
                    popWebView.showPopupCenterWindow();
                }
            });
        }

        if (BuildConfig.FLAVOR.equals("wargacasino")) {
            img_login_title_main = findViewById(R.id.gd_img_login_title_main);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.war_gif).into(img_login_title_main);
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "https://b.link/promowgc";
                        }

                        @Override
                        public String getTitle() {
                            return getString(R.string.PROMOTION);
                        }
                    };
                    popWebView.showPopupCenterWindow();
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
            img_ula_enter_bg = findViewById(R.id.img_ula_enter_bg);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.form_bg_ori).into(img_ula_enter_bg);
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopWebView popWebView = new PopWebView(mContext, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
                        @Override
                        public String getUrl() {
                            return "https://b.link/Bonus-Oricas";
                        }

                        @Override
                        public String getTitle() {
                            return getString(R.string.special_bonus);
                        }
                    };
                    popWebView.showPopupCenterWindow();
                }
            });
            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "http://bit.ly/WA-Oricasino");
                }
            });
            img_login_title_main = findViewById(R.id.gd_img_login_title_main);
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.login_ori_bg).into(img_login_title_main);
        }

        if (BuildConfig.FLAVOR.equals("hitamslot")) {
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://api.whatsapp.com/send?phone=+855964206037");
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

        if (BuildConfig.FLAVOR.equals("merpatislot")) {
            gd_img_login_title_main_sbocasino77 = findViewById(R.id.gd_img_login_title_main_sbocasino77);
            gd_img_login_title_main_sbocasino77.post(new Runnable() {
                @Override
                public void run() {
                    int width = gd_img_login_title_main_sbocasino77.getWidth();
                    ViewGroup.LayoutParams layoutParams = gd_img_login_title_main_sbocasino77.getLayoutParams();
                    layoutParams.width = width;
                    layoutParams.height = (int) (width / 1.46);
                    gd_img_login_title_main_sbocasino77.setLayoutParams(layoutParams);
                }
            });
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
                            PopImg popImg = new PopImg(LoginActivity.this, tv_name, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            popImg.setLoadUrl(result + "/images/Pop-Up.gif");
                            popImg.showPopupCenterWindow();
                        }
                    });
                }
            }.start();
            Glide.with(LoginActivity.this).asGif().load(R.mipmap.gif_365_logo).into(img_login_title);

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

            tvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "http://66.29.153.229/bonus/");
                }
            });
            tvWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://api.whatsapp.com/send/?phone=6282182318018&text&app_absent=0");
                }
            });
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
        }

        if (BuildConfig.FLAVOR.equals("gd88") || BuildConfig.FLAVOR.equals("liga365")) {
            img_login_title.setImageResource(R.mipmap.gd_app_logo);
        } else {
            tv_register.setVisibility(View.VISIBLE);
            if (!BuildConfig.FLAVOR.equals("depocasino") && !BuildConfig.FLAVOR.equals("ratucasino88") &&
                    !BuildConfig.FLAVOR.equals("livecasino338") && !BuildConfig.FLAVOR.equals("kasino365") &&
                    !BuildConfig.FLAVOR.equals("mainkasino")) {
                img_login_title.setImageResource(R.mipmap.gd_title_logo);
            }
            if (BuildConfig.FLAVOR.equals("kuncicasino")) {
                img_login_title.setImageResource(R.mipmap.kuncicasino_lobby_logo);
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
                            popImg.setLoadUrl(result + "images/popup.jpg");
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
                    Gd88Utils.goBrowser(mContext, "http://68.65.120.194/bonus/");
                }
            });
            imgWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://bit.ly/wamkasino");
                }
            });
            imgFB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gd88Utils.goBrowser(mContext, "https://bit.ly/3rtJWdQ");
                }
            });
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
            UserLoginBean objectData = (UserLoginBean) objectData1;
            cb_remember_me.setChecked(objectData.getRememberMe());
            if (objectData.getRememberMe()) {
                tv_name.setText(objectData.getUsername());
                tv_password.setText(objectData.getPassword());
                EditText viewById = (EditText) findViewById(R.id.gd__login_site_edt);
                if (viewById != null) {
                    viewById.setText(objectData.getSite());
                }
            }
            fingerLogin();
        } else {
            cb_remember_me.setChecked(false);
        }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isNeedCount = false;
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
                    UserLoginBean dataBean = new UserLoginBean();
                    UserLoginBean userLoginBean = (UserLoginBean) AppTool.getObjectData(mContext, WebSiteUrl.Tag);
                    if (userLoginBean == null) {
                        String usName = tv_name.getText().toString().trim();
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
}
