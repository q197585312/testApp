package nanyang.com.dig88.Table.utils;

import android.app.Activity;
import android.text.Html;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import gaming178.com.mylibrary.allinone.util.MyLog;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import nanyang.com.dig88.Activity.DigApp;
import nanyang.com.dig88.Table.Imp.IUpdateTableData;
import nanyang.com.dig88.Table.Thread.TableHttpHelper;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.BallGameInfoBean;
import nanyang.com.dig88.Table.entity.CollectionBean;
import nanyang.com.dig88.Table.entity.HandicapBean;
import nanyang.com.dig88.Table.entity.LeagueBean;
import nanyang.com.dig88.Table.entity.MatchBean;
import nanyang.com.dig88.Table.entity.TableDataBean;
import nanyang.com.dig88.Table.entity.TableModuleBean;
import nanyang.com.dig88.Table.entity.VsOtherDataBean;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/11/26.
 */
public class TableDataHelper implements IUpdateTableData {
    int type;//0 是比赛 其他是收藏
    Activity context;
    ThreadEndT endt;
    DigApp digApp;
    private View view;
    private TableHttpHelper helper;
    private String url = "";
    private String dataTitle;
    private TableDataBean resultInfo;
    private ArrayList<TableModuleBean> tablelistData;
    private int betType;
    private int adapterModelType = ModelType.Default;

    public TableDataHelper(final Activity context, int type, ThreadEndT end) {
        this(null, context, type, end);
        digApp = (DigApp) context.getApplication();
    }


    public TableDataHelper(View view, final Activity context, int type, ThreadEndT end) {
        this.context = context;
        digApp = (DigApp) context.getApplication();
        this.type = type;
        this.endt = end;
        this.view = view;
        if (type == 0) {
            helper = new TableHttpHelper<TableDataBean>(context, view, new ThreadEndT<TableDataBean>(new TypeToken<TableDataBean>() {
            }.getType()) {
                @Override
                public void endT(final TableDataBean result, int model) {
                    endt.endString("1", model);
                    parseTableDataBean(result, model);
                }

                @Override
                public void endString(String result, int model) {
                    parseUpdate(result, model);
                }

                @Override
                public void endError(String error) {
                    if (error != null && !error.equals(""))
                        endt.endError(error);
                }
            });
        } else {
            helper = new TableHttpHelper<CollectionBean>(context, view, new ThreadEndT<CollectionBean>(new TypeToken<CollectionBean>() {
            }.getType()) {
                @Override
                public void endT(CollectionBean result, int model) {
                    endt.endString("1", model);
                    parseCollectionData(result, model);
                }

                @Override
                public void endString(String result, int model) {
                    endt.endString("2", model);
                    parseCollectionUpdate(result, model);
                }

                @Override
                public void endError(String error) {
                    endt.endError(error);
                }

            });
        }
    }

    public TableDataHelper() {

    }

