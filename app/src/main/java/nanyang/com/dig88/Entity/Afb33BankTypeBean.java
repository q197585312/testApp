package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/1/15.
 */

public class Afb33BankTypeBean {
    String bankShowName;
    String bankReturnName;

    public Afb33BankTypeBean(String bankShowName, String bankReturnName) {
        this.bankShowName = bankShowName;
        this.bankReturnName = bankReturnName;
    }

    public String getBankShowName() {
        return bankShowName;
    }

    public void setBankShowName(String bankShowName) {
        this.bankShowName = bankShowName;
    }

    public String getBankReturnName() {
        return bankReturnName;
    }

    public void setBankReturnName(String bankReturnName) {
        this.bankReturnName = bankReturnName;
    }
}
