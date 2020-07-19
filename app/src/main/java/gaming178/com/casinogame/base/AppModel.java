package gaming178.com.casinogame.base;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Bean.Baccarat;
import gaming178.com.casinogame.Bean.BaccaratLimit;
import gaming178.com.casinogame.Bean.DragonTiger;
import gaming178.com.casinogame.Bean.DragonTigerLimit;
import gaming178.com.casinogame.Bean.Roulette;
import gaming178.com.casinogame.Bean.RouletteLimit;
import gaming178.com.casinogame.Bean.Sicbo;
import gaming178.com.casinogame.Bean.SicboLimit;
import gaming178.com.casinogame.Bean.User;
import gaming178.com.casinogame.Chat.FaceConversionUtil;
import gaming178.com.casinogame.Util.BackgroudMuzicService;
import gaming178.com.casinogame.Util.FrontMuzicService;
import gaming178.com.casinogame.Util.HttpClient;
import gaming178.com.casinogame.Util.TiagonalBlueView;
import gaming178.com.casinogame.Util.TiagonalRedView;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.allinone.util.ThreadPoolUtils;
import gaming178.com.mylibrary.allinone.util.WidgetUtil;
import gaming178.com.mylibrary.lib.util.LogUtil;

public class AppModel extends ViewModel {
    private HttpClient httpClient;
    private String cookie;
    private User user;
    private Baccarat baccarat01 = new Baccarat();
    private Baccarat baccarat02 = new Baccarat();
    private Baccarat baccarat03 = new Baccarat();
    private Baccarat baccarat61 = new Baccarat();
    private Baccarat baccarat62 = new Baccarat();
    private Baccarat baccarat63 = new Baccarat();
    private Baccarat baccarat64 = new Baccarat();
    private Baccarat baccarat65 = new Baccarat();
    private Baccarat baccarat66 = new Baccarat();
    private Baccarat baccarat81 = new Baccarat();
    private Baccarat baccarat82 = new Baccarat();
    private Baccarat baccarat83 = new Baccarat();
    private Baccarat baccarat84 = new Baccarat();
    private Baccarat baccarat71 = new Baccarat();
    private Roulette roulette01 = new Roulette();
    private Sicbo sicbo01 = new Sicbo();
    private DragonTiger dragonTiger01 = new DragonTiger();
    private String announcement;
    private ViewModelStore mAppViewModelStore;
    private ViewModelProvider.AndroidViewModelFactory mFactory;

    public void setHeadMargin(int headMargin) {

        this.headMargin = headMargin;
    }

    private int headMargin = 2;

    public void setHeadSpeace(int headSpeace) {
        this.headSpeace = headSpeace;
    }

    private int headSpeace = 20;

    public void setbInitLimit(boolean bInitLimit) {
        this.bInitLimit = bInitLimit;
    }

    private boolean bInitLimit;
    private boolean bLogin;
    private boolean bLobby;
    private int tableId;
    private int areaId;
    private int serialId;
    private int backgroudVolume = 50;
    private int frontVolume = 50;
    private String banker_road = "庄";
    private String player_road = "闲";
    private String tie_road = "和";
    private String dragon_road = "龍";
    private String tiger_road = "虎";
    private String evenOdd = "";
    private String waiDic = "T";
    private ComponentName componentFront;
    private int hallId = 1;//百家乐大厅标识，A,B,C

