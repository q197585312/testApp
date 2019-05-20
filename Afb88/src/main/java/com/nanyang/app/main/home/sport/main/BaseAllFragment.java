package com.nanyang.app.main.home.sport.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;

/**
 * Created by ASUS on 2019/4/23.
 */

public abstract class BaseAllFragment extends BaseSportFragment {
    public SportIdBean currentIdBean;
    private boolean itemVisible;

    @Override
    protected String getBallDbid() {
        return "";
    }

    @Bind(R.id.ll_footer_sport)
    protected LinearLayout ll_footer_sport;
    @Bind(R.id.ll_header_sport)
    protected LinearLayout ll_header_sport;
    @Bind(R.id.base_rv)
    protected RecyclerView rvAll;

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();

        initSport(AfbUtils.getSportByG("1"));//默认足球
        rvContent.setVisibility(View.GONE);
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
        for (final SportIdBean sportIdBean : allTopSport) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.sport_selected_layout_base, null);
            inflate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            View sportView = itemSelectConvert(sportIdBean, inflate);
            sportView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleSelectItemCLick(sportIdBean);
                }
            });

            parentView.addView(inflate);
        }
//        baseRecyclerAdapter.removeHeadAndFoot();
        if (ishead)
            getPresenter().getStateHelper().getAdapterHelper().getBaseRecyclerAdapter().setHeader(parentView);
        else {
            getPresenter().getStateHelper().getAdapterHelper().getBaseRecyclerAdapter().setFooter(parentView);
        }
    }

    private void handleSelectItemCLick(SportIdBean sportIdBean) {
        boolean isRefresh;
        if (currentIdBean == null || (!currentIdBean.getDbid().equals(sportIdBean.getDbid())))
            isRefresh = true;
        else
            isRefresh = false;
        initSport(sportIdBean);
        if (isRefresh) {
            getPresenter().getStateHelper().getAdapterHelper().getBaseRecyclerAdapter().clearItems(true);
            getPresenter().getStateHelper().handleAdapter();
            getPresenter().getStateHelper().refresh();
            setItemVisible(true);
        } else {
            setItemVisible(!getItemVisible());
        }
    }

    private View itemSelectConvert(SportIdBean sportIdBean, View inflate) {
        View sportView = inflate.findViewById(R.id.ll_sport);
        TextView sportName = inflate.findViewById(R.id.tv_sport_name);
        ImageView sportPic = inflate.findViewById(R.id.iv_sport_picture);
        sportName.setText(sportIdBean.getTextRes());
        sportPic.setImageResource(sportIdBean.getSportPic());
        sportName.setTextColor(sportIdBean.getTextColor());
        return sportView;
    }

    protected void initDefaultList(List<SportIdBean> all) {
        rvAll.setLayoutManager(new LinearLayoutManager(mContext));
        BaseRecyclerAdapter<SportIdBean> baseRecyclerAdapter = new BaseRecyclerAdapter<SportIdBean>(mContext, all, R.layout.sport_selected_layout_base) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, SportIdBean sportIdBean) {
                View inflate = holder.getHolderView();
                View sportView = itemSelectConvert(sportIdBean, inflate);
            }
        };
        rvAll.setAdapter(baseRecyclerAdapter);
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<SportIdBean>() {
            @Override
            public void onItemClick(View view, SportIdBean item, int position) {
                handleSelectItemCLick(item);

            }
        });


    }

    public void initSport(SportIdBean sportIdBean) {
        if (sportIdBean == null)
            return;
        this.currentIdBean = sportIdBean;
        addSportHeadAndFoot(sportIdBean);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden()) {
            rvAll.setVisibility(View.VISIBLE);
            rvContent.setVisibility(View.GONE);
        }
//        addSportHeadAndFoot(currentIdBean);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            rvAll.setVisibility(View.VISIBLE);
            rvContent.setVisibility(View.GONE);
        }

    }

    public abstract void addSportHeadAndFoot(final SportIdBean sportIdBean);

    @Override
    public void onGetData(List<SportInfo> data) {
//        super.onGetData(data);
        if (data == null || data.size() < 1)
            ToastUtils.showShort("No Games");
    }

    public void setItemVisible(boolean itemVisible) {
        this.itemVisible = itemVisible;
        if (itemVisible) {
            rvContent.setVisibility(View.VISIBLE);
            rvAll.setVisibility(View.GONE);
        } else {
            rvContent.setVisibility(View.GONE);
            rvAll.setVisibility(View.VISIBLE);
        }
    }

    public boolean getItemVisible() {
        return itemVisible;
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
