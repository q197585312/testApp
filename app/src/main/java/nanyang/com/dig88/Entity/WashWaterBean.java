package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */

public class WashWaterBean {

    /**
     * code : 1
     * msg : 1
     * data : [{"p_name":"AFB SPORTS","total_amount":"0"},{"p_name":"SBO SPORTS","total_amount":"0"},{"p_name":"IBC SPORTS","total_amount":"0"},{"p_name":"GD CASINO","total_amount":"0"},{"p_name":"AG CASINO","total_amount":"0"},{"p_name":"GOLD CASINO","total_amount":"0"},{"p_name":"ALLBET CASINO","total_amount":"0"},{"p_name":"DG99 CASINO","total_amount":"0"},{"p_name":"U1888 SLOTS","total_amount":"0"},{"p_name":"HABA SLOTS","total_amount":"0"},{"p_name":"MG SLOTS","total_amount":"0"},{"p_name":"BESTGAMER SLOTS","total_amount":"0"},{"p_name":"PT SLOTS","total_amount":"0"},{"p_name":"KHMER GAMING","total_amount":"0"},{"p_name":"FOREX","total_amount":"0"}]
     * total : 0
     */

    private String code;
    private String msg;
    private float total;
    private List<DataBean> data;

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

    public float getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * p_name : AFB SPORTS
         * total_amount : 0
         */
        private String p_cn_name;
        private String p_name;
        private String total_amount;
        private String p_id;

        public String getP_cn_name() {
            return p_cn_name;
        }

        public void setP_cn_name(String p_cn_name) {
            this.p_cn_name = p_cn_name;
        }

        public String getP_name() {
            return p_name;
        }

        public void setP_name(String p_name) {
            this.p_name = p_name;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getP_id() {
            return p_id;
        }

        public void setP_id(String p_id) {
            this.p_id = p_id;
        }
    }
}
