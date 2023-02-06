package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/12/4.
 */

public class SendTelBean {

    /**
     * code : 0
     * msg : -1
     * errMsg : The 'To' number +855123 is not a valid phone number.
     */
//{"code":1,"msg":1,"errMsg":"Successfully sent","dataCode":"196946"}
    private int code;
    private String msg;
    private String errMsg;
    private String dataCode;

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
