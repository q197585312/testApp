package com.nanyang.app.main.home.sport.muayThai;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sportInterface.SportContract2;

/**
 * Created by Administrator on 2017/3/13.
 */

public class MuayThaiTodayState extends MuayThaiState {
    public MuayThaiTodayState(SportContract2.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_MUAY_THAI_TODAY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new MuayThaiEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "Running":
                getBaseView().switchState(new MuayThaiRunningState(getBaseView()));
                break;
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Today), "Today", getBaseView().getContextActivity().getString(R.string.Muay_Thai));
    }


}
