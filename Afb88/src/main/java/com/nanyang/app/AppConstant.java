package com.nanyang.app;

/**
 * Created by Administrator on 2017/2/10.
 */

public class AppConstant {




    static {
        HOST= "http://a8197c.a36588.com/";
        APP_HOST = "http://main55.afb88.com/";
    }
    public static String APP_HOST = "http://main55.afb88.com/";
    public static String KEY_STRING="KEY_STRING";
    public static String KEY_DATA="KEY_DATA";
    public static String KEY_INT="KEY_INT";
    public static String KEY_BOOLEAN="KEY_BOOLEAN";
    public static final String KEY_DATA2 = "KEY_DATA2";
    public static final String KEY_DATA3 = "KEY_DATA3";
    /**
     * http://a8197c.a36588.com/_View/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=153004462
     RUNING
     http://a8197c.a36588.com/_View/RMOddsGen1.ashx?ot=t&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=153004462&wd=&ia=0
     Today
     http://a8197c.a36588.com/_View/RMOddsGen1.ashx?ot=e&ov=0&mt=0&tf=2&TFStatus=0&update=false&r=246503518&wd=2017-02-16&ia=0
     Early
     */
//    http://a0096f.panda88.org/_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=431367264&LID=&_=1488465754049
            //盘口
//    http://a0096f.panda88.org/Main.aspx?accType=HK
//            http://a0096f.panda88.org/Main.aspx?accType=MY
            //http://a8197c.a36588.com/Main.aspx?accType=ID
            //http://a8197c.a36588.com/Main.aspx?accType=EU

    public static String HOST="http://a8197c.a36588.com/";
    public static String URL_UPDATE_STATE=HOST+"pgajaxS.axd?T=CHKST&P=100";
    public static String URL_ODDS_TYPE=HOST+"Main.aspx?accType=";

    public static String URL_FOOTBALL_RUNNING =HOST+"_view/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=153004462";
    public static String URL_FOOTBALL_TODAY =HOST+"_view/RMOddsGen1.ashx?ot=t&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=153004462&wd=&ia=0";
    public static String URL_FOOTBALL_EARLY =HOST+"_view/RMOddsGen1.ashx?ot=e&ov=0&mt=0&tf=2&TFStatus=0&update=false&r=246503518&ia=0";
    public static String URL_FOOTBALL_EARLY_Mix =HOST+"_view/ParGenWFH.ashx?g=4&ot=e&update=true&r=866546094";
    public static String URL_FOOTBALL_TODAY_Mix =HOST+"_view/ParGenWFH.ashx?g=4&ot=t&update=true&r=1027945624";
    public static String URL_FOOTBALL_OUT_RIGHT =HOST+"_view/OddsGen50.ashx?update=true&r=2076712019";

    public static String URL_ODDS=HOST+"_Bet/JRecPanel.aspx?";

    public static final String URL_SOCCER_REMOVE_MIX =HOST+"_bet/ParRemove.aspx";

//http://a8197c.a36588.com/_View/All_WFHGen.ashx?g=9&ot=e&update=true&r=2033192118&LID=6d93d66cfbe0867d&_=1488511791401
    //http://a8197c.a36588.com/_view/All_WFHGen.ashx?g=9&ot=r&update=true&r=757267892&LID=b54b5d6983173114&_=1488512949361
    //http://a8197c.a36588.com/_view/All_WFHGen.ashx?g=9&ot=t&update=true&r=757267892&LID=1e6eef048d1e3114&_=1488512949362
    public static String URL_BASKETBALL_RUNNING =HOST+"_view/All_WFHGen.ashx?g=9&ot=r&update=true&r=757267892";
    public static String URL_BASKETBALL_TODAY =HOST+"_view/All_WFHGen.ashx?g=9&ot=t&update=true&r=757267892";
    public static String URL_BASKETBALL_EARLY =HOST+"_View/All_WFHGen.ashx?g=9&ot=e&update=true&r=2033192118";

    public static String URL_BASKETBALL_EARLY_Mix =HOST+"_view/ParGen.ashx?g=10&ot=e&update=true&r=866546094";
    public static String URL_BASKETBALL_TODAY_Mix =HOST+"_view/ParGen.ashx?g=10&ot=t&update=true&r=408754578";
    public static String URL_BASKETBALL_OUT_RIGHT=HOST+"_view/OddsOutGen.ashx?g=31&update=true&r=1267181584";


    //http://a8197c.a36588.com/_view/ParGen.ashx?g=10&ot=t&update=true&r=408754578_=1488176690345
    //


