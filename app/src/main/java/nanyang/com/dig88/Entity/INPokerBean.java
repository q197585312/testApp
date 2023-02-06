package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2018/10/24.
 */

public class INPokerBean {

    /**
     * code : 0
     * Msg : No Error
     * Lobby : http://lobbyplay.com/lobby.php?user=EGCC88QW1993&key=IATgm59EZulAE
     * balance : 0
     * LoginId : 26sqw1993
     * LoginPwd : egcc88
     */

    private int code;
    private String Msg;
    private String Lobby;
    private String balance;
    private String LoginId;
    private String LoginPwd;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public String getLobby() {
        return Lobby;
    }

    public void setLobby(String Lobby) {
        this.Lobby = Lobby;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String LoginId) {
        this.LoginId = LoginId;
    }

    public String getLoginPwd() {
        return LoginPwd;
    }

    public void setLoginPwd(String LoginPwd) {
        this.LoginPwd = LoginPwd;
    }
}
