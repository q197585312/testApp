package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/11.
 */
public class SportInfo implements Serializable {
    Type type;
    String moduleId;
    CharSequence moduleTitle;
    String SocOddsId,   Home,IsInetBet, isX12New, HasX12, X12_1Odds , PreSocOddsId;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public CharSequence getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(CharSequence moduleTitle) {
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

    public enum Type{
        TITLE,ITME
    }

    public static class ParseInfo{
        int SocOddsId=0,   Home=1,IsInetBet=2, isX12New=3, HasX12=4, X12_1Odds=5 , PreSocOddsId=6;
    }
}
