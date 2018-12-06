package com.nanyang.app.main.home.sport.additional;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.VsCellBean;
import com.nanyang.app.main.home.sport.model.VsTableRowBean;
import com.nanyang.app.main.home.sportInterface.BaseMixStyleHandler;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nanyang.app.AfbUtils.setColorStyle;


/**
 * Created by Administrator on 2015/11/4.
 */
public class FirstFragment extends BaseVsFragment<VsTableRowBean> {


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
            initFirstData((BallInfo) itemData);
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
        String value0 = getValue0(item);
        if (value0 == null || value0.equals("") || Float.valueOf(getValue0(item)) == 0.0f) {
            value0 = "";
        }
        String value1 = getValue1(item);
        if (value1 == null || value1.equals("") || Float.valueOf(getValue1(item)) == 0.0f) {
            value1 = "";
        }
        String value2 = getValue2(item);
        if (value2 == null || value2.equals("") || Float.valueOf(getValue2(item)) == 0.0f) {
            value2 = "";
        }
        helper.setText(R.id.vs_row_content_tv12, value0);


        if (item.getRows().get(1).getKey() == null || item.getRows().get(1).getKey().equals("")) {
            helper.getView(R.id.vs_row_content_tv21).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.vs_row_content_tv21).setVisibility(View.VISIBLE);
        }
        if (item.getRows().get(2).getKey() == null || item.getRows().get(2).getKey().equals("")) {
            helper.getView(R.id.vs_row_content_tv31).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.vs_row_content_tv31).setVisibility(View.VISIBLE);
        }

        helper.setText(R.id.vs_row_content_tv21, item.getRows().get(1).getKey());
        helper.setText(R.id.vs_row_content_tv22, value1);
        BaseMixStyleHandler baseMixStyleHandler = new BaseMixStyleHandler((BaseToolbarActivity) mContext);
        baseMixStyleHandler.setCommonBackground(helper.getTextView(R.id.vs_row_content_tv22));
        baseMixStyleHandler.setCommonBackground(helper.getTextView(R.id.vs_row_content_tv32));
        if (item.getB().toString().equals("1") || item.getB().toString().equals("X") || item.getB().toString().equals("2")
                || item.getB().toString().equals("odd") || item.getB().toString().equals("even")) {
            int itemFullSocOddsId = item.getRows().get(1).getOid();
            AfbClickBetBean mixItem = baseMixStyleHandler.getMixItem(itemFullSocOddsId + "");
            if (mixItem != null) {
                if (mixItem.getOddsType().startsWith(item.getB().toString()))
                    baseMixStyleHandler.setMixBackground(helper.getTextView(R.id.vs_row_content_tv22));
            }
        }
        helper.setText(R.id.vs_row_content_tv31, item.getRows().get(2).getKey());
        helper.setText(R.id.vs_row_content_tv32, value2);

        if (item.getB().toString().equals("1") || item.getB().toString().equals("X") || item.getB().toString().equals("2")) {
            int itemFullSocOddsId = item.getRows().get(2).getOid();

            AfbClickBetBean mixItem = baseMixStyleHandler.getMixItem(itemFullSocOddsId + "");
            if (mixItem != null) {
                if (mixItem.getOddsType().startsWith(item.getB().toString()))
                    baseMixStyleHandler.setMixBackground(helper.getTextView(R.id.vs_row_content_tv32));
            }
        }
        helper.setClickLisenter(R.id.vs_row_content_tv22, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
                String aa = childParam;
                if (getValue1(item) != null && (!getValue1(item).equals("")) && Float.valueOf(getValue1(item)) != 0f) {
                    if (position > 2 && position < 6) {
                        betHelper.clickOdds(itemData, item.getRows().get(1).getOid(), item.getB().toString(), getValue1(item), (TextView) v, false, /*"&sc=" +*/ item.getSc().toString(), true/*+ childParam*/);
                    } else if (position < 8) {
                        betHelper.clickOdds(itemData, item.getRows().get(1).getOid(), item.getB().toString(), getValue1(item), (TextView) v, false, "", true/*"&g=5" + childParam*/);
                    } else {
                        betHelper.clickOdds(itemData, item.getRows().get(1).getOid(), item.getB().toString(), getValue1(item), (TextView) v, false, "", false /*childParam*/);
                    }


                }
            }
        });
        helper.setClickLisenter(R.id.vs_row_content_tv32, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
                if (getValue2(item) != null && (!getValue2(item).equals("")) && Float.valueOf(getValue2(item)) != 0f) {
                    if (position > 2 && position < 6) {
                        betHelper.clickOdds(itemData, item.getRows().get(2).getOid(), item.getB().toString(), getValue2(item), (TextView) v, true, /*"&sc=" +*/ item.getSc().toString(), true/*+ childParam*/);
                    } else if (position < 8) {
                        betHelper.clickOdds(itemData, item.getRows().get(2).getOid(), item.getB().toString(), getValue2(item), (TextView) v, true, "", true/*+"&g=5"  childParam*/);
                    } else {
                        betHelper.clickOdds(itemData, item.getRows().get(2).getOid(), item.getB().toString(), getValue2(item), (TextView) v, false, "", false/*childParam*/);
                    }

                }
            }
        });
        if (position == 0) {
            helper.getTextView(R.id.vs_row_content_tv22).setTextColor(getResources().getColor(R.color.red_title));
            helper.getTextView(R.id.vs_row_content_tv32).setTextColor(getResources().getColor(R.color.red_title));
        } else if (position == 1) {
            helper.getTextView(R.id.vs_row_content_tv22).setTextColor(getResources().getColor(R.color.blue));
            helper.getTextView(R.id.vs_row_content_tv32).setTextColor(getResources().getColor(R.color.blue));
        } else {
            setOddsTextColor(value1, helper.getTextView(R.id.vs_row_content_tv22));
            setOddsTextColor(value2, helper.getTextView(R.id.vs_row_content_tv32));
        }

    }

    private String getValue2(VsTableRowBean item) {
        return item.getRows().get(2).getValue();
    }

    private String getValue1(VsTableRowBean item) {
        return item.getRows().get(1).getValue();
    }

    private String getValue0(VsTableRowBean item) {
        return item.getRows().get(0).getValue();
    }


    private void initFirstData(BallInfo item) {
        List<VsTableRowBean> rows;
        int socOddsId_hf = 0;
        int socOddsId = 0;
        if (item.getSocOddsId_FH() != null && !item.getSocOddsId_FH().equals(""))
            socOddsId_hf = Integer.valueOf(item.getSocOddsId_FH());
        if (item.getSocOddsId() != null && !item.getSocOddsId().equals(""))
            socOddsId = Integer.valueOf(item.getSocOddsId());
        rows = new ArrayList<>(Arrays.asList(new VsTableRowBean("1_par", Arrays.asList(new VsCellBean(setColorStyle(getString(R.string.h1), new int[]{getResources().getColor(R.color.red_title)}, new String[]{getString(R.string.h1)}), "", 0), new VsCellBean("", item.getHasX12().equals("0") ? "" : item.getX12_1Odds(), socOddsId), new VsCellBean("", item.getX12_1Odds_FH(), socOddsId_hf)), true, false, setColorStyle("1  x  2", new int[]{getResources().getColor(R.color.red_title), getResources().getColor(R.color.blue)}, new String[]{"1", "x"}), "", "", ""),
                new VsTableRowBean("X_par", Arrays.asList(new VsCellBean(setColorStyle(getString(R.string.dx), new int[]{getResources().getColor(R.color.blue)}, new String[]{getString(R.string.dx)}), "", 0), new VsCellBean("", item.getHasX12().equals("0") ? "" : item.getX12_XOdds(), socOddsId), new VsCellBean("", item.getX12_XOdds_FH(), socOddsId_hf))),
                new VsTableRowBean("2_par", Arrays.asList(new VsCellBean(getString(R.string.a2), "", 0), new VsCellBean("", item.getHasX12().equals("0") ? "" : item.getX12_2Odds(), socOddsId), new VsCellBean("", item.getX12_2Odds_FH(), socOddsId_hf)), true),
                new VsTableRowBean("odd_par", Arrays.asList(new VsCellBean(getString(R.string.odd), "", 0), new VsCellBean("", AfbUtils.decimalValue(Float.valueOf(item.getOddOdds()) / 10, "0.00"), socOddsId), new VsCellBean("", "", socOddsId_hf)), true, false, getString(R.string.odd_even), "", "", ""),
                new VsTableRowBean("even_par", Arrays.asList(new VsCellBean(getString(R.string.even), "", 0), new VsCellBean("", AfbUtils.decimalValue(Float.valueOf(item.getEvenOdds()) / 10, "0.00"), socOddsId), new VsCellBean("", "", socOddsId_hf)), true)));
        setData(rows);

    }

}
