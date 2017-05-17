package com.nanyang.app.main.home.sport.europe;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.TableSportInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */

public class EuropeEarlyState extends EuropeState {
    public EuropeEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Early), "Early",getBaseView().getContextActivity().getString(R.string.Tennis));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_TENNIS_EARLY;
    }


    @Override
    protected void onTypeClick(MenuItemInfo item) {
        if (item.getType().equals("Today")) {
            getBaseView().switchState(new EuropeTodayState(getBaseView()));
        } else if (item.getType().equals("Early")) {
            getBaseView().switchState(this);
        }
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new EuropeTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new EuropeRunningState(getBaseView()));
                break;

        }
    }
}
