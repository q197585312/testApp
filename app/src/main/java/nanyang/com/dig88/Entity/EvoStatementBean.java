package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2019/12/3.
 */

public class EvoStatementBean {

    /**
     * code : 1
     * msg : 1
     * win_loss : 7.5
     * bet_amount : 190
     * data : [{"id_mod_bets_egames":"159540","web_id":"995","username":"sps1992","id_mod_member":"566867","currency_rate":"4","status":"1","bet_time":"2019-12-02 15:09:22","draw_time":"2019-12-02 15:09:29","bet_id":"15dc7c377ba694fa8c56d254","table_id":"Speed Baccarat A","game_type":"baccarat","game_id":"boxhtrupthk5wmxj","bet_detail":"Banker(50)","result":"{\"banker\":{\"score\":9,\"cards\":[\"2S\",\"7S\"]},\"player\":{\"score\":0,\"cards\":[\"KD\",\"KS\"]},\"outcome\":\"Banker\"}","bet_amount":"50","valid_amount":"50","win_loss":"47.5","ip":"47.74.255.88 - 3G - Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36-http:m.855kg.com/index.php?lang=en","trs_id":"575010246919875084","balance_before":"106.45","referrall":"0","referrall_update_time":"0000-00-00 00:00:00","referrall_amount":"0","referrall_status":"0","agent_id":"995"},{"id_mod_bets_egames":"159539","web_id":"995","username":"sps1992","id_mod_member":"566867","currency_rate":"4","status":"1","bet_time":"2019-12-02 15:09:48","draw_time":"2019-12-02 15:09:59","bet_id":"15dc7c3dbac8a25be26b908f","table_id":"Speed Baccarat A","game_type":"baccarat","game_id":"boxhtrupthk5wmxj","bet_detail":"Player(50)","result":"{\"banker\":{\"score\":1,\"cards\":[\"9H\",\"3C\",\"9S\"]},\"player\":{\"score\":7,\"cards\":[\"KH\",\"KD\",\"7D\"]},\"outcome\":\"Player\"}","bet_amount":"50","valid_amount":"50","win_loss":"50","ip":"47.74.255.88 - 3G - Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36-http:m.855kg.com/index.php?lang=en","trs_id":"575010246920121084","balance_before":"153.95","referrall":"0","referrall_update_time":"0000-00-00 00:00:00","referrall_amount":"0","referrall_status":"0","agent_id":"995"},{"id_mod_bets_egames":"159532","web_id":"995","username":"sps1992","id_mod_member":"566867","currency_rate":"4","status":"1","bet_time":"2019-12-02 15:08:55","draw_time":"2019-12-02 15:09:02","bet_id":"15dc7c312ebfbfd383bb73d0","table_id":"Speed Baccarat A","game_type":"baccarat","game_id":"boxhtrupthk5wmxj","bet_detail":"Player Pair(50)","result":"{\"banker\":{\"score\":3,\"cards\":[\"JC\",\"3H\"]},\"player\":{\"score\":9,\"cards\":[\"9C\",\"TS\"]},\"outcome\":\"Player\"}","bet_amount":"50","valid_amount":"50","win_loss":"-50","ip":"47.74.255.88 - 3G - Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36-http:m.855kg.com/index.php?lang=en","trs_id":"575010246919617084","balance_before":"156.45","referrall":"0","referrall_update_time":"0000-00-00 00:00:00","referrall_amount":"0","referrall_status":"0","agent_id":"995"},{"id_mod_bets_egames":"159509","web_id":"995","username":"sps1992","id_mod_member":"566867","currency_rate":"4","status":"1","bet_time":"2019-12-02 15:03:37","draw_time":"2019-12-02 15:04:00","bet_id":"15dc7be57e7010eb422f5d22","table_id":"Lightning Roulette","game_type":"roulette","game_id":"boxhtrupthk5wmxj","bet_detail":"23 Red(10)Split bet: 25 and 26(10)","result":"{\"outcomes\":[{\"number\":\"2\",\"type\":\"Even\",\"color\":\"Black\"}],\"luckyNumbers\":{\"21\":49,\"35\":99}}","bet_amount":"20","valid_amount":"20","win_loss":"-20","ip":"47.74.255.88 - 3G - Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36-http:m.855kg.com/index.php?lang=en","trs_id":"575012874801363355","balance_before":"196.45","referrall":"0","referrall_update_time":"0000-00-00 00:00:00","referrall_amount":"0","referrall_status":"0","agent_id":"995"},{"id_mod_bets_egames":"159508","web_id":"995","username":"sps1992","id_mod_member":"566867","currency_rate":"4","status":"1","bet_time":"2019-12-02 15:04:28","draw_time":"2019-12-02 15:04:55","bet_id":"15dc7bf164b64259ebc7b303","table_id":"Lightning Roulette","game_type":"roulette","game_id":"boxhtrupthk5wmxj","bet_detail":"17 Black(10)8 Black(10)","result":"{\"outcomes\":[{\"number\":\"10\",\"type\":\"Even\",\"color\":\"Black\"}],\"luckyNumbers\":{\"6\":99,\"5\":49,\"35\":99,\"29\":99}}","bet_amount":"20","valid_amount":"20","win_loss":"-20","ip":"47.74.255.88 - 3G - Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36-http:m.855kg.com/index.php?lang=en","trs_id":"575012874801794355","balance_before":"176.45","referrall":"0","referrall_update_time":"0000-00-00 00:00:00","referrall_amount":"0","referrall_status":"0","agent_id":"995"}]
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
         * id_mod_bets_egames : 159540
         * web_id : 995
         * username : sps1992
         * id_mod_member : 566867
         * currency_rate : 4
         * status : 1
         * bet_time : 2019-12-02 15:09:22
         * draw_time : 2019-12-02 15:09:29
         * bet_id : 15dc7c377ba694fa8c56d254
         * table_id : Speed Baccarat A
         * game_type : baccarat
         * game_id : boxhtrupthk5wmxj
         * bet_detail : Banker(50)
         * result : {"banker":{"score":9,"cards":["2S","7S"]},"player":{"score":0,"cards":["KD","KS"]},"outcome":"Banker"}
         * bet_amount : 50
         * valid_amount : 50
         * win_loss : 47.5
         * ip : 47.74.255.88 - 3G - Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36-http:m.855kg.com/index.php?lang=en
         * trs_id : 575010246919875084
         * balance_before : 106.45
         * referrall : 0
         * referrall_update_time : 0000-00-00 00:00:00
         * referrall_amount : 0
         * referrall_status : 0
         * agent_id : 995
         */

