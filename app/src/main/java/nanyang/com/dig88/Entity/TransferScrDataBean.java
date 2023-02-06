package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2017/4/1.
 */

public class TransferScrDataBean {
    /**
     * code : 0
     * msg : 1
     * data : {"scr888_id":"0149514181","scr888_pwd":"Lemon0962","scr888_balance":"20"}
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
         * scr888_id : 0149514181
         * scr888_pwd : Lemon0962
         * scr888_balance : 20
         */

        private String scr888_id;
        private String scr888_pwd;
        private String scr888_balance;

        public String getScr888_id() {
            return scr888_id;
        }

        public void setScr888_id(String scr888_id) {
            this.scr888_id = scr888_id;
        }

        public String getScr888_pwd() {
            return scr888_pwd;
        }

        public void setScr888_pwd(String scr888_pwd) {
            this.scr888_pwd = scr888_pwd;
        }

        public String getScr888_balance() {
            return scr888_balance;
        }

        public void setScr888_balance(String scr888_balance) {
            this.scr888_balance = scr888_balance;
        }
    }
}
