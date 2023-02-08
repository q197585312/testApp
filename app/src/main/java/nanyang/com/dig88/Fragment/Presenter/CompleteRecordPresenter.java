package nanyang.com.dig88.Fragment.Presenter;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.Afb1188StatementBean;
import nanyang.com.dig88.Entity.AfbSportsStatementBean;
import nanyang.com.dig88.Entity.AgStatementBean;
import nanyang.com.dig88.Entity.AllBetStatementBean;
import nanyang.com.dig88.Entity.BestGameStatementBean;
import nanyang.com.dig88.Entity.CockfightStatementBean;
import nanyang.com.dig88.Entity.CompleteReportFormBean;
import nanyang.com.dig88.Entity.Dg99StatementBean;
import nanyang.com.dig88.Entity.EvoStatementBean;
import nanyang.com.dig88.Entity.FFYLStatementBean;
import nanyang.com.dig88.Entity.ForexStatementBean;
import nanyang.com.dig88.Entity.GoldStatementBean;
import nanyang.com.dig88.Entity.HabaStatementBean;
import nanyang.com.dig88.Entity.JokerGameStatementBean;
import nanyang.com.dig88.Entity.Lottery4DStatementBean;
import nanyang.com.dig88.Entity.MgSlotsStatementBean;
import nanyang.com.dig88.Entity.NewKenoStatementBean;
import nanyang.com.dig88.Entity.PPlayStatementBean;
import nanyang.com.dig88.Entity.PtStatementBean;
import nanyang.com.dig88.Entity.SagamingStatementBean;
import nanyang.com.dig88.Entity.SexyCasinoStatementBean;
import nanyang.com.dig88.Entity.StatementInfoBean;
import nanyang.com.dig88.Entity.W88StatementBean;
import nanyang.com.dig88.Fragment.CompleteRecordFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/7/8.
 */

