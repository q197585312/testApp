package com.nanyang.app.main.home.sport.myanmarOdds;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/21.
 */

public class MyanmarEarlyState extends MyanmarState {
    public MyanmarEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early),"Early",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Myanmar_Odds));
    }
    @Override
    protected String getAllOddsUrl() {
        String tfDate = ((BaseToolbarActivity) getBaseView().getIBaseContext().getBaseActivity()).getApp().getUser().getTfDate();
        return AppConstant.getInstance().HOST+"_view/MOddsGen2.ashx?ot=e&update=true&r=2000335655&wd="+tfDate+"&ia=0&oview=0&ov=0&&LID=";
    }
    @Override
    protected String getRefreshUrl() {
        String tfDate = ((BaseToolbarActivity) getBaseView().getIBaseContext().getBaseActivity()).getApp().getUser().getTfDate();
        return AppConstant.getInstance().URL_SOCCER_MYANMAR_EARLY+"&wd="+ tfDate+"&mt="+((SportActivity)getBaseView().getIBaseContext().getBaseActivity()).getMarketType().getType();
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new MyanmarTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new MyanmarRunningState(getBaseView()));
                break;
        }
    }
}
