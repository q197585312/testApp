package com.nanyang.app.main.Setting;

/**
 * Created by Administrator on 2019/5/7.
 */

public class SettingInfoBean {
    private String type;
    private String name;
    private String choiceType;
    private int chip1;
    private int chip2;
    private int chip3;
    private int chip4;
    private int chip5;
    private int chip6;
    private int chipSize1;
    private int chipSize2;
    private int chipSize3;
    private int chipSize4;
    private int chipSize5;
    private int chipSize6;

    public int getChipSize1() {
        return chipSize1;
    }

    public void setChipSize1(int chipSize1) {
        this.chipSize1 = chipSize1;
    }

    public int getChipSize2() {
        return chipSize2;
    }

    public void setChipSize2(int chipSize2) {
        this.chipSize2 = chipSize2;
    }

    public int getChipSize3() {
        return chipSize3;
    }

    public void setChipSize3(int chipSize3) {
        this.chipSize3 = chipSize3;
    }

    public int getChipSize4() {
        return chipSize4;
    }

    public void setChipSize4(int chipSize4) {
        this.chipSize4 = chipSize4;
    }

    public int getChipSize5() {
        return chipSize5;
    }

    public void setChipSize5(int chipSize5) {
        this.chipSize5 = chipSize5;
    }

    public int getChipSize6() {
        return chipSize6;
    }

    public void setChipSize6(int chipSize6) {
        this.chipSize6 = chipSize6;
    }

    private String customizeAmount;

    public int getChip6() {
        return chip6;
    }

    public void setChip6(int chip6) {
        this.chip6 = chip6;
    }

    public String getCustomizeAmount() {
        return customizeAmount;
    }

    public void setCustomizeAmount(String customizeAmount) {
        this.customizeAmount = customizeAmount;
    }

    public SettingInfoBean(String type, String name, String choiceType, int chip1, int chip2, int chip3, int chip4, int chip5, int chip6) {
        this.type = type;
        this.name = name;
        this.choiceType = choiceType;
        this.chip1 = chip1;
        this.chip2 = chip2;
        this.chip3 = chip3;
        this.chip4 = chip4;
        this.chip5 = chip5;
        this.chip6 = chip6;
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

    public int getchip1() {
        return chip1;
    }

    public void setchip1(int chip1) {
        this.chip1 = chip1;
    }

    public int getchip2() {
        return chip2;
    }

    public void setchip2(int chip2) {
        this.chip2 = chip2;
    }

    public int getchip3() {
        return chip3;
    }

    public void setchip3(int chip3) {
        this.chip3 = chip3;
    }

    public int getchip4() {
        return chip4;
    }

    public void setchip4(int chip4) {
        this.chip4 = chip4;
    }

    public int getchip5() {
        return chip5;
    }

    public void setchip5(int chip5) {
        this.chip5 = chip5;
    }
}
