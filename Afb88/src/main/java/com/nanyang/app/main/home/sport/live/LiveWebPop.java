package com.nanyang.app.main.home.sport.live;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.additional.AddMBean;
import com.nanyang.app.main.home.sport.dialog.WebPop;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;

/**
 * Created by Administrator on 2019/11/28.
 */

public class LiveWebPop extends WebPop {


    private LinearLayout parentLl;

    public LiveWebPop(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    //  setContentView(R.layout.activity_live_web);
    @Override
    protected int onSetLayoutRes() {
        return R.layout.activity_live_web;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        parentLl = view.findViewById(R.id.common_ball_parent_ll);
    }

    public void setAdditionData(AddMBean additionData, BallAdapterHelper adapterHelper, BallInfo item) {
        adapterHelper.createAddedData(item, parentLl, additionData);
    }

    @Override
    protected void onClose() {
        super.onClose();
    }
}
