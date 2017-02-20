package com.nanyang.app;

import com.unkonw.testapp.libs.base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/20.
 */

public class AfbApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public  Map<String,Map<String,Map<Integer,BettingInfoBean>>> getBetDetail() {
        if(betDetail==null)
            betDetail=new HashMap<>();
        return betDetail;
    }

    public void setBetDetail( Map<String,Map<String,Map<Integer,BettingInfoBean>>> betDetail) {
        this.betDetail = betDetail;
    }
}

    public BettingParPromptBean getBetParList() {
        return betParList;
    }

    public void setBetParList(BettingParPromptBean betParList) {
        this.betParList = betParList;
    }
