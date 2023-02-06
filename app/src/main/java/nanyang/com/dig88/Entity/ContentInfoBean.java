package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/8/9.
 */

public class ContentInfoBean implements Serializable {
    String content;
    String contentId;
    int res;
    String contentMin;
    String contentType;

    public ContentInfoBean() {
    }

    public ContentInfoBean(String content, String contentId) {
        this.content = content;
        this.contentId = contentId;
    }

    public ContentInfoBean(String content, String contentId, int res) {
        this.content = content;
        this.contentId = contentId;
        this.res = res;
    }

    public ContentInfoBean(String content, String contentId, String contentType) {
        this.content = content;
        this.contentId = contentId;
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getContentMin() {
        return contentMin;
    }

    public void setContentMin(String contentMin) {
        this.contentMin = contentMin;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
