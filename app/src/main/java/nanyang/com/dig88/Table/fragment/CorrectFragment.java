package nanyang.com.dig88.Table.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.Entity.VsTableRowBean1;
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
public class CorrectFragment extends BaseFragment {
    @Bind(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    AdapterViewContent<VsTableRowBean1> content;
    @Bind(R.id.ll_base_listview)
    LinearLayout ll_base_listview;
    private List<VsTableRowBean1> data;

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
        content.setBaseAdapter(new QuickAdapterImp<VsTableRowBean1>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_vs_correct_row_content;
            }

            @Override
            public void convert(ViewHolder helper, final VsTableRowBean1 item, final int position) {
                helper.setVisible(R.id.vs_row_head_ll, item.isHasHead());
                helper.setVisible(R.id.vs_row_foot_ll, item.isHasFoot());
                if (item.isHasHead()) {
                    helper.setVisible(R.id.vs_row_head_second_ll,false);
                    helper.setText(R.id.vs_header_module_title_tv, item.getModuleTitle().toString());
                    helper.setText(R.id.vs_header_left_title_tv, "");
                    helper.setText(R.id.vs_header_center_title_tv, "");
                    helper.setText(R.id.vs_header_right_title_tv, "");
                }
                helper.setText(R.id.vs_row_content_tv11, item.getRows().get(0).getKey().toString());
                helper.setText(R.id.vs_row_content_tv12, item.getRows().get(0).getValue());
                setOddsTextColor(item.getRows().get(0).getValue(),helper.getTextView(R.id.vs_row_content_tv12));

                helper.setText(R.id.vs_row_content_tv21, item.getRows().get(1).getKey().toString());
                helper.setText(R.id.vs_row_content_tv22, item.getRows().get(1).getValue());
                setOddsTextColor(item.getRows().get(1).getValue(),helper.getTextView(R.id.vs_row_content_tv22));

                helper.setText(R.id.vs_row_content_tv31, item.getRows().get(2).getKey().toString());
                helper.setText(R.id.vs_row_content_tv32, item.getRows().get(2).getValue());
                setOddsTextColor(item.getRows().get(2).getValue(),helper.getTextView(R.id.vs_row_content_tv32));

                if (item.getRows().get(0).getValue() == null || item.getRows().get(0).getValue().equals("")) {
                    helper.setVisibility(R.id.vs_row_content_tv1x, View.INVISIBLE);
                } else {
                    helper.setVisibility(R.id.vs_row_content_tv1x, View.VISIBLE);
                }
                if (item.getRows().get(1).getValue() == null || item.getRows().get(1).getValue().equals("")) {
                    helper.setVisibility(R.id.vs_row_content_tv2x, View.INVISIBLE);
                } else {
                    helper.setVisibility(R.id.vs_row_content_tv2x, View.VISIBLE);
                }
                if (item.getRows().get(2).getValue() == null || item.getRows().get(2).getValue().equals("")) {
                    helper.setVisibility(R.id.vs_row_content_tv3x, View.INVISIBLE);
                } else {
                    helper.setVisibility(R.id.vs_row_content_tv3x, View.VISIBLE);
                }
                helper.setClickLisenter(R.id.vs_row_content_tv12, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getRows().get(0).getValue() != null && (!item.getRows().get(0).getValue().equals(""))) {
                            BetBasePop pop = new BetBasePop(mContext, v, DeviceUtils.getScreenWidth((Activity) mContext)/5*4, LinearLayout.LayoutParams.WRAP_CONTENT);
                            pop.showPopupCenterWindowBlack();
                            BettingInfoBean info=new BettingInfoBean("s",item.getB().toString(),item.getRows().get(0).getSc(),"",item.getRows().get(0).getValue(),
                                    "","", !TextUtils.isEmpty(item.getModuleTitle())?item.getModuleTitle().toString():"",item.getRows().get(0).getOid()+"","",0,false,false );
                            if(position>9) {
                                pop.setBetSelectionType(getString(R.string.half_time));
                                 info=new BettingInfoBean("s",item.getB().toString(),item.getRows().get(0).getSc(),"",item.getRows().get(0).getValue(),
                                        "","",!TextUtils.isEmpty(item.getModuleTitle())?item.getModuleTitle().toString():"",item.getRows().get(0).getOid()+"",item.getRows().get(0).getOid()+"",1,false,false );
                            }
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
                            BettingInfoBean info=new BettingInfoBean("s",item.getB().toString(),item.getRows().get(1).getSc(),"",item.getRows().get(1).getValue(),
                                    "","",!TextUtils.isEmpty(item.getModuleTitle())?item.getModuleTitle().toString():"",item.getRows().get(1).getOid()+"","",0,false,false );
                            if(position>9) {
                                pop.setBetSelectionType(getString(R.string.half_time));
                                info=new BettingInfoBean("s",item.getB().toString(),item.getRows().get(1).getSc(),"",item.getRows().get(1).getValue(),
                                        "","",!TextUtils.isEmpty(item.getModuleTitle())?item.getModuleTitle().toString():"",item.getRows().get(1).getOid()+"",item.getRows().get(1).getOid()+"",1,false,false );
                            }
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
                            BettingInfoBean info=new BettingInfoBean("s",item.getB().toString(),item.getRows().get(2).getSc(),"",item.getRows().get(2).getValue(),
                                    "","",!TextUtils.isEmpty(item.getModuleTitle())?item.getModuleTitle().toString():"",item.getRows().get(2).getOid()+"","",0,false,false );
                            if(position>9) {
                                pop.setBetSelectionType(getString(R.string.half_time));
                                info=new BettingInfoBean("s",item.getB().toString(),item.getRows().get(2).getSc(),"",item.getRows().get(2).getValue(),
                                        "","",!TextUtils.isEmpty(item.getModuleTitle())?item.getModuleTitle().toString():"",item.getRows().get(2).getOid()+"",item.getRows().get(2).getOid()+"",1,false,false );
                            }
                            pop.getData(info);
                        }
                    }
                });
            }
        });
        content.setData(data);
    }


    public void setData(List<VsTableRowBean1> data) {
        this.data = data;
        if(content!=null) {
            content.getAdapter().setList(data);
        }
    }
    protected void setOddsTextColor(String odds, TextView v) {
        if (odds!=null&&!odds.trim().isEmpty() && Float.valueOf(odds) < 0) {
            v.setTextColor(getResources().getColor(R.color.red_title));
        } else {
            v.setTextColor(getResources().getColor(R.color.black_grey));
        }
    }
}
