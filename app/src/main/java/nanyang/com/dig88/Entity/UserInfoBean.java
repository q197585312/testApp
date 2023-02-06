package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 *用户信息
 */
public class UserInfoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String user_id;
    private String balance;
    private String session_id;
    private String id_mod_currency;
    private String  bank_acc_no;
    private String  bank_acc_name;
    private String  address_bank;
    private String  id_mod_bank;
    private String tel;
    private String open_time;
    private String login_time;
    private String agent_id;
    private String useracc;
    private String poker_id;
    private String username;
    private int set;
    private String full_name;
    private String mem_level;
    private GameBalanceBean moneyBalance;

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPoker_id() {
        return poker_id;
    }

    public void setPoker_id(String poker_id) {
        this.poker_id = poker_id;
    }

    public String getUseracc() {
        return useracc;
    }

    public void setUseracc(String useracc) {
        this.useracc = useracc;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMem_level() {
        return mem_level;
    }

    public void setMem_level(String mem_level) {
        this.mem_level = mem_level;
    }

    public String getId_mod_bank() {
        return id_mod_bank;
    }

    public void setId_mod_bank(String id_mod_bank) {
        this.id_mod_bank = id_mod_bank;
    }

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getId_mod_currency() {
        return id_mod_currency;
    }

    public void setId_mod_currency(String id_mod_currency) {
        this.id_mod_currency = id_mod_currency;
    }

    public String getBank_acc_name() {
        return bank_acc_name;
    }

    public void setBank_acc_name(String bank_acc_name) {
        this.bank_acc_name = bank_acc_name;
    }

    public String getBank_acc_no() {
        return bank_acc_no;
    }

    public void setBank_acc_no(String bank_acc_no) {
        this.bank_acc_no = bank_acc_no;
    }

    public String getAddress_bank() {
        return address_bank;
    }

    public void setAddress_bank(String address_bank) {
        this.address_bank = address_bank;
    }
/*{"user_id":"1116","balance":"99790.136","session_id":"2stest007jke7iee638t72uahuaf67vbfu7","id_mod_currency":"5"}}*/

    public GameBalanceBean getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(GameBalanceBean moneyBalance) {
        this.moneyBalance = moneyBalance;
    }
}
