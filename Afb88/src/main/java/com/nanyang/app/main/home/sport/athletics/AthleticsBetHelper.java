package com.nanyang.app.main.home.sport.athletics;

import com.nanyang.app.main.home.sport.tennis.TennisBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class AthleticsBetHelper extends TennisBetHelper {

    public AthleticsBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected String getBallG() {
        return "101";
    }


}
