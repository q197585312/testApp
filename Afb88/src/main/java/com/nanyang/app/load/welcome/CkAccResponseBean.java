package com.nanyang.app.load.welcome;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/1/8.
 */

class CkAccResponseBean implements Serializable {
    String username;
    String token;
    String error;
    String serverId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
