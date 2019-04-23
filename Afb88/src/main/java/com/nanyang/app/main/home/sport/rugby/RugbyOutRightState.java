package com.nanyang.app.main.home.sport.rugby;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class RugbyOutRightState extends OutRightState {
    public RugbyOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Rugby);
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_RUGBY_OUTRIGHT+"&ot=e";
    }

}
