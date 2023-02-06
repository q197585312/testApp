package nanyang.com.dig88.Table.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/12/4.
 */
public class BetParDetalsBean implements Serializable {


    /**
     * RefNo : PAR1139696755
     * DateTime : 28/12 4:44:25 pm
     * GameType2 : Parlay
     * Detail : [{"ModuleTitle":"SPAIN LA LIGA","IsFH":"","Home":"Las Palmas","Away":"Granada","MatchDate":"31/12 04:00AM","HomeScore":"","AwayScore":"","GameType":"亚州盤","Odds":"2.091","BetType":"Las Palmas","Status":"","HdpOdds":"0.5","Odds2":"2.091"},{"ModuleTitle":"SPAIN LA LIGA","IsFH":"","Home":"Getafe","Away":"Deportivo La Coruna","MatchDate":"31/12 03:30AM","HomeScore":"","AwayScore":"","GameType":"亚州盤","Odds":"1.82","BetType":"Getafe","Status":"","HdpOdds":"0","Odds2":"1.82"},{"ModuleTitle":"SPAIN LA LIGA","IsFH":"","Home":"Celta Vigo","Away":"Athletic Bilbao","MatchDate":"31/12 03:30AM","HomeScore":"","AwayScore":"","GameType":"亚州盤","Odds":"1.82","BetType":"Celta Vigo","Status":"","HdpOdds":"0","Odds2":"1.82"}]
     * Amount : 1.00
     * OddsCnt : 6.926
     * Payout : 6.93
     */

    @SerializedName("RefNo")
    private String RefNo;
    @SerializedName("DateTime")
    private String DateTime;
    @SerializedName("GameType2")
    private String GameType2;
    @SerializedName("Amount")
    private String Amount;
    @SerializedName("OddsCnt")
    private String OddsCnt;
    @SerializedName("Payout")
    private String Payout;
    /**
     * ModuleTitle : SPAIN LA LIGA
     * IsFH :
     * Home : Las Palmas
     * Away : Granada
     * MatchDate : 31/12 04:00AM
     * HomeScore :
     * AwayScore :
     * GameType : 亚州盤
     * Odds : 2.091
     * BetType : Las Palmas
     * Status :
     * HdpOdds : 0.5
     * Odds2 : 2.091
     */

    @SerializedName("Detail")
    private List<DetailBean> Detail;

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String RefNo) {
        this.RefNo = RefNo;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }

    public String getGameType2() {
        return GameType2;
    }

    public void setGameType2(String GameType2) {
        this.GameType2 = GameType2;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getOddsCnt() {
        return OddsCnt;
    }

    public void setOddsCnt(String OddsCnt) {
        this.OddsCnt = OddsCnt;
    }

    public String getPayout() {
        return Payout;
    }

    public void setPayout(String Payout) {
        this.Payout = Payout;
    }

    public List<DetailBean> getDetail() {
        return Detail;
    }

    public void setDetail(List<DetailBean> Detail) {
        this.Detail = Detail;
    }

    public static class DetailBean {
        @SerializedName("ModuleTitle")
        private String ModuleTitle;
        @SerializedName("IsFH")
        private String IsFH;
        @SerializedName("Home")
        private String Home;
        @SerializedName("Away")
        private String Away;
        @SerializedName("MatchDate")
        private String MatchDate;
        @SerializedName("HomeScore")
        private String HomeScore;
        @SerializedName("AwayScore")
        private String AwayScore;
        @SerializedName("GameType")
        private String GameType;
        @SerializedName("Odds")
        private String Odds;
        @SerializedName("BetType")
        private String BetType;
        @SerializedName("Status")
        private String Status;
        @SerializedName("HdpOdds")
        private String HdpOdds;
        @SerializedName("Odds2")
        private String Odds2;

        public String getModuleTitle() {
            return ModuleTitle;
        }

        public void setModuleTitle(String ModuleTitle) {
            this.ModuleTitle = ModuleTitle;
        }

        public String getIsFH() {
            return IsFH;
        }

        public void setIsFH(String IsFH) {
            this.IsFH = IsFH;
        }

        public String getHome() {
            return Home;
        }

        public void setHome(String Home) {
            this.Home = Home;
        }

        public String getAway() {
            return Away;
        }

        public void setAway(String Away) {
            this.Away = Away;
        }

        public String getMatchDate() {
            return MatchDate;
        }

        public void setMatchDate(String MatchDate) {
            this.MatchDate = MatchDate;
        }

        public String getHomeScore() {
            return HomeScore;
        }

        public void setHomeScore(String HomeScore) {
            this.HomeScore = HomeScore;
        }

        public String getAwayScore() {
            return AwayScore;
        }

        public void setAwayScore(String AwayScore) {
            this.AwayScore = AwayScore;
        }

        public String getGameType() {
            return GameType;
        }

        public void setGameType(String GameType) {
            this.GameType = GameType;
        }

        public String getOdds() {
            return Odds;
        }

        public void setOdds(String Odds) {
            this.Odds = Odds;
        }

        public String getBetType() {
            return BetType;
        }

        public void setBetType(String BetType) {
            this.BetType = BetType;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getHdpOdds() {
            return HdpOdds;
        }

        public void setHdpOdds(String HdpOdds) {
            this.HdpOdds = HdpOdds;
        }

        public String getOdds2() {
            return Odds2;
        }

        public void setOdds2(String Odds2) {
            this.Odds2 = Odds2;
        }
    }
}
