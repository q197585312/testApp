package com.nanyang.app;

/**
 * Created by Administrator on 2017/2/10.
 */

public class AppConstant {


    public static final String DownLoadDig88AppUrl = "http://appgd88.com/gd88/download/android/gd88.apk";
    public static final String CHECK_VERSION = "http://www.appgd88.com/afb1188version.php?app=android";
    public static final String DOWNLOAD_APP = "http://appgd88.com/afb1188/download/android/afb1188.apk";

//http://www.afb1188.com/W0/Pub/pcode.axd


    public static String KEY_STRING = "KEY_STRING";
    public static String KEY_DATA = "KEY_DATA";
    public static String KEY_INT = "KEY_INT";
    public static String KEY_BOOLEAN = "KEY_BOOLEAN";
    public static String KEY_DATA2 = "KEY_DATA2";
    public static String KEY_DATA3 = "KEY_DATA3";


    /**
     * http://a8197c.a36588.com/_View/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=153004462
     * RUNING
     * http://a8197c.a36588.com/_View/RMOddsGen1.ashx?ot=t&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=153004462&wd=&ia=0
     * Today
     * http://a8197c.a36588.com/_View/RMOddsGen1.ashx?ot=e&ov=0&mt=0&tf=2&TFStatus=0&update=false&r=246503518&wd=2017-02-16&ia=0
     * Early
     */
//    http://a0096f.panda88.org/_View/SocAllGen.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=431367264&LID=&_=1488465754049
    //盘口
//    http://a0096f.panda88.org/Main.aspx?accType=HK
//            http://a0096f.panda88.org/Main.aspx?accType=MY
    //http://a8197c.a36588.com/Main.aspx?accType=ID
    //http://a8197c.a36588.com/Main.aspx?accType=EU

//    public static String HOST="http://www.doo88.net/";
    public String HOST =  BuildConfig.HOST_AFB;
//http://www.afb1188.com/W0/Pub/pcode.axd
    public String URL_LOGIN = HOST + "W0/Pub/pcode.axd";///W0/Pub/pcode.axd
//    、、/W0/Pub/wfMain0.html
    public String URL_MAIN = HOST + "W0/Pub/wfMain0.html";

    public String _BET="Bet/";

    public String URL_UPDATE_BALANCE = HOST + "Bet/PanelStakeBalance_App.aspx";//http://103.206.122.65/Bet/BetStake.ashx
    public String URL_UPDATE_STATE = HOST + "pgajaxS.axd?T=CHKST&P=001";
    public String URL_ODDS_TYPE = HOST + "W0/Pub/wfMain0.html?accType=";//http://103.206.122.65/W0/Pub/wfMain0.html?accType=HK
    //?tp=0&fh=1&g=1&ot=t&tf=-1&TFStatus=0&update=false&r=1581083541&wd=2018-11-28&ia=0&accType=MY&LID=&ov=0&mt=0&FAV=&SL=&_=1543307725540
    public String URL_FOOTBALL_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=1&ot=r&ov=0&tf=-1&TFStatus=0&update=false&r=153004462&FAV=&SL=&_=1111";
    public String URL_FOOTBALL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=1&ot=t&ov=0&tf=-1&TFStatus=0&update=false&r=153004462&wd=&ia=0&FAV=&SL=&_=1111";
    public String URL_FOOTBALL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=1&ot=e&ov=0&tf=2&TFStatus=0&update=false&r=246503518&FAV=&SL=&_=1111";
    public String URL_FOOTBALL_EARLY_Mix = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=4&ot=e&update=true&r=866546094&FAV=&SL=&_=1111";
    public String URL_FOOTBALL_TODAY_Mix = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=4&ot=t&update=true&r=1027945624&FAV=&SL=&_=1111";
    public String URL_FOOTBALL_OUT_RIGHT = HOST + "_view/OddsGen50.ashx?update=true&r=2076712019&FAV=&SL=&_=1111";


