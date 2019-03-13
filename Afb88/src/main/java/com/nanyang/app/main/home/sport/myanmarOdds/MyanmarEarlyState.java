package com.nanyang.app.main.home.sport.myanmarOdds;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.unkonw.testapp.libs.utils.TimeUtils;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/21.
 */

public class MyanmarEarlyState extends MyanmarState {
    public MyanmarEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getBaseActivity().getString(R.string.Early),"Early",getBaseView().getBaseActivity().getString(R.string.Myanmar_Odds));
    }
    @Override
    protected String getAllOddsUrl() {
        return AppConstant.getInstance().HOST+"_view/MOddsGen2.ashx?ot=e&update=true&r=2000335655&wd="+TimeUtils.dateFormat(new Date(),"yyyy-MM-dd")+"&ia=0&oview=0&ov=0&&LID=";
    }
    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_SOCCER_MYANMAR_EARLY+"&wd="+ TimeUtils.dateFormat(new Date(),"yyyy-MM-dd")+param.getType();
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
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
