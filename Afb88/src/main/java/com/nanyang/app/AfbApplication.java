package com.nanyang.app;

import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.tencent.bugly.crashreport.CrashReport;
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
//        SkinAppManager.getInstance().initSkinLoader(this);
//        KLog.init(BuildConfig.LOG_DEBUG, "AFB");
        Logger.setDebug(true);
        CrashReport.initCrashReport(getApplicationContext(), "ec1874f442", true);
    }


    public BettingParPromptBean getBetParList() {
        return betParList;
    }

    public void setBetParList(BettingParPromptBean betParList) {
        this.betParList = betParList;
    }


}


