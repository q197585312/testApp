package com.nanyang.app.main.home.sport.boxing;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BoxingTodayState extends BoxingState {
    public BoxingTodayState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_BOXING_TODAY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new BoxingEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "Running":
                getBaseView().switchState(new BoxingRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new BoxingOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Today), "Today", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Boxing));
    }


}
