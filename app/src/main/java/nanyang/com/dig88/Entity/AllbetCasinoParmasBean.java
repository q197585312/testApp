package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2017/4/20.
 */

public class AllbetCasinoParmasBean {
    private String currency;
    private String md5Key;
    private String desKey;
    private String propertyId;

    public AllbetCasinoParmasBean(String currency, String md5Key, String desKey, String propertyId) {
        this.currency = currency;
        this.md5Key = md5Key;
        this.desKey = desKey;
        this.propertyId = propertyId;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
