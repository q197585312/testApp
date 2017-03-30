package com.nanyang.app.main.center.transferMoney;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.center.model.TransferMoneyBean;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/30.
 */

public class TransferMoneyFragment extends BaseFragment<TransferMoneyContact.Presenter> implements TransferMoneyContact.View {
    @Bind(R.id.tv_login_name)
    TextView tv_login_name;
    @Bind(R.id.tv_currency)
    TextView tv_currency;
    @Bind(R.id.tv_cash_balance)
    TextView tv_cash_balance;
    @Bind(R.id.tv_outstanding_txn)
    TextView tv_outstanding_txn;
    @Bind(R.id.tv_given_credit)
    TextView tv_given_credit;
    @Bind(R.id.tv_bet_credit)
    TextView tv_bet_credit;
    @Bind(R.id.tv_min_bet)
    TextView tv_min_bet;
    @Bind(R.id.tv_total_balance)
    TextView tv_total_balance;
    @Bind(R.id.tv_sportbook_balance)
    TextView tv_sportbook_balance;
    @Bind(R.id.tv_Egames_balance)
    TextView tv_Egames_balance;
    @Bind(R.id.tv_GD_casino_balance)
    TextView tv_GD_casino_balance;
    @Bind(R.id.tv_855_casino_balance)
    TextView tv_855_casino_balance;
    @Bind(R.id.tv_W88_casino_balance)
    TextView tv_W88_casino_balance;
    @Bind(R.id.edt_Egames)
    EditText edt_Egames;
    @Bind(R.id.tv_Egames_transfer)
    TextView tv_Egames_transfer;
    @Bind(R.id.tv_Egaames_cashout)
    TextView tv_Egaames_cashout;
    @Bind(R.id.edt_GD_casino)
    EditText edt_GD_casino;
    @Bind(R.id.tv_GD_casino_transfer)
    TextView tv_GD_casino_transfer;
    @Bind(R.id.tv_GD_casino_cashout)
    TextView tv_GD_casino_cashout;
    @Bind(R.id.edt_855_casino)
    EditText edt_855_casino;
    @Bind(R.id.tv_855_casino_transfer)
    TextView tv_855_casino_transfer;
    @Bind(R.id.tv_855_casino_cashout)
    TextView tv_855_casino_cashout;
    @Bind(R.id.edt_W88_casino)
    EditText edt_W88_casino;
    @Bind(R.id.tv_W88_casino_transfer)
    TextView tv_W88_casino_transfer;
    @Bind(R.id.tv_W88_casino_cashout)
    TextView tv_W88_casino_cashout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter(new TransferMoneyPresenter(this));
    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_transfer;
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getTransferMoneyData();
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void onGetData(TransferMoneyBean data) {
        TransferMoneyBean.DicAllBean bean = data.getDicAll().get(0);
        tv_login_name.setText(isStartWithTag(bean.getLoginName()));
        tv_currency.setText(isStartWithTag(bean.getCurrency()));
        tv_cash_balance.setText(isStartWithTag(bean.getCashBalance()));
        tv_outstanding_txn.setText(isStartWithTag(bean.getOutStanding()));
        tv_given_credit.setText(isStartWithTag(bean.getTotalCredit()));
        tv_bet_credit.setText(isStartWithTag(bean.getCredit()));
        tv_min_bet.setText(isStartWithTag(bean.getMinLimit()));
        tv_total_balance.setText(isStartWithTag(bean.getTotalBalance()));
        tv_sportbook_balance.setText(isStartWithTag(bean.getTotalSportBookBalance()));
        tv_Egames_balance.setText(isStartWithTag(bean.getEBalance()));
        tv_GD_casino_balance.setText(isStartWithTag(bean.getGdBalance()));
        tv_855_casino_balance.setText(isStartWithTag(bean.get_$855Balance()));
        tv_W88_casino_balance.setText(isStartWithTag(bean.getW88Balance()));
    }

