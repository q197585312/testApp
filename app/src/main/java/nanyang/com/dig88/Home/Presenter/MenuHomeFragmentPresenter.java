package nanyang.com.dig88.Home.Presenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import nanyang.com.dig88.Entity.BannerBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.GameMaintenanceBean;
import nanyang.com.dig88.Entity.GameStatusBean;
import nanyang.com.dig88.Entity.Gd88AgentBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.NoticeBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Home.Bean.HomeContentListBean;
import nanyang.com.dig88.Home.GameWebActivity;
import nanyang.com.dig88.Home.MenuHomeFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.ApkUtils;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.PopIbetPokerTransfer;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/6/20.
 */

public class MenuHomeFragmentPresenter extends BaseRetrofitPresenter<MenuHomeFragment> {
    public String errorImgUrl = "https://down-hk01-cn2.k-api.com/error.jpg";
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    MenuHomeFragment menuHomeFragment;

    public MenuHomeFragmentPresenter(MenuHomeFragment iBaseContext) {
        super(iBaseContext);
        menuHomeFragment = iBaseContext;
    }

    public List<HomeContentListBean> getNoLoginContentData() {
        List<HomeContentListBean> list = new ArrayList<>();
        list.add(new HomeContentListBean(menuHomeFragment.getString(R.string.sports), R.mipmap.sport, null));
        list.add(new HomeContentListBean(menuHomeFragment.getString(R.string.casino), R.mipmap.casino, null));
        list.add(new HomeContentListBean(menuHomeFragment.getString(R.string.game), R.mipmap.game, null));
        list.add(new HomeContentListBean(menuHomeFragment.getString(R.string.indonesia_lottery), R.mipmap.lottery, null));
        list.add(new HomeContentListBean(menuHomeFragment.getString(R.string.number), R.mipmap.number, null));
        list.add(new HomeContentListBean(menuHomeFragment.getString(R.string.poker), R.mipmap.poker, null));
        list.add(new HomeContentListBean(menuHomeFragment.getString(R.string.forex), R.mipmap.forex, null));
        list.add(new HomeContentListBean(menuHomeFragment.getString(R.string.promotions), R.mipmap.promotions, null));
        list.add(new HomeContentListBean(menuHomeFragment.getString(R.string.more), R.mipmap.more, null));
        return list;
    }

