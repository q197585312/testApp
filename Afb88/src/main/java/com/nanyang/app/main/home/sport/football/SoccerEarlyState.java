package com.nanyang.app.main.home.sport.football;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerEarlyState extends SoccerCommonState {
    private MenuItemInfo selectedDateInfo= new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.all), "");;

    public SoccerEarlyState(SportContract.View baseView) {
        super(baseView);

    }


    @Override
    public boolean mix() {
        getBaseView().switchState(new SoccerEarlyMixState(getBaseView()));
        return true;
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_FOOTBALL_EARLY+"&"+selectedDateInfo.getRes()+"&wd="+selectedDateInfo.getType();
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Running":
                getBaseView().switchState(new SoccerRunningState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new SoccerRunningState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new SoccerOutRightState(getBaseView()));
                break;
        }
    }


    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Early), "Early", getBaseView().getContextActivity().getString(R.string.football));
    }

    @Override
    protected SoccerCommonAdapterHelper onSetCommonAdapterHelper() {
        return new SoccerEarlyAdapterHelper(getBaseView().getContextActivity());
    }

    @Override
    protected void bindMenuAdapter(BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter, List<MenuItemInfo> types) {
        types.add(new MenuItemInfo(R.mipmap.menu_date_white, getBaseView().getContextActivity().getString(R.string.date), "Date"));
        super.bindMenuAdapter(baseRecyclerAdapter, types);
    }

    @Override
    protected void popMenuItemClick(View view, MenuItemInfo item) {
        super.popMenuItemClick(view, item);
        if (item.getType().equals("Date")) {
            showDateChoicePop();
        }
    }

    /*@Override
    protected List<TableSportInfo<SoccerCommonInfo>> filterChildData(List<TableSportInfo<SoccerCommonInfo>> allData) {
        List<TableSportInfo<SoccerCommonInfo>> tableSportInfos = super.filterChildData(allData);
        List<TableSportInfo<SoccerCommonInfo>> tableDateInfos =new ArrayList<>();
        if (selectedDateInfo != null) {//挑选日期
            if(selectedDateInfo.getRes()==2){//all
                return tableSportInfos;
            }
            Date date = TimeUtils.format2Date(selectedDateInfo.getText(), "yyyy-MM-dd");
            for (TableSportInfo<SoccerCommonInfo> item : tableSportInfos) {
                List<SoccerCommonInfo> mb=new ArrayList<>();
                for (SoccerCommonInfo bean : item.getRows()) {
                    Date dateMy = TimeUtils.format2Date(bean.getWorkingDate(), "MM/dd/yyyy");
                    if(dateMy==null)
                        continue;
                    if (selectedDateInfo.getRes() == 1) {
                        if( dateMy.getTime()-date.getTime()>0){
                            mb.add(bean);
                        }
                    }else if(selectedDateInfo.getRes() == 0){
                        if( dateMy.getTime()==date.getTime()){
                            mb.add(bean);
                        }
                    }
                }
                if(mb.size()>0){
                    tableDateInfos.add(new TableSportInfo<SoccerCommonInfo>(item.getLeagueBean(),mb));
                }
            }
        }
        return tableDateInfos;
    }*/

    private void showDateChoicePop() {
        BasePopupWindow popChoice = new BasePopupWindow(getBaseView().getContextActivity(), new View(getBaseView().getContextActivity()), 800, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice_date;
            }
            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView lv = (RecyclerView) view.findViewById(R.id.base_rv);
                lv.setLayoutManager(new LinearLayoutManager(getBaseView().getContextActivity()));
                BaseRecyclerAdapter<MenuItemInfo> adapter = getAdapter();
                adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {
                        closePopupWindow();
                        filterDateData(item);
                    }
                });
                lv.setAdapter(adapter);
            }
            @NonNull
            private BaseRecyclerAdapter<MenuItemInfo> getAdapter() {
                String h12 = TimeUtils.dateFormat(new Date(), "yyyy-MM-dd") + " 12:00:00";
                String now = TimeUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
                long dif = TimeUtils.diffTime(now, h12, "yyyy-MM-dd HH:mm:ss");
                Date firstDate = new Date();
                if (dif < 0)
                    firstDate = TimeUtils.getAddDayDate(firstDate, -1);
                String d1 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 1), "yyyy-MM-dd");
                String d2 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 2), "yyyy-MM-dd");
                String d3 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 3), "yyyy-MM-dd");
                String d4 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 4), "yyyy-MM-dd");
                String d5 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 5), "yyyy-MM-dd");
                String dv = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 6), "yyyy-MM-dd");
                String d6 = getBaseView().getContextActivity().getString(R.string.six_day);
                MenuItemInfo item0 = new MenuItemInfo(2, getBaseView().getContextActivity().getString(R.string.all), "");
                MenuItemInfo item1 = new MenuItemInfo(0, d1, d1);
                MenuItemInfo item2 = new MenuItemInfo(0, d2, d2);
                MenuItemInfo item3 = new MenuItemInfo(0, d3, d3);
                MenuItemInfo item4 = new MenuItemInfo(0, d4, d4);
                MenuItemInfo item5 = new MenuItemInfo(0, d5, d5);
                MenuItemInfo item6 = new MenuItemInfo(1, d6, dv);
                return new BaseRecyclerAdapter<MenuItemInfo>(getBaseView().getContextActivity(),new ArrayList<>(Arrays.asList(item0, item1, item2, item3, item4, item5, item6)), R.layout.text_base_item) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView view1 = holder.getView(R.id.item_text_tv);
                        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view1.getLayoutParams();
                        layoutParams.height=LinearLayout.LayoutParams.WRAP_CONTENT;
                        layoutParams.bottomMargin=10;
                        view1.setBackgroundResource(R.drawable.rectangle_button_green);
                        view1.setText(item.getText());
                        view1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                };
            }
        };


        getBaseView().onPopupWindowCreated(popChoice, Gravity.CENTER);

    }

    private void filterDateData(MenuItemInfo item) {
        //http://main55.afb88.com/_View/RMOddsGen1.ashx?ot=e&ov=0&mt=0&tf=2&TFStatus=0&update=false&r=995449827&wd=2017-04-06&ia=0&LID=&_=1491446332397
        //http://main55.afb88.com/_View/RMOddsGen1.ashx?ot=e&ov=0&mt=0&tf=2&TFStatus=0&update=false&r=1524118851&wd=2017-04-11&ia=1&LID=&_=1491446733213
        this.selectedDateInfo=item;
        refresh();

    }
}
