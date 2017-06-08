package com.nanyang.app.main.home.sport.boxing;

import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.tennis.TennisState;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class BoxingState extends TennisState{

    public BoxingState(SportContract.View baseView) {
        super(baseView);
    }
    @Override
    protected IBetHelper<BasketballMixInfo> onSetBetHelper() {
        return new BoxingBetHelper(getBaseView());
    }
}
