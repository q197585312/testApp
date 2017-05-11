package com.nanyang.app.main.home.sport.model;

import android.support.annotation.NonNull;
import android.text.Html;

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
        String odd = Html.fromHtml(ODD).toString().trim();
        return getOddEven(odd);
    }

    @NonNull
    private String getOddEven(String odds) {
        String odd = Html.fromHtml(ODD).toString().trim();
        String even = Html.fromHtml(EVEN).toString().trim();
        if (odd.isEmpty() || even.isEmpty() || Float.valueOf(odd) == 0f || Float.valueOf(even) == 0f) {
            odd = "";
            odds = odd;
        }
        return odds;
    }

    public String getEVEN() {
        String even = Html.fromHtml(EVEN).toString().trim();
        return getOddEven(even);
    }
}