package gaming178.com.mylibrary.base.soap;

/**
 * Created by Administrator on 2015/9/2.
 */
public class WebServiceBean {
    int code;
    String message;

    public WebServiceBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
