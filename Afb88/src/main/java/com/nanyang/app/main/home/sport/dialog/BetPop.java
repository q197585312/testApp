package com.nanyang.app.main.home.sport.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.nanyang.app.main.home.sport.main.AfbParseHelper;
import com.nanyang.app.main.home.sport.main.SportBetHelper;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
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
    @Bind(R.id.bet_balance_tv)
    TextView betBalanceTv;
    //    @Bind(R.id.bet_module_title_tv)
//    TextView betModuleTitleTv;
//    @Bind(R.id.bet_home_tv)
//    TextView betHomeTv;
//    @Bind(R.id.bet_away_tv)
//    TextView betAwayTv;
//    @Bind(R.id.bet_name_tv)
//    TextView betNameTv;
//    @Bind(R.id.bet_score_tv)
//    TextView betScoreTv;
//    @Bind(R.id.bet_hdp_tv)
//    TextView betHdpTv;
//    @Bind(R.id.bet_odds_tv)
//    TextView betOddsTv;
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
    @Bind(R.id.bet_pop_parent_web_ll)
    WebView webView;
//    @Bind(R.id.bet_half_tv)
//    TextView halfTv;
//    @Bind(R.id.tv_home_score)
//    TextView tv_home_score;
//    @Bind(R.id.tv_vs)
//    TextView tv_vs;
//    @Bind(R.id.tv_away_score)
//    TextView tv_away_score;

    @BindString(R.string.loading)
    String loading;
    AfbClickBetBean bean;

    @Bind(R.id.bet_pop_parent_top_fl)
    FrameLayout betPopParentTopFl;

    private String popTitle;
    private String state = "";

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
                if (!AfbUtils.touzi_ed_values22.equals(betAmountEdt.getText().toString().trim().replaceAll(",", ""))) {
                    if (bean != null && !StringUtils.isEmpty(s.toString().trim()) && Integer.valueOf(s.toString().trim().replaceAll(",", "")) > bean.getMaxLimit()) {
                        betAmountEdt.setText(AfbUtils.addComma(bean.getMaxLimit() + "", betAmountEdt));
                    } else {
                        betAmountEdt.setText(AfbUtils.addComma(betAmountEdt.getText().toString().trim().replaceAll(",", ""), betAmountEdt));
                    }
                    double odds = Double.parseDouble(result.getOdds());
                    double writeMoney = Double.parseDouble(s.toString());
                    double maxWin = odds * writeMoney;
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
    private AfbClickBetBean result;

    public void setBetData(List<AfbClickBetBean> list, SportBetHelper mPresenter) {
        initRcBetContent(list);
        tvCurrency.setText(activity.getApp().getUser().getCurCode2());
        result = list.get(0);
        betBalanceTv.setText(((AfbApplication) context.getApplicationContext()).getUser().getBalances());
        tvSingleMaxBet.setText(AfbUtils.scientificCountingToString(result.getMatchLimit() + ""));
        this.presenter = mPresenter;
        ((BaseActivity) context).hideLoadingDialog();
        bean = result;
        betSureBtn.setEnabled(true);
        betCancelBtn.setEnabled(true);
        betMaxWinTv.setText(result.getMinLimit() + "");
        betMaxBetTv.setText(result.getMaxLimit() + "");
//        betModuleTitleTv.setText(result.getLeague());
        if (result.getIsRun() == 1) {
//            betScoreTv.setText(result.getRunHomeScore() + " V " + result.getRunAwayScore());
//            tv_home_score.setText(result.getScore());
//            tv_away_score.setVisibility(View.GONE);
//            tv_vs.setVisibility(View.GONE);
        }
        boolean isHome = result.getIsGive() == 1;
        if (isHome) {
//            betHomeTv.setTextColor(context.getResources().getColor(R.color.red_title));
        } else {
//            betAwayTv.setTextColor(context.getResources().getColor(R.color.red_title));
        }
//        betHomeTv.setText(result.getHome());
//        betAwayTv.setText(result.getAway());
        popTitle = result.getBTT();
        hdp = "";
        String betTypeFromId = new AfbParseHelper<>().getBetTypeFromId(result.getId());
        switch (betTypeFromId) {
            case "1":
                state = result.getHome() + "(" + context.getString(R.string.win) + ")";

                break;
            case "2":
                state = result.getAway() + "(" + context.getString(R.string.win) + ")";

                break;
            case "x":
            case "X":
                state = result.getHome() + "(" + context.getString(R.string.draw) + ")";

                break;
            case "odd":
                state = context.getString(R.string.odd);
                break;
            case "even":
                state = context.getString(R.string.even);
                break;
            case "csr":
                break;
            case "dc":
            case "htft":
            case "fglg":
            case "tg":
                state = popTitle;
                break;
            case "away":
                state = result.getAway();
                if (!isHome)
//                    betNameTv.setTextColor(context.getResources().getColor(R.color.red_title));
                    break;
            case "home":
                state = result.getHome();
                if (isHome)
//                    betNameTv.setTextColor(context.getResources().getColor(R.color.red_title));
                    break;
            case "under":
                state = context.getString(R.string.under);
                break;
            case "over":
                state = context.getString(R.string.over);
//                betNameTv.setTextColor(context.getResources().getColor(R.color.red_title));
                break;
        }
        if (result.getHdp() != null) {

            hdp = Html.fromHtml(result.getHdp()).toString();
        }
//        betNameTv.setText(state);
        if (betTypeFromId.startsWith("mm")) {
            String str = hdp + "@";
            if (str.contains("-") && str.contains("(") && str.contains(")")) {
                SpannableStringBuilder ssb = AfbUtils.handleStringTextColor(str, str.indexOf("(") + 1, str.indexOf(")"), context.getResources().getColor(R.color.red_title));
//                betHdpTv.setText(ssb);
            } else {
//                betHdpTv.setText(hdp + "@");
            }
        } else {
//            betHdpTv.setText(hdp + "@");
        }
        String odds = Html.fromHtml(result.getOdds()).toString();
//        if (odds != null && !odds.isEmpty() && Float.valueOf(odds) < 0) {
//            betOddsTv.setTextColor(context.getResources().getColor(R.color.red_title));
//        } else {
//            betOddsTv.setTextColor(context.getResources().getColor(R.color.black_grey));
//        }
//        betOddsTv.setText(odds);

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
//        halfTv.setVisibility(View.VISIBLE);
//        halfTv.setText(result.getIsFH());
    }

    private void initRcBetContent(List<AfbClickBetBean> list) {
        ViewGroup.LayoutParams layoutParams = rcBetContent.getLayoutParams();
        if (list.size() > 1) {
            llMix.setVisibility(View.VISIBLE);
            layoutParams.height = AfbUtils.dp2px(context, 60 * 2);
        } else {
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            llMix.setVisibility(View.GONE);
        }
        BaseRecyclerAdapter<AfbClickBetBean> adapter = new BaseRecyclerAdapter<AfbClickBetBean>(context, list, R.layout.item_bet) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, AfbClickBetBean item) {
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
                        //TODO
                    }
                });
            }
        };
        rcBetContent.setLayoutManager(new LinearLayoutManager(context));
        rcBetContent.setAdapter(adapter);
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
}
