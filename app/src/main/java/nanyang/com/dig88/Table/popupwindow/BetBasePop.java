package nanyang.com.dig88.Table.popupwindow;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import butterknife.BindString;
import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.BettingInfoBean;
import nanyang.com.dig88.Table.entity.BettingPromptBean;
import nanyang.com.dig88.Table.utils.BettingDataHelper;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2015/10/27.
 */
public class BetBasePop extends BasePopupWindow {
    Context context;
    @BindView(R.id.bet_module_title_tv)
    TextView betModuleTitleTv;
    @BindView(R.id.bet_home_tv)
    TextView betHomeTv;
    @BindView(R.id.bet_away_tv)
    TextView betAwayTv;
    @BindView(R.id.bet_name_tv)
    TextView betNameTv;
    @BindView(R.id.bet_hdp_tv)
    TextView betHdpTv;
    @BindView(R.id.bet_amount_edt)
    EditText betAmountEdt;
    @BindView(R.id.bet_sure_btn)
    TextView betSureBtn;
    BettingDataHelper helper;
    @BindView(R.id.bet_max_win_tv)
    TextView betMaxWinTv;
    @BindView(R.id.bet_max_bet_tv)
    TextView betMaxBetTv;
    @BindView(R.id.bet_pop_parent_ll)
    View parentV;
    @BindView(R.id.bet_half_tv)
    TextView halfTv;
    @BindView(R.id.bet_max_payout_tv)
    TextView betPayoutTv;
    @BindView(R.id.ll_wb_bet)
    LinearLayout ll_wb_bet;
    @BindView(R.id.wb_bet)
    WebView wb_bet;
    @BindString(R.string.loading)
    String loading;
    BettingPromptBean bean;
    private String betSelection;
    private String popTitle;
    private String state = "";
    private BettingInfoBean info;

    public BetBasePop(Context context, View v) {
        super(context, v);
    }

