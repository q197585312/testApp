package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2019/12/24.
 */

public class Afb2BetRecordBean {

    /**
     * code : 1
     * msg : 1
     * total_amount : 80
     * total_bet_amount : 80
     * data : [{"amount":"20","bet_amount":"20","winloss":"0","game_id":"1013866192","bet_time":"2019-12-24 10:30:23","league_id":"AUSTRALIA HYUNDAI A LEAGUE","home_id":"Sydney FC","away":"Melbourne City","score":"0:0","runscore":"","workdate":"2019-12-29 00:00:00","side":"1","game":"1","res":"P","bet_status":"N","odds":"1.99","flg":"","info":"0","laster_update_time":"2019-12-24 10:30:23","ventransid":"2070e009dc21331f","league_name":"AUSTRALIA HYUNDAI A LEAGUE","league_name_cn":"AUSTRALIA HYUNDAI A LEAGUE","home":"Sydney FC","home_cn":"Sydney FC","away_cn":"Melbourne City"},{"amount":"60","bet_amount":"60","winloss":"0","game_id":"OE013866184","bet_time":"2019-12-24 10:30:14","league_id":"AUSTRALIA HYUNDAI A LEAGUE","home_id":"Central Coast Mariners","away":"Perth Glory","score":"0:0","runscore":"","workdate":"2019-12-31 00:00:00","side":"2","game":"OE","res":"P","bet_status":"N","odds":"0.9","flg":"","info":"0","laster_update_time":"2019-12-24 10:30:14","ventransid":"c13016f4aee077af","league_name":"AUSTRALIA HYUNDAI A LEAGUE","league_name_cn":"AUSTRALIA HYUNDAI A LEAGUE","home":"Central Coast Mariners","home_cn":"Central Coast Mariners","away_cn":"Perth Glory"}]
     * method : afb2
     */

    private String code;
    private String msg;
    private String total_amount;
    private String total_bet_amount;
    private String method;
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

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTotal_bet_amount() {
        return total_bet_amount;
    }

    public void setTotal_bet_amount(String total_bet_amount) {
        this.total_bet_amount = total_bet_amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * amount : 20
         * bet_amount : 20
         * winloss : 0
         * game_id : 1013866192
         * bet_time : 2019-12-24 10:30:23
         * league_id : AUSTRALIA HYUNDAI A LEAGUE
         * home_id : Sydney FC
         * away : Melbourne City
         * score : 0:0
         * runscore :
         * workdate : 2019-12-29 00:00:00
         * side : 1
         * game : 1
         * res : P
         * bet_status : N
         * odds : 1.99
         * flg :
         * info : 0
         * laster_update_time : 2019-12-24 10:30:23
         * ventransid : 2070e009dc21331f
         * league_name : AUSTRALIA HYUNDAI A LEAGUE
         * league_name_cn : AUSTRALIA HYUNDAI A LEAGUE
         * home : Sydney FC
         * home_cn : Sydney FC
         * away_cn : Melbourne City
         */

        private String amount;
        private String bet_amount;
        private String winloss;
        private String game_id;
        private String bet_time;
        private String league_id;
        private String home_id;
        private String away;
        private String score;
        private String runscore;
        private String workdate;
        private String side;
        private String game;
        private String res;
        private String bet_status;
        private String odds;
        private String flg;
        private String info;
        private String laster_update_time;
        private String ventransid;
        private String league_name;
        private String league_name_cn;
        private String home;
        private String home_cn;
        private String away_cn;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
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

        public String getLeague_id() {
            return league_id;
        }

        public void setLeague_id(String league_id) {
            this.league_id = league_id;
        }

        public String getHome_id() {
            return home_id;
        }

        public void setHome_id(String home_id) {
            this.home_id = home_id;
        }

        public String getAway() {
            return away;
        }

        public void setAway(String away) {
            this.away = away;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getRunscore() {
            return runscore;
        }

        public void setRunscore(String runscore) {
            this.runscore = runscore;
        }

        public String getWorkdate() {
            return workdate;
        }

        public void setWorkdate(String workdate) {
            this.workdate = workdate;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public String getRes() {
            return res;
        }

        public void setRes(String res) {
            this.res = res;
        }

        public String getBet_status() {
            return bet_status;
        }

        public void setBet_status(String bet_status) {
            this.bet_status = bet_status;
        }

        public String getOdds() {
            return odds;
        }

        public void setOdds(String odds) {
            this.odds = odds;
        }

        public String getFlg() {
            return flg;
        }

        public void setFlg(String flg) {
            this.flg = flg;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getLaster_update_time() {
            return laster_update_time;
        }

        public void setLaster_update_time(String laster_update_time) {
            this.laster_update_time = laster_update_time;
        }

        public String getVentransid() {
            return ventransid;
        }

        public void setVentransid(String ventransid) {
            this.ventransid = ventransid;
        }

        public String getLeague_name() {
            return league_name;
        }

        public void setLeague_name(String league_name) {
            this.league_name = league_name;
        }

        public String getLeague_name_cn() {
            return league_name_cn;
        }

        public void setLeague_name_cn(String league_name_cn) {
            this.league_name_cn = league_name_cn;
        }

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getHome_cn() {
            return home_cn;
        }

        public void setHome_cn(String home_cn) {
            this.home_cn = home_cn;
        }

        public String getAway_cn() {
            return away_cn;
        }

        public void setAway_cn(String away_cn) {
            this.away_cn = away_cn;
        }
    }
}
