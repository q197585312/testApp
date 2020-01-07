package com.nanyang.app.main.home.sport.beachSport;

import com.nanyang.app.main.home.sport.tennis.TennisBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class BeachSportBetHelper extends TennisBetHelper {

    public BeachSportBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    public String getBallG() {
        return "67";
    }


}
