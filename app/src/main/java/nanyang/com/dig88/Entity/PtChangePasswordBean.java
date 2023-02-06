package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2018/3/26.
 */

public class PtChangePasswordBean {

    /**
     * Code : -2
     * Message : The request Error!
     * data : {"Code":"102","Message":"Invalid password, it must be 5-40 alphanumeric."}
     */

    private String Code;
    private String Message;
    private DataBean data;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Code : 102
         * Message : Invalid password, it must be 5-40 alphanumeric.
         */

        private String Code;
        private String Message;

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }
    }
}
