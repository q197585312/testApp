package nanyang.com.dig88.Lottery;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/28.
 */
public class CrossBean implements Serializable{
    String number="";
    String money2d="";
    String money3d="";
    String money4d="";
    String count="";
    int multiple2d;
    int multiple3d;
    int multiple4d;

    public CrossBean() {
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMoney2d() {
        return money2d;
    }

    public void setMoney2d(String money2d) {
        this.money2d = money2d;
    }

    public String getMoney3d() {
        return money3d;
    }

    public void setMoney3d(String money3d) {
        this.money3d = money3d;
    }

    public String getMoney4d() {
        return money4d;
    }

    public void setMoney4d(String money4d) {
        this.money4d = money4d;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getMultiple2d() {
        return multiple2d;
    }

    public void setMultiple2d(int multiple2d) {
        this.multiple2d = multiple2d;
    }

    public int getMultiple3d() {
        return multiple3d;
    }

    public void setMultiple3d(int multiple3d) {
        this.multiple3d = multiple3d;
    }

    public int getMultiple4d() {
        return multiple4d;
    }

    public void setMultiple4d(int multiple4d) {
        this.multiple4d = multiple4d;
    }
}
