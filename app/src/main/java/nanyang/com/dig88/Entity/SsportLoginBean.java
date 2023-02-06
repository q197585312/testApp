package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2017/1/3.
 */

public class SsportLoginBean {
    /**
     * isUM : false
     * error : {"id":1,"msg":"InvalidCompanyKey"}
     * serverId : A88
     */

    private boolean isUM;
    private ErrorBean error;
    private String serverId;

    public boolean isIsUM() {
        return isUM;
    }

    public void setIsUM(boolean isUM) {
        this.isUM = isUM;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public static class ErrorBean {
        /**
         * id : 1
         * msg : InvalidCompanyKey
         */

        private int id;
        private String msg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
