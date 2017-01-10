package com.unkonw.testapp;

import android.os.Bundle;
import android.view.View;

import com.unkonw.testapp.base.BaseActivity;
import com.unkonw.testapp.base.LoginContract;
import com.unkonw.testapp.utils.ToastUtils;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        createPresenter(new LoginPresenter(this));

    }

    @Override
    public void initView() {

    }

    @Override
    public void bindEvent() {

    }

    public void onCLickLogin(View v) {
        presenter.login(new LoginInfo("0", "DLDLDLYY15", "111111", "Android", "1.0"));
    }



    @Override
    public void onGetData(String data) {
        ToastUtils.showShort(data);
    }

    @Override
    public void hideLoading() {

    }
}
