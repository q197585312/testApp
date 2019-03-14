package com.nanyang.app;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/3/14.
 */

public class SportIdBean implements Serializable {
    String id;
    int textRes;
    int sportCount;

    public SportIdBean(String id, int textRes) {
        this.id = id;
        this.textRes = textRes;
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
