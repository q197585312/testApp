package com.nanyang.app.main.home.sport.iceHockey;

import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sport.tennis.TennisBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class IceHockeyBetHelper extends TennisBetHelper {

    public IceHockeyBetHelper(BetView baseView) {
        super(baseView);
    }


    //http://main55.afb88.com/_bet/JRecPanel.aspx?g=29&b=under&oId=12674629&odds=7
    //http://main55.afb88.com/_Bet/JRecPanel.aspx?g=14&b=home&oId=12882940&odds=8.4
    @Override
    protected String getOddsUrl(BasketballMixInfo item, String type, boolean isHf, String odds,String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("g=14");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }


}
