package com.nanyang.app.main.home.keno;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.home.keno.bean.KenoBetLimitBean;
import com.nanyang.app.main.home.keno.bean.KenoDataBean;
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
        tv_draw_num.setText(bean.getDraw_value());
        tv_company_name.setText(bean.getCompany_name());
        tv_bet_type.setText(getTypeName());
        tv_bet_odds.setText(getBetOdds());
        tv_min.setText(limitBean.getMinMaxData().get(0).getMinLimit() + "");
        tv_max.setText(limitBean.getMinMaxData().get(0).getMaxLimit() + "");
        tv_max_get.setText(limitBean.getMinMaxData().get(0).getAmtS() + "");
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
                return "big";
            case "2":
                return "small";
            case "3":
                return "odd";
            case "4":
                return "even";
            case "5":
                return "single";
            case "6":
                return "double";
            case "7":
                return "up";
            case "8":
                return "mid";
            case "9":
                return "down";
            case "10":
                return "gold";
            case "11":
                return "wood";
            case "12":
                return "water";
            case "13":
                return "fire";
            case "14":
                return "soil";
            case "15":
                return "tie";
            case "16":
                return "tie";
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
                if (kenoBet != null) {
                    params = "cn=" + bean.getCompany_name();
                    params += "&wd=" + AfbUtils.getCurrentDate("yyyy-MM-dd|HH:mm:ss");
                    params += "&draw=" + bean.getDraw_value();
                    params += "&id=" + bean.getOdds_id();
                    params += "&dt=" + bean.getMatchDate_value();
                    params += "&t=" + type;
                    params += "&b=KEN";
                    params += "&txtAmount=" + edt_amount.getText().toString();
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
