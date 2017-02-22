package com.nanyang.app.main.home.poker.model;

/**
 * Created by Administrator on 2017/2/15.
 */

public class PorkerCasinoBean {
    String casinoName;
    String casinoIntroduce;
    int img;

    public PorkerCasinoBean(int img,String casinoName, String casinoIntroduce ) {
        this.casinoName = casinoName;
        this.casinoIntroduce = casinoIntroduce;
        this.img = img;
    }

    public String getCasinoName() {
        return casinoName;
    }

    public void setCasinoName(String casinoName) {
        this.casinoName = casinoName;
    }

    public String getCasinoIntroduce() {
        return casinoIntroduce;
    }

    public void setCasinoIntroduce(String casinoIntroduce) {
        this.casinoIntroduce = casinoIntroduce;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
