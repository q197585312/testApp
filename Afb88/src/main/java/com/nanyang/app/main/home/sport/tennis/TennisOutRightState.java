package com.nanyang.app.main.home.sport.tennis;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class TennisOutRightState extends OutRightState {
    public TennisOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Tennis);
    }

    //http://a8206d.a36588.com/_view/OddsOutGen.ashx?g=36&ot=e&update=true&r=447758500&LID=&_=1490064297392
    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_TENNIS_OUTRIGHT+"&ot=e";
    }

}
