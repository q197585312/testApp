package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/1/15.
 */

public class Afb33CurrentBankTypeBean {
    String bankUrl;
    String bankType;

    public Afb33CurrentBankTypeBean(String bankUrl, String bankType) {
        this.bankUrl = bankUrl;
        this.bankType = bankType;
    }

    public String getBankUrl() {
        return bankUrl;
    }

    public void setBankUrl(String bankUrl) {
        this.bankUrl = bankUrl;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
}
