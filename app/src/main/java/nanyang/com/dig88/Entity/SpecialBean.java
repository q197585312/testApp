package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/5.
 */
public class SpecialBean implements Serializable {
   String date;
    String pool;
    String gameid;
    String names;
    String type;
    String rate;
    String discount;
    String kei;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getKei() {
        return kei;
    }

    public void setKei(String kei) {
        this.kei = kei;
    }

    @Override
    public String toString() {
        return "SpecialBean{" +
                "date='" + date + '\'' +
                ", pool='" + pool + '\'' +
                ", gameid='" + gameid + '\'' +
                ", names='" + names + '\'' +
                ", type='" + type + '\'' +
                ", rate='" + rate + '\'' +
                ", discount='" + discount + '\'' +
                ", kei='" + kei + '\'' +
                '}';
    }
}
