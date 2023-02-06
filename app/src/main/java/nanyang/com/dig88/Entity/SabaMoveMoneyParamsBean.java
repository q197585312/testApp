package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2017/1/18.
 */

public class SabaMoveMoneyParamsBean {
    String web_id;
    String id_user;
    String username;
    String from_gameid;
    String to_gameid;
    String amt;
    String session_id;
    String currency;
    String save;

    public String getWeb_id() {
        return web_id;
    }

    public void setWeb_id(String web_id) {
        this.web_id = web_id;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFrom_gameid() {
        return from_gameid;
    }

    public void setFrom_gameid(String from_gameid) {
        this.from_gameid = from_gameid;
    }

    public String getTo_gameid() {
        return to_gameid;
    }

    public void setTo_gameid(String to_gameid) {
        this.to_gameid = to_gameid;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }
}
