package com.nanyang.app.main.home.sport.poll;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
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
    public boolean mix() {
        return false;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getBaseActivity().getString(R.string.OutRight),"OutRight",getBaseView().getBaseActivity().getString(R.string.Pool));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_POOL_OUTRIGHT+"&ot=e";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Today":
                getBaseView().switchState(new PoolTodayState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(new PoolEarlyState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new PoolRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(this);
                break;
        }

    }
}
