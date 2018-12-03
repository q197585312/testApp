package com.nanyang.app.main.home.sport.myanmarOdds;

import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class MyanmarBetHelper extends BallBetHelper<MyanmarInfo, BetView> {


    public MyanmarBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected String getBallG() {
        return "1";
    }


}
