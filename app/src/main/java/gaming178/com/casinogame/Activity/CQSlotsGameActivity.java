package gaming178.com.casinogame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.entity.CQSlotsGameInfoBean;
import gaming178.com.casinogame.Util.GlideRoundTransform;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2018/4/18.
 */

public class CQSlotsGameActivity extends SearchBaseActivity {
    String lg;
    GridView gridView;
    List<CQSlotsGameInfoBean.DataBean> allGameList;
    AdapterViewContent<CQSlotsGameInfoBean.DataBean> adapterViewContent;
    private boolean isCanLoad = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!isAttached)
                return;
            if (msg.what == 1) {
                CQSlotsGameInfoBean slotsBean = (CQSlotsGameInfoBean) msg.obj;
                allGameList = slotsBean.getData();
                mAppViewModel.setCqSlotsBean(slotsBean);
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

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        titleTv.setText(getString(R.string.cq));
        gridView = findViewById(R.id.gridview_content_gv);
        lg = AppTool.getAppLanguage(mContext);
        getDataMsg();
    }

    @Override
    int layout() {
        return R.layout.gd_activity_slots;
    }

    @Override
    void input(Editable s) {
        if (allGameList != null && allGameList.size() > 0) {
            String text = s.toString();
            if (!TextUtils.isEmpty(text)) {
                imgClear.setVisibility(View.VISIBLE);
                List<CQSlotsGameInfoBean.DataBean> list = new ArrayList<>();
                for (int i = 0; i < allGameList.size(); i++) {
                    CQSlotsGameInfoBean.DataBean dataBean = allGameList.get(i);
                    String name = dataBean.getEN_name();
                    if (!TextUtils.isEmpty(lg) && lg.equals("zh")) {
                        name = dataBean.getCN_name();
                    }
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

    private void initUi(CQSlotsGameInfoBean slotsBean) {
        gridView.setNumColumns(3);
        adapterViewContent = new AdapterViewContent<>(mContext, gridView);
        adapterViewContent.setBaseAdapter(new QuickAdapterImp<CQSlotsGameInfoBean.DataBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_slots_game;
            }

            @Override
            public void convert(ViewHolder helper, CQSlotsGameInfoBean.DataBean item, int position) {
                ImageView img = helper.retrieveView(R.id.gd__hall_game_pic_iv);
                img.post(new Runnable() {
                    @Override
                    public void run() {
                        int width = img.getWidth();
                        int height = (int) (width / 1.72);
                        ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
                        layoutParams.height = height;
                        img.setLayoutParams(layoutParams);
                        Glide.with(mContext).load(item.getImgAddress()).centerCrop().transform(new GlideRoundTransform(mContext, 4)).into(img);
                    }
                });
                if (!TextUtils.isEmpty(lg) && lg.equals("zh")) {
                    helper.setText(R.id.gd__hall_game_title_tv, item.getCN_name());
                } else {
                    helper.setText(R.id.gd__hall_game_title_tv, item.getEN_name());
                }
            }
        });
        adapterViewContent.setData(slotsBean.getData());
        adapterViewContent.setItemClick(new ItemCLickImp<CQSlotsGameInfoBean.DataBean>() {
            @Override
            public void itemCLick(View view, CQSlotsGameInfoBean.DataBean item, int position) {
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
                    String url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "cq9path.jsp";
                    String param = "game_usid=" + gameId;
                    String result = mAppViewModel.getHttpClient().sendPostCQ(url, param);
                    //Results=ok#https://iwin2.wxmsg.cn/ifish/AB1/?token=0943a5ba615bfb49df80b78a93e32300#language=en#
                    if (result.startsWith("Results=ok")) {
                        String[] split = result.split("#");
                        String loadUrl = split[1] + "&" + split[2];
                        mAppViewModel.setSlideGameType("CQ9");
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
    protected void onSaveInstanceState(Bundle outState) {
//        if (!WidgetUtil.isRunBackground(this)) {
//            if (currentFragment != null) {
//                getSupportFragmentManager().beginTransaction().remove(currentFragment).commitAllowingStateLoss();
//            }
//        }
//        super.onSaveInstanceState(outState);
    }
}
