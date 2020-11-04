package com.nanyang.app.main.home.sport.poll;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class PoolOutRightState extends OutRightState {
    public PoolOutRightState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Pool);
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_POOL_OUTRIGHT + "&ot=e";
    }


}
