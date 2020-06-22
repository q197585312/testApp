package gaming178.com.mylibrary.base.soap;

/**
 * Created by Administrator on 2015/8/31.
 */
public class SoapParamsBean {
    String key;
    Object value;

    public SoapParamsBean(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
