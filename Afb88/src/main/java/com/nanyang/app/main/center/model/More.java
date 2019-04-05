package com.nanyang.app.main.center.model;

import com.nanyang.app.main.BaseSwitchFragment;
import com.unkonw.testapp.libs.base.BaseFragment;

/**
 * Created by 47184 on 2019/3/15.
 */

public class More {
    private int image_left;
    private String text;
    private int image_right;
    private BaseSwitchFragment fragment;
    private String switchType;

    public String getSwitchType() {
        return switchType;
    }

    public void setSwitchType(String switchType) {
        this.switchType = switchType;
    }

    public More() {
    }

    public More(int image_left, String text, int image_right) {
        this.image_left = image_left;
        this.text = text;
        this.image_right = image_right;
    }
    public More(int image_left, String text, int image_right,BaseSwitchFragment fragment) {
        this.image_left = image_left;
        this.text = text;
        this.image_right = image_right;
        this.fragment = fragment;
    }
    public More(int image_left, String text, int image_right,BaseSwitchFragment fragment,String switchType) {
        this.image_left = image_left;
        this.text = text;
        this.image_right = image_right;
        this.fragment = fragment;
        this.switchType = switchType;
    }

    public int getImage_left() {
        return image_left;
    }

    public void setImage_left(int image_left) {
        this.image_left = image_left;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage_right() {
        return image_right;
    }

    public void setImage_right(int image_right) {
        this.image_right = image_right;
    }

    public BaseSwitchFragment getFragment() {
        return fragment;
    }

    public void setFragment(BaseSwitchFragment fragment) {
        this.fragment = fragment;
    }
}
