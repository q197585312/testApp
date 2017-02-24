package com.nanyang.app.main.home.sport.mixparlayList;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.ClearanceBetAmountBean;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.myView.SlidingButtonView;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.BindString;
import cn.finalteam.toolsfinal.StringUtils;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/21.
 */
public class MixOrderListActivity extends BaseToolbarActivity<MixOrderListPresenter> implements MixOrderListContract.View<String> {

    @Bind(R.id.rv_content)
    RecyclerView rvContent;

    @BindString(R.string.loading)
    String loading;

    private HashMap<LeagueBean, Boolean> selecteds = new HashMap<>();
    //    private BettingParPromptBean  getApp().getBetParList();
    private Map<Boolean, Integer> selectedMap = new HashMap<>();


    private TextView headOddsEdt;
    private ImageView moreIv;
    private TextView headAmountTv;
    private String betUrl;

    List<BettingInfoBean> betInfos;
    BaseRecyclerAdapter<BettingInfoBean> listAdapter;
    private LinearLayout llBottom;
    private TextView footerCountTv;
    private TextView footerContentTv;
    private Button footerCancelBtn;
    private ClearanceBetAmountBean selectedBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix_parlay_list);
        createPresenter(new MixOrderListPresenter(this));
        initBottomListData();
        initListData();
    }

    private void initListData() {
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        listAdapter = new BaseRecyclerAdapter<BettingInfoBean>(mContext, new ArrayList<BettingInfoBean>(), R.layout.mix_parlay_order_item) {
            @Override
            public void convert(MyRecyclerViewHolder helper, final int position, final BettingInfoBean item) {
                final SlidingButtonView mMenu = helper.getView(R.id.sbv);
                helper.setText(R.id.clearance_type_tv, getString(R.string.football));
                if (item.getIsFH() == 1) {
                    helper.setText(R.id.clearance_type_tv, getString(R.string.football) + "(" + getString(R.string.half_time) + ")");
                }
                helper.setText(R.id.clearance_home_tv, item.getHome());
                helper.setText(R.id.clearance_away_tv, item.getAway());
                setOddsText(helper, item);
                LinearLayout ll = helper.getView(R.id.ll_content_parent);
                TextView tvDelete = helper.getView(R.id.tv_delete);
                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //判断是否有删除菜单打开
                        if (menuIsOpen(mMenu)) {
                            closeMenu(mMenu);//关闭菜单
                        }
                    }
                });
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeBetItem(v, position, item, true);
                    }
                });
            }

            /**
             * 关闭菜单
             */
            public void closeMenu(SlidingButtonView mMenu) {
                mMenu.closeMenu();

            }

            /**
             * 判断是否有菜单打开
             */
            public Boolean menuIsOpen(SlidingButtonView mMenu) {
                if (mMenu != null) {
                    return true;
                }
                return false;
            }

        };
        rvContent.setAdapter(listAdapter);
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
                    viewById.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.menu_right_hover, 0);
                } else {
                    viewById.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                final int finalPosition = position;
                inflate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedMap.put(true, finalPosition);
                        headAmountTv.setText(clearanceBetAmountBean.getTitle());
                        selectedBean = clearanceBetAmountBean;
                    }
                });

                llBottom.addView(inflate);
            }
        }
        headAmountTv.setText(selectedBean.getTitle());
    }

    private void cancelBet(View v) {
        if (betInfos != null && betInfos.size() > 0) {
            int i = 0;
            for (BettingInfoBean item : betInfos) {
                removeBetItem(v, i, item, false);
                i++;
            }
        }
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
        } else {
            betUrl = AppConstant.HOST + "_bet/" + getApp().getBetParList().getBetUrl() + "&amt=" + amt + "&coupon=" + selectedBean.getAmount() + "&exRate=" + getApp().getBetParList().getExRate();
            presenter.bet(betUrl);

        }


    }

    private void CheckEnd(String result) {
        if (result == null) {
            Toast.makeText(mContext, getString(R.string.bet_failed), Toast.LENGTH_SHORT).show();
            return;
        }

        if (result.startsWith("Parlay") || result.contains("r=")) {
            Toast.makeText(mContext, getString(R.string.bet_succeed), Toast.LENGTH_SHORT).show();
            getApp().setBetParList(null);
            getApp().setBetDetail(null);
            finish();
        } else if (result.startsWith("MULTIBET")) {
            getApp().setBetParList(null);
            getApp().setBetDetail(null);
            finish();
        } else if (result.startsWith("PARCHG")) {
            BaseYseNoChoosePopupwindow pop = new BaseYseNoChoosePopupwindow(mContext, rvContent) {
                @Override
                protected void clickSure(View v) {
                    presenter.bet(betUrl);
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
        } else {
            llBottom.setVisibility(View.GONE);
        }
    }


    protected void removeBetItem(View v, final int position, final BettingInfoBean item, final boolean shouldRemove) {

        Flowable.create(new FlowableOnSubscribe<BettingParPromptBean>() {
            @Override
            public void subscribe(FlowableEmitter<BettingParPromptBean> e) throws Exception {
                BettingParPromptBean bettingParPromptBean = presenter.removeBetItem(item);
                e.onNext(bettingParPromptBean);
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BettingParPromptBean>() {
                               @Override
                               public void accept(BettingParPromptBean o) throws Exception {
                                   getApp().getBetDetail().remove(item.getHome() + "+" + item.getAway());
                                   getApp().setBetParList(o);
                                   onUpdateMixSucceed(o, null, null);
                               }
                           }
                );

    }

    public void setOddsText(MyRecyclerViewHolder helper, BettingInfoBean item) {
        String hdp = "";
        String b = item.getB();
        String sc = item.getSc();
        String state = "";
        if (b.equals("1"))
            state = item.getHome() + "(" + mContext.getString(R.string.win) + ")";
        else if (b.equals("1_par"))
            state = "1X2:" + item.getHome() + "(" + mContext.getString(R.string.win) + ")";
        else if (b.equals("2"))
            state = item.getAway() + "(" + mContext.getString(R.string.win) + ")";
        else if (b.equals("2_par")) {
            state = "1X2:" + item.getAway() + "(" + mContext.getString(R.string.win) + ")";
        } else if (b.equals("x") || b.equals("X"))
            state = item.getHome() + "(" + mContext.getString(R.string.draw) + ")";
        else if (b.equals("X_par")) {
            state = "1X2:" + item.getHome() + "(" + mContext.getString(R.string.draw) + ")";
        } else if (b.contains("odd")) {
            state = "OE:" + mContext.getString(R.string.odd);
        } else if (b.contains("even")) {
            state = "OE:" + mContext.getString(R.string.even);

        } else if (b.equals("csr")) {
            if (sc != null) {
                if (sc.length() == 1) {
                    sc = "0" + sc;
                }
                char s1 = sc.charAt(0);
                char s2 = sc.charAt(1);
                hdp = s1 + "-" + s2;
            }
        } else if (b.equals("dc")) {
            state = getString(R.string.double_chance);
            if (sc != null) {
                if (sc.equals("10"))
                    hdp = mContext.getString(R.string.x1);
                else if (sc.equals("12"))
                    hdp = mContext.getString(R.string.x12);
                else if (sc.equals("2"))
                    hdp = mContext.getString(R.string.x2);
            }
        } else if (b.equals("htft")) {
            state = getString(R.string.half_full_time);
            if (sc != null) {
                if (sc.equals("10"))
                    hdp = "HD";
                else if (sc.equals("12"))
                    hdp = "HA";
                else if (sc.equals("2"))
                    hdp = "DA";
                else if (sc.equals("0"))
                    hdp = "DD";
                else if (sc.equals("1"))
                    hdp = "DH";
                else if (sc.equals("11"))
                    hdp = "HH";
                else if (sc.equals("20"))
                    hdp = "AD";
                else if (sc.equals("21"))
                    hdp = "AH";
                else if (sc.equals("22"))
                    hdp = "AA";
            }
        } else if (b.equals("fglg")) {
            state = getString(R.string.first_last_goal);
            if (sc != null) {
                if (sc.equals("10"))
                    hdp = item.getHome() + "(" + getString(R.string.first_goal) + ")";
                else if (sc.equals("1"))
                    hdp = item.getHome() + "(" + getString(R.string.last_goal) + ")";
                else if (sc.equals("20"))
                    hdp = item.getAway() + "(" + getString(R.string.first_goal) + ")";
                else if (sc.equals("2"))
                    hdp = item.getAway() + "(" + getString(R.string.last_goal) + ")";
                else if (sc.equals("0"))
                    hdp = getString(R.string.no_goal);
            }
        } else if (b.equals("tg")) {
            state = getString(R.string.total_goals);
            if (sc != null) {
                if (sc.equals("1"))
                    hdp = "0-1";
                else if (sc.equals("23"))
                    hdp = "2-3";
                else if (sc.equals("46"))
                    hdp = "4-6";
                else if (sc.equals("70"))
                    hdp = "7&Over";
            }
        } else if (b.equals("away")) {
            state = "HDP:" + item.getAway();
        } else if (b.equals("home")) {
            state = "HDP:" + item.getHome();
        } else if (b.equals("under")) {
            state = "OU:" + getString(R.string.under);
        } else if (b.equals("over")) {
            state = "OU:" + getString(R.string.over);
        } else {
            state = item.getB();
        }
        if (item.getHdp() != null) {
            if ((item.isHomeGiven() && b.equals("home")) || (!item.isHomeGiven() && b.equals("away")))
                hdp = "-" + item.getHdp();
            else
                hdp = item.getHdp();
        }
        if (item.getIsFH() == 1) {
            state = state + "(" + getString(R.string.half_time) + ")";
        }
        helper.setText(R.id.clearance_odds_content_tv, state + "  " + hdp + "@" + item.getOdds());
    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onPageData(int page, String pageData, String type) {

    }

    @Override
    public void onAddMixFailed(String message) {

    }

    @Override
    public void onGetBetSucceed(BettingPromptBean allData) {

    }

    @Override
    public void onBetSucceed(String result) {
        CheckEnd(result.toString());
    }

    @Override
    public void onUpdateMixSucceed(BettingParPromptBean result, Map keyMap, MatchBean item) {
        presenter.obtainListData();
        if (result == null || result.getBetPar() == null || result.getBetPar().size() < 1) {
            getApp().setBetDetail(null);
            finish();
        }
    }

    @Override
    public void obtainListData(ArrayList<BettingInfoBean> betInfo) {
        listAdapter.addAllAndClear(betInfo);
    }

    @Override
    public void obtainBottomData(List<ClearanceBetAmountBean> data) {
        addBottomDate(data);
    }


    @Override
    public void onGetData(String data) {

    }
}
