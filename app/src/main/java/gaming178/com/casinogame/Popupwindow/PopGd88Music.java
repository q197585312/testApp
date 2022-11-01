package gaming178.com.casinogame.Popupwindow;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.BackgroudMuzicService;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.WidgetUtil;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

public class PopGd88Music extends BasePopupWindow {
    BaseActivity baseActivity;
    Dialog musicDialog;
    LinearLayout ll_parent;
    LinearLayout ll_content;
    ImageView img_exit;
    TextView tv_music;
    ImageView img_background_open_close;
    ImageView img_front_open_close;
    List<String> musicList;
    public PopGd88Music(Context context, View v, int width, int height) {
        super(context, v, width, height);
        baseActivity = (BaseActivity) context;
        initMusic();
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_gd88_music;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ll_parent = view.findViewById(R.id.ll_parent);
        ll_content = view.findViewById(R.id.ll_content);
        img_exit = view.findViewById(R.id.gd_img_exit);
        tv_music = view.findViewById(R.id.tv_music);
        img_background_open_close = view.findViewById(R.id.img_background_open_close);
        img_front_open_close = view.findViewById(R.id.img_front_open_close);
        int screenWidth = WidgetUtil.getPopScreenWidth((Activity) context);
        int width = screenWidth / 5 * 4;
        ViewGroup.LayoutParams layoutParams = ll_content.getLayoutParams();
        layoutParams.width = width;
        ll_content.setLayoutParams(layoutParams);
        musicList = Arrays.asList(context.getString(R.string.music1), context.getString(R.string.music2), context.getString(R.string.music3),
                context.getString(R.string.music4), context.getString(R.string.music5), context.getString(R.string.music6));
        ll_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        tv_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMusicDialog();
            }
        });
        img_background_open_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baseActivity.mAppViewModel.isMusicOpen()) {
                    baseActivity.mAppViewModel.setMusicOpen(false);
                    img_background_open_close.setImageResource(R.mipmap.music_close_grey);
                } else {
                    baseActivity.mAppViewModel.setMusicOpen(true);
                    img_background_open_close.setImageResource(R.mipmap.music_open_green);
                }
            }
        });
        img_front_open_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baseActivity.mAppViewModel.isFrontMusicOpen()) {
                    baseActivity.mAppViewModel.setFrontMusicOpen(false);
                    img_front_open_close.setImageResource(R.mipmap.music_close_grey);
                } else {
                    baseActivity.mAppViewModel.setFrontMusicOpen(true);
                    img_front_open_close.setImageResource(R.mipmap.music_open_green);
                }
            }
        });
    }

    private void initMusic() {
        int musicIndex = baseActivity.mAppViewModel.getMuzicIndex();
        tv_music.setText(musicList.get(musicIndex - 1));
        if (baseActivity.mAppViewModel.isMusicOpen()) {
            img_background_open_close.setImageResource(R.mipmap.music_open_green);
        } else {
            img_background_open_close.setImageResource(R.mipmap.music_close_grey);
        }
        if (baseActivity.mAppViewModel.isFrontMusicOpen()) {
            img_front_open_close.setImageResource(R.mipmap.music_open_green);
        } else {
            img_front_open_close.setImageResource(R.mipmap.music_close_grey);
        }
    }

    private void showMusicDialog() {
        if (musicDialog == null) {
            musicDialog = new Dialog(context, R.style.Music_Dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_music, null);
            ListView musicListView = view.findViewById(R.id.listView);
            AdapterViewContent<String> viewContent = new AdapterViewContent<>(context, musicListView);
            viewContent.setBaseAdapter(new QuickAdapterImp<String>() {
                @Override
                public int getBaseItemResource() {
                    return R.layout.item_text;
                }

                @Override
                public void convert(ViewHolder helper, String item, int position) {
                    helper.setText(R.id.text_tv1, item);
                    helper.setTextSize(R.id.text_tv1, 12);
                    helper.setTextColor(R.id.text_tv1, ContextCompat.getColor(context, R.color.gray_dark));
                }
            });
            viewContent.setItemClick(new ItemCLickImp<String>() {
                @Override
                public void itemCLick(View view, String item, int position) {
                    tv_music.setText(item);
                    chooseMusic(position);
                    musicDialog.dismiss();
                }
            });

            viewContent.setData(musicList);
            musicDialog.setContentView(view);
            Window window = musicDialog.getWindow();
            window.setWindowAnimations(R.style.popWindow_animation);
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.LEFT | Gravity.TOP;
            params.width = tv_music.getWidth();
            int location[] = new int[2];
            tv_music.getLocationOnScreen(location);
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                params.x = location[0] - tv_music.getWidth() / 2;
            } else {
                params.x = location[0];
            }
            params.y = location[1] + tv_music.getHeight();
            window.setAttributes(params);
        }
        musicDialog.show();
    }

    private void chooseMusic(int position) {
        baseActivity.mAppViewModel.setMuzicIndex(position + 1);
        if (baseActivity.mAppViewModel.isbLobby() == false) {
            baseActivity.mAppViewModel.startBackgroudMuzicService(baseActivity.mAppViewModel.getMuzicIndex(), baseActivity.componentBack, context, baseActivity.mAppViewModel.getBackgroudVolume());
        }
    }
}
