package nanyang.com.dig88.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/8/2.
 */

public class AutoPromotionBean {

    /**
     * code : 1
     * msg : 1
     * data : [{"0":"2018-07-23 19:37:15","date_time":"2018-07-23 19:37:15","1":"0.054","type2":"0.054","2":"211","parameter1":"211","3":"Auto Pomotion Turnover(W88 SLOTS)","remark":"Auto Pomotion Turnover(W88 SLOTS)"},{"0":"2018-07-23 19:37:46","date_time":"2018-07-23 19:37:46","1":"11.2952","type2":"11.2952","2":"15","parameter1":"15","3":"Auto Pomotion Turnover(BESTGAMER SLOTS)","remark":"Auto Pomotion Turnover(BESTGAMER SLOTS)"}]
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
         * 0 : 2018-07-23 19:37:15
         * date_time : 2018-07-23 19:37:15
         * 1 : 0.054
         * type2 : 0.054
         * 2 : 211
         * parameter1 : 211
         * 3 : Auto Pomotion Turnover(W88 SLOTS)
         * remark : Auto Pomotion Turnover(W88 SLOTS)
         */

        @SerializedName("0")
        private String _$0;
        private String date_time;
        @SerializedName("1")
        private String _$1;
        private String type2;
        @SerializedName("2")
        private String _$2;
        private String parameter1;
        @SerializedName("3")
        private String _$3;
        private String remark;

        public String get_$0() {
            return _$0;
        }

        public void set_$0(String _$0) {
            this._$0 = _$0;
        }

        public String getDate_time() {
            return date_time;
        }

        public void setDate_time(String date_time) {
            this.date_time = date_time;
        }

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }

        public String getParameter1() {
            return parameter1;
        }

        public void setParameter1(String parameter1) {
            this.parameter1 = parameter1;
        }

        public String get_$3() {
            return _$3;
        }

        public void set_$3(String _$3) {
            this._$3 = _$3;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
