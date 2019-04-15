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

public class EuropeTodayMixState extends EuropeMixState {
    public EuropeTodayMixState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_EUROPE_MIX_TODAY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new EuropeEarlyMixState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;

        }
    }



    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today),"Today",getParentText());
    }

    @Override
    public boolean mix() {
        getBaseView().switchState(new EuropeTodayState(getBaseView()));
        return super.mix();
    }

    @Override
    protected void onChildConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {

    }

}
