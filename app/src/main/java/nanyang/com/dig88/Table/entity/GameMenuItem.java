package nanyang.com.dig88.Table.entity;

/**
 * Created by Administrator on 2015/11/18.
 */
public class GameMenuItem {
    int drawableRes;
    String title;
    String value;

    public GameMenuItem(int drawableRes, String title,String value) {
        this.drawableRes = drawableRes;
        this.title = title;
        this.value=value;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
