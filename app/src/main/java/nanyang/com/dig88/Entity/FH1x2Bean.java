package nanyang.com.dig88.Entity;

public class FH1x2Bean {
        @com.google.gson.annotations.SerializedName("1")
        private String f1="";
        @com.google.gson.annotations.SerializedName("2")
        private String f2="";
        @com.google.gson.annotations.SerializedName("oid")
        private int oid;
        @com.google.gson.annotations.SerializedName("x")
        private String x="";

        public String getF1() {
            return f1;
        }

        public void setF1(String f1) {
            this.f1 = f1;
        }

        public String getF2() {
            return f2;
        }

        public void setF2(String f2) {
            this.f2 = f2;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }
    }