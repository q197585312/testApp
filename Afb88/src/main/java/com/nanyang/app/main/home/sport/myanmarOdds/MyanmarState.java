package com.nanyang.app.main.home.sport.myanmarOdds;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.main.SportState;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class MyanmarState extends SportState<MyanmarInfo, SportContract.View<MyanmarInfo>> {

    private boolean isCollection;


    public MyanmarState(SportContract.View baseView) {
        super(baseView);
    }

    public boolean isCollection() {
        return isCollection;
    }

    public boolean collection() {
        isCollection = !isCollection;
        initAllData(allData);
        return isCollection;
    }

    @Override
    public IAdapterHelper<MyanmarInfo> onSetAdapterHelper() {
        return new MyanmarAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<MyanmarInfo>(baseRecyclerAdapter) {
            @Override
            public boolean isItemCollection(MyanmarInfo item) {
                return isItemCollectionCommon(item);
            }

            @Override
            public void clickOdds(TextView v, MyanmarInfo item, String type, boolean isHf, String odds, int oid, String sc, boolean hasPar) {
                IBetHelper<MyanmarInfo> helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, oid, type, odds, v, isHf, "", hasPar);
            }

            @Override
            public void clickView(View v, MyanmarInfo item, int position) {
                switch (v.getId()) {
                    case R.id.module_right_mark_tv:
                        getBaseView().clickItemAdd(v, item, position);
                        break;
                }

            }

        };
    }


    public IBetHelper<MyanmarInfo> onSetBetHelper() {
        return new MyanmarBetHelper(getBaseView());
    }


    /*  @Override
      protected List<TableSportInfo<MyanmarInfo>> updateFirstData(JSONArray dataListArray) throws JSONException {
          ArrayList<TableSportInfo<MyanmarInfo>> tableModules = new ArrayList<>();
          if (dataListArray.length() > 0) {
              for (int i = 0; i < dataListArray.length(); i++) {
                  LeagueBean leagueBean;
                  List<MyanmarInfo> matchList = new ArrayList<>();
                  JSONArray jsonArray3 = dataListArray.getJSONArray(i);
                  if (jsonArray3.length() > 1) {
                      JSONArray LeagueArray = jsonArray3.getJSONArray(0);
                      if (LeagueArray.length() > 1) {
                          leagueBean = new LeagueBean(LeagueArray.get(0).toString(), LeagueArray.getString(1));
                      } else {
                          continue;
                      }
                      JSONArray LeagueMatchArray = jsonArray3.getJSONArray(1);
                      if (LeagueMatchArray.length() > 0) {
                          for (int j = 0; j < LeagueMatchArray.length(); j++) {
                              JSONArray matchArray = LeagueMatchArray.getJSONArray(j);
                              matchList.add(parseMatchMyanmar(matchArray));
                          }
                      } else {
                          continue;
                      }
                      tableModules.add(new TableSportInfo<>(leagueBean, matchList));
                  }
              }
          }
          return tableModules;
      }*/
    @Override
    protected MyanmarInfo parseMatch(JSONArray matchArray, boolean notify) throws JSONException {
        MyanmarInfo info = new MyanmarInfo();

        info.setSocOddsId(matchArray.opt(0) == null ? "" : matchArray.optString(0));         //全场ID
        info.setSocOddsId_FH(matchArray.opt(1) == null ? "" : matchArray.optString(1));      //半场ID
        info.setLive(matchArray.opt(2) == null ? "" : matchArray.optString(2));              //是否显示live
        info.setHomeId(matchArray.opt(3) == null ? "" : matchArray.optString(3));            //主队id
        info.setAwayId(matchArray.opt(4) == null ? "" : matchArray.optString(4));            //客队id
        info.setIsInFavourite(matchArray.opt(5) == null ? "" : matchArray.optString(5));     //是否加入我的最爱
        info.setScoreNew(matchArray.opt(6) == null ? "" : matchArray.optString(6));          //IsScoreNew;		//有没有更新比数， 作闪闪图片用
        info.setIsLastCall(matchArray.opt(7) == null ? "" : matchArray.optString(7));        //是否最后一次请求数据
        info.setMatchDate(matchArray.opt(8) == null ? "" : matchArray.optString(8));         //比赛时间
        info.setStatus(matchArray.opt(9) == null ? "" : matchArray.optString(9));            //状态(开始或者还没开始)
        info.setCurMinute(matchArray.opt(10) == null ? "" : matchArray.optString(10));         //比赛进行了多少时间
        info.setIsInetBet(matchArray.opt(11) == null ? "" : matchArray.optString(11));         //允许或不允许成员下注
        info.setHasHdp(matchArray.opt(12) == null ? "" : matchArray.optString(12));            //是否有hdp
        info.setHdpOdds(matchArray.opt(13) == null ? "" : matchArray.optString(13));           //分出HomeHdpOdds，AOdds
        info.setIsHomeGive(matchArray.opt(14) == null ? "" : matchArray.optString(14));        //是否主队让球
        info.setHomeRank(matchArray.opt(15) == null ? "" : matchArray.optString(15));          //主队排名
        info.setHome(matchArray.opt(16) == null ? "" : matchArray.optString(16));              //主队名
        info.setRCHome(matchArray.opt(17) == null ? "" : matchArray.optString(17));            //主队红牌数
        info.setRTSMatchId(matchArray.opt(18) == null ? "" : matchArray.optString(18));        //RTS livecast 图标所用
        info.setAwayRank(matchArray.opt(19) == null ? "" : matchArray.optString(19));          //客队排名
        info.setAway(matchArray.opt(20) == null ? "" : matchArray.optString(20));              //客队名
        info.setRCAway(matchArray.opt(21) == null ? "" : matchArray.optString(21));            //客队红牌数
        info.setHdp(matchArray.opt(22) == null ? "" : matchArray.optString(22));               //hdp让球数
        info.setHOdds(matchArray.opt(23) == null ? "" : matchArray.optString(23));       //主队hdp赔率
        info.setAOdds(matchArray.opt(24) == null ? "" : matchArray.optString(24));       //客队hdp赔率
        info.setHasOU(matchArray.opt(25) == null ? "" : matchArray.optString(25));             //是否有ou
        info.setOU(matchArray.opt(26) == null ? "" : matchArray.optString(26));                //大小球
        info.setRunHomeScore(matchArray.opt(27) == null ? "" : matchArray.optString(27));      //滚球主队的分数
        info.setRunAwayScore(matchArray.opt(28) == null ? "" : matchArray.optString(28));      //滚球客队的分数
        info.setOOdds(matchArray.opt(29) == null ? "" : matchArray.optString(29));          //over赔率
        info.setUOdds(matchArray.opt(30) == null ? "" : matchArray.optString(30));         //under赔率
        info.setOUOdds(matchArray.opt(31) == null ? "" : matchArray.optString(31));            //从中分出OverOdds，UOdds
        info.setHasHdp_FH(matchArray.opt(32) == null ? "" : matchArray.optString(32));         //半场是否有hdp
        info.setHdp_FH(matchArray.opt(33) == null ? "" : matchArray.optString(33));            //半场hdp让球
        info.setIsHomeGive_FH(matchArray.opt(34) == null ? "" : matchArray.optString(34));     //半场是否主队让球
        info.setHOdds_FH(matchArray.opt(35) == null ? "" : matchArray.optString(35));    //半场主队hdp赔率
        info.setAOdds_FH(matchArray.opt(36) == null ? "" : matchArray.optString(36));    //半场客队hdp赔率
        info.setHdpOdds_FH(matchArray.opt(37) == null ? "" : matchArray.optString(37));        //分出HomeHdpOdds，AOdds
        info.setIsInetBet_FH(matchArray.opt(38) == null ? "" : matchArray.optString(38));      //半场允许或不允许成员下注
        info.setHasOU_FH(matchArray.opt(39) == null ? "" : matchArray.optString(39));          //半场是否有ou
        info.setOU_FH(matchArray.opt(40) == null ? "" : matchArray.optString(40));             //半场大小球
        info.setRunHomeScore_FH(matchArray.opt(41) == null ? "" : matchArray.optString(41));   //半场滚球主队的分数
        info.setRunAwayScore_FH(matchArray.opt(42) == null ? "" : matchArray.optString(42));   //半场滚球客队的分数
        info.setOOdds_FH(matchArray.opt(43) == null ? "" : matchArray.optString(43));       //半场over赔率
        info.setUOdds_FH(matchArray.opt(44) == null ? "" : matchArray.optString(44));      //半场under赔率
        info.setOUOdds_FH(matchArray.opt(45) == null ? "" : matchArray.optString(45));         //分出OverOdds_FH，UOdds_FH
        info.setStatsId(matchArray.opt(46) == null ? "" : matchArray.optString(46));           //- statistic id
        info.setWorkingDate(matchArray.opt(47) == null ? "" : matchArray.optString(47));       //比赛开始的时间
        info.setPreSocOddsId(matchArray.opt(48) == null ? "" : matchArray.optString(48));
        info.setIsHideMM(matchArray.opt(49) == null ? "" : matchArray.optString(49));
        info.setMMHdpPct(matchArray.opt(50) == null ? "" : matchArray.optString(50));
        info.setMMOUPct(matchArray.opt(51) == null ? "" : matchArray.optString(51));
        info.setMMHdp(matchArray.opt(52) == null ? "" : matchArray.optString(52));
        info.setMMIsHomeGive(matchArray.opt(53) == null ? "" : matchArray.optString(53));
        info.setMMHdpOdds(matchArray.opt(54) == null ? "" : matchArray.optString(54));
        info.setMMOU(matchArray.opt(55) == null ? "" : matchArray.optString(55));
        info.setMMOUOdds(matchArray.opt(56) == null ? "" : matchArray.optString(56));
        info.setHasX12(matchArray.opt(57) == null ? "" : matchArray.optString(57));
        info.setHasPar(matchArray.opt(58) == null ? "" : matchArray.optString(58));
        info.setMExtraTime(matchArray.opt(59) == null ? "" : matchArray.optString(59));
        info.setNotify(notify);
        return info;

    }


    @Override
    protected List<TableSportInfo<MyanmarInfo>> filterChildData(List<TableSportInfo<MyanmarInfo>> allData) {
        return allData;
    }


    @Override
    public boolean mix() {
        return false;
    }

//    @Override
//    protected List<List<String>> initHeaderList() {
//        List<List<String>> lists = super.initHeaderList();
//        lists.add(new ArrayList<>(Arrays.asList(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.AFB_FT_HDP), getBaseView().getIBaseContext().getBaseActivity().getString(R.string.AFB_FT_OU))));
//        return lists;
//    }

}
