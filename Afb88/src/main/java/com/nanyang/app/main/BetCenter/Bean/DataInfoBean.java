package com.nanyang.app.main.BetCenter.Bean;

/**
 * Created by Administrator on 2019/4/6.
 */

public class DataInfoBean {
    private String name;
    private String type;

    public DataInfoBean(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
