package com.nanyang.app.main.home.sport.cricket;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OtherRunningDoubleAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;

/**
 * Created by Administrator on 2017/3/13.
 */

public class CricketRunningState extends CricketState {
    public CricketRunningState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_CRICKET_RUNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new CricketEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new CricketTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new CricketOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running),"Running",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Cricket));
    }
    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new OtherRunningDoubleAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }
}
