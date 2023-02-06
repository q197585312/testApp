package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 15-12-13.lottery 游戏
 */
public class LotteryChipBean implements Serializable {
    private static final long serialVersionUID = 1L;
    String balance;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "LotteryChipBean{" +
                "balance='" + balance + '\'' +
                '}';
    }
}
