package nanyang.com.dig88.Activity;

import android.content.Context;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;
import com.unkonw.testapp.libs.base.BaseApplication;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.DigGameBean;
import nanyang.com.dig88.Entity.DigGameStateBean;
import nanyang.com.dig88.Entity.GameBalanceBean;
import nanyang.com.dig88.Entity.GameStateBean;
import nanyang.com.dig88.Entity.LiveGameBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.Thread.TableHttpHelper;
import nanyang.com.dig88.Table.entity.BallGameInfoBean;
import nanyang.com.dig88.Table.entity.BettingInfoBean;
import nanyang.com.dig88.Table.entity.BettingParPromptBean;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.reporter.AndroidCrash;
import xs.com.mylibrary.allinone.util.reporter.mailreporter.CrashEmailReporter;

/**
 * Created by Administrator on 2015/10/21.
 */
public class DigApp extends BaseApplication {
    static DigApp instance = null;
    final Handler handler = new Handler();
    public BettingParPromptBean betParList;
    protected Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();
    TableHttpHelper<NyBaseResponse<List<GameBalanceBean>>> httpHelper;
    String affiliateId = "";
    String affiliateWeb = "";
    UserInfoBean userInfoBean = new UserInfoBean();
    int areaid;
    //diggame
    DigGameBean dignumbergame_42ball = new DigGameBean();
    DigGameBean dignumbergame_36ball = new DigGameBean();
    DigGameBean dignumbergame_24ball = new DigGameBean();
    DigGameBean dignumbergame_12ball = new DigGameBean();
    DigGameBean dignumbergame_18ball = new DigGameBean();
    DigGameBean dignumbergame_30ball = new DigGameBean();
    //livegame
    LiveGameBean livegame_42balls = new LiveGameBean();
    LiveGameBean livegame_36balls = new LiveGameBean();
    LiveGameBean livegame_12balls = new LiveGameBean();
    LiveGameBean livegame_48balls = new LiveGameBean();
    LiveGameBean livegame_Sicoballs = new LiveGameBean();
    LiveGameBean livegame_Scollballs = new LiveGameBean();
    LiveGameBean livegame_MULTIPLEballs = new LiveGameBean();
    private UserInfoBean userInfo;
    private BallGameInfoBean ballGameInfo;
    private NyVolleyJsonThreadHandler<String> updateThread;
    private String market = "0";
    private String dataType = "0";//0是正常数据，1世界杯数据
    private boolean isRegisterFirstIn = false;
    private String countryMarket = "MY";
    private double scaleValue = 1.25;
    private double balance = 0;
    private String currency = "";
    private boolean bInitVideo = false;
    private HttpClient httpClient;
    private String cookie;
    private Map<String, Map<String, Map<Integer, BettingInfoBean>>> betDetail;
    private String NumberGameStatusMessage;
    private String NumberGameParams;
    private String LiveGameParams;
    private boolean videoPause = false;
    private int error = 0;
    private boolean videoDelay = false;
    private int urlIndex = 0;
    private boolean releaseVideo = true;
    private int iOpenFile = -1;
    private List<String> bannerList = new ArrayList<>();
    private List<String> gameNameList = new ArrayList<>();
    private Map<String, String> gameStatusMap = new HashMap<>();
    private List<String> gameStatusList;
    private List<String> newKenoStatusList;
    private boolean isShowJokerPop;

