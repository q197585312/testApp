package com.nanyang.app.main.home.sport.myanmarOdds;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/21.
 */

public class MyanmarRunningState extends MyanmarState {
    public MyanmarRunningState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Running), "Running", getBaseView().getContextActivity().getString(R.string.Myanmar_Odds));
    }

    @Override
    protected String getAllOddsUrl() {
        return AppConstant.getInstance().HOST + "_view/MOddsGen2.ashx?ot=r&update=true&r=1849481838&ov=0&LID=";
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_SOCCER_MYANMAR_RUNNING + param.getType();
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new MyanmarEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new MyanmarTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;
        }
    }

    @Override
    public IAdapterHelper<MyanmarInfo> onSetAdapterHelper() {
        return new MyanmarRunningAdapterHelper(getBaseView().getContextActivity());
    }

    @Override
    public IBetHelper<MyanmarInfo> onSetBetHelper() {
        return new MyanmarRunningBetHelper(getBaseView());
    }
}
