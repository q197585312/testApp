package nanyang.com.dig88.NewKeno;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2018/9/14.
 */

public class NewKenoBetListBean {

    /**
     * code : 1
     * msg : 1
     * data : {"list":[{"last_game_id":"136980","period":"20180914213","open_time":"2018-09-14 14:12:01","GMT7":"1:12","result":"796|4+#1#1#4+#0#2-3#2-3#2-3|0#0#4#3|3-5|76-78|72#27#33#3#10#58#4#53#31#8#35#68#40#71#57#16#62#76#34#38"},{"last_game_id":"136979","period":"20180914212","open_time":"2018-09-14 14:08:01","GMT7":"1:08","result":"794|4+#1#2-3#2-3#2-3#2-3#2-3#2-3|0#0#4#3|1-2|79-80|15#55#65#22#68#42#36#49#8#4#21#2#31#75#45#80#34#54#79#9"},{"last_game_id":"136978","period":"20180914211","open_time":"2018-09-14 14:04:01","GMT7":"1:04","result":"786|2-3#2-3#2-3#2-3#4+#0#2-3#2-3|0#0#4#3|1-2|79-80|45#61#3#38#15#79#14#62#6#77#21#33#30#44#80#43#1#27#42#65"},{"last_game_id":"136977","period":"20180914210","open_time":"2018-09-14 14:00:01","GMT7":"1:00","result":"863|2-3#2-3#2-3#2-3#2-3#4+#2-3#2-3|1#1#1#4|1-2|79-80|1#9#73#33#55#26#24#16#53#54#62#42#31#71#80#43#49#14#57#70"},{"last_game_id":"136976","period":"20180914209","open_time":"2018-09-14 13:56:01","GMT7":"12:56","result":"796|2-3#2-3#2-3#4+#2-3#1#4+#0|0#0#4#3|3-5|<=75|26#55#42#64#27#40#69#63#33#36#35#18#9#44#70#17#4#68#62#14"},{"last_game_id":"136975","period":"20180914208","open_time":"2018-09-14 13:52:01","GMT7":"12:52","result":"947|2-3#0#2-3#1#2-3#2-3#2-3#4+|1#1#1#5|3-5|79-80|54#70#21#67#29#8#80#42#60#30#57#78#47#72#48#65#6#79#3#31"}],"balance":"428.70","outstanding":"0.00","period":null,"bet_list":[{"gid":"G136978","pid":"#136978","list":[{"betstr":"Odd","return_amount":"3x1.95","win_loss":0,"print":"461366#2018-09-14 1:01#sps1994#Odd#3X1.95#0#G136978","status":"1"},{"betstr":"[21-30]4+","return_amount":"2x4","win_loss":0,"print":"461363#2018-09-14 1:01#sps1994#[21-30]4+#2X4#0#G136978","status":"1"}]}]}
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
         * list : [{"last_game_id":"136980","period":"20180914213","open_time":"2018-09-14 14:12:01","GMT7":"1:12","result":"796|4+#1#1#4+#0#2-3#2-3#2-3|0#0#4#3|3-5|76-78|72#27#33#3#10#58#4#53#31#8#35#68#40#71#57#16#62#76#34#38"},{"last_game_id":"136979","period":"20180914212","open_time":"2018-09-14 14:08:01","GMT7":"1:08","result":"794|4+#1#2-3#2-3#2-3#2-3#2-3#2-3|0#0#4#3|1-2|79-80|15#55#65#22#68#42#36#49#8#4#21#2#31#75#45#80#34#54#79#9"},{"last_game_id":"136978","period":"20180914211","open_time":"2018-09-14 14:04:01","GMT7":"1:04","result":"786|2-3#2-3#2-3#2-3#4+#0#2-3#2-3|0#0#4#3|1-2|79-80|45#61#3#38#15#79#14#62#6#77#21#33#30#44#80#43#1#27#42#65"},{"last_game_id":"136977","period":"20180914210","open_time":"2018-09-14 14:00:01","GMT7":"1:00","result":"863|2-3#2-3#2-3#2-3#2-3#4+#2-3#2-3|1#1#1#4|1-2|79-80|1#9#73#33#55#26#24#16#53#54#62#42#31#71#80#43#49#14#57#70"},{"last_game_id":"136976","period":"20180914209","open_time":"2018-09-14 13:56:01","GMT7":"12:56","result":"796|2-3#2-3#2-3#4+#2-3#1#4+#0|0#0#4#3|3-5|<=75|26#55#42#64#27#40#69#63#33#36#35#18#9#44#70#17#4#68#62#14"},{"last_game_id":"136975","period":"20180914208","open_time":"2018-09-14 13:52:01","GMT7":"12:52","result":"947|2-3#0#2-3#1#2-3#2-3#2-3#4+|1#1#1#5|3-5|79-80|54#70#21#67#29#8#80#42#60#30#57#78#47#72#48#65#6#79#3#31"}]
         * balance : 428.70
         * outstanding : 0.00
         * period : null
         * bet_list : [{"gid":"G136978","pid":"#136978","list":[{"betstr":"Odd","return_amount":"3x1.95","win_loss":0,"print":"461366#2018-09-14 1:01#sps1994#Odd#3X1.95#0#G136978","status":"1"},{"betstr":"[21-30]4+","return_amount":"2x4","win_loss":0,"print":"461363#2018-09-14 1:01#sps1994#[21-30]4+#2X4#0#G136978","status":"1"}]}]
         */

