package com.nanyang.app.main.home.sport.baseball;

import com.nanyang.app.main.home.sport.basketball.BasketballCommonBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class BaseballBetHelper extends BasketballCommonBetHelper {

    public BaseballBetHelper(BetView baseView) {
        super(baseView);
    }


    //http://main55.afb88.com/_bet/JRecPanel.aspx?g=29&b=under&oId=12674629&odds=7

/*    protected String getOddsUrl(BallInfo item, String type, boolean isHf, String odds){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("g=29");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }*/

    @Override
    protected String getBallG() {
        return "29";
    }
}
