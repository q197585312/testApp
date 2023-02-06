package nanyang.com.dig88.Entity;

/**
 * Created by 47184 on 2019/4/9.
 */

public class IgLotteryBalanceBean {

    /**
     * request : null
     * errorCode : 0
     * errorMessage : null
     * logId : 1554800168994
     * params : {"balance":"0.00000"}
     */

    private Object request;
    private int errorCode;
    private Object errorMessage;
    private long logId;
    private ParamsBean params;

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {
        /**
         * balance : 0.00000
         */

        private String balance;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }
}
