package com.nanyang.app.main.home.sport.motorSport;

import androidx.core.content.ContextCompat;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class MotorRunningState extends MotorState {
    public MotorRunningState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new MotorEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new MotorTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.running), "Running", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Motor_Sports));
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
