package com.nanyang.app.main.home.Games;

import android.content.Context;
import android.view.View;

import com.nanyang.app.AfbApplication;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/2/27.
 */

public abstract class BaseGamesFragment<T extends IBasePresenter> extends BaseFragment {
    @Override
    public void initData() {
        super.initData();
    }


    public abstract void toolbarRightClick(View v);

    public AfbApplication getApp() {
        return (AfbApplication) getActivity().getApplication();
    }

    public String getDate(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new java.util.Date());
        return date;
    }

}
