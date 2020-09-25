package com.nanyang.app.main.home.sport.outRight;

import android.graphics.Color;
import android.os.Handler;

import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.main.home.sport.athletics.AthleticsFragment;
import com.nanyang.app.main.home.sport.beachSport.BeachSportFragment;
import com.nanyang.app.main.home.sport.main.BaseAllFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.speedWay.SpeedWayFragment;
import com.nanyang.app.main.home.sport.winterSport.WinterSportFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/4/23.
 */

public class OutRightFragment extends BaseAllFragment {
    @Override
    protected String getBallDbid() {
        return "";
    }


    @Override
    public void initData() {
        super.initData();
        setTitle(getString(R.string.OutRight));

    }

    @Override
    public void addSportHeadAndFoot(final SportIdBean sportIdBean) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<SportIdBean> allOutRightSport = getOutRightSports();
                final List<SportIdBean> allTopSport = new ArrayList<>();
                final List<SportIdBean> allBottomSport = new ArrayList<>();
                boolean addHead = true;
                for (int i = 0; i < allOutRightSport.size(); i++) {
                    SportIdBean s = allOutRightSport.get(i);
                    if (addHead) {
                        allTopSport.add(s);
                    } else {
                        allBottomSport.add(s);
                    }
                    if (s.getDbid().equals(sportIdBean.getDbid())) {
                        addHead = false;
                    }
                }
                initHeadAndFoot(allTopSport, true);
                initHeadAndFoot(allBottomSport, false);
                List<SportIdBean> all = new ArrayList<SportIdBean>();
                all.addAll(allTopSport);
                all.addAll(allBottomSport);
                initDefaultList(all);
            }
        }, 0);

    }


    private List<SportIdBean> getOutRightSports() {
        Iterator<Map.Entry<String, SportIdBean>> iterator = getApp().beanHashMap.entrySet().iterator();
        List<SportIdBean> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<String, SportIdBean> next = iterator.next();
            if (next.getValue().getDbid() != null && !next.getValue().getDbid().equals("") && !next.getValue().getDbid().equals("0")
                    && !next.getValue().getDbid().equals("999")

                    && !next.getValue().getDbid().equals("36")

                    && !next.getValue().getDbid().equals("4")
                    && !next.getValue().getDbid().equals("35")
                    )
                list.add(next.getValue());
        }
        list.add(new SportIdBean("67", "27", R.string.Beach_Soccer, "Beach_Soccer", SportActivity.class, new BeachSportFragment(), Color.BLACK, R.mipmap.financial));
        list.add(new SportIdBean("68", "28", R.string.speed_way, "SPEEDWAY", SportActivity.class, new SpeedWayFragment(), Color.BLACK, R.mipmap.financial));
        list.add(new SportIdBean("101", "29", R.string.Athletics, "Athletics", SportActivity.class, new AthleticsFragment(), Color.BLACK, R.mipmap.financial));
        list.add(new SportIdBean("103", "30", R.string.WinterSport, "WinterSport", SportActivity.class, new WinterSportFragment(), Color.BLACK, R.mipmap.ice_sport));

        return list;
    }


    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new OutRightRunningState(this));
                break;
            case "Today":
                switchState(new OutRightTodayState(this));
                break;
            case "Early":
                switchState(new OutRightEarlyState(this));
                break;
            default:
                switchState(new OutRightTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.OutRight);
    }


}
