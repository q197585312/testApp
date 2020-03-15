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

public class EuropeEarlyMixState extends EuropeMixState {
    public EuropeEarlyMixState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    protected void onChildConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {

    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1, (R.string.Early), "Early", getParentText());
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_EUROPE_MIX_EARLY;
    }


    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new EuropeTodayMixState(getBaseView()));
                break;

        }
    }

    @Override
    public boolean mix() {
        getBaseView().switchState(new EuropeEarlyState(getBaseView()));
        return super.mix();
    }
}