    @OnClick({R.id.tv_Egaames_cashout, R.id.tv_GD_casino_cashout, R.id.tv_855_casino_cashout, R.id.tv_W88_casino_cashout,
            R.id.tv_Egames_transfer, R.id.tv_GD_casino_transfer, R.id.tv_855_casino_transfer, R.id.tv_W88_casino_transfer})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_Egaames_cashout:
                String eBalance = tv_Egames_balance.getText().toString();
                if (!eBalance.equals("0.00")) {
                    presenter.gamesECashOutMonet(eBalance);
                } else {
                    ToastUtils.showShort(getString(R.string.money_not_enough));
                }
                break;
            case R.id.tv_GD_casino_cashout:
                String gdBlance = tv_GD_casino_balance.getText().toString();
                if (!gdBlance.equals("0.00")) {
                    presenter.gamesGDCashOutMonet(gdBlance);
                } else {
                    ToastUtils.showShort(getString(R.string.money_not_enough));
                }
                break;
            case R.id.tv_855_casino_cashout:
                String balance855 = tv_855_casino_balance.getText().toString();
                if (!balance855.equals("0.00")) {
                    presenter.games855CashOutMonet(balance855);
                } else {
                    ToastUtils.showShort(getString(R.string.money_not_enough));
                }
                break;
            case R.id.tv_W88_casino_cashout:
                String w88Blance = tv_W88_casino_balance.getText().toString();
                if (!w88Blance.equals("0.00")) {
                    presenter.gamesW88CashOutMonet(w88Blance);
                } else {
                    ToastUtils.showShort(getString(R.string.money_not_enough));
                }
                break;
            case R.id.tv_Egames_transfer:
                String eMoney = edt_Egames.getText().toString();
                if (!TextUtils.isEmpty(eMoney)) {
                    presenter.gamesETransferMonet(eMoney);
                } else {
                    ToastUtils.showShort(getString(R.string.Input_the_amount_please));
                }
                break;
            case R.id.tv_GD_casino_transfer:
                String gdMoney = edt_GD_casino.getText().toString();
                if (!TextUtils.isEmpty(gdMoney)) {
                    presenter.gamesGDTransferMonet(gdMoney);
                } else {
                    ToastUtils.showShort(getString(R.string.Input_the_amount_please));
                }
                break;
            case R.id.tv_855_casino_transfer:
                String money855 = edt_855_casino.getText().toString();
                if (!TextUtils.isEmpty(money855)) {
                    presenter.games855TransferMonet(money855);
                } else {
                    ToastUtils.showShort(getString(R.string.Input_the_amount_please));
                }
                break;
            case R.id.tv_W88_casino_transfer:
                String w88Money = edt_W88_casino.getText().toString();
                if (!TextUtils.isEmpty(w88Money)) {
                    presenter.gamesW88TransferMonet(w88Money);
                } else {
                    ToastUtils.showShort(getString(R.string.Input_the_amount_please));
                }
                break;
        }
    }

    @Override
    public void onFailed(String error) {

    }

    private SpannableStringBuilder isStartWithTag(String str) {
        if (str.startsWith("<SPAN")) {
            String needStr = Html.fromHtml(str).toString();
            if (needStr.startsWith("-")) {
                return AfbUtils.handleStringTextColor(needStr, Color.RED);
            }
            return new SpannableStringBuilder(needStr);
        } else {
            if (str.startsWith("-")) {
                return AfbUtils.handleStringTextColor(str, Color.RED);
            } else {
                return new SpannableStringBuilder(str);
            }
        }
    }

    @Override
    public void onGetTransferMoneyData(String s) {
        ToastUtils.showShort(s);
        presenter.getTransferMoneyData();
    }

    @Override
    public void onGetCashoutMoneyData(String s) {
        ToastUtils.showShort(s);
        presenter.getTransferMoneyData();
    }

    @Override
    public void refreshEdt(int type) {
        switch (type) {
            case 1:
                edt_Egames.setText("");
                break;
            case 2:
                edt_GD_casino.setText("");
                break;
            case 3:
                edt_855_casino.setText("");
                break;
            case 4:
                edt_W88_casino.setText("");
                break;
        }
    }
}
