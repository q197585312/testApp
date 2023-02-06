package nanyang.com.dig88.Entity;

import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/3/8.
 */

public class VideoPlayControlBean {
    private ImageView imgPlay;
    private ImageView imgHint;
    private Button imgStop;

    public VideoPlayControlBean(ImageView imgPlay, ImageView imgHint, Button imgStop) {
        this.imgPlay = imgPlay;
        this.imgHint = imgHint;
        this.imgStop = imgStop;
    }

    public ImageView getImgHint() {

        return imgHint;
    }

    public void setImgHint(ImageView imgHint) {
        this.imgHint = imgHint;
    }

    public ImageView getImgPlay() {
        return imgPlay;
    }

    public void setImgPlay(ImageView imgPlay) {
        this.imgPlay = imgPlay;
    }

    public Button getImgStop() {
        return imgStop;
    }

    public void setImgStop(Button imgStop) {
        this.imgStop = imgStop;
    }
}
