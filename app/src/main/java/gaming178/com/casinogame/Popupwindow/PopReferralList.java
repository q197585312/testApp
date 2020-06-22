package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.LobbyActivity;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.mylibrary.base.QuickBaseAdapter;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2020/4/20.
 */

public class PopReferralList extends BasePopupWindow {
    public PopReferralList(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_referral_list;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    initAdapter((List<String>) msg.obj);
                    break;
            }
        }
    };

    private void initAdapter(List<String> dataList) {
        QuickBaseAdapter<String> adapter = new QuickBaseAdapter<String>(context, R.layout.item_referral_list, dataList) {
            @Override
            protected void convert(ViewHolder helper, String item, int position) {
                TextView tvContent = helper.retrieveView(R.id.tv_content);
                tvContent.setText(item);
            }
        };
        lv_content.setAdapter(adapter);
    }

    ImageView img_exit;
    ListView lv_content;

    @Override
    protected void initView(View view) {
        super.initView(view);
        img_exit = view.findViewById(R.id.img_exit);
        lv_content = view.findViewById(R.id.lv_content);
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        new Thread() {
            @Override
            public void run() {
                LobbyActivity activity = (LobbyActivity) context;
                String url = WebSiteUrl.REFERRAL_LIST;
                String param = "Usid=" + activity.getApp().getUser().getName();
                if (activity.getApp().getHttpClient() != null) {
                    String s = activity.getApp().getHttpClient().sendPost(url, param);
                    //Results=ok,RAJA02,RAJA03,RAJA04,RAJA13,RAJA43,
                    List<String> dataList = new ArrayList<>();
                    if (s.startsWith("Results=ok")) {
                        String[] split = s.split(",");
                        for (int i = 0; i < split.length; i++) {
                            String content = split[i];
                            if (!content.equals("Results=ok") && !content.equals("null")) {
                                dataList.add(content);
                            }
                        }
                    }
                    handler.sendMessage(handler.obtainMessage(1, dataList));
                }
            }
        }.start();
    }
}
