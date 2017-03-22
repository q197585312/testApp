package com.nanyang.app.main.home.sport.myanmarOdds;

import android.content.Context;
import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sportInterface.SoccerHeaderContent;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

/**
 * Created by Administrator on 2017/3/14.
 */

public class MyanmarEarlyAdapterHelper extends MyanmarAdapterHelper {
    public MyanmarEarlyAdapterHelper(Context context) {
        super(context);
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final MyanmarInfo item) {
        super.onConvert(helper, position, item);
        ScrollLayout sl = helper.getView(R.id.module_center_sl);
         String hasMMHdp = "0";
        if(!item.MMHdpOdds.equals("0")&&Float.valueOf(item.MMHdpPct)!=-1.0){
            hasMMHdp="1";
        }
        String hasMMOu = "0";
        if(!item.MMOUOdds.equals("0")){
            hasMMOu="1";
        }
        String hdpMM=item.getMMHdp()+"("+Float.valueOf(item.getMMHdpPct())/100+")";

        //http://a8206d.a36588.com/_Bet/JRecPanel.aspx?b=mmaway&oId=12264226&odds=9.4
        String ouMM=item.getMMOU()+"("+Float.valueOf(item.getMMOUPct())/100+")";
        View afbView = scrollChild(true, item, item.getMMIsHomeGive(), hasMMHdp, hdpMM, hasMMOu, ouMM, "0", "0", item.getMMOUOdds() ,item.getMMOUOdds(), item.getMMHdpOdds(), item.getMMHdpOdds(),
                 "mmhome", "mmaway", "mmover", "mmunder",
                true, true,false, "","", "","", "", ""
        );
        sl.addView(afbView, SoccerHeaderContent.layoutParams);
    }


}
