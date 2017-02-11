package com.nanyang.app.main.more;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/9.
 */

public class CiscountItemInfo implements Serializable {
    int res;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public CiscountItemInfo(int res) {
        this.res = res;
    }
}
