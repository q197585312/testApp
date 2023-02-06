package gaming178.com.casinogame.Util;

import android.view.View;

import gaming178.com.baccaratgame.R;

/**
 * Created by Administrator on 2016/8/18.
 */
public class BetUiHelper {
    public  static void betStateColor(View btn, boolean isBet){
        if(isBet)
            btn.setBackgroundResource(R.drawable.gd_rectangle_yellow_eed2560b_corner5_bet_btn);
        else{
            btn.setBackgroundResource(R.drawable.gd_rectangle_grey6e6e6e_corner5_bet_btn);
        }
    }
    public  static void betStateColor(ChipShowHelper chipHelperCurrent,View btn, boolean isBet){
        if(chipHelperCurrent!=null)
            chipHelperCurrent.setOperationButtonDisplay(isBet);
        betStateColor(btn, isBet);
    }
}
