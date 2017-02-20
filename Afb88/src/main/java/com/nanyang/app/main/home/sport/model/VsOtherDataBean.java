package com.nanyang.app.main.home.sport.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/18.
 */
public class VsOtherDataBean implements Serializable {
    private String EvenOdds;
    private String HasOE;
    private String IsOENew;

    public String getEvenOdds() {
        return EvenOdds;
    }

    public void setEvenOdds(String evenOdds) {
        EvenOdds = evenOdds;
    }

    public String getHasOE() {
        return HasOE;
    }

    public void setHasOE(String hasOE) {
        HasOE = hasOE;
    }

    public String getIsOENew() {
        return IsOENew;
    }

    public void setIsOENew(String isOENew) {
        IsOENew = isOENew;
    }

    public String getOddOdds() {
        return OddOdds;
    }

    public void setOddOdds(String oddOdds) {
        OddOdds = oddOdds;
    }

    public String getOEOdds() {
        return OEOdds;
    }

    public void setOEOdds(String OEOdds) {
        this.OEOdds = OEOdds;
    }

    private String OddOdds;
    private String OEOdds;


    private String HasX12;
    private String IsX12New;
    private String X121Odds;
    private String X122Odds;
    private String X12XOdds;

    private String HasX12FH;
    private String IsX12NewFH;
    private String X121OddsFH;
    private String X122OddsFH;
    private String X12XOddsFH;


    public String getHasX12() {
        return HasX12;
    }

    public void setHasX12(String hasX12) {
        HasX12 = hasX12;
    }

    public String getHasX12FH() {
        return HasX12FH;
    }

    public void setHasX12FH(String hasX12FH) {
        HasX12FH = hasX12FH;
    }


    public String getIsX12New() {
        return IsX12New;
    }

    public void setIsX12New(String isX12New) {
        IsX12New = isX12New;
    }

    public String getIsX12NewFH() {
        return IsX12NewFH;
    }

    public void setIsX12NewFH(String isX12NewFH) {
        IsX12NewFH = isX12NewFH;
    }


    public String getX121Odds() {
        return X121Odds;
    }

    public void setX121Odds(String x121Odds) {
        X121Odds = x121Odds;
    }

    public String getX121OddsFH() {
        return X121OddsFH;
    }

    public void setX121OddsFH(String x121OddsFH) {
        X121OddsFH = x121OddsFH;
    }

    public String getX122Odds() {
        return X122Odds;
    }

    public void setX122Odds(String x122Odds) {
        X122Odds = x122Odds;
    }

    public String getX122OddsFH() {
        return X122OddsFH;
    }

    public void setX122OddsFH(String x122OddsFH) {
        X122OddsFH = x122OddsFH;
    }

    public String getX12XOdds() {
        return X12XOdds;
    }

    public void setX12XOdds(String x12XOdds) {
        X12XOdds = x12XOdds;
    }

    public String getX12XOddsFH() {
        return X12XOddsFH;
    }

    public void setX12XOddsFH(String x12XOddsFH) {
        X12XOddsFH = x12XOddsFH;
    }

    public VsOtherDataBean(String hasX12, String hasX12FH, String isX12New, String isX12NewFH, String x121Odds, String x121OddsFH, String x122Odds, String x122OddsFH, String x12XOdds, String x12XOddsFH) {

        HasX12 = hasX12;
        HasX12FH = hasX12FH;
        IsX12New = isX12New;
        IsX12NewFH = isX12NewFH;

        X121Odds = x121Odds;
        X121OddsFH = x121OddsFH;
        X122Odds = x122Odds;
        X122OddsFH = x122OddsFH;
        X12XOdds = x12XOdds;
        X12XOddsFH = x12XOddsFH;
    }
    public VsOtherDataBean( String hasX12, String hasX12FH, String isX12New, String isX12NewFH,  String x121Odds, String x121OddsFH, String x122Odds, String x122OddsFH, String x12XOdds, String x12XOddsFH,String evenOdds, String hasOE,String isOENew,String oddOdds, String oEOdds ) {
        HasX12 = hasX12;
        HasX12FH = hasX12FH;
        IsX12New = isX12New;
        IsX12NewFH = isX12NewFH;
        X121Odds = x121Odds;
        X121OddsFH = x121OddsFH;
        X122Odds = x122Odds;
        X122OddsFH = x122OddsFH;
        X12XOdds = x12XOdds;
        X12XOddsFH = x12XOddsFH;
        EvenOdds = evenOdds;
        HasOE = hasOE;
        IsOENew = isOENew;
        OddOdds = oddOdds;
        OEOdds = oEOdds;
    }
    public VsOtherDataBean() {
    }
}
