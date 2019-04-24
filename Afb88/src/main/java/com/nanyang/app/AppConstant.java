package com.nanyang.app;

/**
 * Created by Administrator on 2017/2/10.
 */

public class AppConstant {


    public static final String DownLoadDig88AppUrl = "http://appgd88.com/gd88/download/android/gd88.apk";
    public static final String CHECK_VERSION = "http://www.appgd88.com/afb1188version.php?app=android";
    public static final String DOWNLOAD_APP = "http://appgd88.com/afb1188/afb1188.apk";
    public static boolean IS_AGENT = false;

//http://www.afb1188.com/W0/Pub/pcode.axd


    public static String KEY_STRING = "KEY_STRING";
    public static String KEY_DATA = "KEY_DATA";
    public static String KEY_INT = "KEY_INT";
    public static String KEY_BOOLEAN = "KEY_BOOLEAN";
    public static String KEY_DATA2 = "KEY_DATA2";
    public static String KEY_DATA3 = "KEY_DATA3";


//    http://a0096f.panda88.org/_View/SocAllGen.ashx?ot=r&mt=0&tf=-1&TFStatus=0&update=false&r=431367264&LID=&_=1488465754049
    //盘口
//    http://a0096f.panda88.org/Main.aspx?accType=HK
//            http://a0096f.panda88.org/Main.aspx?accType=MY
    //http://a8197c.a36588.com/Main.aspx?accType=ID
    //http://a8197c.a36588.com/Main.aspx?accType=EU

    //    public static String HOST="http://www.doo88.net/";
    public String HOST = BuildConfig.HOST_AFB;
    public String WebSocket_HOST = "http://wsapp.afb1188.com:8888/";
    //http://www.afb1188.com/W0/Pub/pcode.axd
    public String URL_LOGIN = HOST + "W0/Pub/pcode.axd";///W0/Pub/pcode.axd
    //    、、/W0/Pub/wfMain0.html
    public String URL_MAIN = HOST + "W0/Pub/wfMain0.html";

    public String _BET = "Bet/";
    //https://ws.afb1188.com:8888/fnOddsGen?
    //wst=wsSocAllGen
    // &g=1
    // &ot=r
    // &wd=&pn=1
    // &delay=0
    // &tf=-1
    // &betable=1
    // &lang=en
    // &ia=0
    // &tfDate=2019-03-24
    // &LangCol=C
    // &accType=MY
    // &CTOddsDiff=-0.2
    // &CTSpreadDiff=-1
    // &oddsDiff=0
    // &spreadDiff=0
    // &um=1|1317|22080
    // &LID=
    // &mt=0
    // &FAV=&SL=&LSL=undefined
//https://www.afb1188.com/H50/Pub/pcode.axd?_fm={"ACT":"LOS","DBID":"1","ot":"r","tf":"-1","timess":"","accType":"MY","pgLable":"0.6290983573462304","vsn":"4.0.12","PT":"wfMainH50"}&_db={}


    public String URL_UPDATE_BALANCE = HOST + "Bet/PanelStakeBalance_App.aspx";//http://103.206.122.65/Bet/BetStake.ashx
    public String URL_UPDATE_STATE = HOST + "pgajaxS.axd?T=CHKST&P=001";
    public String URL_ODDS_TYPE = HOST + "W0/Pub/wfMain0.html?accType=";//http://103.206.122.65/W0/Pub/wfMain0.html?accType=HK

    public String URL_ODDS_AFB1188 = HOST + "Bet/hBetOdds.ashx?";
    //    public String URL_ODDS = HOST + "Bet/JRecPanel_App.aspx?";
    public String URL_ODDS = URL_ODDS_AFB1188;
    public String URL_SOCCER_REMOVE_MIX = HOST + "Bet/ParRemove.aspx";
    //http://main55.afb88.com/_view/Result.aspx
    public String URL_RESULT = HOST + "_view/Result_App.aspx";//好的
    // https://ws.afb1188.com:8888/fnOddsGen?wst=wsSocAllGen&g=1&ot=r&wd=&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=0&tfDate=2019-03-25&LangCol=C&accType=MY&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&um=1|1317|22080&LID=&mt=0&FAV=&SL=&LSL=undefined
//    https://ws.afb1188.com:8888/fnOddsGen?wst=wsSocAllGen&g=1&ot=r&wd=&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=0&tfDate=2019-03-25&LangCol=C&accType=MY&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&um=1|1317|22080&LID=&mt=0&FAV=&SL=&LSL=undefined
    //https://ws.afb1188.com:8888/fnOddsGen?wst=wsSocAllGen&g=1&ot=r&wd=&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=0&tfDate=2019-03-25&LangCol=C&accType=MY&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&um=1|1317|22080&LID=&mt=0&FAV=&SL=&LSL=

