package com.nanyang.app.main.home.sport.main;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class OutRightState extends SportState<SportInfo, SportContract.View<SportInfo>> {


    public OutRightState(SportContract.View baseView) {
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
    public SportAdapterHelper<SportInfo> onSetAdapterHelper() {
        return new SportAdapterHelper<SportInfo>() {
            @Override
            public void onConvert(MyRecyclerViewHolder holder, final int position, final SportInfo item) {
                TextView matchTitleTv = holder.getView(R.id.out_right_title_tv);
                View headV = holder.getView(R.id.v_out_right_header_space);
                TextView homeTv = holder.getView(R.id.out_right_home_tv);

                final TextView markTv = holder.getView(R.id.out_right_mark_tv);
                homeTv.setText(item.getHome());
                markTv.setText(item.getX12_1Odds());
                markTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        back.clickOdds(markTv, item, "1", false, item.getX12_1Odds(),Integer.valueOf(item.getSocOddsId()),"");
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

    @Override
    protected SportInfo parseMatch(JSONArray matchArray,boolean notify) throws JSONException {
        SportInfo info = new SportInfo();
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
    protected List<TableSportInfo<SportInfo>> filterChildData(List<TableSportInfo<SportInfo>> allData) {
        return allData;
    }

    @Override
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running), "Running"));
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early"));
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.OutRight), "OutRight"));
        return types;
    }


    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new SportAdapterHelper.ItemCallBack<SportInfo>() {
            @Override
            public SportInfo getItem(int position) {
                return baseRecyclerAdapter.getItem(position);
            }

            @Override
            public void clickOdds(TextView v, SportInfo item, String type, boolean isHf, String odds,int oid,String sc) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, type, odds, v, isHf, sc);
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


}
