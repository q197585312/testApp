package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/19.
 */

public class TransferAccPersonBean {
    /**
     * code : 1
     * msg : 1
     * data : [{"status":"1","amount":"-30","insert_time":"2017-01-19 10:44:57","from_gameid":"1","to_gameid":"2"},{"status":"1","amount":"-20","insert_time":"2017-01-19 10:42:56","from_gameid":"1","to_gameid":"3"},{"status":"1","amount":"-20","insert_time":"2017-01-19 10:42:43","from_gameid":"1","to_gameid":"2"},{"status":"1","amount":"-22","insert_time":"2017-01-19 10:38:36","from_gameid":"1","to_gameid":"3"},{"status":"1","amount":"20","insert_time":"2017-01-19 10:38:23","from_gameid":"3","to_gameid":"1"},{"status":"1","amount":"50","insert_time":"2017-01-19 10:36:03","from_gameid":"2","to_gameid":"1"},{"status":"1","amount":"-20","insert_time":"2017-01-19 10:23:14","from_gameid":"1","to_gameid":"2"},{"status":"1","amount":"-20","insert_time":"2017-01-19 10:22:54","from_gameid":"1","to_gameid":"2"},{"status":"1","amount":"-10","insert_time":"2017-01-19 09:00:33","from_gameid":"1","to_gameid":"2"},{"status":"1","amount":"-20","insert_time":"2017-01-18 17:37:43","from_gameid":"1","to_gameid":"3"},{"status":"1","amount":"-10","insert_time":"2017-01-18 17:37:29","from_gameid":"1","to_gameid":"2"}]
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
         * status : 1
         * amount : -30
         * insert_time : 2017-01-19 10:44:57
         * from_gameid : 1
         * to_gameid : 2
         */

        private String status;
        private String amount;
        private String insert_time;
        private String from_gameid;
        private String to_gameid;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getInsert_time() {
            return insert_time;
        }

        public void setInsert_time(String insert_time) {
            this.insert_time = insert_time;
        }

        public String getFrom_gameid() {
            return from_gameid;
        }

        public void setFrom_gameid(String from_gameid) {
            this.from_gameid = from_gameid;
        }

        public String getTo_gameid() {
            return to_gameid;
        }

        public void setTo_gameid(String to_gameid) {
            this.to_gameid = to_gameid;
        }
    }
}
