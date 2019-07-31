package com.nanyang.app.main.home.sport.tableTennis;

import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.tennis.TennisState;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class TableTennisState extends TennisState {

    public TableTennisState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new TableTennisBetHelper(getBaseView());
    }
}
