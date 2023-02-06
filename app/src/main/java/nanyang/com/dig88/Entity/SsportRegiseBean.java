package nanyang.com.dig88.Entity;

/**
 * Created by s7528 on 2017/01/03.
 */
public class SsportRegiseBean {
    /**
     * error : {"id":4103,"msg":"UserExists"}
     * serverId : A02
     */

    private ErrorBean error;
    private String serverId;

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

    @Override
    public String toString() {
        return "SsportRegiseBean{" +
                "error=" + error +
                ", serverId='" + serverId + '\'' +
                '}';
    }

    public static class ErrorBean {
        /**
         * id : 4103
         * msg : UserExists
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

        @Override
        public String toString() {
            return "ErrorBean{" +
                    "id=" + id +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}
