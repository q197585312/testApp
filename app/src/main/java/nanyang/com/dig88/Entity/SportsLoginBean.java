package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2018/7/4.
 */

public class SportsLoginBean {

    /**
     * code : 1
     * errMessage : No Error
     * URL : http://wapsport-a19.afbsport.com/public/validate.aspx?us=i@bz995ssps1993&k=27c9a3203f6f41b299c41ba31af62076&lang=ZH-CN
     */

    private int code;
    private String errMessage;
    private String URL;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
