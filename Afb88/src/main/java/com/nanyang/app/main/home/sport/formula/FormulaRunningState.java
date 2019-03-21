package com.nanyang.app.main.home.sport.formula;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class FormulaRunningState extends FormulaState {
    public FormulaRunningState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_FORMULA_RUNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new FormulaEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new FormulaTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new FormulaOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running),"Running",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Formula1));
    }

}
