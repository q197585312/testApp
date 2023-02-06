package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public class PtStatementBean {


    /**
     * code : 1
     * msg : 1
     * win_loss : -3
     * data : [{"bet_type":"DRAW","gamename":"PT SLOTS","bet":"0.6","gamedate":"2019-07-10 18:15:30","gameid":"1562753730232399008"},{"bet_type":"BET","gamename":"PT SLOTS","bet":"-1.8","gamedate":"2019-07-10 18:15:30","gameid":"1562753730232399008"},{"bet_type":"DRAW","gamename":"PT SLOTS","bet":"0","gamedate":"2019-07-10 18:15:26","gameid":"1562753726232386008"},{"bet_type":"BET","gamename":"PT SLOTS","bet":"-1.8","gamedate":"2019-07-10 18:15:26","gameid":"1562753726232386008"}]
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
         * bet_type : DRAW
         * gamename : PT SLOTS
         * bet : 0.6
         * gamedate : 2019-07-10 18:15:30
         * gameid : 1562753730232399008
         */

        private String bet_type;
        private String gamename;
        private String bet;
        private String gamedate;
        private String gameid;

        public String getBet_type() {
            return bet_type;
        }

        public void setBet_type(String bet_type) {
            this.bet_type = bet_type;
        }

        public String getGamename() {
            return gamename;
        }

        public void setGamename(String gamename) {
            this.gamename = gamename;
        }

        public String getBet() {
            return bet;
        }

        public void setBet(String bet) {
            this.bet = bet;
        }

        public String getGamedate() {
            return gamedate;
        }

        public void setGamedate(String gamedate) {
            this.gamedate = gamedate;
        }

        public String getGameid() {
            return gameid;
        }

        public void setGameid(String gameid) {
            this.gameid = gameid;
        }
    }
}
