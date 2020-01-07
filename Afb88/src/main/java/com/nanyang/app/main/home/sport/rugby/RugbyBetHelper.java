package com.nanyang.app.main.home.sport.rugby;

import com.nanyang.app.main.home.sport.tennis.TennisBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class RugbyBetHelper extends TennisBetHelper {

    public RugbyBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    public String getBallG() {
        return "17";
    }
//http://main55.afb88.com/_bet/JRecPanel.aspx?g=17&b=home&oId=12875124&odds=8
  /*  @Override
    protected String getOddsUrl(BasketballMixInfo item, String type, boolean isHf, String odds,String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("g=17");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }*/


}
