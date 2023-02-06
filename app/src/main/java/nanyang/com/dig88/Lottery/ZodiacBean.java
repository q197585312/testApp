package nanyang.com.dig88.Lottery;

import java.io.Serializable;

/**
 * Created by xToney on 2016/2/26.
 */
public class ZodiacBean implements Serializable{
    String name;
    String discount;
    String kei;
    String odds;
    String money;
    String count;

    public ZodiacBean(String name, String discount, String kei, String odds, String money, String count) {
        this.name = name;
        this.discount = discount;
        this.kei = kei;
        this.odds = odds;
        this.money = money;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
