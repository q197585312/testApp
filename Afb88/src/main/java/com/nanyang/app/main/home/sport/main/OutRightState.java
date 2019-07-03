package com.nanyang.app.main.home.sport.main;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class OutRightState extends SportState<BallInfo, SportContract.View<BallInfo>> {

    protected BaseAllFragment fragment;

    public String getOt() {
        return ot;
    }

    protected String ot;
    protected String text;

    public OutRightState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public void setBaseView(SportContract.View mBaseView) {
        super.setBaseView(mBaseView);
        fragment = (BaseAllFragment) mBaseView;
        fragment.rvContent.setNestedScrollingEnabled(false);
        fragment.rvContent.setFocusableInTouchMode(false);
    }

    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.OutRight);
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                setStateItemOt("e");
                getBaseView().switchState(this);
                break;
            case "Today":
                setStateItemOt("t");
                getBaseView().switchState(this);
                break;
            case "Running":
                setStateItemOt("t");
                getBaseView().switchState(this);
                break;

        }
    }

    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean collection() {
        return false;
    }

    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new SportAdapterHelper<BallInfo>() {
            @Override
            public void onConvert(MyRecyclerViewHolder holder, final int position, final BallInfo item) {

                TextView matchTitleTv = holder.getView(R.id.out_right_title_tv);
                View headV = holder.getView(R.id.module_match_head_v);
                TextView homeTv = holder.getView(R.id.out_right_home_tv);
                View contentParentLl = holder.getView(R.id.ll_match_content);
                contentParentLl.setBackgroundColor(item.getContentColor());
                final TextView markTv = holder.getView(R.id.out_right_mark_tv);
                homeTv.setText(item.getHome());
                markTv.setText(item.getX12_1Odds());
                markTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        back.clickOdds(markTv, item, "1", false, item.getX12_1Odds(), Integer.valueOf(item.getSocOddsId()), "", item.getHasPar() != null && item.getHasPar().equals("1"));
                    }
                });
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

        };
    }

    public String getDbId() {
        String dbid = fragment.currentIdBean.getDbid();
        String DbId = "";
        if (dbid.equals("15")) {
            DbId = dbid + "_13";
        } else
            DbId = dbid + "_11";
        return DbId;
    }


    @Override
    protected BallInfo parseMatch(JSONArray matchArray, boolean notify) throws JSONException {

        BallInfo info = new BallInfo();
        info.setSocOddsId(matchArray.getString(0));
        info.setHome(matchArray.getString(1));
        info.setIsInetBet(matchArray.getString(2));
        info.setIsX12New(matchArray.getString(3));
        info.setHasX12(matchArray.getString(4));
        info.setX12_1Odds(matchArray.getString(5));
        info.setPreSocOddsId(matchArray.getString(6));
        info.setNotify(notify);
        return info;
    }


    @Override
    protected List<TableSportInfo<BallInfo>> filterChildData(List<TableSportInfo<BallInfo>> allData) {
        return allData;
    }


    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new SportAdapterHelper.ItemCallBack<SportInfo>() {
            @Override
            public SportInfo getItem(int position) {
                return baseRecyclerAdapter.getItem(position);
            }

            @Override
            public void clickOdds(TextView v, SportInfo item, String type, boolean isHf, String odds, int oid, String sc, boolean hasPar) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, oid, type, odds, v, isHf, sc, hasPar);
            }

            @Override
            public void clickView(View v, SportInfo item, int position) {

            }

        };
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new OutRightBetHelper(getBaseView());
    }

    @Override
    public MenuItemInfo getStateType() {
        if (StringUtils.isNull(text))
            text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today);
        return new MenuItemInfo<String>(0, text, "OutRight", getSportName());

    }

    public void setStateItemOt(String ot) {
        switch (ot) {
            case "e":
                this.text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early);
                this.ot = "e";
                break;
            default:
                this.text = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today);
                this.ot = "t";
                break;
        }

    }

    @Override
    protected List<TableSportInfo<BallInfo>> pageData(List<TableSportInfo<BallInfo>> filterData) {
        return filterData;
    }
}