    public String URL_ODDS_AFB1188 = HOST + "Bet/hBetOdds.ashx?";
//    public String URL_ODDS = HOST + "Bet/JRecPanel_App.aspx?";
    public String URL_ODDS = URL_ODDS_AFB1188;
    public String URL_SOCCER_REMOVE_MIX = HOST + "Bet/ParRemove.aspx";


    public String URL_BASKETBALL_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=9&ot=r&update=true&r=757267892&FAV=&SL=&_=1111";
    public String URL_BASKETBALL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=9&ot=t&update=true&r=757267892&FAV=&SL=&_=1111";
    public String URL_BASKETBALL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=9&ot=e&update=true&r=2033192118&FAV=&SL=&_=1111";

    public String URL_BASKETBALL_EARLY_Mix = HOST + "_view/ParGen.ashx?g=10&ot=e&update=true&r=866546094&FAV=&SL=&_=1111";
    public String URL_BASKETBALL_TODAY_Mix = HOST + "_view/ParGen.ashx?g=10&ot=t&update=true&r=408754578&FAV=&SL=&_=1111";
    public String URL_BASKETBALL_OUT_RIGHT = HOST + "_view/OddsOutGen.ashx?g=31&update=true&r=1267181584&FAV=&SL=&_=1111";


    public String URL_TENNIS_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=21&ot=r&update=true&r=442357900&FAV=&SL=&_=1111";
    public String URL_TENNIS_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=21&ot=t&update=true&r=1323982190&FAV=&SL=&_=1111";
    public String URL_TENNIS_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=21&ot=e&update=true&r=876540788&FAV=&SL=&_=1111";
    public String URL_TENNIS_OUTRIGHT = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=36&update=true&r=446070790&FAV=&SL=&_=1111";

    //
    public String URL_FINANCIAL_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=7&ot=r&update=true&r=442357900&FAV=&SL=&_=1111";
    public String URL_FINANCIAL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=7&ot=t&update=true&r=442357900&FAV=&SL=&_=1111";
    public String URL_FINANCIAL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=7&ot=e&update=true&r=1634139687&FAV=&SL=&_=1111";

    //泰拳http://a8197c.a36588.com/_View/SocAllGen.ashx?tp=0&fh=1&g=108&ot=t&update=true&r=1661815184&LID=&_=1488527593418
    public String URL_MUAY_THAI_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=108&ot=t&update=true&r=1233177815&FAV=&SL=&_=1111";
    public String URL_MUAY_THAI_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=108&ot=r&update=true&r=1770989484&FAV=&SL=&_=1111";
    public String URL_MUAY_THAI_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=108&ot=e&update=true&r=1770989484&FAV=&SL=&_=1111";

    //http://a8197c.a36588.com/_View/SocAllGen.ashx?tp=0&fh=1&g=6&ot=t&update=true&r=740497289&LID=&_=1488958736003
    public String URL_4D_SPECIAL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=6&ot=t&update=true&r=1233177815&FAV=&SL=&_=1111";
    public String URL_4D_SPECIAL_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=6&ot=r&update=true&r=1770989484&FAV=&SL=&_=1111";
    public String URL_4D_SPECIAL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=6&ot=e&update=true&r=1770989484&FAV=&SL=&_=1111";

    //http://a8197c.a36588.com/_View/SocAllGen.ashx?tp=0&fh=1&g=106&ot=t&update=true&r=1098285451&LID=&_=1488958947996
    //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=106&ot=t&update=true&r=1233177815
    //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=106&ot=r&update=true&r=596496978&LID=&_=1490255406588
    public String URL_E_SPORT_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=106&ot=t&update=true&r=1233177815&FAV=&SL=&_=1111";
    public String URL_E_SPORT_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=106&ot=r&update=true&r=1770989484&FAV=&SL=&_=1111";
    public String URL_E_SPORT_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=106&ot=e&update=true&r=1770989484&FAV=&SL=&_=1111";
    public String URL_E_SPORT_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=107&update=true&r=1770989484&FAV=&SL=&_=1111";
    //http://main55.afb88.com/_view/OddsOutGen.ashx?g=30&ot=e&update=true&r=741280160&LID=&_=1494315555262
    public String URL_US_FOOTBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=30&update=true&r=1887771784&FAV=&SL=&_=1111";

