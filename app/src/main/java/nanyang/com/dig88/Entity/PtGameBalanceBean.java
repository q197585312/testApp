package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2018/3/20.
 */

public class PtGameBalanceBean {

    /**
     * Code : 0
     * Message : No Error
     * Balance : 0
     * Currency : THB
     */

    private String Code;
    private String Message;
    private double Balance;
    private String Currency;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(int Balance) {
        this.Balance = Balance;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }
}
