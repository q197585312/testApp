package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/3/21.
 */

public class Afb33OnlineBean {
    String payType;
    int payTypePic;
    boolean isCanClick;

    public Afb33OnlineBean(String payType, int payTypePic) {
        this.payType = payType;
        this.payTypePic = payTypePic;
    }

    public boolean isCanClick() {
        return isCanClick;
    }

    public void setCanClick(boolean canClick) {
        isCanClick = canClick;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public int getPayTypePic() {
        return payTypePic;
    }

    public void setPayTypePic(int payTypePic) {
        this.payTypePic = payTypePic;
    }
}