    public static DigApp getIntance() {
        if (instance == null)
            instance = new DigApp();
        return instance;
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

    public boolean isRegisterFirstIn() {
        return isRegisterFirstIn;
    }

    public void setRegisterFirstIn(boolean isRegisterFirstIn) {
        this.isRegisterFirstIn = isRegisterFirstIn;
    }

    public String getAffiliateWeb() {
        return affiliateWeb;
    }

    public void setAffiliateWeb(String affiliateWeb) {
        this.affiliateWeb = affiliateWeb;
    }

    public String getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(String affiliateId) {
        this.affiliateId = affiliateId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getCountryMarket() {
        return countryMarket;
    }

    public void setCountryMarket(String countryMarket) {
        this.countryMarket = countryMarket;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public double getScaleValue() {
        return scaleValue;
    }

    public void setScaleValue(double scaleValue) {
        this.scaleValue = scaleValue;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void ClearBetInformation(DigGameBean digGameBean) {
        digGameBean.setColor_bet_total(0);
        digGameBean.setBigsmall_bet_total(0);
        digGameBean.setOddeven_bet_total(0);
        digGameBean.setCombination_bet_total(0);
        digGameBean.setNumber_bet_total(0);
    }

    public void GetGameStatus(boolean updateAllData, SimpleDateFormat format, String activityName) {
        try {

            if (httpClient == null) {
                // Log.i("LanjianTest","GetData");
                httpClient = new HttpClient(WebSiteUrl.AutonumberStatus, "");
                if (httpClient.connect("POST") == false) {
                    // Log.i("LanjianTest","HttpClient conn error");
                    return;
                } else {
                    NumberGameStatusMessage = httpClient.getBodyString("UTF-8");
                    cookie = httpClient.getSessionId();
                    //   Log.i("LanjianTest","--"+NumberGameStatusMessage);
                }
                NumberGameParams = "web_id=" + WebSiteUrl.WebId + "&user_id=" + getUserInfo().getUser_id() + "&session_id=" + getUserInfo().getSession_id();
            }

            NumberGameStatusMessage = httpClient.sendPost(WebSiteUrl.AutonumberStatus, NumberGameParams);
            Gson gson = new Gson();
            NyBaseResponse<List<DigGameStateBean>> orgData;
            orgData = gson.fromJson(NumberGameStatusMessage, new TypeToken<NyBaseResponse<List<DigGameStateBean>>>() {
            }.getType());
            List<DigGameStateBean> data = orgData.getData();
            if (data == null)
                return;
            /////////
            boolean statu42 = false;
            boolean statu36 = false;
            boolean statu24 = false;
            boolean statu12 = false;
            boolean statu18 = false;
            boolean statu30 = false;
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getGame_name().contains("42")) {
                    // Log.i("LanjianTest42","Start Get 42Balls Data");
                    getDignumbergame_42ball().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                        Log.i("LanjianTest42", e.toString());
                    }
                    //   Log.i("LanjianTest","Open_rule = "+stropenrule);
                    getDignumbergame_42ball().setOpenUrl(Integer.parseInt(stropenrule));
                    statu42 = true;
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getDignumbergame_42ball().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getDignumbergame_42ball().getCount_down() == 0 || updateAllData) {

                        getDignumbergame_42ball().setGame_perid(data.get(i).getPeriod());
                        getDignumbergame_42ball().setCount_down(count_down);
                        ClearBetInformation(getDignumbergame_42ball());
                    }

                } else if (data.get(i).getGame_name().contains("36")) {
                    getDignumbergame_36ball().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getDignumbergame_36ball().setOpenUrl(Integer.parseInt(stropenrule));
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getDignumbergame_36ball().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getDignumbergame_36ball().getCount_down() == 0 || updateAllData) {
                        getDignumbergame_36ball().setGame_perid(data.get(i).getPeriod());
                        getDignumbergame_36ball().setCount_down(count_down);

                        ClearBetInformation(getDignumbergame_36ball());
                    }
                    statu36 = true;
                } else if (data.get(i).getGame_name().contains("24")) {
                    getDignumbergame_24ball().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getDignumbergame_24ball().setOpenUrl(Integer.parseInt(stropenrule));
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getDignumbergame_24ball().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getDignumbergame_24ball().getCount_down() == 0 || updateAllData) {
                        getDignumbergame_24ball().setGame_perid(data.get(i).getPeriod());
                        getDignumbergame_24ball().setCount_down(count_down);

                        ClearBetInformation(getDignumbergame_24ball());
                    }
                    statu24 = true;
                } else if (data.get(i).getGame_name().contains("12")) {
                    getDignumbergame_12ball().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getDignumbergame_12ball().setOpenUrl(Integer.parseInt(stropenrule));
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getDignumbergame_12ball().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getDignumbergame_12ball().getCount_down() == 0 || updateAllData) {
                        getDignumbergame_12ball().setGame_perid(data.get(i).getPeriod());
                        getDignumbergame_12ball().setCount_down(count_down);

                        ClearBetInformation(getDignumbergame_12ball());
                    }
                    statu12 = true;
                } else if (data.get(i).getGame_name().contains("18")) {
                    getDignumbergame_18ball().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getDignumbergame_18ball().setOpenUrl(Integer.parseInt(stropenrule));
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getDignumbergame_18ball().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getDignumbergame_18ball().getCount_down() == 0 || updateAllData) {
                        getDignumbergame_18ball().setGame_perid(data.get(i).getPeriod());
                        getDignumbergame_18ball().setCount_down(count_down);

                        ClearBetInformation(getDignumbergame_18ball());
                    }
                    statu18 = true;
                } else if (data.get(i).getGame_name().contains("30")) {
                    getDignumbergame_30ball().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getDignumbergame_30ball().setOpenUrl(Integer.parseInt(stropenrule));
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getDignumbergame_30ball().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getDignumbergame_30ball().getCount_down() == 0 || updateAllData) {
                        Log.i("LanjianTest30", "Next Game CountDownTime=" + count_down + ",NewGameNumber=" + data.get(i).getPeriod()
                                + ",Old=" + getDignumbergame_30ball().getGame_perid());
                        getDignumbergame_30ball().setGame_perid(data.get(i).getPeriod());
                        getDignumbergame_30ball().setCount_down(count_down);

                        ClearBetInformation(getDignumbergame_30ball());
                    }
                    statu30 = true;

                }
            }
            if (statu42 == false) {
                //     Log.i("LanjianTest42","Status42==false");
                getDignumbergame_42ball().setGame_status(false);
            } else {
                getDignumbergame_42ball().setGame_status(true);
            }
            if (statu36 == false) {
                getDignumbergame_36ball().setGame_status(false);
            } else {
                getDignumbergame_36ball().setGame_status(true);
            }
            if (statu24 == false) {
                getDignumbergame_24ball().setGame_status(false);
            } else {
                getDignumbergame_24ball().setGame_status(true);
            }
            if (statu12 == false) {
                getDignumbergame_12ball().setGame_status(false);
            } else {
                getDignumbergame_12ball().setGame_status(true);
            }
            if (statu18 == false) {
                getDignumbergame_18ball().setGame_status(false);
            } else {
                getDignumbergame_18ball().setGame_status(true);
            }
            if (statu30 == false) {
                getDignumbergame_30ball().setGame_status(false);
            } else {
                getDignumbergame_30ball().setGame_status(true);
            }
            data = null;
            orgData = null;
            gson = null;
            updateAllData = false;

            //  Log.i("LanjianTest",NumberGameStatusMessage);
        } catch (Exception e) {
            Log.i("LanjianTest", e.toString());
            e.printStackTrace();
        }
    }

    public void GetLiveGameStatus(boolean updateAllData, SimpleDateFormat format, String activityName) {
        try {
            if (httpClient == null) {
                // Log.i("LanjianTest","GetData");
                httpClient = new HttpClient(WebSiteUrl.LivenumberStatus, "");
                if (httpClient.connect("POST") == false) {
                    Log.i("LanjianTest", "HttpClient conn error");
                    return;
                } else {
                    NumberGameStatusMessage = httpClient.getBodyString("UTF-8");
                    cookie = httpClient.getSessionId();
                    //  Log.i("LanjianTest","--"+NumberGameStatusMessage);
                }
            }
            LiveGameParams = "web_id=" + WebSiteUrl.WebId + "&user_id=" + getUserInfo().getUser_id() + "&session_id=" + getUserInfo().getSession_id();
            NumberGameStatusMessage = httpClient.sendPost(WebSiteUrl.LivenumberStatus, LiveGameParams);
            Gson gson = new Gson();
            NyBaseResponse<List<GameStateBean>> orgData;
            orgData = gson.fromJson(NumberGameStatusMessage, new TypeToken<NyBaseResponse<List<GameStateBean>>>() {
            }.getType());
            List<GameStateBean> data = orgData.getData();
            if (data == null)
                return;
            /////////
            boolean statu42 = false;
            boolean statu36 = false;
            boolean statu12 = false;
            boolean statu48 = false;
            boolean statuSicbo = false;
            boolean statuRoulette = false;
            boolean statuMultiple = false;
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getGame_name().contains("42")) {
                    //      Log.i("LanjianTest42","HttpClient conn error");
                    getLivegame_42balls().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getLivegame_42balls().setOpenUrl(Long.parseLong(stropenrule));
                    statu42 = true;
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getLivegame_42balls().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getLivegame_42balls().getCount_down() == 0 || updateAllData) {
                        getLivegame_42balls().setGame_perid(data.get(i).getPeriod());
                        getLivegame_42balls().setCount_down(count_down);
                        ClearLiveBetInformation(getLivegame_42balls());
                    }

                } else if (data.get(i).getGame_name().contains("36_Balls")) {
                    getLivegame_36balls().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getLivegame_36balls().setOpenUrl(Long.parseLong(stropenrule));
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getLivegame_36balls().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getLivegame_36balls().getCount_down() == 0 || updateAllData) {
                        getLivegame_36balls().setGame_perid(data.get(i).getPeriod());
                        getLivegame_36balls().setCount_down(count_down);
                        ClearLiveBetInformation(getLivegame_36balls());
                    }
                    statu36 = true;
                } else if (data.get(i).getGame_name().contains("12")) {
                    getLivegame_12balls().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getLivegame_12balls().setOpenUrl(Long.parseLong(stropenrule));
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getLivegame_12balls().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getLivegame_12balls().getCount_down() == 0 || updateAllData) {
                        getLivegame_12balls().setGame_perid(data.get(i).getPeriod());
                        getLivegame_12balls().setCount_down(count_down);
                        ClearLiveBetInformation(getLivegame_12balls());
                    }
                    statu12 = true;
                } else if (data.get(i).getGame_name().contains("48")) {
                    getLivegame_48balls().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getLivegame_48balls().setOpenUrl(Long.parseLong(stropenrule));
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getLivegame_48balls().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getLivegame_48balls().getCount_down() == 0 || updateAllData) {
                        getLivegame_48balls().setGame_perid(data.get(i).getPeriod());
                        getLivegame_48balls().setCount_down(count_down);
                        ClearLiveBetInformation(getLivegame_48balls());
                    }
                    statu48 = true;
                } else if (data.get(i).getGame_name().contains("Sicbo")) {
                    getLivegame_Sicoballs().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getLivegame_Sicoballs().setOpenUrl(Long.parseLong(stropenrule));
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getLivegame_Sicoballs().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getLivegame_Sicoballs().getCount_down() == 0 || updateAllData) {
                        getLivegame_Sicoballs().setGame_perid(data.get(i).getPeriod());
                        getLivegame_Sicoballs().setCount_down(count_down);
                        ClearLiveBetInformation(getLivegame_Sicoballs());
                    }
                    statuSicbo = true;
                } else if (data.get(i).getGame_name().contains("Roulette")) {
                    getLivegame_Scollballs().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getLivegame_Scollballs().setOpenUrl(Long.parseLong(stropenrule));
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getLivegame_Scollballs().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getLivegame_Scollballs().getCount_down() == 0 || updateAllData) {
                        getLivegame_Scollballs().setGame_perid(data.get(i).getPeriod());
                        getLivegame_Scollballs().setCount_down(count_down);
                        ClearLiveBetInformation(getLivegame_Scollballs());
                    }
                    statuRoulette = true;
                } else if (data.get(i).getGame_name().contains("Multiple")) {
                    //     Log.i("LanjianTest","Live6="+statuMultiple);
                    getLivegame_MULTIPLEballs().setGame_opentime(data.get(i).getOpen_time());
                    //opentime
                    String stropentime = data.get(i).getOpen_time();
                    String stropenrule = data.get(i).getOpen_rule();
                    String strnowtime = data.get(i).getNow_time();
                    Date opentime_date = null;
                    Date nowtime_date = null;
                    int time_rule = Integer.valueOf(stropenrule) * 60;
                    try {
                        opentime_date = format.parse(stropentime);
                        nowtime_date = format.parse(strnowtime);
                    } catch (Exception e) {
                    }
                    getLivegame_MULTIPLEballs().setOpenUrl(Long.parseLong(stropenrule));
                    Long nowtime = nowtime_date.getTime(); //nowtime
                    Long opentime = opentime_date.getTime(); //opentime
                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                    if (!getLivegame_MULTIPLEballs().getGame_perid().equals(data.get(i).getPeriod()) ||
                            getLivegame_MULTIPLEballs().getCount_down() == 0 || updateAllData) {
                        getLivegame_MULTIPLEballs().setGame_perid(data.get(i).getPeriod());
                        getLivegame_MULTIPLEballs().setCount_down(count_down);
                        ClearLiveBetInformation(getLivegame_MULTIPLEballs());
                    }
                    statuMultiple = true;
                }
            }
            if (statu42 == false) {
                getLivegame_42balls().setGame_status(false);
            } else {
                getLivegame_42balls().setGame_status(true);
            }
            if (statu36 == false) {
                getLivegame_36balls().setGame_status(false);
            } else {
                getLivegame_36balls().setGame_status(true);
            }
            if (statu12 == false) {
                getLivegame_12balls().setGame_status(false);
            } else {
                getLivegame_12balls().setGame_status(true);
            }
            if (statu48 == false) {
                getLivegame_48balls().setGame_status(false);
            } else {
                getLivegame_48balls().setGame_status(true);
            }
            if (statuSicbo == false) {
                getLivegame_Sicoballs().setGame_status(false);
            } else {
                getLivegame_Sicoballs().setGame_status(true);
            }
            if (statuRoulette == false) {
                getLivegame_Scollballs().setGame_status(false);
            } else {
                getLivegame_Scollballs().setGame_status(true);
            }
            if (statuMultiple == false) {
                // Log.i("LanjianTest","Live6="+statuMultiple);
                getLivegame_MULTIPLEballs().setGame_status(false);
            } else {
                getLivegame_MULTIPLEballs().setGame_status(true);
            }
            data = null;
            orgData = null;
            gson = null;
            updateAllData = false;
            //   Log.i("LanjianTest",NumberGameStatusMessage);
        } catch (IOException e) {
            Log.i("LanjianTest", e.toString());
            e.printStackTrace();
        }
    }

    public void ClearLiveBetInformation(LiveGameBean digGameBean) {
        digGameBean.setColor_bet_total(0);
        digGameBean.setBigsmall_bet_total(0);
        digGameBean.setOddeven_bet_total(0);
        digGameBean.setCombination_bet_total(0);
        digGameBean.setGolodsliver_bet_total(0);
        digGameBean.setRedblack_bet_total(0);
        digGameBean.setComu48_bet_total(0);
        digGameBean.setComu482_bet_total(0);
        digGameBean.setComu483_bet_total(0);
        digGameBean.setComu484_bet_total(0);
        digGameBean.setComu485_bet_total(0);
        digGameBean.setComu486_bet_total(0);
        digGameBean.setNumber_bet_total(0);
        digGameBean.setWaidices_bet_total(0);
        digGameBean.setAlldice_bet_total(0);
        digGameBean.setThreeforces_bet_total(0);
        digGameBean.setPairs_bet_total(0);
        digGameBean.setNinewaycard_bet_total(0);
        digGameBean.setGroup_bet_total(0);
        digGameBean.setJackpot12_bet_total(0);
        digGameBean.setSimgnum48_bet_total(0);
    }

    public LiveGameBean getLivegame_42balls() {
        return livegame_42balls;
    }

    public void setLivegame_42balls(LiveGameBean livegame_42balls) {
        this.livegame_42balls = livegame_42balls;
    }

    public LiveGameBean getLivegame_36balls() {
        return livegame_36balls;
    }

    public void setLivegame_36balls(LiveGameBean livegame_36balls) {
        this.livegame_36balls = livegame_36balls;
    }

    public LiveGameBean getLivegame_12balls() {
        return livegame_12balls;
    }

    public void setLivegame_12balls(LiveGameBean livegame_12balls) {
        this.livegame_12balls = livegame_12balls;
    }

    public LiveGameBean getLivegame_48balls() {
        return livegame_48balls;
    }

    public void setLivegame_48balls(LiveGameBean livegame_48balls) {
        this.livegame_48balls = livegame_48balls;
    }

    public LiveGameBean getLivegame_Sicoballs() {
        return livegame_Sicoballs;
    }

    public void setLivegame_Sicoballs(LiveGameBean livegame_Sicoballs) {
        this.livegame_Sicoballs = livegame_Sicoballs;
    }

    public LiveGameBean getLivegame_Scollballs() {
        return livegame_Scollballs;
    }

    public void setLivegame_Scollballs(LiveGameBean livegame_Scollballs) {
        this.livegame_Scollballs = livegame_Scollballs;
    }

    public LiveGameBean getLivegame_MULTIPLEballs() {
        return livegame_MULTIPLEballs;
    }

    public void setLivegame_MULTIPLEballs(LiveGameBean livegame_MULTIPLEballs) {
        this.livegame_MULTIPLEballs = livegame_MULTIPLEballs;
    }

