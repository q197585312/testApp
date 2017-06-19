package com.nanyang.app.main.home.sport.formula;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class FormulaFragment extends BaseSportFragment {

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
                switchState(new FormulaRunningState(this));
                break;
            case "Today":
                switchState(new FormulaTodayState(this));
                break;
            case "Early":
                switchState(new FormulaEarlyState(this));
                break;
            case "OutRight":
                switchState(new FormulaOutRightState(this));
                break;
            default:
                switchState(new FormulaTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Formula1);
    }

}
