package nanyang.com.dig88.Fragment.Presenter;

import com.google.gson.Gson;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.RunningWaterGameBean;
import nanyang.com.dig88.Entity.WashWaterBean;
import nanyang.com.dig88.Fragment.RunningWaterFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/7/5.
 */

public class RunningWaterPresenter extends BaseRetrofitPresenter<RunningWaterFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    RunningWaterFragment runningWaterFragment;

    public RunningWaterPresenter(RunningWaterFragment iBaseContext) {
        super(iBaseContext);
        runningWaterFragment = iBaseContext;
    }

    public void getSearchData(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.RunningWaterSearchUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                WashWaterBean bean = new Gson().fromJson(data, WashWaterBean.class);
                List<WashWaterBean.DataBean> dataList = bean.getData();
                for (int i = 0; i < dataList.size(); i++) {
                    WashWaterBean.DataBean dataBean = dataList.get(i);
                    String p_name = dataBean.getP_name();
                    switch (p_name) {
                        case "AFB SPORTS":
                            if (BuildConfig.FLAVOR.equals("ibet567")) {
                                dataBean.setP_cn_name(runningWaterFragment.getString(R.string.afb1188_sports));
                            } else {
                                String pId = dataBean.getP_id();
                                if (pId.equals("35")) {
                                    dataBean.setP_cn_name(runningWaterFragment.getString(R.string.afb2_sport_betting));
                                    dataBean.setP_name(runningWaterFragment.getString(R.string.afb2_sport_betting));
                                } else {
                                    dataBean.setP_cn_name(runningWaterFragment.getString(R.string.afb_sport_betting));
                                }
                            }
                            break;
                        case "SBO SPORTS":
                            dataBean.setP_cn_name("SBO " + runningWaterFragment.getString(R.string.sports));
                            break;
                        case "IBC SPORTS":
                            dataBean.setP_cn_name("SABA " + runningWaterFragment.getString(R.string.sports));
                            break;
                        case "GD CASINO":
                            dataBean.setP_cn_name(runningWaterFragment.getString(R.string.gd_live_entertainment));
                            break;
                        case "AG CASINO":
                            dataBean.setP_cn_name(runningWaterFragment.getString(R.string.ag_live_entertainment));
                            break;
                        case "GOLD CASINO":
                            dataBean.setP_cn_name(runningWaterFragment.getString(R.string.gold_live_entertainment));
                            break;
                        case "ALLBET CASINO":
                            dataBean.setP_cn_name("AllBet " + runningWaterFragment.getString(R.string.live_entertainment));
                            break;
                        case "DG99 CASINO":
                            dataBean.setP_cn_name("DG99 " + runningWaterFragment.getString(R.string.live_entertainment));
                            break;
                        case "HABA SLOTS":
                            dataBean.setP_cn_name("HABA " + runningWaterFragment.getString(R.string.game));
                            break;
                        case "MG SLOTS":
                            dataBean.setP_cn_name("MG " + runningWaterFragment.getString(R.string.game));
                            break;
                        case "BESTGAMER SLOTS":
                            dataBean.setP_cn_name("Bestgamer " + runningWaterFragment.getString(R.string.game));
                            break;
                        case "PT SLOTS":
                            dataBean.setP_cn_name("PT " + runningWaterFragment.getString(R.string.game));
                            break;
                        case "KHMER GAMING":
                            if (dataBean.getP_id().equals("28")) {
                                dataBean.setP_cn_name(runningWaterFragment.getString(R.string.khmergaming) + "2");
                            } else {
                                dataBean.setP_cn_name(runningWaterFragment.getString(R.string.khmergaming));
                            }
                            break;
                        case "FOREX":
                            dataBean.setP_cn_name(runningWaterFragment.getString(R.string.eightqihuo));
                            break;
                        case "TANGKAS":
                            dataBean.setP_cn_name("TANGKAS");
                            break;
                        case "Joker123 Slots":
                            dataBean.setP_cn_name("Joker123 " + runningWaterFragment.getString(R.string.game));
                            break;
                        case "W88 CASINO":
                            dataBean.setP_cn_name("W88 " + runningWaterFragment.getString(R.string.live_entertainment));
                            break;
                        case "W88 SLOTS":
                            dataBean.setP_cn_name("W88 " + runningWaterFragment.getString(R.string.game));
                            break;
                        case "CF88 COCK FIGHTING":
                            dataBean.setP_cn_name(runningWaterFragment.getString(R.string.Cockfight));
                            if (BuildConfig.FLAVOR.equals("henbet") && runningWaterFragment.getLocalLanguage().equals("th")) {
                                dataBean.setP_cn_name("CF88 ไก่ชน");
                            }
                            break;
                        case "IG Lottery":
                            dataBean.setP_cn_name("IG " + runningWaterFragment.getString(R.string.tw_game));
                            break;
                        case "HC Lottery":
                            dataBean.setP_cn_name("HC " + runningWaterFragment.getString(R.string.tw_game));
                            break;
                        case "SEXY CASINO":
                            dataBean.setP_cn_name("SEXY " + runningWaterFragment.getString(R.string.live_entertainment));
                            break;
                        case "SAGAMING CASINO":
                            dataBean.setP_cn_name("SA " + runningWaterFragment.getString(R.string.live_entertainment));
                            break;
                        case "SAGAMING SLOTS":
                            dataBean.setP_cn_name("SA " + runningWaterFragment.getString(R.string.game));
                            break;
                        case "WM CASINO":
                            dataBean.setP_cn_name("WM " + runningWaterFragment.getString(R.string.live_entertainment));
                            break;
                        case "GG Poker":
                            dataBean.setP_cn_name("GG " + runningWaterFragment.getString(R.string.texas_holdem1));
                            break;
                        case "Klas Poker":
                            dataBean.setP_cn_name("Klas " + runningWaterFragment.getString(R.string.texas_holdem1));
                            break;
                        case "NEW KENO":
                            dataBean.setP_cn_name(runningWaterFragment.getString(R.string.keno));
                            break;
                        case "KH GAMING":
                            dataBean.setP_cn_name(runningWaterFragment.getString(R.string.ongdo_poker));
                            break;
                        case "PPlay Slot":
                            dataBean.setP_cn_name(runningWaterFragment.getString(R.string.pp_game_slots));
                            break;
                        case "FFYL Poker":
                            dataBean.setP_cn_name(runningWaterFragment.getString(R.string.FFYL_Poker));
                            break;
                        case "EVO CASINO":
                            dataBean.setP_cn_name("EVO " + runningWaterFragment.getString(R.string.live_entertainment));
                            break;
                        default:
                            dataBean.setP_cn_name(p_name);
                            break;
                    }
                }
                runningWaterFragment.onGetSearchResult(bean);
            }
        });
    }

    public List<RunningWaterGameBean> getContentData() {
        List<RunningWaterGameBean> list = new ArrayList<>();
        if (BuildConfig.FLAVOR.equals("ibet567")) {
            list.add(new RunningWaterGameBean("DG99 " + baseContext.getString(R.string.live_entertainment), 1, 8));
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.Cockfight), 0, 21));
            list.add(new RunningWaterGameBean("SEXY " + baseContext.getString(R.string.live_entertainment), 1, 24));
            list.add(new RunningWaterGameBean("SA " + baseContext.getString(R.string.live_entertainment), 1, 25));
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.afb1188_sports), 2, 35));
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.keno), -1, 36));
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.ongdo_poker), -1, 37));
        } else if (BuildConfig.FLAVOR.equals("hjlh6688")) {
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.ag_live_entertainment), 1, 5));
            list.add(new RunningWaterGameBean("HABA " + baseContext.getString(R.string.game), 3, 10));
            list.add(new RunningWaterGameBean("Bestgamer " + baseContext.getString(R.string.game), 3, 12));
            list.add(new RunningWaterGameBean("SA " + baseContext.getString(R.string.game), 3, 26));
            list.add(new RunningWaterGameBean("PPlay " + baseContext.getString(R.string.game), 3, 33));
            list.add(new RunningWaterGameBean("FFYL " + baseContext.getString(R.string.poker), -1, 34));
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.afb1188_sports), 2, 35));
        } else {
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.afb_sport_betting), 2, 1));
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.afb2_sport_betting), 2, 35));
            list.add(new RunningWaterGameBean("SABA " + baseContext.getString(R.string.sports), 2, 3));
            list.add(new RunningWaterGameBean("SBO " + baseContext.getString(R.string.sports), 2, 2));

            list.add(new RunningWaterGameBean(baseContext.getString(R.string.gd_live_entertainment), 1, 4));
            list.add(new RunningWaterGameBean("DG99 " + baseContext.getString(R.string.live_entertainment), 1, 8));
            list.add(new RunningWaterGameBean("WM " + baseContext.getString(R.string.live_entertainment), 1, 29));
            list.add(new RunningWaterGameBean("SEXY " + baseContext.getString(R.string.live_entertainment), 1, 24));
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.ag_live_entertainment), 1, 5));
            list.add(new RunningWaterGameBean("AllBet " + baseContext.getString(R.string.live_entertainment), 1, 7));
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.gold_live_entertainment), 1, 6));
            list.add(new RunningWaterGameBean("SA " + baseContext.getString(R.string.live_entertainment), 1, 25));
            list.add(new RunningWaterGameBean("W88 " + baseContext.getString(R.string.live_entertainment), 1, 18));
            list.add(new RunningWaterGameBean("EVO " + baseContext.getString(R.string.live_entertainment), 1, 38));

            list.add(new RunningWaterGameBean("Bestgamer " + baseContext.getString(R.string.game), 3, 12));
            list.add(new RunningWaterGameBean("HABA " + baseContext.getString(R.string.game), 3, 10));
            list.add(new RunningWaterGameBean("W88 " + baseContext.getString(R.string.game), 3, 19));
            list.add(new RunningWaterGameBean("MG " + baseContext.getString(R.string.game), 3, 11));
            list.add(new RunningWaterGameBean("PT " + baseContext.getString(R.string.game), 3, 13));
            list.add(new RunningWaterGameBean("Joker123 " + baseContext.getString(R.string.game), 3, 17));
            list.add(new RunningWaterGameBean("SA " + baseContext.getString(R.string.game), 3, 26));
            // pp rtg 电子游戏等3G

            list.add(new RunningWaterGameBean(baseContext.getString(R.string.khmergaming), 4, 14));
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.khmergaming) + "2", 4, 28));
            list.add(new RunningWaterGameBean("Tangkas", 0, 16));
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.Cockfight), 0, 21));
            list.add(new RunningWaterGameBean("Klas " + baseContext.getString(R.string.texas_holdem1), 0, 31));
            list.add(new RunningWaterGameBean("GG " + baseContext.getString(R.string.texas_holdem1), 0, 30));
            list.add(new RunningWaterGameBean("FFYL " + baseContext.getString(R.string.poker), -1, 34));
            list.add(new RunningWaterGameBean(baseContext.getString(R.string.eightqihuo), 0, 15));
            list.add(new RunningWaterGameBean("IG " + baseContext.getString(R.string.tw_game), 4, 22));
            list.add(new RunningWaterGameBean("HC " + baseContext.getString(R.string.tw_game), 4, 23));
//        list.add(new RunningWaterGameBean("TW " + baseContext.getString(R.string.game), 3, 9));
//        list.add(new RunningWaterGameBean("Gold " + baseContext.getString(R.string.game), 3, 27));
        }
        return list;
    }
}
