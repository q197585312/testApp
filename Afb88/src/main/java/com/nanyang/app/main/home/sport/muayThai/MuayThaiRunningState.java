package com.nanyang.app.main.home.sport.muayThai;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class MuayThaiRunningState extends MuayThaiState {
    public MuayThaiRunningState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_MUAY_THAI_RUNNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new MuayThaiEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new MuayThaiTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.Running),"Running",getBaseView().getContextActivity().getString(R.string.Muay_Thai));
    }

}
