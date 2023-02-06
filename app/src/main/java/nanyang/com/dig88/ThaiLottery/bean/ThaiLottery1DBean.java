package nanyang.com.dig88.ThaiLottery.bean;

/**
 * Created by Administrator on 2017/7/24.
 */

public class ThaiLottery1DBean {
    private String discount1DTop;
    private String discount1DBottom;
    private String odds1DTop;
    private String odds1DBottom;
    private String min1DTop;
    private String max1DTop;
    private String min1DBottom;
    private String max1DBottom;
    private String topAmount = "";
    private String bottomAmount = "";
    private String topReallyAmount = "";
    private String bottomReallyAmount = "";
    private String num = "";
    private String playtype1DTop;
    private String playtype1DBottom;

    public ThaiLottery1DBean(String playtype1DTop, String playtype1DBottom, String discount1DTop, String discount1DBottom, String odds1DTop, String odds1DBottom, String min1DTop, String max1DTop, String min1DBottom, String max1DBottom, String num) {
        this.discount1DTop = discount1DTop;
        this.discount1DBottom = discount1DBottom;
        this.odds1DTop = odds1DTop;
        this.odds1DBottom = odds1DBottom;
        this.min1DTop = min1DTop;
        this.max1DTop = max1DTop;
        this.min1DBottom = min1DBottom;
        this.max1DBottom = max1DBottom;
        this.num = num;
        this.playtype1DTop = playtype1DTop;
        this.playtype1DBottom = playtype1DBottom;
    }

    public String getPlaytype1DTop() {
        return playtype1DTop;
    }

    public void setPlaytype1DTop(String playtype1DTop) {
        this.playtype1DTop = playtype1DTop;
    }

    public String getPlaytype1DBottom() {
        return playtype1DBottom;
    }

    public void setPlaytype1DBottom(String playtype1DBottom) {
        this.playtype1DBottom = playtype1DBottom;
    }

    public String getDiscount1DTop() {
        return discount1DTop;
    }

    public void setDiscount1DTop(String discount1DTop) {
        this.discount1DTop = discount1DTop;
    }

    public String getDiscount1DBottom() {
        return discount1DBottom;
    }

    public void setDiscount1DBottom(String discount1DBottom) {
        this.discount1DBottom = discount1DBottom;
    }

    public String getOdds1DTop() {
        return odds1DTop;
    }

    public void setOdds1DTop(String odds1DTop) {
        this.odds1DTop = odds1DTop;
    }

    public String getOdds1DBottom() {
        return odds1DBottom;
    }

    public void setOdds1DBottom(String odds1DBottom) {
        this.odds1DBottom = odds1DBottom;
    }

    public String getMin1DTop() {
        return min1DTop;
    }

    public void setMin1DTop(String min1DTop) {
        this.min1DTop = min1DTop;
    }

    public String getMax1DTop() {
        return max1DTop;
    }

    public void setMax1DTop(String max1DTop) {
        this.max1DTop = max1DTop;
    }

    public String getMin1DBottom() {
        return min1DBottom;
    }

    public void setMin1DBottom(String min1DBottom) {
        this.min1DBottom = min1DBottom;
    }

    public String getMax1DBottom() {
        return max1DBottom;
    }

    public void setMax1DBottom(String max1DBottom) {
        this.max1DBottom = max1DBottom;
    }

    public String getTopAmount() {
        return topAmount;
    }

    public void setTopAmount(String topAmount) {
        this.topAmount = topAmount;
    }

    public String getBottomAmount() {
        return bottomAmount;
    }

    public void setBottomAmount(String bottomAmount) {
        this.bottomAmount = bottomAmount;
    }

    public String getTopReallyAmount() {
        return topReallyAmount;
    }

    public void setTopReallyAmount(String topReallyAmount) {
        this.topReallyAmount = topReallyAmount;
    }

    public String getBottomReallyAmount() {
        return bottomReallyAmount;
    }

    public void setBottomReallyAmount(String bottomReallyAmount) {
        this.bottomReallyAmount = bottomReallyAmount;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
