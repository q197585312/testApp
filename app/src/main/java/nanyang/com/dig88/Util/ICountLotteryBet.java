package nanyang.com.dig88.Util;

import nanyang.com.dig88.Entity.LotteryCountBean;
import nanyang.com.dig88.Entity.LotteryPromptBean;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface ICountLotteryBet {
    void updatePrompt(LotteryPromptBean prompt);
    void updateTotal(LotteryCountBean count);
}
