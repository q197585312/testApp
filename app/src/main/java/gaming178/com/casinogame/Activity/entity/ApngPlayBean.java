package gaming178.com.casinogame.Activity.entity;

import com.apng.view.ApngImageView;

/**
 * Created by Administrator on 2020/5/8.
 */

public class ApngPlayBean {
    private ApngImageView apngImageView;
    private String uri;

    public ApngPlayBean() {
    }

    public ApngPlayBean(ApngImageView apngImageView, String uri) {
        this.apngImageView = apngImageView;
        this.uri = uri;
    }

    public ApngImageView getApngImageView() {
        return apngImageView;
    }

    public void setApngImageView(ApngImageView apngImageView) {
        this.apngImageView = apngImageView;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
