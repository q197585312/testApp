package com.nanyang.app.main.home.sport.europe;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/13.
 */

public class EuropeEarlyState extends EuropeState {
    public EuropeEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early", getParentText());
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_EUROPE_EARLY;
    }


    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
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

    @Override
    protected void onChildConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {

    }

    @Override
    public boolean mix() {
        getBaseView().switchState(new EuropeEarlyMixState(getBaseView()));
        return true;
    }
}
