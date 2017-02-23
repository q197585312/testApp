package com.nanyang.app.main.home.sport;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.unkonw.testapp.libs.base.BaseFragment;

public abstract class BaseSportFragment<T extends SportPresenter> extends BaseFragment<T> {
    @Override
    public void initData() {
        super.initData();
    }


    public abstract void toolbarRightClick(View v);
    public AfbApplication getApp(){
        return (AfbApplication) getActivity().getApplication();
    }

    public boolean mixParlayCLick(TextView buttonView) {
        return false;
    }

    public boolean collectionClick(TextView tvCollection) {
        return false;
    }
}
