package com.nanyang.app.main.home.sport.game4d;

import com.nanyang.app.main.home.sport.financial.FinancialState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class Game4dState extends FinancialState{

    public Game4dState(SportContract.View baseView) {
        super(baseView);
    }
/*    @Override
    public IBetHelper<BasketballMixInfo> onSetBetHelper() {
        return new Game4dBetHelper(getBaseView());
    }*/
}