    protected void parseCollectionData(CollectionBean result, int model) {
        LinkedHashMap<LeagueBean, LinkedHashMap<String, MatchBean>> dataContent = new LinkedHashMap<>();
        if (result == null || result.getJSOdds() == null || result.getJSOdds().size() < 1) {
            endt.endT(null, 0);
            return;
        }
        LinkedHashMap<String, MatchBean> matchs = null;
        CollectionBean resultInfo = result;
        for (List<String> lists : result.getJSOdds()) {
            String moduleTitle = Html.fromHtml(lists.get(result.getModuleTitle())).toString();
            String moduleId = Html.fromHtml(lists.get(result.getModuleId())).toString();
            String mkey = Html.fromHtml(lists.get(result.getFavId())).toString();
            if (mkey.equals(""))
                continue;
            String key = Html.fromHtml(lists.get(result.getSocOddsId())).toString();
            String MatchDate = Html.fromHtml(lists.get(result.getMatchDate())).toString();
            String WorkingDate = Html.fromHtml(lists.get(result.getWorkingDate())).toString();
            String guest = Html.fromHtml(lists.get(result.getAwayEvent())).toString();
            String home = Html.fromHtml(lists.get(result.getHomeEvent())).toString();

            String isHomeGive = Html.fromHtml(lists.get(result.getIsHomeGive())).toString();
            String isHomeGive_HF = Html.fromHtml(lists.get(result.getIsHomeGive())).toString();

            String HomeHdpOdds = Html.fromHtml(lists.get(result.getHomeOdds())).toString();
            HomeHdpOdds = changeValueS(HomeHdpOdds);
            String HomeHdpOdds_HF = Html.fromHtml(lists.get(result.getHomeOdds_FH())).toString();
            HomeHdpOdds_HF = changeValueS(HomeHdpOdds_HF);
            String AwayHdpOdds = Html.fromHtml(lists.get(result.getAwayOdds())).toString();
            AwayHdpOdds = changeValueS(AwayHdpOdds);
            String AwayHdpOdds_HF = Html.fromHtml(lists.get(result.getAwayOdds_FH())).toString();
            AwayHdpOdds_HF = changeValueS(AwayHdpOdds_HF);

            String OverOdds = Html.fromHtml(lists.get(result.getOverOdds())).toString();
            OverOdds = changeValueS(OverOdds);
            String OverOdds_HF = Html.fromHtml(lists.get(result.getOverOdds_FH())).toString();
            OverOdds_HF = changeValueS(OverOdds_HF);
            String UnderOdds = Html.fromHtml(lists.get(result.getUnderOdds())).toString();
            UnderOdds = changeValueS(UnderOdds);
            String UnderOdds_HF = Html.fromHtml(lists.get(result.getUnderOdds_FH())).toString();
            UnderOdds_HF = changeValueS(UnderOdds_HF);

            String Hdp = Html.fromHtml(lists.get(result.getStrHDP())).toString();
            Hdp = changeValueF(Hdp);
            String Hdp_HF = Html.fromHtml(lists.get(result.getStrHDP_FH())).toString();
            Hdp_HF = changeValueF(Hdp_HF);
            String OU = Html.fromHtml(lists.get(result.getStrOu())).toString();
            if (OU.equals("0")) {
                OU = "";
            }
            OU = changeValueF(OU);
            String OU_HF = Html.fromHtml(lists.get(result.getStrOu_FH())).toString();
            if (OU_HF.equals("0")) {
                OU_HF = "";
            }
            OU_HF = changeValueF(OU_HF);

            String SocOddsId = Html.fromHtml(lists.get(result.getSocOddsId())).toString();
            String SocOddsId_FH = Html.fromHtml(lists.get(result.getSocOddsId_FH())).toString();

            String IsInetBet = Html.fromHtml(lists.get(result.getIsInetBet())).toString();
            String IsInetBet_FH = Html.fromHtml(lists.get(result.getIsInetBet_FH())).toString();
            String homeId = Html.fromHtml(lists.get(result.getHomeId())).toString();
            String awayId = Html.fromHtml(lists.get(result.getAwayId())).toString();
            String RCHome = Html.fromHtml(lists.get(result.getRCHome())).toString();
            String RCAway = Html.fromHtml(lists.get(result.getRCAway())).toString();
            String IsHdpNew = lists.get(result.getIsHdpNew());
            String IsHdpNew_FH = lists.get(result.getIsHdpNew_FH());
            String IsOUNew = lists.get(result.getIsOUNew());
            String IsOUNew_FH = lists.get(result.getIsOUNew_FH());
            String IsRun = lists.get(result.getIsRun());
            String Live = lists.get(result.getLive());
            String HomeRank = "";
            String AwayRank = "";
            String RunHomeScore = lists.get(result.getRunHomeScore());
            String RunAwayScore = lists.get(result.getRunAwayScore());
            String CurMinute = lists.get(result.getCurMinute());
            if (CurMinute != null && !CurMinute.equals("") && CurMinute.contains("%")) {
                String ss[] = CurMinute.split("%");
                if (ss != null && ss.length > 1) {
                    if (!ss[0].equals("")) {
                        String Score[] = ss[0].split("-");
                        if (Score != null && Score.length > 1) {
                            RunHomeScore = Score[0].trim();
                            RunAwayScore = Score[1].trim();
                        }
                    }
                    CurMinute = ss[1]/*.substring(0,ss[1].lastIndexOf(" ")).trim()*/;
                }

            }
            String Status = "1";
//            if(lists.get(result.getStatus())!=null&&!lists.get(result.getStatus()).equals(""))
//             Status= lists.get(result.getStatus());
            String hasOU;
            if (OU == null || OU.equals(""))
                hasOU = "false";
            else
                hasOU = "true";
            String hasHdp;
            if (Hdp == null || Hdp.equals(""))
                hasHdp = "false";
            else
                hasHdp = "true";

            String hasOU_HF;
            if (OU_HF == null || OU_HF.equals(""))
                hasOU_HF = "false";
            else
                hasOU_HF = "true";
            String hasHdp_HF;
            if (Hdp_HF == null || Hdp_HF.equals(""))
                hasHdp_HF = "false";
            else
                hasHdp_HF = "true";
            matchs = dataContent.get(new LeagueBean(moduleId, moduleTitle));
            if (matchs == null) {
                matchs = new LinkedHashMap<String, MatchBean>();
            }
            String RTSMatchId = Html.fromHtml(lists.get(result.getRTSMatchId())).toString();
            VsOtherDataBean otherDataBean = new VsOtherDataBean();
            matchs.put(key + "", new MatchBean(HomeRank, AwayRank, homeId, awayId, key, MatchDate, WorkingDate, guest, home,
                    new ArrayList<HandicapBean>(Arrays.asList(new HandicapBean(isHomeGive, Hdp, HomeHdpOdds, AwayHdpOdds, OU, OverOdds, UnderOdds, SocOddsId, IsInetBet, IsHdpNew, IsOUNew, hasHdp, hasOU),
                            new HandicapBean(isHomeGive_HF, Hdp_HF, HomeHdpOdds_HF, AwayHdpOdds_HF, OU_HF, OverOdds_HF, UnderOdds_HF, SocOddsId_FH, IsInetBet_FH, IsHdpNew_FH, IsOUNew_FH, hasHdp_HF, hasOU_HF))), "true", RCHome, RCAway, otherDataBean, IsRun, CurMinute,
                    RunHomeScore, RunAwayScore, Live, Status,RTSMatchId));
            dataContent.put(new LeagueBean(moduleId, moduleTitle), matchs);
        }

        ArrayList<TableModuleBean> listData = new ArrayList<>();
//                    for (LinkedHashMap.Entry<LeagueBean, LinkedHashMap<String, MatchBean>> item : dataContent.entrySet()) {
//                        LinkedHashMap<String, MatchBean> list = item.getValue();
//                        List<MatchBean> data = new ArrayList<>();
//                        for (LinkedHashMap.Entry<String, MatchBean> cell : list.entrySet()) {
//                            data.add(cell.getValue());
//                        }
//                        listData.add(new TableModuleBean(item.getKey(), data));
//                    }
        Iterator iter = dataContent.entrySet().iterator();
        while (iter.hasNext()) {

            LinkedHashMap.Entry<LeagueBean, LinkedHashMap<String, MatchBean>> item = (Map.Entry) iter.next();
            LinkedHashMap<String, MatchBean> list = item.getValue();
            List<MatchBean> data = new ArrayList<>();
            Iterator<Map.Entry<String, MatchBean>> listItor = list.entrySet().iterator();
            while (listItor.hasNext()) {
                Map.Entry<String, MatchBean> cell = listItor.next();
                data.add(cell.getValue());
            }
            listData.add(new TableModuleBean(item.getKey(), data));
        }
        endt.endString("1", model);
        endt.endT(listData, model);
    }

