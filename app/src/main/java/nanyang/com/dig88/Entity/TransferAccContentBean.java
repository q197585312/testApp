package nanyang.com.dig88.Entity;

import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by 47184 on 2019/7/5.
 */

public class TransferAccContentBean {
    private String gameName;
    private String gameType;
    private String gameBalance;
    private TextView tvBalance;
    private ProgressBar pbLoading;

    public TransferAccContentBean() {

    }

    public TransferAccContentBean(String gameName, String gameType, String gameBalance) {
        this.gameName = gameName;
        this.gameType = gameType;
        this.gameBalance = gameBalance;
    }

    public ProgressBar getPbLoading() {
        return pbLoading;
    }

    public void setPbLoading(ProgressBar pbLoading) {
        this.pbLoading = pbLoading;
    }

    public TextView getTvBalance() {
        return tvBalance;
    }

    public void setTvBalance(TextView tvBalance) {
        this.tvBalance = tvBalance;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameBalance() {
        return gameBalance;
    }

    public void setGameBalance(String gameBalance) {
        this.gameBalance = gameBalance;
    }
}
