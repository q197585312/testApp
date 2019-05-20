package com.nanyang.app.main.home.sport.model;

import android.support.annotation.NonNull;

import com.nanyang.app.AfbUtils;

public class FTOEBean {
    @com.google.gson.annotations.SerializedName("oid")
    private int oid;
    @com.google.gson.annotations.SerializedName("ODD")
    private String ODD = "";
    @com.google.gson.annotations.SerializedName("EVEN")
    private String EVEN = "";

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setODD(String ODD) {
        this.ODD = ODD;
    }

    public void setEVEN(String EVEN) {
        this.EVEN = EVEN;
    }

    public int getOid() {
        return oid;
    }

    public String getODD() {
        String odd = AfbUtils.delHTMLTag(ODD);
        return getOddEven(odd);
    }

    @NonNull
    private String getOddEven(String odds) {
        String odd =  AfbUtils.delHTMLTag(ODD);
        String even =  AfbUtils.delHTMLTag(EVEN);
        if (odd.isEmpty() || even.isEmpty() || Float.valueOf(odd) == 0f || Float.valueOf(even) == 0f) {
            odd = "";
            odds = odd;
        }
        return odds;
    }

    public String getEVEN() {
        String even =  AfbUtils.delHTMLTag(EVEN);
        return getOddEven(even);
    }
}