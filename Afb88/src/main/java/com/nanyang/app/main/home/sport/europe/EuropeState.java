package com.nanyang.app.main.home.sport.europe;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.e_sport.ESportRunningState;
import com.nanyang.app.main.home.sport.e_sport.ESportTodayState;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.main.SportState;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.training.ScrollLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class EuropeState extends SportState<EuropeInfo, SportContract.View<EuropeInfo>> {


    public EuropeState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    protected List<TableSportInfo<EuropeInfo>> filterChildData(List<TableSportInfo<EuropeInfo>> dateTemp) {
        return dateTemp;
    }



    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new ESportTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new ESportRunningState(getBaseView()));
                break;

        }
    }
    @Override
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Early), "Early"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Running), "Running"));
        return types;
    }

    @Override
    public boolean isMix() {
        return false;
    }



    @Override
    protected IBetHelper<EuropeInfo> onSetBetHelper() {
        return new EuropeBetHelper(getBaseView());
    }

    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> texts = new ArrayList<>();
        List<String> items0 = new ArrayList<>(Arrays.asList(getBaseView().getContextActivity().getString(R.string.full_time), getBaseView().getContextActivity().getString(R.string.half_time)));
        texts.add(items0);
        return texts;
    }


    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean collection() {
        return false;
    }

    @Override
    public SportAdapterHelper<EuropeInfo> onSetAdapterHelper() {
        return new SportAdapterHelper<EuropeInfo>() {
            @Override
            public void onConvert(MyRecyclerViewHolder helper, final int position, final EuropeInfo item) {

                TextView matchTitleTv = helper.getView(R.id.module_match_title_tv);
                View headV = helper.getView(R.id.module_match_head_v);
                TextView dateTv = helper.getView(R.id.module_match_date_tv);
                TextView timeTv = helper.getView(R.id.module_match_time_tv);
                TextView liveTv = helper.getView(R.id.module_match_live_iv);
                TextView homeTv = helper.getTextView(R.id.module_match_home_team_tv);
                TextView awayTv = helper.getTextView(R.id.module_match_away_team_tv);

                TextView full1 = helper.getTextView(R.id.europe_1_full_time_odds_tv);
                TextView full2 = helper.getTextView(R.id.europe_2_full_time_odds_tv);
                TextView fullx = helper.getTextView(R.id.europe_x_full_time_odds_tv);
                TextView half1 = helper.getTextView(R.id.europe_1_half_time_odds_tv);
                TextView half2 = helper.getTextView(R.id.europe_2_half_time_odds_tv);
                TextView halfx = helper.getTextView(R.id.europe_x_half_time_odds_tv);

                TextView tvFull1 = helper.getTextView(R.id.europe_1_full_time_tv);
                TextView tvFull2 = helper.getTextView(R.id.europe_2_full_time_tv);
                TextView tvFullx = helper.getTextView(R.id.europe_x_full_time_tv);
                TextView tvHalf1 = helper.getTextView(R.id.europe_1_half_time_tv);
                TextView tvHalf2 = helper.getTextView(R.id.europe_2_half_time_tv);
                TextView tvHalfx = helper.getTextView(R.id.europe_x_half_time_tv);

                String timeDate = item.getMatchDate();
                if (timeDate.length() > 6) {
                    String time = timeDate.substring(timeDate.length() - 7, timeDate.length());
                    time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
                    timeTv.setText(time);
                    dateTv.setText(timeDate.substring(0, timeDate.length() - 7));
                } else {
                    timeTv.setText(timeDate);
                }
                if (!item.getLive().equals("")) {
                    if (item.getLive().contains("LIVE")) {
                        dateTv.setText("LIVE");
                        liveTv.setVisibility(View.GONE);
                    } else {
                        String channel = item.getLive();
                        channel = Html.fromHtml(channel).toString();
                        int n = channel.indexOf(" ");


                        if (channel.length() > 6 && n > 0 && n < channel.length() - 1) {
                            String channel1 = channel.substring(0, n);
                            String channel2 = channel.substring(n + 1, channel.length());

                            liveTv.setTextSize(7);
                            if (channel2.length() >= 6)
                                dateTv.setTextSize(8);
                            else {
                                dateTv.setTextSize(9);
                            }
                            liveTv.setVisibility(View.VISIBLE);
                            liveTv.setText(channel1.trim());
                            dateTv.setText(channel2);
                        } else {
                            liveTv.setVisibility(View.GONE);
                            if (channel.trim().length() > 6)
                                dateTv.setTextSize(8);
                            dateTv.setText(channel.trim());

                        }
                    }
                } else {
                    if (item.getMatchDate().length() > 6) {
                        String date = item.getMatchDate().substring(0, 5);
                        dateTv.setText(date);
                    } else {
                        dateTv.setText(item.getMatchDate());
                    }
                }

                homeTv.setText(item.getHome());
                awayTv.setText(item.getAway());
                if (item.getHasX12().equals("1")) {
                    tvFull1.setText("1");
                    tvFull2.setText("2");
                    tvFullx.setText("X");

                    full1.setText(item.getX12_1Odds());
                    fullx.setText(item.getX12_XOdds());
                    full2.setText(item.getX12_2Odds());
                    full1.setOnClickListener(new itemClick(back, position, item, item.getX12_1Odds(), "1",false));
                    fullx.setOnClickListener(new itemClick(back, position, item, item.getX12_XOdds(), "X",false));
                    full2.setOnClickListener(new itemClick(back, position, item, item.getX12_2Odds(), "2",false));


                } else {
                    tvFull1.setText("");
                    tvFull2.setText("");
                    tvFullx.setText("");

                    full1.setText("");
                    full2.setText("");
                    fullx.setText("");
                }
                if (item.getHasX12_FH().equals("1")) {
                    tvHalf1.setText("1");
                    tvHalf2.setText("2");
                    tvHalfx.setText("X");

                    half1.setText(item.getX12_1Odds_FH());
                    halfx.setText(item.getX12_XOdds_FH());
                    half2.setText(item.getX12_2Odds_FH());
                    half1.setOnClickListener(new itemClick(back, position, item, item.getX12_1Odds_FH(), "1",true));
                    halfx.setOnClickListener(new itemClick(back, position, item, item.getX12_XOdds_FH(), "X",true));
                    half2.setOnClickListener(new itemClick(back, position, item, item.getX12_2Odds_FH(), "2",true));

                } else {
                    tvHalf1.setText("");
                    tvHalf2.setText("");
                    tvHalfx.setText("");
                    half1.setText("");
                    half2.setText("");
                    halfx.setText("");
                }

                if (item.getType() == SportInfo.Type.ITME) {
                    matchTitleTv.setVisibility(View.GONE);
                    headV.setVisibility(View.GONE);

                } else {
                    matchTitleTv.setVisibility(View.VISIBLE);
                    headV.setVisibility(View.VISIBLE);
                    matchTitleTv.setText(item.getModuleTitle());
                    if (position == 0) {
                        headV.setVisibility(View.GONE);
                    }
                }
                String oldHomeName = "";
                String oldAwayName = "";
                String oldModuleTitle = "";
                if (position > 0) {
                    oldHomeName = back.getItem(position - 1).getHome();
                    oldAwayName = back.getItem(position - 1).getAway();

                    oldModuleTitle = back.getItem(position - 1).getModuleTitle().toString();
                }
                if (item.getModuleTitle().equals(oldModuleTitle) && position != 0 && oldHomeName.equals(item.getHome()) && oldAwayName.equals(item.getAway())) {
                    View tvRightMark = helper.getView(R.id.module_right_mark_tv);
                    tvRightMark.setVisibility(View.INVISIBLE);

                    onMatchRepeat(helper, item, position,back);
                } else {
                    View tvRightMark = helper.getView(R.id.module_right_mark_tv);
                    tvRightMark.setVisibility(View.VISIBLE);
                    tvRightMark.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            back.clickView(v, item,position);
                        }
                    });

                    onMatchNotRepeat(helper, item, position,back);
                }
                onChildConvert(helper, position, item);
            }

            @Override
            public int onSetAdapterItemLayout() {
                return R.layout.sport_europe_item;
            }

        };
    }

    protected abstract void onChildConvert(MyRecyclerViewHolder helper, int position, EuropeInfo item);

    protected void onMatchNotRepeat(MyRecyclerViewHolder helper, EuropeInfo item, int position, SportAdapterHelper.ItemCallBack<EuropeInfo> back) {
        repMap.put(position, false);
    }

    protected void onMatchRepeat(MyRecyclerViewHolder helper, EuropeInfo item, int position, SportAdapterHelper.ItemCallBack<EuropeInfo> back) {
        repMap.put(position, true);
    }
    public Map<Integer, Boolean> getRepMap() {
        return repMap;
    }

    Map<Integer, Boolean> repMap = new HashMap<>();
    public int getNextNotRepeat(int position) {
        if(position<getBaseRecyclerAdapter().getItemCount()) {
            if (repMap.get(position + 1)==null||!repMap.get(position + 1)) {
                return position;
            } else {
                return getNextNotRepeat(position + 1);
            }
        }
        else
            return 0;

    }

    @Override
    protected List<TableSportInfo<EuropeInfo>> updateJsonData(JSONArray dataListArray) throws JSONException {
        ArrayList<TableSportInfo<EuropeInfo>> tableModules = new ArrayList<>();
        if (dataListArray.length() > 0) {
            for (int i = 0; i < dataListArray.length(); i++) {
                LeagueBean leagueBean;
                List<EuropeInfo> matchList = new ArrayList<>();
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
                            matchList.add(parseMatch(matchArray));
                        }
                    } else {
                        continue;
                    }
                    tableModules.add(new TableSportInfo<>(leagueBean, matchList));
                }
            }
        }
        return tableModules;
    }

    private EuropeInfo parseMatch(JSONArray matchArray) throws JSONException {
        EuropeInfo info = new EuropeInfo();
        info.setSocOddsId(matchArray.getString(0));
        info.setSocOddsId_FH(matchArray.getString(1));
        info.setLive(matchArray.getString(2));
        info.setIsLastCall(matchArray.getString(3));
        info.setMatchDate(matchArray.getString(4));
        info.setHome(matchArray.getString(5));
        info.setAway(matchArray.getString(6));
        info.setOENew(matchArray.getString(7));
        info.setIsInetBet(matchArray.getString(8));
        info.setHasOE(matchArray.getString(9));
        info.setOEOdds(matchArray.getString(10));
        info.setOddOdds(matchArray.getString(11));
        info.setEvenOdds(matchArray.getString(12));
        info.setX12New(matchArray.getString(13));
        info.setHasX12(matchArray.getString(14));
        info.setX12_1Odds(matchArray.getString(15));
        info.setX12_XOdds(matchArray.getString(16));
        info.setX12_2Odds(matchArray.getString(17));
        info.setIsInetBet_FH(matchArray.getString(18));
        info.setHasX12_FH(matchArray.getString(19));
        info.setX12_1Odds_FH(matchArray.getString(20));
        info.setX12_XOdds_FH(matchArray.getString(21));
        info.setX12_2Odds_FH(matchArray.getString(22));
        info.setX12New_FH(matchArray.getString(23));
        info.setPreSocOddsId(matchArray.getString(24));
        info.setCurMinute(matchArray.getString(25));
        info.setStatus(matchArray.getString(26));
        info.setRTSMatchId(matchArray.getString(27));
        info.setStatsId(matchArray.getString(28));
        info.setRCHome(matchArray.getString(29));
        info.setRCAway(matchArray.getString(30));
        return info;

    }

    @Override
    protected int getIndexSocOddsId() {
        return 0;
    }

    @Override
    protected int getIndexPreSocOddsId() {
        return 1;
    }


    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new SportAdapterHelper.ItemCallBack<EuropeInfo>() {
            @Override
            public EuropeInfo getItem(int position) {
                return baseRecyclerAdapter.getItem(position);
            }

            @Override
            public void clickOdds(TextView v, EuropeInfo item, String type, boolean isHf, String odds) {
                IBetHelper helper = onSetBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, type, odds, v, isHf, "g=5");
            }

            @Override
            public void clickView(View v, final EuropeInfo item, int position) {
                switch (v.getId()) {
                    case R.id.module_right_mark_tv:
                        getBaseView().clickItemAdd(v,item,"common");

                        break;
                    case R.id.iv_hall_btn:
                        clickHallBtn(v, item, position);
                        break;
                }
            }

            @Override
            public ScrollLayout onSetHeaderFollower() {
                return getBaseView().onSetScrollHeader();
            }
        };
    }

    protected void clickHallBtn(View v, final EuropeInfo item, int position) {

    }
    public String getParentText() {
        return getBaseView().getContextActivity().getString(R.string.Europe_View);
    }

    class itemClick implements View.OnClickListener {
        SportAdapterHelper.ItemCallBack<EuropeInfo> back;
        int position;
        EuropeInfo item;
        String odds;
        String type;
        boolean isHf;

        public itemClick(SportAdapterHelper.ItemCallBack<EuropeInfo> back, int position, EuropeInfo item, String odds, String type,boolean isHf) {
            this.position = position;
            this.item = item;
            this.odds = odds;
            this.back = back;
            this.type = type;
            this.isHf=isHf;
        }

        @Override
        public void onClick(View v) {
            if (!odds.equals("") && Float.valueOf(odds) != 0)
                back.clickOdds((TextView) v, item, type, isHf, odds);
        }
    }
}
