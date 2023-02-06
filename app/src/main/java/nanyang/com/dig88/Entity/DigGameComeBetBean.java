package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/4. dig game bet（数字游戏进入下注实体类）
 */
public class DigGameComeBetBean implements Serializable {
    String balance;
    String Failure;

    public String getFailure() {
        return Failure;
    }

    public void setFailure(String failure) {
        Failure = failure;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "DigGameComeBetBean{" +
                "balance='" + balance + '\'' +
                ", Failure='" + Failure + '\'' +
                '}';
    }
}
