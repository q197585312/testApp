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

    public BaseParamBean() {

    }

    public BaseParamBean(String ACT, String PT) {
        this.ACT = ACT;
        this.PT = PT;
    }

    public Map<String, String> getFmMp(String ACT, String PT) {
        Map<String, String> map = new HashMap<>();
        map.put("_fm", new BaseParamBean(ACT, PT).getJson());
        return map;
    }

    public String getJson() {
        return new Gson().toJson(this);
    }
}
