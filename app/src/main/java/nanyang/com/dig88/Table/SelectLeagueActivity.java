package nanyang.com.dig88.Table;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.SerializableMap;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.PopMenuItemBean;
import xs.com.mylibrary.base.AdapterViewContent;
import xs.com.mylibrary.base.BasePageAdapter;
import xs.com.mylibrary.base.ItemCLickImp;
import xs.com.mylibrary.base.QuickAdapterImp;
import xs.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2015/12/3.
 * 选择联赛
 */
public class SelectLeagueActivity extends BaseActivity {
    public static final int SELETED_RESULT=0x0000000B;
    @Bind(R.id.list_content_lv)
    ListView listContentLv;
    @Bind(R.id.detail_top_vp)
    ViewPager detailTopVp;
//    @Bind(R.id.detail_top_cpi)
//    CirclePageIndicator detailTopCpi;
    private AdapterViewContent<String> contentList;
    private Map<String,Boolean> selecteds;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_selected_league;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        SerializableMap<String, Boolean> ser = (SerializableMap<String, Boolean>) getIntent().getSerializableExtra(AppConfig.ACTION_KEY_INITENT_OTHER);
        if(ser!=null)
            selecteds= ser.getMap();
//        initSelected(dataList);
        initListData();
        initViewPageData();
        toolbar.setVisibility(View.GONE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }
    private void initViewPageData() {
        List<List<PopMenuItemBean>> alldatas=Arrays.asList(
        Arrays.asList(new PopMenuItemBean(R.drawable.icselectcompetitions, getString(R.string.selected_all), ""),
                new PopMenuItemBean(R.drawable.icselectoddstype, getString(R.string.reverse_selection), "")
        ));
        detailTopVp.setAdapter(new BasePageAdapter<List<PopMenuItemBean>>(mContext,alldatas) {
            @Override
            protected void convert(ViewHolder helper, List<PopMenuItemBean> item, int position) {
                GridView gv = helper.retrieveView(R.id.gridview_content_gv);
                AdapterViewContent<PopMenuItemBean> gvc=new AdapterViewContent<PopMenuItemBean>(mContext,gv);
                gvc.setBaseAdapter(new QuickAdapterImp<PopMenuItemBean>() {
                    @Override
                    public int getBaseItemResource() {
                        return R.layout.base_menu_item;
                    }

                    @Override
                    public void convert(ViewHolder helper, PopMenuItemBean item, int position) {
                        helper.setImageResource(R.id.base_menu_item_iv, item.getDrawableRes());
                        helper.setText(R.id.base_menu_item_tv1, item.getTitle());
                        helper.setText(R.id.base_menu_item_tv2, item.getMark());
                        CheckBox cb=helper.retrieveView(R.id.base_menu_item_cb);
                        if(item.getTitle().equals("15分钟进球数")||(item.getTitle()).equals("特别关注")||(item.getTitle()).equals("主/客队积分")){
                            cb.setVisibility(View.VISIBLE);
                        }else{
                            cb.setVisibility(View.GONE);

                        }
                    }
                });
                gvc.setData(item);
                gvc.setItemClick(new ItemCLickImp<PopMenuItemBean>() {
                    @Override
                    public void itemCLick(View v,PopMenuItemBean cell, int position) {
                        if(cell.getTitle().equals(getString(R.string.selected_all))){
                            changeSelceted(true);

                        }else if(cell.getTitle().equals(getString(R.string.reverse_selection))){
                            changeSelceted(false);
                        }
                    }
                });
            }

            @Override
            protected int getPageLayoutRes() {
                return R.layout.base_gridview_content;
            }
        });
//        detailTopCpi.setViewPager(detailTopVp);


    }

    private void changeSelceted(boolean all) {
        for(Map.Entry<String,Boolean> map:selecteds.entrySet()){
            if(all)
                selecteds.put(map.getKey(),true);
            else
                selecteds.put(map.getKey(),!map.getValue());
        }

        contentList.notifyDataSetChanged();
    }

    private void initListData() {
        contentList=new AdapterViewContent<>(mContext,listContentLv);
        contentList.setBaseAdapter(new QuickAdapterImp<String>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_select_text;
            }

            @Override
            public void convert(ViewHolder helper, String item, int position) {
                TextView tv=helper.retrieveView(R.id.selectable_text_content_tv);
                tv.setText(item);
                if(selecteds.get(item)){
                    tv.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.menu_right_hover,0);
                    helper.setBackgroundColorRes(R.id.selectable_text_parent_ll,R.color.grey_selected_bg);
                }else{
                    tv.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    helper.setBackgroundColorRes(R.id.selectable_text_parent_ll,R.color.white);
                }
            }
        });
        contentList.setItemClick(new ItemCLickImp<String>() {
            @Override
            public void itemCLick(View v,String item, int position) {
                selecteds.put(item,!selecteds.get(item));
                contentList.notifyDataSetChanged();
            }
        });
        Iterator<Map.Entry<String, Boolean>> it=selecteds.entrySet().iterator();
        List<String> listStr=new ArrayList<>();
        while (it.hasNext()){
            Map.Entry<String, Boolean> item = it.next();
            listStr.add(item.getKey());
        }
        contentList.setData(listStr);
    }
//
//    private void initSelected(List<String> list) {
//        if(selecteds==null) {
//            selecteds=new HashMap<>();
//
//            for (String item : list) {
//                selecteds.put(item.getLeagueBean(), true);
//            }
//        }
//    }
    public void clickSubmit(View v){
        Intent in=new Intent();
        SerializableMap<String, Boolean> ser=new SerializableMap<>();
        ser.setMap(selecteds);
        in.putExtra(AppConfig.ACTION_KEY_INITENT_OTHER,ser);
        setResult(SELETED_RESULT,in);
        finish();
    }

}
