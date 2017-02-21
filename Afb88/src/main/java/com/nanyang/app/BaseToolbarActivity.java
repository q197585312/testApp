package com.nanyang.app;

import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

public abstract class BaseToolbarActivity<T extends IBasePresenter> extends BaseActivity<T> {
    @Nullable
    protected
    TextView tvToolbarTitle;
    @Nullable
    protected
    TextView tvToolbarRight;
    @Nullable
    protected
    Toolbar toolbar;

    @Override
    public void initData() {
        super.initData();
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        tvToolbarRight= (TextView) findViewById(R.id.tv_toolbar_right);
        tvToolbarTitle= (TextView) findViewById(R.id.tv_toolbar_title);
        toolbar.setNavigationIcon(R.mipmap.arrow_white_back);
        toolbar.setBackgroundResource(R.drawable.rectangle_green_gradient_line);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Nullable
    public TextView getTvToolbarTitle() {
        return tvToolbarTitle;
    }

    @Nullable
    public TextView getTvToolbarRight() {
        return tvToolbarRight;
    }

    @Nullable
    public Toolbar getToolbar() {
        return toolbar;
    }
    public  AfbApplication getApp(){
        return (AfbApplication) getApplication();
    }
}
