package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2020/8/10.
 */

public class KYPokerBalanceBean {


    /**
     * code : 0
     * errMsg : No Error
     * balance : 1.8
     * m_balance : 9
     */

    private String code;
    private String errMsg;
    private double balance;
    private double m_balance;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getM_balance() {
        return m_balance;
    }

    public void setM_balance(double m_balance) {
        this.m_balance = m_balance;
    }
}
