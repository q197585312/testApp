package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2019/7/24.
 */

public class SexyCasinoStatementBean {

    /**
     * code : 1
     * msg : 1
     * win_loss : 47.5
     * data : [{"id_mod_bets_casino_sexy":"11951807","web_id":"995","id_mod_member":"718071","username":"leonew1","status":"1","type":"WIN","bet_time":"2019-07-10 16:00:28","update_time":"2019-07-10 16:00:58","round_id":"Mexico-03-GA193060017","round_start_time":"2019-07-10 16:00:15","table_id":"3","game_type":"Baccarat","bet_amount":"50","win_loss":"47.5","settle_win_loss":"97.5","ip":"203.95.198.148-android/app","result":"[\"S05\",\"C07\",\"H08\",\"H11\",\"C11\",\"D06\"]","transfer_id":"BAC-1057068822","odds":"0.95","category":"Banker","balance_before":"1684.7","currency_rate":"4","referrall":"0","referrall_update_time":"2019-07-10 16:00:28","referrall_amount":"0","referrall_status":"0","agent_id":"995"}]
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
         * id_mod_bets_casino_sexy : 11951807
         * web_id : 995
         * id_mod_member : 718071
         * username : leonew1
         * status : 1
         * type : WIN
         * bet_time : 2019-07-10 16:00:28
         * update_time : 2019-07-10 16:00:58
         * round_id : Mexico-03-GA193060017
         * round_start_time : 2019-07-10 16:00:15
         * table_id : 3
         * game_type : Baccarat
         * bet_amount : 50
         * win_loss : 47.5
         * settle_win_loss : 97.5
         * ip : 203.95.198.148-android/app
         * result : ["S05","C07","H08","H11","C11","D06"]
         * transfer_id : BAC-1057068822
         * odds : 0.95
         * category : Banker
         * balance_before : 1684.7
         * currency_rate : 4
         * referrall : 0
         * referrall_update_time : 2019-07-10 16:00:28
         * referrall_amount : 0
         * referrall_status : 0
         * agent_id : 995
         */

        private String id_mod_bets_casino_sexy;
        private String web_id;
        private String id_mod_member;
        private String username;
        private String status;
        private String type;
        private String bet_time;
        private String update_time;
        private String round_id;
        private String round_start_time;
        private String table_id;
        private String game_type;
        private String bet_amount;
        private String win_loss;
        private String settle_win_loss;
        private String ip;
        private String result;
        private String transfer_id;
        private String odds;
        private String category;
        private String balance_before;
        private String currency_rate;
        private String referrall;
        private String referrall_update_time;
        private String referrall_amount;
        private String referrall_status;
        private String agent_id;

        public String getId_mod_bets_casino_sexy() {
            return id_mod_bets_casino_sexy;
        }

        public void setId_mod_bets_casino_sexy(String id_mod_bets_casino_sexy) {
            this.id_mod_bets_casino_sexy = id_mod_bets_casino_sexy;
        }

        public String getWeb_id() {
            return web_id;
        }

        public void setWeb_id(String web_id) {
            this.web_id = web_id;
        }

        public String getId_mod_member() {
            return id_mod_member;
        }

        public void setId_mod_member(String id_mod_member) {
            this.id_mod_member = id_mod_member;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getRound_id() {
            return round_id;
        }

        public void setRound_id(String round_id) {
            this.round_id = round_id;
        }

        public String getRound_start_time() {
            return round_start_time;
        }

        public void setRound_start_time(String round_start_time) {
            this.round_start_time = round_start_time;
        }

        public String getTable_id() {
            return table_id;
        }

        public void setTable_id(String table_id) {
            this.table_id = table_id;
        }

        public String getGame_type() {
            return game_type;
        }

        public void setGame_type(String game_type) {
            this.game_type = game_type;
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

        public String getSettle_win_loss() {
            return settle_win_loss;
        }

        public void setSettle_win_loss(String settle_win_loss) {
            this.settle_win_loss = settle_win_loss;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getTransfer_id() {
            return transfer_id;
        }

        public void setTransfer_id(String transfer_id) {
            this.transfer_id = transfer_id;
        }

        public String getOdds() {
            return odds;
        }

        public void setOdds(String odds) {
            this.odds = odds;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getBalance_before() {
            return balance_before;
        }

        public void setBalance_before(String balance_before) {
            this.balance_before = balance_before;
        }

        public String getCurrency_rate() {
            return currency_rate;
        }

        public void setCurrency_rate(String currency_rate) {
            this.currency_rate = currency_rate;
        }

        public String getReferrall() {
            return referrall;
        }

        public void setReferrall(String referrall) {
            this.referrall = referrall;
        }

        public String getReferrall_update_time() {
            return referrall_update_time;
        }

        public void setReferrall_update_time(String referrall_update_time) {
            this.referrall_update_time = referrall_update_time;
        }

        public String getReferrall_amount() {
            return referrall_amount;
        }

        public void setReferrall_amount(String referrall_amount) {
            this.referrall_amount = referrall_amount;
        }

        public String getReferrall_status() {
            return referrall_status;
        }

        public void setReferrall_status(String referrall_status) {
            this.referrall_status = referrall_status;
        }

        public String getAgent_id() {
            return agent_id;
        }

        public void setAgent_id(String agent_id) {
            this.agent_id = agent_id;
        }
    }
}
