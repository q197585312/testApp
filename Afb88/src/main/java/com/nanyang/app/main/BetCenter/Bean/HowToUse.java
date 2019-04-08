package com.nanyang.app.main.BetCenter.Bean;

import java.util.List;

/**
 * 使用方法
 * Created by 47184 on 2019/4/4.
 */
public class HowToUse {
    private int img; //图片ID
    private String title; //标题
    private List<String> content; //文字内容

    public HowToUse() {
    }

    public HowToUse(int img, String title, List<String> content) {
        this.img = img;
        this.title = title;
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
