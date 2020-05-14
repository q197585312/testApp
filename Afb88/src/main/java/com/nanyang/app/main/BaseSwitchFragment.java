package com.nanyang.app.main;

import android.support.v7.widget.Toolbar;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.Utils.StringUtils;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.utils.LogUtil;

/**
 * Created by Administrator on 2019/4/3.
 */

public abstract class BaseSwitchFragment<P extends IBasePresenter> extends BaseFragment<P> {

    BaseToolbarActivity baseToolbarActivity;
    public AfbDrawerViewHolder holder;

    @Override
    public void initView() {

        super.initView();
        baseToolbarActivity = (BaseToolbarActivity) getBaseActivity();
    }

    public BaseToolbarActivity getBaseToolbarActivity() {
        return baseToolbarActivity;
    }

    public void setToolbarVisibility(int b) {
        baseToolbarActivity.setToolbarVisibility(b);
    }


    public void setBackTitle(String title) {
        Toolbar toolbar = baseToolbarActivity.getToolbar();
        int visibility = toolbar.getVisibility();
        LogUtil.d("visibility", visibility);
        toolbar.setTitle(StringUtils.isNull(title) ? "" : title.toUpperCase());
    }


    public String switchTypeIndex = "";

    public void setSwitchTypeIndex(String switchType) {
        this.switchTypeIndex = switchType;
    }

    public void showContent() {
        AfbUtils.switchLanguage(AfbUtils.getLanguage(baseToolbarActivity), baseToolbarActivity);
    }

    public void setHolder(AfbDrawerViewHolder holder) {
        this.holder = holder;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden)
            showContent();
        LogUtil.d("showContent", getClass().getSimpleName() + ",hidden:" + hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden()) {
            showContent();
        }
        LogUtil.d("showContent", getClass().getSimpleName() + ",onResumePlay:");
    }

    public boolean checkCanBack() {
        return true;
    }

    public void switchFragment() {
    }
}
