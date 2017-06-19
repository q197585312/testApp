package com.nanyang.app.main.home.sport.formula;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class FormulaOutRightState extends OutRightState {
    public FormulaOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.OutRight),"OutRight",getBaseView().getContextActivity().getString(R.string.Formula1));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_FORMULA_OUTRIGHT+"&ot=e";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Today":
                getBaseView().switchState(new FormulaTodayState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(new FormulaEarlyState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new FormulaRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(this);
                break;
        }

    }
}
