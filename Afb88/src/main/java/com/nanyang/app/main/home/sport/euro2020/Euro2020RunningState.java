package com.nanyang.app.main.home.sport.euro2020;

import androidx.core.content.ContextCompat;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.majorLeagues.FiveMajorState;

/**
 * Created by ASUS on 2019/3/26.
 */

public class Euro2020RunningState extends FiveMajorState {
    public Euro2020RunningState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    public String getDbId() {
        return "1";
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.running), "Running", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Euro_2020));
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
