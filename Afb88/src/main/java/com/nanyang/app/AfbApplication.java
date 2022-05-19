package com.nanyang.app;

import android.graphics.Color;

import com.nanyang.app.Utils.SoundPlayUtils;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.nanyang.app.main.BaseMoreFragment;
import com.nanyang.app.main.DepositAndWithdraw.Bean.UserCashBean;
import com.nanyang.app.main.Setting.RefreshDataBean;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.nanyang.app.main.home.huayThai.HuayThaiFragment;
import com.nanyang.app.main.home.sport.USFootball.USFootballFragment;
import com.nanyang.app.main.home.sport.WaterSport.WaterSportFragment;
import com.nanyang.app.main.home.sport.allRunning.AllRunningFragment;
import com.nanyang.app.main.home.sport.athletics.AthleticsFragment;
import com.nanyang.app.main.home.sport.badminton.BadmintonFragment;
import com.nanyang.app.main.home.sport.baseball.BaseballFragment;
import com.nanyang.app.main.home.sport.basketball.BasketballFragment;
import com.nanyang.app.main.home.sport.beachSport.BeachSportFragment;
import com.nanyang.app.main.home.sport.boxing.BoxingFragment;
import com.nanyang.app.main.home.sport.cricket.CricketFragment;
import com.nanyang.app.main.home.sport.cycling.CyclingFragment;
import com.nanyang.app.main.home.sport.darts.DartsFragment;
import com.nanyang.app.main.home.sport.e_sport.ESportFragment;
import com.nanyang.app.main.home.sport.europe.EuropeFragment;
import com.nanyang.app.main.home.sport.financial.FinancialFragment;
import com.nanyang.app.main.home.sport.football.SoccerFragment;
import com.nanyang.app.main.home.sport.formula.FormulaFragment;
import com.nanyang.app.main.home.sport.futsal.FutsalFragment;
import com.nanyang.app.main.home.sport.game4d.Game4dFragment;
import com.nanyang.app.main.home.sport.golf.GolfFragment;
import com.nanyang.app.main.home.sport.handball.HandballFragment;
import com.nanyang.app.main.home.sport.iceHockey.IceHockeyFragment;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sport.motorSport.MotorFragment;
import com.nanyang.app.main.home.sport.muayThai.MuayThaiFragment;
import com.nanyang.app.main.home.sport.outRight.OutRightFragment;
import com.nanyang.app.main.home.sport.poll.PoolSnookerFragment;
import com.nanyang.app.main.home.sport.rugby.RugbyFragment;
import com.nanyang.app.main.home.sport.squash.SquashFragment;
import com.nanyang.app.main.home.sport.tableTennis.TableTennisFragment;
import com.nanyang.app.main.home.sport.tennis.TennisFragment;
import com.nanyang.app.main.home.sport.volleyball.VolleyballFragment;
import com.nanyang.app.main.home.sport.winterSport.WinterSportFragment;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.finalteam.toolsfinal.AppCacheUtils;
import cn.finalteam.toolsfinal.logger.Logger;

/**
 * Created by Administrator on 2017/2/20.
 */

public class AfbApplication extends BaseApplication {


    private AfbClickResponseBean betAfbList;
    private boolean isGoHome = false;
    private SettingAllDataBean settingAllDataBean;

    private List<OddsClickBean> mixBetList = new ArrayList<>();
    private List<OddsClickBean> oldSingleList = new ArrayList<>();
    private String authorization;

    public int getDelayBet() {
        return delayBet;
    }

    private int delayBet;

    public boolean isNoShowRts() {
        return noShowRts;
    }

    private boolean noShowRts;


