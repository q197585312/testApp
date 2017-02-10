package com.nanyang.app;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/8 0008.
 */
public class MenuItemInfo implements Serializable{
    int res;
    String text;

    public MenuItemInfo(int res, String text) {
        this.res = res;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
