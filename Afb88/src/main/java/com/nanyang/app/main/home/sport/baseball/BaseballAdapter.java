package com.nanyang.app.main.home.sport.baseball;

import android.content.Context;
import android.view.View;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BaseMixStyleHandler;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/6/6.
 */

public class BaseballAdapter extends BallAdapterHelper<BallInfo> {
    public BaseballAdapter(Context context) {
        super(context);
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {
        super.onConvert(helper, position, item);

        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        View markAdd = helper.getView(R.id.module_right_mark_tv);
        markAdd.setVisibility(View.GONE);
        tvCollection.setVisibility(View.GONE);
        View vp = helper.getView(R.id.module_center_sl);
        vp.getLayoutParams().width = DeviceUtils.dip2px(context, 210);
        ScrollLayout sl = helper.getView(R.id.module_center_sl);

        sl.getChildAt(0).setVisibility(View.VISIBLE);
        ViewHolder holder = new ViewHolder(sl.getChildAt(0));
        setUpDownOdds(true, item, false, item.getIsX12New(), item.getHasX12(), "", holder.viewpagerMatchHomeHdpTv, holder.viewpagerMatchVisitHdpTv, holder.viewpagerMatchHomeHdpoddsTv, holder.viewpagerMatchVisitHdpoddsTv
                , item.getX12_1Odds(), item.getX12_2Odds(), "1", "2");

        setUpDownOdds(true, item, false, item.getIsHdpNew(), item.getHasHdp(), item.getHdp(), holder.viewpagerMatchOuTv, holder.viewpagerMatchOu2Tv, holder.viewpagerMatchOveroddsTv, holder.viewpagerMatchUnderoddsTv
                , item.getHOdds(), item.getAOdds(), "home", "away");
        setUpDownOdds(true, item, false, item.getIsOUNew(), item.getHasOU(), item.getOU(), holder.viewpagerOddLabelTv, holder.viewpagerEvenLabelTv, holder.viewpagerMatchOddTv, holder.viewpagerMatchEvenTv
                , item.getOOdds(), item.getUOdds(), "over", "under");

        sl.getChildAt(1).setVisibility(View.VISIBLE);
        ViewHolder ho = new ViewHolder(sl.getChildAt(1));

        setUpDownOdds(true, item, false, item.getIsOENew(), item.getHasOE(), "", ho.viewpagerMatchHomeHdpTv, ho.viewpagerMatchVisitHdpTv, ho.viewpagerMatchHomeHdpoddsTv, ho.viewpagerMatchVisitHdpoddsTv
                , item.getOddOdds(), item.getEvenOdds(), "odd", "even");

        setUpDownOdds(true, item, true, item.getIsHdpNew_FH(), item.getHasHdp_FH(), item.getHdp_FH(), ho.viewpagerMatchOuTv, ho.viewpagerMatchOu2Tv, ho.viewpagerMatchOveroddsTv, ho.viewpagerMatchUnderoddsTv
                , item.getHOdds_FH(), item.getAOdds_FH(), "home", "away");
        setUpDownOdds(true, item, true, item.getIsOUNew_FH(), item.getHasOU_FH(), item.getOU_FH(), ho.viewpagerOddLabelTv, ho.viewpagerEvenLabelTv, ho.viewpagerMatchOddTv, ho.viewpagerMatchEvenTv
                , item.getOOdds_FH(), item.getUOdds_FH(), "over", "under");
        BaseMixStyleHandler baseMixStyleHandler = new BaseMixStyleHandler((BaseToolbarActivity) context);
        baseMixStyleHandler.updateAfbMixBackground(item.getSocOddsId(), 0, sl, "1", "2", "home", "away", "over", "under");
        baseMixStyleHandler.updateAfbMixBackground(item.getSocOddsId(), 1, sl, "odd", "even", "NULL", "NULL", "NULL", "NULL");
        baseMixStyleHandler.updateAfbMixBackground(item.getSocOddsId_FH(), 1, sl, "NULL", "NULL", "home", "away", "over", "under");
    }
}
