package com.nanyang.app.main.home.sport;

import android.view.View;

import com.unkonw.testapp.libs.base.BaseFragment;

public abstract class BaseSportFragment<T extends SportPresenter> extends BaseFragment<T> {
    @Override
    public void initData() {
        super.initData();
    }


    public abstract void toolbarRightClick(View v);
}
