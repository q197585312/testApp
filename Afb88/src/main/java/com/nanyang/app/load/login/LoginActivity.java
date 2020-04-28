package com.nanyang.app.load.login;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.Pop.PopChoiceLanguage;
import com.nanyang.app.R;
import com.nanyang.app.Utils.AutoScrollViewPager;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.AppCacheUtils;
import cn.finalteam.toolsfinal.DeviceUtils;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class LoginActivity extends BaseToolbarActivity<LoginPresenter> {


    @Bind(R.id.c_video_bg)
    CustomVideoView c_video_bg;
    @Bind(R.id.tv_all_right)
    TextView tv_all_right;
    @Bind(R.id.tv_remember_me)
    TextView tv_remember_me;
    @Bind(R.id.edt_login_username)
    EditText edtLoginUsername;
    @Bind(R.id.edt_login_password)
    EditText edtLoginPassword;
    @Bind(R.id.ll_bottom_btn)
    View llBottomBtn;
    @Bind(R.id.btn_login_login)
    TextView btnLoginLogin;
    @Bind(R.id.btn_desktop)
    TextView btn_desktop;
    //    @Bind(R.id.tv_login_forget)
//    TextView tvLoginForget;
    AfbApplication app;
    @Bind(R.id.cb_login_remember)
    CheckBox cbLoginRemember;
    @Bind(R.id.ll_container)
    LinearLayout llContainer;
    @Bind(R.id.ll_login_remember)
    LinearLayout llLoginRemember;
    @Bind(R.id.login_images_vp)
    AutoScrollViewPager loginImagesvp;
    @Bind(R.id.login_indicator_cpi)
    LinearLayout loginIndicatorCpi;
    @Bind(R.id.login_language)
    TextView loginLanguage;
    private PopChoiceLanguage popLanguage;
    private int[] sc;
    private int scrollHeight;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        createPresenter(new LoginPresenter(this));
        tv_all_right.setText(String.format(getString(R.string.copyright_2018_afb88_all_rights_reserved), DateUtils.getCurrentDate("yyyy"),
                BuildConfig.FLAVOR.toUpperCase() + " V" + BuildConfig.VERSION_NAME));
        edtLoginPassword.setOnKeyListener(onKeyListener);
        String password = AppCacheUtils.getInstance(this).getString("PASS_WORD") != null ? AppCacheUtils.getInstance(this).getString("PASS_WORD") : "";
        String userName = AppCacheUtils.getInstance(this).getString("USER_NAME") != null ? AppCacheUtils.getInstance(this).getString("USER_NAME") : "";
        edtLoginUsername.setText(userName);
        edtLoginPassword.setText(password);
        if (userName == null || userName.isEmpty()) {
            cbLoginRemember.setChecked(false);
        } else {
            cbLoginRemember.setChecked(true);
        }
        llLoginRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLoginRemember.isChecked()) {
                    cbLoginRemember.setChecked(false);
                } else {
                    cbLoginRemember.setChecked(true);
                }
            }
        });
        edtLoginUsername.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        inputMove();
        presenter.playVideoRaw(c_video_bg);

    }

    private void initAppNullData() {
        getApp().setBetAfbList(null);
        getApp().setListMainPictures(null);
        getApp().setListMainBanners(null);
    }

    @Override
    public void initLanguage() {
        super.initLanguage();
        String language = AfbUtils.getLanguage(this);
        if (TextUtils.isEmpty(language)) {
            AfbUtils.switchLanguage("zh", this);
            LanguageHelper helper = new LanguageHelper(mContext);
            MenuItemInfo<String> languageItem = helper.getLanguageItem();
            loginLanguage.setText(languageItem.getText());
        }
        restart();
    }

    @Override
    public void init() {
        initFromWhere();
        initView();
        initData();
        initLanguage();
    }

    public void onFailed(String error) {
        if (error != null && error.equals(getString(R.string.System_maintenance))) {
            ToastUtils.showMyToast(error);
        } else {
            ToastUtils.showShort(error);
        }
    }


    public void promptMsg(int msgRes) {
        ToastUtils.showShort(msgRes);
    }

    @OnClick({R.id.btn_login_login, R.id.login_language})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                login();
                break;
            case R.id.login_language:
                if (popLanguage == null) {
                    popLanguage = new PopChoiceLanguage(mContext, view, view.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT) {
                        @Override
                        public void onConvert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                            TextView tv = holder.getView(R.id.item_regist_text_tv);
                            tv.setText(item.getText());
                        }

                        @Override
                        public void onClickItem(MenuItemInfo item, int position) {
                            loginLanguage.setText(item.getText());
                            AfbUtils.switchLanguage(item.getType(), mContext);
                            restart();
                        }
                    };
                    List<MenuItemInfo> languageList = new ArrayList<>();
                    MenuItemInfo info = new MenuItemInfo();
                    info.setText(R.string.language_en);
                    info.setType("en");
                    languageList.add(info);
                    MenuItemInfo info1 = new MenuItemInfo();
                    info1.setText(R.string.language_zh);
                    info1.setType("zh");
                    languageList.add(info1);
                    MenuItemInfo info3 = new MenuItemInfo();
                    info3.setText(R.string.language_th);
                    info3.setType("th");
                    languageList.add(info3);
                    MenuItemInfo info4 = new MenuItemInfo();
                    info4.setText(R.string.language_vi);
                    info4.setType("vi");
                    languageList.add(info4);
                    MenuItemInfo info5 = new MenuItemInfo();
                    info5.setText(R.string.language_ko);
                    info5.setType("ko");
                    languageList.add(info5);
                    MenuItemInfo info6 = new MenuItemInfo();
                    info6.setText(R.string.language_tr);
                    info6.setType("tr");
                    languageList.add(info6);

                    MenuItemInfo info7 = new MenuItemInfo();
                    info7.setText(R.string.language_my);
                    info7.setType("my");
                    languageList.add(info7);

                    popLanguage.setData(languageList);
                }
                popLanguage.showPopupDownWindow();
                break;
        }
    }

    protected void restart() {
        super.restart();
        edtLoginUsername.setHint(getString(R.string.Account));
        edtLoginPassword.setHint(getString(R.string.Password));
        btnLoginLogin.setText(getString(R.string.Login));
        btn_desktop.setText(getString(R.string.desktop));
        tv_remember_me.setText(getString(R.string.remember_me));
        loginLanguage.setText(getString(R.string.language_switch));

    }

    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                login();
                return true;
            }
            return false;
        }
    };

    private void login() {
        initAppNullData();
        String us = edtLoginUsername.getText().toString();
        String k = edtLoginPassword.getText().toString();//"a7c7366ecd6041489d08ecb9ac1f39c9"
        if (!TextUtils.isEmpty(us) && !TextUtils.isEmpty(k)) {
            presenter.login(new LoginInfo(us, k), new BaseConsumer<String>(this) {
                        @Override
                        protected void onBaseGetData(String s) throws JSONException {
                            JSONArray jsonArray = new JSONArray(s);

                            if (s.contains("Maintenance")) {
                                Exception exception = new Exception(((Activity) baseContext).getString(R.string.System_maintenance));
                                onError(exception);
                            } else if (jsonArray.optString(2) != null && StringUtils.matches(jsonArray.optString(2), "^.*\\(\\'([^\\']+)\\'\\);.*?")) {
                                Exception exception = new Exception(StringUtils.findGroup(jsonArray.optString(2), "^.*\\(\\'([^\\']+)\\'\\);.*?", 1));
                                onError(exception);
                            } else {
                                String regex = "window.location";
                                Pattern p = Pattern.compile(regex);
                                Matcher m = p.matcher(s);
                                if (m.find()) {
                                    final MainPresenter switchLanguage = new MainPresenter(baseContext);
                                    switchLanguage.getSetting(new MainPresenter.CallBack<SettingAllDataBean>() {
                                                                  @Override
                                                                  public void onBack(SettingAllDataBean data) throws JSONException {
                                                                      onAppGetData(switchLanguage);
                                                                  }
                                                              }
                                    );
                                } else {
                                    Exception exception1 = new Exception(s);
                                    onError(exception1);
                                }
                            }


                        }

                        @Override
                        protected void onHideDialog() {
                        }

                        @Override
                        protected void onError(final Throwable throwable) {
                            super.onError(throwable);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showShort(throwable.getMessage());
                                }
                            });

                        }
                    }
            );
        } else {
            ToastUtils.showShort(getString(R.string.enter_username_or_password));
        }
    }

    private void onAppGetData(MainPresenter switchLanguage) {
        String language = new LanguageHelper(mContext).getLanguage();
        switchLanguage.loadAllMainData(new LoginInfo.LanguageWfBean("AppGetDate", language, AppConstant.getInstance().wfMain), new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) {
                PersonalInfo personalInfo = new Gson().fromJson(data, PersonalInfo.class);
                personalInfo.setPassword(getApp().getUser().getPassword());
                getApp().setUser(personalInfo);
                onLanguageSwitchSucceed(personalInfo.getLoginName());
            }
        });
    }


    public void onLanguageSwitchSucceed(String str) {
        ToastUtils.showShort(R.string.Login_Success);
        app = (AfbApplication) getApplication();
        String username = edtLoginUsername.getText().toString();
        String password = edtLoginPassword.getText().toString();
        app.getUser().setLoginName(username);
        app.getUser().setPassword(password);
        if (cbLoginRemember.isChecked()) {
            AppCacheUtils.getInstance(this).put("PASS_WORD", password);
            AppCacheUtils.getInstance(this).put("USER_NAME", username);
        } else {
            AppCacheUtils.getInstance(this).put("PASS_WORD", "");
            AppCacheUtils.getInstance(this).put("USER_NAME", "");
        }

        AppConstant.IS_AGENT = false;
        AppConstant.wfMain = "wfMainH50";
        AfbUtils.initAllSprotMap();
        Log.d("doRetrofitApiOnUiThread", ": " + AppConstant.wfMain);
        skipAct(MainActivity.class);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        c_video_bg.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        c_video_bg.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (loginImagesvp != null)
            loginImagesvp.stopTask();
    }

    public void clickDesktop(View view) {
        //代码实现跳转
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        //https://www.afb1188.com/H50/Pub/wfMainH50.html
        Uri content_url = Uri.parse(BuildConfig.H5_URL);//此处填链接
        intent.setData(content_url);
        startActivity(intent);
    }

    public void inputMove() {
        llContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                if (llContainer == null)
                    return;
                llContainer.getWindowVisibleDisplayFrame(r);
                if (sc == null) {
                    sc = new int[2];
                    llBottomBtn.getLocationOnScreen(sc);
                }
                //r.top 是状态栏高度
                int screenHeight = llContainer.getRootView().getHeight();
                int softHeight = screenHeight - r.bottom;
                if (scrollHeight == 0 && softHeight > 120)
                    scrollHeight = sc[1] + btnLoginLogin.getHeight() - (screenHeight - softHeight)+ DeviceUtils.dip2px(mContext,10);//可以加个5dp的距离这样，按钮不会挨着输入法

                if (scrollHeight < 1)
                    return;
                if (softHeight > 120) {//当输入法高度大于100判定为输入法打开了  设置大点，有虚拟键的会超过100
                    if (llContainer.getScrollY() != scrollHeight)
                        scrollToPos(0, scrollHeight);
                } else {//否则判断为输入法隐藏了
                    if (llContainer.getScrollY() != 0)
                        scrollToPos(scrollHeight, 0);
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
                if (llContainer == null) {
                    return;
                }
                llContainer.scrollTo(0, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        animator.start();
    }

    @Override
    public void startUpdateState() {
//        super.startUpdateState();
    }
}
