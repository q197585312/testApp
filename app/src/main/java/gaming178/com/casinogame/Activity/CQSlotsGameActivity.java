package gaming178.com.casinogame.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.entity.CQSlotsGameInfoBean;
import gaming178.com.casinogame.Util.GlideRoundTransform;
import gaming178.com.casinogame.Util.MyViewPagerAdapter;
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

public class CQSlotsGameActivity extends BaseActivity {
    //    @BindView(R2.id.ll_parent)
    LinearLayout ll_parent;
    //    @BindView(R2.id.viewpager)
    ViewPager viewPager;
    //    @BindView(R2.id.ll_circle)
    LinearLayout ll_circle;
    String lg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_slots;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        lg = AppTool.getAppLanguage(mContext);
        getDataMsg();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!isAttached)
                return;
            if (msg.what == 1) {
                CQSlotsGameInfoBean slotsBean = (CQSlotsGameInfoBean) msg.obj;
                gameCount = slotsBean.getData().size();
                initUi(slotsBean);
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
    private List<GridView> gridViewList;
    private List<TextView> tvList;
    private boolean isJust;
    private int pageSize = 6;
    private int pageCount;
    private int gameCount;

    private void initUi(CQSlotsGameInfoBean slotsBean) {
        if (viewPager == null) {
            return;
        }
        gridViewList = new ArrayList<>();
        if (gameCount % pageSize == 0) {
            isJust = true;
            pageCount = gameCount / pageSize;
        } else {
            isJust = false;
            pageCount = gameCount / pageSize + 1;
        }
        for (int i = 0; i < pageCount; i++) {
            List<CQSlotsGameInfoBean.DataBean> dataBeen = new ArrayList<>();
            int starIndex = 0;
            int endIndex = 0;
            starIndex = i * pageSize;
            if (!isJust && i == pageCount - 1) {
                endIndex = starIndex + gameCount % pageSize;
            } else {
                endIndex = starIndex + pageSize;
            }
            for (int j = starIndex; j < endIndex; j++) {
                dataBeen.add(slotsBean.getData().get(j));
            }
            gridViewList.add(getGridView(dataBeen));
        }
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(gridViewList);
        viewPager.setAdapter(adapter);
        tvList = new ArrayList<>();
        for (int i = 0; i < gridViewList.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp2px(mContext, 5), dp2px(mContext, 5));
            params.rightMargin = 10;
            final TextView textView = new TextView(mContext);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(params);
            if (i == 0) {
                textView.setBackgroundResource(R.drawable.shape_circle_select);
                viewPager.setCurrentItem(0, false);
            } else {
                textView.setBackgroundResource(R.drawable.shape_circle_no_select);
            }
            textView.setTag(i);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int) textView.getTag();
                    viewPager.setCurrentItem(tag, true);
                }
            });
            tvList.add(textView);
            ll_circle.addView(textView);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < tvList.size(); i++) {
                    if (position == i) {
                        tvList.get(i).setBackgroundResource(R.drawable.shape_circle_select);
                    } else {
                        tvList.get(i).setBackgroundResource(R.drawable.shape_circle_no_select);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private GridView getGridView(List<CQSlotsGameInfoBean.DataBean> dataBeen) {
        GridView gridView = (GridView) LayoutInflater.from(mContext).inflate(R.layout.include_gridview, null);
        gridView.setNumColumns(3);
        AdapterViewContent<CQSlotsGameInfoBean.DataBean> adapterViewContent = new AdapterViewContent<>(mContext, gridView);
        adapterViewContent.setBaseAdapter(new QuickAdapterImp<CQSlotsGameInfoBean.DataBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_slots_game;
            }

            @Override
            public void convert(ViewHolder helper, CQSlotsGameInfoBean.DataBean item, int position) {
                ImageView img = helper.retrieveView(R.id.hall_game_pic_iv);
                Glide.with(mContext).load(item.getImgAddress()).centerCrop().transform(new GlideRoundTransform(mContext, 4)).into(img);
                if (!TextUtils.isEmpty(lg) && lg.equals("zh")) {
                    helper.setText(R.id.hall_game_title_tv, item.getCN_name());
                } else {
                    helper.setText(R.id.hall_game_title_tv, item.getEN_name());
                }
            }
        });
        adapterViewContent.setData(dataBeen);
        adapterViewContent.setItemClick(new ItemCLickImp<CQSlotsGameInfoBean.DataBean>() {
            @Override
            public void itemCLick(View view, CQSlotsGameInfoBean.DataBean item, int position) {
                getUrl(item.getId());
            }
        });
        return gridView;
    }

    private boolean isCanLoad = true;

    private void getUrl(final String gameId) {
        if (isCanLoad) {
            isCanLoad = false;
            new Thread() {
                @Override
                public void run() {
                    String url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "cq9path.jsp";
                    String param = "game_usid=" + gameId;
                    String result = mAppViewModel.getHttpClient().sendPostCQ(url, param);
                    //Results=ok#https://iwin2.wxmsg.cn/ifish/AB1/?token=0943a5ba615bfb49df80b78a93e32300#language=en#
                    if (result.startsWith("Results=ok")) {
                        String[] split = result.split("#");
                        String loadUrl = split[1] + "&" + split[2];
                        Intent i = new Intent(mContext, SlotsWebActivity.class);
                        i.putExtra("url", loadUrl);
                        i.putExtra("gameType", "CQ9");
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
                String url = "http://www.grjl25.com/getCQ9Inform.jsp";
                if (mAppViewModel.getHttpClient() == null || TextUtils.isEmpty(url)) {
                    return;
                }
                String result = mAppViewModel.getHttpClient().sendPostCQ(url, "");
                if (result.equals("netError")) {
                    handler.sendEmptyMessage(2);
                    return;
                }
                CQSlotsGameInfoBean slotsBean = new Gson().fromJson(result, CQSlotsGameInfoBean.class);
                if (slotsBean.getResult().equals("Success")) {
                    handler.sendMessage(handler.obtainMessage(1, slotsBean));
                }
            }
        }.start();
    }

    @Override
    protected void initView() {
        super.initView();
        ll_parent = (LinearLayout) findViewById(R.id.ll_parent);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ll_circle = (LinearLayout) findViewById(R.id.ll_circle);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            pageSize = 6;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            pageSize = 15;
        }
    }

}