    protected void parseTableDataBean(TableDataBean result, int model) {
        tablelistData = new ArrayList<>();
        if (result == null) return;
        ArrayList<MatchBean> allListMatch = new ArrayList<MatchBean>();
        LinkedHashMap<LeagueBean, LinkedHashMap<String, MatchBean>> dataContent = new LinkedHashMap<>();
        BallGameInfoBean ball = ((DigApp) context.getApplication()).getBallGameInfo();
        ball.setRId(result.getRId());
        if (result == null || result.getJSOdds() == null || result.getJSOdds().size() < 1) {
            endt.endString("2", model);
            endt.endT(null, model);
            return;
        }
        resultInfo = result;
        String market = digApp.getMarket();
        for (List<String> lists : result.getJSOdds()) {
            String gameType2 = lists.get(result.getGameType2());
            if (betType != TableAdapterHelper.ClearanceBet){
                if (market.equals("1")) {
                    if (!gameType2.equals("S") && !gameType2.equals("WC")) {
                        continue;
                    }
                } else if (market.equals("2")) {
                    if (!gameType2.equals("SZ") && !gameType2.equals("WCZ")) {
                        continue;
                    }
                }
            }
            String dataType = digApp.getDataType();
            if (dataType.equals("1")){
                String gameName =lists.get(result.getModuleTitle());
                if (!gameName.contains("世界杯")&&!gameName.contains("WORLD CUP")){
                    continue;
                }
            }
            String SocOddsId = lists.get(result.getSocOddsId());
            if (SocOddsId.equals("-1") || SocOddsId.equals("0") || SocOddsId.equals(""))
                continue;
            String moduleTitle = lists.get(result.getModuleTitle());
            String moduleId = lists.get(result.getModuleId());
            String key = lists.get(result.getKey());
            String MatchDate = lists.get(result.getMatchDate());
            String WorkingDate = lists.get(result.getWorkingDate());
            String TodayDate = lists.get(result.getTodayDate());
            String guest = lists.get(result.getAway());
            String home = lists.get(result.getHome());
            String isHomeGive = lists.get(result.getIsHomeGive());
            String isHomeGive_HF = lists.get(result.getIsHomeGive_FH());
            String HomeHdpOdds = lists.get(result.getHomeHdpOdds());
            HomeHdpOdds = changeValueS(HomeHdpOdds);
            String HomeHdpOdds_HF = lists.get(result.getHomeHdpOdds_FH());
            HomeHdpOdds_HF = changeValueS(HomeHdpOdds_HF);
            String AwayHdpOdds = lists.get(result.getAwayHdpOdds());
            AwayHdpOdds = changeValueS(AwayHdpOdds);
            String AwayHdpOdds_HF = lists.get(result.getAwayHdpOdds_FH());
            AwayHdpOdds_HF = changeValueS(AwayHdpOdds_HF);
            String Hdp = lists.get(result.getHdp());
            Hdp = changeValueF(Hdp);
            String Hdp_HF = lists.get(result.getHdp_FH());
            Hdp_HF = changeValueF(Hdp_HF);
            String OU = lists.get(result.getOU());
            if (OU.equals("0")) {
                OU = "";
            }
            OU = changeValueF(OU);
            String OU_HF = lists.get(result.getOU_FH());
            if (OU_HF.equals("0")) {
                OU_HF = "";
            }
            OU_HF = changeValueF(OU_HF);
            String OverOdds = lists.get(result.getOverOdds());
            OverOdds = changeValueS(OverOdds);
            String OverOdds_HF = lists.get(result.getOverOdds_FH());
            OverOdds_HF = changeValueS(OverOdds_HF);
            String UnderOdds = lists.get(result.getUnderOdds());
            UnderOdds = changeValueS(UnderOdds);
            String UnderOdds_HF = lists.get(result.getUnderOdds_FH());
            UnderOdds_HF = changeValueS(UnderOdds_HF);

            String SocOddsId_FH = lists.get(result.getSocOddsId_FH());
            String IsInetBet = lists.get(result.getIsInetBet());
            String IsInetBet_FH = lists.get(result.getIsInetBet_FH());
            String homeId = lists.get(result.getHomeId());
            String awayId = lists.get(result.getAwayId());

            String isInFavourite = lists.get(result.getIsInFavourite());
            String RCHome = lists.get(result.getRCHome());
            String RCAway = lists.get(result.getRCAway());
            String IsHdpNew = lists.get(result.getIsHdpNew());
            String IsHdpNew_FH = lists.get(result.getIsHdpNew_FH());
            String IsOUNew = lists.get(result.getIsOUNew());
            String IsOUNew_FH = lists.get(result.getIsOUNew_FH());
            String HasHdp = lists.get(result.getHasHdp());
            String HasOU = lists.get(result.getHasOU());
            String HasHdp_fh = lists.get(result.getHasHdp_FH());
            String HasOU_fh = lists.get(result.getHasOU_FH());
            String IsRun = lists.get(result.getIsRun());
            String RunHomeScore = lists.get(result.getRunHomeScore());
            String RunAwayScore = lists.get(result.getRunAwayScore());
            String CurMinute = lists.get(result.getCurMinute());
            String Live = lists.get(result.getLive());
            String Status = lists.get(result.getStatus());
            String homeRank = "";
            String awayRank = "";
            if (OU.equals("") || OverOdds.equals("") || UnderOdds.equals("") || OU.equals("0") || OU.equals("-1")) {
                HasOU = "false";
            }
            if (OU_HF.equals("") || OverOdds_HF.equals("") || UnderOdds_HF.equals("") || OU_HF.equals("0") || OU_HF.equals("-1")) {
                HasOU_fh = "false";
            }
            if (HomeHdpOdds.equals("") || AwayHdpOdds.equals("") || Hdp.equals("") || Hdp.equals("-1")) {
                HasHdp = "false";
            }
            if (HomeHdpOdds_HF.equals("") || AwayHdpOdds_HF.equals("") || Hdp_HF.equals("") || Hdp_HF.equals("-1")) {
                HasHdp_fh = "false";
            }

            if (result.getHomeRank() > 0)
                homeRank = lists.get(result.getHomeRank());
            if (result.getAwayRank() > 0)
                awayRank = lists.get(result.getAwayRank());
            VsOtherDataBean otherDataBean = new VsOtherDataBean();
            if (betType == TableAdapterHelper.ClearanceBet) {
                awayId = lists.get(result.getAway());
                homeId = lists.get(result.getHome());
                isInFavourite = "false";
                key = lists.get(result.getSocOddsId());
                RCHome = "0";
                RCAway = "0";
                otherDataBean = new VsOtherDataBean(lists.get(result.getEvenOdds()), lists.get(result.getHasOE()),
                        lists.get(result.getHasX12()), lists.get(result.getHasX12FH()), lists.get(result.getIsOENew()),
                        lists.get(result.getIsX12New()), lists.get(result.getIsX12NewFH()),
                        lists.get(result.getOddOdds()), lists.get(result.getOEOdds()), lists.get(result.getX121Odds()),
                        lists.get(result.getX121OddsFH()), lists.get(result.getX122Odds()), lists.get(result.getX122OddsFH()),
                        lists.get(result.getX12XOdds()), lists.get(result.getX12XOddsFH()));

            }
            LinkedHashMap<String, MatchBean> matchs = dataContent.get(new LeagueBean(moduleId, moduleTitle));
            if (matchs == null) {
                matchs = new LinkedHashMap<String, MatchBean>();
            }
            String RTSMatchId = lists.get(result.getRTSMatchId());
            MatchBean bean = new MatchBean(homeRank, awayRank, homeId, awayId, key, MatchDate, WorkingDate, guest, home,
                    new ArrayList<HandicapBean>(Arrays.asList(new HandicapBean(isHomeGive, Hdp, HomeHdpOdds, AwayHdpOdds, OU, OverOdds, UnderOdds, SocOddsId, IsInetBet, IsHdpNew, IsOUNew, HasHdp, HasOU),
                            new HandicapBean(isHomeGive_HF, Hdp_HF, HomeHdpOdds_HF, AwayHdpOdds_HF, OU_HF, OverOdds_HF, UnderOdds_HF, SocOddsId_FH, IsInetBet_FH, IsHdpNew_FH, IsOUNew_FH, HasHdp_fh, HasOU_fh))), isInFavourite, RCHome, RCAway, otherDataBean,
                    IsRun, CurMinute,
                    RunHomeScore, RunAwayScore, Live, Status,RTSMatchId);
            allListMatch.add(bean);
            matchs.put(key + "", bean);
            dataContent.put(new LeagueBean(moduleId, moduleTitle), matchs);
        }

        Iterator iter = dataContent.entrySet().iterator();
        while (iter.hasNext()) {

            LinkedHashMap.Entry<LeagueBean, LinkedHashMap<String, MatchBean>> item = (Map.Entry) iter.next();
            LinkedHashMap<String, MatchBean> list = item.getValue();
            List<MatchBean> data = new ArrayList<>();
            Iterator<Map.Entry<String, MatchBean>> listItor = list.entrySet().iterator();
            while (listItor.hasNext()) {
                Map.Entry<String, MatchBean> cell = listItor.next();
                data.add(cell.getValue());
            }
            tablelistData.add(new TableModuleBean(item.getKey(), data));
        }
        endt.endString("2", model);
//        if(model==ModelType.Early){
//            endt.endT(sortEarly(allListMatch), model);
//        }else {
        endt.endT(tablelistData, model);
//        }
    }