    //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=12&ot=e&update=true&r=1259640348&LID=&_=1496371504834
    public String URL_US_FOOTBALL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=12&ot=e&update=true&r=927620479&FAV=&SL=&_=1111";
    public String URL_US_FOOTBALL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=12&ot=t&update=true&r=927620479&FAV=&SL=&_=1111";
    public String URL_US_FOOTBALL_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=12&ot=r&update=true&r=927620479&FAV=&SL=&_=1111";
    //http://main55.afb88.com/_view/ParGen.ashx?g=13&ot=e&update=true&r=1990142665&LID=&_=1496373260537
    public String URL_US_FOOTBALL_EARLY_MIX = HOST + "_view/ParGen.ashx?g=13&ot=e&update=true&r=927620479&FAV=&SL=&_=1111";
    public String URL_US_FOOTBALL_TODAY_MIX = HOST + "_view/ParGen.ashx?g=13&ot=t&update=true&r=927620479&FAV=&SL=&_=1111";

//    http://a8206d.a36588.com/_view/MOddsGen2.ashx?ot=e&update=true&r=1056207418&wd=2017-03-22&ia=0&oview=1&LID=&_=1490092121779
    //http://a8206d.a36588.com/_view/MOddsGen2.ashx?ot=r&update=true&r=1278052286&LID=84733db3885dd967&_=1490092254366
    //http://a8206d.a36588.com/_view/MOddsGen2.ashx?ot=t&update=true&r=1278052286&LID=d784f703f22a941c&_=1490092254367

    public String URL_SOCCER_MYANMAR_RUNNING = HOST + "_view/MOddsGen2.ashx?g=106&ot=r&update=true&r=1233177815&FAV=&SL=&_=1111";
    public String URL_SOCCER_MYANMAR_TODAY = HOST + "_view/MOddsGen2.ashx?g=106&ot=t&update=true&r=1233177815&FAV=&SL=&_=1111";
    public String URL_SOCCER_MYANMAR_EARLY = HOST + "_view/MOddsGen2.ashx?g=106&ot=e&update=true&r=1233177815&FAV=&SL=&_=1111";
    //main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=29&ot=e&update=true&r=265506937&LID=&_=1496903153702
    public String URL_BASEBALL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=29&ot=e&update=true&r=445360998&FAV=&SL=&_=1111";
    public String URL_BASEBALL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=29&ot=t&update=true&r=445360998&FAV=&SL=&_=1111";
    public String URL_BASEBALL_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=29&ot=r&update=true&r=445360998&FAV=&SL=&_=1111";
    //    http://main55.afb88.com/_view/OddsOutGen.ashx?g=39&ot=e&update=true&r=1288648832&LID=&_=1496903235059
    public String URL_BASEBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=39&update=true&r=1186326052&FAV=&SL=&_=1111";

