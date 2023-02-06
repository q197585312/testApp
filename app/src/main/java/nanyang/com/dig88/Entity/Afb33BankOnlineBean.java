package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2019/1/15.
 */

public class Afb33BankOnlineBean {

    /**
     * code : 1
     * data : [{"id_mod_payment_type":"2","payment_id":"1","pay_name":"易付","banktype":"3","min":"10","max":"4500","action":"http://pay.8188.ws/yifu-pay/pay-go.php","info":"京东钱包"},{"id_mod_payment_type":"3","payment_id":"1","pay_name":"易付","banktype":"4","min":"5","max":"700","action":"http://pay.8188.ws/yifu-pay/pay-go.php","info":"QQ钱包"},{"id_mod_payment_type":"4","payment_id":"1","pay_name":"易付","banktype":"5","min":"30","max":"5000","action":"http://pay.8188.ws/yifu-pay/pay-go.php","info":"快捷支付"},{"id_mod_payment_type":"9","payment_id":"1","pay_name":"易付","banktype":"6","min":"10","max":"4499","action":"http://pay.8188.ws/yifu-pay/pay-go.php","info":"银联扫码"}]
     */

    private String code;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id_mod_payment_type : 2
         * payment_id : 1
         * pay_name : 易付
         * banktype : 3
         * min : 10
         * max : 4500
         * action : http://pay.8188.ws/yifu-pay/pay-go.php
         * info : 京东钱包
         */

        private String id_mod_payment_type;
        private String payment_id;
        private String pay_name;
        private String banktype;
        private String min;
        private String max;
        private String action;
        private String info;

        public String getId_mod_payment_type() {
            return id_mod_payment_type;
        }

        public void setId_mod_payment_type(String id_mod_payment_type) {
            this.id_mod_payment_type = id_mod_payment_type;
        }

        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;
        }

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }

        public String getBanktype() {
            return banktype;
        }

        public void setBanktype(String banktype) {
            this.banktype = banktype;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
