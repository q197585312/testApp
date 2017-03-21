package com.nanyang.app.main.home.sport.game4d;

import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sport.financial.FinancialState;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.SportContract2;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class Game4dState extends FinancialState{

    public Game4dState(SportContract2.View baseView) {
        super(baseView);
    }
    @Override
    protected IBetHelper<BasketballMixInfo> onSetBetHelper() {
        return new Game4dBetHelper(getBaseView());
    }
}
