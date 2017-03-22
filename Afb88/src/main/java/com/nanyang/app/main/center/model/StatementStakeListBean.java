package com.nanyang.app.main.center.model;

/**
 * Created by Administrator on 2017/3/22.
 */

public class StatementStakeListBean {
    /**
     * Date : 16/03
     * Event : HUAY THAI Top Prize 1D VS HUAY THAI Top Prize 1D
     * ParamsUrl : AccMatchTrans_App.aspx?userName=demoafbai4&workingDate=2017/03/16&home=213313&away=213313
     * Amount : 27
     * WinLose : -27.00
     * Com : 0.00
     */

    private String Date;
    private String Event;
    private String ParamsUrl;
    private String Amount;
    private String WinLose;
    private String Com;

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String Event) {
        this.Event = Event;
    }

    public String getParamsUrl() {
        return ParamsUrl;
    }

    public void setParamsUrl(String ParamsUrl) {
        this.ParamsUrl = ParamsUrl;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getWinLose() {
        return WinLose;
    }

    public void setWinLose(String WinLose) {
        this.WinLose = WinLose;
    }

    public String getCom() {
        return Com;
    }

    public void setCom(String Com) {
        this.Com = Com;
    }
}
