package com.nanyang.app.load.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.load.forgetPassword.ForgetActivity;
import com.nanyang.app.load.register.RegisterActivity;
import com.nanyang.app.main.MainActivity;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.AppCacheUtils;
import cn.finalteam.toolsfinal.ManifestUtils;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @Bind(R.id.login_language_rg)
    RadioGroup loginLanguageRg;
    @Bind(R.id.edt_login_username)
    EditText edtLoginUsername;
    @Bind(R.id.edt_login_password)
    EditText edtLoginPassword;
    @Bind(R.id.btn_login_login)
    Button btnLoginLogin;
    @Bind(R.id.tv_login_register)
    TextView tvLoginRegister;
    @Bind(R.id.tv_login_forget)
    TextView tvLoginForget;
    @Bind(R.id.tv_login_version)
    TextView tvLoginVersion;

    @Bind(R.id.login_china_rb)
    RadioButton loginChinaRb;
    @Bind(R.id.login_english_rb)
    RadioButton loginEnglishRb;
    AfbApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createPresenter(new LoginPresenter(this));
        edtLoginPassword.setOnKeyListener(onKeyListener);
        String password = AppCacheUtils.getInstance(this).getString("PASS_WORD")!=null? AppCacheUtils.getInstance(this).getString("PASS_WORD"):"";
        String userName = AppCacheUtils.getInstance(this).getString("USER_NAME")!=null? AppCacheUtils.getInstance(this).getString("USER_NAME"):"";
        edtLoginUsername.setText(userName);
        edtLoginPassword.setText(password);
        initLanguage();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initLanguage() {
        String language = AfbUtils.getLanguage(this);
        if (!TextUtils.isEmpty(language)) {
            if (language.equals("zh")) {
                loginChinaRb.setChecked(true);
                loginEnglishRb.setChecked(false);
                AfbUtils.switchLanguage("zh", this);
                restart();
            } else if (language.equals("en")) {
                loginChinaRb.setChecked(false);
                loginEnglishRb.setChecked(true);
                AfbUtils.switchLanguage("en", this);
                restart();
            }
        } else {
            AfbUtils.switchLanguage("en", this);
            restart();
        }
    }


/*    public void onCLickLogin(View v) {
//        presenter.login(new UserInfo("0", "DLDLDLYY15", "111111", "Android", "1.0").getMap());
        presenter.login(new UserInfo("0", "DLDLDLYY15", "111111", "Android", "1.0"));
    }*/


    @Override
    public void onGetData(String data) {

        if (data.equals("Login Success")) {
            ToastUtils.showShort(getString(R.string.Login_Success));
            app = (AfbApplication) getApplication();
            String username = edtLoginUsername.getText().toString();
            String password = edtLoginPassword.getText().toString();
            app.getUser().setUserName(username);
            app.getUser().setPassword(password);
            AppCacheUtils.getInstance(this).put("PASS_WORD",password);
            AppCacheUtils.getInstance(this).put("USER_NAME",username);
        } else {
            ToastUtils.showShort(R.string.Login_Failed);
        }
        skipAct(MainActivity.class);
        finish();
    }

    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void promptMsg(int msgRes) {
        ToastUtils.showShort(msgRes);
    }

    @OnClick({R.id.btn_login_login, R.id.tv_login_register, R.id.tv_login_forget, R.id.login_china_rb, R.id.login_english_rb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                login();
                break;
            case R.id.tv_login_register:
                skipAct(RegisterActivity.class);
                break;
            case R.id.tv_login_forget:
                skipAct(ForgetActivity.class);
                break;
            case R.id.login_china_rb:
                AfbUtils.switchLanguage("zh", this);
                restart();
                break;
            case R.id.login_english_rb:
                AfbUtils.switchLanguage("en", this);
                restart();
                break;
        }
    }

    private void restart() {
        edtLoginUsername.setHint(getString(R.string.Account));
        edtLoginPassword.setHint(getString(R.string.Password));
        btnLoginLogin.setText(getString(R.string.Login));
        tvLoginRegister.setText(getString(R.string.No_Account));
        tvLoginForget.setText(getString(R.string.Forget_password));
        tvLoginVersion.setText(getString(R.string.Version)+":"+ ManifestUtils.getVersionName(this));

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
            presenter.login(new LoginInfo(us, k));
        } else {
            ToastUtils.showShort(getString(R.string.enter_username_or_password));
        }
    }
}
