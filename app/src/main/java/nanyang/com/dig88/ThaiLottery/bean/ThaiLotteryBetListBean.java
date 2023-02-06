package nanyang.com.dig88.ThaiLottery.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/29.
 */

public class ThaiLotteryBetListBean {

    /**
     * code : 1
     * msg : 1
     * data : [{"id_mod_bets":"80764258","bet_time":"2018-08-28 12:26:43","period":"20180817001","type1":"14","type2":"97","type3":"79","play_type":"3D Infront","factor":"250","factor2":"0","factor3":"0","factor4":"0","discount":"33","bet_amount":"3.35","number":"349","rate":"250","game_name":"THAI LOTTERY","pool":"THAILAND"},{"id_mod_bets":"80764257","bet_time":"2018-08-28 12:26:43","period":"20180817001","type1":"14","type2":"97","type3":"76","play_type":"3D Top","factor":"550","factor2":"0","factor3":"0","factor4":"0","discount":"33","bet_amount":"3.35","number":"349","rate":"550","game_name":"THAI LOTTERY","pool":"THAILAND"}]
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
         * id_mod_bets : 80764258
         * bet_time : 2018-08-28 12:26:43
         * period : 20180817001
         * type1 : 14
         * type2 : 97
         * type3 : 79
         * play_type : 3D Infront
         * factor : 250
         * factor2 : 0
         * factor3 : 0
         * factor4 : 0
         * discount : 33
         * bet_amount : 3.35
         * number : 349
         * rate : 250
         * game_name : THAI LOTTERY
         * pool : THAILAND
         */

        private String id_mod_bets;
        private String bet_time;
        private String period;
        private String type1;
        private String type2;
        private String type3;
        private String play_type;
        private String factor;
        private String factor2;
        private String factor3;
        private String factor4;
        private String discount;
        private String bet_amount;
        private String number;
        private String rate;
        private String game_name;
        private String pool;

        public String getId_mod_bets() {
            return id_mod_bets;
        }

        public void setId_mod_bets(String id_mod_bets) {
            this.id_mod_bets = id_mod_bets;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
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

        public String getPlay_type() {
            return play_type;
        }

        public void setPlay_type(String play_type) {
            this.play_type = play_type;
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

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getBet_amount() {
            return bet_amount;
        }

        public void setBet_amount(String bet_amount) {
            this.bet_amount = bet_amount;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getPool() {
            return pool;
        }

        public void setPool(String pool) {
            this.pool = pool;
        }
    }
}
