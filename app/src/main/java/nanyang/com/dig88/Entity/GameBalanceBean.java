package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/20. (余额实体)
 */
public class GameBalanceBean implements Serializable {
    String balance;
    String laster_bonus;
    String total_bonus;
    String bank;
    String bankacc;
    String bankaccno;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankacc() {
        return bankacc;
    }

    public void setBankacc(String bankacc) {
        this.bankacc = bankacc;
    }

    public String getBankaccno() {
        return bankaccno;
    }

    public void setBankaccno(String bankaccno) {
        this.bankaccno = bankaccno;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLaster_bonus() {
        return laster_bonus;
    }

    public void setLaster_bonus(String laster_bonus) {
        this.laster_bonus = laster_bonus;
    }

    public String getTotal_bonus() {
        return total_bonus;
    }

    public void setTotal_bonus(String total_bonus) {
        this.total_bonus = total_bonus;
    }

    @Override
    public String toString() {
        return "GameBalanceBean{" +
                "balance='" + balance + '\'' +
                ", laster_bonus='" + laster_bonus + '\'' +
                ", total_bonus='" + total_bonus + '\'' +
                '}';
    }
}
