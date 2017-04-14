package com.nanyang.app.main.home.sport.additional;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;
import com.nanyang.app.main.home.sport.model.VsCellBean;
import com.nanyang.app.main.home.sport.model.VsTableRowBean;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Administrator on 2015/11/4.
 */
public class ScaleFragment extends BaseVsFragment<VsTableRowBean> {


    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();


    }

    @Override
    public void initData() {
        super.initData();
        tvVsHeader.setVisibility(View.VISIBLE);
        if (((VsActivity) getActivity()).isMixParlay())
            initFirstData((SoccerMixInfo) itemData);
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
            helper.setVisible(R.id.vs_row_head_second_ll, false);
            helper.setText(R.id.vs_header_module_title_tv, item.getModuleTitle());
            helper.setText(R.id.vs_header_left_title_tv, item.getLeftTitle());
            helper.setText(R.id.vs_header_center_title_tv, item.getCenterTitle());
            helper.setText(R.id.vs_header_right_title_tv, item.getRightTitle());
        }
        helper.setText(R.id.vs_row_content_tv11, item.getRows().get(0).getKey());
        helper.setText(R.id.vs_row_content_tv12, Float.valueOf(item.getRows().get(0).getValue()) == 0.0f ? "" : item.getRows().get(0).getValue());
        helper.setText(R.id.vs_row_content_tv21, item.getRows().get(1).getKey());
        helper.setText(R.id.vs_row_content_tv22, Float.valueOf(item.getRows().get(1).getValue()) == 0.0f ? "" : item.getRows().get(1).getValue());
        helper.setText(R.id.vs_row_content_tv31, item.getRows().get(2).getKey());
        helper.setText(R.id.vs_row_content_tv32, Float.valueOf(item.getRows().get(2).getValue()) == 0.0f ? "" : item.getRows().get(2).getValue());

        helper.setClickLisenter(R.id.vs_row_content_tv22, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (item.getRows().get(1).getValue() != null && (!item.getRows().get(1).getValue().equals(""))) {
                    if (position > 2 && position < 6) {
                        betHelper.clickOdds(itemData, item.getB(), item.getRows().get(1).getValue(), (TextView) v, false, "&sc=" + item.getSc() + childParam);
                    } else {
                        betHelper.clickOdds(itemData, item.getB(), item.getRows().get(1).getValue(), (TextView) v, false, "&g=5" + childParam);
                    }


                }
            }
        });
        helper.setClickLisenter(R.id.vs_row_content_tv32, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(2).getValue() != null && (!item.getRows().get(2).getValue().equals(""))) {
                    if (position > 2 && position < 6) {
                        betHelper.clickOdds(itemData, item.getB(), item.getRows().get(2).getValue(), (TextView) v, true, "&sc=" + item.getSc() + childParam);
                    } else {
                        betHelper.clickOdds(itemData, item.getB(), item.getRows().get(2).getValue(), (TextView) v, true, "&g=5" + childParam);
                    }

                }
            }
        });


    }


    private void initFirstData(SoccerMixInfo item) {

        List<VsTableRowBean> rows = new ArrayList<VsTableRowBean>();
        int socOddsId_hf = 0;
        int socOddsId = 0;
        if (item.getSocOddsId_FH() != null && !item.getSocOddsId_FH().equals(""))
            socOddsId_hf = Integer.valueOf(item.getSocOddsId_FH());
        if (item.getSocOddsId() != null && !item.getSocOddsId().equals(""))
            socOddsId = Integer.valueOf(item.getSocOddsId());
        rows = Arrays.asList(new VsTableRowBean("1_par", Arrays.asList(new VsCellBean(getString(R.string.h1), "", 0), new VsCellBean("", item.getHasX12().equals("0") ? "" : item.getX12_1Odds(), socOddsId), new VsCellBean("", item.getHasX12_FH().equals("0") ? "" : item.getX12_1Odds_FH(), socOddsId_hf)), true, false, "1  x  2", "", "", ""),
                new VsTableRowBean("X_par", Arrays.asList(new VsCellBean(getString(R.string.dx), "", 0), new VsCellBean("", item.getHasX12().equals("0") ? "" : item.getX12_XOdds(), socOddsId), new VsCellBean("", item.getHasX12_FH().equals("0") ? "" : item.getX12_XOdds_FH(), socOddsId_hf))),
                new VsTableRowBean("2_par", Arrays.asList(new VsCellBean(getString(R.string.a2), "", 0), new VsCellBean("", item.getHasX12().equals("0") ? "" : item.getX12_2Odds(), socOddsId), new VsCellBean("", item.getHasX12_FH().equals("0") ? "" : item.getX12_2Odds_FH(), socOddsId_hf)), true),
                new VsTableRowBean("odd_par", Arrays.asList(new VsCellBean(getString(R.string.odd), "", 0), new VsCellBean("", item.getHasOE().equals("0") ? "" : item.getOddOdds(), socOddsId), new VsCellBean("", "", socOddsId_hf)), true, false, getString(R.string.odd_even), "", "", ""),
                new VsTableRowBean("even_par", Arrays.asList(new VsCellBean(getString(R.string.even), "", 0), new VsCellBean("", item.getHasOE().equals("0") ? "" : item.getEvenOdds(), socOddsId), new VsCellBean("", "", socOddsId_hf)), true));
        setData(rows);

    }
}
