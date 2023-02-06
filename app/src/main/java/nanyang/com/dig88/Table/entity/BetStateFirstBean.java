package nanyang.com.dig88.Table.entity;

import android.text.Html;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/14.
 */
public class BetStateFirstBean implements Serializable {

    /**
     * Date : 14/12(Mon)
     * Stake : 0
     * ParamsUrl :
     * ValidAmount : 0
     * WL : 0.00
     * Com : 0.00
     */

    @SerializedName("Date")
    private String Date;
    @SerializedName("Stake")
    private String Stake;
    @SerializedName("ParamsUrl")
    private String ParamsUrl;
    @SerializedName("ValidAmount")
    private String ValidAmount;
    @SerializedName("WL")
    private String WL;
    @SerializedName("Com")
    private String Com;

    public String getDate() {
        return Html.fromHtml(Date).toString();
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getStake() {
        return Html.fromHtml(Stake).toString();
    }

    public void setStake(String Stake) {
        this.Stake = Stake;
    }

    public String getParamsUrl() {
        return Html.fromHtml(ParamsUrl).toString();
    }

    public void setParamsUrl(String ParamsUrl) {
        this.ParamsUrl = ParamsUrl;
    }

    public String getValidAmount() {
        return Html.fromHtml(ValidAmount).toString();
    }

    public void setValidAmount(String ValidAmount) {
        this.ValidAmount = ValidAmount;
    }

    public String getWL() {
        return Html.fromHtml(WL).toString();
    }

    public void setWL(String WL) {
        this.WL = WL;
    }

    public String getCom() {
        return Html.fromHtml(Com).toString();
    }

    public void setCom(String Com) {
        this.Com = Com;
    }
}
