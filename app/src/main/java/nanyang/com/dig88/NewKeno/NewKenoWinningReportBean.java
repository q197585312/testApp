package nanyang.com.dig88.NewKeno;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2018/9/21.
 */

public class NewKenoWinningReportBean {

    /**
     * code : 1
     * msg : 1
     * data : {"result":[{"id_mod_bets_lottery":"443070","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"Frequence#3#4+","amount":"1","factor":"4","win_loss":"0","return_amount":4},{"id_mod_bets_lottery":"443069","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"Even","amount":"1","factor":"1.95","win_loss":"1.95","return_amount":1.95},{"id_mod_bets_lottery":"443068","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"<=75","amount":"1","factor":"3.8","win_loss":"0","return_amount":3.8},{"id_mod_bets_lottery":"443066","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"OverEven","amount":"1","factor":"3.7","win_loss":"0","return_amount":3.7},{"id_mod_bets_lottery":"443064","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"Frequence#5#4+","amount":"2","factor":"4","win_loss":"0","return_amount":8},{"id_mod_bets_lottery":"443062","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"Frequence#3#2-3","amount":"1","factor":"1.6","win_loss":"1.6","return_amount":1.6}],"total_bet":7,"total_return_amount":23.05,"total_page":1}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * result : [{"id_mod_bets_lottery":"443070","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"Frequence#3#4+","amount":"1","factor":"4","win_loss":"0","return_amount":4},{"id_mod_bets_lottery":"443069","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"Even","amount":"1","factor":"1.95","win_loss":"1.95","return_amount":1.95},{"id_mod_bets_lottery":"443068","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"<=75","amount":"1","factor":"3.8","win_loss":"0","return_amount":3.8},{"id_mod_bets_lottery":"443066","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"OverEven","amount":"1","factor":"3.7","win_loss":"0","return_amount":3.7},{"id_mod_bets_lottery":"443064","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"Frequence#5#4+","amount":"2","factor":"4","win_loss":"0","return_amount":8},{"id_mod_bets_lottery":"443062","id_mod_member":"528273","bet_time":"2018-09-10 5:14","ticket":"G#20180910 5:16","total_result":"790","bet_detail":"Frequence#3#2-3","amount":"1","factor":"1.6","win_loss":"1.6","return_amount":1.6}]
         * total_bet : 7
         * total_return_amount : 23.05
         * total_page : 1
         */

        private double total_bet;
        private double total_return_amount;
        private int total_page;
        private List<ResultBean> result;

        public double getTotal_bet() {
            return total_bet;
        }

        public void setTotal_bet(double total_bet) {
            this.total_bet = total_bet;
        }

        public double getTotal_return_amount() {
            return total_return_amount;
        }

        public void setTotal_return_amount(double total_return_amount) {
            this.total_return_amount = total_return_amount;
        }

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * id_mod_bets_lottery : 443070
             * id_mod_member : 528273
             * bet_time : 2018-09-10 5:14
             * ticket : G#20180910 5:16
             * total_result : 790
             * bet_detail : Frequence#3#4+
             * amount : 1
             * factor : 4
             * win_loss : 0
             * return_amount : 4
             */

            private String id_mod_bets;
            private String id_mod_member;
            private String bet_time;
            private String ticket;
            private String total_result;
            private String bet_detail;
            private String amount;
            private String factor;
            private String win_loss;
            private double return_amount;

            public String getId_mod_bets() {
                return id_mod_bets;
            }

            public void setId_id_mod_bets(String id_mod_bets) {
                this.id_mod_bets = id_mod_bets;
            }

            public String getId_mod_member() {
                return id_mod_member;
            }

            public void setId_mod_member(String id_mod_member) {
                this.id_mod_member = id_mod_member;
            }

            public String getBet_time() {
                return bet_time;
            }

            public void setBet_time(String bet_time) {
                this.bet_time = bet_time;
            }

            public String getTicket() {
                return ticket;
            }

            public void setTicket(String ticket) {
                this.ticket = ticket;
            }

            public String getTotal_result() {
                return total_result;
            }

            public void setTotal_result(String total_result) {
                this.total_result = total_result;
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

            public String getFactor() {
                return factor;
            }

            public void setFactor(String factor) {
                this.factor = factor;
            }

            public String getWin_loss() {
                return win_loss;
            }

            public void setWin_loss(String win_loss) {
                this.win_loss = win_loss;
            }

            public double getReturn_amount() {
                return return_amount;
            }

            public void setReturn_amount(double return_amount) {
                this.return_amount = return_amount;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ResultBean that = (ResultBean) o;
                return Double.compare(that.return_amount, return_amount) == 0 &&
                        Objects.equals(id_mod_bets, that.id_mod_bets) &&
                        Objects.equals(id_mod_member, that.id_mod_member) &&
                        Objects.equals(bet_time, that.bet_time) &&
                        Objects.equals(ticket, that.ticket) &&
                        Objects.equals(total_result, that.total_result) &&
                        Objects.equals(bet_detail, that.bet_detail) &&
                        Objects.equals(amount, that.amount) &&
                        Objects.equals(factor, that.factor) &&
                        Objects.equals(win_loss, that.win_loss);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id_mod_bets, id_mod_member, bet_time, ticket, total_result, bet_detail, amount, factor, win_loss, return_amount);
            }
        }
    }
}
