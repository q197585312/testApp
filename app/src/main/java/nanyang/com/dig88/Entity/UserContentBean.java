package nanyang.com.dig88.Entity;

/**
 * Created by 47184 on 2019/7/1.
 */

public class UserContentBean {
    private String gameName;
    private String className;

    public UserContentBean(String gameName, String className) {
        this.gameName = gameName;
        this.className = className;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
