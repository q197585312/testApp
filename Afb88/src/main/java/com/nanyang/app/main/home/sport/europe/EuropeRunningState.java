package com.nanyang.app.main.home.sport.europe;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
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
    protected void onTypeClick(MenuItemInfo item, int position) {
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
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new EuropeRunningBetHelper(getBaseView());
    }


    @Override
    protected void clickHallBtn(View v, BallInfo item, int position) {
        super.clickHallBtn(v, item, position);
        int nextNotRepeat = getNextNotRepeat(position);
        getBaseView().onWebShow(nextNotRepeat, position, item, v);


    }


    @Override
    protected void onChildConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {
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
            try {

                switch (item.getStatus()) {
                    case "0":
                        timeTv.setText("LIVE");
                        break;
                    case "2":
                        min = Integer.valueOf(item.getCurMinute());

                        if (min < 130 && min > 0) {
                            timeTv.setText("2H " + min + "'");
                        } else {
                            timeTv.setText("");
                        }
                        break;
                    default:
                        min = Integer.valueOf(item.getCurMinute());
                        if (min < 130 && min > 0) {
                            timeTv.setText("1H "+min + "'");
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
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running), "Running", getParentText());
    }

    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new EuropeRunningAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }
}
