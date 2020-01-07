package com.nanyang.app.main.home.sport.tennis;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.main.home.sport.basketball.BasketballCommonState;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.main.OtherDoubleAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class TennisState extends BasketballCommonState {


    public TennisState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new BallBetHelper(getBaseView()) {
            @Override
            public String getBallG() {
                return "21";
            }
        };
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

    }

    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public boolean isMix() {
        return false;
    }


    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new OtherDoubleAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }

}
