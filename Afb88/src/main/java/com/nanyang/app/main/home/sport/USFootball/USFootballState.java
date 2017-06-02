package com.nanyang.app.main.home.sport.USFootball;

import com.nanyang.app.main.home.sport.basketball.BasketballCommonState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class USFootballState extends BasketballCommonState {

    public USFootballState(SportContract.View baseView) {
        super(baseView);
    }

}
