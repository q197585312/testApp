package com.nanyang.app.main.home.sport.formula;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class FormulaTodayState extends FormulaState {
    public FormulaTodayState(SportContract.View baseView) {
        super(baseView);
    }




    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new FormulaEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "Running":
                getBaseView().switchState(new FormulaRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new FormulaOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Today), "Today", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Formula1));
    }


}
