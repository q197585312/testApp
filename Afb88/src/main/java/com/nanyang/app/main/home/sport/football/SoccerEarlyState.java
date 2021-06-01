package com.nanyang.app.main.home.sport.football;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
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
    protected String getAllOddsUrl() {
        String tfDate = ((BaseToolbarActivity) getBaseView().getIBaseContext().getBaseActivity()).getApp().getUser().getTfDate();
        return AppConstant.getInstance().HOST + "_view/OddsPageSetting.aspx?ot=e&ov=0&wd=" + tfDate + "&tf=-1&isPageSingDouble=RMOdds1&m=save";
    }


    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Running":
                getBaseView().switchState(new SoccerRunningState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new SoccerTodayState(getBaseView()));
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
        return new MenuItemInfo<String>(0, (R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.football));
    }

    @Override
    protected SoccerCommonAdapterHelper onSetCommonAdapterHelper() {
        return new SoccerEarlyAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }

    @Override
    protected void bindMenuAdapter(BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter, List<MenuItemInfo> types) {
        types.add(new MenuItemInfo(R.mipmap.menu_date_white, (R.string.date), "Date"));
        super.bindMenuAdapter(baseRecyclerAdapter, types);
    }



    /*private void showDateChoicePop() {
        BasePopupWindow popChoice = new BasePopupWindow(getBaseView().getIBaseContext().getBaseActivity(), new View(getBaseView().getIBaseContext().getBaseActivity()), 800, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice_date;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView lv = (RecyclerView) view.findViewById(R.id.base_rv);
                lv.setLayoutManager(new LinearLayoutManager(getBaseView().getIBaseContext().getBaseActivity()));
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
                String d6 = getBaseView().getIBaseContext().getBaseActivity().getString(R.string.six_day);
                MenuItemInfo item0 = new MenuItemInfo(2, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.all), "");
                MenuItemInfo item1 = new MenuItemInfo(0, d1, d1);
                MenuItemInfo item2 = new MenuItemInfo(0, d2, d2);
                MenuItemInfo item3 = new MenuItemInfo(0, d3, d3);
                MenuItemInfo item4 = new MenuItemInfo(0, d4, d4);
                MenuItemInfo item5 = new MenuItemInfo(0, d5, d5);
                MenuItemInfo item6 = new MenuItemInfo(1, d6, dv);
                return new BaseRecyclerAdapter<MenuItemInfo>(getBaseView().getIBaseContext().getBaseActivity(), new ArrayList<>(Arrays.asList(item0, item1, item2, item3, item4, item5, item6)), R.layout.text_base_item) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView view1 = holder.getView(R.id.item_text_tv);
                        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view1.getLayoutParams();
                        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        layoutParams.bottomMargin = 10;
                        view1.setBackgroundResource(R.drawable.rectangle_button_green);
                        view1.setText(item.getText());
                        view1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                };
            }
        };


        getBaseView().onPopupWindowCreated(popChoice, Gravity.CENTER);

    }*/

    private void filterDateData(MenuItemInfo item) {
//        this.selectedDateInfo=item;
        refresh();

    }
}
