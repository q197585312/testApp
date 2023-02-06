package nanyang.com.dig88.Lottery;

/**
 * Created by Administrator on 2017/4/14.
 */

public class BetResultBean {
    /**
     * code : 1
     * msg : 1
     * data : {"balance":"537.9109999999968","Failure":""}
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
         * balance : 537.9109999999968
         * Failure :
         */

        private String balance;
        private String Failure;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getFailure() {
            return Failure;
        }

        public void setFailure(String Failure) {
            this.Failure = Failure;
        }
    }
}