    public String URL_FIVE_MAJOR_RUNNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=200&ot=r&TFStatus=0&FAV=&SL=";
    public String URL_FIVE_MAJOR_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=200&ot=t&TFStatus=0&ia=0&FAV=&SL=";
    public String URL_FIVE_MAJOR_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=200&ot=e&TFStatus=0&update=false&FAV=&SL=";

    public String URL_FOOTBALL_RUNNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=1&ot=r&TFStatus=0&FAV=&SL=";
    public String URL_FOOTBALL_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=1&ot=t&TFStatus=0&ia=0&FAV=&SL=";
    public String URL_FOOTBALL_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=1&ot=e&TFStatus=0&update=false&FAV=&SL=";
    public String URL_FOOTBALL_EARLY_Mix = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&&g=4&ot=e4&FAV=&SL=";
    public String URL_FOOTBALL_TODAY_Mix = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=4&ot=t&FAV=&SL=";
    public String URL_FOOTBALL_OUT_RIGHT = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&&ot=r&TFStatus=0&FAV=&SL=";
    //wst=wsSocAllGen&g=31&ot=t&wd=&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=0&tfDate=2019-04-20&LangCol=&accType=EU&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&um=1|1317|22080
//    https://www.afb1188.com/pgajaxS.axd?T=MBH50&dbid=1&oId=703018&r=1553931820250&_=1553931794406
//    https://www.afb1188.com/pgajaxS.axd?T=MBH50&dbid=1&oId=726142&r=1553931973916&_=1553931794436 、、
//    https://www.afb1188.com/pgajaxS.axd?T=MBH50&dbid=1&oId=726142&r=1553931976885&_=1553931794437


//https://ws.afb1188.com:8887/fnOddsGen?wst=wsSocAllGen&g=9&ot=r&wd=&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=0&tfDate=2019-04-08&LangCol=C&accType=MY&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&um=1|1317|22080&LID=&mt=0&FAV=&SL=&LSL=undefined

    public String URL_BASKETBALL_RUNNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=9&ot=r&FAV=&SL=";
    public String URL_BASKETBALL_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=9&ot=t&FAV=&SL=";
    //wss://ws.afb1188.com:8887/                        fnOddsGen?wst=wsSocAllGen&g=9&ot=t&wd=&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=0&tfDate=2019-04-11&LangCol=C&accType=EU&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&um=1|1317|22080&LID=&mt=0&FAV=&SL=&LSL=undefined
    public String URL_BASKETBALL_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=9&ot=e&FAV=&SL=";

    public String URL_BASKETBALL_EARLY_Mix = HOST + "_view/ParGen.ashx?g=10&ot=e&FAV=&SL=";
    public String URL_BASKETBALL_TODAY_Mix = HOST + "_view/ParGen.ashx?g=10&ot=t&FAV=&SL=";
    public String URL_BASKETBALL_OUT_RIGHT = HOST + "_view/OddsOutGen.ashx?g=31&FAV=&SL=";


    public String URL_TENNIS_RUNNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=21&ot=r&FAV=&SL=";
    public String URL_TENNIS_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=21&ot=t&FAV=&SL=";
    public String URL_TENNIS_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=21&ot=e&FAV=&SL=";
    public String URL_TENNIS_OUTRIGHT = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=36&FAV=&SL=";

    //
    public String URL_FINANCIAL_RUNNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=7&ot=r&FAV=&SL=";
    public String URL_FINANCIAL_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=7&ot=t&FAV=&SL=";
    public String URL_FINANCIAL_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=7&ot=e&FAV=&SL=";

    //泰拳http://a8197c.a36588.com/_View/SocAllGen.ashx?g=108&ot=t&r=1661815184&LID=&_=1488527593418
    public String URL_MUAY_THAI_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=108&ot=t&FAV=&SL=";
    public String URL_MUAY_THAI_RUNNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=108&ot=r&FAV=&SL=";
    public String URL_MUAY_THAI_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=108&ot=e&FAV=&SL=";

    //http://a8197c.a36588.com/_View/SocAllGen.ashx?g=6&ot=t&r=740497289&LID=&_=1488958736003
    public String URL_4D_SPECIAL_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=6&ot=t&FAV=&SL=";
    public String URL_4D_SPECIAL_RUNNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=6&ot=r&FAV=&SL=";
    public String URL_4D_SPECIAL_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=6&ot=e&FAV=&SL=";

