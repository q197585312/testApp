package nanyang.com.dig88.Lottery;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.LotteryCountBean;
import nanyang.com.dig88.Entity.LotteryPromptBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ICountLotteryBet;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;
import xs.com.mylibrary.allinone.util.DecimalUtils;
import xs.com.mylibrary.base.quick.QuickRequestBean;

/**
 * Created by Administrator on 2016/2/20.
 */
public abstract class LotteryBaseFragment<T> extends BaseFragment {
    public String betType = "";
    protected LotteryBaseAdapter<T> adapter;
    PopLotteryContent popLotteryContent;
    String betStr;
    private boolean canBet = true;
    private BlockDialog dialog;
    private LotteryCountBean countBean;
    ICountLotteryBet iCount = new ICountLotteryBet() {

        @Override
        public void updatePrompt(LotteryPromptBean promptBean) {
            getAct().setLotteryPrompt(promptBean);
        }

        @Override
        public void updateTotal(LotteryCountBean count) {
            setCountBean(count);
            getAct().setLotteryCount(count);
        }
    };

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        dialog = new BlockDialog(mContext, getString(R.string.zhengjiazai));
        adapter = getAdapter();
        if (adapter != null) {
            adapter.setiCountLotteryBet(iCount);
            adapter.initData();
        }
    }

    public abstract void updateGameState(List<DigGameOddsBean> oddsList, LotteryStateGameBean selectedbean);

    public LotteryActivity getAct() {
        return (LotteryActivity) getActivity();
    }

    public abstract LotteryBaseAdapter<T> getAdapter();

    public void submitBet(View v) {
        if (adapter.getPromptSingle().equals("") && !constructorGetBetStr().equals("")) {
            if (getUserInfo().getMoneyBalance() != null && !getUserInfo().getMoneyBalance().getBalance().equals("") && Double.valueOf(getUserInfo().getMoneyBalance().getBalance()) >=
                    Double.valueOf(getCountBean().getBetTotal())) {
                betThread(constructorGetBetStr());
            } else {
                Toast.makeText(mContext, getString(R.string.not_enough_balance_please_deposit_first), Toast.LENGTH_SHORT).show();
            }
        } else {

//            BaseYseNoChoosePopupwindow pop=new BaseYseNoChoosePopupwindow(mContext,v) {
//                @Override
//                protected void clickSure(View v) {
//                }
//            };
//            pop.getChooseTitleTv().setText(getString(R.string.prompt));
            if (!adapter.getPromptSingle().equals(""))
                Toast.makeText(mContext, adapter.getPromptSingle(), Toast.LENGTH_SHORT).show();
//                pop.getChooseMessage().setText(adapter.getPromptSingle());
            /*else if(!adapter.getPromptTotal().equals("")){
                Toast.makeText(mContext,adapter.getPromptTotal(),Toast.LENGTH_SHORT).show();
//                pop.getChooseMessage().setText(adapter.getPromptTotal());
            }*/
            else if (constructorGetBetStr().equals("")) {
                Toast.makeText(mContext, getString(R.string.qingchashumu), Toast.LENGTH_SHORT).show();
//                pop.getChooseMessage().setText(getString(R.string.qingchashumu));
            }
         /*   pop.getChooseSureTv().setText(getString(R.string.sure));
            pop.getChooseCancelTv().setText(getString(R.string.cancel));
            pop.showPopupCenterWindow();*/
        }
    }

    public boolean isCanBet(double betMoney) {
        String balance = getUserInfo().getMoneyBalance().getBalance();
        if (!TextUtils.isEmpty(balance)) {
            if (Double.parseDouble(balance) <= 0 || Double.parseDouble(balance) < betMoney) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public void submitFor2D(View v) {
        betStr = constructorGetBetStr();
        if (!betStr.contains("#")) {
            Toast.makeText(mContext, betStr, Toast.LENGTH_SHORT).show();
        } else {
            String[] bet = betStr.split("\\^");
            int betMoney = 0;
            for (int i = 0; i < bet.length; i++) {
                String s = bet[i];
                String[] split = s.split("#");
                int money = Integer.parseInt(split[1]);
                betMoney += money;
            }
            if (isCanBet(betMoney)) {
                if (betMoney > Double.parseDouble(getDigGameOddsBean().getMax_total())) {
                    Toast.makeText(mContext, getString(R.string.betting_total_limit_more_than_nominal) + getDigGameOddsBean().getMax_total(), Toast.LENGTH_SHORT).show();
                    return;
                }
                LotteryActivity act = getAct();
                double discount = Double.parseDouble(getDigGameOddsBean().getDiscount()) / 100;
                double key = Math.abs(Double.parseDouble(getDigGameOddsBean().getKei())) / 100;
                double netMoney = (betMoney * (1 - discount)) * (1 + key);
                act.setLotteryCount(DecimalUtils.decimalFormat(betMoney, "0.00"), DecimalUtils.decimalFormat(netMoney, "0.00"));
                if (popLotteryContent == null) {
                    WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                    DisplayMetrics dm = new DisplayMetrics();
                    wm.getDefaultDisplay().getMetrics(dm);
                    int width = dm.widthPixels;         // 屏幕宽度（像素）
                    int height = dm.heightPixels;       // 屏幕高度（像素）
                    popLotteryContent = new PopLotteryContent(mContext, v, width / 3 * 2, ViewGroup.LayoutParams.WRAP_CONTENT) {
                        @Override
                        public void submit() {
                            popLotteryContent.closePopupWindow();
                            betThread(betStr);
                        }
                    };
                }
                popLotteryContent.setData(betStr);
                popLotteryContent.showPopupCenterWindowBlack();
            } else {
                Toast.makeText(mContext, getString(R.string.not_enough_balance_please_deposit_first), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void betThread(final String s) {
        final LotteryStateGameBean selectedBean = ((LotteryActivity) getAct()).getSelectedStateBean();
        NyVolleyJsonThreadHandler<LotteryBetResultBean> betThead = new NyVolleyJsonThreadHandler<LotteryBetResultBean>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("user_id", getUserInfo().getUser_id());
                params.put("session_id", getUserInfo().getSession_id());
                params.put("from", "Android--" + AppTool.getApkInfo(mContext).versionName);
                params.put("id_provider", selectedBean.getType2());
                params.put("get_bet", s);
                return new QuickRequestBean(WebSiteUrl.Dig88UrlIndex + "page=" + getSubmitPageUrl(), params
                        , new TypeToken<NyBaseResponse<LotteryBetResultBean>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, LotteryBetResultBean data) {
                String showDataStr = "";
                if (!TextUtils.isEmpty(data.getSuccess())) {
                    showDataStr += "success: " + data.getSuccess();
                }
                if (!TextUtils.isEmpty(data.getFailure())) {
                    showDataStr += "  failure: " + data.getFailure();
                }
                Toast.makeText(getActivity(), showDataStr, Toast.LENGTH_SHORT).show();
                if (!betType().equals("2DFront") && !betType().equals("2DMid")) {
                    saveBetMap();
                    adapter.clearListMoney();
                    adapter.countTotal();
                } else {
                    clear();
                }
                dialog.dismiss();
                canBet = true;
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
                Toast.makeText(getActivity(), R.string.xiazhucuowu, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                canBet = true;
            }
        };
        if (canBet) {
            dialog.show();
            betThead.startThread(null);
            canBet = false;
        }
    }

    private void saveBetMap() {
        AppTool.saveObjectData(mContext, getAct().getSelectedStateBean().getPeriod(), adapter.getTotalTypeMap());
    }

    protected abstract String getSubmitPageUrl();

    protected abstract String constructorGetBetStr();

    public LotteryCountBean getCountBean() {
        return countBean;
    }

    public void setCountBean(LotteryCountBean countBean) {
        this.countBean = countBean;
    }

    public void setProgressVisibility(boolean isVisibility) {

    }

    public String getType() {
        return "";
    }

    public String betType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public DigGameOddsBean getDigGameOddsBean() {
        return null;
    }

    public void clear() {

    }
}
