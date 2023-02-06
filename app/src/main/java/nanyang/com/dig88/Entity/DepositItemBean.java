package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/7.
 */

public class DepositItemBean implements Serializable{
    int res;
    String typeName;
    String  free;
    String transactionTime ;
    String  min;
    String  max;
    String  currency;
    String  amount;
    String  secret;

    public DepositItemBean(int res, String typeName, String free, String transactionTime, String min, String max, String currency, String amount) {
        this.res = res;
        this.typeName = typeName;
        this.free = free;
        this.transactionTime = transactionTime;
        this.min = min;
        this.max = max;
        this.currency = currency;
        this.amount = amount;
    }

    public DepositItemBean(int res, String typeName, String free, String transactionTime, String min, String max, String currency, String amount, String secret) {
        this.res = res;
        this.typeName = typeName;
        this.free = free;
        this.transactionTime = transactionTime;
        this.min = min;
        this.max = max;
        this.currency = currency;
        this.amount = amount;
        this.secret = secret;

    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