    //http://a8197c.a36588.com/_View/SocAllGen.ashx?g=106&ot=t&r=1098285451&LID=&_=1488958947996
    //http://main55.afb88.com/_View/SocAllGen.ashx?g=106&ot=t
    //http://main55.afb88.com/_View/SocAllGen.ashx?g=106&ot=r&r=596496978&LID=&_=1490255406588
    public String URL_E_SPORT_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=106&ot=t&FAV=&SL=";
    public String URL_E_SPORT_RUNNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=106&ot=r&FAV=&SL=";
    public String URL_E_SPORT_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=106&ot=e&FAV=&SL=";
    public String URL_E_SPORT_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=107&FAV=&SL=";
    //http://main55.afb88.com/_view/OddsOutGen.ashx?g=30&ot=e&r=741280160&LID=&_=1494315555262
    public String URL_US_FOOTBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=30&FAV=&SL=";

    //http://main55.afb88.com/_View/SocAllGen.ashx?g=12&ot=e&r=1259640348&LID=&_=1496371504834
    public String URL_US_FOOTBALL_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=12&ot=e&FAV=&SL=";
    public String URL_US_FOOTBALL_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=12&ot=t&FAV=&SL=";
    public String URL_US_FOOTBALL_RUNNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=12&ot=r&FAV=&SL=";
    //http://main55.afb88.com/_view/ParGen.ashx?g=13&ot=e&r=1990142665&LID=&_=1496373260537
    public String URL_US_FOOTBALL_EARLY_MIX = HOST + "_view/ParGen.ashx?g=13&ot=e&FAV=&SL=";
    public String URL_US_FOOTBALL_TODAY_MIX = HOST + "_view/ParGen.ashx?g=13&ot=t&FAV=&SL=";

//    http://a8206d.a36588.com/_view/MOddsGen2.ashx?ot=e&r=1056207418&wd=2017-03-22&ia=0&oview=1&LID=&_=1490092121779
    //http://a8206d.a36588.com/_view/MOddsGen2.ashx?ot=r&r=1278052286&LID=84733db3885dd967&_=1490092254366
    //http://a8206d.a36588.com/_view/MOddsGen2.ashx?ot=t&r=1278052286&LID=d784f703f22a941c&_=1490092254367

    public String URL_SOCCER_MYANMAR_RUNNING = HOST + "_view/MOddsGen2.ashx?g=106&ot=r&FAV=&SL=";
    public String URL_SOCCER_MYANMAR_TODAY = HOST + "_view/MOddsGen2.ashx?g=106&ot=t&FAV=&SL=";
    public String URL_SOCCER_MYANMAR_EARLY = HOST + "_view/MOddsGen2.ashx?g=106&ot=e&FAV=&SL=";
    //main55.afb88.com/_View/SocAllGen.ashx?g=29&ot=e&r=265506937&LID=&_=1496903153702
    public String URL_BASEBALL_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=29&ot=e&FAV=&SL=";
    public String URL_BASEBALL_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=29&ot=t&FAV=&SL=";
    public String URL_BASEBALL_RUNNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=29&ot=r&FAV=&SL=";
    //    http://main55.afb88.com/_view/OddsOutGen.ashx?g=39&ot=e&r=1288648832&LID=&_=1496903235059
    public String URL_BASEBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=39&FAV=&SL=";

    //http://main55.afb88.com/_view/OddsOutGen.ashx?g=39&ot=e&r=1078436031&LID=78a523670da52883&_=1494322797983
    public String URL_ICE_HOCKEY_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=33&FAV=&SL=";
    //http://main55.afb88.com/_View/SocAllGen.ashx?g=14&ot=e&r=922582020&LID=&_=1494322984276
    public String URL_ICE_HOCKEY_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=14&ot=t&FAV=&SL=";
    public String URL_ICE_HOCKEY_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=14&ot=r&FAV=&SL=";
    public String URL_ICE_HOCKEY_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=14&ot=e&FAV=&SL=";
    //
    public String URL_POOL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=32&FAV=&SL=";
    public String URL_POOL_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=11&ot=t&FAV=&SL=";
    public String URL_POOL_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=11&ot=r&FAV=&SL=";
    public String URL_POOL_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=11&ot=e&FAV=&SL=";
    //
    public String URL_RUGBY_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=34&FAV=&SL=";
    public String URL_RUGBY_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=17&ot=t&FAV=&SL=";
    public String URL_RUGBY_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=17&ot=r&FAV=&SL=";
    public String URL_RUGBY_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=17&ot=e&FAV=&SL=";
    //http://main55.afb88.com/_View/SocAllGen.ashx?g=19&ot=e&r=1676840231&LID=&_=1496913931893
    //http://main55.afb88.com/_view/OddsOutGen.ashx?g=35&ot=e&r=1836836693&LID=&_=1496913986764
    //http://main55.afb88.com/_view/ParGen.ashx?g=20&ot=e&r=1508063117&LID=&_=1496914016514
    public String URL_DARTS_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=35&FAV=&SL=";
    public String URL_DARTS_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=19&ot=e&FAV=&SL=";
    public String URL_DARTS_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=19&ot=t&FAV=&SL=";
    public String URL_DARTS_RUNNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=19&ot=r&FAV=&SL=";
    public String URL_DARTS_EARLY_MIX = HOST + "_view/ParGen.ashx?g=20&ot=e&FAV=&SL=";
    public String URL_DARTS_TODAY_MIX = HOST + "_view/ParGen.ashx?g=20&ot=t&FAV=&SL=";
    //http://main55.afb88.com/_View/SocAllGen.ashx?g=16&ot=e&r=1018773772&LID=&_=1496914467750
    public String URL_BOXING_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=92&FAV=&SL=";
    public String URL_BOXING_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=16&ot=t&FAV=&SL=";
    public String URL_BOXING_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=16&ot=r&FAV=&SL=";
    public String URL_BOXING_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=16&ot=e&FAV=&SL=";
    //
    public String URL_GOLF_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=37&FAV=&SL=";
    public String URL_GOLF_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=22&ot=t&FAV=&SL=";
    public String URL_GOLF_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=22&ot=r&FAV=&SL=";
    public String URL_GOLF_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=22&ot=e&FAV=&SL=";

