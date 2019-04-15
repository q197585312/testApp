package com.nanyang.app.main.home.sport.superCombo;

import android.view.View;

import com.nanyang.app.main.home.sport.football.SoccerMixState;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class SuperComboState extends SoccerMixState{

    public SuperComboState(SportContract.View baseView) {
        super(baseView);
    }
    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new SuperComboBetHelper(getBaseView());
    }
    @Override
    protected void clickAdd(View v, BallInfo item, int position) {
        getBaseView().clickItemAdd(v,item,position);
    }
    @Override
    public boolean isMix() {
        return true;
    }
    @Override
    public boolean mix() {
        return true;
    }
}
