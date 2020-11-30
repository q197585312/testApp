package gaming178.com.casinogame.Util;


import gaming178.com.baccaratgame.BuildConfig;

public class WebSiteUrl {

    public static String Tag = "GDCB";
    public static String PROXY_LOGIN_URL = "http://igtx.playcdn.net/extauth.aspx?game=8&home=11";
    public static String HEADER = "";
    public static String PROJECT = "";

    public static String Register_Url = HEADER + PROJECT + "register2.jsp";
    public static String GetDeposit_Url = HEADER + PROJECT + "getDeposit.jsp";
    public static String GetWithdraw_Url = HEADER + PROJECT + "getWithdraw.jsp";
    public static String Deposit_Url = HEADER + PROJECT + "deposit.jsp";
    public static String Withdraw_Url = HEADER + PROJECT + "withdraw.jsp";

    public static String INDEX = HEADER + PROJECT + "index.jsp";
    public static String LANGUAGE_URL = HEADER + "updateOnlineUser.jsp";
    public static String LOGIN_URL = HEADER + PROJECT + BuildConfig.loginjsp;
    public static String GAME_GG_URL = HEADER + PROJECT + "gameGG.jsp";
    public static String TABLE_INFO_A_URL = HEADER + PROJECT + "select_tb_infoa.jsp";

    public static String TABLEINFO_URL_C = HEADER + PROJECT + "select_tb_infoc.jsp";
    public static String COUNTDOWN_URL_A = HEADER + PROJECT + "select_Timer.jsp";
    public static String COUNTDOWN_URL_A_B = HEADER + PROJECT + "select_Timerab.jsp";
    public static String COUNTDOWN_URL_B = HEADER + PROJECT + "select_Timerb.jsp";
    public static String COUNTDOWN_URL_C = HEADER + PROJECT + "select_Timerf.jsp";
    public static String LOGOUT_URL = HEADER + PROJECT + "main.jsp";

    public static String BJL_TABLE_GAMENUM = HEADER + PROJECT + "bjl_gamenum.jsp";
    public static String BJL_LUZI_URL = HEADER + PROJECT + "luzi.jsp";
    public static String BJL_BET_MONEY_URL = HEADER + PROJECT + "bjl_bet_money.jsp";
    public static String BJL_WON_MONEY_URL = HEADER + PROJECT + "bjl_won_money.jsp";
    public static String BJL_BET_URL = HEADER + PROJECT + "bjl_bet.jsp";

    public static String SICBO_BET_MONEY_URL = HEADER + PROJECT + "sb_bet_money.jsp";
    public static String SICBO_LUZI_URL = HEADER + PROJECT + "sb_luzi.jsp";
    public static String SICBO_TABLE_GAMENUM = HEADER + PROJECT + "sb_gamenum.jsp";
    public static String SICBO_WON_MONEY_URL = HEADER + PROJECT + "sb_won_money.jsp";
    public static String SICBO_TABLE_STATUS_URL = HEADER + PROJECT + "sb_state.jsp";
    public static String SICBO_BET_URL = HEADER + PROJECT + "sb_bet.jsp";

    public static String LP_TABLE_STATUS_URL = HEADER + PROJECT + "lp_state.jsp";
    public static String LP_BET_MONEY_URL = HEADER + PROJECT + "lp_bet_money.jsp";
    public static String LP_TABLE_GAMENUM = HEADER + PROJECT + "lp_gamenum.jsp";
    public static String LP_WON_MONEY_URL = HEADER + PROJECT + "lp_won_money.jsp";
    public static String LP_BET_URL = HEADER + PROJECT + "lp_bet.jsp";
    public static String LP_ROAD_URL = HEADER + PROJECT + "lp_luzih.jsp";

    public static String BJL_TABLE_STATUS_URL = HEADER + PROJECT + "bjl_state.jsp";

    public static String BJL_TABLE_HALL_CHOOSE_SEAT_URL = HEADER + PROJECT + "choice_tb.jsp";
    public static String BJL_TABLE_HALL_CHOOSE_SINGLE_SEAT_URL = HEADER + PROJECT + "choice_single_tb.jsp";

