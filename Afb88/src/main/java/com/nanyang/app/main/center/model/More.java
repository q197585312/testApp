package com.nanyang.app.main.center.model;

/**
 * Created by 47184 on 2019/3/15.
 */

public class More {
    private int image_left;
    private String text;
    private int image_right;

    public More() {
    }

    public More(int image_left, String text, int image_right) {
        this.image_left = image_left;
        this.text = text;
        this.image_right = image_right;
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
}
