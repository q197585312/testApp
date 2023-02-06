package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/10/25.
 */

public class FFYLPokerBalanceBean {

    /**
     * code : 0
     * errMessage : No Error
     * balance : 0
     * gamecoin : 0
     */

    private int code;
    private String errMessage;
    private double balance;
    private double gamecoin;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getGamecoin() {
        return gamecoin;
    }

    public void setGamecoin(double gamecoin) {
        this.gamecoin = gamecoin;
    }
}
