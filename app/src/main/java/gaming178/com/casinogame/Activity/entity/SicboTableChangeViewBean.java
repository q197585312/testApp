package gaming178.com.casinogame.Activity.entity;

import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2020/6/12.
 */

public class SicboTableChangeViewBean {
    LinearLayout linearlayout;
    TextView tv_sicbo_number01;
    TextView tv_even01;
    TextView tv_small01;
    TextView tv_waidic01;
    TextView tv_big01;
    TextView tv_odd01;

    public SicboTableChangeViewBean(LinearLayout linearlayout, TextView tv_sicbo_number01, TextView tv_even01, TextView tv_small01, TextView tv_waidic01, TextView tv_big01, TextView tv_odd01) {
        this.linearlayout = linearlayout;
        this.tv_sicbo_number01 = tv_sicbo_number01;
        this.tv_even01 = tv_even01;
        this.tv_small01 = tv_small01;
        this.tv_waidic01 = tv_waidic01;
        this.tv_big01 = tv_big01;
        this.tv_odd01 = tv_odd01;
    }

    public LinearLayout getLinearlayout() {
        return linearlayout;
    }

    public void setLinearlayout(LinearLayout linearlayout) {
        this.linearlayout = linearlayout;
    }

    public TextView getTv_sicbo_number01() {
        return tv_sicbo_number01;
    }

    public void setTv_sicbo_number01(TextView tv_sicbo_number01) {
        this.tv_sicbo_number01 = tv_sicbo_number01;
    }

    public TextView getTv_even01() {
        return tv_even01;
    }

    public void setTv_even01(TextView tv_even01) {
        this.tv_even01 = tv_even01;
    }

    public TextView getTv_small01() {
        return tv_small01;
    }

    public void setTv_small01(TextView tv_small01) {
        this.tv_small01 = tv_small01;
    }

    public TextView getTv_waidic01() {
        return tv_waidic01;
    }

    public void setTv_waidic01(TextView tv_waidic01) {
        this.tv_waidic01 = tv_waidic01;
    }

    public TextView getTv_big01() {
        return tv_big01;
    }

    public void setTv_big01(TextView tv_big01) {
        this.tv_big01 = tv_big01;
    }

    public TextView getTv_odd01() {
        return tv_odd01;
    }

    public void setTv_odd01(TextView tv_odd01) {
        this.tv_odd01 = tv_odd01;
    }
}
