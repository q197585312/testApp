package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2018/12/5.
 */

public class BasketballRunningBetHelper extends BasketballCommonBetHelper {
    public BasketballRunningBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected String getBallG() {
        return "1";
    }
}
