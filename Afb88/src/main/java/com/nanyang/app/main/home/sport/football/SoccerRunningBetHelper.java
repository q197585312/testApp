package com.nanyang.app.main.home.sport.football;

import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class SoccerRunningBetHelper extends SoccerCommonBetHelper {
    public SoccerRunningBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    public String getBallG() {
        return "1";
    }

}
