package nanyang.com.dig88.Entity;

import android.widget.CheckBox;

/**
 * Created by 47184 on 2019/4/19.
 */

public class RunningWaterGameBean {
    private int order;
    private String title;
    private int type;
    private int value;
    private CheckBox checkBox;

    public RunningWaterGameBean() {
    }

    public RunningWaterGameBean(String title, int type, int value) {
        this.title = title;
        this.type = type;
        this.value = value;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
