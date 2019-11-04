package com.nanyang.app;

import android.util.Log;
import cn.finalteam.toolsfinal.logger.Logger;
import com.nanyang.app.Utils.SoundPlayUtils;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.nanyang.app.main.Setting.RefreshDataBean;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.tencent.bugly.crashreport.CrashReport;
import com.unkonw.testapp.libs.base.BaseApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 */

public class AfbApplication extends BaseApplication {


    private AfbClickResponseBean betAfbList;
    private boolean isGoHome = false;
    private SettingAllDataBean settingAllDataBean;

    private List<OddsClickBean> mixBetList = new ArrayList<>();
    private OddsClickBean currentBet;


    public RefreshDataBean getRefreshDataBean() {
        return refreshDataBean;
    }

    private RefreshDataBean refreshDataBean;

    public String getQuickAmount() {
        return quickAmount;
    }

    private String quickAmount = "";

    public MenuItemInfo getOddsType() {
        return AfbUtils.getOddsTypeByType(this, oddsType.getType(), getSettingAllDataBean().getCurCode());
    }

    public void setOddsType(MenuItemInfo oddsType) {
        this.oddsType = oddsType;
    }

    public MenuItemInfo<String> getMarketType() {
        return AfbUtils.getMarketByType(this, marketType.getType());
//        return marketType;
    }

    public void setMarketType(MenuItemInfo marketType) {
        this.marketType = marketType;
    }

    private MenuItemInfo oddsType;
    private MenuItemInfo marketType;

    public void setSort(int sort) {
        this.sort = sort;
    }

    private int sort;

    public boolean isGoHome() {
        return isGoHome;
    }

    public void setGoHome(boolean goHome) {
        isGoHome = goHome;
    }

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
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setAppChannel(BuildConfig.FLAVOR);  //设置渠道
        CrashReport.initCrashReport(getApplicationContext(), "ec1874f442", false, strategy);

    }


    public AfbClickResponseBean getBetParList() {
        return getBetAfbList();
    }

    public void setBetAfbList(AfbClickResponseBean betParList) {
        this.betAfbList = betParList;
        if (betParList == null)
            clearMixBetList();
    }


    public AfbClickResponseBean getBetAfbList() {
        return betAfbList;
    }


    public String getRefreshCurrentOddsUrl() {

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

    public String getRefreshMixOddsUrl() {

        if (getMixBetList() == null || getMixBetList().size() == 0)
            return "";
        String ids = "";
        String betOddsUrl = "BTMD=S&coupon=0&BETID=";
        if (getMixBetList().size() == 1) {

            betOddsUrl = "BTMD=S&coupon=0&BETID=" + getMixBetList().get(0).getBETID();
        } else {
            for (OddsClickBean afbClickBetBean : getMixBetList()) {
                String itemId = afbClickBetBean.getBETID_PAR();
                ids += itemId + ",";
            }
            ids = ids.substring(0, ids.length() - 1);
            betOddsUrl = "BTMD=P&coupon=1&BETID=" + ids;
        }
        return AppConstant.getInstance().URL_ODDS + betOddsUrl;

    }

    public String getRefreshSingleOddsUrl() {

        if (getMixBetList() == null || getMixBetList().size() == 0)
            return "";
        String ids = "";
        String betOddsUrl = "BTMD=S&coupon=0&BETID=";
        if (getMixBetList().size() >= 1) {
            betOddsUrl = "BTMD=S&coupon=0&BETID=" + getMixBetList().get(getMixBetList().size() - 1).getBETID();
            return AppConstant.getInstance().URL_ODDS + betOddsUrl;
        }
        return "";

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

    public int getSort() {
        return sort;
    }

    public SettingAllDataBean getSettingAllDataBean() {
        return settingAllDataBean;
    }

    public void setSettingAllDataBean(SettingAllDataBean settingAllDataBean) {
        this.settingAllDataBean = settingAllDataBean;
        oddsType = AfbUtils.getOddsTypeByType(this, settingAllDataBean.getAccType(), getSettingAllDataBean().getCurCode());
        marketType = AfbUtils.getMarketByType(this, settingAllDataBean.getAccMarketType());
        if (!StringUtils.isNull(settingAllDataBean.getAccDefaultSorting()))
            sort = Integer.valueOf(settingAllDataBean.getAccDefaultSorting());
        if (!StringUtils.isNull(settingAllDataBean.getAccScoreSound()))
            SoundPlayUtils.setSound(settingAllDataBean.getAccScoreSound());
    }

    public void setQuickAmount(String quickAmount) {
        this.quickAmount = quickAmount;
    }

    public void setRefreshDataBean(RefreshDataBean refreshDataBean) {
        this.refreshDataBean = refreshDataBean;
    }

    public void saveMixBet(OddsClickBean oddsUrlBean) {
        if (mixBetList == null)
            mixBetList = new ArrayList<>();
        int i = 0;
        for (OddsClickBean mixBetBean : mixBetList) {
            if (mixBetBean.getItem().getModuleTitle().trim().equalsIgnoreCase(oddsUrlBean.getItem().getModuleTitle().trim())
                    && mixBetBean.getItem().getHome().trim().equalsIgnoreCase(oddsUrlBean.getItem().getHome().trim())
                    && mixBetBean.getItem().getAway().equalsIgnoreCase(oddsUrlBean.getItem().getAway().trim())) {
                mixBetList.remove(i);
                if (oddsUrlBean.getBETID().equalsIgnoreCase(mixBetBean.getBETID())) {
                    return;
                }
                Log.d("xxx", "hasTeam");

            }
            i++;
        }
        mixBetList.add(oddsUrlBean);
    }

    public void saveCurrentBet(OddsClickBean oddsClickBean) {
        if (mixBetList != null && mixBetList.size() > 9)
            return;
        this.currentBet = oddsClickBean;
        saveMixBet(oddsClickBean);


    }

    public List<OddsClickBean> getMixBetList() {
        return mixBetList;
    }

    public void clearMixBetList() {
        mixBetList = new ArrayList<>();
    }
}


