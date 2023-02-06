package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2019/12/24.
 */

public class SboBetRecordBean {

    /**
     * code : 1
     * msg : 1
     * data : [{"sport_type":"Gaelic Football","bet_amount":"30","winloss":"-30","game_id":"6632681","bet_time":"2019-12-23 22:41:41","live_score":"0:0","bet_team":"Meath","match_name":" vs ","match_time":"2020-09-14 00:00:00","parlay_data":"","league":"All Ireland Football Senior Championship 2020 Winner","islive":"0","hdp":"0","bet_type":"Outright","odds_type":"E","odds":"81","hlaf_score":"0:0","afull_score":"0:0","ticket_status":"running"},{"sport_type":"Gaelic Football","bet_amount":"20","winloss":"-20","game_id":"6632683","bet_time":"2019-12-23 22:41:52","live_score":"0:0","bet_team":"Tyrone","match_name":" vs ","match_time":"2020-06-23 00:00:00","parlay_data":"","league":"Ulster Football Senior Championship 2020 Winner","islive":"0","hdp":"0","bet_type":"Outright","odds_type":"E","odds":"3.5","hlaf_score":"0:0","afull_score":"0:0","ticket_status":"running"},{"sport_type":"Basketball","bet_amount":"40","winloss":"-40","game_id":"6632685","bet_time":"2019-12-23 22:42:28","live_score":"0:0","bet_team":"Over","match_name":"Spartak St. Petersburg (w) vs BC Chernie Medvedi Politekh St. Petersburg (w)","match_time":"2019-12-24 00:00:00","parlay_data":"","league":"Russia Women Basketball Super League 1st Div","islive":"0","hdp":"138.5","bet_type":"Over/Under","odds_type":"M","odds":"0.91","hlaf_score":"0:0","afull_score":"0:0","ticket_status":"running"},{"sport_type":"Basketball","bet_amount":"40","winloss":"-40","game_id":"6632686","bet_time":"2019-12-23 22:42:37","live_score":"0:0","bet_team":"Energia Ivanovo (w)","match_name":"Energia Ivanovo (w) vs Spartak Noginsk 2 (w)","match_time":"2019-12-24 00:00:00","parlay_data":"","league":"Russia Women Basketball Super League 2nd Div","islive":"0","hdp":"-4.5","bet_type":"Handicap","odds_type":"M","odds":"0.92","hlaf_score":"0:0","afull_score":"0:0","ticket_status":"running"}]
     * total_amount : 130
     */

    private String code;
    private String msg;
    private String total_amount;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sport_type : Gaelic Football
         * bet_amount : 30
         * winloss : -30
         * game_id : 6632681
         * bet_time : 2019-12-23 22:41:41
         * live_score : 0:0
         * bet_team : Meath
         * match_name :  vs
         * match_time : 2020-09-14 00:00:00
         * parlay_data :
         * league : All Ireland Football Senior Championship 2020 Winner
         * islive : 0
         * hdp : 0
         * bet_type : Outright
         * odds_type : E
         * odds : 81
         * hlaf_score : 0:0
         * afull_score : 0:0
         * ticket_status : running
         */

        private String sport_type;
        private String bet_amount;
        private String winloss;
        private String game_id;
        private String bet_time;
        private String live_score;
        private String bet_team;
        private String match_name;
        private String match_time;
        private String parlay_data;
        private String league;
        private String islive;
        private String hdp;
        private String bet_type;
        private String odds_type;
        private String odds;
        private String hlaf_score;
        private String afull_score;
        private String ticket_status;

        public String getSport_type() {
            return sport_type;
        }

        public void setSport_type(String sport_type) {
            this.sport_type = sport_type;
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

        public String getLive_score() {
            return live_score;
        }

        public void setLive_score(String live_score) {
            this.live_score = live_score;
        }

        public String getBet_team() {
            return bet_team;
        }

        public void setBet_team(String bet_team) {
            this.bet_team = bet_team;
        }

        public String getMatch_name() {
            return match_name;
        }

        public void setMatch_name(String match_name) {
            this.match_name = match_name;
        }

        public String getMatch_time() {
            return match_time;
        }

        public void setMatch_time(String match_time) {
            this.match_time = match_time;
        }

        public String getParlay_data() {
            return parlay_data;
        }

        public void setParlay_data(String parlay_data) {
            this.parlay_data = parlay_data;
        }

        public String getLeague() {
            return league;
        }

        public void setLeague(String league) {
            this.league = league;
        }

        public String getIslive() {
            return islive;
        }

        public void setIslive(String islive) {
            this.islive = islive;
        }

        public String getHdp() {
            return hdp;
        }

        public void setHdp(String hdp) {
            this.hdp = hdp;
        }

        public String getBet_type() {
            return bet_type;
        }

        public void setBet_type(String bet_type) {
            this.bet_type = bet_type;
        }

        public String getOdds_type() {
            return odds_type;
        }

        public void setOdds_type(String odds_type) {
            this.odds_type = odds_type;
        }

        public String getOdds() {
            return odds;
        }

        public void setOdds(String odds) {
            this.odds = odds;
        }

        public String getHlaf_score() {
            return hlaf_score;
        }

        public void setHlaf_score(String hlaf_score) {
            this.hlaf_score = hlaf_score;
        }

        public String getAfull_score() {
            return afull_score;
        }

        public void setAfull_score(String afull_score) {
            this.afull_score = afull_score;
        }

        public String getTicket_status() {
            return ticket_status;
        }

        public void setTicket_status(String ticket_status) {
            this.ticket_status = ticket_status;
        }
    }
}
