package nanyang.com.dig88.Base;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/29.
 */
public class XmlResponse implements Serializable {
    String errcode;
    String errtext;
    String result;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrtext() {
        return errtext;
    }

    public void setErrtext(String errtext) {
        this.errtext = errtext;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
