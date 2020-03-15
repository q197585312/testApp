package com.nanyang.app.main.home.sport.superCombo;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SuperComboEarlyState extends SuperComboState {
    public SuperComboEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1, (R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.SuperCombo));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_SUPER_COMBO_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new SuperComboTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new SuperComboRunningState(getBaseView()));
                break;

        }
    }

    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new SuperComboBetHelper(getBaseView()) {
        /*    @Override
            protected String getOddsUrl(BallInfo item, String type, boolean isHf, String odds, String params) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(AppConstant.getInstance().URL_ODDS);
                stringBuilder.append("g=99");
                if(!type.endsWith("_par")){
                    type=type+"_par";
                }
                stringBuilder.append("&b=" + type);

                stringBuilder.append("&oId=" + item.getSocOddsId());
                stringBuilder.append("&odds=" + odds);
                stringBuilder.append("&today=e");
                return stringBuilder.toString();
            }*/
        };
    }
}
