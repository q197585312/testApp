package com.nanyang.app;

import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.unkonw.testapp.libs.base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

import cn.finalteam.toolsfinal.logger.Logger;

/**
 * Created by Administrator on 2017/2/20.
 */

public class AfbApplication extends BaseApplication {
    private BettingParPromptBean betParList;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.setDebug(true);
    }
    private Map<String,Map<String,Map<Integer,BettingInfoBean>>> betDetail;
    public  Map<String,Map<String,Map<Integer,BettingInfoBean>>> getBetDetail() {
        if(betDetail==null)
            betDetail=new HashMap<>();
        return betDetail;
    }

    public void setBetDetail( Map<String,Map<String,Map<Integer,BettingInfoBean>>> betDetail) {
        this.betDetail = betDetail;
    }

    public BettingParPromptBean getBetParList() {
        return betParList;
    }

    public void setBetParList(BettingParPromptBean betParList) {
        this.betParList = betParList;
    }
}


