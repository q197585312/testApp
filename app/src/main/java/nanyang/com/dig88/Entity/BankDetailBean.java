package nanyang.com.dig88.Entity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/9.
 */

public class BankDetailBean {

    /**
     * code : 1
     * msg : 1
     * data : [{"id_mod_bank":"443","id_mod_bank_account":"771","bank_name":"MANDIRI","account":"YUDHA PERMANA","no":"1060011651117","type":"2","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"},{"id_mod_bank":"444","id_mod_bank_account":"773","bank_name":"BNI","account":"AGUNG RIYANTO","no":"0362119425","type":"2","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"},{"id_mod_bank":"444","id_mod_bank_account":"774","bank_name":"BNI","account":"NURAZIZAH HASIBUAN","no":"0434137635","type":"1","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"},{"id_mod_bank":"445","id_mod_bank_account":"775","bank_name":"BRI","account":"YOSEPH YANAWO YOLMEN","no":"043801000384568","type":"2","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"},{"id_mod_bank":"445","id_mod_bank_account":"776","bank_name":"BRI","account":"ANDI HASIBUAN","no":"175901000216562","type":"1","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"},{"id_mod_bank":"449","id_mod_bank_account":"782","bank_name":"DANAMON","account":"SETIAWATI","no":"3600970994","type":"2","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"},{"id_mod_bank":"449","id_mod_bank_account":"783","bank_name":"DANAMON","account":"SETIAWATI","no":"3600970994","type":"1","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"},{"id_mod_bank":"443","id_mod_bank_account":"1065","bank_name":"MANDIRI","account":"ADON","no":"1150006848677","type":"1","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"},{"id_mod_bank":"445","id_mod_bank_account":"1377","bank_name":"BRI","account":"M ABDUH SIBAROAR","no":"069301000494561","type":"2","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"1"},{"id_mod_bank":"449","id_mod_bank_account":"1378","bank_name":"DANAMON","account":"ILHAM MARDIANSYAH","no":"003594425963","type":"2","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"1"},{"id_mod_bank":"444","id_mod_bank_account":"1380","bank_name":"BNI","account":"DEBORA ISMAUN","no":"0330790092","type":"2","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"1"},{"id_mod_bank":"442","id_mod_bank_account":"1654","bank_name":"BCA","account":"RAHMAT NURYADI","no":"4371489457","type":"2","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"1"},{"id_mod_bank":"442","id_mod_bank_account":"1702","bank_name":"BCA","account":"BAYU MAHENDRA","no":"3920384501","type":"2","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"},{"id_mod_bank":"446","id_mod_bank_account":"2236","bank_name":"CIMB","account":"RIDUWAN","no":"703699009900","type":"2","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"},{"id_mod_bank":"446","id_mod_bank_account":"2251","bank_name":"CIMB","account":"RIDUWAN","no":"703699009900","type":"1","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"},{"id_mod_bank":"442","id_mod_bank_account":"2253","bank_name":"BCA","account":"TRI HARJANTO","no":"3930538131","type":"1","currency":"11","min_deposit":"25","max_deposit":"99999","min_withdraw":"50","max_withdraw":"100000","bank_level":"0"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id_mod_bank : 443
         * id_mod_bank_account : 771
         * bank_name : MANDIRI
         * account : YUDHA PERMANA
         * no : 1060011651117
         * type : 2
         * currency : 11
         * min_deposit : 25
         * max_deposit : 99999
         * min_withdraw : 50
         * max_withdraw : 100000
         * bank_level : 0
         */

        private String id_mod_bank;
        private String id_mod_bank_account;
        private String bank_name;
        private String account;
        private String no;
        private String type;
        private String currency;
        private String min_deposit;
        private String max_deposit;
        private String min_withdraw;
        private String max_withdraw;
        private String bank_level;

        public String getId_mod_bank() {
            return id_mod_bank;
        }

        public void setId_mod_bank(String id_mod_bank) {
            this.id_mod_bank = id_mod_bank;
        }

        public String getId_mod_bank_account() {
            return id_mod_bank_account;
        }

        public void setId_mod_bank_account(String id_mod_bank_account) {
            this.id_mod_bank_account = id_mod_bank_account;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getMin_deposit() {
            return min_deposit;
        }

        public void setMin_deposit(String min_deposit) {
            this.min_deposit = min_deposit;
        }

        public String getMax_deposit() {
            return max_deposit;
        }

        public void setMax_deposit(String max_deposit) {
            this.max_deposit = max_deposit;
        }

        public String getMin_withdraw() {
            return min_withdraw;
        }

        public void setMin_withdraw(String min_withdraw) {
            this.min_withdraw = min_withdraw;
        }

        public String getMax_withdraw() {
            return max_withdraw;
        }

        public void setMax_withdraw(String max_withdraw) {
            this.max_withdraw = max_withdraw;
        }

        public String getBank_level() {
            return bank_level;
        }

        public void setBank_level(String bank_level) {
            this.bank_level = bank_level;
        }
    }
}
