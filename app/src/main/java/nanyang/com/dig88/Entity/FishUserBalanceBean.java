package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2018/5/10.
 */

public class FishUserBalanceBean {

    /**
     * code : 1
     * msg : No Error
     * data : {"web_id":"995","balance":"202.3568727519044","rate":"31.594","cnyRate":"6.333"}
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
         * web_id : 995
         * balance : 202.3568727519044
         * rate : 31.594
         * cnyRate : 6.333
         */

        private String web_id;
        private String balance;
        private String rate;
        private String cnyRate;

        public String getWeb_id() {
            return web_id;
        }

        public void setWeb_id(String web_id) {
            this.web_id = web_id;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getCnyRate() {
            return cnyRate;
        }

        public void setCnyRate(String cnyRate) {
            this.cnyRate = cnyRate;
        }
    }
}