    //http://a8197c.a36588.com/_View/RMOddsGen1.ashx?ot=t&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=448980496&wd=&ia=0&LID=&_=1488521216204
    //http://a8197c.a36588.com/_View/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=448980496&LID=&_=1488521216203
    public static String URL_TENNIS_RUNNING =HOST+"_view/All_Gen6.ashx?g=21&ot=r&update=true&r=442357900";
    public static String URL_TENNIS_TODAY =HOST+"_view/All_Gen6.ashx?g=21&ot=t&update=true&r=1323982190";
    public static String URL_TENNIS_EARLY =HOST+"_view/All_Gen6.ashx?g=21&ot=e&update=true&r=876540788";
    public static String URL_TENNIS_OUTRIGHT=HOST+"_view/OddsOutGen.ashx?g=36&update=true&r=446070790";

//
public static String URL_FINANCIAL_RUNNING =HOST+"_view/All_Gen6.ashx?g=7&ot=r&update=true&r=442357900";
    public static String URL_FINANCIAL_TODAY =HOST+"_view/All_Gen6.ashx?g=7&ot=t&update=true&r=442357900";
    public static String URL_FINANCIAL_EARLY =HOST+"_view/All_Gen6.ashx?g=7&ot=e&update=true&r=1634139687";

    //泰拳http://a8197c.a36588.com/_view/All_Gen6.ashx?g=108&ot=t&update=true&r=1661815184&LID=&_=1488527593418
    public static String URL_MUAY_THAI_TODAY = HOST+"_view/All_Gen6.ashx?g=108&ot=t&update=true&r=1233177815";
    public static String URL_MUAY_THAI_RUNNING = HOST+"_view/All_Gen6.ashx?g=108&ot=r&update=true&r=1770989484";
    public static String URL_MUAY_THAI_EARLY = HOST+"_view/All_Gen6.ashx?g=108&ot=e&update=true&r=1770989484";

    //http://a8197c.a36588.com/_view/All_Gen6.ashx?g=6&ot=t&update=true&r=740497289&LID=&_=1488958736003
    public static String URL_4D_SPECIAL_TODAY = HOST+"_view/All_Gen6.ashx?g=6&ot=t&update=true&r=1233177815";
    public static String URL_4D_SPECIAL_RUNNING = HOST+"_view/All_Gen6.ashx?g=6&ot=r&update=true&r=1770989484";
    public static String URL_4D_SPECIAL_EARLY = HOST+"_view/All_Gen6.ashx?g=6&ot=e&update=true&r=1770989484";

    //http://a8197c.a36588.com/_view/All_Gen6.ashx?g=106&ot=t&update=true&r=1098285451&LID=&_=1488958947996
    public static String URL_E_SPORT_TODAY = HOST+"_view/All_Gen6.ashx?g=106&ot=t&update=true&r=1233177815";
    public static String URL_E_SPORT_RUNNING = HOST+"_view/All_Gen6.ashx?g=106&ot=r&update=true&r=1770989484";
    public static String URL_E_SPORT_EARLY = HOST+"_view/All_Gen6.ashx?g=106&ot=e&update=true&r=1770989484";
    public static String URL_E_SPORT_OUTRIGHT= HOST+"_view/OddsOutGen.ashx?g=107&update=true&r=1770989484";
    public static String URL_STAEMENT = HOST+"_norm/AccHistory_App.aspx?";

//    http://a8206d.a36588.com/_view/MOddsGen2.ashx?ot=e&update=true&r=1056207418&wd=2017-03-22&ia=0&oview=1&LID=&_=1490092121779
    //http://a8206d.a36588.com/_view/MOddsGen2.ashx?ot=r&update=true&r=1278052286&LID=84733db3885dd967&_=1490092254366
    //http://a8206d.a36588.com/_view/MOddsGen2.ashx?ot=t&update=true&r=1278052286&LID=d784f703f22a941c&_=1490092254367

    public static final String URL_SOCCER_MYANMAR_RUNNING =HOST+"_view/MOddsGen2.ashx?g=106&ot=r&update=true&r=1233177815"; ;
    public static final String URL_SOCCER_MYANMAR_TODAY = HOST+"_view/MOddsGen2.ashx?g=106&ot=t&update=true&r=1233177815";;
    public static final String URL_SOCCER_MYANMAR_EARLY = HOST+"_view/MOddsGen2.ashx?g=106&ot=e&update=true&r=1233177815";;
}
