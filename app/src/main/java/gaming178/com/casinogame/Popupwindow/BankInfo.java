package gaming178.com.casinogame.Popupwindow;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/12.
 */
public class BankInfo implements Serializable {
    String bankName;
    String bankAccount;
    String bankNumber;

    public BankInfo(String bankName, String bankAccount, String bankNumber) {
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        this.bankNumber = bankNumber;
    }

    public BankInfo() {
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }
}
