package nanyang.com.dig88.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2019/2/27.
 */

public class Scr888ChangePasswordBean {

    /**
     * code : -1
     * msg : failed
     * return : 78397261sps199402321291975--qw12345
     */

    private String code;
    private String msg;
    @SerializedName("return")
    private String returnX;

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

    public String getReturnX() {
        return returnX;
    }

    public void setReturnX(String returnX) {
        this.returnX = returnX;
    }
}
