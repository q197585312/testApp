package com.nanyang.app.main.home.sport.europe;

import androidx.core.content.ContextCompat;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/13.
 */

public class EuropeRunningState extends EuropeState {
    public EuropeRunningState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_EUROPE_RUNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new EuropeEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new EuropeTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;

        }
    }

    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new EuropeRunningBetHelper(getBaseView());
    }


/*    @Override
    protected void clickHallBtn(View v, BallInfo item, int position) {
        super.clickHallBtn(v, item, position);
        int nextNotRepeat = getNextNotRepeat(position);
        getBaseView().onWebShow(nextNotRepeat, position, item, v);


    }*/


    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,(R.string.running), "Running", getParentText());
    }

    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new EuropeRunningAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }

    @Override
    public int getTitleContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.green_content1);
    }

    @Override
    public int getNormalContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.green_content2);
    }
}
