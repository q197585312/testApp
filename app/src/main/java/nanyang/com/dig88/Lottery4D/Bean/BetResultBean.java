package nanyang.com.dig88.Lottery4D.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/11/28.
 */

public class BetResultBean {

    /**
     * code : 1
     * msg : 1
     * data : {"balance":"1261.7029999999995","count_success":1,"total":[1,0,0,0,1],"success":[{"period":"20181128001","pools":"Magnum","number":"1234","bettype":"108","amount":1,"date":"11/28","dname":"3"}],"failure":[{"period":"20181128001","pools":"Magnum","number":"1234","bettype":"109","amount":0,"date":"11/28","dname":"3","errmsg":"12"}]}
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
         * balance : 1261.7029999999995
         * count_success : 1
         * total : [1,0,0,0,1]
         * success : [{"period":"20181128001","pools":"Magnum","number":"1234","bettype":"108","amount":1,"date":"11/28","dname":"3"}]
         * failure : [{"period":"20181128001","pools":"Magnum","number":"1234","bettype":"109","amount":0,"date":"11/28","dname":"3","errmsg":"12"}]
         */

        private String balance;
        private int count_success;
        private List<Double> total;
        private List<SuccessBean> success;
        private List<FailureBean> failure;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public int getCount_success() {
            return count_success;
        }

        public void setCount_success(int count_success) {
            this.count_success = count_success;
        }

        public List<Double> getTotal() {
            return total;
        }

        public void setTotal(List<Double> total) {
            this.total = total;
        }

        public List<SuccessBean> getSuccess() {
            return success;
        }

        public void setSuccess(List<SuccessBean> success) {
            this.success = success;
        }

        public List<FailureBean> getFailure() {
            return failure;
        }

        public void setFailure(List<FailureBean> failure) {
            this.failure = failure;
        }

        public static class SuccessBean {
            /**
             * period : 20181128001
             * pools : Magnum
             * number : 1234
             * bettype : 108
             * amount : 1
             * date : 11/28
             * dname : 3
             */

            private String period;
            private String pools;
            private String number;
            private String bettype;
            private float amount;
            private String date;
            private String dname;

            public String getPeriod() {
                return period;
            }

            public void setPeriod(String period) {
                this.period = period;
            }

            public String getPools() {
                return pools;
            }

            public void setPools(String pools) {
                this.pools = pools;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getBettype() {
                return bettype;
            }

            public void setBettype(String bettype) {
                this.bettype = bettype;
            }

            public float getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }
        }

        public static class FailureBean {
            /**
             * period : 20181128001
             * pools : Magnum
             * number : 1234
             * bettype : 109
             * amount : 0
             * date : 11/28
             * dname : 3
             * errmsg : 12
             */

            private String period;
            private String pools;
            private String number;
            private String bettype;
            private float amount;
            private String date;
            private String dname;
            private String errmsg;

            public String getPeriod() {
                return period;
            }

            public void setPeriod(String period) {
                this.period = period;
            }

            public String getPools() {
                return pools;
            }

            public void setPools(String pools) {
                this.pools = pools;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getBettype() {
                return bettype;
            }

            public void setBettype(String bettype) {
                this.bettype = bettype;
            }

            public float getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public String getErrmsg() {
                return errmsg;
            }

            public void setErrmsg(String errmsg) {
                this.errmsg = errmsg;
            }
        }
    }
}