    public void getLoginContentData(String affiliateId, final int requestType) {//0 is Before login,1 is After login
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("affiliate_id", affiliateId);
        if (requestType == 1) {
            p.put("user_id", menuHomeFragment.getUserInfo().getUser_id());
            p.put("currency", menuHomeFragment.getCurrency());
        }
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.gameStatusUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                GameStatusBean gameStatusBean = gson.fromJson(data, GameStatusBean.class);
                List<GameStatusBean.DataBean> beanList = gameStatusBean.getData();
                List<String> allGameData = new ArrayList<>();
                for (int i = 0; i < beanList.size(); i++) {
                    GameStatusBean.DataBean dataBean = beanList.get(i);
                    String gameName = dataBean.getProvider();
                    switch (gameName) {
                        case AppConfig.AFB_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.AFB1188_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.IBC_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.SBO_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.GD_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.GR_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.AG_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.DG_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.GOLD_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.AllBet_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.SAGaming_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.WM_Casino_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.W88_Casino_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.SexyCasino_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.BEST_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.NETENT_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.HABA_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.SAgaming_Slots_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.Joker123_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.W88_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.PT_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.MG_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.FISHING_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.PP_Slots:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.RTG_Slots:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.POKER_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.KLAS_Poker:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.FOREX_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.HC_LOTTERY:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.IG_LOTTERY:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.Cockfight:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.Scr888:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.Ibet567_Poker:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.EVO_Casino_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.FFYL_POKER_GAME:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.KY_poker:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.PLAYSTAR_SLOTS:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.PG_SLOTS:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.AFB_CASINO:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.PP_CASINO:
                            handleGameStatus(allGameData, dataBean);
                            break;
                        case AppConfig.N2_CASINO:
                            handleGameStatus(allGameData, dataBean);
                            break;
                    }
                }
                if (BuildConfig.FLAVOR.equals("khmergaming") || BuildConfig.FLAVOR.equals("gasia88") || BuildConfig.FLAVOR.equals("onegold77")) {
                    allGameData.add(AppConfig.Mega888);
                }
                if (BuildConfig.FLAVOR.equals("mmbet")) {
                    allGameData.add(AppConfig.JdbSlots);
                }
                if (BuildConfig.FLAVOR.equals("khmergaming") || BuildConfig.FLAVOR.equals("afbcash")) {
                    allGameData.add(AppConfig.We1poker);
                }
                if (BuildConfig.FLAVOR.equals("kimsa1")) {
                    allGameData.add(AppConfig.Lottery_VN);
                }
                List<String> list = initSpecialGameStatus(gameStatusBean, allGameData);
                menuHomeFragment.getApp().setGameStatusList(list);
                LinkedHashMap<String, List<String>> gameStatusMap = new LinkedHashMap<>();
                if (BuildConfig.FLAVOR.equals("ibet567")) {
                    sortIbet567DataList(gameStatusMap, allGameData, AppConfig.AFB1188_GAME);
                    sortIbet567DataList(gameStatusMap, allGameData, AppConfig.GD_GAME);
                    sortIbet567DataList(gameStatusMap, allGameData, AppConfig.DG_GAME);
                    sortIbet567DataList(gameStatusMap, allGameData, AppConfig.SAGaming_GAME);
                    sortIbet567DataList(gameStatusMap, allGameData, AppConfig.SexyCasino_GAME);
                    sortIbet567DataList(gameStatusMap, allGameData, AppConfig.NEW_KENO_GAME);
                    sortIbet567DataList(gameStatusMap, allGameData, AppConfig.Cockfight);
                    sortIbet567DataList(gameStatusMap, allGameData, AppConfig.Ibet567_Poker);
                    gameStatusMap.put(AppConfig.Promotions, null);
                } else {
                    List<String> hotGameList = new ArrayList<>();
                    List<String> casinoList = new ArrayList<>();
                    List<String> lotteryList = new ArrayList<>();
                    List<String> sportList = new ArrayList<>();
                    List<String> numberList = new ArrayList<>();
                    List<String> ibetPokerList = new ArrayList<>();
                    List<String> pokerList = new ArrayList<>();
                    List<String> forexList = new ArrayList<>();
                    List<String> gameList = new ArrayList<>();
                    if (BuildConfig.FLAVOR.equals("henbet")) {
                        sortDataList(gameStatusMap, hotGameList, list, AppConfig.Hot_Game, AppConfig.SexyCasino_GAME);
                    }
                    if (BuildConfig.FLAVOR.equals("fun77")) {
                        sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.AFB1188_GAME);
                        sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.AFB_h5_GAME);
                        sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.AFB1188_Desktop_GAME);
                    } else if (BuildConfig.FLAVOR.equals("we1play")) {
                        sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.AFB1188_GAME);
                        sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.AFB_h5_GAME);
                    } else {
                        sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.AFB1188_Desktop_GAME);
                        sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.AFB1188_GAME);
                        sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.AFB_h5_Desktop_GAME);
                        sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.AFB_h5_GAME);
                        sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.AFB_GAME);
                    }
                    sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.SBO_GAME);
                    sortDataList(gameStatusMap, sportList, list, AppConfig.Sports, AppConfig.IBC_GAME);
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.AFB_CASINO);
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.GD_GAME);
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.PP_CASINO);
                    if (!BuildConfig.FLAVOR.equals("coin365bet") || (BuildConfig.FLAVOR.equals("coin365bet") && !menuHomeFragment.getCurrency().equals("LTC"))) {
                        sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.DG_GAME);
                    }
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.WM_Casino_GAME);
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.SexyCasino_GAME);
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.AG_GAME);
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.AllBet_GAME);
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.GOLD_GAME);
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.SAGaming_GAME);
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.W88_Casino_GAME);
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.EVO_Casino_GAME);
                    sortDataList(gameStatusMap, casinoList, list, AppConfig.Casino, AppConfig.N2_CASINO);
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.PG_SLOTS);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.NETENT_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.PLAYSTAR_SLOTS);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.PP_Slots);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.RTG_Slots);
                        if (!menuHomeFragment.getCurrency().equals("IDR")) {
                            sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.Scr888);
                        }
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.BEST_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.MG_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.HABA_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.W88_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.Joker123_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.PT_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.SAgaming_Slots_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.Mega888);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.JdbSlots);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.Xe88_Slot);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.FISHING_GAME);
                    } else {
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.BEST_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.HABA_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.W88_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.MG_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.PT_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.Joker123_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.SAgaming_Slots_GAME);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.PP_Slots);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.RTG_Slots);
                        if (!BuildConfig.FLAVOR.equals("kimsa1") && !BuildConfig.FLAVOR.equals("uplay365") && !BuildConfig.FLAVOR.equals("henbet")) {
                            if (BuildConfig.FLAVOR.equals("mmbet") || BuildConfig.FLAVOR.equals("dig88")) {
                                sortIbet567DataList(gameStatusMap, allGameData, AppConfig.FISHING_GAME);
                            } else {
                                sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.FISHING_GAME);
                            }
                        }
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.Scr888);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.Mega888);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.JdbSlots);
                        sortDataList(gameStatusMap, gameList, list, AppConfig.Game, AppConfig.Xe88_Slot);
                    }

                    if (!BuildConfig.FLAVOR.equals("fun77") || (BuildConfig.FLAVOR.equals("fun77") && !menuHomeFragment.getCurrency().equals("USD"))) {
                        if (BuildConfig.FLAVOR.equals("coin365bet") && requestType == 1) {
                            if (menuHomeFragment.getCurrency().equals("IDR")) {
                                sortDataList(gameStatusMap, lotteryList, list, AppConfig.Lottery, AppConfig.LOTTERY_GAME);
                            } else if (menuHomeFragment.getCurrency().equals("THB")) {
                                sortDataList(gameStatusMap, lotteryList, list, AppConfig.Lottery, AppConfig.THAI_LOTTERY);
                            }
                        } else {
                            sortDataList(gameStatusMap, lotteryList, list, AppConfig.Lottery, AppConfig.LOTTERY_GAME);
                            sortDataList(gameStatusMap, lotteryList, list, AppConfig.Lottery, AppConfig.THAI_LOTTERY);
                        }
                        sortDataList(gameStatusMap, lotteryList, list, AppConfig.Lottery, AppConfig.LOTTERY_4D);
                        if (BuildConfig.FLAVOR.equals("khmergaming")) {
                            if (menuHomeFragment.getCurrency().equals("CNY")) {
                                sortDataList(gameStatusMap, lotteryList, list, AppConfig.Lottery, AppConfig.IG_LOTTERY);
                                sortDataList(gameStatusMap, lotteryList, list, AppConfig.Lottery, AppConfig.HC_LOTTERY);
                            }
                        }
                    }
                    if (!BuildConfig.FLAVOR.equals("fun77") || (BuildConfig.FLAVOR.equals("fun77") && !menuHomeFragment.getCurrency().equals("USD"))) {
                        sortDataList(gameStatusMap, numberList, list, AppConfig.Number, AppConfig.NUMBER_GAME);
                        sortDataList(gameStatusMap, numberList, list, AppConfig.Number, AppConfig.LIVE_NUMBER_GAME);
                    }
                    sortDataList(gameStatusMap, numberList, list, AppConfig.Number, AppConfig.KENO_GAME);
                    sortDataList(gameStatusMap, numberList, list, AppConfig.Number, AppConfig.NEW_KENO_GAME);
                    if (BuildConfig.FLAVOR.equals("henbet")) {
                        sortIbet567DataList(gameStatusMap, allGameData, AppConfig.FISHING_GAME);
                    }
