package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class SagamingStatementBean {

    /**
     * code : 1
     * msg : 1
     * win_loss : -0.5
     * data : [{"game_type":"Baccarat","game_id":"45806432661504","table_id":"603","period":"5","ju_id":"12","bet_time":"2018-01-09 17:01:48","bet_amount":"10","win_loss":"9.5"},{"game_type":"Baccarat","game_id":"45806365552640","table_id":"603","period":"5","ju_id":"11","bet_time":"2018-01-09 17:01:17","bet_amount":"5","win_loss":"5"}]
     */

    private String code;
    private String msg;
    private String win_loss;
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

    public String getWin_loss() {
        return win_loss;
    }

    public void setWin_loss(String win_loss) {
        this.win_loss = win_loss;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * game_type : Baccarat
         * game_id : 45806432661504
         * table_id : 603
         * period : 5
         * ju_id : 12
         * bet_time : 2018-01-09 17:01:48
         * bet_amount : 10
         * win_loss : 9.5
         */

        private String game_type;
        private String game_id;
        private String table_id;
        private String period;
        private String ju_id;
        private String bet_time;
        private String bet_amount;
        private String win_loss;

        public String getGame_type() {
            return game_type;
        }

        public void setGame_type(String game_type) {
            this.game_type = game_type;
        }

        public String getGame_id() {
            return game_id;
        }

        public void setGame_id(String game_id) {
            this.game_id = game_id;
        }

        public String getTable_id() {
            return table_id;
        }

        public void setTable_id(String table_id) {
            this.table_id = table_id;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getJu_id() {
            return ju_id;
        }

        public void setJu_id(String ju_id) {
            this.ju_id = ju_id;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }

        public String getBet_amount() {
            return bet_amount;
        }

        public void setBet_amount(String bet_amount) {
            this.bet_amount = bet_amount;
        }

        public String getWin_loss() {
            return win_loss;
        }

        public void setWin_loss(String win_loss) {
            this.win_loss = win_loss;
        }
    }
}
