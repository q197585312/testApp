package com.nanyang.app.main.home.sport.financial;

import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.basketball.BasketballMixBetHelper;
import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class FinancialBetHelper extends BasketballMixBetHelper{

    public FinancialBetHelper(BetView baseView) {
        super(baseView);
    }

//http://a8206d.a36588.com/_bet/JRecPanel.aspx?g=7&b=over&oId=12264569&odds=9.5
    @Override
    protected String getOddsUrl(BasketballMixInfo item, String type, boolean isHf, String odds,String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.URL_ODDS);
        stringBuilder.append("g=7");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }


}
