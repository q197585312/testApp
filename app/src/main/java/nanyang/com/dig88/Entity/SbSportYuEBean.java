package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2017/1/18.
 */

public class SbSportYuEBean {
    /**
     * username : 995sspsusd
     * currency : USD
     * balance : 10.85
     * outstanding : 0.0
     * error : {"id":0,"msg":"NoError"}
     * serverId : A88
     */

    private String username;
    private String currency;
    private double balance;
    private double outstanding;
    private ErrorBean error;
    private String serverId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(double outstanding) {
        this.outstanding = outstanding;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public static class ErrorBean {
        /**
         * id : 0
         * msg : NoError
         */

        private int id;
        private String msg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
