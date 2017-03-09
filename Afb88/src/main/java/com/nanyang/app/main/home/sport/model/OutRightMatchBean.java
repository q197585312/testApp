package com.nanyang.app.main.home.sport.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/9.
 */

public class OutRightMatchBean implements Serializable{
//[[[486137,'ATP - 巴黎银行公开赛 获胜者',0,0],[[12166180 ,'斯特方.卡佐洛夫' ,1,0,1,501,0],[12166146 ,'J. 汤姆森 ' ,1,0,1,501,12166180],
    String moduleId;
    String moduleTitle;
    String SocOddsId,   Home,IsInetBet, isX12New, HasX12, X12_1Odds , PreSocOddsId;
    private MatchBean.Type type;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
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
        return HasX12;
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

    public OutRightMatchBean(String moduleId, String moduleTitle, String socOddsId, String home, String isInetBet, String isX12New, String hasX12, String x12_1Odds, String preSocOddsId) {
        this.moduleId = moduleId;
        this.moduleTitle = moduleTitle;
        SocOddsId = socOddsId;
        Home = home;
        IsInetBet = isInetBet;
        this.isX12New = isX12New;
        HasX12 = hasX12;
        X12_1Odds = x12_1Odds;
        PreSocOddsId = preSocOddsId;
    }

    public OutRightMatchBean() {
    }

    public void setType(MatchBean.Type type) {
        this.type = type;
    }
}
