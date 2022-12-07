package gaming178.com.casinogame.Popupwindow;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.LobbyActivity;
import gaming178.com.casinogame.Bean.PopNewMusicContentBean;
import gaming178.com.casinogame.Util.BackgroudMuzicService;
import gaming178.com.casinogame.Util.ChangePasswordHelper;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.casinogame.login.LanguageHelper;
import gaming178.com.casinogame.login.MenuItemInfo;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.WidgetUtil;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

public class PopGd88Music extends BasePopupWindow {

    TextView tvMusic, tvPassword, tvLimit, tvFinger, tvLg;
    View lineMusic, linePassword, lineLimit, lineFinger, lineLg;
    LinearLayout llMusic, llPassword, llLimit, llFinger, llLg;
    List<PopNewMusicContentBean> contentList;

    BaseActivity baseActivity;
    Dialog musicDialog;
    LinearLayout ll_parent;
    LinearLayout ll_content;
    ImageView img_exit;
    TextView tv_music;
    ImageView img_background_open_close;
    ImageView img_front_open_close;
    List<String> musicList;
    private boolean isGameUi;


    public PopGd88Music(Context context, View v, int width, int height) {
        super(context, v, width, height);
        baseActivity = (BaseActivity) context;
        contentList = new ArrayList<>();
        contentList.add(new PopNewMusicContentBean(tvMusic, lineMusic, llMusic));
        contentList.add(new PopNewMusicContentBean(tvPassword, linePassword, llPassword));
        contentList.add(new PopNewMusicContentBean(tvLimit, lineLimit, llLimit));
        if (baseActivity instanceof LobbyActivity) {
            tvLg.setVisibility(View.GONE);
            lineLg.setVisibility(View.GONE);
            tvFinger.setVisibility(View.VISIBLE);
            lineFinger.setVisibility(View.VISIBLE);
            contentList.add(new PopNewMusicContentBean(tvFinger, lineFinger, llFinger));
            isGameUi = false;
        } else {
            tvFinger.setVisibility(View.GONE);
            lineFinger.setVisibility(View.GONE);
            tvLg.setVisibility(View.VISIBLE);
            lineLg.setVisibility(View.VISIBLE);
            contentList.add(new PopNewMusicContentBean(tvLg, lineLg, llLg));
            isGameUi = true;
        }
        switchContent(0);
        initMusic();
        initPassword();
        initLimit();
        initFinger();
        initLg();
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_gd88_music;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvMusic = view.findViewById(R.id.gd_tv_music);
        tvPassword = view.findViewById(R.id.gd_tv_password);
        tvLimit = view.findViewById(R.id.gd_tv_limit);
        tvLg = view.findViewById(R.id.gd_tv_lg);
        tvFinger = view.findViewById(R.id.gd_tv_finger);
        lineMusic = view.findViewById(R.id.gd_view_line_music);
        linePassword = view.findViewById(R.id.gd_view_line_password);
        lineLimit = view.findViewById(R.id.gd_view_line_limit);
        lineLg = view.findViewById(R.id.gd_view_line_lg);
        lineFinger = view.findViewById(R.id.gd_view_line_finger);
        llMusic = view.findViewById(R.id.gd__ll_music);
        llPassword = view.findViewById(R.id.gd__ll_password);
        llLimit = view.findViewById(R.id.gd__ll_limit);
        llLg = view.findViewById(R.id.gd__ll_lg);
        llFinger = view.findViewById(R.id.gd__ll_finger);
        tvMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchContent(0);
            }
        });
        tvPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchContent(1);
            }
        });
        tvLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchContent(2);
            }
        });
        tvFinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchContent(3);
            }
        });
        tvLg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchContent(3);
            }
        });
        ll_parent = view.findViewById(R.id.ll_parent);
        ll_content = view.findViewById(R.id.ll_content);
        img_exit = view.findViewById(R.id.gd_img_exit);
        int screenWidth = WidgetUtil.getPopScreenWidth((Activity) context);
        int width = screenWidth / 12 * 11;
        ViewGroup.LayoutParams layoutParams = ll_content.getLayoutParams();
        layoutParams.width = width;
        ll_content.setLayoutParams(layoutParams);
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
    }

    private void switchContent(int index) {
        for (int i = 0; i < contentList.size(); i++) {
            PopNewMusicContentBean bean = contentList.get(i);
            if (index == i) {
                bean.getTvName().setTextColor(Color.parseColor("#015500"));
                bean.getViewLine().setBackgroundColor(Color.parseColor("#015500"));
                bean.getViewContent().setVisibility(View.VISIBLE);
            } else {
                bean.getTvName().setTextColor(Color.BLACK);
                bean.getViewLine().setBackgroundColor(Color.parseColor("#00000000"));
                bean.getViewContent().setVisibility(View.INVISIBLE);
            }
        }
    }

    private void initMusic() {
        tv_music = view.findViewById(R.id.tv_music);
        img_background_open_close = view.findViewById(R.id.img_background_open_close);
        img_front_open_close = view.findViewById(R.id.img_front_open_close);
        musicList = Arrays.asList(context.getString(R.string.music1), context.getString(R.string.music2), context.getString(R.string.music3),
                context.getString(R.string.music4), context.getString(R.string.music5), context.getString(R.string.music6));
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
                    if (isGameUi) {
                        baseActivity.mAppViewModel.closeMuzicService(context, BackgroudMuzicService.class);
                    }
                } else {
                    baseActivity.mAppViewModel.setMusicOpen(true);
                    img_background_open_close.setImageResource(R.mipmap.music_open_green);
                    if (isGameUi) {
                        baseActivity.mAppViewModel.startBackgroudMuzicService(baseActivity.mAppViewModel.getMuzicIndex(), baseActivity.componentBack, context, baseActivity.mAppViewModel.getBackgroudVolume());
                    }
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
            View view = LayoutInflater.from(context).inflate(R.layout.gd_dialog_music, null);
            ListView musicListView = view.findViewById(R.id.gd_listView);
            AdapterViewContent<String> viewContent = new AdapterViewContent<>(context, musicListView);
            viewContent.setBaseAdapter(new QuickAdapterImp<String>() {
                @Override
                public int getBaseItemResource() {
                    return R.layout.gd_item_text;
                }

                @Override
                public void convert(ViewHolder helper, String item, int position) {
                    TextView textView = helper.retrieveView(R.id.gd_text_tv1);
                    textView.setText(item);
                    textView.setTextSize(12);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.gray_dark));
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
        if (isGameUi && baseActivity.mAppViewModel.isMusicOpen()) {
            baseActivity.mAppViewModel.startBackgroudMuzicService(baseActivity.mAppViewModel.getMuzicIndex(), baseActivity.componentBack, context, baseActivity.mAppViewModel.getBackgroudVolume());
        }
    }

    private void initPassword() {
        EditText edt_old_passwrod = view.findViewById(R.id.gd__edt_old_passwrod);
        EditText edt_new_passwrod = view.findViewById(R.id.gd__edt_new_passwrod);
        EditText edt_confirm_passwrod = view.findViewById(R.id.gd__edt_confirm_passwrod);
        TextView tv_ok = view.findViewById(R.id.gd__tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = edt_old_passwrod.getText().toString();
                String newPassword = edt_new_passwrod.getText().toString();
                String confirmPassword = edt_confirm_passwrod.getText().toString();
                ChangePasswordHelper changePasswordHelper = new ChangePasswordHelper(oldPassword, newPassword, confirmPassword, baseActivity);
                changePasswordHelper.changePassword();
            }
        });
    }

    private void initLimit() {
        RecyclerView recyclerView = view.findViewById(R.id.gd__base_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        BaseRecyclerAdapter<String> baseRecyclerAdapter = new BaseRecyclerAdapter<String>(context, baseActivity.getSetLimitData(baseActivity.mAppViewModel.getTableId() == 0 ? 1 : baseActivity.mAppViewModel.getTableId()), R.layout.gd_item_popupwindow_text_select_gd88_pop) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                holder.setText(R.id.gd__pop_text_tv, item);
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String s, int position) {
                if ("0 - 0".endsWith(s)) {
                    return;
                }
                if (baseActivity.mAppViewModel.getTableId() == 1 || baseActivity.mAppViewModel.getTableId() == 2 || baseActivity.mAppViewModel.getTableId() == 3 || baseActivity.mAppViewModel.getTableId() == 61 || baseActivity.mAppViewModel.getTableId() == 62 || baseActivity.mAppViewModel.getTableId() == 63 || baseActivity.mAppViewModel.getTableId() == 71) {
                    baseActivity.mAppViewModel.getBaccarat(1).setLimitIndex(position + 1);
                    baseActivity.mAppViewModel.getBaccarat(2).setLimitIndex(position + 1);
                    baseActivity.mAppViewModel.getBaccarat(3).setLimitIndex(position + 1);
                    baseActivity.mAppViewModel.getBaccarat(61).setLimitIndex(position + 1);
                    baseActivity.mAppViewModel.getBaccarat(62).setLimitIndex(position + 1);
                    baseActivity.mAppViewModel.getBaccarat(63).setLimitIndex(position + 1);
                    baseActivity.mAppViewModel.getBaccarat(71).setLimitIndex(position + 1);
                } else if (baseActivity.mAppViewModel.getTableId() == 5) {
                    baseActivity.mAppViewModel.getDragonTiger01().setLimitIndex(position + 1);
                } else if (baseActivity.mAppViewModel.getTableId() == 21) {
                    baseActivity.mAppViewModel.getRoulette01().setLimitIndex(position + 1);
                } else if (baseActivity.mAppViewModel.getTableId() == 31) {
                    baseActivity.mAppViewModel.getSicbo01().setLimitIndex(position + 1);
                }
                closePopupWindow();
            }
        });
        recyclerView.setAdapter(baseRecyclerAdapter);
    }

    private void initFinger() {
        baseActivity.initFingerView(view);
    }

    private void initLg() {
        RecyclerView rc_lg = view.findViewById(R.id.gd__rc_lg);
        rc_lg.setLayoutManager(new GridLayoutManager(context, 2));
        BaseRecyclerAdapter<MenuItemInfo<String>> recyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo<String>>(context, new LanguageHelper(context).getLanguageItems(), R.layout.gd_item_language_selected_gd88_pop) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo<String> item) {
                ImageView ivFlag = holder.getView(R.id.gd__iv_flag_country);
                TextView tvContent = holder.getView(R.id.gd__selectable_text_content_tv);
                tvContent.setText(item.getText());
                ivFlag.setImageResource(item.getRes());
                boolean itemLanguageSelected = new LanguageHelper(mContext).isItemLanguageSelected(item.getType());
                if (itemLanguageSelected) {
                    tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.oval_blue_point_12, 0);
                } else {
                    tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }
        };
        recyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo<String>>() {
            @Override
            public void onItemClick(View view, MenuItemInfo<String> item, int position) {
                AppTool.setAppLanguage(baseActivity, item.getType());
                closePopupWindow();
                baseActivity.onInGameChooseLanguage();
            }
        });
        rc_lg.setAdapter(recyclerAdapter);
    }
}
