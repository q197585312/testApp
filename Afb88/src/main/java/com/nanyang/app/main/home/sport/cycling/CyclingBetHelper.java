package com.nanyang.app.main.home.sport.cycling;

import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sport.tennis.TennisBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class CyclingBetHelper extends TennisBetHelper {

    public CyclingBetHelper(BetView baseView) {
        super(baseView);
    }



    @Override
    protected String getOddsUrl(BasketballMixInfo item, String type, boolean isHf, String odds,String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("g=65");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }


}
