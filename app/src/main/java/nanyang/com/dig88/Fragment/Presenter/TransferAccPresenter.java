package nanyang.com.dig88.Fragment.Presenter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.ChinaLotteryBalanceBean;
import nanyang.com.dig88.Entity.FFYLPokerBalanceBean;
import nanyang.com.dig88.Entity.GpPokerBalanceBean;
import nanyang.com.dig88.Entity.IbetPokerBalanceBean;
import nanyang.com.dig88.Entity.IgLotteryBalanceBean;
import nanyang.com.dig88.Entity.JdbSlotBalanceBean;
import nanyang.com.dig88.Entity.JokerBalanceBean;
import nanyang.com.dig88.Entity.KYPokerBalanceBean;
import nanyang.com.dig88.Entity.Mega888BalanceBean;
import nanyang.com.dig88.Entity.TransferAccContentBean;
import nanyang.com.dig88.Entity.TransferScrDataBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Entity.We1PokerBalanceBean;
import nanyang.com.dig88.Entity.klasPokerBalanceBean;
import nanyang.com.dig88.Fragment.TransferAccFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.Dig88Utils;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/7/5.
 */

public class TransferAccPresenter extends BaseRetrofitPresenter<TransferAccFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    TransferAccFragment transferAccFragment;

    public TransferAccPresenter(TransferAccFragment iBaseContext) {
        super(iBaseContext);
        transferAccFragment = iBaseContext;
    }

    public List<TransferAccContentBean> getContentData() {
        List<TransferAccContentBean> list = new ArrayList<>();
        list.add(new TransferAccContentBean(baseContext.getString(R.string.master_acc), AppConfig.Main_Account, ""));
        addTransferGame(AppConfig.IBC_GAME, baseContext.getString(R.string.saba_yue_money), list);
        addTransferGame(AppConfig.KLAS_Poker, baseContext.getString(R.string.klaspoker_game_money), list);
        addTransferGame(AppConfig.POKER_GAME, baseContext.getString(R.string.texas_holdem) + "(" + baseContext.getString(R.string.Balance) + "):", list);
        if (baseContext.getCurrency().equals("CNY")) {
            addTransferGame(AppConfig.HC_LOTTERY, baseContext.getString(R.string.hc_lottery) + "(" + baseContext.getString(R.string.Balance) + "):", list);
            addTransferGame(AppConfig.IG_LOTTERY, baseContext.getString(R.string.ig_lottery) + "(" + baseContext.getString(R.string.Balance) + "):", list);
        }
        if (!transferAccFragment.getCurrency().equals("IDR")) {
            addTransferGame(AppConfig.Scr888, baseContext.getString(R.string.scr888) + "(" + baseContext.getString(R.string.Balance) + "):", list);
        }
        addTransferGame(AppConfig.We1poker, transferAccFragment.getString(R.string.we1poker) + "(" + baseContext.getString(R.string.Balance) + ")" + ":", list);
        addTransferGame(AppConfig.FFYL_POKER_GAME, "FFYL " + transferAccFragment.getString(R.string.texas_holdem1) + "(" + baseContext.getString(R.string.Balance) + ")" + ":", list);
        addTransferGame(AppConfig.KY_poker, "Kai Yuan " + transferAccFragment.getString(R.string.texas_holdem1) + "(" + baseContext.getString(R.string.Balance) + ")" + ":", list);
        return list;
    }

    private void addTransferGame(String gameType, String gameName, List<TransferAccContentBean> list) {
        List<String> gameStatusList = baseContext.getApp().getGameStatusList();
        for (int i = 0; i < gameStatusList.size(); i++) {
            String game = gameStatusList.get(i);
            if (game.equals(gameType)) {
                if (gameType.equals(AppConfig.IBC_GAME)) {
                    list.add(new TransferAccContentBean(gameName, game, ""));
                    list.add(new TransferAccContentBean(baseContext.getString(R.string.saba_noclear_money), "", ""));
                } else {
                    list.add(new TransferAccContentBean(gameName, game, ""));
                }
                break;
            }
        }
    }

    public void getBalance(final String url, final String param, final TextView tvBalance, final String gameType, final ProgressBar pbLoading) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(url + param), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                switch (gameType) {
                    case AppConfig.POKER_GAME:
                        GpPokerBalanceBean gpPokerBalanceBean = gson.fromJson(data, GpPokerBalanceBean.class);
                        if (gpPokerBalanceBean.getErrorMsg().equals("No Error")) {
                            tvBalance.setText(Dig88Utils.strFormat("%.2f", gpPokerBalanceBean.getBalance() + ""));
                        } else {
                            tvBalance.setText("0.00");
                        }
                        pbLoading.setVisibility(View.GONE);
                        break;
                    case AppConfig.KLAS_Poker:
                        //{"response":{"code":1,"status":"failed","message":"Access Denied. Your IP Address (52.192.134.139) is not in our White List.","responseDate":"1575879810","elapsedTime":0.003}}
                        if (data.contains("failed") || data.contains("Failed")) {
                            tvBalance.setText("0.00");
                        } else {
                            klasPokerBalanceBean klasPokerBean = gson.fromJson(data, klasPokerBalanceBean.class);
                            tvBalance.setText(Dig88Utils.strFormat("%.2f", klasPokerBean.getMemberBalance()));
                        }
                        pbLoading.setVisibility(View.GONE);
                        break;
                    case AppConfig.IG_LOTTERY:
                        IgLotteryBalanceBean balanceBean = gson.fromJson(data, IgLotteryBalanceBean.class);
                        if (balanceBean.getErrorCode() == 0) {
                            tvBalance.setText(Dig88Utils.strFormat("%.2f", balanceBean.getParams().getBalance() + ""));
                        } else {
                            tvBalance.setText("0.00");
                        }
                        pbLoading.setVisibility(View.GONE);
                        break;
                    case AppConfig.HC_LOTTERY:
                        ChinaLotteryBalanceBean chinaLotteryBalanceBean = gson.fromJson(data, ChinaLotteryBalanceBean.class);
                        if (chinaLotteryBalanceBean.getCode().equals("1") && chinaLotteryBalanceBean.getErrorMsg().equals("No Error")) {
                            tvBalance.setText(Dig88Utils.strFormat("%.2f", chinaLotteryBalanceBean.getBalance() + ""));
                        }
                        pbLoading.setVisibility(View.GONE);
                        break;
                    case AppConfig.IBC_GAME:
                        String[] sabaArr = data.split("#");
                        if (sabaArr[0].equals("ok")) {
                            tvBalance.setText(Dig88Utils.strFormat("%.2f", sabaArr[1]));
                            transferAccFragment.onGetIbcNoClearBalance(Dig88Utils.strFormat("%.2f", sabaArr[2]));
                        } else {
                            tvBalance.setText("0.00");
                            transferAccFragment.onGetIbcNoClearBalance("0.00");
                        }
                        pbLoading.setVisibility(View.GONE);
                        break;
                    case AppConfig.Ibet567_Poker:
                        IbetPokerBalanceBean ibetPokerBalanceBean = gson.fromJson(data, IbetPokerBalanceBean.class);
                        tvBalance.setText(Dig88Utils.strFormat("%.2f", ibetPokerBalanceBean.getBalance()));
                        pbLoading.setVisibility(View.GONE);
                        break;
                    case AppConfig.Mega888:
                        Mega888BalanceBean mega888BalanceBean = gson.fromJson(data, Mega888BalanceBean.class);
                        tvBalance.setText(Dig88Utils.strFormat("%.2f", mega888BalanceBean.getBalance() + ""));
                        pbLoading.setVisibility(View.GONE);
                        transferAccFragment.onGetMega888Balance(mega888BalanceBean.getLoginId());
                        break;
                    case AppConfig.JdbSlots:
                        JdbSlotBalanceBean jdbSlotBalanceBean = gson.fromJson(data, JdbSlotBalanceBean.class);
                        tvBalance.setText(Dig88Utils.strFormat("%.2f", jdbSlotBalanceBean.getBalance() + ""));
                        pbLoading.setVisibility(View.GONE);
                        break;
                    case AppConfig.FFYL_POKER_GAME:
                        FFYLPokerBalanceBean ffylBalanceBean = gson.fromJson(data, FFYLPokerBalanceBean.class);
                        if (ffylBalanceBean.getCode() == 0) {
                            tvBalance.setText(Dig88Utils.strFormat("%.2f", ffylBalanceBean.getBalance() + "") + "(" + Dig88Utils.strFormat("%.2f", ffylBalanceBean.getGamecoin() + "") + ")");
                        } else {
                            tvBalance.setText("0.00");
                        }
                        pbLoading.setVisibility(View.GONE);
                        break;
                    case AppConfig.KY_poker:
                        KYPokerBalanceBean kyPokerBalanceBean = gson.fromJson(data, KYPokerBalanceBean.class);
                        if (kyPokerBalanceBean.getCode().equals("0")) {
                            tvBalance.setText(Dig88Utils.strFormat("%.2f", kyPokerBalanceBean.getM_balance() + "") + "(" + Dig88Utils.strFormat("%.2f", kyPokerBalanceBean.getBalance() + "") + ")");
                        } else {
                            tvBalance.setText("0.00");
                        }
                        pbLoading.setVisibility(View.GONE);
                        break;
                    case AppConfig.We1poker:
                        if (data.contains("No Error")) {
                            We1PokerBalanceBean we1PokerBalanceBean = gson.fromJson(data, We1PokerBalanceBean.class);
                            tvBalance.setText(Dig88Utils.strFormat("%.2f", we1PokerBalanceBean.getBalance()));
                        } else {
                            tvBalance.setText("0.00");
                        }
                        pbLoading.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    public void postBalance(String url, HashMap<String, String> param, final TextView tvBalance, final String gameType, final ProgressBar pbLoading) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(url, param), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                switch (gameType) {
                    case AppConfig.Main_Account:
                        NyBaseResponse<VipInfoBean> dataBean = gson.fromJson(data, new TypeToken<NyBaseResponse<VipInfoBean>>() {
                        }.getType());
                        VipInfoBean vipInfoBean = dataBean.getData();
                        AppTool.saveObjectData(baseContext.getBaseActivity(), "vipInfo", vipInfoBean);
                        String mainBalanceStr = Dig88Utils.strFormat("%.2f", vipInfoBean.getBalance().toString());
                        tvBalance.setText(mainBalanceStr);
                        pbLoading.setVisibility(View.GONE);
                        break;
                    case AppConfig.Joker123_GAME:
                        if (data.contains("code\":-1")) {
                            tvBalance.setText("0.00");
                        } else {
                            JokerBalanceBean jokerBalanceBean = gson.fromJson(data, JokerBalanceBean.class);
                            tvBalance.setText(Dig88Utils.strFormat("%.2f", jokerBalanceBean.getCredit() + ""));
                        }
                        pbLoading.setVisibility(View.GONE);
                        break;
                    case AppConfig.Scr888:
                        //{"code":"0","msg":"1","data":{"scr888_id":"02790578450","scr888_pwd":"8689ENxz","scr888_balance":"10"}}
                        String scrBalance;
                        String scrId;
                        String scrPw;
                        if (data.contains("\"msg\":\"1\"")) {
                            TransferScrDataBean bean = gson.fromJson(data, TransferScrDataBean.class);
                            if (!TextUtils.isEmpty(bean.getData().getScr888_id()) && !bean.getData().getScr888_id().equals("false")) {
                                scrBalance = Dig88Utils.strFormat("%.2f", bean.getData().getScr888_balance());
                                scrId = "ID:(" + bean.getData().getScr888_id() + ")";
                            } else {
                                scrBalance = "0.00";
                                scrId = "ID:( N/A ) ";
                            }
                            if (!TextUtils.isEmpty(bean.getData().getScr888_pwd()) && !bean.getData().getScr888_pwd().equals("false")) {
                                scrPw = "PWD:(" + bean.getData().getScr888_pwd() + ")";
                            } else {
                                scrPw = "PWD:( N/A )";
                            }
                        } else {
                            scrId = "ID:( N/A )";
                            scrPw = "PWD:( N/A )";
                            scrBalance = "0.00";
                        }
                        pbLoading.setVisibility(View.GONE);
                        tvBalance.setText(scrBalance);
                        transferAccFragment.onGetScr888Balance(scrId, scrPw);
                        break;
                }
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
            }
        });
    }

    public void getTransferAmount(String url, String param, final String gameType) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(url + param), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                transferAccFragment.getMainBalance();
                transferAccFragment.hideLoadingDialog();
                switch (gameType) {
                    case AppConfig.POKER_GAME:
                        if (data.contains("No Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getPokerBalance();
                        break;
                    case AppConfig.IG_LOTTERY:
                        if (data.contains("1") && data.contains("No Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getIgLotteryBalance();
                        break;
                    case AppConfig.HC_LOTTERY:
                        if (data.contains("1") && data.contains("No Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getHcLotteryBalance();
                        break;
                    case AppConfig.KLAS_Poker:
                        if (data.contains("Succeed") || data.contains("No Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getKlasPokerBalance();
                        break;
                    case AppConfig.Ibet567_Poker:
                        if (data.contains("Succeed") || data.contains("No Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getOngdoPokerBalance();
                        break;
                    case AppConfig.Mega888:
                        if (data.contains("Succeed") || data.contains("No Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getMega888Balance();
                        break;
                    case AppConfig.JdbSlots:
                        if (data.contains("Succeed") || data.contains("No Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getJdbBalance();
                        break;
                    case AppConfig.FFYL_POKER_GAME:
                        if (data.contains("Succeed") || data.contains("No Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getFFYLBalance();
                        break;
                    case AppConfig.KY_poker:
                        if (data.contains("Succeed") || data.contains("No Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getKYPokerBalance();
                        break;
                    case AppConfig.We1poker:
                        if (data.contains("Succeed") || data.contains("No Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getWe1PokerBalance();
                        break;
                }
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
            }
        });
    }

    public void postTransferAmount(String url, HashMap<String, String> param, final String gameType) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(url, param), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                transferAccFragment.getMainBalance();
                transferAccFragment.hideLoadingDialog();
                switch (gameType) {
                    case AppConfig.Joker123_GAME:
                        if (data.contains("Succeed") || data.contains("No Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getJokerBalance();
                        break;
                    case AppConfig.IBC_GAME:
                        //{"code":"1","msg":"1","balance":"37.772999999987235"}
                        if (data.contains("\"msg\":\"1\"")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getIbcBalance();
                        break;
                    case AppConfig.Scr888:
                        //{"code":"1","msg":"Transfer Successfully!","data":{"scr_balance":"4.00"}}
                        //{"code":"-1","msg":"Transfer fail,please transfer again!","record":{"Code":-1,"Msg":"Register Error","data":null}}
                        if (data.contains("Successfully")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Success));
                        } else if (data.contains("Register Error")) {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Failed));
                        } else {
                            transferAccFragment.onGetTransferMsg(transferAccFragment.getString(R.string.Balance_not_enough));
                        }
                        transferAccFragment.getScr888Balance();
                        break;
                }
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }
}
