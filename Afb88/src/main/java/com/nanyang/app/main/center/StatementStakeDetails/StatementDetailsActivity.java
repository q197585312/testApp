package com.nanyang.app.main.center.StatementStakeDetails;

import android.os.Bundle;

import com.nanyang.app.main.center.model.StatementStakeDetailsListBean;
import com.unkonw.testapp.libs.base.BaseActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class StatementDetailsActivity extends BaseActivity<StatementStakeDetailsPresenter> implements StatementStakeDetailsContact.View {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onGetData(List<StatementStakeDetailsListBean> data) {

    }

    @Override
    public void onFailed(String error) {

    }
}