    //http://main55.afb88.com/_view/OddsOutGen.ashx?g=39&ot=e&update=true&r=1078436031&LID=78a523670da52883&_=1494322797983
    public String URL_ICE_HOCKEY_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=33&update=true&r=1186326052&FAV=&SL=&_=1111";
    //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=14&ot=e&update=true&r=922582020&LID=&_=1494322984276
    public String URL_ICE_HOCKEY_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=14&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_ICE_HOCKEY_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=14&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_ICE_HOCKEY_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=14&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //
    public String URL_POOL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=32&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_POOL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=11&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_POOL_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=11&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_POOL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=11&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //
    public String URL_RUGBY_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=34&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_RUGBY_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=17&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_RUGBY_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=17&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_RUGBY_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=17&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=19&ot=e&update=true&r=1676840231&LID=&_=1496913931893
    //http://main55.afb88.com/_view/OddsOutGen.ashx?g=35&ot=e&update=true&r=1836836693&LID=&_=1496913986764
    //http://main55.afb88.com/_view/ParGen.ashx?g=20&ot=e&update=true&r=1508063117&LID=&_=1496914016514
    public String URL_DARTS_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=35&update=true&r=1887771784&FAV=&SL=&_=1111";
    public String URL_DARTS_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=19&ot=e&update=true&r=927620479&FAV=&SL=&_=1111";
    public String URL_DARTS_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=19&ot=t&update=true&r=927620479&FAV=&SL=&_=1111";
    public String URL_DARTS_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=19&ot=r&update=true&r=927620479&FAV=&SL=&_=1111";
    public String URL_DARTS_EARLY_MIX = HOST + "_view/ParGen.ashx?g=20&ot=e&update=true&r=927620479&FAV=&SL=&_=1111";
    public String URL_DARTS_TODAY_MIX = HOST + "_view/ParGen.ashx?g=20&ot=t&update=true&r=927620479&FAV=&SL=&_=1111";
    //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=16&ot=e&update=true&r=1018773772&LID=&_=1496914467750
    public String URL_BOXING_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=92&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_BOXING_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=16&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_BOXING_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=16&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_BOXING_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=16&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //
    public String URL_GOLF_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=37&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_GOLF_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=22&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_GOLF_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=22&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_GOLF_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=22&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";

    //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=51&ot=t&update=true&r=448891026&LID=&_=1497233419847
    public String URL_BADMINTON_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=52&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_BADMINTON_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=51&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_BADMINTON_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=51&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_BADMINTON_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=51&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=44&ot=e&update=true&r=2130678057&LID=&_=1497238234614
    public String URL_VOLLEYBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=62&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_VOLLEYBALL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=23&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_VOLLEYBALL_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=23&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_VOLLEYBALL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=23&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //
    public String URL_CRICKET_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=61&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_CRICKET_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=44&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_CRICKET_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=44&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_CRICKET_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=44&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //
    public String URL_HANDBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=43&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_HANDBALL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=41&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_HANDBALL_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=41&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_HANDBALL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=41&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //65
    public String URL_CYCLING_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=64&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_CYCLING_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=65&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_CYCLING_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=65&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_CYCLING_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=65&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //103
    public String URL_WINTER_SPORT_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=104&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_WINTER_SPORT_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=103&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_WINTER_SPORT_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=103&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_WINTER_SPORT_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=103&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //103
//    http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=99&ot=e&update=true&r=603659260&LID=&_=1497239829685
    public String URL_SUPER_COMBO_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=99&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_SUPER_COMBO_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=99&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_SUPER_COMBO_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=99&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=25&ot=e&update=true&r=1912041035&LID=&_=1497859662375
    public String URL_FORMULA_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=27&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_FORMULA_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=25&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_FORMULA_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=25&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_FORMULA_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=25&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=25&ot=e&update=true&r=674224967&LID=&_=1497859548627
    public String URL_TABLE_TENNIS_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=58&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_TABLE_TENNIS_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=57&ot=t&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_TABLE_TENNIS_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=57&ot=r&update=true&r=1186326052&FAV=&SL=&_=1111";
    public String URL_TABLE_TENNIS_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=57&ot=e&update=true&r=1186326052&FAV=&SL=&_=1111";
    //http://main55.afb88.com/_view/Result.aspx
    public String URL_RESULT = HOST + "_view/Result_App.aspx";//好的

/*    public String URL_EUROPE_TODAY;
    public String URL_EUROPE_RUNING;
    public String URL_EUROPE_EARLY;
    public String URL_EUROPE_MIX_EARLY;
    public String URL_EUROPE_MIX_TODAY;*/
   public String URL_EUROPE_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=182&ot=t&update=true&r=1833149994&FAV=&SL=&_=1111";
   public String URL_EUROPE_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=182&ot=r&update=true&r=1833149994&FAV=&SL=&_=1111";
   public String URL_EUROPE_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=182&ot=e&update=true&r=1833149994&FAV=&SL=&_=1111";
   public String URL_EUROPE_MIX_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=183&ot=e&update=true&r=524111120&FAV=&SL=&_=1111";
   public String URL_EUROPE_MIX_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=183&ot=t&update=true&r=524111120&FAV=&SL=&_=1111";

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
        URL_FOOTBALL_RUNNING = HOST + "_View/SocAllGen.ashx?ot=r&ov=0&tf=-1&TFStatus=0&update=false&r=153004462";
        URL_FOOTBALL_TODAY = HOST + "_View/SocAllGen.ashx?ot=t&ov=0&tf=-1&TFStatus=0&update=false&r=153004462&wd=&ia=0";
        URL_FOOTBALL_EARLY = HOST + "_View/SocAllGen.ashx?ot=e&ov=0&tf=2&TFStatus=0&update=false&r=246503518";
        URL_FOOTBALL_EARLY_Mix = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=4&ot=e&update=true&r=866546094";
        URL_FOOTBALL_TODAY_Mix = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=4&ot=t&update=true&r=1027945624";
        URL_FOOTBALL_OUT_RIGHT = HOST + "_view/OddsGen50.ashx?update=true&r=2076712019";
        URL_ODDS = HOST + "Bet/JRecPanel_App.aspx?";
        URL_SOCCER_REMOVE_MIX = HOST + "Bet/ParRemove.aspx";

