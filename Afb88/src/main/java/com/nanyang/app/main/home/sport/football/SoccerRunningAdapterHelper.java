package com.nanyang.app.main.home.sport.football;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/14.
 */

public class SoccerRunningAdapterHelper extends SoccerCommonAdapterHelper {

    public SoccerRunningAdapterHelper(Context context) {
        super(context);
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final BallInfo item) {
        super.onConvert(helper, position, item);
        setRunningItemBg(helper, item);
        TextView dateTv = helper.getView(R.id.module_match_date_tv);
        TextView liveTv = helper.getView(R.id.module_match_live_iv);
        TextView timeTv = helper.getView(R.id.module_match_time_tv);

        liveTv.setVisibility(View.GONE);
        dateTv.setVisibility(View.GONE);
        handleLiveTimeTv(item, timeTv);
        dateTv.setTextColor(Color.RED);
    }


    protected void handleLiveTimeTv(BallInfo item, TextView timeTv) {
        SoccerRunningGoalManager.getInstance().runTimeStyle(timeTv, item.getMExtraTime(), item.getStatus(), item.getCurMinute(), item.getLive());

        if (act != null && act.videoHolder != null && act.fl_top_video.getVisibility() == View.VISIBLE && act.videoHolder.tv_run_time.getVisibility() == View.VISIBLE
                && act.itemBallAdded != null && act.itemBallAdded.getSocOddsId().equals(item.getSocOddsId())
                ) {
            SoccerRunningGoalManager.getInstance().runTimeStyleColor(act.videoHolder.tv_run_time, item.getMExtraTime(), item.getStatus(), item.getCurMinute(), item.getLive(), Color.WHITE);
        }
    }


    @Override
    public void showLastCall(BallInfo item, TextView dateTv, ImageView lastGif, TextView dateTv1, TextView timeTv, TextView timeTv1, TextView liveTv, TextView liveTv1) {
//        super.showLastCall(item, dateTv, lastGif, dateTv1, timeTv, timeTv1, liveTv, liveTv1);
    }
}
