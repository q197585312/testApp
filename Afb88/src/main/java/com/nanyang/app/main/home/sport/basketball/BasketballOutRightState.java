package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BasketballOutRightState extends OutRightState {


    public BasketballOutRightState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Basketball);
    }



}
