package com.nanyang.app.main.home.sport.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;

/**
 * Created by ASUS on 2019/4/23.
 */

public abstract class BaseAllFragment extends BaseSportFragment {
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

        initSport(AfbUtils.getSportByG("1"));//默认足球
//        rvContent.setVisibility(View.GONE);
        switchType(type);

//        setTitle(getString(R.string.OutRight));

    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_base_all_ball;
    }


    protected void initHeadAndFoot(final List<SportIdBean> allTopSport, boolean ishead) {
        LinearLayout parentView = new LinearLayout(mContext);
        parentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        parentView.setOrientation(LinearLayout.VERTICAL);
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
                    initSport(sportIdBean);
                    if (isRefesh) {
                        getPresenter().getStateHelper().getAdapterHelper().getBaseRecyclerAdapter().clearItems(true);
                        getPresenter().getStateHelper().handleAdapter();
                        getPresenter().getStateHelper().refresh();
                        ((OutRightState) getPresenter().getStateHelper()).setItemVisible(View.VISIBLE);
                    } else {
                        int itemVisible = ((OutRightState) getPresenter().getStateHelper()).getItemVisible();
//                        ((OutRightState) getPresenter().getStateHelper()).setItemVisible(itemVisible == View.VISIBLE ? View.GONE : View.VISIBLE);
                        getPresenter().getStateHelper().getAdapterHelper().getBaseRecyclerAdapter().notifyDataSetChanged();
                    }

                }
            });

            parentView.addView(inflate);
        }
//        baseRecyclerAdapter.removeHeadAndFoot();
        if (ishead)
            baseRecyclerAdapter.setHeader(parentView);
        else {
            baseRecyclerAdapter.setFooter(parentView);
        }

    }

    public void initSport(SportIdBean sportIdBean) {
        if (sportIdBean == null)
            return;
        this.currentIdBean = sportIdBean;
        addSportHeadAndFoot(sportIdBean);

    }


    protected abstract void addSportHeadAndFoot(final SportIdBean sportIdBean);/*{

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

    }*/

    @Override
    public void onGetData(List<SportInfo> data) {
//        super.onGetData(data);
        if (data == null || data.size() < 1)
            ToastUtils.showShort("No Games");
    }

  /*  @Override
    public void switchTypeIndex(String type) {
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
    }*/


/*    @Override
    public String getTitle() {
        return getString(R.string.OutRight);
    }*/


}
