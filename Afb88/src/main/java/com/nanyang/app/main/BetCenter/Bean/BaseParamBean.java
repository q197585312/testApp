package com.nanyang.app.main.BetCenter.Bean;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2019/4/4.
 */

public class BaseParamBean {
    String ACT;
    String PT;
    String WorkingDate;
    String pgLable;
    String vsn;
    String to;
    String from;
    String Id;
    String transType;
    String socTransId;

    public BaseParamBean(String ACT, String PT) {
        this.ACT = ACT;
        this.PT = PT;
    }

    public BaseParamBean(String ACT, String PT, String WorkingDate, String pgLable, String vsn) {
        this.ACT = ACT;
        this.PT = PT;
        this.WorkingDate = WorkingDate;
        this.pgLable = pgLable;
        this.vsn = vsn;
    }

    public BaseParamBean(String ACT, String PT, String Id, String pgLable, String vsn, int type) {
        this.ACT = ACT;
        this.PT = PT;
        this.Id = Id;
        this.pgLable = pgLable;
        this.vsn = vsn;
    }

    public BaseParamBean(String ACT, String PT, String to, String from, String pgLable, String vsn) {
        this.ACT = ACT;
        this.PT = PT;
        this.to = to;
        this.from = from;
        this.pgLable = pgLable;
        this.vsn = vsn;
    }

    public BaseParamBean(String ACT, String PT, String socTransId, String transType, String pgLable, String vsn, int type) {
        this.ACT = ACT;
        this.PT = PT;
        this.socTransId = socTransId;
        this.transType = transType;
        this.pgLable = pgLable;
        this.vsn = vsn;
    }

    public String getJson() {
        return new Gson().toJson(this);
    }
}
