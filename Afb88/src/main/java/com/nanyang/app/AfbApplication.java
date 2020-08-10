package com.nanyang.app;

import androidx.multidex.MultiDex;

import com.nanyang.app.Utils.SoundPlayUtils;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.nanyang.app.main.Setting.RefreshDataBean;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.tencent.bugly.crashreport.CrashReport;
import com.unkonw.testapp.libs.base.BaseApplication;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.finalteam.toolsfinal.logger.Logger;

/**
 * Created by Administrator on 2017/2/20.
 */

public class AfbApplication extends BaseApplication {


    private AfbClickResponseBean betAfbList;
    private boolean isGoHome = false;
    private SettingAllDataBean settingAllDataBean;

    private List<OddsClickBean> mixBetList = new ArrayList<>();

    public int getDelayBet() {
        return delayBet;
    }

    private int delayBet;

    public boolean isNoShowRts() {
        return noShowRts;
    }

    private boolean noShowRts;


    public RefreshDataBean getRefreshDataBean() {
        return refreshDataBean;
    }

    private RefreshDataBean refreshDataBean;

    public String getQuickAmount() {
        return quickAmount;
    }

    private String quickAmount = "";

    public MenuItemInfo getOddsType() {
        if (oddsType != null && getSettingAllDataBean() != null)
            return AfbUtils.getOddsTypeByType(this, oddsType.getType(), getSettingAllDataBean().getCurCode());
        return  new MenuItemInfo(0, (R.string.MY_ODDS), "MY");
    }

    public void setOddsType(MenuItemInfo oddsType) {
        this.oddsType = oddsType;
    }

    public MenuItemInfo<String> getMarketType() {
        if (marketType == null)
            marketType = new MenuItemInfo<>(0, (R.string.All_Markets), "0", getString(R.string.All_Market));
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
        CrashReport.initCrashReport(getApplicationContext(), "8b1c356aef", true, strategy);
        MultiDex.install(this);
/*        CrashReport.initCrashReport(getApplicationContext(),你后台的ID, true);//true表示异常立刻上报，建议打开

        CrashReport.setUserId(你自己的标识码);*/
    }


    public AfbClickResponseBean getBetParList() {
        return getBetAfbList();
    }

    public void setBetAfbList(AfbClickResponseBean betParList) {
        LogUtil.d("BetPop", "setBetAfbList:" + betParList);
        if (betParList != null && betParList.getList() != null && betParList.getList().size() > 0) {
            if (betParList.getList() == null || betParList.getList().size() < 1) {
                this.betAfbList = null;
            }
        }
        this.betAfbList = betParList;
        if (betParList == null)
            clearMixBetList();
    }


    public AfbClickResponseBean getBetAfbList() {
        return betAfbList;
    }


    public synchronized String getRefreshCurrentOddsUrl() {

        if (betAfbList == null || betAfbList.getList() == null || betAfbList.getList().size() == 0)
            return "";
        String ids = "";
        String betOddsUrl;
        if (betAfbList.getList().size() == 1) {
            String typeOdds = betAfbList.getList().get(0).getOddsType();
            String itemId = betAfbList.getList().get(0).getId();
            if (!StringUtils.isNull(typeOdds) && typeOdds.endsWith("_par")) {
                String replace = itemId.replaceFirst(typeOdds, typeOdds.substring(0, typeOdds.indexOf("_par")));
                itemId = replace;
            }
            betOddsUrl = "BTMD=S&coupon=0&BETID=" + itemId;
            return AppConstant.getInstance().URL_ODDS + betOddsUrl;
        } else {
            return getRefreshMixOddsUrl();
        }

    }

