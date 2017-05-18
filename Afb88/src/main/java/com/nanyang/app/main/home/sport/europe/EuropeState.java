package com.nanyang.app.main.home.sport.europe;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.main.SportState;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.training.ScrollLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class EuropeState extends SportState<EuropeInfo, SportContract.View<EuropeInfo>> {


    public EuropeState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    protected List<TableSportInfo<EuropeInfo>> filterChildData(List<TableSportInfo<EuropeInfo>> dateTemp) {
        return null;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {

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
    public boolean mix() {
        return false;
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
        List<List<String>> lists = super.initHeaderList();
        lists.get(0).set(0,getBaseView().getContextActivity().getString(R.string.full_time));
        lists.get(1).set(0,getBaseView().getContextActivity().getString(R.string.half_time));
        return lists;
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
                View tvRightMark = helper.getView(R.id.module_right_mark_tv);
                TextView full1 = helper.getTextView(R.id.europe_1_full_time_odds_tv);
                TextView full2 = helper.getTextView(R.id.europe_2_full_time_odds_tv);
                TextView fullx = helper.getTextView(R.id.europe_x_full_time_odds_tv);
                TextView half1 = helper.getTextView(R.id.europe_1_half_time_odds_tv);
                TextView half2 = helper.getTextView(R.id.europe_2_half_time_odds_tv);
                TextView halfx = helper.getTextView(R.id.europe_x_half_time_odds_tv);

                String time = item.getMatchDate();
                if (time.length() > 6) {
                    time = time.substring(time.length() - 7, time.length());
                    time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
                    timeTv.setText(time);
                    dateTv.setText(time.substring(0,time.length() - 7));
                }
                else{
                    timeTv.setText(time);
                }
                homeTv.setText(item.getHome());
                awayTv.setText(item.getAway());
                if(item.getHasX12().equals("1")){

                }else{
                    full1.setText("");
                    full2.setText("");
                    fullx.setText("");
                }



            }

            @Override
            public int onSetAdapterItemLayout() {
                return R.layout.sport_europe_item;
            }

        };
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
        info.setHome(matchArray.getString(1));
        info.setIsInetBet(matchArray.getString(2));
        info.setIsX12New(matchArray.getString(3));
        info.setHasX12(matchArray.getString(4));
        info.setX12_1Odds(matchArray.getString(5));
        info.setPreSocOddsId(matchArray.getString(6));
        return info;

    }

    @Override
    protected int getIndexSocOddsId() {
        return 0;
    }

    @Override
    protected int getIndexPreSocOddsId() {
        return 6;
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
                //http://main55.afb88.com/_Bet/JRecPanel.aspx?g=50&b=1&oId=11188250&odds=2.5
                helper.clickOdds(item,type,odds,v,  isHf,"");
            }

            @Override
            public void clickView(View v, EuropeInfo item,int position) {

            }

            @Override
            public ScrollLayout onSetHeaderFollower() {
                return getBaseView().onSetScrollHeader();
            }
        };
    }


}
