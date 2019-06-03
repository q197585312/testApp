package com.nanyang.app.load.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.Pop.PopChoiceLanguage;
import com.nanyang.app.R;
import com.nanyang.app.Utils.AutoScrollViewPager;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.Utils.ViewPagerAdapter;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.AppCacheUtils;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> {


    @Bind(R.id.tv_all_right)
    TextView tv_all_right;
    @Bind(R.id.tv_remember_me)
    TextView tv_remember_me;
    @Bind(R.id.edt_login_username)
    EditText edtLoginUsername;
    @Bind(R.id.edt_login_password)
    EditText edtLoginPassword;
    @Bind(R.id.btn_login_login)
    Button btnLoginLogin;
    //    @Bind(R.id.tv_login_forget)
//    TextView tvLoginForget;
    AfbApplication app;
    @Bind(R.id.cb_login_remember)
    CheckBox cbLoginRemember;
    @Bind(R.id.ll_login_remember)
    LinearLayout llLoginRemember;
    @Bind(R.id.login_images_vp)
    AutoScrollViewPager loginImagesvp;
    @Bind(R.id.login_indicator_cpi)
    LinearLayout loginIndicatorCpi;
    @Bind(R.id.login_language)
    TextView loginLanguage;
    private PopChoiceLanguage popLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        createPresenter(new LoginPresenter(this));
        tv_all_right.setText(String.format(getString(R.string.copyright_2018_afb88_all_rights_reserved), DateUtils.getCurrentDate("yyyy"), BuildConfig.FLAVOR));
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
        initLanguage();
        presenter.loadAllImages();
    }

    private void initLanguage() {
        String language = AfbUtils.getLanguage(this);
        if (!TextUtils.isEmpty(language)) {
            AfbUtils.switchLanguage(language, this);
        } else {
            AfbUtils.switchLanguage("en", this);
            restart();
        }
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
                    info.setText("ENGLISH");
                    info.setType("en");
                    languageList.add(info);
                    MenuItemInfo info1 = new MenuItemInfo();
                    info1.setText("中文(简体)");
                    info1.setType("zh");
                    languageList.add(info1);
                    MenuItemInfo info3 = new MenuItemInfo();
                    info3.setText("ภาษาไทย");
                    info3.setType("th");
                    languageList.add(info3);
                    MenuItemInfo info4 = new MenuItemInfo();
                    info4.setText("Tiếng Việt");
                    info4.setType("vi");
                    languageList.add(info4);
                    MenuItemInfo info5 = new MenuItemInfo();
                    info5.setText("KOREAN");
                    info5.setType("ko");
                    languageList.add(info5);
                    MenuItemInfo info6 = new MenuItemInfo();
                    info6.setText("TURKISH");
                    info6.setType("tr");
                    languageList.add(info6);
                    popLanguage.setData(languageList);
                }
                popLanguage.showPopupDownWindow();
                break;
        }
    }

    private void restart() {
        edtLoginUsername.setHint(getString(R.string.Account));
        edtLoginPassword.setHint(getString(R.string.Password));
        btnLoginLogin.setText(getString(R.string.Login));
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
        String us = edtLoginUsername.getText().toString();
        String k = edtLoginPassword.getText().toString();//"a7c7366ecd6041489d08ecb9ac1f39c9"
        if (!TextUtils.isEmpty(us) && !TextUtils.isEmpty(k)) {
            presenter.login(new LoginInfo(us, k), new BaseConsumer<String>(this) {
                @Override
                protected void onBaseGetData(String s) {

                    if (s.contains("Maintenance")) {
                        Exception exception = new Exception(((Activity) baseContext).getString(R.string.System_maintenance));
                        onError(exception);
                    } else if(StringUtils.matches(s,"^.*alert\\(\\'(.*)\\'\\);\\$.*?")){
                        Exception exception = new Exception(StringUtils.findGroup(s,"^.*alert\\(\\'(.*)\\'\\);\\$.*?",1));
                        onError(exception);
                    }else {
                        String regex = "window.location";
                        Pattern p = Pattern.compile(regex);
                        Matcher m = p.matcher(s);
                        if (m.find()) {
                            LanguagePresenter switchLanguage = new LanguagePresenter(baseContext);
                            switchLanguage.getSetting(new LanguagePresenter.CallBack<SettingAllDataBean>() {
                                                          @Override
                                                          public void onBack(SettingAllDataBean data) throws JSONException {
                                                              onLanguageSwitchSucceed(data.getUserName());
                                                          }
                                                      }
                            );
                        } else {
                            Exception exception1 = new Exception("Server Error");
                            onError(exception1);
                        }
                    }

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
            });
        } else {
            ToastUtils.showShort(getString(R.string.enter_username_or_password));
        }
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
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (loginImagesvp != null)
            loginImagesvp.stopTask();
    }

    public void sendImageEvent(AllBannerImagesBean data) {
        LogUtil.d(getClass().getSimpleName(), "sendEvent--------------->" + data.toString());
//        EventBus.getDefault().postSticky(new ListMainPictures());
//        EventBus.getDefault().postSticky(new ListMainBanners(data.getMainBanners()));
        ((AfbApplication) getApplication()).setListMainPictures(data.getMain());
        ((AfbApplication) getApplication()).setListMainBanners(data.getMainBanners());
        initViewPager(data.getLoginBanners());
    }

    private void initViewPager(List<AllBannerImagesBean.BannersBean> list) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(list, loginIndicatorCpi, mContext);
        loginImagesvp.setAdapter(adapter);
        loginImagesvp.addOnPageChangeListener(loginImagesvp.listener);
    }


}
