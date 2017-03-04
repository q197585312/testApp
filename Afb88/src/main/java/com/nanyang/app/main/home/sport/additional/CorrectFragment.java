package com.nanyang.app.main.home.sport.additional;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.Entity.VsTableRowBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.BettingInfoBean;
import nanyang.com.dig88.Table.popupwindow.BetBasePop;
import xs.com.mylibrary.base.AdapterViewContent;
import xs.com.mylibrary.base.QuickAdapterImp;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by Administrator on 2015/11/4.
 */
public class CorrectFragment extends BaseVsFragment {
    @Bind(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    AdapterViewContent<VsTableRowBean> content;
    private List<VsTableRowBean> data;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.base_pulltorefresh_listview_content;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        content=new AdapterViewContent<>(mContext,listContentPtrlv.getRefreshableView());
        content.setBaseAdapter(new QuickAdapterImp<VsTableRowBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_vs_correct_row_content;
            }

            @Override
            public void convert(ViewHolder helper, final VsTableRowBean item, final int position) {
                helper.setVisible(R.id.vs_row_head_ll,item.isHasHead());
                helper.setVisible(R.id.vs_row_foot_ll,item.isHasFoot());
                if(item.isHasHead()){
                    helper.setText(R.id.vs_header_module_title_tv,item.getModuleTitle());
                    helper.setText(R.id.vs_header_left_title_tv,"");
                    helper.setText(R.id.vs_header_center_title_tv,"");
                    helper.setText(R.id.vs_header_right_title_tv,"");
                }
                helper.setText(R.id.vs_row_content_tv11,item.getRows().get(0).getKey());
                helper.setText(R.id.vs_row_content_tv12,item.getRows().get(0).getValue());

                helper.setText(R.id.vs_row_content_tv21,item.getRows().get(1).getKey());
                helper.setText(R.id.vs_row_content_tv22,item.getRows().get(1).getValue());

                helper.setText(R.id.vs_row_content_tv31,item.getRows().get(2).getKey());
                helper.setText(R.id.vs_row_content_tv32,item.getRows().get(2).getValue());
                if(item.getRows().get(0).getValue()==null||item.getRows().get(0).getValue().equals("")){
                    helper.setVisibility(R.id.vs_row_content_tv1x, View.INVISIBLE);
                }else{
                    helper.setVisibility(R.id.vs_row_content_tv1x, View.VISIBLE);
                }
                if(item.getRows().get(1).getValue()==null||item.getRows().get(1).getValue().equals("")){
                    helper.setVisibility(R.id.vs_row_content_tv2x, View.INVISIBLE);
                }else{
                    helper.setVisibility(R.id.vs_row_content_tv2x, View.VISIBLE);
                }
                if(item.getRows().get(2).getValue()==null||item.getRows().get(2).getValue().equals("")){
                    helper.setVisibility(R.id.vs_row_content_tv3x, View.INVISIBLE);
                }else{
                    helper.setVisibility(R.id.vs_row_content_tv3x, View.VISIBLE);
                }
                helper.setClickLisenter(R.id.vs_row_content_tv12, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getRows().get(0).getValue() != null && (!item.getRows().get(0).getValue().equals(""))) {
                            BetBasePop pop = new BetBasePop(mContext, v, 800, LinearLayout.LayoutParams.WRAP_CONTENT);
                            pop.showPopupCenterWindow();
                            BettingInfoBean info=new BettingInfoBean("s",item.getB(),item.getRows().get(0).getSc(),"",item.getRows().get(0).getValue(),
                                    "","",item.getModuleTitle(),item.getRows().get(0).getOid()+"","",0,false,false );
                            if(position>9) {
                                pop.setBetSelectionType(getString(R.string.half_time));
                                 info=new BettingInfoBean("s",item.getB(),item.getRows().get(0).getSc(),"",item.getRows().get(0).getValue(),
                                        "","",item.getModuleTitle(),item.getRows().get(0).getOid()+"",item.getRows().get(0).getOid()+"",1,false,false );
                            }
                            pop.getData(info);
                        }
                    }
                });
                helper.setClickLisenter(R.id.vs_row_content_tv22, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getRows().get(1).getValue() != null && (!item.getRows().get(1).getValue().equals(""))) {
                            BetBasePop pop = new BetBasePop(mContext, v, 800, LinearLayout.LayoutParams.WRAP_CONTENT);
                            pop.showPopupCenterWindow();
                            BettingInfoBean info=new BettingInfoBean("s",item.getB(),item.getRows().get(1).getSc(),"",item.getRows().get(1).getValue(),
                                    "","",item.getModuleTitle(),item.getRows().get(1).getOid()+"","",0,false,false );
                            if(position>9) {
                                pop.setBetSelectionType(getString(R.string.half_time));
                                info=new BettingInfoBean("s",item.getB(),item.getRows().get(1).getSc(),"",item.getRows().get(1).getValue(),
                                        "","",item.getModuleTitle(),item.getRows().get(1).getOid()+"",item.getRows().get(1).getOid()+"",1,false,false );
                            }
                            pop.getData(info);
                        }
                    }
                });
                helper.setClickLisenter(R.id.vs_row_content_tv32, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getRows().get(2).getValue() != null && (!item.getRows().get(2).getValue().equals(""))){
                            BetBasePop pop=new BetBasePop(mContext,v,800, LinearLayout.LayoutParams.WRAP_CONTENT);
                            pop.showPopupCenterWindow();
                            BettingInfoBean info=new BettingInfoBean("s",item.getB(),item.getRows().get(2).getSc(),"",item.getRows().get(2).getValue(),
                                    "","",item.getModuleTitle(),item.getRows().get(2).getOid()+"","",0,false,false );
                            if(position>9) {
                                pop.setBetSelectionType(getString(R.string.half_time));
                                info=new BettingInfoBean("s",item.getB(),item.getRows().get(2).getSc(),"",item.getRows().get(2).getValue(),
                                        "","",item.getModuleTitle(),item.getRows().get(2).getOid()+"",item.getRows().get(2).getOid()+"",1,false,false );
                            }
                            pop.getData(info);
                        }
                    }
                });
            }
        });
        content.setData(data);
    }


    public void setData(List<VsTableRowBean> data) {
        this.data = data;
        if(content!=null) {
            content.getAdapter().setList(data);
        }
    }
}
