package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2016/12/30.
 */

public class SbosportsBean {
    /**
     * username : 995sleopro10
     * token : 55085.mRVArNbdIDUzXxKwOINqmvH
     * device : 1
     * error : {"id":0,"msg":"NoError"}
     * serverId : A88
     */

    private String username;
    private String token;
    private int device;
    private ErrorBean error;
    private String serverId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
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
         * id : 0
         * msg : NoError
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
