package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public class HabaStatementBean {

    /**
     * code : 1
     * msg : 1
     * win_loss : -92
     * data : [{"amount":"0","bet_time":"2018-01-10 01:59:09","game_no":"5325203724","trs_type":"DRAW","gameid":"00fa00bc-1c69-4e9e-b6c1-df5161ffa9d8","winorloss":"0","gamename":"SGTheKoiGate"},{"amount":"36","bet_time":"2018-01-10 01:59:09","game_no":"5325203724","trs_type":"BET","gameid":"00fa00bc-1c69-4e9e-b6c1-df5161ffa9d8","winorloss":"-36","gamename":"SGTheKoiGate"},{"amount":"0","bet_time":"2018-01-10 01:59:03","game_no":"5325202966","trs_type":"DRAW","gameid":"00fa00bc-1c69-4e9e-b6c1-df5161ffa9d8","winorloss":"0","gamename":"SGTheKoiGate"},{"amount":"36","bet_time":"2018-01-10 01:59:02","game_no":"5325202966","trs_type":"BET","gameid":"00fa00bc-1c69-4e9e-b6c1-df5161ffa9d8","winorloss":"-36","gamename":"SGTheKoiGate"},{"amount":"0","bet_time":"2018-01-10 01:56:08","game_no":"5325184521","trs_type":"DRAW","gameid":"e8ce08d5-db2a-4c40-ab08-e31cb1184f37","winorloss":"18","gamename":"BonusPoker5Hand"},{"amount":"10","bet_time":"2018-01-10 01:56:08","game_no":"5325184521","trs_type":"BET","gameid":"e8ce08d5-db2a-4c40-ab08-e31cb1184f37","winorloss":"-10","gamename":"BonusPoker5Hand"},{"amount":"0","bet_time":"2018-01-10 01:56:02","game_no":"5325183909","trs_type":"DRAW","gameid":"e8ce08d5-db2a-4c40-ab08-e31cb1184f37","winorloss":"16","gamename":"BonusPoker5Hand"},{"amount":"10","bet_time":"2018-01-10 01:56:01","game_no":"5325183909","trs_type":"BET","gameid":"e8ce08d5-db2a-4c40-ab08-e31cb1184f37","winorloss":"-10","gamename":"BonusPoker5Hand"},{"amount":"0","bet_time":"2018-01-09 10:00:09","game_no":"5316124311","trs_type":"DRAW","gameid":"2f636012-a47a-454b-80da-0a412df93a85","winorloss":"10","gamename":"TensorBetter5Hand"},{"amount":"10","bet_time":"2018-01-09 10:00:09","game_no":"5316124311","trs_type":"BET","gameid":"2f636012-a47a-454b-80da-0a412df93a85","winorloss":"-10","gamename":"TensorBetter5Hand"},{"amount":"0","bet_time":"2018-01-09 10:00:04","game_no":"5316123213","trs_type":"DRAW","gameid":"2f636012-a47a-454b-80da-0a412df93a85","winorloss":"2","gamename":"TensorBetter5Hand"},{"amount":"10","bet_time":"2018-01-09 10:00:03","game_no":"5316123213","trs_type":"BET","gameid":"2f636012-a47a-454b-80da-0a412df93a85","winorloss":"-10","gamename":"TensorBetter5Hand"},{"amount":"0","bet_time":"2018-01-09 09:59:58","game_no":"5316122145","trs_type":"DRAW","gameid":"2f636012-a47a-454b-80da-0a412df93a85","winorloss":"2","gamename":"TensorBetter5Hand"},{"amount":"10","bet_time":"2018-01-09 09:59:58","game_no":"5316122145","trs_type":"BET","gameid":"2f636012-a47a-454b-80da-0a412df93a85","winorloss":"-10","gamename":"TensorBetter5Hand"},{"amount":"0","bet_time":"2018-01-09 09:59:43","game_no":"5316119092","trs_type":"DRAW","gameid":"07961504-e8d3-407b-bafe-661cc5c1fa60","winorloss":"3","gamename":"AcesandEights10Hand"},{"amount":"10","bet_time":"2018-01-09 09:59:42","game_no":"5316119092","trs_type":"BET","gameid":"07961504-e8d3-407b-bafe-661cc5c1fa60","winorloss":"-10","gamename":"AcesandEights10Hand"},{"amount":"0","bet_time":"2018-01-09 09:59:37","game_no":"5316117999","trs_type":"DRAW","gameid":"07961504-e8d3-407b-bafe-661cc5c1fa60","winorloss":"8","gamename":"AcesandEights10Hand"},{"amount":"10","bet_time":"2018-01-09 09:59:36","game_no":"5316117999","trs_type":"BET","gameid":"07961504-e8d3-407b-bafe-661cc5c1fa60","winorloss":"-10","gamename":"AcesandEights10Hand"},{"amount":"0","bet_time":"2018-01-09 09:59:31","game_no":"5316116855","trs_type":"DRAW","gameid":"07961504-e8d3-407b-bafe-661cc5c1fa60","winorloss":"1","gamename":"AcesandEights10Hand"},{"amount":"10","bet_time":"2018-01-09 09:59:31","game_no":"5316116855","trs_type":"BET","gameid":"07961504-e8d3-407b-bafe-661cc5c1fa60","winorloss":"-10","gamename":"AcesandEights10Hand"}]
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
         * amount : 0
         * bet_time : 2018-01-10 01:59:09
         * game_no : 5325203724
         * trs_type : DRAW
         * gameid : 00fa00bc-1c69-4e9e-b6c1-df5161ffa9d8
         * winorloss : 0
         * gamename : SGTheKoiGate
         */

        private String amount;
        private String bet_time;
        private String game_no;
        private String trs_type;
        private String gameid;
        private String winorloss;
        private String gamename;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }

        public String getGame_no() {
            return game_no;
        }

        public void setGame_no(String game_no) {
            this.game_no = game_no;
        }

        public String getTrs_type() {
            return trs_type;
        }

        public void setTrs_type(String trs_type) {
            this.trs_type = trs_type;
        }

        public String getGameid() {
            return gameid;
        }

        public void setGameid(String gameid) {
            this.gameid = gameid;
        }

        public String getWinorloss() {
            return winorloss;
        }

        public void setWinorloss(String winorloss) {
            this.winorloss = winorloss;
        }

        public String getGamename() {
            return gamename;
        }

        public void setGamename(String gamename) {
            this.gamename = gamename;
        }
    }
}
