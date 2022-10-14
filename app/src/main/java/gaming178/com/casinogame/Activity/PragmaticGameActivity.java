package gaming178.com.casinogame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.entity.PragmaticGameBean;
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

public class PragmaticGameActivity extends SearchBaseActivity {
    GridView gridView;
    List<PragmaticGameBean.DataBean> allGameList;
    AdapterViewContent<PragmaticGameBean.DataBean> adapterViewContent;

    String lang;

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
                List<PragmaticGameBean.DataBean> list = new ArrayList<>();
                for (int i = 0; i < allGameList.size(); i++) {
                    PragmaticGameBean.DataBean dataBean = allGameList.get(i);
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!isAttached)
                return;
            if (msg.what == 1) {
                PragmaticGameBean slotsBean = (PragmaticGameBean) msg.obj;
                allGameList = slotsBean.getData();
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

    private void initUi(PragmaticGameBean slotsBean) {
        gridView.setNumColumns(3);
        adapterViewContent = new AdapterViewContent<>(mContext, gridView);
        adapterViewContent.setBaseAdapter(new QuickAdapterImp<PragmaticGameBean.DataBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_slots_game;
            }

            @Override
            public void convert(ViewHolder helper, PragmaticGameBean.DataBean item, int position) {
                ImageView img = helper.retrieveView(R.id.gd__hall_game_pic_iv);
                Glide.with(mContext).load(item.getImgAddress()).centerCrop().transform(new GlideRoundTransform(mContext, 4)).into(img);
                helper.setText(R.id.gd__hall_game_title_tv, item.getGame());
            }
        });
        adapterViewContent.setData(slotsBean.getData());
        adapterViewContent.setItemClick(new ItemCLickImp<PragmaticGameBean.DataBean>() {
            @Override
            public void itemCLick(View view, PragmaticGameBean.DataBean item, int position) {
                getUrl(item.getId());
            }
        });
    }

    private boolean isCanLoad = true;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        titleTv.setText(getString(R.string.pragmatic));
        gridView = findViewById(R.id.gridview_content_gv);
        String appLanguage = AppTool.getAppLanguage(mContext);
        if (appLanguage.equals("my")) {
            lang = "id";
        } else {
            lang = "en";
        }
        getDataMsg();
    }

    private void getUrl(final String gameId) {
        if (isCanLoad) {
            isCanLoad = false;
            new Thread() {
                @Override
                public void run() {
                    String url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "pragmaticpath.jsp?";
                    String param = "gameid=" + gameId + "&technology=H5&platform=MOBILE&lang="+lang;
                    String result = mAppViewModel.getHttpClient().sendPostCQ(url + param, "");
                    if (result.startsWith("Results=ok")) {
                        String[] split = result.split("#");
                        Intent i = new Intent(mContext, SlotsWebActivity.class);
                        i.putExtra("url", split[1]);
                        i.putExtra("gameType", "Pra");
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
                String url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "getPragmaticInform.jsp";
                if (mAppViewModel.getHttpClient() == null || TextUtils.isEmpty(url)) {
                    return;
                }
                String result = mAppViewModel.getHttpClient().sendPostCQ(url, "");
                if (result.equals("netError")) {
                    handler.sendEmptyMessage(2);
                    return;
                }
                PragmaticGameBean slotsBean = new Gson().fromJson(result, PragmaticGameBean.class);
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
