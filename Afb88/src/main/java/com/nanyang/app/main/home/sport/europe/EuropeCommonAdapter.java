package com.nanyang.app.main.home.sport.europe;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.AfbParseHelper;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BaseMixStyleHandler;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by ASUS on 2019/4/13.
 */

public class EuropeCommonAdapter extends BallAdapterHelper<BallInfo> {
    public EuropeCommonAdapter(Context context) {
        super(context);
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {
        super.onConvert(helper, position, item);

        updateMixBackground(helper, item);
    }

    @Override
    public void handleOddsContent(MyRecyclerViewHolder helper, BallInfo item, int position) {
        TextView full1 = helper.getTextView(R.id.europe_1_full_time_odds_tv);
        TextView full2 = helper.getTextView(R.id.europe_2_full_time_odds_tv);
        TextView fullx = helper.getTextView(R.id.europe_x_full_time_odds_tv);
        if (item.getHasX12().equals("1")) {

            full1.setText(item.getX12_1Odds());
            fullx.setText(item.getX12_XOdds());
            full2.setText(item.getX12_2Odds());
            full1.setOnClickListener(new ItemClick(back, position, item, item.getX12_1Odds(), "1", false));
            fullx.setOnClickListener(new ItemClick(back, position, item, item.getX12_XOdds(), "X", false));
            full2.setOnClickListener(new ItemClick(back, position, item, item.getX12_2Odds(), "2", false));
        } else {
            full1.setText("");
            full2.setText("");
            fullx.setText("");
        }
    }

    public int onSetAdapterItemLayout() {
        return R.layout.sport_common_europe_ball_item;
    }

    private void updateMixBackground(MyRecyclerViewHolder helper, BallInfo item) {
        BaseMixStyleHandler handler = new BaseMixStyleHandler((BaseToolbarActivity) context);
        String itemFullSocOddsId = item.getSocOddsId();

        AfbClickBetBean mixItem = handler.getMixItem(itemFullSocOddsId);


        TextView fullTv1 = (TextView) helper.getView(R.id.europe_1_full_time_odds_tv);
        handler.setCommonBackground(fullTv1);
        TextView fullTvX = (TextView) helper.getView(R.id.europe_x_full_time_odds_tv);
        handler.setCommonBackground(fullTvX);
        TextView fullTv2 = (TextView) helper.getView(R.id.europe_2_full_time_odds_tv);
        handler.setCommonBackground(fullTv2);
        if (mixItem != null) {
            String transType = new AfbParseHelper().getBetTypeFromId(mixItem.getId());
            if (transType.startsWith("1")) {
                setMixBackground(handler, fullTv1);
            } else if (transType.startsWith("X")) {
                setMixBackground(handler, fullTvX);
            } else if (transType.startsWith("2")) {
                setMixBackground(handler, fullTv2);
            }


        }
    }

    private void setMixBackground(BaseMixStyleHandler handler, TextView fullTv) {
        handler.setMixBackground(fullTv);
    }

}

class ItemClick implements View.OnClickListener {
    SportAdapterHelper.ItemCallBack<BallInfo> back;
    int position;
    BallInfo item;
    String odds;
    String type;
    boolean isHf;

    public ItemClick(SportAdapterHelper.ItemCallBack<BallInfo> back, int position, BallInfo item, String odds, String type, boolean isHf) {
        this.position = position;
        this.item = item;
        this.odds = odds;
        this.back = back;
        this.type = type;
        this.isHf = isHf;
    }

    @Override
    public void onClick(View v) {
        if (!odds.equals("") && Float.valueOf(odds) != 0)
            back.clickOdds((TextView) v, item, type, isHf, odds, Integer.valueOf(isHf ? item.getSocOddsId_FH() : item.getSocOddsId()), "");
    }
}


