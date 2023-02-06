package nanyang.com.dig88.Home.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/6/21.
 */

public class LoginStatusBean implements Serializable {
    private String loginStatus;
    private String username;
    private String password;

    public LoginStatusBean() {
    }

    public LoginStatusBean(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
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
