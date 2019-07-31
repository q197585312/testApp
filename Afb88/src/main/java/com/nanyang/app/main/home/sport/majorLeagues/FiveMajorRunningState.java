package com.nanyang.app.main.home.sport.majorLeagues;

import android.support.v4.content.ContextCompat;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by ASUS on 2019/3/26.
 */

public class FiveMajorRunningState extends FiveMajorState {
    public FiveMajorRunningState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_FIVE_MAJOR_RUNNING;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running), "Running", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Five_Major_Match));
    }

    @Override
    public int getTitleContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.green_content1);
    }

    @Override
    public int getNormalContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.green_content2);
    }
}
