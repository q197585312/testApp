package com.nanyang.app.main.home.sport.dialog;

/**
 * Created by Administrator on 2019/4/10.
 */

public class PopChipBean {
    private int imgRes;
    private int betChip;

    public PopChipBean(int imgRes, int betChip) {
        this.imgRes = imgRes;
        this.betChip = betChip;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public int getBetChip() {
        return betChip;
    }

    public void setBetChip(int betChip) {
        this.betChip = betChip;
    }
}
