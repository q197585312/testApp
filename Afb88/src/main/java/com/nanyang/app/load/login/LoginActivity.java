package com.nanyang.app.load.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.Utils.AutoScrollViewPager;
import com.nanyang.app.Utils.ViewPagerAdapter;
import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.nanyang.app.main.MainActivity;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.AppCacheUtils;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> {


    @Bind(R.id.edt_login_username)
    EditText edtLoginUsername;
    @Bind(R.id.edt_login_password)
    EditText edtLoginPassword;
    @Bind(R.id.btn_login_login)
    Button btnLoginLogin;
    @Bind(R.id.tv_login_forget)
    TextView tvLoginForget;
    AfbApplication app;
    @Bind(R.id.cb_login_remember)
    CheckBox cbLoginRemember;
    @Bind(R.id.login_images_vp)
    AutoScrollViewPager loginImagesvp;
    @Bind(R.id.login_indicator_cpi)
    LinearLayout loginIndicatorCpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        createPresenter(new LoginPresenter(this));
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
       /* initLanguage();*/

    }

    private void initViewPager(List<AllBannerImagesBean.MainBannersBean> lists) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(lists, loginIndicatorCpi, mContext);
        loginImagesvp.setAdapter(adapter);
        loginImagesvp.addOnPageChangeListener(loginImagesvp.listener);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AllBannerImagesBean.LoginBannersBean data) {

    }


    /*private void initLanguage() {
        String language = AfbUtils.getLanguage(this);
        if (language != null && !TextUtils.isEmpty(language)) {
            loginChinaRb.setChecked(false);
            loginEnglishRb.setChecked(false);
            loginthRb.setChecked(false);
            switch (language) {
                case "zh":
                    loginChinaRb.setChecked(true);
                    AfbUtils.switchLanguage("zh", this);
                    restart();
                    break;
                case "en":

                    loginEnglishRb.setChecked(true);
                    AfbUtils.switchLanguage("en", this);
                    restart();
                    break;
                case "th":
                    loginthRb.setChecked(true);
                    AfbUtils.switchLanguage("th", this);
                    restart();
                    break;
                case "ko":
                    loginKoreaRb.setChecked(true);
                    AfbUtils.switchLanguage("ko", this);
                    restart();
                    break;
                case "vi":
                    loginVietnamRb.setChecked(true);
                    AfbUtils.switchLanguage("vi", this);
                    restart();
                    break;
                case "tr":
                    loginTurkeyRb.setChecked(true);
                    AfbUtils.switchLanguage("tr", this);
                    restart();
                    break;

                default:
                    loginEnglishRb.setChecked(true);
                    AfbUtils.switchLanguage("en", this);
                    restart();
            }
        } else {
            loginEnglishRb.setChecked(true);
            AfbUtils.switchLanguage("en", this);
            restart();
        }
    }*/


    public void onGetData(String data) {


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

   /* @OnClick({R.id.btn_login_login, R.id.tv_login_register, R.id.tv_login_forget, R.id.login_china_rb, R.id.login_english_rb, R.id.login_th_rb, R.id.login_korea_rb, R.id.login_vietnam_rb, R.id.login_turkey_rb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                login();
                break;
            case R.id.tv_login_register:
                skipAct(RegisterActivity.class);
                break;
            case R.id.tv_login_forget:
//                skipAct(ForgetActivity.class);
                break;
            case R.id.login_china_rb:
                AfbUtils.switchLanguage("zh", this);
                restart();
                break;
            case R.id.login_th_rb:
                AfbUtils.switchLanguage("th", this);
                restart();
                break;
            case R.id.login_english_rb:
                AfbUtils.switchLanguage("en", this);
                restart();
                break;
            case R.id.login_korea_rb:
                AfbUtils.switchLanguage("ko", this);
                restart();
                break;
            case R.id.login_vietnam_rb:
                AfbUtils.switchLanguage("vi", this);
                restart();
                break;
            case R.id.login_turkey_rb:
                AfbUtils.switchLanguage("tr", this);
                restart();
                break;

        }
    }*/

    private void restart() {
        edtLoginUsername.setHint(getString(R.string.Account));
        edtLoginPassword.setHint(getString(R.string.Password));
        btnLoginLogin.setText(getString(R.string.Login));
        tvLoginForget.setText(getString(R.string.Forget_password));
        cbLoginRemember.setText(R.string.remember_me);

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
                protected void onBaseGetData(String data) {
                    onLanguageSwitchSucceed(data);
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
        app.getUser().setUserName(username);
        app.getUser().setPassword(password);
        if (cbLoginRemember.isChecked()) {
            AppCacheUtils.getInstance(this).put("PASS_WORD", password);
            AppCacheUtils.getInstance(this).put("USER_NAME", username);
        } else {
            AppCacheUtils.getInstance(this).put("PASS_WORD", "");
            AppCacheUtils.getInstance(this).put("USER_NAME", "");
        }

        AppConstant.getInstance().IS_AGENT = false;
        skipAct(MainActivity.class);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllImages();
    }


}
