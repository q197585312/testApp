package nanyang.com.dig88.NewKeno;

/**
 * Created by Administrator on 2018/9/17.
 */

public class NewKenoResultNumBean {
    private String num;
    private boolean isCheck;

    public NewKenoResultNumBean(String num, boolean isCheck) {
        this.num = num;
        this.isCheck = isCheck;
    }

    public String getNum() {
        return num;

    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
