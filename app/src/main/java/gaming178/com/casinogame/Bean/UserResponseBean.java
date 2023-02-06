package gaming178.com.casinogame.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/14.
 */

public class UserResponseBean implements Serializable {

    /**
     * ErrCode : 0
     * ErrText :
     * Result : {"username":"PAKMC1@G","nickname":"LK00B0PAKMC1","currency":"IDR"}
     */

    private int ErrCode;
    private String ErrText;
    /**
     * username : PAKMC1@G
     * nickname : LK00B0PAKMC1
     * currency : IDR
     */

    private ResultBean Result;

    public int getErrCode() {
        return ErrCode;
    }

    public void setErrCode(int ErrCode) {
        this.ErrCode = ErrCode;
    }

    public String getErrText() {
        return ErrText;
    }

    public void setErrText(String ErrText) {
        this.ErrText = ErrText;
    }

    public ResultBean getResult() {
        return Result;
    }

    public void setResult(ResultBean Result) {
        this.Result = Result;
    }

    public static class ResultBean {
        private String username;
        private String nickname;
        private String currency;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }
}
