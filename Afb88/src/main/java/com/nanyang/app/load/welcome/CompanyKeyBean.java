package com.nanyang.app.load.welcome;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/1/8.
 */

class CompanyKeyBean implements Serializable {
    String companyKey;
    String userName;

    public CompanyKeyBean(String companyKey, String userName) {
        this.companyKey = companyKey;
        this.userName = userName;
    }

    public String getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
