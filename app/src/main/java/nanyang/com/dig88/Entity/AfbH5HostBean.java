package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/8/14.
 */

public class AfbH5HostBean {

    /**
     * code : 1
     * errMessage : No Error
     * URL : http://api-a19.afbsport.com/api.aspx?
     * host : wapsport-a7fun7.afbsport.com
     * agent : i@gk
     */

    private int code;
    private String errMessage;
    private String URL;
    private String host;
    private String agent;
    private String hostDesk;

    public String getHostDesk() {
        return hostDesk;
    }

    public void setHostDesk(String hostDesk) {
        this.hostDesk = hostDesk;
    }

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
