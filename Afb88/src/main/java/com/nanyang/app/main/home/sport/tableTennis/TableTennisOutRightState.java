package com.nanyang.app.main.home.sport.tableTennis;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class TableTennisOutRightState extends OutRightState {
    public TableTennisOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_TABLE_TENNIS_OUTRIGHT+"&ot=e";
    }

    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Table_Tennis);
    }
}
