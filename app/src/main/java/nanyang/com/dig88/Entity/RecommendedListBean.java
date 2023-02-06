package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/25.
 */
public class RecommendedListBean implements Serializable {
    String username;
    String currency;
    String open_time;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    @Override
    public String toString() {
        return "RecommendedListBean{" +
                "username='" + username + '\'' +
                ", currency='" + currency + '\'' +
                ", open_time='" + open_time + '\'' +
                '}';
    }
}
