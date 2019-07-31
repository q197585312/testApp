package com.nanyang.app.main.home.sport.cricket;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class CricketOutRightState extends OutRightState {
    public CricketOutRightState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Cricket);
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_CRICKET_OUTRIGHT + "&ot=e";
    }

}
