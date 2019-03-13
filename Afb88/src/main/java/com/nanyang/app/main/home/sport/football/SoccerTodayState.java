package com.nanyang.app.main.home.sport.football;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerTodayState extends SoccerCommonState {
    public SoccerTodayState(SportContract.View baseView) {
        super(baseView);

    }



    @Override
    public boolean mix() {
        getBaseView().switchState(new SoccerTodayMixState(getBaseView()));
        return true;
    }

    @Override
    protected String getAllOddsUrl() {
        return AppConstant.getInstance().HOST+"_view/OddsPageSetting.aspx?ot=t&ov=0&wd=&tf=-1&isPageSingDouble=RMOdds1&m=save";
    }
    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_FOOTBALL_TODAY+param.getType();
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Running":
                getBaseView().switchState(new SoccerRunningState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(new SoccerEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new SoccerOutRightState(getBaseView()));
                break;
        }

    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getBaseActivity().getString(R.string.Today),"Today",getBaseView().getBaseActivity().getString(R.string.football));
    }

    @Override
    protected SoccerCommonAdapterHelper onSetCommonAdapterHelper() {
        return new SoccerTodayAdapterHelper(getBaseView().getBaseActivity());
    }
}
