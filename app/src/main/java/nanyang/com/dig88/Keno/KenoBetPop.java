package nanyang.com.dig88.Keno;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import butterknife.Bind;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Lottery.LotteryBetResultBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.DecimalUtils;
import xs.com.mylibrary.allinone.util.StringUtils;
import xs.com.mylibrary.base.quick.QuickRequestBean;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2016/6/16.
 */
public class KenoBetPop extends BasePopupWindow {
    @Bind(R.id.tv_bet_pop_title)
    TextView tvBetPopTitle;
    @Bind(R.id.tv_bet_pop_country)
    TextView tvBetPopCountry;
    @Bind(R.id.ll_country)
    LinearLayout llCountry;
    @Bind(R.id.tv_bet_pop_number)
    TextView tvBetPopNumber;
    @Bind(R.id.tv_bet_pop_odds)
    TextView tvBetPopOdds;
    @Bind(R.id.edt_bet_pop_amount)
    EditText edtBetPopAmount;
    @Bind(R.id.tv_bet_pop_actual_amount)
    TextView tvBetPopActualAmount;
    @Bind(R.id.btn_bet_pop_sure)
    Button btnBetPopSure;
    @Bind(R.id.btn_bet_pop_cancel)
    Button btnBetPopCancel;
    @Bind(R.id.iv_hide)
    ImageView ivHide;
    KenoBetBean betBean;
    private boolean canBet = true;

    public KenoBetPop(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    public KenoBetBean getBetBean() {
        return betBean;
    }

    public void setBetBean(final KenoBetBean betBean) {
        this.betBean = betBean;
        if (betBean != null) {
            ivHide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopupWindow();
                }
            });
            tvBetPopTitle.setText(betBean.getGameTitle());
            tvBetPopCountry.setText(betBean.getAreaName() + "_" + betBean.getPeriod());
            tvBetPopNumber.setText(betBean.getTypeName());
            tvBetPopOdds.setText(betBean.getFactor());
            btnBetPopSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goBet();
                }
            });
            btnBetPopCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KenoBetPop.this.closePopupWindow();
                }
            });
            edtBetPopAmount.setOnEditorActionListener(new EditText.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        goBet();
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
                    if(s.toString().isEmpty()) {
                        tvBetPopActualAmount.setText("");
                        return;
                    }
                    double d = DecimalUtils.mul(Double.valueOf(s.toString()), 1 - Double.valueOf(betBean.getDiscount()) / 100);
                    tvBetPopActualAmount.setText(DecimalUtils.decimalFormat(d, "0.00"));
                }
            });
        }
    }

    @Override
    protected void onCloose() {
        super.onCloose();
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.betting_dialog;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        llCountry.setVisibility(View.VISIBLE);
    }

    protected void goBet() {
        final String s = edtBetPopAmount.getText().toString().trim();
        if (!StringUtils.isNull(context, s, context.getString(R.string.Input_the_amount_of_bets_please))) {
            if (!betBean.getMaxLimit().equals("") && !betBean.getMaxLimit().equals("0") && !betBean.getMinLimit().equals("")) {
                double count = Double.valueOf(s);
                double max = Double.valueOf(betBean.getMaxLimit());
                double min = Double.valueOf(betBean.getMinLimit());
                if (count > max ) {
                    Toast.makeText(context, context.getString(R.string.nominal_betting_limit_exceeded_max)+"["+min+"-"+max+"]", Toast.LENGTH_SHORT).show();
                    edtBetPopAmount.setText("");
                    return;
                }if( count < min){
                    Toast.makeText(context, context.getString(R.string.nominal_betting_limit_exceeded_min)+"["+min+"-"+max+"]", Toast.LENGTH_SHORT).show();
                    edtBetPopAmount.setText("");
                    return;
                }

            }
            NyVolleyJsonThreadHandler<LotteryBetResultBean> betThead = new NyVolleyJsonThreadHandler<LotteryBetResultBean>(context) {
                @Override
                protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                    params.put("web_id", WebSiteUrl.WebId);
                    params.put("user_id", ((BaseActivity) context).getUserInfoBean().getUser_id());
                    params.put("session_id", ((BaseActivity) context).getUserInfoBean().getSession_id());
                    params.put("from", "App");//13#Big#10
                    params.put("get_bet", betBean.getType2() + "#" + betBean.getTypeKey() + "#" + s);
                    return new QuickRequestBean(WebSiteUrl.KenoSubmitter, params
                            , new TypeToken<NyBaseResponse<LotteryBetResultBean>>() {
                    }.getType());
                }

                @Override
                protected void successEndT(int total, LotteryBetResultBean data) {
                    ((BaseActivity) context).dismissBlockDialog();
                    Toast.makeText(context, R.string.xiazhusuccess, Toast.LENGTH_SHORT).show();
                    KenoBetPop.this.closePopupWindow();

                    canBet = true;
                }

                @Override
                public void errorEnd(String obj) {
                    super.errorEnd(obj);
                    Toast.makeText(context, R.string.xiazhucuowu, Toast.LENGTH_SHORT).show();
                    ((BaseActivity) context).dismissBlockDialog();
                    canBet = true;
                }
            };
            if (canBet) {
                ((BaseActivity) context).showBlockDialog();
                betThead.startThread(null);
                canBet = false;
            }
        }
    }
}
