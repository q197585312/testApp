package gaming178.com.casinogame.Activity.entity;

import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2020/6/12.
 */

public class BaccaratTableChangeViewBean {
    int tableId;
    GridLayout layout;
    TextView tv_baccarat_shoe_number;
    TextView tv_baccarat_total_number;
    TextView tv_baccarat_banker_number;
    TextView tv_baccarat_player_number;
    TextView tv_baccarat_tie_number;
    TextView tv_baccarat_bp_number;
    TextView tv_baccarat_pp_number;
    View ll_good_road_parent;
    TextView tv_good_road_name;
    View view_Parent;

    public View getView_Parent() {
        return view_Parent;
    }

    public void setView_Parent(View view_Parent) {
        this.view_Parent = view_Parent;
    }

    public BaccaratTableChangeViewBean(int tableId, GridLayout layout, TextView tv_baccarat_shoe_number, TextView tv_baccarat_total_number, TextView tv_baccarat_banker_number, TextView tv_baccarat_player_number, TextView tv_baccarat_tie_number, TextView tv_baccarat_bp_number, TextView tv_baccarat_pp_number) {
        this.tableId = tableId;
        this.layout = layout;
        this.tv_baccarat_shoe_number = tv_baccarat_shoe_number;
        this.tv_baccarat_total_number = tv_baccarat_total_number;
        this.tv_baccarat_banker_number = tv_baccarat_banker_number;
        this.tv_baccarat_player_number = tv_baccarat_player_number;
        this.tv_baccarat_tie_number = tv_baccarat_tie_number;
        this.tv_baccarat_bp_number = tv_baccarat_bp_number;
        this.tv_baccarat_pp_number = tv_baccarat_pp_number;
    }

    public View getLl_good_road_parent() {
        return ll_good_road_parent;
    }

    public void setLl_good_road_parent(View ll_good_road_parent) {
        this.ll_good_road_parent = ll_good_road_parent;
    }

    public TextView getTv_good_road_name() {
        return tv_good_road_name;
    }

    public void setTv_good_road_name(TextView tv_good_road_name) {
        this.tv_good_road_name = tv_good_road_name;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public GridLayout getLayout() {
        return layout;
    }

    public void setLayout(GridLayout layout) {
        this.layout = layout;
    }

    public TextView getTv_baccarat_shoe_number() {
        return tv_baccarat_shoe_number;
    }

    public void setTv_baccarat_shoe_number(TextView tv_baccarat_shoe_number) {
        this.tv_baccarat_shoe_number = tv_baccarat_shoe_number;
    }

    public TextView getTv_baccarat_total_number() {
        return tv_baccarat_total_number;
    }

    public void setTv_baccarat_total_number(TextView tv_baccarat_total_number) {
        this.tv_baccarat_total_number = tv_baccarat_total_number;
    }

    public TextView getTv_baccarat_banker_number() {
        return tv_baccarat_banker_number;
    }

    public void setTv_baccarat_banker_number(TextView tv_baccarat_banker_number) {
        this.tv_baccarat_banker_number = tv_baccarat_banker_number;
    }

    public TextView getTv_baccarat_player_number() {
        return tv_baccarat_player_number;
    }

    public void setTv_baccarat_player_number(TextView tv_baccarat_player_number) {
        this.tv_baccarat_player_number = tv_baccarat_player_number;
    }

    public TextView getTv_baccarat_tie_number() {
        return tv_baccarat_tie_number;
    }

    public void setTv_baccarat_tie_number(TextView tv_baccarat_tie_number) {
        this.tv_baccarat_tie_number = tv_baccarat_tie_number;
    }

    public TextView getTv_baccarat_bp_number() {
        return tv_baccarat_bp_number;
    }

    public void setTv_baccarat_bp_number(TextView tv_baccarat_bp_number) {
        this.tv_baccarat_bp_number = tv_baccarat_bp_number;
    }

    public TextView getTv_baccarat_pp_number() {
        return tv_baccarat_pp_number;
    }

    public void setTv_baccarat_pp_number(TextView tv_baccarat_pp_number) {
        this.tv_baccarat_pp_number = tv_baccarat_pp_number;
    }
}
