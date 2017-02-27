package com.nanyang.app.main.home.sport.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/9.
 */
public class HandicapBean implements Serializable {


    private String hasOu;
    private String hasHdp;
    String isHomeGive;                  //是否主队让球                ≈1,
    String Hdp;
    String HomeHdpOdds;
    String AwayHdpOdds;
    String SocOddsId;//全场、半场id
    String OU;
    String OverOdds;
    String UnderOdds;
    String isInetBet;
    String IsHdpNew;String IsOUNew;

    public HandicapBean(String isHomeGive,
                        String hdp,
                        String homeHdpOdds,
                        String awayHdpOdds,
                        String OU,
                        String overOdds,
                        String underOdds,
                        String socOddsId,
                        String IsInetBet,
                        String IsHdpNew,
                        String IsOUNew,
                        String hasHdp,
                        String hasOU) {
        this.isHomeGive = isHomeGive;
        Hdp = hdp;
        HomeHdpOdds = homeHdpOdds;
        AwayHdpOdds = awayHdpOdds;
        this.OU = OU;
        OverOdds = overOdds;
        UnderOdds = underOdds;
        SocOddsId=socOddsId;
        isInetBet=IsInetBet;
        this.IsHdpNew=IsHdpNew;
        this.IsOUNew=IsOUNew;
        this.hasHdp=hasHdp;
        this.hasOu=hasOU;
    }

    public void setHasOu(String hasOu) {
        this.hasOu = hasOu;
    }

    public void setHasHdp(String hasHdp) {
        this.hasHdp = hasHdp;
    }

    public String getHasOu() {
        return hasOu;
    }

    public String getHasHdp() {
        return hasHdp;
    }

    public String getIsHdpNew() {
        return IsHdpNew;
    }

    public void setIsHdpNew(String isHdpNew) {
        IsHdpNew = isHdpNew;
    }

    public String getIsOUNew() {
        return IsOUNew;
    }

    public void setIsOUNew(String isOUNew) {
        IsOUNew = isOUNew;
    }

    public String getIsHomeGive() {
        return isHomeGive;
    }

    public void setIsHomeGive(String isHomeGive) {
        this.isHomeGive = isHomeGive;
    }

    public String getHdp() {
        return Hdp;
    }

    public void setHdp(String Hdp) {
        this.Hdp = Hdp;
    }
    public String getHomeHdpOdds() {
        return HomeHdpOdds;
    }

    public void setHomeHdpOdds(String homeHdpOdds) {
        HomeHdpOdds = homeHdpOdds;
    }

    public String getAwayHdpOdds() {
        return AwayHdpOdds;
    }

    public void setAwayHdpOdds(String awayHdpOdds) {
        AwayHdpOdds = awayHdpOdds;
    }

    public String getOU() {
        return OU;
    }
    public void setOU(String OU) {
        this.OU = OU;
    }

    public String getOverOdds() {
        return OverOdds;
    }

    public void setOverOdds(String overOdds) {
        OverOdds = overOdds;
    }

    public String getUnderOdds() {
        return UnderOdds;
    }

    public void setUnderOdds(String underOdds) {
        UnderOdds = underOdds;
    }

    public String getSocOddsId() {
        return SocOddsId;
    }

    public void setSocOddsId(String socOddsId) {
        SocOddsId = socOddsId;
    }

    public String getIsInetBet() {
        return isInetBet;
    }

    public void setIsInetBet(String isInetBet) {
        this.isInetBet = isInetBet;
    }
}