//    /**
//     * 使用HTTP发送日志
//     */
//    public void initHttpReporter() {
//        CrashHttpReporter reporter = new CrashHttpReporter(this) {
//            @Override
//            public void closeApp(Thread thread, Throwable ex) {
//                final Activity activity = currentActivity();
//                showCrashDialog(activity);
//            }
//        };
//        reporter.setUrl("接收你请求的API").setFileParam("fileName").setToParam("to")
//                .setTo("你的接收邮箱").setTitleParam("subject")
//                .setBodyParam("message");
//        reporter.setCallback(new CrashHttpReporter.HttpReportCallback() {
//            @Override
//            public boolean isSuccess(int i, String s) {
//                return s.endsWith("ok");
//            }
//        });
//        AndroidCrash.getInstance().setCrashReporter(reporter).init(this);
//    }

    public DigGameBean getDignumbergame_30ball() {
        return dignumbergame_30ball;
    }

    public void setDignumbergame_30ball(DigGameBean dignumbergame_30ball) {
        this.dignumbergame_30ball = dignumbergame_30ball;
    }

    public DigGameBean getDignumbergame_18ball() {
        return dignumbergame_18ball;
    }

    public void setDignumbergame_18ball(DigGameBean dignumbergame_18ball) {
        this.dignumbergame_18ball = dignumbergame_18ball;
    }

    public Map<String, Map<String, Boolean>> getLocalCollectionMap() {
        return localCollectionMap;
    }

    public void setLocalCollectionMap(Map<String, Map<String, Boolean>> localCollectionMap) {
        this.localCollectionMap = localCollectionMap;
    }

    public void clearLocalCollection() {
        localCollectionMap = new HashMap<>();
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

    //player end
    @Override
    public void onCreate() {
        super.onCreate();
//        handleSSLHandshake();
        Fresco.initialize(this);
        closeAndroidPDialog();
        iniBugly();
        initX5WebView();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    private void initX5WebView() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("onViewInitFinished", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());

        strategy.setCrashHandleCallback(new CrashReport.CrashHandleCallback() {

            public Map<String, String> onCrashHandleStart(int crashType, String errorType, String errorMessage, String errorStack) {

                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

                String x5CrashInfo = com.tencent.smtt.sdk.WebView.getCrashExtraMessage(getApplicationContext());

                map.put("x5crashInfo", x5CrashInfo);

                return map;

            }

            @Override

            public byte[] onCrashHandleStart2GetExtraDatas(int crashType, String errorType, String errorMessage, String errorStack) {

                try {

                    return "Extra data.".getBytes("UTF-8");

                } catch (Exception e) {

                    return null;

                }

            }

        });

        CrashReport.initCrashReport(getApplicationContext(), "356b7590e8", true, strategy);
    }

    private void iniBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "356b7590e8", false);
    }

    /**
     * 使用EMAIL发送日志
     */
    private void initEmailReporter() {
        CrashEmailReporter reporter = new CrashEmailReporter(this) {
            @Override
            public void closeApp(Thread thread, Throwable ex) {

            }
        };
        reporter.setReceiver(getResources().getString(R.string.crash_mail));
        reporter.setSender(getResources().getString(R.string.crash_mail));
        reporter.setSendPassword(getResources().getString(
                R.string.crash_mail_passwd));
        reporter.setSMTPHost(getResources().getString(
                R.string.crash_mail_smtphost));
        reporter.setPort(getResources().getString(R.string.crash_mail_port));
        AndroidCrash.getInstance().setCrashReporter(reporter).init(this);
    }

    public boolean isFirstUse(Context context) {
        boolean isFirstUse = PreferenceManager.getDefaultSharedPreferences(
                context).getBoolean("app_first_use", true);
        if (isFirstUse) {
            PreferenceManager.getDefaultSharedPreferences(context).edit()
                    .putBoolean("app_first_use", false).commit();
        }
        return isFirstUse;
    }

    public DigGameBean getDignumbergame_42ball() {
        return dignumbergame_42ball;
    }

    public void setDignumbergame_42ball(DigGameBean dignumbergame_42ball) {
        this.dignumbergame_42ball = dignumbergame_42ball;
    }

    public DigGameBean getDignumbergame_36ball() {
        return dignumbergame_36ball;
    }

    public void setDignumbergame_36ball(DigGameBean dignumbergame_36ball) {
        this.dignumbergame_36ball = dignumbergame_36ball;
    }

    public DigGameBean getDignumbergame_24ball() {
        return dignumbergame_24ball;
    }

    public void setDignumbergame_24ball(DigGameBean dignumbergame_24ball) {
        this.dignumbergame_24ball = dignumbergame_24ball;
    }

    public DigGameBean getDignumbergame_12ball() {
        return dignumbergame_12ball;
    }

    public void setDignumbergame_12ball(DigGameBean dignumbergame_12ball) {
        this.dignumbergame_12ball = dignumbergame_12ball;
    }

    public UserInfoBean getUserInfo() {
        return userInfoBean;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        userInfoBean = userInfo;
    }

    public BallGameInfoBean getBallGameInfo() {
        return ballGameInfo;
    }

    public void setBallGameInfo(BallGameInfoBean ballGameInfo) {
        this.ballGameInfo = ballGameInfo;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

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

    public boolean isbInitVideo() {
        return bInitVideo;
    }

    public void setbInitVideo(boolean bInitVideo) {
        this.bInitVideo = bInitVideo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isVideoPause() {
        return videoPause;
    }

    public void setVideoPause(boolean videoPause) {
        this.videoPause = videoPause;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public boolean isVideoDelay() {
        return videoDelay;
    }

    public void setVideoDelay(boolean videoDelay) {
        this.videoDelay = videoDelay;
    }

    public int getUrlIndex() {
        return urlIndex;
    }

    public void setUrlIndex(int urlIndex) {
        this.urlIndex = urlIndex;
    }

    public boolean isReleaseVideo() {
        return releaseVideo;
    }

    public void setReleaseVideo(boolean releaseVideo) {
        this.releaseVideo = releaseVideo;
    }

    public int getiOpenFile() {
        return iOpenFile;
    }

    public void setiOpenFile(int iOpenFile) {
        this.iOpenFile = iOpenFile;
    }

    public List<String> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<String> bannerList) {
        this.bannerList = bannerList;
    }

    public Map<String, String> getGameStatusMap() {
        return gameStatusMap;
    }

    public void setGameStatusMap(Map<String, String> gameStatusMap) {
        this.gameStatusMap = gameStatusMap;
    }

    public List<String> getGameNameList() {
        return gameNameList;
    }

    public void setGameNameList(List<String> gameNameList) {
        this.gameNameList = gameNameList;
    }

    public List<String> getNewKenoStatusList() {
        return newKenoStatusList;
    }

    public void setNewKenoStatusList(List<String> newKenoStatusList) {
        this.newKenoStatusList = newKenoStatusList;
    }

    public List<String> getGameStatusList() {
        return gameStatusList;
    }

    public void setGameStatusList(List<String> gameStatusList) {
        this.gameStatusList = gameStatusList;
    }

    public boolean getIsShowJokerPop() {
        return isShowJokerPop;
    }

    public void setIsShowJokerPop(boolean isShowJokerPop) {
        this.isShowJokerPop = isShowJokerPop;
    }
}
