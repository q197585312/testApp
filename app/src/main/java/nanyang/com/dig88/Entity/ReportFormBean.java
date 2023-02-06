package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 15-12-22.
 */
public class ReportFormBean implements Serializable {
    String game;
    String pool;
    String type;
    String number;
    String factor;
    String discount;
    String bet_amount;
    String period;
    String bet_time;

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getBet_amount() {
        return bet_amount;
    }

    public void setBet_amount(String bet_amount) {
        this.bet_amount = bet_amount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getBet_time() {
        return bet_time;
    }

    public void setBet_time(String bet_time) {
        this.bet_time = bet_time;
    }

    @Override
    public String toString() {
        return "ReportFormBean{" +
                "game='" + game + '\'' +
                ", pool='" + pool + '\'' +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", factor='" + factor + '\'' +
                ", discount='" + discount + '\'' +
                ", bet_amount='" + bet_amount + '\'' +
                ", period='" + period + '\'' +
                ", bet_time='" + bet_time + '\'' +
                '}';
    }
}
