package com.nanyang.app.main.home.sport.additional;

import android.view.View;
import android.widget.LinearLayout;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.VsTableRowBean;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;


/**
 * Created by Administrator on 2015/11/4.
 */
public class CorrectFragment extends BaseVsFragment<VsTableRowBean> {


    @Override
    protected void convertItem(MyRecyclerViewHolder helper, final int position, final VsTableRowBean item) {
        helper.setVisible(R.id.vs_row_head_ll, item.isHasHead());
        helper.setVisible(R.id.vs_row_foot_ll, item.isHasFoot());
        if (item.isHasHead()) {
            helper.setText(R.id.vs_header_module_title_tv, item.getModuleTitle());
            helper.setText(R.id.vs_header_left_title_tv, "");
            helper.setText(R.id.vs_header_center_title_tv, "");
            helper.setText(R.id.vs_header_right_title_tv, "");
        }
        helper.setText(R.id.vs_row_content_tv11, item.getRows().get(0).getKey());
        helper.setText(R.id.vs_row_content_tv12, item.getRows().get(0).getValue());

        helper.setText(R.id.vs_row_content_tv21, item.getRows().get(1).getKey());
        helper.setText(R.id.vs_row_content_tv22, item.getRows().get(1).getValue());

        helper.setText(R.id.vs_row_content_tv31, item.getRows().get(2).getKey());
        helper.setText(R.id.vs_row_content_tv32, item.getRows().get(2).getValue());
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
                    BetBasePop pop = new BetBasePop(mContext, v, 800, LinearLayout.LayoutParams.WRAP_CONTENT);
                    pop.showPopupCenterWindow();
                    BettingInfoBean info = new BettingInfoBean("s", item.getB(), item.getRows().get(0).getSc(), "", item.getRows().get(0).getValue(),
                            "", "", item.getModuleTitle(), item.getRows().get(0).getOid() + "", "", 0, false, false);
                    if (position > 9) {
                        pop.setBetSelectionType(getString(R.string.half_time));
                        info = new BettingInfoBean("s", item.getB(), item.getRows().get(0).getSc(), "", item.getRows().get(0).getValue(),
                                "", "", item.getModuleTitle(), item.getRows().get(0).getOid() + "", item.getRows().get(0).getOid() + "", 1, false, false);
                    }
                    pop.initData(info);
                }
            }
        });
        helper.setClickLisenter(R.id.vs_row_content_tv22, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(1).getValue() != null && (!item.getRows().get(1).getValue().equals(""))) {
                    BetBasePop pop = new BetBasePop(mContext, v, 800, LinearLayout.LayoutParams.WRAP_CONTENT);
                    pop.showPopupCenterWindow();
                    BettingInfoBean info = new BettingInfoBean("s", item.getB(), item.getRows().get(1).getSc(), "", item.getRows().get(1).getValue(),
                            "", "", item.getModuleTitle(), item.getRows().get(1).getOid() + "", "", 0, false, false);
                    if (position > 9) {
                        pop.setBetSelectionType(getString(R.string.half_time));
                        info = new BettingInfoBean("s", item.getB(), item.getRows().get(1).getSc(), "", item.getRows().get(1).getValue(),
                                "", "", item.getModuleTitle(), item.getRows().get(1).getOid() + "", item.getRows().get(1).getOid() + "", 1, false, false);
                    }
                    pop.initData(info);
                }
            }
        });
        helper.setClickLisenter(R.id.vs_row_content_tv32, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(2).getValue() != null && (!item.getRows().get(2).getValue().equals(""))) {
                    BetBasePop pop = new BetBasePop(mContext, v, 800, LinearLayout.LayoutParams.WRAP_CONTENT);
                    pop.showPopupCenterWindow();
                    BettingInfoBean info = new BettingInfoBean("s", item.getB(), item.getRows().get(2).getSc(), "", item.getRows().get(2).getValue(),
                            "", "", item.getModuleTitle(), item.getRows().get(2).getOid() + "", "", 0, false, false);
                    if (position > 9) {
                        pop.setBetSelectionType(getString(R.string.half_time));
                        info = new BettingInfoBean("s", item.getB(), item.getRows().get(2).getSc(), "", item.getRows().get(2).getValue(),
                                "", "", item.getModuleTitle(), item.getRows().get(2).getOid() + "", item.getRows().get(2).getOid() + "", 1, false, false);
                    }
                    pop.initData(info);
                }
            }
        });
    }


    @Override
    protected int onSetItemLayoutId() {
        return R.layout.item_vs_correct_row_content;
    }
}
