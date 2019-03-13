package com.nanyang.app.main.home.sport.darts;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballEarlyMixState;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/6/2.
 */

class DartsEarlyMixState extends BasketballEarlyMixState {

    public DartsEarlyMixState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new DartsBetHelper(getBaseView());
    }

    @Override
    public boolean mix() {
        getBaseView().switchState(new DartsEarlyState(getBaseView()));
        return super.mix();
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_DARTS_EARLY_MIX;
    }
    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early),"Early",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Darts));
    }
}