//                    if (BuildConfig.FLAVOR.equals("mmbet") || BuildConfig.FLAVOR.equals("dig88") || BuildConfig.FLAVOR.equals("fun77") ||
//                            BuildConfig.FLAVOR.equals("kbet789") || BuildConfig.FLAVOR.equals("afbcash") || BuildConfig.FLAVOR.equals("henbet")) {
//                        sortIbet567DataList(gameStatusMap, allGameData, AppConfig.Cockfight);
//                    } else {
//                        sortDataList(gameStatusMap, numberList, list, AppConfig.Number, AppConfig.Cockfight);
//                    }
                    if (!TextUtils.isEmpty(menuHomeFragment.getCurrency())) {
                        if (menuHomeFragment.getCurrency().equals("USD") || menuHomeFragment.getLocalLanguage().equals("kh") ||
                                menuHomeFragment.getCurrency().equals("IDR") || menuHomeFragment.getLocalLanguage().equals("in")) {
                            sortDataList(gameStatusMap, numberList, list, AppConfig.Number, AppConfig.Cockfight);
                            numberList.add(AppConfig.Cockfight);
                            gameStatusMap.put(AppConfig.Cockfight, numberList);
                        }
                    }
                    if (BuildConfig.FLAVOR.equals("kimsa1")) {
                        gameStatusMap.put(AppConfig.Cockfight_X, null);
                        sortIbet567DataList(gameStatusMap, allGameData, AppConfig.FISHING_GAME);
                        sortDataList(gameStatusMap, lotteryList, list, AppConfig.Lottery, AppConfig.Lottery_VN);
                    }
                    if (!BuildConfig.FLAVOR.equals("fun77") || (BuildConfig.FLAVOR.equals("fun77") && !menuHomeFragment.getCurrency().equals("USD"))) {
                        sortDataList(gameStatusMap, pokerList, list, AppConfig.Poker, AppConfig.We1poker);
                        sortDataList(gameStatusMap, pokerList, list, AppConfig.Poker, AppConfig.POKER_GAME);
                        sortDataList(gameStatusMap, pokerList, list, AppConfig.Poker, AppConfig.KLAS_Poker);
                        if ((BuildConfig.FLAVOR.equals("khmergaming") || BuildConfig.FLAVOR.equals("dig88")) && menuHomeFragment.getCurrency().equals("USD")) {
                            sortDataList(gameStatusMap, pokerList, list, AppConfig.Poker, AppConfig.Ibet567_Poker);
                        }
                        sortDataList(gameStatusMap, pokerList, list, AppConfig.Poker, AppConfig.FFYL_POKER_GAME);
                    }
                    sortDataList(gameStatusMap, pokerList, list, AppConfig.Poker, AppConfig.KY_poker);
                    if (BuildConfig.FLAVOR.equals("afbcash")) {
                        if (menuHomeFragment.getCurrency().equals("USD") || menuHomeFragment.getCurrency().equals("MYR")) {
                            sortDataList(gameStatusMap, pokerList, list, AppConfig.Poker, AppConfig.Ibet567_Poker);
                        }
                    }
                    if (!BuildConfig.FLAVOR.equals("fun77") || (BuildConfig.FLAVOR.equals("fun77") && !menuHomeFragment.getCurrency().equals("USD"))) {
                        if (!BuildConfig.FLAVOR.equals("u2bet")) {
                            sortDataList(gameStatusMap, forexList, list, AppConfig.Forex, AppConfig.FOREX_GAME);
                        }
                    }
                    if (BuildConfig.FLAVOR.equals("uplay365")) {
                        sortIbet567DataList(gameStatusMap, allGameData, AppConfig.FISHING_GAME);
                        gameStatusMap.put(AppConfig.LiveChat, null);
                    }
                    gameStatusMap.put(AppConfig.Promotions, null);
