package com.nanyang.app.main.home.sport.main;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.betOrder.IBetOrderView;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.List;

import cn.finalteam.toolsfinal.StringUtils;

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

    OddsClickBean oddsUrlBean;
    String betOddsUrl = "";
    @Override
    public void clickOdds(B item, int oid, String type, String odds, final TextView v, final boolean isHf, String sc, final boolean hasPar) {
        final Handler handler = new Handler();
        BaseActivity baseActivity = baseView.getIBaseContext().getBaseActivity();
        if (baseActivity instanceof IBetOrderView) {
            if (((IBetOrderView) baseActivity).getBetContent().v.getVisibility() == View.VISIBLE)
                ((IBetOrderView) baseActivity).getBetContent().stopUpdateOdds();
        }

        this.hasPar = hasPar;
        this.item = item;
        this.isHf = isHf;

        oddsUrlBean = getOddsUrl(oid, type, isHf, odds, sc, item);


        final boolean typeHasPar = type.equalsIgnoreCase("over")
                || type.equalsIgnoreCase("under")
                || type.equalsIgnoreCase("even")
                || type.equalsIgnoreCase("odd")
                || type.equalsIgnoreCase("home")
                || type.equalsIgnoreCase("away")
                || type.equalsIgnoreCase("1")
                || type.equalsIgnoreCase("X")
                || type.equalsIgnoreCase("2");
        final AfbApplication app = (AfbApplication) AfbApplication.getInstance();
        if (app.getMixBetList().size() < 1) {
            app.isSingleBet = true;
        }
        if (app.isSingleBet) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!StringUtils.isEmpty(oddsUrlBean.getBETID())) {
                        LogUtil.d("typeHasPar", "typeHasPar:" + typeHasPar + ",hasPar:" + hasPar);
                        if (hasPar && typeHasPar) {
                            saveCurrentMixBet(oddsUrlBean);
                        } else {
                            onChangeSuccess(app.removeSameMix(oddsUrlBean));
                        }
                        saveCurrentSingleBet(oddsUrlBean);
                        betOrderDelay(v, isHf, handler, app);
                        return;
                    }

                }
            }, app.getDelayBet());
        } else if ((isHf && item.getHasPar_FH() != null && item.getHasPar_FH().equals("0")) || (!isHf && item.getHasPar().equals("0")) || !typeHasPar || !hasPar || getBallG().equals("50")) {
            ToastUtils.showShort(R.string.can_not_mixparly);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    saveCurrentMixBet(oddsUrlBean);
                    betOddsUrl = app.getRefreshMixOddsUrl();
                    if (app.getMixBetList().size() == 1 || app.getMixBetList().size() > 14) {
                        getDisposable(v, isHf, betOddsUrl);
                        return;
                    }
                }
            }, app.getDelayBet());

        }

        return;
    }

    private void betOrderDelay(final TextView v, final boolean isHf, Handler handler, final AfbApplication app) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getDisposable(v, isHf, app.getRefreshSingleOddsUrl());
            }
        }, app.getDelayBet());
    }

    private void saveCurrentSingleBet(OddsClickBean oddsUrlBean) {
        ((AfbApplication) AfbApplication.getInstance()).saveSingleBet(oddsUrlBean);

    }

    private void saveCurrentMixBet(OddsClickBean oddsUrlBean) {
        boolean listHasChanged = ((AfbApplication) AfbApplication.getInstance()).saveCurrentBet(oddsUrlBean);
        onChangeSuccess(listHasChanged);
    }

    public void onChangeSuccess(boolean listHasChanged) {
        if (listHasChanged) {
            BaseActivity baseActivity = getBaseView().getIBaseContext().getBaseActivity();
            if (baseActivity != null && baseActivity instanceof SportActivity) {
                ((SportActivity) baseActivity).updateMixOrder();
            }
        }
    }

    private boolean isOneTeamBoolean(B item, List<OddsClickBean> betAfbList) {
        boolean onTeam = betAfbList.get(0).getItem().getMainModuleId() != null &&
                betAfbList.get(0).getItem().getMainModuleId().equalsIgnoreCase(item.getMainModuleId()) &&
                betAfbList.get(0).getItem().getMainHomeId().equalsIgnoreCase(item.getMainHomeId()) &&
                betAfbList.get(0).getItem().getMainAwayId().equalsIgnoreCase(item.getMainAwayId()) /*&& (betAfbList.gettList().get(0).getIsGive() + "").equalsIgnoreCase(item.getIsHomeGive())*/;
        return onTeam;
    }

    protected OddsClickBean<B> getOddsUrl(int socId, String type, boolean isHf, String odds, String sc, B item) {
        return new OddsClickBean<>(type, getBallG(), socId + "", isHf ? socId + "" : "", sc, item);
    }


}
