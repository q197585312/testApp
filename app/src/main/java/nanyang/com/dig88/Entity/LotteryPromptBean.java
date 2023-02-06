package nanyang.com.dig88.Entity;

/**
 * Created by Administrator on 2016/2/23.
 */
public class LotteryPromptBean {

    String singlePrompt;
    String totalPrompt;


    public LotteryPromptBean(String singlePrompt, String totalPrompt) {
        this.singlePrompt = singlePrompt;
        this.totalPrompt = totalPrompt;
    }

    public String getSinglePrompt() {
        return singlePrompt;
    }

    public void setSinglePrompt(String singlePrompt) {
        this.singlePrompt = singlePrompt;
    }

    public String getTotalPrompt() {
        return totalPrompt;
    }

    public void setTotalPrompt(String totalPrompt) {
        this.totalPrompt = totalPrompt;
    }
}
