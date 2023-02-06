package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2019/12/3.
 */

public class FFYLStatementBean {

    /**
     * code : 1
     * msg : 1
     * win_loss : 11.802575100000013
     * bet_amount : 364.80686696
     * data : [{"id_mod_member":"718071","trs_id":"5de5c47d4725cc375464cbe8","game_name":"Red Black","bet_time":"2019-12-03 10:11:53","currency_rate":"4","bet_amount":"128.75536481","win_loss":"-128.75536481","ip":"36.37.174.245 - Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36","username":"leonew1","commsion":"0","remark":"0"},{"id_mod_member":"718071","trs_id":"5de5c4024725cc375464cbdd","game_name":"Red Black","bet_time":"2019-12-03 10:09:50","currency_rate":"4","bet_amount":"42.91845494","win_loss":"-42.91845494","ip":"36.37.174.245 - Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36","username":"leonew1","commsion":"0","remark":"0"},{"id_mod_member":"718071","trs_id":"5de5c3a04725cc375464cbd3","game_name":"Red Black","bet_time":"2019-12-03 10:08:12","currency_rate":"4","bet_amount":"64.3776824","win_loss":"61.15879828","ip":"36.37.174.245 - Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36","username":"leonew1","commsion":"3.21888412","remark":"0"},{"id_mod_member":"718071","trs_id":"5de5c3b84725cc375464cbd8","game_name":"Red Black","bet_time":"2019-12-03 10:08:37","currency_rate":"4","bet_amount":"128.75536481","win_loss":"122.31759657","ip":"36.37.174.245 - Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36","username":"leonew1","commsion":"6.43776824","remark":"0"}]
     */

    private String code;
    private String msg;
    private String win_loss;
    private String bet_amount;
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

    public String getBet_amount() {
        return bet_amount;
    }

    public void setBet_amount(String bet_amount) {
        this.bet_amount = bet_amount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id_mod_member : 718071
         * trs_id : 5de5c47d4725cc375464cbe8
         * game_name : Red Black
         * bet_time : 2019-12-03 10:11:53
         * currency_rate : 4
         * bet_amount : 128.75536481
         * win_loss : -128.75536481
         * ip : 36.37.174.245 - Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36
         * username : leonew1
         * commsion : 0
         * remark : 0
         */

        private String id_mod_member;
        private String trs_id;
        private String game_name;
        private String bet_time;
        private String currency_rate;
        private String bet_amount;
        private String win_loss;
        private String ip;
        private String username;
        private String commsion;
        private String remark;

        public String getId_mod_member() {
            return id_mod_member;
        }

        public void setId_mod_member(String id_mod_member) {
            this.id_mod_member = id_mod_member;
        }

        public String getTrs_id() {
            return trs_id;
        }

        public void setTrs_id(String trs_id) {
            this.trs_id = trs_id;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }

        public String getCurrency_rate() {
            return currency_rate;
        }

        public void setCurrency_rate(String currency_rate) {
            this.currency_rate = currency_rate;
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

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCommsion() {
            return commsion;
        }

        public void setCommsion(String commsion) {
            this.commsion = commsion;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
