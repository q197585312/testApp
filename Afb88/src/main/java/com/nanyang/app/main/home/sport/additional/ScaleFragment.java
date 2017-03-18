package com.nanyang.app.main.home.sport.additional;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BetPresenter;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;
import com.nanyang.app.main.home.sport.model.VsCellBean;
import com.nanyang.app.main.home.sport.model.VsTableRowBean;
import com.nanyang.app.main.home.sportInterface.BallCommonBetHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2015/11/4.
 */
public class ScaleFragment extends BaseVsFragment<VsTableRowBean> implements ScaleContract.View {
    private Map<String, Map<String, Map<Integer, BettingInfoBean>>> betDetail;
    private BallInfo itemData;
    private boolean isMix;


    @Override
    public void initData() {
        super.initData();
        itemData = (BallInfo)((VsActivity) getActivity()).getItem();
        betDetail = ((VsActivity) getActivity()).getApp().getBetDetail();
        createPresenter(new ScalePresenter(this));
        isMix=((VsActivity) getActivity()).getMixParlay();

    }



    @Override
    public void onResume() {
        super.onResume();
       adapter.notifyDataSetChanged();
        countBet();

    }


    private void handleClearanceBet(View v, final String rowPosition, final int i, final BettingInfoBean info) {


        ((BetPresenter)presenter).addMixParlayBet(info,null,null);
            Map<Integer, BettingInfoBean> positionMap = new HashMap<>();
            positionMap.put(i, info);
            Map<String, Map<Integer, BettingInfoBean>> keyMap = new HashMap<>();
            keyMap.put(rowPosition, positionMap);
            betDetail.put(itemData.getHome() + "+" + itemData.getAway(), keyMap);
            adapter.notifyDataSetChanged();


    }
    private void notifyClearanceBet(String position, MyRecyclerViewHolder helper) {
        Map<String, Map<Integer, BettingInfoBean>> keyMap = betDetail.get(itemData.getHome() + "+" + itemData.getAway());
        if (keyMap == null)
            return;
        Map<Integer, BettingInfoBean> positionMap = keyMap.get(position+"");
        if (positionMap == null) {
            helper.setBackgroundRes(R.id.vs_row_content_tv22, R.color.white);
            helper.setTextColor(R.id.vs_row_content_tv22, getResources().getColor(R.color.black_grey));
            helper.setBackgroundRes(R.id.vs_row_content_tv32, R.color.white);
            helper.setTextColor(R.id.vs_row_content_tv32, getResources().getColor(R.color.black_grey));
            return;
        }

        for(Map.Entry<Integer, BettingInfoBean> entry:positionMap.entrySet()){
            int key=entry.getKey();
            if (key==1) {
                helper.setBackgroundRes(R.id.vs_row_content_tv22, R.drawable.sport_mix_parlay_bet_green_bg);
                helper.setTextColor(R.id.vs_row_content_tv22, getResources().getColor(R.color.white));
                helper.setBackgroundRes(R.id.vs_row_content_tv32, R.color.white);
                helper.setTextColor(R.id.vs_row_content_tv32, getResources().getColor(R.color.black_grey));


            } else if (key==2) {
                helper.setBackgroundRes(R.id.vs_row_content_tv32, R.drawable.sport_mix_parlay_bet_green_bg);
                helper.setTextColor(R.id.vs_row_content_tv32,  getResources().getColor(R.color.white));
                helper.setBackgroundRes(R.id.vs_row_content_tv22, R.color.white);
                helper.setTextColor(R.id.vs_row_content_tv22,  getResources().getColor(R.color.black_grey));
            }
        }
    }
    private void countBet() {
        BettingParPromptBean result = ((VsActivity)getActivity()).getApp().getBetParList();
        if (tvMixBetCount != null && result != null) {
            tvMixBetCount.setText(result.getBetPar().size()+"" );
            tvMixBetCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b=new Bundle();
                    b.putSerializable(AppConstant.KEY_DATA,  ((VsActivity) getActivity()).getType());
                    skipAct( MixOrderListActivity.class,b);
                    getActivity().finish();
                }
            });
        }
    }

    @Override
    protected int onSetItemLayoutId() {
        return R.layout.item_vs_scale_row_content;
    }

    @Override
    protected void convertItem(MyRecyclerViewHolder helper, final int position, final VsTableRowBean item) {
        helper.setVisible(R.id.vs_row_head_ll, item.isHasHead());
        helper.setVisible(R.id.vs_row_foot_ll, item.isHasFoot());
        if (item.isHasHead()) {
            helper.setText(R.id.vs_header_module_title_tv, item.getModuleTitle());
            helper.setText(R.id.vs_header_left_title_tv, item.getLeftTitle());
            helper.setText(R.id.vs_header_center_title_tv, item.getCenterTitle());
            helper.setText(R.id.vs_header_right_title_tv, item.getRightTitle());
        }
        helper.setText(R.id.vs_row_content_tv11, item.getRows().get(0).getKey());
        helper.setText(R.id.vs_row_content_tv12, item.getRows().get(0).getValue());
        helper.setText(R.id.vs_row_content_tv21, item.getRows().get(1).getKey());
        helper.setText(R.id.vs_row_content_tv22, item.getRows().get(1).getValue());
        helper.setText(R.id.vs_row_content_tv31, item.getRows().get(2).getKey());
        helper.setText(R.id.vs_row_content_tv32, item.getRows().get(2).getValue());
        helper.setClickLisenter(R.id.vs_row_content_tv22, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(1).getValue() != null && (!item.getRows().get(1).getValue().equals(""))) {
                    if(isMix){
                        BettingInfoBean info = new BettingInfoBean("", item.getB(), item.getSc(), "", item.getRows().get(1).getValue(),
                                itemData.getHome(),   itemData.getAway(), item.getModuleTitle(), item.getRows().get(1).getOid() + "", "", 0, false, false);

                        handleClearanceBet(v,position+item.getB(),1,info);
                    }else {
                        BetBasePop pop = new BetBasePop(mContext, v, 800, LinearLayout.LayoutParams.WRAP_CONTENT);
                        pop.showPopupCenterWindow();
                        BettingInfoBean info = new BettingInfoBean("s", item.getB(), item.getSc(), "", item.getRows().get(1).getValue(),
                                itemData.getHome(),   itemData.getAway(), item.getModuleTitle(), item.getRows().get(1).getOid() + "", "", 0, false, false);
                        pop.initData(info);

                        BallCommonBetHelper helper1=new BallCommonBetHelper(this);
                    }

                }
            }
        });
        helper.setClickLisenter(R.id.vs_row_content_tv32, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(2).getValue() != null && (!item.getRows().get(2).getValue().equals(""))){
                    if(isMix){
                        BettingInfoBean info = new BettingInfoBean("", item.getB(), item.getSc(), "", item.getRows().get(2).getValue(),
                                itemData.getHome(),   itemData.getAway(), item.getModuleTitle(), item.getRows().get(2).getOid() + "", item.getRows().get(2).getOid() + "", 1, false, false);
                        handleClearanceBet(v, position+item.getB(),2,info);
                    }else {
                        BetBasePop pop = new BetBasePop(mContext, v, 800, LinearLayout.LayoutParams.WRAP_CONTENT);
                        pop.setBetSelectionType(getString(R.string.half_time));
                        pop.showPopupCenterWindow();
                        BettingInfoBean info = new BettingInfoBean("s", item.getB(), item.getSc(), ((SoccerCommonInfo)itemData).getHdp_FH(), item.getRows().get(2).getValue(),
                                itemData.getHome(),   itemData.getAway(), item.getModuleTitle(), item.getRows().get(2).getOid() + "",item.getRows().get(2).getOid() +"", 1, false, false);
                        pop.initData(info);
                    }
                }
            }
        });
        if(isMix)
            notifyClearanceBet(position+item.getB(), helper);

    }


    @Override
    public void onGetData(BettingParPromptBean data) {
        ((VsActivity) getActivity()).getApp().setBetParList(data);
        countBet();
    }

    @Override
    public void onFailed(String error) {
        if(betDetail!=null)
            betDetail.put(itemData.getHome() + "+" + itemData.getAway(), new HashMap<String, Map<Integer, BettingInfoBean>>());
    }

    private void initFirstData(SoccerMixInfo item) {

        List<VsTableRowBean> rows = new ArrayList<VsTableRowBean>();
        int socOddsId_hf = 0;
        int socOddsId = 0;
        if (item.getSocOddsId_FH() != null && !item.getSocOddsId_FH().equals(""))
            socOddsId_hf = Integer.valueOf(item.getSocOddsId_FH());
        if (item.getSocOddsId() != null && !item.getSocOddsId().equals(""))
            socOddsId = Integer.valueOf(item.getSocOddsId());
        rows = Arrays.asList(new VsTableRowBean("1_par", Arrays.asList(new VsCellBean(getString(R.string.h1), "", 0), new VsCellBean("", item.getX12_1Odds(), socOddsId), new VsCellBean("", item.getX12_1Odds_FH(), socOddsId_hf)), true, false, "1  x  2", getString(R.string.against), getString(R.string.full_time), getString(R.string.half_time)),
                new VsTableRowBean("X_par", Arrays.asList(new VsCellBean(getString(R.string.dx), "", 0), new VsCellBean("", item.getX12_XOdds(), socOddsId), new VsCellBean("", item.getX12_XOdds_FH(), socOddsId_hf))),
                new VsTableRowBean("2_par", Arrays.asList(new VsCellBean(getString(R.string.a2), "", 0), new VsCellBean("", item.getX12_2Odds(), socOddsId), new VsCellBean("", item.getX12_2Odds_FH(), socOddsId_hf)), true),
                new VsTableRowBean("odd_par", Arrays.asList(new VsCellBean(getString(R.string.odd), "", 0), new VsCellBean("", item.getOddOdds(), socOddsId), new VsCellBean("", "", socOddsId_hf)), true, false, getString(R.string.odd_even), getString(R.string.against), getString(R.string.full_time), getString(R.string.half_time)),
                new VsTableRowBean("even_par", Arrays.asList(new VsCellBean(getString(R.string.even), "", 0), new VsCellBean("", item.getEvenOdds(), socOddsId), new VsCellBean("", "", socOddsId_hf)), true));
        setData(rows);

    }
}
