package com.nanyang.app.main.home.sport.tableTennis;

import com.nanyang.app.main.home.sport.tennis.TennisBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class TableTennisBetHelper extends TennisBetHelper {

    public TableTennisBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    public String getBallG() {
        return "57";
    }

/* @Override
    protected String getOddsUrl(BasketballMixInfo item, String type, boolean isHf, String odds,String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("g=57");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }*/


}
