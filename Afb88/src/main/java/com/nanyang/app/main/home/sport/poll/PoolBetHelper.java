package com.nanyang.app.main.home.sport.poll;

import com.nanyang.app.main.home.sport.tennis.TennisBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class PoolBetHelper extends TennisBetHelper {

    public PoolBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    public String getBallG() {
        return "11";
    }


/*  @Override
    protected String getOddsUrl(BasketballMixInfo item, String type, boolean isHf, String odds,String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        http://main55.afb88.com/_Bet/JRecPanel.aspx?g=16&b=home&oId=12556772&odds=11.7
        stringBuilder.append("g=16");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }*/


}
