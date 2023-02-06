package nanyang.com.dig88.Table.entity;

/**
 * Created by Administrator on 2015/10/26.
 */
public class TableHeaderBean {
    String title;
    int drawableRes;

    public TableHeaderBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }

}