        private String balance;
        private String outstanding;
        private Object period;
        private List<ListBean> list;
        private List<BetListBean> bet_list;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getOutstanding() {
            return outstanding;
        }

        public void setOutstanding(String outstanding) {
            this.outstanding = outstanding;
        }

        public Object getPeriod() {
            return period;
        }

        public void setPeriod(Object period) {
            this.period = period;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<BetListBean> getBet_list() {
            return bet_list;
        }

        public void setBet_list(List<BetListBean> bet_list) {
            this.bet_list = bet_list;
        }

        public static class ListBean {
            /**
             * last_game_id : 136980
             * period : 20180914213
             * open_time : 2018-09-14 14:12:01
             * GMT7 : 1:12
             * result : 796|4+#1#1#4+#0#2-3#2-3#2-3|0#0#4#3|3-5|76-78|72#27#33#3#10#58#4#53#31#8#35#68#40#71#57#16#62#76#34#38
             */

            private String last_game_id;
            private String period;
            private String open_time;
            private String GMT7;
            private String result;

            public String getLast_game_id() {
                return last_game_id;
            }

            public void setLast_game_id(String last_game_id) {
                this.last_game_id = last_game_id;
            }

            public String getPeriod() {
                return period;
            }

            public void setPeriod(String period) {
                this.period = period;
            }

            public String getOpen_time() {
                return open_time;
            }

            public void setOpen_time(String open_time) {
                this.open_time = open_time;
            }

            public String getGMT7() {
                return GMT7;
            }

            public void setGMT7(String GMT7) {
                this.GMT7 = GMT7;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }
        }

        public static class BetListBean {
            /**
             * gid : G136978
             * pid : #136978
             * list : [{"betstr":"Odd","return_amount":"3x1.95","win_loss":0,"print":"461366#2018-09-14 1:01#sps1994#Odd#3X1.95#0#G136978","status":"1"},{"betstr":"[21-30]4+","return_amount":"2x4","win_loss":0,"print":"461363#2018-09-14 1:01#sps1994#[21-30]4+#2X4#0#G136978","status":"1"}]
             */

            private String gid;
            private String pid;
            private List<ListBeanX> list;

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public List<ListBeanX> getList() {
                return list;
            }

            public void setList(List<ListBeanX> list) {
                this.list = list;
            }

            public static class ListBeanX {
                /**
                 * betstr : Odd
                 * return_amount : 3x1.95
                 * win_loss : 0
                 * print : 461366#2018-09-14 1:01#sps1994#Odd#3X1.95#0#G136978
                 * status : 1
                 */

                private String betstr;
                private String return_amount;
                private Object win_loss;
                private String print;
                private String status;

                public String getBetstr() {
                    return betstr;
                }

                public void setBetstr(String betstr) {
                    this.betstr = betstr;
                }

                public String getReturn_amount() {
                    return return_amount;
                }

                public void setReturn_amount(String return_amount) {
                    this.return_amount = return_amount;
                }

                public Object getWin_loss() {
                    return win_loss;
                }

                public void setWin_loss(Object win_loss) {
                    this.win_loss = win_loss;
                }

                public String getPrint() {
                    return print;
                }

                public void setPrint(String print) {
                    this.print = print;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }
        }
    }
}
