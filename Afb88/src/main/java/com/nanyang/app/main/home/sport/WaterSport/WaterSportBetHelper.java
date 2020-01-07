package com.nanyang.app.main.home.sport.WaterSport;

import com.nanyang.app.main.home.sport.tennis.TennisBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class WaterSportBetHelper extends TennisBetHelper {

    public WaterSportBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    public String getBallG() {
        return "53";
    }


}
