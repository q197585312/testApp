package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/7/8.
 */

public class StatementInfoBean {
    private String url;
    private String gameName;
    private String gameType;

    public StatementInfoBean(String url, String gameName, String gameType) {
        this.url = url;
        this.gameName = gameName;
        this.gameType = gameType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
