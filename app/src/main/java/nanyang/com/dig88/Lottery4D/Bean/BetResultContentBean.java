package nanyang.com.dig88.Lottery4D.Bean;

/**
 * Created by Administrator on 2018/11/28.
 */

public class BetResultContentBean {
    private String number;
    private String PoolAndPeriod;
    private String betAmount;
    private int contentType;

    public BetResultContentBean(String number, String poolAndPeriod, String betAmount, int contentType) {
        this.number = number;
        PoolAndPeriod = poolAndPeriod;
        this.betAmount = betAmount;
        this.contentType = contentType;
    }

    public String getNumber() {
        return number;

    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPoolAndPeriod() {
        return PoolAndPeriod;
    }

    public void setPoolAndPeriod(String poolAndPeriod) {
        PoolAndPeriod = poolAndPeriod;
    }

    public String getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(String betAmount) {
        this.betAmount = betAmount;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }
}
