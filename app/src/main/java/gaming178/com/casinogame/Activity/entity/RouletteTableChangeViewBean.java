package gaming178.com.casinogame.Activity.entity;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2020/6/12.
 */

public class RouletteTableChangeViewBean {
    TextView tv_game_number01;
    TextView tv_roulette_red01;
    TextView tv_roulette_black01;
    TextView tv_roulette_zero01;
    TextView tv_roulette_even01;
    TextView tv_roulette_odd01;
    TextView tv_roulette_big01;
    TextView tv_roulette_small01;
    View view_Parent;

    public RouletteTableChangeViewBean(TextView tv_game_number01, TextView tv_roulette_red01, TextView tv_roulette_black01, TextView tv_roulette_zero01, TextView tv_roulette_even01, TextView tv_roulette_odd01, TextView tv_roulette_big01, TextView tv_roulette_small01) {
        this.tv_game_number01 = tv_game_number01;
        this.tv_roulette_red01 = tv_roulette_red01;
        this.tv_roulette_black01 = tv_roulette_black01;
        this.tv_roulette_zero01 = tv_roulette_zero01;
        this.tv_roulette_even01 = tv_roulette_even01;
        this.tv_roulette_odd01 = tv_roulette_odd01;
        this.tv_roulette_big01 = tv_roulette_big01;
        this.tv_roulette_small01 = tv_roulette_small01;
    }

    public View getView_Parent() {
        return view_Parent;
    }

    public void setView_Parent(View view_Parent) {
        this.view_Parent = view_Parent;
    }

    public TextView getTv_game_number01() {
        return tv_game_number01;
    }

    public void setTv_game_number01(TextView tv_game_number01) {
        this.tv_game_number01 = tv_game_number01;
    }

    public TextView getTv_roulette_red01() {
        return tv_roulette_red01;
    }

    public void setTv_roulette_red01(TextView tv_roulette_red01) {
        this.tv_roulette_red01 = tv_roulette_red01;
    }

    public TextView getTv_roulette_black01() {
        return tv_roulette_black01;
    }

    public void setTv_roulette_black01(TextView tv_roulette_black01) {
        this.tv_roulette_black01 = tv_roulette_black01;
    }

    public TextView getTv_roulette_zero01() {
        return tv_roulette_zero01;
    }

    public void setTv_roulette_zero01(TextView tv_roulette_zero01) {
        this.tv_roulette_zero01 = tv_roulette_zero01;
    }

    public TextView getTv_roulette_even01() {
        return tv_roulette_even01;
    }

    public void setTv_roulette_even01(TextView tv_roulette_even01) {
        this.tv_roulette_even01 = tv_roulette_even01;
    }

    public TextView getTv_roulette_odd01() {
        return tv_roulette_odd01;
    }

    public void setTv_roulette_odd01(TextView tv_roulette_odd01) {
        this.tv_roulette_odd01 = tv_roulette_odd01;
    }

    public TextView getTv_roulette_big01() {
        return tv_roulette_big01;
    }

    public void setTv_roulette_big01(TextView tv_roulette_big01) {
        this.tv_roulette_big01 = tv_roulette_big01;
    }

    public TextView getTv_roulette_small01() {
        return tv_roulette_small01;
    }

    public void setTv_roulette_small01(TextView tv_roulette_small01) {
        this.tv_roulette_small01 = tv_roulette_small01;
    }
}
