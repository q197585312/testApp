package gaming178.com.casinogame.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import gaming178.com.casinogame.Bean.SlotsBean;
import gaming178.com.casinogame.Util.GlideRoundTransform;
import gaming178.com.casinogame.Util.MyViewPager;
import gaming178.com.casinogame.Util.MyViewPagerAdapter;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;

/**
 * Created by Administrator on 2018/4/18.
 */

public class SlotsGameActivity extends BaseActivity {
    //    @BindView(R2.id.gd__ll_parent)
    LinearLayout ll_parent;
    //    @BindView(R2.id.gd__viewpager)
    MyViewPager viewPager;
    //    @BindView(R2.id.gd__ll_circle)
    LinearLayout ll_circle;

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
        getDataMsg();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!isAttached)
                return;
            if (msg.what == 1) {
                SlotsBean slotsBean = (SlotsBean) msg.obj;
                mAppViewModel.setSlotsBean(slotsBean);
                gameCount = slotsBean.getData().size();
                initUi(slotsBean);
            } else if (msg.what == 2) {
                Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };
    private List<View> gridViewList;
    private List<TextView> tvList;
    private boolean isJust;
    private int pageSize = 6;
    private int pageCount;
    private int gameCount;

    private void initUi(SlotsBean slotsBean) {
        if (viewPager == null) {
            return;
        }
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                gridViewList = new ArrayList<>();
                if (gameCount % pageSize == 0) {
                    isJust = true;
                    pageCount = gameCount / pageSize;
                } else {
                    isJust = false;
                    pageCount = gameCount / pageSize + 1;
                }
                for (int i = 0; i < pageCount; i++) {
                    List<SlotsBean.DataBean> dataBeen = new ArrayList<>();
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
                        textView.setBackgroundResource(R.drawable.gd_shape_circle_select);
                        viewPager.setCurrentItem(0, false);
                    } else {
                        textView.setBackgroundResource(R.drawable.gd_shape_circle_no_select);
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
                                tvList.get(i).setBackgroundResource(R.drawable.gd_shape_circle_select);
                            } else {
                                tvList.get(i).setBackgroundResource(R.drawable.gd_shape_circle_no_select);
                            }
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }
        });
    }

    private View getGridView(List<SlotsBean.DataBean> dataBeen) {
        LinearLayout llParent = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.include_ll, null);
        int height = viewPager.getHeight();
        int size = dataBeen.size();
        int count;
        if (size >= 3) {
            if (size % 3 == 0) {
                count = size / 3;
            } else {
                count = size / 3 + 1;
            }
        } else {
            count = 1;
        }
        int orientation = getResources().getConfiguration().orientation;
        double heightChild;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            heightChild = height / 5.1;
        } else {
            heightChild = height / 2.1;
        }
        for (int i = 0; i < count; i++) {
            LinearLayout llChild = new LinearLayout(mContext);
            llChild.setOrientation(LinearLayout.HORIZONTAL);
            ViewGroup.LayoutParams layoutParamsChild = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) heightChild);
            llChild.setLayoutParams(layoutParamsChild);
            A:
            for (int j = 0; j < 3; j++) {
                int index;
                if (j == 0) {
                    index = 3 * i;
                } else if (j == 1) {
                    index = 3 * i + 1;
                } else {
                    index = 3 * i + 2;
                }
                if (index > size - 1) {
                    int childCount = llChild.getChildCount();
                    if (childCount < 3) {
                        for (int k = childCount; k < 3; k++) {
                            LinearLayout linearLayout = new LinearLayout(mContext);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.weight = 1;
                            linearLayout.setLayoutParams(params);
                            llChild.addView(linearLayout);
                        }
                    }
                    break A;
                }
                View view = LayoutInflater.from(mContext).inflate(R.layout.gd_item_slots_game, null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.weight = 1;
                view.setLayoutParams(params);
                SlotsBean.DataBean item = dataBeen.get(index);
                ImageView img = view.findViewById(R.id.gd__hall_game_pic_iv);
                Glide.with(mContext).load(item.getImgAddress()).centerCrop().transform(new GlideRoundTransform(mContext, 4)).into(img);
                TextView tv = view.findViewById(R.id.gd__hall_game_title_tv);
                tv.setText(item.getName());
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(mContext, SlotsWebActivity.class);
                        String url = item.getUrl() + "?" + "platform=" + item.getPlatform() + "&language=" + item.getLanguage() + "&game=" + item.getGame() +
                                "&id=" + item.getId() + "&username=" + item.getUsername() + "&token=" + item.getToken();
                        i.putExtra("url", url);
                        i.putExtra("gameType", "SLOTS");
                        startActivity(i);
                    }
                });
                llChild.addView(view);
            }
            llParent.addView(llChild);
        }


//        GrapeGridview gridView = (GrapeGridview) LayoutInflater.from(mContext).inflate(R.layout.include_gridview, null);
//        gridView.setNumColumns(3);
//        AdapterViewContent<SlotsBean.DataBean> adapterViewContent = new AdapterViewContent<>(mContext, gridView);
//        adapterViewContent.setBaseAdapter(new QuickAdapterImp<SlotsBean.DataBean>() {
//            @Override
//            public int getBaseItemResource() {
//                return R.layout.gd_item_slots_game;
//            }
//
//            @Override
//            public void convert(ViewHolder helper, SlotsBean.DataBean item, int position) {
//                ImageView img = helper.retrieveView(R.id.gd__hall_game_pic_iv);
//                Glide.with(mContext).load(item.getImgAddress()).centerCrop().transform(new GlideRoundTransform(mContext, 4)).into(img);
//                helper.setText(R.id.gd__hall_game_title_tv, item.getName());
//            }
//        });
//        adapterViewContent.setData(dataBeen);
//        adapterViewContent.setItemClick(new ItemCLickImp<SlotsBean.DataBean>() {
//            @Override
//            public void itemCLick(View view, SlotsBean.DataBean item, int position) {
//                Log.d("Math.abs", "SlotsBean: ");
//                //https://www.slotsgamingonline.com/loader.php?platform=html5&language=en&game=safarisam&id=67&username=username&token=token
//                mAppViewModel.setSlideGameType("SLOTS");
//                mAppViewModel.setSlotsCurrentIndex(position);
//                Intent i = new Intent(mContext, SlotsWebActivity.class);
//                String url = item.getUrl() + "?" + "platform=" + item.getPlatform() + "&language=" + item.getLanguage() + "&game=" + item.getGame() +
//                        "&id=" + item.getId() + "&username=" + item.getUsername() + "&token=" + item.getToken();
//                i.putExtra("url", url);
//                i.putExtra("gameType", "SLOTS");
//                startActivity(i);
//            }
//        });
        return llParent;
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
    protected void initView() {
        super.initView();
        ll_parent = (LinearLayout) findViewById(R.id.gd__ll_parent);
        viewPager = (MyViewPager) findViewById(R.id.gd__viewpager);
        ll_circle = (LinearLayout) findViewById(R.id.gd__ll_circle);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            pageSize = 6;

        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            pageSize = 15;

        }
    }

}
