package gaming178.com.casinogame.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/28.
 */
public class LiveInfoBean implements Serializable{
    private String value1;

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    private String value2;
    String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public LiveInfoBean(String type, String value1,String value2) {
        this.type = type;
        this.value1 = value1;
        this.value2 = value2;
    }

    public LiveInfoBean() {
    }

    public void setValue(String s, String s1) {
        this.value1 = s;
        this.value2 = s1;

    }
}
