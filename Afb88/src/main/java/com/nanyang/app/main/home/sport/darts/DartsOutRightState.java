package com.nanyang.app.main.home.sport.darts;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/13.
 */

public class DartsOutRightState extends OutRightState {
    public DartsOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Darts);
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_DARTS_OUTRIGHT + "&ot=e";
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new DartsBetHelper(getBaseView());
    }
}
