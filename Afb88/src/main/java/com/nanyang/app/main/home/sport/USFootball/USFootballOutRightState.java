package com.nanyang.app.main.home.sport.USFootball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class USFootballOutRightState extends OutRightState {
    public USFootballOutRightState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.US_Football);
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_US_FOOTBALL_OUTRIGHT + "&ot=e";
    }


}
