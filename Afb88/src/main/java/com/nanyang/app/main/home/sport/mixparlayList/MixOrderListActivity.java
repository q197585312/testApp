package com.nanyang.app.main.home.sport.mixparlayList;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.ClearanceBetAmountBean;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.listview.ItemRemoveRecyclerView;
import com.unkonw.testapp.libs.view.listview.OnItemClickListener;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.BindString;
import cn.finalteam.toolsfinal.StringUtils;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/2/21.
 */
public class MixOrderListActivity extends BaseToolbarActivity<MixOrderListPresenter> implements MixOrderListContract.View<String> {

    @Bind(R.id.rv_content)
    ItemRemoveRecyclerView rvContent;

    @BindString(R.string.loading)
    String loading;

    private HashMap<LeagueBean, Boolean> selecteds = new HashMap<>();
    //    private BettingParPromptBean  getApp().getBetParList();
    private Map<Boolean, Integer> selectedMap = new HashMap<>();


    private TextView headOddsEdt;
    private ImageView moreIv;
    private TextView headAmountTv;
    private String betUrl;


    BaseRecyclerAdapter<BettingParPromptBean.BetParBean> listAdapter;
    private LinearLayout llBottom;
    private TextView footerCountTv;
    private TextView footerContentTv;
    private Button footerCancelBtn;
    private ClearanceBetAmountBean selectedBean;

