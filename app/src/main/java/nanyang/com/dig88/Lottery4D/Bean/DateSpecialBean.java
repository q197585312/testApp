package nanyang.com.dig88.Lottery4D.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2019/2/25.
 */

public class DateSpecialBean {

    /**
     * code : 1
     * data : {"214":["20190227001#27/02","20190302001#02/03","20190303001#03/03"],"215":["20190227001#27/02","20190302001#02/03","20190303001#03/03"],"216":["20190227001#27/02","20190302001#02/03","20190303001#03/03"],"217":["20190227001#27/02","20190302001#02/03","20190303001#03/03"],"218":["20190227001#27/02","20190302001#02/03","20190303001#03/03"],"219":["20190227001#27/02","20190302001#02/03","20190303001#03/03"],"220":["20190227001#27/02","20190302001#02/03","20190303001#03/03"],"221":["20190225001#25/02","20190226001#26/02","20190227001#27/02","20190228001#28/02","20190301001#01/03","20190302001#02/03","20190303001#03/03"],"248":["20190227001#27/02","20190302001#02/03","20190303001#03/03"]}
     */

    private String code;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("214")
        private List<String> _$214;
        @SerializedName("215")
        private List<String> _$215;
        @SerializedName("216")
        private List<String> _$216;
        @SerializedName("217")
        private List<String> _$217;
        @SerializedName("218")
        private List<String> _$218;
        @SerializedName("219")
        private List<String> _$219;
        @SerializedName("220")
        private List<String> _$220;
        @SerializedName("221")
        private List<String> _$221;
        @SerializedName("248")
        private List<String> _$248;

        public List<String> get_$214() {
            return _$214;
        }

        public void set_$214(List<String> _$214) {
            this._$214 = _$214;
        }

        public List<String> get_$215() {
            return _$215;
        }

        public void set_$215(List<String> _$215) {
            this._$215 = _$215;
        }

        public List<String> get_$216() {
            return _$216;
        }

        public void set_$216(List<String> _$216) {
            this._$216 = _$216;
        }

        public List<String> get_$217() {
            return _$217;
        }

        public void set_$217(List<String> _$217) {
            this._$217 = _$217;
        }

        public List<String> get_$218() {
            return _$218;
        }

        public void set_$218(List<String> _$218) {
            this._$218 = _$218;
        }

        public List<String> get_$219() {
            return _$219;
        }

        public void set_$219(List<String> _$219) {
            this._$219 = _$219;
        }

        public List<String> get_$220() {
            return _$220;
        }

        public void set_$220(List<String> _$220) {
            this._$220 = _$220;
        }

        public List<String> get_$221() {
            return _$221;
        }

        public void set_$221(List<String> _$221) {
            this._$221 = _$221;
        }

        public List<String> get_$248() {
            return _$248;
        }

        public void set_$248(List<String> _$248) {
            this._$248 = _$248;
        }
    }
}
