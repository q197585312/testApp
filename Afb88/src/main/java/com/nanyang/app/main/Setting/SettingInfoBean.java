package com.nanyang.app.main.Setting;

import java.util.List;

import gaming178.com.casinogame.Bean.ChipBean;

/**
 * Created by Administrator on 2019/5/7.
 */

public class SettingInfoBean {
    private String type;
    private String name;
    private String choiceType;
    private String customizeAmount;

    public String getCustomizeAmount() {
        return customizeAmount;
    }

    public void setCustomizeAmount(String customizeAmount) {
        this.customizeAmount = customizeAmount;
    }

    public SettingInfoBean(String type, String name, String choiceType) {
        this.type = type;
        this.name = name;
        this.choiceType = choiceType;
    }

    public SettingInfoBean(String type, String name, String choiceType, List<ChipBean> chipBeans) {
        this.type = type;
        this.name = name;
        this.choiceType = choiceType;
        this.chipBeans = chipBeans;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(String choiceType) {
        this.choiceType = choiceType;
    }

    public List<ChipBean> chipBeans;
}
