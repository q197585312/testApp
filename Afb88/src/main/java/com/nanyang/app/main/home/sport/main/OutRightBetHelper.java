package com.nanyang.app.main.home.sport.main;

import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BetView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/3/15.
 */

public class OutRightBetHelper extends SportBetHelper<SportInfo, BetView> {

    public OutRightBetHelper(BetView baseView) {
        super(baseView);
    }
    @Override
    public Disposable clickOdds(SportInfo item, String type, String odds, TextView v, boolean isHf, String sc) {

        AfbClickResponseBean betAfbList = ((AfbApplication) AfbApplication.getInstance()).getBetAfbList();
        String betOddsUrl = "";
        //http://www.afb1188.com/Bet/hBetOdds.ashx?BTMD=S&coupon=0&BETID=|1|50|473355||&_=1543560701987
        if (betAfbList == null || betAfbList.getList().size() == 0 || (betAfbList.getList().size() == 1)) {
            betOddsUrl = "BTMD=S&coupon=0&BETID=" + "|1|50|" + item.getSocOddsId() + "||";
        } else {
            getBaseView().onFailed("不能点击过关");
            return new CompositeDisposable();
        }
        //http://www.afb1188.com/Bet/hBetOdds.ashx?BTMD=S&coupon=0&BETID=s|home|1|469195||&_=1543457322841
        return getDisposable(v, isHf, betOddsUrl);
    }
}