    public static String LH_TABLE_STATUS_URL = HEADER + PROJECT + "lh_state.jsp";
    public static String LH_BET_MONEY_URL = HEADER + PROJECT + "lh_bet_money.jsp";
    public static String LH_TABLE_GAMENUM = HEADER + PROJECT + "lh_gamenum.jsp";
    public static String LH_WON_MONEY_URL = HEADER + PROJECT + "lh_won_money.jsp";
    public static String LH_BET_URL = HEADER + PROJECT + "lh_bet.jsp";
    public static String LH_LUZI_URL = HEADER + PROJECT + "lh_luzi.jsp";
    public static String REPORT_URL = HEADER + PROJECT + "report.jsp";
    public static String REFERRAL_LIST = HEADER + PROJECT + "Daftar.jsp";

    //选择台桌
    public static String CHOOSE_TABLE_URL = HEADER + PROJECT + "choice_tb.jsp";
    public static String AppDig88LoginUrl = HEADER + PROJECT + "login.jsp";

    public static String TABLEINFO_URL = HEADER + PROJECT + "select_tb_info.jsp";
    public static String COUNTDOWN_URL = HEADER + PROJECT + "select_Timer.jsp";
    public static boolean isDomain = false;
    public static int GameType = 0;//0： A区 1： B区
    //	public static String AppWebServiceUrl = "http://casino.dig88.com/Dig88/GD88digWS?wsdl";
    public static String AppWebServiceUrl = BuildConfig.AppWebServiceUrl.startsWith("http")? BuildConfig.AppWebServiceUrl:HEADER + PROJECT + "GDOnlineWS";
    public static String AppWebServiceNameSpace = BuildConfig.AppWebServiceNameSpace;

    public static void setNormal(String url) {
        isDomain = false;
        GameType = 0;
        HEADER = url;
        PROJECT = "";
        Register_Url = HEADER + PROJECT + "register2.jsp";
        GetDeposit_Url = HEADER + PROJECT + "getDeposit.jsp";
        GetWithdraw_Url = HEADER + PROJECT + "getWithdraw.jsp";
        Deposit_Url = HEADER + PROJECT + "deposit.jsp";
        Withdraw_Url = HEADER + PROJECT + "withdraw.jsp";
        INDEX = HEADER + PROJECT + "index.jsp";
        LANGUAGE_URL = HEADER + "updateOnlineUser.jsp";
        LOGIN_URL = HEADER + PROJECT + BuildConfig.loginjsp;
        GAME_GG_URL = HEADER + PROJECT + "gameGG.jsp";
        TABLEINFO_URL = HEADER + PROJECT + "select_tb_info.jsp";
        COUNTDOWN_URL = HEADER + PROJECT + "select_Timer.jsp";
        LOGOUT_URL = HEADER + PROJECT + "main.jsp";
        BJL_TABLE_STATUS_URL = HEADER + PROJECT + "bjl_state.jsp";

        BJL_TABLE_HALL_CHOOSE_SEAT_URL = HEADER + PROJECT + "choice_tb.jsp";
        BJL_TABLE_HALL_CHOOSE_SINGLE_SEAT_URL = HEADER + PROJECT + "choice_single_tb.jsp";
        BJL_TABLE_GAMENUM = HEADER + PROJECT + "bjl_gamenum.jsp";
        BJL_LUZI_URL = HEADER + PROJECT + "luzi.jsp";
        BJL_BET_MONEY_URL = HEADER + PROJECT + "bjl_bet_money.jsp";
        BJL_WON_MONEY_URL = HEADER + PROJECT + "bjl_won_money.jsp";
        BJL_BET_URL = HEADER + PROJECT + "bjl_bet.jsp";

        SICBO_BET_MONEY_URL = HEADER + PROJECT + "sb_bet_money.jsp";
        SICBO_LUZI_URL = HEADER + PROJECT + "sb_luzi.jsp";
        SICBO_TABLE_GAMENUM = HEADER + PROJECT + "sb_gamenum.jsp";
        SICBO_WON_MONEY_URL = HEADER + PROJECT + "sb_won_money.jsp";
        SICBO_TABLE_STATUS_URL = HEADER + PROJECT + "sb_state.jsp";
        SICBO_BET_URL = HEADER + PROJECT + "sb_bet.jsp";

        LP_TABLE_STATUS_URL = HEADER + PROJECT + "lp_state.jsp";
        LP_BET_MONEY_URL = HEADER + PROJECT + "lp_bet_money.jsp";
        LP_TABLE_GAMENUM = HEADER + PROJECT + "lp_gamenum.jsp";
        LP_WON_MONEY_URL = HEADER + PROJECT + "lp_won_money.jsp";
        LP_BET_URL = HEADER + PROJECT + "lp_bet.jsp";
        LP_ROAD_URL = HEADER + PROJECT + "lp_luzih.jsp";

        LH_TABLE_STATUS_URL = HEADER + PROJECT + "lh_state.jsp";
        LH_BET_MONEY_URL = HEADER + PROJECT + "lh_bet_money.jsp";
        LH_TABLE_GAMENUM = HEADER + PROJECT + "lh_gamenum.jsp";
        LH_WON_MONEY_URL = HEADER + PROJECT + "lh_won_money.jsp";
        LH_BET_URL = HEADER + PROJECT + "lh_bet.jsp";
        LH_LUZI_URL = HEADER + PROJECT + "lh_luzi.jsp";
        REPORT_URL = HEADER + PROJECT + "report.jsp";

        AppDig88LoginUrl = HEADER + PROJECT + "login.jsp";
        TABLE_INFO_A_URL = HEADER + PROJECT + "select_tb_infoa.jsp";

        TABLEINFO_URL_C = HEADER + PROJECT + "select_tb_infoc.jsp";
        COUNTDOWN_URL_A = HEADER + PROJECT + "select_Timer.jsp";
        COUNTDOWN_URL_A_B = HEADER + PROJECT + "select_Timerab.jsp";
        COUNTDOWN_URL_B = HEADER + PROJECT + "select_Timerb.jsp";
        COUNTDOWN_URL_C = HEADER + PROJECT + "select_Timerf.jsp";
        AppWebServiceUrl = BuildConfig.AppWebServiceUrl.startsWith("http")? BuildConfig.AppWebServiceUrl:HEADER + PROJECT + "GDOnlineWS";
        REFERRAL_LIST = HEADER + PROJECT + "Daftar.jsp";

    }

