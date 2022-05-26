package com.nanyang.app.main.home.sport.dialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.text.Editable;
import android.text.Spanned;
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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.main.AfbDrawerViewHolder;
import com.nanyang.app.main.BetCenter.HtmlTagHandler;
import com.nanyang.app.main.home.sport.football.SoccerRunningGoalManager;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportBetHelper;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.ClearanceBetAmountBean;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.DeviceUtils;
import cn.finalteam.toolsfinal.StringUtils;

/**
 * Created by Administrator on 2015/10/27.
 */
public class BetPop {
    public View v;
    private Handler handler;
    Context context;
    SportActivity activity;
    AfbApplication afbApplication;
    private int listSize;

    @BindView(R.id.mix_count)
    View mix_count;
    @BindView(R.id.ll_bet_title)
    View ll_bet_title;
    @BindView(R.id.bet_balance_tv)
    TextView betBalanceTv;
    @BindView(R.id.bet_amount_edt)
    EditText betAmountEdt;
    @BindView(R.id.bet_sure_btn)
    TextView betSureBtn;
    @BindView(R.id.rc_bet_chip)
    RecyclerView rcBetChip;
    @BindView(R.id.fl_rc_content)
    FrameLayout fl_rc_content;
    @BindView(R.id.rc_bet_content)
    RecyclerView rcBetContent;
    @BindView(R.id.ll_mix)
    LinearLayout llMix;
    @BindView(R.id.bet_sure_cancel)
    TextView betCancelBtn;
    @BindView(R.id.bet_max_win_tv)
    TextView betMaxWinTv;
    @BindView(R.id.tv_currency)
    TextView tvCurrency;
    @BindView(R.id.tv_single_max_bet)
    TextView tvSingleMaxBet;
    @BindView(R.id.bet_max_bet_tv)
    TextView betMaxBetTv;
    @BindView(R.id.tv_max_win)
    TextView tvMaxWin;
    @BindView(R.id.tv_1x2)
    TextView tv1x2;
    @BindView(R.id.tv_1x2_odds)
    TextView tv1x2Odds;
    @BindView(R.id.tv_single_bet)
    TextView tvSingleBet;

    @BindView(R.id.tv_mix_bet)
    TextView tvMixBet;
    @BindView(R.id.tv_delete)
    ImageView tvDelete;
    @BindView(R.id.ll_back_title_line)
    ImageView llBack;
    @BindView(R.id.ll_1x2)
    LinearLayout ll1x2;
    @BindView(R.id.ll_bet_failed_hint)
    LinearLayout llBetFailedHint;
    @BindView(R.id.tv_bet_failed_hint)
    TextView tvBetFailedHint;
    @BindView(R.id.img_failed)
    ImageView imgFailed;
    @BindView(R.id.bet_pop_parent_web_ll)
    WebView webView;
    @BindString(R.string.loading)
    String loading;
    @BindView(R.id.bet_pop_parent_top_fl)
    FrameLayout betPopParentTopFl;
    @BindView(R.id.my_bets)
    TextView my_bets;
    @BindView(R.id.max_bet)
    TextView max_bet;
    @BindView(R.id.min_bet)
    TextView min_bet;
    @BindView(R.id.max_single_bet)
    TextView max_single_bet;
    @BindView(R.id.max_win)
    TextView max_win;
    @BindView(R.id.bet_amount)
    TextView bet_amount;

    private SportBetHelper presenter;
    private int coupon;

    private boolean isRefreshEd = false;
    private volatile String oldRtsId = "";
    private volatile String refreshCurrentOddsUrl = "";


    public BetPop(Context context, View v) {
        this.context = context;
        this.v = v;
        ButterKnife.bind(this, v);
        initContent();

    }

