package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2017/11/28.
 */

public class Fun77UpateUserBean {
    /**
     * code : 1
     * msg : 1
     * data : {"bank_id":"941","bank_acc_name":"jxkxkkx","bank_acc_no":"6767667","tel":"6767667","email":"kxkdkdk@qq.com"}
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
         * bank_id : 941
         * bank_acc_name : jxkxkkx
         * bank_acc_no : 6767667
         * tel : 6767667
         * email : kxkdkdk@qq.com
         */

        private String bank_id;
        private String bank_acc_name;
        private String bank_acc_no;
        private String tel;
        private String email;

        public String getBank_id() {
            return bank_id;
        }

        public void setBank_id(String bank_id) {
            this.bank_id = bank_id;
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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