    private BallBetHelper helper;
    private MenuItemInfo<String> type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert tvToolbarTitle != null;
        setContentView(R.layout.activity_mix_parlay_list);
        createPresenter(new MixOrderListPresenter(this));
        if (getApp().getBetParList() == null || getApp().getBetParList().getBetPar().size() < 1)
            return;
        initBottomListData();
        initListData();
        tvToolbarTitle.setBackgroundResource(0);
        tvToolbarTitle.setText(R.string.MixParlay);
        tvToolbarRight.setVisibility(View.GONE);
        type = (MenuItemInfo<String>) getIntent().getSerializableExtra(AppConstant.KEY_DATA);
        helper = new BallBetHelper(this) {
            @Override
            public Disposable clickOdds(Object item, String type, String odds, TextView v, boolean isHf, String params) {
                return null;
            }
        };
    }

    private void initListData() {
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        listAdapter = new BaseRecyclerAdapter<BettingParPromptBean.BetParBean>(mContext, new ArrayList<BettingParPromptBean.BetParBean>(), R.layout.mix_parlay_order_item) {
            @Override
            public void convert(MyRecyclerViewHolder helper, final int position, final BettingParPromptBean.BetParBean item) {

                helper.setText(R.id.clearance_type_tv, type.getParent());
                if (!item.getParFullTimeId().equals("0") && !item.getParFullTimeId().equals("")) {
                    helper.setText(R.id.clearance_type_tv, getString(R.string.football) + "(" + getString(R.string.half_time) + ")");
                }

                helper.setText(R.id.clearance_home_tv, item.getHome());
                helper.setText(R.id.clearance_away_tv, item.getAway());
                setOddsText(helper, item);
            }
        };
        rvContent.setAdapter(listAdapter);

        rvContent.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                presenter.removeBetItem(listAdapter.getItem(position));
            }
        });
        presenter.obtainListData();

    }


    private void initBottomListData() {
        View selectLl = findViewById(R.id.header_clearance_select_ll);
        //header
        headAmountTv = (TextView) findViewById(R.id.header_clearance_amount_tv);
        headOddsEdt = (TextView) findViewById(R.id.header_clearance_odds_edt);
        moreIv = (ImageView) findViewById(R.id.header_clearance_more_mark_iv);
        //footer
        footerCountTv = (TextView) findViewById(R.id.footer_clearance_count_tv);
        footerContentTv = (TextView) findViewById(R.id.footer_clearance_content_tv);
        Button footerBetTv = (Button) findViewById(R.id.footer_clearance_bet_submit_btn);
        footerCancelBtn = (Button) findViewById(R.id.footer_clearance_bet_cancel_btn);
        selectLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSelectedList();
            }
        });
        footerBetTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitParBet(v);
            }
        });
        footerCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBet(v);
            }
        });

        footerCountTv.setText(getString(R.string.odds) + getApp().getBetParList().getPayoutOdds() + "");
        footerContentTv.setText(getString(R.string.max_bet) + getApp().getBetParList().getMaxLimit() + "    " + getString(R.string.min_bet) + getApp().getBetParList().getMinLimit());
        selectedMap.put(true, 0);
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom_content);
        presenter.showBottomSelectedList();
    }

    private void addBottomDate(List<ClearanceBetAmountBean> data) {
        selectedBean = new ClearanceBetAmountBean(1, "");
        if (data.size() > 0) {
            selectedBean = data.get(0);
            llBottom.removeAllViews();
            for (int position = 0; position < data.size(); position++) {
                final ClearanceBetAmountBean clearanceBetAmountBean = data.get(position);
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.selected_text_item, null, false);
                TextView viewById = (TextView) inflate.findViewById(R.id.selectable_text_content_tv);
                viewById.setText(clearanceBetAmountBean.getTitle());
                if (position == selectedMap.get(true)) {
                    footerCountTv.setText(clearanceBetAmountBean.getTitle());
//                    viewById.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.menu_right_hover, 0);
                    dynamicAddView(viewById, "drawableRight", R.mipmap.menu_right_hover);
                } else {
                    viewById.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                final int finalPosition = position;
                inflate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedMap.put(true, finalPosition);
                        headAmountTv.setText(clearanceBetAmountBean.getTitle());
                        footerCountTv.setText(clearanceBetAmountBean.getTitle());
                        selectedBean = clearanceBetAmountBean;
                    }
                });

                llBottom.addView(inflate);
            }
        }
        headAmountTv.setText(selectedBean.getTitle());
    }

    private void cancelBet(View v) {
        presenter.removeAll();
    }

    private void submitParBet(View v) {

        String amt = headOddsEdt.getText().toString().trim();
        if (StringUtils.isEmpty(amt)) {
            ToastUtils.showShort(R.string.input_bet_amount_please);
            return;
        } else if (getApp().getBetParList() == null || getApp().getBetParList().getBetPar().size() < 3) {

            ToastUtils.showShort(R.string.clearance_should_be_more_than_three);
            return;

        }
        if (!getApp().getBetParList().getMaxLimit().equals("") && !getApp().getBetParList().getMaxLimit().equals("0") && !getApp().getBetParList().getMinLimit().equals("")) {
            int max = Integer.valueOf(getApp().getBetParList().getMaxLimit());
            int min = Integer.valueOf(getApp().getBetParList().getMinLimit());
            int amount = Integer.valueOf(amt);
            if (amount < min || amount > max) {
                Toast.makeText(mContext, getString(R.string.invalid_amount_bet), Toast.LENGTH_SHORT).show();
                headOddsEdt.setText("");
                return;
            }
        }
//a8197c.a36588.com/_Bet/PanelBet.aspx?betType=X_par&odds=160.670744228768&amt=10&coupon=1&exRate=1
        //"PanelBet.aspx?betType=X_par&odds=160.670744228768",
        betUrl = AppConstant.getInstance().HOST+ AppConstant.getInstance()._BET + getApp().getBetParList().getBetUrl() + "&amt=" + amt + "&coupon=" + selectedBean.getAmount() + "&exRate=" + getApp().getBetParList().getExRate();
        helper.bet(betUrl);

    }

    @Override
    public void onBetSuccess(String betResult) {
        super.onBetSuccess(betResult);
        getApp().setBetParList(null);
        finish();
    }

    private void checkEnd(String result) {
        if (result == null) {
            Toast.makeText(mContext, getString(R.string.bet_failed), Toast.LENGTH_SHORT).show();
            return;
        }

        if (result.startsWith("Parlay") || result.contains("r=")) {
            Toast.makeText(mContext, getString(R.string.bet_succeed), Toast.LENGTH_SHORT).show();
            getApp().setBetParList(null);
            finish();
        } else if (result.startsWith("MULTIBET")) {
            getApp().setBetParList(null);
            finish();
        } else if (result.startsWith("PARCHG")) {
            BaseYseNoChoosePopupWindow pop = new BaseYseNoChoosePopupWindow(mContext, rvContent) {
                @Override
                protected void clickSure(View v) {
                    helper.bet(betUrl);
                }
            };
            pop.getChooseTitleTv().setText(getString(R.string.confirm_or_not));
            pop.getChooseMessage().setText(result + " " + getString(R.string.do_you_bet_again));
            pop.getChooseSureTv().setText(getString(R.string.sure));
            pop.getChooseCancelTv().setText(getString(R.string.cancel));
            pop.showPopupCenterWindow();
        } else {
            Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
        }
    }

    private void changeSelectedList() {
        if (llBottom.getVisibility() == View.GONE) {
            llBottom.setVisibility(View.VISIBLE);
            moreIv.setImageResource(R.mipmap.arrow_down_green);
        } else {
            llBottom.setVisibility(View.GONE);
            moreIv.setImageResource(R.mipmap.arrow_up_green);
        }
    }

    SpannableStringBuilder style;


    public void setOddsText(MyRecyclerViewHolder helper, BettingParPromptBean.BetParBean item) {
        String b = item.getTransType();
        boolean isHome = item.isIsBetHome();
        String hdp = item.getBetHdp();
        String state = b;
        SpannableStringBuilder style = null;
        if (b.equals("1"))
            state = item.getHome() + "(" + mContext.getString(R.string.win) + ")";
        else if (b.equals("1_par"))
            state = "1X2:" + item.getHome() + "(" + mContext.getString(R.string.win) + ")";
        else if (b.equals("2"))
            state = item.getAway() + "(" + mContext.getString(R.string.win) + ")";
        else if (b.equals("2_par")) {
            state = "1X2:" + item.getAway() + "(" + mContext.getString(R.string.win) + ")";
        } else if (b.equalsIgnoreCase("x"))
            state = item.getHome() + "(" + mContext.getString(R.string.draw) + ")";
        else if (b.equalsIgnoreCase("X_par")) {
            state = "1X2:" + item.getHome() + "(" + mContext.getString(R.string.draw) + ")";
        } else if (b.equalsIgnoreCase("OE")) {
            if (isHome) {
                state = "OE:" + item.getHome() + "(" + mContext.getString(R.string.odd) + ")";
                style = new SpannableStringBuilder(state);
                style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_title)), state.indexOf("("), state.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                state = "OE:" + item.getHome() + "(" + mContext.getString(R.string.even) + ")";
            }

        } else if (b.equals("dc")) {
            state = getString(R.string.double_chance);
        } else if (b.equals("htft")) {
            state = getString(R.string.half_full_time);
        } else if (b.equals("fglg")) {
            state = getString(R.string.first_last_goal);

        } else if (b.equals("tg")) {
            state = getString(R.string.total_goals);

        } else if (b.equalsIgnoreCase("HDP")) {
            if (isHome) {

                state = "HDP:" + item.getHome();
                if (item.isIsHomeGive()) {
                    style = new SpannableStringBuilder(state);
                    style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_title)), state.indexOf(":"), state.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            } else {
                state = "HDP:" + item.getAway();
                if (!item.isIsHomeGive()) {
                    style = new SpannableStringBuilder(state);
                    style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_title)), state.indexOf(":"), state.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }

        } else if (b.equalsIgnoreCase("OU")) {
            hdp = item.getBetOu();
            if (isHome) {
                state = "OU:" + item.getHome() + "(" + getString(R.string.over) + ")";
                style = new SpannableStringBuilder(state);
                style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_title)), state.indexOf("("), state.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                state = "OU:" + item.getAway() + "(" + getString(R.string.under) + ")";
            }
        }
        if (style == null) {
            style = new SpannableStringBuilder(state);
        }

        TextView tc = helper.getView(R.id.clearance_odds_content_tv);
        tc.setText(style);
        String state2 = "";
        if (!item.getParFullTimeId().equals("0") && !item.getParFullTimeId().equals("")) {
            state2 = "(" + getString(R.string.half_time) + ")";
        }

        helper.setText(R.id.clearance_odds_content_tv2, state2 + "  " + hdp + "@" + item.getBetOdds());
    }

    @Override
    public void onFailed(String error) {
        checkEnd(error);
    }


    @Override
    public void obtainListData(BettingParPromptBean betInfo) {
        getApp().setBetParList(betInfo);
        if (betInfo != null && betInfo.getBetPar() != null)
            listAdapter.addAllAndClear(betInfo.getBetPar());
        else {
            listAdapter.clearItems(true);
        }
        presenter.showBottomSelectedList();
    }

    @Override
    public void obtainBottomData(List<ClearanceBetAmountBean> data) {
        addBottomDate(data);
    }


    @Override
    public void onGetData(String data) {

    }


    @Override
    public void onUpdateMixSucceed(BettingParPromptBean bean) {

    }

}