        URL_BASKETBALL_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=9&ot=r&update=true&r=757267892";
        URL_BASKETBALL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=9&ot=t&update=true&r=757267892";
        URL_BASKETBALL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=9&ot=e&update=true&r=2033192118";

        URL_BASKETBALL_EARLY_Mix = HOST + "_view/ParGen.ashx?g=10&ot=e&update=true&r=866546094";
        URL_BASKETBALL_TODAY_Mix = HOST + "_view/ParGen.ashx?g=10&ot=t&update=true&r=408754578";
        URL_BASKETBALL_OUT_RIGHT = HOST + "_view/OddsOutGen.ashx?g=31&update=true&r=1267181584";

        URL_TENNIS_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=21&ot=r&update=true&r=442357900";
        URL_TENNIS_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=21&ot=t&update=true&r=1323982190";
        URL_TENNIS_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=21&ot=e&update=true&r=876540788";
        URL_TENNIS_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=36&update=true&r=446070790";

        URL_FINANCIAL_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=7&ot=r&update=true&r=442357900";
        URL_FINANCIAL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=7&ot=t&update=true&r=442357900";
        URL_FINANCIAL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=7&ot=e&update=true&r=1634139687";

        URL_MUAY_THAI_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=108&ot=t&update=true&r=1233177815";
        URL_MUAY_THAI_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=108&ot=r&update=true&r=1770989484";
        URL_MUAY_THAI_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=108&ot=e&update=true&r=1770989484";

        URL_4D_SPECIAL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=6&ot=t&update=true&r=1233177815";
        URL_4D_SPECIAL_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=6&ot=r&update=true&r=1770989484";
        URL_4D_SPECIAL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=6&ot=e&update=true&r=1770989484";

        URL_E_SPORT_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=106&ot=t&update=true&r=1233177815";
        URL_E_SPORT_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=106&ot=r&update=true&r=1770989484";
        URL_E_SPORT_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=106&ot=e&update=true&r=1770989484";
        URL_E_SPORT_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=107&update=true&r=1770989484";

