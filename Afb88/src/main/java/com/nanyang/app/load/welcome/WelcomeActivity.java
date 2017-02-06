package com.nanyang.app.load.welcome;

import android.content.pm.PackageManager;
import android.os.Bundle;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.SystemTool;
import com.unkonw.testapp.libs.utils.ToastUtils;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        createPresenter(new WelcomePresenter(this));

        try {
            presenter.checkVersion( SystemTool.getPackageInfo(this).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onGetData(String data) {
        ToastUtils.showShort(data);
    }

    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }

}