    private ArrayList<TableModuleBean> sortEarly(ArrayList<MatchBean> allListMatch) {
        if (allListMatch == null || allListMatch.size() == 0)
            return null;
        Comparator<MatchBean> sort = new Comparator<MatchBean>() {
            @Override
            public int compare(MatchBean o1, MatchBean o2) {
                int flag = o1.getWorkingDate().compareTo(o2.getWorkingDate());
                return flag;
            }
        };
        Collections.sort(allListMatch, sort);
        ArrayList<TableModuleBean> tableDatas = new ArrayList<>();
        int i = 0;
        int j = 0;
        for (MatchBean item : allListMatch) {
            if (i == 0 || (!item.getLeagueBean().equals(allListMatch.get(i - 1).getLeagueBean()))) {
                tableDatas.add(new TableModuleBean(item.getLeagueBean(), new ArrayList<MatchBean>(Arrays.asList(item))));
                j++;
            } else {
                tableDatas.get(j - 1).getRows().add(item);
            }
            i++;
        }
        return tableDatas;
    }

    public List<TableModuleBean> getTablelistData() {
        return tablelistData;
    }

    /*http://mobilesport.dig88api.com/_View/Favourite.aspx?id=29278,139575,55712&IsAdd=False&ot=t
    Id组成	联赛id,主队id,客队id
    Ot	t (today)	 e(Early)	r(Running)

    我的最爱单行Running:
    http://mobilesport.dig88api.com/_view/SocFav1Run.aspx?ot=t&update=false&r=1308696953&t=1447251827100

    我的最爱单行 Today:
    http://mobilesport.dig88api.com/_view/SocFav1Today.aspx?ot=t&update=false&r=1308696953&wd=&ia=0&t=1447251827100

    我的最爱Early
    http://mobilesport.dig88api.com/_view/SocFav1Today.aspx?ot=e&update=false&r=336780400&wd=&ia=0&t=1448349041181
 http://mobilesport.dig88api.com/_view/SocFav1Today.aspx?ot=e&update=false&r=1668192273&t=1449287523828&wd=&ia=0&
    */
    public void getData(String dataTitle, String params, String modelType) {
        this.dataTitle = dataTitle;
        String ot = "";
        String t = "&t=" + System.currentTimeMillis();
        String localStr = "&ov=0&mt=" + digApp.getMarket() + "&tf=-1&TFStatus=0";
        String ov = "&update=false&r=" + new Random().nextInt() + t;
        String headType = "Socodds1";
        if (type == 1) {
            headType = "SocFav1";
        } else if (type == 0) {
            ov = localStr + ov;
        }
        url = "";
        if (dataTitle.equals("Early")) {
            ot = "?ot=e";
            if (betType == TableAdapterHelper.ClearanceBet) {
                url = WebSiteUrl.SportUrl + "_view/SocParlay.aspx" + ot + "&update=true&r=9789777";
            } else {
                if (params == null || params.equals(""))
                    params = "&wd=&ia=0";
                url = WebSiteUrl.SportUrl + "_view/" + headType + "Today.aspx" + ot + ov + params;
            }
        } else if (dataTitle.equals("Today")) {
            ot = "?ot=t";
            if (betType == TableAdapterHelper.ClearanceBet) {
                url = WebSiteUrl.SportUrl + "_view/SocParlay.aspx" + ot + "&update=true&r=9789777";
            } else {
                url = WebSiteUrl.SportUrl + "_view/" + headType + "Today.aspx" + ot + ov + "&wd=&ia=0";
            }

        } else if (dataTitle.equals("Running")) {
            ot = "?ot=t";
            url = WebSiteUrl.SportUrl + "_view/" + headType + "Run.aspx" + ot + ov;
        }
        url += "&accType=" + digApp.getCountryMarket();
        getNet(modelType);
    }


