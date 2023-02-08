package nanyang.com.dig88.Table.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshListView;
import nanyang.com.dig88.Entity.VsTableRowBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.ClearanceBetActivity;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.VsActivity;
import nanyang.com.dig88.Table.entity.BettingInfoBean;
import nanyang.com.dig88.Table.entity.BettingParPromptBean;
import nanyang.com.dig88.Table.entity.MatchBean;
import nanyang.com.dig88.Table.popupwindow.BetBasePop;
import nanyang.com.dig88.Table.utils.BettingDataHelper;
import nanyang.com.dig88.Table.utils.TableAdapterHelper;
import nanyang.com.dig88.Util.DeviceUtils;

/**
 * Created by Administrator on 2015/11/4.
 */
public class ScaleFragment extends BaseFragment {
    @BindString(R.string.scaleplate_asianplate)
    String scale_asianplate;
    @BindColor(R.color.white)

    int white;
    @BindColor(R.color.black_grey)
    int blackGrey;

    AdapterViewContent<VsTableRowBean> content;
    @BindView(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    @BindView(R.id.vs_scale_bet_count_tv)
    TextView betCountTv;
    private List<VsTableRowBean> data;
    private int betType;
    private Map<String, Map<String, Map<Integer, BettingInfoBean>>> betDetail;
    private MatchBean itemData;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_vs_scale;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        betType = ((VsActivity) getActivity()).getBetType();
        if (betType == TableAdapterHelper.ClearanceBet) {
            betCountTv.setVisibility(View.VISIBLE);
        } else {
            betCountTv.setVisibility(View.GONE);
        }
        betDetail = getApp().getBetDetail();
        itemData = ((VsActivity) getActivity()).getItem();
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        content = new AdapterViewContent<>(mContext, listContentPtrlv.getRefreshableView());
        content.setBaseAdapter(new QuickAdapterImp<VsTableRowBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_vs_scale_row_content;
            }

            @Override
            public void convert(ViewHolder helper, final VsTableRowBean item, final int position) {
                if (position == 0) {
                    helper.setTextColor(R.id.vs_row_content_tv11, mContext.getResources().getColor(R.color.red));
                    helper.setTextColor(R.id.vs_row_content_tv12, mContext.getResources().getColor(R.color.red));
                    helper.setTextColor(R.id.vs_row_content_tv21, mContext.getResources().getColor(R.color.red));
                    helper.setTextColor(R.id.vs_row_content_tv22, mContext.getResources().getColor(R.color.red));
                    helper.setTextColor(R.id.vs_row_content_tv31, mContext.getResources().getColor(R.color.red));
                    helper.setTextColor(R.id.vs_row_content_tv32, mContext.getResources().getColor(R.color.red));
                } else if (position == 1) {
                    helper.setTextColor(R.id.vs_row_content_tv11, Color.BLUE);
                    helper.setTextColor(R.id.vs_row_content_tv12, Color.BLUE);
                    helper.setTextColor(R.id.vs_row_content_tv21, Color.BLUE);
                    helper.setTextColor(R.id.vs_row_content_tv22, Color.BLUE);
                    helper.setTextColor(R.id.vs_row_content_tv31, Color.BLUE);
                    helper.setTextColor(R.id.vs_row_content_tv32, Color.BLUE);
                } else if (position == 6) {
                    if (item.getRows().get(2).getValue().startsWith("-")) {
                        helper.setTextColor(R.id.vs_row_content_tv32, mContext.getResources().getColor(R.color.red));
                    } else {
                        helper.setTextColor(R.id.vs_row_content_tv32, mContext.getResources().getColor(R.color.black));
                    }
                } else {
                    helper.setTextColor(R.id.vs_row_content_tv11, mContext.getResources().getColor(R.color.black));
                    helper.setTextColor(R.id.vs_row_content_tv12, mContext.getResources().getColor(R.color.black));
                    helper.setTextColor(R.id.vs_row_content_tv21, mContext.getResources().getColor(R.color.black));
                    helper.setTextColor(R.id.vs_row_content_tv22, mContext.getResources().getColor(R.color.black));
                    helper.setTextColor(R.id.vs_row_content_tv31, mContext.getResources().getColor(R.color.black));
                    helper.setTextColor(R.id.vs_row_content_tv32, mContext.getResources().getColor(R.color.black));
                }
                if (position == data.size() - 1) {
                    helper.setVisible(R.id.footer_line1, true);
                    helper.setVisible(R.id.footer_line2, true);
                }
                helper.setVisible(R.id.vs_row_head_ll, item.isHasHead());
                helper.setVisible(R.id.vs_row_foot_ll, false);
                TextView title = helper.retrieveView(R.id.vs_header_module_title_tv);
                if (item.isHasHead()) {
                    if (item.getModuleTitle().equals("1  x  2")) {
                        title.setText(handleStringColor(item.getModuleTitle()));
                    } else {
                        title.setText(item.getModuleTitle());
                    }
                    helper.setText(R.id.vs_header_left_title_tv, item.getLeftTitle());
                    helper.setText(R.id.vs_header_center_title_tv, item.getCenterTitle());
                    helper.setText(R.id.vs_header_right_title_tv, item.getRightTitle());
                }
                if (position == 3 || position == 4 || position == 5) {
                    String name = item.getRows().get(0).getKey();
                    if (name.length() > 1) {
                        TextView tv = helper.retrieveView(R.id.vs_row_content_tv11);
                        if (name.equals("X2")) {
                            name = "2X";
                        }
                        tv.setText(handleStringColor(name));
                    } else {
                        helper.setText(R.id.vs_row_content_tv11, item.getRows().get(0).getKey());
                    }
                } else {
                    helper.setText(R.id.vs_row_content_tv11, item.getRows().get(0).getKey());
                }
                helper.setText(R.id.vs_row_content_tv12, item.getRows().get(0).getValue());
                helper.setText(R.id.vs_row_content_tv21, item.getRows().get(1).getKey());
                helper.setText(R.id.vs_row_content_tv22, item.getRows().get(1).getValue());
                helper.setText(R.id.vs_row_content_tv31, item.getRows().get(2).getKey());
                helper.setText(R.id.vs_row_content_tv32, item.getRows().get(2).getValue());
                helper.setClickLisenter(R.id.vs_row_content_tv22, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getRows().get(1).getValue() != null && (!item.getRows().get(1).getValue().equals(""))) {
                            if (betType == TableAdapterHelper.ClearanceBet) {
                                BettingInfoBean info = new BettingInfoBean("", item.getB(), item.getSc(), "", item.getRows().get(1).getValue(),
                                        itemData.getHome(), itemData.getAway(), item.getModuleTitle(), item.getRows().get(1).getOid() + "", "", 0, false, false);

                                handleClearanceBet(v, position + item.getB(), 1, info);
                            } else {
                                BetBasePop pop = new BetBasePop(mContext, v, DeviceUtils.getScreenWidth((Activity) mContext)/5*4, LinearLayout.LayoutParams.WRAP_CONTENT);
                                pop.showPopupCenterWindowBlack();
                                BettingInfoBean info = new BettingInfoBean("s", item.getB(), item.getSc(), "", item.getRows().get(1).getValue(),
                                        itemData.getHome(), itemData.getAway(), item.getModuleTitle(), item.getRows().get(1).getOid() + "", "", 0, false, false);
                                pop.getData(info);
                            }

                        }
                    }
                });
                helper.setClickLisenter(R.id.vs_row_content_tv32, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getRows().get(2).getValue() != null && (!item.getRows().get(2).getValue().equals(""))) {
                            if (betType == TableAdapterHelper.ClearanceBet) {
                                BettingInfoBean info = new BettingInfoBean("", item.getB(), item.getSc(), "", item.getRows().get(2).getValue(),
                                        itemData.getHome(), itemData.getAway(), item.getModuleTitle(), item.getRows().get(2).getOid() + "", item.getRows().get(2).getOid() + "", 1, false, false);
                                handleClearanceBet(v, position + item.getB(), 2, info);
                            } else {
                                BetBasePop pop = new BetBasePop(mContext, v, DeviceUtils.getScreenWidth((Activity) mContext)/5*4, LinearLayout.LayoutParams.WRAP_CONTENT);
                                pop.setBetSelectionType(getString(R.string.half_time));
                                pop.showPopupCenterWindowBlack();
                                BettingInfoBean info = new BettingInfoBean("s", item.getB(), item.getSc(), itemData.getHandicapBeans().get(1).getHdp(), item.getRows().get(2).getValue(),
                                        itemData.getHome(), itemData.getAway(), item.getModuleTitle(), item.getRows().get(2).getOid() + "", item.getRows().get(2).getOid() + "", 1, false, false);
                                pop.getData(info);
                            }
                        }
                    }
                });
                if (betType == TableAdapterHelper.ClearanceBet)
                    notifyClearanceBet(position + item.getB(), helper);
            }
        });
        content.setData(data);

    }

    @Override
    public void onResume() {
        super.onResume();
        content.notifyDataSetChanged();
        countBet();

    }

    public void setData(List<VsTableRowBean> data) {
        this.data = data;
        if (content != null) {
            content.getAdapter().setList(data);
        }
    }

    private void handleClearanceBet(View v, final String rowPosition, final int i, final BettingInfoBean info) {

        if (info != null) {
            BettingDataHelper bettingDataHelper = new BettingDataHelper(mContext, null, new ThreadEndT<BettingParPromptBean>(new TypeToken<BettingParPromptBean>() {
            }.getType()) {
                @Override
                public void endT(BettingParPromptBean result, int model) {
                    if (result != null && getApp() != null)
                        getApp().setBetParList(result);
                    countBet();
                }

                @Override
                public void endString(String result, int model) {

                }

                @Override
                public void endError(String error) {
                    if (betDetail != null)
                        betDetail.put(((VsActivity) getActivity()).getItem().getHome() + "+" + ((VsActivity) getActivity()).getItem().getAway(), new HashMap<String, Map<Integer, BettingInfoBean>>());
                }
            });
            bettingDataHelper.getData(info);
            Map<Integer, BettingInfoBean> positionMap = new HashMap<>();
            positionMap.put(i, info);
            Map<String, Map<Integer, BettingInfoBean>> keyMap = new HashMap<>();
            keyMap.put(rowPosition, positionMap);
            betDetail.put(((VsActivity) getActivity()).getItem().getHome() + "+" + ((VsActivity) getActivity()).getItem().getAway(), keyMap);
            content.notifyDataSetChanged();

        } else {

        }
    }

    private void notifyClearanceBet(String position, ViewHolder helper) {
        Map<String, Map<Integer, BettingInfoBean>> keyMap = betDetail.get(((VsActivity) getActivity()).getItem().getHome() + "+" + ((VsActivity) getActivity()).getItem().getAway());
        if (keyMap == null)
            return;
        Map<Integer, BettingInfoBean> positionMap = keyMap.get(position + "");
        if (positionMap == null) {
            helper.setBackgroundRes(R.id.vs_row_content_tv22, R.color.listView_bg);
            helper.setTextColor(R.id.vs_row_content_tv22, blackGrey);
            helper.setBackgroundRes(R.id.vs_row_content_tv32, R.color.listView_bg);
            helper.setTextColor(R.id.vs_row_content_tv32, blackGrey);
            return;
        }

        for (Map.Entry<Integer, BettingInfoBean> entry : positionMap.entrySet()) {
            int key = entry.getKey();
            if (key == 1) {
                helper.setBackgroundRes(R.id.vs_row_content_tv22, R.drawable.rectangle_green_table_bg_allradius5);
                helper.setTextColor(R.id.vs_row_content_tv22, white);
                helper.setBackgroundRes(R.id.vs_row_content_tv32, R.color.listView_bg);
                helper.setTextColor(R.id.vs_row_content_tv32, blackGrey);


            } else if (key == 2) {
                helper.setBackgroundRes(R.id.vs_row_content_tv32, R.drawable.rectangle_green_table_bg_allradius5);
                helper.setTextColor(R.id.vs_row_content_tv32, white);
                helper.setBackgroundRes(R.id.vs_row_content_tv22, R.color.listView_bg);
                helper.setTextColor(R.id.vs_row_content_tv22, blackGrey);
            }
        }
    }

    private void countBet() {
        BettingParPromptBean result = getApp().getBetParList();
        if (betCountTv != null && result != null) {
            betCountTv.setText(result.getBetPar().size() + "");
            betCountTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppTool.activiyJump(mContext, ClearanceBetActivity.class);
                    if (getAct() != null)
                        getAct().finish();
                }
            });
        }
    }

    public SpannableStringBuilder handleStringColor(String str) {
        if (str.equals("2X")) {
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), 0, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            style.setSpan(new ForegroundColorSpan(Color.BLUE), 1, str.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            return style;
        } else if (str.equals("12")) {
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red)), 0, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), 1, str.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            return style;
        } else if (str.equals("1  x  2")) {
            str = "1 x 2";
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red)), 0, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            style.setSpan(new ForegroundColorSpan(Color.BLUE), 2, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), 4, str.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            return style;
        } else {
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red)), 0, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            style.setSpan(new ForegroundColorSpan(Color.BLUE), 1, str.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            return style;
        }
    }
}
