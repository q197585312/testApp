package nanyang.com.dig88.Base;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/29.
 */

public class NyBaseXmlResponse<T> implements Serializable{

    public T result;
    String errcode;
    String errtext;
    Integer total;

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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
