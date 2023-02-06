package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/12/29.
 */

public class AfbLimitBean {

    /**
     * code : 1
     * msg : 1
     * data : [{"type2":"52","min_max":"USD^^^^^^|SGD^^^^^^|MYR^1000^5^2000^^^|THB^50^1^100^^^|CNY^^^^^^|HKD^^^^^^|VND^^^^^^|IDR^^^^^^|KRW^^^^^^|"},{"type2":"61","min_max":"USD^^^^^^|SGD^^^^^^|MYR^^^^^^|THB^^^^^^|CNY^^^^^^|HKD^^^^^^|VND^^^^^^|IDR^^^^^^|KRW^^^^^^|"},{"type2":"98","min_max":"USD^^^^^^|SGD^^^^^^|MYR^500^15^2000^^10^5000|THB^400^1^1500^^99^100000|CNY^^^^^^|HKD^500000^5000^1000000^^^|VND^10000^50^50000^^10^100000|IDR^10000^10^100000^^10^100000|KRW^20000^6000^50000^^6000^500000|"},{"type2":"100","min_max":"USD^^^^^^|SGD^^^^^^|MYR^^^^^^|THB^500^110^10000^^10^1000|CNY^^^^^^|HKD^^^^^^|VND^^^^^^|IDR^^^^^^|KRW^^^^^^|"},{"type2":"0","min_max":"USD^^^^^^|SGD^^^^^^|MYR^^^^^^|THB^^^^^^|CNY^^^^^^|HKD^^^^^^|VND^^^^^^|IDR^^^^^^|KRW^^^^^^|"}]
     */

    private String code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type2 : 52
         * min_max : USD^^^^^^|SGD^^^^^^|MYR^1000^5^2000^^^|THB^50^1^100^^^|CNY^^^^^^|HKD^^^^^^|VND^^^^^^|IDR^^^^^^|KRW^^^^^^|
         */

        private String type2;
        private String min_max;
        private String member_min;
        private String member_max;
        private String match_max;

        public String getMatch_max() {
            return match_max;
        }

        public void setMatch_max(String match_max) {
            this.match_max = match_max;
        }

        public String getMember_min() {
            return member_min;
        }

        public void setMember_min(String member_min) {
            this.member_min = member_min;
        }

        public String getMember_max() {
            return member_max;
        }

        public void setMember_max(String member_max) {
            this.member_max = member_max;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public String getMin_max() {
            return min_max;
        }

        public void setMin_max(String min_max) {
            this.min_max = min_max;
        }
    }
}
