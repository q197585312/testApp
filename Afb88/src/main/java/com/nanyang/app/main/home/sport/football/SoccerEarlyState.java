package com.nanyang.app.main.home.sport.football;

import android.view.View;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerEarlyState extends SoccerCommonState {
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
        return AppConstant.URL_FOOTBALL_EARLY;
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
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.Early),"Early",getBaseView().getContextActivity().getString(R.string.football));
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
        if(item.getType().equals("Date")){

        }
    }
/*    private void showDateChoicePrompt() {
        BasePopupWindow popChoice = new BasePopupWindow (getBaseView().getContextActivity(), new View(getBaseView().getContextActivity()), 800, LinearLayout.LayoutParams.WRAP_CONTENT) {


            @Override
            protected void popItemCLick(MenuItemInfo item, int position) {
                closePopupWindow();
                getEarlyDayData(item);
            }

            @Override
            protected void convertItem(ViewHolder helper, MenuItemInfo item, int position) {
                helper.setText(R.id.item_text_tv, item.getTitle());

            }

            @Override
            protected int getItemLayoutRes() {
                return R.layout.item_text_base;
            }

            @Override
            protected int getListViewId() {
                return R.id.popup_choice_lv;
            }

            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice_date;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                ListView lv = (ListView) view.findViewById(R.id.popup_choice_lv);
                View header = LayoutInflater.from(getBaseView().getContextActivity()).inflate(R.layout.item_text, null);

            }
        };


        getBaseView().onPopupWindowCreated();

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
        String d6 = getString(R.string.six_day);
        MenuItemInfo item0 = new MenuItemInfo(0, getString(R.string.all), "");
        MenuItemInfo item1 = new MenuItemInfo(0, d1, d1);
        MenuItemInfo item2 = new MenuItemInfo(0, d2, d2);
        MenuItemInfo item3 = new MenuItemInfo(0, d3, d3);
        MenuItemInfo item4 = new MenuItemInfo(0, d4, d4);
        MenuItemInfo item5 = new MenuItemInfo(0, d5, d5);
        MenuItemInfo item6 = new MenuItemInfo(1, d6, dv);
        popChoice.setData(new ArrayList<MenuItemInfo>(Arrays.asList(item0, item1, item2, item3, item4, item5, item6)));
        popChoice.showPopupCenterWindow();

    }*/
}
