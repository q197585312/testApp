package com.nanyang.app.main.home.sport.football;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BaseSportFragment;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.additional.VsActivity;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.MenuListInfo;
import com.nanyang.app.myView.LinkedViewPager.MyPagerAdapter;
import com.nanyang.app.myView.LinkedViewPager.ViewPager;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

//import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;


public class FootballFragment extends BaseSportFragment<FootballPresenter> implements SportContract.View<List<MatchBean>> {

    @Bind(R.id.tv_total_match)
    TextView tvTotalMatch;
    @Bind(R.id.tv_odds_type)
    TextView tvOddsType;
    @Bind(R.id.vp_header)
    ViewPager vpHeader;

    @Bind(R.id.tv_mix_parlay_order)
    TextView tvMixParlayOrder;
    @Bind(R.id.ll_mix_parlay_order)
    LinearLayout llMixParlayOrder;
    private BetBasePop betPop;
    private TextView tvCollection;


    @Override
    public void initData() {
        super.initData();
        initAdapter();
    }

    @Override
    protected FootballPresenter getPresenter() {
        return new FootballPresenter(this);
    }

    private void initAdapter() {


        MyPagerAdapter<MenuListInfo> headerAdapter = headerAdapter();
        List<MenuListInfo> listInfos=new ArrayList<>();
        MenuListInfo list1=new MenuListInfo();
        MenuListInfo list2=new MenuListInfo();
        list1.setList(Arrays.asList(new MenuItemInfo(1,"FULL   H/A"),new MenuItemInfo(1,"FULL    O/U")));
        list2.setList(Arrays.asList(new MenuItemInfo(1,"HALF   H/A"),new MenuItemInfo(1,"HALF    O/U")));
        listInfos.add(list1);
        listInfos.add(list2);
        headerAdapter.setDatas(listInfos);
        baseRecyclerAdapter.setVpHeader(vpHeader);
        vpHeader.setAdapter(headerAdapter);

    }




    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onPageData(int page, List<MatchBean> pageData, String modelType) {
        baseRecyclerAdapter.addAllAndClear(pageData);
        rvContent.setAdapter(baseRecyclerAdapter);
        String size = pageData.size() + "";
        tvTotalMatch.setText(size);
        ((BaseToolbarActivity) getActivity()).getTvToolbarTitle().setText(modelType);
        if (presenter.isMixParlay()) {
            llMixParlayOrder.setVisibility(View.VISIBLE);
        } else {
            llMixParlayOrder.setVisibility(View.GONE);
        }
        if(tvCollection!=null)
        if(presenter.isCollection){
            tvCollection.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_star_green, 0, 0);
        }else
            tvCollection.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_star_black, 0, 0);
    }

    @Override
    public void onUpdateMixSucceed(BettingParPromptBean result, Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item) {
        if (result != null)
            getApp().setBetParList(result);
        saveBetMap(keyMap, item);
        onCountBet();
    }

    @Override
    public void onAddMixFailed(String message) {
        onCountBet();
        baseRecyclerAdapter.notifyDataSetChanged();
    }




    @Override
    public void onRightMarkClick(Bundle b) {
        skipAct(VsActivity.class, b);
    }

    @Override
    public void onCountBet() {
        llMixParlayOrder.setVisibility(View.VISIBLE);
        Map<String, Map<String, Map<Integer, BettingInfoBean>>> result = getApp().getBetDetail();
        if (result != null) {
            if (result.size() == 0) {
                llMixParlayOrder.setVisibility(View.GONE);
            } else {
                tvMixParlayOrder.setText(result.size() + "");
                llMixParlayOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b=new Bundle();
                        b.putString(AppConstant.KEY_STRING,"Soccer");
                        skipAct(MixOrderListActivity.class,b);
                    }
                });
            }
        } else
            llMixParlayOrder.setVisibility(View.GONE);
    }




    private void saveBetMap(Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item) {
        if (item == null) {
            getApp().setBetDetail(null);
        } else {
            getApp().getBetDetail().put(item.getHome() + "+" + item.getAway(), keyMap);
            baseRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean mixParlayCLick(TextView tvMix) {
        presenter.mixParlay();
        if (presenter.isMixParlay())
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_oval_u_green, 0, 0);
        else
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_oval_u_black, 0, 0);
        return presenter.isMixParlay();
    }

    @Override
    public boolean collectionClick(TextView tvCollection) {
        presenter.collection();
        this.tvCollection=tvCollection;
        return presenter.isCollection();

    }

    @Override
    public void onGetData(List<MatchBean> data) {
        ToastUtils.showShort(data.toString());
    }

    @Override
    public String getTitle() {
        return getString(R.string.Football);
    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_football;
    }




    @OnClick(R.id.tv_odds_type)
    public void onClick(View v) {
        clickOddsType(v);
    }
}
