package com.nanyang.app.main.home.Games.model;

/**
 * Created by Administrator on 2017/2/27.
 */

public class ThaiThousandIntroduceBean {
    String name;
    String introduce;
    String way;

    public ThaiThousandIntroduceBean(String name, String introduce, String way) {
        this.name = name;
        this.introduce = introduce;
        this.way = way;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
}
