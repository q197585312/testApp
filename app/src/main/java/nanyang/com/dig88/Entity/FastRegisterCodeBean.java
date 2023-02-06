package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2020/1/6.
 */

public class FastRegisterCodeBean {

    /**
     * code : 1
     * errMsg : No error
     * captcha : 48242
     */

    private String code;
    private String errMsg;
    private String captcha;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
