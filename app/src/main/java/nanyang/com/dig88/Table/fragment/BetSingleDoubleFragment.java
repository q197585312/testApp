package nanyang.com.dig88.Table.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.BindColor;
import nanyang.com.dig88.Entity.VsTableRowBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.BettingInfoBean;
import nanyang.com.dig88.Table.popupwindow.BetBasePop;
import nanyang.com.dig88.Util.DeviceUtils;
import xs.com.mylibrary.base.AdapterViewContent;
import xs.com.mylibrary.base.QuickAdapterImp;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by Administrator on 2015/11/4.
 */
public class BetSingleDoubleFragment extends BaseFragment {
    @BindColor(R.color.grey_background)
    int grey;
    @Bind(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    AdapterViewContent<VsTableRowBean> content;
    @Bind(R.id.ll_base_listview)
    LinearLayout ll_base_listview;
    private List<VsTableRowBean> data;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.base_pulltorefresh_listview_content;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        ll_base_listview.setBackgroundColor(mContext.getResources().getColor(R.color.listView_bg));
        content=new AdapterViewContent<>(mContext,listContentPtrlv.getRefreshableView());
        content.setBaseAdapter(new QuickAdapterImp<VsTableRowBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_vs_goals_row_content;
            }

            @Override
            public void convert(ViewHolder helper, final VsTableRowBean item, int position) {
                if (position==data.size()-1||position==2||position==3){
                    helper.setVisible(R.id.footer_line1,true);
                    helper.setVisible(R.id.footer_line2,true);
                }
                helper.setVisible(R.id.vs_row_head_ll,item.isHasHead());
                helper.setVisible(R.id.vs_row_foot_ll,false);
                if(item.isHasHead()){
                    helper.setVisible(R.id.vs_row_head_second_ll,false);
                    helper.setText(R.id.vs_header_module_title_tv,item.getModuleTitle());
                }
                if(item.getRows().size()>3){
                    helper.setVisible(R.id.vs_row_content_ll4,true);
                    helper.setVisible(R.id.vs_row_content_ll5,true);
                    helper.setText(R.id.vs_row_content_tv41,item.getRows().get(3).getKey());
                    helper.setText(R.id.vs_row_content_tv42,item.getRows().get(3).getValue());
                    helper.setText(R.id.vs_row_content_tv51,item.getRows().get(4).getKey());
                    helper.setText(R.id.vs_row_content_tv52,item.getRows().get(4).getValue());
                    helper.setClickLisenter(R.id.vs_row_content_tv42, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (item.getRows().get(3).getValue() != null && (!item.getRows().get(3).getValue().equals(""))){
                                BetBasePop pop= new BetBasePop(mContext, v, DeviceUtils.getScreenWidth((Activity) mContext)/5*4, LinearLayout.LayoutParams.WRAP_CONTENT);
                                pop.showPopupCenterWindowBlack();
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
                                BetBasePop pop=new BetBasePop(mContext, v, DeviceUtils.getScreenWidth((Activity) mContext)/5*4, LinearLayout.LayoutParams.WRAP_CONTENT);
                                pop.showPopupCenterWindowBlack();
                                BettingInfoBean info=new BettingInfoBean("s",item.getB(),item.getRows().get(4).getSc(),"",item.getRows().get(4).getValue(),
                                        "","",item.getModuleTitle(),item.getRows().get(4).getOid()+"","",0,false,false );
                                pop.getData(info);
                            }
                        }
                    });

                }else{
                    helper.setVisible(R.id.vs_row_content_ll4,false);
                    helper.setVisible(R.id.vs_row_content_ll5,false);
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
                            BetBasePop pop = new BetBasePop(mContext, v, DeviceUtils.getScreenWidth((Activity) mContext)/5*4, LinearLayout.LayoutParams.WRAP_CONTENT);
                            pop.showPopupCenterWindowBlack();
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
                            BetBasePop pop = new BetBasePop(mContext, v, DeviceUtils.getScreenWidth((Activity) mContext)/5*4, LinearLayout.LayoutParams.WRAP_CONTENT);
                            pop.showPopupCenterWindowBlack();
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
                            BetBasePop pop=new BetBasePop(mContext, v, DeviceUtils.getScreenWidth((Activity) mContext)/5*4, LinearLayout.LayoutParams.WRAP_CONTENT);
                            pop.showPopupCenterWindowBlack();
                            BettingInfoBean info=new BettingInfoBean("s",item.getB(),item.getRows().get(2).getSc(),"",item.getRows().get(2).getValue(),
                                    "","",item.getModuleTitle(),item.getRows().get(2).getOid()+"","",0,false,false );
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
