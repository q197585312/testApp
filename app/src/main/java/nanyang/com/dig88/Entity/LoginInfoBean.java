package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/16.
 */
public class LoginInfoBean implements Serializable {
    /* params.put("web_id","2");
     params.put("username","test003");
     params.put("password","11111111");*/
    String webId;
    String username;
    String password;
    String lang;
    boolean isNeedShowUser = true;
    String vipClub360UserName;

    public LoginInfoBean(String username, String password, String webId) {
        this.password = password;
        this.username = username;
        this.webId = webId;
    }

    public String getVipClub360UserName() {
        return vipClub360UserName;
    }

    public void setVipClub360UserName(String vipClub360UserName) {
        this.vipClub360UserName = vipClub360UserName;
    }

    public boolean isNeedShowUser() {
        return isNeedShowUser;
    }

    public void setNeedShowUser(boolean needShowUser) {
        isNeedShowUser = needShowUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
