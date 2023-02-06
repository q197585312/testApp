package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AgentBean {

    /**
     * code : 1
     * errMessage : No Error
     * Agent : i@gw
     */

    private int code;
    private String errMessage;
    private String Agent;

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

    public String getAgent() {
        return Agent;
    }

    public void setAgent(String Agent) {
        this.Agent = Agent;
    }
}
