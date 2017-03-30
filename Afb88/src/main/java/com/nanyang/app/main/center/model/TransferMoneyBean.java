package com.nanyang.app.main.center.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class TransferMoneyBean {
    private List<DicAllBean> dicAll;

    public List<DicAllBean> getDicAll() {
        return dicAll;
    }

    public void setDicAll(List<DicAllBean> dicAll) {
        this.dicAll = dicAll;
    }

    public static class DicAllBean {
        /**
         * loginName : demoafbai11
         * currency : MYR
         * cashBalance : -197.79
         * outStanding : 33
         * totalCredit : 5,000
         * credit : 4,769
         * minLimit : 10
         * totalBalance : -168
         * totalSportBookBalance : -198
         * eBalance : 0.00
         * gdBalance : 30.00
         * 855Balance : 0.00
         * w88Balance : 0.00
         */

        private String loginName;
        private String currency;
        private String cashBalance;
        private String outStanding;
        private String totalCredit;
        private String credit;
        private String minLimit;
        private String totalBalance;
        private String totalSportBookBalance;
        private String eBalance;
        private String gdBalance;
        @SerializedName("855Balance")
        private String _$855Balance;
        private String w88Balance;

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getCashBalance() {
            return cashBalance;
        }

        public void setCashBalance(String cashBalance) {
            this.cashBalance = cashBalance;
        }

        public String getOutStanding() {
            return outStanding;
        }

        public void setOutStanding(String outStanding) {
            this.outStanding = outStanding;
        }

        public String getTotalCredit() {
            return totalCredit;
        }

        public void setTotalCredit(String totalCredit) {
            this.totalCredit = totalCredit;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getMinLimit() {
            return minLimit;
        }

        public void setMinLimit(String minLimit) {
            this.minLimit = minLimit;
        }

        public String getTotalBalance() {
            return totalBalance;
        }

        public void setTotalBalance(String totalBalance) {
            this.totalBalance = totalBalance;
        }

        public String getTotalSportBookBalance() {
            return totalSportBookBalance;
        }

        public void setTotalSportBookBalance(String totalSportBookBalance) {
            this.totalSportBookBalance = totalSportBookBalance;
        }

        public String getEBalance() {
            return eBalance;
        }

        public void setEBalance(String eBalance) {
            this.eBalance = eBalance;
        }

        public String getGdBalance() {
            return gdBalance;
        }

        public void setGdBalance(String gdBalance) {
            this.gdBalance = gdBalance;
        }

        public String get_$855Balance() {
            return _$855Balance;
        }

        public void set_$855Balance(String _$855Balance) {
            this._$855Balance = _$855Balance;
        }

        public String getW88Balance() {
            return w88Balance;
        }

        public void setW88Balance(String w88Balance) {
            this.w88Balance = w88Balance;
        }
    }
}
