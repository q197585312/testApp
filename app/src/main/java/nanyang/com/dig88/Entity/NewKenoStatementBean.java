package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2020/2/4.
 */

public class NewKenoStatementBean {

    /**
     * code : 1
     * msg : 1
     * win_loss : 0
     * total_bet : 2
     * data : [{"id_mod_bets_lottery":"5707340","web_id":"4","provider":"1","type1":"17","type2":"167","type3":"94","id_mod_member":"564338","balance_before":"41.28000000000002","bet_time":"2020-02-01 03:54:50","bet_detail":"Over","amount":"1","discount":"0","kei":"0","factor":"1.95","factor2":"0","factor3":"0","factor4":"0","factor5":"4256525","dis_amount":"0","kei_amount":"0","bet_amount":"1","status":"1","result_time":"2020-02-01 03:56:06","result":"Under","agent_id":"4","win_loss":"0","referrall":"0","referrall_update_time":"0000-00-00 00:00:00","referrall_amount":"0","referrall_status":"0","period":"20200201059","currency_rate":"1","ip":"36.37.174.245"},{"id_mod_bets_lottery":"5707339","web_id":"4","provider":"1","type1":"17","type2":"167","type3":"97","id_mod_member":"564338","balance_before":"42.28000000000002","bet_time":"2020-02-01 03:54:46","bet_detail":"1st","amount":"1","discount":"0","kei":"0","factor":"9.2","factor2":"0","factor3":"0","factor4":"0","factor5":"4256524","dis_amount":"0","kei_amount":"0","bet_amount":"1","status":"1","result_time":"2020-02-01 03:56:06","result":"3rd","agent_id":"4","win_loss":"0","referrall":"0","referrall_update_time":"0000-00-00 00:00:00","referrall_amount":"0","referrall_status":"0","period":"20200201059","currency_rate":"1","ip":"36.37.174.245"}]
     */

    private String code;
    private String msg;
    private String win_loss;
    private String total_bet;
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

    public String getTotal_bet() {
        return total_bet;
    }

    public void setTotal_bet(String total_bet) {
        this.total_bet = total_bet;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id_mod_bets_lottery : 5707340
         * web_id : 4
         * provider : 1
         * type1 : 17
         * type2 : 167
         * type3 : 94
         * id_mod_member : 564338
         * balance_before : 41.28000000000002
         * bet_time : 2020-02-01 03:54:50
         * bet_detail : Over
         * amount : 1
         * discount : 0
         * kei : 0
         * factor : 1.95
         * factor2 : 0
         * factor3 : 0
         * factor4 : 0
         * factor5 : 4256525
         * dis_amount : 0
         * kei_amount : 0
         * bet_amount : 1
         * status : 1
         * result_time : 2020-02-01 03:56:06
         * result : Under
         * agent_id : 4
         * win_loss : 0
         * referrall : 0
         * referrall_update_time : 0000-00-00 00:00:00
         * referrall_amount : 0
         * referrall_status : 0
         * period : 20200201059
         * currency_rate : 1
         * ip : 36.37.174.245
         */

        private String id_mod_bets_lottery;
        private String web_id;
        private String provider;
        private String type1;
        private String type2;
        private String type3;
        private String id_mod_member;
        private String balance_before;
        private String bet_time;
        private String bet_detail;
        private String amount;
        private String discount;
        private String kei;
        private String factor;
        private String factor2;
        private String factor3;
        private String factor4;
        private String factor5;
        private String dis_amount;
        private String kei_amount;
        private String bet_amount;
        private String status;
        private String result_time;
        private String result;
        private String agent_id;
        private String win_loss;
        private String referrall;
        private String referrall_update_time;
        private String referrall_amount;
        private String referrall_status;
        private String period;
        private String currency_rate;
        private String ip;

        public String getId_mod_bets_lottery() {
            return id_mod_bets_lottery;
        }

        public void setId_mod_bets_lottery(String id_mod_bets_lottery) {
            this.id_mod_bets_lottery = id_mod_bets_lottery;
        }

        public String getWeb_id() {
            return web_id;
        }

        public void setWeb_id(String web_id) {
            this.web_id = web_id;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public String getType3() {
            return type3;
        }

        public void setType3(String type3) {
            this.type3 = type3;
        }

        public String getId_mod_member() {
            return id_mod_member;
        }

        public void setId_mod_member(String id_mod_member) {
            this.id_mod_member = id_mod_member;
        }

        public String getBalance_before() {
            return balance_before;
        }

        public void setBalance_before(String balance_before) {
            this.balance_before = balance_before;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }

        public String getBet_detail() {
            return bet_detail;
        }

        public void setBet_detail(String bet_detail) {
            this.bet_detail = bet_detail;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getKei() {
            return kei;
        }

        public void setKei(String kei) {
            this.kei = kei;
        }

        public String getFactor() {
            return factor;
        }

        public void setFactor(String factor) {
            this.factor = factor;
        }

        public String getFactor2() {
            return factor2;
        }

        public void setFactor2(String factor2) {
            this.factor2 = factor2;
        }

        public String getFactor3() {
            return factor3;
        }

        public void setFactor3(String factor3) {
            this.factor3 = factor3;
        }

        public String getFactor4() {
            return factor4;
        }

        public void setFactor4(String factor4) {
            this.factor4 = factor4;
        }

        public String getFactor5() {
            return factor5;
        }

        public void setFactor5(String factor5) {
            this.factor5 = factor5;
        }

        public String getDis_amount() {
            return dis_amount;
        }

        public void setDis_amount(String dis_amount) {
            this.dis_amount = dis_amount;
        }

        public String getKei_amount() {
            return kei_amount;
        }

        public void setKei_amount(String kei_amount) {
            this.kei_amount = kei_amount;
        }

        public String getBet_amount() {
            return bet_amount;
        }

        public void setBet_amount(String bet_amount) {
            this.bet_amount = bet_amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getResult_time() {
            return result_time;
        }

        public void setResult_time(String result_time) {
            this.result_time = result_time;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getAgent_id() {
            return agent_id;
        }

        public void setAgent_id(String agent_id) {
            this.agent_id = agent_id;
        }

        public String getWin_loss() {
            return win_loss;
        }

        public void setWin_loss(String win_loss) {
            this.win_loss = win_loss;
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

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getCurrency_rate() {
            return currency_rate;
        }

        public void setCurrency_rate(String currency_rate) {
            this.currency_rate = currency_rate;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }
}
