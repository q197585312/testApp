package nanyang.com.dig88.Table.entity;

import android.text.Html;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/14.
 */
public class BetStatesecondBean implements Serializable{

    /**
     * Date : 13/12
     * Event : USA [w]&nbsp;VS&nbsp;China [w]
     * ParamsUrl : AccMatchTrans_App.aspx?userName=t@dc2stest003&workingDate=2015/12/13&home=5631&away=902
     * Amount : <SPAN class='positive'>12</SPAN>
     * WinLose : <SPAN class='positive'>0.00</SPAN>
     * Com : <SPAN class='positive'>0.00</SPAN>
     */

    @SerializedName("Date")
    private String Date;
    @SerializedName("Event")
    private String Event;
    @SerializedName("ParamsUrl")
    private String ParamsUrl;
    @SerializedName("Amount")
    private String Amount;
    @SerializedName("WinLose")
    private String WinLose;
    @SerializedName("Com")
    private String Com;

    public String getDate() {
        return Html.fromHtml(Date).toString();
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getEvent() {
        return Html.fromHtml(Event).toString();
    }

    public void setEvent(String Event) {
        this.Event = Event;
    }

    public String getParamsUrl() {
        return Html.fromHtml(ParamsUrl).toString();
    }

    public void setParamsUrl(String ParamsUrl) {
        this.ParamsUrl = ParamsUrl;
    }

    public String getAmount() {
        return Html.fromHtml(Amount).toString();
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
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
