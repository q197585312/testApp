package com.nanyang.app.main.home.sport.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2015/11/30.
 */
public class FGLGBean {
//    "oid":9112329,"HF":"1.42","HL":"1.42","AF":"3","AL":"3","NO GOAL":"15"
    int oid;
    String HF="";
    String HL="";
    String AF="";
    String AL="";
    @SerializedName("NO GOAL")
    String NO_GOAL="";

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getHF() {
        return HF;
    }

    public void setHF(String HF) {
        this.HF = HF;
    }

    public String getHL() {
        return HL;
    }

    public void setHL(String HL) {
        this.HL = HL;
    }

    public String getAF() {
        return AF;
    }

    public void setAF(String AF) {
        this.AF = AF;
    }

    public String getAL() {
        return AL;
    }

    public void setAL(String AL) {
        this.AL = AL;
    }

    public String getNO_GOAL() {
        return NO_GOAL;
    }

    public void setNO_GOAL(String NO_GOAL) {
        this.NO_GOAL = NO_GOAL;
    }
}
