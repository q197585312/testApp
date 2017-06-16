package com.nanyang.app.main.home.sport.tableTennis;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class TableTennisFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new TableTennisRunningState(this));
                break;
            case "Today":
                switchState(new TableTennisTodayState(this));
                break;
            case "Early":
                switchState(new TableTennisEarlyState(this));
                break;
            case "OutRight":
                switchState(new TableTennisOutRightState(this));
                break;
            default:
                switchState(new TableTennisTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Table_Tennis);
    }

}
