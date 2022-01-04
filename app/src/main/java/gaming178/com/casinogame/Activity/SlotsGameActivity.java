package gaming178.com.casinogame.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Bean.SlotsBean;
import gaming178.com.casinogame.Util.GlideRoundTransform;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2018/4/18.
 */

public class SlotsGameActivity extends BaseActivity {
    GridView gridView;
    EditText edtSearch;
    ImageView imgClear;
    LinearLayout ll_parent;
    List<SlotsBean.DataBean> allGameList;
    AdapterViewContent<SlotsBean.DataBean> adapterViewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.gd_activity_slots;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        titleTv.setText(getString(R.string.slots));
        initSearch();
        gridView = findViewById(R.id.gridview_content_gv);
        getDataMsg();
    }

    private void initSearch() {
        if (BuildConfig.FLAVOR.equals("hokicasino88")) {
            toolbar.setNavigationIcon(R.mipmap.search_back);
            toolbar.setBackgroundResource(R.mipmap.bg_search);
            ll_parent = findViewById(R.id.gd__ll_parent);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                ll_parent.setBackgroundResource(R.mipmap.gd_home_bottom_land);
            } else {
                ll_parent.setBackgroundResource(R.mipmap.gd_home_bottom);
            }
        }
        edtSearch = findViewById(R.id.edt_search);
        imgClear = findViewById(R.id.img_clear);
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("");
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (allGameList != null && allGameList.size() > 0) {
                    String text = s.toString();
                    if (!TextUtils.isEmpty(text)) {
                        imgClear.setVisibility(View.VISIBLE);
                        List<SlotsBean.DataBean> list = new ArrayList<>();
                        for (int i = 0; i < allGameList.size(); i++) {
                            SlotsBean.DataBean dataBean = allGameList.get(i);
                            String name = dataBean.getName();
                            if (name.toLowerCase().contains(text.toLowerCase())) {
                                list.add(dataBean);
                            }
                        }
                        adapterViewContent.setData(list);
                    } else {
                        imgClear.setVisibility(View.GONE);
                        adapterViewContent.setData(allGameList);
                    }
                    adapterViewContent.notifyDataSetChanged();
                }
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!isAttached)
                return;
            if (msg.what == 1) {
                SlotsBean slotsBean = (SlotsBean) msg.obj;
                allGameList = slotsBean.getData();
                mAppViewModel.setSlotsBean(slotsBean);
                initUi(slotsBean);
            } else if (msg.what == 2) {
                Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };

    private void initUi(SlotsBean slotsBean) {
        gridView.setNumColumns(3);
        adapterViewContent = new AdapterViewContent<>(mContext, gridView);
        adapterViewContent.setBaseAdapter(new QuickAdapterImp<SlotsBean.DataBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_slots_game;
            }

            @Override
            public void convert(ViewHolder helper, SlotsBean.DataBean item, int position) {
                ImageView img = helper.retrieveView(R.id.gd__hall_game_pic_iv);
                Glide.with(mContext).load(item.getImgAddress()).centerCrop().transform(new GlideRoundTransform(mContext, 4)).into(img);
                helper.setText(R.id.gd__hall_game_title_tv, item.getName());
            }
        });
        adapterViewContent.setData(slotsBean.getData());
        adapterViewContent.setItemClick(new ItemCLickImp<SlotsBean.DataBean>() {
            @Override
            public void itemCLick(View view, SlotsBean.DataBean item, int position) {
                //https://www.slotsgamingonline.com/loader.php?platform=html5&language=en&game=safarisam&id=67&username=username&token=token
                mAppViewModel.setSlideGameType("SLOTS");
                mAppViewModel.setSlotsCurrentIndex(position);
                Intent i = new Intent(mContext, SlotsWebActivity.class);
                String url = item.getUrl() + "?" + "platform=" + item.getPlatform() + "&language=" + item.getLanguage() + "&game=" + item.getGame() +
                        "&id=" + item.getId() + "&username=" + item.getUsername() + "&token=" + item.getToken();
                i.putExtra("url", url);
                i.putExtra("gameType", "SLOTS");
                startActivity(i);
            }
        });
    }

    private void getDataMsg() {
        setLayout.setVisibility(View.GONE);
        setMoreToolbar(true);
        setToolbarNameAndBalance();
        new Thread() {
            @Override
            public void run() {
                String url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "getSlotsInform.jsp";
                if (TextUtils.isEmpty(url) || mAppViewModel.getHttpClient() == null) {
                    return;
                }
                String result = mAppViewModel.getHttpClient().sendPost(url, "");
                if (result.equals("netError") || result.contains("=no")) {
                    handler.sendEmptyMessage(2);
                    return;
                }
                SlotsBean slotsBean = new Gson().fromJson(result, SlotsBean.class);
                if (slotsBean.getResult().equals("Success")) {
                    handler.sendMessage(handler.obtainMessage(1, slotsBean));
                }
            }
        }.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        if (!WidgetUtil.isRunBackground(this)) {
//            if (currentFragment != null) {
//                getSupportFragmentManager().beginTransaction().remove(currentFragment).commitAllowingStateLoss();
//            }
//        }
//        super.onSaveInstanceState(outState);
    }

}
