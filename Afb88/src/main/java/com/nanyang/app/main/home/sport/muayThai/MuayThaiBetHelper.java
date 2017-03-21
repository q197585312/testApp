package com.nanyang.app.main.home.sport.muayThai;

import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.basketball.BasketballMixBetHelper;
import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class MuayThaiBetHelper extends BasketballMixBetHelper{

    public MuayThaiBetHelper(BetView baseView) {
        super(baseView);
    }

//  http://a8206d.a36588.com/_Bet/JRecPanel.aspx?g=108&b=away&oId=12271261&odds=8
    @Override
    protected String getOddsUrl(BasketballMixInfo item, String type, boolean isHf, String odds) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.URL_ODDS);
        stringBuilder.append("g=108");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }


}
