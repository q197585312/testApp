package com.nanyang.app;

import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.unkonw.testapp.libs.base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/20.
 */

public class AfbApplication extends BaseApplication {
    private BettingParPromptBean betParList;
    private String userName;

    @Override
    public void onCreate() {
        super.onCreate();
//        KLog.init(BuildConfig.LOG_DEBUG, "AFB");
//        Logger.setDebug(true);
    }

    private Map<String, Map<String, Map<Integer, BettingInfoBean>>> betDetail;

    public Map<String, Map<String, Map<Integer, BettingInfoBean>>> getBetDetail() {
        if (betDetail == null)
            betDetail = new HashMap<>();
        return betDetail;
    }

    public void setBetDetail(Map<String, Map<String, Map<Integer, BettingInfoBean>>> betDetail) {
        this.betDetail = betDetail;
    }

    public BettingParPromptBean getBetParList() {
        return betParList;
    }

    public void setBetParList(BettingParPromptBean betParList) {
        this.betParList = betParList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}


