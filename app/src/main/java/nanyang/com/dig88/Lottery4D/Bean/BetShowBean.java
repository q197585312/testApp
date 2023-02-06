package nanyang.com.dig88.Lottery4D.Bean;

/**
 * Created by Administrator on 2018/11/21.
 */

public class BetShowBean {
    String betType;
    String betNumber;
    String bigBetAmount;
    String smallBetAmount;
    String betDate;

    public BetShowBean(String betType, String betNumber, String bigBetAmount, String smallBetAmount, String betDate) {
        this.betType = betType;
        this.betNumber = betNumber;
        this.bigBetAmount = bigBetAmount;
        this.smallBetAmount = smallBetAmount;
        this.betDate = betDate;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public String getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(String betNumber) {
        this.betNumber = betNumber;
    }

    public String getBigBetAmount() {
        return bigBetAmount;
    }

    public void setBigBetAmount(String bigBetAmount) {
        this.bigBetAmount = bigBetAmount;
    }

    public String getSmallBetAmount() {
        return smallBetAmount;
    }

    public void setSmallBetAmount(String smallBetAmount) {
        this.smallBetAmount = smallBetAmount;
    }

    public String getBetDate() {
        return betDate;
    }

    public void setBetDate(String betDate) {
        this.betDate = betDate;
    }
}
