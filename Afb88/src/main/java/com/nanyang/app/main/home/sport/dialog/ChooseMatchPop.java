package com.nanyang.app.main.home.sport.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


public class ChooseMatchPop<B extends SportInfo, T extends TableSportInfo<B>> extends BasePopupWindow {
    private Map<String, Boolean> leagueSelectedMap;
    private BaseRecyclerAdapter<T> contentAdapter;
    private BaseRecyclerAdapter<MenuItemInfo> bottomAdapter;

    public void setBack(CallBack back) {
        this.back = back;
    }

    private CallBack back;

    public List<T> gettList() {
        return tList;
    }

    public void setList(List<T> list, Map<String, Boolean> leagueSelectedMap) {
        List<T> l = new ArrayList<>(list);
        Comparator<T> comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.getLeagueBean().getModuleTitle().compareToIgnoreCase(o2.getLeagueBean().getModuleTitle());
            }
        };
        Collections.sort(l, comparator);
        this.tList = l;
        this.leagueSelectedMap = leagueSelectedMap;
        contentAdapter.addAllAndClear(tList);
    }

    List<T> tList;
    /*
        @Bind(R.id.tv_toolbar_title)
        TextView tvToolbarTitle;
        @Bind(R.id.tv_toolbar_right)
        TextView tvToolbarRight;
        @Bind(R.id.toolbar)
        Toolbar toolbar;*/
    @Bind(R.id.base_rv)
    RecyclerView baseRv;
    @Bind(R.id.rv_detail_top)
    RecyclerView rvDetailTop;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;

    public ChooseMatchPop(Context context, View v) {
        super(context, v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.popupwindow_choose_match;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        baseRv.setLayoutManager(new LinearLayoutManager(context));
        contentAdapter = contentAdapter();
        contentAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<T>() {
            @Override
            public void onItemClick(View view, T item, int position) {
                leagueSelectedMap.put(item.getLeagueBean().getModuleId(), !leagueSelectedMap.get(item.getLeagueBean().getModuleId()));
                contentAdapter.notifyDataSetChanged();
            }
        });
        baseRv.setAdapter(contentAdapter);
        bv rvDetailTop.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        bottomAdapter = bottomAdapter();
        rvDetailTop.setAdapter(bottomAdapter);
        bottomAdapter.addAllAndClear(Arrays.asList(new MenuItemInfo(R.mipmap.icselectcompetitions, context.getString(R.string.selected_all), "all"),
                new MenuItemInfo(R.mipmap.icselectoddstype, context.getString(R.string.reverse_selection), "reverse")
        ));
        bottomAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                if (item.getType().equals("all")) {
                    leagueSelectedMap.clear();
                } else if (item.getType().equals("reverse")) {
                    for (TableSportInfo t : tList) {
                        if (leagueSelectedMap.get(t.getLeagueBean().getModuleId()) == null) {
                            leagueSelectedMap.put(t.getLeagueBean().getModuleId(), false);
                        } else {
                            leagueSelectedMap.put(t.getLeagueBean().getModuleId(), !leagueSelectedMap.get(t.getLeagueBean().getModuleId()));
                        }
                    }
                }
                contentAdapter.notifyDataSetChanged();
            }
        });
    }

    @NonNull
    private BaseRecyclerAdapter<T> contentAdapter() {
        return new BaseRecyclerAdapter<T>(context, new ArrayList<T>(), R.layout.selected_text_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, T item) {
                if (leagueSelectedMap.get(item.getLeagueBean().getModuleId()) == null) {
                    leagueSelectedMap.put(item.getLeagueBean().getModuleId(), true);
                }
                TextView txt = holder.getView(R.id.selectable_text_content_tv);
                if (leagueSelectedMap.get(item.getLeagueBean().getModuleId())) {

                    txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.menu_right_hover, 0);
                    txt.setBackgroundResource(R.color.grey_default_bg);
                } else {
                    txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    txt.setBackgroundResource(R.color.white);
                }
                txt.setText(item.getLeagueBean().getModuleTitle());

            }
        };
    }

    @NonNull
    private BaseRecyclerAdapter<MenuItemInfo> bottomAdapter() {
        return new BaseRecyclerAdapter<MenuItemInfo>(context, new ArrayList<MenuItemInfo>(), R.layout.text_base_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView view = holder.getView(R.id.item_text_tv);
                view.setBackgroundResource(R.color.transparent);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                view.setText(item.getText());
                view.setTextColor(context.getResources().getColor(R.color.black_grey));
                view.setCompoundDrawablesWithIntrinsicBounds(0, item.getRes(), 0, 0);
            }
        };
    }

    @OnClick(R.id.tv_submit)
    public void onClick() {
        if (back != null) {
            back.chooseMap(leagueSelectedMap);
        }
        closePopupWindow();
    }

    public interface CallBack {

        void chooseMap(Map<String, Boolean> map);
    }
}
