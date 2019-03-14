package com.nanyang.app;

import java.io.Serializable;

public class MenuItemInfo<P> implements Serializable {
    int res;
    String text;

    public P getParent() {
        return parent;
    }

    public void setParent(P parent) {
        this.parent = parent;
    }

    P parent;

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
    public MenuItemInfo(int res,String text, String type, P parent) {
        this(res, text);
        this.type = type;
        this.parent=parent;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    /**0是正常 1是mix*/
    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }


    public MenuItemInfo() {
        super();
    }
}
