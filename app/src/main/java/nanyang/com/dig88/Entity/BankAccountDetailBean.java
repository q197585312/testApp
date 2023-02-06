package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/19.
 */
public class BankAccountDetailBean implements Serializable {
    String id_mod_bank;
    String id_mod_bank_account;
    String account;
    String no;
    String type;
    String currency;
    String bank_name;
    String max_deposit;
    String min_deposit;
    String min_withdraw;
    String max_withdraw;
    String bank_level;

    public String getBank_level() {
        return bank_level;
    }

    public void setBank_level(String bank_level) {
        this.bank_level = bank_level;
    }

    public String getMin_deposit() {
        return min_deposit;
    }

    public void setMin_deposit(String min_deposit) {
        this.min_deposit = min_deposit;
    }

    public String getMax_deposit() {
        return max_deposit;
    }

    public void setMax_deposit(String max_deposit) {
        this.max_deposit = max_deposit;
    }

    public String getMin_withdraw() {
        return min_withdraw;
    }

    public void setMin_withdraw(String min_withdraw) {
        this.min_withdraw = min_withdraw;
    }

    public String getMax_withdraw() {
        return max_withdraw;
    }

    public void setMax_withdraw(String max_withdraw) {
        this.max_withdraw = max_withdraw;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (obj instanceof BankAccountDetailBean) {
            BankAccountDetailBean user = (BankAccountDetailBean) obj;
            if (user.currency == this.currency)
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return currency.hashCode();
    }

    public String getId_mod_bank() {
        return id_mod_bank;
    }

    public void setId_mod_bank(String id_mod_bank) {
        this.id_mod_bank = id_mod_bank;
    }

    public String getId_mod_bank_account() {
        return id_mod_bank_account;
    }

    public void setId_mod_bank_account(String id_mod_bank_account) {
        this.id_mod_bank_account = id_mod_bank_account;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    @Override
    public String toString() {
        return "BankAccountDetailBean{" +
                "id_mod_bank='" + id_mod_bank + '\'' +
                ", id_mod_bank_account='" + id_mod_bank_account + '\'' +
                ", account='" + account + '\'' +
                ", no='" + no + '\'' +
                ", type='" + type + '\'' +
                ", currency='" + currency + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", max_deposit='" + max_deposit + '\'' +
                ", min_deposit='" + min_deposit + '\'' +
                ", min_withdraw='" + min_withdraw + '\'' +
                ", max_withdraw='" + max_withdraw + '\'' +
                '}';
    }
}
