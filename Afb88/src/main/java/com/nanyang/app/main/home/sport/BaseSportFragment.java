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

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {// 不在最前端界面显示
            presenter.stopUpdate();
        } else {// 重新显示到最前端中
            presenter.startUpdate();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.stopUpdate();
    }
}
