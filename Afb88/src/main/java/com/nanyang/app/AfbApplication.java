package com.nanyang.app;

import com.nanyang.app.Utils.SoundPlayUtils;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.tencent.bugly.crashreport.CrashReport;
import com.unkonw.testapp.libs.base.BaseApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import cn.finalteam.toolsfinal.logger.Logger;

/**
 * Created by Administrator on 2017/2/20.
 */

public class AfbApplication extends BaseApplication {


    private AfbClickResponseBean betAfbList;

    public List<AllBannerImagesBean.MainBannersBean> getListMainPictures() {
        return listMainPictures;
    }

    public List<AllBannerImagesBean.BannersBean> getListMainBanners() {
        return listMainBanners;
    }

    private List<AllBannerImagesBean.MainBannersBean> listMainPictures;
    private List<AllBannerImagesBean.BannersBean> listMainBanners;

    public void setUser(PersonalInfo user) {
        this.user = user;
    }

    public PersonalInfo getUser() {
        return user;
    }

    private PersonalInfo user = new PersonalInfo();

    @Override
    public void onCreate() {
        super.onCreate();
//        SkinAppManager.getInstance().initSkinLoader(this);
//        KLog.init(BuildConfig.LOG_DEBUG, "AFB");
        SoundPlayUtils.init(this);
        Logger.setDebug(true);
        closeAndroidPDialog();
        AfbUtils.initAllSprotMap();
        CrashReport.initCrashReport(getApplicationContext(), "ec1874f442", true);
    }


    public AfbClickResponseBean getBetParList() {
        return getBetAfbList();
    }

    public void setBetParList(AfbClickResponseBean betParList) {
        this.betAfbList = betParList;
    }


    public AfbClickResponseBean getBetAfbList() {
        return betAfbList;
    }

    public void setBetAfbList(AfbClickResponseBean betAfbList) {
        this.betAfbList = betAfbList;
    }

    public String getRefreshOddsUrl() {

        if (betAfbList == null || betAfbList.getList() == null || betAfbList.getList().size() == 0)
            return "";
        String ids = "";
        String betOddsUrl = "BTMD=S&coupon=0&BETID=";
        if (betAfbList.getList().size() == 1) {
            String typeOdds = betAfbList.getList().get(0).getOddsType();
            String itemId = betAfbList.getList().get(0).getId();
            if (!cn.finalteam.toolsfinal.StringUtils.isEmpty(typeOdds) && typeOdds.endsWith("_par")) {
                String replace = itemId.replaceFirst(typeOdds, typeOdds.substring(0, typeOdds.indexOf("_par")));
                itemId = replace;
            }
            betOddsUrl = "BTMD=S&coupon=0&BETID=" + itemId;
        } else {
            for (AfbClickBetBean afbClickBetBean : betAfbList.getList()) {
                String itemId = afbClickBetBean.getId();
                ids += itemId + ",";
            }
            ids = ids.substring(0, ids.length() - 1);
            betOddsUrl = "BTMD=P&coupon=1&BETID=" + ids;
        }


        return AppConstant.getInstance().URL_ODDS + betOddsUrl;

    }

    private void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setListMainPictures(List<AllBannerImagesBean.MainBannersBean> listMainPictures) {
        this.listMainPictures = listMainPictures;
    }

    public void setListMainBanners(List<AllBannerImagesBean.BannersBean> listMainBanners) {
        this.listMainBanners = listMainBanners;
    }
}


