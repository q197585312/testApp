package com.nanyang.app.main.home.sportInterface;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class OutRightState extends SportState<SportInfo, SportContract2.View<SportInfo>> {


    public OutRightState(SportContract2.View baseView) {
        super(baseView);
    }

    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean collection() {
        return false;
    }

    @Override
    public IAdapterHelper<SportInfo> onSetAdapterHelper() {
        return new IAdapterHelper<SportInfo>() {
            @Override
            public void onConvert(MyRecyclerViewHolder holder, final int position, final SportInfo item) {
                TextView matchTitleTv = holder.getView(R.id.out_right_title_tv);
                View headV = holder.getView(R.id.v_out_right_header_space);
                TextView homeTv = holder.getView(R.id.out_right_home_tv);

                TextView markTv = holder.getView(R.id.out_right_mark_tv);
                homeTv.setText(item.getHome());
                markTv.setText(item.getX12_1Odds());

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
            }

            @Override
            public int onSetAdapterItemLayout() {
                return R.layout.sport_out_right_item;
            }

            @Override
            public List<ScrollLayout> getFollowers() {
                return null;
            }
        };
    }


    @Override
    protected List<TableSportInfo<SportInfo>> updateJsonData(JSONArray dataListArray) throws JSONException {
        ArrayList<TableSportInfo<SportInfo>> tableModules = new ArrayList<>();
        if (dataListArray.length() > 0) {
            for (int i = 0; i < dataListArray.length(); i++) {
                LeagueBean leagueBean;
                List<SportInfo> matchList = new ArrayList<>();
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

    private SportInfo parseMatch(JSONArray matchArray) throws JSONException {
        SportInfo info = new SportInfo();
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
    protected List<TableSportInfo<SportInfo>> filterData(List<TableSportInfo<SportInfo>> allData) {
            return allData;
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
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Running), "Running"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Early), "Early"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.OutRight), "OutRight"));
        return types;
    }

    @Override
    public void setHeaderContent(ScrollLayout slHeader) {
        new SoccerHeaderContent().setHeaderContent(getBaseView().getContextActivity(), slHeader);
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new SportAdapterHelper.ItemCallBack<SportInfo>() {
            @Override
            public SportInfo getItem(int position) {
                return baseRecyclerAdapter.getItem(position);
            }

            @Override
            public void clickOdds(TextView v, SportInfo item, String type, boolean isHf, String odds) {

            }
        };
    }

    @Override
    protected IBetHelper onSetBetHelper() {
        return new BallCommonBetHelper(getBaseView());
    }
}
