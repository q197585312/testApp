package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/30.
 */
public class VsCellBean1 implements Serializable {
    CharSequence key;
    String value;
    /**
     * 下注类型
     */
    String b;
    /**
     * 类型
     */
    String Sc;
    int oid;

    public VsCellBean1(String key, String value, String b, String Sc, int oid) {
        this(key,value,Sc,oid);
        this.b=b;

    }

    public VsCellBean1(String key, String value, String Sc, int oid) {
        this(key,value,oid);
        this.Sc=Sc;
    }

    public VsCellBean1(CharSequence key, String value, int oid) {
        this.key = key;
        this.value = value;
        this.oid=oid;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getSc() {
        return Sc;
    }

    public void setSc(String sc) {
        Sc = sc;
    }

    public CharSequence getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

}
