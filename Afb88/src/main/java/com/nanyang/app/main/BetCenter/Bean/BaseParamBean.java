package com.nanyang.app.main.BetCenter.Bean;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/4.
 */

public class BaseParamBean {
    String ACT;
    String PT;
    String WorkingDate;
    String pgLable;
    String vsn;

    public BaseParamBean() {

    }

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

    public Map<String, String> getFmBaseMp() {
        Map<String, String> map = new HashMap<>();
        map.put("_fm", new BaseParamBean("GetTT", "wfStatement2H50").getJson());
        return map;
    }

    public Map<String, String> getFmSatementOpen1Mp(String date) {
        Map<String, String> map = new HashMap<>();
        map.put("_fm", new BaseParamBean("GetTableD", "wfStatement2H50", date, "", "").getJson());
        return map;
    }


    public String getJson() {
        return new Gson().toJson(this);
    }
}
