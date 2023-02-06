package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/18.(数字游戏实体)
 */
public class DigGameResultBean implements Serializable {
    String period;
    String open_time;
    String result;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PersonGameResultBean{" +
                "period='" + period + '\'' +
                ", open_time='" + open_time + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
