package nanyang.com.dig88.Entity;

public class FHDCBean {
        @com.google.gson.annotations.SerializedName("12")
        private String f12="";
        @com.google.gson.annotations.SerializedName("oid")
        private int oid;
        @com.google.gson.annotations.SerializedName("1x")
        private String f1x="";
        @com.google.gson.annotations.SerializedName("x2")
        private String fx2="";

        public String getF12() {
            return f12;
        }

        public void setF12(String f12) {
            this.f12 = f12;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public String getF1x() {
            return f1x;
        }

        public void setF1x(String f1x) {
            this.f1x = f1x;
        }

        public String getFx2() {
            return fx2;
        }

        public void setFx2(String fx2) {
            this.fx2 = fx2;
        }
    }