package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class BasketballMixBetHelper extends BallBetHelper<BasketballMixInfo, BetView> {


    public BasketballMixBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected String getBallG() {
        return "9";
    }

}
