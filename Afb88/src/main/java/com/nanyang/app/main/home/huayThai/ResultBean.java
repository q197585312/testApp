package com.nanyang.app.main.home.huayThai;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/31.
 */

public class ResultBean implements Serializable {

    /**
     * number : ok
     */

    private List<DicAllBean> dicAll;

    public List<DicAllBean> getDicAll() {
        return dicAll;
    }

    public void setDicAll(List<DicAllBean> dicAll) {
        this.dicAll = dicAll;
    }

    public static class DicAllBean {
        private String number;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
