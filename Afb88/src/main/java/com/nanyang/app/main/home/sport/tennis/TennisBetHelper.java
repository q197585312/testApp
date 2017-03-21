package com.nanyang.app.main.home.sport.tennis;

import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.basketball.BasketballMixBetHelper;
import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class TennisBetHelper extends BasketballMixBetHelper{

    public TennisBetHelper(BetView baseView) {
        super(baseView);
    }

//http://a8206d.a36588.com/_Bet/JRecPanel.aspx?g=21&b=2&oId=12266526&odds=1.41
    //http://a8206d.a36588.com/_Bet/JRecPanel.aspx?g=21&b=home&oId=12269880&odds=8.8
//    http://a8197c.a36588.com/_Bet/JRecPanel.aspx?g=21&b=8.8&oId=12269880&odds=8.8 (263ms)
    @Override
    protected String getOddsUrl(BasketballMixInfo item, String type, boolean isHf, String odds) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.URL_ODDS);
        stringBuilder.append("g=21");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());

        stringBuilder.append("&odds=" + odds);

        return stringBuilder.toString();
    }
//    http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=9&b=home&oId=12264737&odds=9.6
    //http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=9&b=even&oId=12264963&oId_fh=12264964&odds=9.4&isFH=true

}
