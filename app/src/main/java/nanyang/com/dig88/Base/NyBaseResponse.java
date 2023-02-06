package nanyang.com.dig88.Base;

import java.io.Serializable;

/**
 * {"code":"1","msg":"1","data":{"user_id":"1245","balance":"9999920","session_id":"2stest0041arf1u7c6s924bc445bj9n5m37"}}
 */
public class NyBaseResponse<T> implements Serializable{
    String code;
    String msg;
    Integer total;
    int set;
    T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }
}
