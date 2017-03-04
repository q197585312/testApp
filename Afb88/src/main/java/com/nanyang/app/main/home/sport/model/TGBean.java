package com.nanyang.app.main.home.sport.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/30.
 */
public class TGBean implements Serializable {
//    {"oid":9112329,"0~1":"4.15","2~3":"1.86","4~6":"2.62","7 & OVER":"25.00"}
    int oid;
    @SerializedName("0~1")
    String T0_1;
    @SerializedName("2~3")
    String T2_3;
    @SerializedName("4~6")
    String T4_6;
    @SerializedName("7 & OVER")
    String T7_OVER;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getT0_1() {
        return T0_1;
    }

    public void setT0_1(String t0_1) {
        T0_1 = t0_1;
    }

    public String getT2_3() {
        return T2_3;
    }

    public void setT2_3(String t2_3) {
        T2_3 = t2_3;
    }

    public String getT4_6() {
        return T4_6;
    }

    public void setT4_6(String t4_6) {
        T4_6 = t4_6;
    }

    public String getT7_OVER() {
        return T7_OVER;
    }

    public void setT7_OVER(String t7_OVER) {
        T7_OVER = t7_OVER;
    }
}
