package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/25.
 */
public class ApplyDividendListBean implements Serializable {
   String provider;
    String end_time;
    String status;

    String ref_amount;
    String transfer_count;
    String currency;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRef_amount() {
        return ref_amount;
    }

    public void setRef_amount(String ref_amount) {
        this.ref_amount = ref_amount;
    }

    public String getTransfer_count() {
        return transfer_count;
    }

    public void setTransfer_count(String transfer_count) {
        this.transfer_count = transfer_count;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "ApplyDividendListBean{" +
                "provider='" + provider + '\'' +
                ", end_time='" + end_time + '\'' +
                ", status='" + status + '\'' +
                ", ref_amount='" + ref_amount + '\'' +
                ", transfer_count='" + transfer_count + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