    private String changeValueS(String v) {
        if (v == null || v.equals(""))
            return "";
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = "";
        try {
            if (Float.valueOf(v) == 0) {
                return "";
            }
            if (type == 0 && betType != TableAdapterHelper.ClearanceBet)
                p = decimalFormat.format(Float.valueOf(v) / 10);//format 返回的是字符串
            else
                p = decimalFormat.format(Float.valueOf(v));//format 返回的是字符串收藏不除以10
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return p;
    }

    private String changeValueF(String f) {
        if (f.equals("") || f.startsWith("-"))
            return "";
        if (type == 0 && betType != TableAdapterHelper.ClearanceBet) {
            try {
                float v = Float.valueOf(f);
                int i;
                i = (int) (v / 0.5);   //先取商
                if (v == 0.5 * i) {
                    return StringUtils.subZeroAndDot(v);
                } else {
                    return StringUtils.subZeroAndDot((float) (v - 0.25)) + "-" + StringUtils.subZeroAndDot((float) (v + 0.25));
                }
            } catch (Exception e) {
                e.printStackTrace();
                MyLog.w("Error", f + ":" + e.getMessage());
            }

        }
        return f;
    }

    //刷新
    @Override
    public void updateNet(String modelType) {
        if (modelType == null || helper == null)
            return;
        if (type == 0) {
            int id = ((DigApp) (context.getApplication())).getBallGameInfo().getRId();
//            helper.updateData(url + "&Rid=" + id, "");
            if (modelType.equals("Running"))
                helper.updateData(url + "&Rid=" + id, "", ModelType.Running);
            else if (modelType.equals("Today")) {
                helper.updateData(url + "&Rid=" + id, "", ModelType.Today);
            } else if (modelType.equals("Early")) {
                helper.updateData(url + "&Rid=" + id, "", ModelType.Early);
            } else {
                helper.updateData(url + "&Rid=" + id, "", ModelType.Default);
            }
        } else {
            helper.updateData(url, "", ModelType.Default);
        }
    }

    //获取
    @Override
    public void getNet(String modelType) {
        if (type == 0) {
            helper.setDataView(view);
            if (modelType.equals("Running"))
                adapterModelType = ModelType.Running;
            else if (modelType.equals("Today")) {
                adapterModelType = ModelType.Today;
            } else if (modelType.equals("Early")) {
                adapterModelType = ModelType.Early;
            } else {
                adapterModelType = ModelType.Default;
            }
            helper.getData(url + "&Rid=0", "", adapterModelType);
        } else {
            helper.getData(url, modelType, ModelType.Default);
        }
    }

    public void parseUpdate(String jsonData, int model) {
//        try {
//            String jsonS = Html.fromHtml(jsonData).toString().replaceAll("\\n", "%");
//            JSONObject json = JSONObject.fromObject(jsonS);
//            int rid = json.getInt("RId");
//            int oRid = json.getInt("OldRId");
//            ((DigApp) (context.getApplication())).getBallGameInfo().setRId(rid);
//            //如果需要解析JSON数据，首要要生成一个JsonReader对象
//            String testKey = "JSOddsCnt";
//            if (json.get(testKey) != null && model == adapterModelType) {
//                if (updateAddDel(json, rid, oRid)) {
//                    endt.endString("2", model);
//                    endt.endT(tablelistData, model);
//                }
//            } else {
//                TableDataBean t = new Gson().fromJson(jsonData, new TypeToken<TableDataBean>() {
//                }.getType());
//                if (t != null) {
//                    resultInfo = t;
//                    if (adapterModelType == model)
//                        parseTableDataBean(resultInfo, model);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        /*try {
            TableDataBean t = new Gson().fromJson(jsonData, new TypeToken<TableDataBean>() {
            }.getType());
            if (t != null) {
                resultInfo = t;
                if(adapterModelType==model)
                    parseTableDataBean(resultInfo,model);
            }
        }catch (IllegalStateException e){
                endt.endError(jsonData);
            e.printStackTrace();
        }*/
    }

    private void parseCollectionUpdate(String jsonData, int model) {
        try {
            CollectionBean t = new Gson().fromJson(jsonData, new TypeToken<CollectionBean>() {
            }.getType());
            if (t != null) {
                parseCollectionData(t, model);
            }
        } catch (IllegalStateException e) {
            endt.endError(jsonData);
        }
    }

    public boolean updateAddDel(JSONObject json, int rid, int oRid) {
        boolean update = false;
//        int i = oRid + 1;
//        for (; i < rid + 1; i++) {
//            if (json.get("JSUpd_" + i) instanceof JSONObject) {
//                JSONObject jo = (JSONObject) json.get("JSUpd_" + i);
//                Iterator<String> keys = jo.keys();
//                while (keys.hasNext()) {
//                    String key1 = keys.next();
//                    String moduleId = splitModule(key1);
//                    update = update | update(moduleId, key1, (JSONObject) jo.get(key1));
//                }
//            }
//            if (json.get("JSAdd_" + i) instanceof JSONArray) {
//                JSONArray jaAdd = (JSONArray) json.get("JSAdd_" + i);
//                for (int m = 0; m < jaAdd.size(); m++) {
//                    Object[] addS = (Object[]) JSONArray.toArray((JSONArray) jaAdd.get(m));
//                    if (addS != null && addS.length > 0)
//                        update = update | add(addS);
//                }
//            }
//            if (json.get("JSDel_" + i) instanceof JSONArray) {
//                JSONArray jaDel = (JSONArray) json.get("JSDel_" + i);
//                for (int n = 0; n < jaDel.size(); n++) {
//                    Object[] delS = (Object[]) JSONArray.toArray((JSONArray) jaDel.get(n));
//                    if (delS != null && delS.length > 0) {
//                        update = update | del(delS[0].toString());
//                    }
//                }
//            } else {
//                MyLog.w("Error", "Nothing:" + json.get("JSUpd_" + i).toString());
//            }
//        }
        return update;
    }

