package nanyang.com.dig88.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.SharePreferenceUtil;
import nanyang.com.dig88.Activity.ActivityFragmentShow;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Activity.Scr888ChangePasswordActivity;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.TransferAccContentBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.Presenter.TransferAccPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseListPopWindow;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2017/1/17.
 */

public class TransferAccFragment extends BaseFragment<TransferAccPresenter> {
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.tv_to)
    TextView tvTo;
    @BindView(R.id.edt_amount)
    EditText edtAmount;
    TextView tvIbcNoClear;
    ProgressBar pbLoadingIbcNoClear;
    List<TransferAccContentBean> gameContentList;
    List<String> transferGameList;
    String fromGameId;
    String toGameId;
    VipInfoBean info;
    String amount;

    String masterAcc;
    String hcLottery;
    String igLottery;
    String saba;
    String scr;
    String klasPoker;
    String ggPoker;
    String joker123;
    String ongdoPoker;
    String meaga888;
    String jdb;
    String ffyl;
    String KYPoker;
    String we1poker;

    TextView tvScrPw;
    TextView tvScrId;
    LinearLayout llScr;
    LinearLayout llMega888;
    TextView tvMega888Id;
    TextView tvMega888Pw;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_transferacc;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getAct().setTitle(getString(R.string.transfer_center));
        BaseActivity act = getAct();
        masterAcc = getString(R.string.list1_master_acc);
        hcLottery = getString(R.string.hc_lottery);
        igLottery = getString(R.string.ig_lottery);
        saba = getString(R.string.list2_saba);
        scr = getString(R.string.scr888);
        klasPoker = getString(R.string.klasPoker);
        ggPoker = getString(R.string.texas_holdem);
        joker123 = getString(R.string.joker_game_slots);
        ongdoPoker = getString(R.string.transfer_ongdo_poker);
        meaga888 = getString(R.string.mega_game_slots);
        jdb = getString(R.string.jdb_game_slots);
        ffyl = "FFYL POKER";
        KYPoker = "Kai Yuan";
        we1poker = getString(R.string.we1poker);
        act.rightTv2.setVisibility(View.VISIBLE);
        act.rightTv2.setBackgroundResource(R.mipmap.deposit_list);
        act.rightTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityFragmentShow.class);
                intent.putExtra("type", TransferAccListFragment.class.getName());
                startActivity(intent);
            }
        });
        createPresenter(new TransferAccPresenter(this));
        edtAmount.setKeyListener(new DigitsKeyListener(false, true) {
            @Override
            protected char[] getAcceptedChars() {
                char[] numberChars = {'.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
                return numberChars;
            }
        });
        info = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
        tvFrom.setText(masterAcc);
        tvTo.setText(masterAcc);
        initGameBalance();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (llScr != null) {
            getScr888Balance();
        }
    }

    public void initGameBalance() {
        transferGameList = new ArrayList<>();
        gameContentList = presenter.getContentData();
        for (int i = 0; i < gameContentList.size(); i++) {
            TransferAccContentBean transferAccContentBean = gameContentList.get(i);
            String gameType = transferAccContentBean.getGameType();
            String gameName = transferAccContentBean.getGameName();
            String gameBalance = transferAccContentBean.getGameBalance();
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_transfer_content, null);
            ProgressBar pbLoading = view.findViewById(R.id.loading);
            TextView tvGameName = view.findViewById(R.id.tv_game_name);
            TextView tvBalance = view.findViewById(R.id.tv_game_balance);
            if (gameType.equals(AppConfig.Scr888)) {
                llScr = view.findViewById(R.id.ll_scr);
                tvScrId = view.findViewById(R.id.tv_scr_id);
                tvScrPw = view.findViewById(R.id.tv_scr_pw);
            } else if (gameType.equals(AppConfig.Mega888)) {
                llMega888 = view.findViewById(R.id.ll_scr);
                tvMega888Id = view.findViewById(R.id.tv_scr_id);
                tvMega888Pw = view.findViewById(R.id.tv_scr_pw);
            }
            transferAccContentBean.setTvBalance(tvBalance);
            transferAccContentBean.setPbLoading(pbLoading);
            View viewLine = view.findViewById(R.id.view_line);
            if (i == gameContentList.size() - 1) {
                viewLine.setVisibility(View.GONE);
            }
            tvGameName.setText(gameName);
            tvBalance.setText(gameBalance);
            llContent.addView(view);
            if (TextUtils.isEmpty(gameType)) {
                tvIbcNoClear = tvBalance;
                pbLoadingIbcNoClear = pbLoading;
                continue;
            }
            switch (gameType) {
                case AppConfig.Main_Account:
                    transferGameList.add(masterAcc);
                    getMainBalance();
                    break;
                case AppConfig.Joker123_GAME:
                    transferGameList.add(joker123);
                    getJokerBalance();
                    break;
                case AppConfig.Scr888:
                    transferGameList.add(scr);
                    getScr888Balance();
                    break;
                case AppConfig.POKER_GAME:
                    transferGameList.add(ggPoker);
                    getPokerBalance();
                    break;
                case AppConfig.KLAS_Poker:
                    transferGameList.add(klasPoker);
                    getKlasPokerBalance();
                    break;
                case AppConfig.IG_LOTTERY:
                    transferGameList.add(igLottery);
                    getIgLotteryBalance();
                    break;
                case AppConfig.HC_LOTTERY:
                    transferGameList.add(hcLottery);
                    getHcLotteryBalance();
                    break;
                case AppConfig.IBC_GAME:
                    transferGameList.add(saba);
                    getIbcBalance();
                    break;
                case AppConfig.Ibet567_Poker:
                    transferGameList.add(ongdoPoker);
                    getOngdoPokerBalance();
                    break;
                case AppConfig.Mega888:
                    transferGameList.add(meaga888);
                    getMega888Balance();
                    break;
                case AppConfig.JdbSlots:
                    transferGameList.add(jdb);
                    getJdbBalance();
                    break;
                case AppConfig.FFYL_POKER_GAME:
                    transferGameList.add(ffyl);
                    getFFYLBalance();
                    break;
                case AppConfig.KY_poker:
                    transferGameList.add(KYPoker);
                    getKYPokerBalance();
                    break;
                case AppConfig.We1poker:
                    transferGameList.add(we1poker);
                    getWe1PokerBalance();
                    break;
            }
        }
    }

    public void getWe1PokerBalance() {
        String gameType = AppConfig.We1poker;
        TextView tvBalance = getBalanceTv(gameType);
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&token=" + getUserInfo().getSession_id();
        presenter.getBalance(WebSiteUrl.We1pokerBalanceUrl, param, tvBalance, gameType, getPbLoading(gameType));
    }

    public void getKYPokerBalance() {
        String gameType = AppConfig.KY_poker;
        TextView tvBalance = getBalanceTv(gameType);
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername();
        presenter.getBalance(WebSiteUrl.KYPokerBalanceUrl, param, tvBalance, gameType, getPbLoading(gameType));
    }

    public void getFFYLBalance() {
        String gameType = AppConfig.FFYL_POKER_GAME;
        TextView tvBalance = getBalanceTv(gameType);
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername();
        presenter.getBalance(WebSiteUrl.FFYLBalanceUrl, param, tvBalance, gameType, getPbLoading(gameType));
    }

    public void getJdbBalance() {
        String gameType = AppConfig.JdbSlots;
        TextView tvBalance = getBalanceTv(gameType);
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername();
        presenter.getBalance(WebSiteUrl.JDBBalanceUrl, param, tvBalance, gameType, getPbLoading(gameType));
    }

    public void getMega888Balance() {
        String gameType = AppConfig.Mega888;
        TextView tvBalance = getBalanceTv(gameType);
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername();
        presenter.getBalance(WebSiteUrl.Mega888BalanceUrl, param, tvBalance, gameType, getPbLoading(gameType));
    }

    public void onGetMega888Balance(String loginId) {
        llMega888.setVisibility(View.VISIBLE);
        String content = "ID:(" + loginId + ")";
        LoginInfoBean info = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        String pw = "PWD:(" + info.getPassword() + ")";
        tvMega888Id.setText(Dig88Utils.handleStringColor(content, ":", ContextCompat.getColor(mContext, R.color.blue), ContextCompat.getColor(mContext, R.color.red)));
        tvMega888Pw.setText(Dig88Utils.handleStringColor(pw, ":", ContextCompat.getColor(mContext, R.color.blue), ContextCompat.getColor(mContext, R.color.red)));
    }

    public void getOngdoPokerBalance() {
        String gameType = AppConfig.Ibet567_Poker;
        TextView tvBalance = getBalanceTv(gameType);
        String param = "web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername();
        presenter.getBalance(WebSiteUrl.Ibet567PokerBalanceUrl, param, tvBalance, gameType, getPbLoading(gameType));
    }

    public void getIbcBalance() {
        String gameType = AppConfig.IBC_GAME;
        TextView tvBalance = getBalanceTv(gameType);
        String param = "id_mod_member=" + getUserInfo().getUser_id() + "&agentid=W9Eqo9Lem24";
        presenter.getBalance(WebSiteUrl.SabaBalanceUrl, param, tvBalance, gameType, getPbLoading(gameType));
    }

    public void getHcLotteryBalance() {
        String gameType = AppConfig.HC_LOTTERY;
        TextView tvBalance = getBalanceTv(gameType);
        String param = "web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername();
        presenter.getBalance(WebSiteUrl.HcLotteryBanlanceUrl, param, tvBalance, gameType, getPbLoading(gameType));
    }

    public void getIgLotteryBalance() {
        String gameType = AppConfig.IG_LOTTERY;
        TextView tvBalance = getBalanceTv(gameType);
        String param = "web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername();
        presenter.getBalance(WebSiteUrl.IgLotteryBalance, param, tvBalance, gameType, getPbLoading(gameType));
    }

    public void getKlasPokerBalance() {
        String gameType = AppConfig.KLAS_Poker;
        TextView tvBalance = getBalanceTv(gameType);
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername();
        presenter.getBalance(WebSiteUrl.KlasPokerBanlanceUrl, param, tvBalance, gameType, getPbLoading(gameType));
    }

    public void getPokerBalance() {
        String gameType = AppConfig.POKER_GAME;
        TextView tvBalance = getBalanceTv(gameType);
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername();
        presenter.getBalance(WebSiteUrl.TexasPokerBalanceUrl, param, tvBalance, gameType, getPbLoading(gameType));
    }

    public void getScr888Balance() {
        String gameType = AppConfig.Scr888;
        TextView tvBalance = getBalanceTv(gameType);
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("id_user", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        p.put("scr888", "1");
        presenter.postBalance(WebSiteUrl.ScrBalanceUrl, p, tvBalance, gameType, getPbLoading(gameType));
    }

    public void onGetScr888Balance(final String id, final String pw) {
        llScr.setVisibility(View.VISIBLE);
        llScr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Scr888ChangePasswordActivity.class);
                ContentInfoBean contentInfoBean = new ContentInfoBean();
                contentInfoBean.setContent(id);
                contentInfoBean.setContentId(pw);
                intent.putExtra("ContentInfoBean", contentInfoBean);
                startActivity(intent);
            }
        });
        tvScrId.setText(Dig88Utils.handleStringColor(id, ":", ContextCompat.getColor(mContext, R.color.blue), ContextCompat.getColor(mContext, R.color.red)));
        tvScrPw.setText(Dig88Utils.handleStringColor(pw, ":", ContextCompat.getColor(mContext, R.color.blue), ContextCompat.getColor(mContext, R.color.red)));
    }

    public void getJokerBalance() {
        String gameType = AppConfig.Joker123_GAME;
        TextView tvBalance = getBalanceTv(gameType);
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("username", info.getUsername());
        presenter.postBalance(WebSiteUrl.Joker123BalanceUrl, p, tvBalance, gameType, getPbLoading(gameType));
    }

    public void getMainBalance() {
        String gameType = AppConfig.Main_Account;
        TextView tvBalance = getBalanceTv(AppConfig.Main_Account);
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        presenter.postBalance(WebSiteUrl.MemberInfoSubmitter, p, tvBalance, gameType, getPbLoading(gameType));
    }

    public ProgressBar getPbLoading(String gameType) {
        ProgressBar pbLoading = null;
        for (int i = 0; i < gameContentList.size(); i++) {
            TransferAccContentBean transferAccContentBean = gameContentList.get(i);
            String gameType1 = transferAccContentBean.getGameType();
            ProgressBar pbLoading1 = transferAccContentBean.getPbLoading();
            if (gameType.equals(gameType1)) {
                pbLoading = pbLoading1;
                break;
            }
        }
        return pbLoading;
    }

    public TextView getBalanceTv(String gameType) {
        TextView tvBalance = null;
        for (int i = 0; i < gameContentList.size(); i++) {
            TransferAccContentBean transferAccContentBean = gameContentList.get(i);
            String gameType1 = transferAccContentBean.getGameType();
            TextView tvBalance1 = transferAccContentBean.getTvBalance();
            if (gameType.equals(gameType1)) {
                tvBalance = tvBalance1;
                break;
            }
        }
        return tvBalance;
    }

    public void onGetIbcNoClearBalance(String balance) {
        tvIbcNoClear.setText(balance);
        pbLoadingIbcNoClear.setVisibility(View.GONE);
    }

    @OnClick({R.id.btn_submit, R.id.tv_to, R.id.tv_from})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_from:
                BaseListPopWindow popFromSwitchGame = new BaseListPopWindow(mContext, tvFrom, tvFrom.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<String> getContentData() {
                        return transferGameList;
                    }

                    @Override
                    public void onClickItem(int position, String item) {
                        if (!item.equals(masterAcc)) {
                            tvTo.setText(masterAcc);
                        }
                        tvFrom.setText(item);
                    }
                };
                popFromSwitchGame.showPopupDownWindow();
                break;
            case R.id.tv_to:
                BaseListPopWindow popToSwitchGame = new BaseListPopWindow(mContext, tvTo, tvTo.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<String> getContentData() {
                        return transferGameList;
                    }

                    @Override
                    public void onClickItem(int position, String item) {
                        if (!item.equals(masterAcc)) {
                            tvFrom.setText(masterAcc);
                        }
                        tvTo.setText(item);
                    }
                };
                popToSwitchGame.showPopupDownWindow();
                break;
            case R.id.btn_submit:
                submit();
                break;
        }
    }

    private void submit() {
        amount = edtAmount.getText().toString().trim();
        if (TextUtils.isEmpty(amount)) {
            ToastUtils.showShort(getString(R.string.money_null));
            return;
        }
        String from = tvFrom.getText().toString();
        String to = tvTo.getText().toString();
        showLoadingDialog();
        if (from.equals(masterAcc) && to.equals(saba)) {
            fromGameId = "1";
            toGameId = "2";
            sahaAndScr888Transfer("123", WebSiteUrl.SabaTransferUrl);
        } else if (from.equals(saba) && to.equals(masterAcc)) {
            fromGameId = "2";
            toGameId = "1";
            sahaAndScr888Transfer("123", WebSiteUrl.SabaTransferUrl);
        } else if (from.equals(masterAcc) && to.equals(scr)) {
            fromGameId = "1";
            toGameId = "5";
            sahaAndScr888Transfer("true", WebSiteUrl.scrTransferUrl);
        } else if (from.equals(scr) && to.equals(masterAcc)) {
            fromGameId = "5";
            toGameId = "1";
            sahaAndScr888Transfer("true", WebSiteUrl.scrTransferUrl);
        } else if (from.equals(masterAcc) && to.equals(klasPoker)) {
            fromGameId = "1";
            toGameId = "8";
            klasPokerTransfer();
        } else if (from.equals(klasPoker) && to.equals(masterAcc)) {
            fromGameId = "8";
            toGameId = "1";
            klasPokerTransfer();
        } else if (from.equals(masterAcc) && to.equals(hcLottery)) {
            hcLotteryTransfer(WebSiteUrl.HcLotteryBuyUrl);
        } else if (from.equals(hcLottery) && to.equals(masterAcc)) {
            hcLotteryTransfer(WebSiteUrl.HcLotteryTakeoutUrl);
        } else if (from.equals(masterAcc) && to.equals(igLottery)) {
            igLotteryTransfer(WebSiteUrl.IgLotteryDeposit);
        } else if (from.equals(igLottery) && to.equals(masterAcc)) {
            igLotteryTransfer(WebSiteUrl.IgLotteryWithdraw);
        } else if (from.equals(masterAcc) && to.equals(ggPoker)) {
            ggPokerTransfer(WebSiteUrl.TexasPokerBuyUrl);
        } else if (from.equals(ggPoker) && to.equals(masterAcc)) {
            ggPokerTransfer(WebSiteUrl.TexasPokerTakeOutUrl);
        } else if (from.equals(masterAcc) && to.equals(joker123)) {
            fromGameId = "1";
            toGameId = "11";
            joker123Transfer();
        } else if (from.equals(joker123) && to.equals(masterAcc)) {
            fromGameId = "11";
            toGameId = "1";
            joker123Transfer();
        } else if (from.equals(masterAcc) && to.equals(ongdoPoker)) {
            fromGameId = "1";
            toGameId = "24";
            ongdoPokerPokerTransfer(WebSiteUrl.Ibet567PokerTransferUrl);
        } else if (from.equals(ongdoPoker) && to.equals(masterAcc)) {
            fromGameId = "24";
            toGameId = "1";
            ongdoPokerPokerTransfer(WebSiteUrl.Ibet567PokerTransferUrl);
        } else if (from.equals(masterAcc) && to.equals(meaga888)) {
            fromGameId = "1";
            toGameId = "28";
            mega888Transfer(WebSiteUrl.Mega888TransferUrl);
        } else if (from.equals(meaga888) && to.equals(masterAcc)) {
            fromGameId = "28";
            toGameId = "1";
            mega888Transfer(WebSiteUrl.Mega888TransferUrl);
        } else if (from.equals(masterAcc) && to.equals(jdb)) {
            fromGameId = "1";
            toGameId = "27";
            JdbTransfer(WebSiteUrl.JDBTransferUrl);
        } else if (from.equals(jdb) && to.equals(masterAcc)) {
            fromGameId = "27";
            toGameId = "1";
            JdbTransfer(WebSiteUrl.JDBTransferUrl);
        } else if (from.equals(masterAcc) && to.equals(ffyl)) {
            fromGameId = "1";
            toGameId = "18";
            FfylTransfer(WebSiteUrl.FFYLTransferUrl);
        } else if (from.equals(ffyl) && to.equals(masterAcc)) {
            fromGameId = "18";
            toGameId = "1";
            FfylTransfer(WebSiteUrl.FFYLTransferUrl);
        } else if (from.equals(masterAcc) && to.equals(KYPoker)) {
            fromGameId = "1";
            toGameId = "1036";
            KYPokerTransfer(WebSiteUrl.KYPokerTransferUrl);
        } else if (from.equals(KYPoker) && to.equals(masterAcc)) {
            fromGameId = "1036";
            toGameId = "1";
            KYPokerTransfer(WebSiteUrl.KYPokerTransferUrl);
        } else if (from.equals(masterAcc) && to.equals(we1poker)) {
            fromGameId = "1";
            toGameId = "20";
            We1PokerTransfer(WebSiteUrl.We1pokerTransferUrl);
        } else if (from.equals(we1poker) && to.equals(masterAcc)) {
            fromGameId = "20";
            toGameId = "1";
            We1PokerTransfer(WebSiteUrl.We1pokerTransferUrl);
        } else {
            hideLoadingDialog();
            ToastUtils.showShort(getString(R.string.choice_again));
        }
    }

    private void We1PokerTransfer(String url) {
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&from=" + fromGameId + "&to=" + toGameId + "&amount=" + amount +
                "&token=" + getUserInfo().getSession_id();
        presenter.getTransferAmount(url, param, AppConfig.We1poker);
    }

    private void KYPokerTransfer(String url) {
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&from=" + fromGameId + "&to=" + toGameId + "&amount=" + amount;
        presenter.getTransferAmount(url, param, AppConfig.KY_poker);
    }

    private void FfylTransfer(String url) {
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&from=" + fromGameId + "&to=" + toGameId + "&amount=" + amount;
        presenter.getTransferAmount(url, param, AppConfig.FFYL_POKER_GAME);
    }

    private void JdbTransfer(String url) {
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&from=" + fromGameId + "&to=" + toGameId + "&amount=" + amount;
        presenter.getTransferAmount(url, param, AppConfig.JdbSlots);
    }

    private void mega888Transfer(String url) {
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&from=" + fromGameId + "&to=" + toGameId + "&amount=" + amount;
        presenter.getTransferAmount(url, param, AppConfig.Mega888);
    }

    private void ongdoPokerPokerTransfer(String url) {
        String param = "web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&from=" + fromGameId + "&to=" + toGameId + "&amount=" + amount;
        presenter.getTransferAmount(url, param, AppConfig.Ibet567_Poker);
    }

    private void joker123Transfer() {
        HashMap<String, String> params = new HashMap<>();
        params.put("web_id", WebSiteUrl.WebId);
        params.put("username", info.getUsername());
        params.put("amount", amount);
        params.put("from", fromGameId);
        params.put("to", toGameId);
        presenter.postTransferAmount(WebSiteUrl.Joker123TransferUrl, params, AppConfig.Joker123_GAME);
    }

    private void ggPokerTransfer(String url) {
        String param = "token=" + getUserInfo().getSession_id() + "&amount=" + amount;
        presenter.getTransferAmount(url, param, AppConfig.POKER_GAME);
    }

    private void igLotteryTransfer(String url) {
        String param = "web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&amount=" + amount;
        presenter.getTransferAmount(url, param, AppConfig.IG_LOTTERY);
    }

    private void hcLotteryTransfer(String url) {
        String param = "web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&sess_id=" +
                getUserInfo().getSession_id() + "&amount=" + amount;
        presenter.getTransferAmount(url, param, AppConfig.HC_LOTTERY);
    }

    public void klasPokerTransfer() {
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername()
                + "&from=" + fromGameId + "&to=" + toGameId + "&amount=" + amount;
        presenter.getTransferAmount(WebSiteUrl.KlasPokerTransferUrl, param, AppConfig.KLAS_Poker);
    }

    public void sahaAndScr888Transfer(String save, String url) {
        HashMap<String, String> params = new HashMap<>();
        params.put("web_id", WebSiteUrl.WebId);
        params.put("id_user", getUserInfo().getUser_id());
        params.put("session_id", getUserInfo().getSession_id());
        params.put("save", save);
        params.put("username", info.getUsername());
        params.put("from_gameid", fromGameId);
        params.put("to_gameid", toGameId);
        params.put("amt", amount);
        params.put("currency", getCurrency());
        String gameType = AppConfig.IBC_GAME;
        if (url.equals(WebSiteUrl.scrTransferUrl)) {
            params.put("IP", SharePreferenceUtil.getString(mContext, "IP"));
            gameType = AppConfig.Scr888;
        }
        presenter.postTransferAmount(url, params, gameType);
    }

    public void onGetTransferMsg(String data) {
        ToastUtils.showShort(data);
    }
}
