package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/22.
 */
public class VipInfoBean implements Serializable {
    String email;
    String tel;
    String bank_acc_name;
    String bank_acc_name2;
    String address;
    String full_name;
    String bank_acc_no;
    String bank_acc_no2;
    String bank_name;
    String total_refer;
    String laster_refer;
    String balance;
    String username;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBank_acc_name2() {
        return bank_acc_name2;
    }

    public void setBank_acc_name2(String bank_acc_name2) {
        this.bank_acc_name2 = bank_acc_name2;
    }

    public String getBank_acc_no2() {
        return bank_acc_no2;
    }

    public void setBank_acc_no2(String bank_acc_no2) {
        this.bank_acc_no2 = bank_acc_no2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBank_acc_name() {
        return bank_acc_name;
    }

    public void setBank_acc_name(String bank_acc_name) {
        this.bank_acc_name = bank_acc_name;
    }

    public String getBank_acc_no() {
        return bank_acc_no;
    }

    public void setBank_acc_no(String bank_acc_no) {
        this.bank_acc_no = bank_acc_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getTotal_refer() {
        return total_refer;
    }

    public void setTotal_refer(String total_refer) {
        this.total_refer = total_refer;
    }

    public String getLaster_refer() {
        return laster_refer;
    }

    public void setLaster_refer(String laster_refer) {
        this.laster_refer = laster_refer;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "VipInfoBean{" +
                "email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", bank_acc_name='" + bank_acc_name + '\'' +
                ", bank_acc_no='" + bank_acc_no + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", total_refer='" + total_refer + '\'' +
                ", laster_refer='" + laster_refer + '\'' +
                ", balance='" + balance + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
