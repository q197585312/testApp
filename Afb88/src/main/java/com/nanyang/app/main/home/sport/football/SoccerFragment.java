package com.nanyang.app.main.home.sport.football;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class SoccerFragment extends BaseSportFragment {



    @Override
    public void initData() {
        super.initData();

        String type = ((SportActivity) getActivity()).getType();
        switchType(type);//第一个刷新逻辑
        setTitle(getString(R.string.football));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new SoccerRunningState(this));
                break;
            case "Today":
                switchState(new SoccerTodayState(this));
                break;
            case "Early":
                switchState(new SoccerEarlyState(this));
                break;
            case "OutRight":
                switchState(new SoccerOutRightState(this));
                break;
            default:
                switchState(new SoccerTodayState(this));
                break;

        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Soccer);
    }


}