    //http://main55.afb88.com/_View/SocAllGen.ashx?g=51&ot=t&r=448891026&LID=&_=1497233419847
    public String URL_BADMINTON_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=52&FAV=&SL=";
    public String URL_BADMINTON_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=51&ot=t&FAV=&SL=";
    public String URL_BADMINTON_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=51&ot=r&FAV=&SL=";
    public String URL_BADMINTON_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=51&ot=e&FAV=&SL=";
    //http://main55.afb88.com/_View/SocAllGen.ashx?g=44&ot=e&r=2130678057&LID=&_=1497238234614
    public String URL_VOLLEYBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=62&FAV=&SL=";
    public String URL_VOLLEYBALL_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=23&ot=t&FAV=&SL=";
    public String URL_VOLLEYBALL_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=23&ot=r&FAV=&SL=";
    public String URL_VOLLEYBALL_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=23&ot=e&FAV=&SL=";
    //
    public String URL_CRICKET_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=61&FAV=&SL=";
    public String URL_CRICKET_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=44&ot=t&FAV=&SL=";
    public String URL_CRICKET_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=44&ot=r&FAV=&SL=";
    public String URL_CRICKET_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=44&ot=e&FAV=&SL=";
    //
    public String URL_HANDBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=43&FAV=&SL=";
    public String URL_HANDBALL_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=41&ot=t&FAV=&SL=";
    public String URL_HANDBALL_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=41&ot=r&FAV=&SL=";
    public String URL_HANDBALL_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=41&ot=e&FAV=&SL=";
    //65
    public String URL_CYCLING_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=64&FAV=&SL=";
    public String URL_CYCLING_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=65&ot=t&FAV=&SL=";
    public String URL_CYCLING_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=65&ot=r&FAV=&SL=";
    public String URL_CYCLING_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=65&ot=e&FAV=&SL=";
    //103
    public String URL_WINTER_SPORT_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=104&FAV=&SL=";
    public String URL_WINTER_SPORT_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=103&ot=t&FAV=&SL=";
    public String URL_WINTER_SPORT_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=103&ot=r&FAV=&SL=";
    public String URL_WINTER_SPORT_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=103&ot=e&FAV=&SL=";
    //103
//    http://main55.afb88.com/_View/SocAllGen.ashx?g=99&ot=e&r=603659260&LID=&_=1497239829685
    public String URL_SUPER_COMBO_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=99&ot=t&FAV=&SL=";
    public String URL_SUPER_COMBO_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=99&ot=r&FAV=&SL=";
    public String URL_SUPER_COMBO_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=99&ot=e&FAV=&SL=";
    //http://main55.afb88.com/_View/SocAllGen.ashx?g=25&ot=e&r=1912041035&LID=&_=1497859662375
    public String URL_FORMULA_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=27&FAV=&SL=";
    public String URL_FORMULA_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=25&ot=t&FAV=&SL=";
    public String URL_FORMULA_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=25&ot=r&FAV=&SL=";
    public String URL_FORMULA_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=25&ot=e&FAV=&SL=";
    //http://main55.afb88.com/_View/SocAllGen.ashx?g=25&ot=e&r=674224967&LID=&_=1497859548627
    public String URL_TABLE_TENNIS_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=58&FAV=&SL=";
    public String URL_TABLE_TENNIS_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=57&ot=t&FAV=&SL=";
    public String URL_TABLE_TENNIS_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=57&ot=r&FAV=&SL=";
    public String URL_TABLE_TENNIS_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=57&ot=e&FAV=&SL=";


