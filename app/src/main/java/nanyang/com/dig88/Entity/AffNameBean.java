package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2018/10/8.
 */

public class AffNameBean {

    /**
     * code : 0
     * msg : No Error
     * data : {"domain":"bets6.net;www.bets6.net","Aff_name":"czy"}
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
         * domain : bets6.net;www.bets6.net
         * Aff_name : czy
         */

        private String domain;
        private String Aff_name;

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getAff_name() {
            return Aff_name;
        }

        public void setAff_name(String Aff_name) {
            this.Aff_name = Aff_name;
        }
    }
}
