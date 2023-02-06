package gaming178.com.casinogame.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/11.
 */
public class CurrencyBean implements Serializable {
    String currencyId;
    String currencyName;

    public CurrencyBean(String currencyId, String currencyName) {
        this.currencyId = currencyId;
        this.currencyName = currencyName;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
