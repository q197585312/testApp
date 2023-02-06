package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2018/12/26.
 */

public class AddBankBean {
    private String bankNumber;
    private String bankAddress;
    private String bankId;
    private String bankName;

    public AddBankBean(String bankNumber, String bankId) {
        this.bankNumber = bankNumber;
        this.bankId = bankId;
    }

    public AddBankBean(String bankNumber, String bankAddress, String bankId) {

        this.bankNumber = bankNumber;
        this.bankAddress = bankAddress;
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}
