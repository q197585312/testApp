package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 15-12-22.
 */
public class CompleteReportFormBean implements Serializable {
    String game;
    String pool;
    String type;
    String number;
    String factor;
    String bet_amount;
    String period;
    String  win_loss;
    String  result;

    public String getWin_loss() {
        return win_loss;
    }

    public void setWin_loss(String win_loss) {
        this.win_loss = win_loss;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

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

    @Override
    public String toString() {
        return "CompleteReportFormBean{" +
                "game='" + game + '\'' +
                ", pool='" + pool + '\'' +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", factor='" + factor + '\'' +
                ", bet_amount='" + bet_amount + '\'' +
                ", period='" + period + '\'' +
                ", win_loss='" + win_loss + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
