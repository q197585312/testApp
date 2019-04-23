package com.nanyang.app.main.home.sport.iceHockey;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class IceHockeyOutRightState extends OutRightState {
    public IceHockeyOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }


    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.IceHockey);
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_ICE_HOCKEY_OUTRIGHT+"&ot=e";
    }


}
