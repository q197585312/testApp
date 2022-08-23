package com.nanyang.app.main.Setting;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/19.
 */
public class ChipBean implements Serializable{
    int drawableRes;
    String name;
    int value;

    public int getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ChipBean(int drawableRes, String name, int value) {
        this.drawableRes = drawableRes;
        this.name = name;
        this.value = value;
    }

}
