package com.nanyang.app.main.home.keno;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.home.keno.bean.KenoBetLimitBean;
import com.nanyang.app.main.home.keno.bean.KenoDataBean;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

/**
 * Created by Administrator on 2017/11/24.
 */

public class KenoBetPopu extends BasePopupWindow {
    private KenoDataBean.PublicDataBean.CompanyDataBean bean;
    private String type;
    private KenoBetLimitBean limitBean;
    private KenoBet kenoBet;
    private String params;

    public KenoBetPopu(Context context, View v, int width, int height,
                       KenoDataBean.PublicDataBean.CompanyDataBean bean, String type, KenoBetLimitBean limitBean) {
        super(context, v, width, height);
        this.bean = bean;
        this.type = type;
        this.limitBean = limitBean;
        initUi();
    }

    public void setKenoBet(KenoBet kenoBet) {
        this.kenoBet = kenoBet;
    }

    private void initUi() {
        tv_time.setText(bean.getMatchDate_value());
        tv_draw_num.setText(bean.getDraw_value() + context.getString(R.string.keno));
        tv_company_name.setText(bean.getCompany_name());
        tv_bet_type.setText(getTypeName());
        tv_bet_odds.setText(getBetOdds());
        tv_min.setText(limitBean.getMinMaxData().get(0).getMinLimit() + "");
        tv_max.setText(limitBean.getMinMaxData().get(0).getMaxLimit() + "");
        if (limitBean.getMinMaxData().get(0).getAmtS().equals("0")) {
            tv_max_get.setText("");
        }
    }

    private String getBetOdds() {
        for (int i = 0; i < bean.getBet_id().size(); i++) {
            if (bean.getBet_id().get(i).getId().equals(type)) {
                return bean.getBet_id().get(i).getValue();
            }
        }
        return "";
    }

    private String getTypeName() {
        switch (type) {
            case "1":
                return context.getString(R.string.big_keno);
            case "2":
                return context.getString(R.string.small_keno);
            case "3":
                return context.getString(R.string.odd_keno);
            case "4":
                return context.getString(R.string.even_keno);
            case "5":
                return context.getString(R.string.single_keno);
            case "6":
                return context.getString(R.string.double_keno);
            case "7":
                return context.getString(R.string.up_keno);
            case "8":
                return context.getString(R.string.mid_keno);
            case "9":
                return context.getString(R.string.down_keno);
            case "10":
                return context.getString(R.string.gold);
            case "11":
                return context.getString(R.string.wood);
            case "12":
                return context.getString(R.string.water);
            case "13":
                return context.getString(R.string.fire);
            case "14":
                return context.getString(R.string.earth);
            case "15":
                return context.getString(R.string.tie_up_keno);
            case "16":
                return context.getString(R.string.tie_bottom_keno);
        }
        return "";
    }

    private TextView tv_time;
    private TextView tv_draw_num;
    private TextView tv_company_name;
    private TextView tv_bet_type;
    private TextView tv_bet_odds;
    private TextView tv_max_get;
    private EditText edt_amount;
    private TextView tv_min;
    private TextView tv_max;
    private TextView bet_sure_btn;

    @Override
    protected void initView(View view) {
        super.initView(view);
        tv_time = (TextView) view.findViewById(R.id.tv_time);
        tv_draw_num = (TextView) view.findViewById(R.id.tv_draw_num);
        tv_company_name = (TextView) view.findViewById(R.id.tv_company_name);
        tv_bet_type = (TextView) view.findViewById(R.id.tv_bet_type);
        tv_bet_odds = (TextView) view.findViewById(R.id.tv_bet_odds);
        tv_max_get = (TextView) view.findViewById(R.id.tv_max_get);
        edt_amount = (EditText) view.findViewById(R.id.edt_amount);
        tv_min = (TextView) view.findViewById(R.id.tv_min);
        tv_max = (TextView) view.findViewById(R.id.tv_max);
        bet_sure_btn = (TextView) view.findViewById(R.id.bet_sure_btn);
        bet_sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String money = edt_amount.getText().toString();
                if (TextUtils.isEmpty(money)) {
                    ToastUtils.showShort(context.getString(R.string.Input_the_amount_of_bets_please));
                    return;
                }
                if (Integer.parseInt(money) < limitBean.getMinMaxData().get(0).getMinLimit() ||
                        Integer.parseInt(money) > Integer.parseInt(limitBean.getMinMaxData().get(0).getMaxLimit())) {
                    ToastUtils.showShort(context.getString(R.string.invalid_amount_bet));
                    return;
                }
                if (kenoBet != null) {
                    params = "cn=" + bean.getCompany_name();
                    params += "&wd=" + AfbUtils.getCurrentDate("yyyy-MM-dd|HH:mm:ss");
                    params += "&draw=" + bean.getDraw_value();
                    params += "&id=" + bean.getOdds_id();
                    params += "&dt=" + bean.getMatchDate_value();
                    params += "&t=" + type;
                    params += "&b=KEN";
                    params += "&txtAmount=" + money;
                    for (int i = 0; i < bean.getBet_id().size(); i++) {
                        if (bean.getBet_id().get(i).getId().equals(type)) {
                            params += "&v=" + bean.getBet_id().get(i).getValue();
                            break;
                        }
                    }
                    kenoBet.onKenoBetListener(params);
                }
            }
        });
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.popupwindow_keno_betting;
    }

    interface KenoBet {
        void onKenoBetListener(String params);
    }
}
