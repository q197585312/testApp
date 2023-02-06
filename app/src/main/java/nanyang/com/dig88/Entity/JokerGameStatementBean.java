package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2019/7/24.
 */

public class JokerGameStatementBean {

    /**
     * code : 1
     * msg : 1
     * win_loss : 4.8
     * data : [{"win_loss":"4.8","bet_amount":"5.4","bet_time":"2019-07-23 17:29:49","trs_id":"qptj16dbo1e14","type":"Main","game_id":"kf41ymtxfos1r","game_name":null},{"win_loss":"0","bet_amount":"5.4","bet_time":"2019-07-23 17:29:43","trs_id":"qptg445go1e14","type":"Main","game_id":"kf41ymtxfos1r","game_name":null}]
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
         * win_loss : 4.8
         * bet_amount : 5.4
         * bet_time : 2019-07-23 17:29:49
         * trs_id : qptj16dbo1e14
         * type : Main
         * game_id : kf41ymtxfos1r
         * game_name : null
         */

        private String win_loss;
        private String bet_amount;
        private String bet_time;
        private String trs_id;
        private String type;
        private String game_id;
        private Object game_name;

        public String getWin_loss() {
            return win_loss;
        }

        public void setWin_loss(String win_loss) {
            this.win_loss = win_loss;
        }

        public String getBet_amount() {
            return bet_amount;
        }

        public void setBet_amount(String bet_amount) {
            this.bet_amount = bet_amount;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGame_id() {
            return game_id;
        }

        public void setGame_id(String game_id) {
            this.game_id = game_id;
        }

        public Object getGame_name() {
            return game_name;
        }

        public void setGame_name(Object game_name) {
            this.game_name = game_name;
        }
    }
}
