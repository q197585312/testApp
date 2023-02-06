package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2019/11/7.
 */

public class PPlayStatementBean {

    /**
     * code : 1
     * msg : 1
     * win_loss : -60
     * data : [{"bet_amount":"30","result_win_loss":"30","bet_time":"2019-11-06 17:03:27","trs_id":"5dc28c5f5f1503000af496c5","gameid":"vs10firestrike","providerId":"PragmaticPlay"},{"bet_amount":"30","result_win_loss":"-30","bet_time":"2019-11-06 17:03:22","trs_id":"5dc28c5a5f1503000af493e5","gameid":"vs10firestrike","providerId":"PragmaticPlay"},{"bet_amount":"30","result_win_loss":"-15","bet_time":"2019-11-06 17:03:13","trs_id":"5dc28c510c76920008c6cabf","gameid":"vs10firestrike","providerId":"PragmaticPlay"},{"bet_amount":"30","result_win_loss":"-30","bet_time":"2019-11-06 17:03:09","trs_id":"5dc28c4d0c76920008c6c8a2","gameid":"vs10firestrike","providerId":"PragmaticPlay"},{"bet_amount":"30","result_win_loss":"-30","bet_time":"2019-11-06 17:03:05","trs_id":"5dc28c495f1503000af48b3e","gameid":"vs10firestrike","providerId":"PragmaticPlay"},{"bet_amount":"30","result_win_loss":"60","bet_time":"2019-11-06 17:03:00","trs_id":"5dc28c445f1503000af4886e","gameid":"vs10firestrike","providerId":"PragmaticPlay"},{"bet_amount":"30","result_win_loss":"-30","bet_time":"2019-11-06 17:02:54","trs_id":"5dc28c3e0c76920008c6c130","gameid":"vs10firestrike","providerId":"PragmaticPlay"},{"bet_amount":"30","result_win_loss":"-30","bet_time":"2019-11-06 17:02:50","trs_id":"5dc28c3a5f1503000af483bb","gameid":"vs10firestrike","providerId":"PragmaticPlay"},{"bet_amount":"30","result_win_loss":"15","bet_time":"2019-11-06 17:02:46","trs_id":"5dc28c365f1503000af48158","gameid":"vs10firestrike","providerId":"PragmaticPlay"}]
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
         * bet_amount : 30
         * result_win_loss : 30
         * bet_time : 2019-11-06 17:03:27
         * trs_id : 5dc28c5f5f1503000af496c5
         * gameid : vs10firestrike
         * providerId : PragmaticPlay
         */

        private String bet_amount;
        private String result_win_loss;
        private String bet_time;
        private String trs_id;
        private String gameid;
        private String providerId;

        public String getBet_amount() {
            return bet_amount;
        }

        public void setBet_amount(String bet_amount) {
            this.bet_amount = bet_amount;
        }

        public String getResult_win_loss() {
            return result_win_loss;
        }

        public void setResult_win_loss(String result_win_loss) {
            this.result_win_loss = result_win_loss;
        }

        public String getBet_time() {
            return bet_time;
        }

        public void setBet_time(String bet_time) {
            this.bet_time = bet_time;
        }

        public String getTrs_id() {
            return trs_id;
        }

        public void setTrs_id(String trs_id) {
            this.trs_id = trs_id;
        }

        public String getGameid() {
            return gameid;
        }

        public void setGameid(String gameid) {
            this.gameid = gameid;
        }

        public String getProviderId() {
            return providerId;
        }

        public void setProviderId(String providerId) {
            this.providerId = providerId;
        }
    }
}