        private String id_mod_bets_egames;
        private String web_id;
        private String username;
        private String id_mod_member;
        private String currency_rate;
        private String status;
        private String bet_time;
        private String draw_time;
        private String bet_id;
        private String table_id;
        private String game_type;
        private String game_id;
        private String bet_detail;
        private String result;
        private String bet_amount;
        private String valid_amount;
        private String win_loss;
        private String ip;
        private String trs_id;
        private String balance_before;
        private String referrall;
        private String referrall_update_time;
        private String referrall_amount;
        private String referrall_status;
        private String agent_id;

        public String getId_mod_bets_egames() {
            return id_mod_bets_egames;
        }

        public void setId_mod_bets_egames(String id_mod_bets_egames) {
            this.id_mod_bets_egames = id_mod_bets_egames;
        }

        public String getWeb_id() {
            return web_id;
        }

        public void setWeb_id(String web_id) {
            this.web_id = web_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getId_mod_member() {
            return id_mod_member;
        }

        public void setId_mod_member(String id_mod_member) {
            this.id_mod_member = id_mod_member;
        }

        public String getCurrency_rate() {
            return currency_rate;
        }

        public void setCurrency_rate(String currency_rate) {
            this.currency_rate = currency_rate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }

        public String getDraw_time() {
            return draw_time;
        }

        public void setDraw_time(String draw_time) {
            this.draw_time = draw_time;
        }

        public String getBet_id() {
            return bet_id;
        }

        public void setBet_id(String bet_id) {
            this.bet_id = bet_id;
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

        public String getGame_id() {
            return game_id;
        }

        public void setGame_id(String game_id) {
            this.game_id = game_id;
        }

        public String getBet_detail() {
            return bet_detail;
        }

        public void setBet_detail(String bet_detail) {
            this.bet_detail = bet_detail;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getBet_amount() {
            return bet_amount;
        }

        public void setBet_amount(String bet_amount) {
            this.bet_amount = bet_amount;
        }

        public String getValid_amount() {
            return valid_amount;
        }

        public void setValid_amount(String valid_amount) {
            this.valid_amount = valid_amount;
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

        public String getTrs_id() {
            return trs_id;
        }

        public void setTrs_id(String trs_id) {
            this.trs_id = trs_id;
        }

        public String getBalance_before() {
            return balance_before;
        }

        public void setBalance_before(String balance_before) {
            this.balance_before = balance_before;
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
