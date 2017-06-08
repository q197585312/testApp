package com.nanyang.app.main.home.sport.boxing;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class BoxingFragment extends BaseSportFragment {

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
                switchState(new BoxingRunningState(this));
                break;
            case "Today":
                switchState(new BoxingTodayState(this));
                break;
            case "Early":
                switchState(new BoxingEarlyState(this));
                break;
            case "OutRight":
                switchState(new BoxingOutRightState(this));
                break;
            default:
                switchState(new BoxingTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Boxing);
    }



}