    public static void setOther(String header, String project) {
        HEADER = header;
        PROJECT = project;
        Register_Url = HEADER + PROJECT + "register2.jsp";
        GetDeposit_Url = HEADER + PROJECT + "getDeposit.jsp";
        GetWithdraw_Url = HEADER + PROJECT + "getWithdraw.jsp";
        Deposit_Url = HEADER + PROJECT + "deposit.jsp";
        Withdraw_Url = HEADER + PROJECT + "withdraw.jsp";
        INDEX = HEADER + PROJECT + "index.jsp";
        LANGUAGE_URL = HEADER + "onlineusers_2.jsp";
        LOGIN_URL = HEADER + PROJECT + "checklogin.jsp";
        GAME_GG_URL = HEADER + PROJECT + "gameGG.jsp";
        TABLEINFO_URL = HEADER + PROJECT + "select_tb_infoa.jsp";
        COUNTDOWN_URL = HEADER + PROJECT + "select_Timer.jsp";
        LOGOUT_URL = HEADER + PROJECT + "main.jsp";
        BJL_TABLE_STATUS_URL = HEADER + PROJECT + "bjl_state.jsp";

        BJL_TABLE_HALL_CHOOSE_SEAT_URL = HEADER + PROJECT + "choice_tb.jsp";
        BJL_TABLE_HALL_CHOOSE_SINGLE_SEAT_URL = HEADER + PROJECT + "choice_single_tb.jsp";
        BJL_TABLE_GAMENUM = HEADER + PROJECT + "bjl_gamenum.jsp";
        BJL_LUZI_URL = HEADER + PROJECT + "luzi.jsp";
        BJL_BET_MONEY_URL = HEADER + PROJECT + "bjl_bet_money.jsp";
        BJL_WON_MONEY_URL = HEADER + PROJECT + "bjl_won_money.jsp";
        BJL_BET_URL = HEADER + PROJECT + "bjl_bet.jsp";

        SICBO_BET_MONEY_URL = HEADER + PROJECT + "sb_bet_money.jsp";
        SICBO_LUZI_URL = HEADER + PROJECT + "sb_luzi.jsp";
        SICBO_TABLE_GAMENUM = HEADER + PROJECT + "sb_gamenum.jsp";
        SICBO_WON_MONEY_URL = HEADER + PROJECT + "sb_won_money.jsp";
        SICBO_TABLE_STATUS_URL = HEADER + PROJECT + "sb_state.jsp";
        SICBO_BET_URL = HEADER + PROJECT + "sb_bet.jsp";

        LP_TABLE_STATUS_URL = HEADER + PROJECT + "lp_state.jsp";
        LP_BET_MONEY_URL = HEADER + PROJECT + "lp_bet_money.jsp";
        LP_TABLE_GAMENUM = HEADER + PROJECT + "lp_gamenum.jsp";
        LP_WON_MONEY_URL = HEADER + PROJECT + "lp_won_money.jsp";
        LP_BET_URL = HEADER + PROJECT + "lp_bet.jsp";
        LP_ROAD_URL = HEADER + PROJECT + "lp_luzih.jsp";

        LH_TABLE_STATUS_URL = HEADER + PROJECT + "lh_state.jsp";
        LH_BET_MONEY_URL = HEADER + PROJECT + "lh_bet_money.jsp";
        LH_TABLE_GAMENUM = HEADER + PROJECT + "lh_gamenum.jsp";
        LH_WON_MONEY_URL = HEADER + PROJECT + "lh_won_money.jsp";
        LH_BET_URL = HEADER + PROJECT + "lh_bet.jsp";
        LH_LUZI_URL = HEADER + PROJECT + "lh_luzi.jsp";
        REPORT_URL = HEADER + PROJECT + "report.jsp";

        AppDig88LoginUrl = HEADER + PROJECT + "login.jsp";
        TABLE_INFO_A_URL = HEADER + PROJECT + "select_tb_infoa.jsp";

        TABLEINFO_URL_C = HEADER + PROJECT + "select_tb_infoc.jsp";
        COUNTDOWN_URL_A = HEADER + PROJECT + "select_Timer.jsp";
        COUNTDOWN_URL_A_B = HEADER + PROJECT + "select_Timerab.jsp";
        COUNTDOWN_URL_B = HEADER + PROJECT + "select_Timerb.jsp";
        COUNTDOWN_URL_C = HEADER + PROJECT + "select_Timerf.jsp";
        REFERRAL_LIST = HEADER + PROJECT + "Daftar.jsp";
    }

