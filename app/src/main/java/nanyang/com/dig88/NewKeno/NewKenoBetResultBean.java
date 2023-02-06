package nanyang.com.dig88.NewKeno;

/**
 * Created by Administrator on 2018/9/18.
 */

public class NewKenoBetResultBean {

    /**
     * code : 1
     * msg : 1
     * balance : 45.00
     * oustanding : 10.00
     * recent_list : {"game_type":"[41-50]2-3","return_amount":"10x1.60","last_id":2306,"bet_time":"2018-11-22 9:51","bet_id":"G3146"}
     * errMsg : Success
     */

    private String code;
    private String msg;
    private String balance;
    private String oustanding;
    private RecentListBean recent_list;
    private String errMsg;

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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getOustanding() {
        return oustanding;
    }

    public void setOustanding(String oustanding) {
        this.oustanding = oustanding;
    }

    public RecentListBean getRecent_list() {
        return recent_list;
    }

    public void setRecent_list(RecentListBean recent_list) {
        this.recent_list = recent_list;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public static class RecentListBean {
        /**
         * game_type : [41-50]2-3
         * return_amount : 10x1.60
         * last_id : 2306
         * bet_time : 2018-11-22 9:51
         * bet_id : G3146
         */

        private String game_type;
        private String return_amount;
        private int last_id;
        private String bet_time;
        private String bet_id;

        public String getGame_type() {
            return game_type;
        }

        public void setGame_type(String game_type) {
            this.game_type = game_type;
        }

        public String getReturn_amount() {
            return return_amount;
        }

        public void setReturn_amount(String return_amount) {
            this.return_amount = return_amount;
        }

        public int getLast_id() {
            return last_id;
        }

        public void setLast_id(int last_id) {
            this.last_id = last_id;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }

        public String getBet_id() {
            return bet_id;
        }

        public void setBet_id(String bet_id) {
            this.bet_id = bet_id;
        }
    }
}