    public synchronized String getRefreshMixOddsUrl() {

        if (getMixBetList() == null || getMixBetList().size() == 0)
            return "";
        String ids = "";
        String betOddsUrl = "BTMD=S&coupon=0&BETID=";
        if (getMixBetList().size() == 1) {

            betOddsUrl = "BTMD=S&coupon=0&BETID=" + getMixBetList().get(0).getBETID().trim() + "&_par=";
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

    public synchronized String getRefreshSingleOddsUrl() {
        String betOddsUrl = "BTMD=S&coupon=0&BETID=";
        if (singleBet != null) {
            betOddsUrl = "BTMD=S&coupon=0&BETID=" + singleBet.getBETID();
            return AppConstant.getInstance().URL_ODDS + betOddsUrl;
        }
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


    public synchronized boolean saveCurrentBet(OddsClickBean oddsUrlBean) {

        if (mixBetList == null)
            mixBetList = new ArrayList<>();
        Iterator<OddsClickBean> iterator = mixBetList.iterator();
        while (iterator.hasNext()) {
            OddsClickBean mixBetBean = iterator.next();
            if (mixBetBean.getItem().getMainModuleId().trim().equalsIgnoreCase(oddsUrlBean.getItem().getMainModuleId().trim())
                    && mixBetBean.getItem().getMainHomeId().trim().equalsIgnoreCase(oddsUrlBean.getItem().getMainHomeId().trim())
                    && mixBetBean.getItem().getMainAwayId().equalsIgnoreCase(oddsUrlBean.getItem().getMainAwayId().trim())) {
                iterator.remove();
                if (oddsUrlBean.getBETID().equalsIgnoreCase(mixBetBean.getBETID())) {
                    return true;
                }

            }
        }
        if (mixBetList.size() > 14) {
            ToastUtils.showShort(R.string.can_not_more_than_10);
            return false;
        } else {
            mixBetList.add(oddsUrlBean);
            return true;
        }
    }

    public List<OddsClickBean> getMixBetList() {
        return mixBetList;
    }


    public void clearMixBetList() {

        mixBetList = new ArrayList<>();
        setSingleBet(null);
    }

    public synchronized void setNoShowRts(boolean noShowRts) {
        this.noShowRts = noShowRts;
    }

    public boolean isSingleBet = true;

    public OddsClickBean getSingleBet() {
        return singleBet;
    }

    public void setSingleBet(OddsClickBean singleBet) {
        this.singleBet = singleBet;
    }

    OddsClickBean singleBet;

    public void saveSingleBet(OddsClickBean oddsUrlBean) {
        if (singleBet != null && singleBet.getBETID().equals(oddsUrlBean.getBETID())) {
            if (getMixBetList().size() > 0) {
                setSingleBet(getMixBetList().get(getMixBetList().size() - 1));
            } else {
                setSingleBet(null);
            }
        } else {
            setSingleBet(oddsUrlBean);
        }


    }

    public boolean removeSameMix(OddsClickBean oddsUrlBean) {
        Iterator<OddsClickBean> iterator = mixBetList.iterator();
        while (iterator.hasNext()) {
            OddsClickBean mixBetBean = iterator.next();
            if (mixBetBean.getItem().getMainModuleId().trim().equalsIgnoreCase(oddsUrlBean.getItem().getMainModuleId().trim())
                    && mixBetBean.getItem().getMainHomeId().trim().equalsIgnoreCase(oddsUrlBean.getItem().getMainHomeId().trim())
                    && mixBetBean.getItem().getMainAwayId().equalsIgnoreCase(oddsUrlBean.getItem().getMainAwayId().trim())) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    Map<String, String> enableMap = new HashMap<>();

    public Map<String, String> updateOtherMap() {
        String isLDEnabled = getUser().getIsLDEnabled();
        enableMap.put("Casino", isLDEnabled);
        String isEnabledPG = getUser().getIsEnabledPG();
        enableMap.put("PG CASINO", isEnabledPG);
        String isEnabledPRG = getUser().getIsEnabledPRG();
        enableMap.put("PRAGMATIC CASINO", isEnabledPRG);
        String isEnabledPS = getUser().getIsEnabledPS();
        enableMap.put("PS GAMING", isEnabledPS);
        String isEnabledSA = getUser().getIsEnabledSA();
        enableMap.put("SEXY CASINO", isEnabledSA);
        String isEnabledSG = getUser().getIsEnabledSG();
        enableMap.put("SA CASINO", isEnabledSG);
        String isEnabledEV = getUser().getIsEnabledEV();
        enableMap.put("EVOPLAY", isEnabledEV);
        String isEnabledDG = getUser().getIsEnabledDG();
        enableMap.put("DG CASINO", isEnabledDG);
        String isEnabledWM = getUser().getIsEnabledWM();
        enableMap.put("WM CASINO", isEnabledWM);
        enableMap.put("NL CASINO", isEnabledWM);
        enableMap.put("LG CASINO", isEnabledWM);
        return enableMap;
    }
    public void setDelayBet(int delayBet) {
        this.delayBet = delayBet;
    }
}



