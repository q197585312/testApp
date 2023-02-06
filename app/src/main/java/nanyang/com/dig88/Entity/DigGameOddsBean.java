package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/30.
 */
public class DigGameOddsBean implements Serializable {


    /**
     * type1 : 5
     * type2 : 1
     * type3 : 1
     * min_bet : 1
     * max_bet : 300
     * max_total : 1000
     * discount : 29.75
     * factor : 70
     * factor2 : 0
     * factor3 : 0
     * factor4 : 0
     * kei : 0
     * kei2 : 0
     * kei3 : 0
     * kei4 : 0
     */
    private static final long serialVersionUID = 1L;
    private String type1;
    private String type2;
    private String type3;
    private String min_bet;
    private String max_bet;
    private String max_total;
    private String discount;
    private String factor;
    private String factor2;
    private String factor3;
    private String factor4;
    private String kei;
    private String kei2;
    private String kei3;
    private String kei4;
    private String bigsmall;
    private String doublesimgle;
    private String combination;
    private String number;
    private String color;
    private String daojishi;
    private String opentimetime;
    private String zhouqi;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZhouqi() {
        return zhouqi;
    }

    public void setZhouqi(String zhouqi) {
        this.zhouqi = zhouqi;
    }

    public String getOpentimetime() {
        return opentimetime;
    }

    public void setOpentimetime(String opentimetime) {
        this.opentimetime = opentimetime;
    }

    public String getDaojishi() {
        return daojishi;
    }

    public void setDaojishi(String daojishi) {
        this.daojishi = daojishi;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBigsmall() {
        return bigsmall;
    }

    public void setBigsmall(String bigsmall) {
        this.bigsmall = bigsmall;
    }

    public String getDoublesimgle() {
        return doublesimgle;
    }


    public void setDoublesimgle(String doublesimgle) {
        this.doublesimgle = doublesimgle;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getMin_bet() {
        return min_bet;
    }

    public void setMin_bet(String min_bet) {
        this.min_bet = min_bet;
    }

    public String getMax_bet() {
        return max_bet;
    }

    public void setMax_bet(String max_bet) {
        this.max_bet = max_bet;
    }

    public String getMax_total() {
        return max_total;
    }

    public void setMax_total(String max_total) {
        this.max_total = max_total;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getFactor2() {
        return factor2;
    }

    public void setFactor2(String factor2) {
        this.factor2 = factor2;
    }

    public String getFactor3() {
        return factor3;
    }

    public void setFactor3(String factor3) {
        this.factor3 = factor3;
    }

    public String getFactor4() {
        return factor4;
    }

    public void setFactor4(String factor4) {
        this.factor4 = factor4;
    }

    public String getKei() {
        return kei;
    }

    public void setKei(String kei) {
        this.kei = kei;
    }

    public String getKei2() {
        return kei2;
    }

    public void setKei2(String kei2) {
        this.kei2 = kei2;
    }

    public String getKei3() {
        return kei3;
    }

    public void setKei3(String kei3) {
        this.kei3 = kei3;
    }

    public String getKei4() {
        return kei4;
    }

    public void setKei4(String kei4) {
        this.kei4 = kei4;
    }

    @Override
    public String toString() {
        return "GameOddsBean{" +
                "type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", type3='" + type3 + '\'' +
                ", min_bet='" + min_bet + '\'' +
                ", max_bet='" + max_bet + '\'' +
                ", max_total='" + max_total + '\'' +
                ", discount='" + discount + '\'' +
                ", factor='" + factor + '\'' +
                ", factor2='" + factor2 + '\'' +
                ", factor3='" + factor3 + '\'' +
                ", factor4='" + factor4 + '\'' +
                ", kei='" + kei + '\'' +
                ", kei2='" + kei2 + '\'' +
                ", kei3='" + kei3 + '\'' +
                ", kei4='" + kei4 + '\'' +
                '}';
    }
}
