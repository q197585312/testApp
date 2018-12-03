package com.nanyang.app.main.home.sport.model;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */

public class AfbClickResponseBean implements Serializable {


    private String minLimit="";
    JSONArray dataListArray;
    List<AfbClickBetBean> list;
    String parUrl="";
    private String exRate="";

    public String getMinLimit() {
        return minLimit;
    }

    public String getMaxLimit() {
        return maxLimit;
    }

    String maxLimit="0";
    private String payoutOdds="";

    public List<AfbClickBetBean> getList() {
        return list;
    }

    public String getParUrl() {
        return parUrl;
    }

    public AfbClickResponseBean(List<AfbClickBetBean> list, JSONArray dataListArray1) {
        this.list = list;
        this.dataListArray=dataListArray1;

        if (dataListArray1.length() > 4) {
            parUrl = dataListArray1.optString(4);
            maxLimit = dataListArray1.optString(0);
            minLimit = dataListArray1.optString(1);
            payoutOdds = dataListArray1.optString(2);
            exRate = dataListArray1.optString(5);
        }
    }

    public String getPayoutOdds() {
        return payoutOdds;
    }

    public String getExRate() {
        return exRate;
    }
}
