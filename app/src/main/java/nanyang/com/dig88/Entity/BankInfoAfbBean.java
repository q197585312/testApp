package nanyang.com.dig88.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class BankInfoAfbBean {
    private String language;
    private List<BankInfoBean> bankList = new ArrayList<BankInfoBean>();

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<BankInfoBean> getBankList() {
        return bankList;
    }


}
