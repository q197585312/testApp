package nanyang.com.dig88.Lottery4D.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */

public class StatusBean {

    /**
     * code : 1
     * msg : 1
     * set_pool : [{"id_mod_game_type2":"214","id_mod_game_type1":"20","open_time":"20:00:00","open_date":"","close_time":"17:00:00","open_rule":"27","name_ex":"Magnum","period":"20181121001"},{"id_mod_game_type2":"215","id_mod_game_type1":"20","open_time":"20:00:00","open_date":"","close_time":"17:00:00","open_rule":"18","name_ex":"Singapore","period":"20181119001"},{"id_mod_game_type2":"216","id_mod_game_type1":"20","open_time":"20:00:00","open_date":"","close_time":"17:00:00","open_rule":"27","name_ex":"4D Toto","period":"20181121001"},{"id_mod_game_type2":"217","id_mod_game_type1":"20","open_time":"20:00:00","open_date":"","close_time":"17:00:00","open_rule":"27","name_ex":"Damacai","period":"20181121001"},{"id_mod_game_type2":"218","id_mod_game_type1":"20","open_time":"20:00:00","open_date":"","close_time":"17:00:00","open_rule":"27","name_ex":"Sabah","period":"20181121001"},{"id_mod_game_type2":"219","id_mod_game_type1":"20","open_time":"20:00:00","open_date":"","close_time":"17:00:00","open_rule":"27","name_ex":"Sandakan","period":"20181121001"},{"id_mod_game_type2":"220","id_mod_game_type1":"20","open_time":"20:00:00","open_date":"","close_time":"17:00:00","open_rule":"27","name_ex":"Sarawak","period":"20181121001"},{"id_mod_game_type2":"221","id_mod_game_type1":"20","open_time":"20:00:00","open_date":"","close_time":"17:00:00","open_rule":"0","name_ex":"Grand Dragon","period":"20181120001"}]
     * pool_id : ["214","215","216","217","218","219","220","221"]
     * pool_name : {"214":"Magnum","215":"Singapore","216":"4D Toto","217":"Damacai","218":"Sabah","219":"Sandakan","220":"Sarawak","221":"Grand Dragon"}
     */

    private String code;
    private String msg;
    private PoolNameBean pool_name;
    private List<SetPoolBean> set_pool;
    private List<String> pool_id;

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

    public PoolNameBean getPool_name() {
        return pool_name;
    }

    public void setPool_name(PoolNameBean pool_name) {
        this.pool_name = pool_name;
    }

    public List<SetPoolBean> getSet_pool() {
        return set_pool;
    }

    public void setSet_pool(List<SetPoolBean> set_pool) {
        this.set_pool = set_pool;
    }

    public List<String> getPool_id() {
        return pool_id;
    }

    public void setPool_id(List<String> pool_id) {
        this.pool_id = pool_id;
    }

    public static class PoolNameBean {
        /**
         * 214 : Magnum
         * 215 : Singapore
         * 216 : 4D Toto
         * 217 : Damacai
         * 218 : Sabah
         * 219 : Sandakan
         * 220 : Sarawak
         * 221 : Grand Dragon
         */

        @SerializedName("214")
        private String _$214;
        @SerializedName("215")
        private String _$215;
        @SerializedName("216")
        private String _$216;
        @SerializedName("217")
        private String _$217;
        @SerializedName("218")
        private String _$218;
        @SerializedName("219")
        private String _$219;
        @SerializedName("220")
        private String _$220;
        @SerializedName("221")
        private String _$221;

        public String get_$214() {
            return _$214;
        }

        public void set_$214(String _$214) {
            this._$214 = _$214;
        }

        public String get_$215() {
            return _$215;
        }

        public void set_$215(String _$215) {
            this._$215 = _$215;
        }

        public String get_$216() {
            return _$216;
        }

        public void set_$216(String _$216) {
            this._$216 = _$216;
        }

        public String get_$217() {
            return _$217;
        }

        public void set_$217(String _$217) {
            this._$217 = _$217;
        }

        public String get_$218() {
            return _$218;
        }

        public void set_$218(String _$218) {
            this._$218 = _$218;
        }

        public String get_$219() {
            return _$219;
        }

        public void set_$219(String _$219) {
            this._$219 = _$219;
        }

        public String get_$220() {
            return _$220;
        }

        public void set_$220(String _$220) {
            this._$220 = _$220;
        }

        public String get_$221() {
            return _$221;
        }

        public void set_$221(String _$221) {
            this._$221 = _$221;
        }
    }

    public static class SetPoolBean {
        /**
         * id_mod_game_type2 : 214
         * id_mod_game_type1 : 20
         * open_time : 20:00:00
         * open_date :
         * close_time : 17:00:00
         * open_rule : 27
         * name_ex : Magnum
         * period : 20181121001
         */

        private String id_mod_game_type2;
        private String id_mod_game_type1;
        private String open_time;
        private String open_date;
        private String close_time;
        private String open_rule;
        private String name_ex;
        private String period;

        public String getId_mod_game_type2() {
            return id_mod_game_type2;
        }

        public void setId_mod_game_type2(String id_mod_game_type2) {
            this.id_mod_game_type2 = id_mod_game_type2;
        }

        public String getId_mod_game_type1() {
            return id_mod_game_type1;
        }

        public void setId_mod_game_type1(String id_mod_game_type1) {
            this.id_mod_game_type1 = id_mod_game_type1;
        }

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public String getOpen_date() {
            return open_date;
        }

        public void setOpen_date(String open_date) {
            this.open_date = open_date;
        }

        public String getClose_time() {
            return close_time;
        }

        public void setClose_time(String close_time) {
            this.close_time = close_time;
        }

        public String getOpen_rule() {
            return open_rule;
        }

        public void setOpen_rule(String open_rule) {
            this.open_rule = open_rule;
        }

        public String getName_ex() {
            return name_ex;
        }

        public void setName_ex(String name_ex) {
            this.name_ex = name_ex;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }
    }
}
