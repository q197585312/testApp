package nanyang.com.dig88.Table.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
public class TableMaxPlayBean implements Serializable {


    /**
     * {
     "JSOdds": ["-1", "-1", "False", "False", "478004", "CLUB WORLD CUP 2015 [ IN JAPAN ]", "", "", false, "", "", "Draw", false, false, "", "", "", false, false, "", "", "", false, false, "0", "", "", false, false,"", "", false, false, "", "", "", false, false, "", "", "", false, false, "0", "", "", "eng" ],["9215866", "9215867", "True", "True", "478004", "CLUB WORLD CUP 2015 [ IN JAPAN ]", "CTH 046
     * M811", "06:30PM", true, "Barcelona", "Guangzhou Evergrande", "Draw", true, false, "1.07", "18.37", "8.98", true, false, "2.5", "1.93", "1.96", true, false, "3.5", "1.99", "1.88", true, false,"1.96", "1.90", true, false, "1.34", "11.98", "3.48", true, false, "1", "1.82", "2.03", true, false, "1.5", "2.03", "1.82", "eng" ],["9215877", "9215878", "True", "True", "478004", "CLUB WORLD CUP 2015 [ IN JAPAN ]", "CTH 046
     * M811", "06:30PM", true, "Barcelona", "Guangzhou Evergrande", "Draw", false, false, "", "", "", true, false, "2.5-3", "2.15", "1.76", true, false, "3.5-4", "2.23", "1.69", false, false,"", "", false, false, "", "", "", true, false, "1-1.5", "2.19", "1.69", true, false, "1.5-2", "2.42", "1.56", "eng" ]* SocOddsId : 0
     * "SocOddsId": 0, "SocOddsId_FH": 1, "IsInetBet": 2, "IsInetBet_FH": 3, "ModuleId": 4, "ModuleTitle": 5, "Live": 6, "MatchDate": 7, "IsHomeGive": 8, "Home": 9, "Away": 10, "Draw": 11, "HasX12": 12, "IsX12New": 13, "X12_1Odds": 14, "X12_2Odds": 15, "X12_XOdds": 16, "HasHdp": 17, "IsHdpNew": 18, "HdpOdds": 19, "HomeOdds": 20, "AwayOdds": 21, "HasOU": 22, "IsOUNew": 23, "OUOdds": 24, "OverOdds": 25, "UnderOdds": 26, "HasOE": 27, "IsOENew": 28, "OEOdds": 29, "OddOdds": 30, "EvenOdds": 31, "HasX12_FH": 32, "IsX12New_FH": 33, "X12_1Odds_FH": 34, "X12_2Odds_FH": 35, "X12_XOdds_FH": 36, "HasHdp_FH": 37, "IsHdpNew_FH": 38, "HdpOdds_FH": 39, "HomeOdds_FH": 40, "AwayOdds_FH": 41, "HasOU_FH": 42, "IsOUNew_FH": 43, "OUOdds_FH": 44, "OverOdds_FH": 45, "UnderOdds_FH": 46 }
     */

    @SerializedName("SocOddsId")
    private int SocOddsId;
    @SerializedName("SocOddsId_FH")
    private int SocOddsIdFH;
    @SerializedName("IsInetBet")
    private int IsInetBet;
    @SerializedName("IsInetBet_FH")
    private int IsInetBetFH;
    @SerializedName("ModuleId")
    private int ModuleId;
    @SerializedName("ModuleTitle")
    private int ModuleTitle;
    @SerializedName("Live")
    private int Live;
    @SerializedName("MatchDate")
    private int MatchDate;
    @SerializedName("IsHomeGive")
    private int IsHomeGive;
    @SerializedName("Home")
    private int Home;
    @SerializedName("Away")
    private int Away;
    @SerializedName("Draw")
    private int Draw;
    @SerializedName("HasX12")
    private int HasX12;
    @SerializedName("IsX12New")
    private int IsX12New;
    @SerializedName("X12_1Odds")
    private int X121Odds;
    @SerializedName("X12_2Odds")
    private int X122Odds;
    @SerializedName("X12_XOdds")
    private int X12XOdds;
    @SerializedName("HasHdp")
    private int HasHdp;
    @SerializedName("IsHdpNew")
    private int IsHdpNew;
    @SerializedName("HdpOdds")
    private int HdpOdds;
    @SerializedName("HomeOdds")
    private int HomeOdds;
    @SerializedName("AwayOdds")
    private int AwayOdds;
    @SerializedName("HasOU")
    private int HasOU;
    @SerializedName("IsOUNew")
    private int IsOUNew;
    @SerializedName("OUOdds")
    private int OUOdds;
    @SerializedName("OverOdds")
    private int OverOdds;
    @SerializedName("UnderOdds")
    private int UnderOdds;
    @SerializedName("HasOE")
    private int HasOE;
    @SerializedName("IsOENew")
    private int IsOENew;
    @SerializedName("OEOdds")
    private int OEOdds;
    @SerializedName("OddOdds")
    private int OddOdds;
    @SerializedName("EvenOdds")
    private int EvenOdds;
    @SerializedName("HasX12_FH")
    private int HasX12FH;
    @SerializedName("IsX12New_FH")
    private int IsX12NewFH;
    @SerializedName("X12_1Odds_FH")
    private int X121OddsFH;
    @SerializedName("X12_2Odds_FH")
    private int X122OddsFH;
    @SerializedName("X12_XOdds_FH")
    private int X12XOddsFH;
    @SerializedName("HasHdp_FH")
    private int HasHdpFH;
    @SerializedName("IsHdpNew_FH")
    private int IsHdpNewFH;
    @SerializedName("HdpOdds_FH")
    private int HdpOddsFH;
    @SerializedName("HomeOdds_FH")
    private int HomeOddsFH;
    @SerializedName("AwayOdds_FH")
    private int AwayOddsFH;
    @SerializedName("HasOU_FH")
    private int HasOUFH;
    @SerializedName("IsOUNew_FH")
    private int IsOUNewFH;
    @SerializedName("OUOdds_FH")
    private int OUOddsFH;
    @SerializedName("OverOdds_FH")
    private int OverOddsFH;
    @SerializedName("UnderOdds_FH")
    private int UnderOddsFH;
    @SerializedName("JSOdds")
    private List<List<String>> JSOdds;

