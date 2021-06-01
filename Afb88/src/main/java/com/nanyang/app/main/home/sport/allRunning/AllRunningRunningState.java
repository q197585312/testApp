package com.nanyang.app.main.home.sport.allRunning;

import androidx.core.content.ContextCompat;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.europe.EuropeRunningAdapterHelper;
import com.nanyang.app.main.home.sport.football.SoccerRunningAdapterHelper;
import com.nanyang.app.main.home.sport.main.OtherRunningDoubleAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;

/**
 * Created by ASUS on 2019/4/23.
 */

class AllRunningRunningState extends AllRunningCommonState {
    public AllRunningRunningState(SportContract.View baseView) {
        super(baseView);
        ot = "r";
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,(R.string.running), "Running", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running));
    }


    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Running":
                getBaseView().switchState(this);
                break;

        }
    }

    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        if (fragment.currentIdBean.getId().equals("182"))
            return new EuropeRunningAdapterHelper(baseView.getIBaseContext().getBaseActivity());
        else if (fragment.currentIdBean.getId().equals("1")) {
            return new SoccerRunningAdapterHelper(baseView.getIBaseContext().getBaseActivity());
        } else {
            return new OtherRunningDoubleAdapterHelper(baseView.getIBaseContext().getBaseActivity());
        }
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
