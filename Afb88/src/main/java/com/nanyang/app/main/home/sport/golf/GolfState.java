package com.nanyang.app.main.home.sport.golf;

import com.nanyang.app.main.home.sport.financial.FinancialState;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class GolfState extends FinancialState{


    public GolfState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public IBetHelper onSetBetHelper() {
        return new GolfBetHelper(getBaseView());
    }



}
