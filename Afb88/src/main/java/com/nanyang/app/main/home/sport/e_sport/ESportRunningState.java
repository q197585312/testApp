package com.nanyang.app.main.home.sport.e_sport;

import android.support.v4.content.ContextCompat;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OtherRunningDoubleAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;

/**
 * Created by Administrator on 2017/3/13.
 */

public class ESportRunningState extends ESportState {
    public ESportRunningState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_E_SPORT_RUNNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new ESportEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new ESportTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new ESportOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.running), "Running", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Specials_4D));
    }

    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new OtherRunningDoubleAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
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