    //    private ComponentName componentBack = new ComponentName(this,
//            BackgroudMuzicService.class);
    private void initEmjoy(final Context mContext) {
        ThreadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
                FaceConversionUtil.getInstace().getFileText(mContext);
            }
        });

    }

    public Baccarat getBaccarat(int tableId) {
        switch (tableId) {
            case 1:
                return baccarat01;

            case 2:
                return baccarat02;

            case 3:
                return baccarat03;
            case 61:
                return baccarat61;
            case 62:
                return baccarat62;
            case 63:
                return baccarat63;
            case 64:
                return baccarat64;
            case 65:
                return baccarat65;
            case 66:
                return baccarat66;
            case 81:
                return baccarat81;
            case 82:
                return baccarat82;
            case 83:
                return baccarat83;
            case 84:
                return baccarat84;
            case 71:
                return baccarat71;

            default:
                return baccarat01;

        }
    }

    public DragonTiger getDragonTiger(int tableId) {
        switch (tableId) {
            case 5:
                return dragonTiger01;
            default:
                return dragonTiger01;

        }
    }

    public String hideName(String name) {
        String userName = "";
        if (name.length() > 4) {
            userName += name.substring(0, 2) + "****" + name.substring(name.length() - 2, name.length());
        } else {
            userName = name;
        }
        return userName;
    }

    public void getBaccaratLimitDetail(String strLimit, Baccarat baccarat, int index) {

        BaccaratLimit bLimit = null;
        switch (index) {
            case 1:
                bLimit = baccarat.getBaccaratLimit1();
                break;
            case 2:
                bLimit = baccarat.getBaccaratLimit2();
                break;
            case 3:
                bLimit = baccarat.getBaccaratLimit3();
                break;
            case 4:
                bLimit = baccarat.getBaccaratLimit4();
                break;
        }
        String limit[] = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
        if ("".equals(strLimit) || strLimit == null || "0".equals(strLimit)
                || "null".equals(strLimit)) {

        } else {
            limit = strLimit.split("\\#");
        }
        if (limit.length < 12)
            return;
        bLimit.setMaxTotalBet(Integer.parseInt(limit[0]));
        bLimit.setMinTotalBet(Integer.parseInt(limit[1]));
        bLimit.setMaxBankerPlayerBet(Integer.parseInt(limit[2]));
        bLimit.setMinBankerPlayerBet(Integer.parseInt(limit[3]));
        bLimit.setMaxTieBet(Integer.parseInt(limit[4]));
        bLimit.setMinTieBet(Integer.parseInt(limit[5]));
        bLimit.setMaxPairBet(Integer.parseInt(limit[6]));
        bLimit.setMinPairBet(Integer.parseInt(limit[7]));
        bLimit.setMaxBigBet(Integer.parseInt(limit[8]));
        bLimit.setMinBigBet(Integer.parseInt(limit[9]));
        bLimit.setMaxSmallBet(Integer.parseInt(limit[10]));
        bLimit.setMinSmallBet(Integer.parseInt(limit[11]));
        //	bLimit.setIndex(Integer.parseInt(limit[12]));
    }

    public void getDragonTigerLimitDetail(String strLimit, DragonTiger dragonTiger, int index) {
        if ("".equals(strLimit) || strLimit == null || "0".equals(strLimit)
                || "null".equals(strLimit)) {
            return;
        }
        DragonTigerLimit bLimit = null;
        switch (index) {
            case 1:
                bLimit = dragonTiger.getDragonTigerLimit1();
                break;
            case 2:
                bLimit = dragonTiger.getDragonTigerLimit2();
                break;
            case 3:
                bLimit = dragonTiger.getDragonTigerLimit3();
                break;
            case 4:
                bLimit = dragonTiger.getDragonTigerLimit4();
                break;
        }
        String limit[] = strLimit.split("\\#");
        if (limit.length < 6)
            return;
        bLimit.setMaxTotalBet(Integer.parseInt(limit[0]));
        bLimit.setMinTotalBet(Integer.parseInt(limit[1]));
        bLimit.setMaxDragonTigerBet(Integer.parseInt(limit[2]));
        bLimit.setMinDragonTigerBet(Integer.parseInt(limit[3]));
        bLimit.setMaxTieBet(Integer.parseInt(limit[4]));
        bLimit.setMinTieBet(Integer.parseInt(limit[5]));
        //	bLimit.setIndex(Integer.parseInt(limit[6]));

    }

    private void getSicBoLmitDetail(String strLimit, Sicbo sicbo, int index) {
        if ("".equals(strLimit) || strLimit == null || "0".equals(strLimit)
                || "null".equals(strLimit)) {
            return;
        }
        SicboLimit bLimit = null;
        switch (index) {
            case 1:
                bLimit = sicbo.getSicboLimit1();
                break;
            case 2:
                bLimit = sicbo.getSicboLimit2();
                break;
            case 3:
                bLimit = sicbo.getSicboLimit3();
                break;
            case 4:
                bLimit = sicbo.getSicboLimit4();
                break;
        }
        String limit[] = strLimit.split("\\#");
        if (limit.length < 24)
            return;
        bLimit.setMaxTotalBet(Integer.parseInt(limit[0]));
        bLimit.setMinTotalBet(Integer.parseInt(limit[1]));
        bLimit.setMaxBigSmallBet(Integer.parseInt(limit[2]));
        bLimit.setMinBigSmallBet(Integer.parseInt(limit[3]));
        bLimit.setMaxThreeforcesBet(Integer.parseInt(limit[4]));
        bLimit.setMinThreeforcesBet(Integer.parseInt(limit[5]));
        bLimit.setMaxNinewaycardBet(Integer.parseInt(limit[6]));
        bLimit.setMinNinewaycardBet(Integer.parseInt(limit[7]));
        bLimit.setMaxPairBet(Integer.parseInt(limit[8]));
        bLimit.setMinPairBet(Integer.parseInt(limit[9]));
        bLimit.setMaxWaidiceBet(Integer.parseInt(limit[12]));
        bLimit.setMinWaidiceBet(Integer.parseInt(limit[13]));
        bLimit.setMaxAlldiceBet(Integer.parseInt(limit[10]));
        bLimit.setMinAlldiceBet(Integer.parseInt(limit[11]));
        bLimit.setMaxCombination1Bet(Integer.parseInt(limit[14]));// 4-17
        bLimit.setMinCombination1Bet(Integer.parseInt(limit[15]));
        bLimit.setMaxCombination2Bet(Integer.parseInt(limit[16]));// 5-16
        bLimit.setMinCombination2Bet(Integer.parseInt(limit[17]));
        bLimit.setMaxCombination3Bet(Integer.parseInt(limit[18]));// 6-15
        bLimit.setMinCombination3Bet(Integer.parseInt(limit[19]));
        bLimit.setMaxCombination4Bet(Integer.parseInt(limit[20]));// 7-14
        bLimit.setMinCombination4Bet(Integer.parseInt(limit[21]));
        bLimit.setMaxCombination5Bet(Integer.parseInt(limit[22]));// 9,10,11,12
        bLimit.setMinCombination5Bet(Integer.parseInt(limit[23]));
        //	bLimit.setIndex(Integer.parseInt(limit[24]));


    }

    private void getRouletteLimitDetail(String strLimit, Roulette roulette, int index) {
        if ("".equals(strLimit) || strLimit == null || "0".equals(strLimit)
                || "null".equals(strLimit)) {
            return;
        }
        RouletteLimit bLimit = null;
        switch (index) {
            case 1:
                bLimit = roulette.getRouletteLimit1();
                break;
            case 2:
                bLimit = roulette.getRouletteLimit2();
                break;
            case 3:
                bLimit = roulette.getRouletteLimit3();
                break;
            case 4:
                bLimit = roulette.getRouletteLimit4();
                break;
        }
        String limit[] = strLimit.split("\\#");
        if (limit.length < 16)
            return;
        bLimit.setMaxTotalBet(Integer.parseInt(limit[0]));
        bLimit.setMinTotalBet(Integer.parseInt(limit[1]));
        bLimit.setMaxNumberBet(Integer.parseInt(limit[2]));
        bLimit.setMinNumberBet(Integer.parseInt(limit[3]));
        bLimit.setMaxSplitBet(Integer.parseInt(limit[4]));
        bLimit.setMinSplitBet(Integer.parseInt(limit[5]));
        bLimit.setMaxStreetBet(Integer.parseInt(limit[6]));
        bLimit.setMinStreetBet(Integer.parseInt(limit[7]));
        bLimit.setMaxCornerBet(Integer.parseInt(limit[8]));
        bLimit.setMinCornerBet(Integer.parseInt(limit[9]));
        bLimit.setMaxLineBet(Integer.parseInt(limit[10]));
        bLimit.setMinLineBet(Integer.parseInt(limit[11]));
        bLimit.setMaxColumnDozenBet(Integer.parseInt(limit[12]));
        bLimit.setMinColumnDozenBet(Integer.parseInt(limit[13]));
        bLimit.setMaxEvenOddBet(Integer.parseInt(limit[14]));
        bLimit.setMinEvenOddBet(Integer.parseInt(limit[15]));
        //	bLimit.setIndex(Integer.parseInt(limit[16]));
    }

    public void splitTableInfo(String strRes, int hallId) {
        String tableInfo[] = strRes.split("\\^");
        if (tableInfo.length < 8)
            return;
        String gameInfo[] = tableInfo[0].split("\\#");
        if (gameInfo.length < 8)
            return;
        if (!gameInfo[1].equals(""))
            this.user.setName(gameInfo[1]);
        this.user.setBalance(!gameInfo[2].equals("") ? Double.parseDouble(gameInfo[2]) : 0);
        if (WebSiteUrl.isDomain && WebSiteUrl.GameType == 1) {
            this.user.setExchangeRate(!gameInfo[7].equals("") && !gameInfo[7].equals("null") ? Double.parseDouble(gameInfo[7]) : 0);
        } else
            this.user.setExchangeRate(!gameInfo[6].equals("") && !gameInfo[6].equals("null") ? Double.parseDouble(gameInfo[6]) : 0);

        this.user.setVideoUrl("rtmp://" + gameInfo[3]);
        this.user.setVideoUrlDefault("rtmp://" + gameInfo[4]);
        this.user.setVideoPoker("rtmp://" + gameInfo[5]);

        if (!bInitLimit) {
            String tableLimit[] = tableInfo[1].split("\\|");
            if (tableLimit.length > 3) {
                bInitLimit = true;
                getBaccaratLimitDetail(tableLimit[0], baccarat01, 1);
                getBaccaratLimitDetail(tableLimit[1], baccarat01, 2);
                getBaccaratLimitDetail(tableLimit[2], baccarat01, 3);
                getBaccaratLimitDetail(tableLimit[3], baccarat01, 4);
                getBaccaratLimitDetail(tableLimit[0], baccarat02, 1);
                getBaccaratLimitDetail(tableLimit[1], baccarat02, 2);
                getBaccaratLimitDetail(tableLimit[2], baccarat02, 3);
                getBaccaratLimitDetail(tableLimit[3], baccarat02, 4);
                getBaccaratLimitDetail(tableLimit[0], baccarat03, 1);
                getBaccaratLimitDetail(tableLimit[1], baccarat03, 2);
                getBaccaratLimitDetail(tableLimit[2], baccarat03, 3);
                getBaccaratLimitDetail(tableLimit[3], baccarat03, 4);
                getBaccaratLimitDetail(tableLimit[0], baccarat71, 1);
                getBaccaratLimitDetail(tableLimit[1], baccarat71, 2);
                getBaccaratLimitDetail(tableLimit[2], baccarat71, 3);
                getBaccaratLimitDetail(tableLimit[3], baccarat71, 4);
                getBaccaratLimitDetail(tableLimit[0], baccarat61, 1);
                getBaccaratLimitDetail(tableLimit[1], baccarat61, 2);
                getBaccaratLimitDetail(tableLimit[2], baccarat61, 3);
                getBaccaratLimitDetail(tableLimit[3], baccarat61, 4);
                getBaccaratLimitDetail(tableLimit[0], baccarat62, 1);
                getBaccaratLimitDetail(tableLimit[1], baccarat62, 2);
                getBaccaratLimitDetail(tableLimit[2], baccarat62, 3);
                getBaccaratLimitDetail(tableLimit[3], baccarat62, 4);
                getBaccaratLimitDetail(tableLimit[0], baccarat63, 1);
                getBaccaratLimitDetail(tableLimit[1], baccarat63, 2);
                getBaccaratLimitDetail(tableLimit[2], baccarat63, 3);
                getBaccaratLimitDetail(tableLimit[3], baccarat63, 4);

                getBaccaratLimitDetail(tableLimit[0], baccarat64, 1);
                getBaccaratLimitDetail(tableLimit[1], baccarat64, 2);
                getBaccaratLimitDetail(tableLimit[2], baccarat64, 3);
                getBaccaratLimitDetail(tableLimit[3], baccarat64, 4);
                getBaccaratLimitDetail(tableLimit[0], baccarat65, 1);
                getBaccaratLimitDetail(tableLimit[1], baccarat65, 2);
                getBaccaratLimitDetail(tableLimit[2], baccarat65, 3);
                getBaccaratLimitDetail(tableLimit[3], baccarat65, 4);
                getBaccaratLimitDetail(tableLimit[0], baccarat66, 1);
                getBaccaratLimitDetail(tableLimit[1], baccarat66, 2);
                getBaccaratLimitDetail(tableLimit[2], baccarat66, 3);
                getBaccaratLimitDetail(tableLimit[3], baccarat66, 4);

                // 龙虎
                getDragonTigerLimitDetail(tableLimit[4], dragonTiger01, 1);
                getDragonTigerLimitDetail(tableLimit[5], dragonTiger01, 2);
                getDragonTigerLimitDetail(tableLimit[6], dragonTiger01, 3);
                getDragonTigerLimitDetail(tableLimit[7], dragonTiger01, 4);
                // 轮盘限红
                getRouletteLimitDetail(tableLimit[8], roulette01, 1);
                getRouletteLimitDetail(tableLimit[9], roulette01, 2);
                getRouletteLimitDetail(tableLimit[10], roulette01, 3);
                getRouletteLimitDetail(tableLimit[11], roulette01, 4);
                // 晒宝限红
                getSicBoLmitDetail(tableLimit[12], sicbo01, 1);
                getSicBoLmitDetail(tableLimit[13], sicbo01, 2);
                getSicBoLmitDetail(tableLimit[14], sicbo01, 3);
                getSicBoLmitDetail(tableLimit[15], sicbo01, 4);

            }

        }
        switch (hallId) {
            case 1:
                getTableDetail(tableInfo[2], baccarat01);
                getTableDetail(tableInfo[3], baccarat02);
                getTableDetail(tableInfo[4], baccarat03);
                getTableDetail(tableInfo[5], dragonTiger01);
                getTableDetail(tableInfo[6], baccarat61);
                getTableDetail(tableInfo[7], baccarat62);
                getTableDetail(tableInfo[8], baccarat63);
                getTableDetail(tableInfo[9], baccarat71);
                getTableDetail(tableInfo[10], roulette01);
                getTableDetail(tableInfo[11], sicbo01);
                break;
            case 2:
                String[] tableTempInfo = new String[15];
                if (WebSiteUrl.isDomain && WebSiteUrl.GameType == 1 && tableInfo.length > 15) {
                    tableTempInfo[0] = tableInfo[0];
                    for (int i = 1; i < 15; i++) {
                        tableTempInfo[i] = tableInfo[i + 1];
                    }
                    tableInfo = tableTempInfo;

                }
                getTableDetail(tableInfo[1], baccarat61);
                getTableDetail(tableInfo[2], baccarat62);
                getTableDetail(tableInfo[3], baccarat63);
                getTableDetail(tableInfo[4], baccarat64);
                getTableDetail(tableInfo[5], baccarat65);
                getTableDetail(tableInfo[6], baccarat66);
//                getTableDetail(tableInfo[7], baccarat71);
                //拆分照片信息
                getDealerDetail(tableInfo[8], baccarat61);
                getDealerDetail(tableInfo[9], baccarat62);
                getDealerDetail(tableInfo[10], baccarat63);
                getDealerDetail(tableInfo[11], baccarat64);
                getDealerDetail(tableInfo[12], baccarat65);
                getDealerDetail(tableInfo[13], baccarat66);
//                getDealerDetail(tableInfo[14], baccarat71);
                break;
        }

    }

    public void getDealerDetail(String strDetail, Baccarat baccarat) {
        String tableInfoDetail1[] = strDetail.split("\\#");
        if (tableInfoDetail1.length < 3)
            return;
        baccarat.setDealerName(tableInfoDetail1[1]);
        baccarat.setDealerPicture(tableInfoDetail1[2]);

    }

    public void getTableDetail(String strDetail, Sicbo sicbo) {
        String tableInfoDetail1[] = strDetail.split("\\#");
        if (tableInfoDetail1.length < 5)
            return;
        sicbo.setTableName(tableInfoDetail1[0]);
        sicbo.setStatus(Integer.parseInt(tableInfoDetail1[1]));
        sicbo.setGameNumber(tableInfoDetail1[3]);
        sicbo.setTableLimit(Integer.parseInt(tableInfoDetail1[4]));
        sicbo.setVideoUrlIndex(tableInfoDetail1[5]);


        String tableInfoDetail2[] = strDetail.split("\\|");
        if (tableInfoDetail2.length > 1)
            sicbo.setRoad(tableInfoDetail2[1]);

    }

    public void getTableDetail(String strDetail, Roulette roulette) {
        String tableInfoDetail1[] = strDetail.split("\\#");
        if (tableInfoDetail1.length < 5)
            return;
        roulette.setTableName(tableInfoDetail1[0]);
        roulette.setStatus(Integer.parseInt(tableInfoDetail1[1]));
        roulette.setGameNumber(tableInfoDetail1[3]);
        roulette.setTableLimit(Integer.parseInt(tableInfoDetail1[4]));
        roulette.setVideoUrlIndex(tableInfoDetail1[5]);


        String tableInfoDetail2[] = strDetail.split("\\|");
        if (tableInfoDetail2.length > 1)
            roulette.setRoad(tableInfoDetail2[1]);

    }

    public void getTableDetail(String strDetail, DragonTiger dragonTiger) {
        String tableInfoDetail1[] = strDetail.split("\\#");
        if (tableInfoDetail1.length < 5)
            return;
        dragonTiger.setTableName(tableInfoDetail1[0]);
        dragonTiger.setStatus(Integer.parseInt(tableInfoDetail1[1]));
        dragonTiger.setShoeNumber(tableInfoDetail1[2]);
        dragonTiger.setGameNumber(tableInfoDetail1[3]);
        dragonTiger.setTableLimit(Integer.parseInt(tableInfoDetail1[4]));
        dragonTiger.setVideoUrlIndex(tableInfoDetail1[5]);


        String tableInfoDetail2[] = strDetail.split("\\|");
        if (tableInfoDetail2.length > 1)
            dragonTiger.setBigRoad(tableInfoDetail2[1]);

    }

    public void getTableDetail(String strDetail, Baccarat baccarat) {
        String tableInfoDetail1[] = strDetail.split("\\#");
        if (tableInfoDetail1.length < 5)
            return;

        if (tableInfoDetail1[2].equals("80")) {
            LogUtil.d("tv_baccarat_shoe_number", "getTableDetail,setShoeNumber:" + tableInfoDetail1[2]);
        }
        baccarat.setTableName(tableInfoDetail1[0]);
        baccarat.setStatus(Integer.parseInt(tableInfoDetail1[1]));
        baccarat.setShoeNumber(tableInfoDetail1[2]);
        baccarat.setGameNumber(tableInfoDetail1[3]);
        baccarat.setTableLimit(Integer.parseInt(tableInfoDetail1[4]));
        baccarat.setVideoUrlIndex(tableInfoDetail1[5]);
        //	tableInfo.setPhone1(tableInfoDetail1[5]);
        //	tableInfo.setPhone2(tableInfoDetail1[6]);

        String tableInfoDetail2[] = strDetail.split("\\|");
        if (tableInfoDetail2.length > 1)
            baccarat.setBigRoad(tableInfoDetail2[1]);

    }

    public void splitTimer(String strRes) {
        String timerInfo[] = strRes.split("\\^");
        if (timerInfo.length < 7)
            return;

        switch (hallId) {
            case 1:
                splitTimerDetail(timerInfo[1], baccarat01);
                splitTimerDetail(timerInfo[2], baccarat02);
                splitTimerDetail(timerInfo[3], baccarat03);

                splitTimerDetail(timerInfo[4], dragonTiger01);
                splitTimerDetail(timerInfo[5], roulette01);
                splitTimerDetail(timerInfo[6], sicbo01);
                if (timerInfo.length >= 8) {
                    splitTimerDetail(timerInfo[7], baccarat71);
                    splitTimerDetail(timerInfo[8], baccarat61);
                    splitTimerDetail(timerInfo[9], baccarat62);
                    splitTimerDetail(timerInfo[10], baccarat63);
                }
                break;
            case 2:
                if (timerInfo.length < 8)
                    return;
                splitTimerDetail(timerInfo[1], baccarat61);
                splitTimerDetail(timerInfo[2], baccarat62);
                splitTimerDetail(timerInfo[3], baccarat63);
                splitTimerDetail(timerInfo[4], baccarat64);
                splitTimerDetail(timerInfo[5], baccarat65);
                splitTimerDetail(timerInfo[6], baccarat66);
//                splitTimerDetail(timerInfo[7], baccarat71);
                break;
            case 3:
                splitTimerDetail(timerInfo[1], baccarat81);
                splitTimerDetail(timerInfo[2], baccarat82);
                splitTimerDetail(timerInfo[3], baccarat83);
                splitTimerDetail(timerInfo[4], baccarat84);
                break;
        }


    }

    public void splitTimerDetail(String strRes, Baccarat baccarat) {
        String timerInfoDetail[] = strRes.split("\\#");
        if (timerInfoDetail.length < 3) {
            return;
        }
        baccarat.setTableName(timerInfoDetail[0]);
        LogUtil.d("tv_baccarat_shoe_number", "splitTimerDetail:" + timerInfoDetail[0]);
        baccarat.setGameStatus(Integer.parseInt(timerInfoDetail[1]));
        baccarat.setTimer(Integer.parseInt(timerInfoDetail[2]));
    }

    public void splitTimerDetail(String strRes, Roulette roulette) {
        String timerInfoDetail[] = strRes.split("\\#");
        if (timerInfoDetail.length < 3) {
            return;
        }
        roulette.setTableName(timerInfoDetail[0]);
        roulette.setGameStatus(Integer.parseInt(timerInfoDetail[1]));
        roulette.setTimer(Integer.parseInt(timerInfoDetail[2]));

    }

    public void splitTimerDetail(String strRes, Sicbo sicbo) {
        String timerInfoDetail[] = strRes.split("\\#");
        if (timerInfoDetail.length < 3) {
            return;
        }
        sicbo.setTableName(timerInfoDetail[0]);
        sicbo.setGameStatus(Integer.parseInt(timerInfoDetail[1]));
        sicbo.setTimer(Integer.parseInt(timerInfoDetail[2]));

    }

    public void splitTimerDetail(String strRes, DragonTiger dragonTiger) {
        String timerInfoDetail[] = strRes.split("\\#");
        if (timerInfoDetail.length < 3) {
            return;
        }
        dragonTiger.setTableName(timerInfoDetail[0]);
        dragonTiger.setGameStatus(Integer.parseInt(timerInfoDetail[1]));
        dragonTiger.setTimer(Integer.parseInt(timerInfoDetail[2]));

    }

    /////////////////////////////
    public void clearChildView(ViewGroup parent) {
        if (parent != null) {
            parent.removeAllViewsInLayout();
        }

    }

    public void ShowHeadRoad(String luzi, GridLayout gridLayout, Context ctx, float density, String banker, String player, String tie) {
        try {


            if (luzi == null || luzi.length() <= 0 || "null".equals(luzi) || "null#".equals(luzi)) {
                return;
            }
            String luziInfo[] = luzi.split("\\#");
            if (luziInfo.length <= 0 && luziInfo.length > 80) {//数据格式不对
                return;
            }
            int index = luziInfo.length;
            int row = 0;
            int col = 0;
            int iStart = 0;
            if (index > 90)//显示不下了，返回，一般不会超过66局
            {
                return;
            }

            clearChildView(gridLayout);

            for (int i = 0; i < index; i++) {
                String luziInfoDetail[] = luziInfo[i].split("\\-");//路子相信信息9-8-7
                if (luziInfoDetail[0] == null || luziInfoDetail[0].length() == 0)
                    break;
           /* if(luziInfoDetail.length == 3 && isbLobby() == false && tableId != 5){
                banker = player = tie = luziInfoDetail[2];
            }*/
                int res = Integer.parseInt(luziInfoDetail[0]);
                row = i % 6;
                col = i / 6;
                //	Log.i(WebSiteUrl.Tag,"row = "+row+",col = "+col+",i = "+ i+",res = "+res);
                int rowCount = gridLayout.getRowCount();
                //		ViewGroup  viewGroup = (ViewGroup) gridLayout.getChildAt(col * rowCount + row);
                ViewGroup viewGroup = addChildView(gridLayout, row, col, ctx, density, headSpeace);
                if (viewGroup == null) {
                    Log.i(WebSiteUrl.Tag, "ShowHeadRoad = error,rowCount = " + rowCount + ",row=" + row + ",col=" + col);
                    continue;
                }
                switch (res) {
                    case 1:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_red_solid, 10, banker);
                        break;
                    case 2:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_red_solid, 10, banker);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_red_solid, Gravity.LEFT);
                        break;
                    case 3:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_red_solid, 10, banker);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_blue_solid, Gravity.RIGHT);
                        break;
                    case 4:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_red_solid, 10, banker);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_red_solid, Gravity.LEFT);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_blue_solid, Gravity.RIGHT);
                        break;
                    case 5:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_blue_solid, 10, player);
                        break;
                    case 6:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_blue_solid, 10, player);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_red_solid, Gravity.LEFT);
                        break;
                    case 7:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_blue_solid, 10, player);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_blue_solid, Gravity.RIGHT);
                        break;
                    case 8:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_blue_solid, 10, player);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_red_solid, Gravity.LEFT);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_blue_solid, Gravity.RIGHT);
                        break;
                    case 9:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_green_solid, 10, tie);
                        break;
                    case 10:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_green_solid, 10, tie);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_red_solid, Gravity.LEFT);
                        break;
                    case 11:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_green_solid, 10, tie);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_blue_solid, Gravity.RIGHT);
                        break;
                    case 12:
                        setTextMarginsBackground(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_green_solid, 10, tie);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_red_solid, Gravity.LEFT);
                        setMarginsPointMarker(ctx, viewGroup, density, headMargin, R.drawable.gd_oval_blue_solid, Gravity.RIGHT);
                        break;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FrameLayout addChildView(GridLayout parent, int row, int column, Context ctx, float density, int space) {

        GridLayout.Spec rowSpec = GridLayout.spec(row);     //设置它的行和列
        GridLayout.Spec columnSpec = GridLayout.spec(column);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
        params.setGravity(Gravity.CENTER);
        params.width = (int) (space * density);
        params.height = (int) (space * density);
        FrameLayout fl = new FrameLayout(ctx);
        try {
            parent.addView(fl, params);
        } catch (java.lang.IllegalArgumentException e) {
            Log.e("test", "row=" + row + "----->column=" + column);
        }
        return fl;
    }

    public void showBigRoad(Context ctx, GridLayout gridLayout, float density, int res, int row, int col, int textSiae, int margins) {
        if (gridLayout == null)
            return;
        int rowCount = gridLayout.getRowCount();
        //	ViewGroup  viewGroup = (ViewGroup) gridLayout.getChildAt(col * rowCount + row);
        ViewGroup viewGroup = addChildView(gridLayout, row, col, ctx, density, 14);
        if (viewGroup == null) {
            Log.i(WebSiteUrl.Tag, "showBigRoad = error,rowCount = " + rowCount + ",row=" + row + ",col=" + col);
            return;
        }

        switch (res) {
            case 1:
                setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_solid_not, textSiae, "");
                break;
            case 2:
                setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_solid_not, textSiae, "");
                setMarginsPointMarker(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_solid, Gravity.LEFT);
                break;
            case 3:
                setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_solid_not, textSiae, "");
                setMarginsPointMarker(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_solid, Gravity.RIGHT);
                break;
            case 4:
                setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_solid_not, textSiae, "");
                setMarginsPointMarker(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_solid, Gravity.LEFT);
                setMarginsPointMarker(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_solid, Gravity.RIGHT);
                break;
            case 5:
                setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_solid_not, textSiae, "");
                break;
            case 6:
                setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_solid_not, textSiae, "");
                setMarginsPointMarker(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_solid, Gravity.LEFT);
                break;
            case 7:
                setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_solid_not, textSiae, "");
                setMarginsPointMarker(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_solid, Gravity.RIGHT);
                break;
            case 8:
                setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_solid_not, textSiae, "");
                setMarginsPointMarker(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_solid, Gravity.LEFT);
                setMarginsPointMarker(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_solid, Gravity.RIGHT);
                break;

        }
    }

    public ViewGroup findGridIndexView(GridLayout parent, int row, int column) {
        int rowCount = parent.getRowCount();
        int iCount = parent.getChildCount();
        if (iCount <= 0)
            return null;
        //	View imageView = parent.getChildAt(iCount-1);

        //	return (ViewGroup) parent.getChildAt(column * rowCount+row );
        return (ViewGroup) parent.getChildAt(iCount - 1);
    }

    public void showTieNumber(Context ctx, GridLayout gridLayout, float density, int number, int row, int col) {


        ViewGroup viewGroup = (ViewGroup) findGridIndexView(gridLayout, row, col);
        if (viewGroup == null) {
            Log.i(WebSiteUrl.Tag, "showTieNumber ,res = " + ",row=" + row + ",col=" + col);
        } else {
            int iCount = viewGroup.getChildCount();
            //	Log.i(WebSiteUrl.Tag,"showTieNumber ,res = " +",row="+row+",col="+col+",iCount"+iCount);
            if (iCount <= 0)
                return;
//			int iTie = 0;
//			switch(iCount)
//			{
//				case 1:
//					iTie =
//					break;
//				case 2:
//					break;
//			}
            TextView textView = (TextView) viewGroup.getChildAt(0);
            textView.setTextSize(7);
            textView.setTextColor(ctx.getResources().getColor(R.color.grey_dark_word));
            textView.setText("" + number);
        }

    }

    public void showBigEyesRoad(Context ctx, GridLayout gridLayout, float density, int res, int row, int col, int textSiae, int margins) {
        int rowCount = gridLayout.getRowCount();
        //	ViewGroup  viewGroup = (ViewGroup) gridLayout.getChildAt(col * rowCount + row);
        ViewGroup viewGroup = addChildView(gridLayout, row, col, ctx, density, 6);
        if (viewGroup == null) {
            Log.i(WebSiteUrl.Tag, "showBigEyesRoad = error,rowCount = " + rowCount + ",row=" + row + ",col=" + col);
            return;
        }
        switch (res) {
            case 1:
                setTextMarginsBackground(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_solid_not, textSiae, "");
                break;
            case 5:
                setTextMarginsBackground(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_solid_not, textSiae, "");
                break;


        }

    }

    public void showSmallEyesRoad(Context ctx, GridLayout gridLayout, float density, int res, int row, int col, int textSiae, int margins) {
        int rowCount = gridLayout.getRowCount();
        ViewGroup viewGroup = addChildView(gridLayout, row, col, ctx, density, 6);
        if (viewGroup == null) {
            Log.i(WebSiteUrl.Tag, "showSmallEyesRoad = error,rowCount = " + rowCount + ",row=" + row + ",col=" + col);
            return;
        }
        switch (res) {
            case 1:
                setTextMarginsBackground(ctx, viewGroup, density, margins, 0, textSiae, "/", 1);
                break;
            case 5:
                setTextMarginsBackground(ctx, viewGroup, density, margins, 0, textSiae, "/", 2);
                break;


        }
    }

    public void showRoachRoad(Context ctx, GridLayout gridLayout, float density, int res, int row, int col, int textSiae, int margins) {
        try {
            int rowCount = gridLayout.getRowCount();
            ViewGroup viewGroup = addChildView(gridLayout, row, col, ctx, density, 6);
            if (viewGroup == null) {
                Log.i(WebSiteUrl.Tag, "showRoachRoad = error,rowCount = " + rowCount + ",row=" + row + ",col=" + col);
                return;
            }
            switch (res) {
                case 1:
                    setTextMarginsBackground(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_bg, textSiae, "");
                    break;
                case 5:
                    setTextMarginsBackground(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_bg, textSiae, "");
                    break;


            }
        } catch (IllegalArgumentException e) {
            Log.i(WebSiteUrl.Tag, "showRoachRoad = error,,row=" + row + ",col=" + col);
        }


    }

    /**
     * 骰宝bigsmall路子
     *
     * @param ctx
     * @param gridLayout
     * @param density
     * @param res
     * @param row
     * @param col
     * @param textSiae
     * @param margins
     */
    public void showBigSmallRoad(Context ctx, GridLayout gridLayout, float density, int res, int row, int col, int textSiae, int margins, int point, int waidic) {

        int rowCount = gridLayout.getRowCount();
        //	ViewGroup  viewGroup = (ViewGroup) gridLayout.getChildAt(col * rowCount + row);
        //    Log.i(WebSiteUrl.Tag,"addChildView rowCount = " +",row="+row+",col="+col+",point="+point);
        ViewGroup viewGroup = addChildView(gridLayout, row, col, ctx, density, headSpeace);
        if (viewGroup == null) {
            Log.i(WebSiteUrl.Tag, "showBigRoad = error,rowCount = " + rowCount + ",row=" + row + ",col=" + col);
            return;
        }

        switch (res) {
            case 1:
                if (waidic == 1) {
                    setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_green_solid_not, textSiae, "" + point);
                    TextView textView = (TextView) viewGroup.getChildAt(0);
                    textView.setTextColor(ctx.getResources().getColor(R.color.green500));
                } else {
                    setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_solid_not, textSiae, "" + point);
                    TextView textView = (TextView) viewGroup.getChildAt(0);
                    textView.setTextColor(ctx.getResources().getColor(R.color.red));
                }

                break;

            case 5:
                if (waidic == 1) {
                    setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_green_solid_not, textSiae, "" + point);
                    TextView textView = (TextView) viewGroup.getChildAt(0);
                    textView.setTextColor(ctx.getResources().getColor(R.color.green500));
                } else {
                    setTextMarginsBackgroundBig(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_solid_not, textSiae, "" + point);
                    TextView textViewSmall = (TextView) viewGroup.getChildAt(0);
                    textViewSmall.setTextColor(ctx.getResources().getColor(R.color.blue));
                }

                break;


        }
    }

    public void showEvenOddRoad(Context ctx, GridLayout gridLayout, float density, int res, int row, int col, int textSiae, int margins, int point, int waidic) {

        int rowCount = gridLayout.getRowCount();
        //	ViewGroup  viewGroup = (ViewGroup) gridLayout.getChildAt(col * rowCount + row);
        //   Log.i(WebSiteUrl.Tag,"addChildView rowCount = " +",row="+row+",col="+col+",point="+point);
        ViewGroup viewGroup = addChildView(gridLayout, row, col, ctx, density, headSpeace);
        if (viewGroup == null) {
            Log.i(WebSiteUrl.Tag, "showBigRoad = error,rowCount = " + rowCount + ",row=" + row + ",col=" + col);
            return;
        }


        if (point % 2 == 0) {
            if (AppTool.getAppLanguage(ctx).equals("zh")) {
                evenOdd = "双";
                waiDic = "围";
            } else {
                evenOdd = "E";
                waiDic = "T";
            }
        } else {
            if (AppTool.getAppLanguage(ctx).equals("zh")) {
                evenOdd = "单";
                waiDic = "围";
            } else {
                evenOdd = "O";
                waiDic = "T";
            }
        }

        switch (res) {
            case 1:
                if (waidic == 1) {
                    setTextMarginsBackground(ctx, viewGroup, density, margins, R.drawable.gd_oval_green_solid, textSiae, waiDic);
                } else
                    setTextMarginsBackground(ctx, viewGroup, density, margins, R.drawable.gd_oval_red_solid, textSiae, evenOdd);

                break;

            case 5:
                if (waidic == 1) {
                    setTextMarginsBackground(ctx, viewGroup, density, margins, R.drawable.gd_oval_green_solid, textSiae, waiDic);
                } else
                    setTextMarginsBackground(ctx, viewGroup, density, margins, R.drawable.gd_oval_blue_solid, textSiae, evenOdd);

                break;


        }
    }

    public void showRoad(Context ctx, GridLayout gridLayout, float density, int type, int res, int row, int col, int point, int waidic) {
        switch (type) {
            case 1:
                showBigRoad(ctx, gridLayout, density, res, row, col, 6, 1);
                break;
            case 2:
                showBigEyesRoad(ctx, gridLayout, density, res, row, col, 5, 1);
                break;
            case 3:
                showSmallEyesRoad(ctx, gridLayout, density, res, row, col, 7, 0);
                break;
            case 4:
                showRoachRoad(ctx, gridLayout, density, res, row, col, 5, 0);
                break;
            case 5:
                if (ctx.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    showBigSmallRoad(ctx, gridLayout, density, res, row, col, 8, 1, point, waidic);
                } else {
                    showBigSmallRoad(ctx, gridLayout, density, res, row, col, 10, 1, point, waidic);
                }
                break;
            case 6:
                showEvenOddRoad(ctx, gridLayout, density, res, row, col, 10, 0, point, waidic);
                break;

        }
    }

    public void setMarginsPointMarker(Context ctx, ViewGroup view, float density, int margins, int backgroundRes, int gravity) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) (6 * density), (int) (6 * density));
        layoutParams.setMargins(((int) (margins * density)) / 4, ((int) (margins * density)) / 4, ((int) (margins * density)) / 4, ((int) (margins * density)) / 4);
        layoutParams.gravity = gravity;
        TextView markTv = new TextView(ctx);
        markTv.setLayoutParams(layoutParams);
        markTv.setBackgroundResource(backgroundRes);
        view.addView(markTv);
    }

    public void setTextMarginsBackground(Context ctx, ViewGroup view, float density, int Margins, int backgroundRes, int textSize, String text, int type) {
//        TextView tx = new TextView(ctx);
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        layoutParams.setMargins(0, 0, 0, 0);//4个参数按顺序分别是左上右下
////		tx.setBackgroundResource(backgroundRes);
//        tx.setLayoutParams(layoutParams);
//        tx.setTextSize(textSize);
//        tx.setText(text);
//        tx.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD | Typeface.ITALIC));
//        if (type == 1)
//            tx.setTextColor(ctx.getResources().getColor(R.color.red));
//        else
//            tx.setTextColor(ctx.getResources().getColor(R.color.blue));
//        tx.setGravity(Gravity.CENTER);
        if (type == 1) {
            TiagonalRedView redView = new TiagonalRedView(ctx);
            view.addView(redView);
        } else {
            TiagonalBlueView blueView = new TiagonalBlueView(ctx);
            view.addView(blueView);
        }
//        view.addView(tx);
    }

    public void setTextMarginsBackground(Context ctx, ViewGroup view, float density, float Margins, int backgroundRes, int textSize, String text) {
        TextView tx = new TextView(ctx);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(((int) (Margins * density)) / 3, ((int) (Margins * density)) / 3, ((int) (Margins * density)) / 3, ((int) (Margins * density)) / 3);//4个参数按顺序分别是左上右下
        tx.setBackgroundResource(backgroundRes);
        tx.setLayoutParams(layoutParams);
        setText(ctx, tx, textSize, text);
        view.addView(tx);
    }

    public void setTextMarginsBackgroundBig(Context ctx, ViewGroup view, float density, float Margins, int backgroundRes, int textSize, String text) {
        TextView tx = new TextView(ctx);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(((int) (Margins * density)), ((int) (Margins * density)), ((int) (Margins * density)), ((int) (Margins * density)));//4个参数按顺序分别是左上右下
        tx.setBackgroundResource(backgroundRes);
        tx.setLayoutParams(layoutParams);
        setText(ctx, tx, textSize, text);
        view.addView(tx);
    }

    public void setText(Context ctx, TextView tv, int size, String text) {
        tv.setText(text);
        tv.setTextSize(size);
        tv.setTextColor(ctx.getResources().getColor(R.color.white));
        tv.setGravity(Gravity.CENTER);
    }

    /**
     * @param luzi
     * @param ctx
     * @param gridLayout
     * @param iColCount
     * @param density
     * @param type       百家乐里面（1表示bigroad,2表示bigeyesroad,3表示smalleyesroad,4表示小强路），骰宝里面1表示bigsmall,2表示evenodd road
     * @param gameType   1表示百家乐，2表示轮盘，3表示骰宝，4表示龙虎
     * @return
     */
    public int[][] ShowBaccaratBigRoad(String luzi, Context ctx, GridLayout gridLayout, int iColCount, float density, int type, int gameType) {
        //定义一个整型的二维数组40X40
        int[][] arrayRoad = new int[40][40];
        int col = 0;//列数
        int row = 0;//行数
        try {

            if (luzi == null || luzi.length() <= 0 || "null".equals(luzi) || "null#".equals(luzi)) {
                return arrayRoad;
            }
            String luziInfo[] = luzi.split("\\#");
            if (luziInfo.length <= 0 && luziInfo.length > 80) {//数据格式不对
                return arrayRoad;
            }
            //   Log.i("855play"," luzi length "+luziInfo.length);
            int tieIndex = 0;
            int bankerIndex = 0;
            int playerIndex = 0;
            int index = luziInfo.length;
            int lastRow = 0;
            int lastCol = 0;
            int point = 0;//点数
            int waidic = 0;

            String flag[] = new String[350];
            for (int i = 0; i < 300; i++) {
                flag[i] = "0";
            }


            boolean bJumpBanker = false;
            int jumpBankerIndex = 0;

            boolean bJumpPlayer = false;
            int jumpPlayerIndex = 0;
            clearChildView(gridLayout);
            //    Log.i(WebSiteUrl.Tag,"ShowBaccaratBigRoad = index = "+index );

            for (int i = 0; i < index; i++) {
                String luziInfoDetail[] = luziInfo[i].split("\\-");//路子相信信息9-8-7
                if (luziInfoDetail[0] == null || luziInfoDetail[0].length() == 0)
                    break;
                int res = Integer.parseInt(luziInfoDetail[0]);
                //只有骰宝才这样执行
                waidic = 0;
                if (gameType == 3 && luziInfoDetail.length == 3) {
                    point = Integer.parseInt(luziInfoDetail[0]) + Integer.parseInt(luziInfoDetail[1]) + Integer.parseInt(luziInfoDetail[2]);
                    if ((Integer.parseInt(luziInfoDetail[0]) == Integer.parseInt(luziInfoDetail[1])) && (Integer.parseInt(luziInfoDetail[0]) == Integer.parseInt(luziInfoDetail[2])))
                        waidic = 1;
//                    if("111".equals(luziInfoDetail[1]) || "222".equals(luziInfoDetail[1])|| "333".equals(luziInfoDetail[1])
//                            || "444".equals(luziInfoDetail[1])
//                            || "555".equals(luziInfoDetail[1])
//                            || "666".equals(luziInfoDetail[1])){
//                        waidic = 1;
//                    }
//                    point = Integer.parseInt(luziInfoDetail[0]) ;

                    if (type == 5) {
                        if (point > 10) {
                            res = 1;
                        } else
                            res = 5;
                    } else {
                        if (point % 2 == 0) {
                            res = 5;
                        } else
                            res = 1;
                    }

                }
                //     Log.i("855play"," luzi data "+i + " -- " +res);
                if (res == 9 || res == 10 || res == 11 || res == 12) {//和不显示
                    if (tieIndex > 0) {//要移掉显示和的数字
                        //移除最后一层的数字跟斜杠
                        //	removeLastView(framelayout);

                    }
                    tieIndex++;
                    ///////////显示和的路子
                    if (bankerIndex == 0 && playerIndex == 0) {//前面一个显示都还没有的特殊情况

                    } else {//在最后一层上面添加和的信息
                        //	show_Tie_Luzi(framelayout,tieIndex, ctx, picWidth, picHeight);
                        showTieNumber(ctx, gridLayout, density, tieIndex, lastRow, lastCol);
                    }
                } else if (res == 1 || res == 2 || res == 3 || res == 4) {//庄，要先判断前面一个是什么才知道怎么显示在哪一列
                    if (bankerIndex == 0) {//和前面一个不一样
                        if (bankerIndex == 0 && playerIndex == 0) {//填充到第一个格子，一个路子都还么有填充的情况
                            /////////下面又分前面一个是不是和的情况

                            ///////////还要判断这个格子是否被填充了
                            int insertIndex = col * iColCount + row;
                            if (type == 1)
                                arrayRoad[row][col] = 1;
                            //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                            if ("0".equals(flag[insertIndex])) {//允许插入
                                //插入图片

                                flag[insertIndex] = "1";
                                //	show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
                                showRoad(ctx, gridLayout, density, type, res, row, col, point, waidic);
                                lastRow = row;
                                lastCol = col;
                            }


                        } else {//换列

                            col++;
                            row = 0;
                            /////////////判断是否有插入了
                            int insertIndex = (col + jumpBankerIndex) * iColCount + row;
                            if (type == 1)
                                arrayRoad[row][col] = 1;
                            //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                            if ("0".equals(flag[insertIndex])) {//允许插入，此种情况是顶部第一行被插入了
                                //插入图片
                                flag[insertIndex] = "1";
                                //	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                showRoad(ctx, gridLayout, density, type, res, row, (col + jumpBankerIndex), point, waidic);
                                lastRow = row;
                                lastCol = col + jumpBankerIndex;
                            }
                            ////////如果被插入了，转弯
                            else {
                                bJumpBanker = true;
                                jumpBankerIndex++;
                                col++;
                                insertIndex = col * iColCount + row;
                                flag[insertIndex] = "1";
                                //   show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
                                showRoad(ctx, gridLayout, density, type, res, row, col, point, waidic);
                                lastRow = row;
                                lastCol = col;

                            }


                        }
                    } else {//继续庄，不换列,换行

                        if (bJumpBanker == true) {

                            row++;
                            int insertIndex = (col + jumpBankerIndex) * iColCount + row;
                            if (type == 1)
                                arrayRoad[row][col] = 1;
                            //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                            if ("0".equals(flag[insertIndex])) {//允许插入
                                //插入图片
                                flag[insertIndex] = "1";
                                //	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                showRoad(ctx, gridLayout, density, type, res, row, (col + jumpBankerIndex), point, waidic);
                                lastRow = row;
                                lastCol = col + jumpBankerIndex;
                            } else {
                                jumpBankerIndex++;
                                insertIndex = (col + jumpBankerIndex) * iColCount + row;
                                flag[insertIndex] = "1";
                                //     show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                showRoad(ctx, gridLayout, density, type, res, row, (col + jumpBankerIndex), point, waidic);
                                lastRow = row;
                                lastCol = col + jumpBankerIndex;
                            }

                        } else {
                            row++;
                            //	arrayRoad[row][col] = 2;

                            if (row > iColCount - 1) {

                                row = iColCount - 1;
                                ///////需要换列
                                jumpBankerIndex++;
                            }
                            if (type == 1)
                                arrayRoad[row + jumpBankerIndex][col] = 1;

                            int insertIndex = (col + jumpBankerIndex) * iColCount + row;
                            int topIndex = (col + jumpBankerIndex) * iColCount;
                            int lastIndex = (col + jumpBankerIndex) * iColCount + (row - 1);
                            if ("0".equals(flag[insertIndex])) {//允许插入,并且顶部第一行必须没有被插入的情况下
                                //插入图片
                                if (row > 0 && "0".equals(flag[topIndex]) && "1".equals(flag[lastIndex])) {
                                    jumpBankerIndex++;
                                    row--;
                                    insertIndex = (col + jumpBankerIndex) * iColCount + row;
                                    flag[insertIndex] = "1";
                                    showRoad(ctx, gridLayout, density, type, res, row, (col + jumpBankerIndex), point, waidic);
                                    lastRow = row;
                                    lastCol = col + jumpBankerIndex;
                                } else {
                                    flag[insertIndex] = "1";
                                    //    	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                    showRoad(ctx, gridLayout, density, type, res, row, (col + jumpBankerIndex), point, waidic);
                                    lastRow = row;
                                    lastCol = col + jumpBankerIndex;
                                }

                            } else {
                                jumpBankerIndex++;
                                row--;
                                insertIndex = (col + jumpBankerIndex) * iColCount + row;
                                flag[insertIndex] = "1";
                                //    show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                showRoad(ctx, gridLayout, density, type, res, row, (col + jumpBankerIndex), point, waidic);
                                lastRow = row;
                                lastCol = col + jumpBankerIndex;
                            }

                        }


                    }
                    bankerIndex = res;
                    playerIndex = 0;
                    tieIndex = 0;
                    jumpPlayerIndex = 0;
                    bJumpPlayer = false;
                } else if (res == 5 || res == 6 || res == 7 || res == 8) {//闲,，要先判断前面一个是什么才知道怎么显示在哪一列
                    if (playerIndex == 0) {//和前面一个不一样
                        if (bankerIndex == 0 && playerIndex == 0) {//填充到第一个格子，一个路子都还么有填充的情况
                            /////////下面又分前面一个是不是和的情况

                            ///////////还要判断这个格子是否被填充了
                            int insertIndex = col * iColCount + row;
                            if ("0".equals(flag[insertIndex])) {//允许插入
                                //插入图片
                                flag[insertIndex] = "1";
                                //  	show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
                                showRoad(ctx, gridLayout, density, type, res, row, col, point, waidic);
                                lastRow = row;
                                lastCol = col;
                            }
                            if (type == 1)
                                arrayRoad[row][col] = 2;
                            //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                        } else {//换列

                            col++;
                            row = 0;
                            /////////////判断是否有插入了
                            int insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                            if (type == 1)
                                arrayRoad[row][col] = 2;
                            //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                            if ("0".equals(flag[insertIndex])) {//允许插入
                                //插入图片
                                flag[insertIndex] = "1";
                                //  	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                showRoad(ctx, gridLayout, density, type, res, row, (col + jumpPlayerIndex), point, waidic);
                                lastRow = row;
                                lastCol = col + jumpPlayerIndex;
                            }
                            ////////如果被插入了，转弯
                            else {
                                bJumpPlayer = true;
                                jumpPlayerIndex++;
                                col++;
                                insertIndex = col * iColCount + row;
                                flag[insertIndex] = "1";
                                //     show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
                                showRoad(ctx, gridLayout, density, type, res, row, col, point, waidic);
                                lastRow = row;
                                lastCol = col;
                            }


                        }
                    } else {//继续闲，不换列,换行

                        if (bJumpPlayer == true) {
                            row++;
                            int insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                            if (type == 1)
                                arrayRoad[row][col] = 2;
                            //		Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                            if ("0".equals(flag[insertIndex])) {
                                flag[insertIndex] = "1";
                                //show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                showRoad(ctx, gridLayout, density, type, res, row, (col + jumpPlayerIndex), point, waidic);
                                lastRow = row;
                                lastCol = col + jumpPlayerIndex;
                            } else {
                                jumpPlayerIndex++;
                                insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                                flag[insertIndex] = "1";
                                //    show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                showRoad(ctx, gridLayout, density, type, res, row, (col + jumpPlayerIndex), point, waidic);
                                lastRow = row;
                                lastCol = col + jumpPlayerIndex;
                            }

                        } else {
                            row++;
                            //arrayRoad[row][col] = 2;

                            if (row > iColCount - 1) {
                                row = iColCount - 1;
                                ///////需要换列
                                jumpPlayerIndex++;
                            }
                            if (type == 1)
                                arrayRoad[row + jumpPlayerIndex][col] = 2;

                            int insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                            int topIndex = (col + jumpPlayerIndex) * iColCount;
                            int lastIndex = (col + jumpPlayerIndex) * iColCount + (row - 1);
                            if ("0".equals(flag[insertIndex])) {//允许插入,并且顶部第一行必须没有被插入的情况下
                                //插入图片
                                if (row > 0 && "0".equals(flag[topIndex]) && "1".equals(flag[lastIndex])) {
                                    jumpPlayerIndex++;
                                    row--;//转弯的话，行数不加
                                    insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                                    flag[insertIndex] = "1";
                                    //  [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpPlayerIndex) :row];
                                    //      show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                    showRoad(ctx, gridLayout, density, type, res, row, (col + jumpPlayerIndex), point, waidic);
                                    lastRow = row;
                                    lastCol = col + jumpPlayerIndex;
                                } else {
                                    flag[insertIndex] = "1";
                                    //	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                    showRoad(ctx, gridLayout, density, type, res, row, (col + jumpPlayerIndex), point, waidic);
                                    lastRow = row;
                                    lastCol = col + jumpPlayerIndex;
                                }

                            } else {
                                jumpPlayerIndex++;
                                row--;//转弯的话，行数不加
                                insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                                flag[insertIndex] = "1";
                                showRoad(ctx, gridLayout, density, type, res, row, (col + jumpPlayerIndex), point, waidic);
                                lastRow = row;
                                lastCol = col + jumpPlayerIndex;
                            }

                        }


                    }
                    playerIndex = res;
                    bankerIndex = 0;
                    tieIndex = 0;
                    jumpBankerIndex = 0;
                    bJumpBanker = false;
                }
            }


            //大眼路
            //  smallLuzi = GetSmallRoad(arrayRoad,1,2,2,1);
            //小眼路
            //   smallLuzi = GetSmallRoad(arrayRoad,2,3,3,2);
            //小强路
            //    smallLuzi = GetSmallRoad(arrayRoad,3,4,4,3);
            //    Log.i("LanjianTest", "++++++"+luzi);
            //    Log.i("LanjianTest", "------"+smallLuzi);

        } catch (ArrayIndexOutOfBoundsException e) {
            Log.i(WebSiteUrl.Tag, "ArrayIndexOutOfBoundsException,row=" + row + ",col" + col + ",Luzi=" + luzi);
        }

        return arrayRoad;
    }

    public void showRoulette(String luzi, Context ctx, GridLayout gridLayout, int iColCount, float density) {
        //定义一个整型的二维数组40X40

        int col = 0;//列数
        int row = 0;//行数
        try {

            if (luzi == null || luzi.length() <= 0 || "null".equals(luzi) || "null#".equals(luzi)) {
                return;
            }
            String luziInfo[] = luzi.split("\\#");


            if (luziInfo.length <= 0 && luziInfo.length > 80) {//数据格式不对
                return;
            }
            List<String> listItem = new ArrayList<String>();
            if (luziInfo != null) {

                int n = luziInfo.length;
                for (int i = n - 1; i >= n - 50; i--) {
                    listItem.add(luziInfo[i]);
                }
            }
            luziInfo = listItem.toArray(new String[50]);
            //   Log.i("855play"," luzi length "+luziInfo.length);
            int tieIndex = 0;
            int bankerIndex = 0;
            int playerIndex = 0;
            int index = luziInfo.length;
            int lastRow = 0;
            int lastCol = 0;
            int point = 0;//点数
            int waidic = 0;

            String flag[] = new String[350];
            for (int i = 0; i < 300; i++) {
                flag[i] = "0";
            }


            boolean bJumpBanker = false;
            int jumpBankerIndex = 0;

            boolean bJumpPlayer = false;
            int jumpPlayerIndex = 0;
            clearChildView(gridLayout);
            //    Log.i(WebSiteUrl.Tag,"ShowBaccaratBigRoad = index = "+index );

            for (int i = 0; i < index; i++) {
                String luziInfoDetail[] = luziInfo[i].split("\\-");//路子相信信息9-8-7
                if (luziInfoDetail[0] == null || luziInfoDetail[0].length() == 0)
                    break;
                int res = Integer.parseInt(luziInfoDetail[0]);
                //只有骰宝才这样执行
                waidic = 0;

                //     Log.i("855play"," luzi data "+i + " -- " +res);
               /* if (res == 9 || res == 10 || res == 11 || res == 12) {//和不显示
                    if (tieIndex > 0) {//要移掉显示和的数字
                        //移除最后一层的数字跟斜杠
                        //	removeLastView(framelayout);

                    }
                    tieIndex++;
                    ///////////显示和的路子
                    if (bankerIndex == 0 && playerIndex == 0) {//前面一个显示都还没有的特殊情况

                    } else {//在最后一层上面添加和的信息
                        //	show_Tie_Luzi(framelayout,tieIndex, ctx, picWidth, picHeight);
                        showTieNumber(ctx, gridLayout, density, tieIndex, lastRow, lastCol);
                    }
                } else */
                switch (res) {
                    case 2:
                    case 4:
                    case 6:
                    case 8:
                    case 10:
                    case 11:
                    case 13:
                    case 15:
                    case 17:
                    case 20:
                    case 22:
                    case 24:
                    case 26:
                    case 28:
                    case 29:
                    case 31:
                    case 33:
                    case 35:
                        //闲,，要先判断前面一个是什么才知道怎么显示在哪一列
                        if (playerIndex == 0) {//和前面一个不一样
                            if (bankerIndex == 0 && playerIndex == 0) {//填充到第一个格子，一个路子都还么有填充的情况
                                /////////下面又分前面一个是不是和的情况

                                ///////////还要判断这个格子是否被填充了
                                int insertIndex = col * iColCount + row;
                                if ("0".equals(flag[insertIndex])) {//允许插入
                                    //插入图片
                                    flag[insertIndex] = "1";
                                    //  	show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
//                                    showBigRoad(ctx, gridLayout, density, res, row, col, 6, 1);
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, col, res, R.color.black_brown))
                                        return;
                                    lastRow = row;
                                    lastCol = col;
                                }


                                //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                            } else {//换列
                                col++;
                                row = 0;
                                /////////////判断是否有插入了
                                int insertIndex = (col + jumpPlayerIndex) * iColCount + row;

                                //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                                if ("0".equals(flag[insertIndex])) {//允许插入
                                    //插入图片
                                    flag[insertIndex] = "1";
                                    lastRow = row;
                                    lastCol = col + jumpPlayerIndex;
                                    //  	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpPlayerIndex), res, R.color.black_brown))
                                        return;

                                }
                                ////////如果被插入了，转弯
                                else {
                                    bJumpPlayer = true;
                                    jumpPlayerIndex++;
                                    col++;
                                    insertIndex = col * iColCount + row;
                                    flag[insertIndex] = "1";
                                    //     show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
                                    lastRow = row;
                                    lastCol = col;
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, col, res, R.color.black_brown))
                                        return;
                                }


                            }
                        } else {//继续闲，不换列,换行

                            if (bJumpPlayer == true) {
                                row++;
                                int insertIndex = (col + jumpPlayerIndex) * iColCount + row;

                                //		Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                                if ("0".equals(flag[insertIndex])) {
                                    flag[insertIndex] = "1";
                                    //show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);

                                    lastRow = row;
                                    lastCol = col + jumpPlayerIndex;
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpPlayerIndex), res, R.color.black_brown))
                                        return;
                                } else {
                                    jumpPlayerIndex++;
                                    insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                                    flag[insertIndex] = "1";
                                    //    show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);

                                    lastRow = row;
                                    lastCol = col + jumpPlayerIndex;
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpPlayerIndex), res, R.color.black_brown))
                                        return;
                                }

                            } else {
                                row++;
                                //arrayRoad[row][col] = 2;

                                if (row > iColCount - 1) {
                                    row = iColCount - 1;
                                    ///////需要换列
                                    jumpPlayerIndex++;
                                }


                                int insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                                int topIndex = (col + jumpPlayerIndex) * iColCount;
                                int lastIndex = (col + jumpPlayerIndex) * iColCount + (row - 1);
                                if ("0".equals(flag[insertIndex])) {//允许插入,并且顶部第一行必须没有被插入的情况下
                                    //插入图片
                                    if (row > 0 && "0".equals(flag[topIndex]) && "1".equals(flag[lastIndex])) {
                                        jumpPlayerIndex++;
                                        row--;//转弯的话，行数不加
                                        insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                                        flag[insertIndex] = "1";
                                        //  [self show_Pic_Luzi:res :sv :x :y :0 :(col+jumpPlayerIndex) :row];
                                        //      show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                        lastRow = row;
                                        lastCol = col + jumpPlayerIndex;
                                        if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpPlayerIndex), res, R.color.black_brown))
                                            return;
                                    } else {
                                        flag[insertIndex] = "1";
                                        //	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                        lastRow = row;
                                        lastCol = col + jumpPlayerIndex;
                                        if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpPlayerIndex), res, R.color.black_brown))
                                            return;
                                    }

                                } else {
                                    jumpPlayerIndex++;
                                    row--;//转弯的话，行数不加
                                    insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                                    flag[insertIndex] = "1";

                                    lastRow = row;
                                    lastCol = col + jumpPlayerIndex;
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpPlayerIndex), res, R.color.black_brown))
                                        return;
                                }

                            }


                        }
                        playerIndex = res;
                        bankerIndex = 0;
                        jumpBankerIndex = 0;
                        bJumpBanker = false;
                        break;

                    default://zhuang
                        //庄，要先判断前面一个是什么才知道怎么显示在哪一列
                        if (bankerIndex == 0) {//和前面一个不一样
                            if (bankerIndex == 0 && playerIndex == 0) {//填充到第一个格子，一个路子都还么有填充的情况
                                /////////下面又分前面一个是不是和的情况

                                ///////////还要判断这个格子是否被填充了
                                int insertIndex = col * iColCount + row;


                                //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                                if ("0".equals(flag[insertIndex])) {//允许插入
                                    //插入图片

                                    flag[insertIndex] = "1";
                                    //	show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
                                    showRoad(ctx, gridLayout, density, 1, res, row, col, point, waidic);
                                    lastRow = row;
                                    lastCol = col;
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, col, res, R.color.red_brown))
                                        return;
                                }


                            } else {//换列

                                col++;
                                row = 0;
                                /////////////判断是否有插入了
                                int insertIndex = (col + jumpBankerIndex) * iColCount + row;


                                //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                                if ("0".equals(flag[insertIndex])) {//允许插入，此种情况是顶部第一行被插入了
                                    //插入图片
                                    flag[insertIndex] = "1";
                                    //	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                    lastRow = row;
                                    lastCol = col + jumpBankerIndex;
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpBankerIndex), res, R.color.red_brown))
                                        return;
                                }
                                ////////如果被插入了，转弯
                                else {
                                    bJumpBanker = true;
                                    jumpBankerIndex++;
                                    col++;
                                    insertIndex = col * iColCount + row;
                                    flag[insertIndex] = "1";
                                    //   show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);
                                    lastRow = row;
                                    lastCol = col;
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, col, res, R.color.red_brown))
                                        return;
                                }


                            }
                        } else {//继续庄，不换列,换行

                            if (bJumpBanker == true) {

                                row++;
                                int insertIndex = (col + jumpBankerIndex) * iColCount + row;

                                //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                                if ("0".equals(flag[insertIndex])) {//允许插入
                                    //插入图片
                                    flag[insertIndex] = "1";
                                    //	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                    lastRow = row;
                                    lastCol = col + jumpBankerIndex;
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpBankerIndex), res, R.color.red_brown))
                                        return;
                                } else {
                                    jumpBankerIndex++;
                                    insertIndex = (col + jumpBankerIndex) * iColCount + row;
                                    flag[insertIndex] = "1";
                                    //     show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                    lastRow = row;
                                    lastCol = col + jumpBankerIndex;
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpBankerIndex), res, R.color.red_brown))
                                        return;
                                }

                            } else {
                                row++;
                                //	arrayRoad[row][col] = 2;

                                if (row > iColCount - 1) {

                                    row = iColCount - 1;
                                    ///////需要换列
                                    jumpBankerIndex++;
                                }


                                int insertIndex = (col + jumpBankerIndex) * iColCount + row;
                                int topIndex = (col + jumpBankerIndex) * iColCount;
                                int lastIndex = (col + jumpBankerIndex) * iColCount + (row - 1);
                                if ("0".equals(flag[insertIndex])) {//允许插入,并且顶部第一行必须没有被插入的情况下
                                    //插入图片
                                    if (row > 0 && "0".equals(flag[topIndex]) && "1".equals(flag[lastIndex])) {
                                        jumpBankerIndex++;
                                        row--;
                                        insertIndex = (col + jumpBankerIndex) * iColCount + row;
                                        flag[insertIndex] = "1";
                                        lastRow = row;
                                        lastCol = col + jumpBankerIndex;
                                        if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpBankerIndex), res, R.color.red_brown))
                                            return;
                                    } else {
                                        flag[insertIndex] = "1";
                                        //    	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);

                                        lastRow = row;
                                        lastCol = col + jumpBankerIndex;
                                        if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpBankerIndex), res, R.color.red_brown))
                                            return;
                                    }

                                } else {
                                    jumpBankerIndex++;
                                    row--;
                                    insertIndex = (col + jumpBankerIndex) * iColCount + row;
                                    flag[insertIndex] = "1";
                                    //    show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);
                                    lastRow = row;
                                    lastCol = col + jumpBankerIndex;
                                    if (showRouletteBigRoad(ctx, gridLayout, density, row, (col + jumpBankerIndex), res, R.color.red_brown))
                                        return;
                                }

                            }


                        }
                        bankerIndex = res;
                        if (res == 0) {
                            bankerIndex = 1;
                        }
                        playerIndex = 0;
                        jumpPlayerIndex = 0;
                        bJumpPlayer = false;
                        break;
                }
                if (res == 1 || res == 2 || res == 3 || res == 4) {
                } else if (res == 5 || res == 6 || res == 7 || res == 8) {
                }
            }


            //大眼路
            //  smallLuzi = GetSmallRoad(arrayRoad,1,2,2,1);
            //小眼路
            //   smallLuzi = GetSmallRoad(arrayRoad,2,3,3,2);
            //小强路
            //    smallLuzi = GetSmallRoad(arrayRoad,3,4,4,3);
            //    Log.i("LanjianTest", "++++++"+luzi);
            //    Log.i("LanjianTest", "------"+smallLuzi);

        } catch (ArrayIndexOutOfBoundsException e) {
            Log.i(WebSiteUrl.Tag, "ArrayIndexOutOfBoundsException,row=" + row + ",col" + col + ",Luzi=" + luzi);
        }

    }

    private boolean showRouletteBigRoad(Context ctx, GridLayout gridLayout, float density, int row, int col, int res, int backRes) {
        if (gridLayout == null) {
            return true;
        }
        int rowCount = gridLayout.getRowCount();
        //	ViewGroup  viewGroup = (ViewGroup) gridLayout.getChildAt(col * rowCount + row);
        ViewGroup viewGroup = addChildView(gridLayout, row, col, ctx, density, 20);
        if (viewGroup == null) {
            Log.i(WebSiteUrl.Tag, "showBigRoad = error,rowCount = " + rowCount + ",row=" + row + ",col=" + col);
            return true;
        }
        if (res == 0) {
            setTextMarginsBackground(ctx, viewGroup, density, 0.5f, R.color.green500, 12, "0");
        } else {
            setTextMarginsBackground(ctx, viewGroup, density, 0.5f, backRes, 12, res + "");
        }
        return false;
    }

    public void updateDragenTigerBigRoad(final Context mContext, DragonTiger baccarat, final GridLayout gridLayoutBigRoad, TextView tv_shoe, TextView tv_total, TextView tv_banker, TextView tv_player, TextView tv_tie, TextView tv_bp, TextView tv_pp) {

        if (baccarat.getBigRoad() != null && !baccarat.getBigRoad().equals(baccarat.getBigRoadOld()) && !baccarat.getGameNumber().equals("0")) {
            //        Log.i(WebSiteUrl.Tag,"updateRoad(),TableID="+baccarat.getTableName()+",Luzi roads="+baccarat.getBigRoadOld()+ ",BigRoad="+baccarat.getBigRoad());
            baccarat.setBigRoadOld(baccarat.getBigRoad());
            ShowBaccaratBigRoad(baccarat.getBigRoadOld(), mContext, gridLayoutBigRoad, 6, ScreenUtil.getDisplayMetrics(mContext).density, 1, 1);
            if (gridLayoutBigRoad != null) {
                gridLayoutBigRoad.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollTableRoad(gridLayoutBigRoad, mContext);
                    }
                });
            }
            //            updateDragonTigerGameNumber(baccarat, tv_shoe, tv_total, tv_banker, tv_player, tv_tie, tv_bp, tv_pp);

        }
    }

    public void updateDragenTigerRoad(Context mContext, float density, DragonTiger baccarat, GridLayout gridLayoutHeadRoad, final GridLayout gridLayoutBigRoad, final GridLayout gridLayoutBigEyesRoad
            , final GridLayout gridLayoutSmallEyesRoad, final GridLayout gridLayoutRoachRoad
            , TextView tv_shoe, TextView tv_total, TextView tv_banker, TextView tv_player, TextView tv_tie, TextView tv_bp, TextView tv_pp,
                                      boolean isBigShow, final View gridLayoutBigRoadParent, final View hsv_small_road1, final View hsv_small_road2, final View hsv_small_road3) {
        if (baccarat.getStatus() == 0 || baccarat.getGameNumber().equals("0"))//清除路子信息
        {
            clearChildView(gridLayoutHeadRoad);
            clearChildView(gridLayoutBigRoad);
            clearChildView(gridLayoutBigEyesRoad);
            clearChildView(gridLayoutSmallEyesRoad);
            clearChildView(gridLayoutRoachRoad);
            gridLayoutHeadRoad.invalidate();
            gridLayoutBigRoad.invalidate();
            gridLayoutBigEyesRoad.invalidate();
            gridLayoutSmallEyesRoad.invalidate();
            gridLayoutRoachRoad.invalidate();
            baccarat.setShoeNumberOld(baccarat.getShoeNumber());
            tv_shoe.setText(baccarat.getShoeNumber() + " - 0");
            tv_total.setText("0");
            tv_banker.setText("0");
            tv_player.setText("0");
            tv_tie.setText("0");
            tv_bp.setText("0");
            tv_pp.setText("0");
            //需要显示洗牌中
        } else {//显示路子
            if (baccarat.getBigRoad() != null && !baccarat.getBigRoad().equals(baccarat.getBigRoadOld()) && !baccarat.getGameNumber().equals("0")) {
                //        Log.i(WebSiteUrl.Tag,"updateRoad(),TableID="+baccarat.getTableName()+",Luzi roads="+baccarat.getBigRoadOld()+ ",BigRoad="+baccarat.getBigRoad());
                baccarat.setBigRoadOld(baccarat.getBigRoad());
                int[][] road;
                if (AppTool.getAppLanguage(mContext).equals("zh")) {
                    dragon_road = "龍";
                    tiger_road = "虎";
                    tie_road = "和";
                } else {
                    dragon_road = "D";
                    tiger_road = "T";
                    tie_road = "T";
                }
                String bigRoadOld = baccarat.getBigRoadOld();
                List<String> dataList = new ArrayList<>();
                String[] split = bigRoadOld.split("#");
                if (split.length > 41) {
                    int firstIndex = 41;
                    for (int i = 0; i < 41; i++) {
                        firstIndex--;
                        int index = (split.length - 1) - firstIndex;
                        dataList.add(split[index]);
                    }
                    bigRoadOld = "";
                    for (int i = 0; i < dataList.size(); i++) {
                        bigRoadOld += dataList.get(i) + "#";
                    }
                }
                ShowHeadRoad(bigRoadOld, gridLayoutHeadRoad, mContext, density, dragon_road, tiger_road, tie_road);//头路
                //    road = ShowBaccaratBigRoad("1#5#5#5#1#12#3#7#1#5#6#1#5#1#1#9#9#1#5#1#1#1#1#5#5#3#5#5#1#1#1#5#5#1#5#2#5#1#1#1#5#1#5#5#6#7#5#1#1#5#1#6#5#5#9#", mContext, gridLayoutBigRoad, 6, density, 1,1);
                road = ShowBaccaratBigRoad(bigRoadOld, mContext, gridLayoutBigRoad, 6, density, 1, 1);

                String smallLuzi = baccarat.GetSmallRoad(road, 1, 2, 2, 1);
                if (smallLuzi == null || smallLuzi.length() <= 0) {
                    clearChildView(gridLayoutBigEyesRoad);
                } else
                    ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutBigEyesRoad, 6, density, 2, 1);

                smallLuzi = baccarat.GetSmallRoad(road, 3, 4, 4, 3);
                if (smallLuzi == null || smallLuzi.length() <= 0) {
                    clearChildView(gridLayoutSmallEyesRoad);
                } else
                    ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutSmallEyesRoad, 6, density, 3, 1);

                smallLuzi = baccarat.GetSmallRoad(road, 2, 3, 3, 2);
                if (smallLuzi == null || smallLuzi.length() <= 0) {
                    clearChildView(gridLayoutRoachRoad);
                } else
                    ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutRoachRoad, 6, density, 4, 1);

                if (gridLayoutBigRoadParent != null) {
                    gridLayoutBigRoadParent.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollRoad(gridLayoutBigRoadParent, gridLayoutBigRoad, hsv_small_road1, gridLayoutBigEyesRoad, hsv_small_road2, gridLayoutSmallEyesRoad, hsv_small_road3, gridLayoutRoachRoad);
                        }
                    });
                }

                //更新局数
                updateDragonTigerGameNumber(baccarat, tv_shoe, tv_total, tv_banker, tv_player, tv_tie, tv_bp, tv_pp);

            }

        }
    }

    public void updateDragenTigerAskRoad(Context mContext, float density, DragonTiger baccarat, GridLayout gridLayoutHeadRoad, final GridLayout gridLayoutBigRoad, final GridLayout gridLayoutBigEyesRoad
            , final GridLayout gridLayoutSmallEyesRoad, final GridLayout gridLayoutRoachRoad
            , String data, boolean isBigShow, boolean isAddData, final View gridLayoutBigRoadParent, final View hsv_small_road1, final View hsv_small_road2, final View hsv_small_road3, final boolean isNeedInit) {
        int[][] road;
        if (AppTool.getAppLanguage(mContext).equals("zh")) {
            dragon_road = "龍";
            tiger_road = "虎";
            tie_road = "和";
        } else {
            dragon_road = "D";
            tiger_road = "T";
            tie_road = "T";
        }
        String bigRoadOld = data;
        List<String> dataList = new ArrayList<>();
        String[] split = bigRoadOld.split("#");
        int length = 41;
        if (isAddData) {
            length = 42;
        }
        if (split.length > 41) {
            int firstIndex = length;
            for (int i = 0; i < length; i++) {
                firstIndex--;
                int index = (split.length - 1) - firstIndex;
                dataList.add(split[index]);
            }
            bigRoadOld = "";
            for (int i = 0; i < dataList.size(); i++) {
                bigRoadOld += dataList.get(i) + "#";
            }
        }
        ShowHeadRoad(bigRoadOld, gridLayoutHeadRoad, mContext, density, dragon_road, tiger_road, tie_road);//头路
        //    road = ShowBaccaratBigRoad("1#5#5#5#1#12#3#7#1#5#6#1#5#1#1#9#9#1#5#1#1#1#1#5#5#3#5#5#1#1#1#5#5#1#5#2#5#1#1#1#5#1#5#5#6#7#5#1#1#5#1#6#5#5#9#", mContext, gridLayoutBigRoad, 6, density, 1,1);
        road = ShowBaccaratBigRoad(bigRoadOld, mContext, gridLayoutBigRoad, 6, density, 1, 1);

        String smallLuzi = baccarat.GetSmallRoad(road, 1, 2, 2, 1);
        if (smallLuzi == null || smallLuzi.length() <= 0) {
            clearChildView(gridLayoutBigEyesRoad);
        } else
            ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutBigEyesRoad, 6, density, 2, 1);

        smallLuzi = baccarat.GetSmallRoad(road, 3, 4, 4, 3);
        if (smallLuzi == null || smallLuzi.length() <= 0) {
            clearChildView(gridLayoutSmallEyesRoad);
        } else
            ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutSmallEyesRoad, 6, density, 3, 1);

        smallLuzi = baccarat.GetSmallRoad(road, 2, 3, 3, 2);
        if (smallLuzi == null || smallLuzi.length() <= 0) {
            clearChildView(gridLayoutRoachRoad);
        } else
            ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutRoachRoad, 6, density, 4, 1);

        if (gridLayoutBigRoadParent != null) {
            gridLayoutBigRoadParent.post(new Runnable() {
                @Override
                public void run() {
                    if (isNeedInit) {
                        scrollRoad(gridLayoutBigRoadParent, gridLayoutBigRoad, hsv_small_road1, gridLayoutBigEyesRoad, hsv_small_road2, gridLayoutSmallEyesRoad, hsv_small_road3, gridLayoutRoachRoad);
                    }
                }
            });
        }

    }

    public void updateRouletteBigRoad(Roulette roulette
            , BaseRecyclerAdapter adapter, TextView tv_roulette_number, TextView tv_red, TextView tv_black, TextView tv_zero, TextView tv_even, TextView tv_odd, TextView tv_big, TextView tv_small) {

        if (roulette.getRoad() != null && !roulette.getRoad().equals(roulette.getRoadOld())) {

            roulette.setRoadOld(roulette.getRoad());
            String roadDetail[] = roulette.getRoad().split("\\#");
            if (roadDetail != null) {
                List<String> listItem = new ArrayList<String>();
                int start = 99;
                int end = 78;
                if (roadDetail.length == 99) {
                    start = 98;
                    end = 77;
                }
                for (int i = start; i >= end; i--) {
                    listItem.add(roadDetail[i]);
                }
                adapter.addAllAndClear(listItem);
//                roulette.getTotal(roulette.getRoad());
//                tv_roulette_number.setText("" + roulette.getGameNumber());
//                tv_red.setText("" + roulette.getRed());
//                tv_black.setText("" + roulette.getBlack());
//                tv_zero.setText("" + roulette.getZero());
//                tv_even.setText("" + roulette.getEven());
//                tv_odd.setText("" + roulette.getOdd());
//                tv_big.setText("" + roulette.getBig());
//                tv_small.setText("" + roulette.getSmall());
            }

        }
    }

    public void updateBigRoad(final Context mContext, Baccarat baccarat, final GridLayout gridLayoutBigRoad,
                              TextView tv_shoe, TextView tv_total, TextView tv_banker, TextView tv_player, TextView tv_tie, TextView tv_bp, TextView tv_pp, View ll_parent, TextView tvName) {
        if (baccarat.getBigRoad() != null && !baccarat.getBigRoad().equals(baccarat.getBigRoadOld()) && !baccarat.getGameNumber().equals("0")) {
            baccarat.setBigRoadOld(baccarat.getBigRoad());
            String s = updateGoodRoad(mContext, baccarat.getBigRoad());
            if (!TextUtils.isEmpty(s)) {
                tvName.setText(s);
                ll_parent.setVisibility(View.VISIBLE);
            } else {
                ll_parent.setVisibility(View.GONE);
            }
            //        Log.i(WebSiteUrl.Tag,"updateRoad(),TableID="+baccarat.getTableName()+",Luzi roads="+baccarat.getBigRoadOld()+ ",BigRoad="+baccarat.getBigRoad());
            baccarat.setBigRoadOld(baccarat.getBigRoad());

            ShowBaccaratBigRoad(baccarat.getBigRoadOld(), mContext, gridLayoutBigRoad, 6, ScreenUtil.getDisplayMetrics(mContext).density, 1, 1);
            if (gridLayoutBigRoad != null) {
                gridLayoutBigRoad.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollTableRoad(gridLayoutBigRoad, mContext);
                    }
                });
            }
            //            updateGameNumber(baccarat, tv_shoe, tv_total, tv_banker, tv_player, tv_tie, tv_bp, tv_pp);
        }
    }

    public void updateRoad(Context mContext, float density, Baccarat baccarat, GridLayout gridLayoutHeadRoad, final GridLayout gridLayoutBigRoad, final GridLayout gridLayoutBigEyesRoad
            , final GridLayout gridLayoutSmallEyesRoad, final GridLayout gridLayoutRoachRoad
            , TextView tv_shoe, TextView tv_total, TextView tv_banker, TextView tv_player, TextView tv_tie, TextView tv_bp, TextView tv_pp, View ll_good_road_parent,
                           TextView tv_good_road_name, boolean isBigShow, final View gridLayoutBigRoadParent, final View hsv_small_road1, final View hsv_small_road2, final View hsv_small_road3) {
        try {


            if (baccarat.getStatus() == 0 || baccarat.getGameNumber().equals("0"))//清除路子信息
            {
                clearChildView(gridLayoutHeadRoad);
                clearChildView(gridLayoutBigRoad);
                clearChildView(gridLayoutBigEyesRoad);
                clearChildView(gridLayoutSmallEyesRoad);
                clearChildView(gridLayoutRoachRoad);
                gridLayoutHeadRoad.invalidate();
                gridLayoutBigRoad.invalidate();
                gridLayoutBigEyesRoad.invalidate();
                gridLayoutSmallEyesRoad.invalidate();
                gridLayoutRoachRoad.invalidate();
                //     baccarat.setShoeNumberOld(baccarat.getShoeNumber());
                tv_shoe.setText(baccarat.getShoeNumber() + " - 0");
                LogUtil.d("tv_baccarat_shoe_number2", baccarat.getShoeNumber() + " - 0");
                tv_total.setText("0");
                tv_banker.setText("0");
                tv_player.setText("0");
                tv_tie.setText("0");
                tv_bp.setText("0");
                tv_pp.setText("0");
                //需要显示洗牌中
            } else {//显示路子
                if (baccarat.getBigRoad() != null && !baccarat.getBigRoad().equals(baccarat.getBigRoadOld()) && !baccarat.getGameNumber().equals("0")) {
                    //        Log.i(WebSiteUrl.Tag,"updateRoad(),TableID="+baccarat.getTableName()+",Luzi roads="+baccarat.getBigRoadOld()+ ",BigRoad="+baccarat.getBigRoad());
                    baccarat.setBigRoadOld(baccarat.getBigRoad());
                    if (ll_good_road_parent != null && tv_good_road_name != null) {
                        String s = updateGoodRoad(mContext, baccarat.getBigRoad());
                        if (!TextUtils.isEmpty(s)) {
                            tv_good_road_name.setText(s);
                            ll_good_road_parent.setVisibility(View.VISIBLE);
                        } else {
                            ll_good_road_parent.setVisibility(View.GONE);
                        }
                    }
                    int[][] road;
                    if (AppTool.getAppLanguage(mContext).equals("zh")) {
                        banker_road = "庄";
                        player_road = "闲";
                        tie_road = "和";
                    } else {
                        banker_road = "B";
                        player_road = "P";
                        tie_road = "T";
                    }
                    String bigRoadOld = baccarat.getBigRoadOld();
                    List<String> dataList = new ArrayList<>();
                    String[] split = bigRoadOld.split("#");
                    if (split.length > 41) {
                        int firstIndex = 41;
                        for (int i = 0; i < 41; i++) {
                            firstIndex--;
                            int index = (split.length - 1) - firstIndex;
                            dataList.add(split[index]);
                        }
                        bigRoadOld = "";
                        for (int i = 0; i < dataList.size(); i++) {
                            bigRoadOld += dataList.get(i) + "#";
                        }
                    }
                    ShowHeadRoad(bigRoadOld, gridLayoutHeadRoad, mContext, density, banker_road, player_road, tie_road);//头路
                    //    road = ShowBaccaratBigRoad("1#5#5#5#1#12#3#7#1#5#6#1#5#1#1#9#9#1#5#1#1#1#1#5#5#3#5#5#1#1#1#5#5#1#5#2#5#1#1#1#5#1#5#5#6#7#5#1#1#5#1#6#5#5#9#", mContext, gridLayoutBigRoad, 6, density, 1,1);
                    road = ShowBaccaratBigRoad(bigRoadOld, mContext, gridLayoutBigRoad, 6, density, 1, 1);

                    String smallLuzi = baccarat.GetSmallRoad(road, 1, 2, 2, 1);
                    if (smallLuzi == null || smallLuzi.length() <= 0) {
                        clearChildView(gridLayoutBigEyesRoad);
                    } else
                        ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutBigEyesRoad, 6, density, 2, 1);

                    smallLuzi = baccarat.GetSmallRoad(road, 3, 4, 4, 3);
                    if (smallLuzi == null || smallLuzi.length() <= 0) {
                        clearChildView(gridLayoutSmallEyesRoad);
                    } else
                        ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutSmallEyesRoad, 6, density, 3, 1);

                    smallLuzi = baccarat.GetSmallRoad(road, 2, 3, 3, 2);
                    if (smallLuzi == null || smallLuzi.length() <= 0) {
                        clearChildView(gridLayoutRoachRoad);
                    } else
                        ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutRoachRoad, 6, density, 4, 1);
                    if (gridLayoutBigRoadParent != null) {
                        gridLayoutBigRoadParent.post(new Runnable() {
                            @Override
                            public void run() {
                                scrollRoad(gridLayoutBigRoadParent, gridLayoutBigRoad, hsv_small_road1, gridLayoutBigEyesRoad, hsv_small_road2, gridLayoutSmallEyesRoad, hsv_small_road3, gridLayoutRoachRoad);
                            }
                        });
                    }
                    //更新局数
                    updateGameNumber(baccarat, tv_shoe, tv_total, tv_banker, tv_player, tv_tie, tv_bp, tv_pp);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollTableRoad(GridLayout baccarat_big_road, Context mContext) {
        int bigRoadParent2width = ScreenUtil.dip2px(mContext, 140);
        int bigRoadWidth = baccarat_big_road.getWidth();
        if (bigRoadWidth > bigRoadParent2width) {
            baccarat_big_road.scrollTo(0, 0);
            baccarat_big_road.scrollTo(bigRoadWidth - bigRoadParent2width, 0);
        }
    }

    public void scrollRoad(View baccarat_big_road_Parent, GridLayout baccarat_big_road, View hsv_small_road1, GridLayout gridLayoutBigEyesRoad
            , View hsv_small_road2, GridLayout gridLayoutSmallEyesRoad, View hsv_small_road3, GridLayout gridLayoutRoachRoad) {
        int bigRoadParent2width = baccarat_big_road_Parent.getWidth();
        int bigRoadWidth = baccarat_big_road.getWidth();
        if (bigRoadWidth > bigRoadParent2width) {
            baccarat_big_road.scrollTo(0, 0);
            baccarat_big_road.scrollTo(bigRoadWidth - bigRoadParent2width, 0);
        } else {
            baccarat_big_road.scrollTo(0, 0);
        }
        int hsvSmallRoadWidth1 = hsv_small_road1.getWidth();
        int gridLayoutBigEyesRoadWidth = gridLayoutBigEyesRoad.getWidth();
        if (gridLayoutBigEyesRoadWidth > hsvSmallRoadWidth1) {
            gridLayoutBigEyesRoad.scrollTo(0, 0);
            gridLayoutBigEyesRoad.scrollTo(gridLayoutBigEyesRoadWidth - hsvSmallRoadWidth1, 0);
        } else {
            gridLayoutBigEyesRoad.scrollTo(0, 0);
        }
        int hsvSmallRoadWidth2 = hsv_small_road2.getWidth();
        int gridLayoutSmallEyesRoadWidth = gridLayoutSmallEyesRoad.getWidth();
        if (gridLayoutSmallEyesRoadWidth > hsvSmallRoadWidth2) {
            gridLayoutSmallEyesRoad.scrollTo(0, 0);
            gridLayoutSmallEyesRoad.scrollTo(gridLayoutSmallEyesRoadWidth - hsvSmallRoadWidth2, 0);
        } else {
            gridLayoutSmallEyesRoad.scrollTo(0, 0);
        }
        int hsvSmallRoadWidth3 = hsv_small_road3.getWidth();
        int gridLayoutRoachRoadWidth = gridLayoutRoachRoad.getWidth();
        if (gridLayoutRoachRoadWidth > hsvSmallRoadWidth3) {
            gridLayoutRoachRoad.scrollTo(0, 0);
            gridLayoutRoachRoad.scrollTo(gridLayoutRoachRoadWidth - hsvSmallRoadWidth3, 0);
        } else {
            gridLayoutRoachRoad.scrollTo(0, 0);
        }
    }

    public String updateGoodRoad(Context mContext, String data) {
        if (TextUtils.isEmpty(data)) {
            return "";
        }
        String replaceData = data.replace("9", "").replace("10", "").replace("11", "").replace("12", "");
        String replaceData2 = replaceData.replace("2", "1").replace("3", "1").replace("4", "1").replace("6", "5").replace("7", "5").replace("8", "5");
        String[] split = replaceData2.split("#");
        List<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (!TextUtils.isEmpty(s)) {
                dataList.add(Integer.parseInt(s));
            }
        }
        int lastIndexNumber0 = 0;
        int lastIndexNumber1 = 0;
        int lastIndexNumber2 = 0;
        int lastIndexNumber3 = 0;
        int lastIndexNumber4 = 0;
        int lastIndexNumber5 = 0;
        int lastIndexNumber6 = 0;
        int lastIndexNumber7 = 0;
        int lastIndexNumber8 = 0;
        if (dataList.size() >= 4) {
            int lasIndex = dataList.size() - 1;
            lastIndexNumber0 = dataList.get(lasIndex);
            lastIndexNumber1 = dataList.get(lasIndex - 1);
            lastIndexNumber2 = dataList.get(lasIndex - 2);
            lastIndexNumber3 = dataList.get(lasIndex - 3);
            if (dataList.size() > 4) {
                lastIndexNumber4 = dataList.get(lasIndex - 4);
            }
            if (dataList.size() > 5) {
                lastIndexNumber5 = dataList.get(lasIndex - 5);
            }
            if (dataList.size() > 6) {
                lastIndexNumber6 = dataList.get(lasIndex - 6);
            }
            if (dataList.size() > 7) {
                lastIndexNumber7 = dataList.get(lasIndex - 7);
            }
            if (dataList.size() > 8) {
                lastIndexNumber8 = dataList.get(lasIndex - 8);
            }
            if (dataList.size() == 4) {
                if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber0 == lastIndexNumber3) {
                    if (lastIndexNumber0 == 1) {
                        return mContext.getString(R.string.GoodRoad_1);
                    } else {
                        return mContext.getString(R.string.GoodRoad_2);
                    }
                } else if (lastIndexNumber0 != lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber1 == lastIndexNumber3) {
                    return mContext.getString(R.string.GoodRoad_5);
                } else {
                    return "";
                }
            } else if (dataList.size() == 6) {
                if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber0 == lastIndexNumber3) {
                    if (lastIndexNumber0 == 1) {
                        return mContext.getString(R.string.GoodRoad_1);
                    } else {
                        return mContext.getString(R.string.GoodRoad_2);
                    }
                } else if (lastIndexNumber0 != lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber1 == lastIndexNumber3 && lastIndexNumber3 != lastIndexNumber4) {
                    return mContext.getString(R.string.GoodRoad_5);
                } else if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber3 == lastIndexNumber4 && lastIndexNumber0 == lastIndexNumber3 && lastIndexNumber2 == lastIndexNumber5 && lastIndexNumber0 != lastIndexNumber2) {
                    if (lastIndexNumber0 == 1) {
                        return mContext.getString(R.string.GoodRoad_3);
                    } else {
                        return mContext.getString(R.string.GoodRoad_4);
                    }
                } else {
                    return "";
                }
            } else if (dataList.size() == 8) {
                if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber0 == lastIndexNumber3) {
                    if (lastIndexNumber0 == 1) {
                        return mContext.getString(R.string.GoodRoad_1);
                    } else {
                        return mContext.getString(R.string.GoodRoad_2);
                    }
                } else if (lastIndexNumber0 != lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber1 == lastIndexNumber3 && lastIndexNumber3 != lastIndexNumber4) {
                    return mContext.getString(R.string.GoodRoad_5);
                } else if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber3 == lastIndexNumber4 && lastIndexNumber0 == lastIndexNumber3 && lastIndexNumber2 == lastIndexNumber5 && lastIndexNumber0 != lastIndexNumber2 && lastIndexNumber5 != lastIndexNumber6) {
                    if (lastIndexNumber0 == 1) {
                        return mContext.getString(R.string.GoodRoad_3);
                    } else {
                        return mContext.getString(R.string.GoodRoad_4);
                    }
                } else if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber2 == lastIndexNumber3 && lastIndexNumber4 == lastIndexNumber5 && lastIndexNumber6 == lastIndexNumber7 &&
                        lastIndexNumber0 == lastIndexNumber4 && lastIndexNumber2 == lastIndexNumber7 && lastIndexNumber0 != lastIndexNumber2) {
                    return mContext.getString(R.string.GoodRoad_6);
                } else {
                    return "";
                }
            } else {
                if (dataList.size() == 5) {
                    if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber0 == lastIndexNumber3) {
                        if (lastIndexNumber0 == 1) {
                            return mContext.getString(R.string.GoodRoad_1);
                        } else {
                            return mContext.getString(R.string.GoodRoad_2);
                        }
                    } else if (lastIndexNumber0 != lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber1 == lastIndexNumber3 && lastIndexNumber3 != lastIndexNumber4) {
                        return mContext.getString(R.string.GoodRoad_5);
                    } else {
                        return "";
                    }
                } else if (dataList.size() == 7) {
                    if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber0 == lastIndexNumber3) {
                        if (lastIndexNumber0 == 1) {
                            return mContext.getString(R.string.GoodRoad_1);
                        } else {
                            return mContext.getString(R.string.GoodRoad_2);
                        }
                    } else if (lastIndexNumber0 != lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber1 == lastIndexNumber3 && lastIndexNumber3 != lastIndexNumber4) {
                        return mContext.getString(R.string.GoodRoad_5);
                    } else if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber3 == lastIndexNumber4 && lastIndexNumber0 == lastIndexNumber3 && lastIndexNumber2 == lastIndexNumber5 && lastIndexNumber0 != lastIndexNumber2 && lastIndexNumber5 != lastIndexNumber6) {
                        if (lastIndexNumber0 == 1) {
                            return mContext.getString(R.string.GoodRoad_3);
                        } else {
                            return mContext.getString(R.string.GoodRoad_4);
                        }
                    } else {
                        return "";
                    }
                } else {
                    if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber0 == lastIndexNumber3) {
                        if (lastIndexNumber0 == 1) {
                            return mContext.getString(R.string.GoodRoad_1);
                        } else {
                            return mContext.getString(R.string.GoodRoad_2);
                        }
                    } else if (lastIndexNumber0 != lastIndexNumber1 && lastIndexNumber0 == lastIndexNumber2 && lastIndexNumber1 == lastIndexNumber3 && lastIndexNumber3 != lastIndexNumber4) {
                        return mContext.getString(R.string.GoodRoad_5);
                    } else if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber3 == lastIndexNumber4 && lastIndexNumber0 == lastIndexNumber3 && lastIndexNumber2 == lastIndexNumber5 && lastIndexNumber0 != lastIndexNumber2 && lastIndexNumber5 != lastIndexNumber6) {
                        if (lastIndexNumber0 == 1) {
                            return mContext.getString(R.string.GoodRoad_3);
                        } else {
                            return mContext.getString(R.string.GoodRoad_4);
                        }
                    } else if (lastIndexNumber0 == lastIndexNumber1 && lastIndexNumber2 == lastIndexNumber3 && lastIndexNumber4 == lastIndexNumber5 && lastIndexNumber6 == lastIndexNumber7 &&
                            lastIndexNumber0 == lastIndexNumber4 && lastIndexNumber2 == lastIndexNumber7 && lastIndexNumber0 != lastIndexNumber2 && lastIndexNumber7 != lastIndexNumber8) {
                        return mContext.getString(R.string.GoodRoad_6);
                    } else {
                        return "";
                    }
                }
            }
        }
        return "";
    }

    public void updateAskRoad(Context mContext, float density, Baccarat baccarat, GridLayout gridLayoutHeadRoad, final GridLayout gridLayoutBigRoad, final GridLayout gridLayoutBigEyesRoad
            , final GridLayout gridLayoutSmallEyesRoad, final GridLayout gridLayoutRoachRoad
            , String data, boolean isBigShow, boolean isAddData, final View gridLayoutBigRoadParent, final View hsv_small_road1, final View hsv_small_road2, final View hsv_small_road3, final boolean isNeedInit) {
        try {
            int[][] road;
            if (AppTool.getAppLanguage(mContext).equals("zh")) {
                banker_road = "庄";
                player_road = "闲";
                tie_road = "和";
            } else {
                banker_road = "B";
                player_road = "P";
                tie_road = "T";
            }
            String bigRoadOld = data;
            List<String> dataList = new ArrayList<>();
            String[] split = bigRoadOld.split("#");
            int length = 41;
            if (isAddData) {
                length = 42;
            }
            if (split.length > 41) {
                int firstIndex = length;
                for (int i = 0; i < length; i++) {
                    firstIndex--;
                    int index = (split.length - 1) - firstIndex;
                    dataList.add(split[index]);
                }
                bigRoadOld = "";
                for (int i = 0; i < dataList.size(); i++) {
                    bigRoadOld += dataList.get(i) + "#";
                }
            }
            ShowHeadRoad(bigRoadOld, gridLayoutHeadRoad, mContext, density, banker_road, player_road, tie_road);//头路
            //    road = ShowBaccaratBigRoad("1#5#5#5#1#12#3#7#1#5#6#1#5#1#1#9#9#1#5#1#1#1#1#5#5#3#5#5#1#1#1#5#5#1#5#2#5#1#1#1#5#1#5#5#6#7#5#1#1#5#1#6#5#5#9#", mContext, gridLayoutBigRoad, 6, density, 1,1);
            road = ShowBaccaratBigRoad(bigRoadOld, mContext, gridLayoutBigRoad, 6, density, 1, 1);

            String smallLuzi = baccarat.GetSmallRoad(road, 1, 2, 2, 1);
            if (smallLuzi == null || smallLuzi.length() <= 0) {
                clearChildView(gridLayoutBigEyesRoad);
            } else
                ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutBigEyesRoad, 6, density, 2, 1);

            smallLuzi = baccarat.GetSmallRoad(road, 3, 4, 4, 3);
            if (smallLuzi == null || smallLuzi.length() <= 0) {
                clearChildView(gridLayoutSmallEyesRoad);
            } else
                ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutSmallEyesRoad, 6, density, 3, 1);

            smallLuzi = baccarat.GetSmallRoad(road, 2, 3, 3, 2);
            if (smallLuzi == null || smallLuzi.length() <= 0) {
                clearChildView(gridLayoutRoachRoad);
            } else
                ShowBaccaratBigRoad(smallLuzi, mContext, gridLayoutRoachRoad, 6, density, 4, 1);

            if (gridLayoutBigRoadParent != null) {
                gridLayoutBigRoadParent.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isNeedInit) {
                            scrollRoad(gridLayoutBigRoadParent, gridLayoutBigRoad, hsv_small_road1, gridLayoutBigEyesRoad, hsv_small_road2, gridLayoutSmallEyesRoad, hsv_small_road3, gridLayoutRoachRoad);
                        }
                    }
                });
            }
            //更新局数
