package nanyang.com.dig88.Lottery;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/24.
 */
public class SelectedItemBean implements Serializable{
    String name;

    public SelectedItemBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

