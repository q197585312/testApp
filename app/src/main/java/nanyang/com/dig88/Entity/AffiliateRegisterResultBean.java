package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/9/29.
 */

public class AffiliateRegisterResultBean {

    /**
     * code : 0
     * msg : -2
     * error : User name exist
     */

    private String code;
    private String msg;
    private String error;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
