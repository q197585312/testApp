package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/19.
 */
public class BankInfoBean implements Serializable {
   String id_mod_bank;
    String name;
     String min_deposit;
    String max_deposit;
    String min_withdraw;
    String max_withdraw;
    String online;
    public BankInfoBean() {

    }
    public BankInfoBean(String id_mod_bank, String name) {
        this.id_mod_bank = id_mod_bank;
        this.name = name;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
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

    public String getId_mod_bank() {
        return id_mod_bank;
    }

    public void setId_mod_bank(String id_mod_bank) {
        this.id_mod_bank = id_mod_bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BankInfoBean{" +
                "id_mod_bank='" + id_mod_bank + '\'' +
                ", name='" + name + '\'' +
                ", min_deposit='" + min_deposit + '\'' +
                ", max_deposit='" + max_deposit + '\'' +
                ", min_withdraw='" + min_withdraw + '\'' +
                ", max_withdraw='" + max_withdraw + '\'' +
                '}';
    }
}
