package com.nanyang.app.load.login;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createPresenter(new LoginPresenter(this));
        initLanguage();
    }

    private void initLanguage() {
        AfbUtils.switchLanguage("", this);
    }


/*    public void onCLickLogin(View v) {
//        presenter.login(new UserInfo("0", "DLDLDLYY15", "111111", "Android", "1.0").getMap());
        presenter.login(new UserInfo("0", "DLDLDLYY15", "111111", "Android", "1.0"));
    }*/


    @Override
    public void onGetData(String data) {
        ToastUtils.showShort(data);
        if (data.equals("Login Success")) {
            AfbApplication app = (AfbApplication) getApplication();
            app.getUser().setUserName(edtLoginUsername.getText().toString());
            app.getUser().setPassword(edtLoginPassword.getText().toString());
        }
        skipAct(MainActivity.class);
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

                String us = edtLoginUsername.getText().toString();
                String k = edtLoginPassword.getText().toString();//"a7c7366ecd6041489d08ecb9ac1f39c9"
                presenter.login(new LoginInfo(us, k));
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
    }
}
