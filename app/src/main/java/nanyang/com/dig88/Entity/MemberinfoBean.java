package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MemberinfoBean {

    /**
     * code : 1
     * msg : 1
     * data : {"username":"vichai","email":"123123123","tel":"+85569543201","address":"013-國泰世華/jsjsjej","id_mod_bank":"1126","bank_acc_name":"nd****","bank_acc_name2":"ndndnd","bank_acc_no":"499****","bank_acc_no2":"4994949","bank_name":"123456","total_refer":"0","laster_refer":"0","balance":"0"}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : vichai
         * email : 123123123
         * tel : +85569543201
         * address : 013-國泰世華/jsjsjej
         * id_mod_bank : 1126
         * bank_acc_name : nd****
         * bank_acc_name2 : ndndnd
         * bank_acc_no : 499****
         * bank_acc_no2 : 4994949
         * bank_name : 123456
         * total_refer : 0
         * laster_refer : 0
         * balance : 0
         */

        private String username;
        private String email;
        private String tel;
        private String address;
        private String id_mod_bank;
        private String bank_acc_name;
        private String bank_acc_name2;
        private String bank_acc_no;
        private String bank_acc_no2;
        private String bank_name;
        private String total_refer;
        private String laster_refer;
        private String balance;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getId_mod_bank() {
            return id_mod_bank;
        }

        public void setId_mod_bank(String id_mod_bank) {
            this.id_mod_bank = id_mod_bank;
        }

        public String getBank_acc_name() {
            return bank_acc_name;
        }

        public void setBank_acc_name(String bank_acc_name) {
            this.bank_acc_name = bank_acc_name;
        }

        public String getBank_acc_name2() {
            return bank_acc_name2;
        }

        public void setBank_acc_name2(String bank_acc_name2) {
            this.bank_acc_name2 = bank_acc_name2;
        }

        public String getBank_acc_no() {
            return bank_acc_no;
        }

        public void setBank_acc_no(String bank_acc_no) {
            this.bank_acc_no = bank_acc_no;
        }

        public String getBank_acc_no2() {
            return bank_acc_no2;
        }

        public void setBank_acc_no2(String bank_acc_no2) {
            this.bank_acc_no2 = bank_acc_no2;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getTotal_refer() {
            return total_refer;
        }

        public void setTotal_refer(String total_refer) {
            this.total_refer = total_refer;
        }

        public String getLaster_refer() {
            return laster_refer;
        }

        public void setLaster_refer(String laster_refer) {
            this.laster_refer = laster_refer;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }
}