//                    if (BuildConfig.FLAVOR.equals("mgold1")) {
//                        gameStatusMap.put(AppConfig.LiveScore, null);
//                    }
                    if (BuildConfig.FLAVOR.equals("jf58")) {
                        gameStatusMap.put(AppConfig.News, null);
                    }
                    if (!BuildConfig.FLAVOR.equals("mgold1") && !BuildConfig.FLAVOR.equals("dig88") && !BuildConfig.FLAVOR.equals("afbcash")) {
                        gameStatusMap.put(AppConfig.More, null);
                    }
                    if (BuildConfig.FLAVOR.equals("lct99")) {
                        gameStatusMap.put(AppConfig.LiveChat, null);
                    }
                }
                List<HomeContentListBean> homeContentListBeanList = new ArrayList<>();
                Iterator<Map.Entry<String, List<String>>> iterator = gameStatusMap.entrySet().iterator();
                Dig88Utils.setLang(menuHomeFragment.getContext());
                while (iterator.hasNext()) {
                    HomeContentListBean bean = new HomeContentListBean();
                    Map.Entry<String, List<String>> next = iterator.next();
                    String key = next.getKey();
                    List<String> value = next.getValue();
                    switch (key) {
                        case AppConfig.Hot_Game:
                            bean.setGameName(menuHomeFragment.getString(R.string.Hot_Games));
                            bean.setGamePic(R.mipmap.number);
                            break;
                        case AppConfig.Casino:
                            bean.setGameName(menuHomeFragment.getString(R.string.casino));
                            bean.setGamePic(R.mipmap.casino);
                            break;
                        case AppConfig.Lottery:
                            bean.setGameName(menuHomeFragment.getString(R.string.indonesia_lottery));
                            bean.setGamePic(R.mipmap.lottery);
                            break;
                        case AppConfig.Sports:
                            bean.setGameName(menuHomeFragment.getString(R.string.sports));
                            bean.setGamePic(R.mipmap.sport);
                            break;
                        case AppConfig.Number:
                            String name = menuHomeFragment.getString(R.string.number);
                            int gamePic = R.mipmap.number;
                            if (BuildConfig.FLAVOR.equals("fun77") && menuHomeFragment.getCurrency().equals("USD")) {
                                name = "Keno";
                                gamePic = R.mipmap.home_keno;
                            } else if (BuildConfig.FLAVOR.equals("uplay365")) {
                                name = menuHomeFragment.getString(R.string.keno);
                            }
                            bean.setGameName(name);
                            bean.setGamePic(gamePic);
                            break;
                        case AppConfig.Poker:
                            String poker = menuHomeFragment.getString(R.string.poker);
                            if (BuildConfig.FLAVOR.equals("dig88")) {
                                poker = menuHomeFragment.getString(R.string.dig88_poker);
                            }
                            bean.setGameName(poker);
                            bean.setGamePic(R.mipmap.poker);
                            break;
                        case AppConfig.Forex:
                            bean.setGamePic(R.mipmap.forex);
                            bean.setGameName(menuHomeFragment.getString(R.string.forex));
                            break;
                        case AppConfig.Promotions:
                            bean.setGameName(menuHomeFragment.getString(R.string.promotions));
                            bean.setGamePic(R.mipmap.promotions);
                            break;
                        case AppConfig.LiveScore:
                            bean.setGameName(menuHomeFragment.getString(R.string.live_score));
                            bean.setGamePic(R.mipmap.live_score);
                            break;
                        case AppConfig.LiveChat:
                            bean.setGameName("LiveChat");
                            bean.setGamePic(R.mipmap.live_chat);
                            break;
                        case AppConfig.News:
                            bean.setGameName(menuHomeFragment.getString(R.string.news));
                            bean.setGamePic(R.mipmap.news);
                            break;
                        case AppConfig.Game:
                            bean.setGameName(menuHomeFragment.getString(R.string.game));
                            bean.setGamePic(R.mipmap.game);
                            break;
                        case AppConfig.More:
                            bean.setGameName(menuHomeFragment.getString(R.string.more));
                            bean.setGamePic(R.mipmap.more);
                            break;
                        case AppConfig.AFB1188_GAME:
                            bean.setGameName(menuHomeFragment.getString(R.string.afb1188_sports));
                            bean.setGamePic(R.mipmap.sport_afb1188);
                            break;
                        case AppConfig.GD_GAME:
                            bean.setGameName(menuHomeFragment.getString(R.string.gd_live_entertainment));
                            bean.setGamePic(R.mipmap.casino_gd);
                            break;
                        case AppConfig.DG_GAME:
                            bean.setGameName(menuHomeFragment.getString(R.string.dg_live_entertainment));
                            bean.setGamePic(R.mipmap.casino_dg);
                            break;
                        case AppConfig.SAGaming_GAME:
                            bean.setGameName(menuHomeFragment.getString(R.string.sa_live_entertainment));
                            bean.setGamePic(R.mipmap.casino_sa);
                            break;
                        case AppConfig.SexyCasino_GAME:
                            bean.setGameName(menuHomeFragment.getString(R.string.sexy_live_entertainment));
                            bean.setGamePic(R.mipmap.casino_sexy);
                            break;
                        case AppConfig.NEW_KENO_GAME:
                            bean.setGameName(menuHomeFragment.getString(R.string.keno));
                            bean.setGamePic(R.mipmap.new_keno);
                            break;
                        case AppConfig.Cockfight:
                            bean.setGameName(menuHomeFragment.getString(R.string.Cockfight));
                            if (BuildConfig.FLAVOR.equals("afbcash")) {
                                if (menuHomeFragment.getCurrency().equals("IDR") || menuHomeFragment.getLocalLanguage().equals("in")) {
                                    bean.setGameName("Sabung Ayam");
                                }
                            }
                            bean.setGamePic(R.mipmap.cockfight);
                            break;
                        case AppConfig.ONGDO_Poker:
                            bean.setGameName(menuHomeFragment.getString(R.string.ongdo_poker));
                            bean.setGamePic(R.mipmap.ongdo_poker);
                            break;
                        case AppConfig.Poker_Poker:
                            bean.setGameName(menuHomeFragment.getString(R.string.poker_poker));
                            bean.setGamePic(R.mipmap.poker_poker);
                            break;
                        case AppConfig.HAM_Poker:
                            bean.setGameName(menuHomeFragment.getString(R.string.ham_poker));
                            bean.setGamePic(R.mipmap.ham_poker);
                            break;
                        case AppConfig.TIENLEN_Poker:
                            bean.setGameName(menuHomeFragment.getString(R.string.tienlen_poker));
                            bean.setGamePic(R.mipmap.tienlen_poker);
                            break;
                        case AppConfig.KLA_KLOUK_Poker:
                            bean.setGameName(menuHomeFragment.getString(R.string.kla_klouk_poker));
                            bean.setGamePic(R.mipmap.kla_klouk_poker);
                            break;
                        case AppConfig.SIKUTHAI_Poker:
                            bean.setGameName(menuHomeFragment.getString(R.string.sikuthai_poker));
                            bean.setGamePic(R.mipmap.sikuthai_poker);
                            break;
                        case AppConfig.FISHING_GAME:
                            bean.setGameName(menuHomeFragment.getString(R.string.fish_game_slots));
                            bean.setGamePic(R.mipmap.game_fish);
                            break;
                        case AppConfig.Ibet567_Poker:
                            bean.setGameName("KH Gaming");
                            bean.setGamePic(R.mipmap.ongdo_poker);
                            break;
                        case AppConfig.Cockfight_X:
                            bean.setGameName(menuHomeFragment.getString(R.string.Cockfight));
                            bean.setGamePic(R.mipmap.cockfight_x);
                            break;
                    }
                    bean.setGameType(key);
                    bean.setGameList(value);
                    homeContentListBeanList.add(bean);
                }
                if (requestType == 0) {
                    menuHomeFragment.onGetNoLoginContentData(homeContentListBeanList);
                } else {
                    menuHomeFragment.onGetAlreadyLoginContentData(homeContentListBeanList);
                }
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    private LinkedHashMap<String, List<String>> sortIbet567DataList(LinkedHashMap<String, List<String>> gameStatusMap, List<String> allGameStatus, String gameType) {
        for (int i = 0; i < allGameStatus.size(); i++) {
            String gameName1 = allGameStatus.get(i);
            if (gameName1.equals(gameType)) {
                if (gameName1.equals(AppConfig.Ibet567_Poker)) {
                    gameStatusMap.put(AppConfig.ONGDO_Poker, null);
                    gameStatusMap.put(AppConfig.Poker_Poker, null);
                    gameStatusMap.put(AppConfig.HAM_Poker, null);
                    gameStatusMap.put(AppConfig.TIENLEN_Poker, null);
                    gameStatusMap.put(AppConfig.KLA_KLOUK_Poker, null);
                    gameStatusMap.put(AppConfig.SIKUTHAI_Poker, null);
                } else {
                    gameStatusMap.put(gameType, null);
                }
                break;
            }
        }

        return gameStatusMap;
    }

    private LinkedHashMap<String, List<String>> sortDataList(LinkedHashMap<String, List<String>> gameStatusMap, List<String> gameList, List<String> allGameStatus, String gameType, String gameName) {
        for (int i = 0; i < allGameStatus.size(); i++) {
            String gameName1 = allGameStatus.get(i);
            if (gameName1.equals(gameName)) {
                if (gameName1.equals(AppConfig.Ibet567_Poker)) {
                    gameList.add(AppConfig.ONGDO_Poker);
                    gameList.add(AppConfig.Poker_Poker);
                    gameList.add(AppConfig.HAM_Poker);
                    gameList.add(AppConfig.TIENLEN_Poker);
                    gameList.add(AppConfig.KLA_KLOUK_Poker);
                    gameList.add(AppConfig.SIKUTHAI_Poker);
                    gameList.add(AppConfig.Kate_Poker);
                    gameList.add(AppConfig.Apoung_Poker);
                    gameStatusMap.put(gameType, gameList);
                } else {
                    gameList.add(gameName);
                    gameStatusMap.put(gameType, gameList);
                }
                break;
            }
        }
        return gameStatusMap;
    }

    private List<String> initSpecialGameStatus(GameStatusBean gameStatusBean, List<String> allGameData) {
        List<String> newKenoListStateList = new ArrayList<>();
        for (int i = 0; i < gameStatusBean.getData().size(); i++) {
            GameStatusBean.DataBean dataBean = gameStatusBean.getData().get(i);
            String gameType = dataBean.getGametype();
            String status = dataBean.getStatus();
            String provider = dataBean.getProvider();
            if (TextUtils.isEmpty(gameType)) {
                continue;
            }
            if (gameType.equals(AppConfig.NEW_KENO_GAME)) {
                if (TextUtils.isEmpty(provider)) {
                    continue;
                } else {
                    String keno4 = "NK-4MIN";
                    String keno3 = "NK-3MIN";
                    String keno2 = "NK-2MIN";
                    if (WebSiteUrl.WebId.equals("33") || WebSiteUrl.WebId.equals("52") || WebSiteUrl.WebId.equals("99") || WebSiteUrl.WebId.equals("135") || WebSiteUrl.WebId.equals("194")) {
                        keno4 = "web_id-NK";
                        keno3 = "web_id_3MIN";
                        keno2 = "web_id_2MIN";
                    }
                    if (provider.equals(keno4) || provider.equals(keno3) || provider.equals(keno2)) {
                        if (status.equals("1")) {
                            if (!allGameData.contains(AppConfig.NEW_KENO_GAME)) {
                                allGameData.add(AppConfig.NEW_KENO_GAME);
                            }
                            if (provider.equals(keno4)) {
                                newKenoListStateList.add(keno4);
                            }
                            if (provider.equals(keno3)) {
                                newKenoListStateList.add(keno3);
                            }
                            if (provider.equals(keno2)) {
                                newKenoListStateList.add(keno2);
                            }
                        }
                    }
                }
            }
            if (gameType.equals(AppConfig.LOTTERY_4D) && status.equals("1") && !allGameData.contains(AppConfig.LOTTERY_4D)) {
                allGameData.add(AppConfig.LOTTERY_4D);
            }
            if (gameType.equals(AppConfig.THAI_LOTTERY) && status.equals("1") && !allGameData.contains(AppConfig.THAI_LOTTERY)) {
                allGameData.add(AppConfig.THAI_LOTTERY);
            }
            if (gameType.equals(AppConfig.LOTTERY_GAME) && status.equals("1") && !allGameData.contains(AppConfig.LOTTERY_GAME)) {
                allGameData.add(AppConfig.LOTTERY_GAME);
            }
            if (gameType.equals(AppConfig.LIVE_NUMBER_GAME) && status.equals("1") && !allGameData.contains(AppConfig.LIVE_NUMBER_GAME)) {
                allGameData.add(AppConfig.LIVE_NUMBER_GAME);
            }
            if (gameType.equals(AppConfig.NUMBER_GAME) && status.equals("1") && !allGameData.contains(AppConfig.NUMBER_GAME)) {
                allGameData.add(AppConfig.NUMBER_GAME);
            }
            if (gameType.equals(AppConfig.KENO_GAME) && status.equals("1") && !allGameData.contains(AppConfig.KENO_GAME)) {
                allGameData.add(AppConfig.KENO_GAME);
            }
//            if (allGameData.contains(AppConfig.LOTTERY_4D) && allGameData.contains(AppConfig.THAI_LOTTERY) &&
//                    allGameData.contains(AppConfig.LOTTERY_GAME) && allGameData.contains(AppConfig.LIVE_NUMBER_GAME) &&
//                    allGameData.contains(AppConfig.NUMBER_GAME) && allGameData.contains(AppConfig.KENO_GAME) &&
//                    allGameData.contains(AppConfig.NEW_KENO_GAME)) {
//                break;
//            }
        }
        menuHomeFragment.getApp().setNewKenoStatusList(newKenoListStateList);
        return allGameData;
    }

    private void handleGameStatus(List<String> allGameData, GameStatusBean.DataBean dataBean) {
        String gameStatus = dataBean.getStatus();
        if (gameStatus.equals("1")) {
            String gameName = dataBean.getProvider();
            if (gameName.equals(AppConfig.AFB_GAME)) {
                if (BuildConfig.FLAVOR.equals("gasia88") || BuildConfig.FLAVOR.equals("fun77") || BuildConfig.FLAVOR.equals("we1play") ||
                        BuildConfig.FLAVOR.equals("jf58") || BuildConfig.FLAVOR.equals("bbet99") || BuildConfig.FLAVOR.equals("uplay365")) {
                    allGameData.add(AppConfig.AFB_h5_GAME);
                } else if (BuildConfig.FLAVOR.equals("afbcash")) {
                    allGameData.add(gameName);
                    allGameData.add(AppConfig.AFB_h5_Desktop_GAME);
                } else {
                    allGameData.add(gameName);
                    allGameData.add(AppConfig.AFB_h5_GAME);
                }
            } else if (gameName.equals(AppConfig.AFB1188_GAME)) {
                if (BuildConfig.FLAVOR.equals("afbcash") || BuildConfig.FLAVOR.equals("fun77")) {
                    allGameData.add(AppConfig.AFB1188_GAME);
                    allGameData.add(AppConfig.AFB1188_Desktop_GAME);
                } else {
                    allGameData.add(AppConfig.AFB1188_GAME);
                }
            } else if (gameName.equals(AppConfig.Scr888)) {
                allGameData.add(gameName);
                if (menuHomeFragment.getCurrency().equals("IDR") || menuHomeFragment.getCurrency().equals("MYR") || menuHomeFragment.getCurrency().equals("THB")) {
                    if (BuildConfig.FLAVOR.equals("khmergaming") || BuildConfig.FLAVOR.equals("mcd88") || BuildConfig.FLAVOR.equals("afbcash")) {
                        allGameData.add(AppConfig.Xe88_Slot);
                    }
                }
            } else {
                allGameData.add(gameName);
            }
        }
    }

    public int switchLanguage() {
        String localLanguage = baseContext.getLocalLanguage();
        switch (localLanguage) {
            case "zh":
                return R.drawable.lang_zh_flag;
            case "en":
                return R.drawable.lang_en_flag;
            case "kh":
                return R.drawable.lang_ka_flag;
            case "in":
                return R.drawable.lang_in_flag;
            case "ms":
                return R.drawable.lang_my_flag;
            case "th":
                return R.drawable.lang_th_flag;
            case "kr":
                return R.drawable.lang_kr_flag;
            case "vn":
                return R.drawable.lang_vn_flag;
            case "de":
                return R.drawable.lang_de_flag;
            case "sq":
                return R.drawable.lang_sq_flag;
            case "tr":
                return R.drawable.lang_tr_flag;
            case "mk":
                return R.drawable.lang_mk_flag;
            case "my":
                return R.drawable.lang_mm_flag;
            default:
                return R.drawable.lang_en_flag;
        }
    }

    public void getNoticeData() {
        String param = "&web_id=" + WebSiteUrl.WebId + "&lang=" + getLanguage();
        if (BuildConfig.FLAVOR.equals("f2bet")) {
            param += "&type=3";
        }
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(WebSiteUrl.BulletinUrl + param), new BaseConsumer<String>(baseContext) {
                    @Override
                    protected void onBaseGetData(String data) throws JSONException {
                        NoticeBean noticeBean = gson.fromJson(data, NoticeBean.class);
                        if (!TextUtils.isEmpty(noticeBean.getData())) {
                            String noticeContent = noticeBean.getData();
                            String[] split = noticeContent.split("&");
                            if (split.length > 0) {
                                menuHomeFragment.onGetNoticeData(split[0]);
                            }
                        }
                    }

                    @Override
                    protected void onAccept() {
//                        super.onAccept();
                    }
                }
        );
    }

    public List<Object> getLocalBannerData() {
        List<Object> bannerList = new ArrayList<>();
        if (BuildConfig.FLAVOR.equals("hjlh6688")) {
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.hjlh6688_banner_01));
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.hjlh6688_banner_02));
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.hjlh6688_banner_03));
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.hjlh6688_banner_04));
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.hjlh6688_banner_05));
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.hjlh6688_banner_06));
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.hjlh6688_banner_07));
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.hjlh6688_banner_08));
        } else if (BuildConfig.FLAVOR.equals("bb88sbet") || BuildConfig.FLAVOR.equals("win3888")) {
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.bb88sbet_banner_1));
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.bb88sbet_banner_2));
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.bb88sbet_banner_3));
        } else if (BuildConfig.FLAVOR.equals("k9th")) {
            if (menuHomeFragment.getLocalLanguage().equals("kh") || menuHomeFragment.getLocalLanguage().equals("vn")) {
                bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.k9kh_banner_1));
                bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.k9kh_banner_2));
                bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.k9kh_banner_3));
                bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.k9kh_banner_2));
            }
        } else if (BuildConfig.FLAVOR.equals("lct99")) {
            bannerList.add(Uri.parse("https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/lct99/banners/1.jpg"));
            bannerList.add(Uri.parse("https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/lct99/banners/2.jpg"));
            bannerList.add(Uri.parse("https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/lct99/banners/3.jpg"));
            bannerList.add(Uri.parse("https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/lct99/banners/4.jpg"));
        } else if (BuildConfig.FLAVOR.equals("henbet")) {
            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.banner3));
        } else {
//            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.banner1));
//            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.banner2));
//            bannerList.add(Uri.parse("res://mipmap-xhdpi/" + R.mipmap.banner3));
            bannerList.add(errorImgUrl);
        }
        return bannerList;
    }

    public void getBannerData() {
        String lg = getLanguage();
        if (BuildConfig.FLAVOR.equals("fun77")) {
            if (lg.equals("en")) {
                lg = "ca";
            }
            if (menuHomeFragment.hasLoginInfo()) {
                if (menuHomeFragment.getCurrency().equals("USD")) {
                    lg = "ca";
                }
            }
        } else if (BuildConfig.FLAVOR.equals("sbobet")) {
            lg = "id";
        } else if (BuildConfig.FLAVOR.equals("omi88")) {
            lg = "cn";
        }
        String param = "&web_id=" + WebSiteUrl.WebId + "&lang=" + lg + "&type=banner";
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(WebSiteUrl.BannerUrl + param), new BaseConsumer<String>(baseContext) {
                    @Override
                    protected void onBaseGetData(String data) throws JSONException {
                        List<Object> bannerList = new ArrayList<>();
                        if (data.contains("\"msg\":\"-1\",\"data\":\"0\"")) {
                            bannerList.addAll(getLocalBannerData());
                        } else {
                            BannerBean bannerBean = gson.fromJson(data, BannerBean.class);
                            List<BannerBean.DataBean> dataList = bannerBean.getData();
                            for (int i = 0; i < dataList.size(); i++) {
                                BannerBean.DataBean dataBean = dataList.get(i);
                                String type = "1";
                                if (BuildConfig.FLAVOR.equals("ibet567") || BuildConfig.FLAVOR.equals("mmbet") ||
                                        BuildConfig.FLAVOR.equals("hjlh6688") || BuildConfig.FLAVOR.equals("xslot88") ||
                                        BuildConfig.FLAVOR.equals("f2bet")) {
                                    type = "2";
                                }
                                if (dataBean.getType().equals(type)) {
                                    bannerList.add(dataBean.getUrl());
                                }
                            }
                        }
                        menuHomeFragment.onGetBannerData(bannerList);
                    }

                    @Override
                    protected void onAccept() {
//                        super.onAccept();
                    }
                }
        );
    }


    public String getForexUrl(String sessionId) {
        StringBuilder builder = new StringBuilder();
        builder.append("https://outweb094.khmergaming.com/index.php?gameid=forex");
        AppTool.setAppLanguage(baseContext.getContext(), "");
        String lan = AppTool.getAppLanguage(baseContext.getContext());
        String lg = "en";
        if (lan.equals("zh") || lan.equals("zh_TW")) {
            lg = "cn";
        }
        builder.append("&lang=");
        builder.append(lg);
        builder.append("&token=" + sessionId);
        builder.append("&moblie=1");
        return builder.toString();
    }

    public String getLiveScoreUrl() {
        String url = "http://m.1mgold.com/index.php?page=livescore&livescore&from=app";
        return url;
    }

    public String getLiveChatUrl() {
        String url = "https://tawk.to/chat/5dee57a443be710e1d2140ef/default?$_tawk_sk=5e153d68db32caaecb82d98a&$_tawk_tk=547a4379bba83ff19f19127d1cb27d50&v=680";
        return url;
    }

    public String getNewsUrl() {
        String url = "https://m.jf58.net/index.php?page=latest-news&from=app";
        return url;
    }

    public String getPromotionsUrl() {
        String url = BuildConfig.PromotionsUrl;
        if (BuildConfig.FLAVOR.equals("k9th")) {
            if (getLanguage().equals("in")) {
                url = "http://m.k9id-v2.com/index.php?page=promotion&from=app" + "&set_lang=" + getLanguage();
            } else if (getLanguage().equals("kh")) {
                url = "http://m.k9winkh.com/index.php?page=promotion&from=app&set_lang=kh";
            } else {
                url += "&set_lang=" + getLanguage();
            }
        } else {
            url += "&set_lang=" + getLanguage();
        }
        return url;
    }

    private String getLanguage() {
        return Dig88Utils.getLanguage(menuHomeFragment.getContext());
    }

    public void openUrl(String gameUrl) {
        if (gameUrl.startsWith("http")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(gameUrl);
            intent.setData(content_url);
            baseContext.startActivity(intent);
        }
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
                            Exception exception = new Exception(menuHomeFragment.getString(R.string.System_maintenance));
                            throw exception;
                        } else {
                            LoginInfoBean logininfobean = (LoginInfoBean) AppTool.getObjectData(menuHomeFragment.getContext(), "loginInfo");
                            String platFrom = "mobile";
                            if (gameType.equals(AppConfig.AFB1188_Desktop_GAME)) {
                                platFrom = "PC";
                            }
                            String p = "web_id=" + WebSiteUrl.WebId + "&username=" + logininfobean.getUsername() + "&language=" + getLanguage() +
                                    "&token=" + menuHomeFragment.getUserInfo().getSession_id() + "&from=app" + "&platfrom=" + platFrom;
                            return getService(ApiService.class).getData(WebSiteUrl.Sport1188LimitUrl + p);
                        }
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String data) throws Exception {
                        Afb1188LoginBean afb1188LoginBean = gson.fromJson(data, Afb1188LoginBean.class);
                        Intent intent = new Intent(menuHomeFragment.getContext(), GameWebActivity.class);
                        intent.putExtra("url", afb1188LoginBean.getLoginURL());
                        intent.putExtra("title", menuHomeFragment.getString(R.string.afb1188_sports));
                        menuHomeFragment.startActivity(intent);
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
        String param = "&web_id=" + WebSiteUrl.WebId + "&currency=" + menuHomeFragment.getCurrency();
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(WebSiteUrl.Gd88H5AgentIDUrl + param), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                Gd88AgentBean gd88AgentBean = gson.fromJson(data, Gd88AgentBean.class);
                LoginInfoBean info = (LoginInfoBean) AppTool.getObjectData(menuHomeFragment.getContext(), "loginInfo");
                String url = WebSiteUrl.Gd88H5LoginUrl;
                String p = "web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&currency=" + menuHomeFragment.getCurrency() +
                        "&agentID=" + gd88AgentBean.getData().getAgent() + "&language=" + Dig88Utils.getLanguage(menuHomeFragment.getContext()) +
                        "&balance=" + menuHomeFragment.getUserInfo().getMoneyBalance().getBalance() + "&gameType=0&lobbyId=0";
                Intent intent = new Intent(menuHomeFragment.getContext(), Gd88WebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("parmas", p);
                menuHomeFragment.startActivity(intent);
            }
        });
    }

    public void enterIbetPoker(String name, String lessBalance, final String gameType, View v) {
        PopIbetPokerTransfer popIbetPokerTransfer = new PopIbetPokerTransfer(baseContext.getContext(), v, baseContext.screenWidth / 6 * 5, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            public void enterGame() {
                Intent intent = new Intent(baseContext.getContext(), IbetPokerActivity.class);
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
                }
                intent.putExtra(AppConfig.Ibet567_Poker, contentInfoBean);
                baseContext.startActivity(intent);
                closePopupWindow();
            }
        };
        popIbetPokerTransfer.setPokerName(name, lessBalance);
        popIbetPokerTransfer.showPopupCenterWindow();
    }

    public void getVipInfoBean(UserInfoBean userInfoBean) {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", userInfoBean.getUser_id());
        p.put("session_id", userInfoBean.getSession_id());
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.MemberInfoSubmitter, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<VipInfoBean> dataBean = gson.fromJson(data, new TypeToken<NyBaseResponse<VipInfoBean>>() {
                }.getType());
                VipInfoBean vipInfoBean = dataBean.getData();
                AppTool.saveObjectData(baseContext.getBaseActivity(), "vipInfo", vipInfoBean);
            }
        });
    }
}