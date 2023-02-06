package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/3/19.
 */

public class GameMaintenanceBean {

    /**
     * code : 1
     * msg : 1
     * data : {"type2":"129","status":"1"}
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
         * type2 : 129
         * status : 1
         */

        private String type2;
        private String status;

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