    public static void setDomain(int gameType, String urlHost) {
        isDomain = true;
        GameType = 0;
        Register_Url = HEADER + PROJECT + "register2.jsp";
        GetDeposit_Url = HEADER + PROJECT + "getDeposit.jsp";
        GetWithdraw_Url = HEADER + PROJECT + "getWithdraw.jsp";
        Deposit_Url = HEADER + PROJECT + "deposit.jsp";
        Withdraw_Url = HEADER + PROJECT + "withdraw.jsp";
        HEADER = gameType == 0 ? "http://www.greendragon88.com/" : "http://" + urlHost + "/";//"http://113.130.125.201/";
        PROJECT = gameType == 0 ? "kgapi/" : "";
        INDEX = HEADER + PROJECT + "index.jsp";
        LANGUAGE_URL = HEADER + "updateOnlineUser.jsp";
        LOGIN_URL = HEADER + PROJECT + BuildConfig.loginjsp;
        GAME_GG_URL = HEADER + PROJECT + "gameGG.jsp";
        TABLEINFO_URL = HEADER + PROJECT + (gameType == 0 ? "select_tb_info.jsp" : "select_tb_info.jsp");
        COUNTDOWN_URL = HEADER + PROJECT + (gameType == 0 ? "select_Timer.jsp" : "select_Timer.jsp");
        LOGOUT_URL = HEADER + PROJECT + "main.jsp";
        BJL_TABLE_STATUS_URL = HEADER + PROJECT + "bjl_state.jsp";

        BJL_TABLE_HALL_CHOOSE_SEAT_URL = HEADER + PROJECT + "choice_tb.jsp";
        BJL_TABLE_HALL_CHOOSE_SINGLE_SEAT_URL = HEADER + PROJECT + "choice_single_tb.jsp";
        BJL_TABLE_GAMENUM = HEADER + PROJECT + "bjl_gamenum.jsp";
        BJL_LUZI_URL = HEADER + PROJECT + "luzi.jsp";
        BJL_BET_MONEY_URL = HEADER + PROJECT + "bjl_bet_money.jsp";
        BJL_WON_MONEY_URL = HEADER + PROJECT + "bjl_won_money.jsp";
        BJL_BET_URL = HEADER + PROJECT + "bjl_bet.jsp";

        SICBO_BET_MONEY_URL = HEADER + PROJECT + "sb_bet_money.jsp";
        SICBO_LUZI_URL = HEADER + PROJECT + "sb_luzi.jsp";
        SICBO_TABLE_GAMENUM = HEADER + PROJECT + "sb_gamenum.jsp";
        SICBO_WON_MONEY_URL = HEADER + PROJECT + "sb_won_money.jsp";
        SICBO_TABLE_STATUS_URL = HEADER + PROJECT + "sb_state.jsp";
        SICBO_BET_URL = HEADER + PROJECT + "sb_bet.jsp";

        LP_TABLE_STATUS_URL = HEADER + PROJECT + "lp_state.jsp";
        LP_BET_MONEY_URL = HEADER + PROJECT + "lp_bet_money.jsp";
        LP_TABLE_GAMENUM = HEADER + PROJECT + "lp_gamenum.jsp";
        LP_WON_MONEY_URL = HEADER + PROJECT + "lp_won_money.jsp";
        LP_BET_URL = HEADER + PROJECT + "lp_bet.jsp";
        LP_ROAD_URL = HEADER + PROJECT + "lp_luzih.jsp";

        LH_TABLE_STATUS_URL = HEADER + PROJECT + "lh_state.jsp";
        LH_BET_MONEY_URL = HEADER + PROJECT + "lh_bet_money.jsp";
        LH_TABLE_GAMENUM = HEADER + PROJECT + "lh_gamenum.jsp";
        LH_WON_MONEY_URL = HEADER + PROJECT + "lh_won_money.jsp";
        LH_BET_URL = HEADER + PROJECT + "lh_bet.jsp";
        LH_LUZI_URL = HEADER + PROJECT + "lh_luzi.jsp";
        REPORT_URL = HEADER + PROJECT + (gameType == 0 ? "report.jsp" : "report.jsp");
        AppDig88LoginUrl = HEADER + PROJECT + (gameType == 0 ? "login.jsp" : "login.jsp");
        AppWebServiceUrl = gameType == 0 ? "http://www.greendragon88.com/kgapi/GD88digWS?wsdl" : "http://www.greendragon88.com/Dig88WS?wsdl";
        AppWebServiceNameSpace = gameType == 0 ? "http://Dig88/" : "http://OLTGames/";

        TABLE_INFO_A_URL = HEADER + PROJECT + "select_tb_infoa.jsp";

        TABLEINFO_URL_C = HEADER + PROJECT + "select_tb_infoc.jsp";
        COUNTDOWN_URL_A = HEADER + PROJECT + "select_Timer.jsp";
        COUNTDOWN_URL_A_B = HEADER + PROJECT + "select_Timerab.jsp";
        COUNTDOWN_URL_B = HEADER + PROJECT + "select_Timerb.jsp";
        COUNTDOWN_URL_C = HEADER + PROJECT + "select_Timerf.jsp";
        REFERRAL_LIST = HEADER + PROJECT + "Daftar.jsp";
    }


