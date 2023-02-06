package nanyang.com.dig88.Table.entity;

/**
 * Created by Administrator on 2015/10/23.
 */
public class PopMenuItemBean {
    int drawableRes;
    String title;
    String mark;
    String state;

    public PopMenuItemBean(int drawableRes, String title, String mark) {
        this.drawableRes = drawableRes;
        this.title = title;
        this.mark = mark;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
