package com.nanyang.app.main.home.sport.USFootball;

import com.nanyang.app.main.home.sport.tennis.TennisBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class USFootballBetHelper extends TennisBetHelper {

    public USFootballBetHelper(BetView baseView) {
        super(baseView);
    }

//   http://a8206d.a36588.com/_Bet/JRecPanel.aspx?g=106&b=1&oId=12269721&odds=1.53
   /* @Override
    protected String getOddsUrl(BasketballMixInfo item, String type, boolean isHf, String odds,String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("g=106");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }*/


}
