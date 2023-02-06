package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/9.
 */
public class DepositMethodBean implements Serializable{
    String name;
    String value;

    public DepositMethodBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
