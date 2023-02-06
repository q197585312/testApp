package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class AgStatementBean {

    /**
     * code : 1
     * msg : 1
     * win_loss : 200
     * data : [{"gameid":"GB021181090CF","gametype":"DT","tableid":"B021","bet_amount":"50","bet_time":"2018-01-09 05:37:18","win_loss":"400","play_type":"23","platType":"AGIN","agentCode":"F21","status":"WIN"},{"gameid":"GC002181090D9","gametype":"BAC","tableid":"C002","bet_amount":"100","bet_time":"2018-01-09 05:37:06","win_loss":"-100","play_type":"4","platType":"AGIN","agentCode":"F21","status":"LOSE"},{"gameid":"GC00118109038","gametype":"BAC","tableid":"C001","bet_amount":"100","bet_time":"2018-01-09 02:37:38","win_loss":"-100","play_type":"2","platType":"AGIN","agentCode":"F21","status":"LOSE"}]
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
         * gameid : GB021181090CF
         * gametype : DT
         * tableid : B021
         * bet_amount : 50
         * bet_time : 2018-01-09 05:37:18
         * win_loss : 400
         * play_type : 23
         * platType : AGIN
         * agentCode : F21
         * status : WIN
         */

        private String gameid;
        private String gametype;
        private String tableid;
        private String bet_amount;
        private String bet_time;
        private String win_loss;
        private String play_type;
        private String platType;
        private String agentCode;
        private String status;

        public String getGameid() {
            return gameid;
        }

        public void setGameid(String gameid) {
            this.gameid = gameid;
        }

        public String getGametype() {
            return gametype;
        }

        public void setGametype(String gametype) {
            this.gametype = gametype;
        }

        public String getTableid() {
            return tableid;
        }

        public void setTableid(String tableid) {
            this.tableid = tableid;
        }

        public String getBet_amount() {
            return bet_amount;
        }

        public void setBet_amount(String bet_amount) {
            this.bet_amount = bet_amount;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }

        public String getWin_loss() {
            return win_loss;
        }

        public void setWin_loss(String win_loss) {
            this.win_loss = win_loss;
        }

        public String getPlay_type() {
            return play_type;
        }

        public void setPlay_type(String play_type) {
            this.play_type = play_type;
        }

        public String getPlatType() {
            return platType;
        }

        public void setPlatType(String platType) {
            this.platType = platType;
        }

        public String getAgentCode() {
            return agentCode;
        }

        public void setAgentCode(String agentCode) {
            this.agentCode = agentCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
