package com.nanyang.app.main.home.keno.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/24.
 */

public class KenoBetLimitBean {

    private List<MinMaxDataBean> minMaxData;

    public List<MinMaxDataBean> getMinMaxData() {
        return minMaxData;
    }

    public void setMinMaxData(List<MinMaxDataBean> minMaxData) {
        this.minMaxData = minMaxData;
    }

    public static class MinMaxDataBean {
        /**
         * minLimit : 1
         * maxLimit : 5000
         * AmtS : 0
         */

        private int minLimit;
        private String maxLimit;
        private String AmtS;

        public int getMinLimit() {
            return minLimit;
        }

        public void setMinLimit(int minLimit) {
            this.minLimit = minLimit;
        }

        public String getMaxLimit() {
            return maxLimit;
        }

        public void setMaxLimit(String maxLimit) {
            this.maxLimit = maxLimit;
        }

        public String getAmtS() {
            return AmtS;
        }

        public void setAmtS(String AmtS) {
            this.AmtS = AmtS;
        }
    }
}
