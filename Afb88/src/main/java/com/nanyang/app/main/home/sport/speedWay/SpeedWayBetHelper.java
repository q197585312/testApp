package com.nanyang.app.main.home.sport.speedWay;

import com.nanyang.app.main.home.sport.tennis.TennisBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SpeedWayBetHelper extends TennisBetHelper {

    public SpeedWayBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected String getBallG() {
        return "68";
    }


}
