package com.nanyang.app;

import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/3/14.
 */

public class SportIdBean implements Serializable {
    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    private String dbid;

    public BaseSportFragment getBaseFragment() {
        return baseFragment;
    }

    public void setBaseFragment(BaseSportFragment baseFragment) {
        this.baseFragment = baseFragment;
    }

    private BaseSportFragment baseFragment;

    public Class<SportActivity> getCls() {
        return cls;
    }

    public void setCls(Class<SportActivity> cls) {
        this.cls = cls;
    }

    private Class<SportActivity> cls;
    String id;
    int textRes;
    int sportCount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    public SportIdBean(String g, String dbid, int textRes, String type, Class<SportActivity> cls, BaseSportFragment baseFragment) {
        this.dbid = dbid;
        this.id = g;
        this.textRes = textRes;
        this.type = type;
        this.cls = cls;
        this.baseFragment = baseFragment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTextRes() {
        return textRes;
    }

    public void setTextRes(int textRes) {
        this.textRes = textRes;
    }

    public int getSportCount() {
        return sportCount;
    }

    public void setSportCount(int sportCount) {
        this.sportCount = sportCount;
    }
}
