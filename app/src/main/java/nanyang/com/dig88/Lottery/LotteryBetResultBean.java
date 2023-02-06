package nanyang.com.dig88.Lottery;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/25.
 */
public class LotteryBetResultBean implements Serializable {
    //{"code":"1","msg":"1","data":{"balance":"651.8746666667372","success":"","failure":"11X10&13X10&16X10&18X10"}}
    String data;
    String balance;
    String success;
    String failure;

    public LotteryBetResultBean(String balance, String data, String failure) {
        this.balance = balance;
        this.data = data;
        this.failure = failure;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }
}
