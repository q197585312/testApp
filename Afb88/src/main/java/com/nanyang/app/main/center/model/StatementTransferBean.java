package com.nanyang.app.main.center.model;

/**
 * Created by Administrator on 2017/3/21.
 */

public class StatementTransferBean {
    String data;
    String state;
    String money;

    public StatementTransferBean(String data, String state, String money) {
        this.data = data;
        this.state = state;
        this.money = money;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
