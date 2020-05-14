package com.nanyang.app.main.home.sport.main;

import com.nanyang.app.main.home.sport.model.BallInfo;

import org.json.JSONArray;

/**
 * Created by Administrator on 2018/11/27.
 */

public class AfbParseHelper<T extends BallInfo> {
    public T parseJsonArray(JSONArray matchArray, boolean notify) {
        BallInfo info = new BallInfo();
        info.setSocOddsId(matchArray.optString(0));
        info.setPreSocOddsId(matchArray.optString(1));
        info.setSocOddsId_FH(matchArray.optString(2));
        info.setLive(matchArray.optString(3));
        info.setIsLastCall(matchArray.optString(4));
        info.setMatchDate(matchArray.optString(5));

        info.setStatus(matchArray.optString(6));
        info.setCurMinute(matchArray.optString(7));
        info.setIsInetBet(matchArray.optString(8));
        info.setIsHomeGive(matchArray.optString(9));
        info.setRTSMatchId(matchArray.optString(10));
        info.setHomeId(matchArray.optString(11));
        info.setAwayId(matchArray.optString(12));

        info.setHome(matchArray.optString(13));
        info.setAway(matchArray.optString(14));
        info.setHomeRank(matchArray.optString(15));
        info.setAwayRank(matchArray.optString(16));
        info.setRunHomeScore(matchArray.optString(17));
        info.setRunAwayScore(matchArray.optString(18));
        info.setRCHome(matchArray.optString(19));
        info.setRCAway(matchArray.optString(20));

        info.setGamesSum(matchArray.optString(21));
        info.setHdp(matchArray.optString(22));
        info.setOU(matchArray.optString(23));
        info.setHOdds(matchArray.optString(24));
        info.setAOdds(matchArray.optString(25));
        info.setOOdds(matchArray.optString(26));
        info.setUOdds(matchArray.optString(27));
        info.setOddOdds(matchArray.optString(28));
        info.setEvenOdds(matchArray.optString(29));
        info.setX12_1Odds(matchArray.optString(30));
        info.setX12_XOdds(matchArray.optString(31));
        info.setX12_2Odds(matchArray.optString(32));
        info.setHdpOdds(matchArray.optString(33));
        info.setOUOdds(matchArray.optString(34));
        info.setOEOdds(matchArray.optString(35));
        info.setHasPar(matchArray.optString(36));

        info.setIsInetBet_FH(matchArray.optString(37));
        info.setIsHomeGive_FH(matchArray.optString(38));
        info.setHdp_FH(matchArray.optString(39));
        info.setOU_FH(matchArray.optString(40));
        info.setHOdds_FH(matchArray.optString(41));
        info.setAOdds_FH(matchArray.optString(42));
        info.setOOdds_FH(matchArray.optString(43));
        info.setUOdds_FH(matchArray.optString(44));
        info.setOddOdds_FH(matchArray.optString(45));
        info.setEvenOdds_FH(matchArray.optString(46));
        info.setX12_1Odds_FH(matchArray.optString(47));
        info.setX12_XOdds_FH(matchArray.optString(48));
        info.setX12_2Odds_FH(matchArray.optString(49));
        info.setHdpOdds_FH(matchArray.optString(50));
        info.setOUOdds_FH(matchArray.optString(51));
        info.setOEOdds_FH(matchArray.optString(52));
        info.setStatsId(matchArray.optString(53));
        info.setMExtraTime(matchArray.optString(54));

        info.setWorkingDate(matchArray.optString(55));
        info.setHasPar_FH(matchArray.optString(56));
        info.setTvPathIBC(matchArray.optString(57));
        info.setShowGoal(matchArray.optString(58));
        info.setIsHomeGoal(matchArray.optString(59));
        info.setIsLast(matchArray.optString(60));
        //61代表的是MainModuleId    62代表MainHomeId  63代表 MainAwayId
        info.setMainModuleId(matchArray.optString(61));
        info.setMainHomeId(matchArray.optString(62));
        info.setMainAwayId(matchArray.optString(63));

        return (T) info;
    }

