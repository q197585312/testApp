package nanyang.com.dig88.Keno;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.DecimalUtils;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.base.quick.QuickRequestBean;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Lottery.LotteryBetResultBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2016/6/7.
 */
public class KenoDetailsFragment extends KenoBaseFragment {
    @BindView(R.id.tv_keno_country2)
    TextView tvKenoCountry2;
    @BindView(R.id.tv_keno_time_period)
    TextView tvKenoTimePeriod;
    @BindView(R.id.tv_keno_period_total)
    TextView tvKenoPeriodCount;
    @BindView(R.id.tv_keno_up_odds)
    TextView tvKenoUpOdds;
    @BindView(R.id.tv_keno_down_odds)
    TextView tvKenoDownOdds;
    @BindView(R.id.tv_keno_odds_odds)
    TextView tvKenoOddsOdds;
    @BindView(R.id.tv_keno_odds_evens_tip_odds)
    TextView tvKenoOddsEvensTipOdds;
    @BindView(R.id.tv_keno_up_down_tip_odds)
    TextView tvKenoUpDownTipOdds;
    @BindView(R.id.tv_keno_evens_odds)
    TextView tvKenoEvensOdds;
    @BindView(R.id.fgv_number_list)
    GridView fgv_number_list;
    int currentIndex = 0;

    @BindView(R.id.bet_pop_parent)
    View popParent;
    @BindView(R.id.iv_hide)
    ImageView ivHide;
    @BindView(R.id.tv_bet_pop_title)
    TextView tvBetPopTitle;
    @BindView(R.id.tv_bet_pop_country)
    TextView tvBetPopCountry;
    @BindView(R.id.ll_country)
    LinearLayout llCountry;
    @BindView(R.id.tv_bet_pop_number)
    TextView tvBetPopNumber;
    @BindView(R.id.tv_bet_pop_odds)
    TextView tvBetPopOdds;
    @BindView(R.id.edt_bet_pop_amount)
    EditText edtBetPopAmount;
    @BindView(R.id.tv_bet_pop_actual_amount)
    TextView tvBetPopActualAmount;
    @BindView(R.id.btn_bet_pop_sure)
    Button btnBetPopSure;
    @BindView(R.id.btn_bet_pop_cancel)
    Button btnBetPopCancel;


    @BindView(R.id.tv_pearl1)
    TextView tvPearl1;
    @BindView(R.id.tv_pearl1_odds)
    TextView tvPearl1Odds;
    @BindView(R.id.tv_pearl2)
    TextView tvPearl2;
    @BindView(R.id.tv_pearl2_odds)
    TextView tvPearl2Odds;
    @BindView(R.id.tv_pearl3)
    TextView tvPearl3;
    @BindView(R.id.tv_pearl3_odds)
    TextView tvPearl3Odds;
    @BindView(R.id.tv_pearl4)
    TextView tvPearl4;
    @BindView(R.id.tv_pearl4_odds)
    TextView tvPearl4Odds;
    @BindView(R.id.tv_pearl5)
    TextView tvPearl5;
    @BindView(R.id.tv_pearl5_odds)
    TextView tvPearl5Odds;
    @BindView(R.id.btn_bet_up)
    LinearLayout btnBetUp;
    @BindView(R.id.btn_bet_up_down_tip)
    LinearLayout btnBetUpDownTip;
    @BindView(R.id.btn_bet_down)
    LinearLayout btnBetDown;
    @BindView(R.id.btn_bet_odds)
    LinearLayout btnBetOdds;
    @BindView(R.id.btn_bet_odds_evens_tie)
    LinearLayout btnBetOddsEvensTie;
    @BindView(R.id.btn_bet_evens)
    LinearLayout btnBetEvens;
    @BindView(R.id.keno_period_left)
    ImageView kenoPeriodLeft;
    @BindView(R.id.keno_period_right)
    ImageView kenoPeriodRight;



    private AdapterViewContent<KenoNumberBean> numberListContent;
    private ArrayList<String> numberList = new ArrayList<>();
    private int pid2;

