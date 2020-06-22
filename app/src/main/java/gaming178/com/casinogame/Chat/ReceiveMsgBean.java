package gaming178.com.casinogame.Chat;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ReceiveMsgBean implements Serializable{
    /**
     * type : login
     * client_id : 287013
     * client_name : DLDLDLYY15
     * time : 2016-09-05 14:26:04
     */

    private String type;
    private int client_id;
    private String client_name;
    private String time;
    /**
     * from_client_id : 287041
     * message : See you everyone.
     */

    private int from_client_id;
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getFrom_client_id() {
        return from_client_id;
    }

    public void setFrom_client_id(int from_client_id) {
        this.from_client_id = from_client_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
  /*  {"type":"sendMsg","from_client_id":287041,"client_name":"DLDLDLYY15","message":"See you everyone.","time":"2016-09-05 14:56:50"}*/
/*>{"type":"login","client_id":287013,"client_name":"DLDLDLYY15","time":"2016-09-05 14:26:04"}*/
    
}
