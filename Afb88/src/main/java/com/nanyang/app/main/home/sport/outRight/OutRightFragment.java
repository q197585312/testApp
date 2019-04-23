package com.nanyang.app.main.home.sport.outRight;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by ASUS on 2019/4/23.
 */

public class OutRightFragment extends BaseSportFragment {
    public SportIdBean currentIdBean;

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
        String type = ((SportActivity) getActivity()).getType();
        initOutRight(AfbUtils.getSportByG("1"));//默认足球
        switchType(type);
        setTitle(getString(R.string.OutRight));

    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_out_right_ball;
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

    protected void initHeadAndFootOutRight(final List<SportIdBean> allTopSport, LinearLayout parentView) {
        parentView.removeAllViews();
        for (final SportIdBean sportIdBean : allTopSport) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.sport_selected_layout_base, null);
            inflate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            View sportView = inflate.findViewById(R.id.ll_sport);
            TextView sportName = inflate.findViewById(R.id.tv_sport_name);
            ImageView sportPic = inflate.findViewById(R.id.iv_sport_picture);
            sportName.setText(sportIdBean.getTextRes());
            sportPic.setImageResource(sportIdBean.getSportPic());
            sportName.setTextColor(sportIdBean.getTextColor());

            sportView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isRefesh = true;
                    if (currentIdBean == null || (!currentIdBean.getDbid().equals(sportIdBean.getDbid())))
                        isRefesh = true;
                    else
                        isRefesh = false;
                    initOutRight(sportIdBean);
                    if (isRefesh) {
                        refresh();
                        rvContent.setVisibility(View.VISIBLE);
                    } else
                        rvContent.setVisibility(rvContent.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

                }
            });

            parentView.addView(inflate);
        }

    }

    public void initOutRight(SportIdBean sportIdBean) {
        if (sportIdBean == null)
            return;
        this.currentIdBean = sportIdBean;
        addOutRightHeadAndFoot(sportIdBean);

    }

    private void addOutRightHeadAndFoot(final SportIdBean sportIdBean) {

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
        initHeadAndFootOutRight(allTopSport, ll_header_sport);
        initHeadAndFootOutRight(allBottomSport, ll_footer_sport);

    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new OutRightTodayState(this));
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
