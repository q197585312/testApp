package com.nanyang.app.main.center.result;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ResultPresenter extends BaseRetrofitPresenter<String, ResultContact.View> implements ResultContact.Presenter {
    private List<MenuItemInfo> sortList;
    private List<MenuItemInfo> games;
    private List<MenuItemInfo> gameDateList;
    private List<MenuItemInfo> marketList;
    private BasePopupWindow sortPop;
    private BasePopupWindow marketPop;
    private BasePopupWindow gameDatePop;
    private BasePopupWindow gameTypePop;

    public ResultPresenter(ResultContact.View view) {
        super(view);
    }

    @Override
    public void getResultData() {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getData(AppConstant.getInstance().URL_RESULT))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String stakeListBeen) throws Exception {
                        baseView.onGetData(stakeListBeen);
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(d);
    }


    Map<String, String> selectedMap = new HashMap<>();

    public void initSort(TextView textView) {
        sortList = new ArrayList<>();
        MenuItemInfo item1 = new MenuItemInfo(0, "热门排序", "0");
        MenuItemInfo item2 = new MenuItemInfo(1, "时间排序", "1");
        selectedMap.put("sortBy", item1.getType());
        sortList.add(item1);
        sortList.add(item2);
        sortPop = choicePop("sortBy", sortList, textView);
    }

    public void initMarket(TextView textView) {
        marketList = new ArrayList<>();
        MenuItemInfo item1 = new MenuItemInfo(0, "全部成绩", "0");
        MenuItemInfo item2 = new MenuItemInfo(0, "主要成绩", "1");
        MenuItemInfo item3 = new MenuItemInfo(0, "更多玩法成绩", "2");
        selectedMap.put("marketType", item1.getType());
        marketList.add(item1);
        marketList.add(item2);
        marketList.add(item3);
        marketPop = choicePop("marketType", marketList, textView);
    }

    public void initGameDate(TextView textView) {
        gameDateList = new ArrayList<>();
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
        String d6 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 6), "yyyy-MM-dd");
        String d7 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 7), "yyyy-MM-dd");
        String d8 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 8), "yyyy-MM-dd");

        MenuItemInfo item1 = new MenuItemInfo(0, d1, d1);
        MenuItemInfo item2 = new MenuItemInfo(0, d2, d2);
        MenuItemInfo item3 = new MenuItemInfo(0, d3, d3);
        MenuItemInfo item4 = new MenuItemInfo(0, d4, d4);
        MenuItemInfo item5 = new MenuItemInfo(0, d5, d5);
        MenuItemInfo item6 = new MenuItemInfo(0, d6, d6);
        MenuItemInfo item7 = new MenuItemInfo(0, d7, d7);
        MenuItemInfo item8 = new MenuItemInfo(0, d8, d8);
        selectedMap.put("gameDate", item1.getType());
        gameDateList.add(item1);
        gameDateList.add(item2);
        gameDateList.add(item3);
        gameDateList.add(item4);
        gameDateList.add(item5);
        gameDateList.add(item6);
        gameDateList.add(item7);
        gameDateList.add(item8);
        gameDatePop = choicePop("gameDate", gameDateList, textView);

    }

    public void initGameTypes(TextView textView) {
        games = new ArrayList<>();
        games.add(new MenuItemInfo(0, "足球", "S,S,p1,g1"));
        games.add(new MenuItemInfo(0, "足球 - 優勝冠軍", "S,SO,p3,g3"));
        games.add(new MenuItemInfo(0, "3D, 4D 大/小 &amp; 单/双", "S,M,p1,g1"));
        games.add(new MenuItemInfo(0, "1D", "1,S,p2,g2"));
        games.add(new MenuItemInfo(0, "2D", "2,S,p2,g2"));
        games.add(new MenuItemInfo(0, "篮球", "B,B,p1,g1"));
        games.add(new MenuItemInfo(0, "篮球 - 優勝冠軍", "S,BO,p3,g3"));
        games.add(new MenuItemInfo(0, "奥运", "B,OL,p1,g1"));
        games.add(new MenuItemInfo(0, "奥运 - 優勝冠軍", "S,OLO,p3,g3"));
        games.add(new MenuItemInfo(0, "美式足球", "B,N,p1,g1"));
        games.add(new MenuItemInfo(0, "美式足球 - 優勝冠軍", "S,NO,p3,g3"));
        games.add(new MenuItemInfo(0, "棒球", "S,BB,p1,g1"));
        games.add(new MenuItemInfo(0, "棒球 - 優勝冠軍", "S,BBO,p3,g3"));
        games.add(new MenuItemInfo(0, "冰球", "S,H,p1,g1"));
        games.add(new MenuItemInfo(0, "冰球 - 優勝冠軍", "S,HO,p3,g3"));
        games.add(new MenuItemInfo(0, "撞球/桌球", "S,K,p2,g2"));
        games.add(new MenuItemInfo(0, "撞球/桌球 - 優勝冠軍", "S,KO,p3,g3"));
        games.add(new MenuItemInfo(0, "橄榄球", "B,R,p1,g1"));
        games.add(new MenuItemInfo(0, "橄榄球 - 優勝冠軍", "S,RO,p3,g3"));
        games.add(new MenuItemInfo(0, "网球", "B,T,p2,g2"));
        games.add(new MenuItemInfo(0, "网球 - 優勝冠軍", "B,TO,p3,g3"));
        games.add(new MenuItemInfo(0, "投镖", "S,D,p2,g2"));
        games.add(new MenuItemInfo(0, "投镖 - 優勝冠軍", "S,DO,p3,g3"));
        games.add(new MenuItemInfo(0, "拳击", "S,X,p4,g4"));
        games.add(new MenuItemInfo(0, "拳击 - 優勝冠軍", "S,XO,p3,g3"));
        games.add(new MenuItemInfo(0, "一级方程式", "S,F1H,p2,g2"));
        games.add(new MenuItemInfo(0, "汽车运动", "S,MSH,p2,g2"));
        games.add(new MenuItemInfo(0, "汽车运动 - 優勝冠軍", "S,MSO,p2,g2"));
        games.add(new MenuItemInfo(0, "高尔夫球", "S,G,p2,g2"));
        games.add(new MenuItemInfo(0, "高尔夫球 - 優勝冠軍", "S,GO,p3,g3"));
        games.add(new MenuItemInfo(0, "室内足球", "S,FS,p1,g1"));
        games.add(new MenuItemInfo(0, "室内足球 - 優勝冠軍", "S,FSO,p3,g3"));
        games.add(new MenuItemInfo(0, "羽毛球", "B,BD,p2,g2"));
        games.add(new MenuItemInfo(0, "羽毛球 - 優勝冠軍", "B,BDO,p3,g3"));
        games.add(new MenuItemInfo(0, "水球", "B,WP,p1,g1"));
        games.add(new MenuItemInfo(0, "水球 - 優勝冠軍", "B,WPO,p3,g3"));
        games.add(new MenuItemInfo(0, "乒乓球", "B,TT,p2,g2"));
        games.add(new MenuItemInfo(0, "乒乓球 - 優勝冠軍", "B,TTO,p3,g3"));
        games.add(new MenuItemInfo(0, "板球", "S,CK,p1,g1"));
        games.add(new MenuItemInfo(0, "板球 - 優勝冠軍", "S,CKO,p3,g3"));
        games.add(new MenuItemInfo(0, "排球", "S,V,p2,g2"));
        games.add(new MenuItemInfo(0, "排球 - 優勝冠軍", "S,VO,p3,g3"));
        games.add(new MenuItemInfo(0, "手球", "B,HB,p1,g1"));
        games.add(new MenuItemInfo(0, "手球 - 優勝冠軍", "B,HBO,p3,g3"));
        games.add(new MenuItemInfo(0, "沙滩足球", "B,BS,p1,g1"));
        games.add(new MenuItemInfo(0, "沙滩足球 - 優勝冠軍", "B,BSO,p3,g3"));
        games.add(new MenuItemInfo(0, "股市", "S,F,p2,g2"));
        games.add(new MenuItemInfo(0, "滑雪运动", "S,WS,p1,g1"));
        games.add(new MenuItemInfo(0, "滑雪运动 - 優勝冠軍", "S,WSO,p3,g3"));
        games.add(new MenuItemInfo(0, "自行车", "S,CYH,p2,g2"));
        games.add(new MenuItemInfo(0, "自行车 - 優勝冠軍", "S,CYO,p3,g3"));
        games.add(new MenuItemInfo(0, "壁球", "B,SQ,p2,g2"));
        games.add(new MenuItemInfo(0, "泰式千字", "S,TH,p1,g1"));
        games.add(new MenuItemInfo(0, "竞技", "B,AT,p2,g2"));
        games.add(new MenuItemInfo(0, "竞技 - 優勝冠軍", "B,ATO,p3,g3"));
        games.add(new MenuItemInfo(0, "电子竞技", "S,ES,p1,g1"));
        games.add(new MenuItemInfo(0, "电子竞技-優勝冠軍", "S,ESO,p3,g3"));
        games.add(new MenuItemInfo(0, "泰拳", "S,MT,p4,g4"));
        selectedMap.put("GameType", "S,S,p1,g1");
        gameTypePop = choicePop("GameType", games, textView);
    }

    private BasePopupWindow choicePop(final String parent, final List<MenuItemInfo> lists, final TextView viewTv) {
        final BasePopupWindow popChoice = new BasePopupWindow(baseView.getContextActivity(), viewTv, viewTv.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.layout_base_recycler_view;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView lv = (RecyclerView) view.findViewById(R.id.base_rv);
                lv.setLayoutManager(new LinearLayoutManager(baseView.getContextActivity()));
                BaseRecyclerAdapter<MenuItemInfo> adapter = new BaseRecyclerAdapter<MenuItemInfo>(baseView.getContextActivity(), lists, R.layout.text_base) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView view1 = holder.getView(R.id.item_text_tv);
                        view1.setText(item.getText());
                        view1.setTextColor(baseView.getContextActivity().getResources().getColor(R.color.green900));
                    }
                };
                adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {
                        closePopupWindow();
                        if (!selectedMap.get(parent).equals(item.getType())) {
                            selectedMap.put(parent, item.getType());
                            viewTv.setText(item.getText());
                            submit(selectedMap);
                        }
                    }
                });
                lv.setAdapter(adapter);
            }

        };


        return popChoice;

    }

    private void submit(Map<String, String> selectedMap) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_RESULT, selectedMap))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String stakeListBeen) throws Exception {
                        baseView.onGetData(stakeListBeen);
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(d);
    }

    /*GameType
gameDate
 sortBy
marketType*/
    public void selectedType(String parent, View v) {
        switch (parent) {
            case "GameType":
                baseView.onPopupWindowCreated(gameTypePop, -2);
                break;
            case "gameDate":
                baseView.onPopupWindowCreated(gameDatePop, -2);
                break;
            case "sortBy":
                baseView.onPopupWindowCreated(sortPop, -2);
                break;
            case "marketType":
                baseView.onPopupWindowCreated(marketPop, -2);
                break;
        }


    }
}
