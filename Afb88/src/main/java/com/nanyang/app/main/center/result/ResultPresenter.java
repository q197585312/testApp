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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ResultPresenter extends BaseRetrofitPresenter<ResultOrderFragment> implements ResultContact.Presenter {
    private List<MenuItemInfo> sortList;
    private List<MenuItemInfo> games;
    private List<MenuItemInfo> gameDateList;
    private List<MenuItemInfo> marketList;
    private BasePopupWindow sortPop;
    private BasePopupWindow marketPop;
    private BasePopupWindow gameDatePop;
    private BasePopupWindow gameTypePop;
    private HashMap<String, List<ResultInfo>> moduleMap;

    public ResultPresenter(ResultOrderFragment view) {
        super(view);
        initGameDate();
        initGameTypes();
        initMarket();
        initSort();
    }

    @Override
    public void getResultData() {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getData(AppConstant.getInstance().URL_RESULT))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String stakeListBeen) throws Exception {
                        baseContext.onGetData(stakeListBeen);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseContext.showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(d);
    }


    Map<String, String> selectedMap = new HashMap<>();

    public void initSort() {
        sortList = new ArrayList<>();
        MenuItemInfo item1 = new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.hot_sort), "0");
        MenuItemInfo item2 = new MenuItemInfo(1, baseContext.getBaseActivity().getString(R.string.sort_by_time), "1");
        selectedMap.put("sortBy", item1.getType());
        sortList.add(item1);
        sortList.add(item2);
    }

    public void initMarket() {
        marketList = new ArrayList<>();
        MenuItemInfo item1 = new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.All_Markets), "0");
        MenuItemInfo item2 = new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Main_Markets), "1");
        MenuItemInfo item3 = new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Other_Bet_Markets), "2");
        selectedMap.put("marketType", item1.getType());
        marketList.add(item1);
        marketList.add(item2);
        marketList.add(item3);

    }

    public void initGameDate() {
        gameDateList = new ArrayList<>();
        Date firstDate = new Date();

        String d0 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 0), "yyyy-MM-dd");
        String d1 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, -1), "yyyy-MM-dd");
        String d2 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, -2), "yyyy-MM-dd");
        String d3 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, -3), "yyyy-MM-dd");
        String d4 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, -4), "yyyy-MM-dd");
        String d5 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, -5), "yyyy-MM-dd");
        String d6 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, -6), "yyyy-MM-dd");
        String d7 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, -7), "yyyy-MM-dd");

        MenuItemInfo item0 = new MenuItemInfo(0, d0, d0);
        MenuItemInfo item1 = new MenuItemInfo(0, d1, d1);
        MenuItemInfo item2 = new MenuItemInfo(0, d2, d2);
        MenuItemInfo item3 = new MenuItemInfo(0, d3, d3);
        MenuItemInfo item4 = new MenuItemInfo(0, d4, d4);
        MenuItemInfo item5 = new MenuItemInfo(0, d5, d5);
        MenuItemInfo item6 = new MenuItemInfo(0, d6, d6);
        MenuItemInfo item7 = new MenuItemInfo(0, d7, d7);

        selectedMap.put("gameDate", item0.getType());
        gameDateList.add(item0);
        gameDateList.add(item1);
        gameDateList.add(item2);
        gameDateList.add(item3);
        gameDateList.add(item4);
        gameDateList.add(item5);
        gameDateList.add(item6);
        gameDateList.add(item7);



    }

    public void initGameTypes() {
        games = new ArrayList<>();
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Soccer), "S,S,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Soccer)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,SO,p3,g3"));
        games.add(new MenuItemInfo(0, "3D, 4D "+ baseContext.getBaseActivity().getString(R.string.OVER_UNDER)+" & "+ baseContext.getBaseActivity().getString(R.string.ODD_EVEN), "S,M,p1,g1"));
        games.add(new MenuItemInfo(0, "1D", "1,S,p2,g2"));
        games.add(new MenuItemInfo(0, "2D", "2,S,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Basketball), "B,B,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Basketball)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,BO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.OLYMPIC), "B,OL,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.OLYMPIC)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,OLO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.US_Football)+"", "B,N,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.US_Football)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,NO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Baseball)+"", "S,BB,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Baseball)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,BBO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.IceHockey)+"", "S,H,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.IceHockey)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,HO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Pool)+"", "S,K,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Pool)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,KO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Rugby)+"", "B,R,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Rugby)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,RO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Tennis)+"", "B,T,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Tennis)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "B,TO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Darts)+"", "S,D,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Darts)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,DO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Boxing)+"", "S,X,p4,g4"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Boxing)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,XO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Formula1), "S,F1H,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Motor_Sports), "S,MSH,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Motor_Sports)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,MSO,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Golf)+"", "S,G,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Golf)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,GO,p3,g3"));
        games.add(new MenuItemInfo(0,  baseContext.getBaseActivity().getString(R.string.Futsal)+"", "S,FS,p1,g1"));
        games.add(new MenuItemInfo(0,  baseContext.getBaseActivity().getString(R.string.Futsal)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,FSO,p3,g3"));
        games.add(new MenuItemInfo(0,  baseContext.getBaseActivity().getString(R.string.Badminton)+"", "B,BD,p2,g2"));
        games.add(new MenuItemInfo(0,  baseContext.getBaseActivity().getString(R.string.Badminton)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "B,BDO,p3,g3"));
        games.add(new MenuItemInfo(0,  baseContext.getBaseActivity().getString(R.string.Water_Polo)+"", "B,WP,p1,g1"));
        games.add(new MenuItemInfo(0,  baseContext.getBaseActivity().getString(R.string.Water_Polo)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "B,WPO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Table_Tennis), "B,TT,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Table_Tennis)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "B,TTO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Cricket)+"", "S,CK,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Cricket)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,CKO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Volleyball)+"", "S,V,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Volleyball)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,VO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Handball)+"", "B,HB,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Handball)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "B,HBO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Beach_Soccer)+"", "B,BS,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Beach_Soccer)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "B,BSO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Financial)+"", "S,F,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.WinterSport)+"", "S,WS,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.WinterSport)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,WSO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Cycling)+"", "S,CYH,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Cycling)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,CYO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Squash)+"", "B,SQ,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Huay_Thai)+"", "S,TH,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Athletics)+"", "B,AT,p2,g2"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Athletics)+" - "+ baseContext.getBaseActivity().getString(R.string.OutRight), "B,ATO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.E_Sport)+"", "S,ES,p1,g1"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.E_Sport)+"-"+ baseContext.getBaseActivity().getString(R.string.OutRight), "S,ESO,p3,g3"));
        games.add(new MenuItemInfo(0, baseContext.getBaseActivity().getString(R.string.Muay_Thai)+"", "S,MT,p4,g4"));
        selectedMap.put("GameType", "S,S,p1,g1");

    }

    private BasePopupWindow choicePop(final String parent, final List<MenuItemInfo> lists, final TextView viewTv) {
        final BasePopupWindow popChoice = new BasePopupWindow(baseContext.getBaseActivity(), viewTv, viewTv.getMeasuredWidth(), LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.layout_base_recycler_view;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView lv = (RecyclerView) view.findViewById(R.id.base_rv);
                lv.setLayoutManager(new LinearLayoutManager(baseContext.getBaseActivity()));
                BaseRecyclerAdapter<MenuItemInfo> adapter = new BaseRecyclerAdapter<MenuItemInfo>(baseContext.getBaseActivity(), lists, R.layout.text_base) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView view1 = holder.getView(R.id.item_text_tv);
                        view1.setText(item.getText());
                        view1.setTextColor(baseContext.getBaseActivity().getResources().getColor(R.color.green900));
                        view1.setBackgroundResource(R.color.white);
                        view1.setTextSize(10);
                        ((RecyclerView.LayoutParams)view1.getLayoutParams()).setMargins(0,0,0,0);
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
                        baseContext.onGetData(stakeListBeen);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseContext.showLoadingDialog();
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
                gameTypePop = choicePop("GameType", games, (TextView) v);
                baseContext.onPopupWindowCreated(gameTypePop, -2);
                break;
            case "gameDate":
                gameDatePop = choicePop("gameDate", gameDateList, (TextView) v);
                baseContext.onPopupWindowCreated(gameDatePop, -2);
                break;
            case "sortBy":
                sortPop = choicePop("sortBy", sortList, (TextView) v);
                baseContext.onPopupWindowCreated(sortPop, -2);
                break;
            case "marketType":
                marketPop = choicePop("marketType", marketList, (TextView) v);
                baseContext.onPopupWindowCreated(marketPop, -2);
                break;
            case "ModuleTitle":
                Iterator<Map.Entry<String, List<ResultInfo>>> iterator = moduleMap.entrySet().iterator();
                final List<String> modules=new ArrayList<>();
                while (iterator.hasNext()){
                    modules.add(iterator.next().getKey());
                }

                BasePopupWindow popChoice = new BasePopupWindow(baseContext.getBaseActivity(),  v, v.getMeasuredWidth(), LinearLayout.LayoutParams.WRAP_CONTENT) {
                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.layout_base_recycler_view;
                    }

                    @Override
                    protected void initView(View view) {
                        super.initView(view);
                        RecyclerView lv = (RecyclerView) view.findViewById(R.id.base_rv);
                        lv.setLayoutManager(new LinearLayoutManager(baseContext.getBaseActivity()));
                        BaseRecyclerAdapter<String> adapter = new BaseRecyclerAdapter<String>(baseContext.getBaseActivity(), modules, R.layout.text_base) {
                            @Override
                            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                                TextView view1 = holder.getView(R.id.item_text_tv);
                                view1.setText(item);
                                view1.setTextColor(baseContext.getBaseActivity().getResources().getColor(R.color.green900));
                                view1.setBackgroundResource(R.color.white);
                                view1.setTextSize(10);
                                ((RecyclerView.LayoutParams) view1.getLayoutParams()).setMargins(0, 0, 0, 0);

                            }
                        };
                        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
                            @Override
                            public void onItemClick(View view, String item, int position) {
                                closePopupWindow();
                                List<ResultInfo> resultInfos = moduleMap.get(item);
                                baseContext.onModuleList(item,resultInfos);
                                ((TextView)v).setText(item);
                            }
                        });
                        lv.setAdapter(adapter);
                    }
                };
                baseContext.onPopupWindowCreated(popChoice, -2);
                break;
        }
    }

    public void listLeague(List<ResultInfo> list) {
        moduleMap=new LinkedHashMap<>();
        moduleMap.put(baseContext.getBaseActivity().getString(R.string.all),list);
        for (ResultInfo resultInfo : list) {
            String moduleTitle = resultInfo.getModuleTitle();
            List<ResultInfo> resultInfos = moduleMap.get(moduleTitle);
            if(resultInfos==null){
                resultInfos=new ArrayList<>();
                moduleMap.put(moduleTitle,resultInfos);
            }
            resultInfos.add(resultInfo);

        }

    }

}