    public static void setDomainDIGKorean(int gameType) {

        isDomain = true;
        GameType = gameType;
        Register_Url = HEADER + PROJECT + "register2.jsp";
        GetDeposit_Url = HEADER + PROJECT + "getDeposit.jsp";
        GetWithdraw_Url = HEADER + PROJECT + "getWithdraw.jsp";
        Deposit_Url = HEADER + PROJECT + "deposit.jsp";
        Withdraw_Url = HEADER + PROJECT + "withdraw.jsp";
        HEADER = "http://42.168gdc.com/";
        PROJECT = "DIGKorean/";
        INDEX = HEADER + PROJECT + "index.jsp";
        LANGUAGE_URL = HEADER + "updateOnlineUser.jsp";
        LOGIN_URL = HEADER + PROJECT + BuildConfig.loginjsp;
        GAME_GG_URL = HEADER + PROJECT + "gameGG.jsp";
        TABLEINFO_URL = HEADER + PROJECT + "select_tb_infoa.jsp";
        COUNTDOWN_URL = HEADER + PROJECT + "select_Timer.jsp";
        LOGOUT_URL = HEADER + PROJECT + "main.jsp";
        BJL_TABLE_STATUS_URL = HEADER + PROJECT + "bjl_state.jsp";
        BJL_TABLE_HALL_CHOOSE_SEAT_URL = HEADER + PROJECT + "choice_tb.jsp";
        BJL_TABLE_HALL_CHOOSE_SINGLE_SEAT_URL = HEADER + PROJECT + "choice_single_tb.jsp";
        BJL_TABLE_GAMENUM = HEADER + PROJECT + "bjl_gamenum.jsp";
        BJL_LUZI_URL = HEADER + PROJECT + "luzi.jsp";
        BJL_BET_MONEY_URL = HEADER + PROJECT + "bjl_bet_money.jsp";
        BJL_WON_MONEY_URL = HEADER + PROJECT + "bjl_won_money.jsp";
        BJL_BET_URL = HEADER + PROJECT + "bjl_bet.jsp";

        SICBO_BET_MONEY_URL = HEADER + PROJECT + "sb_bet_money.jsp";
        SICBO_LUZI_URL = HEADER + PROJECT + "sb_luzi.jsp";
        SICBO_TABLE_GAMENUM = HEADER + PROJECT + "sb_gamenum.jsp";
        SICBO_WON_MONEY_URL = HEADER + PROJECT + "sb_won_money.jsp";
        SICBO_TABLE_STATUS_URL = HEADER + PROJECT + "sb_state.jsp";
        SICBO_BET_URL = HEADER + PROJECT + "sb_bet.jsp";

        LP_TABLE_STATUS_URL = HEADER + PROJECT + "lp_state.jsp";
        LP_BET_MONEY_URL = HEADER + PROJECT + "lp_bet_money.jsp";
        LP_TABLE_GAMENUM = HEADER + PROJECT + "lp_gamenum.jsp";
        LP_WON_MONEY_URL = HEADER + PROJECT + "lp_won_money.jsp";
        LP_BET_URL = HEADER + PROJECT + "lp_bet.jsp";
        LP_ROAD_URL = HEADER + PROJECT + "lp_luzih.jsp";

        LH_TABLE_STATUS_URL = HEADER + PROJECT + "lh_state.jsp";
        LH_BET_MONEY_URL = HEADER + PROJECT + "lh_bet_money.jsp";
        LH_TABLE_GAMENUM = HEADER + PROJECT + "lh_gamenum.jsp";
        LH_WON_MONEY_URL = HEADER + PROJECT + "lh_won_money.jsp";
        LH_BET_URL = HEADER + PROJECT + "lh_bet.jsp";
        LH_LUZI_URL = HEADER + PROJECT + "lh_luzi.jsp";
        REPORT_URL = HEADER + PROJECT + "report.jsp";
        AppDig88LoginUrl = HEADER + PROJECT + "cklogin.jsp";
        AppWebServiceUrl = "http://42.168gdc.com/DIGKorean/DIGknWS?wsdl";
        AppWebServiceNameSpace = "http://DIGKorean/";

        TABLE_INFO_A_URL = HEADER + PROJECT + "select_tb_infoa.jsp";
        TABLEINFO_URL_C = HEADER + PROJECT + "select_tb_infoc.jsp";
        COUNTDOWN_URL_A = HEADER + PROJECT + "select_Timer.jsp";
        COUNTDOWN_URL_A_B = HEADER + PROJECT + "select_Timerab.jsp";
        COUNTDOWN_URL_B = HEADER + PROJECT + "select_Timerb.jsp";
        COUNTDOWN_URL_C = HEADER + PROJECT + "select_Timerf.jsp";
        REFERRAL_LIST = HEADER + PROJECT + "Daftar.jsp";
    }

    public static String GET_GR_CASINO_URL = "http://app.info.dig88api.com/index.php?page=get_grcasino_agent";
    public static String GET_GD_CASINO_URL = "http://app.info.dig88api.com/index.php?page=get_gdcasino_agent";

    public static String DownLoadPicture = "http://www.appgd88.com/dealer/";
    public static String AppVersionUrl = "http://www.appgd88.com/gd88version.php?app=android";

    public static String ChatSocketHost = "96.9.71.8";
    public static int ChatSocketPort = 7275;

}