    /*    public String URL_EUROPE_TODAY;
        public String URL_EUROPE_RUNING;
        public String URL_EUROPE_EARLY;
        public String URL_EUROPE_MIX_EARLY;
        public String URL_EUROPE_MIX_TODAY;*/
    public String URL_EUROPE_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=182&ot=t&FAV=&SL=";
    public String URL_EUROPE_RUNING = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=182&ot=r&FAV=&SL=";
    public String URL_EUROPE_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=182&ot=e&FAV=&SL=";
    public String URL_EUROPE_MIX_EARLY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=183&ot=e&FAV=&SL=";
    public String URL_EUROPE_MIX_TODAY = BuildConfig.HOST_SPORT + "?wst=wsSocAllGen&g=183&ot=t&FAV=&SL=";

    public String URL_TRANSFER_MONEY_DATA = HOST + "Bet/PanelBalance_App.aspx?";//好的
    public String URL_CASHOUT_MONEY_E_GAMES = HOST + "Bet/FundEGCashOut.aspx";
    public String URL_TRANSFER_MONEY_E_GAMES = HOST + "Bet/FundEGTransfer.aspx";
    public String URL_CASHOUT_MONEY_GD_GAMES = HOST + "Bet/FundLDCashOut.aspx";
    public String URL_TRANSFER_MONEY_GD_GAMES = HOST + "Bet/FundLDTransfer.aspx";
    public String URL_CASHOUT_MONEY_855_GAMES = HOST + "Bet/FundLDCCashOut.aspx";
    public String URL_TRANSFER_MONEY_855_GAMES = HOST + "Bet/FundLDCTransfer.aspx";
    public String URL_CASHOUT_MONEY_W88_GAMES = HOST + "Bet/FundLDDCashOut.aspx";
    public String URL_TRANSFER_MONEY_W88_GAMES = HOST + "Bet/FundLDDTransfer.aspx";
    public String URL_CHANGE_LANGUAGE = HOST + "Main.aspx?";

    public String URL_APamTransDetail = HOST + "_norm/APamTransDetail.aspx?";

    //http://www.afb1188.com/W0/Pub/LiveCast.aspx?Id=1051254&oId=440795&Home=%E5%B8%83%E9%87%8C%E6%96%AF%E7%8F%AD%E7%8B%AE%E5%90%BC&Away=%E5%A2%A8%E5%B0%94%E6%9C%AC%E5%9F%8E&L=en
    public String URL_RUNNING_MATCH_WEB = HOST + "W0/Pub/LiveCast.aspx";

    public String URL_STAEMENT = HOST + "_norm/AccHistory_App.aspx?";//好的
    public String URL_STAKE = HOST + "Bet/PanelStake_App.aspx";//haizai nong
    public String URL_STATEMENT_CONFIRM_BLANCE = HOST + "_norm/AccConfirmDate_App.aspx?userName=";
    public String URL_PANEL = HOST + "Bet/panel.aspx";
    //    http://main55.afb88.net/_view/kenGame_App.aspx
    public String URL_KENO_DATA = HOST + "_view/kenGame_App.aspx";//不管
    public String URL_KENO_STATU_DATA = HOST + "Bet/panel_keno_App.aspx?";
    public String URL_KENO_BET = HOST + "Bet/panel_kenoBet_App.aspx?";
    static AppConstant instance;
    private String host;

