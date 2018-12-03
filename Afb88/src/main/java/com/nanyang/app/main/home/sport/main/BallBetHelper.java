package com.nanyang.app.main.home.sport.main;

import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sportInterface.BetView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/3/15.
 */

public abstract class BallBetHelper<B extends BallInfo, V extends BetView> extends SportBetHelper<B, V> {

    public BallBetHelper(V baseView) {
        super(baseView);
    }

    @Override
    public Disposable clickOdds(B item, String type, String odds, final TextView v, final boolean isHf, String sc) {
        OddsClickBean oddsUrlBean = getOddsUrl(item, type, isHf, odds, sc);
        AfbClickResponseBean betAfbList = ((AfbApplication) AfbApplication.getInstance()).getBetAfbList();
        String betOddsUrl = "";
        boolean typeHasPar = type.equalsIgnoreCase("over") || type.equalsIgnoreCase("under") || type.equalsIgnoreCase("even") || type.equalsIgnoreCase("odd") || type.equalsIgnoreCase("home")
                || type.equalsIgnoreCase("away") || type.equalsIgnoreCase("1") || type.equalsIgnoreCase("X") || type.equalsIgnoreCase("2");
        if (betAfbList == null || betAfbList.getList().size() == 0 || (betAfbList.getList().size() == 1 && (betAfbList.getList().get(0).getLeague().equalsIgnoreCase(item.getModuleTitle().toString()) &&
                betAfbList.getList().get(0).getHome().equalsIgnoreCase(item.getHome()) && betAfbList.getList().get(0).getAway().equalsIgnoreCase(item.getAway()) && (betAfbList.getList().get(0).getIsGive() + "").equalsIgnoreCase(item.getIsHomeGive()))
                || betAfbList.getList().get(0).getOddsG().equals("50") || betAfbList.getList().get(0).getOddsType().startsWith("mm"))) {
            betOddsUrl = "BTMD=S&coupon=0&BETID=" + oddsUrlBean.getBETID();
        } else if ((isHf && item.getHasPar_FH() != null && item.getHasPar_FH().equals("0")) || (!isHf && item.getHasPar().equals("0")) || !typeHasPar) {
            getBaseView().onFailed(getBaseView().getContextActivity().getString(R.string.can_not_mixparly));
            return new CompositeDisposable();
        } else {
            String ids = "";
            for (AfbClickBetBean afbClickBetBean : betAfbList.getList()) {
                String itemId = afbClickBetBean.getId();
                String typeOdds = afbClickBetBean.getOddsType();

                if (afbClickBetBean.getLeague().trim().equalsIgnoreCase(item.getModuleTitle().toString().trim()) &&
                        afbClickBetBean.getHome().trim().equalsIgnoreCase(item.getHome().trim()) && afbClickBetBean.getAway().equalsIgnoreCase(item.getAway().trim()) && (afbClickBetBean.getIsGive() + "").equalsIgnoreCase(item.getIsHomeGive())) {
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
        //http://www.afb1188.com/Bet/hBetOdds.ashx?BTMD=S&coupon=0&BETID=s|home|1|469195||&_=1543457322841
        return getDisposable(v, isHf, betOddsUrl);
    }


    protected OddsClickBean getOddsUrl(B item, String type, boolean isHf, String odds, String sc) {
        return new OddsClickBean(type, getBallG(), isHf ? item.getSocOddsId_FH() : item.getSocOddsId(), isHf ? item.getSocOddsId_FH() : "", sc);
    }

    protected abstract String getBallG();

}
