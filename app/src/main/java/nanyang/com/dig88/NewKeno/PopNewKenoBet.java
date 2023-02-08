package nanyang.com.dig88.NewKeno;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.DecimalUtils;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.OkhttpUtils;
import nanyang.com.dig88.Util.WebSiteUrl;
import okhttp3.FormBody;

/**
 * Created by Administrator on 2018/9/18.
 */

public abstract class PopNewKenoBet extends BasePopupWindow {
    NewKenoActivity activity;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_bet_gid)
    TextView tv_bet_gid;
    @BindView(R.id.edt_bet_amount)
    EditText edt_bet_amount;
    @BindView(R.id.tv_max_payout)
    TextView tv_max_payout;
    @BindView(R.id.tv_bet_limit)
    TextView tv_bet_limit;
    Gson gson;
    String betUrl = "";
    String betAmount;
    FormBody.Builder paramBuilder;
    String getBetParam;
    double factor;
    double min;
    double max;

    public PopNewKenoBet(Context context, View v, int width, int height) {
        super(context, v, width, height);
        activity = (NewKenoActivity) context;
        betUrl = activity.getBaseUrl() + "api.php?page=keno_new_submitter";
        gson = new Gson();
        edt_bet_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString().trim();
                if (!TextUtils.isEmpty(str)) {
                    double doubleStr = Double.parseDouble(str) * factor;
                    tv_max_payout.setText(DecimalUtils.decimalFormat(doubleStr, "0.00"));
                } else {
                    tv_max_payout.setText("0.00");
                }
            }
        });
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_new_keno_bet;
    }

    @OnClick({R.id.tv_submit, R.id.tv_cancel, R.id.tv_reset, R.id.tv_bet_1, R.id.tv_bet_5, R.id.tv_bet_10, R.id.tv_bet_50,
            R.id.tv_bet_100, R.id.tv_bet_500, R.id.tv_bet_1000})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
                submit();
                break;
            case R.id.tv_cancel:
                closePopupWindow();
                break;
            case R.id.tv_reset:
                clear();
                break;
            case R.id.tv_bet_1:
                setBetAmount(1);
                break;
            case R.id.tv_bet_5:
                setBetAmount(5);
                break;
            case R.id.tv_bet_10:
                setBetAmount(10);
                break;
            case R.id.tv_bet_50:
                setBetAmount(50);
                break;
            case R.id.tv_bet_100:
                setBetAmount(100);
                break;
            case R.id.tv_bet_500:
                setBetAmount(500);
                break;
            case R.id.tv_bet_1000:
                setBetAmount(1000);
                break;
        }
    }

    private void setBetAmount(int amount) {
        String betAmount = edt_bet_amount.getText().toString().trim();
        if (TextUtils.isEmpty(betAmount)) {
            betAmount = "0";
        }
        int currentBetAmount = Integer.parseInt(betAmount);
        currentBetAmount += amount;
        String totalBetAmount = Integer.valueOf(currentBetAmount).toString();
        edt_bet_amount.setText(totalBetAmount);
        edt_bet_amount.setSelection(totalBetAmount.length());
    }

    private void submit() {
        betAmount = edt_bet_amount.getText().toString().trim();
        if (TextUtils.isEmpty(betAmount)) {
            Toast.makeText(context, context.getString(R.string.shurujine), Toast.LENGTH_SHORT).show();
            return;
        }
        paramBuilder.add("get_bet", getBetParam + betAmount);
        double money = Double.parseDouble(betAmount);
        if (money > max) {
            Toast.makeText(context, context.getString(R.string.xiazhuzuida) + max, Toast.LENGTH_SHORT).show();
        } else if (money < min) {
            Toast.makeText(context, context.getString(R.string.xiazhuzuixiao) + min, Toast.LENGTH_SHORT).show();
        } else {
            activity.showBlockDialog();
            OkhttpUtils.postRequest(betUrl, paramBuilder.build(), new OkhttpUtils.Result() {
                @Override
                public void onSuccess(String result) {
                    NewKenoBetResultBean newKenoBetResultBean = gson.fromJson(result, NewKenoBetResultBean.class);
                    betResult(newKenoBetResultBean);
                }

                @Override
                public void onFailed(String result) {
                    Toast.makeText(context, context.getString(R.string.Failed), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(context, "loginInfo");
        tv_username.setText(s.getUsername());
    }

    public void setData(String factor1, String betType, String playType, String betGid, NewKenoGameSetBean.DataBean dataBean, int position,String rule) {
        factor = Double.parseDouble(factor1);
        min = Double.parseDouble(dataBean.getMin_bet());
        max = Double.parseDouble(dataBean.getMax_bet());
        paramBuilder = new FormBody.Builder();
        paramBuilder.add("web_id", WebSiteUrl.WebId);
        paramBuilder.add("username", activity.getUsername());
        paramBuilder.add("from", "Android");
        paramBuilder.add("rule", rule);
        tv_bet_gid.setText(betGid);
        tv_bet_limit.setText(min + "-" + max);
        if (betType.equals("Frequence")) {
            int frequenceBetindex = (position / 4) + 1;
            getBetParam = betType + "^" + frequenceBetindex + "#";
            int i = position % 4;
            switch (i) {
                case 0:
                    playType = "1";
                    break;
                case 1:
                    playType = "2-3";
                    break;
                case 2:
                    playType = "4+";
                    break;
                case 3:
                    playType = "0";
                    break;
            }
            getBetParam += playType + "^";
        } else {
            getBetParam = betType + "^" + playType + "^";
        }
    }

    public abstract void betResult(NewKenoBetResultBean newKenoBetResultBean);

    @Override
    protected void onCloose() {
        super.onCloose();
        clear();
    }

    public void clear() {
        edt_bet_amount.setText("");
        tv_max_payout.setText("0.00");
    }
}