//            updateGameNumber(baccarat, tv_shoe, tv_total, tv_banker, tv_player, tv_tie, tv_bp, tv_pp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateDragonTigerGameNumber(DragonTiger dragonTiger, TextView tv_shoe, TextView tv_total, TextView tv_banker, TextView tv_player, TextView tv_tie, TextView tv_bp, TextView tv_pp) {
        if (dragonTiger.getStatus() == 0) {
            tv_shoe.setText("0 - 0");
            tv_total.setText("0");
            tv_banker.setText("0");
            tv_player.setText("0");
            tv_tie.setText("0");
//            tv_bp.setText("0");
//            tv_pp.setText("0");
        } else {
            dragonTiger.setDragonTigerCount(dragonTiger.getBigRoad());
            tv_shoe.setText("" + dragonTiger.getShoeNumber() + " - " + dragonTiger.getGameNumber());
            tv_total.setText("" + (dragonTiger.getTotalDragon() + dragonTiger.getTotalTiger() + dragonTiger.getTotalTie()));
            tv_banker.setText("" + dragonTiger.getTotalDragon());
            tv_player.setText("" + dragonTiger.getTotalTiger());
            tv_tie.setText("" + dragonTiger.getTotalTie());
//            tv_bp.setText(""+dragonTiger.getTotalAll());
//            tv_pp.setText(""+dragonTiger.getTotalAll());
        }

    }

    public void updateGameNumber(Baccarat baccarat, TextView tv_shoe, TextView tv_total, TextView tv_banker, TextView tv_player, TextView tv_tie, TextView tv_bp, TextView tv_pp) {
        if (baccarat.getStatus() == 0) {
            tv_shoe.setText("0 - 0");
            tv_total.setText("0");
            tv_banker.setText("0");
            tv_player.setText("0");
            tv_tie.setText("0");
            tv_bp.setText("0");
            tv_pp.setText("0");
        } else {
            baccarat.setBankerPlayerCount(baccarat.getBigRoad());
            tv_shoe.setText("" + baccarat.getShoeNumber() + " - " + baccarat.getGameNumber());
            LogUtil.d("tv_baccarat_shoe_number3", baccarat.getShoeNumber() + " - " + baccarat.getGameNumber());
            getMethodName();
            tv_total.setText("" + (baccarat.getTotalBanker() + baccarat.getTotalPlayer() + baccarat.getTotalTie()));
            tv_banker.setText("" + baccarat.getTotalBanker());
            tv_player.setText("" + baccarat.getTotalPlayer());
            tv_tie.setText("" + baccarat.getTotalTie());
            tv_bp.setText("" + baccarat.getTotalBankerPair());
            tv_pp.setText("" + baccarat.getTotalPlayerPair());
        }

    }

    private void getMethodName() {
        StackTraceElement[] temp = Thread.currentThread().getStackTrace();
        if (temp.length > 3) {
            for (int i = 3; i < (temp.length > 7 ? 7 : temp.length); i++) {
                StackTraceElement a = temp[i];
                LogUtil.d("tv_baccarat_shoe_number3", a.getMethodName());
            }

        }
    }

    public void updateRoad(Sicbo sicbo, GridLayout gridLayoutBigSmallRoad, GridLayout gridLayoutEvenOddRoad
            , TextView tv_sicbo_number, TextView tv_even, TextView tv_small, TextView tv_waidic, TextView tv_big, TextView tv_odd, Context mContext, float density) {
        if (sicbo.getStatus() == 0)//清除路子信息
        {
            clearChildView(gridLayoutBigSmallRoad);
            clearChildView(gridLayoutEvenOddRoad);

            tv_sicbo_number.setText("0");
            tv_even.setText("0");
            tv_small.setText("0");
            tv_waidic.setText("0");
            tv_big.setText("0");
            tv_odd.setText("0");
            //需要显示关桌
        } else {//显示路子
            if (sicbo.getRoad() != null && !sicbo.getRoad().equals(sicbo.getRoadOld())) {
                //     Log.i(WebSiteUrl.Tag,"updateRoad(),TableID="+sicbo.getTableName()+",Luzi roads="+sicbo.getRoadOld()+ ",Road="+sicbo.getRoad());
                sicbo.setRoadOld(sicbo.getRoad());
                //路子只显示19列
                String road = getRoadShow(sicbo.getRoad(), 6, 5);
                //  Log.i(WebSiteUrl.Tag,"updateRoad(),TableID="+sicbo.getTableName()+",Luzi roads="+road);
                //         String road = "13-346-0#14-455-0#6-123-0#13-166-0#13-346-0#13-166-0#10-145-0#8-125-0#6-123-0#6-114-0#13-256-0#12-336-0#11-335-0#14-455-0#11-344-0#18-666-1#3-111-1#9-135-0#6-123-0#10-235-0#4-112-0#8-125-0#10-145-0#10-136-0#12-156-0#8-125-0#7-223-0#10-235-0#7-124-0#6-123-0#10-145-0#7-223-0#9-126-0#9-234-0#9-234-0#10-244-0#8-116-0#15-456-0#11-344-0#7-223-0#13-445-0#12-345-0#14-455-0#14-455-0#14-455-0#14-455-0#14-455-0#14-455-0#14-455-0#";
                if (!"".endsWith(road) && road != null) {
                    ShowBaccaratBigRoad(road, mContext, gridLayoutBigSmallRoad, 6, density, 5, 3);
                    ShowBaccaratBigRoad(road, mContext, gridLayoutEvenOddRoad, 6, density, 6, 3);
                }
                //      String road = getRoadShow(sicbo.getRoad(),6,6);


                //更新局数
                updateGameNumber(getSicbo01(), tv_sicbo_number, tv_even, tv_small, tv_waidic, tv_big, tv_odd);

            }

        }
    }

    public void updateSicboBigRoad(Sicbo sicbo, GridLayout gridLayoutBigSmallRoad, Context mContext) {

        if (sicbo.getRoad() != null && !sicbo.getRoad().equals(sicbo.getRoadOld())) {
            //     Log.i(WebSiteUrl.Tag,"updateRoad(),TableID="+sicbo.getTableName()+",Luzi roads="+sicbo.getRoadOld()+ ",Road="+sicbo.getRoad());
            sicbo.setRoadOld(sicbo.getRoad());
            //路子只显示19列
            String road = getRoadShow(sicbo.getRoad(), 6, 5);
            //  Log.i(WebSiteUrl.Tag,"updateRoad(),TableID="+sicbo.getTableName()+",Luzi roads="+road);
            //         String road = "13-346-0#14-455-0#6-123-0#13-166-0#13-346-0#13-166-0#10-145-0#8-125-0#6-123-0#6-114-0#13-256-0#12-336-0#11-335-0#14-455-0#11-344-0#18-666-1#3-111-1#9-135-0#6-123-0#10-235-0#4-112-0#8-125-0#10-145-0#10-136-0#12-156-0#8-125-0#7-223-0#10-235-0#7-124-0#6-123-0#10-145-0#7-223-0#9-126-0#9-234-0#9-234-0#10-244-0#8-116-0#15-456-0#11-344-0#7-223-0#13-445-0#12-345-0#14-455-0#14-455-0#14-455-0#14-455-0#14-455-0#14-455-0#14-455-0#";
            if (!"".endsWith(road) && road != null) {
                ShowBaccaratBigRoad(road, mContext, gridLayoutBigSmallRoad, 6, ScreenUtil.getDisplayMetrics(mContext).density, 5, 3);

            }

        }
    }

    public void updateGameNumber(Sicbo sicbo, TextView tv_sicbo_number, TextView tv_even, TextView tv_small, TextView tv_waidic, TextView tv_big, TextView tv_odd) {
        if (sicbo.getStatus() == 0) {
            //    tv_sicbo_number.setText("0");
            tv_even.setText("0");
            tv_small.setText("0");
            tv_waidic.setText("0");
            tv_big.setText("0");
            tv_odd.setText("0");
        } else {
            sicbo.getTotal(sicbo.getRoad());
            tv_sicbo_number.setText(sicbo.getGameNumber());
            tv_even.setText("" + sicbo.getEven());
            tv_odd.setText("" + sicbo.getOdd());
            tv_big.setText("" + sicbo.getBig());
            tv_small.setText("" + sicbo.getSmall());
            tv_waidic.setText("" + sicbo.getWaidic());
        }

    }

    public String getRoadShow(String luzi, int iColCount, int type) {
        int col = 0;//列数
        int row = 0;//行数
        String road = "";
        try {

            if (luzi == null || luzi.length() <= 0 || "null".equals(luzi) || "null#".equals(luzi)) {
                return "";
            }
            String luziInfo[] = luzi.split("\\#");
            if (luziInfo.length <= 0 && luziInfo.length > 100) {//数据格式不对
                return "";
            }
            //   Log.i("855play"," luzi length "+luziInfo.length);
            int tieIndex = 0;
            int bankerIndex = 0;
            int playerIndex = 0;
            int index = luziInfo.length;
            int lastRow = 0;
            int lastCol = 0;
            int point = 0;//点数
            int iStart = 0;

            String flag[] = new String[1000];
            for (int i = 0; i < 1000; i++) {
                flag[i] = "0";
            }


            boolean bJumpBanker = false;
            int jumpBankerIndex = 0;

            boolean bJumpPlayer = false;
            int jumpPlayerIndex = 0;
            //     Log.i(WebSiteUrl.Tag,"getRoadShow = index = "+index );
            int n = luziInfo.length;
            //50局开始就差不多了
            for (int i = n - 1; i >= n - 65; i--) {
                if (lastCol == 19) {
                    iStart = i;
                    break;
                }

                String luziInfoDetail[] = luziInfo[i].split("\\-");//路子相信信息9-8-7
                int res = Integer.parseInt(luziInfoDetail[0]);


                point = Integer.parseInt(luziInfoDetail[0]) + Integer.parseInt(luziInfoDetail[1]) + Integer.parseInt(luziInfoDetail[2]);
                //    point = Integer.parseInt(luziInfoDetail[0]) ;
                if (type == 5) {
                    if (point > 10) {
                        res = 1;
                    } else
                        res = 5;
                } else {
                    if (point % 2 == 0) {
                        res = 5;
                    } else
                        res = 1;
                }


                //     Log.i("855play"," luzi data "+i + " -- " +res);
                if (res == 9 || res == 10 || res == 11 || res == 12) {//和不显示
                    if (tieIndex > 0) {//要移掉显示和的数字
                        //移除最后一层的数字跟斜杠
                        //	removeLastView(framelayout);

                    }
                    tieIndex++;
                    ///////////显示和的路子
                    if (bankerIndex == 0 && playerIndex == 0) {//前面一个显示都还没有的特殊情况

                    } else {//在最后一层上面添加和的信息

                    }
                } else if (res == 1 || res == 2 || res == 3 || res == 4) {//庄，要先判断前面一个是什么才知道怎么显示在哪一列
                    if (bankerIndex == 0) {//和前面一个不一样
                        if (bankerIndex == 0 && playerIndex == 0) {//填充到第一个格子，一个路子都还么有填充的情况
                            /////////下面又分前面一个是不是和的情况

                            ///////////还要判断这个格子是否被填充了
                            int insertIndex = col * iColCount + row;

                            //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                            if ("0".equals(flag[insertIndex])) {//允许插入
                                //插入图片

                                flag[insertIndex] = "1";

                                lastRow = row;
                                lastCol = col;
                            }


                        } else {//换列

                            col++;
                            row = 0;
                            /////////////判断是否有插入了
                            int insertIndex = (col + jumpBankerIndex) * iColCount + row;

                            //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                            if ("0".equals(flag[insertIndex])) {//允许插入，此种情况是顶部第一行被插入了
                                //插入图片
                                flag[insertIndex] = "1";
                                //	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);

                                lastRow = row;
                                lastCol = col + jumpBankerIndex;
                            }
                            ////////如果被插入了，转弯
                            else {
                                bJumpBanker = true;
                                jumpBankerIndex++;
                                col++;
                                insertIndex = col * iColCount + row;
                                flag[insertIndex] = "1";
                                //   show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);

                                lastRow = row;
                                lastCol = col;

                            }


                        }
                    } else {//继续庄，不换列,换行

                        if (bJumpBanker == true) {

                            row++;
                            int insertIndex = (col + jumpBankerIndex) * iColCount + row;

                            //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                            if ("0".equals(flag[insertIndex])) {//允许插入
                                //插入图片
                                flag[insertIndex] = "1";
                                //	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);

                                lastRow = row;
                                lastCol = col + jumpBankerIndex;
                            } else {
                                jumpBankerIndex++;
                                insertIndex = (col + jumpBankerIndex) * iColCount + row;
                                flag[insertIndex] = "1";
                                //     show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpBankerIndex),row, xSpace, ySpace, picWidth, picHeight);

                                lastRow = row;
                                lastCol = col + jumpBankerIndex;
                            }

                        } else {
                            row++;
                            //	arrayRoad[row][col] = 2;

                            if (row > iColCount - 1) {

                                row = iColCount - 1;
                                ///////需要换列
                                jumpBankerIndex++;
                            }


                            int insertIndex = (col + jumpBankerIndex) * iColCount + row;
                            int topIndex = (col + jumpBankerIndex) * iColCount;
                            int lastIndex = (col + jumpBankerIndex) * iColCount + (row - 1);
                            if ("0".equals(flag[insertIndex])) {//允许插入
                                //插入图片
                                if (row > 0 && "0".equals(flag[topIndex]) && "1".equals(flag[lastIndex])) {
                                    jumpBankerIndex++;
                                    row--;
                                    insertIndex = (col + jumpBankerIndex) * iColCount + row;
                                    flag[insertIndex] = "1";

                                    lastRow = row;
                                    lastCol = col + jumpBankerIndex;
                                } else {
                                    flag[insertIndex] = "1";

                                    lastRow = row;
                                    lastCol = col + jumpBankerIndex;
                                }

                            } else {
                                jumpBankerIndex++;
                                row--;
                                insertIndex = (col + jumpBankerIndex) * iColCount + row;
                                flag[insertIndex] = "1";

                                lastRow = row;
                                lastCol = col + jumpBankerIndex;
                            }

                        }


                    }
                    bankerIndex = res;
                    playerIndex = 0;
                    tieIndex = 0;
                    jumpPlayerIndex = 0;
                    bJumpPlayer = false;
                } else if (res == 5 || res == 6 || res == 7 || res == 8) {//闲,，要先判断前面一个是什么才知道怎么显示在哪一列
                    if (playerIndex == 0) {//和前面一个不一样
                        if (bankerIndex == 0 && playerIndex == 0) {//填充到第一个格子，一个路子都还么有填充的情况
                            /////////下面又分前面一个是不是和的情况

                            ///////////还要判断这个格子是否被填充了
                            int insertIndex = col * iColCount + row;
                            if ("0".equals(flag[insertIndex])) {//允许插入
                                //插入图片
                                flag[insertIndex] = "1";
                                //  	show_Pic_Luzi(res,framelayout,ctx,x,y,0,col,row, xSpace, ySpace, picWidth, picHeight);

                                lastRow = row;
                                lastCol = col;
                            }

                            //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                        } else {//换列

                            col++;
                            row = 0;
                            /////////////判断是否有插入了
                            int insertIndex = (col + jumpPlayerIndex) * iColCount + row;

                            //	Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                            if ("0".equals(flag[insertIndex])) {//允许插入
                                //插入图片
                                flag[insertIndex] = "1";
                                //  	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);

                                lastRow = row;
                                lastCol = col + jumpPlayerIndex;
                            }
                            ////////如果被插入了，转弯
                            else {
                                bJumpPlayer = true;
                                jumpPlayerIndex++;
                                col++;
                                insertIndex = col * iColCount + row;
                                flag[insertIndex] = "1";

                                lastRow = row;
                                lastCol = col;
                            }


                        }
                    } else {//继续闲，不换列,换行

                        if (bJumpPlayer == true) {
                            row++;
                            int insertIndex = (col + jumpPlayerIndex) * iColCount + row;

                            //		Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+row+",col="+col);
                            if ("0".equals(flag[insertIndex])) {
                                flag[insertIndex] = "1";
                                //show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);

                                lastRow = row;
                                lastCol = col + jumpPlayerIndex;
                            } else {
                                jumpPlayerIndex++;
                                insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                                flag[insertIndex] = "1";

                                lastRow = row;
                                lastCol = col + jumpPlayerIndex;
                            }

                        } else {
                            row++;
                            //arrayRoad[row][col] = 2;

                            if (row > iColCount - 1) {
                                row = iColCount - 1;
                                ///////需要换列
                                jumpPlayerIndex++;
                            }
                            //  Log.i(WebSiteUrl.Tag,"showBigRoad ,res = "+res +",row="+(row+jumpPlayerIndex)+",col="+col);
                            int insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                            int lastIndex = (col + jumpPlayerIndex) * iColCount + (row - 1);
                            int topIndex = (col + jumpPlayerIndex) * iColCount;
                            if ("0".equals(flag[insertIndex])) {//允许插入
                                //插入图片
                                if (row > 0 && "0".equals(flag[topIndex]) && "1".equals(flag[lastIndex])) {
                                    jumpPlayerIndex++;
                                    row--;//转弯的话，行数不加
                                    insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                                    flag[insertIndex] = "1";
                                    lastRow = row;
                                    lastCol = col + jumpPlayerIndex;
                                } else {
                                    flag[insertIndex] = "1";
                                    //	show_Pic_Luzi(res,framelayout,ctx,x,y,0,(col+jumpPlayerIndex),row, xSpace, ySpace, picWidth, picHeight);

                                    lastRow = row;
                                    lastCol = col + jumpPlayerIndex;
                                }

                            } else {
                                jumpPlayerIndex++;
                                row--;//转弯的话，行数不加
                                insertIndex = (col + jumpPlayerIndex) * iColCount + row;
                                flag[insertIndex] = "1";
                                lastRow = row;
                                lastCol = col + jumpPlayerIndex;
                            }

                        }


                    }
                    playerIndex = res;
                    bankerIndex = 0;
                    tieIndex = 0;
                    jumpBankerIndex = 0;
                    bJumpBanker = false;
                }
            }

            for (int j = iStart + 2; j < luziInfo.length; j++) {
                road += luziInfo[j] + "#";
            }
            //     Log.i(WebSiteUrl.Tag,"getRoadShow="+",col="+iStart+",Luzi="+road);

        } catch (ArrayIndexOutOfBoundsException e) {
            Log.i(WebSiteUrl.Tag, "ArrayIndexOutOfBoundsException,row=" + row + ",col" + col + ",Luzi=" + luzi);
        }

        return road;
    }

    public int getBetMoney(int chooseChip, int minLimit, int maxLimit, int clickCount, int alreadyBet, int totalbet, Context ctx, ComponentName componentName) {
        int betMoney = 0;
        int betChip = chooseChip;
        Log.i(WebSiteUrl.Tag, "minLimit=" + minLimit + ",maxLimit=" + maxLimit + ",clickCount=" + clickCount + ",alreadyBet=" + alreadyBet + ",betChip=" + betChip + ",totalbet=" + totalbet);
        if (clickCount == 1)
            betMoney = betChip * clickCount + alreadyBet;
        else if (clickCount > 1) {
            //   betMoney = betChip * clickCount + alreadyBet;
            if (betChip < minLimit) {
                betMoney = minLimit + betChip * (clickCount - 1) + alreadyBet;
            } else
                betMoney = betChip * clickCount + alreadyBet;
        }

        if (betMoney > maxLimit) {
            betMoney = maxLimit;
        }
        if (betMoney < minLimit) {
            betMoney = minLimit;
        }

        if (betMoney > totalbet)
            betMoney = 0;

        //判断是否所有下注过的金额，有没有超过最大值
//        int allBetMoney = 0;
//         switch(tableId)
//         {
//             case 1:
//             case 2:
//             case 3:
//             case 71:
//                 allBetMoney = getBaccarat(tableId).getBaccaratBetInformation().getAllBetMoney();
//                 break;
//             case 31:
//                 allBetMoney = sicbo01.getSicboBetInformation().getAllBetMoney();
//                 break;
//             case 21:
//                 allBetMoney = roulette01.getRouletteBetInformation().getAllBetMoney();
//                 break;
//             case 5:
//                 allBetMoney = dragonTiger01.getDragonTigerBetInformation().getAllBetMoney();
//                 break;
//
//         }

        if (betMoney > 0 && alreadyBet != betMoney) {

            startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 9, componentName, ctx, getFrontVolume());
        }
        Log.i(WebSiteUrl.Tag, "minLimit=" + minLimit + ",maxLimit=" + maxLimit + ",clickCount=" + clickCount + ",alreadyBet=" + alreadyBet + ",betMoney=" + betMoney + ",totalbet=" + totalbet);
        return betMoney;
    }

    public void closeMuzicService(Context ctx, Class<?> cls) {
        try {
            Intent intent = new Intent(ctx, cls);
            //停止服务
            ctx.stopService(intent);
            intent = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //	Log.i(WebSiteUrl.Tag, "closeMuzicService="+ctx);
    }

    @TargetApi(26)
    public void startFrontMuzicService(String action, int index, ComponentName component, Context ctx, int volume) {
        try {
            BaseActivity activity = (BaseActivity) ctx;
            if (WidgetUtil.isRunBackground(activity)) {
                return;
            }
            Intent mIntent = null;

            // 	Log.i(WebSiteUrl.Tag, "startFrontMuzicService="+action);
            mIntent = new Intent(action);
            mIntent.putExtra("muzic_index", index);
            mIntent.putExtra("volume", volume);
            mIntent.setComponent(component);
            ctx.startService(mIntent);
            mIntent = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(26)
    public void startBackgroudMuzicService(int index, ComponentName component, Context ctx, int volume) {
        try {
            BaseActivity activity = (BaseActivity) ctx;
            if (WidgetUtil.isRunBackground(activity)) {
                return;
            }
            Intent mIntent = null;
            String strMuzic = "";
            switch (index) {
                case 1:
                    strMuzic = BackgroudMuzicService.PLAY_SONG1;
                    break;
                case 2:
                    strMuzic = BackgroudMuzicService.PLAY_SONG2;
                    break;
                case 3:
                    strMuzic = BackgroudMuzicService.PLAY_SONG3;
                    break;
            }
            mIntent = new Intent(strMuzic);
            mIntent.putExtra("volume", volume);
            mIntent.setComponent(component);
            ctx.startService(mIntent);
            mIntent = null;
            ctx = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(26)
    public void changeMuzicVolumeService(ComponentName component, Context ctx, int volume, String action) {
        try {
            BaseActivity activity = (BaseActivity) ctx;
            if (WidgetUtil.isRunBackground(activity)) {
                return;
            }
            Intent mIntent = null;
            mIntent = new Intent(action);
            mIntent.putExtra("volume", volume);
            mIntent.setComponent(component);
            ctx.startService(mIntent);
            mIntent = null;
            ctx = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String covertLimit(int limitValue) {
        String resValue = "";
        int iValue = 0;
        if (limitValue >= 10000) {
            iValue = limitValue / 1000;
            resValue = "" + iValue + "k";
        } else {
            resValue = "" + limitValue;
        }
        return resValue;
    }

    public String covertBalance(int limitValue) {
        String resValue = "";
        int iValue = 0;
        if (limitValue >= 10000) {
            iValue = limitValue / 1000;
            double end = limitValue % 1000;
            end = end / 1000;
            String endStr = end + "";
            if (endStr.length() > 4) {
                endStr = endStr.substring(0, 4);
            }
            end = Double.parseDouble(endStr);
            resValue = "" + (iValue + end) + "k";
        } else {
            resValue = "" + limitValue;
        }
        return resValue;
    }

    public String covertWinLose(double limitValue) {
        int value = (int) limitValue;
        double v = limitValue - value;
        String resValue = "";
        int iValue = 0;
        if (value >= 10000) {
            iValue = value / 1000;
            resValue = "" + iValue + "k";
        } else {
            resValue = "" + limitValue;
        }
        if (v > 0 && value >= 10000) {
            String format = String.format("%.2f", v);
            String substring = format.substring(1);
            resValue += substring;
        }

        return resValue;
    }

    public int changePokerPoint(int poker) {
        int point = poker;
        float fPoker = point / (float) 13;
        //   NSLog(@"fPoker %f",fPoker);
        if (fPoker > 1 && fPoker <= 2) {
            point = point - 13;
        } else if (fPoker > 2 && fPoker <= 3) {
            point = point - 26;
        } else if (fPoker > 3 && fPoker <= 4) {
            point = point - 39;
        }
        return point;
    }

    public void showPointBanker(int b1, int b2, int b3, TextView tv_banker, String banker) {
        tv_banker.setVisibility(View.VISIBLE);
        int banker1 = changePokerPoint(b1);
        int banker2 = changePokerPoint(b2);
        int banker3 = changePokerPoint(b3);
        if (banker1 == 0 && banker2 == 0 && banker3 == 0) {
            tv_banker.setText(banker + "0");
        } else if (banker1 > 0 && banker2 == 0 && banker3 == 0) {
            if (banker1 > 9) {
                tv_banker.setText(banker + "0");
            } else {
                tv_banker.setText(banker + "" + banker1);
            }
        } else if (banker1 > 0 && banker2 > 0 && banker3 == 0) {
            if (banker1 >= 10) {
                banker1 = 0;
            }
            if (banker2 >= 10) {
                banker2 = 0;
            }
            if (banker1 + banker2 >= 20) {
                tv_banker.setText(banker + "0");
            } else if (banker1 + banker2 >= 10) {
                tv_banker.setText(banker + "" + (banker1 + banker2 - 10));
            } else {
                tv_banker.setText(banker + "" + (banker1 + banker2));
            }
        } else {
            if (banker1 >= 10) {
                banker1 = 0;
            }
            if (banker2 >= 10) {
                banker2 = 0;
            }
            if (banker3 >= 10) {
                banker3 = 0;
            }
            if (banker1 + banker2 + banker3 >= 30) {
                tv_banker.setText(banker + "0");
            } else if (banker1 + banker2 + banker3 >= 20) {
                tv_banker.setText(banker + "" + (banker1 + banker2 + banker3 - 20));
            } else if (banker1 + banker2 + banker3 >= 10) {
                tv_banker.setText(banker + "" + (banker1 + banker2 + banker3 - 10));
            } else {
                tv_banker.setText(banker + "" + (banker1 + banker2 + banker3));
            }
        }


    }

    public void showPointPlayer(int p1, int p2, int p3, TextView tv_player, String player) {
        int player1 = changePokerPoint(p1);
        int player2 = changePokerPoint(p2);
        int player3 = changePokerPoint(p3);

        if (player1 == 0 && player2 == 0 && player3 == 0) {
            tv_player.setText(player + "0");
        } else if (player1 > 0 && player2 == 0 && player3 == 0) {

            if (player1 > 9) {
                tv_player.setText(player + "0");
            } else {
                tv_player.setText(player + "" + player1);
            }
        } else if (player1 > 0 && player2 > 0 && player3 == 0) {
            if (player1 >= 10) {
                player1 = 0;
            }
            if (player2 >= 10) {
                player2 = 0;
            }

            if (player1 + player2 >= 20) {
                tv_player.setText(player + "0");
            } else if (player1 + player2 >= 10) {
                tv_player.setText(player + "" + (player1 + player2 - 10));
            } else {
                tv_player.setText(player + "" + (player1 + player2));
            }
        } else {
            if (player1 >= 10) {
                player1 = 0;
            }
            if (player2 >= 10) {
                player2 = 0;
            }
            if (player3 >= 10) {
                player3 = 0;
            }
            if (player1 + player2 + player3 >= 30) {
                tv_player.setText(player + "0");
            } else if (player1 + player2 + player3 >= 20) {
                tv_player.setText(player + "" + (player1 + player2 + player3 - 20));
            } else if (player1 + player2 + player3 >= 10) {
                tv_player.setText(player + "" + (player1 + player2 + player3 - 10));
            } else {
                tv_player.setText(player + "" + (player1 + player2 + player3));
            }
        }
    }

    public void showPoint(int p1, int p2, int p3, int b1, int b2, int b3, TextView tv_banker, TextView tv_player, String banker, String player) {
        int banker1 = changePokerPoint(b1);
        int banker2 = changePokerPoint(b2);
        int banker3 = changePokerPoint(b3);
        int player1 = changePokerPoint(p1);
        int player2 = changePokerPoint(p2);
        int player3 = changePokerPoint(p3);

        if (banker1 == 0 && banker2 == 0 && banker3 == 0) {
            tv_banker.setText(banker + "0");
        } else if (banker1 > 0 && banker2 == 0 && banker3 == 0) {
            if (banker1 > 9) {
                tv_banker.setText(banker + "0");
            } else {
                tv_banker.setText(banker + "" + banker1);
            }
        } else if (banker1 > 0 && banker2 > 0 && banker3 == 0) {
            if (banker1 >= 10) {
                banker1 = 0;
            }
            if (banker2 >= 10) {
                banker2 = 0;
            }
            if (banker1 + banker2 >= 20) {
                tv_banker.setText(banker + "0");
            } else if (banker1 + banker2 >= 10) {
                tv_banker.setText(banker + "" + (banker1 + banker2 - 10));
            } else {
                tv_banker.setText(banker + "" + (banker1 + banker2));
            }
        } else {
            if (banker1 >= 10) {
                banker1 = 0;
            }
            if (banker2 >= 10) {
                banker2 = 0;
            }
            if (banker3 >= 10) {
                banker3 = 0;
            }
            if (banker1 + banker2 + banker3 >= 30) {
                tv_banker.setText(banker + "0");
            } else if (banker1 + banker2 + banker3 >= 20) {
                tv_banker.setText(banker + "" + (banker1 + banker2 + banker3 - 20));
            } else if (banker1 + banker2 + banker3 >= 10) {
                tv_banker.setText(banker + "" + (banker1 + banker2 + banker3 - 10));
            } else {
                tv_banker.setText(banker + "" + (banker1 + banker2 + banker3));
            }
        }

        if (player1 == 0 && player2 == 0 && player3 == 0) {
            tv_player.setText(player + "0");
        } else if (player1 > 0 && player2 == 0 && player3 == 0) {

            if (player1 > 9) {
                tv_player.setText(player + "0");
            } else {
                tv_player.setText(player + "" + player1);
            }
        } else if (player1 > 0 && player2 > 0 && player3 == 0) {
            if (player1 >= 10) {
                player1 = 0;
            }
            if (player2 >= 10) {
                player2 = 0;
            }

            if (player1 + player2 >= 20) {
                tv_player.setText(player + "0");
            } else if (player1 + player2 >= 10) {
                tv_player.setText(player + "" + (player1 + player2 - 10));
            } else {
                tv_player.setText(player + "" + (player1 + player2));
            }
        } else {
            if (player1 >= 10) {
                player1 = 0;
            }
            if (player2 >= 10) {
                player2 = 0;
            }
            if (player3 >= 10) {
                player3 = 0;
            }
            if (player1 + player2 + player3 >= 30) {
                tv_player.setText(player + "0");
            } else if (player1 + player2 + player3 >= 20) {
                tv_player.setText(player + "" + (player1 + player2 + player3 - 20));
            } else if (player1 + player2 + player3 >= 10) {
                tv_player.setText(player + "" + (player1 + player2 + player3 - 10));
            } else {
                tv_player.setText(player + "" + (player1 + player2 + player3));
            }
        }
    }

    public Map<String, Integer> getPoint(int p1, int p2, int b1, int b2) {
        Map<String, Integer> map = new HashMap<>();
        int banker1 = changePokerPoint(b1);
        int banker2 = changePokerPoint(b2);

        int player1 = changePokerPoint(p1);
        int player2 = changePokerPoint(p2);
        int banker = 0, player = 0;


        if (banker1 == 0 && banker2 == 0) {


        } else if (banker1 > 0 && banker2 == 0) {
            if (banker1 > 9) {

            } else {
                banker = banker1;
            }
        } else if (banker1 > 0 && banker2 > 0) {
            if (banker1 >= 10) {
                banker1 = 0;
            }
            if (banker2 >= 10) {
                banker2 = 0;
            }
            if (banker1 + banker2 >= 20) {

            } else if (banker1 + banker2 >= 10) {
                banker = (banker1 + banker2 - 10);
            } else {
                banker = (banker1 + banker2);
            }
        }

        if (player1 == 0 && player2 == 0) {

        } else if (player1 > 0 && player2 == 0) {

            if (player1 > 9) {

            } else {
                player = player1;
            }
        } else if (player1 > 0 && player2 > 0) {
            if (player1 >= 10) {
                player1 = 0;
            }
            if (player2 >= 10) {
                player2 = 0;
            }

            if (player1 + player2 >= 20) {

            } else if (player1 + player2 >= 10) {
                player = (player1 + player2 - 10);
            } else {
                player = player1 + player2;
            }
        }
        map.put("player", player);
        map.put("banker", banker);
        return map;
    }

    public void showPoint(int p1, int b1, TextView tv_banker, TextView tv_player, String banker, String player) {
        int banker1 = changePokerPoint(b1);

        int player1 = changePokerPoint(p1);


        if (banker1 == 0) {
            tv_banker.setText(banker + "0");
        } else if (banker1 > 0) {
            tv_banker.setText(banker + "" + banker1);

        }

        if (player1 == 0) {
            tv_player.setText(player + "0");
        } else if (player1 > 0) {


            tv_player.setText(player + "" + player1);

        }
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



    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("TLS");
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception ignored) {
            ignored.toString();
        }
    }

    public void showCrashDialog(Context context) {
        System.exit(0);
      /*  AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.program_crash_dialog, null);
        view.findViewById(R.id.crash_dialog_but).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        AppExit();
                    }
                });
        dialogBuilder.setView(view);
        dialogBuilder.show();*/
    }


    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public Baccarat getBaccarat01() {
        if (baccarat01 == null)
            baccarat01 = new Baccarat();
        return baccarat01;
    }

    public Baccarat getBaccarat02() {
        if (baccarat02 == null)
            baccarat02 = new Baccarat();
        return baccarat02;
    }

    public Baccarat getBaccarat03() {
        if (baccarat03 == null)
            baccarat03 = new Baccarat();
        return baccarat03;
    }

    public Baccarat getBaccarat61() {
        if (baccarat61 == null)
            baccarat61 = new Baccarat();
        return baccarat61;
    }

    public Baccarat getBaccarat62() {
        if (baccarat62 == null)
            baccarat62 = new Baccarat();
        return baccarat62;
    }

    public Baccarat getBaccarat63() {
        if (baccarat63 == null)
            baccarat63 = new Baccarat();
        return baccarat63;
    }

    public Baccarat getBaccarat71() {
        if (baccarat71 == null)
            baccarat71 = new Baccarat();
        return baccarat71;
    }

    public Roulette getRoulette01() {
        if (roulette01 == null)
            roulette01 = new Roulette();
        return roulette01;
    }

    public Sicbo getSicbo01() {
        if (sicbo01 == null)
            sicbo01 = new Sicbo();
        return sicbo01;
    }

    public DragonTiger getDragonTiger01() {
        if (dragonTiger01 == null)
            dragonTiger01 = new DragonTiger();
        return dragonTiger01;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public User getUser() {
        if (user == null)
            user = new User();
        return user;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public boolean isbLogin() {
        return bLogin;
    }

    public void setbLogin(boolean bLogin) {
        this.bLogin = bLogin;
    }

    public boolean isbLobby() {
        return bLobby;
    }

    public void setbLobby(boolean bLobby) {
        this.bLobby = bLobby;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getSerialId() {
        return serialId;
    }

    public void setSerialId(int serialId) {
        this.serialId = serialId;
    }

    public int getFrontVolume() {
        return frontVolume;
    }

    public void setFrontVolume(int frontVolume) {
        this.frontVolume = frontVolume;
    }

    public int getBackgroudVolume() {
        return backgroudVolume;
    }

    public void setBackgroudVolume(int backgroudVolume) {
        this.backgroudVolume = backgroudVolume;
    }

    private int muzicIndex = 1;


    public int getMuzicIndex() {
        return muzicIndex;
    }

    public void setMuzicIndex(int muzicIndex) {
        this.muzicIndex = muzicIndex;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getGameType() {
        String reallyGameType = gameType;
        gameType = "";
        return reallyGameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    private String gameType;

    public int getHomeColor() {
        return homeColor;
    }

    public void setHomeColor(int homeColor) {
        this.homeColor = homeColor;
    }

    public int homeColor = 0;
}
