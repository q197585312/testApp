package com.nanyang.app;

import java.io.Serializable;

public class MenuItemInfo implements Serializable {
    int res;
    String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    public MenuItemInfo(int res, String text) {
        this.res = res;
        this.text = text;
    }

    public MenuItemInfo(int res, String text, String type) {
        this(res, text);
        this.type = type;
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
