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
import android.view.ViewGroup;
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
import gaming178.com.casinogame.Activity.entity.PGGameBean;
import gaming178.com.casinogame.Util.GlideRoundTransform;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2018/4/18.
 */

public class PGGameActivity extends BaseActivity {
    String lg;
    GridView gridView;
    EditText edtSearch;
    ImageView imgClear;
    LinearLayout ll_parent;
    List<PGGameBean.DataBean> allGameList;
    AdapterViewContent<PGGameBean.DataBean> adapterViewContent;
    private boolean isCanLoad = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!isAttached)
                return;
            if (msg.what == 1) {
                PGGameBean pgGameBean = (PGGameBean) msg.obj;
                allGameList = pgGameBean.getData();
                initUi(pgGameBean);
            } else if (msg.what == 2) {
                Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                finish();
            } else if (msg.what == 3) {
                Toast.makeText(mContext, "login error", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 4) {
                Toast.makeText(mContext, "Game is under maintenance", Toast.LENGTH_SHORT).show();
            }
        }
    };

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
//        titleTv.setText(getString(R.string.king_kong));
        initSearch();
        gridView = findViewById(R.id.gridview_content_gv);
        lg = AppTool.getAppLanguage(mContext);
        getDataMsg();
    }

    private void initSearch() {
        if (BuildConfig.FLAVOR.equals("hokicasino88") || BuildConfig.FLAVOR.equals("doacasino")) {
            toolbar.setNavigationIcon(R.mipmap.search_back);
            toolbar.setBackgroundResource(R.mipmap.bg_search);
            if (BuildConfig.FLAVOR.equals("hokicasino88")) {
                ll_parent = findViewById(R.id.gd__ll_parent);
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ll_parent.setBackgroundResource(R.mipmap.gd_home_bottom_land);
                } else {
                    ll_parent.setBackgroundResource(R.mipmap.gd_home_bottom);
                }
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
                        List<PGGameBean.DataBean> list = new ArrayList<>();
                        for (int i = 0; i < allGameList.size(); i++) {
                            PGGameBean.DataBean dataBean = allGameList.get(i);
                            String name = dataBean.getGame();
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

    private void initUi(PGGameBean pgGameBean) {
        gridView.setNumColumns(3);
        adapterViewContent = new AdapterViewContent<>(mContext, gridView);
        adapterViewContent.setBaseAdapter(new QuickAdapterImp<PGGameBean.DataBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_slots_game;
            }

            @Override
            public void convert(ViewHolder helper, PGGameBean.DataBean item, int position) {
                ImageView img = helper.retrieveView(R.id.gd__hall_game_pic_iv);
                img.post(new Runnable() {
                    @Override
                    public void run() {
                        int width = img.getWidth();
                        int height = width;
                        ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
                        layoutParams.height = height;
                        img.setLayoutParams(layoutParams);
                        Glide.with(mContext).load(item.getImgAddress()).centerCrop().transform(new GlideRoundTransform(mContext, 4)).into(img);
                    }
                });
                helper.setText(R.id.gd__hall_game_title_tv, item.getGame());
            }
        });
        adapterViewContent.setData(pgGameBean.getData());
        adapterViewContent.setItemClick(new ItemCLickImp<PGGameBean.DataBean>() {
            @Override
            public void itemCLick(View view, PGGameBean.DataBean item, int position) {
                getUrl(item.getId());
            }
        });
    }

    private void getUrl(final String gameId) {
        if (isCanLoad) {
            isCanLoad = false;
            new Thread() {
                @Override
                public void run() {
                    String url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "pgpath.jsp?";
                    String param = "gamecode=" + gameId;
                    String result = mAppViewModel.getHttpClient().sendPostCQ(url + param, "");
                    if (result.startsWith("Results=ok")) {
                        String[] split = result.split("#");
                        Intent i = new Intent(mContext, SlotsWebActivity.class);
                        i.putExtra("url", split[1]);
                        i.putExtra("gameType", "PG");
                        startActivity(i);
                    } else if (result.startsWith("Results=no")) {
                        handler.sendEmptyMessage(4);
                    } else {
                        handler.sendEmptyMessage(3);
                    }
                    isCanLoad = true;
                }
            }.start();
        }
    }

    private void getDataMsg() {
        setLayout.setVisibility(View.GONE);
        setMoreToolbar(true);
        setToolbarNameAndBalance();

        new Thread() {
            @Override
            public void run() {
                String url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "getPGInform.jsp";
                if (mAppViewModel.getHttpClient() == null || TextUtils.isEmpty(url)) {
                    return;
                }
                String result = mAppViewModel.getHttpClient().sendPostCQ(url, "");
                if (result.equals("netError")) {
                    handler.sendEmptyMessage(2);
                    return;
                }
                PGGameBean pgGameBean = new Gson().fromJson(result, PGGameBean.class);
                if (pgGameBean.getResult().equals("Success")) {
                    handler.sendMessage(handler.obtainMessage(1, pgGameBean));
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
