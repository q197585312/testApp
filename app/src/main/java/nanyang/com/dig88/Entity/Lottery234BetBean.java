package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2016/2/21.
 */
public class Lottery234BetBean {
    String number0="";
    String money0="";
    String count0="";
    String number1="";
    String money1="";
    String count1="";
    String kei0="";
    String kei1="";

    public Lottery234BetBean(String number0, String number1,String money0, String money1,String count0, String count1,String kei0,String kei1) {
        this.count0 = count0;
        this.count1 = count1;
        this.money0 = money0;
        this.money1 = money1;
        this.number0 = number0;
        this.number1 = number1;
        this.kei0=kei0;
        this.kei1=kei1;
    }

    public Lottery234BetBean() {
    }

    public String getCount0() {
        return count0;
    }

    public void setCount0(String count0) {
        this.count0 = count0;
    }

    public String getCount1() {
        return count1;
    }

    public void setCount1(String count1) {
        this.count1 = count1;
    }

    public String getMoney0() {
        return money0;
    }

    public void setMoney0(String money0) {
        this.money0 = money0;
    }

    public String getMoney1() {
        return money1;
    }

    public void setMoney1(String money1) {
        this.money1 = money1;
    }

    public String getNumber0() {
        return number0;
    }

    public void setNumber0(String number0) {
        this.number0 = number0;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getKei0() {
        return kei0;
    }

    public void setKei0(String kei0) {
        this.kei0 = kei0;
    }

    public String getKei1() {
        return kei1;
    }

    public void setKei1(String kei1) {
        this.kei1 = kei1;
    }
}
