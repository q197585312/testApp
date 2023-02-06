package nanyang.com.dig88.ThaiLottery.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2019/1/22.
 */

public class HotNumberBean {

    /**
     * code : 1
     * msg : 1
     * errMsg : No Error
     * data : [{"0":"4469","id_mod_games_set_totalmax_num":"4469","1":"14","type1":"14","2":"97","type2":"97","3":"76","type3":"76","4":"50","web_id":"50","5":"234","number":"234","6":"1","is_hot":"1","7":"1000","max_total":"1000","8":"1000","max_total_left":"1000","9":"2019-01-15 18:24:31","insert_time":"2019-01-15 18:24:31","10":"","options":"","11":"250","rate":"250","12":"3D Top","play_type":"3D Top","game_name":"THAI LOTTERY","pool":"THAILAND"}]
     */

    private String code;
    private String msg;
    private String errMsg;
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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * 0 : 4469
         * id_mod_games_set_totalmax_num : 4469
         * 1 : 14
         * type1 : 14
         * 2 : 97
         * type2 : 97
         * 3 : 76
         * type3 : 76
         * 4 : 50
         * web_id : 50
         * 5 : 234
         * number : 234
         * 6 : 1
         * is_hot : 1
         * 7 : 1000
         * max_total : 1000
         * 8 : 1000
         * max_total_left : 1000
         * 9 : 2019-01-15 18:24:31
         * insert_time : 2019-01-15 18:24:31
         * 10 :
         * options :
         * 11 : 250
         * rate : 250
         * 12 : 3D Top
         * play_type : 3D Top
         * game_name : THAI LOTTERY
         * pool : THAILAND
         */

        @SerializedName("0")
        private String _$0;
        private String id_mod_games_set_totalmax_num;
        @SerializedName("1")
        private String _$1;
        private String type1;
        @SerializedName("2")
        private String _$2;
        private String type2;
        @SerializedName("3")
        private String _$3;
        private String type3;
        @SerializedName("4")
        private String _$4;
        private String web_id;
        @SerializedName("5")
        private String _$5;
        private String number;
        @SerializedName("6")
        private String _$6;
        private String is_hot;
        @SerializedName("7")
        private String _$7;
        private String max_total;
        @SerializedName("8")
        private String _$8;
        private String max_total_left;
        @SerializedName("9")
        private String _$9;
        private String insert_time;
        @SerializedName("10")
        private String _$10;
        private String options;
        @SerializedName("11")
        private String _$11;
        private String rate;
        @SerializedName("12")
        private String _$12;
        private String play_type;
        private String game_name;
        private String pool;

        public String get_$0() {
            return _$0;
        }

        public void set_$0(String _$0) {
            this._$0 = _$0;
        }

        public String getId_mod_games_set_totalmax_num() {
            return id_mod_games_set_totalmax_num;
        }

        public void setId_mod_games_set_totalmax_num(String id_mod_games_set_totalmax_num) {
            this.id_mod_games_set_totalmax_num = id_mod_games_set_totalmax_num;
        }

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public String get_$3() {
            return _$3;
        }

        public void set_$3(String _$3) {
            this._$3 = _$3;
        }

        public String getType3() {
            return type3;
        }

        public void setType3(String type3) {
            this.type3 = type3;
        }

        public String get_$4() {
            return _$4;
        }

        public void set_$4(String _$4) {
            this._$4 = _$4;
        }

        public String getWeb_id() {
            return web_id;
        }

        public void setWeb_id(String web_id) {
            this.web_id = web_id;
        }

        public String get_$5() {
            return _$5;
        }

        public void set_$5(String _$5) {
            this._$5 = _$5;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String get_$6() {
            return _$6;
        }

        public void set_$6(String _$6) {
            this._$6 = _$6;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String get_$7() {
            return _$7;
        }

        public void set_$7(String _$7) {
            this._$7 = _$7;
        }

        public String getMax_total() {
            return max_total;
        }

        public void setMax_total(String max_total) {
            this.max_total = max_total;
        }

        public String get_$8() {
            return _$8;
        }

        public void set_$8(String _$8) {
            this._$8 = _$8;
        }

        public String getMax_total_left() {
            return max_total_left;
        }

        public void setMax_total_left(String max_total_left) {
            this.max_total_left = max_total_left;
        }

        public String get_$9() {
            return _$9;
        }

        public void set_$9(String _$9) {
            this._$9 = _$9;
        }

        public String getInsert_time() {
            return insert_time;
        }

        public void setInsert_time(String insert_time) {
            this.insert_time = insert_time;
        }

        public String get_$10() {
            return _$10;
        }

        public void set_$10(String _$10) {
            this._$10 = _$10;
        }

        public String getOptions() {
            return options;
        }

        public void setOptions(String options) {
            this.options = options;
        }

        public String get_$11() {
            return _$11;
        }

        public void set_$11(String _$11) {
            this._$11 = _$11;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String get_$12() {
            return _$12;
        }

        public void set_$12(String _$12) {
            this._$12 = _$12;
        }

        public String getPlay_type() {
            return play_type;
        }

        public void setPlay_type(String play_type) {
            this.play_type = play_type;
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
