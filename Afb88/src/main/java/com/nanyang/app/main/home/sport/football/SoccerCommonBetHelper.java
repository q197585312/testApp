package com.nanyang.app.main.home.sport.football;

import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SoccerCommonBetHelper extends BallBetHelper<SoccerCommonInfo, BetView> {


    public SoccerCommonBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    public String getBallG() {
        return "1";
    }

}
