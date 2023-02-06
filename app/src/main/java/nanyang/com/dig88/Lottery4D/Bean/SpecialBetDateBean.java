package nanyang.com.dig88.Lottery4D.Bean;

import java.util.List;

/**
 * Created by Administrator on 2019/2/26.
 */

public class SpecialBetDateBean {
    private String betPoolId;
    private String betDate;
    private List<String> dateList;

    public SpecialBetDateBean(String betPoolId, String betDate, List<String> dateList) {
        this.betPoolId = betPoolId;
        this.betDate = betDate;
        this.dateList = dateList;
    }

    public String getBetPoolId() {
        return betPoolId;
    }

    public void setBetPoolId(String betPoolId) {
        this.betPoolId = betPoolId;
    }

    public String getBetDate() {
        return betDate;
    }

    public void setBetDate(String betDate) {
        this.betDate = betDate;
    }

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }
}