    public BetBasePop(final Context context, View v, int width, int height) {
        super(context, v, width, height);
        this.context = context;
        this.v = v;
        betAmountEdt.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    goBetting(context);
                    return true;
                }
                return false;
            }

        });
        helper = new BettingDataHelper(context, parentV, new ThreadEndT<BettingPromptBean>(new TypeToken<BettingPromptBean>() {
        }.getType()) {

            @Override
            public void endT(BettingPromptBean result, int model) {

                ((BaseActivity) context).dismissBlockDialog();
                bean = result;
                betSureBtn.setEnabled(true);
                betMaxWinTv.setText(result.getMinLimit());
                betMaxBetTv.setText(result.getMaxLimit());
                betModuleTitleTv.setText(result.getModuleTitle());
                betHomeTv.setText(result.getHome());
                betAwayTv.setText(result.getAway());
                String hdp = "";
                if (info.getB().equals("1"))
                    state = result.getHome() + "(" + context.getString(R.string.win) + ")";
                else if (info.getB().equals("2"))
                    state = result.getAway() + "(" + context.getString(R.string.win) + ")";
                else if (info.getB().equals("x") || info.getB().equals("X"))
                    state = result.getHome() + "(" + context.getString(R.string.draw) + ")";
                else if (info.getB().equals("odd")) {
                    state = context.getString(R.string.odd);
                } else if (info.getB().equals("even")) {
                    state = context.getString(R.string.even);
                } else if (info.getB().equals("csr")) {
                    String sc = info.getSc();
                    if (sc != null) {

                        if (sc.length() == 1) {
                            sc = "0" + sc;
                        }
                        char s1 = sc.charAt(0);
                        char s2 = sc.charAt(1);
                        hdp = s1 + "-" + s2;
                    }
                } else if (info.getB().equals("dc")) {
                    state = context.getString(R.string.double_chance);
                    if (info.getSc() != null) {
                        if (info.getSc().equals("10"))
                            hdp = context.getString(R.string.x1);
                        else if (info.getSc().equals("12"))
                            hdp = context.getString(R.string.x12);
                        else if (info.getSc().equals("2"))
                            hdp = context.getString(R.string.x2);
                    }
                } else if (info.getB().equals("htft")) {
                    state = context.getString(R.string.half_full_time);
                    if (info.getSc() != null) {
                        if (info.getSc().equals("10"))
                            hdp = "HD";
                        else if (info.getSc().equals("12"))
                            hdp = "HA";
                        else if (info.getSc().equals("2"))
                            hdp = "DA";
                        else if (info.getSc().equals("0"))
                            hdp = "DD";
                        else if (info.getSc().equals("1"))
                            hdp = "DH";
                        else if (info.getSc().equals("11"))
                            hdp = "HH";
                        else if (info.getSc().equals("20"))
                            hdp = "AD";
                        else if (info.getSc().equals("21"))
                            hdp = "AH";
                        else if (info.getSc().equals("22"))
                            hdp = "AA";
                    }
                } else if (info.getB().equals("fglg")) {
                    state = popTitle;
                    if (info.getSc() != null) {
                        if (info.getSc().equals("10"))
                            hdp = result.getHome() + "(" + context.getString(R.string.first_goal) + ")";
                        else if (info.getSc().equals("1"))
                            hdp = result.getHome() + "(" + context.getString(R.string.last_goal) + ")";
                        else if (info.getSc().equals("20"))
                            hdp = result.getAway() + "(" + context.getString(R.string.first_goal) + ")";
                        else if (info.getSc().equals("2"))
                            hdp = result.getAway() + "(" + context.getString(R.string.last_goal) + ")";
                        else if (info.getSc().equals("0"))
                            hdp = context.getString(R.string.no_goal);
                    }
                } else if (info.getB().equals("tg")) {
                    state = popTitle;
                    if (info.getSc() != null) {
                        if (info.getSc().equals("1"))
                            hdp = "0-1";
                        else if (info.getSc().equals("23"))
                            hdp = "2-3";
                        else if (info.getSc().equals("46"))
                            hdp = "4-6";
                        else if (info.getSc().equals("70"))
                            hdp = "7&Over";
                    }
                } else if (info.getB().equals("away")) {
                    state = result.getAway();
                } else if (info.getB().equals("home")) {
                    state = result.getHome();
                } else if (info.getB().equals("under")) {
                    state = context.getString(R.string.under);
                } else if (info.getB().equals("over")) {
                    state = context.getString(R.string.over);
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
                betHdpTv.setText(hdp + "@" + info.getOdds());

                betSureBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goBetting(context);

                    }
                });/*"http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?gt=s&info.getB()="+data.getType()+"&oId=9070924&odds=1",""*/

            }

            @Override
            public void endString(String result, int model) {
                ((BaseActivity) context).dismissBlockDialog();
                if (result != null) {
                    if (result.contains("||") && result.contains("@")) {
                        result = result + " " + context.getString(R.string.xiazhusuccess);
                    }
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                    closePopupWindow();
                }
            }

            @Override
            public void endError(String error) {
                if (error != null)
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                ((BaseActivity) context).dismissBlockDialog();
                closePopupWindow();
            }
        });
    }

    private String changeV(String hdp) {
        String[] hdps = hdp.split("-");
        if (hdps.length < 2)
            return hdp;
        else {
            float v = Float.valueOf(hdps[0]);
            return StringUtils.subZeroAndDot((float) (v + 0.25));
        }
    }

    private void goBetting(Context context) {
        String s = betAmountEdt.getText().toString().trim();
        if (!StringUtils.isNull(context, s, context.getString(R.string.Input_the_amount_of_bets_please))) {
            if (!bean.getMaxLimit().equals("") && !bean.getMaxLimit().equals("0") && !bean.getMinLimit().equals("")) {
                int count = Integer.valueOf(s);
                int max = Integer.valueOf(bean.getMaxLimit());
                int min = Integer.valueOf(bean.getMinLimit());
                if (count > max || count < min) {
                    Toast.makeText(context, context.getString(R.string.invalid_amount_bet), Toast.LENGTH_SHORT).show();
                    betAmountEdt.setText("");
                    return;
                }

            }
            helper.updateData(WebSiteUrl.SportUrl + "_bet/" + bean.getBetUrl() + "&amt=" + s, "");
        }
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.popupwindow_match_betting;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

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

    public void getData(BettingInfoBean info) {
        this.info = info;

        popTitle = "";
        state = "";
        if (info.getB().equals("1") || info.getB().equals("2") || info.getB().equals("x"))
            popTitle = "1X2";
        else if (info.getB().equals("dc")) {
            popTitle = context.getString(R.string.double_chance);
        } else if (info.getB().equals("odd") || info.getB().equals("even")) {
            popTitle = context.getString(R.string.odd_even);
        } else if (info.getB().equals("csr")) {
            popTitle = context.getString(R.string.correct_score);
        } else if (info.getB().equals("htft")) {
            popTitle = context.getString(R.string.half_full_time);
        } else if (info.getB().equals("fglg")) {
            popTitle = context.getString(R.string.first_last_goal);
            ;
        } else if (info.getB().equals("tg")) {
            popTitle = context.getString(R.string.total_goals);
            ;
        }
        if (info.getB().equals("x"))
            info.setB("X");
        ((BaseActivity) context).setDialog(new BlockDialog(context, loading));
        ((BaseActivity) context).showBlockDialog();
        helper.getData(info);
    }

    public void initWebView(String url) {
        ll_wb_bet.setVisibility(View.VISIBLE);
        wb_bet.getSettings().setJavaScriptEnabled(true);
        wb_bet.setWebChromeClient(new WebChromeClient());
//        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
//        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
//        webView.getSettings().setLoadWithOverviewMode(true);
        wb_bet.loadUrl(url);
        wb_bet.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });

    }

}
