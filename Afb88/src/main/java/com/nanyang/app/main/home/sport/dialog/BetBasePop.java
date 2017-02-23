package com.nanyang.app.main.home.sport.dialog;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import butterknife.Bind;
import butterknife.BindString;
import cn.finalteam.toolsfinal.StringUtils;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2015/10/27.
 */
public class BetBasePop extends BasePopupWindow {
    private Disposable subscribe;
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
    private String betSelection;
    private String popTitle;
    private String state = "";
    private BettingInfoBean info;
    private SportContract.Presenter presenter;

    public BetBasePop(Context context, View v) {
        super(context, v);
    }

    public BetBasePop(Context mContext, View v, int width, int height) {
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

    @Override
    protected void onClose() {
        super.onClose();
        if (subscribe != null)
            subscribe.dispose();
    }

    public void setBetSelectionType(String betSelection) {
        this.betSelection = betSelection;
        if (betSelection != null && !betSelection.equals("")) {
            halfTv.setVisibility(View.VISIBLE);
            halfTv.setText(betSelection);
        } else {
            halfTv.setVisibility(View.GONE);
        }
    }

    public BettingInfoBean initData(final BettingInfoBean info) {
        this.info = info;

        popTitle = "";
        state = "";
        switch (info.getB()) {
            case "1":
            case "2":
            case "x":
                popTitle = "1X2";
                break;
            case "dc":
                popTitle = context.getString(R.string.double_chance);
                break;
            case "odd":
            case "even":
                popTitle = context.getString(R.string.odd_even);
                break;
            case "csr":
                popTitle = context.getString(R.string.correct_score);
                break;
            case "htft":
                popTitle = context.getString(R.string.half_full_time);
                break;
            case "fglg":
                popTitle = context.getString(R.string.first_last_goal);
                break;
            case "tg":
                popTitle = context.getString(R.string.total_goals);
                break;
        }
        if (info.getB().equals("x"))
            info.setB("X");
        return info;

    }

    public void setBetData(BettingPromptBean result, SportContract.Presenter mPresenter) {
        this.presenter = mPresenter;
        ((BaseActivity) context).hideLoadingDialog();
        bean = result;
        betSureBtn.setEnabled(true);
        betMaxWinTv.setText(result.getMinLimit());
        betMaxBetTv.setText(result.getMaxLimit());
        betModuleTitleTv.setText(result.getModuleTitle());
        betHomeTv.setText(result.getHome());
        betAwayTv.setText(result.getAway());
        String hdp = "";
        switch (info.getB()) {
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
                String sc = info.getSc();
                if (sc != null) {

                    if (sc.length() == 1) {
                        sc = "0" + sc;
                    }
                    char s1 = sc.charAt(0);
                    char s2 = sc.charAt(1);
                    hdp = s1 + "-" + s2;
                }
                break;
            case "dc":
                state = context.getString(R.string.double_chance);
                if (info.getSc() != null) {
                    switch (info.getSc()) {
                        case "10":
                            hdp = context.getString(R.string.x1);
                            break;
                        case "12":
                            hdp = context.getString(R.string.x12);
                            break;
                        case "2":
                            hdp = context.getString(R.string.x2);
                            break;
                    }
                }
                break;
            case "htft":
                state = context.getString(R.string.half_full_time);
                if (info.getSc() != null) {
                    switch (info.getSc()) {
                        case "10":
                            hdp = "HD";
                            break;
                        case "12":
                            hdp = "HA";
                            break;
                        case "2":
                            hdp = "DA";
                            break;
                        case "0":
                            hdp = "DD";
                            break;
                        case "1":
                            hdp = "DH";
                            break;
                        case "11":
                            hdp = "HH";
                            break;
                        case "20":
                            hdp = "AD";
                            break;
                        case "21":
                            hdp = "AH";
                            break;
                        case "22":
                            hdp = "AA";
                            break;
                    }
                }
                break;
            case "fglg":
                state = popTitle;
                if (info.getSc() != null) {
                    switch (info.getSc()) {
                        case "10":
                            hdp = result.getHome() + "(" + context.getString(R.string.first_goal) + ")";
                            break;
                        case "1":
                            hdp = result.getHome() + "(" + context.getString(R.string.last_goal) + ")";
                            break;
                        case "20":
                            hdp = result.getAway() + "(" + context.getString(R.string.first_goal) + ")";
                            break;
                        case "2":
                            hdp = result.getAway() + "(" + context.getString(R.string.last_goal) + ")";
                            break;
                        case "0":
                            hdp = context.getString(R.string.no_goal);
                            break;
                    }
                }
                break;
            case "tg":
                state = popTitle;
                if (info.getSc() != null) {
                    switch (info.getSc()) {
                        case "1":
                            hdp = "0-1";
                            break;
                        case "23":
                            hdp = "2-3";
                            break;
                        case "46":
                            hdp = "4-6";
                            break;
                        case "70":
                            hdp = "7&Over";
                            break;
                    }
                }
                break;
            case "away":
                state = result.getAway();
                break;
            case "home":
                state = result.getHome();
                break;
            case "under":
                state = context.getString(R.string.under);
                break;
            case "over":
                state = context.getString(R.string.over);
                break;
        }
        if (result.getBetHdp() != null) {
            if ((result.isIsHomeGive() && info.getB().equals("home")) || (!result.isIsHomeGive() && info.getB().equals("away"))) {
//                        if (betSelection == null||betSelection.equals(""))
                hdp = "-" + result.getBetHdp();
//                        else
//                            hdp = "-" + changeV(info.getHdp());
            } else {
//                        if (betSelection == null||betSelection.equals(""))
                hdp = result.getBetHdp();
//                        else
//                            hdp =changeV(info.getHdp());
            }
        }
        betNameTv.setText(state);
        betHdpTv.setText(hdp + "@" + AfbUtils.changeValueS(info.getOdds()) );

        betSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBetting();

            }
        });/*"http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?gt=s&info.getB()="+data.getType()+"&oId=9070924&odds=1",""*/
    }
}
