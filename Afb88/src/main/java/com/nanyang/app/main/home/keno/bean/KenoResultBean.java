package com.nanyang.app.main.home.keno.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class KenoResultBean {
    private String drawNum;
    private String type;
    private List<String> numList;

    public String getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(String drawNum) {
        this.drawNum = drawNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getNumList() {
        return numList;
    }

    public void setNumList(List<String> numList) {
        this.numList = numList;
    }

    public KenoResultBean(String drawNum, String type, List<String> numList) {
        this.drawNum = drawNum;
        this.type = type;
        this.numList = numList;

    }
}
