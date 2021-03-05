package gaming178.com.casinogame.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/22.
 */
public class HallGameItemBean implements Serializable {
    String title;
    int ImageRes;
    int gameType;
    String browserUrl;


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

    public HallGameItemBean(int imageRes, String title, int gameType) {
        ImageRes = imageRes;
        this.title = title;
        this.gameType = gameType;
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public String getBrowserUrl() {
        return browserUrl;
    }

    public void setBrowserUrl(String browserUrl) {
        this.browserUrl = browserUrl;
    }

    public HallGameItemBean(int imageRes, String title, int gameType,String browserUrl) {
        ImageRes = imageRes;
        this.title = title;
        this.gameType = gameType;
        this.browserUrl = browserUrl;
    }
}