        URL_US_FOOTBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=30&update=true&r=1887771784";
        URL_US_FOOTBALL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=12&ot=e&update=true&r=927620479";
        URL_US_FOOTBALL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=12&ot=t&update=true&r=927620479";
        URL_US_FOOTBALL_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=12&ot=r&update=true&r=927620479";
        URL_US_FOOTBALL_EARLY_MIX = HOST + "_view/ParGen.ashx?g=13&ot=e&update=true&r=927620479";
        URL_US_FOOTBALL_TODAY_MIX = HOST + "_view/ParGen.ashx?g=13&ot=t&update=true&r=927620479";
        //
        URL_SOCCER_MYANMAR_RUNNING = HOST + "_view/MOddsGen2.ashx?g=106&ot=r&update=true&r=1233177815";
        URL_SOCCER_MYANMAR_TODAY = HOST + "_view/MOddsGen2.ashx?g=106&ot=t&update=true&r=1233177815";
        URL_SOCCER_MYANMAR_EARLY = HOST + "_view/MOddsGen2.ashx?g=106&ot=e&update=true&r=1233177815";

        URL_BASEBALL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=29&ot=e&update=true&r=445360998";
        URL_BASEBALL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=29&ot=t&update=true&r=445360998";
        URL_BASEBALL_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=29&ot=r&update=true&r=445360998";
        URL_BASEBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=39&update=true&r=1186326052";

        //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=14&ot=r&update=true&r=1545705412
        //http://main55.afb88.com/_view/OddsOutGen.ashx?g=33&ot=e&update=true&r=1341186982&LID=&_=1496905566929
        URL_ICE_HOCKEY_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=33&update=true&r=1186326052";
        URL_ICE_HOCKEY_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=14&ot=t&update=true&r=1186326052";
        URL_ICE_HOCKEY_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=14&ot=r&update=true&r=1186326052";
        URL_ICE_HOCKEY_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=14&ot=e&update=true&r=1186326052";

