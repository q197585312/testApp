package com.nanyang.app.main.home.sport.formula;

import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sport.cycling.CyclingState;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class FormulaState extends CyclingState {

    public FormulaState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    protected IBetHelper<BasketballMixInfo> onSetBetHelper() {
        return new FormulaBetHelper(getBaseView());
    }

}
