package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

public class GoldStatementBean {

    /**
     * code : 1
     * msg : 1
     * data : [{"game":"WIN","pool":"Baccarat","gameid":"B31708311339","bet_time":"2017-08-31 10:59:22","trs_id":"1708311103201eu8epLfoCnWkRkONjHu","win_loss":"39"},{"game":"BET","pool":"Baccarat","gameid":"B31708311339","bet_time":"2017-08-31 10:58:49","trs_id":"1708311103201eu8epLfoCnWkRkONjHu","win_loss":"-20"}]
     */

    private String code;
    private String msg;
    private double win_loss;
    private List<DataBean> data;

    public double getWin_loss() {
        return win_loss;
    }

    public void setWin_loss(double win_loss) {
        this.win_loss = win_loss;
    }

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
         * pool : Baccarat
         * gameid : B31708311339
         * bet_time : 2017-08-31 10:59:22
         * trs_id : 1708311103201eu8epLfoCnWkRkONjHu
         * win_loss : 39
         */

        private String game;
        private String pool;
        private String gameid;
        private String bet_time;
        private String trs_id;
        private String win_loss;

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

        public String getTrs_id() {
            return trs_id;
        }

        public void setTrs_id(String trs_id) {
            this.trs_id = trs_id;
        }

        public String getWin_loss() {
            return win_loss;
        }

        public void setWin_loss(String win_loss) {
            this.win_loss = win_loss;
        }
    }
}
