package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2018/3/26.
 */

public class ChinaLotteryBalanceBean {

    /**
     * Code : 1
     * errorMsg : No Error
     * balance : 50
     * status : 1
     */

    private String Code;
    private String errorMsg;
    private double balance;
    private int status;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
