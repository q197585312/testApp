package com.nanyang.app.main;

import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public abstract class BaseToolbarActivity<T extends IBasePresenter> extends BaseActivity<T> {
    @Nullable
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Nullable
    @Bind(R.id.tv_toolbar_right)
    TextView tvToolbarRight;
    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

}
