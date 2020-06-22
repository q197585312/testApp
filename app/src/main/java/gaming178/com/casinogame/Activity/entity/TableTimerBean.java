package gaming178.com.casinogame.Activity.entity;

import android.widget.TextView;

/**
 * Created by Administrator on 2020/5/15.
 */

public class TableTimerBean {
    private TextView tvTimer;
    private String type;

    public TableTimerBean() {
    }

    public TableTimerBean(TextView tvTimer, String type) {
        this.tvTimer = tvTimer;
        this.type = type;
    }

    public TextView getTvTimer() {
        return tvTimer;
    }

    public void setTvTimer(TextView tvTimer) {
        this.tvTimer = tvTimer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
