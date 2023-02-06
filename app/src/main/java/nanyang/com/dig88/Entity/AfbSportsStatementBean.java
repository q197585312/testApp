package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class AfbSportsStatementBean {

    /**
     * code : 1
     * msg : 1
     * win_loss : -3
     * data : [{"bet_amount":"2","winloss":"-2","game_id":"OU1530514474","bet_time":"2017-12-29 14:33:02"},{"bet_amount":"1","winloss":"-1","game_id":"OU1530514389","bet_time":"2017-12-29 14:32:50"}]
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
         * bet_amount : 2
         * winloss : -2
         * game_id : OU1530514474
         * bet_time : 2017-12-29 14:33:02
         */

        private String bet_amount;
        private String winloss;
        private String game_id;
        private String bet_time;

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

        public String getGame_id() {
            return game_id;
        }

        public void setGame_id(String game_id) {
            this.game_id = game_id;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }
    }
}
