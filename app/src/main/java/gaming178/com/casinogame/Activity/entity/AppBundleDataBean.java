package gaming178.com.casinogame.Activity.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/24.
 */
public class AppBundleDataBean implements Serializable{
     String web_id;
     String currency;
     String username;
     String balance;
     String language;

    public AppBundleDataBean(String web_id, String currency, String username, String balance, String language) {
        this.web_id = web_id;
        this.currency = currency;
        this.username = username;
        this.balance = balance;
        this.language = language;
    }

    public String getWeb_id() {
        return web_id;
    }

    public void setWeb_id(String web_id) {
        this.web_id = web_id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "AppBundleDataBean{" +
                "web_id='" + web_id + '\'' +
                ", currency='" + currency + '\'' +
                ", username='" + username + '\'' +
                ", balance='" + balance + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
