package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/6/12.
 */

public class UploadBean {

    /**
     * code : 1
     * msg : success
     * url : https://s3.amazonaws.com/deposit.img/m12win-sps001-1560333230app.jpeg
     */

    private String code;
    private String msg;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
}
