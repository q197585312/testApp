package nanyang.com.dig88.Keno;

/**
 * Created by Administrator on 2016/2/24.
 */
public class KenoBetBean {
    String factor;
    String discount;
    String typeKey;
    String minLimit;
    String maxLimit;
    String totalLimit;
    String period;
    String areaName;
    private String gameTitle;
    private String type2;
    private String typeName;
    public KenoBetBean(String GameTitle,String type2,String areaName, String period, String typeKey, String typeName, String maxLimit, String minLimit, String totalLimit, String discount, String factor) {
        this.gameTitle=GameTitle;
        this.type2=type2;
        this.areaName = areaName;
        this.period=period;
        this.typeName=typeName;
        this.maxLimit = maxLimit;
        this.minLimit = minLimit;
        this.totalLimit = totalLimit;
        this.typeKey = typeKey;
        this.discount=discount;
        this.factor=factor;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(String maxLimit) {
        this.maxLimit = maxLimit;
    }

    public String getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(String minLimit) {
        this.minLimit = minLimit;
    }

    public String getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(String totalLimit) {
        this.totalLimit = totalLimit;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

}
