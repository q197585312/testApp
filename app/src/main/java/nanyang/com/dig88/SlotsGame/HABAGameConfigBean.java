package nanyang.com.dig88.SlotsGame;

/**
 * Created by Administrator on 2017/4/27.
 */

public class HABAGameConfigBean {
    /**
     * code : 0
     * msg : No Error
     * data : {"action":"https://app-a.insvr.com/go.ashx","brandid":"d85d0efd-f81b-e611-80c3-000d3a805b30","brandgameid":"00fa00bc-1c69-4e9e-b6c1-df5161ffa9d8","token":"995sspsusdjac6fccisaid5bemu0eqcql5j1","mode":"real","locale":"en","mobile":"1","lobbyurl":null}
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
         * action : https://app-a.insvr.com/go.ashx
         * brandid : d85d0efd-f81b-e611-80c3-000d3a805b30
         * brandgameid : 00fa00bc-1c69-4e9e-b6c1-df5161ffa9d8
         * token : 995sspsusdjac6fccisaid5bemu0eqcql5j1
         * mode : real
         * locale : en
         * mobile : 1
         * lobbyurl : null
         */

        private String action;
        private String brandid;
        private String brandgameid;
        private String token;
        private String mode;
        private String locale;
        private String mobile;
        private Object lobbyurl;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getBrandid() {
            return brandid;
        }

        public void setBrandid(String brandid) {
            this.brandid = brandid;
        }

        public String getBrandgameid() {
            return brandgameid;
        }

        public void setBrandgameid(String brandgameid) {
            this.brandgameid = brandgameid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getLobbyurl() {
            return lobbyurl;
        }

        public void setLobbyurl(Object lobbyurl) {
            this.lobbyurl = lobbyurl;
        }
    }
}
