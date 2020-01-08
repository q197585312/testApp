package com.nanyang.app.main.home.sport.additional;

import com.nanyang.app.main.home.sport.model.BallInfo;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/1/5.
 */

public class AddedParamsInfo implements Serializable {
    private BallInfo bean;

    private String dbid;
    private String oddsType;

    public AddedParamsInfo(BallInfo bean, String dbid, String oddsType) {
        this.bean = bean;
        this.dbid = dbid;
        this.oddsType = oddsType;
    }

    public BallInfo getBean() {
        return bean;
    }

    public String getDbid() {
        return dbid;
    }

    public String getOddsType() {
        return oddsType;
    }
}
