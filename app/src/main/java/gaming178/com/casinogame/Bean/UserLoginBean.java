package gaming178.com.casinogame.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/14.
 */

public class UserLoginBean implements Serializable{
    String site;
    String username;
    String password;
    Boolean rememberMe;

    public UserLoginBean() {
    }

    public UserLoginBean(String site, String username, String password) {
        this.site = site;
        this.username = username;
        this.password = password;
    }

    public UserLoginBean(String site, String username, String password, Boolean rememberMe) {
        this.site = site;
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}