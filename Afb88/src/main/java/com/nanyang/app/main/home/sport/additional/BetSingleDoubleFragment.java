package com.nanyang.app.main.home.sport.additional;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.VsTableRowBean;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2015/11/4.
 */
public class BetSingleDoubleFragment extends BaseVsFragment<VsTableRowBean> {
    @Override
    public void initData() {
        super.initData();
        tvVsHeader.setVisibility(View.GONE);
    }

    @Override
    protected void convertItem(MyRecyclerViewHolder helper, int position, final VsTableRowBean item) {

        helper.setVisible(R.id.vs_row_head_ll, item.isHasHead());
        helper.setVisible(R.id.vs_row_foot_ll, item.isHasFoot());
        if (item.isHasHead()) {
            helper.setVisible(R.id.vs_row_head_second_ll, false);
            helper.setText(R.id.vs_header_module_title_tv, item.getModuleTitle());
        }
        if (item.getRows().size() > 3) {
            helper.setVisible(R.id.vs_row_content_ll4, true);
            helper.setVisible(R.id.vs_row_content_ll5, true);
            helper.setVisible(R.id.vs_row_line4, true);
            helper.setVisible(R.id.vs_row_line5, true);
            helper.setText(R.id.vs_row_content_tv41, item.getRows().get(3).getKey());
            helper.setText(R.id.vs_row_content_tv42, item.getRows().get(3).getValue());
            helper.setText(R.id.vs_row_content_tv51, item.getRows().get(4).getKey());
            helper.setText(R.id.vs_row_content_tv52, item.getRows().get(4).getValue());
            helper.setClickLisenter(R.id.vs_row_content_tv42, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getRows().get(3).getValue() != null && (!item.getRows().get(3).getValue().equals(""))) {
                        betHelper.clickOdds(itemData, item.getB(), item.getRows().get(3).getValue(), (TextView) v, false,"&sc="+item.getRows().get(3).getSc()+childParam);

                    }
                }
            });
            helper.setClickLisenter(R.id.vs_row_content_tv52, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getRows().get(4).getValue() != null && (!item.getRows().get(4).getValue().equals(""))) {
                        betHelper.clickOdds(itemData, item.getB(), item.getRows().get(4).getValue(), (TextView) v, false,"&sc="+item.getRows().get(4).getSc()+childParam);

                    }
                }
            });

        } else {
            helper.setVisible(R.id.vs_row_content_ll4, false);
            helper.setVisible(R.id.vs_row_content_ll5, false);
            helper.setVisible(R.id.vs_row_line4, false);
            helper.setVisible(R.id.vs_row_line5, false);
        }
        helper.setText(R.id.vs_row_content_tv11, item.getRows().get(0).getKey());
        helper.setText(R.id.vs_row_content_tv12, item.getRows().get(0).getValue());
        helper.setText(R.id.vs_row_content_tv21, item.getRows().get(1).getKey());
        helper.setText(R.id.vs_row_content_tv22, item.getRows().get(1).getValue());
        helper.setText(R.id.vs_row_content_tv31, item.getRows().get(2).getKey());
        helper.setText(R.id.vs_row_content_tv32, item.getRows().get(2).getValue());
        helper.setClickLisenter(R.id.vs_row_content_tv12, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(0).getValue() != null && (!item.getRows().get(0).getValue().equals(""))) {
                    betHelper.clickOdds(itemData, item.getB(), item.getRows().get(0).getValue(), (TextView) v, false,"&sc="+item.getRows().get(0).getSc()+childParam);

                }
            }
        });
        helper.setClickLisenter(R.id.vs_row_content_tv22, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(1).getValue() != null && (!item.getRows().get(1).getValue().equals(""))) {
                    betHelper.clickOdds(itemData, item.getB(), item.getRows().get(1).getValue(), (TextView) v, false,"&sc="+item.getRows().get(1).getSc()+childParam);

                }
            }
        });
        helper.setClickLisenter(R.id.vs_row_content_tv32, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(2).getValue() != null && (!item.getRows().get(2).getValue().equals(""))) {
                    betHelper.clickOdds(itemData, item.getB(), item.getRows().get(2).getValue(), (TextView) v, false,"&sc="+item.getRows().get(2).getSc()+childParam);

                }
            }
        });
    }
//http://main55.afb88.com/_Bet/JRecPanel.aspx?gt=s&b=dc&oId=12286343&odds=1.38

    @Override
    protected int onSetItemLayoutId() {
        return R.layout.item_vs_goals_row_content;
    }
}