    public void initContent() {
        tvMixBet.setText(R.string.Parlay1);
        tvSingleBet.setText(R.string.single_bet);
        max_win.setText(R.string.max_win);
        max_bet.setText(R.string.max_bet_money);
        min_bet.setText(R.string.min_bet_money);
        max_single_bet.setText(R.string.max_single_money);
        bet_amount.setText(R.string.bet_limit);
        my_bets.setText(R.string.TabMyBets);
        betSureBtn.setText(R.string.bet1);
        betCancelBtn.setText(R.string.cancel);
        AfbUtils.switchLanguage(AfbUtils.getLanguage(context), context);
        activity = (SportActivity) context;
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
                AfbClickResponseBean betAfbList = afbApplication.getBetAfbList();
                double max;
                if (betAfbList != null && list.size() > 1 && !StringUtils.isEmpty(betAfbList.getMaxLimit())) {

                    max = Double.parseDouble(betAfbList.getMaxLimit());
                } else {
                    max = list.get(0).getMaxLimit();
                }
                afterChange(s, max, betAmountEdt, this, true);

            }


        });
        betAmountEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    CursorEditView cursorEditView = new CursorEditView("", betAmountEdt);
                    cursorMap.put(true, cursorEditView);
                    betAmountEdt.setBackgroundResource(R.drawable.shape_bet_bg2);
                } else {
                    betAmountEdt.setBackgroundColor(Color.WHITE);
                }
            }
        });
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMixAndClose();
            }
        });

        my_bets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMyBets(view);
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopUpdateOdds();
                goCancel();
            }
        });
        tvSingleBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goChooseBetSingle(true);
                webView.reload();
            }
        });
        tvMixBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                closePopupWindow();

                if (afbApplication.getMixBetList().size() > 1) {
                    goChooseBetSingle(false);
                }

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
                closePopupWindow();
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
        initBetChip();
    }

    private void goMixAndClose() {
        if (afbApplication.getMixBetList() != null && afbApplication.getMixBetList().size() > 0)
            afbApplication.isSingleBet = false;
//        stopUpdateOdds();
        closePopupWindow();
    }

    public void closePopupWindow() {

        if (v != null && v.getVisibility() == View.VISIBLE) {
            v.setVisibility(View.GONE);
            onClose();
        }

    }

    private void showMyBets(View view) {
        if (activity != null && activity instanceof SportActivity && ((SportActivity) activity).afbDrawerViewHolder != null) {
            AfbDrawerViewHolder afbDrawerViewHolder = ((SportActivity) activity).afbDrawerViewHolder;
            afbDrawerViewHolder.goRecord();

        }
    }

    private String afterChange(Editable s, double max, EditText betAmountEdt, TextWatcher textWatcher, boolean hasWin) {
        if (s.toString().startsWith("0") && s.toString().length() > 1) {
            s.delete(0, 1);
        }
        String amount = s.toString().trim().replaceAll(",", "");
        if (TextUtils.isEmpty(amount)) {
            amount = "0";
        }
        if (afbApplication.getBetAfbList() == null) {
            return "0";

        }
        amount = amount.replaceAll(",", "");
        if (Double.parseDouble(amount) > max) {
            betAmountEdt.removeTextChangedListener(textWatcher);
            betAmountEdt.setText(AfbUtils.addComma((int) max + "", betAmountEdt));
            betAmountEdt.addTextChangedListener(textWatcher);
            amount = max + "";
        } else if (!AfbUtils.touzi_ed_values22.equals(amount) && !TextUtils.isEmpty(s.toString().trim().replaceAll(",", ""))) {
            betAmountEdt.setText(AfbUtils.addComma(amount, betAmountEdt));
        }
        if (hasWin) {
            double writeMoney = Double.parseDouble(amount);
            double maxWin = countMaxPayout(writeMoney);
            tvMaxWin.setText(AfbUtils.scientificCountingToString(AfbUtils.decimalValue((float) maxWin, "0.00")));
        }
        betAmountEdt.setSelection(betAmountEdt.getText().toString().trim().length());
        return amount;
    }

    private void goChooseBetSingle(boolean isSingle) {

        String refreshOddsUrl = "";
        hashMap = new HashMap<>();
        if (isSingle) {
            startAlphaColor(tvSingleBet, tvMixBet);
            tvSingleBet.setTextColor(context.getResources().getColor(R.color.yellow_gold));
            tvMixBet.setTextColor(Color.WHITE);
            refreshOddsUrl = afbApplication.getRefreshSingleOddsUrl();
            mix_count.setVisibility(View.GONE);
        } else {
            startAlphaColor(tvMixBet, tvSingleBet);
            tvSingleBet.setTextColor(Color.WHITE);
            tvMixBet.setTextColor(context.getResources().getColor(R.color.yellow_gold));
            if (afbApplication.getMixBetList() != null && afbApplication.getMixBetList().size() > 0) {
                afbApplication.setSingleBet(afbApplication.getMixBetList().get(afbApplication.getMixBetList().size() - 1));
                presenter.updateMixListText();
            }
            refreshOddsUrl = afbApplication.getRefreshMixOddsUrl();
            mix_count.setVisibility(View.VISIBLE);
        }
        afbApplication.isSingleBet = isSingle;
        presenter.getRefreshOdds(refreshOddsUrl);


    }


    private double countMaxPayout(double money) {
        double maxWin;
        double odds;
        AfbClickResponseBean betAfbList = afbApplication.getBetAfbList();
        if (list.size() > 1 && !StringUtils.isEmpty(betAfbList.getPayoutOdds())) {
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

    BaseRecyclerAdapter<PopChipBean> adapterChip;
    List<PopChipBean> allListChip;

    private void initBetChip() {
        allListChip = Arrays.asList(new PopChipBean(R.mipmap.chips_setting, -1, "set"), new PopChipBean(R.mipmap.chips_min, -1, "min"), new PopChipBean(R.mipmap.chips1, 1, "1"), new PopChipBean(R.mipmap.chips10, 10, "10"),
                new PopChipBean(R.mipmap.chips50, 50, "50"), new PopChipBean(R.mipmap.chips100, 100, "100"), new PopChipBean(R.mipmap.chips500, 500, "500"),
                new PopChipBean(R.mipmap.chips1000, 1000, "1000"), new PopChipBean(R.mipmap.chips5000, 5000, "5000"), new PopChipBean(R.mipmap.chips10000, 10000, "10000"),
                new PopChipBean(R.mipmap.chips30000, 30000, "30000"), new PopChipBean(R.mipmap.chips50000, 50000, "50000"), new PopChipBean(R.mipmap.chips100000, 100000, "100000"),
                new PopChipBean(R.mipmap.chips_max, 0, "max"));


        adapterChip = new BaseRecyclerAdapter<PopChipBean>(context, new ArrayList<PopChipBean>(), R.layout.item_bet_chip) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, PopChipBean item) {
                View holderView = holder.getHolderView();
                int widthPixels = DeviceUtils.getScreenPix(activity).widthPixels;
                int width = (widthPixels - DeviceUtils.dip2px(activity, 20)) / adapterChip.getItemCount();
                ViewGroup.LayoutParams layoutParams = holderView.getLayoutParams();
                layoutParams.width = width;
                ImageView imgContent = holder.getView(R.id.img_content);
                imgContent.setImageResource(item.getImgRes());
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcBetChip.setLayoutManager(linearLayoutManager);
        rcBetChip.setAdapter(adapterChip);
        adapterChip.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<PopChipBean>() {
            @Override
            public void onItemClick(View view, PopChipBean item, int position) {
                if (item.getKey().equals("set")) {
                    activity.afbDrawerViewHolder.switchFragment(activity.afbDrawerViewHolder.getSettingFragment());
                    return;
                }
                EditText edt = betAmountEdt;
                CursorEditView cursorEditView = cursorMap.get(true);
                if (cursorEditView != null && cursorEditView.getEditView() != null) {
                    edt = cursorEditView.getEditView();
                }
                String s = edt.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    s = "0";
                }
                s = s.replaceAll(",", "");
                int betAmount = Integer.parseInt(s);
                int betChip = item.getBetChip();
                if (betChip == -1) {
                    int maxLimit = getMinLimit();
                    betAmount = maxLimit;
                } else {
                    if (betChip == 0) {
                        int maxLimit = getMaxLimit();
                        betChip = maxLimit;

                    }
                    betAmount += betChip;
                }


                edt.setText(betAmount + "");
                edt.setCursorVisible(true);//显示光标
                edt.requestFocus();
                edt.setSelection(edt.getText().length());
            }
        });
    }

    private int getMaxLimit() {
        int maxLimit = (int) Double.parseDouble(afbApplication.getBetAfbList().getMaxLimit());
        if (cursorMap.get(true) == null || StringUtils.isEmpty(cursorMap.get(true).getItemSocId())) {
            if (list.size() > 1) {
                maxLimit = (int) Double.parseDouble(afbApplication.getBetAfbList().getMaxLimit());
            } else {
                maxLimit = list.get(0).getMaxLimit();
            }
        } else {
            for (AfbClickBetBean afbClickBetBean : list) {
                if (afbClickBetBean.getSocOddsId().equals(cursorMap.get(true).getItemSocId())) {
                    try {
                        float max = Float.valueOf(afbClickBetBean.getSlingMaxLimit());
                        return (int) max;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return afbClickBetBean.getMaxLimit();
                    }

                }
            }
        }

        return maxLimit;
    }

    private int getMinLimit() {
        int minLimit = 1;
        if (cursorMap.get(true) == null || StringUtils.isEmpty(cursorMap.get(true).getItemSocId())) {
            if (list.size() > 1) {
                minLimit = (int) Double.parseDouble(afbApplication.getBetAfbList().getMinLimit());
            } else {
                minLimit = list.get(0).getMinLimit();
            }
        } else {
            for (AfbClickBetBean afbClickBetBean : list) {
                if (afbClickBetBean.getSocOddsId().equals(cursorMap.get(true).getItemSocId())) {
                    try {
                        float min = Float.valueOf(afbClickBetBean.getMinLimit());
                        return (int) min;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return afbClickBetBean.getMinLimit();
                    }

                }
            }
        }

        return minLimit;
    }

    private PopChipBean findChip(Integer key, List<PopChipBean> allList) {
        for (PopChipBean popChipBean : allList) {
            if (popChipBean.getBetChip() == key) {
                return popChipBean;
            }
        }
        return null;

    }

    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    private void goBetting() {
        //http://www.afb1188.com/Bet/hBetSub.ashx?betType=1&oId=471838&odds=3.6&BTMD=S&amt=11&_=1543457323225
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
        } else {
            return;
        }
        boolean isValid = amountValid();
        if (!isValid)
            return;
        if (afbApplication.getBetParList() == null)
            return;
        String s1 = betAmountEdt.getText().toString().trim().replace(",", "");
        String s = s1.replaceAll(",", "");
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

        if (list != null && list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                if (!StringUtils.isEmpty(hashMap.get(list.get(i).getSocOddsId()))) {
                    String ss = hashMap.get(list.get(i).getSocOddsId());
                    betUrl = AppConstant.getInstance().HOST + AppConstant.getInstance()._BET + list.get(i).getBeturl() + "&amt=" + ss.trim();
                    presenter.bet(betUrl);
                }
            }
        }
        presenter.setResultCallBack(new IBetHelper.ResultCallBack() {
            @Override
            public void callBack(String back) {
//                        Chile (Over) 2 @ 0.745 ||r=467428298|5|100|34941
                if (back.contains("||") && back.contains("|")) {
                    String[] split = back.split("\\|");
                    String tidss = split[5];
                    BaseToolbarActivity sportActivity = (BaseToolbarActivity) context;
                    String oddsType = sportActivity.getOtType();
                    sportActivity.BetGoalWindowUtils.showBetWindow(oddsType, tidss, sportActivity, false);
                } else {
                    tvBetFailedHint.setText(back);
                    llBetFailedHint.setVisibility(View.VISIBLE);
                    LogUtil.d("BetPop", "updateOdds");
                    updateOdds(0);
                }
            }
        });
    }

    private boolean amountValid() {

        String s1 = betAmountEdt.getText().toString().trim().replace(",", "");
        String s = s1.replaceAll(",", "");
        if (StringUtils.isEmpty(s)) {
            ToastUtils.showShort(R.string.Input_the_amount_of_bets_please);
            return false;
        }

        double min = Double.parseDouble(betMaxWinTv.getText().toString().trim().replace(",", ""));
        double max = Double.parseDouble(betMaxBetTv.getText().toString().trim().replace(",", ""));
        if (min >= 0 && max > 0) {
            int count = Integer.valueOf(s);

            if (count <= 0) {
                ToastUtils.showShort(context.getString(R.string.invalid_amount_bet));
                betAmountEdt.setText("");
                return false;
            }
            if (count > max) {
                ToastUtils.showShort(context.getString(R.string.stake_is_more_than_max_limit));
                betAmountEdt.setText("");
                return false;
            }
            if (count < min) {
                ToastUtils.showShort(context.getString(R.string.stake_is_less_than_min_limit));
                betAmountEdt.setText("");
                return false;
            }

        }
        if (list != null && list.size() > 1) {
            boolean isValid = true;
            for (int i = 0; i < list.size(); i++) {
                if (!StringUtils.isEmpty(hashMap.get(list.get(i).getSocOddsId()))) {
                    double count = Double.parseDouble(hashMap.get(list.get(i).getSocOddsId()).trim().replace(",", ""));
                    int max1 = list.get(i).getMaxLimit();
                    try {
                        float maxaa = (Float.valueOf(list.get(i).getSlingMaxLimit()));
                        max1 = (int) maxaa;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    int min1 = list.get(i).getMinLimit();
                    if (count > max1) {
                        ToastUtils.showShort(context.getString(R.string.stake_is_more_than_max_limit));
                        return false;
                    } else if (count < min1) {
                        ToastUtils.showShort(context.getString(R.string.stake_is_less_than_min_limit));
                        return false;
                    }
                }
            }
            return isValid;
        }
        return true;
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
        if (list != null && listSize != list.size()) {
            this.listSize = list.size();
            setListLayoutParams();
            isRefreshEd = false;
        }
        this.presenter = mPresenter;
        if (list.size() == 1) {
            if (afbApplication.getMixBetList().size() < 2) {
                afbApplication.isSingleBet = true;
            }
            rcBetContent.setBackgroundResource(R.color.transparent);
        } else {
            afbApplication.isSingleBet = false;
            rcBetContent.setBackgroundResource(R.color.grey_dialog_background);
        }
        initRcBetContent();
        if (!isRefreshEd) {
            setEditNum();
        }

        tvCurrency.setText(afbApplication.getUser().getCurCode2().replace("MYR", activity.getString(R.string.MYR)));
        betBalanceTv.setText(AfbUtils.addComma(afbApplication.getUser().getCredit2(), false));
        if (list.size() > 1) {

            AfbClickResponseBean betAfbList = afbApplication.getBetAfbList();
            tvSingleMaxBet.setText(AfbUtils.scientificCountingToString(list.get(0).getMatchLimit() + ""));
            betMaxWinTv.setText(betAfbList.getMinLimit());
            betMaxBetTv.setText(AfbUtils.addComma(betAfbList.getMaxLimit(), betMaxBetTv));
        } else {
            AfbClickBetBean afbClickBetBean = list.get(0);
            tvSingleMaxBet.setText(AfbUtils.scientificCountingToString(afbClickBetBean.getMatchLimit() + ""));
            betMaxWinTv.setText(afbClickBetBean.getMinLimit() + "");
            betMaxBetTv.setText(AfbUtils.addComma(afbClickBetBean.getMaxLimit() + "", betMaxBetTv));

            if (!isRefreshEd && list.size() == 1 && activity != null) {
                if (afbClickBetBean.getIsRun() == 1) {
                    String id = afbClickBetBean.getSocOddsId();
                    OddsClickBean oddsClickBean = findRTMatchInfo(id);
                    if (oddsClickBean != null) {
                        BallInfo item = oddsClickBean.getItem();
                        setrTMatchInfo(item);
                    }
                }
            }
            if (list.get(0).getIsRun() != 1) {
                webViewPause();
            }
        }
        ((BaseActivity) context).hideLoadingDialog();
//        showInput();
        String writeMoney = betAmountEdt.getText().toString().trim().replace(",", "");
        if (!TextUtils.isEmpty(writeMoney)) {
            tvMaxWin.setText(AfbUtils.scientificCountingToString(AfbUtils.decimalValue((float) countMaxPayout(Double.parseDouble(writeMoney)), "0.00")));
        }

        if (afbApplication.getBetAfbList() != null && !StringUtils.isEmpty(afbApplication.getBetAfbList().getPayoutOdds()))
            tv1x2Odds.setText(AfbUtils.decimalValue(Float.parseFloat(afbApplication.getBetAfbList().getPayoutOdds()), "0.00"));
        stopUpdateOdds();
        LogUtil.d("BetPop", "updateOdds4000");
        updateOdds(4000);
        if (cursorMap.get(true) == null || StringUtils.isEmpty(cursorMap.get(true).getItemSocId())) {
            betAmountEdt.setCursorVisible(true);//显示光标
            betAmountEdt.requestFocus();
            betAmountEdt.setSelection(betAmountEdt.getText().length());
        }
        if (list.size() > 1) {
            webViewPause();
        }
        ll_bet_title.setVisibility(View.VISIBLE);

    }

    private OddsClickBean findRTMatchInfo(String id) {
        if (afbApplication.getSingleBet() != null && id.equals(afbApplication.getSingleBet().getOid()))
            return afbApplication.getSingleBet();
        if (afbApplication.getMixBetList() != null && afbApplication.getMixBetList().size() > 0) {
            for (OddsClickBean oddsClickBean : afbApplication.getMixBetList()) {
                if (oddsClickBean.getOid().equals(id))
                    return oddsClickBean;
            }
        }
        return null;
    }

    public void refreshChip() {
        HashMap<String, Boolean> chipStatusMap = AfbUtils.getChipStatusMap();
        List<PopChipBean> beanList = new ArrayList<>();
        for (PopChipBean popChipBean : allListChip) {
            if (popChipBean.getKey().equals("set") || chipStatusMap.get(popChipBean.getKey())) {
                beanList.add(popChipBean);
            }
        }
        if (beanList.size() < 2) {
            beanList = allListChip;

            beanList = beanList.subList(0, 7);

        }
        adapterChip.addAllAndClear(beanList);
    }

    private void webViewPause() {
        betPopParentTopFl.setVisibility(View.GONE);
        webView.getSettings().setJavaScriptEnabled(false);

    }

    private void webViewResume() {

        betPopParentTopFl.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    private void setListLayoutParams() {
        ViewGroup.LayoutParams layoutParams = fl_rc_content.getLayoutParams();
        if (list.size() > 3) {
            layoutParams.height = AfbUtils.dp2px(context, 50 * 4 + 5);
        } else {
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        }

    }

    BaseRecyclerAdapter<AfbClickBetBean> contentAdapter;

    public HashMap<String, String> getSingleHashMap() {
        return hashMap;
    }

    HashMap<String, String> hashMap = new HashMap();
    HashMap<Boolean, CursorEditView> cursorMap = new HashMap();

    private void initRcBetContent() {
        refreshChip();
        if (list.size() > 1) {
            tvSingleBet.setTextColor(Color.WHITE);
            tvMixBet.setTextColor(context.getResources().getColor(R.color.yellow_gold));
            startAlphaColor(tvMixBet, tvSingleBet);
            if (!isRefreshEd)
                initMix();
            llMix.setVisibility(View.VISIBLE);
            mix_count.setVisibility(View.VISIBLE);

        } else {
            llMix.setVisibility(View.GONE);
            tvSingleBet.setTextColor(context.getResources().getColor(R.color.yellow_gold));
            startAlphaColor(tvSingleBet, tvMixBet);
            tvMixBet.setTextColor(Color.WHITE);
            mix_count.setVisibility(View.GONE);
        }

        if (contentAdapter == null) {
            objectAnimatorMap = new HashMap<>();
            contentAdapter = new BaseRecyclerAdapter<AfbClickBetBean>(context, list, R.layout.item_bet) {

                @Override
                public void onBindViewHolder(MyRecyclerViewHolder holder, int position, List<Object> payloads) {
                    super.onBindViewHolder(holder, position, payloads);
                    holder.setIsRecyclable(false);
                }

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void convert(final MyRecyclerViewHolder holder, final int position, final AfbClickBetBean item) {
                    TextView bet_module_title_tv = holder.getView(R.id.bet_module_title_tv);
                    TextView bet_module_result_tv = holder.getView(R.id.bet_module_result_tv);
                    View ll_bottom = holder.getView(R.id.ll_bottom);
                    final EditText edt_single_bet = holder.getView(R.id.edt_single_bet);

                    ImageView imgDelete = holder.getView(R.id.img_delete);
                    TextView tv_order_index = holder.getView(R.id.tv_order_index);
                    TextView tvBetHome = holder.getView(R.id.bet_home_tv);
                    TextView tvScore = holder.getView(R.id.tv_score);
                    TextView tvBetAway = holder.getView(R.id.bet_away_tv);
                    TextView tvBetName = holder.getView(R.id.bet_name_tv);
                    TextView tv_vs = holder.getView(R.id.tv_vs);

                    TextView tvHdp = holder.getView(R.id.tv_hdp);
                    TextView tvBetHdp = holder.getView(R.id.bet_hdp_tv);
                    TextView tvBetOdds = holder.getView(R.id.bet_odds_tv);
                    TextView tvBetOddsAnimation = holder.getView(R.id.bet_odds_tv_animation);

                    View vLine = holder.getView(R.id.v_line);
                    View ll_second = holder.getView(R.id.ll_second);
                    String typeOdds = item.getOddsType();
                    if (list.size() < 2 && typeOdds != null && (typeOdds.toLowerCase().contains("home") || typeOdds.toLowerCase().contains("away") || typeOdds.toLowerCase().contains("over") || typeOdds.toLowerCase().contains("under"))) {
                        tv_vs.setVisibility(View.GONE);
                        tvBetHome.setVisibility(View.GONE);
                        tvBetAway.setVisibility(View.GONE);
                    } else {
                        tv_vs.setVisibility(View.VISIBLE);
                        tvBetHome.setVisibility(View.VISIBLE);
                        tvBetAway.setVisibility(View.VISIBLE);
                    }
                    final String socOddsId = item.getSocOddsId();
                    if (list.size() < 2) {
                        edt_single_bet.setVisibility(View.GONE);
                        tv_order_index.setVisibility(View.GONE);

                    } else {
                        int res = position % 2 == 0 ? R.drawable.bet_item_white_shadow_bottom : R.drawable.bet_item_grey_shadow_bottom;
                        holder.getHolderView().setBackgroundResource(res);
                        tv_order_index.setVisibility(View.VISIBLE);
                        tv_order_index.setText(list.size() - position + "");
                        edt_single_bet.setVisibility(View.VISIBLE);
                        edt_single_bet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View v, boolean hasFocus) {
                                if (hasFocus) {
                                    LogUtil.d("onFocusChange", position);
                                    CursorEditView cursorEditView = new CursorEditView(socOddsId, edt_single_bet);
                                    cursorMap.put(true, cursorEditView);
                                } else {
                                    // 此处为失去焦点时的处理内容
                                }
                            }
                        });
                        //如果hashMap不为空，就设置的editText
                        edt_single_bet.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start,
                                                          int count, int after) {
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                //将editText中改变的值设置的HashMap中
                                String max = item.getSlingMaxLimit();
                                if (s.toString().startsWith("0") && s.toString().length() > 1) {
                                    s.delete(0, 1);
                                }
                                String amount = s.toString().trim().replaceAll(",", "");
                                if (!TextUtils.isDigitsOnly(amount)) {
                                    amount = "0";
                                }
                                if (TextUtils.isEmpty(amount)) {
                                    amount = "0";
                                }

                                amount = amount.replaceAll(",", "");
                                try {
                                    if (Double.parseDouble(amount) > Double.parseDouble(max)) {
                                        amount = max + "";
                                    }
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                    amount = "0";
                                }
                                if (!amount.equals("0"))
                                    hashMap.put(socOddsId, amount);
                                else {
                                    hashMap.put(socOddsId, "");
                                }

                            }
                        });
                    }

                    if (hashMap.get(socOddsId) != null && !edt_single_bet.getText().toString().equals(hashMap.get(socOddsId)) && !hashMap.get(socOddsId).equals("0")) {
                        edt_single_bet.setText(hashMap.get(socOddsId));
                    } else {
                        edt_single_bet.setText("");
                    }
                    if (cursorMap.get(true) != null && !StringUtils.isEmpty(cursorMap.get(true).getItemSocId()) && cursorMap.get(true).getItemSocId().equals(socOddsId)) {
                        edt_single_bet.setCursorVisible(true);//显示光标
                        edt_single_bet.requestFocus();
                        edt_single_bet.setSelection(edt_single_bet.getText().length());
                    } else {
                        edt_single_bet.clearFocus();
                    }
                    if (position == list.size() - 1) {
                        vLine.setVisibility(View.GONE);
                    } else {
                        vLine.setVisibility(View.VISIBLE);
                    }
                    String hScore = item.getHScore();
                    String aScore = item.getAScore();
                    String score = item.getScore();
                    String isHomeGoal = item.getIsHomeGoal();
                    if (item.getIsRun() == 1) {
                        SoccerRunningGoalManager.getInstance().runScoreStyle(socOddsId, tvScore, hScore, aScore, score, isHomeGoal);
                    } else {
                        tvScore.setText("");
                    }
                    bet_module_title_tv.setText(item.getLeague());
                    if (item.getId().contains("fglg")) {
                        ll_bottom.setVisibility(View.VISIBLE);
                        String sc = item.getSc();
                        bet_module_title_tv.setTextColor(ContextCompat.getColor(activity, R.color.grey_dark));
                        switch (sc) {
                            case "1":
                            case "10":
                                bet_module_title_tv.setText(item.getHome());
                                if (item.getIsGive() == 1) {
                                    bet_module_title_tv.setTextColor(Color.RED);
                                }
                                break;
                            case "2":
                            case "20":
                                bet_module_title_tv.setText(item.getAway());
                                if (item.getIsGive() != 1) {
                                    bet_module_title_tv.setTextColor(Color.RED);
                                }
                                break;
                            default:
                                bet_module_title_tv.setText("");
                                break;
                        }
                        bet_module_result_tv.setText(item.getBTT().replace("@", ""));
                    } else {
                        ll_bottom.setVisibility(View.GONE);
                    }

                    tvBetHome.setText(item.getHome());
                    tvBetAway.setText(item.getAway());
                    String hdp = item.getHdp();

                    if (hdp.contains("-")) {
                        tvHdp.setTextColor(Color.RED);
                    } else if (item.getId().toLowerCase().contains("over") || item.getId().toLowerCase().contains("under")) {
                        tvHdp.setTextColor(Color.BLUE);
                    } else {
                        tvHdp.setTextColor(ContextCompat.getColor(activity, R.color.grey_dark));
                    }
                    tvHdp.setText(HtmlTagHandler.spanFontHtml(hdp));
                    tvBetHdp.setText(HtmlTagHandler.spanFontHtml(item.getBTT()));
                    String odds = item.getNOddsOLD();
                    Spanned spanned = HtmlTagHandler.spanFontHtml(odds);
                    tvBetOdds.setText(spanned);
                    tvBetOddsAnimation.setText(spanned);


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
                            tvBetName.setTextColor(ContextCompat.getColor(activity, R.color.grey_dark));
                        }
                    } else {
                        if (tvBetName.getText().toString().equals(item.getHome())) {
                            tvBetName.setTextColor(ContextCompat.getColor(activity, R.color.grey_dark));
                        } else {
                            tvBetName.setTextColor(Color.RED);
                        }
                        tvBetAway.setTextColor(Color.RED);
                        tvBetHome.setTextColor(ContextCompat.getColor(activity, R.color.grey_dark));
                    }
                    String typeId = item.getOddsType().toLowerCase();
                    if (!StringUtils.isEmpty(typeId) && (typeId.contains("home") || typeId.contains("away"))) {
                        tvBetHdp.setTextColor(ContextCompat.getColor(activity, R.color.black_grey));
                        String btt = "HDP " + item.getBTT();
                        tvBetHdp.setText(btt);
                    }
                    LogUtil.d("itemid", "itemid:" + typeId);
                    if (StringUtils.isEmpty(typeId) || (!typeId.contains("home")
                            && !typeId.contains("away")
                            && !typeId.contains("over")
                            && !typeId.contains("under")
                            && !typeId.equals("1")
                            && !typeId.equals("2")
                            && !typeId.equals("odd")
                            && !typeId.equals("even")
                    )) {
                        LogUtil.d("itemid", "grey_dark:");
                        tvBetName.setTextColor(ContextCompat.getColor(activity, R.color.grey_dark));
                    }
                    Animation animation = tvBetOddsAnimation.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                    }
                    ValueAnimator objectAnimator = objectAnimatorMap.get(position);

                    String oddsed = oddsMap.get(position);
                    if (oddsed != null && oddsed.equals(odds)) {
                        objectAnimator = startAlphaColor(tvBetOddsAnimation, R.color.update_bg1);
                    } else {
                        objectAnimator = startAlphaColor(tvBetOddsAnimation, R.color.yellow_gold);
                        oddsMap.put(position, odds);
                    }

                    objectAnimatorMap.put(position, objectAnimator);
                    if (contentAdapter.getItemCount() < 2) {
                        imgDelete.setVisibility(View.GONE);
                    } else {
                        imgDelete.setVisibility(View.VISIBLE);
                    }
                    imgDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            stopUpdateOdds();
                            if (position >= list.size()) {
                                goCancel();
                                return;
                            }
                            BetPop.this.deletedOne(item);
                            if (list.size() > 1) {
                                list.remove(position);
                                isNeedInitWeb = true;
                                ValueAnimator objectAnimator1 = objectAnimatorMap.get(position);
                                if (objectAnimator1 != null) {
                                    objectAnimator1.cancel();
                                    objectAnimatorMap.remove(objectAnimator1);
                                }
                                LogUtil.d("BetPop", "updateOdds00");
                                updateOdds(0);
                            } else {
                                goCancel();
                            }
                            hashMap.put(socOddsId, "");
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
                if ((presenter.getBaseView().getIBaseContext().getBaseActivity()) != null && presenter.getBaseView().getIBaseContext().getBaseActivity() instanceof SportActivity) {
                    ((SportActivity) presenter.getBaseView().getIBaseContext().getBaseActivity()).updateMixOrder();
                }
            }
        }
        if (afbApplication.getSingleBet() != null && (afbApplication.getSingleBet().getBETID().equals(id)
                || afbApplication.getSingleBet().getBETID_PAR().equals(id)
                || socOddsId.equals(afbApplication.getSingleBet().getOid())
                || socOddsId.equals(afbApplication.getSingleBet().getOid_fh()))) {
            if (mixBetList.size() > 0)
                afbApplication.setSingleBet(mixBetList.get(mixBetList.size() - 1));

        }
    }

    public void setEditNum() {
        String quickAmount = afbApplication.getQuickAmount();
        if (quickAmount.equals("0"))
            betAmountEdt.setText("");
        else {
            betAmountEdt.setText(quickAmount);
        }
        if (list == null)
            return;
        if (list.size() <= 1)
            betAmountEdt.setHint(R.string.single_bet);
        else {
            betAmountEdt.setHint(R.string.mix);
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
        if (afbApplication.getBetAfbList() != null && !StringUtils.isEmpty(afbApplication.getBetAfbList().getPayoutOdds()))
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
        if (presenter.getBaseView().getIBaseContext().getBaseActivity() instanceof SportActivity) {
            SportActivity baseActivity = (SportActivity) presenter.getBaseView().getIBaseContext().getBaseActivity();
            if (baseActivity.currentFragment != null) {
                baseActivity.currentFragment.onUpdateMixSucceed(null);
            }
        } else {
            activity.getApp().setBetAfbList(null);
        }
    }

    public volatile boolean isNeedInitWeb = true;

    public synchronized void setrTMatchInfo(IRTMatchInfo rTMatchInfo) {

        if (activity == null)
            return;
        if (activity.getApp().getHideChip().equals("1")) {
            rcBetChip.setVisibility(View.GONE);
        } else {
            rcBetChip.setVisibility(View.VISIBLE);
        }
        String rtsMatchId = rTMatchInfo.getRTSMatchId();
        if (rtsMatchId != null && (!oldRtsId.equals(rtsMatchId) || !isRefreshEd)) {
            oldRtsId = rtsMatchId;
            isNeedInitWeb = true;
        } else {
            isNeedInitWeb = false;
        }
        if (activity.getApp().isNoShowRts()) {
            webViewPause();
            return;
        }
        if (activity.fl_top_video.getVisibility() == View.VISIBLE) {
            betPopParentTopFl.setVisibility(View.GONE);
            return;
        }
        if (list.size() > 1) {
            webViewPause();
            return;
        }
        if (rTMatchInfo == null || StringUtils.isEmpty(rTMatchInfo.getRTSMatchId()) || rTMatchInfo.getRTSMatchId().equals("0")) {
            webViewPause();
            return;
        }
        if (!isNeedInitWeb)
            return;

        if (rtsMatchId != null && !rtsMatchId.isEmpty()) {
            String language = new LanguageHelper(activity).getLanguage();

            webViewResume();
            LogUtil.d("updateListText", "webViewResume--oid:" + rTMatchInfo.getSocOddsId() + ",item:" + rTMatchInfo.getHome() + "-" + rTMatchInfo.getAway());
            String gameUrl = AppConstant.getInstance().URL_RUNNING_MATCH_WEB + "?Id=" + rTMatchInfo.getRTSMatchId() + "&Home=" + com.nanyang.app.Utils.StringUtils.URLEncode(rTMatchInfo.getHome()) + "&Away=" + com.nanyang.app.Utils.StringUtils.URLEncode(rTMatchInfo.getAway()) + "&L=" + language;
            AfbUtils.synCookies(context, webView, gameUrl, false);

            LogUtil.d("gameUrl", gameUrl);
        } else {
            webViewPause();
        }
        isNeedInitWeb = false;
    }

    MixDialog mixdialog;

    public void clearSingleHashMap() {
        hashMap = new HashMap<>();
    }


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


    protected void onClose() {

//        activity.hintPopInput(betAmountEdt);
        llBetFailedHint.setVisibility(View.GONE);
        stopUpdateOdds();
        betAmountEdt.setText("");
        isNeedInitWeb = true;
        for (ValueAnimator objectAnimator : objectAnimatorMap.values()) {
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
        }
        objectAnimatorMap.clear();

        webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);


    }


    public void updateOdds(long delayedTime) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String refreshCurrentOddsUrl = afbApplication.getRefreshCurrentOddsUrl();

                if (StringUtils.isEmpty(refreshCurrentOddsUrl)) {
                    stopUpdateOdds();
                    goCancel();
                    return;
                }

                presenter.getRefreshOdds(refreshCurrentOddsUrl);
                if (!BetPop.this.refreshCurrentOddsUrl.equals(refreshCurrentOddsUrl)) {
                    BetPop.this.refreshCurrentOddsUrl = refreshCurrentOddsUrl;
                    isRefreshEd = false;
                } else {
                    isRefreshEd = true;
                }
                handler.postDelayed(this, 3000);
            }
        }, delayedTime);
    }

    public void stopUpdateOdds() {
        handler.removeCallbacksAndMessages(null);
        isRefreshEd = false;
    }

    private Map<Integer, ValueAnimator> objectAnimatorMap;
    private Map<Integer, String> oddsMap = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ValueAnimator startAlphaColor(final View view, int color) {
        //
        ValueAnimator animator = ValueAnimator.ofArgb(ContextCompat.getColor(context, R.color.transparent), ContextCompat.getColor(context, color));
        animator.setRepeatCount(Animation.INFINITE);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });
        animator.start();
        return animator;
    }

    public void startAlphaColor(TextView start, TextView stop) {
        stop.setShadowLayer(0, 0, 0, 0);
        start.setShadowLayer(12, 0, 0, ContextCompat.getColor(context, R.color.yellow_gold));
    }


}
