package com.nanyang.app.main.home.sport.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/26.
 */

public class OddsClickBean<B extends BallInfo> implements Serializable {


    private B item;

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    private String sc = "";
    private String type, g, oid, oid_fh;

    public B getItem() {
        return item;
    }

    public OddsClickBean(String type, String g, String oid, String oid_fh, String sc, B item) {
        this.type = type;
        this.g = g;
        this.oid = oid;
        this.oid_fh = oid_fh;
        this.sc = sc;
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOid_fh() {
        return oid_fh;
    }

    public void setOid_fh(String oid_fh) {
        this.oid_fh = oid_fh;
    }

    public String getBETID() {
        return "s|" + type + "|" + g + "|" + oid + "|" + oid_fh + "|" + sc;
    }

    public String getBETID_PAR() {
        return "s|" + (type.endsWith("_par") ? type : (type + "_par")) + "|" + g + "|" + oid + "|" + oid_fh + "|" + sc;
    }

    @Override
    public String toString() {
        return "OddsClickBean{" +
                "item=" + item +
                ", oid='" + oid + '\'' +
                ", oid_fh='" + oid_fh + '\'' +
                '}';
    }
    //    OddsClickBean(type,"9",item.getSocOddsId(),isHf?item.getSocOddsId_FH():"");
}
