package com.nanyang.app.main.home.sport.europe;

import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class EuropeBetHelper extends BallBetHelper<BallInfo, BetView> {

    public EuropeBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected String getBallG() {
        return "182";
    }


}
