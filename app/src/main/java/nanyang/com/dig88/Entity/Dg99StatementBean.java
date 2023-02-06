package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class Dg99StatementBean {

    /**
     * code : 1
     * msg : 1
     * win_loss : 200
     * data : [{"bet_amount":"100","winloss":"100","game_name":"Dragon Tiger","tableName":"10301","bet_time":"2018-01-09 17:21:51"},{"bet_amount":"100","winloss":"100","game_name":"Jing Mi Baccarat","tableName":"20803","bet_time":"2018-01-09 17:22:05"}]
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
         * bet_amount : 100
         * winloss : 100
         * game_name : Dragon Tiger
         * tableName : 10301
         * bet_time : 2018-01-09 17:21:51
         */

        private String bet_amount;
        private String winloss;
        private String game_name;
        private String tableName;
        private String bet_time;
        private String sport_type;
        private String game_id;

        public String getSport_type() {
            return sport_type;
        }

        public void setSport_type(String sport_type) {
            this.sport_type = sport_type;
        }

        public String getGame_id() {
            return game_id;
        }

        public void setGame_id(String game_id) {
            this.game_id = game_id;
        }

        public String getBet_amount() {
            return bet_amount;
        }

        public void setBet_amount(String bet_amount) {
            this.bet_amount = bet_amount;
        }

        public String getWinloss() {
            return winloss;
        }

        public void setWinloss(String winloss) {
            this.winloss = winloss;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }
    }
}
