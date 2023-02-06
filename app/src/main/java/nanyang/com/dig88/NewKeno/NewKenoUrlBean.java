package nanyang.com.dig88.NewKeno;

import java.util.List;

/**
 * Created by Administrator on 2019/2/13.
 */

public class NewKenoUrlBean {


    /**
     * code : 1
     * data : {"newkeno":"https://newkeno.k-api.com/api/","cockfight":["https://cockfight.k-api.com/api/login.php?","web_id","username","language","from","sess_id"]}
     * msg : 1
     */

    private String code;
    private DataBean data;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * newkeno : https://newkeno.k-api.com/api/
         * cockfight : ["https://cockfight.k-api.com/api/login.php?","web_id","username","language","from","sess_id"]
         */

        private String newkeno;
        private List<String> cockfight;

        public String getNewkeno() {
            return newkeno;
        }

        public void setNewkeno(String newkeno) {
            this.newkeno = newkeno;
        }

        public List<String> getCockfight() {
            return cockfight;
        }

        public void setCockfight(List<String> cockfight) {
            this.cockfight = cockfight;
        }
    }
}
