package com.nanyang.app.load.welcome;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2019/3/12.
 */

public class AllBannerImagesBean implements Serializable {


    @Override
    public String toString() {
        return "AllBannerImagesBean{" +
                "status='" + status + '\'' +
                ", loginBanners=" + loginBanners +
                ", mainBanners=" + mainBanners +
                ", main=" + main +
                '}';
    }

    /**
     * status : 0
     * loginBanners : [{"id":"1","url":"","img":"https://www.afb1188.com/H50/img/slide_show_festival.jpg"},{"id":"2","url":"","img":"https://www.afb1188.com/H50/img/1.jpg"},{"id":"3","url":"","img":"https://www.afb1188.com/H50/img/2.jpg"},{"id":"4","url":"","img":"https://www.afb1188.com/H50/img/3.jpg"}]
     * mainBanners : [{"id":"1","url":"","img":"http://appgd88.com/images/afb88/home_banner_1.jpg"},{"id":"2","url":"","img":"http://appgd88.com/images/afb88/home_banner_2.jpg"},{"id":"3","url":"","img":"http://appgd88.com/images/afb88/home_banner_3.jpg"}]
     * main : [{"id":"1","img":"https://www.afb1188.com/H50/Img/soccer.jpg"},{"id":"Cashio","img":"https://www.afb1188.com/H50/Img/baccarat.jpg"},{"id":"2","img":"https://www.afb1188.com/H50/Img/basketball.jpg"},{"id":"3","img":"https://www.afb1188.com/H50/Img/tennis.jpg"},{"id":"9","img":"https://www.afb1188.com/H50/Img/baseball.jpg"},{"id":"20","img":"https://www.afb1188.com/H50/Img/badminton.jpg"},{"id":"34","img":"https://www.afb1188.com/H50/Img/esports.jpg"},{"id":"14","img":"https://www.afb1188.com/H50/Img/boxing.jpg"},{"id":"23","img":"https://www.afb1188.com/H50/Img/cricket.jpg"},{"id":"26","img":"https://www.afb1188.com/H50/Img/cycling.jpg"},{"id":"13","img":"https://www.afb1188.com/H50/Img/darts.jpg"},{"id":"15","img":"https://www.afb1188.com/H50/Img/formula1.jpg"},{"id":"19","img":"https://www.afb1188.com/H50/Img/futsal.jpg"},{"id":"17","img":"https://www.afb1188.com/H50/Img/golf.jpg"},{"id":"25","img":"https://www.afb1188.com/H50/Img/handball.jpg"},{"id":"10","img":"https://www.afb1188.com/H50/Img/icehockey.jpg"},{"id":"16","img":"https://www.afb1188.com/H50/Img/motor.jpg"},{"id":"12","img":"https://www.afb1188.com/H50/Img/rugby.jpg"},{"id":"11","img":"https://www.afb1188.com/H50/Img/snooker.jpg"},{"id":"22","img":"https://www.afb1188.com/H50/Img/tabletennis.jpg"},{"id":"8","img":"https://www.afb1188.com/H50/Img/usfootball.jpg"},{"id":"24","img":"https://www.afb1188.com/H50/Img/volleyball.jpg"},{"id":"21","img":"https://www.afb1188.com/H50/Img/waterpolo.jpg"},{"id":"0","img":"https://www.afb1188.com/H50/Img/running.jpg"},{"id":"999","img":"https://www.afb1188.com/H50/Img/Outright.jpg"}]
     * lang : {"UserName":"戶口","timess":"03/11/2019 16:40:46","lang":"EN-CA","rememberMe":"記住我?","Login":"登錄","lblPassword":"密碼","mobile":"手機版","Desktop":"桌機版","accType":"MY","LoginName":"TestSH01","Myaccount":"我的账户","StatsPath":"http://www.stats888.com/Stat","Welcome":"歡迎","Balance":"結餘","Currency":"貨幣","CurCode2":"美圓","MessagesLog":"0","CashBalance":"現金餘額","balances":"0.00","Outstanding":"未完場投注","Etotalstanding":"0","MinBet":"最小注碼","MinLimit":"3","BetCredit":"投注額度","credit2":"0","GivenCredit":"信用額度","TotalCredit":"0","TabStatement":"賬目","ContactUs":"聯繫我們","TabResult":"成績","SETTING":"設定","Logout":"登出","FTMyanmarHDP":"FT.MyanmarHDP","FHMyanmarHDP":"FH.MyanmarHDP","FTMyanmarOU":"FT.MyanmarOU","FHMyanmarOU":"FH.MyanmarOU","Messages":"信息","Home":"主場","Away":"客場","Over":"大","Under":"小","Odd":"單","Even":"雙","FT":"全場.","FH":"上半場.","HDP":"讓球","OU":"大小","OE":"單雙盤","X12":"主客和","FTDoubleChance":"全場雙重機會","FTOddEven":"全場 單/雙","FTCorrectScore":"全場波膽","HALFTIME_FULLTIME":"半場/全場","FirstGoalLastGoal":"最先得分/最后得分","TotalGoal":"總入球","HOMETEAMTOTALGOALS":"主隊總進球","AWAYTEAMTOTALGOALS":"客場總入球","FT15MINSHANDICAP_OVER_UNDER":"全場15分鐘亞洲盤 & 大/小","FT15MINSHANDICAP_OVER_UNDER1":"全場15分鐘亞洲盤 & 大/小(00 =>00-15 =>00)","FT15MINSHANDICAP_OVER_UNDER2":"全場15分鐘亞洲盤 & 大/小(15 =>01-30 =>00)","FT15MINSHANDICAP_OVER_UNDER3":"全場15分鐘亞洲盤 & 大/小(30 =>01-45 =>00)","FT15MINSHANDICAP_OVER_UNDER4":"全場15分鐘亞洲盤 & 大/小(45 =>01-60 =>00)","FT15MINSHANDICAP_OVER_UNDER5":"全場15分鐘亞洲盤 & 大/小(60 =>01-75 =>00)","FirstGoal":"最先得分","LastGoal":"最後得分","NoGoal":"無得分","FHDoubleChance":"半場雙重機會","FHOddEven":"半場 單/雙","FHCorrectScore":"波膽(上半場)","Accepted2":"已接受","Jan":"1月","Feb":"2月","Mar":"3月","Apr":"4月","May":"5月","Jun":"6月","Jul":"7月","Aug":"8月","Sep":"9月","Oct":"10月","Nov":"11月","Dec":"12月","Howtouse":"使用方法","More1":"更多","Today":"今天球賽","Early":"早盤","All":"全部","Live":"滾球","SortbyTime":"時間排序","Others":"其他","ATIEB2":"A-TIE-B","Moneyline":"勝負盤","boxing":"拳擊","MyBET":"我的投注","HK2":"香港","EVEN2":"雙","ODD2":"單","X1":"主","XX":"和","X2":"客","HH2":"主主","HD2":"主和","HA2":"主客","DH2":"和主","DD2":"和和","DA2":"和客","AH2":"客主","AD2":"客和","AA2":"客客","min2":"分鐘","ScoreSound":"1","multiParMaxBet":10000,"SearchLeague":"選擇聯賽","Taptoadd":"過關投注需要選擇最少三場賽事或以上！","PPYB":"請完成您的下注","YourSel":"您選擇為：","StakePerBet":"每笔投注金额","TV":"及時視屏","Malay":"馬來","HK":"香港","MY":"MY","EU":"歐洲","ID":"編號","Accepted":"已接受","EXCEEDMIN":"金額應大於最小註碼","Simple":"簡易","Simple818":"簡易818賠率","MyanmarOdds":"緬甸賠率","Main":"主要","OtherBet":"更多玩法","Sports":"體育博彩","Single":"單注","Multiple":"多項投注","Parlay":"混合過關","Parlay2":"過關","Odds":"賠率","MaxPayout":"最大贏得金額","MaxMatch":"最高單場注額","MaxBet":"最大注碼","TotalStakes":"總的投注金額","TopLiga":"五大联赛","NickName":"暱稱","LastLogin":"登錄時間","PwdChange":"密碼過期","SetNickName":"設置暱稱","OKMY":"確定","CancelMY":"清除","Checkmessage":"暱稱可用","balance":"0.00","credit":"0","LastTime":"2019-03-11 16:40:46","NickNameshow":"","Result":"比數","Statement":"賬目","results":"成績","BULLFIGHTING":"BULLFIGHTING","KENO":"快樂彩","LOTTERY":"彩票","normal":"一般","Note":"注意","Pleasenote2":"請注意顯示時間為GMT+8時區","running":"進行中","WAITING":"等待中","MyBets":"我的投注","Tapthesports":"點擊下面的體育產品或進入體育投注頁面開始投注。","Nowaiting":"沒有等待中投注","FirstHalf":"上半場","Rejected":"被拒絕","CANCELLED2":"取消","Parlay3":"過關","Handicap":"亞州盤","OverUnder2":"大/小","OddEven2":"單/雙","DOUBLECHANCE":"雙重機會","CORRECTSCORE2":"波膽","FIRSTGOALLASTGOAL2":"最先得分/最后得分","TOTALGOAL2":"總入球","HALFTIMEFULLTIME2":"半場/全場","OpenDetail":"打開詳細信息","CloseDetail":"關閉詳細信息","TotalOdds":"總賠率","Password":"密碼","Language":"語言","OddsType":"水錢種類","Cancel":"清除","OK":"確定","Back":"返回","Customise":"自定義","QuicklyLink":"快捷鍵","AutoRefresh":"自動刷新","AccpetBetterOdds":"接受較好的賠率","DefaultSorting":"排序","MarketType":"盤口顯示","AllMarkets":"全部盤口","MainMarkets":"主要盤口","OtherBetMarkets":"更多玩法 盤口","NormalSorting":"熱門排序","ScoreSounds":"進球聲音","QuickBetAmount":"快速打賭金額","Chipset":"Chip Set","Sport":"體育類","HOME2":"首頁","TabMyBets":"我的投注","message":"請輸入金額!","LangChoose":"繁體中文","OldPSW":"**********","accType2":"MY","BetterOdds":1,"accMarketType":"0","accDefaultSorting":"0","accScoreSound":"1","accamount":100,"Chipsetwrong":"No less than five chips and no more than 8 chips","Lastweeksummary":"本週舊賬","Sunday":"日","Monday":"一","Tuesday":"二","Wednesday":"三","Thursday":"四","Friday":"五","Saturday":"六","win":"贏","lose":"輸","com":"佣金","draw":"和局","outrights":"優勝冠軍","Soccer":"足球","BIG_SMALL_3D_4D":"3D, 4D 大/小 &amp; 單/雙","Basketball":"籃球","Olympic":"奧運","USFootball":"美式足球","Baseball":"棒球","IceHockey":"冰球","PoolSnooker":"撞球/桌球","Rugby":"橄欖球","Tennis":"網球","Darts":"飛镖","Formula1":"一級方程式","MotorSports":"汽車運動","Golf":"高爾夫球","Futsal":"室內足球","Badminton":"羽毛球","WaterPolo":"水球","TableTennis":"乒乓球","Cricket":"板球","Volleyball":"排球","Handball":"手球","BeachSoccer":"沙灘足球","Financials":"股市","WinterSport":"滑雪運","Cycling":"單車","Squash":"壁球","HuayThai":"泰式千字","Athletics":"田徑運動","ESports":"電子競技","Muay_Thai":"泰拳","Outright":"優勝冠軍","TabToday":"今天球賽","Yesterday":"所有联赛","FirstHalfScore":"上半場比分","FinalScore":"最後賽果","Status":"狀態","Pending":"等待賽果","Completed":"已完場","ToDayMatch":"今日賽事","SportsC":"體育博彩","Allleague":"昨日赛事","Sooutright":"足球-優勝冠軍","SingleBet2":"單註","MultiBet":"多重投注","BettingMode":"下注模式","SingleBetMultiBetoption":"單註/多注的選擇","SelectProductandMarket":"選擇產品與時間區","FirstchooseSportsGameType":"1. 先選擇運動遊戲類別","ThenchooseTodayLiveEarly":"2. 選擇今日/滾球/早盤/日期 的聯賽 後列所有場次","selectleague":"選擇聯賽","TLeaguesunderSports":"- 在選定的運動或遊戲類別下顯示所有聯賽","Tapononeormoreleagues":"- 選擇想看的聯賽","BetSlip":"注單","HenabledwhenyouhaveMultiBets":"- 進入多注或混合過關的投注清單","HabovetodisplayalltheMultiBet":"- 顯示多注或混合過關待投注的數量","MyFavorites":"MY FAVORITES","HaddremovleaguesormatchesintoFavorites":"1. 在喜愛的聯賽或場次前點擊星星","Hallyourfavoriteleagues":"2. 點選右上角的星星來顯示有加入最愛的聯賽或場次","HToleavetheFavoritmode":"3. 再點選一次就能離開我的最愛模式","LiveMatch":"滾球賽事/滾球視訊","HallthematchesprovideLiveMatch":"1. 列出所有支持滾球賽事或者滾球視訊的比賽","Hbuttontowatchandbet":"2. 點擊\u201d滾球賽事\u201d或者\u201d滾球視訊\u201d的按鈕即可進行觀賞中下注","Quickaccesstopresetfunctions":"預設功能的快捷鍵","openlinks":"- 點選 \u2018+\u2019 展開","activatefunctions":"- 點選子圖示啟動該功能","Start":"開始","Skip":"略過","Announcements":"公告","MsgCol":"C2","Beton1numbe":"Bet on 1 number prediction over 3 possible number in top 3D prize draw. If top prize is 312, player will win the prize.","Beton1numbe2":"Bet on 1 number prediction over 2 possible number in bottom 2D prize draw.","Beton1numbe3":"Player can bet on One single number prediction on 3D top prize at 1st digit. Example, if outcome is 132, then player will get the payout.","Beton1numbe4":"Player can bet on Second single number prediction on 3D top prize at 2nd digit. Example, if outcome is 132, then player will get the payout.","Beton1numbe5":"Player can bet on Third single number prediction on 3D top prize at 3rddigit. Example, if outcome is 132, then player will get the payout.","DTops":"1D Top Prize","DBottom":"1D Bottom Prize","DTopFi":"1D Top Prize Fix 1","DTopFi2":"1D Top Prize Fix 2","DTopFi3":"1D Top Prize Fix 3","Pay3":"1 Pay 3","Pay4":"1 Pay 4","Pay9":"1 Pay 5","D2Top1":"2D Top Prize","D2Top2":"2D Bottom Prize","D2Top3":"2D Top Roll","Bnumber2Ddraw1":"1 x 2D number draw at top prize. Player needs to straight same sequence number in order to get payout.","Bnumber2Ddraw2":"1 x 2D number draw at bottom prize. Player needs to straight same sequence number in order to get payout.","Bnumber2Ddraw3":"Player can bet on 3D top prize Last two digit (\u201800). If outcome is 132 Outcome possible 12,13,21,23,31,32of total 6 outcomes. Any one fall into 2D top prize will be paid.","Pay80":"1 Pay 80","Pay12":"1 Pay 12","BtnSubmit":"提交","D3Top1":"3D Top Prize","D3Top2":"3D In front Prize","D3Top3":"3D Bottom Prize","D3Top4":"3D Top Prize Roll","Bnumber3D1":"Straight number prediction","Bnumber3D2":"Total of 2 x In front prizes draw. Player buy one number possible to hit one of the 2 In front numbers draw (3 digits).","Bnumber3D3":"Total of 2 x Bottom prizes draw. Player buy one number possible to hit one of the 2 bottom numbers draw (3 digits).","Bnumber3D4":"Random number predictions. Outcome possible, 123,132,213,231,312,321 of total 6 outcomes. Any one fall into 3D top prize will be paid.","Pay550":"1 Pay 550","Pay250":"1 Pay 250","Pay150":"1 Pay 105","Refresh":"更新","Confirmbet":"確定投注？","Plsamount":"請輸入金額!","canbetmix":"can't bet mix parlay","ChipSetChoose":"","M_Name36":"欧洲","M_TAm36":58,"M_RAm36":3,"M_EAm36":311,"M_Name1":"足球","M_TAm1":171,"M_RAm1":7,"M_EAm1":433,"M_Name2":"籃球","M_TAm2":34,"M_Name3":"網球","M_TAm3":31,"M_RAm3":3,"M_Name4":"股市","M_TAm4":22,"M_EAm4":104,"M_Name5":"4D遊戲","M_EAm5":91,"M_Name33":"泰式千字","M_Name33_18":"1D 遊戲","M_TAm33_18":5,"M_RAm33_18":0,"M_EAm33_18":5,"M_Name33_19":"2D遊戲","M_TAm33_19":3,"M_RAm33_19":0,"M_EAm33_19":3,"M_Name33_20":"3D遊戲","M_TAm33_20":4,"M_RAm33_20":0,"M_EAm33_20":4,"M_Name35":"泰拳","M_TAm35":7,"M_Name34":"電子競技","M_TAm34":29,"M_Name7":"奧運","M_Name8":"美式足球","M_Name9":"棒球","M_TAm9":9,"M_Name10":"冰球","M_TAm10":10,"M_EAm10":8,"M_Name11":"撞球/桌球","M_TAm11":19,"M_EAm11":31,"M_Name12":"橄欖球","M_EAm12":21,"M_Name13":"飛镖","M_Name14":"拳擊","M_Name15":"一級方程式","M_EAm15":2,"M_Name16":"汽車運動","M_Name17":"高爾夫球","M_Name19":"室內足球","M_Name20":"羽毛球","M_Name21":"水球","M_Name22":"乒乓球","M_Name23":"板球","M_Name24":"排球","M_TAm24":5,"M_Name25":"手球","M_TAm25":1,"M_Name26":"單車","M_EAm26":4,"M_Name27":"沙灘足球","M_Name29":"田徑運動","M_Name30":"滑雪運","M_Name31":"壁球","M_Name0":"全部 滾球","M_RAm0":13,"M_Name999":"優勝冠軍","M_EAm999":110,"M_Name66":"足球 - 緬甸賠率","M_RAm66":7,"M_TAm66":171,"M_EAm66":433}
     */

