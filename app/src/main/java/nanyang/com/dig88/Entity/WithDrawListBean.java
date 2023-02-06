package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/22.
 */
public class WithDrawListBean implements Serializable {
    String date_time;
    String amount;
    String status;
    String id_mod_deposit_withdraw;
    String trs_id;

    public String getTrs_id() {
        return trs_id;
    }

    public void setTrs_id(String trs_id) {
        this.trs_id = trs_id;
    }

    public String getId_mod_deposit_withdraw() {
        return id_mod_deposit_withdraw;
    }

    public void setId_mod_deposit_withdraw(String id_mod_deposit_withdraw) {
        this.id_mod_deposit_withdraw = id_mod_deposit_withdraw;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DepositListBean{" +
                "date_time='" + date_time + '\'' +
                ", amount='" + amount + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
