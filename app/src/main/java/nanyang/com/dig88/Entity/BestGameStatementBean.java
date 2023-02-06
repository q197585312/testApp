package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

public class BestGameStatementBean {
    /**
     * code : 1
     * msg : 1
     * data : [{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:45:51","win_loss":"0","bet_type":"DRAW"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:45:50","win_loss":"-1.5","bet_type":"BET"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:45:39","win_loss":"0","bet_type":"DRAW"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:45:39","win_loss":"-1.5","bet_type":"BET"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:45:26","win_loss":"0","bet_type":"DRAW"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:45:26","win_loss":"-1.5","bet_type":"BET"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:45:14","win_loss":"0","bet_type":"DRAW"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:45:14","win_loss":"-1.5","bet_type":"BET"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:45:08","win_loss":"0","bet_type":"DRAW"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:45:07","win_loss":"-5","bet_type":"BET"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:44:47","win_loss":"5","bet_type":"DRAW"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:44:47","win_loss":"-1.5","bet_type":"BET"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:44:36","win_loss":"0","bet_type":"DRAW"},{"game":"SLOTS","pool":"BestGamer","bet_time":"2017-08-31 10:44:36","win_loss":"-1.5","bet_type":"BET"}]
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
         * game : SLOTS
         * pool : BestGamer
         * bet_time : 2017-08-31 10:45:51
         * win_loss : 0
         * bet_type : DRAW
         */

        private String game;
        private String pool;
        private String bet_time;
        private String win_loss;
        private String bet_type;

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public String getPool() {
            return pool;
        }

        public void setPool(String pool) {
            this.pool = pool;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }

        public String getWin_loss() {
            return win_loss;
        }

        public void setWin_loss(String win_loss) {
            this.win_loss = win_loss;
        }

        public String getBet_type() {
            return bet_type;
        }

        public void setBet_type(String bet_type) {
            this.bet_type = bet_type;
        }
    }
}
