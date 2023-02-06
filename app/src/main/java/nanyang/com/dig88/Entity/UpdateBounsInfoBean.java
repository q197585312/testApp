package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/7/24.
 */

public class UpdateBounsInfoBean {
    private String gameName;
    private String gameType;

    public UpdateBounsInfoBean(String gameName, String gameType) {
        this.gameName = gameName;
        this.gameType = gameType;
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
