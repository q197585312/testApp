package com.nanyang.app.main.home.sport.baseball;

import com.nanyang.app.main.home.sport.basketball.BasketballCommonAdapterHelper;
import com.nanyang.app.main.home.sport.basketball.BasketballCommonState;
import com.nanyang.app.main.home.sport.main.OtherDoubleAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class BaseballState extends BasketballCommonState {

    public BaseballState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new BaseballBetHelper(getBaseView());
    }

    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new OtherDoubleAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }



    @Override
    protected BasketballCommonAdapterHelper onSetCommonAdapterHelper() {
        return null;
    }

    @Override
    public boolean mix() {
        return false;
    }

}
