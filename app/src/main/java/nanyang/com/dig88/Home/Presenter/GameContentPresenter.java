package nanyang.com.dig88.Home.Presenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import nanyang.com.dig88.Activity.Gd88WebActivity;
import nanyang.com.dig88.Activity.IbetPokerActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.Afb1188LoginBean;
import nanyang.com.dig88.Entity.AppVersionBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.DigGameStateBean;
import nanyang.com.dig88.Entity.GameMaintenanceBean;
import nanyang.com.dig88.Entity.Gd88AgentBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.Mega888AddressBean;
import nanyang.com.dig88.Home.Bean.HomeContentListBean;
import nanyang.com.dig88.Home.GameContentActivity;
import nanyang.com.dig88.Home.GameWebActivity;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.BallGameActivity;
import nanyang.com.dig88.Table.Thread.TableHttpHelper;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.BallGameInfoBean;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.ApkUtils;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.PopIbetPokerTransfer;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/6/25.
 */

public class GameContentPresenter extends BaseRetrofitPresenter<GameContentActivity> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    GameContentActivity gameContentActivity;
    SimpleDateFormat format;

    public GameContentPresenter(GameContentActivity iBaseContext) {
        super(iBaseContext);
        gameContentActivity = iBaseContext;
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public int getBannerPic(String gameType) {
        switch (gameType) {
            case AppConfig.Casino:
                return R.mipmap.game_casino_banner;
            case AppConfig.Lottery:
                return R.mipmap.game_lottery_banner;
            case AppConfig.Sports:
                return R.mipmap.game_sports_banner;
            case AppConfig.Number:
                return R.mipmap.game_number_banner;
            case AppConfig.Poker:
            case AppConfig.Ibet567_Poker:
                return R.mipmap.game_poker_banner;
            case AppConfig.Forex:
                return R.mipmap.game_forex_banner;
            case AppConfig.Promotions:
                return R.mipmap.game_promotions_banner;
            case AppConfig.Game:
            case AppConfig.Hot_Game:
                return R.mipmap.game_game_banner;
            case AppConfig.More:
                return R.mipmap.game_more_banner;
            default:
                return 0;
        }
    }

    public List<HomeContentListBean> getContent(List<String> gameList) {
        List<HomeContentListBean> homeContentListBeanList = new ArrayList<>();
        for (int i = 0; i < gameList.size(); i++) {
            HomeContentListBean bean = new HomeContentListBean();
            String gameType = gameList.get(i);
            switch (gameType) {
                case AppConfig.GD_GAME:
                    String gd88Name = gameContentActivity.getString(R.string.gd_live_entertainment);
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        gd88Name = "GD88 " + gameContentActivity.getString(R.string.live_entertainment);
                    }
                    bean.setGameName(gd88Name);
                    bean.setGamePic(R.mipmap.casino_gd);
                    break;
                case AppConfig.AG_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.ag_live_entertainment));
                    bean.setGamePic(R.mipmap.casino_ag);
                    break;
                case AppConfig.DG_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.dg_live_entertainment));
                    bean.setGamePic(R.mipmap.casino_dg);
                    break;
                case AppConfig.GOLD_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.gold_live_entertainment));
                    bean.setGamePic(R.mipmap.casino_gold);
                    break;
                case AppConfig.AllBet_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.ob_live_entertainment));
                    bean.setGamePic(R.mipmap.casino_allbet);
                    break;
                case AppConfig.SAGaming_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.sa_live_entertainment));
                    bean.setGamePic(R.mipmap.casino_sa);
                    break;
                case AppConfig.WM_Casino_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.wm_live_entertainment));
                    bean.setGamePic(R.mipmap.casino_wm);
                    break;
                case AppConfig.W88_Casino_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.w88_live_entertainment));
                    bean.setGamePic(R.mipmap.casino_w88);
                    break;
                case AppConfig.SexyCasino_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.sexy_live_entertainment));
                    bean.setGamePic(R.mipmap.casino_sexy);
                    break;
                case AppConfig.EVO_Casino_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.evo_live_entertainment));
                    bean.setGamePic(R.mipmap.evogaming_casino);
                    break;
                case AppConfig.AFB_GAME:
                    String afb88Name = gameContentActivity.getString(R.string.afb88_sports);
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        afb88Name = "AFB88";
                    }
                    bean.setGameName(afb88Name);
                    bean.setGamePic(R.mipmap.sport_afb88);
                    break;
                case AppConfig.AFB_h5_Desktop_GAME:
                    bean.setGameName("AFB88 Desktop");
                    bean.setGamePic(R.mipmap.sport_afb88_h5);
                    break;
                case AppConfig.AFB_h5_GAME:
                    String afbH5Name = gameContentActivity.getString(R.string.afb88_sports_h5);
                    if (BuildConfig.FLAVOR.equals("bbet99")) {
                        afbH5Name = "AFB88";
                    } else if (BuildConfig.FLAVOR.equals("uplay365")) {
                        afbH5Name = "U.Sports-H5";
                    }
                    bean.setGameName(afbH5Name);
                    bean.setGamePic(R.mipmap.sport_afb88_h5);
                    break;
                case AppConfig.AFB1188_GAME:
                    String afb1188Name = gameContentActivity.getString(R.string.afb1188_sports);
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        afb1188Name = "AFB1188_H5";
                    } else if (BuildConfig.FLAVOR.equals("bbet99")) {
                        afb1188Name = "AFB88 NEW";
                    } else if (BuildConfig.FLAVOR.equals("uplay365")) {
                        afb1188Name = "U.Sports";
                    }
                    bean.setGameName(afb1188Name);
                    bean.setGamePic(R.mipmap.sport_afb1188);
                    break;
                case AppConfig.AFB1188_Desktop_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.AFB1188_Desktop));
                    if (BuildConfig.FLAVOR.equals("fun77")) {
                        bean.setGamePic(R.mipmap.sport_afb88_h5);
                    } else {
                        bean.setGamePic(R.mipmap.sport_afb1188);
                    }
                    break;
                case AppConfig.IBC_GAME:
                    String ibcName = gameContentActivity.getString(R.string.i_sport_betting);
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        ibcName = "";
                    }
                    bean.setGameName(ibcName);
                    bean.setGamePic(R.mipmap.sport_ibc);
                    break;
                case AppConfig.SBO_GAME:
                    String sboName = gameContentActivity.getString(R.string.s_sport_betting);
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        sboName = "";
                    } else if (BuildConfig.FLAVOR.equals("uplay365")) {
                        sboName = "S.Sports";
                    }
                    bean.setGameName(sboName);
                    bean.setGamePic(R.mipmap.sport_sbo);
                    break;
                case AppConfig.Scr888:
                    bean.setGameName(gameContentActivity.getString(R.string.scr888));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("918Kiss");
                    }
                    bean.setGamePic(R.mipmap.game_scr888);
                    break;
                case AppConfig.Xe88_Slot:
                    bean.setGameName(gameContentActivity.getString(R.string.xe88_game_slots));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("XE88 Slots");
                    }
                    bean.setGamePic(R.mipmap.game_xe88);
                    break;
                case AppConfig.BEST_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.best_game_slots));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("AFB GAMING");
                    }
                    bean.setGamePic(R.mipmap.game_bestgame);
                    break;
                case AppConfig.NETENT_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.netent_game_slots));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("NETENT Slots");
                    }
                    bean.setGamePic(R.mipmap.slots_netent);
                    break;
                case AppConfig.PLAYSTAR_SLOTS:
                    bean.setGameName(gameContentActivity.getString(R.string.playstar_game_slots));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("PLAYSTAR Slots");
                    }
                    bean.setGamePic(R.mipmap.slots_playstar);
                    break;
                case AppConfig.PG_SLOTS:
                    bean.setGameName("PG Slots");
                    bean.setGamePic(R.mipmap.pg_slots);
                    break;
                case AppConfig.HABA_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.haba_game_slots));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("HABA Slots");
                    } else if (BuildConfig.FLAVOR.equals("kimsa1")) {
                        bean.setGameName("HABA Slot");
                    }
                    bean.setGamePic(R.mipmap.game_haba);
                    break;
                case AppConfig.SAgaming_Slots_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.sa_game_slots));
                    if (BuildConfig.FLAVOR.equals("kimsa1")) {
                        bean.setGameName("SA Slot");
                    }
                    bean.setGamePic(R.mipmap.game_sa);
                    break;
                case AppConfig.Joker123_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.joker_game_slots));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("Joker123");
                    }
                    bean.setGamePic(R.mipmap.game_joker);
                    break;
                case AppConfig.W88_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.w88_game_slots));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("W88 Slots");
                    } else if (BuildConfig.FLAVOR.equals("kimsa1")) {
                        bean.setGameName("W88 Slot");
                    }
                    bean.setGamePic(R.mipmap.game_w88);
                    break;
                case AppConfig.PT_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.pt_game_slots));
                    if (BuildConfig.FLAVOR.equals("kimsa1")) {
                        bean.setGameName("PT Slot");
                    }
                    bean.setGamePic(R.mipmap.game_pt);
                    break;
                case AppConfig.MG_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.mg_game_slots));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("MG Slots");
                    } else if (BuildConfig.FLAVOR.equals("kimsa1")) {
                        bean.setGameName("MG Slot");
                    }
                    bean.setGamePic(R.mipmap.game_mg);
                    break;
                case AppConfig.FISHING_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.fish_game_slots));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("Fishing Games");
                    }
                    bean.setGamePic(R.mipmap.game_fish);
                    break;
                case AppConfig.PP_Slots:
                    bean.setGameName(gameContentActivity.getString(R.string.pp_game_slots));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("Pragmatic Play Slots");
                    } else if (BuildConfig.FLAVOR.equals("kimsa1")) {
                        bean.setGameName("PP Slot");
                    }
                    bean.setGamePic(R.mipmap.game_pp);
                    break;
                case AppConfig.RTG_Slots:
                    bean.setGameName(gameContentActivity.getString(R.string.rtg_game_slots));
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        bean.setGameName("RTG Slots");
                    }
                    bean.setGamePic(R.mipmap.game_rtg);
                    break;
                case AppConfig.POKER_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.texas_holdem));
                    bean.setGamePic(R.mipmap.poker_gg);
                    break;
                case AppConfig.KLAS_Poker:
                    bean.setGameName(gameContentActivity.getString(R.string.klasPoker));
                    bean.setGamePic(R.mipmap.poker_klas);
                    break;
                case AppConfig.HC_LOTTERY:
                    bean.setGameName(gameContentActivity.getString(R.string.hc_lottery));
                    bean.setGamePic(R.mipmap.hc_lottery);
                    break;
                case AppConfig.IG_LOTTERY:
                    bean.setGameName(gameContentActivity.getString(R.string.ig_lottery));
                    bean.setGamePic(R.mipmap.ig_lottery);
                    break;
                case AppConfig.LOTTERY_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.indonesia_lottery));
                    bean.setGamePic(R.mipmap.lottery_lottery);
                    break;
                case AppConfig.THAI_LOTTERY:
                    bean.setGameName(gameContentActivity.getString(R.string.thai_lottery));
                    bean.setGamePic(R.mipmap.lottery_thai);
                    break;
                case AppConfig.LOTTERY_4D:
                    String lottery4DName = gameContentActivity.getString(R.string.lottery4d);
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        lottery4DName = gameContentActivity.getString(R.string.lottery4d1);
                    }
                    bean.setGameName(lottery4DName);
                    bean.setGamePic(R.mipmap.lottery_4d);
                    break;
                case AppConfig.KENO_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.keno));
                    bean.setGamePic(R.mipmap.keno);
                    break;
                case AppConfig.LIVE_NUMBER_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.live_number));
                    bean.setGamePic(R.mipmap.number_live_number);
                    break;
                case AppConfig.NUMBER_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.number));
                    bean.setGamePic(R.mipmap.number_number);
                    break;
                case AppConfig.NEW_KENO_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.new_keno));
                    bean.setGamePic(R.mipmap.new_keno);
                    break;
                case AppConfig.Cockfight:
                    bean.setGameName(gameContentActivity.getString(R.string.Cockfight));
                    bean.setGamePic(R.mipmap.cockfight);
                    break;
                case AppConfig.ONGDO_Poker:
                    bean.setGameName(gameContentActivity.getString(R.string.transfer_ongdo_poker));
                    bean.setGamePic(R.mipmap.ongdo_poker);
                    break;
                case AppConfig.Poker_Poker:
                    bean.setGameName(gameContentActivity.getString(R.string.transfer_poker_poker));
                    bean.setGamePic(R.mipmap.poker_poker);
                    break;
                case AppConfig.HAM_Poker:
                    bean.setGameName(gameContentActivity.getString(R.string.transfer_ham_poker));
                    bean.setGamePic(R.mipmap.ham_poker);
                    break;
                case AppConfig.TIENLEN_Poker:
                    bean.setGameName(gameContentActivity.getString(R.string.transfer_tienlen_poker));
                    bean.setGamePic(R.mipmap.tienlen_poker);
                    break;
                case AppConfig.KLA_KLOUK_Poker:
                    bean.setGameName(gameContentActivity.getString(R.string.transfer_kla_klouk_poker));
                    bean.setGamePic(R.mipmap.kla_klouk_poker);
                    break;
                case AppConfig.SIKUTHAI_Poker:
                    bean.setGameName(gameContentActivity.getString(R.string.transfer_sikuthai_Poker));
                    bean.setGamePic(R.mipmap.sikuthai_poker);
                    break;
                case AppConfig.Kate_Poker:
                    bean.setGameName(gameContentActivity.getString(R.string.transfer_kate_Poker));
                    bean.setGamePic(R.mipmap.icon_kate);
                    break;
                case AppConfig.Apoung_Poker:
                    bean.setGameName(gameContentActivity.getString(R.string.transfer_Apoung_Poker));
                    bean.setGamePic(R.mipmap.icon_apoung);
                    break;
                case AppConfig.FFYL_POKER_GAME:
                    bean.setGameName(gameContentActivity.getString(R.string.FFYL_Poker));
                    bean.setGamePic(R.mipmap.ffyl_poker);
                    break;
                case AppConfig.Mega888:
                    bean.setGameName(gameContentActivity.getString(R.string.mega_game_slots));
                    bean.setGamePic(R.mipmap.mega_slots);
                    break;
                case AppConfig.JdbSlots:
                    bean.setGameName(gameContentActivity.getString(R.string.jdb_game_slots));
                    bean.setGamePic(R.mipmap.jdb_slot);
                    break;
                case AppConfig.We1poker:
                    bean.setGameName(gameContentActivity.getString(R.string.we1poker));
                    bean.setGamePic(R.mipmap.we1poker);
                    break;
                case AppConfig.KY_poker:
                    bean.setGameName("Kai Yuan");
                    bean.setGamePic(R.mipmap.ky_poker);
                    break;
                case AppConfig.Lottery_VN:
                    bean.setGameName("Lô Đề VN");
                    bean.setGamePic(R.mipmap.lottery_vn);
                    break;
                case AppConfig.AFB_CASINO:
                    bean.setGameName(gameContentActivity.getString(R.string.afb_live_entertainment));
                    bean.setGamePic(R.mipmap.afb_casino1);
                    break;
                case AppConfig.PP_CASINO:
                    bean.setGameName(gameContentActivity.getString(R.string.pp_live_entertainment));
                    bean.setGamePic(R.mipmap.pp_casino1);
                    break;
                case AppConfig.N2_CASINO:
                    bean.setGameName(gameContentActivity.getString(R.string.n2_live_entertainment));
                    bean.setGamePic(R.mipmap.n2_casino1);
                    break;
            }
            bean.setGameType(gameType);
            homeContentListBeanList.add(bean);
        }
        return homeContentListBeanList;
    }

    public void enterAfb1188(final String gameType) {
        HashMap<String, String> param = new HashMap<>();
        param.put("type2", "61");
        Disposable subscription = getService(ApiService.class).doPostMap(WebSiteUrl.GameMaintenanceUrl, param)
                .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String s) throws Exception {
                        GameMaintenanceBean gameMaintenanceBean = gson.fromJson(s, GameMaintenanceBean.class);
                        String afb1188Status = gameMaintenanceBean.getData().getStatus();
                        if (afb1188Status.equals("1")) {
                            Exception exception = new Exception(gameContentActivity.getString(R.string.System_maintenance));
                            throw exception;
                        } else {
                            LoginInfoBean logininfobean = (LoginInfoBean) AppTool.getObjectData(gameContentActivity, "loginInfo");
                            String platFrom = "mobile";
                            if (gameType.equals(AppConfig.AFB1188_Desktop_GAME)) {
                                platFrom = "PC";
                            }
                            String p = "web_id=" + WebSiteUrl.WebId + "&username=" + logininfobean.getUsername() + "&language=" + getLanguage() +
                                    "&token=" + gameContentActivity.getUserInfoBean().getSession_id() + "&from=app" + "&platfrom=" + platFrom;
                            return getService(ApiService.class).getData(WebSiteUrl.Sport1188LimitUrl + p);
                        }
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String data) throws Exception {
                        Afb1188LoginBean afb1188LoginBean = gson.fromJson(data, Afb1188LoginBean.class);
                        Intent intent = new Intent(gameContentActivity, GameWebActivity.class);
                        intent.putExtra("url", afb1188LoginBean.getLoginURL());
                        intent.putExtra("title", gameContentActivity.getString(R.string.afb1188_sports));
                        gameContentActivity.startActivity(intent);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.hideLoadingDialog();
                        ToastUtils.showShort(throwable.getMessage());
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseContext.showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void enterGd88() {
        if (ApkUtils.isAvilible(baseContext, "gaming178.com.baccaratgame")) {
            LoginInfoBean info = (LoginInfoBean) AppTool.getObjectData(baseContext, "loginInfo");
            Bundle bundle = new Bundle();
            String userName = "";
            if (info != null) {
                userName = info.getUsername();
            }
            bundle.putString("web_id", WebSiteUrl.WebId);
            String currency = baseContext.getCurrency();
            bundle.putString("currency", currency);
            bundle.putString("username", userName);
            bundle.putString("balance", baseContext.getUserInfoBean().getBalance());
            String appLg = AppTool.getAppLanguage(baseContext);
            String lang = "en";
            if (appLg.equals("zh")) {
                lang = "zh";
            } else if (appLg.equals("zh_TW")) {
                lang = "zh_TW";
            } else if (appLg.equals("th")) {
                lang = "th";
            }
            bundle.putString("language", lang);
            bundle.putInt("gameType", 0);
            AppTool.appJump(baseContext, "gaming178.com.baccaratgame", "gaming178.com.casinogame.Activity.WelcomeActivity", bundle);
        } else {
            doRetrofitApiOnUiThread(getService(ApiService.class).getData(WebSiteUrl.gd88DownloadUrl), new BaseConsumer<String>(baseContext) {
                @Override
                protected void onBaseGetData(String data) throws JSONException {
                    AppVersionBean appVersionBean = new Gson().fromJson(data, AppVersionBean.class);
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(appVersionBean.getData().getUrl());
                    intent.setData(content_url);
                    gameContentActivity.startActivity(intent);
                }

                @Override
                protected void onAccept() {
//                    super.onAccept();
                }
            });
        }
//        String param = "&web_id=" + WebSiteUrl.WebId + "&currency=" + gameContentActivity.getCurrency();
//        doRetrofitApiOnUiThread(getService(ApiService.class).getData(WebSiteUrl.Gd88H5AgentIDUrl + param), new BaseConsumer<String>(baseContext) {
//            @Override
//            protected void onBaseGetData(String data) throws JSONException {
//                Gd88AgentBean gd88AgentBean = gson.fromJson(data, Gd88AgentBean.class);
//                LoginInfoBean info = (LoginInfoBean) AppTool.getObjectData(gameContentActivity, "loginInfo");
//                String url = WebSiteUrl.Gd88H5LoginUrl;
//                String p = "web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&currency=" + gameContentActivity.getCurrency() +
//                        "&agentID=" + gd88AgentBean.getData().getAgent() + "&language=" + Dig88Utils.getLanguage(gameContentActivity) +
//                        "&balance=" + gameContentActivity.getUserInfoBean().getMoneyBalance().getBalance() + "&gameType=0&lobbyId=0";
//                Intent intent = new Intent(gameContentActivity, Gd88WebActivity.class);
//                intent.putExtra("url", url);
//                intent.putExtra("parmas", p);
//                gameContentActivity.startActivity(intent);
//            }
//        });
    }

    public void enterLiveNumber() {
        final Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", baseContext.getUserInfoBean().getUser_id());
        p.put("session_id", baseContext.getUserInfoBean().getSession_id());
        Disposable subscription = getService(ApiService.class).doPostMap(WebSiteUrl.LivenumberStatus, p)
                .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String s) throws Exception {
                        NyBaseResponse<List<DigGameStateBean>> orgData;
                        orgData = gson.fromJson(s, new TypeToken<NyBaseResponse<List<DigGameStateBean>>>() {
                        }.getType());
                        List<DigGameStateBean> data = orgData.getData();
                        boolean statu42 = false;
                        boolean statu36 = false;
                        boolean statu12 = false;
                        boolean statu48 = false;
                        boolean statuSicbo = false;
                        boolean statuRoulette = false;
                        boolean statuMultiple = false;
                        if (orgData.getMsg().equals("1")) {
                            for (int i = 0; i < data.size(); i++) {
                                if (data.get(i).getGame_name().equals("42_Balls")) {
                                    baseContext.getApp().getLivegame_42balls().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getLivegame_42balls().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getLivegame_42balls().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getLivegame_42balls().setGame_opentime(data.get(i).getOpen_time());
                                    statu42 = true;
                                    baseContext.getApp().getLivegame_42balls().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {

                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down <= 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getLivegame_42balls().setCount_down(count_down);
                                    baseContext.getApp().getLivegame_42balls().setGame_close(i);
                                } else if (data.get(i).getGame_name().equals("36_Balls")) {
                                    baseContext.getApp().getLivegame_36balls().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getLivegame_36balls().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getLivegame_36balls().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getLivegame_36balls().setGame_opentime(data.get(i).getOpen_time());
                                    statu36 = true;
                                    baseContext.getApp().getLivegame_36balls().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {
                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down <= 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getLivegame_36balls().setCount_down(count_down);
                                    baseContext.getApp().getLivegame_36balls().setGame_close(i);
                                } else if (data.get(i).getGame_name().equals("12_Balls")) {
                                    baseContext.getApp().getLivegame_12balls().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getLivegame_12balls().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getLivegame_12balls().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getLivegame_12balls().setGame_opentime(data.get(i).getOpen_time());
                                    statu12 = true;
                                    baseContext.getApp().getLivegame_12balls().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {
                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down <= 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getLivegame_12balls().setCount_down(count_down);
                                    baseContext.getApp().getLivegame_12balls().setGame_close(i);
                                } else if (data.get(i).getGame_name().equals("48_Balls")) {
                                    baseContext.getApp().getLivegame_48balls().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getLivegame_48balls().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getLivegame_48balls().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getLivegame_48balls().setGame_opentime(data.get(i).getOpen_time());
                                    statu48 = true;
                                    baseContext.getApp().getLivegame_48balls().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {
                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down <= 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getLivegame_48balls().setCount_down(count_down);
                                    baseContext.getApp().getLivegame_48balls().setGame_close(i);
                                } else if (data.get(i).getGame_name().equals("Sicbo_Balls")) {
                                    baseContext.getApp().getLivegame_Sicoballs().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getLivegame_Sicoballs().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getLivegame_Sicoballs().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getLivegame_Sicoballs().setGame_opentime(data.get(i).getOpen_time());
                                    statuSicbo = true;
                                    baseContext.getApp().getLivegame_Sicoballs().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {
                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down <= 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getLivegame_Sicoballs().setCount_down(count_down);
                                    baseContext.getApp().getLivegame_Sicoballs().setGame_close(i);
                                } else if (data.get(i).getGame_name().equals("Roulette_Balls")) {
                                    baseContext.getApp().getLivegame_Scollballs().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getLivegame_Scollballs().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getLivegame_Scollballs().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getLivegame_Scollballs().setGame_opentime(data.get(i).getOpen_time());
                                    statuRoulette = true;
                                    baseContext.getApp().getLivegame_Scollballs().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {
                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down <= 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getLivegame_Scollballs().setCount_down(count_down);
                                    baseContext.getApp().getLivegame_Scollballs().setGame_close(i);
                                } else if (data.get(i).getGame_name().equals("Multiple_36Balls")) {
                                    baseContext.getApp().getLivegame_MULTIPLEballs().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getLivegame_MULTIPLEballs().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getLivegame_MULTIPLEballs().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getLivegame_MULTIPLEballs().setGame_opentime(data.get(i).getOpen_time());
                                    statuMultiple = true;
                                    baseContext.getApp().getLivegame_MULTIPLEballs().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {
                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down <= 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getLivegame_MULTIPLEballs().setCount_down(count_down);
                                    baseContext.getApp().getLivegame_MULTIPLEballs().setGame_close(i);
                                }
                            }
                        }
                        if (statu42 == false) {
                            baseContext.getApp().getLivegame_42balls().setGame_status(false);
                        }
                        if (statu36 == false) {
                            baseContext.getApp().getLivegame_36balls().setGame_status(false);
                        }
                        if (statu12 == false) {
                            baseContext.getApp().getLivegame_12balls().setGame_status(false);
                        }
                        if (statu48 == false) {
                            baseContext.getApp().getLivegame_48balls().setGame_status(false);
                        }
                        if (statuSicbo == false) {
                            baseContext.getApp().getLivegame_Sicoballs().setGame_status(false);
                        }
                        if (statuRoulette == false) {
                            baseContext.getApp().getLivegame_Scollballs().setGame_status(false);
                        }
                        if (statuMultiple == false) {
                            baseContext.getApp().getLivegame_MULTIPLEballs().setGame_status(false);
                        }
                        return getService(ApiService.class).doPostMap(WebSiteUrl.GetGameOdd, p);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String str) throws Exception {
                        NyBaseResponse<List<DigGameOddsBean>> orgData;
                        orgData = gson.fromJson(str, new TypeToken<NyBaseResponse<List<DigGameOddsBean>>>() {
                        }.getType());
                        List<DigGameOddsBean> data = orgData.getData();
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getType1().equals("7")) {
                                if (data.get(i).getType2().equals(baseContext.getApp().getLivegame_42balls().getGame_type())) {
                                    if (data.get(i).getType3().equals("24")) { // 42 ball big small
                                        baseContext.getApp().getLivegame_42balls().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_42balls().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_42balls().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_42balls().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_42balls().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("25")) { // evenodd
                                        baseContext.getApp().getLivegame_42balls().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_42balls().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_42balls().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_42balls().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_42balls().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("26")) { //com
                                        baseContext.getApp().getLivegame_42balls().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_42balls().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_42balls().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_42balls().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_42balls().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("27")) { // color
                                        baseContext.getApp().getLivegame_42balls().setColor_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_42balls().setColor_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_42balls().setColor_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_42balls().setColor_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_42balls().setColor_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("23")) { // NUMBER
                                        baseContext.getApp().getLivegame_42balls().setNumber_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_42balls().setNumber_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_42balls().setNumber_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_42balls().setNumber_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_42balls().setNumber_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }
                                } else if (data.get(i).getType2().equals(baseContext.getApp().getLivegame_36balls().getGame_type())) {
                                    if (data.get(i).getType3().equals("24")) { // 36 ball big small
                                        baseContext.getApp().getLivegame_36balls().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_36balls().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_36balls().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_36balls().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_36balls().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("25")) { // evenodd
                                        baseContext.getApp().getLivegame_36balls().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_36balls().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_36balls().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_36balls().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_36balls().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("26")) { //com
                                        baseContext.getApp().getLivegame_36balls().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_36balls().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_36balls().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_36balls().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_36balls().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("27")) { // color
                                        baseContext.getApp().getLivegame_36balls().setColor_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_36balls().setColor_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_36balls().setColor_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_36balls().setColor_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_36balls().setColor_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("23")) { // NUMBER
                                        baseContext.getApp().getLivegame_36balls().setNumber_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_36balls().setNumber_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_36balls().setNumber_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_36balls().setNumber_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_36balls().setNumber_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }
                                } else if (data.get(i).getType2().equals(baseContext.getApp().getLivegame_12balls().getGame_type())) {
                                    if (data.get(i).getType3().equals("24")) { //12 ball big small
                                        baseContext.getApp().getLivegame_12balls().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_12balls().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_12balls().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("25")) { // evenodd
                                        baseContext.getApp().getLivegame_12balls().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_12balls().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_12balls().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("26")) { //com
                                        baseContext.getApp().getLivegame_12balls().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_12balls().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_12balls().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("27")) { // color
                                        baseContext.getApp().getLivegame_12balls().setColor_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_12balls().setColor_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setColor_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setColor_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_12balls().setColor_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("23")) { // NUMBER
                                        baseContext.getApp().getLivegame_12balls().setNumber_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_12balls().setNumber_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setNumber_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setNumber_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_12balls().setNumber_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("57")) { // JACKPOT
                                        baseContext.getApp().getLivegame_12balls().setJackpot12_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_12balls().setJackpot12_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setJackpot12_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_12balls().setJackpot12_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_12balls().setJackpot12_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }
                                } else if (data.get(i).getType2().equals(baseContext.getApp().getLivegame_48balls().getGame_type())) {
                                    if (data.get(i).getType3().equals("24")) { //ball big small
                                        baseContext.getApp().getLivegame_48balls().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_48balls().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_48balls().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("25")) { // evenodd
                                        baseContext.getApp().getLivegame_48balls().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_48balls().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_48balls().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("26")) { //com
                                        baseContext.getApp().getLivegame_48balls().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_48balls().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_48balls().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("27")) { // color（golod sliver）
                                        baseContext.getApp().getLivegame_48balls().setGolodsliver_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_48balls().setGolodsliver_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setGolodsliver_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setGolodsliver_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_48balls().setGolodsliver_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("70")) { // GROUP
                                        baseContext.getApp().getLivegame_48balls().setGroup_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_48balls().setGroup_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setGroup_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setGroup_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_48balls().setGroup_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("23")) { // NUMBER(单个号码 英 法 德)
                                        baseContext.getApp().getLivegame_48balls().setSimgnum48_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_48balls().setSimgnum48_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setSimgnum48_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_48balls().setSimgnum48_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_48balls().setSimgnum48_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }
                                } else if (data.get(i).getType2().equals(baseContext.getApp().getLivegame_Sicoballs().getGame_type())) {
                                    if (data.get(i).getType3().equals("24")) { //ball big small
                                        baseContext.getApp().getLivegame_Sicoballs().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("25")) { // evenodd
                                        baseContext.getApp().getLivegame_Sicoballs().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("59")) { // PAIRS
                                        baseContext.getApp().getLivegame_Sicoballs().setPairs_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setPairs_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setPairs_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setPairs_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setPairs_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("60")) { //WAI DICES
                                        baseContext.getApp().getLivegame_Sicoballs().setWaidices_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setWaidices_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setWaidices_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setWaidices_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setWaidices_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("62")) { //NINEWAYCARD
                                        baseContext.getApp().getLivegame_Sicoballs().setNinewaycard_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setNinewaycard_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setNinewaycard_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setNinewaycard_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setNinewaycard_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("64")) { //COMBINATIONES 1
                                        baseContext.getApp().getLivegame_Sicoballs().setComu48_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu48_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu48_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu48_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu48_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("65")) { //COMBINATIONES 2
                                        baseContext.getApp().getLivegame_Sicoballs().setComu482_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu482_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu482_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu482_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu482_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("66")) { //COMBINATIONES 3
                                        baseContext.getApp().getLivegame_Sicoballs().setComu483_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu483_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu483_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu483_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu483_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("67")) { //COMBINATIONES 4
                                        baseContext.getApp().getLivegame_Sicoballs().setComu484_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu484_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu484_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu484_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu484_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("68")) { //COMBINATIONES 5
                                        baseContext.getApp().getLivegame_Sicoballs().setComu485_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu485_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu485_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu485_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu485_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("69")) { //COMBINATIONES 6
                                        baseContext.getApp().getLivegame_Sicoballs().setComu486_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu486_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu486_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu486_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setComu486_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("63")) {  //Threeforces
                                        baseContext.getApp().getLivegame_Sicoballs().setThreeforces_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setThreeforces_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setThreeforces_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setThreeforces_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setThreeforces_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("61")) { //AllDICE
                                        baseContext.getApp().getLivegame_Sicoballs().setAlldice_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Sicoballs().setAlldice_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setAlldice_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Sicoballs().setAlldice_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Sicoballs().setAlldice_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }
                                } else if (data.get(i).getType2().equals(baseContext.getApp().getLivegame_Scollballs().getGame_type())) {
                                    if (data.get(i).getType3().equals("23")) { // NUMBER
                                        baseContext.getApp().getLivegame_Scollballs().setNumber_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Scollballs().setNumber_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Scollballs().setNumber_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Scollballs().setNumber_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Scollballs().setNumber_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("26")) { //com
                                        baseContext.getApp().getLivegame_Scollballs().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Scollballs().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Scollballs().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Scollballs().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Scollballs().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("24")) { //ball big small
                                        baseContext.getApp().getLivegame_Scollballs().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Scollballs().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Scollballs().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Scollballs().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Scollballs().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("25")) { // evenodd
                                        baseContext.getApp().getLivegame_Scollballs().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Scollballs().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Scollballs().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Scollballs().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Scollballs().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("27")) { // color（red black）
                                        baseContext.getApp().getLivegame_Scollballs().setRedblack_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_Scollballs().setRedblack_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Scollballs().setRedblack_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_Scollballs().setRedblack_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_Scollballs().setRedblack_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }
                                } else if (data.get(i).getType2().equals(baseContext.getApp().getLivegame_MULTIPLEballs().getGame_type())) {
                                    if (data.get(i).getType3().equals("24")) { //ball big small
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("25")) { // evenodd
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("26")) { //com
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("23")) { // NUMBER
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setNumber_factor(data.get(i).getFactor());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setNumber_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setNumber_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setNumber_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getLivegame_MULTIPLEballs().setNumber_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }
                                }
                            }
                        }
                        gameContentActivity.onGetEnterLiveNumberStatus();
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseContext.showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void enterNumber() {
        final Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", baseContext.getUserInfoBean().getUser_id());
        p.put("session_id", baseContext.getUserInfoBean().getSession_id());
        Disposable subscription = getService(ApiService.class).doPostMap(WebSiteUrl.AutonumberStatus, p)
                .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String s) throws Exception {
                        NyBaseResponse<List<DigGameStateBean>> orgData;
                        orgData = gson.fromJson(s, new TypeToken<NyBaseResponse<List<DigGameStateBean>>>() {
                        }.getType());
                        List<DigGameStateBean> data = orgData.getData();
                        if (orgData.getMsg().equals("1")) {
                            boolean statu42 = false;
                            boolean statu36 = false;
                            boolean statu24 = false;
                            boolean statu12 = false;
                            boolean statu18 = false;
                            boolean statu30 = false;
                            for (int i = 0; i < data.size(); i++) {
                                if (data.get(i).getGame_name().contains("42")) {
                                    baseContext.getApp().getDignumbergame_42ball().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getDignumbergame_42ball().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getDignumbergame_42ball().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getDignumbergame_42ball().setGame_opentime(data.get(i).getOpen_time());
                                    statu42 = true;
                                    baseContext.getApp().getDignumbergame_42ball().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {

                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down < 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getDignumbergame_42ball().setCount_down(count_down);
                                    baseContext.getApp().getDignumbergame_42ball().setGameclose(i);
                                } else if (data.get(i).getGame_name().contains("36")) {
                                    baseContext.getApp().getDignumbergame_36ball().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getDignumbergame_36ball().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getDignumbergame_36ball().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getDignumbergame_36ball().setGame_opentime(data.get(i).getOpen_time());
                                    statu36 = true;
                                    baseContext.getApp().getDignumbergame_36ball().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {
                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down < 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getDignumbergame_36ball().setCount_down(count_down);
                                    baseContext.getApp().getDignumbergame_36ball().setGameclose(i);
                                } else if (data.get(i).getGame_name().contains("24")) {
                                    baseContext.getApp().getDignumbergame_24ball().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getDignumbergame_24ball().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getDignumbergame_24ball().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getDignumbergame_24ball().setGame_opentime(data.get(i).getOpen_time());
                                    statu24 = true;
                                    baseContext.getApp().getDignumbergame_24ball().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {
                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down < 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getDignumbergame_24ball().setCount_down(count_down);
                                    baseContext.getApp().getDignumbergame_24ball().setGameclose(i);
                                } else if (data.get(i).getGame_name().contains("12")) {
                                    baseContext.getApp().getDignumbergame_12ball().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getDignumbergame_12ball().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getDignumbergame_12ball().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getDignumbergame_12ball().setGame_opentime(data.get(i).getOpen_time());
                                    statu12 = true;
                                    baseContext.getApp().getDignumbergame_12ball().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {
                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down < 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getDignumbergame_12ball().setCount_down(count_down);
                                    baseContext.getApp().getDignumbergame_12ball().setGameclose(i);
                                } else if (data.get(i).getGame_name().contains("18")) {
                                    baseContext.getApp().getDignumbergame_18ball().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getDignumbergame_18ball().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getDignumbergame_18ball().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getDignumbergame_18ball().setGame_opentime(data.get(i).getOpen_time());
                                    statu18 = true;
                                    baseContext.getApp().getDignumbergame_18ball().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {
                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down < 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getDignumbergame_18ball().setCount_down(count_down);
                                    baseContext.getApp().getDignumbergame_18ball().setGameclose(i);
                                } else if (data.get(i).getGame_name().contains("30")) {
                                    baseContext.getApp().getDignumbergame_30ball().setGame_name(data.get(i).getGame_name());
                                    baseContext.getApp().getDignumbergame_30ball().setGame_type(data.get(i).getType2());
                                    baseContext.getApp().getDignumbergame_30ball().setGame_perid(data.get(i).getPeriod());
                                    baseContext.getApp().getDignumbergame_30ball().setGame_opentime(data.get(i).getOpen_time());
                                    statu30 = true;
                                    baseContext.getApp().getDignumbergame_30ball().setGame_status(true);
                                    //opentime
                                    String stropentime = data.get(i).getOpen_time();
                                    String stropenrule = data.get(i).getOpen_rule();
                                    String strnowtime = data.get(i).getNow_time();
                                    Date opentime_date = null;
                                    Date nowtime_date = null;
                                    int time_rule = Integer.valueOf(stropenrule) * 60;
                                    try {
                                        opentime_date = format.parse(stropentime);
                                        nowtime_date = format.parse(strnowtime);
                                    } catch (Exception e) {
                                    }
                                    Long nowtime = nowtime_date.getTime(); //nowtime
                                    Long opentime = opentime_date.getTime(); //opentime
                                    Long count_down = time_rule * 1000 - (nowtime - opentime); //倒计时=接受投注时间时间戳-（当前时间戳-服务器时间戳）
                                    if (count_down < 0) {
                                        count_down = Long.valueOf(0);
                                    }
                                    baseContext.getApp().getDignumbergame_30ball().setCount_down(count_down);
                                    baseContext.getApp().getDignumbergame_30ball().setGameclose(i);
                                }
                            }
                            if (statu42 == false) {
                                baseContext.getApp().getDignumbergame_42ball().setGame_status(false);
                            }
                            if (statu36 == false) {
                                baseContext.getApp().getDignumbergame_36ball().setGame_status(false);
                            }
                            if (statu24 == false) {
                                baseContext.getApp().getDignumbergame_24ball().setGame_status(false);
                            }
                            if (statu12 == false) {
                                baseContext.getApp().getDignumbergame_12ball().setGame_status(false);
                            }
                            if (statu18 == false) {
                                baseContext.getApp().getDignumbergame_18ball().setGame_status(false);
                            }
                            if (statu30 == false) {
                                baseContext.getApp().getDignumbergame_30ball().setGame_status(false);
                            }
                        }
                        return getService(ApiService.class).doPostMap(WebSiteUrl.GetGameOdd, p);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String str) throws Exception {
                        NyBaseResponse<List<DigGameOddsBean>> orgData;
                        orgData = gson.fromJson(str, new TypeToken<NyBaseResponse<List<DigGameOddsBean>>>() {
                        }.getType());
                        List<DigGameOddsBean> data = orgData.getData();
                        for (int i = 0; i < data.size(); i++) {
                            //大小
                            if (data.get(i).getType1().equals("8")) {
                                if (data.get(i).getType2().equals(baseContext.getApp().getDignumbergame_42ball().getGame_type())) {
                                    if (data.get(i).getType3().equals("37")) { // 42ball big small
                                        baseContext.getApp().getDignumbergame_42ball().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_42ball().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_42ball().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_42ball().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_42ball().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());

                                    } else if (data.get(i).getType3().equals("38")) { // evenodd
                                        baseContext.getApp().getDignumbergame_42ball().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_42ball().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_42ball().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_42ball().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_42ball().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("40")) { //clcor
                                        baseContext.getApp().getDignumbergame_42ball().setColor_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_42ball().setColor_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_42ball().setColor_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_42ball().setColor_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_42ball().setColor_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("36")) { // number
                                        baseContext.getApp().getDignumbergame_42ball().setNumber_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_42ball().setNumber_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_42ball().setNumber_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_42ball().setNumber_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_42ball().setNumber_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("39")) { // combination
                                        baseContext.getApp().getDignumbergame_42ball().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_42ball().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_42ball().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_42ball().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_42ball().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }
                                } else if (data.get(i).getType2().equals(baseContext.getApp().getDignumbergame_36ball().getGame_type())) {
                                    if (data.get(i).getType3().equals("37")) { // 36 ball big small
                                        baseContext.getApp().getDignumbergame_36ball().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_36ball().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_36ball().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_36ball().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_36ball().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("38")) { // evenodd
                                        baseContext.getApp().getDignumbergame_36ball().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_36ball().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_36ball().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_36ball().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_36ball().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("40")) { //clcor
                                        baseContext.getApp().getDignumbergame_36ball().setColor_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_36ball().setColor_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_36ball().setColor_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_36ball().setColor_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_36ball().setColor_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("36")) { // number
                                        baseContext.getApp().getDignumbergame_36ball().setNumber_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_36ball().setNumber_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_36ball().setNumber_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_36ball().setNumber_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_36ball().setNumber_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("39")) { // combination
                                        baseContext.getApp().getDignumbergame_36ball().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_36ball().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_36ball().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_36ball().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_36ball().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }
                                } else if (data.get(i).getType2().equals(baseContext.getApp().getDignumbergame_24ball().getGame_type())) {
                                    if (data.get(i).getType3().equals("37")) { // 24 ball big small
                                        baseContext.getApp().getDignumbergame_24ball().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_24ball().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_24ball().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_24ball().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_24ball().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("38")) { // evenodd
                                        baseContext.getApp().getDignumbergame_24ball().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_24ball().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_24ball().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_24ball().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_24ball().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("40")) { //clcor
                                        baseContext.getApp().getDignumbergame_24ball().setColor_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_24ball().setColor_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_24ball().setColor_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_24ball().setColor_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_24ball().setColor_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("36")) { // number
                                        baseContext.getApp().getDignumbergame_24ball().setNumber_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_24ball().setNumber_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_24ball().setNumber_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_24ball().setNumber_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_24ball().setNumber_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("39")) { // combination
                                        baseContext.getApp().getDignumbergame_24ball().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_24ball().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_24ball().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_24ball().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_24ball().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }
                                } else if (data.get(i).getType2().equals(baseContext.getApp().getDignumbergame_12ball().getGame_type())) {
                                    if (data.get(i).getType3().equals("37")) { // 24 ball big small
                                        baseContext.getApp().getDignumbergame_12ball().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_12ball().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_12ball().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_12ball().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_12ball().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("38")) { // evenodd
                                        baseContext.getApp().getDignumbergame_12ball().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_12ball().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_12ball().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_12ball().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_12ball().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("40")) { //clcor
                                        baseContext.getApp().getDignumbergame_12ball().setColor_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_12ball().setColor_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_12ball().setColor_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_12ball().setColor_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_12ball().setColor_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("36")) { // number
                                        baseContext.getApp().getDignumbergame_12ball().setNumber_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_12ball().setNumber_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_12ball().setNumber_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_12ball().setNumber_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_12ball().setNumber_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("39")) { // combination
                                        baseContext.getApp().getDignumbergame_12ball().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_12ball().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_12ball().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_12ball().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_12ball().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }

                                } else if (data.get(i).getType2().equals(baseContext.getApp().getDignumbergame_18ball().getGame_type())) {
                                    if (data.get(i).getType3().equals("37")) { // 18ball big small
                                        baseContext.getApp().getDignumbergame_18ball().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_18ball().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_18ball().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_18ball().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_18ball().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("38")) { // evenodd
                                        baseContext.getApp().getDignumbergame_18ball().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_18ball().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_18ball().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_18ball().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_18ball().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("40")) { //clcor
                                        baseContext.getApp().getDignumbergame_18ball().setColor_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_18ball().setColor_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_18ball().setColor_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_18ball().setColor_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_18ball().setColor_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("36")) { // number
                                        baseContext.getApp().getDignumbergame_18ball().setNumber_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_18ball().setNumber_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_18ball().setNumber_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_18ball().setNumber_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_18ball().setNumber_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("39")) { // combination
                                        baseContext.getApp().getDignumbergame_18ball().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_18ball().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_18ball().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_18ball().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_18ball().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }

                                } else if (data.get(i).getType2().equals(baseContext.getApp().getDignumbergame_30ball().getGame_type())) {
                                    if (data.get(i).getType3().equals("37")) { // 30ball big small
                                        baseContext.getApp().getDignumbergame_30ball().setBigsmall_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_30ball().setBigsmall_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_30ball().setBigsmall_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_30ball().setBigsmall_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_30ball().setBigsmall_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("38")) { // evenodd
                                        baseContext.getApp().getDignumbergame_30ball().setOddeven_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_30ball().setOddeven_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_30ball().setOddeven_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_30ball().setOddeven_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_30ball().setOddeven_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("40")) { //clcor
                                        baseContext.getApp().getDignumbergame_30ball().setColor_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_30ball().setColor_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_30ball().setColor_minbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_30ball().setColor_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_30ball().setColor_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("36")) { // number
                                        baseContext.getApp().getDignumbergame_30ball().setNumber_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_30ball().setNumber_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_30ball().setNumber_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_30ball().setNumber_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_30ball().setNumber_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    } else if (data.get(i).getType3().equals("39")) { // combination
                                        baseContext.getApp().getDignumbergame_30ball().setCombination_factor(data.get(i).getFactor());
                                        baseContext.getApp().getDignumbergame_30ball().setCombination_maxbet((new Double(data.get(i).getMax_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_30ball().setCombination_mixbet((new Double(data.get(i).getMin_bet())).doubleValue());
                                        baseContext.getApp().getDignumbergame_30ball().setCombination_discount(data.get(i).getDiscount());
                                        baseContext.getApp().getDignumbergame_30ball().setCombination_total((new Double(data.get(i).getMax_total())).doubleValue());
                                    }
                                }
                                //
                            }
                        }
                        gameContentActivity.onGetEnterNumberStatus();
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseContext.showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void loginSport() {
        baseContext.showBlockDialog();
        baseContext.showBlockDialog();
        baseContext.getApp().setCookie("");
        baseContext.getApp().clearLocalCollection();
        TableHttpHelper<String> loginHelper = new TableHttpHelper<String>(baseContext, null, new ThreadEndT<String>(new TypeToken<String>() {
        }.getType()) {
            @Override
            public void endT(String result, int model) {
                BallGameInfoBean ball = new BallGameInfoBean();
                ball.setIsBallGameLogin(true);
                baseContext.getApp().setBallGameInfo(ball);
                Bundle b = new Bundle();
                baseContext.getApp().setMarket("0");
                baseContext.getApp().setDataType("0");
                if (WebSiteUrl.WebId.equals("54")) {
                    b.putString(AppConfig.ACTION_KEY_INITENT_STRING, "Running");
                    baseContext.getApp().setCountryMarket("HK");
                    baseContext.getApp().setMarket("0");
                } else if (WebSiteUrl.WebId.equals("19") || WebSiteUrl.WebId.equals("36")) {
                    b.putString(AppConfig.ACTION_KEY_INITENT_STRING, "Today");
                    baseContext.getApp().setCountryMarket("ID");
                    baseContext.getApp().setMarket("0");
                } else {
                    baseContext.getApp().setMarket("0");
                    baseContext.getApp().setCountryMarket("MY");
                    b.putString(AppConfig.ACTION_KEY_INITENT_STRING, "Today");
                }
                AppTool.activiyJump(baseContext, BallGameActivity.class, b);
                baseContext.dismissBlockDialog();
            }

            @Override
            public void endString(String result, int model) {
                baseContext.dismissBlockDialog();
            }

            @Override
            public void endError(String error) {
                Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show();
                if (baseContext.getApp() != null)
                    baseContext.dismissBlockDialog();
            }
        });
        String currency = baseContext.getCurrency();
        loginHelper.login(((LoginInfoBean) AppTool.getObjectData(baseContext, "loginInfo")), currency);
    }

    private String getLanguage() {
        String lg = AppTool.getAppLanguage(baseContext);
        if (TextUtils.isEmpty(lg)) {
            return "en";
        } else {
            switch (lg) {
                case "en":
                    return "en";
                case "th":
                    return "th";
                case "vn":
                    return "vn";
                case "zh":
                    return "cn";
                case "in":
                    return "id";
                case "kr":
                    return "kr";
                case "my":
                    return "ma";
                case "kh":
                    return "ca";
                default:
                    return "en";
            }
        }
    }

    public void enterIbetPoker(String name, String lessBalance, final String gameType, View v) {
        PopIbetPokerTransfer popIbetPokerTransfer = new PopIbetPokerTransfer(baseContext, v, baseContext.screenWidth / 6 * 5, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            public void enterGame() {
                Intent intent = new Intent(baseContext, IbetPokerActivity.class);
                ContentInfoBean contentInfoBean = new ContentInfoBean();
                switch (gameType) {
                    case AppConfig.ONGDO_Poker:
                        contentInfoBean.setContent(baseContext.getString(R.string.ongdo_poker));
                        contentInfoBean.setContentId("1");
                        break;
                    case AppConfig.Poker_Poker:
                        contentInfoBean.setContent(baseContext.getString(R.string.poker_poker));
                        contentInfoBean.setContentId("3");
                        break;
                    case AppConfig.HAM_Poker:
                        contentInfoBean.setContent(baseContext.getString(R.string.ham_poker));
                        contentInfoBean.setContentId("5");
                        break;
                    case AppConfig.TIENLEN_Poker:
                        contentInfoBean.setContent(baseContext.getString(R.string.tienlen_poker));
                        contentInfoBean.setContentId("2");
                        break;
                    case AppConfig.KLA_KLOUK_Poker:
                        contentInfoBean.setContent(baseContext.getString(R.string.kla_klouk_poker));
                        contentInfoBean.setContentId("4");
                        break;
                    case AppConfig.SIKUTHAI_Poker:
                        contentInfoBean.setContent(baseContext.getString(R.string.sikuthai_poker));
                        contentInfoBean.setContentId("6");
                        break;
                    case AppConfig.Kate_Poker:
                        contentInfoBean.setContent(baseContext.getString(R.string.kate_poker));
                        contentInfoBean.setContentId("7");
                        break;
                    case AppConfig.Apoung_Poker:
                        contentInfoBean.setContent(baseContext.getString(R.string.Apoung_poker));
                        contentInfoBean.setContentId("8");
                        break;
                }
                intent.putExtra(AppConfig.Ibet567_Poker, contentInfoBean);
                baseContext.startActivity(intent);
                closePopupWindow();
            }
        };
        popIbetPokerTransfer.setPokerName(name, lessBalance);
        popIbetPokerTransfer.showPopupCenterWindow();
    }

    public void getMega888Url() {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(WebSiteUrl.Mega888AddressUrl), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) {
                Mega888AddressBean mega888AddressBean = gson.fromJson(data, Mega888AddressBean.class);
                String link = mega888AddressBean.getLink();
                if (!link.contains("http")) {
                    link = "http://" + link;
                }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(link);
                intent.setData(content_url);
                gameContentActivity.startActivity(intent);
            }
        });
    }

    public String getWe1PokerUrl() {
        //http://we1poker.k-api.com/login.php?token=&language=&web_id=&username= - 登录
        LoginInfoBean logininfobean = (LoginInfoBean) AppTool.getObjectData(gameContentActivity, "loginInfo");
        String url = "http://we1poker.k-api.com/login.php?token=" + gameContentActivity.getUserInfoBean().getSession_id() +
                "&language=" + Dig88Utils.getLanguage(gameContentActivity) + "&web_id=" + WebSiteUrl.WebId + "&username=" + logininfobean.getUsername();
        return url;
    }
}
