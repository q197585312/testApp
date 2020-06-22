package gaming178.com.casinogame.Activity.entity;

import android.widget.ImageView;

/**
 * Created by Administrator on 2020/4/28.
 */

public class ResultHintBean {
    private ImageView img;
    private int res;

    public ResultHintBean(ImageView img, int res) {
        this.img = img;
        this.res = res;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
