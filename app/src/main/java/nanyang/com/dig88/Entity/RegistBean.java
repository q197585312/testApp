package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/3/20.
 */

public class RegistBean {

    /**
     * code : 1
     * msg : 1
     * data : {"username":"test2131","password":"5d93ceb70e2bf5daa84ec3d0cd2c731a"}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : test2131
         * password : 5d93ceb70e2bf5daa84ec3d0cd2c731a
         */

        private String username;
        private String password;

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
}
