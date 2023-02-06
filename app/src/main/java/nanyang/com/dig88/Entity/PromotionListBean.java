package nanyang.com.dig88.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/8/2.
 */

public class PromotionListBean {

    /**
     * code : 1
     * msg : 1
     * data : [{"0":"IDEGCCFW","promosi_code":"IDEGCCFW","1":"2018-07-31 21:19:53","date_time":"2018-07-31 21:19:53","2":"0","bonus_amount":"0","3":"0","turnover_amount":"0","4":"1","status":"1","5":"Refused","status_text":"Refused","6":"IDN Fighting Wednesday","info":"IDN Fighting Wednesday","7":"2018-05-01 00:00:00","start_time":"2018-05-01 00:00:00","8":"2020-01-01 23:59:59","end_time":"2020-01-01 23:59:59"},{"0":"IDEGCCUB","promosi_code":"IDEGCCUB","1":"2018-07-14 21:12:29","date_time":"2018-07-14 21:12:29","2":"0","bonus_amount":"0","3":"0","turnover_amount":"0","4":"1","status":"1","5":"Refused","status_text":"Refused","6":"IDN 3% Unlimited Reload Bonus","info":"IDN 3% Unlimited Reload Bonus","7":"2018-05-01 00:00:00","start_time":"2018-05-01 00:00:00","8":"2020-01-01 23:59:59","end_time":"2020-01-01 23:59:59"}]
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
         * 0 : IDEGCCFW
         * promosi_code : IDEGCCFW
         * 1 : 2018-07-31 21:19:53
         * date_time : 2018-07-31 21:19:53
         * 2 : 0
         * bonus_amount : 0
         * 3 : 0
         * turnover_amount : 0
         * 4 : 1
         * status : 1
         * 5 : Refused
         * status_text : Refused
         * 6 : IDN Fighting Wednesday
         * info : IDN Fighting Wednesday
         * 7 : 2018-05-01 00:00:00
         * start_time : 2018-05-01 00:00:00
         * 8 : 2020-01-01 23:59:59
         * end_time : 2020-01-01 23:59:59
         */

        @SerializedName("0")
        private String _$0;
        private String promosi_code;
        @SerializedName("1")
        private String _$1;
        private String date_time;
        @SerializedName("2")
        private String _$2;
        private String bonus_amount;
        @SerializedName("3")
        private String _$3;
        private String turnover_amount;
        @SerializedName("4")
        private String _$4;
        private String status;
        @SerializedName("5")
        private String _$5;
        private String status_text;
        @SerializedName("6")
        private String _$6;
        private String info;
        @SerializedName("7")
        private String _$7;
        private String start_time;
        @SerializedName("8")
        private String _$8;
        private String end_time;

        public String get_$0() {
            return _$0;
        }

        public void set_$0(String _$0) {
            this._$0 = _$0;
        }

        public String getPromosi_code() {
            return promosi_code;
        }

        public void setPromosi_code(String promosi_code) {
            this.promosi_code = promosi_code;
        }

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public String getDate_time() {
            return date_time;
        }

        public void setDate_time(String date_time) {
            this.date_time = date_time;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }

        public String getBonus_amount() {
            return bonus_amount;
        }

        public void setBonus_amount(String bonus_amount) {
            this.bonus_amount = bonus_amount;
        }

        public String get_$3() {
            return _$3;
        }

        public void set_$3(String _$3) {
            this._$3 = _$3;
        }

        public String getTurnover_amount() {
            return turnover_amount;
        }

        public void setTurnover_amount(String turnover_amount) {
            this.turnover_amount = turnover_amount;
        }

        public String get_$4() {
            return _$4;
        }

        public void set_$4(String _$4) {
            this._$4 = _$4;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String get_$5() {
            return _$5;
        }

        public void set_$5(String _$5) {
            this._$5 = _$5;
        }

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
        }

        public String get_$6() {
            return _$6;
        }

        public void set_$6(String _$6) {
            this._$6 = _$6;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String get_$7() {
            return _$7;
        }

        public void set_$7(String _$7) {
            this._$7 = _$7;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String get_$8() {
            return _$8;
        }

        public void set_$8(String _$8) {
            this._$8 = _$8;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }
    }
}
