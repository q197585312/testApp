package nanyang.com.dig88.Entity;

import android.text.Html;

public class FTOEBean {
        @com.google.gson.annotations.SerializedName("oid")
        private int oid;
        @com.google.gson.annotations.SerializedName("ODD")
        private String ODD="";
        @com.google.gson.annotations.SerializedName("EVEN")
        private String EVEN="";

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public String getODD() {
            return Html.fromHtml(ODD).toString().trim();
        }

        public void setODD(String ODD) {
            this.ODD = ODD;
        }

        public String getEVEN() {
            return Html.fromHtml(EVEN).toString().trim();
        }

        public void setEVEN(String EVEN) {
            this.EVEN = EVEN;
        }
    }