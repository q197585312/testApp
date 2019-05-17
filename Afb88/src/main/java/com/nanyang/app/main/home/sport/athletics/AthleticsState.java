package com.nanyang.app.main.home.sport.athletics;

import com.nanyang.app.main.home.sport.main.OtherDoubleAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.tennis.TennisState;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class AthleticsState extends TennisState{

    public AthleticsState(SportContract.View baseView) {
        super(baseView);
    }
    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new AthleticsBetHelper(getBaseView());
    }
    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new OtherDoubleAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }
}
