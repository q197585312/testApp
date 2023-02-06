package nanyang.com.dig88.Lottery;

import java.util.List;

/**
 * Created by Administrator on 2016/2/24.
 */
public class CombinationBean {
    String title;
    String  odds;
    String discount;
    String money;
    String count;
    List<SelectedItemBean> list;
    private String kei;
    private String kei2;
    private String kei3;
    private String kei4;
    public CombinationBean( List<SelectedItemBean> list,String discount, String odds, String title,String money,String count,String kei) {
        this.discount = discount;
        this.list = list;
        this.odds = odds;
        this.title = title;
        this.money=money;
        this.count=count;
        this.kei=kei;
    }

    public String getKei() {
        return kei;
    }

    public void setKei(String kei) {
        this.kei = kei;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<SelectedItemBean> getList() {
        return list;
    }

    public void setList(List<SelectedItemBean> list) {
        this.list = list;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKei2() {
        return kei2;
    }

    public void setKei2(String kei2) {
        this.kei2 = kei2;
    }

    public String getKei3() {
        return kei3;
    }

    public void setKei3(String kei3) {
        this.kei3 = kei3;
    }

    public String getKei4() {
        return kei4;
    }

    public void setKei4(String kei4) {
        this.kei4 = kei4;
    }
}
