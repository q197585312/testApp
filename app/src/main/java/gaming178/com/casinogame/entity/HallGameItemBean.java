package gaming178.com.casinogame.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/22.
 */
public class HallGameItemBean implements Serializable{
    String title;
    int ImageRes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageRes() {
        return ImageRes;
    }

    public void setImageRes(int imageRes) {
        ImageRes = imageRes;
    }

    public HallGameItemBean(int imageRes, String title) {
        ImageRes = imageRes;
        this.title = title;
    }
}
