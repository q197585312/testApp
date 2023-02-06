package nanyang.com.dig88.Lottery;

/**
 * Created by Administrator on 2016/2/24.
 */
public class LotteryLimitBean {
    String typeKey;
    String minLimit;
    String maxLimit;
    String totalLimit;
    String period;
    String gameName;

    public LotteryLimitBean(String gameName, String period, String maxLimit, String minLimit, String totalLimit, String typeKey) {
        this.gameName=gameName;
        this.period=period;
        this.maxLimit = maxLimit;
        this.minLimit = minLimit;
        this.totalLimit = totalLimit;
        this.typeKey = typeKey;
    }

    public LotteryLimitBean() {
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

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
