package com.nanyang.app.main.home.sport.additional;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.VsTableRowBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2015/11/4.
 */
public class SecondFragment extends BaseVsFragment<VsTableRowBean> {
    @Bind(R.id.base_rv2)
    RecyclerView baseRv2;
    private BaseRecyclerAdapter<VsTableRowBean> adapter2;

    @Override
    public void initData() {
        super.initData();
        tvVsHeader.setVisibility(View.GONE);
        adapter2 = new BaseRecyclerAdapter<VsTableRowBean>(mContext, new ArrayList<VsTableRowBean>(), R.layout.item_vs_goals_row_content_h) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, VsTableRowBean item) {
                convertItem(holder, position, item);
            }

        };
        baseRv2.setLayoutManager(new LinearLayoutManager(mContext));
        baseRv2.setAdapter(adapter2);
    }

    @Override
    protected void setData(List<VsTableRowBean> list) {
        this.list = list;
        refresh(list);
    }

    private void refresh(List<VsTableRowBean> list) {
        if (list.size() > 4) {
            List<VsTableRowBean> vsTableRowBeen = list.subList(0, 5);
            if (adapter != null)
                adapter.addAllAndClear(vsTableRowBeen);
            if (list.size() > 5) {
                List<VsTableRowBean> list2 = list.subList(5, list.size());
                if (adapter2 != null)
                    adapter2.addAllAndClear(list2);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh(list);
    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_vs_sd;
    }

    @Override
    protected void convertItem(MyRecyclerViewHolder helper, int position, final VsTableRowBean item) {


        helper.setVisible(R.id.vs_row_head_ll, item.isHasHead());
        helper.setVisible(R.id.vs_row_foot_ll, item.isHasFoot());
        if (item.isHasHead()) {
            helper.setVisible(R.id.vs_row_head_second_ll, false);
            helper.setText(R.id.vs_header_module_title_tv, item.getModuleTitle());
        }
        helper.setVisible(R.id.vs_row_content_ll4, true);
        helper.setVisible(R.id.vs_row_content_ll5, true);
        helper.setVisible(R.id.vs_row_line4, true);
        helper.setVisible(R.id.vs_row_line5, true);
        switch (item.getRows().size()) {
            case 3:
                helper.setVisible(R.id.vs_row_content_ll4, false);
                helper.setVisible(R.id.vs_row_content_ll5, false);
                helper.setVisible(R.id.vs_row_line4, false);
                helper.setVisible(R.id.vs_row_line5, false);
                break;
            case 4:
                helper.setVisible(R.id.vs_row_line5, false);
                helper.setVisible(R.id.vs_row_content_ll5, false);
                setRowItem4(helper, item);
                break;
            case 5:
                setRowItem4(helper, item);
                helper.setText(R.id.vs_row_content_tv51, item.getRows().get(4).getKey());
                helper.setText(R.id.vs_row_content_tv52, item.getRows().get(4).getValue());
                setOddsTextColor(item.getRows().get(4).getValue(), helper.getTextView(R.id.vs_row_content_tv52));
                helper.setClickLisenter(R.id.vs_row_content_tv52, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getRows().get(4).getValue() != null && (!item.getRows().get(4).getValue().equals(""))) {
                            betHelper.clickOdds(itemData, item.getRows().get(4).getOid(), item.getB().toString().equals("") ? item.getRows().get(4).getB() : item.getB().toString(), item.getRows().get(4).getValue(), (TextView) v, false, item.getRows().get(4).getSc(), false);

                        }
                    }
                });
                break;
        }


        helper.setText(R.id.vs_row_content_tv11, item.getRows().get(0).getKey());
        helper.setText(R.id.vs_row_content_tv12, item.getRows().get(0).getValue());
        setOddsTextColor(item.getRows().get(0).getValue(), helper.getTextView(R.id.vs_row_content_tv12));
        helper.setText(R.id.vs_row_content_tv21, item.getRows().get(1).getKey());
        helper.setText(R.id.vs_row_content_tv22, item.getRows().get(1).getValue());
        setOddsTextColor(item.getRows().get(1).getValue(), helper.getTextView(R.id.vs_row_content_tv22));
        helper.setText(R.id.vs_row_content_tv31, item.getRows().get(2).getKey());
        helper.setText(R.id.vs_row_content_tv32, item.getRows().get(2).getValue());
        setOddsTextColor(item.getRows().get(2).getValue(), helper.getTextView(R.id.vs_row_content_tv32));
        helper.setClickLisenter(R.id.vs_row_content_tv12, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(0).getValue() != null && (!item.getRows().get(0).getValue().equals(""))) {
                    betHelper.clickOdds(itemData, item.getRows().get(0).getOid(), item.getB().toString().equals("") ? item.getRows().get(0).getB() : item.getB().toString(), item.getRows().get(0).getValue(), (TextView) v, false, item.getRows().get(0).getSc(), false);

                }
            }
        });
        helper.setClickLisenter(R.id.vs_row_content_tv22, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(1).getValue() != null && (!item.getRows().get(1).getValue().equals(""))) {
                    betHelper.clickOdds(itemData, item.getRows().get(1).getOid(), item.getB().toString().equals("") ? item.getRows().get(1).getB() : item.getB().toString(), item.getRows().get(1).getValue(), (TextView) v, false, item.getRows().get(1).getSc(), false);

                }
            }
        });
        helper.setClickLisenter(R.id.vs_row_content_tv32, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(2).getValue() != null && (!item.getRows().get(2).getValue().equals(""))) {
                    betHelper.clickOdds(itemData, item.getRows().get(2).getOid(), item.getB().toString().equals("") ? item.getRows().get(2).getB() : item.getB().toString(), item.getRows().get(2).getValue(), (TextView) v, false, item.getRows().get(2).getSc(), false);

                }
            }
        });
    }

    private void setRowItem4(MyRecyclerViewHolder helper, final VsTableRowBean item) {
        helper.setText(R.id.vs_row_content_tv41, item.getRows().get(3).getKey());
        helper.setText(R.id.vs_row_content_tv42, item.getRows().get(3).getValue());
        TextView textView42 = helper.getTextView(R.id.vs_row_content_tv42);
        setOddsTextColor(item.getRows().get(3).getValue(), textView42);
        helper.setClickLisenter(R.id.vs_row_content_tv42, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getRows().get(3).getValue() != null && (!item.getRows().get(3).getValue().equals(""))) {
                    betHelper.clickOdds(itemData, item.getRows().get(3).getOid(), item.getB().toString().equals("") ? item.getRows().get(3).getB() : item.getB().toString(), item.getRows().get(3).getValue(), (TextView) v, false, item.getRows().get(3).getSc(), false);

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