    private boolean add(Object[] addS) {
        StringBuilder b = new StringBuilder();
        for (Object o : addS) {
            b.append(o.toString() + ",");
        }
        String SocOddsId = addS[resultInfo.getSocOddsId()].toString();
        if (SocOddsId.equals("-1") || SocOddsId.equals("0") || SocOddsId.equals(""))
            return false;
        String moduleTitle = addS[resultInfo.getModuleTitle()].toString();
        String moduleId = addS[resultInfo.getModuleId()].toString();
        String key = addS[(resultInfo.getKey())].toString();
        String MatchDate = addS[(resultInfo.getMatchDate())].toString();
        String WorkingDate = addS[(resultInfo.getWorkingDate())].toString();
        String TodayDate = addS[(resultInfo.getTodayDate())].toString();
        String guest = addS[(resultInfo.getAway())].toString();
        String home = addS[(resultInfo.getHome())].toString();
        String isHomeGive = addS[(resultInfo.getIsHomeGive())].toString();
        String isHomeGive_HF = addS[(resultInfo.getIsHomeGive_FH())].toString();
        String HomeHdpOdds = addS[(resultInfo.getHomeHdpOdds())].toString();
        HomeHdpOdds = changeValueS(HomeHdpOdds);
        String HomeHdpOdds_HF = addS[(resultInfo.getHomeHdpOdds_FH())].toString();
        HomeHdpOdds_HF = changeValueS(HomeHdpOdds_HF);
        String AwayHdpOdds = addS[(resultInfo.getAwayHdpOdds())].toString();
        AwayHdpOdds = changeValueS(AwayHdpOdds);
        String AwayHdpOdds_HF = addS[(resultInfo.getAwayHdpOdds_FH())].toString();
        AwayHdpOdds_HF = changeValueS(AwayHdpOdds_HF);
        String Hdp = addS[(resultInfo.getHdp())].toString();
        Hdp = changeValueF(Hdp);
        String Hdp_HF = addS[(resultInfo.getHdp_FH())].toString();
        Hdp_HF = changeValueF(Hdp_HF);
        String OU = addS[(resultInfo.getOU())].toString();
        if (OU.equals("0")) {
            OU = "";
        }
        OU = changeValueF(OU);
        String OU_HF = addS[(resultInfo.getOU_FH())].toString();
        if (OU_HF.equals("0")) {
            OU_HF = "";
        }
        OU_HF = changeValueF(OU_HF);
        String OverOdds = addS[(resultInfo.getOverOdds())].toString();
        OverOdds = changeValueS(OverOdds);
        String OverOdds_HF = addS[(resultInfo.getOverOdds_FH())].toString();
        OverOdds_HF = changeValueS(OverOdds_HF);
        String UnderOdds = addS[(resultInfo.getUnderOdds())].toString();
        UnderOdds = changeValueS(UnderOdds);
        String UnderOdds_HF = addS[(resultInfo.getUnderOdds_FH())].toString();
        UnderOdds_HF = changeValueS(UnderOdds_HF);

        String SocOddsId_FH = addS[(resultInfo.getSocOddsId_FH())].toString();
        String IsInetBet = addS[(resultInfo.getIsInetBet())].toString();
        String IsInetBet_FH = addS[(resultInfo.getIsInetBet_FH())].toString();
        String homeId = addS[(resultInfo.getHomeId())].toString();
        String awayId = addS[(resultInfo.getAwayId())].toString();

        String isInFavourite = addS[(resultInfo.getIsInFavourite())].toString();
        String RCHome = addS[(resultInfo.getRCHome())].toString();
        String RCAway = addS[(resultInfo.getRCAway())].toString();
        String IsHdpNew = addS[(resultInfo.getIsHdpNew())].toString();
        String IsHdpNew_FH = addS[(resultInfo.getIsHdpNew_FH())].toString();
        String IsOUNew = addS[(resultInfo.getIsOUNew())].toString();
        String IsOUNew_FH = addS[(resultInfo.getIsOUNew_FH())].toString();
        String HasHdp = addS[(resultInfo.getHasHdp())].toString();
        String HasOU = addS[(resultInfo.getHasOU())].toString();
        String HasHdp_fh = addS[(resultInfo.getHasHdp_FH())].toString();
        String HasOU_fh = addS[(resultInfo.getHasOU_FH())].toString();
        String IsRun = addS[(resultInfo.getIsRun())].toString();
        String RunHomeScore = addS[(resultInfo.getRunHomeScore())].toString();
        String RunAwayScore = addS[(resultInfo.getRunAwayScore())].toString();
        String CurMinute = addS[(resultInfo.getCurMinute())].toString();
        String Live = addS[(resultInfo.getLive())].toString();
        String Status = addS[(resultInfo.getStatus())].toString();
        String homeRank = "";
        String awayRank = "";
        if (OU.equals("-1") || OU.equals("0") || OU.equals("") || OverOdds.equals("") || UnderOdds.equals("")) {
            HasOU = "false";
        }
        if (OU_HF.equals("-1") || OU_HF.equals("0") || OU_HF.equals("") || OverOdds_HF.equals("") || UnderOdds_HF.equals("")) {
            HasOU_fh = "false";
        }
        if (HomeHdpOdds.equals("") || AwayHdpOdds.equals("") || Hdp.equals("") || Hdp.equals("-1")) {
            HasHdp = "false";
        }
        if (HomeHdpOdds_HF.equals("") || AwayHdpOdds_HF.equals("") || Hdp_HF.equals("") || Hdp_HF.equals("-1")) {
            HasHdp_fh = "false";
        }

        if (resultInfo.getHomeRank() > 0)
            homeRank = addS[(resultInfo.getHomeRank())].toString();
        if (resultInfo.getAwayRank() > 0)
            awayRank = addS[(resultInfo.getAwayRank())].toString();
        String perKey = addS[(resultInfo.getPrevKey())].toString();
        String RTSMatchId = addS[(resultInfo.getRTSMatchId())].toString();
        MatchBean itemIndex = new MatchBean(homeRank, awayRank, homeId, awayId, key, MatchDate, WorkingDate, guest, home,
                new ArrayList<>(Arrays.asList(new HandicapBean(isHomeGive, Hdp, HomeHdpOdds, AwayHdpOdds, OU, OverOdds, UnderOdds, SocOddsId, IsInetBet, IsHdpNew, IsOUNew, HasHdp, HasOU),
                        new HandicapBean(isHomeGive_HF, Hdp_HF, HomeHdpOdds_HF, AwayHdpOdds_HF, OU_HF, OverOdds_HF, UnderOdds_HF, SocOddsId_FH, IsInetBet_FH, IsHdpNew_FH, IsOUNew_FH, HasHdp_fh, HasOU_fh))), isInFavourite, RCHome, RCAway, null,
                IsRun, CurMinute,
                RunHomeScore, RunAwayScore, Live, Status,RTSMatchId);
        itemIndex.setLeagueBean(new LeagueBean(moduleId, moduleTitle));
        return addIndex(perKey, itemIndex);
    }

