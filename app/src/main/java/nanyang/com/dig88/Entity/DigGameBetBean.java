package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/4. dig game bet（数字游戏大厅下注实体类）
 */
public class DigGameBetBean  implements Serializable {
    String balance;
    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "DigGameBetBean{" +
                "balance='" + balance + '\'' +
                '}';
    }
}
