package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

public class MgSlotsStatementBean {
    /**
     * code : 1
     * msg : 1
     * data : [{"game":"SLOTS","pool":"Microgaming","bet_time":"2017-08-31 12:00:33","bet_amount":null,"win_loss":-90},{"game":"SLOTS","pool":"Microgaming","bet_time":"2017-08-31 12:00:26","bet_amount":null,"win_loss":-90}]
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
         * pool : Microgaming
         * bet_time : 2017-08-31 12:00:33
         * bet_amount : null
         * win_loss : -90
         */

        private String game;
        private String pool;
        private String bet_time;
        private Object bet_amount;
        private int win_loss;

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

        public Object getBet_amount() {
            return bet_amount;
        }

        public void setBet_amount(Object bet_amount) {
            this.bet_amount = bet_amount;
        }

        public int getWin_loss() {
            return win_loss;
        }

        public void setWin_loss(int win_loss) {
            this.win_loss = win_loss;
        }
    }
}