    private boolean addIndex(String perKey, MatchBean itemIndex) {
        if (itemIndex.getLeagueBean() == null || itemIndex.getLeagueBean().getModuleId() == null || itemIndex.getLeagueBean().getModuleId().equals(""))
            if (itemIndex.getLeagueBean() == null || itemIndex.getLeagueBean().getModuleId() == null || (itemIndex.getLeagueBean().getModuleId().equals("") && itemIndex.getLeagueBean().getModuleTitle().equals("")))
                return false;
        String perModuleId = splitModule(perKey);
        int i = 0;
        for (TableModuleBean tableModuleBean : tablelistData) {
            String indexModuleId = itemIndex.getLeagueBean().getModuleId();
            String itemModuleId = tableModuleBean.getLeagueBean().getModuleId();
            String indexModuleTitle = itemIndex.getLeagueBean().getModuleTitle();
            String itemModuleTitle = tableModuleBean.getLeagueBean().getModuleTitle();
            if (indexModuleId.equals(itemModuleId) || indexModuleTitle.equals(itemModuleTitle)) {
                int j = 0;
                for (MatchBean matchBean : tableModuleBean.getRows()) {
                    j++;
                    if (perKey.equals(matchBean.getKey())) {
                        if (!tablelistData.get(i).getRows().contains(itemIndex)) {
                            tablelistData.get(i).getRows().add(j, itemIndex);
                            return true;
                        }
                        MyLog.w("Error", "2:" + itemIndex.getKey());
                        return false;
                    }
                }
                if (adapterModelType == ModelType.Running) {
                    if (!tablelistData.get(i).getRows().contains(itemIndex)) {
                        tablelistData.get(i).getRows().add(0, itemIndex);
                        return true;
                    }
                    MyLog.w("Error", "2:" + itemIndex.getKey());
                    return false;
                } else if (adapterModelType == ModelType.Early || adapterModelType == ModelType.Today) {
                    if (!tablelistData.get(i).getRows().contains(itemIndex)) {
                        tablelistData.get(i).getRows().add(tableModuleBean.getRows().size(), itemIndex);
                        return true;
                    }
                    MyLog.w("Error", "2:" + itemIndex.getKey());
                    return false;
                }
            }
            i++;
        }
        int m = 0;
        for (TableModuleBean tableModuleBean : tablelistData) {
            m++;
            if (tableModuleBean.getLeagueBean().getModuleId().equals(perModuleId)) {
                TableModuleBean bean = new TableModuleBean(itemIndex.getLeagueBean(), new ArrayList<>(Arrays.asList(itemIndex)));
                tablelistData.add(m, bean);
                return true;
            }
        }
        if (adapterModelType == ModelType.Running) {
            TableModuleBean bean = new TableModuleBean(itemIndex.getLeagueBean(), new ArrayList<>(Arrays.asList(itemIndex)));
            tablelistData.add(0, bean);
            return true;
        } else {
            TableModuleBean bean = new TableModuleBean(itemIndex.getLeagueBean(), new ArrayList<>(Arrays.asList(itemIndex)));
            tablelistData.add(tablelistData.size(), bean);
            return true;
        }
    }

    private String splitGetKey(String sss) {
        String keys = "";
        String[] keyS = sss.split("\\u007C");
        if (keyS != null && keyS.length > 0)
            keys = keyS[0];
        return keys;
    }

    private String splitGetModuleId(String s) {
        String moudleId = "";
        String[] ids = s.split("E");
        if (ids != null && ids.length > 1)
            moudleId = ids[1];
        return moudleId;
    }

    private String splitModule(String key) {
        return splitGetModuleId(splitGetKey(key));
    }

    private boolean del(String s) {
        boolean change = false;
        String moduleId = splitModule(s);
        change = delFromModuleKey(moduleId, s);
        return change;
    }

    private boolean delFromModuleKey(String moduleId, String keys) {
        int i = 0;
        for (TableModuleBean tableModuleBean : tablelistData) {
            if (tableModuleBean.getLeagueBean().getModuleId().equals(moduleId)) {
                int j = 0;
                for (MatchBean matchBean : tableModuleBean.getRows()) {
                    if (matchBean.getKey().equals(keys)) {
                        tablelistData.get(i).getRows().remove(j);
                        if (tablelistData.get(i).getRows().size() < 1) {
                            tablelistData = new ArrayList<>(tablelistData);
                            tablelistData.remove(i);
                        }
                        return true;
                    }
                    j++;
                }
            }
            i++;
        }
        return false;
    }

