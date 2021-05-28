package com.nanyang.app.data;

/**
 * Created by Administrator on 2019/3/18.
 */

public class HomePopItemBeen {
    private String name;
    private String data;

    public HomePopItemBeen(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
