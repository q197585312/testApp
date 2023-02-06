package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/25.
 */
public class DividendCenterListBean implements Serializable {
   String time;
    String amount;
    String bonus_type;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBonus_type() {
        return bonus_type;
    }

    public void setBonus_type(String bonus_type) {
        this.bonus_type = bonus_type;
    }

    @Override
    public String toString() {
        return "DividendCenterListBean{" +
                "time='" + time + '\'' +
                ", amount='" + amount + '\'' +
                ", bonus_type='" + bonus_type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
