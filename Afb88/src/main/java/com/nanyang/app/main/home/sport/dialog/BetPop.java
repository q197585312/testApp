package com.nanyang.app.main.home.sport.dialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.Utils.BetGoalWindowUtils;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportBetHelper;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.ClearanceBetAmountBean;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.BindString;
import cn.finalteam.toolsfinal.DeviceUtils;
import cn.finalteam.toolsfinal.StringUtils;

/**
 * Created by Administrator on 2015/10/27.
 */
public class BetPop extends BasePopupWindow {
    private Handler handler;
    Context context;
    BaseToolbarActivity activity;
    AfbApplication afbApplication;
    @Bind(R.id.bet_balance_tv)
    TextView betBalanceTv;
    @Bind(R.id.bet_amount_edt)
    EditText betAmountEdt;
    @Bind(R.id.bet_sure_btn)
    TextView betSureBtn;
    @Bind(R.id.rc_bet_chip)
    RecyclerView rcBetChip;
    @Bind(R.id.rc_bet_content)
    RecyclerView rcBetContent;
    @Bind(R.id.ll_mix)
    LinearLayout llMix;
    @Bind(R.id.bet_sure_cancel)
    TextView betCancelBtn;
    @Bind(R.id.bet_max_win_tv)
    TextView betMaxWinTv;
    @Bind(R.id.tv_currency)
    TextView tvCurrency;
    @Bind(R.id.tv_single_max_bet)
    TextView tvSingleMaxBet;
    @Bind(R.id.bet_max_bet_tv)
    TextView betMaxBetTv;
    @Bind(R.id.tv_max_win)
    TextView tvMaxWin;
    @Bind(R.id.tv_1x2)
    TextView tv1x2;
    @Bind(R.id.tv_1x2_odds)
    TextView tv1x2Odds;
    @Bind(R.id.tv_single_bet)
    TextView tvSingleBet;
    @Bind(R.id.tv_mix_bet)
    TextView tvMixBet;
    @Bind(R.id.tv_delete)
    TextView tvDelete;
    @Bind(R.id.ll_back)
    ImageView llBack;
    @Bind(R.id.ll_1x2)
    LinearLayout ll1x2;
    @Bind(R.id.ll_bet_failed_hint)
    LinearLayout llBetFailedHint;
    @Bind(R.id.tv_bet_failed_hint)
    TextView tvBetFailedHint;
    @Bind(R.id.img_failed)
    ImageView imgFailed;
    @Bind(R.id.bet_pop_parent_web_ll)
    WebView webView;
    @BindString(R.string.loading)
    String loading;
    @Bind(R.id.bet_pop_parent_top_fl)
    FrameLayout betPopParentTopFl;

    private SportBetHelper presenter;
    private IRTMatchInfo rTMatchInfo;
    private int coupon;
    private boolean isRefresh;


