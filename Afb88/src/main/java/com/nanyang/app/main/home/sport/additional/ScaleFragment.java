package com.nanyang.app.main.home.sport.additional;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nanyang.app.
import butterknife.Bind;
import butterknife.BindColor;
import butterknife.BindString;
import com.nanyang.app.R;
/**
 * Created by Administrator on 2015/11/4.
 */
public class ScaleFragment extends BaseVsFragment {
    @BindString(R.string.scaleplate_asianplate)
    String scale_asianplate;
    @BindColor(R.color.white)

    int white;
    @BindColor(R.color.black_grey)
            int blackGrey;

    AdapterViewContent<VsTableRowBean> content;
    @Bind(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    @Bind(R.id.vs_scale_bet_count_tv)
    TextView betCountTv;
    private List<VsTableRowBean> data;
    private int betType;
    private Map<String, Map<String, Map<Integer, BettingInfoBean>>> betDetail;
    private MatchBean itemData;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_vs_scale;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        betType=((VsActivity)getActivity()).getBetType();
        if(betType== TableAdapterHelper.ClearanceBet){
            betCountTv.setVisibility(View.VISIBLE);
        }else{
            betCountTv.setVisibility(View.GONE);
        }
        betDetail=getApp().getBetDetail();
       itemData = ((VsActivity) getActivity()).getItem();
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        content = new AdapterViewContent<>(mContext, listContentPtrlv.getRefreshableView());
        content.setBaseAdapter(new QuickAdapterImp<VsTableRowBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_vs_scale_row_content;
            }
            @Override
            public void convert(ViewHolder helper, final VsTableRowBean item, final int position) {
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
                            if(betType==TableAdapterHelper.ClearanceBet){
                                BettingInfoBean info = new BettingInfoBean("", item.getB(), item.getSc(), "", item.getRows().get(1).getValue(),
                                        itemData.getHome(),   itemData.getAway(), item.getModuleTitle(), item.getRows().get(1).getOid() + "", "", 0, false, false);

                                handleClearanceBet(v,position+item.getB(),1,info);
                            }else {
                                BetBasePop pop = new BetBasePop(mContext, v, 800, LinearLayout.LayoutParams.WRAP_CONTENT);
                                pop.showPopupCenterWindow();
                                BettingInfoBean info = new BettingInfoBean("s", item.getB(), item.getSc(), "", item.getRows().get(1).getValue(),
                                        itemData.getHome(),   itemData.getAway(), item.getModuleTitle(), item.getRows().get(1).getOid() + "", "", 0, false, false);
                                pop.getData(info);
                            }

                        }
                    }
                });
                helper.setClickLisenter(R.id.vs_row_content_tv32, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getRows().get(2).getValue() != null && (!item.getRows().get(2).getValue().equals(""))){
                            if(betType==TableAdapterHelper.ClearanceBet){
                                BettingInfoBean info = new BettingInfoBean("", item.getB(), item.getSc(), "", item.getRows().get(2).getValue(),
                                        itemData.getHome(),   itemData.getAway(), item.getModuleTitle(), item.getRows().get(2).getOid() + "", item.getRows().get(2).getOid() + "", 1, false, false);
                                handleClearanceBet(v, position+item.getB(),2,info);
                            }else {
                                BetBasePop pop = new BetBasePop(mContext, v, 800, LinearLayout.LayoutParams.WRAP_CONTENT);
                                pop.setBetSelectionType(getString(R.string.half_time));
                                pop.showPopupCenterWindow();
                                BettingInfoBean info = new BettingInfoBean("s", item.getB(), item.getSc(), itemData.getHandicapBeans().get(1).getHdp(), item.getRows().get(2).getValue(),
                                        itemData.getHome(),   itemData.getAway(), item.getModuleTitle(), item.getRows().get(2).getOid() + "",item.getRows().get(2).getOid() +"", 1, false, false);
                                pop.getData(info);
                            }
                        }
                    }
                });
                if(betType==TableAdapterHelper.ClearanceBet)
                    notifyClearanceBet(position+item.getB(), helper);
            }
        });
        content.setData(data);

    }

    @Override
    public void onResume() {
        super.onResume();
        content.notifyDataSetChanged();
        countBet();

    }
    public void setData(List<VsTableRowBean> data) {
        this.data = data;
        if(content!=null) {
            content.getAdapter().setList(data);
        }
    }

    private void handleClearanceBet(View v, final String rowPosition, final int i, final BettingInfoBean info) {

        if (info != null) {
            BettingDataHelper bettingDataHelper = new BettingDataHelper(mContext, null, new ThreadEndT<BettingParPromptBean>(new TypeToken<BettingParPromptBean>() {
            }.getType()) {
                @Override
                public void endT(BettingParPromptBean result,int model) {
                    if(result!=null&&getApp()!=null)
                        getApp().setBetParList(result);
                    countBet();
                }

                @Override
                public void endString(String result, int model) {

                }

                @Override
                public void endError( String error) {
                    if(betDetail!=null)
                        betDetail.put(((VsActivity)getActivity()).getItem().getHome() + "+" + ((VsActivity)getActivity()).getItem().getAway(), new HashMap<String, Map<Integer, BettingInfoBean>>());
                }
            });
            bettingDataHelper.getData(info);
            Map<Integer, BettingInfoBean> positionMap = new HashMap<>();
            positionMap.put(i, info);
            Map<String, Map<Integer, BettingInfoBean>> keyMap = new HashMap<>();
            keyMap.put(rowPosition, positionMap);
            betDetail.put(((VsActivity)getActivity()).getItem().getHome() + "+" + ((VsActivity)getActivity()).getItem().getAway(), keyMap);
            content.notifyDataSetChanged();

        } else {

        }
    }
    private void notifyClearanceBet(String position, ViewHolder helper) {
        Map<String, Map<Integer, BettingInfoBean>> keyMap = betDetail.get(((VsActivity)getActivity()).getItem().getHome() + "+" + ((VsActivity)getActivity()).getItem().getAway());
        if (keyMap == null)
            return;
        Map<Integer, BettingInfoBean> positionMap = keyMap.get(position+"");
        if (positionMap == null) {
            helper.setBackgroundRes(R.id.vs_row_content_tv22, R.color.white);
            helper.setTextColor(R.id.vs_row_content_tv22, blackGrey);
            helper.setBackgroundRes(R.id.vs_row_content_tv32, R.color.white);
            helper.setTextColor(R.id.vs_row_content_tv32, blackGrey);
            return;
        }

        for(Map.Entry<Integer, BettingInfoBean> entry:positionMap.entrySet()){
            int key=entry.getKey();
            if (key==1) {
                helper.setBackgroundRes(R.id.vs_row_content_tv22, R.drawable.rectangle_blue_table_bg_allradius5);
                helper.setTextColor(R.id.vs_row_content_tv22, white);
                helper.setBackgroundRes(R.id.vs_row_content_tv32, R.color.white);
                helper.setTextColor(R.id.vs_row_content_tv32, blackGrey);


            } else if (key==2) {
                helper.setBackgroundRes(R.id.vs_row_content_tv32, R.drawable.rectangle_blue_table_bg_allradius5);
                helper.setTextColor(R.id.vs_row_content_tv32, white);
                helper.setBackgroundRes(R.id.vs_row_content_tv22, R.color.white);
                helper.setTextColor(R.id.vs_row_content_tv22, blackGrey);
            }
        }
    }
    private void countBet() {
        BettingParPromptBean result = getApp().getBetParList();
        if (betCountTv != null && result != null) {
            betCountTv.setText(result.getBetPar().size() + getString(R.string.order));
            betCountTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppTool.activiyJump(mContext, ClearanceBetActivity.class);
                    if( getAct()!=null)
                        getAct().finish();
                }
            });
        }
    }

    @Override
    public int onSetLayoutId() {
        return 0;
    }

    @Override
    protected void convertItem(MyRecyclerViewHolder holder, int position, Object item) {

    }

    @Override
    protected int onSetItemLayoutId() {
        return 0;
    }

    @Override
    public String getTitle() {
        return getString(R.string.scaleplate_asianplate)
    }
}
