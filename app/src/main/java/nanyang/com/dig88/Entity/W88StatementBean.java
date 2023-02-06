package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

public class W88StatementBean {
    /**
     * code : 1
     * msg : 1
     * data : [{"game":"WIN","pool":null,"gameid":"676020753","bet_time":"2017-08-31 11:13:14","bet_amount":-37.5,"winloss":"37.5"},{"game":"BET","pool":null,"gameid":"676020753","bet_time":"2017-08-31 11:13:14","bet_amount":7.5,"winloss":"-7.5"},{"game":"WIN","pool":null,"gameid":"676020560","bet_time":"2017-08-31 11:13:07","bet_amount":0,"winloss":"0"},{"game":"BET","pool":null,"gameid":"676020560","bet_time":"2017-08-31 11:13:06","bet_amount":7.5,"winloss":"-7.5"}]
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
         * game : WIN
         * pool : null
         * gameid : 676020753
         * bet_time : 2017-08-31 11:13:14
         * bet_amount : -37.5
         * winloss : 37.5
         */

        private String game;
        private Object pool;
        private String gameid;
        private String bet_time;
        private double bet_amount;
        private String winloss;

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public Object getPool() {
            return pool;
        }

        public void setPool(Object pool) {
            this.pool = pool;
        }

        public String getGameid() {
            return gameid;
        }

        public void setGameid(String gameid) {
            this.gameid = gameid;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }

        public double getBet_amount() {
            return bet_amount;
        }

        public void setBet_amount(double bet_amount) {
            this.bet_amount = bet_amount;
        }

        public String getWinloss() {
            return winloss;
        }

        public void setWinloss(String winloss) {
            this.winloss = winloss;
        }
    }
}
