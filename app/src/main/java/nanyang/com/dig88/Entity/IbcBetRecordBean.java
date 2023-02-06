package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2019/12/24.
 */

public class IbcBetRecordBean {

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
         * sport_type : 1
         * bet_amount : 100
         * winloss : -84
         * game_id : 108205587335
         * bet_time : 2019-12-25 05:17:08
         * parlay_type :
         * parlay_data :
         * bet_type : 3
         * bet_team : h
         * hdp : 3
         * bet_tag :
         * league_id : 404
         * match_id : 33814771
         * odds_type : 1
         * islive : 0
         * home_score : 0
         * away_score : 0
         * agentid : W9Eqo9Lem24
         * home_id : 444069
         * away_id : 178142
         * parlay_ref_no : 0
         * is_lucky : 0
         * combo_type :
         * ticket_status : running
         * odds : -0.84
         * away_team_name_cn : 西悉尼流浪者
         * away_team_name : Western Sydney Wanderers FC
         * home_team_name_cn : 阿德莱德联
         * home_team_name : Adelaide United
         * league_name_cn : 澳洲甲组联赛
         * league_name : AUSTRALIA A-LEAGUE
         * bet_type_name : Over/Under
         * bet_detail : Over
         * sport_table_name : Soccer
         * odds_type_name : MY
         */

        private String sport_type;
        private String bet_amount;
        private String winloss;
        private String game_id;
        private String bet_time;
        private String parlay_type;
        private String parlay_data;
        private String bet_type;
        private String bet_team;
        private String hdp;
        private String bet_tag;
        private String league_id;
        private String match_id;
        private String odds_type;
        private String islive;
        private String home_score;
        private String away_score;
        private String agentid;
        private String home_id;
        private String away_id;
        private String parlay_ref_no;
        private String is_lucky;
        private String combo_type;
        private String ticket_status;
        private String odds;
        private String away_team_name_cn;
        private String away_team_name;
        private String home_team_name_cn;
        private String home_team_name;
        private String league_name_cn;
        private String league_name;
        private String bet_type_name;
        private String bet_detail;
        private String sport_table_name;
        private String odds_type_name;

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

        public String getParlay_type() {
            return parlay_type;
        }

        public void setParlay_type(String parlay_type) {
            this.parlay_type = parlay_type;
        }

        public String getParlay_data() {
            return parlay_data;
        }

        public void setParlay_data(String parlay_data) {
            this.parlay_data = parlay_data;
        }

        public String getBet_type() {
            return bet_type;
        }

        public void setBet_type(String bet_type) {
            this.bet_type = bet_type;
        }

        public String getBet_team() {
            return bet_team;
        }

        public void setBet_team(String bet_team) {
            this.bet_team = bet_team;
        }

        public String getHdp() {
            return hdp;
        }

        public void setHdp(String hdp) {
            this.hdp = hdp;
        }

        public String getBet_tag() {
            return bet_tag;
        }

        public void setBet_tag(String bet_tag) {
            this.bet_tag = bet_tag;
        }

        public String getLeague_id() {
            return league_id;
        }

        public void setLeague_id(String league_id) {
            this.league_id = league_id;
        }

        public String getMatch_id() {
            return match_id;
        }

        public void setMatch_id(String match_id) {
            this.match_id = match_id;
        }

        public String getOdds_type() {
            return odds_type;
        }

        public void setOdds_type(String odds_type) {
            this.odds_type = odds_type;
        }

        public String getIslive() {
            return islive;
        }

        public void setIslive(String islive) {
            this.islive = islive;
        }

        public String getHome_score() {
            return home_score;
        }

        public void setHome_score(String home_score) {
            this.home_score = home_score;
        }

        public String getAway_score() {
            return away_score;
        }

        public void setAway_score(String away_score) {
            this.away_score = away_score;
        }

        public String getAgentid() {
            return agentid;
        }

        public void setAgentid(String agentid) {
            this.agentid = agentid;
        }

        public String getHome_id() {
            return home_id;
        }

        public void setHome_id(String home_id) {
            this.home_id = home_id;
        }

        public String getAway_id() {
            return away_id;
        }

        public void setAway_id(String away_id) {
            this.away_id = away_id;
        }

        public String getParlay_ref_no() {
            return parlay_ref_no;
        }

        public void setParlay_ref_no(String parlay_ref_no) {
            this.parlay_ref_no = parlay_ref_no;
        }

        public String getIs_lucky() {
            return is_lucky;
        }

        public void setIs_lucky(String is_lucky) {
            this.is_lucky = is_lucky;
        }

        public String getCombo_type() {
            return combo_type;
        }

        public void setCombo_type(String combo_type) {
            this.combo_type = combo_type;
        }

        public String getTicket_status() {
            return ticket_status;
        }

        public void setTicket_status(String ticket_status) {
            this.ticket_status = ticket_status;
        }

        public String getOdds() {
            return odds;
        }

        public void setOdds(String odds) {
            this.odds = odds;
        }

        public String getAway_team_name_cn() {
            return away_team_name_cn;
        }

        public void setAway_team_name_cn(String away_team_name_cn) {
            this.away_team_name_cn = away_team_name_cn;
        }

        public String getAway_team_name() {
            return away_team_name;
        }

        public void setAway_team_name(String away_team_name) {
            this.away_team_name = away_team_name;
        }

        public String getHome_team_name_cn() {
            return home_team_name_cn;
        }

        public void setHome_team_name_cn(String home_team_name_cn) {
            this.home_team_name_cn = home_team_name_cn;
        }

        public String getHome_team_name() {
            return home_team_name;
        }

        public void setHome_team_name(String home_team_name) {
            this.home_team_name = home_team_name;
        }

        public String getLeague_name_cn() {
            return league_name_cn;
        }

        public void setLeague_name_cn(String league_name_cn) {
            this.league_name_cn = league_name_cn;
        }

        public String getLeague_name() {
            return league_name;
        }

        public void setLeague_name(String league_name) {
            this.league_name = league_name;
        }

        public String getBet_type_name() {
            return bet_type_name;
        }

        public void setBet_type_name(String bet_type_name) {
            this.bet_type_name = bet_type_name;
        }

        public String getBet_detail() {
            return bet_detail;
        }

        public void setBet_detail(String bet_detail) {
            this.bet_detail = bet_detail;
        }

        public String getSport_table_name() {
            return sport_table_name;
        }

        public void setSport_table_name(String sport_table_name) {
            this.sport_table_name = sport_table_name;
        }

        public String getOdds_type_name() {
            return odds_type_name;
        }

        public void setOdds_type_name(String odds_type_name) {
            this.odds_type_name = odds_type_name;
        }
    }
}
