package com.nanyang.app.main.home.sport.europe;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/3/13.
 */

public class EuropeRunningState extends EuropeState {
    public EuropeRunningState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_EUROPE_RUNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new EuropeEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new EuropeTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;

        }
    }

    @Override
    protected void onMatchNotRepeat(MyRecyclerViewHolder helper, final EuropeInfo item, final int position, final SportAdapterHelper.ItemCallBack<EuropeInfo> back) {
        super.onMatchNotRepeat(helper, item, position, back);
        ImageView ivHall = helper.getView(R.id.iv_hall_btn);
        String rtsMatchId = item.getRTSMatchId();
        if (rtsMatchId != null && !rtsMatchId.isEmpty() && !rtsMatchId.equals("0")) {
            ivHall.setVisibility(View.VISIBLE);
            ivHall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back.clickView(v, item, position);
                }
            });
        } else {
            ivHall.setVisibility(View.GONE);
        }
    }

    @Override
    protected void clickHallBtn(View v, EuropeInfo item, int position) {
        super.clickHallBtn(v, item, position);
        int nextNotRepeat = getNextNotRepeat(position);
        getBaseView().onWebShow(nextNotRepeat, position, item, v);


    }

    @Override
    protected void onMatchRepeat(MyRecyclerViewHolder helper, EuropeInfo item, int position, SportAdapterHelper.ItemCallBack<EuropeInfo> back) {
        super.onMatchRepeat(helper, item, position, back);
        ImageView ivHall = helper.getView(R.id.iv_hall_btn);
        ivHall.setVisibility(View.GONE);
    }

    @Override
    protected void onChildConvert(MyRecyclerViewHolder helper, int position, EuropeInfo item) {
        TextView timeTv = helper.getView(R.id.module_match_time_tv);
        TextView dateTv = helper.getView(R.id.module_match_date_tv);
        helper.getView(R.id.module_match_live_iv).setVisibility(View.GONE);
        String channel = item.getLive();
        channel = Html.fromHtml(channel).toString();


        if (channel.contains("HT")) {
            timeTv.setText("HT");
            String score = channel.substring(0, channel.indexOf("HT"));
            dateTv.setText(score.trim());
        } else {
            int min;
            int start;
            try {

                switch (item.getStatus()) {
                    case "0":
                        timeTv.setText("LIVE");
                        break;
                    case "2":
                        min = Integer.valueOf(item.getCurMinute());
                        start = 45;
                        min = min + start;
                        if (min < 130 && min > 0) {
                            timeTv.setText(min + getBaseView().getContextActivity().getString(R.string.min));
                        } else {
                            timeTv.setText("");
                        }
                        break;
                    default:
                        min = Integer.valueOf(item.getCurMinute());
                        if (min < 130 && min > 0) {
                            timeTv.setText(min + getBaseView().getContextActivity().getString(R.string.min));
                        } else {
                            timeTv.setText("");
                        }
                        break;
                }


            } catch (Exception e) {
                e.printStackTrace();
                timeTv.setText("");
            }
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Running), "Running", getParentText());
    }

    @Override
    public boolean mix() {
        return false;
    }


}
