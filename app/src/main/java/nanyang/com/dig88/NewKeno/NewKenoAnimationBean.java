package nanyang.com.dig88.NewKeno;

/**
 * Created by Administrator on 2018/9/17.
 */

public class NewKenoAnimationBean {
    private String index;
    private String number;
    private String total;

    public NewKenoAnimationBean(String index, String number, String total) {
        this.index = index;
        this.number = number;
        this.total = total;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
