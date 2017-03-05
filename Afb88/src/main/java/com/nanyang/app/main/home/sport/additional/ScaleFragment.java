package com.nanyang.app.main.home.sport.additional;

import android.view.View;
import android.widget.LinearLayout;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BetPresenter;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.VsTableRowBean;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2015/11/4.
 */
public class ScaleFragment extends BaseVsFragment<VsTableRowBean> implements ScaleContract.View {
    private Map<String, Map<String, Map<Integer, BettingInfoBean>>> betDetail;
    private MatchBean itemData;
    private boolean isMix;


    @Override
    public void initData() {
        super.initData();

        itemData = ((VsActivity) getActivity()).getItem();
        betDetail = ((VsActivity) getActivity()).getApp().getBetDetail();
        createPresenter(new ScalePresenter(this));
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
            betDetail.put(((VsActivity)getActivity()).getItem().getHome() + "+" + ((VsActivity)getActivity()).getItem().getAway(), keyMap);
            adapter.notifyDataSetChanged();


    }
    private void notifyClearanceBet(String position, MyRecyclerViewHolder helper) {
        Map<String, Map<Integer, BettingInfoBean>> keyMap = betDetail.get(((VsActivity)getActivity()).getItem().getHome() + "+" + ((VsActivity)getActivity()).getItem().getAway());
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
            tvMixBetCount.setText(result.getBetPar().size() );
            tvMixBetCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    skipAct( MixOrderListActivity.class);
                    getActivity().finish();
                }
            });
        }
    }

    @Override
    public int onSetLayoutId() {
        return 0;
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
                        BettingInfoBean info = new BettingInfoBean("s", item.getB(), item.getSc(), itemData.getHandicapBeans().get(1).getHdp(), item.getRows().get(2).getValue(),
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
    protected int onSetItemLayoutId() {
        return 0;
    }


    @Override
    public String getTitle() {
        return getString(R.string.scaleplate_asianplate);
    }

    @Override
    public void onGetData(BettingParPromptBean data) {
        ((VsActivity) getActivity()).getApp().setBetParList(data);
        countBet();
    }

    @Override
    public void onFailed(String error) {
        if(betDetail!=null)
            betDetail.put(((VsActivity)getActivity()).getItem().getHome() + "+" + ((VsActivity)getActivity()).getItem().getAway(), new HashMap<String, Map<Integer, BettingInfoBean>>());
    }
}
