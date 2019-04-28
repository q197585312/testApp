package com.nanyang.app.main.home.sport.main;

import android.util.Log;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.unkonw.testapp.libs.utils.ToastUtils;

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

        OddsClickBean oddsUrlBean = getOddsUrl(oid, type, isHf, odds, sc);
        AfbClickResponseBean betAfbList = ((AfbApplication) AfbApplication.getInstance()).getBetAfbList();
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
                || betAfbList.getList().size() == 0
                || (betAfbList.getList().size() == 1 && (isOneTeamBoolean(item, betAfbList)
                || betAfbList.getList().get(0).getHasPar() == null
                || betAfbList.getList().get(0).getHasPar().equals("0")
                || betAfbList.getList().get(0).getOddsG().equals("50")
                || betAfbList.getList().get(0).getOddsType().startsWith("mm")
                || !hasPar))) {
            betOddsUrl = "BTMD=S&coupon=0&BETID=" + oddsUrlBean.getBETID();
        } else if ((isHf && item.getHasPar_FH() != null && item.getHasPar_FH().equals("0")) || (!isHf && item.getHasPar().equals("0")) || !typeHasPar || !hasPar || getBallG().equals("50")) {
//            getBaseView().onFailed(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.can_not_mixparly));
            ToastUtils.showShort(R.string.can_not_mixparly);
            return new CompositeDisposable();
        } else {
            String ids = "";
            for (AfbClickBetBean afbClickBetBean : betAfbList.getList()) {
                String itemId = afbClickBetBean.getId();
                String typeOdds = afbClickBetBean.getOddsType();
                Log.d("xxx", "点击的Item：" + item.toString());
                if (afbClickBetBean.getLeague().trim().equalsIgnoreCase(item.getModuleTitle().trim()) &&
                        afbClickBetBean.getHome().trim().equalsIgnoreCase(item.getHome().trim()) && afbClickBetBean.getAway().equalsIgnoreCase(item.getAway().trim())) {
                    Log.d("xxx", "hasTeam");
                    continue;
                }
                if (!cn.finalteam.toolsfinal.StringUtils.isEmpty(typeOdds) && !typeOdds.endsWith("_par")) {
                    String replace = itemId.replaceFirst(typeOdds, typeOdds + "_par");
                    itemId = replace;
                }
                ids += itemId + ",";
            }
            betOddsUrl = "BTMD=P&coupon=1&BETID=" + ids + oddsUrlBean.getBETID_PAR();
        }
        return getDisposable(v, isHf, betOddsUrl);
    }

    private boolean isOneTeamBoolean(B item, AfbClickResponseBean betAfbList) {
        boolean onTeam =
                betAfbList.getList().get(0).getLeague().equalsIgnoreCase(item.getModuleTitle()) &&
                        betAfbList.getList().get(0).getHome().equalsIgnoreCase(item.getHome()) &&
                        betAfbList.getList().get(0).getAway().equalsIgnoreCase(item.getAway()) /*&& (betAfbList.getList().get(0).getIsGive() + "").equalsIgnoreCase(item.getIsHomeGive())*/;
        return onTeam;
    }

    protected OddsClickBean getOddsUrl(int socId, String type, boolean isHf, String odds, String sc) {
        return new OddsClickBean(type, getBallG(), socId + "", isHf ? socId + "" : "", sc);
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