    public RefreshDataBean getRefreshDataBean() {
        if (refreshDataBean == null) {
            Object refresh = AppCacheUtils.getInstance(this).getObject("RefreshData");
            if (refresh != null)
                this.refreshDataBean = (RefreshDataBean) refresh;
        }
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
        return new MenuItemInfo(0, (R.string.MY_ODDS), "MY");
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

    private UserCashBean userCashBean;

    public UserCashBean getUserCashBean() {
        return userCashBean;
    }

    public void setUserCashBean(UserCashBean userCashBean) {
        this.userCashBean = userCashBean;
    }

    public LinkedHashMap<String, SportIdBean> beanHashMap = new LinkedHashMap<>();
    public LinkedHashMap<String, SportIdBean> sportMap = new LinkedHashMap<>();
    public LinkedHashMap<String, SportIdBean> othersMap = new LinkedHashMap<>();
    public LinkedHashMap<String, SportIdBean> outRightMap = new LinkedHashMap<>();

    public void initAllSprotMap() {
        BaseSportFragment soccerFragment = new SoccerFragment();
        BaseSportFragment basketballFragment = new BasketballFragment();
        BaseSportFragment tennisFragment = new TennisFragment();
        BaseSportFragment financialFragment = new FinancialFragment();
        BaseSportFragment game4dFragment = new Game4dFragment();
        BaseSportFragment eSportFragment = new ESportFragment();
        BaseSportFragment muayThaiFragment = new MuayThaiFragment();
        BaseMoreFragment huayThaiFragment = new HuayThaiFragment();

        BaseSportFragment europeFragment = new EuropeFragment();
        BaseSportFragment usFootballFragment = new USFootballFragment();
        BaseSportFragment baseballFragment = new BaseballFragment();
        BaseSportFragment iceHockeyFragment = new IceHockeyFragment();
        BaseSportFragment poolSnookerFragment = new PoolSnookerFragment();
        BaseSportFragment rugbyFragment = new RugbyFragment();
        BaseSportFragment dartsFragment = new DartsFragment();
        BaseSportFragment boxingFragment = new BoxingFragment();
        BaseSportFragment motorFragment = new MotorFragment();
        BaseSportFragment golfFragment = new GolfFragment();
        BaseSportFragment futsalFragment = new FutsalFragment();
        BaseSportFragment badmintonFragment = new BadmintonFragment();
        BaseSportFragment volleyballFragment = new VolleyballFragment();
        BaseSportFragment cricketFragment = new CricketFragment();

        BaseSportFragment handballFragment = new HandballFragment();
        BaseSportFragment cyclingFragment = new CyclingFragment();

        BaseSportFragment beachSoccerFragment = new BeachSportFragment();
        BaseSportFragment athleticsFragment = new AthleticsFragment();
        BaseSportFragment squashFragment = new SquashFragment();

        BaseSportFragment winterSportFragment = new WinterSportFragment();
        BaseSportFragment waterSportFragment = new WaterSportFragment();
        //    BaseSportFragment superComboFragment = new SuperComboFragment();
        BaseSportFragment tableTennisFragment = new TableTennisFragment();
        BaseSportFragment formulaFragment = new FormulaFragment();
        BaseSportFragment outRightFragment = new OutRightFragment();
        BaseSportFragment allRunningFragment = new AllRunningFragment();
        beanHashMap = new LinkedHashMap<>();
        sportMap = new LinkedHashMap<>();
        othersMap = new LinkedHashMap<>();
        outRightMap = new LinkedHashMap<>();
        beanHashMap.put("1", new SportIdBean("1", "1", R.string.Soccer, "SportBook", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.football, R.mipmap.white_soccer_small));

        putOtherMap(beanHashMap, soccerFragment);

        beanHashMap.put("9", new SportIdBean("9", "2", R.string.Basketball, "Basketball", SportActivity.class, basketballFragment, Color.BLACK, R.mipmap.basketball, R.mipmap.white_basketball2));
        beanHashMap.put("21", new SportIdBean("21", "3", R.string.Tennis, "Tennis", SportActivity.class, tennisFragment, Color.BLACK, R.mipmap.tennis, R.mipmap.white_table_tennis));
        beanHashMap.put("29", new SportIdBean("29", "9", R.string.Baseball, "Baseball", SportActivity.class, baseballFragment, Color.BLACK, R.mipmap.baseball, R.mipmap.white_baseball));
        beanHashMap.put("51", new SportIdBean("51", "20", R.string.Badminton, "Badminton", SportActivity.class, badmintonFragment, Color.BLACK, R.mipmap.badminton, R.mipmap.white_badminton2));
        beanHashMap.put("106", new SportIdBean("106", "34", R.string.E_Sport, "E_Sport", SportActivity.class, eSportFragment, Color.BLACK, R.mipmap.e_sport, R.mipmap.white_e_sports2));
        beanHashMap.put("16", new SportIdBean("16", "14", R.string.Boxing, "Boxing", SportActivity.class, boxingFragment, Color.BLACK, R.mipmap.boxing, R.mipmap.white_soccer_small));
        beanHashMap.put("44", new SportIdBean("44", "23", R.string.Cricket, "Cricket", SportActivity.class, cricketFragment, Color.BLACK, R.mipmap.cricket, R.mipmap.white_soccer_small));
// '26', g = '65', img = 'https://www.afb1188.com/H50/Img/cycling.jpg'

        beanHashMap.put("65", new SportIdBean("65", "26", R.string.Cycling, "Cycling", SportActivity.class, cyclingFragment, Color.BLACK, R.mipmap.cycling, R.mipmap.white_soccer_small));

        beanHashMap.put("19", new SportIdBean("19", "13", R.string.Darts, "Darts", SportActivity.class, dartsFragment, Color.BLACK, R.mipmap.darts, R.mipmap.white_soccer_small));

        beanHashMap.put("25", new SportIdBean("25", "15", R.string.Formula1, "Formula1", SportActivity.class, formulaFragment, Color.BLACK, R.mipmap.motor_sports, R.mipmap.white_soccer_small));
        beanHashMap.put("28", new SportIdBean("28", "19", R.string.Futsal, "Futsal", SportActivity.class, futsalFragment, Color.BLACK, R.mipmap.football, R.mipmap.white_soccer_small));
        beanHashMap.put("182", new SportIdBean("182", "36", R.string.Europe_View, "Europe", SportActivity.class, europeFragment, Color.BLACK, R.mipmap.football, R.mipmap.white_soccer_small));
        beanHashMap.put("22", new SportIdBean("22", "17", R.string.Golf, "Golf", SportActivity.class, golfFragment, Color.BLACK, R.mipmap.ice_hockey, R.mipmap.white_soccer_small));
        beanHashMap.put("41", new SportIdBean("41", "25", R.string.Handball, "Handball", SportActivity.class, handballFragment, Color.BLACK, R.mipmap.football, R.mipmap.white_soccer_small));
        beanHashMap.put("14", new SportIdBean("14", "10", R.string.IceHockey, "IceHockey", SportActivity.class, iceHockeyFragment, Color.BLACK, R.mipmap.ice_hockey, R.mipmap.white_ice_hockey));
        beanHashMap.put("49", new SportIdBean("49", "16", R.string.Motor_Sports, "Motor_Sports", SportActivity.class, motorFragment, Color.BLACK, R.mipmap.motor_sports, R.mipmap.white_soccer_small));
        beanHashMap.put("7", new SportIdBean("7", "4", R.string.Financial, "Financial", SportActivity.class, financialFragment, Color.BLACK, R.mipmap.financial, R.mipmap.white_soccer_small));

        beanHashMap.put("17", new SportIdBean("17", "12", R.string.Rugby, "Rugby", SportActivity.class, rugbyFragment, Color.BLACK, R.mipmap.rugby, R.mipmap.white_soccer_small));
        beanHashMap.put("11", new SportIdBean("11", "11", R.string.Pool, "Snooker", SportActivity.class, poolSnookerFragment, Color.BLACK, R.mipmap.cricket, R.mipmap.white_soccer_small));
        beanHashMap.put("57", new SportIdBean("57", "22", R.string.Table_Tennis, "Table_Tennis", SportActivity.class, tableTennisFragment, Color.BLACK, R.mipmap.table_tennis, R.mipmap.white_table_tennis));
        beanHashMap.put("12", new SportIdBean("12", "8", R.string.US_Football, "US_Football", SportActivity.class, usFootballFragment, Color.BLACK, R.mipmap.volleyball, R.mipmap.white_soccer_small));
        beanHashMap.put("23", new SportIdBean("23", "24", R.string.Volleyball, "Volleyball", SportActivity.class, volleyballFragment, Color.BLACK, R.mipmap.volleyball, R.mipmap.white_soccer_small));
        beanHashMap.put("53", new SportIdBean("53", "21", R.string.Water_Polo, "Water_Polo", SportActivity.class, waterSportFragment, Color.BLACK, R.mipmap.water_polo, R.mipmap.white_soccer_small));
        beanHashMap.put("108", new SportIdBean("108", "35", R.string.Muay_Thai, "Muay_Thai", SportActivity.class, muayThaiFragment, Color.BLACK, R.mipmap.financial, R.mipmap.white_soccer_small));
        beanHashMap.put("1,9,21,29,51,182", new SportIdBean("1,9,21,29,51,182", "0", R.string.all_running, "AllRunning", SportActivity.class, allRunningFragment, Color.BLACK, R.mipmap.all_running, R.mipmap.white_soccer_small));
        beanHashMap.put("43,104,61,58,64,54,91,69,37,91,61,63,102", new SportIdBean("43,104,61,58,64,54,91,69,37,91,61,63,102", "999", R.string.OutRight, "OutRight", SportActivity.class, outRightFragment, Color.BLACK, R.mipmap.outright, R.mipmap.white_soccer_small));
        SportIdBean soccerRunning = new SportIdBean("1", "1", R.string.Soccer_Runing, "SportBook", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.football, R.mipmap.white_soccer_small);
        soccerRunning.setKey("-1");

        SportIdBean soccerEuro_2020= new SportIdBean("1", "1", R.string.Euro_2020, "SportBook", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.football,R.mipmap.white_soccer_small);
        soccerEuro_2020.setKey("122");

        sportMap.put("1,9,21,29,51,182", new SportIdBean("1,9,21,29,51,182", "0", R.string.all_running, "AllRunning", SportActivity.class, allRunningFragment, Color.BLACK, R.mipmap.all_running, R.mipmap.white_soccer_small));
        sportMap.put("-1", soccerRunning);
        sportMap.put("122", soccerEuro_2020);

        sportMap.put("182", new SportIdBean("182", "36", R.string.Europe_View, "Europe", SportActivity.class, europeFragment, Color.BLACK, R.mipmap.football, R.mipmap.white_soccer_small));
        sportMap.put("1", new SportIdBean("1", "1", R.string.Soccer, "SportBook", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.football, R.mipmap.white_soccer_small));
        sportMap.put("9", new SportIdBean("9", "2", R.string.Basketball, "Basketball", SportActivity.class, basketballFragment, Color.BLACK, R.mipmap.basketball, R.mipmap.white_basketball2));
        sportMap.put("21", new SportIdBean("21", "3", R.string.Tennis, "Tennis", SportActivity.class, tennisFragment, Color.BLACK, R.mipmap.tennis, R.mipmap.white_table_tennis));
        sportMap.put("7", new SportIdBean("7", "4", R.string.Financial, "Financial", SportActivity.class, financialFragment, Color.BLACK, R.mipmap.financial, R.mipmap.white_soccer_small));
        sportMap.put("106", new SportIdBean("106", "34", R.string.E_Sport, "E_Sport", SportActivity.class, eSportFragment, Color.BLACK, R.mipmap.e_sport, R.mipmap.white_e_sports2));
        sportMap.put("6", new SportIdBean("6", "5", R.string.Specials_4D, "Specials_4D", SportActivity.class, game4dFragment, Color.BLACK, R.mipmap.baseball, R.mipmap.white_baseball));
        sportMap.put("43,104,61,58,64,54,91,69,37,91,61,63,102", new SportIdBean("43,104,61,58,64,54,91,69,37,91,61,63,102", "999", R.string.OutRight, "OutRight", SportActivity.class, outRightFragment, Color.BLACK, R.mipmap.outright, R.mipmap.white_soccer_small));


        sportMap.put("12", new SportIdBean("12", "8", R.string.US_Football, "US_Football", SportActivity.class, usFootballFragment, Color.BLACK, R.mipmap.volleyball, R.mipmap.white_soccer_small));
        sportMap.put("29", new SportIdBean("29", "9", R.string.Baseball, "Baseball", SportActivity.class, baseballFragment, Color.BLACK, R.mipmap.baseball, R.mipmap.white_baseball));
        sportMap.put("14", new SportIdBean("14", "10", R.string.IceHockey, "IceHockey", SportActivity.class, iceHockeyFragment, Color.BLACK, R.mipmap.ice_hockey, R.mipmap.white_ice_hockey));
        sportMap.put("11", new SportIdBean("11", "11", R.string.Pool, "Snooker", SportActivity.class, poolSnookerFragment, Color.BLACK, R.mipmap.cricket, R.mipmap.white_soccer_small));
        sportMap.put("17", new SportIdBean("17", "12", R.string.Rugby, "Rugby", SportActivity.class, rugbyFragment, Color.BLACK, R.mipmap.rugby, R.mipmap.white_soccer_small));
        sportMap.put("19", new SportIdBean("19", "13", R.string.Darts, "Darts", SportActivity.class, dartsFragment, Color.BLACK, R.mipmap.darts, R.mipmap.white_soccer_small));
        sportMap.put("16", new SportIdBean("16", "14", R.string.Boxing, "Boxing", SportActivity.class, boxingFragment, Color.BLACK, R.mipmap.boxing, R.mipmap.white_soccer_small));
        sportMap.put("49", new SportIdBean("49", "16", R.string.Motor_Sports, "Motor_Sports", SportActivity.class, motorFragment, Color.BLACK, R.mipmap.motor_sports, R.mipmap.white_soccer_small));

        sportMap.put("25", new SportIdBean("25", "15", R.string.Formula1, "Formula1", SportActivity.class, formulaFragment, Color.BLACK, R.mipmap.motor_sports, R.mipmap.white_soccer_small));
        sportMap.put("22", new SportIdBean("22", "17", R.string.Golf, "Golf", SportActivity.class, golfFragment, Color.BLACK, R.mipmap.ice_hockey, R.mipmap.white_soccer_small));
        sportMap.put("28", new SportIdBean("28", "19", R.string.Futsal, "Futsal", SportActivity.class, futsalFragment, Color.BLACK, R.mipmap.football, R.mipmap.white_soccer_small));
        sportMap.put("51", new SportIdBean("51", "20", R.string.Badminton, "Badminton", SportActivity.class, badmintonFragment, Color.BLACK, R.mipmap.badminton, R.mipmap.white_badminton2));
        sportMap.put("53", new SportIdBean("53", "21", R.string.Water_Polo, "Water_Polo", SportActivity.class, waterSportFragment, Color.BLACK, R.mipmap.water_polo, R.mipmap.white_soccer_small));
        sportMap.put("57", new SportIdBean("57", "22", R.string.Table_Tennis, "Table_Tennis", SportActivity.class, tableTennisFragment, Color.BLACK, R.mipmap.table_tennis, R.mipmap.white_table_tennis));
        sportMap.put("44", new SportIdBean("44", "23", R.string.Cricket, "Cricket", SportActivity.class, cricketFragment, Color.BLACK, R.mipmap.cricket, R.mipmap.white_soccer_small));
        sportMap.put("23", new SportIdBean("23", "24", R.string.Volleyball, "Volleyball", SportActivity.class, volleyballFragment, Color.BLACK, R.mipmap.volleyball, R.mipmap.white_soccer_small));
        sportMap.put("41", new SportIdBean("41", "25", R.string.Handball, "Handball", SportActivity.class, handballFragment, Color.BLACK, R.mipmap.football, R.mipmap.white_soccer_small));
        sportMap.put("65", new SportIdBean("65", "26", R.string.Cycling, "Cycling", SportActivity.class, cyclingFragment, Color.BLACK, R.mipmap.cycling, R.mipmap.white_soccer_small));
        sportMap.put("67", new SportIdBean("67", "27", R.string.Beach_Soccer, "Beach_Soccer", SportActivity.class, beachSoccerFragment, Color.BLACK, R.mipmap.financial, R.mipmap.white_soccer_small));

        sportMap.put("101", new SportIdBean("101", "29", R.string.Athletics, "Athletics", SportActivity.class, athleticsFragment, Color.BLACK, R.mipmap.financial, R.mipmap.white_soccer_small));
        sportMap.put("103", new SportIdBean("103", "30", R.string.WinterSport, "WinterSport", SportActivity.class, winterSportFragment, Color.BLACK, R.mipmap.ice_sport, R.mipmap.white_soccer_small));
        sportMap.put("105", new SportIdBean("105", "31", R.string.Squash, "Squash", SportActivity.class, squashFragment, Color.BLACK, R.mipmap.financial, R.mipmap.white_soccer_small));
       /* if (!AppConstant.IS_AGENT) {
            sportMap.put("33_18", new SportIdBean("33_18", "33_18", R.string.Thai_game1, "Thai_1d", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.financial,R.mipmap.white_soccer_small));
            sportMap.put("33_19", new SportIdBean("33_19", "33_19", R.string.Thai_game2, "Thai_2d", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.financial,R.mipmap.white_soccer_small));
            sportMap.put("33_20", new SportIdBean("33_20", "33_20", R.string.Thai_game3, "Thai_3d", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.financial,R.mipmap.white_soccer_small));
        }*/
        sportMap.put("108", new SportIdBean("108", "108", R.string.Muay_Thai, "Muay_Thai", SportActivity.class, muayThaiFragment, Color.BLACK, R.mipmap.financial, R.mipmap.white_soccer_small));

        putOtherMap(othersMap, soccerFragment);

    }

    private static void putOtherMap(LinkedHashMap<String, SportIdBean> map, BaseSportFragment soccerFragment) {
        if (BuildConfig.FLAVOR.equals("ez2888")) {
            map.put("Casino", new SportIdBean("Casino", "", R.string.gd88_casino, "Casino", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_baccarat));
            map.put("SEXY CASINO", new SportIdBean("SEXY CASINO", "", R.string.SEXY_CASINO, "SEXY CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_sg_gaming));
            map.put("SA CASINO", new SportIdBean("SA CASINO", "", R.string.SA_CASINO, "SA CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_sa_gaming));
            map.put("DG CASINO", new SportIdBean("DG CASINO", "", R.string.DG_Cashio, "DG CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_dg));
            map.put("WM CASINO", new SportIdBean("WM CASINO", "", R.string.WM_Cashio, "WM CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_wm_gaming));
            map.put("PRAGMATIC CASINO", new SportIdBean("PRAGMATIC CASINO", "", R.string.PRGCashio, "PRAGMATIC CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_prg));
        } else {
            map.put("Casino", new SportIdBean("Casino", "", R.string.gd88_casino, "Casino", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_baccarat));
            map.put("AFB CASINO", new SportIdBean("AFB CASINO", "", R.string.AFB_CASINO, "AFB CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_afb_gaming));
            map.put("SEXY CASINO", new SportIdBean("SEXY CASINO", "", R.string.SEXY_CASINO, "SEXY CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_sg_gaming));
            map.put("SV388", new SportIdBean("SV388", "", R.string.SV388_CASINO, "SV388", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_sv388_gaming));
            map.put("SA CASINO", new SportIdBean("SA CASINO", "", R.string.SA_CASINO, "SA CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_sa_gaming));
            map.put("DG CASINO", new SportIdBean("DG CASINO", "", R.string.DG_Cashio, "DG CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_dg));
            map.put("WM CASINO", new SportIdBean("WM CASINO", "", R.string.WM_Cashio, "WM CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_wm_gaming));

            map.put("NL CASINO", new SportIdBean("NL CASINO", "", R.string.NG_Cashio, "NL CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_ng_casnio_game));
            map.put("LG CASINO", new SportIdBean("LG CASINO", "", R.string.LG_Cashio, "LG CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_lg_gaming));

            map.put("MK CASINO", new SportIdBean("MK CASINO", "", R.string.MK_CASINO, "MK CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.mkg_game));


            map.put("PG CASINO", new SportIdBean("PG CASINO", "", R.string.PGCashio, "PG CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_pg_symbol));
            map.put("PRAGMATIC CASINO", new SportIdBean("PRAGMATIC CASINO", "", R.string.PRGCashio, "PRAGMATIC CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_prg));
            map.put("PS GAMING", new SportIdBean("PS GAMING", "", R.string.PS_GAMING, "PS GAMING", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_ps_gaming));
            map.put("EVOPLAY", new SportIdBean("EVOPLAY", "", R.string.EV_Cashio, "EVOPLAY", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_evoplay));

            map.put("CQ9 GAME", new SportIdBean("CQ9 GAME", "", R.string.CQ9_GAME, "CQ9 GAME", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.cq9_game));
            map.put("TFG CASINO", new SportIdBean("TFG CASINO", "", R.string.TFG_CASINO, "TFG CASINO", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.tfg_game));
//        map.put("betsoft", new SportIdBean("betsoft", "", R.string.betsoft, "betsoft", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.bts_gaming));
            map.put("Joker", new SportIdBean("Joker", "", R.string.Joker, "Joker", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.joker_game));
            map.put("AFB GAMING", new SportIdBean("AFB GAMING", "", R.string.afb_gaming, "AFB GAMING", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.other_afb_slot_gaming));
        }
    }

    //("1", "9", "21", "29", "14", "5");
    public SportIdBean getSportFromOtherAndSportByG(String id) {
        return sportMap.get(id);
    }

    public SportIdBean getSportByG(String id) {
        return beanHashMap.get(id);
    }


    public SportIdBean getSportByType(String type) {
        Iterator<Map.Entry<String, SportIdBean>> iterator = beanHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            SportIdBean next = iterator.next().getValue();
            if (next.getType().equals(type))
                return next;
        }
        return null;
    }

    public SportIdBean getSportByDbid(String dbid) {
        Iterator<Map.Entry<String, SportIdBean>> iterator = beanHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            SportIdBean next = iterator.next().getValue();
            if (next.getDbid().equals(dbid))
                return next;
        }
        return null;
    }

    public String getOutRightGByDbid(SportIdBean sportIdBean) {
        String dbid = sportIdBean.getDbid();
        String outRightG = "";
        switch (dbid) {
            case "1":
                outRightG = "2";
                break;
            case "2":
                outRightG = "31";
                break;
            case "3":
                outRightG = "36";
                break;

            case "34":
                outRightG = "107";
                break;
            case "8":
                outRightG = "30";
            case "9":
                outRightG = "39";
                break;
            case "10":
                outRightG = "33";
                break;

            case "11":
                outRightG = "32";
                break;
            case "12":
                outRightG = "34";
            case "13":
                outRightG = "35";
                break;
            case "14":
                outRightG = "92";
                break;
            case "16":
                outRightG = "46";
                break;


            case "17":
                outRightG = "37";
                break;
            case "19":
                outRightG = "63";
                break;
            case "20":
                outRightG = "52";
                break;
            case "21":
                outRightG = "54";
                break;

            case "22":
                outRightG = "58";
                break;

            case "23":
                outRightG = "61";
                break;

            case "24":
                outRightG = "62";
                break;

            case "25":
                outRightG = "43";
                break;
            case "26":
                outRightG = "64";
                break;
            case "27":
                outRightG = "69";
                break;
            case "28":
                outRightG = "91";
                break;
            case "29":
                outRightG = "102";
                break;
            case "30":
                outRightG = "104";

        }
        return outRightG;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        SkinAppManager.getInstance().initSkinLoader(this);
//        KLog.init(BuildConfig.LOG_DEBUG, "AFB");
        initAllSprotMap();
        SoundPlayUtils.init(this);
        Logger.setDebug(true);
        closeAndroidPDialog();
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setAppChannel(BuildConfig.FLAVOR);  //设置渠道
        CrashReport.initCrashReport(getApplicationContext(), "8b1c356aef", true, strategy);

/*        CrashReport.initCrashReport(getApplicationContext(),你后台的ID, true);//true表示异常立刻上报，建议打开

        CrashReport.setUserId(你自己的标识码);*/
    }


    public AfbClickResponseBean getBetParList() {
        return getBetAfbList();
    }

    public synchronized void setBetAfbList(AfbClickResponseBean betParList) {
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
        AppCacheUtils.getInstance(this).put("RefreshData", refreshDataBean);
        this.refreshDataBean = refreshDataBean;
    }


    public synchronized boolean saveCurrentBet(OddsClickBean oddsUrlBean) {

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
        LogUtil.getMethodName("clearMixBetList");
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
        enableMap.put("MK CASINO", isEnabledWM);
        enableMap.put("CQ9 GAME", isEnabledWM);
        enableMap.put("TFG CASINO", isEnabledWM);
        enableMap.put("betsoft", isEnabledWM);
        enableMap.put("Joker", isEnabledWM);
        enableMap.put("allCasino", isEnabledWM);
        enableMap.put("AFB CASINO", isEnabledWM);
        enableMap.put("SV388", isEnabledWM);
        enableMap.put("AFB GAMING", isEnabledWM);
        return enableMap;
    }

    public void setDelayBet(int delayBet) {
        this.delayBet = delayBet;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }
}



