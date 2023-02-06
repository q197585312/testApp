package nanyang.com.dig88.Lottery4D.Bean;

/**
 * Created by Administrator on 2018/11/27.
 */

public class BetDetailBean {
    private String betPool;
    private String betNumber;
    private String showBigAmount;
    private String betBigAmount;
    private String showSmallAmount;
    private String betSmallAmount;
    private String betAAmount;
    private String betABCAmount;
    private String showAAmount;
    private String showABCAmount;
    private String period;

    public BetDetailBean(String betPool, String betNumber, String showBigAmount, String betBigAmount, String showSmallAmount, String betSmallAmount, String period) {
        this.betPool = betPool;
        this.betNumber = betNumber;
        this.showBigAmount = showBigAmount;
        this.betBigAmount = betBigAmount;
        this.showSmallAmount = showSmallAmount;
        this.betSmallAmount = betSmallAmount;
        this.period = period;
    }

    public BetDetailBean() {
    }

    public String getBetAAmount() {
        return betAAmount;
    }

    public void setBetAAmount(String betAAmount) {
        this.betAAmount = betAAmount;
    }

    public String getBetABCAmount() {
        return betABCAmount;
    }

    public void setBetABCAmount(String betABCAmount) {
        this.betABCAmount = betABCAmount;
    }

    public String getShowAAmount() {
        return showAAmount;
    }

    public void setShowAAmount(String showAAmount) {
        this.showAAmount = showAAmount;
    }

    public String getShowABCAmount() {
        return showABCAmount;
    }

    public void setShowABCAmount(String showABCAmount) {
        this.showABCAmount = showABCAmount;
    }

    public String getShowBigAmount() {
        return showBigAmount;
    }

    public void setShowBigAmount(String showBigAmount) {
        this.showBigAmount = showBigAmount;
    }

    public String getBetBigAmount() {
        return betBigAmount;
    }

    public void setBetBigAmount(String betBigAmount) {
        this.betBigAmount = betBigAmount;
    }

    public String getShowSmallAmount() {
        return showSmallAmount;
    }

    public void setShowSmallAmount(String showSmallAmount) {
        this.showSmallAmount = showSmallAmount;
    }

    public String getBetSmallAmount() {
        return betSmallAmount;
    }

    public void setBetSmallAmount(String betSmallAmount) {
        this.betSmallAmount = betSmallAmount;
    }

    public String getBetPool() {
        return betPool;
    }

    public void setBetPool(String betPool) {
        this.betPool = betPool;
    }

    public String getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(String betNumber) {
        this.betNumber = betNumber;
    }


    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
