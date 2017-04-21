package com.nanyang.app.main.home.sport.dialog;

import android.content.Context;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import butterknife.Bind;
import butterknife.BindString;
import cn.finalteam.toolsfinal.StringUtils;

/**
 * Created by Administrator on 2015/10/27.
 */
public class BetPop extends BasePopupWindow {
    Context context;
    @Bind(R.id.bet_module_title_tv)
    TextView betModuleTitleTv;
    @Bind(R.id.bet_home_tv)
    TextView betHomeTv;
    @Bind(R.id.bet_away_tv)
    TextView betAwayTv;
    @Bind(R.id.bet_name_tv)
    TextView betNameTv;
    @Bind(R.id.bet_hdp_tv)
    TextView betHdpTv;
    @Bind(R.id.bet_odds_tv)
    TextView betOddsTv;
    @Bind(R.id.bet_amount_edt)
    EditText betAmountEdt;
    @Bind(R.id.bet_sure_btn)
    TextView betSureBtn;

    @Bind(R.id.bet_max_win_tv)
    TextView betMaxWinTv;
    @Bind(R.id.bet_max_bet_tv)
    TextView betMaxBetTv;
    @Bind(R.id.bet_pop_parent_ll)
    View parentV;
    @Bind(R.id.bet_half_tv)
    TextView halfTv;

    @BindString(R.string.loading)
    String loading;
    BettingPromptBean bean;
    private boolean betSelection;
    private String popTitle;
    private String state = "";

    private IBetHelper presenter;
    private String hdp;

    public BetPop(Context context, View v) {
        this(context, v, 800, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public BetPop(Context mContext, View v, int width, int height) {
        super(mContext, v, width, height);
        this.context = mContext;
        this.v = v;
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
    }


    private void goBetting() {
        String s = betAmountEdt.getText().toString().trim();
        if (!StringUtils.isEmpty(s)) {
            if (!bean.getMaxLimit().equals("") && !bean.getMaxLimit().equals("0") && !bean.getMinLimit().equals("")) {
                int count = Integer.valueOf(s);
                int max = Integer.valueOf(bean.getMaxLimit());
                int min = Integer.valueOf(bean.getMinLimit());
                if (count > max || count < min) {
                    ToastUtils.showShort(R.string.invalid_amount_bet);
                    betAmountEdt.setText("");
                    return;
                }
                //http://a8197c.a36588.com/_bet/PanelBet.aspx?betGrp=1&betType=under&oId=12042173&ou=3&isBetHome=False&isFH=False&accType=HK&odds=6.9&reducePercent=1&amt=11&isBetterOdds=true
                presenter.bet(AppConstant.HOST + "_bet/" + bean.getBetUrl() + "&amt=" + s);
                presenter.setResultCallBack(new IBetHelper.ResultCallBack() {
                    @Override
                    public void callBack(String odds) {
                        String betUrl = bean.getBetUrl();
                        String substring1 = betUrl.substring(0, betUrl.indexOf("odds=") + 5);
                        String sb = betUrl.substring(betUrl.indexOf("odds="));
                        String substring2 = "";
                        if (sb.indexOf("&") > 0) {
                            substring2 = sb.substring(sb.indexOf("&"));
                        }
                        bean.setBetUrl(substring1 + odds + substring2);
                        betOddsTv.setText(AfbUtils.decimalValue(Float.valueOf(odds) / 10, "0.00"));
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

    public void setIsHf(boolean betSelection) {
        this.betSelection = betSelection;
        if (betSelection) {
            halfTv.setVisibility(View.VISIBLE);
            halfTv.setText(context.getString(R.string.half_time));
        } else {
            halfTv.setVisibility(View.GONE);
        }
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
    public void setBetData(BettingPromptBean result, IBetHelper mPresenter) {
        this.presenter = mPresenter;
        ((BaseActivity) context).hideLoadingDialog();
        bean = result;
        betSureBtn.setEnabled(true);
        betMaxWinTv.setText(result.getMinLimit());
        betMaxBetTv.setText(result.getMaxLimit());
        betModuleTitleTv.setText(result.getModuleTitle());
        boolean isHome = result.isIsHomeGive();
        if (isHome) {
            betHomeTv.setTextColor(context.getColor(R.color.red_title));
        } else {
            betAwayTv.setTextColor(context.getColor(R.color.red_title));
        }
        betHomeTv.setText(result.getHome());
        betAwayTv.setText(result.getAway());
        popTitle = result.getGTitle();
        hdp = "";
        switch (result.getBetType()) {
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
                    betNameTv.setTextColor(context.getColor(R.color.red_title));
                break;
            case "home":
                state = result.getHome();
                if (isHome)
                    betNameTv.setTextColor(context.getColor(R.color.red_title));
                break;
            case "under":
                state = context.getString(R.string.under);
                break;
            case "over":
                state = context.getString(R.string.over);
                betNameTv.setTextColor(context.getColor(R.color.red_title));
                break;
        }
        if (result.getBetHdp() != null) {
            if ((result.isIsHomeGive() && result.getBetType().equals("home")) || (!result.isIsHomeGive() && result.getBetType().equals("away"))) {

                hdp = "-" + Html.fromHtml(result.getBetHdp()).toString();


            } else {

                hdp = Html.fromHtml(result.getBetHdp()).toString();

            }
        }
        betNameTv.setText(state);
        betHdpTv.setText(hdp + "@");
        String odds = Html.fromHtml(result.getBetOdds()).toString();
        if (odds != null && !odds.isEmpty() && Float.valueOf(odds) < 0) {
            betOddsTv.setTextColor(context.getColor(R.color.red_title));
        } else {
            betOddsTv.setTextColor(context.getColor(R.color.black_grey));
        }
        betOddsTv.setText(odds);

        betSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBetting();

            }
        });/*"http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?gt=s&info.getB()="+data.getType()+"&oId=9070924&odds=1",""*/
    }
}
