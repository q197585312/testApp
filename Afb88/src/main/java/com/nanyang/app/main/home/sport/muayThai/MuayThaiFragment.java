package com.nanyang.app.main.home.sport.muayThai;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class MuayThaiFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.Muay_Thai));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new MuayThaiRunningState(this));
                break;
            case "Today":
                switchState(new MuayThaiTodayState(this));
                break;
            case "Early":
                switchState(new MuayThaiEarlyState(this));
                break;

            default:
                switchState(new MuayThaiTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Muay_Thai);
    }




}
