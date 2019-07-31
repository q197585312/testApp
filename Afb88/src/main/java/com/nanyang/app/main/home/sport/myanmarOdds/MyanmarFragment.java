package com.nanyang.app.main.home.sport.myanmarOdds;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class MyanmarFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "";
    }

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.Myanmar_Odds));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new MyanmarRunningState(this));
                break;
            case "Today":
                switchState(new MyanmarTodayState(this));
                break;
            case "Early":
                switchState(new MyanmarEarlyState(this));
                break;
            default:
                switchState(new MyanmarTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Myanmar_Odds);
    }


}
