package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class BasketballFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "2";
    }

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.Basketball));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new BasketballRunningState(this));
                break;
            case "Today":
                switchState(new BasketballTodayState(this));
                break;
            case "Early":
                switchState(new BasketballEarlyState(this));
                break;
            case "OutRight":
                switchState(new BasketballOutRightState(this));
                break;
            default:
                switchState(new BasketballTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Basketball);
    }


}
