package com.nanyang.app;

/**
 * Created by Administrator on 2017/2/10.
 */

public class AppConstant {
    static {
        HOST="http://a8197c.a36588.com/";
    }
    public static String KEY_STRING="KEY_STRING";
    public static String KEY_DATA="KEY_DATA";
    public static String KEY_INT="KEY_INT";
    public static String KEY_BOOLEAN="KEY_BOOLEAN";
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
    public static String URL_ODDS_TYPE=HOST+"Main.aspx?accType=";
    public static String URL_FOOTBALL_RUNNING ="http://a8197c.a36588.com/_View/RMOddsGen1.ashx?ot=r&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=153004462";
    public static String URL_FOOTBALL_TODAY ="http://a8197c.a36588.com/_View/RMOddsGen1.ashx?ot=t&ov=0&mt=0&tf=-1&TFStatus=0&update=false&r=153004462&wd=&ia=0";
    public static String URL_FOOTBALL_EARLY ="http://a8197c.a36588.com/_iew/RMOddsGen1.ashx?ot=e&ov=0&mt=0&tf=2&TFStatus=0&update=false&r=246503518&ia=0";
    public static String URL_FOOTBALL_EARLY_Mix ="http://a8197c.a36588.com/_view/ParGenWFH.ashx?g=4&ot=e&update=true&r=866546094";
    public static String URL_FOOTBALL_TODAY_Mix ="http://a8197c.a36588.com/_view/ParGenWFH.ashx?g=4&ot=t&update=true&r=1027945624";
/*
          urlstr=[NSString stringWithFormat:@"%@/_view/All_WFHGen.ashx?g=9&ot=r&update=true&r=199199940&LID=%@&_=%zd",KURLAFBBall,BASKETBALLDATA.RequestKey,BASKETBALLDATA.randomId];
            break;
        case FootballToday:
            urlstr=[NSString stringWithFormat:@"%@/_view/All_WFHGen.ashx?g=9&ot=t&update=true&r=199199940&LID=%@&_=%zd",KURLAFBBall,BASKETBALLDATA.RequestKey,BASKETBALLDATA.randomId];
            break;
        case FootballEarly:
            urlstr=[NSString stringWithFormat:@"%@/_view/All_WFHGen.ashx?g=9&ot=e&update=true&r=894139351&LID=%@&_=%zd",KURLAFBBall,BASKETBALLDATA.RequestKey,BASKETBALLDATA.randomId];
            break;
* */
    public static String URL_BASKETBALL_RUNNING ="http://a0096f.panda88.org/_view/All_WFHGen.ashx?g=9&ot=t&update=true&r=127579615";
    public static String URL_BASKETBALL_TODAY ="http://a8197c.a36588.com/_view/All_WFHGen.ashx?g=9&ot=t&update=true&r=199199940";
    public static String URL_BASKETBALL_EARLY ="http://a8197c.a36588.com/_view/All_WFHGen.ashx?g=9&ot=e&update=true&r=894139351";

    public static String URL_BASKETBALL_EARLY_Mix ="http://a8197c.a36588.com/_view/ParGen.ashx?g=10&ot=e&update=true&r=866546094";
    public static String URL_BASKETBALL_TODAY_Mix ="http://a8197c.a36588.com/_view/ParGen.ashx?g=10&ot=t&update=true&r=408754578";
    //http://a8197c.a36588.com/_view/ParGen.ashx?g=10&ot=t&update=true&r=408754578_=1488176690345


    //http://a8197c.a36588.com/_view/All_Gen6.ashx?g=21&ot=r&update=true&r=442357900_=1488244902038
    public static String URL_VOLLEYBALL_RUNNING ="http://a8197c.a36588.com/_view/All_Gen6.ashx?g=21&ot=r&update=true&r=442357900";
    public static String URL_VOLLEYBALL_TODAY ="http://a8197c.a36588.com/_view/All_Gen6.ashx?g=21&ot=t&update=true&r=442357900";
    public static String URL_VOLLEYBALL_EARLY =" http://a8197c.a36588.com/_view/All_Gen6.ashx?g=21&ot=e&update=true&r=876540788";

//
public static String URL_SHARES_RUNNING ="http://a8197c.a36588.com/_view/All_Gen6.ashx?g=7&ot=r&update=true&r=442357900";
    public static String URL_SHARES_TODAY ="http://a8197c.a36588.com/_view/All_Gen6.ashx?g=7&ot=t&update=true&r=442357900";
    public static String URL_SHARES_EARLY ="http://a8197c.a36588.com/_view/All_Gen6.ashx?g=7&ot=e&update=true&r=1634139687";

}
