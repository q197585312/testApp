package nanyang.com.dig88.NewKeno;

/**
 * Created by Administrator on 2018/9/13.
 */

public class NewKenoStateBean {

    /**
     * code : 1
     * msg : 1
     * data : {"total_sec":234,"gid":"136613","period":"20180913207","result":"810|2-3#0#4+#4+#0#2-3#4+#1|0#0#4#3|3-5|<=75|5#30#62#4#39#21#36#58#32#70#63#23#59#9#37#74#38#25#57#68"}
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
         * total_sec : 234
         * gid : 136613
         * period : 20180913207
         * result : 810|2-3#0#4+#4+#0#2-3#4+#1|0#0#4#3|3-5|<=75|5#30#62#4#39#21#36#58#32#70#63#23#59#9#37#74#38#25#57#68
         */

        private int total_sec;
        private String gid;
        private String period;
        private String result;

        public int getTotal_sec() {
            return total_sec;
        }

        public void setTotal_sec(int total_sec) {
            this.total_sec = total_sec;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
