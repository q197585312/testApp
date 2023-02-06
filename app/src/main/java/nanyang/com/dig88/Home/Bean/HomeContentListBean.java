package nanyang.com.dig88.Home.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/6/20.
 */

public class HomeContentListBean implements Serializable {
    private String gameName;
    private String gameType;
    private int gamePic;
    private List<String> gameList;

    public HomeContentListBean() {

    }

    public HomeContentListBean(String gameName, int gamePic) {
        this.gameName = gameName;
        this.gamePic = gamePic;
    }

    public HomeContentListBean(String gameName, int gamePic, List<String> gameList) {
        this.gameName = gameName;
        this.gamePic = gamePic;
        this.gameList = gameList;
    }

    public HomeContentListBean(String gameName, String gameType, int gamePic) {
        this.gameName = gameName;
        this.gameType = gameType;
        this.gamePic = gamePic;
    }

    public HomeContentListBean(String gameName, String gameType, int gamePic, List<String> gameList) {
        this.gameName = gameName;
        this.gameType = gameType;
        this.gamePic = gamePic;
        this.gameList = gameList;
    }

    public List<String> getGameList() {
        return gameList;
    }

    public void setGameList(List<String> gameList) {
        this.gameList = gameList;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getGamePic() {
        return gamePic;
    }

    public void setGamePic(int gamePic) {
        this.gamePic = gamePic;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
