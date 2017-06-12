package com.nanyang.app.main.home.sport.winterSport;

import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.tennis.TennisState;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class WinterSportState extends TennisState{

    public WinterSportState(SportContract.View baseView) {
        super(baseView);
    }
    @Override
    protected IBetHelper<BasketballMixInfo> onSetBetHelper() {
        return new WinterSportBetHelper(getBaseView());
    }
}