    public void setHost(String host) {
        this.HOST = host;
        URL_MAIN = HOST + "main.aspx";
        URL_UPDATE_BALANCE = HOST + "Bet/PanelStakeBalance_App.aspx";
        URL_UPDATE_STATE = HOST + "pgajaxS.axd?T=CHKST&P=001";
        URL_ODDS_TYPE = HOST + "Main.aspx?accType=";
        URL_ODDS = HOST + "Bet/JRecPanel_App.aspx?";
        URL_SOCCER_REMOVE_MIX = HOST + "Bet/ParRemove.aspx";
        //http://main55.afb88.com/_view/Result.aspx
        URL_RESULT = HOST + "_view/Result_App.aspx";

        URL_FOOTBALL_RUNNING = HOST + "_View/SocAllGen.ashx?ot=r&TFStatus=0";
        URL_FOOTBALL_TODAY = HOST + "_View/SocAllGen.ashx?ot=t&TFStatus=0&ia=0";
        URL_FOOTBALL_EARLY = HOST + "_View/SocAllGen.ashx?ot=e&TFStatus=0&update=false";
        URL_FOOTBALL_EARLY_Mix = HOST + "_View/SocAllGen.ashx?g=4&ot=e";
        URL_FOOTBALL_TODAY_Mix = HOST + "_View/SocAllGen.ashx?g=4&ot=t";
        URL_FOOTBALL_OUT_RIGHT = HOST + "_view/OddsGen50.ashx?";

        URL_BASKETBALL_RUNNING = HOST + "_View/SocAllGen.ashx?g=9&ot=r";
        URL_BASKETBALL_TODAY = HOST + "_View/SocAllGen.ashx?g=9&ot=t";
        URL_BASKETBALL_EARLY = HOST + "_View/SocAllGen.ashx?g=9&ot=e";

        URL_BASKETBALL_EARLY_Mix = HOST + "_view/ParGen.ashx?g=10&ot=e";
        URL_BASKETBALL_TODAY_Mix = HOST + "_view/ParGen.ashx?g=10&ot=t";
        URL_BASKETBALL_OUT_RIGHT = HOST + "_view/OddsOutGen.ashx?g=31";

        URL_TENNIS_RUNNING = HOST + "_View/SocAllGen.ashx?g=21&ot=r";
        URL_TENNIS_TODAY = HOST + "_View/SocAllGen.ashx?g=21&ot=t";
        URL_TENNIS_EARLY = HOST + "_View/SocAllGen.ashx?g=21&ot=e";
        URL_TENNIS_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=36";

        URL_FINANCIAL_RUNNING = HOST + "_View/SocAllGen.ashx?g=7&ot=r";
        URL_FINANCIAL_TODAY = HOST + "_View/SocAllGen.ashx?g=7&ot=t";
        URL_FINANCIAL_EARLY = HOST + "_View/SocAllGen.ashx?g=7&ot=e";

        URL_MUAY_THAI_TODAY = HOST + "_View/SocAllGen.ashx?g=108&ot=t";
        URL_MUAY_THAI_RUNNING = HOST + "_View/SocAllGen.ashx?g=108&ot=r";
        URL_MUAY_THAI_EARLY = HOST + "_View/SocAllGen.ashx?g=108&ot=e";

        URL_4D_SPECIAL_TODAY = HOST + "_View/SocAllGen.ashx?g=6&ot=t";
        URL_4D_SPECIAL_RUNNING = HOST + "_View/SocAllGen.ashx?g=6&ot=r";
        URL_4D_SPECIAL_EARLY = HOST + "_View/SocAllGen.ashx?g=6&ot=e";

        URL_E_SPORT_TODAY = HOST + "_View/SocAllGen.ashx?g=106&ot=t";
        URL_E_SPORT_RUNNING = HOST + "_View/SocAllGen.ashx?g=106&ot=r";
        URL_E_SPORT_EARLY = HOST + "_View/SocAllGen.ashx?g=106&ot=e";
        URL_E_SPORT_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=107";

        URL_US_FOOTBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=30";
        URL_US_FOOTBALL_EARLY = HOST + "_View/SocAllGen.ashx?g=12&ot=e";
        URL_US_FOOTBALL_TODAY = HOST + "_View/SocAllGen.ashx?g=12&ot=t";
        URL_US_FOOTBALL_RUNNING = HOST + "_View/SocAllGen.ashx?g=12&ot=r";
        URL_US_FOOTBALL_EARLY_MIX = HOST + "_view/ParGen.ashx?g=13&ot=e";
        URL_US_FOOTBALL_TODAY_MIX = HOST + "_view/ParGen.ashx?g=13&ot=t";
        //
        URL_SOCCER_MYANMAR_RUNNING = HOST + "_view/MOddsGen2.ashx?g=106&ot=r";
        URL_SOCCER_MYANMAR_TODAY = HOST + "_view/MOddsGen2.ashx?g=106&ot=t";
        URL_SOCCER_MYANMAR_EARLY = HOST + "_view/MOddsGen2.ashx?g=106&ot=e";

        URL_BASEBALL_EARLY = HOST + "_View/SocAllGen.ashx?g=29&ot=e";
        URL_BASEBALL_TODAY = HOST + "_View/SocAllGen.ashx?g=29&ot=t";
        URL_BASEBALL_RUNNING = HOST + "_View/SocAllGen.ashx?g=29&ot=r";
        URL_BASEBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=39";

        //http://main55.afb88.com/_View/SocAllGen.ashx?g=14&ot=r&r=1545705412
        //http://main55.afb88.com/_view/OddsOutGen.ashx?g=33&ot=e&r=1341186982&LID=&_=1496905566929
        URL_ICE_HOCKEY_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=33";
        URL_ICE_HOCKEY_TODAY = HOST + "_View/SocAllGen.ashx?g=14&ot=t";
        URL_ICE_HOCKEY_RUNING = HOST + "_View/SocAllGen.ashx?g=14&ot=r";
        URL_ICE_HOCKEY_EARLY = HOST + "_View/SocAllGen.ashx?g=14&ot=e";

        //http://main55.afb88.com/_View/SocAllGen.ashx?g=11&ot=t&r=340371970&LID=&_=1496906053812
        //http://main55.afb88.com/_view/OddsOutGen.ashx?g=32&ot=e&r=1492002023&LID=&_=1496906224047
        URL_POOL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=32";
        URL_POOL_TODAY = HOST + "_View/SocAllGen.ashx?g=11&ot=t";
        URL_POOL_RUNING = HOST + "_View/SocAllGen.ashx?g=11&ot=r";
        URL_POOL_EARLY = HOST + "_View/SocAllGen.ashx?g=11&ot=e";
        //http://main55.afb88.com/_View/SocAllGen.ashx?g=17&ot=e&r=2080506517&LID=&_=1496906889008
        //http://main55.afb88.com/_view/OddsOutGen.ashx?g=34&ot=e&r=1222999374&LID=&_=1496906975411
        URL_RUGBY_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=34";
        URL_RUGBY_TODAY = HOST + "_View/SocAllGen.ashx?g=17&ot=t";
        URL_RUGBY_RUNING = HOST + "_View/SocAllGen.ashx?g=17&ot=r";
        URL_RUGBY_EARLY = HOST + "_View/SocAllGen.ashx?g=17&ot=e";
        //
        URL_DARTS_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=35&r=1887771784";
        URL_DARTS_EARLY = HOST + "_View/SocAllGen.ashx?g=19&ot=e";
        URL_DARTS_TODAY = HOST + "_View/SocAllGen.ashx?g=19&ot=t";
        URL_DARTS_RUNNING = HOST + "_View/SocAllGen.ashx?g=19&ot=r";
        URL_DARTS_EARLY_MIX = HOST + "_view/ParGen.ashx?g=20&ot=e";
        URL_DARTS_TODAY_MIX = HOST + "_view/ParGen.ashx?g=20&ot=t";
        //
        URL_BOXING_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=92";
        URL_BOXING_TODAY = HOST + "_View/SocAllGen.ashx?g=16&ot=t";
        URL_BOXING_RUNING = HOST + "_View/SocAllGen.ashx?g=16&ot=r";
        URL_BOXING_EARLY = HOST + "_View/SocAllGen.ashx?g=16&ot=e";
        //http://main55.afb88.com/_View/SocAllGen.ashx?g=22&ot=t&r=158540506&LID=&_=1496915863924
        URL_GOLF_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=37";
        URL_GOLF_TODAY = HOST + "_View/SocAllGen.ashx?g=22&ot=t";
        URL_GOLF_RUNING = HOST + "_View/SocAllGen.ashx?g=22&ot=r";
        URL_GOLF_EARLY = HOST + "_View/SocAllGen.ashx?g=22&ot=e";
        //http://main55.afb88.com/_View/SocAllGen.ashx?g=51&ot=t&r=448891026&LID=&_=1497233419847
        URL_BADMINTON_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=52";
        URL_BADMINTON_TODAY = HOST + "_View/SocAllGen.ashx?g=51&ot=t";
        URL_BADMINTON_RUNING = HOST + "_View/SocAllGen.ashx?g=51&ot=r";
        URL_BADMINTON_EARLY = HOST + "_View/SocAllGen.ashx?g=51&ot=e";
        //http://main55.afb88.com/_View/SocAllGen.ashx?g=44&ot=e&r=2130678057&LID=&_=1497238234614
        URL_VOLLEYBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=62";
        URL_VOLLEYBALL_TODAY = HOST + "_View/SocAllGen.ashx?g=23&ot=t";
        URL_VOLLEYBALL_RUNING = HOST + "_View/SocAllGen.ashx?g=23&ot=r";
        URL_VOLLEYBALL_EARLY = HOST + "_View/SocAllGen.ashx?g=23&ot=e";
        //
        URL_CRICKET_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=61";
        URL_CRICKET_TODAY = HOST + "_View/SocAllGen.ashx?g=44&ot=t";
        URL_CRICKET_RUNING = HOST + "_View/SocAllGen.ashx?g=44&ot=r";
        URL_CRICKET_EARLY = HOST + "_View/SocAllGen.ashx?g=44&ot=e";
        //
        URL_HANDBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=43";
        URL_HANDBALL_TODAY = HOST + "_View/SocAllGen.ashx?g=41&ot=t";
        URL_HANDBALL_RUNING = HOST + "_View/SocAllGen.ashx?g=41&ot=r";
        URL_HANDBALL_EARLY = HOST + "_View/SocAllGen.ashx?g=41&ot=e";
        //65
        URL_CYCLING_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=64";
        URL_CYCLING_TODAY = HOST + "_View/SocAllGen.ashx?g=65&ot=t";
        URL_CYCLING_RUNING = HOST + "_View/SocAllGen.ashx?g=65&ot=r";
        URL_CYCLING_EARLY = HOST + "_View/SocAllGen.ashx?g=65&ot=e";
        //103
        URL_WINTER_SPORT_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=104";
        URL_WINTER_SPORT_TODAY = HOST + "_View/SocAllGen.ashx?g=103&ot=t";
        URL_WINTER_SPORT_RUNING = HOST + "_View/SocAllGen.ashx?g=103&ot=r";
        URL_WINTER_SPORT_EARLY = HOST + "_View/SocAllGen.ashx?g=103&ot=e";
        //103
//    http://main55.afb88.com/_View/SocAllGen.ashx?g=99&ot=e&r=603659260&LID=&_=1497239829685
        URL_SUPER_COMBO_TODAY = HOST + "_View/SocAllGen.ashx?g=99&ot=t";
        URL_SUPER_COMBO_RUNING = HOST + "_View/SocAllGen.ashx?g=99&ot=r";
        URL_SUPER_COMBO_EARLY = HOST + "_View/SocAllGen.ashx?g=99&ot=e";
        //57http://main55.afb88.com/_View/SocAllGen.ashx?g=57&ot=r&r=764149212&LID=&_=1497599416541
        URL_TABLE_TENNIS_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=58";
        URL_TABLE_TENNIS_TODAY = HOST + "_View/SocAllGen.ashx?g=57&ot=t";
        URL_TABLE_TENNIS_RUNING = HOST + "_View/SocAllGen.ashx?g=57&ot=r";
        URL_TABLE_TENNIS_EARLY = HOST + "_View/SocAllGen.ashx?g=57&ot=e";


        //http://main55.afb88.com/_View/SocAllGen.ashx?g=182&ot=r&LID=ba82fa925e9d154e&_=1495597268312
        URL_EUROPE_TODAY = HOST + "_View/SocAllGen.ashx?g=182&ot=t";
        URL_EUROPE_RUNING = HOST + "_View/SocAllGen.ashx?g=182&ot=r";
        URL_EUROPE_EARLY = HOST + "_View/SocAllGen.ashx?g=182&ot=e";
        URL_EUROPE_MIX_EARLY = HOST + "_View/SocAllGen.ashx?g=183&ot=e";
        URL_EUROPE_MIX_TODAY = HOST + "_View/SocAllGen.ashx?g=183&ot=t";

        URL_TRANSFER_MONEY_DATA = HOST + "Bet/PanelBalance_App.aspx?";
        URL_CASHOUT_MONEY_E_GAMES = HOST + "Bet/FundEGCashOut.aspx";
        URL_TRANSFER_MONEY_E_GAMES = HOST + "Bet/FundEGTransfer.aspx";
        URL_CASHOUT_MONEY_GD_GAMES = HOST + "Bet/FundLDCashOut.aspx";
        URL_TRANSFER_MONEY_GD_GAMES = HOST + "Bet/FundLDTransfer.aspx";
        URL_CASHOUT_MONEY_855_GAMES = HOST + "Bet/FundLDCCashOut.aspx";
        URL_TRANSFER_MONEY_855_GAMES = HOST + "Bet/FundLDCTransfer.aspx";
        URL_CASHOUT_MONEY_W88_GAMES = HOST + "Bet/FundLDDCashOut.aspx";
        URL_TRANSFER_MONEY_W88_GAMES = HOST + "Bet/FundLDDTransfer.aspx";
        URL_CHANGE_LANGUAGE = HOST + "Main.aspx?";

        URL_APamTransDetail = HOST + "_norm/APamTransDetail.aspx?";

        URL_RUNNING_MATCH_WEB = HOST + "W0/Pub/LiveCast.aspx";
        URL_PANEL = HOST + "Bet/panel.aspx";
        URL_STAEMENT = HOST + "_norm/AccHistory_App.aspx?";
        URL_STAKE = HOST + "Bet/PanelStake_App.aspx";
        URL_STATEMENT_CONFIRM_BLANCE = HOST + "_norm/AccConfirmDate_App.aspx?role=mb&userName=";

        URL_KENO_DATA = HOST + "_view/kenGame_App.aspx";
        URL_KENO_STATU_DATA = HOST + "Bet/panel_keno_App.aspx?";
        URL_KENO_BET = HOST + "Bet/panel_kenoBet_App.aspx?";
        URL_ODDS_AFB1188 = HOST + "Bet/hBetOdds.ashx?";
        URL_ODDS = URL_ODDS_AFB1188;
    }

    public static AppConstant getInstance() {
        if (instance == null)
            instance = new AppConstant();
        return instance;
    }
}