    private boolean canBet = true;


    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_details_keno;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getAct().setRightViewClickable(false);
        pid2 = ScreenUtil.dip2px(mContext, 2);
        tvKenoCountry2.setText(selectedStateBean.getGame_name());
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        setClick();
        popParent.setVisibility(View.GONE);
        llCountry.setVisibility(View.VISIBLE);
        ivHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeBetNumber();
            }
        });
        numberListContent = new AdapterViewContent<KenoNumberBean>(mContext, fgv_number_list);
        numberListContent.setBaseAdapter(new QuickAdapterImp<KenoNumberBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_text;
            }

            @Override
            public void convert(ViewHolder helper, KenoNumberBean item, int position) {
                helper.setBackgroundRes(R.id.text_tv1, R.drawable.rectangle_button_corner5_white_orange_selector);
                helper.setText(R.id.text_tv1, item.number);
            }
        });
        List<KenoNumberBean> list = new ArrayList<>();
        for (int i = 1; i < 81; i++) {
            String number = "" + i;
            if (i < 10) {
                number = "0" + number;
            }

            list.add(new KenoNumberBean(number));

        }
        numberListContent.setItemClick(new ItemCLickImp<KenoNumberBean>() {
            @Override
            public void itemCLick(View view, KenoNumberBean kenoNumberBean, int position) {
                if (getAct().getPearlBallList() == null)
                    return;
                if (numberList.contains(kenoNumberBean.number)) {
                    if (numberList.size() > 1)
                        numberList.remove(kenoNumberBean.number);
                } else {
                    if (numberList.size() < 5) {
                        numberList.add(kenoNumberBean.number);
                    }
                }
                int pearlBallSize = numberList.size();
                DigGameOddsBean pearlBall = getAct().getPearlBallList().get(5 - pearlBallSize);
                betPopData(pearlBallSize, pearlBall);
            }
        });
        numberListContent.setData(list);

    }

    private void setClick() {
        kenoPeriodLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPeriodLeft(v);
            }
        });
        kenoPeriodRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPeriodRight(v);
            }
        });
        btnBetUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnBetUpDownTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUpDownTie(v);
            }
        });
        btnBetDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDown(v);
            }
        });
        btnBetUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUp(v);
            }
        });
        btnBetOdds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOdds(v);
            }
        });
        btnBetOddsEvensTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOddsEvensTie(v);
            }
        });
        btnBetEvens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvens(v);
            }
        });
    }

    private void betPopData(int size, final DigGameOddsBean pearlBall) {
        popParent.setVisibility(View.VISIBLE);
        tvPearl1.setText("");
        tvPearl2.setText("");
        tvPearl3.setText("");
        tvPearl4.setText("");
        tvPearl5.setText("");
        tvPearl1Odds.setText("");
        tvPearl2Odds.setText("");
        tvPearl3Odds.setText("");
        tvPearl4Odds.setText("");
        tvPearl5Odds.setText("");
        String odds = "";
        switch (size) {
            case 1:
                tvPearl1.setText("1");
                tvPearl1Odds.setText(pearlBall.getFactor());
                odds = "1#" + pearlBall.getFactor();
                break;
            case 2:
                tvPearl1.setText("1");
                tvPearl2.setText("2");
                tvPearl1Odds.setText("-");
                tvPearl2Odds.setText(pearlBall.getFactor());
                odds = "2#" + pearlBall.getFactor();
                break;
            case 3:
                tvPearl1.setText("1");
                tvPearl2.setText("2");
                tvPearl3.setText("3");
                tvPearl1Odds.setText("-");
                tvPearl2Odds.setText(pearlBall.getFactor());
                tvPearl3Odds.setText(pearlBall.getFactor2());
                odds = "2#" + pearlBall.getFactor() + "|3#" + pearlBall.getFactor2();
                break;
            case 4:
                tvPearl1.setText("1");
                tvPearl2.setText("2");
                tvPearl3.setText("3");
                tvPearl4.setText("4");
                tvPearl1Odds.setText("-");
                tvPearl2Odds.setText(pearlBall.getFactor());
                tvPearl3Odds.setText(pearlBall.getFactor2());
                tvPearl4Odds.setText(pearlBall.getFactor3());
                odds = "2#" + pearlBall.getFactor() + "|3#" + pearlBall.getFactor2() + "|4#" + pearlBall.getFactor3();
                break;
            case 5:
                tvPearl1.setText("1");
                tvPearl2.setText("2");
                tvPearl3.setText("3");
                tvPearl4.setText("4");
                tvPearl5.setText("5");
                tvPearl1Odds.setText("-");
                tvPearl2Odds.setText("-");
                tvPearl3Odds.setText(pearlBall.getFactor());
                tvPearl4Odds.setText(pearlBall.getFactor2());
                tvPearl5Odds.setText(pearlBall.getFactor3());
                odds = "3#" + pearlBall.getFactor() + "|4#" + pearlBall.getFactor2() + "|5#" + pearlBall.getFactor3();
                break;
        }
        tvBetPopTitle.setText(getString(R.string.zhuzai));
        tvBetPopCountry.setText(selectedStateBean.getGame_name() + "_" + selectedStateBean.getPeriod());
        StringBuilder builder = new StringBuilder();
        for (String s : numberList) {
            builder.append("#");
            builder.append(s);
        }
        tvBetPopNumber.setText(builder.toString());
        tvBetPopOdds.setText(odds);
        btnBetPopSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBet(pearlBall);
            }
        });
        btnBetPopCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KenoDetailsFragment.this.closeBetNumber();
            }
        });
        edtBetPopAmount.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(mContext.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    goBet(pearlBall);
                    return true;
                }
                return false;
            }

        });
        edtBetPopAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty())
                    return;
                double d = DecimalUtils.mul(Double.valueOf(s.toString()), 1 - Double.valueOf(pearlBall.getDiscount()) / 100);
                tvBetPopActualAmount.setText(DecimalUtils.decimalFormat(d, "0.000"));
            }
        });
    }

    private void closeBetNumber() {
        popParent.setVisibility(View.GONE);
        if (numberList.size() > 0)
            numberList.clear();
        edtBetPopAmount.setText("");
        tvBetPopActualAmount.setText("");

        tvPearl1.setText("");
        tvPearl2.setText("");
        tvPearl3.setText("");
        tvPearl4.setText("");
        tvPearl5.setText("");
        tvPearl1Odds.setText("");
        tvPearl2Odds.setText("");
        tvPearl3Odds.setText("");
        tvPearl4Odds.setText("");
        tvPearl5Odds.setText("");
    }

    protected void goBet(final DigGameOddsBean pearlBall) {

        final String s = edtBetPopAmount.getText().toString().trim();
        if (!StringUtils.isNull(mContext, s, mContext.getString(R.string.Input_the_amount_of_bets_please))) {
            if (!pearlBall.getMax_bet().equals("") && !pearlBall.getMax_bet().equals("0") && !pearlBall.getMin_bet().equals("")) {
                double count = Double.valueOf(s);
                double max = Double.valueOf(pearlBall.getMax_bet());
                double min = Double.valueOf(pearlBall.getMin_bet());
                if (count > max) {
                    Toast.makeText(mContext, mContext.getString(R.string.nominal_betting_limit_exceeded_max) + "[" + min + "-" + max + "]", Toast.LENGTH_SHORT).show();
                    edtBetPopAmount.setText("");
                    return;
                }
                if (count < min) {
                    Toast.makeText(mContext, mContext.getString(R.string.nominal_betting_limit_exceeded_min) + "[" + min + "-" + max + "]", Toast.LENGTH_SHORT).show();
                    edtBetPopAmount.setText("");
                    return;
                }

            }
            NyVolleyJsonThreadHandler<LotteryBetResultBean> betThead = new NyVolleyJsonThreadHandler<LotteryBetResultBean>(mContext) {
                @Override
                protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                    params.put("web_id", WebSiteUrl.WebId);
                    params.put("user_id", ((BaseActivity) mContext).getUserInfoBean().getUser_id());
                    params.put("session_id", ((BaseActivity) mContext).getUserInfoBean().getSession_id());
                    params.put("from", "App");//13#Big#10
                    StringBuilder builder = new StringBuilder();
                    builder.append("zhuzai");
                    builder.append(numberList.size());
                    for (String s : numberList) {
                        builder.append("_");
                        builder.append(s);
                    }
                    params.put("get_bet", selectedStateBean.getType2() + "#" + builder.toString() + "#" + s);
                    return new QuickRequestBean(WebSiteUrl.KenoSubmitter, params
                            , new TypeToken<NyBaseResponse<LotteryBetResultBean>>() {
                    }.getType());
                }

                @Override
                protected void successEndT(int total, LotteryBetResultBean data) {

                    Toast.makeText(mContext, R.string.xiazhusuccess, Toast.LENGTH_SHORT).show();
                    closeBetNumber();
                    canBet = true;
                    dismissBlockDialog();
                }

                @Override
                public void errorEnd(String obj) {
                    super.errorEnd(obj);
                    Toast.makeText(mContext, R.string.xiazhucuowu, Toast.LENGTH_SHORT).show();

                    canBet = true;
                    dismissBlockDialog();
                }
            };
            if (canBet) {
                ((BaseActivity) mContext).showBlockDialog();
                betThead.startThread(null);
                canBet = false;
            }
        }
    }


    @Override
    public void updateOddsUI() {
        super.updateOddsUI();

        if (getAct() == null || getAct().isFinishing())
            return;
        tvKenoUpOdds.setText(getAct().getUpDownBean().getFactor());
        tvKenoDownOdds.setText(getAct().getUpDownBean().getFactor());
        tvKenoUpDownTipOdds.setText(getAct().getUpDownBean().getFactor3());
        tvKenoOddsOdds.setText(getAct().getEvensOddsBean().getFactor());
        tvKenoEvensOdds.setText(getAct().getEvensOddsBean().getFactor());
        tvKenoOddsEvensTipOdds.setText(getAct().getEvensOddsBean().getFactor3());
    }

    @Override
    public void updateResultUI() {
        super.updateResultUI();
        currentIndex=0;
        updateHeader();
    }

    public void clickUp(View view) {
        if (selectedStateBean == null || upDownBean == null)
            return;
        KenoBetBean betBean = new KenoBetBean(getString(R.string.up_down), selectedStateBean.getType2(), selectedStateBean.getGame_name(), selectedStateBean.getPeriod(), "Up", getString(R.string.up), upDownBean.getMax_bet(), upDownBean.getMin_bet(), upDownBean.getMax_total(), upDownBean.getDiscount(), upDownBean.getFactor());
        showBetPop(view, betBean);

    }

    public void clickDown(View view) {
        if (selectedStateBean == null || upDownBean == null)
            return;
        KenoBetBean betBean = new KenoBetBean(getString(R.string.up_down), selectedStateBean.getType2(), selectedStateBean.getGame_name(), selectedStateBean.getPeriod(), "Down", getString(R.string.down), upDownBean.getMax_bet(), upDownBean.getMin_bet(), upDownBean.getMax_total(), upDownBean.getDiscount(), upDownBean.getFactor());
        showBetPop(view, betBean);
    }

    public void clickOdds(View view) {
        if (selectedStateBean == null || evensOddsBean == null)
            return;
        KenoBetBean betBean = new KenoBetBean(getString(R.string.odds_evens), selectedStateBean.getType2(), selectedStateBean.getGame_name(), selectedStateBean.getPeriod(), "Odds", getString(R.string.ODDS), evensOddsBean.getMax_bet(), evensOddsBean.getMin_bet(), evensOddsBean.getMax_total(), evensOddsBean.getDiscount(), evensOddsBean.getFactor());
        showBetPop(view, betBean);
    }

    public void clickEvens(View view) {
        if (selectedStateBean == null || evensOddsBean == null)
            return;
        KenoBetBean betBean = new KenoBetBean(getString(R.string.odds_evens), selectedStateBean.getType2(), selectedStateBean.getGame_name(), selectedStateBean.getPeriod(), "Evens", getString(R.string.EVENS), evensOddsBean.getMax_bet(), evensOddsBean.getMin_bet(), evensOddsBean.getMax_total(), evensOddsBean.getDiscount(), evensOddsBean.getFactor());
        showBetPop(view, betBean);
    }

    public void clickOddsEvensTie(View view) {
        if (selectedStateBean == null || evensOddsBean == null)
            return;
        KenoBetBean betBean = new KenoBetBean(getString(R.string.odds_evens), selectedStateBean.getType2(), selectedStateBean.getGame_name(), selectedStateBean.getPeriod(), "OddsEvensTie", getString(R.string.tie), evensOddsBean.getMax_bet(), evensOddsBean.getMin_bet(), evensOddsBean.getMax_total(), evensOddsBean.getDiscount(), evensOddsBean.getFactor3());
        popParent.setVisibility(View.GONE);
        showBetPop(view, betBean);
    }

    public void clickUpDownTie(View view) {
        if (selectedStateBean == null || upDownBean == null)
            return;
        KenoBetBean betBean = new KenoBetBean(getString(R.string.up_down), selectedStateBean.getType2(), selectedStateBean.getGame_name(), selectedStateBean.getPeriod(), "UpDownTie", getString(R.string.tie), upDownBean.getMax_bet(), upDownBean.getMin_bet(), upDownBean.getMax_total(), upDownBean.getDiscount(), upDownBean.getFactor3());
        showBetPop(view, betBean);
    }

    public void clickPeriodRight(View view) {
        if (headerList != null && currentIndex > 0) {
            currentIndex--;
            updateHeader();
        }
    }

    protected void convertHeader(ViewHolder helper, String item, int position) {
        TextView tv = helper.retrieveView(R.id.text_tv1);
        tv.setPadding(pid2, pid2, pid2, pid2);
        helper.setBackgroundRes(R.id.text_tv1, R.drawable.rectangle_white_graystroke_radius0);
        helper.setTextColorRes(R.id.text_tv1, R.color.orange_word);

    }

    protected void convertResult(ViewHolder helper, String item, int position) {
        TextView tv = helper.retrieveView(R.id.text_tv1);
        helper.setTextSize(R.id.text_tv1, 12);
        tv.setPadding(pid2, pid2, pid2, pid2);
        helper.setBackgroundRes(R.id.text_tv1, R.drawable.rectangle_white_graystroke_radius0);
        helper.setTextColorRes(R.id.text_tv1, R.color.orange_word);
    }

    private void updateHeader() {
        if (getAct() == null || getAct().isFinishing())
            return;
        resultContent.setData(headerList.get(currentIndex).getResultStr());
        headerContent.setData(headerList.get(currentIndex).getHeaderStr());
        String timeOpen = headerList.get(currentIndex).getBean().getOpen_time();
        String time = timeOpen.substring(timeOpen.length() - 8, timeOpen.length());
        tvKenoTimePeriod.setText("[" + time + "]" + " " + headerList.get(currentIndex).getBean().getPeriod());
        tvKenoPeriodCount.setText(getString(R.string.total) + headerList.get(currentIndex).getTotal());
    }

    public void clickPeriodLeft(View view) {
        if (headerList != null && currentIndex < headerList.size() - 1) {
            currentIndex++;
            updateHeader();
        }
    }

    @Override
    public void clickSmallEven(View view) {
        super.clickSmallEven(view);
        closeBetNumber();
    }

    @Override
    public void clickSmallOdd(View view) {
        super.clickSmallOdd(view);
        closeBetNumber();
    }

    @Override
    public void clickBigEven(View view) {
        super.clickBigEven(view);
        closeBetNumber();
    }

    @Override
    public void clickBigOdd(View view) {
        super.clickBigOdd(view);
        closeBetNumber();
    }

    @Override
    public void clickGold(View view) {
        super.clickGold(view);
        closeBetNumber();
    }

    @Override
    public void clickWood(View view) {
        super.clickWood(view);
        closeBetNumber();
    }

    @Override
    public void clickWater(View view) {
        super.clickWater(view);
        closeBetNumber();
    }

    @Override
    public void clickFire(View view) {
        super.clickFire(view);
        closeBetNumber();
    }

    @Override
    public void clickEarth(View view) {
        super.clickEarth(view);
        closeBetNumber();
    }
    @Override
    protected void changeFragment() {
        closeBetNumber();
        super.changeFragment();

    }
}
