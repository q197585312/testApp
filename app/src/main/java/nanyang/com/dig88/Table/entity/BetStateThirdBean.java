package nanyang.com.dig88.Table.entity;

import android.text.Html;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/14.
 */
public class BetStateThirdBean implements Serializable {
    /**
     * "ParUrl":""
     * RefNo : HDP1132201026
     * Date : 2015/12/09 5:21:17 pm
     * ModuleTitle : AUSTRALIA HYUNDAI A LEAGUE
     * Home : Brisbane Roar [n]
     * Away : Wellington Phoenix
     * IsRun :
     * FirstHalf :
     * RunHomeScore :
     * RunAwayScore :
     * WorkingDate : 2015/12/12
     * Result : 2-1
     * BetType : Wellington Phoenix
     * Status :
     * HdpOdds : +0.5
     * Odds : <span class='Negative'>-1.02</span>
     * Amount : 12.00
     * ValidAmount : 12.00
     * WinLose : <SPAN class='negative'>-12.24</SPAN>
     * Com : <SPAN class='positive'>0.00</SPAN>
     */
    private String ParUrl;
    @SerializedName("RefNo")
    private String RefNo;
    @SerializedName("Date")
    private String Date;
    @SerializedName("ModuleTitle")
    private String ModuleTitle;
    @SerializedName("Home")
    private String Home;
    @SerializedName("Away")
    private String Away;
    @SerializedName("IsRun")
    private String IsRun;
    @SerializedName("FirstHalf")
    private String FirstHalf;
    @SerializedName("RunHomeScore")
    private String RunHomeScore;
    @SerializedName("RunAwayScore")
    private String RunAwayScore;
    @SerializedName("WorkingDate")
    private String WorkingDate;
    @SerializedName("Result")
    private String Result;
    @SerializedName("BetType")
    private String BetType;
    @SerializedName("Status")
    private String Status;
    @SerializedName("HdpOdds")
    private String HdpOdds;
    @SerializedName("Odds")
    private String Odds;
    @SerializedName("Amount")
    private String Amount;
    @SerializedName("ValidAmount")
    private String ValidAmount;
    @SerializedName("WinLose")
    private String WinLose;
    @SerializedName("Com")
    private String Com;

    public String getParUrl() {
        return ParUrl;
    }

    public void setParUrl(String parUrl) {
        ParUrl = parUrl;
    }

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String RefNo) {
        this.RefNo = RefNo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getModuleTitle() {
        return ModuleTitle;
    }

    public void setModuleTitle(String ModuleTitle) {
        this.ModuleTitle = ModuleTitle;
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

    public String getIsRun() {
        return IsRun;
    }

    public void setIsRun(String IsRun) {
        this.IsRun = IsRun;
    }

    public String getFirstHalf() {
        return FirstHalf;
    }

    public void setFirstHalf(String FirstHalf) {
        this.FirstHalf = FirstHalf;
    }

    public String getRunHomeScore() {
        return RunHomeScore;
    }

    public void setRunHomeScore(String RunHomeScore) {
        this.RunHomeScore = RunHomeScore;
    }

    public String getRunAwayScore() {
        return RunAwayScore;
    }

    public void setRunAwayScore(String RunAwayScore) {
        this.RunAwayScore = RunAwayScore;
    }

    public String getWorkingDate() {
        return WorkingDate;
    }

    public void setWorkingDate(String WorkingDate) {
        this.WorkingDate = WorkingDate;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
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

    public String getOdds() {
        return Html.fromHtml(Odds).toString();
    }

    public void setOdds(String Odds) {
        this.Odds = Odds;
    }

    public String getAmount() {
        return Html.fromHtml(Amount).toString();
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getValidAmount() {
        return Html.fromHtml(ValidAmount).toString();
    }

    public void setValidAmount(String ValidAmount) {
        this.ValidAmount = ValidAmount;
    }

    public String getWinLose() {
        return Html.fromHtml(WinLose).toString();
    }

    public void setWinLose(String WinLose) {
        this.WinLose = WinLose;
    }

    public String getCom() {
        return Html.fromHtml(Com).toString();
    }

    public void setCom(String Com) {
        this.Com = Com;
    }
}