    public int getSocOddsId() {
        return SocOddsId;
    }

    public void setSocOddsId(int SocOddsId) {
        this.SocOddsId = SocOddsId;
    }

    public int getSocOddsIdFH() {
        return SocOddsIdFH;
    }

    public void setSocOddsIdFH(int SocOddsIdFH) {
        this.SocOddsIdFH = SocOddsIdFH;
    }

    public int getIsInetBet() {
        return IsInetBet;
    }

    public void setIsInetBet(int IsInetBet) {
        this.IsInetBet = IsInetBet;
    }

    public int getIsInetBetFH() {
        return IsInetBetFH;
    }

    public void setIsInetBetFH(int IsInetBetFH) {
        this.IsInetBetFH = IsInetBetFH;
    }

    public int getModuleId() {
        return ModuleId;
    }

    public void setModuleId(int ModuleId) {
        this.ModuleId = ModuleId;
    }

    public int getModuleTitle() {
        return ModuleTitle;
    }

    public void setModuleTitle(int ModuleTitle) {
        this.ModuleTitle = ModuleTitle;
    }

    public int getLive() {
        return Live;
    }

    public void setLive(int Live) {
        this.Live = Live;
    }

    public int getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(int MatchDate) {
        this.MatchDate = MatchDate;
    }

    public int getIsHomeGive() {
        return IsHomeGive;
    }

    public void setIsHomeGive(int IsHomeGive) {
        this.IsHomeGive = IsHomeGive;
    }

    public int getHome() {
        return Home;
    }

    public void setHome(int Home) {
        this.Home = Home;
    }

    public int getAway() {
        return Away;
    }

    public void setAway(int Away) {
        this.Away = Away;
    }

    public int getDraw() {
        return Draw;
    }

    public void setDraw(int Draw) {
        this.Draw = Draw;
    }

    public int getHasX12() {
        return HasX12;
    }

    public void setHasX12(int HasX12) {
        this.HasX12 = HasX12;
    }

    public int getIsX12New() {
        return IsX12New;
    }

    public void setIsX12New(int IsX12New) {
        this.IsX12New = IsX12New;
    }

    public int getX121Odds() {
        return X121Odds;
    }

    public void setX121Odds(int X121Odds) {
        this.X121Odds = X121Odds;
    }

    public int getX122Odds() {
        return X122Odds;
    }

    public void setX122Odds(int X122Odds) {
        this.X122Odds = X122Odds;
    }

    public int getX12XOdds() {
        return X12XOdds;
    }

    public void setX12XOdds(int X12XOdds) {
        this.X12XOdds = X12XOdds;
    }

    public int getHasHdp() {
        return HasHdp;
    }

    public void setHasHdp(int HasHdp) {
        this.HasHdp = HasHdp;
    }

    public int getIsHdpNew() {
        return IsHdpNew;
    }

    public void setIsHdpNew(int IsHdpNew) {
        this.IsHdpNew = IsHdpNew;
    }

    public int getHdpOdds() {
        return HdpOdds;
    }

    public void setHdpOdds(int HdpOdds) {
        this.HdpOdds = HdpOdds;
    }

    public int getHomeOdds() {
        return HomeOdds;
    }

    public void setHomeOdds(int HomeOdds) {
        this.HomeOdds = HomeOdds;
    }

