package com.nanyang.app.main.home.sportInterface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.unkonw.testapp.training.ScrollLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class SoccerHeaderContent implements IHeaderContentHelper {
    public static ScrollLayout.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    @Override
    public void setHeaderContent(Context context, ScrollLayout slHeader) {
        slHeader.removeAllViews();
        LayoutInflater from = LayoutInflater.from(context);
        View v1=from.inflate(R.layout.sport_head_vp_item, null);
        View v2=from.inflate(R.layout.sport_head_vp_item, null);

        ViewHolder viewHolder = new ViewHolder(v1);
        viewHolder.tvHeadLeft.setText("FULL   H/A");
        viewHolder.tvHeadRight.setText("FULL   O/U");

        slHeader.addView(v1,layoutParams);

        ViewHolder viewHolder2 = new ViewHolder(v2);
        viewHolder2.tvHeadLeft.setText("HALF   H/A");
        viewHolder2.tvHeadRight.setText("HALF   O/U");
        slHeader.addView(v2,layoutParams);

    }
    static class ViewHolder {
        @Bind(R.id.tv_head_left)
        TextView tvHeadLeft;
        @Bind(R.id.tv_head_right)
        TextView tvHeadRight;
        @Bind(R.id.tv_head_3)
        TextView tvHead3;
        @Bind(R.id.ll_head_parent)
        LinearLayout llHeadParent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