public class CompleteRecordPresenter extends BaseRetrofitPresenter<CompleteRecordFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    CompleteRecordFragment completeRecordFragment;

    public CompleteRecordPresenter(CompleteRecordFragment iBaseContext) {
        super(iBaseContext);
        completeRecordFragment = iBaseContext;
    }

    public List<StatementInfoBean> getGameContentList() {
        List<StatementInfoBean> list = new ArrayList<>();
        list.add(new StatementInfoBean(WebSiteUrl.StatementsList, baseContext.getString(R.string.khmergaming), AppConfig.Statement_statement));
        list.add(new StatementInfoBean(WebSiteUrl.AfbSportGameStatementUrl, baseContext.getString(R.string.afb_sport_betting), AppConfig.Statement_afbSports));
        list.add(new StatementInfoBean(WebSiteUrl.Afb1188StatementUrl, baseContext.getString(R.string.afb2_sport_betting), AppConfig.Statement_afb1188));
        list.add(new StatementInfoBean(WebSiteUrl.SabaSportGameStatementUrl, baseContext.getString(R.string.list2_saba), AppConfig.Statement_sabaSports));
        list.add(new StatementInfoBean(WebSiteUrl.SboSportGameStatementUrl, baseContext.getString(R.string.list2_ib), AppConfig.Statement_sboSports));
        list.add(new StatementInfoBean(WebSiteUrl.ForexStatementUrl, baseContext.getString(R.string.eightqihuo), AppConfig.Statement_forex));
        list.add(new StatementInfoBean(WebSiteUrl.W88StatementUrl, completeRecordFragment.getString(R.string.W88_Casino_Slots), AppConfig.Statement_w88));
        list.add(new StatementInfoBean(WebSiteUrl.GoldStatementUrl, baseContext.getString(R.string.gold_live_entertainment), AppConfig.Statement_goldCasino));
        list.add(new StatementInfoBean(WebSiteUrl.SaGameStatementUrl, "SA GAMING " + baseContext.getString(R.string.casino), AppConfig.Statement_saCasino));
        list.add(new StatementInfoBean(WebSiteUrl.Dg99GameStatementUrl, "Dg99 " + baseContext.getString(R.string.casino), AppConfig.Statement_dg99Casino));
        list.add(new StatementInfoBean(WebSiteUrl.AgGameStatementUrl, "Ag " + baseContext.getString(R.string.casino), AppConfig.Statement_agCasino));
        list.add(new StatementInfoBean(WebSiteUrl.AllBetStatementUrl, "AllBet " + baseContext.getString(R.string.casino), AppConfig.Statement_AllbetCasino));
        list.add(new StatementInfoBean(WebSiteUrl.EvoStatementUrl, "Evo " + baseContext.getString(R.string.casino), AppConfig.Statement_EvoCasino));
        list.add(new StatementInfoBean(WebSiteUrl.MgStatementUrl, baseContext.getString(R.string.tiyubocai1), AppConfig.Statement_mgGame));
        list.add(new StatementInfoBean(WebSiteUrl.BestGameStatementUrl, baseContext.getString(R.string.bestgame_slots), AppConfig.Statement_bestGame));
        list.add(new StatementInfoBean(WebSiteUrl.PtGameStatementUrl, baseContext.getString(R.string.pt_game_slots), AppConfig.Statement_ptGame));
        list.add(new StatementInfoBean(WebSiteUrl.HabaGameStatementUrl, "Haba " + baseContext.getString(R.string.game), AppConfig.Statement_habaGame));
        list.add(new StatementInfoBean(WebSiteUrl.PPlayGameStatementUrl, "PPlay " + baseContext.getString(R.string.game), AppConfig.Statement_pplayGame));
        list.add(new StatementInfoBean(WebSiteUrl.NewkenoStatementUrl, completeRecordFragment.getString(R.string.NewKeno), AppConfig.Statement_Newkeno));
        list.add(new StatementInfoBean(WebSiteUrl.Gd88GameStatementUrl, baseContext.getString(R.string.gd_live_entertainment), AppConfig.Statement_gd88Casino));
        list.add(new StatementInfoBean(WebSiteUrl.SexyCasinoStatementUrl, baseContext.getString(R.string.sexy_live_entertainment), AppConfig.Statement_sexyCasino));
        list.add(new StatementInfoBean(WebSiteUrl.Lottery4dGameStatementUrl, "4D " + baseContext.getString(R.string.indonesia_lottery), AppConfig.Statement_4dLottery));
        list.add(new StatementInfoBean(WebSiteUrl.JokerGameStatementUrl, baseContext.getString(R.string.joker_game_slots), AppConfig.Statement_jokerGame));
        list.add(new StatementInfoBean(WebSiteUrl.FfylStatementUrl, completeRecordFragment.getString(R.string.FFYL_Poker), AppConfig.Statement_ffyl));
        return list;
    }

    public void getListData(String url, HashMap<String, String> p, final String gameType) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(url, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                switch (gameType) {
                    case AppConfig.Statement_afb1188:
                        Afb1188StatementBean afb1188StatementBean = gson.fromJson(data, Afb1188StatementBean.class);
                        List<Afb1188StatementBean.DataBean> afb1188List = afb1188StatementBean.getData();
                        if (afb1188List == null || afb1188List.size() == 0) {
                            afb1188List = new ArrayList<>();
                            Afb1188StatementBean.DataBean dataBean = new Afb1188StatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            afb1188List.add(dataBean);
                        }
                        completeRecordFragment.onGetAfb1188StatementData(afb1188List, gameType);
                        break;
                    case AppConfig.Statement_Cockfight:
                        CockfightStatementBean cockfightStatementBean = gson.fromJson(data, CockfightStatementBean.class);
                        List<CockfightStatementBean.DataBean> cockfightList = cockfightStatementBean.getData();
                        if (cockfightList == null || cockfightList.size() == 0) {
                            cockfightList = new ArrayList<>();
                            CockfightStatementBean.DataBean dataBean = new CockfightStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            cockfightList.add(dataBean);
                        }
                        completeRecordFragment.onGetCocfightStatementData(cockfightList, gameType);
                        break;
                    case AppConfig.Statement_statement:
                        NyBaseResponse<List<CompleteReportFormBean>> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<List<CompleteReportFormBean>>>() {
                        }.getType());
                        List<CompleteReportFormBean> beanList = orgData.getData();
                        switch (orgData.getMsg()) {
                            case "1":
                                if (beanList == null || beanList.size() == 0) {
                                    beanList = new ArrayList<>();
                                    CompleteReportFormBean completeReportFormBean = new CompleteReportFormBean();
                                    completeReportFormBean.setBet_amount("-1");
                                    beanList.add(completeReportFormBean);
                                    completeRecordFragment.onGetStatementData(beanList);
                                } else {
                                    completeRecordFragment.onGetStatementData(beanList);
                                }
                                break;
                            default:
                                beanList = new ArrayList<>();
                                CompleteReportFormBean completeReportFormBean = new CompleteReportFormBean();
                                completeReportFormBean.setBet_amount("-1");
                                beanList.add(completeReportFormBean);
                                completeRecordFragment.onGetStatementData(beanList);
                                break;
                        }
                        break;
                    case AppConfig.Statement_afbSports:
                    case AppConfig.Statement_sabaSports:
                        AfbSportsStatementBean afbSportsStatementBean = gson.fromJson(data, AfbSportsStatementBean.class);
                        List<AfbSportsStatementBean.DataBean> afbList = afbSportsStatementBean.getData();
                        if (afbList == null || afbList.size() == 0) {
                            afbList = new ArrayList<>();
                            AfbSportsStatementBean.DataBean dataBean = new AfbSportsStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            afbList.add(dataBean);
                        }
                        completeRecordFragment.onGetAfbStatementData(afbList, gameType);
                        break;
                    case AppConfig.Statement_forex:
                        ForexStatementBean forexStatementBean = gson.fromJson(data, ForexStatementBean.class);
                        List<ForexStatementBean.DataBean> forexList = forexStatementBean.getData();
                        if (forexList == null || forexList.size() == 0) {
                            forexList = new ArrayList<>();
                            ForexStatementBean.DataBean dataBean = new ForexStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            forexList.add(dataBean);
                        }
                        completeRecordFragment.onGetForexStatementData(forexList);
                        break;
                    case AppConfig.Statement_w88:
                        W88StatementBean w88StatementBean = gson.fromJson(data, W88StatementBean.class);
                        List<W88StatementBean.DataBean> w88List = w88StatementBean.getData();
                        if (w88List == null || w88List.size() == 0) {
                            w88List = new ArrayList<>();
                            W88StatementBean.DataBean dataBean = new W88StatementBean.DataBean();
                            dataBean.setBet_amount(-1);
                            w88List.add(dataBean);
                        }
                        completeRecordFragment.onGetW88StatementData(w88List);
                        break;
                    case AppConfig.Statement_goldCasino:
                        GoldStatementBean goldStatementBean = gson.fromJson(data, GoldStatementBean.class);
                        List<GoldStatementBean.DataBean> goldList = goldStatementBean.getData();
                        if (goldList == null || goldList.size() == 0) {
                            goldList = new ArrayList<>();
                            GoldStatementBean.DataBean dataBean = new GoldStatementBean.DataBean();
                            dataBean.setWin_loss("0");
                            dataBean.setBet_time("-1");
                            goldList.add(dataBean);
                        }
                        goldStatementBean.setData(goldList);
                        double total = 0;
                        for (int i = 0; i < goldStatementBean.getData().size(); i++) {
                            GoldStatementBean.DataBean dataBean = goldStatementBean.getData().get(i);
                            String win_loss = dataBean.getWin_loss();
                            double winLoss = Double.parseDouble(win_loss);
                            total += winLoss;
                        }
                        goldStatementBean.setWin_loss(total);
                        completeRecordFragment.onGetGoldStatementData(goldStatementBean);
                        break;
                    case AppConfig.Statement_mgGame:
                        MgSlotsStatementBean mgSlotsStatementBean = gson.fromJson(data, MgSlotsStatementBean.class);
                        List<MgSlotsStatementBean.DataBean> mgList = mgSlotsStatementBean.getData();
                        if (mgList == null || mgList.size() == 0) {
                            mgList = new ArrayList<>();
                            MgSlotsStatementBean.DataBean dataBean = new MgSlotsStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            mgList.add(dataBean);
                        }
                        completeRecordFragment.onGetMgStatementData(mgList);
                        break;
                    case AppConfig.Statement_bestGame:
                        BestGameStatementBean bestGameStatementBean = gson.fromJson(data, BestGameStatementBean.class);
                        List<BestGameStatementBean.DataBean> bestGameList = bestGameStatementBean.getData();
                        if (bestGameList == null || bestGameList.size() == 0) {
                            bestGameList = new ArrayList<>();
                            BestGameStatementBean.DataBean dataBean = new BestGameStatementBean.DataBean();
                            dataBean.setBet_time("-1");
                            bestGameList.add(dataBean);
                        }
                        completeRecordFragment.onGetBestGameStatementData(bestGameList);
                        break;

                    case AppConfig.Statement_saCasino:
                        SagamingStatementBean sagamingStatementBean = gson.fromJson(data, SagamingStatementBean.class);
                        List<SagamingStatementBean.DataBean> saList = sagamingStatementBean.getData();
                        if (saList == null || saList.size() == 0) {
                            saList = new ArrayList<>();
                            SagamingStatementBean.DataBean dataBean = new SagamingStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            saList.add(dataBean);
                        }
                        completeRecordFragment.onGetSaStatementData(saList);
                        break;
                    case AppConfig.Statement_dg99Casino:
                    case AppConfig.Statement_sboSports:
                        Dg99StatementBean dg99StatementBean = gson.fromJson(data, Dg99StatementBean.class);
                        List<Dg99StatementBean.DataBean> dg99List = dg99StatementBean.getData();
                        if (dg99List == null || dg99List.size() == 0) {
                            dg99List = new ArrayList<>();
                            Dg99StatementBean.DataBean dataBean = new Dg99StatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            dg99List.add(dataBean);
                        }
                        completeRecordFragment.onGetDg99StatementData(dg99List, gameType);
                        break;
                    case AppConfig.Statement_agCasino:
                        AgStatementBean agStatementBean = gson.fromJson(data, AgStatementBean.class);
                        List<AgStatementBean.DataBean> agList = agStatementBean.getData();
                        if (agList == null || agList.size() == 0) {
                            agList = new ArrayList<>();
                            AgStatementBean.DataBean dataBean = new AgStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            agList.add(dataBean);
                        }
                        completeRecordFragment.onGetAgStatementData(agList);
                        break;
                    case AppConfig.Statement_habaGame:
                        HabaStatementBean habaStatementBean = gson.fromJson(data, HabaStatementBean.class);
                        List<HabaStatementBean.DataBean> habaList = habaStatementBean.getData();
                        if (habaList == null || habaList.size() == 0) {
                            habaList = new ArrayList<>();
                            HabaStatementBean.DataBean dataBean = new HabaStatementBean.DataBean();
                            dataBean.setBet_time("-1");
                            habaList.add(dataBean);
                        }
                        completeRecordFragment.onGetHabaStatementData(habaList);
                        break;
                    case AppConfig.Statement_ptGame:
                        PtStatementBean ptStatementBean = gson.fromJson(data, PtStatementBean.class);
                        List<PtStatementBean.DataBean> ptList = ptStatementBean.getData();
                        if (ptList == null || ptList.size() == 0) {
                            ptList = new ArrayList<>();
                            PtStatementBean.DataBean dataBean = new PtStatementBean.DataBean();
                            dataBean.setGamedate("-1");
                            ptList.add(dataBean);
                        }
                        completeRecordFragment.onGetPtStatementData(ptList);
                        break;
                    case AppConfig.Statement_pplayGame:
                        PPlayStatementBean pPlayStatementBean = gson.fromJson(data, PPlayStatementBean.class);
                        List<PPlayStatementBean.DataBean> ppList = pPlayStatementBean.getData();
                        if (ppList == null || ppList.size() == 0) {
                            ppList = new ArrayList<>();
                            PPlayStatementBean.DataBean dataBean = new PPlayStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            ppList.add(dataBean);
                        }
                        completeRecordFragment.onGetPPlayStatementData(ppList);
                        break;
                    case AppConfig.Statement_sexyCasino:
                        SexyCasinoStatementBean sexyCasinoStatementBean = gson.fromJson(data, SexyCasinoStatementBean.class);
                        List<SexyCasinoStatementBean.DataBean> sexyList = sexyCasinoStatementBean.getData();
                        if (sexyList == null || sexyList.size() == 0) {
                            sexyList = new ArrayList<>();
                            SexyCasinoStatementBean.DataBean dataBean = new SexyCasinoStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            sexyList.add(dataBean);
                        }
                        completeRecordFragment.onGetSexyCasinoStatementData(sexyList);
                        break;
                    case AppConfig.Statement_jokerGame:
                        JokerGameStatementBean jokerGameStatementBean = gson.fromJson(data, JokerGameStatementBean.class);
                        List<JokerGameStatementBean.DataBean> jokerList = jokerGameStatementBean.getData();
                        if (jokerList == null || jokerList.size() == 0) {
                            jokerList = new ArrayList<>();
                            JokerGameStatementBean.DataBean dataBean = new JokerGameStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            jokerList.add(dataBean);
                        }
                        completeRecordFragment.onGetJokerStatementData(jokerList);
                        break;
                    case AppConfig.Statement_4dLottery:
                        Lottery4DStatementBean lottery4DStatementBean = gson.fromJson(data, Lottery4DStatementBean.class);
                        List<Lottery4DStatementBean.DataBean> lottery4dList = lottery4DStatementBean.getData();
                        if (lottery4dList == null || lottery4dList.size() == 0) {
                            lottery4dList = new ArrayList<>();
                            Lottery4DStatementBean.DataBean dataBean = new Lottery4DStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            lottery4dList.add(dataBean);
                        }
                        completeRecordFragment.onGet4dLotteryStatementData(lottery4dList);
                        break;
                    case AppConfig.Statement_EvoCasino:
                        EvoStatementBean evoStatementBean = gson.fromJson(data, EvoStatementBean.class);
                        List<EvoStatementBean.DataBean> evoList = evoStatementBean.getData();
                        if (evoList == null || evoList.size() == 0) {
                            evoList = new ArrayList<>();
                            EvoStatementBean.DataBean dataBean = new EvoStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            evoList.add(dataBean);
                        }
                        completeRecordFragment.onGetEvoStatementData(evoList);
                        break;
                    case AppConfig.Statement_ffyl:
                        FFYLStatementBean ffylStatementBean = gson.fromJson(data, FFYLStatementBean.class);
                        List<FFYLStatementBean.DataBean> ffylList = ffylStatementBean.getData();
                        if (ffylList == null || ffylList.size() == 0) {
                            ffylList = new ArrayList<>();
                            FFYLStatementBean.DataBean dataBean = new FFYLStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            ffylList.add(dataBean);
                        }
                        completeRecordFragment.onGetFfylStatementData(ffylList);
                        break;
                    case AppConfig.Statement_AllbetCasino:
                        AllBetStatementBean allBetStatementBean = gson.fromJson(data, AllBetStatementBean.class);
                        List<AllBetStatementBean.DataBean> allBetList = allBetStatementBean.getData();
                        if (allBetList == null || allBetList.size() == 0) {
                            allBetList = new ArrayList<>();
                            AllBetStatementBean.DataBean dataBean = new AllBetStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            allBetList.add(dataBean);
                        }
                        allBetStatementBean.setData(allBetList);
                        completeRecordFragment.onGetAllBetStatementData(allBetStatementBean);
                        break;
                    case AppConfig.Statement_Newkeno:
                        NewKenoStatementBean newKenoStatementBean = gson.fromJson(data, NewKenoStatementBean.class);
                        List<NewKenoStatementBean.DataBean> newkenoList = newKenoStatementBean.getData();
                        if (newkenoList == null || newkenoList.size() == 0) {
                            newkenoList = new ArrayList<>();
                            NewKenoStatementBean.DataBean dataBean = new NewKenoStatementBean.DataBean();
                            dataBean.setBet_amount("-1");
                            newkenoList.add(dataBean);
                        }
                        newKenoStatementBean.setData(newkenoList);
                        completeRecordFragment.onGetNewKenoStatementData(newKenoStatementBean);
                        break;
                }
                completeRecordFragment.showContent();
            }
        });
    }
}
