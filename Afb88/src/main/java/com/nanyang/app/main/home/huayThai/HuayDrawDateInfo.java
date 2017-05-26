package com.nanyang.app.main.home.huayThai;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public class HuayDrawDateInfo implements Serializable {

    /**
     * Desc : 01/06/2017 - HUAY THAI TOP PRIZE 3D
     * Value : 12729669
     */

    private List<DicAllBean> dicAll;

    public List<DicAllBean> getDicAll() {
        return dicAll;
    }

    public void setDicAll(List<DicAllBean> dicAll) {
        this.dicAll = dicAll;
    }

    public static class DicAllBean {
        private String Desc;
        private String Value;

        public String getDesc() {
            return Desc;
        }

        public void setDesc(String Desc) {
            this.Desc = Desc;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }
    }
}
