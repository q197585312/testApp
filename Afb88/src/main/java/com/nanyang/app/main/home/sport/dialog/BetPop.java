package com.nanyang.app.main.home.sport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.nanyang.app.main.home.sport.main.AfbParseHelper;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportBetHelper;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.ClearanceBetAmountBean;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.DeviceUtils;
import cn.finalteam.toolsfinal.StringUtils;

/**
 * Created by Administrator on 2015/10/27.
 */
public class BetPop extends BasePopupWindow {
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
    @Bind(R.id.ll_1x2)
    LinearLayout ll1x2;
    @Bind(R.id.bet_pop_parent_web_ll)
    WebView webView;
    @BindString(R.string.loading)
    String loading;
    AfbClickBetBean bean;
    @Bind(R.id.bet_pop_parent_top_fl)
    FrameLayout betPopParentTopFl;

    private SportBetHelper presenter;
    private String hdp;
    private IRTMatchInfo rTMatchInfo;

    public BetPop(Context context, View v) {
        this(context, v, DeviceUtils.dip2px(context, 350), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public BetPop(Context mContext, View v, int width, int height) {
        super(mContext, v, width, height);
        this.context = mContext;
        this.v = v;
        activity = (BaseToolbarActivity) context;
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
                int min;
                int max;
                double odds;
                if (list.size() > 1) {
                    AfbClickResponseBean betAfbList = afbApplication.getBetAfbList();
                    odds = Double.parseDouble(betAfbList.getPayoutOdds());
                    min = Integer.parseInt(betAfbList.getMinLimit());
                    max = Integer.parseInt(betAfbList.getMaxLimit());
                } else {
                    odds = Double.parseDouble(list.get(0).getOdds());
                    min = list.get(0).getMinLimit();
                    max = list.get(0).getMaxLimit();
                }
                if (!AfbUtils.touzi_ed_values22.equals(betAmountEdt.getText().toString().trim().replaceAll(",", ""))) {
                    if (bean != null && !StringUtils.isEmpty(s.toString().trim()) && Integer.valueOf(s.toString().trim().replaceAll(",", "")) > max) {
                        betAmountEdt.setText(AfbUtils.addComma(max + "", betAmountEdt));
                    } else {
                        betAmountEdt.setText(AfbUtils.addComma(betAmountEdt.getText().toString().trim().replaceAll(",", ""), betAmountEdt));
                    }
                    double writeMoney;
                    if (TextUtils.isEmpty(s)) {
                        writeMoney = 0;
                    } else {
                        writeMoney = Double.parseDouble(s.toString());
                    }
                    double maxWin;
                    if (list.size() > 1) {
                        maxWin = odds * writeMoney;
                    } else {
                        maxWin = odds * writeMoney + writeMoney;
                    }
                    if (odds < 0) {
                        maxWin = writeMoney * 2;
                    }
                    tvMaxWin.setText(Double.valueOf(maxWin).toString());
                    betAmountEdt.setSelection(AfbUtils.addComma(betAmountEdt.getText().toString().trim().replaceAll(",", ""), betAmountEdt).length());
                }

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
        initBetChip();
    }

    private void initBetChip() {
        List<PopChipBean> beanList = Arrays.asList(new PopChipBean(R.mipmap.chip1, 1), new PopChipBean(R.mipmap.chip10, 10),
                new PopChipBean(R.mipmap.chip50, 50), new PopChipBean(R.mipmap.chip100, 100), new PopChipBean(R.mipmap.chip500, 500),
                new PopChipBean(R.mipmap.chip1000, 1000), new PopChipBean(R.mipmap.chip5000, 5000), new PopChipBean(R.mipmap.chip10000, 10000),
                new PopChipBean(R.mipmap.chip30000, 30000), new PopChipBean(R.mipmap.chip50000, 50000), new PopChipBean(R.mipmap.chip100000, 100000));
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
                ToastUtils.showShort(item.getBetChip() + "");
            }
        });
    }

    private void goBetting() {
        //http://www.afb1188.com/Bet/hBetSub.ashx?betType=1&oId=471838&odds=3.6&BTMD=S&amt=11&_=1543457323225
        String s1 = betAmountEdt.getText().toString().trim();
        if (!StringUtils.isEmpty(s1)) {
            String s = s1.replaceAll(",", "");
            if (bean.getMaxLimit() > 0 && bean.getMinLimit() > 0) {
                int count = Integer.valueOf(s);
                int max = bean.getMaxLimit();
                int min = bean.getMinLimit();
                if (count > max || count < min) {
                    ToastUtils.showShort(R.string.invalid_amount_bet);
                    betAmountEdt.setText("");
                    return;
                }
                presenter.bet(AppConstant.getInstance().HOST + AppConstant.getInstance()._BET + bean.getBeturl() + "&amt=" + s);
                presenter.setResultCallBack(new IBetHelper.ResultCallBack() {
                    @Override
                    public void callBack(String odds) {
                        String betUrl = bean.getBeturl();
                        String substring1 = betUrl.substring(0, betUrl.indexOf("odds=") + 5);
                        String sb = betUrl.substring(betUrl.indexOf("odds="));
                        String substring2 = "";
                        if (sb.indexOf("&") > 0) {
                            substring2 = sb.substring(sb.indexOf("&"));
                        }
                        bean.setBeturl(substring1 + AfbUtils.decimalValue(Float.valueOf(odds) * 10, "0.00") + substring2);
//                        betOddsTv.setText(AfbUtils.decimalValue(Float.valueOf(odds), "0.00"));
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
        tvCurrency.setText(afbApplication.getUser().getCurCode2());
        betBalanceTv.setText(afbApplication.getUser().getBalances());
        if (list.size() > 1) {
            AfbClickResponseBean betAfbList = afbApplication.getBetAfbList();
            tvSingleMaxBet.setText(AfbUtils.scientificCountingToString(list.get(0).getMatchLimit() + ""));
            betMaxWinTv.setText(betAfbList.getMinLimit());
            betMaxBetTv.setText(betAfbList.getMaxLimit());
        } else {
            bean = list.get(0);
            tvSingleMaxBet.setText(AfbUtils.scientificCountingToString(bean.getMatchLimit() + ""));
            betMaxWinTv.setText(bean.getMinLimit() + "");
            betMaxBetTv.setText(bean.getMaxLimit() + "");
        }
        betSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBetting();

            }
        });/*"http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?gt=s&info.getB()="+data.getType()+"&oId=9070924&odds=1",""*/
        betCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCancel();
            }
        });
        betSureBtn.setEnabled(true);
        betCancelBtn.setEnabled(true);
        ((BaseActivity) context).hideLoadingDialog();
    }

    BaseRecyclerAdapter<AfbClickBetBean> contentAdapter;

    private void initRcBetContent() {
        ViewGroup.LayoutParams layoutParams = rcBetContent.getLayoutParams();
        if (list.size() > 1) {
            initMix();
            llMix.setVisibility(View.VISIBLE);
            layoutParams.height = AfbUtils.dp2px(context, 60 * 2);
        } else {
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            llMix.setVisibility(View.GONE);
        }
        contentAdapter = new BaseRecyclerAdapter<AfbClickBetBean>(context, list, R.layout.item_bet) {
            @Override
            public void convert(MyRecyclerViewHolder holder, final int position, AfbClickBetBean item) {
                TextView tvBetModuleTitle = holder.getView(R.id.bet_module_title_tv);
                ImageView imgDelete = holder.getView(R.id.img_delete);
                TextView tvBetHome = holder.getView(R.id.bet_home_tv);
                TextView tvBetAway = holder.getView(R.id.bet_away_tv);
                TextView tvBetName = holder.getView(R.id.bet_name_tv);
                TextView tvBetHdp = holder.getView(R.id.bet_hdp_tv);
                TextView tvBetOdds = holder.getView(R.id.bet_odds_tv);
                tvBetModuleTitle.setText(item.getLeague());
                tvBetHome.setText(item.getHome());
                tvBetAway.setText(item.getAway());
                String bTeam = item.getBTeam();
                if (bTeam.equals("Home")) {
                    tvBetName.setText(item.getHome() + item.getHdp());
                } else {
                    tvBetName.setText(item.getAway() + item.getHdp());
                }
                tvBetHdp.setText(item.getBTT());
                String odds = item.getOdds();
                tvBetOdds.setText(odds);
                if (odds.startsWith("-")) {
                    tvBetOdds.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                } else {
                    tvBetOdds.setTextColor(Color.BLACK);
                }
                boolean isHome = item.getIsGive() == 1;
                if (isHome) {
                    tvBetHome.setTextColor(ContextCompat.getColor(context, R.color.red_title));
                    tvBetAway.setTextColor(Color.BLACK);
                } else {
                    tvBetAway.setTextColor(ContextCompat.getColor(context, R.color.red_title));
                    tvBetHome.setTextColor(Color.BLACK);
                }
                imgDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (list.size() <= 1) {
                            list.clear();
                            SportActivity sportActivity = (SportActivity) context;
                            sportActivity.updateMixOrderCount();
                            closePopupWindow();
                        } else {
                            list.remove(position);
                            presenter.getRefreshOdds(afbApplication.getRefreshOddsUrl());
                        }
                    }
                });
            }
        };
        rcBetContent.setLayoutManager(new LinearLayoutManager(context));
        rcBetContent.setAdapter(contentAdapter);
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
        tv1x2Odds.setText(AfbUtils.decimalValue(Float.parseFloat(afbApplication.getBetAfbList().getPayoutOdds()), "0.00"));
        ll1x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mixdialog = new MixDialog(activity, R.style.MyDialog, ll1x2);
                mixdialog.show();
            }
        });
    }

    private void goCancel() {
        closePopupWindow();
        ((BetView) presenter.getBaseView()).onUpdateMixSucceed(null);
    }

    public void setrTMatchInfo(IRTMatchInfo rTMatchInfo) {
        if (rTMatchInfo == null)
            return;
        String rtsMatchId = rTMatchInfo.getRTSMatchId();
        if (rtsMatchId != null && !rtsMatchId.isEmpty() && !rtsMatchId.equals("0")) {
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
        }

    }

    @OnClick(R.id.bet_pop_close_iv)
    public void onClick() {

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
}