    public BetPop(Context context, View v) {
        this(context, v, DeviceUtils.dip2px(context, 350), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public BetPop(Context mContext, View v, int width, int height) {
        super(mContext, v, width, height);
        this.context = mContext;
        this.v = v;
        AfbUtils.switchLanguage(AfbUtils.getLanguage(context), context);
        activity = (BaseToolbarActivity) context;
        handler = new Handler();
        afbApplication = activity.getApp();
        betAmountEdt.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    goBetting();
                    return true;
                }
                return false;
            }

        });
        betAmountEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().startsWith("0") && s.toString().length() > 1) {
                    s.delete(0, 1);
                }
                String amount = s.toString().trim().replaceAll(",", "");
                if (TextUtils.isEmpty(amount)) {
                    amount = "0";
                }

                double max;
                if (list.size() > 1) {
                    AfbClickResponseBean betAfbList = afbApplication.getBetAfbList();
                    max = Double.parseDouble(betAfbList.getMaxLimit());
                } else {
                    max = list.get(0).getMaxLimit();
                }
                amount = amount.replaceAll(",", "");
                if (Double.parseDouble(amount) > max) {
                    betAmountEdt.removeTextChangedListener(this);
                    betAmountEdt.setText(AfbUtils.addComma((int) max + "", betAmountEdt));
                    betAmountEdt.addTextChangedListener(this);
                    amount = max + "";
                } else if (!AfbUtils.touzi_ed_values22.equals(amount) && !TextUtils.isEmpty(s.toString().trim().replaceAll(",", ""))) {
                    betAmountEdt.setText(AfbUtils.addComma(amount, betAmountEdt));
                }

                double writeMoney = Double.parseDouble(amount);
                double maxWin = countMaxPayout(writeMoney);
                tvMaxWin.setText(AfbUtils.scientificCountingToString(AfbUtils.decimalValue((float) maxWin, "0.00")));
                betAmountEdt.setSelection(betAmountEdt.getText().toString().trim().length());
            }
        });
        betAmountEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    betAmountEdt.setBackgroundResource(R.drawable.shape_bet_bg2);
                } else {
                    betAmountEdt.setBackgroundColor(Color.WHITE);
                }
            }
        });
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopUpdateOdds();
                closePopupWindow();
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopUpdateOdds();
                if (list.size() > 1) {
                    goCancel();
                } else if (list.size() == 1) {
                    closePopupWindow();
                    deletedOne(list.get(0));
                }
            }
        });
        tvSingleBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goChooseBet(true);
                webView.reload();
            }
        });
        tvMixBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopUpdateOdds();
                closePopupWindow();
            }
        });
        betSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBetting();

            }
        });
        betCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCancel();
            }
        });
        betSureBtn.setEnabled(true);
        betCancelBtn.setEnabled(true);
        imgFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llBetFailedHint.setVisibility(View.GONE);
            }
        });
    }

    private void goChooseBet(boolean isSingle) {

        String refreshOddsUrl = "";
        if (isSingle) {
            tvSingleBet.setTextColor(context.getResources().getColor(R.color.orange_desktop));
            tvMixBet.setTextColor(Color.WHITE);
            refreshOddsUrl = afbApplication.getRefreshSingleOddsUrl();

        } else {
            tvSingleBet.setTextColor(Color.WHITE);
            tvMixBet.setTextColor(context.getResources().getColor(R.color.orange_desktop));
            refreshOddsUrl = afbApplication.getRefreshMixOddsUrl();
        }
        presenter.getRefreshOdds(refreshOddsUrl);
    }


    private double countMaxPayout(double money) {
        double maxWin;
        double odds;
        if (list.size() > 1) {
            AfbClickResponseBean betAfbList = afbApplication.getBetAfbList();
            odds = Double.parseDouble(betAfbList.getPayoutOdds());
            maxWin = Math.abs(odds) * money;
        } else {
            odds = Double.parseDouble(list.get(0).getOdds());
            String id = list.get(0).getId();
            String[] split = id.split("\\|");
            String _b = split[1];
            if (!_b.equals("home") && !_b.equals("away") && !_b.equals("over") && !_b.equals("under") && !_b.equals("mmhome") && !_b.equals("mmaway") && !_b.equals("mmover") && !_b.equals("mmunder") && !_b.equals("odd") && !_b.equals("even")) {
                odds = odds - 1;
            }
            String accType = list.get(0).getAccType();
            if (accType.equals("EU") && (_b.equals("home") || _b.equals("away") || _b.equals("over") || _b.equals("under") || _b.equals("odd") || _b.equals("even"))) {
                odds = odds - 1;
            }
            //单注：下注金额*（赔率的绝对值+1）
            maxWin = money * (Math.abs(odds) + 1);
        }
        return maxWin;
    }

    private void initBetChip() {
        HashMap<String, Boolean> chipStatusMap = AfbUtils.getChipStatusMap();
        List<PopChipBean> allList = Arrays.asList(new PopChipBean(R.mipmap.chips1, 1, "1"), new PopChipBean(R.mipmap.chips10, 10, "10"),
                new PopChipBean(R.mipmap.chips50, 50, "50"), new PopChipBean(R.mipmap.chips100, 100, "100"), new PopChipBean(R.mipmap.chips500, 500, "500"),
                new PopChipBean(R.mipmap.chips1000, 1000, "1000"), new PopChipBean(R.mipmap.chips5000, 5000, "5000"), new PopChipBean(R.mipmap.chips10000, 10000, "10000"),
                new PopChipBean(R.mipmap.chips30000, 30000, "30000"), new PopChipBean(R.mipmap.chips50000, 50000, "50000"), new PopChipBean(R.mipmap.chips100000, 100000, "100000"),
                new PopChipBean(R.mipmap.chips_max, 0, "max"));
        List<PopChipBean> beanList = new ArrayList<>();
        for (PopChipBean popChipBean : allList) {
            if (chipStatusMap.get(popChipBean.getKey())) {
                beanList.add(popChipBean);
            }
        }
        if (beanList.size() < 1) {
            beanList = allList;
        }

        BaseRecyclerAdapter<PopChipBean> adapter = new BaseRecyclerAdapter<PopChipBean>(context, beanList, R.layout.item_bet_chip) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, PopChipBean item) {
                ImageView imgContent = holder.getView(R.id.img_content);
                imgContent.setImageResource(item.getImgRes());
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcBetChip.setLayoutManager(linearLayoutManager);
        rcBetChip.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<PopChipBean>() {
            @Override
            public void onItemClick(View view, PopChipBean item, int position) {
                String s = betAmountEdt.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    s = "0";
                }
                s = s.replaceAll(",", "");
                int betAmount = Integer.parseInt(s);
                int betChip = item.getBetChip();
                if (betChip == 0) {
                    AfbClickResponseBean betAfbList = afbApplication.getBetAfbList();
                    int maxLimit;
                    if (list.size() > 1) {
                        maxLimit = (int) Double.parseDouble(betAfbList.getMaxLimit());
                    } else {
                        maxLimit = list.get(0).getMaxLimit();
                    }

                    betChip = maxLimit;
                }

                betAmount += betChip;
                betAmountEdt.setText(betAmount + "");
            }
        });
    }

    private PopChipBean findChip(Integer key, List<PopChipBean> allList) {
        for (PopChipBean popChipBean : allList) {
            if (popChipBean.getBetChip() == key) {
                return popChipBean;
            }
        }
        return null;

    }

    private void goBetting() {
        //http://www.afb1188.com/Bet/hBetSub.ashx?betType=1&oId=471838&odds=3.6&BTMD=S&amt=11&_=1543457323225
        String s1 = betAmountEdt.getText().toString().trim().replace(",", "");
        if (!StringUtils.isEmpty(s1)) {
            String s = s1.replaceAll(",", "");
            double min = Double.parseDouble(betMaxWinTv.getText().toString().trim().replace(",", ""));
            double max = Double.parseDouble(betMaxBetTv.getText().toString().trim().replace(",", ""));
            if (min >= 0 && max > 0) {
                int count = Integer.valueOf(s);
                if (count > max || count < min || count <= 0) {
                    ToastUtils.showShort(context.getString(R.string.invalid_amount_bet));
                    betAmountEdt.setText("");
                    return;
                }
                String betUrl;
                if (list.size() > 1) {
                    betUrl = AppConstant.getInstance().HOST + AppConstant.getInstance()._BET + afbApplication.getBetParList().getParUrl() + "&amt=" + s + "&coupon=" + coupon + "&exRate=" + afbApplication.getBetParList().getExRate();
                    StringBuilder BETIDBuilder = new StringBuilder();
                    BETIDBuilder.append("&BETID=");
                    for (AfbClickBetBean afbClickBetBean : afbApplication.getBetParList().getList()) {
                        BETIDBuilder.append(afbClickBetBean.getId());
                        BETIDBuilder.append(",");
                    }
                    String betId = BETIDBuilder.toString();
                    betId = betId.substring(0, betId.length() - 1);
                    betUrl += betId;
                } else {
                    betUrl = AppConstant.getInstance().HOST + AppConstant.getInstance()._BET + list.get(0).getBeturl() + "&amt=" + s;
                }
                stopUpdateOdds();
                presenter.bet(betUrl);
                presenter.setResultCallBack(new IBetHelper.ResultCallBack() {
                    @Override
                    public void callBack(String back) {
//                        Chile (Over) 2 @ 0.745 ||r=467428298|5|100|34941
                        if (back.contains("||") && back.contains("|")) {
                            String[] split = back.split("\\|");
                            String tidss = split[5];
                            SportActivity sportActivity = (SportActivity) context;
                            String oddsType = sportActivity.tvOddsType.getText().toString().trim();
                            BetGoalWindowUtils.showBetWindow(oddsType, tidss, sportActivity, false);
                        } else {
                            tvBetFailedHint.setText(back);
                            llBetFailedHint.setVisibility(View.VISIBLE);
                            updateOdds(0);
                        }
                    }
                });
            }
        } else {
            ToastUtils.showShort(R.string.Input_the_amount_of_bets_please);
        }
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.popupwindow_match_betting;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
    }


    /**
     * {"BetType": "away",
     * "GTitle": "亚州盤",
     * "ModuleTitle": "欧洲冠军联赛",
     * "FullTimeId": "0",
     * "IsHomeGive": true,
     * "Home": "AS摩纳哥",
     * "Away": "曼城",
     * "IsRun": false,
     * "IsOddsChange": false,
     * "RunHomeScore": "0",
     * "RunAwayScore": "0",
     * "GameType3": "N",
     * "BetHdp": "0",
     * "BetOdds": "1.08",
     * "BetUrl":
     * "PanelBet.aspx?betGrp=1&betType=away&oId=12219287&isHomeGive=True&isBetHome=False&isFH=False&hdp=0&accType=HK&odds=10.8&reducePercent=1",
     * "MinLimit": "10",
     * "MaxLimit": "30000",
     * "HidOdds": "1.08",
     * "AmtS": "0",
     * "ExRate": "1",
     * "BetterOdds": 1,
     * "MoreBetUrl": "../_View/MoreBet.aspx?oId=12219287&home=AS%e6%91%a9%e7%ba%b3%e5%93%a5&away=%e6%9b%bc%e5%9f%8e&moduleTitle=%e6%ac%a7%e6%b4%b2%e5%86%a0%e5%86%9b%e8%81%94%e8%b5%9b&date=03%3a45AM&lang=eng",
     * "Test": "testing" }
     */
    List<AfbClickBetBean> list;

    public void setBetData(List<AfbClickBetBean> list, SportBetHelper mPresenter) {
        this.list = list;
        this.presenter = mPresenter;
        initRcBetContent();
        if (!isRefresh) {
            setEditNum();
            initBetChip();
        }
        tvCurrency.setText(afbApplication.getUser().getCurCode2());
        betBalanceTv.setText(AfbUtils.addComma(afbApplication.getUser().getCredit2(), betBalanceTv));
        if (list.size() > 1) {
            tvDelete.setVisibility(View.VISIBLE);
            AfbClickResponseBean betAfbList = afbApplication.getBetAfbList();
            tvSingleMaxBet.setText(AfbUtils.scientificCountingToString(list.get(0).getMatchLimit() + ""));
            betMaxWinTv.setText(betAfbList.getMinLimit());
            betMaxBetTv.setText(AfbUtils.addComma(betAfbList.getMaxLimit(), betMaxBetTv));
        } else {
            tvDelete.setVisibility(View.GONE);
            AfbClickBetBean afbClickBetBean = list.get(0);
            tvSingleMaxBet.setText(AfbUtils.scientificCountingToString(afbClickBetBean.getMatchLimit() + ""));
            betMaxWinTv.setText(afbClickBetBean.getMinLimit() + "");
            betMaxBetTv.setText(AfbUtils.addComma(afbClickBetBean.getMaxLimit() + "", betMaxBetTv));
        }
        ((BaseActivity) context).hideLoadingDialog();
//        showInput();
        String writeMoney = betAmountEdt.getText().toString().trim().replace(",", "");
        if (!TextUtils.isEmpty(writeMoney)) {
            tvMaxWin.setText(AfbUtils.addComma(AfbUtils.decimalValue((float) countMaxPayout(Double.parseDouble(writeMoney)), "0.00"), tvMaxWin));
        }
        stopUpdateOdds();
        updateOdds(4000);
    }

    BaseRecyclerAdapter<AfbClickBetBean> contentAdapter;

    private void initRcBetContent() {
        ViewGroup.LayoutParams layoutParams = rcBetContent.getLayoutParams();
        if (list.size() > 1) {
            tvSingleBet.setTextColor(Color.WHITE);
            tvMixBet.setTextColor(context.getResources().getColor(R.color.orange_desktop));
            if (!isRefresh)
                initMix();
            llMix.setVisibility(View.VISIBLE);
            layoutParams.height = AfbUtils.dp2px(context, 62 * 2 + 5);
        } else {
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            llMix.setVisibility(View.GONE);
            tvSingleBet.setTextColor(context.getResources().getColor(R.color.orange_desktop));
            tvMixBet.setTextColor(Color.WHITE);
        }
        if (contentAdapter == null) {
            objectAnimatorMap = new HashMap<>();
            contentAdapter = new BaseRecyclerAdapter<AfbClickBetBean>(context, list, R.layout.item_bet) {
                @Override
                public void convert(MyRecyclerViewHolder holder, final int position, AfbClickBetBean item) {
                    TextView tvBetModuleTitle = holder.getView(R.id.bet_module_title_tv);
                    ImageView imgDelete = holder.getView(R.id.img_delete);
                    TextView tvBetHome = holder.getView(R.id.bet_home_tv);
                    TextView tvScore = holder.getView(R.id.tv_score);
                    TextView tvBetAway = holder.getView(R.id.bet_away_tv);
                    TextView tvBetName = holder.getView(R.id.bet_name_tv);
                    TextView tvHdp = holder.getView(R.id.tv_hdp);
                    TextView tvBetHdp = holder.getView(R.id.bet_hdp_tv);
                    TextView tvBetOdds = holder.getView(R.id.bet_odds_tv);
                    TextView tvBetOddsAnimation = holder.getView(R.id.bet_odds_tv_animation);
                    View vLine = holder.getView(R.id.v_line);
                    if (position == list.size() - 1) {
                        vLine.setVisibility(View.GONE);
                    } else {
                        vLine.setVisibility(View.VISIBLE);
                    }
                    if (isRunning) {
                        tvScore.setText(item.getScore() + " ");
                    } else {
                        tvScore.setText("");
                    }
                    tvBetModuleTitle.setText(item.getLeague());
                    tvBetHome.setText(item.getHome());
                    tvBetAway.setText(item.getAway());
                    String hdp = item.getHdp();
                    if (hdp != null && hdp.contains("-")) {
                        tvHdp.setTextColor(Color.RED);
                    } else {
                        tvHdp.setTextColor(Color.BLACK);
                    }
                    tvHdp.setText(hdp);
                    tvBetHdp.setText(item.getBTT());
                    String odds = item.getOdds();
                    tvBetOdds.setText(odds);
                    tvBetOddsAnimation.setText(odds);
                    if (odds.startsWith("-")) {
                        tvBetOdds.setTextColor(Color.RED);
                    } else {
                        tvBetOdds.setTextColor(Color.BLACK);
                    }
                    String bTeam = item.getBTeam();
                    if (bTeam.equals("Home")) {
                        tvBetName.setText(item.getHome());
                    } else if (bTeam.equals("Away")) {
                        tvBetName.setText(item.getAway());
                    } else {
                        tvBetName.setText(bTeam);
                    }
                    boolean isHome = item.getIsGive() == 1;
                    if (isHome) {
                        tvBetHome.setTextColor(Color.RED);
                        tvBetAway.setTextColor(Color.BLACK);
                        if (tvBetName.getText().toString().equals(item.getHome())) {
                            tvBetName.setTextColor(Color.RED);
                        } else {
                            tvBetName.setTextColor(Color.BLACK);
                        }
                    } else {
                        if (tvBetName.getText().toString().equals(item.getHome())) {
                            tvBetName.setTextColor(Color.BLACK);
                        } else {
                            tvBetName.setTextColor(Color.RED);
                        }
                        tvBetAway.setTextColor(Color.RED);
                        tvBetHome.setTextColor(Color.BLACK);
                    }
                    Animation animation = tvBetOddsAnimation.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                    }
                    ObjectAnimator objectAnimator = startAlphaAnimation(tvBetOddsAnimation);
                    objectAnimatorMap.put(position, objectAnimator);
                    imgDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            stopUpdateOdds();

                            AfbClickBetBean afbClickBetBean = list.get(position);
                            BetPop.this.deletedOne(afbClickBetBean);
                            if (list.size() > 1) {
                                list.remove(position);
//                                presenter.getRefreshOdds(afbApplication.getRefreshCurrentOddsUrl());
                                isNeedInitWeb = true;
                                ObjectAnimator objectAnimator1 = objectAnimatorMap.get(position);
                                if (objectAnimator1 != null) {
                                    objectAnimator1.cancel();
                                    objectAnimatorMap.remove(objectAnimator1);
                                }
                                updateOdds(0);
                            } else {
                                closePopupWindow();
                            }
                        }

                    });
                }
            };
            rcBetContent.setLayoutManager(new LinearLayoutManager(context));
            rcBetContent.setAdapter(contentAdapter);
        } else {
            contentAdapter.setData(list);
        }
    }

    private void deletedOne(AfbClickBetBean afbClickBetBean) {
        String socOddsId = afbClickBetBean.getSocOddsId();
        String id = afbClickBetBean.getId();
        List<OddsClickBean> mixBetList = afbApplication.getMixBetList();
        Iterator<OddsClickBean> iterator = mixBetList.iterator();
        while (iterator.hasNext()) {
            OddsClickBean oddsClickBean = iterator.next();
            if (oddsClickBean.getBETID().equals(id)
                    || oddsClickBean.getBETID_PAR().equals(id)
                    || socOddsId.equals(oddsClickBean.getOid())
                    || socOddsId.equals(oddsClickBean.getOid_fh())) {
                iterator.remove();
                if ((presenter.getBaseView().getIBaseContext().getBaseActivity()) != null) {
                    ((SportActivity) presenter.getBaseView().getIBaseContext().getBaseActivity()).updateMixOrder();
                }
            }
        }
    }

    public void setEditNum() {
        String quickAmount = afbApplication.getQuickAmount();
        if (quickAmount.equals("0"))
            betAmountEdt.setText("");
        else {
            betAmountEdt.setText(quickAmount);
        }
    }

    private List<ClearanceBetAmountBean> clearanceBetAmountBeenList;

    private void initMix() {
        clearanceBetAmountBeenList = new ArrayList<>();
        int size = list.size();
        if (size == 2) {
            clearanceBetAmountBeenList = Arrays.asList(new ClearanceBetAmountBean(1, "2  X  1"), new ClearanceBetAmountBean(2, "2  X  2"), new ClearanceBetAmountBean(3, "2  X  3"));
        } else if (size == 3) {
            clearanceBetAmountBeenList = Arrays.asList(new ClearanceBetAmountBean(1, "3  X  1"), new ClearanceBetAmountBean(3, "3  X  3"), new ClearanceBetAmountBean(4, "3  X  4"));
        } else if (size == 4) {
            clearanceBetAmountBeenList = Arrays.asList(new ClearanceBetAmountBean(1, "4  X  1"), new ClearanceBetAmountBean(4, "4  X  4"), new ClearanceBetAmountBean(5, "4  X  5"), new ClearanceBetAmountBean(6, "4  X  6"));
        } else if (size == 5) {
            clearanceBetAmountBeenList = Arrays.asList(new ClearanceBetAmountBean(1, "5  X  1"), new ClearanceBetAmountBean(5, "5  X  5"), new ClearanceBetAmountBean(6, "5  X  6"), new ClearanceBetAmountBean(10, "5  X  10")
                    , new ClearanceBetAmountBean(16, "5  X  16"), new ClearanceBetAmountBean(20, "5  X  20"), new ClearanceBetAmountBean(26, "5  X  26"));
        } else if (size == 6) {
            clearanceBetAmountBeenList = Arrays.asList(new ClearanceBetAmountBean(1, "6  X  1"), new ClearanceBetAmountBean(6, "6  X  6"), new ClearanceBetAmountBean(7, "6  X  7"), new ClearanceBetAmountBean(15, "6  X  15"),
                    new ClearanceBetAmountBean(20, "6  X  20"), new ClearanceBetAmountBean(35, "6  X  35"), new ClearanceBetAmountBean(42, "6  X  42"), new ClearanceBetAmountBean(50, "6  X  50"), new ClearanceBetAmountBean(57, "6  X  57"));
        } else if (size == 7) {
            clearanceBetAmountBeenList = Arrays.asList(new ClearanceBetAmountBean(1, "7  X  1"), new ClearanceBetAmountBean(7, "7  X  7"), new ClearanceBetAmountBean(8, "7  X  8"), new ClearanceBetAmountBean(21, "7  X  21"), new ClearanceBetAmountBean(28, "7  X  28"),
                    new ClearanceBetAmountBean(29, "7  X  29"), new ClearanceBetAmountBean(35, "7  X  35"), new ClearanceBetAmountBean(56, "7  X  56"), new ClearanceBetAmountBean(70, "7  X  70"), new ClearanceBetAmountBean(91, "7  X  91"),
                    new ClearanceBetAmountBean(98, "7  X  98"), new ClearanceBetAmountBean(99, "7  X  99"), new ClearanceBetAmountBean(112, "7  X  112"), new ClearanceBetAmountBean(119, "7  X  119"), new ClearanceBetAmountBean(120, "7  X  120"));
        } else {
            clearanceBetAmountBeenList = Arrays.asList(new ClearanceBetAmountBean(1, list.size() + "  X  1"));
        }
        tv1x2.setText(clearanceBetAmountBeenList.get(0).getTitle());
        coupon = clearanceBetAmountBeenList.get(0).getAmount();
        tv1x2Odds.setText(AfbUtils.decimalValue(Float.parseFloat(afbApplication.getBetAfbList().getPayoutOdds()), "0.00"));
        ll1x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mixdialog = new MixDialog(activity, R.style.MyDialog, ll1x2);
                mixdialog.show();
            }
        });
        ValueAnimator anim = ObjectAnimator.ofInt(llMix, "backgroundColor", Color.WHITE, ContextCompat.getColor(context, R.color.pink_light_bg));
        //动画持续时间为3秒
        anim.setDuration(1000);
        AfbUtils.startAnimator(anim);
    }

    private void goCancel() {
        closePopupWindow();
        if (((SportActivity) presenter.getBaseView().getIBaseContext().getBaseActivity()).currentFragment != null) {
            ((SportActivity) presenter.getBaseView().getIBaseContext().getBaseActivity()).currentFragment.onUpdateMixSucceed(null);
        }
    }

    private boolean isNeedInitWeb = true;

    public void setrTMatchInfo(IRTMatchInfo rTMatchInfo) {
        if (list.size() > 1) {
            betPopParentTopFl.setVisibility(View.GONE);
            return;
        }
        if (rTMatchInfo == null || !isNeedInitWeb) {
            return;
        }
        String rtsMatchId = rTMatchInfo.getRTSMatchId();
        if (rtsMatchId != null && !rtsMatchId.isEmpty() && !rtsMatchId.equals("0")) {
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
            this.rTMatchInfo = rTMatchInfo;
            String lag = AfbUtils.getLanguage(context);
            String l = "eng";
            if (lag.equals("zh")) {
                l = "eng";
            } else {
                l = "EN-US";
            }
            betPopParentTopFl.setVisibility(View.VISIBLE);
            String gameUrl = AppConstant.getInstance().URL_RUNNING_MATCH_WEB + "?Id=" + rTMatchInfo.getRTSMatchId() + "&Home=" + com.nanyang.app.Utils.StringUtils.URLEncode(rTMatchInfo.getHome()) + "&Away=" + com.nanyang.app.Utils.StringUtils.URLEncode(rTMatchInfo.getAway()) + "&L=" + l;
            AfbUtils.synCookies(context, webView, gameUrl);
            LogUtil.d("gameUrl", gameUrl);
        } else {
            betPopParentTopFl.setVisibility(View.GONE);
        }
        isNeedInitWeb = false;
    }

    MixDialog mixdialog;

    public class MixDialog extends Dialog {

        public MixDialog(@NonNull final Context context, int theme, View v) {
            super(context, theme);
            LinearLayout llMix = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.pop_grade_switch_type, null);
            RecyclerView rcContent = llMix.findViewById(R.id.rc_content);
            BaseRecyclerAdapter<ClearanceBetAmountBean> adapter = new BaseRecyclerAdapter<ClearanceBetAmountBean>(context, clearanceBetAmountBeenList, R.layout.item_text) {
                @Override
                public void convert(MyRecyclerViewHolder holder, int position, ClearanceBetAmountBean item) {
                    TextView tvContent = holder.getView(R.id.tv_content);
                    tvContent.setText(item.getTitle());
                    if (item.getTitle().equals(tv1x2.getText().toString())) {
                        tvContent.setBackgroundColor(ContextCompat.getColor(context, R.color.gary1));
                        tvContent.setTextColor(ContextCompat.getColor(context, R.color.blue));
                    } else {
                        tvContent.setBackgroundColor(Color.WHITE);
                        tvContent.setTextColor(Color.BLACK);
                    }
                }
            };
            rcContent.setLayoutManager(new LinearLayoutManager(context));
            rcContent.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<ClearanceBetAmountBean>() {
                @Override
                public void onItemClick(View view, ClearanceBetAmountBean item, int position) {
                    tv1x2.setText(item.getTitle());
                    coupon = item.getAmount();
                    mixdialog.dismiss();
                }
            });
            setContentView(llMix);
            Window window = getWindow();
            //设置窗口的属性，以便设设置
            WindowManager.LayoutParams wlp = window.getAttributes();
            int[] location = new int[2];
            v.getLocationOnScreen(location); //获取在当前窗体内的绝对坐标
            wlp.x = location[0];//对 dialog 设置 x 轴坐标
            wlp.y = location[1] + v.getHeight(); //对dialog设置y轴坐标
            wlp.gravity = Gravity.TOP | Gravity.LEFT;
            wlp.width = v.getWidth();
            window.setAttributes(wlp);
        }
    }


    @Override
    protected void onClose() {
        super.onClose();
//        activity.hintPopInput(betAmountEdt);
        llBetFailedHint.setVisibility(View.GONE);
        stopUpdateOdds();
        betAmountEdt.setText("");
        isNeedInitWeb = true;
        for (ObjectAnimator objectAnimator : objectAnimatorMap.values()) {
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
        }
        objectAnimatorMap.clear();
        webView.stopLoading();
        webView.clearFormData();
        webView.clearDisappearingChildren();
    }


    public void updateOdds(long delayedTime) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (StringUtils.isEmpty(afbApplication.getRefreshCurrentOddsUrl())) {
                    stopUpdateOdds();
                    goCancel();
                    return;
                }
                presenter.getRefreshOdds(afbApplication.getRefreshCurrentOddsUrl());
                isRefresh = true;
                handler.postDelayed(this, 3000);
            }
        }, delayedTime);
    }

    public void stopUpdateOdds() {
        handler.removeCallbacksAndMessages(null);
        isRefresh = false;
    }

    private Map<Integer, ObjectAnimator> objectAnimatorMap;

    public ObjectAnimator startAlphaAnimation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1, 0, 1);
        animator.setRepeatCount(Animation.INFINITE);
        animator.setDuration(2000);
        animator.start();
        return animator;
    }

    private boolean isRunning;

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
