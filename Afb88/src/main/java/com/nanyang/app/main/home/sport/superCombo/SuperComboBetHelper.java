package com.nanyang.app.main.home.sport.superCombo;

import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SuperComboBetHelper extends BallBetHelper<BallInfo, BetView> {

    public SuperComboBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    public String getBallG() {
        return "99";
    }

}
