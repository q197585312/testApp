package com.nanyang.app.main.home.sport.europe;

import android.view.View;

import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sportInterface.BetView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class EuropeRunningBetHelper extends EuropeBetHelper {
    public EuropeRunningBetHelper(BetView baseView) {
        super(baseView);
    }



    @Override
    protected void showBetPopView(List<AfbClickBetBean> bean, View v) {
        super.showBetPopView(bean,v);
        betPop.setrTMatchInfo(item);

    }
}
