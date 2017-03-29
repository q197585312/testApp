package com.nanyang.app.load;

/**
 * Created by Administrator on 2017/3/27.
 */

public class PersonalInfo {
    String userName;
    String password;
    String balance="";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
