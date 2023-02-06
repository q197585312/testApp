package nanyang.com.dig88.Lottery;

import java.io.Serializable;
import java.util.List;

import nanyang.com.dig88.Entity.Lottery234BetBean;

/**
 * Created by Administrator on 2016/2/27.
 */
public class ColokBean implements Serializable {
   int positionType;
    String colokTitle1;
    String colokTitle2;
    String discount1;
    String discount2;
    String odds1;
    String odds2;
    String odds3;
    String odds4;
    String kei;
    LotteryLimitBean limit2;
    List<Lottery234BetBean> betList;

    public ColokBean(int positionType,List<Lottery234BetBean> betList, String colokTitle1, String colokTitle2, String discount1, String discount2, String odds1, String odds2, String odds3, String odds4,String kei) {
        this.betList = betList;
        this.colokTitle1 = colokTitle1;
        this.colokTitle2 = colokTitle2;
        this.discount1 = discount1;
        this.discount2 = discount2;
        this.odds1 = odds1;
        this.odds2 = odds2;
        this.odds3 = odds3;
        this.odds4 = odds4;
        this.positionType = positionType;
        this.kei=kei;
    }

    public LotteryLimitBean getLimit2() {
        return limit2;
    }

    public void setLimit2(LotteryLimitBean limit2) {
        this.limit2 = limit2;
    }

    public String getKei() {
        return kei;
    }

    public void setKei(String kei) {
        this.kei = kei;
    }

    public List<Lottery234BetBean> getBetList() {
        return betList;
    }

    public void setBetList(List<Lottery234BetBean> betList) {
        this.betList = betList;
    }

    public int getPositionType() {
        return positionType;
    }

    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    public String getColokTitle1() {
        return colokTitle1;
    }

    public void setColokTitle1(String colokTitle1) {
        this.colokTitle1 = colokTitle1;
    }

    public String getColokTitle2() {
        return colokTitle2;
    }

    public void setColokTitle2(String colokTitle2) {
        this.colokTitle2 = colokTitle2;
    }

    public String getDiscount1() {
        return discount1;
    }

    public void setDiscount1(String discount1) {
        this.discount1 = discount1;
    }

    public String getDiscount2() {
        return discount2;
    }

    public void setDiscount2(String discount2) {
        this.discount2 = discount2;
    }

    public String getOdds1() {
        return odds1;
    }

    public void setOdds1(String odds1) {
        this.odds1 = odds1;
    }

    public String getOdds2() {
        return odds2;
    }

    public void setOdds2(String odds2) {
        this.odds2 = odds2;
    }

    public String getOdds3() {
        return odds3;
    }

    public void setOdds3(String odds3) {
        this.odds3 = odds3;
    }

    public String getOdds4() {
        return odds4;
    }

    public void setOdds4(String odds4) {
        this.odds4 = odds4;
    }
}
