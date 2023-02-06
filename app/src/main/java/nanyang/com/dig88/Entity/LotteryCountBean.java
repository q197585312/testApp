package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2016/2/23.
 */
public class LotteryCountBean {

    String moneyTotal;
    String betTotal;

    public LotteryCountBean(String betTotal,String moneyTotal) {
        this.moneyTotal = moneyTotal;
        this.betTotal = betTotal;
    }

    public String getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(String moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public String getBetTotal() {
        return betTotal;
    }

    public void setBetTotal(String betTotal) {
        this.betTotal = betTotal;
    }
}
