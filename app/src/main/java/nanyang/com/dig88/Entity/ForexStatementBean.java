package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

public class ForexStatementBean {
    /**
     * code : 1
     * msg : 1
     * data : [{"game":"XAUUSD","type":"up","bet_time":"2017-08-31 13:59:41","bet_amount":"300.000","factor":"0.76","win_loss":"-300.000","bet_rate":"1302.93","draw_rate":"1302.64"}]
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
         * game : XAUUSD
         * type : up
         * bet_time : 2017-08-31 13:59:41
         * bet_amount : 300.000
         * factor : 0.76
         * win_loss : -300.000
         * bet_rate : 1302.93
         * draw_rate : 1302.64
         */

        private String game;
        private String type;
        private String bet_time;
        private String bet_amount;
        private String factor;
        private String win_loss;
        private String bet_rate;
        private String draw_rate;

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getFactor() {
            return factor;
        }

        public void setFactor(String factor) {
            this.factor = factor;
        }

        public String getWin_loss() {
            return win_loss;
        }

        public void setWin_loss(String win_loss) {
            this.win_loss = win_loss;
        }

        public String getBet_rate() {
            return bet_rate;
        }

        public void setBet_rate(String bet_rate) {
            this.bet_rate = bet_rate;
        }

        public String getDraw_rate() {
            return draw_rate;
        }

        public void setDraw_rate(String draw_rate) {
            this.draw_rate = draw_rate;
        }
    }
}