        //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=11&ot=t&update=true&r=340371970&LID=&_=1496906053812
        //http://main55.afb88.com/_view/OddsOutGen.ashx?g=32&ot=e&update=true&r=1492002023&LID=&_=1496906224047
        URL_POOL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=32&update=true&r=1186326052";
        URL_POOL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=11&ot=t&update=true&r=1186326052";
        URL_POOL_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=11&ot=r&update=true&r=1186326052";
        URL_POOL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=11&ot=e&update=true&r=1186326052";
        //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=17&ot=e&update=true&r=2080506517&LID=&_=1496906889008
        //http://main55.afb88.com/_view/OddsOutGen.ashx?g=34&ot=e&update=true&r=1222999374&LID=&_=1496906975411
        URL_RUGBY_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=34&update=true&r=1186326052";
        URL_RUGBY_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=17&ot=t&update=true&r=1186326052";
        URL_RUGBY_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=17&ot=r&update=true&r=1186326052";
        URL_RUGBY_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=17&ot=e&update=true&r=1186326052";
        //
        URL_DARTS_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=35&update=true&r=1887771784";
        URL_DARTS_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=19&ot=e&update=true&r=927620479";
        URL_DARTS_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=19&ot=t&update=true&r=927620479";
        URL_DARTS_RUNNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=19&ot=r&update=true&r=927620479";
        URL_DARTS_EARLY_MIX = HOST + "_view/ParGen.ashx?g=20&ot=e&update=true&r=927620479";
        URL_DARTS_TODAY_MIX = HOST + "_view/ParGen.ashx?g=20&ot=t&update=true&r=927620479";
        //
        URL_BOXING_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=92&update=true&r=1186326052";
        URL_BOXING_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=16&ot=t&update=true&r=1186326052";
        URL_BOXING_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=16&ot=r&update=true&r=1186326052";
        URL_BOXING_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=16&ot=e&update=true&r=1186326052";
        //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=22&ot=t&update=true&r=158540506&LID=&_=1496915863924
        URL_GOLF_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=37&update=true&r=1186326052";
        URL_GOLF_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=22&ot=t&update=true&r=1186326052";
        URL_GOLF_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=22&ot=r&update=true&r=1186326052";
        URL_GOLF_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=22&ot=e&update=true&r=1186326052";
        //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=51&ot=t&update=true&r=448891026&LID=&_=1497233419847
        URL_BADMINTON_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=52&update=true&r=1186326052";
        URL_BADMINTON_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=51&ot=t&update=true&r=1186326052";
        URL_BADMINTON_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=51&ot=r&update=true&r=1186326052";
        URL_BADMINTON_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=51&ot=e&update=true&r=1186326052";
        //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=44&ot=e&update=true&r=2130678057&LID=&_=1497238234614
        URL_VOLLEYBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=62&update=true&r=1186326052";
        URL_VOLLEYBALL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=23&ot=t&update=true&r=1186326052";
        URL_VOLLEYBALL_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=23&ot=r&update=true&r=1186326052";
        URL_VOLLEYBALL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=23&ot=e&update=true&r=1186326052";
        //
        URL_CRICKET_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=61&update=true&r=1186326052";
        URL_CRICKET_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=44&ot=t&update=true&r=1186326052";
        URL_CRICKET_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=44&ot=r&update=true&r=1186326052";
        URL_CRICKET_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=44&ot=e&update=true&r=1186326052";
        //
        URL_HANDBALL_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=43&update=true&r=1186326052";
        URL_HANDBALL_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=41&ot=t&update=true&r=1186326052";
        URL_HANDBALL_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=41&ot=r&update=true&r=1186326052";
        URL_HANDBALL_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=41&ot=e&update=true&r=1186326052";
        //65
        URL_CYCLING_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=64&update=true&r=1186326052";
        URL_CYCLING_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=65&ot=t&update=true&r=1186326052";
        URL_CYCLING_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=65&ot=r&update=true&r=1186326052";
        URL_CYCLING_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=65&ot=e&update=true&r=1186326052";
        //103
        URL_WINTER_SPORT_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=104&update=true&r=1186326052";
        URL_WINTER_SPORT_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=103&ot=t&update=true&r=1186326052";
        URL_WINTER_SPORT_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=103&ot=r&update=true&r=1186326052";
        URL_WINTER_SPORT_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=103&ot=e&update=true&r=1186326052";
        //103
//    http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=99&ot=e&update=true&r=603659260&LID=&_=1497239829685
        URL_SUPER_COMBO_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=99&ot=t&update=true&r=1186326052";
        URL_SUPER_COMBO_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=99&ot=r&update=true&r=1186326052";
        URL_SUPER_COMBO_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=99&ot=e&update=true&r=1186326052";
        //57http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=57&ot=r&update=true&r=764149212&LID=&_=1497599416541
        URL_TABLE_TENNIS_OUTRIGHT = HOST + "_view/OddsOutGen.ashx?g=58&update=true&r=1186326052";
        URL_TABLE_TENNIS_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=57&ot=t&update=true&r=1186326052";
        URL_TABLE_TENNIS_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=57&ot=r&update=true&r=1186326052";
        URL_TABLE_TENNIS_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=57&ot=e&update=true&r=1186326052";

        //http://main55.afb88.com/_view/Result.aspx
        URL_RESULT = HOST + "_view/Result_App.aspx";

        //http://main55.afb88.com/_View/SocAllGen.ashx?tp=0&fh=1&g=182&ot=r&update=true&r=1833149994&LID=ba82fa925e9d154e&_=1495597268312
        URL_EUROPE_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=182&ot=t&update=true&r=1833149994";
        URL_EUROPE_RUNING = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=182&ot=r&update=true&r=1833149994";
        URL_EUROPE_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=182&ot=e&update=true&r=1833149994";
        URL_EUROPE_MIX_EARLY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=183&ot=e&update=true&r=524111120";
        URL_EUROPE_MIX_TODAY = HOST + "_View/SocAllGen.ashx?tp=0&fh=1&g=183&ot=t&update=true&r=524111120";

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
        URL_ODDS=URL_ODDS_AFB1188;
    }

    public static AppConstant getInstance() {
        if (instance == null)
            instance = new AppConstant();
        return instance;
    }
}
