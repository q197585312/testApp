package com.nanyang.app.load.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.load.register.RegisterActivity;
import com.nanyang.app.main.BaseToolbarActivity;
import com.unkonw.testapp.libs.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class LoginActivity extends BaseToolbarActivity<LoginPresenter> implements LoginContract.View {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createPresenter(new LoginPresenter(this));
    }

    @Override
    public void initView() {

    }

    @Override
    public void bindEvent() {

    }

/*    public void onCLickLogin(View v) {
//        presenter.login(new UserInfo("0", "DLDLDLYY15", "111111", "Android", "1.0").getMap());
        presenter.login(new UserInfo("0", "DLDLDLYY15", "111111", "Android", "1.0"));
    }*/


    @Override
    public void onGetData(String data) {
        ToastUtils.showShort(data);
    }

    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void promptMsg(int msgRes) {
        ToastUtils.showShort(msgRes);
    }

    @OnClick({R.id.btn_login_login, R.id.tv_login_register, R.id.tv_login_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                //http://a8197c.a36588.com/Public/validate.aspx?us=demoafbAi2&k=a7c7366ecd6041489d08ecb9ac1f39c9&r=732357946&lang=EN-US
                String us=edtLoginUsername.getText().toString();
                String k=edtLoginPassword.getText().toString();//"a7c7366ecd6041489d08ecb9ac1f39c9"
                presenter.login(new LoginInfo(us,k));
                break;
            case R.id.tv_login_register:
                skipAct(RegisterActivity.class);
                break;
            case R.id.tv_login_forget:
                skipAct(RegisterActivity.class);
                break;
        }
    }
}
