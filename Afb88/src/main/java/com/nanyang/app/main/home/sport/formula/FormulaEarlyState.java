package com.nanyang.app.main.home.sport.formula;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class FormulaEarlyState extends FormulaState {
    public FormulaEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Formula1));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_FORMULA_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new FormulaTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new FormulaRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new FormulaOutRightState(getBaseView()));
                break;

        }
    }
}
