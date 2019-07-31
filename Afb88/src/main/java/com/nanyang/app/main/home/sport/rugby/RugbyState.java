package com.nanyang.app.main.home.sport.rugby;

import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.tennis.TennisState;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class RugbyState extends TennisState {

    public RugbyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new RugbyBetHelper(getBaseView());
    }
}