    /*public T parseJsonArray(JSONArray matchArray ,boolean notify)

    {
        BallInfo info = new BallInfo();
        info.setSocOddsId(matchArray.optString(0));
        info.setPreSocOddsId(matchArray.optString(1));
        info.setSocOddsId_FH(matchArray.optString(2));
        info.setLive(matchArray.optString(3));
        info.setIsLastCall(matchArray.optString(4));
        info.setMatchDate(matchArray.optString(5));
        info.setStatus(matchArray.optString(6));
        info.setCurMinute(matchArray.optString(7));
        info.setIsInetBet(matchArray.optString(8));
        info.setIsHomeGive(matchArray.optString(9));
        info.setRTSMatchId(matchArray.optString(10));
        info.setHomeId(matchArray.optString(11));
        info.setAwayId(matchArray.optString(12));
        info.setHome(matchArray.optString(13));
        info.setAway(matchArray.optString(14));
        info.setHomeRank(matchArray.optString(15));
        info.setAwayRank(matchArray.optString(16));
        info.setRunHomeScore(matchArray.optString(17));
        info.setRunAwayScore(matchArray.optString(18));
        info.setRCHome(matchArray.optString(19));
        info.setRCAway(matchArray.optString(20));
        info.setGamesSum(matchArray.optString(21));
        info.setHdp(matchArray.optString(22));
        info.setOU(matchArray.optString(23));
        info.setHOdds(matchArray.optString(24));
        info.setAOdds(matchArray.optString(25));
        info.setOOdds(matchArray.optString(26));
        info.setUOdds(matchArray.optString(27));
        info.setOddOdds(matchArray.optString(28));
        info.setEvenOdds(matchArray.optString(29));
        info.setX12_1Odds(matchArray.optString(30));
        info.setX12_XOdds(matchArray.optString(31));
        info.setX12_2Odds(matchArray.optString(32));
        info.setHdpOdds(matchArray.optString(33));
        info.setOUOdds(matchArray.optString(34));
        info.setOEOdds(matchArray.optString(35));
        info.setHasPar(matchArray.optString(36));
        info.setIsInetBet_FH(matchArray.optString(37));
        info.setIsHomeGive_FH(matchArray.optString(38));
        info.setHdp_FH(matchArray.optString(39));
        info.setOU_FH(matchArray.optString(40));
        info.setHOdds_FH(matchArray.optString(41));
        info.setAOdds_FH(matchArray.optString(42));
        info.setOOdds_FH(matchArray.optString(43));
        info.setUOdds_FH(matchArray.optString(44));
        info.setOddOdds_FH(matchArray.optString(45));
        info.setEvenOdds_FH(matchArray.optString(46));
        info.setX12_1Odds_FH(matchArray.optString(47));
        info.setX12_XOdds_FH(matchArray.optString(48));
        info.setX12_2Odds_FH(matchArray.optString(49));
        info.setHdpOdds_FH(matchArray.optString(50));
        info.setOUOdds_FH(matchArray.optString(51));
        info.setOEOdds_FH(matchArray.optString(52));
        info.setStatsId(matchArray.optString(53));
        info.setMExtraTime(matchArray.optString(54));
        info.setWorkingDate(matchArray.optString(55));
        info.setHasPar_FH(matchArray.optString(56));
        info.setNotify(notify);
        return (T) info;
    }*/


    public String getBetTypeFromId(String itemId) {
        String[] split = itemId.split("\\|");
        if (split.length > 3) {
            return split[1];
        }
        return "";
    }

    public String getSocOddsIdFromId(String itemId) {
        String[] split = itemId.split("\\|");
        if (split.length > 3) {
            return split[3];
        }
        return "";
    }

    public String getSocOddsG(String itemId) {
        String[] split = itemId.split("\\|");
        if (split.length > 3) {
            return split[2];
        }
        return "";
    }

    public String getScFormId(String itemId) {
        String[] split = itemId.split("\\|");
        if (split.length > 5) {
            return split[5];
        }
        return "";
    }
}
