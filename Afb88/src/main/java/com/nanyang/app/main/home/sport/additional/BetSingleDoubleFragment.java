package com.nanyang.app.main.home.sport.additional;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.nanyang.app.main.home.sport.model.VsTableRowBean;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.BindColor;
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
public class BetSingleDoubleFragment extends BaseVsFragment<VsTableRowBean> {




    @Override
    protected void convertItem(MyRecyclerViewHolder holder, int position, VsTableRowBean item) {

        helper.setVisible(R.id.vs_row_head_ll,item.isHasHead());
        helper.setVisible(R.id.vs_row_foot_ll,item.isHasFoot());
        if(item.isHasHead()){
            helper.setVisible(R.id.vs_row_head_second_ll,false);
            helper.setText(R.id.vs_header_module_title_tv,item.getModuleTitle());
        }
        if(item.getRows().size()>3){
            helper.setVisible(R.id.vs_row_content_ll4,true);
            helper.setVisible(R.id.vs_row_content_ll5,true);
            helper.setVisible(R.id.vs_row_line4,true);
            helper.setVisible(R.id.vs_row_line5,true);
            helper.setText(R.id.vs_row_content_tv41,item.getRows().get(3).getKey());
            helper.setText(R.id.vs_row_content_tv42,item.getRows().get(3).getValue());
            helper.setText(R.id.vs_row_content_tv51,item.getRows().get(4).getKey());
            helper.setText(R.id.vs_row_content_tv52,item.getRows().get(4).getValue());
            helper.setClickLisenter(R.id.vs_row_content_tv42, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getRows().get(3).getValue() != null && (!item.getRows().get(3).getValue().equals(""))){
                        BetBasePop pop=new BetBasePop(mContext,v,800, LinearLayout.LayoutParams.WRAP_CONTENT);
                        pop.showPopupCenterWindow();
                        BettingInfoBean info=new BettingInfoBean("s",item.getB(),item.getRows().get(3).getSc(),"",item.getRows().get(3).getValue(),
                                "","",item.getModuleTitle(),item.getRows().get(3).getOid()+"","",0,false,false );
                        pop.getData(info);
                    }
                }
            });
            helper.setClickLisenter(R.id.vs_row_content_tv52, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getRows().get(4).getValue() != null && (!item.getRows().get(4).getValue().equals(""))){
                        BetBasePop pop=new BetBasePop(mContext,v,800, LinearLayout.LayoutParams.WRAP_CONTENT);
                        pop.showPopupCenterWindow();
                        BettingInfoBean info=new BettingInfoBean("s",item.getB(),item.getRows().get(4).getSc(),"",item.getRows().get(4).getValue(),
                                "","",item.getModuleTitle(),item.getRows().get(4).getOid()+"","",0,false,false );
                        pop.getData(info);
                    }
                }
            });

        }else{
            helper.setVisible(R.id.vs_row_content_ll4,false);
            helper.setVisible(R.id.vs_row_content_ll5,false);
            helper.setVisible(R.id.vs_row_line4,false);
            helper.setVisible(R.id.vs_row_line5,false);
        }
        helper.setText(R.id.vs_row_content_tv11,item.getRows().get(0).getKey());
        helper.setText(R.id.vs_row_content_tv12,item.getRows().get(0).getValue());
        helper.setText(R.id.vs_row_content_tv21,item.getRows().get(1).getKey());
        helper.setText(R.id.vs_row_content_tv22,item.getRows().get(1).getValue());
        helper.setText(R.id.vs_row_content_tv31,item.getRows().get(2).getKey());
        helper.setText(R.id.vs_row_content_tv32,item.getRows().get(2).getValue());
        helper.setClickLisenter(R.id.vs_row_content_tv12, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(0).getValue() != null && (!item.getRows().get(0).getValue().equals(""))) {
                    BetBasePop pop = new BetBasePop(mContext, v, 800, LinearLayout.LayoutParams.WRAP_CONTENT);
                    pop.showPopupCenterWindow();
                    BettingInfoBean info=new BettingInfoBean("s",item.getB(),item.getRows().get(0).getSc(),"",item.getRows().get(0).getValue(),
                            "","",item.getModuleTitle(),item.getRows().get(0).getOid()+"","",0,false,false );
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
                    pop.getData(info);
                }
            }
        });
    }

    @Override
    protected int onSetItemLayoutId() {
        return R.layout.item_vs_goals_row_content;
    }
}
