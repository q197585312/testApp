package nanyang.com.dig88.Table.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/19.
 */
public class ClearanceBetAmountBean implements Serializable{
    String title;
    int amount;

    public ClearanceBetAmountBean(int amount, String title) {
        this.amount = amount;
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
