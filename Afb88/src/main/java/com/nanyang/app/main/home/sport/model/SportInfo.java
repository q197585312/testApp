package com.nanyang.app.main.home.sport.model;

import android.text.TextUtils;

import com.nanyang.app.Utils.StringUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/11.
 */
public class SportInfo implements Serializable {


    private boolean notify;


    public void setValue(int i, String s) {
        switch (i) {
            case 0:
                setSocOddsId(s);
                break;
            case 1:
                setHome(s);
                break;
            case 2:
                setIsInetBet(s);
                break;
            case 3:
                setIsX12New(s);
                break;
            case 4:
                setHasX12(s);
                break;
            case 5:
                setX12_1Odds(s);
                setIsX12New("1");
                break;
            case 6:
                setPreSocOddsId(s);
                break;
        }
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
        if (notify) {
            setIsX12New("1");
        } else {
            setIsX12New("0");
        }
    }

    public enum Type {
        TITLE, ITME
    }

    SportInfo.Type type;
    public String ModuleId;
    public String ModuleTitle;
    public String SocOddsId, Home, IsInetBet, isX12New, HasX12, X12_1Odds, PreSocOddsId;
    public int contentColor;

    public int getContentColor() {
        return contentColor;
    }

    public void setContentColor(int contentColor) {
        this.contentColor = contentColor;
    }


    public String getAway() {
        return Away;
    }

    public void setAway(String away) {
        Away = away;
    }

    String Away;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getModuleId() {
        return ModuleId;
    }

    public void setModuleId(String moduleId) {
        this.ModuleId = moduleId;
    }

    public String getModuleTitle() {
        return ModuleTitle;
    }

    @Override
    public String toString() {
        return "SportInfo{" +
                "notify=" + notify +
                ", type=" + type +
                ", moduleId='" + ModuleId + '\'' +
                ", ModuleTitle=" + ModuleTitle +
                ", SocOddsId='" + SocOddsId + '\'' +
                ", Home='" + Home + '\'' +
                ", IsInetBet='" + IsInetBet + '\'' +
                ", isX12New='" + isX12New + '\'' +
                ", HasX12='" + HasX12 + '\'' +
                ", X12_1Odds='" + X12_1Odds + '\'' +
                ", PreSocOddsId='" + PreSocOddsId + '\'' +
                ", Away='" + Away + '\'' +
                '}';
    }

    public void setModuleTitle(String moduleTitle) {
        this.ModuleTitle = moduleTitle;
    }

    public String getSocOddsId() {
        return SocOddsId;
    }

    public void setSocOddsId(String socOddsId) {
        SocOddsId = socOddsId;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }

    public String getIsInetBet() {
        return IsInetBet;
    }

    public void setIsInetBet(String isInetBet) {
        IsInetBet = isInetBet;
    }

    public String getIsX12New() {
        return isX12New;
    }

    public void setIsX12New(String isX12New) {
        this.isX12New = isX12New;
    }

    public String getHasX12() {
        return "1";
    }

    public void setHasX12(String hasX12) {
        HasX12 = hasX12;
    }

    public String getX12_1Odds() {
        return X12_1Odds;
    }

    public void setX12_1Odds(String x12_1Odds) {
        X12_1Odds = x12_1Odds;
    }

    public String getPreSocOddsId() {
        return PreSocOddsId;
    }

    public void setPreSocOddsId(String preSocOddsId) {
        PreSocOddsId = preSocOddsId;
    }


    public boolean isBigger(String fOld, String fNew) {
        if (StringUtils.isNull(fOld) || StringUtils.isNull(fNew) || Float.valueOf(fNew) > Float.valueOf(fOld))
            return true;
        return false;
    }

    public boolean isScoreBigger(String scoreOld, String scoreNew) {
        if (TextUtils.isEmpty(scoreOld)) {
            scoreOld = "0";
        }
        if (TextUtils.isEmpty(scoreNew)) {
            scoreNew = "0";
        }
        if (Integer.parseInt(scoreNew) > Integer.parseInt(scoreOld)) {
            return true;
        }
        return false;
    }
}
