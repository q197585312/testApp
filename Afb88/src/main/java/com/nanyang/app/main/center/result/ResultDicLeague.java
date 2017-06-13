package com.nanyang.app.main.center.result;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class ResultDicLeague implements Serializable {
    List<LinkedHashMap<String,String>> dicLeague;

    public List<LinkedHashMap<String, String>> getDicLeague() {
        return dicLeague;
    }

    public void setDicLeague(List<LinkedHashMap<String, String>> dicLeague) {
        this.dicLeague = dicLeague;
    }
}
