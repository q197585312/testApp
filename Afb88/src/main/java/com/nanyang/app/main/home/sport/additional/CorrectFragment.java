package com.nanyang.app.main.home.sport.additional;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.VsTableRowBean;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;


/**
 * Created by Administrator on 2015/11/4.
 */
public class CorrectFragment extends BaseVsFragment<VsTableRowBean> {

    @Override
    public void initData() {
        super.initData();
        tvVsHeader.setVisibility(View.GONE);
    }
    @Override
    protected void convertItem(MyRecyclerViewHolder helper, final int position, final VsTableRowBean item) {
        helper.setVisible(R.id.vs_row_head_ll, item.isHasHead());
        helper.setVisible(R.id.vs_row_foot_ll, item.isHasFoot());
        if (item.isHasHead()) {
            helper.setVisible(R.id.vs_row_head_second_ll,false);
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
                    betHelper.clickOdds(itemData,item.getB(),item.getRows().get(0).getValue(),(TextView) v,item.isFh(),"&sc="+item.getRows().get(0).getSc()+childParam);

                }
            }
        });
        helper.setClickLisenter(R.id.vs_row_content_tv22, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(1).getValue() != null && (!item.getRows().get(1).getValue().equals(""))) {
                    betHelper.clickOdds(itemData,item.getB(),item.getRows().get(1).getValue(),(TextView) v,item.isFh(),"&sc="+item.getRows().get(1).getSc()+childParam);

                }
            }
        });
        helper.setClickLisenter(R.id.vs_row_content_tv32, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(2).getValue() != null && (!item.getRows().get(2).getValue().equals(""))) {
                    betHelper.clickOdds(itemData,item.getB(),item.getRows().get(2).getValue(),(TextView) v,item.isFh(),"&sc="+item.getRows().get(2).getSc()+childParam);

                }
            }
        });
    }


    @Override
    protected int onSetItemLayoutId() {
        return R.layout.item_vs_correct_row_content;
    }
}
