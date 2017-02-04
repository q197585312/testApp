package com.nanyang.app.login;

import android.os.Bundle;
import android.view.View;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.login.LoginContract;
import com.unkonw.testapp.login.LoginInfo;
import com.unkonw.testapp.login.LoginPresenter;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


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

    public void onCLickLogin(View v) {
//        presenter.login(new LoginInfo("0", "DLDLDLYY15", "111111", "Android", "1.0").getMap());
        presenter.login(new LoginInfo("0", "DLDLDLYY15", "111111", "Android", "1.0"));
    }


    @Override
    public void onGetData(final String data) {
        ToastUtils.showShort(data);
    }

}