    private String status;


    /**
     * id : 1
     * url :
     * img : https://www.afb1188.com/H50/img/slide_show_festival.jpg
     */

    private List<BannersBean> loginBanners;
    /**
     * id : 1
     * url :
     * img : http://appgd88.com/images/afb88/home_banner_1.jpg
     */

    private List<BannersBean> mainBanners;

    /**
     * id : 1
     * img : https://www.afb1188.com/H50/Img/soccer.jpg
     */


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<BannersBean> getLoginBanners() {
        return loginBanners;
    }

    public void setLoginBanners(List<BannersBean> loginBanners) {
        this.loginBanners = loginBanners;
    }

    public List<BannersBean> getMainBanners() {
        return mainBanners;
    }

    public void setMainBanners(List<BannersBean> mainBanners) {
        this.mainBanners = mainBanners;
    }

    public List<MainBannersBean> getMain() {
        return main;
    }

    public void setMain(List<MainBannersBean> main) {
        this.main = main;
    }


    public static class LoginBannersBean {
        private String id;
        private String url;
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        @Override
        public String toString() {
            return "LoginBannersBean{" +
                    "id='" + id + '\'' +
                    ", url='" + url + '\'' +
                    ", img='" + img + '\'' +
                    '}';
        }
    }

    private List<MainBannersBean> main;

    public static class MainBannersBean {
        /*   "dbid": "1",
                   "g": "1",
                   "img": "https:\/\/www.afb1188.com\/H50\/Img\/soccer.jpg"
       }, {*/
        private String dbid;
        private String g;

        public String getDbid() {
            return dbid;
        }

        public void setDbid(String dbid) {
            this.dbid = dbid;
        }

        public String getG() {
            return g;
        }

        public void setG(String g) {
            this.g = g;
        }

        private String img;



        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }


    }

    public static class BannersBean {
        private String id;
        private String url;
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        @Override
        public String toString() {
            return "BannersBean{" +
                    "id='" + id + '\'' +
                    ", url='" + url + '\'' +
                    ", img='" + img + '\'' +
                    '}';
        }
    }


}