    //    * IsScoreNew : 10 19 38 20
    private boolean update(String moudleId, String key, JSONObject e) {

        boolean change = false;
//        for (Iterator<String> keys = e.keys(); keys.hasNext(); ) {
//            String update = keys.next();
//            int i = 0;
//
//            for (TableModuleBean item : tablelistData) {
//                String itemModuleid = item.getLeagueBean().getModuleId();
//                if (itemModuleid.equals(moudleId)) {
//                    int j = 0;
//                    for (MatchBean bean : item.getRows()) {
//                        String itemKey = bean.getKey();
//                        if (key.equals(itemKey)) {
//                            if (Integer.valueOf(update) == resultInfo.getIsHdpNew()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).setIsHdpNew("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getIsHdpNew_FH()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(1).setIsHdpNew("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getIsOUNew()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).setIsOUNew("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getIsOUNew_FH()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(1).setIsOUNew("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getOU()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).setOU(changeValueF("" + e.get(update)));
//                                allOuClearCheck(i, j, 0);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getOU_FH()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(1).setOU(changeValueF("" + e.get(update)));
//                                allOuClearCheck(i, j, 1);
//                                change = change | true;
//                            } else if (resultInfo.getHdp() == Integer.valueOf(update)) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).setHdp(changeValueF("" + e.get(update)));
//                                allHdpClearCheck(i, j, 0);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getHdp_FH()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(1).setHdp(changeValueF("" + e.get(update)));
//                                allHdpClearCheck(i, j, 1);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getHomeHdpOdds()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).setHomeHdpOdds(changeValueS("" + e.get(update)));
//                                allHdpClearCheck(i, j, 0);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getHomeHdpOdds_FH()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(1).setHomeHdpOdds(changeValueS("" + e.get(update)));
//                                allHdpClearCheck(i, j, 1);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getAwayHdpOdds()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).setAwayHdpOdds(changeValueS("" + e.get(update)));
//                                allHdpClearCheck(i, j, 0);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getAwayHdpOdds_FH()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(1).setAwayHdpOdds(changeValueS("" + e.get(update)));
//                                allHdpClearCheck(i, j, 1);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getUnderOdds()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).setUnderOdds(changeValueS("" + e.get(update)));
//                                allOuClearCheck(i, j, 0);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getUnderOdds_FH()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(1).setUnderOdds(changeValueS("" + e.get(update)));
//                                allOuClearCheck(i, j, 1);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getOverOdds()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).setOverOdds(changeValueS("" + e.get(update)));
//                                allOuClearCheck(i, j, 0);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getOverOdds_FH()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(1).setOverOdds(changeValueS("" + e.get(update)));
//                                allOuClearCheck(i, j, 1);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getIsHomeGive()) {
//                                String HomeGiveNow = tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).getIsHomeGive();
//                                String HomeGiveUpdate = e.get(update).toString();
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).setIsHomeGive(HomeGiveUpdate);
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getIsHomeGive_FH()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(1).setIsHomeGive("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getAwayRank()) {
//                                tablelistData.get(i).getRows().get(j).setAwayRank("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getHomeRank()) {
//                                tablelistData.get(i).getRows().get(j).setHomeRank("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getLive()) {
//                                tablelistData.get(i).getRows().get(j).setLive("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getStatus()) {
//                                tablelistData.get(i).getRows().get(j).setStatus("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getWorkingDate()) {
//                                tablelistData.get(i).getRows().get(j).setWorkingDate("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getMatchDate()) {
//                                tablelistData.get(i).getRows().get(j).setMatchDate("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getRunAwayScore()) {
//                                tablelistData.get(i).getRows().get(j).setRunAwayScore("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getRunHomeScore()) {
//                                tablelistData.get(i).getRows().get(j).setRunHomeScore("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getCurMinute()) {
//                                tablelistData.get(i).getRows().get(j).setCurMinute(e.get(update) + "");
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getHasHdp()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).setHasHdp("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getHasHdp_FH()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(1).setHasHdp("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getHasOU()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(0).setHasOu("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getHasOU_FH()) {
//                                tablelistData.get(i).getRows().get(j).getHandicapBeans().get(1).setHasOu("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getRCHome()) {
//                                tablelistData.get(i).getRows().get(j).setRCHome("" + e.get(update));
//                                change = change | true;
//                            } else if (Integer.valueOf(update) == resultInfo.getRCAway()) {
//                                tablelistData.get(i).getRows().get(j).setRCAway("" + e.get(update));
//                                change = change | true;
//                            } else {
//                                change = change | false;
//                            }
//                        }
//                        j++;
//                    }
//                }
//                i++;
//            }
//        }
        return change;
    }

    private void allHdpClearCheck(int i, int j, int position) {
        String hdp = tablelistData.get(i).getRows().get(j).getHandicapBeans().get(position).getHdp();
        String homeHdpOdds = tablelistData.get(i).getRows().get(j).getHandicapBeans().get(position).getHomeHdpOdds();
        String awayHdpOdds = tablelistData.get(i).getRows().get(j).getHandicapBeans().get(position).getAwayHdpOdds();
        if (hdp.equals("") || hdp.equals("-1") || homeHdpOdds.equals("") || homeHdpOdds.equals("0") || awayHdpOdds.equals("") || awayHdpOdds.equals("0")) {
            tablelistData.get(i).getRows().get(j).getHandicapBeans().get(position).setHasHdp("false");
        } else {
            tablelistData.get(i).getRows().get(j).getHandicapBeans().get(position).setHasHdp("true");
        }

    }

    private void allOuClearCheck(int i, int j, int position) {
        String ou = tablelistData.get(i).getRows().get(j).getHandicapBeans().get(position).getOU();
        String overOdds = tablelistData.get(i).getRows().get(j).getHandicapBeans().get(position).getOverOdds();
        String underOdds = tablelistData.get(i).getRows().get(j).getHandicapBeans().get(position).getUnderOdds();
        if (ou.equals("") || ou.equals("-1") || ou.equals("0") || overOdds.equals("") || overOdds.equals("0") || underOdds.equals("") || underOdds.equals("0")) {
            tablelistData.get(i).getRows().get(j).getHandicapBeans().get(position).setHasOu("false");
        } else {
            tablelistData.get(i).getRows().get(j).getHandicapBeans().get(position).setHasOu("true");
        }

    }


    public void setBetType(int betType) {
        this.betType = betType;
    }

    public void setView(View view) {
        this.view = view;
    }

    public class ModelType {
        public final static int Running = 0x0210;
        public final static int Today = 0x0220;
        public final static int Early = 0x0230;
        public final static int TodayClearance = 0x0221;
        public final static int EarlyClearance = 0x0231;
        public final static int Default = 0;
    }
}
