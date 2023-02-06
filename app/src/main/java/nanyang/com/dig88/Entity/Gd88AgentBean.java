package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/11/15.
 */

public class Gd88AgentBean {

    /**
     * code : 1
     * msg : No Error
     * data : {"web_id":"995","currency":"THB","agent":"D0DDIG88Y"}
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
         * currency : THB
         * agent : D0DDIG88Y
         */

        private String web_id;
        private String currency;
        private String agent;

        public String getWeb_id() {
            return web_id;
        }

        public void setWeb_id(String web_id) {
            this.web_id = web_id;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }
    }
}