    public int getAwayOdds() {
        return AwayOdds;
    }

    public void setAwayOdds(int AwayOdds) {
        this.AwayOdds = AwayOdds;
    }

    public int getHasOU() {
        return HasOU;
    }

    public void setHasOU(int HasOU) {
        this.HasOU = HasOU;
    }

    public int getIsOUNew() {
        return IsOUNew;
    }

    public void setIsOUNew(int IsOUNew) {
        this.IsOUNew = IsOUNew;
    }

    public int getOUOdds() {
        return OUOdds;
    }

    public void setOUOdds(int OUOdds) {
        this.OUOdds = OUOdds;
    }

    public int getOverOdds() {
        return OverOdds;
    }

    public void setOverOdds(int OverOdds) {
        this.OverOdds = OverOdds;
    }

    public int getUnderOdds() {
        return UnderOdds;
    }

    public void setUnderOdds(int UnderOdds) {
        this.UnderOdds = UnderOdds;
    }

    public int getHasOE() {
        return HasOE;
    }

    public void setHasOE(int HasOE) {
        this.HasOE = HasOE;
    }

    public int getIsOENew() {
        return IsOENew;
    }

    public void setIsOENew(int IsOENew) {
        this.IsOENew = IsOENew;
    }

    public int getOEOdds() {
        return OEOdds;
    }

    public void setOEOdds(int OEOdds) {
        this.OEOdds = OEOdds;
    }

    public int getOddOdds() {
        return OddOdds;
    }

    public void setOddOdds(int OddOdds) {
        this.OddOdds = OddOdds;
    }

    public int getEvenOdds() {
        return EvenOdds;
    }

    public void setEvenOdds(int EvenOdds) {
        this.EvenOdds = EvenOdds;
    }

    public int getHasX12FH() {
        return HasX12FH;
    }

    public void setHasX12FH(int HasX12FH) {
        this.HasX12FH = HasX12FH;
    }

    public int getIsX12NewFH() {
        return IsX12NewFH;
    }

    public void setIsX12NewFH(int IsX12NewFH) {
        this.IsX12NewFH = IsX12NewFH;
    }

    public int getX121OddsFH() {
        return X121OddsFH;
    }

    public void setX121OddsFH(int X121OddsFH) {
        this.X121OddsFH = X121OddsFH;
    }

    public int getX122OddsFH() {
        return X122OddsFH;
    }

    public void setX122OddsFH(int X122OddsFH) {
        this.X122OddsFH = X122OddsFH;
    }

    public int getX12XOddsFH() {
        return X12XOddsFH;
    }

    public void setX12XOddsFH(int X12XOddsFH) {
        this.X12XOddsFH = X12XOddsFH;
    }

    public int getHasHdpFH() {
        return HasHdpFH;
    }

    public void setHasHdpFH(int HasHdpFH) {
        this.HasHdpFH = HasHdpFH;
    }

    public int getIsHdpNewFH() {
        return IsHdpNewFH;
    }

    public void setIsHdpNewFH(int IsHdpNewFH) {
        this.IsHdpNewFH = IsHdpNewFH;
    }

    public int getHdpOddsFH() {
        return HdpOddsFH;
    }

    public void setHdpOddsFH(int HdpOddsFH) {
        this.HdpOddsFH = HdpOddsFH;
    }

    public int getHomeOddsFH() {
        return HomeOddsFH;
    }

    public void setHomeOddsFH(int HomeOddsFH) {
        this.HomeOddsFH = HomeOddsFH;
    }

    public int getAwayOddsFH() {
        return AwayOddsFH;
    }

    public void setAwayOddsFH(int AwayOddsFH) {
        this.AwayOddsFH = AwayOddsFH;
    }

    public int getHasOUFH() {
        return HasOUFH;
    }

    public void setHasOUFH(int HasOUFH) {
        this.HasOUFH = HasOUFH;
    }

    public int getIsOUNewFH() {
        return IsOUNewFH;
    }

    public void setIsOUNewFH(int IsOUNewFH) {
        this.IsOUNewFH = IsOUNewFH;
    }

    public int getOUOddsFH() {
        return OUOddsFH;
    }

    public void setOUOddsFH(int OUOddsFH) {
        this.OUOddsFH = OUOddsFH;
    }

    public int getOverOddsFH() {
        return OverOddsFH;
    }

    public void setOverOddsFH(int OverOddsFH) {
        this.OverOddsFH = OverOddsFH;
    }

    public int getUnderOddsFH() {
        return UnderOddsFH;
    }

    public void setUnderOddsFH(int UnderOddsFH) {
        this.UnderOddsFH = UnderOddsFH;
    }

    public List<List<String>> getJSOdds() {
        return JSOdds;
    }

    public void setJSOdds(List<List<String>> JSOdds) {
        this.JSOdds = JSOdds;
    }
}
