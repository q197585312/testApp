package com.nanyang.app.main.home.sport.outRight;

import android.widget.LinearLayout;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.main.home.sport.main.BaseAllFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by ASUS on 2019/4/23.
 */

public class OutRightFragment extends BaseAllFragment {
    @Override
    protected String getBallDbid() {
        return "";
    }

    @Bind(R.id.ll_footer_sport)
    protected LinearLayout ll_footer_sport;
    @Bind(R.id.ll_header_sport)
    protected LinearLayout ll_header_sport;

    @Override
    public void initData() {
        super.initData();
        setTitle(getString(R.string.OutRight));

    }

    @Override
    protected void addSportHeadAndFoot(SportIdBean sportIdBean) {
        List<SportIdBean> allOutRightSport = getOutRightSports();
        List<SportIdBean> allTopSport = new ArrayList<>();
        List<SportIdBean> allBottomSport = new ArrayList<>();
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
        initHeadAndFoot(allTopSport, ll_header_sport);
        initHeadAndFoot(allBottomSport, ll_footer_sport);
    }
    private List<SportIdBean> getOutRightSports() {
        Iterator<Map.Entry<String, SportIdBean>> iterator = AfbUtils.beanHashMap.entrySet().iterator();
        List<SportIdBean> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<String, SportIdBean> next = iterator.next();
            if (next.getValue().getDbid() != null && !next.getValue().getDbid().equals("") && !next.getValue().getDbid().equals("0")
                    && !next.getValue().getDbid().equals("999")
                    && !next.getValue().getDbid().equals("19")
                    && !next.getValue().getDbid().equals("36")
                    && !next.getValue().getDbid().equals("15")
                    && !next.getValue().getDbid().equals("12")
                    && !next.getValue().getDbid().equals("11")
                    && !next.getValue().getDbid().equals("4")
                    )
                list.add(next.getValue());
        }
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
