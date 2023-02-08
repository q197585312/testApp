package nanyang.com.dig88.SlotsGame.Presenter;

import android.content.Intent;

import com.google.gson.Gson;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.SharePreferenceUtil;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.JdbFishBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Home.GameWebActivity;
import nanyang.com.dig88.SlotsGame.HABAGameConfigBean;
import nanyang.com.dig88.SlotsGame.SlotsGameActivity;
import nanyang.com.dig88.SlotsGame.SlotsGameWebActivity;
import nanyang.com.dig88.SlotsGame.W88GameConfigBean;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/7/4.
 */

public class SlotsGamePresenter extends BaseRetrofitPresenter<SlotsGameActivity> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    SlotsGameActivity slotsGameActivity;
    String language;
    LoginInfoBean loginInfo;

    public SlotsGamePresenter(SlotsGameActivity iBaseContext) {
        super(iBaseContext);
        slotsGameActivity = iBaseContext;
        language = AppTool.getAppLanguage(slotsGameActivity);
        loginInfo = (LoginInfoBean) AppTool.getObjectData(slotsGameActivity, "loginInfo");
    }

    public void getSlotsData(HashMap<String, String> p, final String gameType) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.SlotsGameUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                List<W88GameConfigBean.DataBean> dataList = new ArrayList<>();
                W88GameConfigBean w88GameConfigBean = gson.fromJson(data, W88GameConfigBean.class);
                dataList.addAll(w88GameConfigBean.getData());
                slotsGameActivity.onGetSlotsData(dataList);
            }
        });
    }

    public String getGameLoadUrl(String gameType, W88GameConfigBean.DataBean bean) {
        StringBuilder builder = new StringBuilder();
        String lg = "en";
        switch (gameType) {
            case AppConfig.BEST_GAME:
            case AppConfig.PT_GAME:
            case AppConfig.MG_GAME:
            case AppConfig.NETENT_GAME:
                builder.append("http://www.slotsgamingonline.com/loader.php?");
                if (language.equals("zh") || language.equals("zh_TW")) {
                    lg = "zh-cn";
                }
                builder.append("language=" + lg);
                builder.append("&game=" + bean.getGid());
                builder.append("&mode=real&username=" + loginInfo.getUsername());
                builder.append("&id=5&token=" + baseContext.getUserInfoBean().getSession_id());
                break;
            case AppConfig.PG_SLOTS:
                builder.append("https://pgslots.k-api.com/api/login.php?");
                if (language.equals("zh") || language.equals("zh_TW")) {
                    lg = "cn";
                }
                builder.append("web_id=" + WebSiteUrl.WebId);
                builder.append("&username=" + loginInfo.getUsername());
                builder.append("&token=");
                builder.append(baseContext.getUserInfoBean().getSession_id());
                builder.append("&language=" + lg);
                builder.append("&platform=" + "mobile");
                builder.append("&gameid=" + bean.getGid());
                builder.append("&free=" + "0");
                break;
            case AppConfig.PLAYSTAR_SLOTS:
                builder.append("http://playstar.k-api.com/api/login.php?");
                builder.append("web_id=" + WebSiteUrl.WebId);
                builder.append("&username=" + loginInfo.getUsername());
                builder.append("&token=");
                builder.append(baseContext.getUserInfoBean().getSession_id());
                builder.append("&language=");
                if (language.equals("zh")) {
                    lg = "cn";
                }
                builder.append(lg);
                builder.append("&platfrom=mobile");
                builder.append("&gameid=" + bean.getGid());
                builder.append("&free=0");
                break;
            case AppConfig.SAgaming_Slots_GAME:
                builder.append("https://sagming.khmergaming.com/api/login.php?");
                builder.append("token=");
                builder.append(baseContext.getUserInfoBean().getSession_id());
                builder.append("&loginFrom=1&slots=1");
                builder.append("&language=");
                if (language.equals("zh")) {
                    lg = "cn";
                }
                builder.append(lg);
                builder.append("&gameid=" + bean.getGid());
                break;
            case AppConfig.W88_GAME:
                builder.append("http://mrslots.gpiops.com/" + bean.getGid() + "/?");
                lg = "en-us";
                if (language.equals("zh")) {
                    lg = "zh-cn";
                }
                builder.append("lang=");
                builder.append(lg);
                builder.append("&fun=0");
                builder.append("&op=DIG88");
                builder.append("&token=" + baseContext.getUserInfoBean().getSession_id());
                builder.append("&hidelobby=1");
                break;
            case AppConfig.FISHING_GAME:

                break;
            case AppConfig.PP_Slots:
                builder.append("http://ppslots.k-api.com/api/login.php?");
                if (language.equals("zh") || language.equals("zh_TW")) {
                    lg = "zh-cn";
                }
                builder.append("language=");
                builder.append(lg);
                builder.append("&game_id=" + bean.getGid());
                builder.append("&platfrom=android&username=" + loginInfo.getUsername());
                builder.append("&token=" + baseContext.getUserInfoBean().getSession_id() + "&web_id=" + WebSiteUrl.WebId);
                break;
            case AppConfig.RTG_Slots:
                builder.append("http://rtg.dig88api.com/api/login.php?");
                if (language.equals("zh") || language.equals("zh_TW")) {
                    lg = "zh-cn";
                }
                builder.append("language=");
                builder.append(lg);
                builder.append("&game_id=" + bean.getGid());
                builder.append("&platfrom=android&username=" + loginInfo.getUsername());
                builder.append("&free=0&token=" + baseContext.getUserInfoBean().getSession_id() + "&web_id=" + WebSiteUrl.WebId);
                break;
        }
        return builder.toString();
    }

    public void goJdbFish(String title, String gid) {
        String url = WebSiteUrl.JDBLoginUrl + "web_id=" + WebSiteUrl.WebId + "&language=" +
                Dig88Utils.getLanguage(slotsGameActivity) + "&username=" + loginInfo.getUsername() +
                "&token=" + slotsGameActivity.getUserInfoBean().getSession_id() + "&from=app&gametype=7" + "&gameid=" + gid;
        Intent intent = new Intent(slotsGameActivity, GameWebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        slotsGameActivity.startActivity(intent);
    }

    public void goFish(String gid, int position) {
        if (position > 1) {
            goJoker(gid);
        } else {
            if (gid.equals("4001")) {
                StringBuilder builder = new StringBuilder();
                builder.append("http://twslots-pg.khmergaming.com/api/login.php?");
                String lang = "en";
                if (language.equals("zh")) {
                    lang = "cn";
                }
                builder.append("curr=");
                builder.append(baseContext.getCurrency());
                builder.append("&id=");
                builder.append(gid);
                builder.append("&language=");
                builder.append(lang);
                builder.append("&member=");
                builder.append(WebSiteUrl.WebId + "s" + loginInfo.getUsername());
                builder.append("&token=");
                builder.append(baseContext.getUserInfoBean().getSession_id());
                builder.append("&ip=");
                builder.append(SharePreferenceUtil.getString(baseContext, "IP"));
                doRetrofitApiOnUiThread(getService(ApiService.class).getData(builder.toString()), new BaseConsumer<String>(baseContext) {
                    @Override
                    protected void onBaseGetData(String data) throws JSONException {
                        Intent intent = new Intent(slotsGameActivity, SlotsGameWebActivity.class);
                        intent.putExtra("url", data);
                        slotsGameActivity.startActivity(intent);
                    }

                    @Override
                    protected void onAccept() {
//                super.onAccept();
                    }
                });
            } else {
                String lg = "en";
                switch (language) {
                    case "th":
                        lg = "th";
                        break;
                    case "en":
                        lg = "en";
                        break;
                    case "zh":
                    case "zh_TW":
                        lg = "cn";
                        break;
                    case "in":
                        lg = "id";
                        break;
                    case "kh":
                        lg = "kh";
                        break;
                    case "kr":
                        lg = "kr";
                        break;
                    case "my":
                        lg = "ma";
                        break;
                    case "vn":
                        lg = "vn";
                        break;
                }
                String url = "https://sagming.khmergaming.com/api/login.php?token=" + baseContext.getUserInfoBean().getSession_id() +
                        "&language=" + lg + "&loginFrom=1&slots=1&mobile=1";
                Intent intent = new Intent(slotsGameActivity, SlotsGameWebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("Fish", "YuLeWuQiong");
                slotsGameActivity.startActivity(intent);
            }
        }
    }

    public void goJoker(String gid) {
        StringBuilder builder = new StringBuilder();
        builder.append("http://joker123.k-api.com/api/login.php?");
        builder.append("web_id=");
        builder.append(WebSiteUrl.WebId);
        builder.append("&username=" + loginInfo.getUsername());
        builder.append("&language=");
        String lg = "en";
        if (language.equals("zh")) {
            lg = "cn";
        }
        builder.append(lg);
        builder.append("&game_id=" + gid);
        builder.append("&token=");
        builder.append(baseContext.getUserInfoBean().getSession_id());
        String url = builder.toString();
        Intent intent = new Intent(baseContext, SlotsGameWebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("Fish", "Joker123");
        baseContext.startActivity(intent);
    }

    public void goHabaGame(String gid) {
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", baseContext.getUserInfoBean().getUser_id());
        p.put("session_id", baseContext.getUserInfoBean().getSession_id());
        p.put("game_id", gid);
        String lang = "en";
        if (language.equals("zh") || language.equals("zh_TW")) {
            lang = "cn";
        }
        p.put("lang", lang);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.HabaGameAddressUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                HABAGameConfigBean habaGameConfigBean = new Gson().fromJson(data, HABAGameConfigBean.class);
                HABAGameConfigBean.DataBean bean = habaGameConfigBean.getData();
                String requestUrl = bean.getAction();
                requestUrl += "?brandid=" + bean.getBrandid();
                requestUrl += "&brandgameid=" + bean.getBrandgameid();
                requestUrl += "&token=" + bean.getToken();
                requestUrl += "&mode=" + bean.getMode();
                requestUrl += "&locale=" + bean.getLocale();
                requestUrl += "&mobile=" + bean.getMobile();
                requestUrl += "&lobbyurl=";
                Intent intent = new Intent(slotsGameActivity, SlotsGameWebActivity.class);
                intent.putExtra("url", requestUrl);
                slotsGameActivity.startActivity(intent);
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    public HashMap<String, String> getGameParam(String gameType) {
        HashMap<String, String> param = new HashMap<>();
        param.put("typeid", "1");
        param.put("web_id", WebSiteUrl.WebId);
        switch (gameType) {
            case AppConfig.BEST_GAME:
                param.put("provider", "slots_bestgamer");
                break;
            case AppConfig.NETENT_GAME:
                param.put("provider", "slots_netent");
                break;
            case AppConfig.HABA_GAME:
                param.put("provider", "slots_haba_index");
                break;
            case AppConfig.SAgaming_Slots_GAME:
                param.put("provider", "slots_sagaming");
                break;
            case AppConfig.Joker123_GAME:
                param.put("provider", "slots_joker123");
                break;
            case AppConfig.W88_GAME:
                param.put("provider", "slots_w88");
                break;
            case AppConfig.PT_GAME:
                param.put("provider", "slots_pt");
                break;
            case AppConfig.MG_GAME:
                param.put("provider", "slots_mg");
                break;
            case AppConfig.FISHING_GAME:
                param.put("provider", "slots_fish");
                break;
            case AppConfig.PP_Slots:
                param.put("provider", "slots_pplay");
                break;
            case AppConfig.RTG_Slots:
                param.put("provider", "slots_rtg");
                break;
            case AppConfig.PLAYSTAR_SLOTS:
                param.put("provider", "slots_playstar");
                break;
            case AppConfig.PG_SLOTS:
                param.put("provider", "slots_pg");
                break;
        }
        return param;
    }

    public String getGameImgUrlHead(String gameType) {
        String imgUrlHead = "";
        switch (gameType) {
            case AppConfig.BEST_GAME:
            case AppConfig.SAgaming_Slots_GAME:
            case AppConfig.PT_GAME:
            case AppConfig.MG_GAME:
            case AppConfig.HABA_GAME:
            case AppConfig.W88_GAME:
                imgUrlHead = "https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/ig/";
                break;
            case AppConfig.Joker123_GAME:
                imgUrlHead = "https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/game/joker123/";
                break;
            case AppConfig.PP_Slots:
                imgUrlHead = "https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/game/pplay/";
                break;
            case AppConfig.RTG_Slots:
                imgUrlHead = "https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/game/rtg_slots/";
                break;
            case AppConfig.NETENT_GAME:
                imgUrlHead = "https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/game/netent/";
                break;
            case AppConfig.PLAYSTAR_SLOTS:
                String appLanguage = AppTool.getAppLanguage(slotsGameActivity);
                if (appLanguage.equals("zh")) {
                    imgUrlHead = "https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/game/playstar/cn/";
                } else {
                    imgUrlHead = "https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/game/playstar/en/";
                }
                break;
            case AppConfig.PG_SLOTS:
                imgUrlHead = "https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/game/pgslots/";
                break;
        }
        return imgUrlHead;
    }

    public String getFishImgUrlHead(String fishName) {
        if (fishName.equals("Fishing king") || fishName.equals("Yu Le Wu Qiong")) {
            return "https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/ig/";
        } else {
            return "https://s3-ap-northeast-1.amazonaws.com/hcgames.3g/content/images/game/joker123/";
        }
    }

    public String getGameName(W88GameConfigBean.DataBean bean) {
        switch (language) {
            case "zh":
            case "zh_TW":
                return bean.getGname_cn();
            default:
                return bean.getGname_en();
        }
    }
}
