package com.unkonw.testapp.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.unkonw.testapp.R;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.AutoUtils;
import com.unkonw.testapp.libs.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @Bind(R.id.login_btn)
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoUtils.setSize(this,false,480,800);

        setContentView(R.layout.login);
        ButterKnife.bind(this);
        createPresenter(new LoginPresenter(this));
        AutoUtils.auto(this);
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
