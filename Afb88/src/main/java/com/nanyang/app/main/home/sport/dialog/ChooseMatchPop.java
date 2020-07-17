package com.nanyang.app.main.home.sport.dialog;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class ChooseMatchPop<B extends SportInfo, T extends TableSportInfo<B>> extends BasePopupWindow {
    private String type;
    private Map<String, Boolean> leagueSelectedMap;
    private BaseRecyclerAdapter<T> contentAdapter;
    private HashMap<String, String> numMap;


    public void setBack(CallBack back) {
        this.back = back;
    }

    private CallBack back;

    public List<T> gettList() {
        return tList;
    }

    public void setList(List<T> list, Map<String, Boolean> leagueSelectedMap, HashMap<String, String> numMap) {
 /*       List<T> l = new ArrayList<>(list);
        Comparator<T> comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.getLeagueBean().getModuleTitle().compareToIgnoreCase(o2.getLeagueBean().getModuleTitle());
            }
        };
        Collections.sort(l, comparator);*/
        this.tList = list;
        this.numMap = numMap;
        this.leagueSelectedMap = leagueSelectedMap;
        contentAdapter.addAllAndClear(tList);

    }


    List<T> tList;
    /*
        @BindView(R.id.tv_toolbar_title)
        TextView tvToolbarTitle;
        @BindView(R.id.tv_toolbar_right)
        TextView tvToolbarRight;
        @BindView(R.id.toolbar)
        Toolbar toolbar;*/
    @BindView(R.id.base_rv)
    RecyclerView baseRv;

    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    public ChooseMatchPop(Context context, View v, String type) {
        super(context, v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        this.type = type;
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

    }

    @NonNull
    private BaseRecyclerAdapter<T> contentAdapter() {
        return new BaseRecyclerAdapter<T>(context, new ArrayList<T>(), R.layout.selected_text_item_choose_leagua) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, T item) {
                if (leagueSelectedMap.get(item.getLeagueBean().getModuleId()) == null) {
                    leagueSelectedMap.put(item.getLeagueBean().getModuleId(), true);
                }
                TextView txt = holder.getView(R.id.selectable_text_content_tv);
                if (leagueSelectedMap.get(item.getLeagueBean().getModuleId())) {

                    txt.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.checkbox_pressed, 0, 0, 0);

                } else {
                    txt.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.checkbox_normal, 0, 0, 0);
                }
                if (numMap.get(item.getLeagueBean().getModuleId()) != null) {
                    holder.getTextView(R.id.selectable_num_tv).setText(numMap.get(item.getLeagueBean().getModuleId()));
                } else {
                    holder.getTextView(R.id.selectable_num_tv).setText(item.getRows().size()+"");
                }
                txt.setText(item.getLeagueBean().getModuleTitle());
                if (type != null && type.toLowerCase().startsWith("r")) {
                    holder.getView(R.id.selectable_text_parent_ll).setBackgroundResource(R.color.green1);
                } else {
                    holder.getView(R.id.selectable_text_parent_ll).setBackgroundResource(R.color.grey_background);
                }

            }
        };
    }

   /* @NonNull
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
    }*/

    @OnClick({R.id.tv_submit
            , R.id.tv_all_cancel
            , R.id.tv_all_select
            , R.id.tv_back
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
            case R.id.tv_back:
                if (back != null) {
                    back.chooseMap(leagueSelectedMap);
                }
                closePopupWindow();
                break;
            case R.id.tv_all_cancel:
                for (TableSportInfo t : tList) {
                    leagueSelectedMap.put(t.getLeagueBean().getModuleId(), false);
                }
                contentAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_all_select:
                for (TableSportInfo t : tList) {
                    leagueSelectedMap.put(t.getLeagueBean().getModuleId(), true);
                }
                contentAdapter.notifyDataSetChanged();
                break;

        }

    }

    public interface CallBack {

        void chooseMap(Map<String, Boolean> map);
    }
}
