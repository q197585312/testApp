package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/9.
 */
public class LotteryStateGameBean implements Serializable {
    String type2;
    String period;
    String game_name;
    String close_time;
    String open_time;
    String open_rule;
    String now_time;

    public String getNow_time() {
        return now_time;
    }

    public void setNow_time(String now_time) {
        this.now_time = now_time;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getPeriod() {

        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getOpen_rule() {
        return open_rule;
    }

    public void setOpen_rule(String open_rule) {
        this.open_rule = open_rule;
    }

    @Override
    public String toString() {
        return "LotteryGameBean{" +
                "type2='" + type2 + '\'' +
                ", period='" + period + '\'' +
                ", game_name='" + game_name + '\'' +
                ", close_time='" + close_time + '\'' +
                ", open_time='" + open_time + '\'' +
                ", open_rule='" + open_rule + '\'' +
                '}';
    }
}

