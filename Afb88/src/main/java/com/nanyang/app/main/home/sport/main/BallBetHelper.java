package com.nanyang.app.main.home.sport.main;

import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.List;

import cn.finalteam.toolsfinal.StringUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/3/15.
 */

public abstract class BallBetHelper<B extends BallInfo, V extends BetView> extends SportBetHelper<B, V> {

    protected boolean isHf;
    protected B item;
    protected boolean hasPar;

    public BallBetHelper(V baseView) {
        super(baseView);
    }


    @Override
    public Disposable clickOdds(B item, int oid, String type, String odds, TextView v, boolean isHf, String sc, boolean hasPar) {
        this.hasPar = hasPar;
        this.item = item;
        this.isHf = isHf;
        List<OddsClickBean> mixBetList = ((AfbApplication) AfbApplication.getInstance()).getMixBetList();
        if (mixBetList != null && mixBetList.size() > 9)
            return new CompositeDisposable();

        OddsClickBean oddsUrlBean = getOddsUrl(oid, type, isHf, odds, sc, item);


        String betOddsUrl = "";

        boolean typeHasPar = type.equalsIgnoreCase("over")
                || type.equalsIgnoreCase("under")
                || type.equalsIgnoreCase("even")
                || type.equalsIgnoreCase("odd")
                || type.equalsIgnoreCase("home")
                || type.equalsIgnoreCase("away")
                || type.equalsIgnoreCase("1")
                || type.equalsIgnoreCase("X")
                || type.equalsIgnoreCase("2");
        if (((AfbApplication) AfbApplication.getInstance()).getMixBetList() == null
                || ((AfbApplication) AfbApplication.getInstance()).getMixBetList().size() == 0
                || (((AfbApplication) AfbApplication.getInstance()).getMixBetList().size() == 1 && (isOneTeamBoolean(item, ((AfbApplication) AfbApplication.getInstance()).getMixBetList())
                || !hasPar))) {
            if (!StringUtils.isEmpty(oddsUrlBean.getBETID())) {
                betOddsUrl = AppConstant.getInstance().URL_ODDS + "BTMD=S&coupon=0&BETID=" + oddsUrlBean.getBETID();
                LogUtil.d("typeHasPar", "typeHasPar:" + typeHasPar + ",hasPar:" + hasPar);
                if (hasPar && typeHasPar)
                    ((AfbApplication) AfbApplication.getInstance()).saveCurrentBet(oddsUrlBean);
                ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).updateMixOrderCount();

            }
        } else if ((isHf && item.getHasPar_FH() != null && item.getHasPar_FH().equals("0")) || (!isHf && item.getHasPar().equals("0")) || !typeHasPar || !hasPar || getBallG().equals("50")) {
            ToastUtils.showShort(R.string.can_not_mixparly);
            return new CompositeDisposable();
        } else {
            ((AfbApplication) AfbApplication.getInstance()).saveCurrentBet(oddsUrlBean);
            ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).updateMixOrderCount();
            betOddsUrl = ((AfbApplication) AfbApplication.getInstance()).getRefreshMixOddsUrl();
        }
        return getDisposable(v, isHf, betOddsUrl);

    }

    private boolean isOneTeamBoolean(B item, List<OddsClickBean> betAfbList) {
        boolean onTeam = betAfbList.get(0).getItem().getModuleId() != null &&
                betAfbList.get(0).getItem().getModuleTitle().equalsIgnoreCase(item.getModuleTitle()) &&
                betAfbList.get(0).getItem().getHome().equalsIgnoreCase(item.getHome()) &&
                betAfbList.get(0).getItem().getAway().equalsIgnoreCase(item.getAway()) /*&& (betAfbList.gettList().get(0).getIsGive() + "").equalsIgnoreCase(item.getIsHomeGive())*/;
        return onTeam;
    }

    protected OddsClickBean<B> getOddsUrl(int socId, String type, boolean isHf, String odds, String sc, B item) {
        return new OddsClickBean<>(type, getBallG(), socId + "", isHf ? socId + "" : "", sc, item);
    }

    protected AfbClickResponseBean initHasPar(AfbClickResponseBean bean) {
        if (bean == null) {
            return null;
        }
        if (bean.getList().size() == 1) {
            for (AfbClickBetBean afbClickBetBean : bean.getList()) {
                String strPar = "";
                if (!hasPar) {
                    strPar = "0";
                } else if (isHf && !StringUtils.isEmpty(item.getHasPar_FH())) {
                    strPar = item.getHasPar_FH();
                } else {
                    strPar = item.getHasPar();
                }
                afbClickBetBean.setHasPar(strPar);
            }
        } else {
            for (AfbClickBetBean afbClickBetBean : bean.getList()) {
                String hasPar = "1";
                afbClickBetBean.setHasPar(hasPar);
            }
        }
        return bean;
    }

    protected abstract String getBallG();


}
