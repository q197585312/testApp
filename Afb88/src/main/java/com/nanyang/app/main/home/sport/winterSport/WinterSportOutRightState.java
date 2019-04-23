package com.nanyang.app.main.home.sport.winterSport;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class WinterSportOutRightState extends OutRightState {
    public WinterSportOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.WinterSport);
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_WINTER_SPORT_OUTRIGHT+"&ot=e";
    }

}
