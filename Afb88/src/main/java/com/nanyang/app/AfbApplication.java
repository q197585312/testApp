package com.nanyang.app;

import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.unkonw.testapp.libs.base.BaseApplication;

import cn.finalteam.toolsfinal.logger.Logger;

/**
 * Created by Administrator on 2017/2/20.
 */

public class AfbApplication extends BaseApplication {
    private BettingParPromptBean betParList;

    public PersonalInfo getUser() {
        return user;
    }

    private PersonalInfo user=new PersonalInfo();

    @Override
    public void onCreate() {
        super.onCreate();
//        KLog.init(BuildConfig.LOG_DEBUG, "AFB");
        Logger.setDebug(true);
    }


    public BettingParPromptBean getBetParList() {
        return betParList;
    }

    public void setBetParList(BettingParPromptBean betParList) {
        this.betParList = betParList;
    }


}


