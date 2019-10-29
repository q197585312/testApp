package com.nanyang.app.main.home.sport.main;

import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.BaseToolbarActivity;
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

        OddsClickBean oddsUrlBean = getOddsUrl(oid, type, isHf, odds, sc, item);
        List<OddsClickBean> betAfbList = ((AfbApplication) AfbApplication.getInstance()).getMixBetList();

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
        if (betAfbList == null
                || betAfbList.size() == 0
                || (betAfbList.size() == 1 && (isOneTeamBoolean(item, betAfbList)
                || !hasPar))) {
            if (!StringUtils.isEmpty(oddsUrlBean.getBETID())) {
                betOddsUrl = "BTMD=S&coupon=0&BETID=" + oddsUrlBean.getBETID();
                LogUtil.d("typeHasPar", "typeHasPar:" + typeHasPar + ",hasPar:" + hasPar);
                ((BaseToolbarActivity) getBaseView().getIBaseContext().getBaseActivity()).getApp().setShowBet(true);
                if (hasPar && typeHasPar)
                    ((AfbApplication) AfbApplication.getInstance()).saveCurrentBet(oddsUrlBean);
                ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).updateMixOrderCount();
                return getDisposable(v, isHf, betOddsUrl);
            }
        } else if ((isHf && item.getHasPar_FH() != null && item.getHasPar_FH().equals("0")) || (!isHf && item.getHasPar().equals("0")) || !typeHasPar || !hasPar || getBallG().equals("50")) {
            ToastUtils.showShort(R.string.can_not_mixparly);
        } else {
            String ids = "";
            ((AfbApplication) AfbApplication.getInstance()).saveCurrentBet(oddsUrlBean);
            ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).updateMixOrderCount();
            /*for (AfbClickBetBean afbClickBetBean : betAfbList.getList()) {
                String itemId = afbClickBetBean.getId();
                String typeOdds = afbClickBetBean.getOddsType();
                Log.d("xxx", "点击的Item：" + item.toString());
                if (afbClickBetBean.getLeague().trim().equalsIgnoreCase(item.getModuleTitle().trim()) &&
                        afbClickBetBean.getHome().trim().equalsIgnoreCase(item.getHome().trim()) && afbClickBetBean.getAway().equalsIgnoreCase(item.getAway().trim())) {
                    Log.d("xxx", "hasTeam");
                    continue;
                }
                if (!StringUtils.isEmpty(typeOdds) && !typeOdds.endsWith("_par")) {
                    String replace = itemId.replaceFirst(typeOdds, typeOdds + "_par");
                    itemId = replace;
                }
                ids += itemId + ",";
            }

            betOddsUrl = "BTMD=P&coupon=1&BETID=" + ids + oddsUrlBean.getBETID_PAR();
            ((BaseToolbarActivity) getBaseView().getIBaseContext().getBaseActivity()).getApp().setShowBet(false, true);*/
        }


        return new CompositeDisposable();
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
