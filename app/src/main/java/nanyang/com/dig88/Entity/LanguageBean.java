package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2019/3/29.
 */

public class LanguageBean {
    private String lg;
    private int res;
    private boolean isChoice;

    public LanguageBean(String lg, int res, boolean isChoice) {
        this.lg = lg;
        this.res = res;
        this.isChoice = isChoice;
    }

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
